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
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.view.factories.BasicNodeViewFactory;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;

/**
 * @was-generated
 */
public class DNodeContainerNameViewFactory extends BasicNodeViewFactory {

    @Override
    public View createView(IAdaptable semanticAdapter, View containerView, String semanticHint, int index, boolean persisted, PreferencesHint preferencesHint) {
        View view = super.createView(semanticAdapter, containerView, semanticHint, index, persisted, preferencesHint);
        DDiagramElement dDiagramElement = semanticAdapter.getAdapter(DDiagramElement.class);
        view.setVisible(!new DDiagramElementQuery(dDiagramElement).isLabelHidden());
        return view;
    }

    /**
     * @was-generated
     */
    protected List<?> createStyles(View view) {
        return new ArrayList<>();
    }
}
