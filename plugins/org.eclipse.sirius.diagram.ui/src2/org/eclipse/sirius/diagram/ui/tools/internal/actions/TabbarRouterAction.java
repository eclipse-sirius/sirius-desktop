/*******************************************************************************
 * Copyright (c) 2010, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.actions;

import java.util.List;
import java.util.ListIterator;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.RouterAction;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.l10n.DiagramUIActionsMessages;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.l10n.DiagramUIActionsPluginImages;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.l10n.DiagramUIPluginImages;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPage;

/**
 * Edge Routing action.
 * 
 * @author nlepine
 * 
 */
public class TabbarRouterAction extends RouterAction {

    /**
     * Construct a new instance.
     * 
     * @param workbenchPage
     *            the page
     * @param routerType
     *            the routing type
     */
    public TabbarRouterAction(IWorkbenchPage workbenchPage, Routing routerType) {
        super(workbenchPage, routerType);
    }

    /**
     * {@inheritDoc}
     */
    // CHECKSTYLE:OFF
    @Override
    protected boolean calculateEnabled() {
        if (org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds.ROUTING_STYLE.equals(getId())) {
            List<?> selected = getSelectedObjects();
            if (selected.size() < 1) {
                return false;
            }

            ListIterator<?> li = selected.listIterator();
            while (li.hasNext()) {
                if (!(li.next() instanceof ITreeBranchEditPart)) {
                    return false;
                }
            }
        }

        Command command = getCommand();
        return command != null && command.canExecute();
        // CHECKSTYLE:ON
    }

    /**
     * Creates the tree router action.
     * 
     * @param workbenchPage
     *            the page
     * @return the created tree action
     */
    public static RouterAction createTreeRouterAction(IWorkbenchPage workbenchPage) {
        RouterAction action = new TabbarRouterAction(workbenchPage, Routing.TREE_LITERAL);
        action.setId(org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds.ROUTING_STYLE);
        action.setText(DiagramUIActionsMessages.ChangeRouterAction_Tree_ActionLabelText);
        action.setToolTipText(DiagramUIActionsMessages.ChangeRouterAction_Tree_ActionToolTipText);

        ImageDescriptor enabledImage = DiagramUIPluginImages.DESC_CHANGEROUTERACTION_TREE;
        action.setImageDescriptor(enabledImage);
        action.setDisabledImageDescriptor(DiagramUIPluginImages.DESC_CHANGEROUTERACTION_TREE_DISABLED);
        action.setHoverImageDescriptor(enabledImage);
        return action;
    }

    /**
     * Creates the rectilinear router action.
     * 
     * @param workbenchPage
     *            the page
     * @return rectilinear router action
     */
    public static RouterAction createRectilinearRouterAction(IWorkbenchPage workbenchPage) {
        RouterAction action = new TabbarRouterAction(workbenchPage, Routing.RECTILINEAR_LITERAL);
        action.setId(ActionIds.ACTION_ROUTER_RECTILINEAR);
        action.setText(DiagramUIActionsMessages.ChangeRouterAction_Rectilinear_ActionLabelText);
        action.setToolTipText(DiagramUIActionsMessages.ChangeRouterAction_Rectilinear_ActionToolTipText);

        action.setImageDescriptor(DiagramUIActionsPluginImages.DESC_CHANGEROUTERACTION_RECTILINEAR);
        action.setDisabledImageDescriptor(DiagramUIActionsPluginImages.DESC_CHANGEROUTERACTION_RECTILINEAR_DISABLED);
        action.setHoverImageDescriptor(DiagramUIActionsPluginImages.DESC_CHANGEROUTERACTION_RECTILINEAR);
        return action;
    }

    /**
     * Creates the oblique router action.
     * 
     * @param workbenchPage
     *            the page
     * @return oblique router action
     */
    public static RouterAction createObliqueRouterAction(IWorkbenchPage workbenchPage) {
        RouterAction action = new TabbarRouterAction(workbenchPage, Routing.MANUAL_LITERAL);
        action.setId(ActionIds.ACTION_ROUTER_OBLIQUE);
        action.setText(DiagramUIActionsMessages.ChangeRouterAction_Oblique_ActionLabelText);
        action.setToolTipText(DiagramUIActionsMessages.ChangeRouterAction_Oblique_ActionToolTipText);

        ImageDescriptor enabledImage = DiagramUIPluginImages.DESC_CHANGEROUTERACTION_OBLIQUE;
        action.setImageDescriptor(enabledImage);
        action.setDisabledImageDescriptor(DiagramUIPluginImages.DESC_CHANGEROUTERACTION_OBLIQUE_DISABLED);
        action.setHoverImageDescriptor(enabledImage);
        return action;
    }
}
