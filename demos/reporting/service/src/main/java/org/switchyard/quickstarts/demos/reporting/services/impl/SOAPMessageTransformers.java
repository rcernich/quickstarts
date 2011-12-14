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

import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import org.switchyard.annotations.Transformer;
import org.switchyard.quickstarts.demos.reporting.beans.AttachNotesEventBean;
import org.switchyard.quickstarts.demos.reporting.beans.ReportGeneratedEventBean;
import org.switchyard.quickstarts.demos.reporting.beans.ReportIdBean;
import org.switchyard.quickstarts.demos.reporting.beans.ReportStatusEventBean;
import org.switchyard.quickstarts.demos.reporting.beans.ReportTransmissionFailedEventBean;
import org.switchyard.quickstarts.demos.reporting.events.AttachNotesEvent;
import org.switchyard.quickstarts.demos.reporting.events.ReportGeneratedEvent;
import org.switchyard.quickstarts.demos.reporting.events.ReportStatusEvent;
import org.switchyard.quickstarts.demos.reporting.events.ReportTransmissionFailedEvent;
import org.switchyard.quickstarts.demos.reporting.services.ReportQueryService.ReportId;
import org.w3c.dom.Element;

/**
 * 
 */
public class SOAPMessageTransformers {

    // Common items
    @Transformer(from = "{urn:org.switchyard.quickstarts.demos:reporting-service:0.0.1}ReportId")
    public static ReportId toReportId(Element from) {
        return new ReportIdBean(getElementText(from, "clientId"), getElementText(from, "destinationId"),
                parseDate(getElementText(from, "reportingDate").trim()));
    }

    // Event types
    @Transformer(from = "{urn:org.switchyard.quickstarts.demos:reporting-service:0.0.1}ReportStatusEvent")
    public static ReportStatusEvent toReportStatusEvent(Element from) {
        return new ReportStatusEventBean(toReportId(getElement(from, "reportId")), parseDateTime(getElementText(from,
                "timestamp")));
    }

    @Transformer(from = "{urn:org.switchyard.quickstarts.demos:reporting-service:0.0.1}ReportTransmissionFailedEvent")
    public static ReportTransmissionFailedEvent toReportTransmissionFailedEvent(Element from) {
        return new ReportTransmissionFailedEventBean(toReportId(getElement(from, "reportId")), getElementText(from,
                "error"), parseDateTime(getElementText(from, "timestamp")));
    }

    @Transformer(from = "{urn:org.switchyard.quickstarts.demos:reporting-service:0.0.1}ReportGeneratedEvent")
    public static ReportGeneratedEvent toReportGeneratedEvent(Element from) {
        return new ReportGeneratedEventBean(toReportId(getElement(from, "reportId")), getElementText(from, "reportSummary"),
                parseDateTime(getElementText(from, "timestamp")));
    }

    @Transformer(from = "{urn:org.switchyard.quickstarts.demos:reporting-service:0.0.1}reportTrasmitted")
    public static AttachNotesEvent toAttachNotesEvent(Element from) {
        return new AttachNotesEventBean(toReportId(getElement(from, "reportId")), getElementText(from, "notes"),
                parseDateTime(getElementText(from, "timestamp")));
    }

    // Operation types
    @Transformer(from = "{urn:org.switchyard.quickstarts.demos:reporting-service:0.0.1}reportTrasmitted")
    public static ReportStatusEvent reportTransmittedInput(Element from) {
        return toReportStatusEvent(getElement(from, "event"));
    }

    @Transformer(from = "{urn:org.switchyard.quickstarts.demos:reporting-service:0.0.1}reportTransmissionFailed")
    public static ReportTransmissionFailedEvent reportTransmissionFailedInput(Element from) {
        return toReportTransmissionFailedEvent(getElement(from, "event"));
    }

    @Transformer(from = "{urn:org.switchyard.quickstarts.demos:reporting-service:0.0.1}reportRequired")
    public static ReportId reportRequiredInput(Element from) {
        return toReportId(getElement(from, "reportId"));
    }

    @Transformer(from = "{urn:org.switchyard.quickstarts.demos:reporting-service:0.0.1}reportProcessed")
    public static ReportStatusEvent reportProcessedInput(Element from) {
        return toReportStatusEvent(getElement(from, "event"));
    }

    @Transformer(from = "{urn:org.switchyard.quickstarts.demos:reporting-service:0.0.1}reportGenerated")
    public static ReportGeneratedEvent reportGeneratedInput(Element from) {
        return toReportGeneratedEvent(getElement(from, "event"));
    }

    @Transformer(from = "{urn:org.switchyard.quickstarts.demos:reporting-service:0.0.1}reportClosed")
    public static ReportStatusEvent reportClosedInput(Element from) {
        return toReportStatusEvent(getElement(from, "event"));
    }

    @Transformer(from = "{urn:org.switchyard.quickstarts.demos:reporting-service:0.0.1}reportAcknowledged")
    public static ReportStatusEvent reportAcknowledgedInput(Element from) {
        return toReportStatusEvent(getElement(from, "event"));
    }

    @Transformer(from = "{urn:org.switchyard.quickstarts.demos:reporting-service:0.0.1}attachNotes")
    public static AttachNotesEvent attachNotesInput(Element from) {
        return toAttachNotesEvent(getElement(from, "event"));
    }

    // Utility
    private static Date parseDate(final String date) {
        Calendar cal = DatatypeConverter.parseDate(date);
        cal.set(Calendar.ZONE_OFFSET, 0);
        return cal.getTime();
    }

    private static Date parseDateTime(final String timestamp) {
        return DatatypeConverter.parseDateTime(timestamp).getTime();
    }

    private static Element getElement(final Element root, final String name) {
        return (Element) root.getElementsByTagNameNS("*", name).item(0);
    }

    private static String getElementText(final Element root, final String name) {
        return getElement(root, name).getTextContent();
    }

}
