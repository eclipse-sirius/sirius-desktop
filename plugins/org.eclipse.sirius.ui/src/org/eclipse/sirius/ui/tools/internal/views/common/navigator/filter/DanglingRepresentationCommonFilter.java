/*******************************************************************************
 * Copyright (c) 2014, 2016 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

/**
 * Filter to allow the user to hide {@link RepresentationItemImpl} or
 * {@link DRepresentationDescriptor} if there is no target semantic element or
 * if this element no more belongs to a session.
 * 
 * @author mporhel
 * 
 */
public class DanglingRepresentationCommonFilter extends ViewerFilter {

    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element) {
        DRepresentation representation = null;
        if (element instanceof DRepresentation) {
            representation = (DRepresentation) element;
        } else if (element instanceof DRepresentationDescriptor) {
            representation = ((DRepresentationDescriptor) element).getRepresentation();
        } else if (element instanceof RepresentationItemImpl) {
            DRepresentationDescriptor repDesc = ((RepresentationItemImpl) element).getDRepresentationDescriptor();
            if (repDesc != null) {
                representation = repDesc.getRepresentation();
            }
        }

        if (representation != null) {
            return !new DRepresentationQuery(representation).isDanglingRepresentation();
        }

        return true;
    }

}
