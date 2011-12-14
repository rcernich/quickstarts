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
package org.switchyard.quickstarts.demos.reporting.beans;

import java.util.ArrayList;
import java.util.List;

import org.switchyard.quickstarts.demos.reporting.ReportNotes;
import org.switchyard.quickstarts.demos.reporting.ReportStatus;
import org.switchyard.quickstarts.demos.reporting.ReportSummary;
import org.switchyard.quickstarts.demos.reporting.services.ReportQueryService.ReportId;

/**
 * ReportSummaryBean
 * 
 * @author Rob Cernich
 */
public class ReportSummaryBean implements ReportSummary {

    private ReportId reportId;
    private String summary;
    private List<ReportNotes> notes = new ArrayList<ReportNotes>();
    private List<ReportStatus> statusHistory = new ArrayList<ReportStatus>();

    /**
     * Create a new ReportSummaryBean.
     * 
     */
    public ReportSummaryBean() {
    }

    @Override
    public ReportId getReportId() {
        return reportId;
    }

    @Override
    public ReportStatus getStatus() {
        return statusHistory == null || statusHistory.size() == 0 ? ReportStatus.NULL : statusHistory.get(statusHistory
                .size() - 1);
    }

    @Override
    public String getSummary() {
        return summary;
    }

    @Override
    public List<ReportNotes> getNotes() {
        return notes;
    }

    @Override
    public List<ReportStatus> getStatusHistory() {
        return statusHistory;
    }

    public void setReportId(ReportId reportId) {
        this.reportId = reportId;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setNotes(List<ReportNotes> notes) {
        this.notes = notes;
    }

    public void setStatusHistory(List<ReportStatus> statusHistory) {
        this.statusHistory = statusHistory;
    }

    public void setStatus(ReportStatus reportStatus) {
        if (statusHistory == null) {
            statusHistory = new ArrayList<ReportStatus>();
        }
        statusHistory.add(reportStatus);
    }
    
    public void addNotes(ReportNotes reportNotes) {
        if (notes == null) {
            notes = new ArrayList<ReportNotes>();
        }
        notes.add(reportNotes);
    }

}
