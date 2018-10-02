/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.layoutdata.tools.api.util.configuration;

import org.eclipse.sirius.diagram.layoutdata.tools.internal.util.configuration.ConfigurationImpl;

/**
 * Factory of {@link Configuration}.
 * 
 * @author dlecan
 */
public final class ConfigurationFactory {

    private ConfigurationFactory() {
        // Nothing
    }

    /**
     * Build a {@link Configuration}.
     * 
     * @return New configuration.
     */
    public static Configuration buildConfiguration() {
        return new ConfigurationImpl();
    }

}
