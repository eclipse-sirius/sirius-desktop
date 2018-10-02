/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.internal.view.factories;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.view.factories.AbstractShapeViewFactory;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.internal.refresh.diagram.ViewPropertiesSynchronizer;
import org.eclipse.sirius.viewpoint.DStylizable;

/**
 * Creates and decorates GMF views.
 * 
 * @author ymortier
 */
public abstract class AbstractDesignerNodeFactory extends AbstractShapeViewFactory {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.view.factories.BasicNodeViewFactory#decorateView(org.eclipse.gmf.runtime.notation.View,
     *      org.eclipse.gmf.runtime.notation.View,
     *      org.eclipse.core.runtime.IAdaptable, java.lang.String, int, boolean)
     */
    @Override
    protected void decorateView(final View containerView, final View view, final IAdaptable semanticAdapter, final String semanticHint, final int index, final boolean persisted) {
        super.decorateView(containerView, view, semanticAdapter, semanticHint, index, persisted);
        final EObject semantic = semanticAdapter.getAdapter(EObject.class);
        if (semantic instanceof DStylizable) {
            new ViewPropertiesSynchronizer().synchronizeViewProperties(view);
        }
    }

}
