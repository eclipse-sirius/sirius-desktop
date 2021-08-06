/*******************************************************************************
 * Copyright (c) 2007, 2018 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DragDropEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderedNodeFigure;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.resource.support.WorkspaceDragAndDropSupport;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.business.internal.metamodel.operations.DDiagramElementContainerWithInterpreterOperations;
import org.eclipse.sirius.diagram.description.DragAndDropTargetDescription;
import org.eclipse.sirius.diagram.description.tool.ContainerDropDescription;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.diagram.ui.business.internal.command.AddLayoutDataToManageCommand;
import org.eclipse.sirius.diagram.ui.business.internal.query.RequestQuery;
import org.eclipse.sirius.diagram.ui.business.internal.view.AbstractLayoutData;
import org.eclipse.sirius.diagram.ui.business.internal.view.RootLayoutData;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.graphical.edit.internal.policies.validators.DragAndDropValidator;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.command.GMFCommandWrapper;
import org.eclipse.sirius.diagram.ui.tools.api.draw2d.ui.figures.FigureUtilities;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.internal.dnd.DragAndDropWrapper;
import org.eclipse.sirius.diagram.ui.tools.internal.util.EditPartQuery;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.tool.DragSource;
import org.eclipse.swt.graphics.Color;

/**
 * EditPolicy used to handle Drop of ViewNodes in a DDiagramElementContainer.
 * 
 * @author cbrun
 */
public class SiriusContainerDropPolicy extends DragDropEditPolicy {

    /**
     * The name of the Drag'n'Drop command.
     */
    public static final String DROP_ELEMENTS_CMD_NAME = Messages.SiriusContainerDropPolicy_dropElementsCommandLabel;

    /** The background feedback color as in GMF. */
    private static final Color GRAY = new Color(null, 200, 200, 200);

    /**
     * the original target figure
     */
    private IFigure figure;

    /**
     * A weak reference to the last request used in {@link #getDropCommand(ChangeBoundsRequest)} or
     * {@link #showTargetFeedback(Request)}. This reference is linked to
     * {@link SiriusContainerDropPolicy#weakLastCommand}. This two weak references allow to be more fluent during drag
     * and avoid to recompute the command to each call. The method {@link #addCommandInWeakCache(Request, Command)} must
     * be used to set this two members.
     */
    private WeakReference<Request> weakLastRequest;

    /**
     * A weak reference to the last command computed in {@link #getDropCommand(ChangeBoundsRequest)} or
     * {@link #showTargetFeedback(Request)}. This reference is linked to
     * {@link SiriusContainerDropPolicy#weakLastRequest}. This two weak references allow to be more fluent during drag
     * and avoid to recompute the command to each call. The method {@link #addCommandInWeakCache(Request, Command)} must
     * be used to set this two members.
     */
    private WeakReference<Command> weakLastCommand;

    /**
     * Create a new drop in container policy.
     */
    public SiriusContainerDropPolicy() {
        super();
    }

    /**
     * {@inheritDoc}
     * 
     * We can use a cache here, because the command is used only to know if it is executable.
     * 
     * @see org.eclipse.gef.EditPolicy#showTargetFeedback(org.eclipse.gef.Request)
     */
    @Override
    public void showTargetFeedback(Request request) {
        Command c = null;
        if (weakLastRequest != null && request.equals(weakLastRequest.get()) && weakLastCommand != null) {
            c = weakLastCommand.get();
        }
        if (c == null) {
            c = getCommand(request);
            if (c != null) {
                if (!RequestConstants.REQ_DROP.equals(request.getType())) {
                    // If the request type is a RequestConstants.REQ_DROP, the
                    // command has already been added to the cache in method
                    // getDropCommand.
                    addCommandInWeakCache(request, c);
                }
            }
        }
        if (c != null && c.canExecute() && revertColor == null) {
            if (getHostFigure() instanceof BorderedNodeFigure) {
                final Rectangle bounds = getHostFigure().getBounds().getCopy();
                getHostFigure().translateToAbsolute(bounds);
                IFigure rootFigure = ((AbstractGraphicalEditPart) getHost().getRoot()).getFigure();
                Point searchPoint = bounds.getCenter();
                if (!rootFigure.getBounds().contains(searchPoint)) {
                    // If the search is not in the bounds of the root figure, we
                    // set the search point to the center of the visible part of
                    // the figure.
                    Rectangle intersection = rootFigure.getBounds().getIntersection(bounds);
                    if (!intersection.isEmpty()) {
                        searchPoint = intersection.getCenter();
                    }
                }
                figure = rootFigure.findFigureAt(searchPoint);
                revertColor = figure.getBackgroundColor();
                opacity = figure.isOpaque();
                figure.setBackgroundColor(org.eclipse.draw2d.FigureUtilities.mixColors(GRAY, revertColor));
                figure.setOpaque(true);
            } else {
                revertColor = getHostFigure().getBackgroundColor();
                opacity = getHostFigure().isOpaque();
                getHostFigure().setBackgroundColor(org.eclipse.draw2d.FigureUtilities.mixColors(GRAY, revertColor));
                getHostFigure().setOpaque(true);
            }
        }
    }

