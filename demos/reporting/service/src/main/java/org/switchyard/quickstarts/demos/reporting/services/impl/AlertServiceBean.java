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

import org.switchyard.component.bean.Service;
import org.switchyard.quickstarts.demos.reporting.services.AlertService;
import org.switchyard.quickstarts.demos.reporting.services.ReportQueryService.ReportId;

/**
 * Crude implementation of an alert service
 * 
 * TODO: convert this to a camel implemention for sending alert email
 */
@Service(AlertService.class)
public class AlertServiceBean implements AlertService {

    @Override
    public void notifyTransmissionFailed(ReportId reportId) {
        System.err.println("Report transmission failed: " + reportId.toString());
    }

    @Override
    public void notifyNotTransmitted(ReportId reportId) {
        System.err.println("Warning: Report hasn't been transmitted: " + reportId.toString());
    }

    @Override
    public void notifyTransmissionOverdue(ReportId reportId) {
        System.err.println("Report transmission overdue: " + reportId.toString());
    }

}
