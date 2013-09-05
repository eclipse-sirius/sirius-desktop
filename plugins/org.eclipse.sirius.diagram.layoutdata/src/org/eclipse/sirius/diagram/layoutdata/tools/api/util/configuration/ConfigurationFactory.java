/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
