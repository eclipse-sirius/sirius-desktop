/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.layout.provider;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.AbstractLayoutEditPartProvider;

/**
 * A class that provides
 * {@link org.eclipse.gmf.runtime.diagram.ui.services.layout.AbstractLayoutNodeProvider}
 * for a specific container.
 * 
 * @author ymortier
 */
public interface LayoutProvider {
    /**
     * Simple flag to easily switch between the old/new behaviors during
     * development.
     */
    boolean ENABLE_BORDERED_NODES_ARRANGE_ALL = true;

    /**
     * Return <code>true</code> if this provider provides a
     * {@link AbstractLayoutEditPartProvider} for the specified container. A
     * diagram layout provider should return <code>true</code> if container is
     * an instance of
     * {@link org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart}.
     * 
     * @param container
     *            the container.
     * @return <code>true</code> if this provider provides a
     *         {@link AbstractLayoutEditPartProvider} for the specified
     *         container.
     */
    boolean provides(IGraphicalEditPart container);

    /**
     * Return the {@link AbstractLayoutEditPartProvider} to use with the
     * container.
     * 
     * @param container
     *            the container.
     * @return the {@link AbstractLayoutEditPartProvider} to use with the
     *         container.
     */
    AbstractLayoutEditPartProvider getLayoutNodeProvider(IGraphicalEditPart container);

    /**
     * Check if this provider is a diagram provider.
     * 
     * @return <code>true</code> if this provider is a diagram provider.
     */
    boolean isDiagramLayoutProvider();
}
