/*******************************************************************************
 * Copyright (c) 2018, 2019 Obeo
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
package org.eclipse.sirius.server.diagram.internal;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.websocket.DeploymentException;
import javax.websocket.server.ServerContainer;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Builder;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.elk.alg.layered.options.LayeredOptions;
import org.eclipse.elk.core.util.persistence.ElkGraphResourceFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;
import org.eclipse.sirius.server.api.ISiriusServerConfigurator;
import org.eclipse.sprotty.layout.ElkLayoutEngine;
import org.osgi.framework.Bundle;

/**
 * The configurator used to connect the support for diagrams to the Sirius server.
 *
 * @author sbegaudeau
 */
public class SiriusServerDiagramConfigurator implements ISiriusServerConfigurator {

	/**
	 * The default context path.
	 */
	private static final String CONTEXT_PATH = "/diagrams"; //$NON-NLS-1$

	/**
	 * The default servlet holder name.
	 */
	private static final String SERVLET_HOLDER_NAME = "Sirius Diagrams"; //$NON-NLS-1$

	@Override
	public void configure(Server server) {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("elkg", new ElkGraphResourceFactory()); //$NON-NLS-1$
		ElkLayoutEngine.initialize(new LayeredOptions());
		SiriusDiagramService siriusDiagramService = new SiriusDiagramService();

		try {
			server.setStopAtShutdown(true);

			ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS | ServletContextHandler.GZIP);
			servletContextHandler.setContextPath(CONTEXT_PATH);
			servletContextHandler.addEventListener(siriusDiagramService);

			Handler handler = server.getHandler();
			if (handler instanceof HandlerCollection) {
				HandlerCollection handlerCollection = (HandlerCollection) handler;
				handlerCollection.addHandler(servletContextHandler);
			}

			ServletHolder servletHolder = new ServletHolder(SERVLET_HOLDER_NAME, new DefaultServlet());

			Bundle bundle = SiriusDiagramServerPlugin.getPlugin().getBundle();
			URL webappFolderUrl = bundle.getResource("./webapp"); //$NON-NLS-1$
			URI webappFolderUri = FileLocator.resolve(webappFolderUrl).toURI();
			String webappFolderAbsolutePath = new File(webappFolderUri).getAbsolutePath();

			servletHolder.setInitParameter("resourceBase", webappFolderAbsolutePath); //$NON-NLS-1$
			servletHolder.setInitParameter("dirAllowed", "false"); //$NON-NLS-1$ //$NON-NLS-2$
			servletContextHandler.addServlet(servletHolder, "/"); //$NON-NLS-1$

			SiriusEndpointConfigurator siriusEndpointConfigurator = new SiriusEndpointConfigurator(siriusDiagramService);
			ServerContainer container = WebSocketServerContainerInitializer.configureContext(servletContextHandler);
			container.setDefaultMaxSessionIdleTimeout(0);
			Builder endpointConfigBuilder = ServerEndpointConfig.Builder.create(SiriusDiagramServerEndpoint.class, "/api"); //$NON-NLS-1$
			endpointConfigBuilder.configurator(siriusEndpointConfigurator);
			container.addEndpoint(endpointConfigBuilder.build());
		} catch (ServletException | URISyntaxException | IOException | DeploymentException exception) {
			exception.printStackTrace();
		}
	}

}
