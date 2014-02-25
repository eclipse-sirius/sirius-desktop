/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.palette;

import org.eclipse.gef.Tool;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gmf.runtime.diagram.ui.services.palette.PaletteFactory;

/**
 * A specific {@link PaletteFactory} to create a {@link CreationTool} with the
 * specified {@link CreationFactory}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class CreationToolPaletteFactory implements PaletteFactory {

    private CreationFactory creationFactory;

    /**
     * Default constructor.
     * 
     * @param creationFactory
     *            the {@link CreationFactory} to use to create the
     *            {@link CreationTool}
     */
    public CreationToolPaletteFactory(CreationFactory creationFactory) {
        this.creationFactory = creationFactory;
    }

    /**
     * Overridden to create a {@link CreationTool} with the specified
     * {@link CreationFactory}.
     * 
     * {@inheritDoc}
     */
    public Tool createTool(String toolId) {
        CreationTool creationTool = new CreationTool(creationFactory);
        return creationTool;
    }

    /**
     * {@inheritDoc}
     */
    public Object getTemplate(String templateId) {
        return creationFactory;
    }

}
