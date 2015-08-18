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

import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions.TabbarAlignMenuManager;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.contributions.expressions.DDiagramElementTabbarExpression;
import org.eclipse.ui.menus.IContributionRoot;
import org.eclipse.ui.services.IServiceLocator;

/**
 * ExtensionContributionFactory responsible for Align menu tabbar item creation.
 * 
 * @author fbarbin
 */
public class AlignMenutExtensionContributionFactory extends SiriusTabbarExtensionContributionFactory {

    @Override
    public void createContributionItems(IServiceLocator serviceLocator, IContributionRoot additions) {

        super.createContributionItems(serviceLocator, additions);
        TabbarAlignMenuManager alignMenu = new TabbarAlignMenuManager();
        alignMenu.setDefaultAction("org.eclipse.gef.align_left"); //$NON-NLS-1$

        additions.addContributionItem(alignMenu, new DDiagramElementTabbarExpression());
    }

}
