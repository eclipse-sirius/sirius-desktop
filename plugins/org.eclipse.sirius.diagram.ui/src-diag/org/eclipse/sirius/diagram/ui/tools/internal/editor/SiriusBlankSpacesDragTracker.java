/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.editor;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.Request;
import org.eclipse.gef.SharedCursors;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.internal.ui.rulers.RulerEditPart;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gef.tools.SimpleDragTracker;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.command.GMFCommandWrapper;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants;
import org.eclipse.sirius.ext.draw2d.figure.InsertBlankSpaceGuide;
import org.eclipse.swt.graphics.Cursor;

/**
 * A specific {@link SimpleDragTracker} to insert or remove vertical blank space in diagram representation. This tracker
 * is used when Ctrl+shift key is pressed when user makes a selection or when doing a selection from the ruler.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
@SuppressWarnings("restriction")
public class SiriusBlankSpacesDragTracker extends SimpleDragTracker {
    /**
     * The part associated to this drag tracker.
     */
    protected AbstractGraphicalEditPart source;

    /**
     * The location where the end-user starts to define the zone to insert or remove blank space.
     */
    private int startLocation;

    /**
     * Same location as <code>startLocation</code> but with consideration of zoom (to correctly draw the feedback).
     */
    private int startLocationForFeedback;

    /**
     * The location where the end-user ends to define the zone to insert or remove blank space.
     */
    private int endLocation;

    /**
     * The feedback figure representing the blank space to insert or remove.
     */
    private InsertBlankSpaceGuide blankSpaceGuide;

    /**
     * The diagram viewer where the drag will occur.
     */
    private GraphicalViewer diagramViewer;

    /**
     * Default constructor.
     * 
     * @param diagramViewer
     *            the diagram viewer where the drag will occur.
     * @param editPart
     *            The part associated to this drag tracker.
     */
    public SiriusBlankSpacesDragTracker(AbstractGraphicalEditPart editPart, GraphicalViewer diagramViewer) {
        this.source = editPart;
        this.diagramViewer = diagramViewer;
    }

    @Override
    protected Command getCommand() {
        Command result = UnexecutableCommand.INSTANCE;
        // The case (startLocation == 0 && endLocation == 0) corresponds to the case where the user do the first click
        // (without moving).
        if ((startLocation == 0 && endLocation == 0) || (startLocation != 0 && endLocation != startLocation)) {
            if (isHorizontal(source)) {
                result = createInsertOrRemoveHorizontalBlankSpaceCommand(startLocation, endLocation - startLocation, diagramViewer);
            } else {
                result = createInsertOrRemoveVerticalBlankSpaceCommand(startLocation, endLocation - startLocation, diagramViewer);
            }
        }
        return result;
    }

    @Override
    protected boolean handleButtonDown(int button) {
        startLocation = getCurrentPosition();
        startLocationForFeedback = getCurrentPositionZoomed();
        return super.handleButtonDown(button);
    }

    @Override
    protected boolean handleDragInProgress() {
        endLocation = getCurrentPosition();
        return super.handleDragInProgress();
    }

    /**
     * Execute the command and clean up the recorded location.
     * 
     * @see org.eclipse.gef.internal.ui.rulers.RulerDragTracker#handleButtonUp(int)
     */
    @Override
    protected boolean handleButtonUp(int button) {
        boolean result = super.handleButtonUp(button);
        // Clean up the recorded location.
        startLocation = 0;
        startLocationForFeedback = 0;
        endLocation = 0;
        return result;
    }

    @Override
    protected Cursor calculateCursor() {
        if (getCommand().canExecute()) {
            return isHorizontal(source) ? SharedCursors.SIZEWE : SharedCursors.SIZENS;
        }
        return SharedCursors.NO;
    }

    @Override
    protected void showSourceFeedback() {
        if (blankSpaceGuide == null) {
            blankSpaceGuide = new InsertBlankSpaceGuide(ColorConstants.blue, !isHorizontal(source));
            getFeedbackLayer().add(blankSpaceGuide);
        }
        Rectangle bounds = getFeedbackLayer().getBounds().getCopy();
        if (isHorizontal(source)) {
            bounds.x = startLocationForFeedback;
            bounds.width = getCurrentPositionZoomed() - startLocationForFeedback;
        } else {
            bounds.y = startLocationForFeedback;
            bounds.height = getCurrentPositionZoomed() - startLocationForFeedback;
            if (bounds.height < 0) {
                // from bottom to top
                bounds.y = bounds.y + bounds.height;
                bounds.height = Math.abs(bounds.height);
            }
        }
        blankSpaceGuide.setBounds(bounds);
    }

    @Override
    protected void eraseSourceFeedback() {
        if (blankSpaceGuide != null) {
            getFeedbackLayer().remove(blankSpaceGuide);
            blankSpaceGuide = null;
        }
    }

    /**
     * Returns the layer used for displaying feedback.
     * 
     * @return the feedback layer
     */
    protected IFigure getFeedbackLayer() {
        return getFeedbackLayer(diagramViewer);
    }

    /**
     * Return the current position in the ruler zoomed (ie if the location is 100 and the zoom is 200%, the returned
     * value will be 200).<BR>
     * Method copied from {@link org.eclipse.gef.internal.ui.rulers.RulerDragTracker#getCurrentPositionZoomed()}.
     * 
     * @return the current position in the ruler.
     */
    protected int getCurrentPositionZoomed() {
        Point pt = getLocation();
        source.getFigure().translateToRelative(pt);
        if (!(source instanceof SiriusRulerEditPart)) {
            // When the tool is applied on the diagram, the location must considered the zoom. It is not the case when
            // the tool is applied on the ruler.
            double zoom = getZoom();
            if (zoom != 0) {
                pt.performScale(zoom);
            }
        }
        int position = isHorizontal(source) ? pt.x : pt.y;
        return position;
    }

    /**
     * Return the current position in the ruler.<BR>
     * Method copied from {@link org.eclipse.gef.internal.ui.rulers.RulerDragTracker#getCurrentPosition()}.
     * 
     * @return the current position in the ruler.
     */
    protected int getCurrentPosition() {
        int position = getCurrentPositionZoomed();
        double zoom = getZoom();
        if (zoom != 0) {
            position = (int) Math.round(position / zoom);
        }
        return position;
    }

    /**
     * Get the current zoom level. 0 can be returned if the zoom level is not retrieved.
     * 
     * @return the zoom level
     */
    protected double getZoom() {
        double zoom = 0;
        ZoomManager zoomManager;
        if (source instanceof SiriusRulerEditPart) {
            zoomManager = ((SiriusRulerEditPart) source).getZoomManager();
        } else {
            zoomManager = (ZoomManager) this.getCurrentViewer().getProperty(ZoomManager.class.toString());
        }
        if (zoomManager != null) {
            zoom = zoomManager.getZoom();
        }
        return zoom;
    }

    @Override
    protected String getCommandName() {
        if (startLocation > endLocation) {
            return Messages.RemoveBlankSpace_cmdName;
        } else {
            return Messages.InsertBlankSpace_cmdName;
        }
    }

    /**
     * Returns a drag tracker allowing to add or remove blank in diagram representation.
     * 
     * @param graphicalEditPart
     *            the edit part from which
     * @param diagramViewer
     *            the diagram viewer where the drag will occur.
     * @param request
     *            the current diagram request.
     * @param controlKeyRequirement
     *            true if the CTRL key must be down to return a drag tracker, false otherwise.
     * @param shiftKeyRequirement
     *            true if the SHIFT key must be down to return a drag tracker, false otherwise.
     * @return a drag tracker allowing to add or remove blank in diagram representation.
     */
    public static DragTracker getDragTracker(AbstractGraphicalEditPart graphicalEditPart, GraphicalViewer diagramViewer, Request request, boolean controlKeyRequirement, boolean shiftKeyRequirement) {
        DragTracker result = null;
        if (request.getType().equals(RequestConstants.REQ_SELECTION)) {
            SelectionRequest selectionRequest = (SelectionRequest) request;
            if (selectionRequest.getLastButtonPressed() != 1) {
                result = null;
            } else if ((!controlKeyRequirement || selectionRequest.isControlKeyPressed()) && (!shiftKeyRequirement || selectionRequest.isShiftKeyPressed())) {
                try {
                    if (isHorizontal(graphicalEditPart)) {
                        createInsertOrRemoveHorizontalBlankSpaceCommand(0, 0, diagramViewer);
                    } else {
                        createInsertOrRemoveVerticalBlankSpaceCommand(0, 0, diagramViewer);
                    }
                    result = new SiriusBlankSpacesDragTracker(graphicalEditPart, diagramViewer);
                } catch (UnsupportedOperationException e) {
                    // Do not return a SiriusRulerDragTracker in this case.
                }
            }
        }
        return result;
    }

    /**
     * Create a command that inserts or removes horizontal blank space in a diagram by shifting nodes. It is currently
     * not implemented in Sirius core, probably done later.
     * 
     * @param startLocation
     *            the initial location in pixel to insert or removes blank space
     * @param spaceToInsertOrRemove
     *            the number of pixels to insert or remove
     * @param diagramViewer
     *            the diagram viewer where the drag will occur.
     * @return a command that inserts or removes vertical blank space in a diagram by shifting nodes.
     */
    private static Command createInsertOrRemoveHorizontalBlankSpaceCommand(int startLocation, int spaceToInsertOrRemove, GraphicalViewer diagramViewer) {
        return createInsertOrRemoveBlankSpaceCommand(startLocation, spaceToInsertOrRemove, true, diagramViewer);
    }

    /**
     * Create a command that inserts vertical blank space in a diagram by shifting nodes.
     * 
     * @param startLocation
     *            the initial location in pixel to insert blank space
     * @param spaceToInsert
     *            the number of pixels to insert
     * @param diagramViewer
     *            the diagram viewer where the drag will occur.
     * @return a command that inserts vertical blank space in a diagram by shifting nodes.
     */
    private static Command createInsertOrRemoveVerticalBlankSpaceCommand(int startLocation, int spaceToInsert, GraphicalViewer diagramViewer) {
        return createInsertOrRemoveBlankSpaceCommand(startLocation, spaceToInsert, false, diagramViewer);
    }

    /**
     * Returns true if horizontal blank must be added or removed. False if vertical space must be added or removed.
     * 
     * @param editPart
     *            the edit part from which blank addition or removal is done.
     * @return true if horizontal blank must be added or removed. False if vertical space must be added or removed.
     */
    private static boolean isHorizontal(AbstractGraphicalEditPart editPart) {
        boolean isHorizontal = false;
        if (editPart instanceof DDiagramEditPart) {
            // not yet implemented.
        } else if (editPart instanceof RulerEditPart) {
            isHorizontal = ((RulerEditPart) editPart).isHorizontal();
        }
        return isHorizontal;
    }

    /**
     * Create a command that inserts or removes vertical blank space in a diagram by shifting nodes.
     * 
     * @param startLocation
     *            the initial location in pixel to insert blank space
     * @param spaceToInsertOrRemove
     *            the number of pixels to insert
     * @param horizontal
     *            true if the blank space must be inserted or removed horizontally, false otherwise
     * @param diagramViewer
     *            the diagram viewer where the drag will occur.
     * @return a command to add space.
     */
    private static Command createInsertOrRemoveBlankSpaceCommand(int startLocation, int spaceToInsertOrRemove, boolean horizontal, GraphicalViewer diagramViewer) {
        if (diagramViewer.getRootEditPart().getContents() instanceof IGraphicalEditPart) {
            IGraphicalEditPart diagramEditPart = (IGraphicalEditPart) diagramViewer.getRootEditPart().getContents();
            TransactionalEditingDomain ted = diagramEditPart.getEditingDomain();
            Object property = diagramViewer.getProperty(DDiagramEditor.EDITOR_ID);
            if (property instanceof DDiagramEditor) {
                DDiagramEditor diagramEditor = (DDiagramEditor) property;
                if (horizontal) {
                    // Currently not implemented. A new method buildInsertHorizontalBlankSpaceCommand must be added in
                    // IDiagramCommandFactory and implemented in UndoRedoCapableEMFCommandFactory for standard diagrams
                    // and in SequenceEMFCommandFactory for sequence diagrams.
                    throw new UnsupportedOperationException(Messages.UndoRedoCapableEMFCommandFactory_insertHorizontalBlankSpaceNotImplemented);
                } else {
                    org.eclipse.emf.common.command.Command command = diagramEditor.getEmfCommandFactoryProvider().getCommandFactory(ted)
                            .buildInsertOrRemoveVerticalBlankSpaceCommand((DDiagram) diagramEditPart.getNotationView().getElement(), startLocation, spaceToInsertOrRemove);
                    return new ICommandProxy(new GMFCommandWrapper(ted, command));
                }
            }
        }
        return UnexecutableCommand.INSTANCE;
    }

    /**
     * Returns the layer used for displaying feedback.
     * 
     * @param diagramViewer
     *            the diagram viewer where the drag will occur.
     * @return the feedback layer
     */
    private static IFigure getFeedbackLayer(GraphicalViewer diagramViewer) {
        LayerManager lm = (LayerManager) diagramViewer.getEditPartRegistry().get(LayerManager.ID);
        if (lm != null) {
            return lm.getLayer(LayerConstants.FEEDBACK_LAYER);
        }
        return null;
    }
}
