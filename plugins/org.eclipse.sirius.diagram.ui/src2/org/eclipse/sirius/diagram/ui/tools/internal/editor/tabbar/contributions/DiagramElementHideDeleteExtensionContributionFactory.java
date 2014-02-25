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
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.contributions;

import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.ui.part.SiriusDiagramActionBarContributor;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.action.DeleteFromDiagramContributionItem;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.delete.DeleteFromDiagramAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.delete.DeleteFromModelWithHookAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.visibility.HideDDiagramElementAction;
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

        super.createContributionItems(serviceLocator, additions);

        HideDDiagramElementAction hideDDiagramElementAction = new HideDDiagramElementAction(SiriusDiagramActionBarContributor.HIDE_ELEMENT);
        hideDDiagramElementAction.setEnabled(canEdit());
        hideDDiagramElementAction.setActionPart(getPart());
        additions.addContributionItem(new ActionContributionItem(hideDDiagramElementAction), new DDiagramElementTabbarExpression());

        createHideDDiagramElementLabelMenu(additions);

        final DeleteFromDiagramContributionItem deleteFromDiagram = new DeleteFromDiagramContributionItem(new DeleteFromDiagramAction(DiagramUIMessages.DiagramEditor_Delete_from_Diagram,
                SiriusDiagramActionBarContributor.DELETE_FROM_DIAGRAM, ActionIds.ACTION_DELETE_FROM_DIAGRAM,
                DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.DELETE_FROM_DIAGRAM_ICON)), getPage());
        deleteFromDiagram.setItemPart(getPart());
        additions.addContributionItem(deleteFromDiagram, new DDiagramElementTabbarExpression());

        final DeleteFromModelWithHookAction deleteFromModelAction = new DeleteFromModelWithHookAction(getPage(), getPart());
        deleteFromModelAction.init();

        additions.addContributionItem(new ActionContributionItem(deleteFromModelAction), new DDiagramElementTabbarExpression());

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
