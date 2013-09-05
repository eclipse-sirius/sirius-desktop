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
package org.eclipse.sirius.ui.tools.internal.views.common.navigator.filter;

import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import org.eclipse.sirius.DRepresentation;
import org.eclipse.sirius.ui.tools.api.views.common.item.RepresentationDescriptionItem;

/**
 * Filter to allow the user to hide {@link DRepresentation} displayed as
 * children of semantic resource nodes.
 * 
 * @author <a href="mailto:julien.dupont@obeo.fr">Julien DUPONT</a>
 * 
 */
public class RepresentationInSemanticNodeCommonFilter extends ViewerFilter {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element) {
        if (element instanceof DRepresentation && parentElement instanceof TreePath) {
            TreePath tp = (TreePath) parentElement;
            return tp.getLastSegment() instanceof RepresentationDescriptionItem;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFilterProperty(Object element, String property) {
        return true;
    }

}
