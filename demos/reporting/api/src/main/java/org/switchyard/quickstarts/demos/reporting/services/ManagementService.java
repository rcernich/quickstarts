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
package org.switchyard.quickstarts.demos.reporting.services;

import org.switchyard.quickstarts.demos.reporting.events.AttachNotesEvent;
import org.switchyard.quickstarts.demos.reporting.events.ReportGeneratedEvent;
import org.switchyard.quickstarts.demos.reporting.events.ReportStatusEvent;
import org.switchyard.quickstarts.demos.reporting.events.ReportTransmissionFailedEvent;


/**
 * Service interface for managing report status. The simple workflow is:
 * <p/>
 * required&rArr;generated&rArr;trasmitted&rArr;acknowledged&rArr;processed&rArr
 * ;closed
 * <p/>
 * acknowledged and processed may be ommited. notes may be attached within any
 * state except closed. transmission failed moves the state back to generated.
 */
// TODO: remove ReportQueryService once SwitchYard Bean service supports
// multiple services per implementation.
public interface ManagementService extends ReportQueryService {

    /**
     * Notify the system that a filing is required for a specified report.
     * 
     * @param reportId the report ID (client, destination, date)
     */
    public void reportRequired(ReportId reportId);

    /**
     * Notify the system that a report has been generated.
     * 
     * @param event the updated status details
     * @see ReportGeneratedEvent
     */
    public void reportGenerated(ReportGeneratedEvent event);

    /**
     * Notify the system that a report has been successfully transmitted to its
     * destination.
     *
     * @param event the updated status details
     * @see ReportStatusEvent
     */
    public void reportTrasmitted(ReportStatusEvent event);

    /**
     * Notify the system that an error occurred while attempting to transmit a
     * report to its destination.
     * 
     * @param event the updated status details
     * @see ReportTransmissionFailedEvent
     */
    public void reportTransmissionFailed(ReportTransmissionFailedEvent event);

    /**
     * Notify the system that a report has been acknowledged as recieved by the
     * destination.
     * 
     * @param event the updated status details
     * @see ReportStatusEvent
     */
    public void reportAcknowledged(ReportStatusEvent event);

    /**
     * Notify the system that a report has been processed by the destination.
     * 
     * @param event the updated status details
     * @see ReportStatusEvent
     */
    public void reportProcessed(ReportStatusEvent event);

    /**
     * Attach notes to a report. Notes may include details about trade breaks,
     * resolutions, etc.
     * 
     * @param event the updated status details
     * @see AttachNotesEvent
     */
    public void attachNotes(AttachNotesEvent event);

    /**
     * Notify the system that a report has been closed out.
     * 
     * @param event the updated status details
     * @see ReportStatusEvent
     */
    public void reportClosed(ReportStatusEvent event);

}
