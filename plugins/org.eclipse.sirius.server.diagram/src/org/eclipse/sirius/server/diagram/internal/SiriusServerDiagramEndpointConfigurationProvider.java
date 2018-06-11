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
package org.eclipse.sirius.server.diagram.internal;

import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Builder;

import org.eclipse.sirius.server.api.ISiriusServerEndpointConfigurationProvider;

/**
 * The endpoint configuration provider used to register the diagram API.
 *
 * @author sbegaudeau
 */
public class SiriusServerDiagramEndpointConfigurationProvider implements ISiriusServerEndpointConfigurationProvider {

    /**
     * The path of the diagram WebSocket API.
     */
    private static final String PATH = "/diagrams"; //$NON-NLS-1$

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.server.api.ISiriusServerEndpointConfigurationProvider#getEndpointConfiguration()
     */
    @Override
    public ServerEndpointConfig getEndpointConfiguration() {
        // @formatter:off
        return Builder.create(SiriusServerDiagramEndpoint.class, PATH)
                .configurator(new SiriusServerDiagramEndpointConfigurator())
                .build();
        // @formatter:on
    }

}
