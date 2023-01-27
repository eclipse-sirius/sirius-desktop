/*******************************************************************************
 * Copyright (c) 2014, 2023 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.actions.distribute;

import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.tools.ToolUtilities;
import org.eclipse.gmf.runtime.common.core.util.StringStatics;
import org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.part.SiriusDiagramEditor;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.diagram.ui.tools.api.requests.DistributeRequest;
import org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds;
import org.eclipse.sirius.diagram.ui.tools.internal.util.EditPartQuery;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * A {@link DiagramAction} to distribute shapes or labels of edges.<BR>
 * Inspired by the class {@link org.eclipse.gmf.runtime.diagram.ui.actions.internal.ArrangeAction}.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
@SuppressWarnings("restriction")
public class DistributeAction extends DiagramAction {

    /** Constant indicating horizontal distribution with uniform gaps. */
    public static final int GAPS_HORIZONTALLY = 0;

    /** Constant indicating distribution of centers horizontally. */
    public static final int CENTERS_HORIZONTALLY = 1;

    /** Constant indicating vertical distribution with uniform gaps. */
    public static final int GAPS_VERTICALLY = 2;

    /** Constant indicating distribution of centers vertically. */
    public static final int CENTERS_VERTICALLY = 3;

    /**
     * The distribution type must by one of:
     * <UL>
     * <LI>DistributeAction.HORIZONTALLY_WITH_UNIFORM_GAPS</LI>
     * <LI>DistributeAction.CENTERS_HORIZONTALLY</LI>
     * <LI>DistributeAction.VERTICALLY_WITH_UNIFORM_GAPS</LI>
     * <LI>DistributeAction.CENTERS_VERTICALLY</LI>
     * </UL>
     */
    private int distributeType;

    /**
     * Constructs a DistributeAction with the given part and distribute type.
     * 
     * The distribute type must by one of:
     * <UL>
     * <LI>DistributeAction.HORIZONTALLY_WITH_UNIFORM_GAPS</LI>
     * <LI>DistributeAction.CENTERS_HORIZONTALLY</LI>
     * <LI>DistributeAction.VERTICALLY_WITH_UNIFORM_GAPS</LI>
     * <LI>DistributeAction.CENTERS_VERTICALLY</LI>
     * </UL>
     * 
     * @param workbenchPage
     *            the workbench part used to obtain context
     * @param distributeType
     *            The distribute type
     * @param isToolbarItem
     *            the indicator of whether or not this is a toolbar action as opposed to a context-menu action.
     */
    protected DistributeAction(IWorkbenchPage workbenchPage, int distributeType, boolean isToolbarItem) {
        super(workbenchPage);
        this.distributeType = distributeType;
        setText(getLabel(distributeType, isToolbarItem));
        setToolTipText(getTooltip(distributeType));
    }

    /**
     * Get the label of the action according to <code>distributionType</code> and <code>isToolbarItem</code>.
     * 
     * @param distributeType
     *            the kind of distribution.
     * @param isToolbarItem
     *            the indicator of whether or not this is a toolbar action as opposed to a context-menu action
     * @return The label
     */
    public static String getLabel(int distributeType, boolean isToolbarItem) {
        String label = StringStatics.BLANK;
        switch (distributeType) {
        case DistributeAction.GAPS_HORIZONTALLY:
            label = Messages.DistributeAction_gapsHorizontallyLabel;
            break;
        case DistributeAction.CENTERS_HORIZONTALLY:
            label = Messages.DistributeAction_centersHorizontallyLabel;
            break;
        case DistributeAction.GAPS_VERTICALLY:
            label = Messages.DistributeAction_gapsVerticallyLabel;
            break;
        case DistributeAction.CENTERS_VERTICALLY:
            label = Messages.DistributeAction_centersVerticallyLabel;
            break;
        default:
            break;
        }
        if (isToolbarItem && !StringStatics.BLANK.equals(label)) {
            // Use same label but with distribute prefix
            switch (distributeType) {
            case DistributeAction.GAPS_HORIZONTALLY:
                label = Messages.DistributeAction_distributeGapsHorizontallyLabel;
                break;
            case DistributeAction.CENTERS_HORIZONTALLY:
                label = Messages.DistributeAction_distributeCentersHorizontallyLabel;
                break;
            case DistributeAction.GAPS_VERTICALLY:
                label = Messages.DistributeAction_distributeGapsVerticallyLabel;
                break;
            case DistributeAction.CENTERS_VERTICALLY:
                label = Messages.DistributeAction_distributeCentersVerticallyLabel;
                break;
            default:
                break;
            }
        }
        return label;
    }

    /**
     * Get the tooltip of the action according to <code>distributionType</code>.
     * 
     * @param distributeType
     *            the kind of distribution.
     * @return The tooltip
     */
    public static String getTooltip(int distributeType) {
        String tooltip = StringStatics.BLANK;
        switch (distributeType) {
        case DistributeAction.GAPS_HORIZONTALLY:
            tooltip = Messages.DistributeAction_distributeGapsHorizontallyTooltip;
            break;
        case DistributeAction.CENTERS_HORIZONTALLY:
            tooltip = Messages.DistributeAction_distributeCentersHorizontallyTooltip;
            break;
        case DistributeAction.GAPS_VERTICALLY:
            tooltip = Messages.DistributeAction_distributeGapsVerticallyTooltip;
            break;
        case DistributeAction.CENTERS_VERTICALLY:
            tooltip = Messages.DistributeAction_distributeCentersVerticallyTooltip;
            break;
        default:
            break;
        }
        return tooltip;
    }

    /**
     * Creates the Distribute action to distribute shapes horizontally with uniform gaps.
     * 
     * @param workbenchPage
     *            the workbench part used to obtain context
     * @param isToolbarItem
     *            the indicator of whether or not this is a toolbar action as opposed to a context-menu action.
     * @return the corresponding action
     */
    public static DistributeAction createDistributeHorizontallyWithUniformGapsAction(IWorkbenchPage workbenchPage, boolean isToolbarItem) {
        DistributeAction action = new DistributeAction(workbenchPage, DistributeAction.GAPS_HORIZONTALLY, isToolbarItem);
        action.setId(ActionIds.DISTRIBUTE_GAPS_HORIZONTALLY);
        ImageDescriptor bundledImageDescriptor = DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.DISTRIBUTE_WITH_UNIFORM_GAPS_HORIZONTALLY);
        action.setImageDescriptor(bundledImageDescriptor);
        action.setHoverImageDescriptor(bundledImageDescriptor);
        return action;
    }

    /**
     * Creates the Distribute action to distribute evenly centers of shapes horizontally.
     * 
     * @param workbenchPage
     *            the workbench part used to obtain context
     * @param isToolbarItem
     *            the indicator of whether or not this is a toolbar action as opposed to a context-menu action.
     * @return the corresponding action
     */
    public static DistributeAction createDistributeCentersHorizontallyAction(IWorkbenchPage workbenchPage, boolean isToolbarItem) {
        DistributeAction action = new DistributeAction(workbenchPage, DistributeAction.CENTERS_HORIZONTALLY, isToolbarItem);
        action.setId(ActionIds.DISTRIBUTE_CENTERS_HORIZONTALLY);
        ImageDescriptor bundledImageDescriptor = DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.DISTRIBUTE_CENTERS_HORIZONTALLY);
        action.setImageDescriptor(bundledImageDescriptor);
        action.setHoverImageDescriptor(bundledImageDescriptor);
        return action;
    }

    /**
     * Creates the Distribute action to distribute shapes vertically with uniform gaps.
     * 
     * @param workbenchPage
     *            the workbench part used to obtain context
     * @param isToolbarItem
     *            the indicator of whether or not this is a toolbar action as opposed to a context-menu action.
     * @return the corresponding action
     */
    public static DistributeAction createDistributeVerticallyWithUniformGapsAction(IWorkbenchPage workbenchPage, boolean isToolbarItem) {
        DistributeAction action = new DistributeAction(workbenchPage, DistributeAction.GAPS_VERTICALLY, isToolbarItem);
        action.setId(ActionIds.DISTRIBUTE_GAPS_VERTICALLY);
        ImageDescriptor bundledImageDescriptor = DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.DISTRIBUTE_WITH_UNIFORM_GAPS_VERTICALLY);
        action.setImageDescriptor(bundledImageDescriptor);
        action.setHoverImageDescriptor(bundledImageDescriptor);
        return action;
    }

    /**
     * Creates the Distribute action to distribute evenly centers of shapes vertically.
     * 
     * @param workbenchPage
     *            the workbench part used to obtain context
     * @param isToolbarItem
     *            the indicator of whether or not this is a toolbar action as opposed to a context-menu action.
     * @return the corresponding action
     */
    public static DistributeAction createDistributeCentersVerticallyAction(IWorkbenchPage workbenchPage, boolean isToolbarItem) {
        DistributeAction action = new DistributeAction(workbenchPage, DistributeAction.CENTERS_VERTICALLY, isToolbarItem);
        action.setId(ActionIds.DISTRIBUTE_CENTERS_VERTICALLY);
        ImageDescriptor bundledImageDescriptor = DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.DISTRIBUTE_CENTERS_VERTICALLY);
        action.setImageDescriptor(bundledImageDescriptor);
        action.setHoverImageDescriptor(bundledImageDescriptor);
        return action;
    }

    @Override
    protected Request createTargetRequest() {
        DistributeRequest distributionRequest = new DistributeRequest();
        distributionRequest.setDistributeType(distributeType);
        return distributionRequest;
    }

    @Override
    protected void updateTargetRequest() {
        DistributeRequest distributionRequest = (DistributeRequest) getTargetRequest();
        distributionRequest.setDistributeType(distributeType);
        distributionRequest.setEditParts(getOperationSet());
    }

    @Override
    protected Command getCommand() {
        Command cmd = null;
        List<?> operationSet = getOperationSet();
        if (!operationSet.isEmpty()) {
            EditPart targetEP = getTargetEditPartForDistributeSelection(operationSet);
            if (targetEP != null) {
                cmd = targetEP.getCommand(getTargetRequest());
            }
        }
        return cmd;
    }

    @Override
    protected List<?> createOperationSet() {
        List<?> selection = getSelectedObjects();
        if (selection.isEmpty() || !(selection.get(0) instanceof IGraphicalEditPart)) {
            selection = Collections.EMPTY_LIST;
        } else {
            // Get the the top level selected edit parts
            selection = ToolUtilities.getSelectionWithoutDependants(selection);
            // Remove the connections
            selection = Lists.newArrayList(Iterables.filter(selection, Predicates.not(Predicates.instanceOf(ConnectionEditPart.class))));
            if (selection.size() < 3) {
                selection = Collections.EMPTY_LIST;
            } else {
                EditPart parent = ((EditPart) selection.get(0)).getParent();
                int sideOfFirstSelection = PositionConstants.NONE;
                boolean isEdgeLabel = false;
                if (selection.get(0) instanceof IBorderItemEditPart) {
                    // If the first selected element is a border node
                    sideOfFirstSelection = ((IBorderItemEditPart) selection.get(0)).getBorderItemLocator().getCurrentSideOfParent();
                    // Check that the side corresponds to the action axis
                    // (horizontal or vertical)
                    if (!isHorizontalAxisAuthorizedForBorderNode(sideOfFirstSelection) && !isVerticalAxisAuthorizedForBorderNode(sideOfFirstSelection)) {
                        selection = Collections.EMPTY_LIST;
                    }
                } else if (selection.get(0) instanceof AbstractDEdgeNameEditPart) {
                    isEdgeLabel = true;
                }

                for (int i = 1; i < selection.size(); i++) {
                    EditPart part = (EditPart) selection.get(i);
                    if (isEdgeLabel) {
                        if (!(part instanceof AbstractDEdgeNameEditPart)) {
                            // If the first item is an edge label, all the selected elements must be edge label.
                            selection = Collections.EMPTY_LIST;
                            break;
                        }
                    } else {
                        if (part instanceof AbstractDEdgeNameEditPart) {
                            // If the first item is not an edge label, no selected elements must be an edge label.
                            selection = Collections.EMPTY_LIST;
                            break;
                        } else if (part.getParent() != parent) {
                            // All the selected shapes must have the same parent.
                            selection = Collections.EMPTY_LIST;
                            break;
                        } else if (sideOfFirstSelection != PositionConstants.NONE && !isABorderNodeOnSameAxis(part, sideOfFirstSelection)) {
                            // All the selected border nodes must have the same axis.
                            selection = Collections.EMPTY_LIST;
                            break;
                        } else if (part instanceof IGraphicalEditPart) {
                            EditPartQuery containerLayoutQuery = new EditPartQuery((IGraphicalEditPart) part);
                            if (!containerLayoutQuery.isFreeFormContainerChildrenPresentation()) {
                                // List item and elements inside compartment can not be distribute
                                selection = Collections.EMPTY_LIST;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return selection;
    }

    private boolean isHorizontalAxisAuthorizedForBorderNode(int side) {
        return isOnHorizontalAxis(side) && (distributeType == DistributeAction.GAPS_HORIZONTALLY || distributeType == DistributeAction.CENTERS_HORIZONTALLY);
    }

    private boolean isVerticalAxisAuthorizedForBorderNode(int side) {
        return isOnVerticalAxis(side) && (distributeType == DistributeAction.GAPS_VERTICALLY || distributeType == DistributeAction.CENTERS_VERTICALLY);
    }

    private boolean isABorderNodeOnSameAxis(EditPart part, int expectedSide) {
        boolean result = false;
        if (part instanceof IBorderItemEditPart) {
            int currentSide = ((IBorderItemEditPart) part).getBorderItemLocator().getCurrentSideOfParent();
            if ((isOnHorizontalAxis(expectedSide) && isOnHorizontalAxis(currentSide)) || (isOnVerticalAxis(expectedSide) && isOnVerticalAxis(currentSide))) {
                result = true;
            }
        }
        return result;
    }

    private boolean isOnHorizontalAxis(int side) {
        return side == PositionConstants.NORTH || side == PositionConstants.SOUTH;
    }

    private boolean isOnVerticalAxis(int side) {
        return side == PositionConstants.EAST || side == PositionConstants.WEST;
    }

    @Override
    protected boolean isSelectionListener() {
        return true;
    }

    @Override
    protected boolean isOperationHistoryListener() {
        return true;
    }

    private EditPart getTargetEditPartForDistributeSelection(List<?> editparts) {
        EditPart result = null;
        // The Distribute request gets sent to the common parent for nodes and to the diagram for labels of edges.
        EditPart firstEditPart = (EditPart) editparts.get(0);
        if (firstEditPart instanceof AbstractDEdgeNameEditPart) {
            // As in ArrangeBorderNodesAction.getCommand(), avoid NPE in getDiagramEditPart when diagramGraphialViewer
            // is not ready.
            if (getDiagramGraphicalViewer() != null) {
                final IEditorPart activeEditor = getWorkbenchPage().getActiveEditor();
                if (activeEditor instanceof SiriusDiagramEditor) {
                    result = ((SiriusDiagramEditor) activeEditor).getDiagramEditPart();
                }
            }
        } else {
            result = firstEditPart.getParent();
            // Check that the parent is the same for all selected edit parts
            // (normally it was already done via createOperationSet()).
            for (int i = 1; i < editparts.size() && result != null; i++) {
                EditPart part = (EditPart) editparts.get(i);
                // if there is no common parent, then Distribute isn't
                // supported.
                if (part.getParent() != result) {
                    result = null;
                }
            }
        }
        return result;
    }

}
