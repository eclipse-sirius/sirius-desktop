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
package org.eclipse.sirius.server.internal;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.SessionCookieConfig;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.eclipse.sirius.server.api.ISiriusServerConfigurator;

/**
 * The entry point of the back-end used to configure the Sirius server.
 *
 * @author sbegaudeau
 */
public class SiriusServerConfigurator implements ISiriusServerConfigurator {

    /**
     * The constant used specified allowed methods. Expects a list of string
     * with comma separated values.
     */
    private static final String ALLOWED_METHODS = "org.eclipse.sirius.server.cors.allowed.methods"; //$NON-NLS-1$

    /**
     * The constant used specified allowed headers. Expects a list of string
     * with comma separated values.
     */
    private static final String ALLOWED_HEADERS = "org.eclipse.sirius.server.cors.allowed.headers"; //$NON-NLS-1$

    /**
     * The constant used specified allowed origins. Expects a list of string
     * with comma separated values.
     */
    private static final String ALLOWED_ORIGINS = "org.eclipse.sirius.server.cors.allowed.origins"; //$NON-NLS-1$

    /**
     * The constant used to determine if Cross Origin Resource Sharing are
     * enabled or not. Expects a boolean.
     */
    private static final String ALLOW_CORS = "org.eclipse.sirius.server.cors.enabled"; //$NON-NLS-1$

    /**
     * The context path of the Sirius back-end.
     */
    private static final String CONTEXT_PATH = "/api"; //$NON-NLS-1$

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.server.api.ISiriusServerConfigurator#configure(org.eclipse.sirius.server.api.Server)
     */
    @Override
    public void configure(Server server) {
        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS | ServletContextHandler.GZIP);
        servletContextHandler.setContextPath(CONTEXT_PATH);
        servletContextHandler.setErrorHandler(new SiriusServerErrorHandler());
        SessionCookieConfig sessionCookieConfig = servletContextHandler.getServletContext().getSessionCookieConfig();
        sessionCookieConfig.setHttpOnly(true);

        boolean allowCors = Boolean.parseBoolean(System.getProperty(ALLOW_CORS));
        if (allowCors) {
            FilterHolder cors = new FilterHolder();
            String allowedOrigins = System.getProperty(ALLOWED_ORIGINS);
            if (allowedOrigins != null) {
                cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, allowedOrigins);
            }
            String allowedHeaders = System.getProperty(ALLOWED_HEADERS);
            if (allowedHeaders != null) {
                cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, allowedHeaders);
            }
            String allowedMethods = System.getProperty(ALLOWED_METHODS);
            if (allowedMethods != null) {
                cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, allowedMethods);
            }
            cors.setFilter(new CrossOriginFilter());
            servletContextHandler.addFilter(cors, "/*", EnumSet.of(DispatcherType.REQUEST, DispatcherType.ASYNC, DispatcherType.INCLUDE)); //$NON-NLS-1$
        }

        servletContextHandler.addFilter(SiriusServerFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST, DispatcherType.INCLUDE)); //$NON-NLS-1$

        Handler handler = server.getHandler();
        if (handler instanceof HandlerCollection) {
            HandlerCollection handlerCollection = (HandlerCollection) handler;
            handlerCollection.addHandler(servletContextHandler);
        }
    }

}
