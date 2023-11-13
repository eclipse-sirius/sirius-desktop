/*******************************************************************************
 * Copyright (c) 2023 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.actions.layout;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.util.ObjectAdapter;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.LayoutType;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.AbstractDiagramAction;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.provider.LayoutChildrenProvider;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Action to layout the children of a diagram selection.
 * 
 * @author Séraphin Costa <seraphin.costa@obeosoft.com>
 *
 */
public class LayoutChildrenAction extends AbstractDiagramAction {

    /**
     * Constructs the diagram action to layout children.
     * 
     * @param workbenchPage
     *            The workbench page associated with this action
     */
    protected LayoutChildrenAction(IWorkbenchPage workbenchPage) {
        super(workbenchPage);
    }

    /**
     * Constructs the diagram action to layout children.
     * 
     * This constructor is provided just in case a derived class needs to
     * support both the construction of a diagram action with a workbench part.
     * Typically this is only when the diagram declares its own action in
     * additional to the one registered with the action service.
     * 
     * @param workbenchPart
     *            The workbench part associated with this action
     */
    protected LayoutChildrenAction(IWorkbenchPart workbenchPart) {
        super(workbenchPart);
    }

    @Override
    protected Request createTargetRequest() {
        return null;
    }

    @Override
    protected boolean isSelectionListener() {
        return true;
    }

    /**
     * This function is overloaded because the parent function uses getCommand, which causes a bug.
     */
    @Override
    protected boolean calculateEnabled() {
        List<IGraphicalEditPart> selectedEditParts = getSelectedEditPart().stream() //
                .filter(IGraphicalEditPart.class::isInstance) //
                .map(IGraphicalEditPart.class::cast) //
                .toList();
        LayoutChildrenProvider provider = new LayoutChildrenProvider();
        return provider.isAvailableFor(selectedEditParts) && super.canEditInstance();
    }

    private List<EditPart> getSelectedEditPart() {
        List<?> selection = getSelectedObjects();

        return selection.stream() //
                .filter(EditPart.class::isInstance) //
                .map(EditPart.class::cast).toList();
    }

    @Override
    protected Command getCommand() {
        List<IGraphicalEditPart> selectedEditParts = getSelectedEditPart().stream() //
                .filter(IGraphicalEditPart.class::isInstance) //
                .map(IGraphicalEditPart.class::cast) //
                .toList();

        LayoutChildrenProvider provider = new LayoutChildrenProvider();
        return provider.layoutEditParts(selectedEditParts, new ObjectAdapter(LayoutType.DEFAULT));
    }

    @Override
    protected String getCommandLabel() {
        return Messages.LayoutChildrenAction_commandLabel;
    }

    /**
     * Creates the Layout Children action for menu (not the same label as for the tool bar).
     * 
     * @param workbenchPage
     *            The workbench page associated with this action
     * @return A new Layout Children action
     */
    public static LayoutChildrenAction createMenuAction(final IWorkbenchPage workbenchPage) {
        final LayoutChildrenAction action = new LayoutChildrenAction(workbenchPage);
        action.setId(ActionIds.LAYOUT_CHILDREN);
        action.setText(Messages.LayoutChildrenAction_menuText);
        action.setToolTipText(Messages.LayoutChildrenAction_tooltip);
        action.setImageDescriptor(DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.LAYOUT_BORDERED_NODES_ICON));
        return action;
    }

    /**
     * Creates the Layout Children action for tool bar (not the same label as for the menu).
     * 
     * @param workbenchPage
     *            The workbench page associated with this action
     * @return A new Layout Children action
     */
    public static LayoutChildrenAction createToolbarAction(final IWorkbenchPage workbenchPage) {
        final LayoutChildrenAction action = new LayoutChildrenAction(workbenchPage);
        action.setId(ActionIds.LAYOUT_CHILDREN_TOOLBAR);
        action.setText(Messages.LayoutChildrenAction_toolbarText);
        action.setToolTipText(Messages.LayoutChildrenAction_tooltip);
        action.setImageDescriptor(DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.LAYOUT_BORDERED_NODES_ICON));
        return action;
    }
}
