/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.api.diagramtype;

import org.eclipse.sirius.diagram.CollapseFilter;
import org.eclipse.sirius.diagram.DDiagramElement;

/**
 * A {@link ICollapseUpdater} has the responsibility to update the graphical
 * filter ({@link CollapseFilter} or
 * {@link org.eclipse.sirius.viewpoint.IndirectlyCollapseFilter}) and the bounds of the
 * GMF Node corresponding to this {@link DDiagramElement} according to the
 * collapse changes.
 * 
 * @author lredor
 */
public interface ICollapseUpdater {

    /**
     * Update the graphical filter ({@link CollapseFilter} or
     * {@link org.eclipse.sirius.viewpoint.IndirectlyCollapseFilter}) of
     * <code>element</code> and update bounds of the GMF Node according to the
     * collapse changes.
     * 
     * @param element
     *            The {@link DDiagramElement} to update
     * @param add
     *            true if a new filter must be added to element, false if all
     *            filters of kind <code>kindOfFilter</code> must be removed.
     * @param kindOfFilter
     *            the kind of filter to add or remove ( {@link CollapseFilter}
     *            or {@link org.eclipse.sirius.viewpoint.IndirectlyCollapseFilter}
     */
    void synchronizeCollapseFiltersAndGMFBounds(DDiagramElement element, boolean add, Class<? extends CollapseFilter> kindOfFilter);
}
