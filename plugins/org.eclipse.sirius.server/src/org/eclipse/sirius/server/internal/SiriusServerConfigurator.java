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
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.DispatcherType;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.websocket.DeploymentException;
import javax.websocket.server.ServerContainer;
import javax.websocket.server.ServerEndpointConfig;

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
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;
import org.eclipse.sirius.server.api.ISiriusServerConfigurator;
import org.eclipse.sirius.server.api.ISiriusServerEndpointConfigurationProvider;

/**
 * The entry point of the back-end used to configure the Sirius server.
 *
 * @author sbegaudeau
 */
public class SiriusServerConfigurator implements ISiriusServerConfigurator {

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
     * The context path of the Sirius HTTP API.
     */
    private static final String HTTP_API_CONTEXT_PATH = "/api"; //$NON-NLS-1$

    /**
     * The context path of the Sirius WS API.
     */
    private static final String WS_API_CONTEXT_PATH = "/ws"; //$NON-NLS-1$

    /**
     * The context path of all requests.
     */
    private static final String ALL_PATH = "/*"; //$NON-NLS-1$

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

        ServletContextHandler httpAPIServletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS | ServletContextHandler.GZIP);
        httpAPIServletContextHandler.setContextPath(HTTP_API_CONTEXT_PATH);
        httpAPIServletContextHandler.setErrorHandler(new SiriusServerErrorHandler());
        SessionCookieConfig sessionCookieConfig = httpAPIServletContextHandler.getServletContext().getSessionCookieConfig();
        sessionCookieConfig.setHttpOnly(true);

        httpAPIServletContextHandler.addFilter(SiriusServerFilter.class, ALL_PATH, EnumSet.of(DispatcherType.REQUEST, DispatcherType.INCLUDE));

        ServletContextHandler wsAPIServletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS | ServletContextHandler.GZIP);
        wsAPIServletContextHandler.setContextPath(WS_API_CONTEXT_PATH);

        Handler handler = server.getHandler();
        if (handler instanceof HandlerCollection) {
            HandlerCollection handlerCollection = (HandlerCollection) handler;
            handlerCollection.addHandler(httpAPIServletContextHandler);
            handlerCollection.addHandler(wsAPIServletContextHandler);
        }

        try {
            ServerContainer container = WebSocketServerContainerInitializer.configureContext(wsAPIServletContextHandler);
            container.setDefaultMaxSessionIdleTimeout(0);

            List<ISiriusServerEndpointConfigurationProvider> providers = SiriusServerPlugin.getPlugin().getEndpointConfigurationProviders();
            List<ServerEndpointConfig> endpointConfigurations = providers.stream().map(ISiriusServerEndpointConfigurationProvider::getEndpointConfiguration).collect(Collectors.toList());
            for (ServerEndpointConfig endpointConfiguration : endpointConfigurations) {
                container.addEndpoint(endpointConfiguration);
            }
        } catch (DeploymentException | ServletException e) {
            IStatus status = new Status(IStatus.ERROR, SiriusServerPlugin.PLUGIN_ID, e.getMessage(), e);
            SiriusServerPlugin.INSTANCE.log(status);
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
        HttpConfiguration httpConfiguration = new HttpConfiguration();
        httpConfiguration.setSendServerVersion(false);
        HttpConnectionFactory httpConnectionFactory = new HttpConnectionFactory(httpConfiguration);
        ServerConnector serverConnector = new ServerConnector(server, httpConnectionFactory);
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
        httpsConfiguration.setSendServerVersion(false);
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
