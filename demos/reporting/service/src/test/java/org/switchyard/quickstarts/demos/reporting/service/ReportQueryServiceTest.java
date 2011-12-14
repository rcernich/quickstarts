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
package org.switchyard.quickstarts.demos.reporting.service;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.switchyard.quickstarts.demos.reporting.ReportSummary;
import org.switchyard.test.Invoker;
import org.switchyard.test.ServiceOperation;
import org.switchyard.test.SwitchYardRunner;
import org.switchyard.test.SwitchYardTestCaseConfig;
import org.switchyard.test.mixins.CDIMixIn;

/**
 * ReportQueryServiceTest
 * 
 * @author Rob Cernich
 */
@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(mixins = CDIMixIn.class, config = SwitchYardTestCaseConfig.SWITCHYARD_XML)
public class ReportQueryServiceTest {

    @ServiceOperation("ReportQueryService")
    private Invoker service;

    @Test
    public void testGetReportSummaries() throws Exception {
        // FIXME testGetReportSummaries
        // initialize your test message
        Object message = null;
        @SuppressWarnings("unchecked")
        Collection<ReportSummary> result = (Collection<ReportSummary>) service.operation("getReportSummaries")
                .sendInOut(message).getContent(Collection.class);

        // validate the results
        Assert.assertTrue("Implement me", false);
    }

    @Test
    public void testGetReportSummary() throws Exception {
        // FIXME testGetReportSummary
        // initialize your test message
        Object message = null;
        ReportSummary result = service.operation("getReportSummary").sendInOut(message).getContent(ReportSummary.class);

        // validate the results
        Assert.assertTrue("Implement me", false);
    }

}
