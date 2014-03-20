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
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions;

import org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction;
import org.eclipse.jface.action.ActionContributionItem;

/**
 * {@link ActionContributionItem} implementation for {@link DiagramAction}.
 * 
 * @author fbarbin
 */
public class DiagramActionContributionItem extends ActionContributionItem {

    /**
     * Creates a new contribution item from the given action. The id of the
     * action is used as the id of the item.
     * 
     * @param action
     *            the action
     */
    public DiagramActionContributionItem(DiagramAction action) {
        super(action);
    }

    /**
     * returns the diagram action.
     * 
     * @return the diagram action.
     */
    public DiagramAction getDiagramAction() {
        return (DiagramAction) getAction();
    }

    /**
     * Refresh the enablement of the encapsulated Diagram action and then update
     * the item.
     * 
     * When the diagram part is selected, the update of the tabbar is done
     * before the enablement refresh of the action for selection listener.
     * 
     * {@inheritDoc}
     */
    @Override
    public void update(String propertyName) {
        DiagramAction diagramAction = getDiagramAction();
        if (diagramAction != null) {
            diagramAction.refresh();
        }

        super.update(propertyName);
    }
}
