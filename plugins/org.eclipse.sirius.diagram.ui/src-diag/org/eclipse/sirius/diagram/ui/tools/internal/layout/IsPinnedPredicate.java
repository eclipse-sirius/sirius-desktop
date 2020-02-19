/*******************************************************************************
 * Copyright (c) 2010, 2020 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.layout;

import java.util.List;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.ui.business.api.query.EditPartQuery;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;

import com.google.common.base.Predicate;

/**
 * A predicate to identify pinned/fixed edit-parts.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class IsPinnedPredicate implements Predicate<IGraphicalEditPart> {

    private final List<IDiagramElementEditPart> elementsToKeepFixed;

    /**
     * Default constructor.
     * 
     * @param elementsToKeepFixed
     *            IDiagramElementEditPart which are not actually pinned but have to stay fixed.
     */
    protected IsPinnedPredicate(List<IDiagramElementEditPart> elementsToKeepFixed) {
        this.elementsToKeepFixed = elementsToKeepFixed;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.google.common.base.Predicate#apply(java.lang.Object)
     */
    @Override
    public boolean apply(final IGraphicalEditPart part) {
        return !(new EditPartQuery(part).isMovableByAutomaticLayout(elementsToKeepFixed));
    }
}
