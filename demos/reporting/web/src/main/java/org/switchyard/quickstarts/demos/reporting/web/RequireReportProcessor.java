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
package org.switchyard.quickstarts.demos.reporting.web;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.switchyard.component.bean.Reference;
import org.switchyard.quickstarts.demos.reporting.services.ManagementService;
import org.switchyard.quickstarts.demos.reporting.services.ReportQueryService.ReportId;

/**
 * 
 */
@Named("requireReportProcessor")
@RequestScoped
public class RequireReportProcessor {

    @Inject
    @Reference("ManagementServiceProxy")
    private ManagementService managementService;

    /**
     * Create a new RequireReportProcessor.
     */
    public RequireReportProcessor() {
    }

    public String execute() {
        ReportId reportId = (ReportId)FacesContext.getCurrentInstance().getELContext().getVariableMapper().resolveVariable("reportId");
        managementService.reportRequired(reportId);
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(String.format("Notified system that a report for %s must be sent to %s for %tF",
                        reportId.getClientId(), reportId.getDestinationId(), reportId.getReportingDate())));
        return "success";
    }

}
