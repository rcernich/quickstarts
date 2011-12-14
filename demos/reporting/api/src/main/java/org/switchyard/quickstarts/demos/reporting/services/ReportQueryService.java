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

import java.util.Collection;
import java.util.Date;

import org.switchyard.quickstarts.demos.reporting.ReportSummary;
import org.switchyard.quickstarts.demos.reporting.events.ReportSummaryQueryFilter;

/**
 * Service interface for accessing report details.
 */
public interface ReportQueryService {

    /**
     * Information required to identify a specific report.
     */
    public interface ReportId {
        /**
         * @return the client on whose behalf this report is being generated.
         */
        public String getClientId();

        /**
         * @return the destination to which this report should be filed.
         */
        public String getDestinationId();

        /**
         * @return the date for which this report applies.
         */
        public Date getReportingDate();
    }

    /**
     * Returns a report summary for the specified report ID.
     * 
     * @param reportId the report ID (client, destination, date)
     * 
     * @return the report summary; null if no summary exists (e.g. no report was
     *         required)
     */
    public ReportSummary getReportSummary(ReportId reportId);

    /**
     * Returns a collection of report summaries based on the specified filter
     * criteria.
     * 
     * @param queryFilter filter criteria for the query.
     * 
     * @return a collection of ReportSummary objects matching the criteria.
     * @see ReportSummaryQueryFilter
     */
    public Collection<ReportSummary> getReportSummaries(ReportSummaryQueryFilter queryFilter);

}
