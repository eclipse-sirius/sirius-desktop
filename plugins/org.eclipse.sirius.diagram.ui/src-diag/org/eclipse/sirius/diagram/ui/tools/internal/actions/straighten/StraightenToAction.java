/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.actions.straighten;

import java.util.Collections;
import java.util.List;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.common.core.util.StringStatics;
import org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.diagram.ui.tools.api.requests.StraightenToRequest;
import org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds;
import org.eclipse.ui.IWorkbenchPage;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * A {@link DiagramAction} to straighten edges.<BR>
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class StraightenToAction extends DiagramAction {

    /** Constant indicating a straighten to top action. */
    public static final int TO_TOP = 0;

    /** Constant indicating a straighten to bottom action. */
    public static final int TO_BOTTOM = 1;

    /** Constant indicating a straighten to left action. */
    public static final int TO_LEFT = 2;

    /** Constant indicating a straighten to right action. */
    public static final int TO_RIGHT = 3;

    /** Constant indicating a straighten with left side pinned action. */
    public static final int LEFT_SIDE_PINNED = 4;

    /** Constant indicating a straighten with right side pinned action. */
    public static final int RIGHT_SIDE_PINNED = 5;

    /** Constant indicating a straighten with top side pinned action. */
    public static final int TOP_SIDE_PINNED = 6;

    /** Constant indicating a straighten with bottom side pinned action. */
    public static final int BOTTOM_SIDE_PINNED = 7;

    /**
     * The straighten type must by one of:
     * <UL>
     * <LI>{@link StraightenToAction#TO_TOP}</LI>
     * <LI>{@link StraightenToAction#TO_BOTTOM}</LI>
     * <LI>{@link StraightenToAction#TO_LEFT}</LI>
     * <LI>{@link StraightenToAction#TO_RIGHT}</LI>
     * <LI>{@link StraightenToAction#TOP_SIDE_PINNED}</LI>
     * <LI>{@link StraightenToAction#BOTTOM_SIDE_PINNED}</LI>
     * <LI>{@link StraightenToAction#LEFT_SIDE_PINNED}</LI>
     * <LI>{@link StraightenToAction#RIGHT_SIDE_PINNED}</LI>
     * </UL>
     */
    private int straightenType;

    /**
     * Constructs a StraightenTpAction with the given part and straighten type.
     * 
     * The straighten type must by one of:
     * <UL>
     * <LI>{@link StraightenToAction#TO_TOP}</LI>
     * <LI>{@link StraightenToAction#TO_BOTTOM}</LI>
     * <LI>{@link StraightenToAction#TO_LEFT}</LI>
     * <LI>{@link StraightenToAction#TO_RIGHT}</LI>
     * <LI>{@link StraightenToAction#TOP_SIDE_PINNED}</LI>
     * <LI>{@link StraightenToAction#BOTTOM_SIDE_PINNED}</LI>
     * <LI>{@link StraightenToAction#LEFT_SIDE_PINNED}</LI>
     * <LI>{@link StraightenToAction#RIGHT_SIDE_PINNED}</LI>
     * </UL>
     * 
     * @param workbenchPage
     *            the workbench part used to obtain context
     * @param straightenType
     *            The straighten type
     */
    protected StraightenToAction(IWorkbenchPage workbenchPage, int straightenType) {
        super(workbenchPage);
        this.straightenType = straightenType;
        setText(getLabel(straightenType));
        setToolTipText(getTooltip(straightenType));
    }

    /**
     * Get the label of the action according to <code>distributionType</code> and <code>isToolbarItem</code>.
     * 
     * @param straightenType
     *            the kind of straighten.
     * @return The label
     */
    public static String getLabel(int straightenType) {
        String label = StringStatics.BLANK;
        switch (straightenType) {
        case StraightenToAction.TO_TOP:
            label = Messages.StraightenToAction_toTopLabel;
            break;
        case StraightenToAction.TO_BOTTOM:
            label = Messages.StraightenToAction_toBottomLabel;
            break;
        case StraightenToAction.TO_LEFT:
            label = Messages.StraightenToAction_toLeftLabel;
            break;
        case StraightenToAction.TO_RIGHT:
            label = Messages.StraightenToAction_toRightLabel;
            break;
        case StraightenToAction.LEFT_SIDE_PINNED:
            label = Messages.StraightenToAction_LeftSidePinnedLabel;
            break;
        case StraightenToAction.RIGHT_SIDE_PINNED:
            label = Messages.StraightenToAction_RightSidePinnedLabel;
            break;
        case StraightenToAction.TOP_SIDE_PINNED:
            label = Messages.StraightenToAction_TopSidePinnedLabel;
            break;
        case StraightenToAction.BOTTOM_SIDE_PINNED:
            label = Messages.StraightenToAction_BottomSidePinnedLabel;
            break;
        default:
            break;
        }
        return label;
    }

    /**
     * Get the tooltip of the action according to <code>distributionType</code>.
     * 
     * @param straightenType
     *            the kind of straighten.
     * @return The tooltip
     */
    public static String getTooltip(int straightenType) {
        String tooltip = StringStatics.BLANK;
        switch (straightenType) {
        case StraightenToAction.TO_TOP:
            tooltip = Messages.StraightenToAction_toTopTooltip;
            break;
        case StraightenToAction.TO_BOTTOM:
            tooltip = Messages.StraightenToAction_toBottomTooltip;
            break;
        case StraightenToAction.TO_LEFT:
            tooltip = Messages.StraightenToAction_toLeftTooltip;
            break;
        case StraightenToAction.TO_RIGHT:
            tooltip = Messages.StraightenToAction_toRightTooltip;
            break;
        case StraightenToAction.LEFT_SIDE_PINNED:
            tooltip = Messages.StraightenToAction_LeftSidePinnedTooltip;
            break;
        case StraightenToAction.RIGHT_SIDE_PINNED:
            tooltip = Messages.StraightenToAction_RightSidePinnedTooltip;
            break;
        case StraightenToAction.TOP_SIDE_PINNED:
            tooltip = Messages.StraightenToAction_TopSidePinnedTooltip;
            break;
        case StraightenToAction.BOTTOM_SIDE_PINNED:
            tooltip = Messages.StraightenToAction_BottomSidePinnedTooltip;
            break;
        default:
            break;
        }
        return tooltip;
    }

    /**
     * Creates the Straighten to top action to align the edge horizontally to the highest source or target.
     * 
     * @param workbenchPage
     *            the workbench part used to obtain context
     * @return the corresponding action
     */
    public static StraightenToAction createStraightenToTopAction(IWorkbenchPage workbenchPage) {
        StraightenToAction action = new StraightenToAction(workbenchPage, StraightenToAction.TO_TOP);
        action.setId(ActionIds.STRAIGHTEN_TO_TOP);
        ImageDescriptor bundledImageDescriptor = DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.STRAIGHTEN_TO_TOP);
        action.setImageDescriptor(bundledImageDescriptor);
        action.setHoverImageDescriptor(bundledImageDescriptor);
        return action;
    }

    /**
     * Creates the Straighten to bottom action to align the edge horizontally to the lowest source or target.
     * 
     * @param workbenchPage
     *            the workbench part used to obtain context
     * @return the corresponding action
     */
    public static StraightenToAction createStraightenToBottomAction(IWorkbenchPage workbenchPage) {
        StraightenToAction action = new StraightenToAction(workbenchPage, StraightenToAction.TO_BOTTOM);
        action.setId(ActionIds.STRAIGHTEN_TO_BOTTOM);
        ImageDescriptor bundledImageDescriptor = DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.STRAIGHTEN_TO_BOTTOM);
        action.setImageDescriptor(bundledImageDescriptor);
        action.setHoverImageDescriptor(bundledImageDescriptor);
        return action;
    }

    /**
     * Creates the Straighten to left action to align the edge vertically to the left most source or target.
     * 
     * @param workbenchPage
     *            the workbench part used to obtain context
     * @return the corresponding action
     */
    public static StraightenToAction createStraightenToLeftAction(IWorkbenchPage workbenchPage) {
        StraightenToAction action = new StraightenToAction(workbenchPage, StraightenToAction.TO_LEFT);
        action.setId(ActionIds.STRAIGHTEN_TO_LEFT);
        ImageDescriptor bundledImageDescriptor = DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.STRAIGHTEN_TO_LEFT);
        action.setImageDescriptor(bundledImageDescriptor);
        action.setHoverImageDescriptor(bundledImageDescriptor);
        return action;
    }

    /**
     * Creates the Straighten to right action to align the edge vertically to the right most source or target.
     * 
     * @param workbenchPage
     *            the workbench part used to obtain context
     * @return the corresponding action
     */
    public static StraightenToAction createStraightenToRightAction(IWorkbenchPage workbenchPage) {
        StraightenToAction action = new StraightenToAction(workbenchPage, StraightenToAction.TO_RIGHT);
        action.setId(ActionIds.STRAIGHTEN_TO_RIGHT);
        ImageDescriptor bundledImageDescriptor = DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.STRAIGHTEN_TO_RIGHT);
        action.setImageDescriptor(bundledImageDescriptor);
        action.setHoverImageDescriptor(bundledImageDescriptor);
        return action;
    }

    /**
     * Creates the Straighten with left side pinned action to align the edge(s) horizontally by moving all right side
     * extremities of edge(s).
     * 
     * @param workbenchPage
     *            the workbench part used to obtain context
     * @return the corresponding action
     */
    public static StraightenToAction createStraightenLeftSidePinnedAction(IWorkbenchPage workbenchPage) {
        StraightenToAction action = new StraightenToAction(workbenchPage, StraightenToAction.LEFT_SIDE_PINNED);
        action.setId(ActionIds.STRAIGHTEN_LEFT_SIDE_PINNED);
        ImageDescriptor bundledImageDescriptor = DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.STRAIGHTEN_WITH_LEFT_PINNED);
        action.setImageDescriptor(bundledImageDescriptor);
        action.setHoverImageDescriptor(bundledImageDescriptor);
        return action;
    }

    /**
     * Creates the Straighten with right side pinned action to align the edge(s) horizontally by moving all left side
     * extremities of edge(s).
     * 
     * @param workbenchPage
     *            the workbench part used to obtain context
     * @return the corresponding action
     */
    public static StraightenToAction createStraightenRightSidePinnedAction(IWorkbenchPage workbenchPage) {
        StraightenToAction action = new StraightenToAction(workbenchPage, StraightenToAction.RIGHT_SIDE_PINNED);
        action.setId(ActionIds.STRAIGHTEN_RIGHT_SIDE_PINNED);
        ImageDescriptor bundledImageDescriptor = DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.STRAIGHTEN_WITH_RIGHT_PINNED);
        action.setImageDescriptor(bundledImageDescriptor);
        action.setHoverImageDescriptor(bundledImageDescriptor);
        return action;
    }

    /**
     * Creates the Straighten with top side pinned action to align the edge(s) vertically by moving all bottom side
     * extremities of edge(s).
     * 
     * @param workbenchPage
     *            the workbench part used to obtain context
     * @return the corresponding action
     */
    public static StraightenToAction createStraightenTopSidePinnedAction(IWorkbenchPage workbenchPage) {
        StraightenToAction action = new StraightenToAction(workbenchPage, StraightenToAction.TOP_SIDE_PINNED);
        action.setId(ActionIds.STRAIGHTEN_TOP_SIDE_PINNED);
        ImageDescriptor bundledImageDescriptor = DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.STRAIGHTEN_WITH_TOP_PINNED);
        action.setImageDescriptor(bundledImageDescriptor);
        action.setHoverImageDescriptor(bundledImageDescriptor);
        return action;
    }

    /**
     * Creates the Straighten with bottom side pinned action to align the edge(s) vertically by moving all top side
     * extremities of edge(s).
     * 
     * @param workbenchPage
     *            the workbench part used to obtain context
     * @return the corresponding action
     */
    public static StraightenToAction createStraightenBottomSidePinnedAction(IWorkbenchPage workbenchPage) {
        StraightenToAction action = new StraightenToAction(workbenchPage, StraightenToAction.BOTTOM_SIDE_PINNED);
        action.setId(ActionIds.STRAIGHTEN_BOTTOM_SIDE_PINNED);
        ImageDescriptor bundledImageDescriptor = DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.STRAIGHTEN_WITH_BOTTOM_PINNED);
        action.setImageDescriptor(bundledImageDescriptor);
        action.setHoverImageDescriptor(bundledImageDescriptor);
        return action;
    }

    @Override
    protected Request createTargetRequest() {
        StraightenToRequest straightenRequest = new StraightenToRequest();
        straightenRequest.setStraightenType(straightenType);
        return straightenRequest;
    }

    @Override
    protected void updateTargetRequest() {
        StraightenToRequest straightenRequest = (StraightenToRequest) getTargetRequest();
        straightenRequest.setStraightenType(straightenType);
        straightenRequest.setSelectedEdgeEditParts(getSelectedEdgeEditParts());
    }

    @Override
    protected Command getCommand() {
        List<?> operationSet = getOperationSet();
        if (!operationSet.isEmpty()) {
            for (Object object : operationSet) {
                if (object instanceof AbstractDiagramEdgeEditPart) {
                    return ((AbstractDiagramEdgeEditPart) object).getCommand(getTargetRequest());
                }
            }
        }
        return UnexecutableCommand.INSTANCE;
    }

    /**
     * Return only a list of selected AbstractDiagramEdgeEditPart that understands the request. If there is at least one
     * other kind of edit part, an empty list is returned.
     * 
     * @return A list of {@link AbstractDiagramEdgeEditPart} selected.
     */
    @Override
    protected List<?> createOperationSet() {
        List<?> selection = getSelectedObjects();

        if (!selection.stream().anyMatch(AbstractDiagramEdgeEditPart.class::isInstance)) {
            selection = Collections.EMPTY_LIST;
        }
        return selection;
    }

    @Override
    protected boolean isSelectionListener() {
        return true;
    }

    @Override
    protected boolean isOperationHistoryListener() {
        return true;
    }

    private List<AbstractDiagramEdgeEditPart> getSelectedEdgeEditParts() {
        return Lists.newArrayList(Iterables.filter(getOperationSet(), AbstractDiagramEdgeEditPart.class));
    }
}
