/*******************************************************************************
 * Copyright (c) 2014, 2017 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.ui.tools.internal.views.common.navigator.filter;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.sirius.business.api.query.DRepresentationDescriptorQuery;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.ui.tools.internal.views.common.item.RepresentationItemImpl;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

/**
 * Filter to allow the user to hide {@link RepresentationItemImpl} or {@link DRepresentationDescriptor} if there is no
 * target semantic element or if this element no more belongs to a session.
 * 
 * @author mporhel
 * 
 */
public class DanglingRepresentationCommonFilter extends ViewerFilter {

    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element) {
        boolean select = true;
        DRepresentationDescriptor repDesc = null;
        if (element instanceof DRepresentation) {
            select = !new DRepresentationQuery((DRepresentation) element).isDanglingRepresentation();
        } else if (element instanceof DRepresentationDescriptor) {
            repDesc = (DRepresentationDescriptor) element;
        } else if (element instanceof RepresentationItemImpl) {
            repDesc = ((RepresentationItemImpl) element).getDRepresentationDescriptor();
        }

        if (repDesc != null) {
            select = !new DRepresentationDescriptorQuery(repDesc).isDangling();
        }

        return select;
    }
}
