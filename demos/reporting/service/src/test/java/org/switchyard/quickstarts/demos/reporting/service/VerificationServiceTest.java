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

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.switchyard.test.Invoker;
import org.switchyard.test.ServiceOperation;
import org.switchyard.test.SwitchYardRunner;
import org.switchyard.test.SwitchYardTestCaseConfig;
import org.switchyard.test.mixins.CDIMixIn;

/**
 * VerificationServiceTest
 * 
 * @author Rob Cernich
 */
@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(mixins = CDIMixIn.class, config = SwitchYardTestCaseConfig.SWITCHYARD_XML)
public class VerificationServiceTest {

    @ServiceOperation("VerificationService")
    private Invoker service;

    @Test
    public void testVerifyTransmissionStatus() throws Exception {
        // FIXME testVerifyTransmissionStatus
        // initialize your test message
        Object message = null;
        service.operation("verifyTransmissionStatus").sendInOnly(message);

        // validate the results
        Assert.assertTrue("Implement me", false);
    }

    @Test
    public void testVerifyTransmitted() throws Exception {
        // FIXME testVerifyTransmitted
        // initialize your test message
        Object message = null;
        service.operation("verifyTransmitted").sendInOnly(message);

        // validate the results
        Assert.assertTrue("Implement me", false);
    }

}
