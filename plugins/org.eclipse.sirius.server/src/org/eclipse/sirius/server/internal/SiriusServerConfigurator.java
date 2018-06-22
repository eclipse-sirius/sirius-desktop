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

import java.io.File;
import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.SessionCookieConfig;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.eclipse.jetty.util.ssl.SslContextFactory;
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
     * The constant used to determine whether ssl is activated or not.
     */
    private static final String HTTPS_ENABLED = "org.eclipse.sirius.server.https.enabled"; //$NON-NLS-1$

    /**
     * The constant used to customize the https host.
     */
    private static final String HTTPS_HOST = "org.eclipse.sirius.server.https.host"; //$NON-NLS-1$

    /**
     * The constant used to customize the https port.
     */
    private static final String HTTPS_PORT = "org.eclipse.sirius.server.https.port"; //$NON-NLS-1$

    /**
     * The constant used to give the location of the key store for ssl
     * authentication.
     */
    private static final String SSL_KEYSTORE_PATH = "org.eclipse.sirius.server.ssl.keystore.path"; //$NON-NLS-1$

    /**
     * The constant used to give the pass phrase for ssl authentication.
     */
    private static final String SSL_KEYSTORE_PASSPHRASE = "org.eclipse.sirius.server.ssl.keystore.passphrase"; //$NON-NLS-1$

    /**
     * The constant used to customize the http host.
     */
    private static final String HTTP_HOST = "org.eclipse.sirius.server.http.host"; //$NON-NLS-1$

    /**
     * The constant used to customize the http port.
     */
    private static final String HTTP_PORT = "org.eclipse.sirius.server.http.port"; //$NON-NLS-1$

    /**
     * The default hostname.
     */
    private static final String DEFAULT_HOSTNAME = "0.0.0.0"; //$NON-NLS-1$

    /**
     * The default http port.
     */
    private static final int DEFAULT_HTTP_PORT = 0;

    /**
     * The default https port.
     */
    private static final int DEFAULT_HTTPS_PORT = 0;

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
        boolean httpsEnabled = Boolean.parseBoolean(System.getProperty(HTTPS_ENABLED));

        Connector connector = null;

        if (httpsEnabled) {
            connector = createHttpsConnector(server);
        } else {
            connector = createHttpConnector(server);
        }
        server.addConnector(connector);

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

    /**
     * Creates the http connector if https has been disabled using system
     * properties.
     *
     * @param server
     *            The jetty server
     * @return The created http connector
     */
    private Connector createHttpConnector(Server server) {
        ServerConnector serverConnector = new ServerConnector(server);
        serverConnector.setHost(System.getProperty(HTTP_HOST, DEFAULT_HOSTNAME));
        serverConnector.setPort(handleSystemPropertiesIntegerValue(HTTP_PORT, DEFAULT_HTTP_PORT));
        return serverConnector;
    }

    /**
     * Creates the https connector if https has been enabled using system
     * properties.
     *
     * @param server
     *            The jetty server.
     * @return The created https connector
     */
    private Connector createHttpsConnector(Server server) {
        SslContextFactory sslContextFactory = new SslContextFactory();
        File file = new File(System.getProperty(SSL_KEYSTORE_PATH));
        file.exists();
        sslContextFactory.setKeyStorePath(file.getAbsolutePath());
        sslContextFactory.setKeyStorePassword(System.getProperty(SSL_KEYSTORE_PASSPHRASE));
        sslContextFactory.setKeyStoreType("JKS"); //$NON-NLS-1$
        sslContextFactory.setProtocol("TLS"); //$NON-NLS-1$
        sslContextFactory.setWantClientAuth(false);
        sslContextFactory.setNeedClientAuth(false);

        HttpConfiguration httpsConfiguration = new HttpConfiguration();
        httpsConfiguration.addCustomizer(new SecureRequestCustomizer());

        ServerConnector serverConnector = new ServerConnector(server, new SslConnectionFactory(sslContextFactory, "http/1.1"), new HttpConnectionFactory(httpsConfiguration)); //$NON-NLS-1$
        serverConnector.setHost(System.getProperty(HTTPS_HOST, DEFAULT_HOSTNAME));
        serverConnector.setPort(handleSystemPropertiesIntegerValue(HTTPS_PORT, DEFAULT_HTTPS_PORT));
        serverConnector.setIdleTimeout(30000);
        return serverConnector;
    }

    /**
     * Returns the integer value of the given system property value. If the
     * system property is not a integer, the default value will be used instead.
     *
     * @param property
     *            The system property key
     * @param defaultValue
     *            The default value
     * @return The integer value of given system property value or the default
     *         value
     */
    private int handleSystemPropertiesIntegerValue(String property, int defaultValue) {
        int value = defaultValue;
        String propertyValue = System.getProperty(property);
        if (propertyValue != null && propertyValue.length() > 0) {
            try {
                value = Integer.parseInt(propertyValue);
            } catch (NumberFormatException exception) {
                String message = String.format(Messages.SiriusServerConfigurator_wrongPropertyTypeWarning, propertyValue);
                IStatus status = new Status(IStatus.ERROR, SiriusServerPlugin.PLUGIN_ID, message, exception);
                SiriusServerPlugin.getPlugin().getLog().log(status);
            }
        }
        return value;
    }

}
