/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.outline;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.ui.business.api.provider.AbstractDDiagramElementLabelItemProvider;
import org.eclipse.sirius.diagram.ui.business.api.provider.DEdgeBeginLabelItemProvider;
import org.eclipse.sirius.diagram.ui.business.api.provider.DEdgeEndLabelItemProvider;
import org.eclipse.sirius.diagram.ui.business.api.provider.DEdgeLabelItemProvider;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeBeginNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEndNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.ui.IObjectActionDelegate;

/**
 * {@link IObjectActionDelegateWrapperWithImageDescription} dedicated to the contextual action "Show label" of the
 * outline.
 * 
 * @author <a href="mailto:steve.monnier@obeosoft.ca">Steve Monnier</a>
 *
 */
public class RevealOutlineLabelsActionWrapper extends IObjectActionDelegateWrapperWithImageDescription {

    /**
     * Constructor.
     * 
     * @param action
     *            the delegated action to wrap
     * 
     * @param text
     *            the string used as the text for the action, or <code>null</code> if there is no text
     */
    public RevealOutlineLabelsActionWrapper(IObjectActionDelegate action, String text) {
        super(action, text);
    }

    @Override
    public boolean isEnabled() {
        boolean result = false;
        if (currentSelection instanceof IStructuredSelection) {
            final Object selectedObject = ((IStructuredSelection) currentSelection).getFirstElement();
            if (selectedObject instanceof DDiagramElement) {
                DDiagramElementQuery query = new DDiagramElementQuery((DDiagramElement) selectedObject);
                result = query.isLabelHidden();
            } else if (selectedObject instanceof AbstractDDiagramElementLabelItemProvider) {
                DDiagramElement dDiagramElement = ((AbstractDDiagramElementLabelItemProvider) selectedObject).getDiagramElementTarget().get();
                DDiagramElementQuery query = new DDiagramElementQuery(dDiagramElement);
                if (selectedObject instanceof DEdgeBeginLabelItemProvider) {
                    result = query.isLabelHidden(DEdgeBeginNameEditPart.VISUAL_ID);
                } else if (selectedObject instanceof DEdgeLabelItemProvider) {
                    result = query.isLabelHidden(DEdgeNameEditPart.VISUAL_ID);
                } else if (selectedObject instanceof DEdgeEndLabelItemProvider) {
                    result = query.isLabelHidden(DEdgeEndNameEditPart.VISUAL_ID);
                } else {
                    result = query.isLabelHidden();
                }
            }
        }
        return result;
    }
}
