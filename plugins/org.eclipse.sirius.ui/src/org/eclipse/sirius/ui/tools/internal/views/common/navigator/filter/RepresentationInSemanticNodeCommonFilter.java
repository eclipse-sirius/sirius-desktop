/*******************************************************************************
 * Copyright (c) 2011, 2016 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.ui.tools.api.views.common.item.RepresentationDescriptionItem;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

/**
 * Filter to allow the user to hide {@link DRepresentation} displayed as
 * children of semantic resource nodes.
 * 
 * @author <a href="mailto:julien.dupont@obeo.fr">Julien DUPONT</a>
 * 
 */
public class RepresentationInSemanticNodeCommonFilter extends ViewerFilter {

    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element) {
        if ((element instanceof DRepresentation || element instanceof DRepresentationDescriptor) && parentElement instanceof TreePath) {
            TreePath tp = (TreePath) parentElement;
            return tp.getLastSegment() instanceof RepresentationDescriptionItem;
        }
        return true;
    }

    @Override
    public boolean isFilterProperty(Object element, String property) {
        return true;
    }

}
