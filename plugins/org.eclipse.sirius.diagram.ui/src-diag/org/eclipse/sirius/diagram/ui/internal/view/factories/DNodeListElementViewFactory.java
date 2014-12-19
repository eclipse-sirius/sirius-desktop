/*******************************************************************************
 * Copyright (c) 2007-2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.view.factories;

import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.view.factories.AbstractLabelViewFactory;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.Style;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListElementEditPart;
import org.eclipse.sirius.diagram.ui.internal.refresh.diagram.ViewPropertiesSynchronizer;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.viewpoint.DStylizable;

import com.google.common.collect.Lists;

/**
 * @was-generated
 */
public class DNodeListElementViewFactory extends AbstractLabelViewFactory {

    /**
     * @was-generated
     */
    @Override
    protected List<?> createStyles(View view) {
        List<Style> styles = Lists.newArrayList();
        styles.add(NotationFactory.eINSTANCE.createFontStyle());
        return styles;
    }

    /**
     * @not-generated
     */
    @Override
    protected void decorateView(View containerView, View view, IAdaptable semanticAdapter, String semanticHint, int index, boolean persisted) {
        final EObject semantic = (EObject) semanticAdapter.getAdapter(EObject.class);
        if (semantic instanceof DDiagramElement) {
            view.setVisible(((DDiagramElement) semantic).isVisible());
        }

        if (semanticHint == null) {
            semanticHint = SiriusVisualIDRegistry.getType(DNodeListElementEditPart.VISUAL_ID);
            view.setType(semanticHint);
        }

        super.decorateView(containerView, view, semanticAdapter, semanticHint, index, persisted);

        if (semantic instanceof DStylizable) {
            new ViewPropertiesSynchronizer().synchronizeViewProperties(view);
        }
    }
}
