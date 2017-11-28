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
import org.eclipse.gmf.runtime.diagram.ui.view.factories.BasicNodeViewFactory;
import org.eclipse.gmf.runtime.notation.DrawerStyle;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Style;
import org.eclipse.gmf.runtime.notation.TitleStyle;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;

/**
 * @was-generated
 */
public class DNodeContainerViewNodeContainerCompartmentViewFactory extends BasicNodeViewFactory {

    /**
     * @was-generated
     */
    protected List<?> createStyles(View view) {
        List<Style> styles = new ArrayList<>();
        styles.add(NotationFactory.eINSTANCE.createSortingStyle());
        styles.add(NotationFactory.eINSTANCE.createFilteringStyle());
        return styles;
    }

    /**
     * @was-generated
     */
    protected void decorateView(View containerView, View view, IAdaptable semanticAdapter, String semanticHint, int index, boolean persisted) {
        if (semanticHint == null) {
            semanticHint = SiriusVisualIDRegistry.getType(DNodeContainerViewNodeContainerCompartmentEditPart.VISUAL_ID);
            view.setType(semanticHint);
        }
        super.decorateView(containerView, view, semanticAdapter, semanticHint, index, persisted);
        setupCompartmentTitle(view);
        setupCompartmentCollapsed(view);
    }

    /**
     * @was-generated
     */
    protected void setupCompartmentTitle(View view) {
        TitleStyle titleStyle = (TitleStyle) view.getStyle(NotationPackage.eINSTANCE.getTitleStyle());
        if (titleStyle != null) {
            titleStyle.setShowTitle(true);
        }
    }

    /**
     * @was-generated
     */
    protected void setupCompartmentCollapsed(View view) {
        DrawerStyle drawerStyle = (DrawerStyle) view.getStyle(NotationPackage.eINSTANCE.getDrawerStyle());
        if (drawerStyle != null) {
            drawerStyle.setCollapsed(false);
        }
    }
}
