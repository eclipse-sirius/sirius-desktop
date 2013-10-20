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

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.ui.menus.IContributionRoot;
import org.eclipse.ui.services.IServiceLocator;

import org.eclipse.sirius.diagram.tools.internal.actions.layout.CopyLayoutAction;
import org.eclipse.sirius.diagram.tools.internal.editor.tabbar.contributions.expressions.DDiagramElementTabbarExpression;

/**
 * ExtensionContributionFactory responsible for Copy layout action tabbar item
 * creation.
 * 
 * @author fbarbin
 */
public class DiagramElementCopyExtensionContributionFactory extends SiriusTabbarExtensionContributionFactory {

    @Override
    public void createContributionItems(IServiceLocator serviceLocator, IContributionRoot additions) {

        super.createContributionItems(serviceLocator, additions);
        CopyLayoutAction copyLayoutAction = new CopyLayoutAction(getPage(), getPart());
        additions.addContributionItem(new ActionContributionItem(copyLayoutAction), new DDiagramElementTabbarExpression());

    }

}
