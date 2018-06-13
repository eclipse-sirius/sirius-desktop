/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar;

import org.eclipse.gmf.runtime.diagram.ui.actions.internal.SelectAllAction;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.l10n.DiagramUIActionsMessages;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.l10n.DiagramUIActionsPluginImages;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.internal.menu.PopupMenuContribution;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;

/**
 * Contribute a menu to tab bar to activate a specific SelectAll actions. This menu contains GMF global SelectAll
 * actions plus SelectAll actions defined in the VSM. Currently, the action defined in the VSM is just for a POC.
 * 
 * @author lredor
 */
public class SelectAllContributionItem extends AbstractMenuContributionItem {

    /**
     * Default constructor.
     */
    public SelectAllContributionItem() {
        super(DiagramUIActionsMessages.SelectActionMenu_SelectDropDownTooltip);
    }

    @Override
    protected Image getMenuImage() {
        return DiagramUIPlugin.getPlugin().getImage(DiagramUIActionsPluginImages.DESC_SELECTALL);
    }

    @Override
    protected String getLabel() {
        // Add a menu named Select
        return DiagramUIActionsMessages.SelectActionMenu_SelectDropDownText;
    }

    @Override
    protected void menuShow(IMenuManager manager) {
        IWorkbenchPage page = EclipseUIUtil.getActivePage();
        // Add default GMF contributions
        if (page != null) {
            manager.add(SelectAllAction.createToolbarSelectAllAction(page));
            manager.add(SelectAllAction.createToolbarSelectAllConnectionsAction(page));
            manager.add(SelectAllAction.createToolbarSelectAllShapesAction(page));
        }
        // Add a default "additions" group
        manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
        // Add potential select all actions contributed in VSM
        PopupMenuContribution.contributeToPopupMenuProgrammatically(manager, part);
    }

}
