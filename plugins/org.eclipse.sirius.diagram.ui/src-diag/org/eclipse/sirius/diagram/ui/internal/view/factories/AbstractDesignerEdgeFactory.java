/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.view.factories;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.view.factories.ConnectionViewFactory;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.internal.refresh.diagram.ViewPropertiesSynchronizer;
import org.eclipse.sirius.viewpoint.DStylizable;

/**
 * Creates and decorates GMF views.
 * 
 * @author ymortier
 */
public abstract class AbstractDesignerEdgeFactory extends ConnectionViewFactory {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.view.factories.ConnectionViewFactory#decorateView(org.eclipse.gmf.runtime.notation.View,
     *      org.eclipse.gmf.runtime.notation.View,
     *      org.eclipse.core.runtime.IAdaptable, java.lang.String, int, boolean)
     */
    @Override
    protected void decorateView(final View containerView, final View view, final IAdaptable element, final String semanticHint, final int index, final boolean persisted) {
        super.decorateView(containerView, view, element, semanticHint, index, persisted);
        final EObject semantic = (EObject) element.getAdapter(EObject.class);
        if (semantic instanceof DStylizable) {
            new ViewPropertiesSynchronizer().synchronizeViewProperties(view);
        }
    }

}
