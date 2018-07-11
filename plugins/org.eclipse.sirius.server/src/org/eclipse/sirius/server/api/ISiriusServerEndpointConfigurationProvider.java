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
package org.eclipse.sirius.server.api;

import javax.websocket.server.ServerEndpointConfig;

/**
 * Interface used to contribute WebSocket endpoints to the server.
 *
 * @author sbegaudeau
 */
public interface ISiriusServerEndpointConfigurationProvider {
    /**
     * Returns the configuration of the WebSocket endpoint.
     * 
     * @return The configuration of the WebSocket endpoint
     */
    ServerEndpointConfig getEndpointConfiguration();
}
