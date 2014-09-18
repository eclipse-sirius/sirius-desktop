/*******************************************************************************
 * Copyright (c) 2010, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.actions.layout;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.util.ObjectAdapter;
import org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.LayoutType;
import org.eclipse.sirius.diagram.ui.part.SiriusDiagramEditor;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.provider.BorderItemAwareLayoutProvider;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Action that arranges all bordered nodes of a diagram.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class ArrangeBorderedNodesAction extends DiagramAction {

    /**
     * Constructs a new diagram action.
     * 
     * @param workbenchPage
     *            The workbench page associated with this action
     */
    protected ArrangeBorderedNodesAction(final IWorkbenchPage workbenchPage) {
        super(workbenchPage);
    }

    /**
     * This constructor is provided just in case a derived class needs to
     * support both the construction of a diagram action with a workbenchpart.
     * Typically this is only when the diagram declares its own action in
     * additional to the one registered with the action serivce.
     * 
     * @param workbenchpart
     *            The workbench part associated with this action
     */
    protected ArrangeBorderedNodesAction(final IWorkbenchPart workbenchpart) {
        super(workbenchpart);
    }

    /**
     * Initialize the action.
     * 
     * @param id
     *            The id of the action
     * @param text
     *            The label of the action
     */
    protected void initAction(final String id, final String text) {
        setId(id);
        setText(text);
        setToolTipText("Arrange all the linked bordered nodes of the diagram.");

        setImageDescriptor(DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.ARRANGE_BORDERED_NODES_ICON));
        // setDisabledImageDescriptor(DiagramUIActionsPluginImages.DESC_ARRANGE_ALL_DISABLED);
        // setHoverImageDescriptor(DiagramUIActionsPluginImages.DESC_ARRANGE_ALL);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction#createTargetRequest()
     */
    @Override
    protected Request createTargetRequest() {
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction#isSelectionListener()
     */
    @Override
    protected boolean isSelectionListener() {
        return true;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction#doRun(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    protected void doRun(final IProgressMonitor progressMonitor) {
        super.doRun(progressMonitor);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction#calculateEnabled()
     */
    @Override
    protected boolean calculateEnabled() {
        // Do not call super.calculateEnabled(), it would break client that use
        // "Arrange" actions ("Arrange All" and "Arrange Selection").
        // The following tests will failed otherwise:
        // - DiagramMigrationTestCampaign09
        // - SequenceArrangeLinkedBorderedNodesTest
        // - BendpointsStabilityOnMovesTest
        return getSelectedObjects().size() > 0;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction#getCommand()
     */
    @Override
    protected Command getCommand() {
        Command command = null;
        final IEditorPart activeEditor = getWorkbenchPage().getActiveEditor();
        if (activeEditor instanceof SiriusDiagramEditor) {
            final DiagramEditPart diagramEditPart = ((SiriusDiagramEditor) activeEditor).getDiagramEditPart();
            final List<EditPart> selectedEditPart = new ArrayList<EditPart>(1);
            selectedEditPart.add(diagramEditPart);
            final BorderItemAwareLayoutProvider layoutProvider = new BorderItemAwareLayoutProvider(null);
            command = layoutProvider.layoutEditParts(selectedEditPart, new ObjectAdapter(LayoutType.DEFAULT), false);
        }
        return command;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction#getCommandLabel()
     */
    @Override
    protected String getCommandLabel() {
        return "Arrange Linked Bordered Nodes ";
    }

    /**
     * Creates the Arrange Bordered Nodes action.
     * 
     * @param workbenchPage
     *            The workbench page associated with this action
     * @return A new arrange bordered nodes action
     */
    public static ArrangeBorderedNodesAction createArrangeBorderedNodesAction(final IWorkbenchPage workbenchPage) {
        final ArrangeBorderedNodesAction action = new ArrangeBorderedNodesAction(workbenchPage);
        action.initAction(ActionIds.ARRANGE_BORDERED_NODES, "Linked Bordered Nodes");
        return action;
    }

    /**
     * Creates the Arrange Bordered Nodes action for the toolbar menu.
     * 
     * @param workbenchPage
     *            The workbench page associated with this action
     * @return A new arrange bordered nodes action
     */
    public static ArrangeBorderedNodesAction createToolBarArrangeBorderedNodesAction(final IWorkbenchPage workbenchPage) {
        final ArrangeBorderedNodesAction action = new ArrangeBorderedNodesAction(workbenchPage);
        action.initAction(ActionIds.ARRANGE_BORDERED_NODES_TOOLBAR, "Arrange Linked Bordered Nodes");
        return action;
    }
}
