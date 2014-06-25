/*******************************************************************************
 * Copyright (c) 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.common.navigator.filter;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.ui.tools.internal.views.common.item.RepresentationItemImpl;
import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * Filter to allow the user to hide {@link RepresentationItemIml} or
 * {@link DRepresentation} if there is no target semantic element or if this
 * element no more belongs to a session.
 * 
 * @author mporhel
 * 
 */
public class DanglingRepresentationCommonFilter extends ViewerFilter {

    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element) {
        DRepresentation rep = null;
        if (element instanceof DRepresentation) {
            rep = (DRepresentation) element;
        } else if (element instanceof RepresentationItemImpl) {
            rep = ((RepresentationItemImpl) element).getRepresentation();
        }

        if (rep != null) {
            return !new DRepresentationQuery(rep).isDanglingRepresentation();
        }

        return true;
    }

}
