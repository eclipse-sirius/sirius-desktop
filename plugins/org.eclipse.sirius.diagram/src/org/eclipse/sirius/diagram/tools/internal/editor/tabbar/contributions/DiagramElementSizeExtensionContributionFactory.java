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

import org.eclipse.gmf.runtime.diagram.ui.actions.internal.AutoSizeAction;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.SizeBothAction;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.sirius.diagram.tools.internal.editor.tabbar.contributions.expressions.DDiagramElementTabbarExpression;
import org.eclipse.ui.menus.IContributionRoot;
import org.eclipse.ui.services.IServiceLocator;

/**
 * 
 * ExtensionContributionFactory responsible for size tabbar item creation.
 * 
 * @author fbarbin
 */
public class DiagramElementSizeExtensionContributionFactory extends SiriusTabbarExtensionContributionFactory {

    @Override
    public void createContributionItems(IServiceLocator serviceLocator, IContributionRoot additions) {

        super.createContributionItems(serviceLocator, additions);
        SizeBothAction sizeBothAction = new SizeBothAction(getPage());
        sizeBothAction.init();
        AutoSizeAction autoSizeAction = new AutoSizeAction(getPage());
        additions.addContributionItem(new ActionContributionItem(sizeBothAction), new DDiagramElementTabbarExpression());
        additions.addContributionItem(new ActionContributionItem(autoSizeAction), new DDiagramElementTabbarExpression());

    }

}
