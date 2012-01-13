/* 
 * JBoss, Home of Professional Open Source 
 * Copyright 2011 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved. 
 * See the copyright.txt in the distribution for a 
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use, 
 * modify, copy, or redistribute it subject to the terms and conditions 
 * of the GNU Lesser General Public License, v. 2.1. 
 * This program is distributed in the hope that it will be useful, but WITHOUT A 
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A 
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details. 
 * You should have received a copy of the GNU Lesser General Public License, 
 * v.2.1 along with this distribution; if not, write to the Free Software 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, 
 * MA  02110-1301, USA.
 */
package org.switchyard.quickstarts.demos.reporting.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.inject.Inject;

import org.switchyard.component.bean.Reference;
import org.switchyard.component.bean.Service;
import org.switchyard.quickstarts.demos.reporting.ReportStatus;
import org.switchyard.quickstarts.demos.reporting.ReportSummary;
import org.switchyard.quickstarts.demos.reporting.beans.ReportIdBean;
import org.switchyard.quickstarts.demos.reporting.beans.ReportNotesBean;
import org.switchyard.quickstarts.demos.reporting.beans.ReportStatusBean;
import org.switchyard.quickstarts.demos.reporting.beans.ReportSummaryBean;
import org.switchyard.quickstarts.demos.reporting.events.AttachNotesEvent;
import org.switchyard.quickstarts.demos.reporting.events.ReportGeneratedEvent;
import org.switchyard.quickstarts.demos.reporting.events.ReportStatusEvent;
import org.switchyard.quickstarts.demos.reporting.events.ReportSummaryQueryFilter;
import org.switchyard.quickstarts.demos.reporting.events.ReportTransmissionFailedEvent;
import org.switchyard.quickstarts.demos.reporting.services.AlertService;
import org.switchyard.quickstarts.demos.reporting.services.ManagementService;
import org.switchyard.quickstarts.demos.reporting.services.ReportQueryService;

/**
 * 
 */
@Service(ManagementService.class)
public class ManagementServiceBean implements ManagementService, ReportQueryService {

    @Inject @Reference("AlertService")
    private AlertService alertService;
    private ConcurrentMap<ReportId, ReportSummaryBean> repository;

    public ManagementServiceBean() {
        this.repository = new ConcurrentHashMap<ReportId, ReportSummaryBean>();
    }

    @Override
    public void reportRequired(ReportId reportId) {
        ReportSummaryBean rsb = new ReportSummaryBean();
        reportId = new ReportIdBean(reportId);
        rsb.setReportId(reportId);
        if (repository.putIfAbsent(reportId, rsb) != null) {
            // TODO: should we consider throwing an exception?
        }
    }

    @Override
    public void reportGenerated(final ReportGeneratedEvent event) {
        ReportSummaryBean rsb = getReportSummaryBean(event.getReportId());
        if (rsb == null) {
            // TODO: should we consider throwing an exception or creating a
            // report summary?
            return;
        }
        rsb.setSummary(event.getReportSummary());
        rsb.setStatus(new ReportStatusBean(ReportStatus.Type.GENERATED, event.getTimestamp()));
    }

    @Override
    public void reportTrasmitted(final ReportStatusEvent event) {
        ReportSummaryBean rsb = getReportSummaryBean(event.getReportId());
        if (rsb == null) {
            // TODO: should we consider throwing an exception or creating a
            // report summary?
            return;
        }
        rsb.setStatus(new ReportStatusBean(ReportStatus.Type.TRANSMITTED, event.getTimestamp()));
    }

    @Override
    public void reportTransmissionFailed(final ReportTransmissionFailedEvent event) {
        ReportSummaryBean rsb = getReportSummaryBean(event.getReportId());
        if (rsb == null) {
            // TODO: should we consider throwing an exception or creating a
            // report summary?
            return;
        }
        rsb.setStatus(new ReportStatusBean(ReportStatus.Type.TRANSMISSION_FAILED, event.getTimestamp()));
        alertService.notifyTransmissionFailed(event.getReportId());
    }

    @Override
    public void reportAcknowledged(final ReportStatusEvent event) {
        ReportSummaryBean rsb = getReportSummaryBean(event.getReportId());
        if (rsb == null) {
            // TODO: should we consider throwing an exception or creating a
            // report summary?
            return;
        }
        rsb.setStatus(new ReportStatusBean(ReportStatus.Type.ACKNOWLEDGED, event.getTimestamp()));
    }

    @Override
    public void reportProcessed(final ReportStatusEvent event) {
        ReportSummaryBean rsb = getReportSummaryBean(event.getReportId());
        if (rsb == null) {
            // TODO: should we consider throwing an exception or creating a
            // report summary?
            return;
        }
        rsb.setStatus(new ReportStatusBean(ReportStatus.Type.PROCESSED, event.getTimestamp()));
    }

    @Override
    public void attachNotes(final AttachNotesEvent event) {
        ReportSummaryBean rsb = getReportSummaryBean(event.getReportId());
        if (rsb == null) {
            // TODO: should we consider throwing an exception or creating a
            // report summary?
            return;
        }
        rsb.addNotes(new ReportNotesBean(event.getNotes(), event.getTimestamp()));
    }

    @Override
    public void reportClosed(final ReportStatusEvent event) {
        ReportSummaryBean rsb = getReportSummaryBean(event.getReportId());
        if (rsb == null) {
            // TODO: should we consider throwing an exception or creating a
            // report summary?
            return;
        }
        rsb.setStatus(new ReportStatusBean(ReportStatus.Type.CLOSED, event.getTimestamp()));
    }

    @Override
    public ReportSummary getReportSummary(ReportId reportId) {
        return getReportSummaryBean(reportId);
    }

    @Override
    public Collection<ReportSummary> getReportSummaries(final ReportSummaryQueryFilter filter) {
        final String clientId = filter.getClientId();
        final String destinationId = filter.getDestinationId();
        final Date fromDate = filter.getFromDate();
        final Date toDate = filter.getToDate();
        List<ReportSummary> retVal = new ArrayList<ReportSummary>();
        for (ReportSummary summary : repository.values()) {
            final ReportId reportId = summary.getReportId();
            if ((clientId == null || clientId.length() == 0 || clientId.equals(reportId.getClientId()))
                    && (destinationId == null || destinationId.length() == 0 || destinationId.equals(reportId
                            .getDestinationId()))
                    && (fromDate == null || fromDate.compareTo(reportId.getReportingDate()) <= 0)
                    && (toDate == null || toDate.after(reportId.getReportingDate()))) {
                retVal.add(summary);
            }
        }
        return retVal;
    }

    private ReportSummaryBean getReportSummaryBean(ReportId reportId) {
        // normalize the id
        reportId = new ReportIdBean(reportId);
        return repository.get(reportId);
    }

}
