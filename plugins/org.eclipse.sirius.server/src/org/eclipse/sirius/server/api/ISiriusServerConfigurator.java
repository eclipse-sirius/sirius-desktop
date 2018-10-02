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

import org.eclipse.jetty.server.Server;

/**
 * Interface used to configure the Sirius server.
 *
 * @author sbegaudeau
 */
public interface ISiriusServerConfigurator {
    /**
     * Configures the given server.
     * 
     * @param server
     *            The server
     */
    void configure(Server server);
}
