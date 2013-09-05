/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.tools.api.command;

import org.eclipse.sirius.table.tools.internal.command.TableCommandFactoryProvider;

/**
 * The Table Command Factory service. For the moment the class has only one
 * provider.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public final class TableCommandFactoryService {

    private static final TableCommandFactoryService INSTANCE = new TableCommandFactoryService();

    private TableCommandFactoryService() {

    }

    /**
     * Get the single instance.
     * 
     * @return the single instance
     */
    public static TableCommandFactoryService getInstance() {
        return INSTANCE;
    }

    /**
     * Get the Table Command Factory provider.
     * 
     * @return the Table Command Factory provider
     */
    public ITableCommandFactoryProvider getNewProvider() {
        return new TableCommandFactoryProvider();
    }
}
