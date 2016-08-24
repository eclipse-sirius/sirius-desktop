/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.actions.straighten;

import java.util.Collections;
import java.util.List;

import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
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

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

/**
 * A {@link DiagramAction} to straighten edges.<BR>
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
@SuppressWarnings("restriction")
public class StraightenToAction extends DiagramAction {

    /** Constant indicating a straighten to top action. */
    public static final int TO_TOP = 0;

    /** Constant indicating a straighten to bottom action. */
    public static final int TO_BOTTOM = 1;

    /** Constant indicating a straighten to left action. */
    public static final int TO_LEFT = 2;

    /** Constant indicating a straighten to right action. */
    public static final int TO_RIGHT = 3;

    /**
     * The straighten type must by one of:
     * <UL>
     * <LI>{@link StraightenToAction#TO_TOP}</LI>
     * <LI>{@link StraightenToAction#TO_BOTTOM}</LI>
     * <LI>{@link StraightenToAction#TO_LEFT}</LI>
     * <LI>{@link StraightenToAction#TO_RIGHT}</LI>
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
     * Get the label of the action according to <code>distributionType</code>
     * and <code>isToolbarItem</code>.
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
        default:
            break;
        }
        return tooltip;
    }

    /**
     * Creates the Straighten to top action to align the edge horizontally to
     * the highest source or target.
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
     * Creates the Straighten to bottom action to align the edge horizontally to
     * the lowest source or target.
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
     * Creates the Straighten to left action to align the edge vertically to the
     * left most source or target.
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
     * Creates the Straighten to right action to align the edge vertically to
     * the right most source or target.
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
    }

    @Override
    protected Command getCommand() {
        CompoundCommand straightenEdgesCmd = new CompoundCommand(Messages.StraightenToAction_commandLabel);
        List<?> operationSet = getOperationSet();
        if (!operationSet.isEmpty()) {
            for (Object object : operationSet) {
                if (object instanceof ConnectionEditPart) {
                    straightenEdgesCmd.add(((ConnectionEditPart) object).getCommand(getTargetRequest()));
                }
            }
        }
        return straightenEdgesCmd;
    }

    /**
     * Return only a list of selected AbstractDiagramEdgeEditPart that
     * understands the request. If there is at least one other kind of edit
     * part, an empty list is returned.
     * 
     * @return A list of {@link AbstractDiagramEdgeEditPart} selected.
     */
    @Override
    protected List<?> createOperationSet() {
        List<?> selection = getSelectedObjects();
        if (!Iterables.all(selection, Predicates.instanceOf(AbstractDiagramEdgeEditPart.class))) {
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
}
