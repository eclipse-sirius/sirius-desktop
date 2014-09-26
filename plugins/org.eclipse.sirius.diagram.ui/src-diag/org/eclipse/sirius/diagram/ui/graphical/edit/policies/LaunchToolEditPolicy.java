/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.sirius.diagram.description.tool.RequestDescription;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.diagram.ui.business.internal.query.RequestQuery;
import org.eclipse.sirius.diagram.ui.tools.api.draw2d.ui.figures.FigureUtilities;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription;
import org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolDescription;

/**
 * Edit policy for launching tools.
 * 
 * @author dlecan
 */
public class LaunchToolEditPolicy extends AbstractEditPolicy {

    /**
     * Build the edit policy.
     * 
     */
    public LaunchToolEditPolicy() {
        // Nothing
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gef.EditPolicy#getTargetEditPart(org.eclipse.gef.Request)
     */
    @Override
    public EditPart getTargetEditPart(final Request request) {
        return understandsRequest(request) ? getHost() : null;
    }

    /**
     * Understand request.
     * 
     * @param request
     *            the request
     * @return true if request is {@link RequestConstants#REQ_CREATE}.
     */
    @Override
    public boolean understandsRequest(final Request request) {
        return RequestConstants.REQ_CREATE.equals(request.getType());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command getCommand(final Request request) {
        Command result;
        if (REQ_CREATE.equals(request.getType()) && request instanceof CreateRequest) {
            result = getCreateCommand((CreateRequest) request);
        } else {
            result = super.getCommand(request);
        }
        return result;
    }

    /**
     * Get create command.
     * 
     * @param request
     *            Request to use.
     * @return Command.
     */
    protected Command getCreateCommand(final CreateRequest request) {
        // final Point location = request.getLocation().getCopy();
        // if (location != null && getHost() instanceof IGraphicalEditPart) {
        // ((IGraphicalEditPart)
        // getHost()).getFigure().translateToRelative(location);
        // }
        return createCommandFromRequest(request, getRealLocation(request), getHost());
    }

    /**
     * Computes the real location where the element must be created from the raw
     * information passed in the request.
     * 
     * @param request
     *            the creation request.
     * @return the real location where the element must be created.
     */
    protected Point getRealLocation(final CreateRequest request) {
        Point location = request.getLocation().getCopy();
        final Point realLocation;
        if (location != null && getHost() instanceof GraphicalEditPart) {
            final IFigure fig = ((GraphicalEditPart) getHost()).getFigure();
            fig.translateToRelative(location);
            final Point containerLocation = fig.getBounds().getLocation();
            location = new Point(location.x - containerLocation.x, location.y - containerLocation.y);
            if (fig instanceof ResizableCompartmentFigure) {
                final Point scrollOffset = ((ResizableCompartmentFigure) fig).getScrollPane().getViewport().getViewLocation();
                final IFigure hostFigure = ((IGraphicalEditPart) getHost()).getFigure();
                final Point shiftFromMarginOffset = FigureUtilities.getShiftFromMarginOffset((ResizableCompartmentFigure) hostFigure, new RequestQuery(request).isDropOrCreationOfBorderNode(),
                        getHost());
                realLocation = new Point(location.x + scrollOffset.x - shiftFromMarginOffset.x, location.y + scrollOffset.y - shiftFromMarginOffset.y);
            } else {
                realLocation = location;
            }
        } else {
            realLocation = location;
        }
        return realLocation;
    }

    /**
     * Create command from request.
     * 
     * @param request
     *            Request where to extract data.
     * @param location
     *            Location where command will apply.
     * @param editPart
     *            Edit part where command will apply.
     * @return Created command.
     */
    private Command createCommandFromRequest(final CreateRequest request, final Point location, final EditPart editPart) {
        Command result = null;

        if (request.getNewObject() instanceof AbstractToolDescription) {
            final AbstractToolDescription tool = (AbstractToolDescription) request.getNewObject();
            final CreationUtil creationUtil = getCreationUtil(request, location, editPart, LaunchToolEditPolicy.getDiagramCommandFactory(editPart));

            if (creationUtil != null) {
                final EObject containerView = ((IGraphicalEditPart) editPart).resolveSemanticElement();
                if (tool instanceof RequestDescription) {
                    result = creationUtil.getRequestToolCommand((RequestDescription) tool, editPart);

                } else if (tool instanceof SelectionWizardDescription) {
                    result = creationUtil.getSelectionWizardCommand((SelectionWizardDescription) tool, containerView);

                } else if (tool instanceof PaneBasedSelectionWizardDescription) {
                    result = creationUtil.getPaneBasedSelectionWizardCommand((PaneBasedSelectionWizardDescription) tool, containerView);

                } else if (tool instanceof ToolDescription) {
                    result = creationUtil.getGenericToolCommand(containerView, (ToolDescription) request.getNewObject());
                }
            }
        }
        return result;
    }

    /**
     * Return the helper to build the requested command.
     * 
     * @param request
     *            the request to handle.
     * @param location
     *            the current location.
     * @param editPart
     *            the edit part.
     * @param baseEmfCommandFactory
     *            the base command factory.
     * @return an helper to build the command.
     */
    protected CreationUtil getCreationUtil(final CreateRequest request, final Point location, final EditPart editPart, final IDiagramCommandFactory baseEmfCommandFactory) {
        return new CreationUtil(request, baseEmfCommandFactory, location, editPart);
    }

    /**
     * Returns the emf command factory.
     * 
     * @param editPart
     *            the current edit part
     * 
     * @return the emf command factory.
     */
    private static IDiagramCommandFactory getDiagramCommandFactory(final EditPart editPart) {
        IDiagramCommandFactory diagramCommandFactory = null;

        final DDiagramEditor diagramEditor = (DDiagramEditor) editPart.getViewer().getProperty(DDiagramEditor.EDITOR_ID);
        final TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(diagramEditor.getEditingDomain().getResourceSet());
        if (diagramEditor != null) {
            final IDiagramCommandFactoryProvider adapter = (IDiagramCommandFactoryProvider) diagramEditor.getAdapter(IDiagramCommandFactoryProvider.class);
            diagramCommandFactory = adapter.getCommandFactory(transactionalEditingDomain);
        }

        return diagramCommandFactory;
    }
}
