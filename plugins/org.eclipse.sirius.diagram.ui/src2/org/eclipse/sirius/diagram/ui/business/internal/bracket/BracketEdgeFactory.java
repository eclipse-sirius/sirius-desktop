/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.bracket;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.graphical.edit.part.specific.BracketEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.view.factories.DEdgeViewFactory;

/**
 * A specific {@link DEdgeViewFactory} for {@link BracketEdgeEditPart}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class BracketEdgeFactory extends DEdgeViewFactory {

    /**
     * Overridden to set the correct {@link View#getType()}, i.e. the
     * {@link BracketEdgeEditPart#VISUAL_ID}.
     * 
     * {@inheritDoc}
     */
    @Override
    protected void decorateView(View containerView, View view, IAdaptable semanticAdapter, String semanticHint, int index, boolean persisted) {
        super.decorateView(containerView, view, semanticAdapter, semanticHint, index, persisted);
        view.setType(String.valueOf(BracketEdgeEditPart.VISUAL_ID));
    }
}
