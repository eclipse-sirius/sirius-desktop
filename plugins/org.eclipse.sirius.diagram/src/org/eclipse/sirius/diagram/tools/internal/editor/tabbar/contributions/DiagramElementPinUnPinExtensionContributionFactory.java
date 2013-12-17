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
package org.eclipse.sirius.diagram.tools.internal.editor.tabbar.contributions;

import org.eclipse.sirius.diagram.tools.internal.editor.tabbar.actions.TabbarPinElementsEclipseAction;
import org.eclipse.sirius.diagram.tools.internal.editor.tabbar.actions.TabbarUnpinElementsEclipseAction;
import org.eclipse.sirius.diagram.tools.internal.editor.tabbar.contributions.expressions.DDiagramElementTabbarExpression;
import org.eclipse.ui.menus.IContributionRoot;
import org.eclipse.ui.services.IServiceLocator;

/**
 * ExtensionContributionFactory responsible for pin/unpin tabbar item creation.
 * 
 * @author fbarbin
 */
public class DiagramElementPinUnPinExtensionContributionFactory extends SiriusTabbarExtensionContributionFactory {

    @Override
    public void createContributionItems(IServiceLocator serviceLocator, IContributionRoot additions) {

        super.createContributionItems(serviceLocator, additions);
        TabbarPinElementsEclipseAction pinAction = new TabbarPinElementsEclipseAction();
        TabbarUnpinElementsEclipseAction unpinAction = new TabbarUnpinElementsEclipseAction();
        pinAction.setOppositePinAction(unpinAction);
        unpinAction.setOppositePinAction(pinAction);
        additions.addContributionItem(new TabbarActionContributionItem(pinAction, getPart()), new DDiagramElementTabbarExpression());
        additions.addContributionItem(new TabbarActionContributionItem(unpinAction, getPart()), new DDiagramElementTabbarExpression());

    }
}
