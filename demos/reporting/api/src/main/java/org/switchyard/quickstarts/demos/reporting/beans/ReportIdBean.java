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

import java.util.Date;

import org.switchyard.quickstarts.demos.reporting.DateUtil;
import org.switchyard.quickstarts.demos.reporting.services.ReportQueryService.ReportId;

/**
 * 
 */
public class ReportIdBean implements ReportId {

    private String clientId;
    private String destinationId;
    private Date reportingDate;
    private transient volatile int hashCode;

    public ReportIdBean() {
    }
    
    public ReportIdBean(ReportId orig) {
        this(orig.getClientId(), orig.getDestinationId(), orig.getReportingDate());
    }

    public ReportIdBean(String clientId, String destinationId, Date reportingDate) {
        this.clientId = clientId;
        this.destinationId = destinationId;
        this.reportingDate = DateUtil.getStartTimeForDay(reportingDate);
    }

    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public String getDestinationId() {
        return destinationId;
    }

    @Override
    public Date getReportingDate() {
        return reportingDate;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
        hashCode = 0;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
        hashCode = 0;
    }

    public void setReportingDate(Date reportingDate) {
        this.reportingDate = reportingDate;
        hashCode = 0;
    }

    @Override
    public boolean equals(Object other) {
        ReportId otherId;
        if (other instanceof ReportId) {
            otherId = (ReportId) other;
        } else {
            return false;
        }
        return clientId.equals(otherId.getClientId()) && destinationId.equals(otherId.getDestinationId())
                && reportingDate.equals(otherId.getReportingDate());
    }

    @Override
    public int hashCode() {
        if (hashCode == 0) {
            int result = 41;
            result = 31*result + clientId.hashCode();
            result = 31*result + destinationId.hashCode();
            result = 31*result + reportingDate.hashCode();
            hashCode = result;
        }
        return hashCode;
    }
}
