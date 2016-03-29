/*******************************************************************************
 * Copyright (c) 2012, 2016 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.contributions;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.visibility.HideDDiagramElementLabelAction;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions.HideDDiagramElementLabelActionContributionItem;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.contributions.expressions.DDiagramElementTabbarExpression;
import org.eclipse.ui.menus.IContributionRoot;
import org.eclipse.ui.services.IServiceLocator;

/**
 * ExtensionContributionFactory responsible for hide and Delete actions tabbar
 * item creation.
 * 
 * @author fbarbin
 */
public class DiagramElementHideDeleteExtensionContributionFactory extends SiriusTabbarExtensionContributionFactory {

    @Override
    public void createContributionItems(IServiceLocator serviceLocator, IContributionRoot additions) {
        createHideDDiagramElementLabelMenu(additions);
    }

    /**
     * Create the menu to hide the label of a DiagramElement and enabled it (or
     * not) according to the selection.
     */
    private void createHideDDiagramElementLabelMenu(IContributionRoot additions) {
        // boolean isEnabled =
        // HideDDiagramElementLabelAction.isEnabled(selectedElements);
        // if (isEnabled) {

        // newAction.setEnabled(isEnabled && canEditInstance);
        HideDDiagramElementLabelActionContributionItem contributionItem = new HideDDiagramElementLabelActionContributionItem(getPart());
        // contributionItem.setCanEdit(canEdit());
        additions.addContributionItem(contributionItem, new DDiagramElementTabbarExpression() {
            @Override
            protected boolean isVisible(IStructuredSelection selection) {
                if (super.isVisible(selection)) {
                    boolean isEnabled = HideDDiagramElementLabelAction.isEnabled(selection.toList());
                    return isEnabled && canEdit();
                }
                return false;
            }
        });
        // }
    }

}
