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

import java.util.Collection;
import java.util.Date;

import javax.inject.Inject;

import org.switchyard.component.bean.Reference;
import org.switchyard.component.bean.Service;
import org.switchyard.quickstarts.demos.reporting.DateUtil;
import org.switchyard.quickstarts.demos.reporting.ReportStatus;
import org.switchyard.quickstarts.demos.reporting.ReportSummary;
import org.switchyard.quickstarts.demos.reporting.beans.ReportSummaryQueryFilterBean;
import org.switchyard.quickstarts.demos.reporting.services.AlertService;
import org.switchyard.quickstarts.demos.reporting.services.ManagementService;
import org.switchyard.quickstarts.demos.reporting.services.VerificationService;

/**
 * Simple implementation for verifying transmission status.
 */
@Service(VerificationService.class)
public class VerificationServiceBean implements VerificationService {

    // TODO: Replace once bean service types support multiple service interfaces
    //@Inject @Reference("ReportQueryService")
    //private ReportQueryService reportQueryService;
    @Inject @Reference("ManagementService")
    private ManagementService reportQueryService;
    @Inject @Reference("AlertService")
    private AlertService alertService;

    @Override
    public void verifyTransmissionStatus(final Date reportingDate) {
        final Collection<ReportSummary> summaries = reportQueryService
                .getReportSummaries(new ReportSummaryQueryFilterBean(null, null, DateUtil
                        .getStartTimeForDay(reportingDate), DateUtil.getEndTimeForDay(reportingDate)));
        if (summaries == null) {
            return;
        }
        for (final ReportSummary summary : summaries) {
            if (summary.getStatus() == null
                    || ReportStatus.Type.TRANSMITTED.compareTo(summary.getStatus().getType()) < 0) {
                alertService.notifyNotTransmitted(summary.getReportId());
            }
        }
    }

    @Override
    public void verifyTransmitted(final Date reportingDate) {
        final Collection<ReportSummary> summaries = reportQueryService
                .getReportSummaries(new ReportSummaryQueryFilterBean(null, null, DateUtil
                        .getStartTimeForDay(reportingDate), DateUtil.getEndTimeForDay(reportingDate)));
        if (summaries == null) {
            return;
        }
        for (final ReportSummary summary : summaries) {
            if (summary.getStatus() == null
                    || ReportStatus.Type.TRANSMITTED.compareTo(summary.getStatus().getType()) < 0) {
                alertService.notifyTransmissionOverdue(summary.getReportId());
            }
        }
    }

}
