/*******************************************************************************
 * Copyright (c) 2012, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.contributions;

import java.util.Collection;

import org.eclipse.gmf.runtime.diagram.ui.actions.internal.CopyAppearancePropertiesAction;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.ui.graphical.edit.part.specific.BracketEdgeEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.action.SetStyleToWorkspaceImageContributionItem;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.style.ResetStylePropertiesToDefaultValuesAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.style.SetStyleToWorkspaceImageAction;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions.TabbarColorPropertyContributionItem;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions.TabbarRouterMenuManager;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.contributions.expressions.DDiagramElementTabbarExpression;
import org.eclipse.ui.menus.IContributionRoot;
import org.eclipse.ui.services.IServiceLocator;

/**
 * 
 * ExtensionContributionFactory responsible for style tabbar item creation.
 * 
 * @author fbarbin
 */
public class DiagramElementStyleExtensionContributionFactory extends SiriusTabbarExtensionContributionFactory {

    @Override
    public void createContributionItems(IServiceLocator serviceLocator, IContributionRoot additions) {

        super.createContributionItems(serviceLocator, additions);
        createFillColorMenu(additions);
        createLineColorMenu(additions);

        createRouterManagerMenu(additions);

        additions.addContributionItem(new SetStyleToWorkspaceImageContributionItem(new SetStyleToWorkspaceImageAction(), getPage(), getPart()), new DDiagramElementTabbarExpression());
        ResetStylePropertiesToDefaultValuesAction resetStylePropertiesToDefaultValuesAction = new ResetStylePropertiesToDefaultValuesAction(getPage());
        additions.addContributionItem(new TabbarActionContributionItem(resetStylePropertiesToDefaultValuesAction, getPart()), new DDiagramElementTabbarExpression());

        CopyAppearancePropertiesAction copyAppearancePropertiesAction = new CopyAppearancePropertiesAction(getPage());
        additions.addContributionItem(new ActionContributionItem(copyAppearancePropertiesAction), new DDiagramElementTabbarExpression());

    }

    private void createRouterManagerMenu(IContributionRoot additions) {
        MenuManager routerManager = new TabbarRouterMenuManager(getPage());
        additions.addContributionItem(routerManager, new RouterManagerMenuTestExpression());
    }

    private void createFillColorMenu(IContributionRoot additions) {
        TabbarColorPropertyContributionItem fillColorMenu = TabbarColorPropertyContributionItem.createFillColorContributionItem(getPage());
        fillColorMenu.setActionWorkbenchPart(getPart());
        additions.addContributionItem(fillColorMenu, new DDiagramElementTabbarExpression());
    }

    private void createLineColorMenu(IContributionRoot additions) {
        TabbarColorPropertyContributionItem lineColorMenu = TabbarColorPropertyContributionItem.createLineColorContributionItem(getPage());
        lineColorMenu.setActionWorkbenchPart(getPart());
        additions.addContributionItem(lineColorMenu, new DDiagramElementTabbarExpression());
    }

    /**
     * Test expression to display or not router menu.
     * 
     * @author fbarbin
     * 
     */
    private class RouterManagerMenuTestExpression extends DDiagramElementTabbarExpression {

        @Override
        protected boolean isVisible(IStructuredSelection selection) {
            Collection<Object> selectedElements = selection.toList();
            boolean hasBracketEdgeSelected = false;
            for (Object selectedElt : selectedElements) {
                if (selectedElt instanceof BracketEdgeEditPart) {
                    hasBracketEdgeSelected = true;
                    break;
                }
            }
            return super.isVisible(selection) && !hasBracketEdgeSelected;
        }

    }
}
