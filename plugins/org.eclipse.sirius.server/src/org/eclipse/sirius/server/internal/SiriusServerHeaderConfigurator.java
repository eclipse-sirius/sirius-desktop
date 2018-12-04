/*******************************************************************************
 * Copyright (c) 2018 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.server.internal;

import java.util.Arrays;
import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.eclipse.jetty.servlets.HeaderFilter;
import org.eclipse.sirius.server.api.ISiriusServerConfigurator;

/**
 * The configurator used to setup the response headers.
 *
 * @author sbegaudeau
 */
public class SiriusServerHeaderConfigurator implements ISiriusServerConfigurator {

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
     * The header configuration key.
     */
    private static final String HEADER_CONFIG = "headerConfig"; //$NON-NLS-1$

    /**
     * The context path of all requests.
     */
    private static final String ALL_PATH = "/*"; //$NON-NLS-1$

    @Override
    public void configure(Server server) {
        this.configureHeaders(server.getHandler());
    }

    /**
     * Configures the headers of the response of the given handler.
     *
     * @param handler
     *            The handler to configure
     */
    private void configureHeaders(Handler handler) {
        if (handler instanceof HandlerCollection) {
            HandlerCollection handlerCollection = (HandlerCollection) handler;
            Arrays.stream(handlerCollection.getHandlers()).forEach(this::configureHeaders);
        } else if (handler instanceof ServletContextHandler) {
            ServletContextHandler servletContextHandler = (ServletContextHandler) handler;
            this.configureCORS(servletContextHandler);
            this.configureAdditionalHeaders(servletContextHandler);
        }
    }

    /**
     * Configures the Cross Origin Resource Sharing headers.
     *
     * @param servletContextHandler
     *            The servlet context handler to configure
     */
    private void configureCORS(ServletContextHandler servletContextHandler) {
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
            servletContextHandler.addFilter(cors, ALL_PATH, EnumSet.of(DispatcherType.REQUEST, DispatcherType.ASYNC, DispatcherType.INCLUDE));
        }
    }

    /**
     * Configures additional headers.
     *
     * @param servletContextHandler
     *            The servlet context handler to configure
     */
    private void configureAdditionalHeaders(ServletContextHandler servletContextHandler) {
        FilterHolder headerFilterHolder = new FilterHolder();
        headerFilterHolder.setInitParameter(HEADER_CONFIG, Messages.SiriusServerConfigurator_headerConfig);
        headerFilterHolder.setFilter(new HeaderFilter());
        // servletContextHandler.addFilter(headerFilterHolder, ALL_PATH,
        // EnumSet.of(DispatcherType.REQUEST, DispatcherType.ASYNC,
        // DispatcherType.INCLUDE, DispatcherType.FORWARD));
    }

}
