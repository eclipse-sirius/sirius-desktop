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
package org.eclipse.sirius.diagram.tools.internal.editor.tabbar.contributions;

import org.eclipse.ui.menus.IContributionRoot;
import org.eclipse.ui.services.IServiceLocator;

import org.eclipse.sirius.diagram.tools.internal.actions.SelectHiddenElementsAction;
import org.eclipse.sirius.diagram.tools.internal.actions.SelectPinnedElementsAction;
import org.eclipse.sirius.diagram.tools.internal.editor.tabbar.actions.DiagramActionContributionItem;
import org.eclipse.sirius.diagram.tools.internal.editor.tabbar.contributions.expressions.DDiagramTabbarExpression;

/**
 * 
 * ExtensionContributionFactory responsible for Show/Hide and Pin/Unpin tabbar
 * item creation.
 * 
 * @author fbarbin
 */
public class ShowHidePinUnPinExtensionContributionFactory extends SiriusTabbarExtensionContributionFactory {

    @Override
    public void createContributionItems(IServiceLocator serviceLocator, IContributionRoot additions) {

        super.createContributionItems(serviceLocator, additions);
        SelectPinnedElementsAction pinnedElementsAction = new SelectPinnedElementsAction(getPage(), getPart());
        SelectHiddenElementsAction selectHiddenElementsAction = new SelectHiddenElementsAction(getPage(), getPart());

        additions.addContributionItem(new DiagramActionContributionItem(selectHiddenElementsAction), new DDiagramTabbarExpression());
        additions.addContributionItem(new DiagramActionContributionItem(pinnedElementsAction), new DDiagramTabbarExpression());

    }

}
