/*******************************************************************************
 * Copyright (c) 2010, 2023 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.actions.layout;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.common.core.util.ObjectAdapter;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.LayoutType;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.AbstractDiagramAction;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.provider.BorderItemAwareLayoutProvider;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Action that arranges all border nodes of a diagram.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class ArrangeBorderNodesAction extends AbstractDiagramAction {

    private String textSelection = new String();

    private String textDiagram = new String();

    /**
     * Constructs a new diagram action.
     * 
     * @param workbenchPage
     *            The workbench page associated with this action
     */
    protected ArrangeBorderNodesAction(final IWorkbenchPage workbenchPage) {
        super(workbenchPage);
    }

    /**
     * This constructor is provided just in case a derived class needs to
     * support both the construction of a diagram action with a workbench part.
     * Typically this is only when the diagram declares its own action in
     * additional to the one registered with the action service.
     * 
     * @param workbenchpart
     *            The workbench part associated with this action
     */
    protected ArrangeBorderNodesAction(final IWorkbenchPart workbenchpart) {
        super(workbenchpart);
    }

    /**
     * Initialize the action.
     * 
     * @param id
     *            The id of the action
     * @param textSelection
     *            The label of the action when elements are selected
     * @param textDiagram
     *            The label of the action when the diagram is selected
     */
    protected void initAction(final String id, final String newTextSelection, final String newTextDiagram) {
        this.textSelection = newTextSelection;
        this.textDiagram = newTextDiagram;
        setId(id);
        setText(textSelection);
        setToolTipText(Messages.ArrangeBorderNodesAction_toolTipText);
        setImageDescriptor(DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.ARRANGE_BORDERED_NODES_ICON));
    }

    @Override
    protected Request createTargetRequest() {
        return null;
    }

    @Override
    protected boolean isSelectionListener() {
        return true;
    }

    @Override
    protected boolean calculateEnabled() {
        // Do not call super.calculateEnabled(), it would break client that use
        // "Arrange" actions ("Arrange All" and "Arrange Selection").
        // The following tests will failed otherwise:
        // - DiagramMigrationTestCampaign09
        // - SequenceArrangeLinkedBorderedNodesTest
        // - BendpointsStabilityOnMovesTest
        // Call super.canEditInstance() instead to check permission authorities
        return getSelectedObjects().size() > 0 && super.canEditInstance();
    }

    @Override
    protected void internalRefresh() {
        super.internalRefresh();
        if (isDiagramSelection(getSelectedEditPart())) {
            setText(textDiagram);
            setToolTipText(Messages.ArrangeBorderNodesAction_toolTipDiagramText);
        } else {
            setText(textSelection);
            setToolTipText(Messages.ArrangeBorderNodesAction_toolTipText);
        }
    }

    private List<EditPart> getSelectedEditPart() {
        List<?> selection = getSelectedObjects();

        return selection.stream() //
                .filter(EditPart.class::isInstance) //
                .map(EditPart.class::cast).toList();
    }

    private boolean isDiagramSelection(List<EditPart> selection) {
        return selection.stream().allMatch(DDiagramEditPart.class::isInstance);
    }

    @Override
    protected Command getCommand() {
        List<EditPart> selectedEditPart = getSelectedEditPart();

        if (selectedEditPart.isEmpty()) {
            return UnexecutableCommand.INSTANCE;
        } else {
            // recursive with diagram selection (=no selection) only
            boolean recursive = isDiagramSelection(selectedEditPart);

            final BorderItemAwareLayoutProvider layoutProvider = new BorderItemAwareLayoutProvider(null, recursive);
            return layoutProvider.layoutEditParts(selectedEditPart, new ObjectAdapter(LayoutType.DEFAULT), false);
        }
    }

    @Override
    protected String getCommandLabel() {
        return Messages.ArrangeBorderNodesAction_commandLabel;
    }

    /**
     * Creates the Arrange Border Nodes action.
     * 
     * @param workbenchPage
     *            The workbench page associated with this action
     * @return A new arrange border nodes action
     */
    public static ArrangeBorderNodesAction createArrangeBorderNodesAction(final IWorkbenchPage workbenchPage) {
        final ArrangeBorderNodesAction action = new ArrangeBorderNodesAction(workbenchPage);
        action.initAction(ActionIds.ARRANGE_BORDER_NODES, Messages.ArrangeBorderNodesAction_actionText, Messages.ArrangeBorderNodesAction_actionDiagramText);
        return action;
    }

    /**
     * Creates the Arrange Border Nodes action for the toolbar menu.
     * 
     * @param workbenchPage
     *            The workbench page associated with this action
     * @return A new arrange border nodes action
     */
    public static ArrangeBorderNodesAction createToolBarArrangeBorderNodesAction(final IWorkbenchPage workbenchPage) {
        final ArrangeBorderNodesAction action = new ArrangeBorderNodesAction(workbenchPage);
        action.initAction(ActionIds.ARRANGE_BORDER_NODES_TOOLBAR, Messages.ArrangeBorderNodesAction_toolbarActionText, Messages.ArrangeBorderNodesAction_toolbarActionDiagramText);
        return action;
    }

}
