/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.gef.internal.ui.rulers.RulerDragTracker;
import org.eclipse.gef.internal.ui.rulers.RulerEditPart;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.command.GMFCommandWrapper;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;

/**
 * A specific {@link RulerEditPart} to use a specific {@link RulerDragTracker}, to insert blank space instead of create
 * guide, if Ctrl key is pressed.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
@SuppressWarnings("restriction")
public class SiriusRulerEditPart extends RulerEditPart {

    /**
     * Default constructor.
     * 
     * @param model
     *            The primary model object that this EditPart represents
     */
    public SiriusRulerEditPart(Object model) {
        super(model);
    }

    @Override
    public DragTracker getDragTracker(Request request) {
        DragTracker result = null;
        if (request.getType().equals(REQ_SELECTION)) {
            SelectionRequest selectionRequest = (SelectionRequest) request;
            if (selectionRequest.getLastButtonPressed() != 1) {
                result = null;
            } else if (selectionRequest.isControlKeyPressed()) {
                try {
                    if (isHorizontal()) {
                        createInsertHorizontalBlankSpaceCommand(0, 0);
                    } else {
                        createInsertVerticalBlankSpaceCommand(0, 0);
                    }
                    result = new SiriusRulerDragTracker(this);
                } catch (UnsupportedOperationException e) {
                    // Do not return a SiriusRulerDragTracker in this case.
                }
            }
        }
        if (result == null) {
            return super.getDragTracker(request);
        } else {
            return result;
        }
    }

    /**
     * Create a command that inserts horizontal blank space in a diagram by shifting nodes. It is currently not
     * implemented in Sirius core, probably done later.
     * 
     * @param startLocation
     *            the initial location in pixel to insert blank space
     * @param spaceToInsert
     *            the number of pixels to insert
     * @return .
     */
    public Command createInsertHorizontalBlankSpaceCommand(int startLocation, int spaceToInsert) {
        return createInsertBlankSpaceCommand(startLocation, spaceToInsert, true);
    }

    /**
     * Create a command that inserts vertical blank space in a diagram by shifting nodes.
     * 
     * @param startLocation
     *            the initial location in pixel to insert blank space
     * @param spaceToInsert
     *            the number of pixels to insert
     * @return .
     */
    public Command createInsertVerticalBlankSpaceCommand(int startLocation, int spaceToInsert) {
        return createInsertBlankSpaceCommand(startLocation, spaceToInsert, false);
    }

    /**
     * Create a command that inserts vertical blank space in a diagram by shifting nodes.
     * 
     * @param startLocation
     *            the initial location in pixel to insert blank space
     * @param spaceToInsert
     *            the number of pixels to insert
     * @param horizontal
     *            true if the blank space must be inserted horizontally, false otherwise
     * @return a command to add space.
     */
    protected Command createInsertBlankSpaceCommand(int startLocation, int spaceToInsert, boolean horizontal) {
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
                            .buildInsertVerticalBlankSpaceCommand((DDiagram) diagramEditPart.getNotationView().getElement(), startLocation, spaceToInsert);
                    return new ICommandProxy(new GMFCommandWrapper(ted, command));
                }
            }
        }
        return UnexecutableCommand.INSTANCE;
    }


    /**
     * Returns the layer used for displaying feedback.
     * 
     * @return the feedback layer
     */
    public IFigure getFeedbackLayer() {
        LayerManager lm = (LayerManager) diagramViewer.getEditPartRegistry().get(LayerManager.ID);
        if (lm != null) {
            return lm.getLayer(LayerConstants.FEEDBACK_LAYER);
        }
        return null;
    }
}
