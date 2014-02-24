/*******************************************************************************
 * Copyright (c) 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.api.command;

import org.eclipse.sirius.diagram.tools.internal.command.EMFCommandFactoryProvider;

/**
 * The Diagram Command Factory service. For the moment the class has only one
 * provider.
 * 
 * @author mchauvin
 * @since 0.9.0
 */
public final class DiagramCommandFactoryService {

    private static final DiagramCommandFactoryService INSTANCE = new DiagramCommandFactoryService();

    private DiagramCommandFactoryService() {

    }

    /**
     * Get the single instance.
     * 
     * @return the single instance
     */
    public static DiagramCommandFactoryService getInstance() {
        return INSTANCE;
    }

    /**
     * Get the EMF Command Factory provider.
     * 
     * @return the EMF Command Factory provider
     */
    public IDiagramCommandFactoryProvider getNewProvider() {
        return new EMFCommandFactoryProvider();
    }
}
