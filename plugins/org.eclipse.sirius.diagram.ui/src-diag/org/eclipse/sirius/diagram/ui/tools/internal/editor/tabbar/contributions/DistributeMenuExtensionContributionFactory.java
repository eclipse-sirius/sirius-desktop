/*******************************************************************************
 * Copyright (c) 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.contributions;

import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions.TabbarDistributeMenuManager;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.contributions.expressions.DDiagramElementTabbarExpression;
import org.eclipse.ui.menus.IContributionRoot;
import org.eclipse.ui.services.IServiceLocator;

/**
 * {@link SiriusTabbarExtensionContributionFactory} to contribute
 * {@link TabbarDistributeMenuManager}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DistributeMenuExtensionContributionFactory extends SiriusTabbarExtensionContributionFactory {

    @Override
    public void createContributionItems(IServiceLocator serviceLocator, IContributionRoot additions) {
        super.createContributionItems(serviceLocator, additions);
        TabbarDistributeMenuManager distributeMenu = new TabbarDistributeMenuManager();
        additions.addContributionItem(distributeMenu, new DDiagramElementTabbarExpression());
    }

}
