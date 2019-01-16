/*******************************************************************************
 * Copyright (c) 2018, 2019 TypeFox and others.
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
     * The instance which will be used for most calls.
     */
    private ContainerDefaultConfigurator delegate = new ContainerDefaultConfigurator();

    /**
     * The diagram service manager.
     */
    private SiriusServerDiagramServiceManager diagramServiceManager = new SiriusServerDiagramServiceManager();

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

    @Override
    public boolean checkOrigin(String originHeaderValue) {
        return this.delegate.checkOrigin(originHeaderValue);
    }

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        this.delegate.modifyHandshake(sec, request, response);
    }

    @Override
    public String getNegotiatedSubprotocol(List<String> supported, List<String> requested) {
        return this.delegate.getNegotiatedSubprotocol(supported, requested);
    }

    @Override
    public List<Extension> getNegotiatedExtensions(List<Extension> installed, List<Extension> requested) {
        return this.delegate.getNegotiatedExtensions(installed, requested);
    }

}
