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

import org.switchyard.quickstarts.demos.reporting.events.ReportSummaryQueryFilter;

/**
 * 
 */
public class ReportSummaryQueryFilterBean implements ReportSummaryQueryFilter {

    private String clientId;
    private String destinationId;
    private Date fromDate;
    private Date toDate;

    /**
     * Create a new ReportSummaryQueryFilterBean.
     */
    public ReportSummaryQueryFilterBean() {
    }

    public ReportSummaryQueryFilterBean(String clientId, String destinationId, Date fromDate, Date toDate) {
        super();
        this.clientId = clientId;
        this.destinationId = destinationId;
        this.fromDate = fromDate;
        this.toDate = toDate;
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
    public Date getFromDate() {
        return fromDate;
    }

    @Override
    public Date getToDate() {
        return toDate;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

}
