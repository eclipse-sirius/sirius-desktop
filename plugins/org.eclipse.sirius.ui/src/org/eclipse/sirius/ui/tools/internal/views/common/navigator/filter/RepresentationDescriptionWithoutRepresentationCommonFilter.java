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

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.sirius.ui.tools.api.views.common.item.RepresentationDescriptionItem;

/**
 * Filter to allow the user to hide {@link RepresentationDescriptionItem} if
 * there is no corresponding {@link org.eclipse.sirius.viewpoint.DRepresentation}.
 * 
 * @author mporhel
 * 
 */
public class RepresentationDescriptionWithoutRepresentationCommonFilter extends ViewerFilter {
    /**
     * The ID of this filter (used in plugin.xml).
     */
    public static final String ID = "org.eclipse.sirius.ui.commonFilter.representation.description"; //$NON-NLS-1$

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element) {
        if (element instanceof RepresentationDescriptionItem) {
            return !((RepresentationDescriptionItem) element).getChildren().isEmpty();
        }
        return true;
    }

}
