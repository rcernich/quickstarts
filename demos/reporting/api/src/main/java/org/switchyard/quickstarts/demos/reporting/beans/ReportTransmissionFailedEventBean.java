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

import org.switchyard.quickstarts.demos.reporting.events.ReportTransmissionFailedEvent;
import org.switchyard.quickstarts.demos.reporting.services.ReportQueryService.ReportId;

/**
 * 
 */
public class ReportTransmissionFailedEventBean extends ReportStatusEventBean implements ReportTransmissionFailedEvent {

    private String error;

    /**
     * Create a new ReportTransmissionFailedEventBean.
     */
    public ReportTransmissionFailedEventBean() {
        super();
    }

    public ReportTransmissionFailedEventBean(ReportId reportId, String error, Date timestamp) {
        super(reportId, timestamp);
        this.error = error;
    }

    @Override
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
