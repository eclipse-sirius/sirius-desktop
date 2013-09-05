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
package org.eclipse.sirius.tree.business.api.command;

import org.eclipse.sirius.tree.tools.internal.command.TreeCommandFactoryProvider;

/**
 * The Tree Command Factory service. For the moment the class has only one
 * provider.
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public final class TreeCommandFactoryService {

    private static final TreeCommandFactoryService INSTANCE = new TreeCommandFactoryService();

    private TreeCommandFactoryService() {

    }

    /**
     * Get the single instance.
     * 
     * @return the single instance
     */
    public static TreeCommandFactoryService getInstance() {
        return INSTANCE;
    }

    /**
     * Get the Table Command Factory provider.
     * 
     * @return the Table Command Factory provider
     */
    public ITreeCommandFactoryProvider getNewProvider() {
        return new TreeCommandFactoryProvider();
    }
}
