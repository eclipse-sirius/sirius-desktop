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
package org.eclipse.sirius.diagram.ui.tools.api.layout.ordering;

import org.eclipse.sirius.diagram.description.DiagramElementMapping;

/**
 * Represents an object that provides {@link ViewOrdering}.
 * 
 * @author ymortier
 */
public interface ViewOrderingProvider {

    /**
     * Return <code>true</code> if this provider provides {@link ViewOrdering}s.
     * for the specified mapping.
     * 
     * @param mapping
     *            the mapping.
     * @return <code>true</code> if this provider provides {@link ViewOrdering}
     *         s. for the specified mapping.
     */
    boolean provides(DiagramElementMapping mapping);

    /**
     * Return the {@link ViewOrdering} to use for the specified
     * {@link DiagramElementMapping}.
     * 
     * @param mapping
     *            the mapping.
     * @return the {@link ViewOrdering} to use for the specified
     *         {@link DiagramElementMapping}.
     */
    ViewOrdering getViewOrdering(DiagramElementMapping mapping);

}
