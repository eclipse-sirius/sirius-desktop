/*******************************************************************************
 * Copyright (c) 2015, 2024 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.tabbar;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDDiagramEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IAbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.editor.tabbar.AbstractTabbarContributor;

/**
 * Example of Tabbar Contributor used to test the extension point.
 * 
 * @author fbarbin
 *
 */
public class TabbarContributorSample extends AbstractTabbarContributor {

    private ArrayList<IContributionItem> diagramContributionItems;

    private ArrayList<IContributionItem> diagramElementContributionItems;

    @Override
    public List<IContributionItem> getContributionItems(ISelection selection, IDiagramWorkbenchPart part, ToolBarManager manager) {
        List<IContributionItem> contributionItems = new ArrayList<IContributionItem>();
        if (selection instanceof IStructuredSelection) {
            Object firstElement = ((IStructuredSelection) selection).getFirstElement();
            if (firstElement instanceof AbstractDDiagramEditPart) {
                contributionItems = getDiagramContributionItem(part, manager);
            } else if (firstElement instanceof IAbstractDiagramNodeEditPart) {
                contributionItems = getDiagramElementContributionItem(part, manager);
            }
        }
        return contributionItems;
    }

    @Override
    public boolean accept(ISelection selection) {
        boolean accept = false;
        if (selection == null) {
            accept = true;
        } else if (selection instanceof IStructuredSelection) {
            Object firstElement = ((IStructuredSelection) selection).getFirstElement();
            if (firstElement instanceof AbstractDDiagramEditPart || firstElement instanceof IAbstractDiagramNodeEditPart) {
                accept = true;
            }
        }
        return accept;
    }

    @Override
    public List<IContributionItem> getContributionItems(IDiagramWorkbenchPart part, ToolBarManager manager) {
        return getDiagramContributionItem(part, manager);
    }

    private List<IContributionItem> getDiagramContributionItem(IDiagramWorkbenchPart part, ToolBarManager manager) {
        if (diagramContributionItems == null) {
            diagramContributionItems = new ArrayList<IContributionItem>();
            diagramContributionItems.add(createArrangeMenuManager(part));
            diagramContributionItems.add(createSelectMenuManager());
            diagramContributionItems.add(createLayerContribution(part, manager));
            diagramContributionItems.add(createZoomInContribution(part));
            diagramContributionItems.add(createZoomOutContribution(part));
            diagramContributionItems.add(createZoomContribution(part));
            diagramContributionItems.add(createSelectPinnedElementsContribution(part));
            diagramContributionItems.add(createSelectHiddenElementsContribution(part));
            diagramContributionItems.add(createFilterContribution(part, manager));
            diagramContributionItems.add(createPasteFormatContribution(part));
            diagramContributionItems.add(createRefreshContribution());
            diagramContributionItems.add(createSaveAsImageContributionItem());
            diagramContributionItems.add(createModeMenuManagerContributionItem(part));
            diagramContributionItems.add(createCopyFormatContribution(part));
        }
        return diagramContributionItems;
    }

    private List<IContributionItem> getDiagramElementContributionItem(IDiagramWorkbenchPart part, ToolBarManager manager) {
        if (diagramElementContributionItems == null) {
            diagramElementContributionItems = new ArrayList<IContributionItem>();
            diagramElementContributionItems.add(createArrangeMenuManager(part));
            diagramElementContributionItems.add(createAlignMenuManager());
            ActionContributionItem hideLabelcontributionItem = createHideElementLabelContribution(part);
            diagramElementContributionItems.add(hideLabelcontributionItem);
            diagramElementContributionItems.add(createHideElementContribution(part));
            diagramElementContributionItems.add(createShowElementContribution(part));
            diagramElementContributionItems.add(createShowElementLabelContribution(part, hideLabelcontributionItem));
            diagramElementContributionItems.add(createDeleteFromDiagramContribution(part));
            diagramElementContributionItems.add(createDeleteFromModelContribution(part));
            diagramElementContributionItems.add(createPinElementContribution(part));
            diagramElementContributionItems.add(createFontColorContribution(part));
            diagramElementContributionItems.add(createPasteFormatContribution(part));
            diagramElementContributionItems.add(createBoldFontStyleContribution(part));
            diagramElementContributionItems.add(createItalicFontStyleContribution(part));
            diagramElementContributionItems.add(createFontDialogContribution(part));
            diagramElementContributionItems.add(createFillColorContribution(part));
            diagramElementContributionItems.add(createLineColorPropertyContribution(part));
            diagramElementContributionItems.add(createResetStylePropertyContribution(part));
            diagramElementContributionItems.add(createSetStyleToWorkspaceImageContribution(part));
            diagramElementContributionItems.add(createSaveAsImageContributionItem());
            diagramElementContributionItems.add(createDistributeContribution());
            diagramElementContributionItems.add(createModeMenuManagerContributionItem(part));
            diagramElementContributionItems.add(createRouterContribution());
            diagramElementContributionItems.add(createCopyFormatContribution(part));
            diagramElementContributionItems.add(createSizeBothContribution(part));
            diagramElementContributionItems.add(createAutoSizeContribution(part));
            diagramElementContributionItems.add(createStraightenContribution());
        }
        return diagramElementContributionItems;
    }

}
