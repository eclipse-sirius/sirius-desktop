/*******************************************************************************
 * Copyright (c) 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.server.images.internal;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.sirius.server.api.ISiriusServerConfigurator;

/**
 * The entry point of the images configurator used to configure the Sirius server.
 *
 * @author sbegaudeau
 */
public class SiriusServerImagesConfigurator implements ISiriusServerConfigurator {

    /**
     * The context path of the Sirius images.
     */
    private static final String CONTEXT_PATH = "/images"; //$NON-NLS-1$

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.server.api.ISiriusServerConfigurator#configure(org.eclipse.jetty.server.Server)
     */
    @Override
    public void configure(Server server) {
        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS | ServletContextHandler.GZIP);
        servletContextHandler.setContextPath(CONTEXT_PATH);

        servletContextHandler.addFilter(SiriusServerImagesFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST, DispatcherType.INCLUDE)); //$NON-NLS-1$

        Handler handler = server.getHandler();
        if (handler instanceof HandlerCollection) {
            HandlerCollection handlerCollection = (HandlerCollection) handler;
            handlerCollection.addHandler(servletContextHandler);
        }
    }
}
