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

import org.apache.camel.component.quartz.QuartzMessage;
import org.switchyard.Exchange;
import org.switchyard.Message;
import org.switchyard.component.camel.composer.CamelMessageComposer;

/**
 * 
 */
public class QuartzMessageComposer extends CamelMessageComposer {

    @Override
    public Message compose(org.apache.camel.Message source, Exchange exchange, boolean create) throws Exception {
        source.setBody(((QuartzMessage) source).getJobExecutionContext().getFireTime());
        return super.compose(source, exchange, create);
    }

}
