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