    /**
     * Set the cache represented by the two weak members.
     * 
     * @param request
     *            The request to add as key in the weak cache
     * @param command
     *            The command to add as value in the weak cache
     */
    private void addCommandInWeakCache(Request request, Command command) {
        weakLastRequest = new WeakReference<Request>(request);
        weakLastCommand = new WeakReference<Command>(command);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void eraseTargetFeedback(Request request) {
        if (revertColor != null) {
            if (getHostFigure() instanceof BorderedNodeFigure) {
                figure.setBackgroundColor(revertColor);
                figure.setOpaque(opacity);
                figure = null;
            } else {
                getHostFigure().setBackgroundColor(revertColor);
                getHostFigure().setOpaque(opacity);
            }
            revertColor = null;
        }
    }

    /**
     * Return the {@link IDiagramCommandFactoryProvider}.
     * 
     * @return the {@link IDiagramCommandFactoryProvider}.
     */
    private IDiagramCommandFactoryProvider getIEMFCommandFactoryProvider() {
        final DDiagramEditor diagramEditor = (DDiagramEditor) this.getHost().getViewer().getProperty(DDiagramEditor.EDITOR_ID);
        final Object adapter = diagramEditor.getAdapter(IDiagramCommandFactoryProvider.class);
        final IDiagramCommandFactoryProvider cmdFactoryProvider = (IDiagramCommandFactoryProvider) adapter;
        return cmdFactoryProvider;
    }

    /**
     * {@inheritDoc}
     * 
     * We can use a cache here, because:
     * <UL>
     * <LI>for drag and drop from diagram, the buildCommand is called at the execution of the command and it recomputes
     * the location directly from the request (the location is updated in the request at each call of this method,</LI>
     * <LI>for drag and drop from "model explorer view" (not a diagram), the request is different for each call to this
     * method.</LI>
     * </UL>
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.DragDropEditPolicy#getDropCommand(org.eclipse.gef.requests.ChangeBoundsRequest)
     * 
     * 
     */
    @Override
    protected Command getDropCommand(final ChangeBoundsRequest request) {
        if (weakLastRequest != null && request.equals(weakLastRequest.get()) && weakLastCommand != null && weakLastCommand.get() != null) {
            return weakLastCommand.get();
        }
        Command dropCommand = null;
        EditPart hostEditPart = getHost();
        if (hostEditPart instanceof GraphicalEditPart) {
            GraphicalEditPart hostGraphicalEditPart = (GraphicalEditPart) hostEditPart;
            final TransactionalEditingDomain editingDomain = hostGraphicalEditPart.getEditingDomain();

            EObject hostSemanticElement = hostGraphicalEditPart.resolveSemanticElement();
            if (hostSemanticElement instanceof DragAndDropTarget) {
                // Validate dragAndDrop
                final DragAndDropTarget targetDragAndDropTarget = (DragAndDropTarget) hostSemanticElement;
                final DragAndDropValidator validator = new DragAndDropValidator();

                validator.setTargetDragAndDropTarget(targetDragAndDropTarget);

                if (validator.isValid(request, hostGraphicalEditPart)) {
                    Point absoluteRequestLocation = request.getLocation().getCopy();
                    absoluteRequestLocation = computeSnap(request, hostGraphicalEditPart, absoluteRequestLocation);
                    GraphicalHelper.screen2logical(absoluteRequestLocation, hostGraphicalEditPart);
                    final Point locationRelativeToNewContainer = computeRelativeLocation(absoluteRequestLocation, false,
                            new RequestQuery(request).isDropOrCreationOfBorderNode() || validator.isConcerningOnlyBorderNodeFromView());
                    // Create an intermediate command. The "real" command is
                    // created during the execution (this to avoid time
                    // consumption during drag).
                    dropCommand = new Command(DROP_ELEMENTS_CMD_NAME) {

                        private Command innerDropCommand;

                        @Override
                        public void execute() {
                            innerDropCommand = buildCommand(validator, request, targetDragAndDropTarget, locationRelativeToNewContainer, editingDomain);
                            if (innerDropCommand != null && innerDropCommand.canExecute()) {
                                innerDropCommand.execute();
                            }
                            // The action has been executed, the cache is now
                            // not useful.
                            weakLastRequest = null;
                            weakLastCommand = null;
                        }

                        @Override
                        public void undo() {
                            if (innerDropCommand != null) {
                                innerDropCommand.undo();
                            }
                        }

                        @Override
                        public void redo() {
                            if (innerDropCommand != null) {
                                innerDropCommand.redo();
                            }
                        }
                    };

                } else {
                    dropCommand = UnexecutableCommand.INSTANCE;
                }
            }
        }
        addCommandInWeakCache(request, dropCommand);
        return dropCommand;
    }

    private Point computeSnap(final ChangeBoundsRequest request, GraphicalEditPart hostGraphicalEditPart, Point absoluteRequestLocation) {
        if (hostGraphicalEditPart != null) {
            EditPartQuery editPartQuery = new EditPartQuery(hostGraphicalEditPart);
            return editPartQuery.getSnapLocation(request, absoluteRequestLocation);
        }
        return absoluteRequestLocation;
    }

    private Command buildCommand(DragAndDropValidator validator, ChangeBoundsRequest request, DragAndDropTarget targetDragAndDropTarget, Point adaptedRequestLocation,
            TransactionalEditingDomain editingDomain) {
        Command result = null;
        Set<DragAndDropWrapper> elementsFromEclipseViewToDrop = validator.getElementsFromEclipseViewToDrop();
        Set<DDiagramElement> elementsFromDiagramToDrop = validator.getElementsFromDiagramToDrop();
        Set<IGraphicalEditPart> editPartsFromDiagramToDrop = validator.getEditPartsFromDiagramToDrop();

        IDiagramCommandFactoryProvider diagramFactoryProvider = getIEMFCommandFactoryProvider();
        IDiagramCommandFactory diagramCommandFactory = diagramFactoryProvider.getCommandFactory(editingDomain);
        DragAndDropTargetDescription dragDragAndDropDescription = validator.getDragDragAndDropDescription();
        EObject targetAbstractDNodeSemanticTarget = validator.getTargetAbstractDNodeSemanticTarget();

        org.eclipse.emf.common.command.CompoundCommand command = new org.eclipse.emf.common.command.CompoundCommand(DROP_ELEMENTS_CMD_NAME);

        /* The drag operation starts from an eclipse view */
        for (DragAndDropWrapper elementFromEclipseViewToDrop : elementsFromEclipseViewToDrop) {
            if (elementFromEclipseViewToDrop.getObject() instanceof StructuredSelection) {
                StructuredSelection structuredSelection = (StructuredSelection) elementFromEclipseViewToDrop.getObject();
                final Iterator<?> selection = structuredSelection.iterator();
                while (selection.hasNext()) {
                    Object next = selection.next();
                    Session session = SessionManager.INSTANCE.getSession(targetAbstractDNodeSemanticTarget);
                    EObject droppedElementForDropTool = new WorkspaceDragAndDropSupport().convert(next, session);
                    ContainerDropDescription dropTool = DDiagramElementContainerWithInterpreterOperations.getBestDropDescription(dragDragAndDropDescription, droppedElementForDropTool, null,
                            targetAbstractDNodeSemanticTarget, targetDragAndDropTarget, DragSource.PROJECT_EXPLORER_LITERAL, null);
                    org.eclipse.emf.common.command.Command buildDropInContainerCommandFromTool = diagramCommandFactory.buildDropInContainerCommandFromTool(targetDragAndDropTarget,
                            droppedElementForDropTool, dropTool);
                    org.eclipse.emf.common.command.Command cmd = buildDropInContainerCommandFromTool;
                    if (cmd != null && cmd.canExecute()) {
                        command.append(cmd);
                    }
                }
                AbstractLayoutData abstractLayoutData = new RootLayoutData(getHost(), adaptedRequestLocation, new Dimension(-1, -1));
                org.eclipse.emf.common.command.Command addLayoutDataToManageCommand = new AddLayoutDataToManageCommand(abstractLayoutData);
                if (addLayoutDataToManageCommand != null && addLayoutDataToManageCommand.canExecute()) {
                    command.append(addLayoutDataToManageCommand);
                }
            }
        }
        /* The drag operation starts from an Diagram */
        for (DDiagramElement elementFromDiagramToDrop : elementsFromDiagramToDrop) {
            ContainerDropDescription dropTool = DDiagramElementContainerWithInterpreterOperations.getBestDropDescription(dragDragAndDropDescription,
                    ((DSemanticDecorator) elementFromDiagramToDrop).getTarget(), SiriusContainerDropPolicy.getSemanticContainer(elementFromDiagramToDrop), targetAbstractDNodeSemanticTarget,
                    targetDragAndDropTarget, DragSource.DIAGRAM_LITERAL, elementFromDiagramToDrop);
            org.eclipse.emf.common.command.Command cmd = diagramCommandFactory.buildDropInContainerCommandFromTool(targetDragAndDropTarget, elementFromDiagramToDrop, dropTool);
            if (cmd != null && cmd.canExecute()) {
                command.append(cmd);
            }
            org.eclipse.emf.common.command.Command saveLayoutCommand = getSaveLayoutCommand(request, editPartsFromDiagramToDrop, editingDomain);
            if (saveLayoutCommand != null && saveLayoutCommand.canExecute()) {
                command.append(saveLayoutCommand);
            }
        }
        if (command != null && command.canExecute()) {
            result = new ICommandProxy(new GMFCommandWrapper(editingDomain, command));
        }
        return result;
    }

    private static EObject getSemanticContainer(final DDiagramElement diagramElement) {
        EObject semanticContainer = null;
        EObject current = diagramElement.eContainer();
        while (current != null && semanticContainer == null) {
            if (current instanceof DSemanticDecorator) {
                semanticContainer = ((DSemanticDecorator) current).getTarget();
            }
            current = current.eContainer();
        }
        return semanticContainer;
    }

    @SuppressWarnings("unchecked")
    private org.eclipse.emf.common.command.Command getSaveLayoutCommand(ChangeBoundsRequest request, final Iterable<IGraphicalEditPart> editPartsToDrop, TransactionalEditingDomain editingDomain) {
        org.eclipse.emf.common.command.CompoundCommand saveLayoutCommand = new org.eclipse.emf.common.command.CompoundCommand(Messages.SiriusContainerDropPolicy_saveEditPartLayoutCommandLabel);
        EditPartViewer viewer = getHost().getViewer();

        Point moveDelta = request.getMoveDelta().getCopy();
        boolean isConcernedBorderNode = new RequestQuery(request).isDropOrCreationOfBorderNode();

        // Get border node locations computed during feedback display (in case
        // of drag'n'drop of border node).
        Map<DDiagramElement, Point> borderNodeLocationForDDiagramElement = null;
        Object requestData = request.getExtendedData().get(SpecificBorderItemSelectionEditPolicy.BORDER_NODE_REAL_LOCATION_KEY);
        if (requestData instanceof Map<?, ?>) {
            borderNodeLocationForDDiagramElement = (Map<DDiagramElement, Point>) requestData;
        }
        // Clean the extended data of the request
        request.getExtendedData().put(SpecificBorderItemSelectionEditPolicy.BORDER_NODE_REAL_LOCATION_KEY, null);

        /* Stores the layout data about the current dropped element */
        for (final IGraphicalEditPart editPart : editPartsToDrop) {
            if (editPart instanceof ShapeEditPart) {
                final ShapeEditPart shapeEditPart = (ShapeEditPart) editPart;
                final Object adaptObject = shapeEditPart.resolveSemanticElement();
                if (adaptObject instanceof AbstractDNode) {
                    AbstractDNode abstractDNode = (AbstractDNode) adaptObject;

                    Point absoluteLayoutConstraint = shapeEditPart.getFigure().getBounds().getTopLeft().getCopy();
                    shapeEditPart.getFigure().translateToAbsolute(absoluteLayoutConstraint);
                    GraphicalHelper.screen2logical(absoluteLayoutConstraint, (IGraphicalEditPart) getHost());
                    Point scaledMoveDelta = moveDelta.getScaled(1.0d / GraphicalHelper.getZoom(getHost()));
                    absoluteLayoutConstraint.translate(scaledMoveDelta);

                    boolean isBorderNode = editPart instanceof AbstractDiagramBorderNodeEditPart;
                    if (isBorderNode) {
                        // Get the computed location for feedback
                        if (borderNodeLocationForDDiagramElement != null && borderNodeLocationForDDiagramElement.get(abstractDNode) != null) {
                            // Adapt this location to the current zoom
                            Point borderNodeFeedbackLocation = borderNodeLocationForDDiagramElement.get(abstractDNode).getScaled(1.0d / GraphicalHelper.getZoom(getHost()));
                            // Translate the original delta with the diff
                            // between proposed location and the real one.
                            Dimension deltaBetweenProposedAndRealLocation = borderNodeFeedbackLocation.getDifference(absoluteLayoutConstraint);
                            scaledMoveDelta.translate(deltaBetweenProposedAndRealLocation.width, deltaBetweenProposedAndRealLocation.height);
                            // Replace the proposed location by the real one.
                            absoluteLayoutConstraint = borderNodeFeedbackLocation;
                        }
                    }
                    final Point computedLayoutConstraint = computeRelativeLocation(absoluteLayoutConstraint, isBorderNode, isConcernedBorderNode);

                    AbstractLayoutData abstractLayoutData = new RootLayoutData(abstractDNode, shapeEditPart, viewer, computedLayoutConstraint, scaledMoveDelta);

                    org.eclipse.emf.common.command.Command addLayoutDataToManageCommand = new AddLayoutDataToManageCommand(abstractLayoutData);
                    saveLayoutCommand.append(addLayoutDataToManageCommand);
                }
            }
        }
        return saveLayoutCommand;
    }

    /**
     * Compute location relative to the figure of the host of this policy (relative to the new container).
     * 
     * @param absolutePointerLocation
     *            The absolute location
     * @param isBorderNode
     *            indicates if the dropped element is a border node
     * @return the relative location
     */
    private Point computeRelativeLocation(final Point absolutePointerLocation, boolean isBorderNode, boolean isConcernedBorderedNode) {
        if (absolutePointerLocation != null && getHost() instanceof IGraphicalEditPart) {
            final IFigure hostFigure = ((IGraphicalEditPart) getHost()).getFigure();
            Dimension difference;
            // If the host edit part is the diagram itself, there is no need to proceed a translation since the
            // absolutePointerLocation value is already a logical absolute coordinate.
            if (getHost() instanceof DiagramEditPart) {
                difference = new Dimension(absolutePointerLocation.x, absolutePointerLocation.y);
            } else {
                Point topLeftHostLocation = hostFigure.getBounds().getTopLeft().getCopy();
                // To avoid the -1 bounds of BorderItemsAwareFreeFormLayer
                if (topLeftHostLocation.x == -1) {
                    topLeftHostLocation.x = 0;
                }
                hostFigure.translateToAbsolute(topLeftHostLocation);
                GraphicalHelper.screen2logical(topLeftHostLocation, (IGraphicalEditPart) getHost());

                difference = absolutePointerLocation.getDifference(topLeftHostLocation);
            }
            Point locationHint = new Point(difference.width, difference.height);
            // if the node is place in a CompartmentEditPart and is
            // not a border node, we must take the shifts of compartments in
            // account
            if ((hostFigure instanceof ResizableCompartmentFigure) && (getHost() instanceof AbstractDNodeContainerCompartmentEditPart) && !isBorderNode) {
                final Point scrollOffset = ((ResizableCompartmentFigure) hostFigure).getScrollPane().getViewport().getViewLocation();
                final Point shiftFromMarginOffset = FigureUtilities.getShiftFromMarginOffset((ResizableCompartmentFigure) hostFigure, isConcernedBorderedNode, getHost());
                locationHint = new Point(locationHint.x + scrollOffset.x - shiftFromMarginOffset.x, locationHint.y + scrollOffset.y - shiftFromMarginOffset.y);
            }

            return locationHint;
        }
        return null;
    }

}
