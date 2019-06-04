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

import java.util.List;

import javax.websocket.Extension;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

import org.apache.log4j.Logger;
import org.eclipse.jetty.websocket.jsr356.server.ContainerDefaultConfigurator;

/**
 * The {@link SiriusEndpointConfigurator} is used to connect the {@link SiriusDiagramService} to the
 * {@link SiriusDiagramServerEndpoint}.
 *
 * @author sbegaudeau
 */
public class SiriusEndpointConfigurator extends Configurator {
	/**
	 * The logger.
	 */
	private static Logger LOG = Logger.getLogger(SiriusEndpointConfigurator.class);

	/**
	 * The diagram service.
	 */
	private SiriusDiagramService diagramService;

	/**
	 * The constructor.
	 *
	 * @param diagramService
	 *            The diagram service
	 */
	public SiriusEndpointConfigurator(SiriusDiagramService diagramService) {
		this.diagramService = diagramService;
	}

	@Override
	public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException {
		T instance = new ContainerDefaultConfigurator().getEndpointInstance(endpointClass);
		if (instance instanceof SiriusDiagramServerEndpoint) {
			SiriusDiagramServerEndpoint testServerEndpoint = (SiriusDiagramServerEndpoint) instance;
			testServerEndpoint.setDiagramServerProvider(this.diagramService);
			testServerEndpoint.setExceptionHandler((exception) -> LOG.warn(exception));
		}
		return instance;
	}

	@Override
	public boolean checkOrigin(String originHeaderValue) {
		return true;
	}

	@Override
	public String getNegotiatedSubprotocol(List<String> supported, List<String> requested) {
		return new ContainerDefaultConfigurator().getNegotiatedSubprotocol(supported, requested);
	}

	@Override
	public List<Extension> getNegotiatedExtensions(List<Extension> installed, List<Extension> requested) {
		return requested;
	}

	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
		// Do nothing because why not :)
	}
}
