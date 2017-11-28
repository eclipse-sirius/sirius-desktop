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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.Style;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainer2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerName2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartment2EditPart;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;

/**
 * @was-generated
 */
public class DNodeContainer2ViewFactory extends AbstractContainerViewFactory {

    /**
     * @was-generated
     */
    protected List<?> createStyles(View view) {
        List<Style> styles = new ArrayList<>();
        styles.add(NotationFactory.eINSTANCE.createShapeStyle());
        return styles;
    }

    /**
     * @was-generated
     */
    protected void decorateView(View containerView, View view, IAdaptable semanticAdapter, String semanticHint, int index, boolean persisted) {
        if (semanticHint == null) {
            semanticHint = SiriusVisualIDRegistry.getType(DNodeContainer2EditPart.VISUAL_ID);
            view.setType(semanticHint);
        }
        super.decorateView(containerView, view, semanticAdapter, semanticHint, index, persisted);
        IAdaptable eObjectAdapter = null;
        EObject eObject = semanticAdapter.getAdapter(EObject.class);
        if (eObject != null) {
            eObjectAdapter = new EObjectAdapter(eObject);
        }
        getViewService().createNode(eObjectAdapter, view, SiriusVisualIDRegistry.getType(DNodeContainerName2EditPart.VISUAL_ID), ViewUtil.APPEND, true, getPreferencesHint());
        getViewService()
                .createNode(eObjectAdapter, view, SiriusVisualIDRegistry.getType(DNodeContainerViewNodeContainerCompartment2EditPart.VISUAL_ID), ViewUtil.APPEND, true, getPreferencesHint());
        updateLayoutConstraint(view, semanticAdapter);
    }
}
