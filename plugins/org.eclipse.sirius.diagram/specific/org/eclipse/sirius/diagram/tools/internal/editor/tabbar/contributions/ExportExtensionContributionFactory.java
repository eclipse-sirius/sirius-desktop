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

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.ui.menus.IContributionRoot;
import org.eclipse.ui.services.IServiceLocator;

import org.eclipse.sirius.DDiagram;
import org.eclipse.sirius.diagram.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.tools.internal.actions.SaveAsImageFileAction;
import org.eclipse.sirius.diagram.tools.internal.editor.tabbar.LayoutingModeSwitchingAction;
import org.eclipse.sirius.diagram.tools.internal.editor.tabbar.contributions.expressions.DDiagramTabbarExpression;

/**
 * 
 * ExtensionContributionFactory responsible for "Save As Image" tabbar item
 * creation.
 * 
 * @author fbarbin
 */
public class ExportExtensionContributionFactory extends SiriusTabbarExtensionContributionFactory {

    @Override
    public void createContributionItems(IServiceLocator serviceLocator, IContributionRoot additions) {

        super.createContributionItems(serviceLocator, additions);
        final DDiagramEditor editor = (DDiagramEditor) getPart();
        DDiagram editorDiagram = (DDiagram) editor.getRepresentation();
        SaveAsImageFileAction saveAsImageFileAction = new SaveAsImageFileAction();

        additions.addContributionItem(new ActionContributionItem(saveAsImageFileAction), new DDiagramTabbarExpression());
        if (LayoutingModeSwitchingAction.diagramAllowsLayoutingMode(editorDiagram)) {
            LayoutingModeSwitchingAction layoutingModeSwitchingAction = new LayoutingModeSwitchingAction(getPage(), editorDiagram);
            additions.addContributionItem(new TabbarActionContributionItem(layoutingModeSwitchingAction), new DDiagramTabbarExpression());
        }
    }
}
