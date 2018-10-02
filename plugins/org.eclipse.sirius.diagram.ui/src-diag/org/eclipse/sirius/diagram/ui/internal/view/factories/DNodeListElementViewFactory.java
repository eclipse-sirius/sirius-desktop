/*******************************************************************************
 * Copyright (c) 2007, 2017 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
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

/**
 * @was-generated
 */
public class DNodeListElementViewFactory extends AbstractLabelViewFactory {

    /**
     * @was-generated
     */
    @Override
    protected List<?> createStyles(View view) {
        List<Style> styles = new ArrayList<>();
        styles.add(NotationFactory.eINSTANCE.createFontStyle());
        return styles;
    }

    /**
     * @not-generated
     */
    @Override
    protected void decorateView(View containerView, View view, IAdaptable semanticAdapter, String semanticHint, int index, boolean persisted) {
        final EObject semantic = semanticAdapter.getAdapter(EObject.class);
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
