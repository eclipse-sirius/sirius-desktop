/*******************************************************************************
 * Copyright (c) 2012, 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.contributions;

import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions.TabbarArrangeMenuManager;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions.TabbarSelectMenuManager;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.contributions.expressions.DDiagramAndDDiagramElementTabbarExpression;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.contributions.expressions.DDiagramTabbarExpression;
import org.eclipse.ui.menus.IContributionRoot;
import org.eclipse.ui.services.IServiceLocator;

/**
 * ExtensionContributionFactory responsible for Arrange all tabbar item
 * creation.
 * 
 * @author fbarbin
 */
public class ArrangeAllContributionFactory extends SiriusTabbarExtensionContributionFactory {

    @Override
    public void createContributionItems(IServiceLocator serviceLocator, IContributionRoot additions) {

        super.createContributionItems(serviceLocator, additions);
        TabbarArrangeMenuManager arrangeMenu = new TabbarArrangeMenuManager(getPart());

        additions.addContributionItem(arrangeMenu, new DDiagramAndDDiagramElementTabbarExpression());

        TabbarSelectMenuManager selectMenu = new TabbarSelectMenuManager();
        selectMenu.setDefaultAction("toolbarSelectAllAction"); //$NON-NLS-1$

        additions.addContributionItem(selectMenu, new DDiagramTabbarExpression());

    }

}
