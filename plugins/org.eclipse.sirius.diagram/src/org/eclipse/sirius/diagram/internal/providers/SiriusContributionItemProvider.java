/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.internal.providers;

import org.eclipse.gmf.runtime.common.ui.services.action.contributionitem.AbstractContributionItemProvider;
import org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.printing.actions.PrintPreviewAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IWorkbenchPage;

import org.eclipse.sirius.diagram.tools.api.ui.actions.ActionIds;
import org.eclipse.sirius.diagram.tools.internal.actions.SaveAsImageFileAction;
import org.eclipse.sirius.diagram.tools.internal.actions.SelectHiddenElementsAction;
import org.eclipse.sirius.diagram.tools.internal.actions.TabbarRouterAction;
import org.eclipse.sirius.diagram.tools.internal.actions.layout.ArrangeBorderedNodesAction;
import org.eclipse.sirius.diagram.tools.internal.actions.layout.CopyLayoutAction;
import org.eclipse.sirius.diagram.tools.internal.actions.layout.PasteLayoutAction;
import org.eclipse.sirius.diagram.tools.internal.actions.pinning.PinElementsEclipseAction;
import org.eclipse.sirius.diagram.tools.internal.actions.pinning.UnpinElementsEclipseAction;
import org.eclipse.sirius.diagram.tools.internal.print.SiriusDiagramPrintPreviewAction;
import org.eclipse.sirius.diagram.tools.internal.print.SiriusEnhancedPrintActionHelper;

/**
 * @was-generated
 */
public class SiriusContributionItemProvider extends AbstractContributionItemProvider {

    /**
     * @was-generated NOT
     */
    protected IAction createAction(final String actionId, final IWorkbenchPartDescriptor partDescriptor) {
        IAction result;
        final IWorkbenchPage workbenchPage = partDescriptor.getPartPage();
        // if (actionId.equals(ActionIds.ACTION_DELETE_FROM_DIAGRAM)) {
        // result = new DeleteFromDiagramAction(partDescriptor.getPartPage());
        // } else
        if (actionId.equals(PrintPreviewAction.ID)) {
            result = new SiriusDiagramPrintPreviewAction(new SiriusEnhancedPrintActionHelper());
        } else if (ActionIds.COPY_LAYOUT.equals(actionId)) {
            result = new CopyLayoutAction(workbenchPage);
        } else if (ActionIds.PASTE_LAYOUT.equals(actionId)) {
            result = new PasteLayoutAction(workbenchPage);
        } else if (ActionIds.PIN_ELEMENTS.equals(actionId)) {
            result = new PinElementsEclipseAction();
        } else if (ActionIds.UNPIN_ELEMENTS.equals(actionId)) {
            result = new UnpinElementsEclipseAction();
        } else if (ActionIds.ARRANGE_BORDERED_NODES.equals(actionId)) {
            result = ArrangeBorderedNodesAction.createArrangeBorderedNodesAction(workbenchPage);
        } else if (ActionIds.ARRANGE_BORDERED_NODES_TOOLBAR.equals(actionId)) {
            result = ArrangeBorderedNodesAction.createToolBarArrangeBorderedNodesAction(workbenchPage);
        } else if (ActionIds.COPY_TO_IMAGE.equals(actionId)) {
            result = new SaveAsImageFileAction();
        } else if (ActionIds.SELECT_HIDDEN_ELEMENTS.equals(actionId)) {
            result = new SelectHiddenElementsAction(workbenchPage);
        } else if (ActionIds.ROUTING_STYLE.equals(actionId)) {
            result = TabbarRouterAction.createTreeRouterAction(workbenchPage);
        } else {
            result = super.createAction(actionId, partDescriptor);
        }
        return result;
    }
}
