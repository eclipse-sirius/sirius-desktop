/*******************************************************************************
 * Copyright (c) 2018 TypeFox and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.server.diagram.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.websocket.Extension;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

import org.eclipse.jetty.websocket.jsr356.server.ContainerDefaultConfigurator;

/**
 * The configurator of the Sirius server diagram endpoint.
 *
 * @author sbegaudeau
 */
public class SiriusServerDiagramEndpointConfigurator extends Configurator {

    /**
     * The diagram service manager.
     */
    private SiriusServerDiagramServiceManager diagramServiceManager = new SiriusServerDiagramServiceManager();

    /**
     * {@inheritDoc}
     *
     * @see javax.websocket.server.ServerEndpointConfig.Configurator#getEndpointInstance(java.lang.Class)
     */
    @Override
    public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException {
        try {
            Constructor<T> constructor = endpointClass.getConstructor(SiriusServerDiagramServiceManager.class);
            T instance = constructor.newInstance(this.diagramServiceManager);
            return instance;
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new InstantiationException(String.format("%s: %s", e.getClass().getName(), e.getMessage())); //$NON-NLS-1$
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see javax.websocket.server.ServerEndpointConfig.Configurator#checkOrigin(java.lang.String)
     */
    @Override
    public boolean checkOrigin(String originHeaderValue) {
        return new ContainerDefaultConfigurator().checkOrigin(originHeaderValue);
    }

    /**
     * {@inheritDoc}
     *
     * @see javax.websocket.server.ServerEndpointConfig.Configurator#modifyHandshake(javax.websocket.server.ServerEndpointConfig,
     *      javax.websocket.server.HandshakeRequest,
     *      javax.websocket.HandshakeResponse)
     */
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        new ContainerDefaultConfigurator().modifyHandshake(sec, request, response);
    }

    /**
     * {@inheritDoc}
     *
     * @see javax.websocket.server.ServerEndpointConfig.Configurator#getNegotiatedSubprotocol(java.util.List,
     *      java.util.List)
     */
    @Override
    public String getNegotiatedSubprotocol(List<String> supported, List<String> requested) {
        return new ContainerDefaultConfigurator().getNegotiatedSubprotocol(supported, requested);
    }

    /**
     * {@inheritDoc}
     *
     * @see javax.websocket.server.ServerEndpointConfig.Configurator#getNegotiatedExtensions(java.util.List,
     *      java.util.List)
     */
    @Override
    public List<Extension> getNegotiatedExtensions(List<Extension> installed, List<Extension> requested) {
        return new ContainerDefaultConfigurator().getNegotiatedExtensions(installed, requested);
    }

}
