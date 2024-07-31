/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
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

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.diagram.ui.business.internal.query.RequestQuery;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainer2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartment2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeList2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListViewNodeListCompartment2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListViewNodeListCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.draw2d.ui.figures.FigureUtilities;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;

/**
 * Edit policy for nodes.
 * 
 * @author ymortier
 */
public class NodeCreationEditPolicy extends SiriusContainerEditPolicy {
    /**
     * {@inheritDoc}
     */
    @Override
    protected Command getCreateCommand(CreateRequest request) {
        if (!(getHost().getModel() instanceof Node)) {
            return null;
        }

        EObject containerElement = ((Node) getHost().getModel()).getElement();
        AbstractToolDescription tool = getTool(request);
        /*
         * Dispatch to the appropriate specialized command depending on the type
         * of the container element and the nature of the tool.
         */
        Command result = null;
        if (containerElement instanceof DDiagramElementContainer) {
            DDiagramElementContainer viewNodeContainer = (DDiagramElementContainer) containerElement;
            if (tool instanceof NodeCreationDescription) {
                result = getCreateNodeInContainerCommand(request, (NodeCreationDescription) tool, viewNodeContainer);
            } else if (tool instanceof ContainerCreationDescription) {
                result = getCreateContainerInContainerCommand(request, (ContainerCreationDescription) tool, viewNodeContainer);
            }
        } else if (containerElement instanceof DNode) {
            DNode viewNode = (DNode) containerElement;
            if (tool instanceof NodeCreationDescription) {
                result = getCreateNodeOnNodeCommand(request, (NodeCreationDescription) tool, viewNode);
            }
        }
        return result;
    }

    /**
     * Returns the command to create a new node (bordered node) on a node.
     * 
     * @param request
     *            the original request.
     * @param tool
     *            the node creation tool description.
     * @param viewnode
     *            the node on which to create the new (bordered) node.
     * @return a command to create the new node.
     */
    protected Command getCreateNodeOnNodeCommand(CreateRequest request, NodeCreationDescription tool, DNode viewnode) {
        CreationUtil creationUtil = new CreationUtil(request, getDiagramCommandFactory(), getRealLocation(request), getHost());
        return creationUtil.getNodeCreationCommand(viewnode, tool);
    }

    /**
     * Returns the command to create a new container inside a container.
     * 
     * @param request
     *            the original request.
     * @param tool
     *            the container creation tool description.
     * @param viewNodeContainer
     *            the container on which to create the new (bordered) node.
     * @return a command to create the new container.
     */
    protected Command getCreateContainerInContainerCommand(CreateRequest request, ContainerCreationDescription tool, DDiagramElementContainer viewNodeContainer) {
        CreationUtil creationUtil = new CreationUtil(request, getDiagramCommandFactory(), getRealLocation(request), getHost());
        return creationUtil.getContainerCreationDescription(viewNodeContainer, tool);
    }

    /**
     * Returns the command to create a new node inside a container.
     * 
     * @param request
     *            the original request.
     * @param tool
     *            the node creation tool description.
     * @param viewNodeContainer
     *            the container on which to create the new (bordered) node.
     * @return a command to create the new node.
     */
    protected Command getCreateNodeInContainerCommand(CreateRequest request, NodeCreationDescription tool, DDiagramElementContainer viewNodeContainer) {
        CreationUtil creationUtil = new CreationUtil(request, getDiagramCommandFactory(), getRealLocation(request), getHost());
        if (!tool.getExtraMappings().isEmpty()) {
            // BorderNode requires extra mapping when in Stack Container.
            // If it is in a stack container, we need to provide more layout Data.
            EditPart stackContainer = getStackContainer(getHost());
            if (stackContainer != getHost() && stackContainer != null) { // Host is not the main container.
                creationUtil.setStackContainer(getRealLocation(request, stackContainer), stackContainer);
            }
        }
        return creationUtil.getNodeCreationCommand(viewNodeContainer, tool);
    }
    
    private EditPart getStackContainer(EditPart part) {
        // Note:
        // Model won't help much as it is always 
        // DNodeContainerSpec or DNodeListSpec for the different cases.
        boolean regionOfList = part instanceof DNodeList2EditPart // Head of region
                || part instanceof DNodeListViewNodeListCompartmentEditPart; // List with label
        boolean regionOfFreeForm = part instanceof DNodeContainerViewNodeContainerCompartment2EditPart // Free Region
                || part instanceof DNodeContainer2EditPart // inner header
                || part instanceof DNodeListViewNodeListCompartment2EditPart; // List region
                
        if (regionOfList || regionOfFreeForm) {
            EditPart parent = part.getParent(); 
            while (parent != null  && !isMappedPart(parent)) {
                parent = parent.getParent(); 
            }
            
            return getStackContainer(parent);
        } 
            
        // Not a part of stack container, part is already the last container.
        return part;
    }
    
    private static boolean isMappedPart(EditPart part) {
        // Some parts are only boilerplate.
        return part.getModel() instanceof Node
                && ((Node) part.getModel()).getElement() != null;
    }

    /**
     * Finds the Sirius tool description associated with the request, if any.
     * 
     * @param request
     *            the creation request.
     * @return the Sirius tool description associated with the request, if any.
     */
    protected AbstractToolDescription getTool(CreateRequest request) {
        if (request.getNewObject() instanceof AbstractToolDescription) {
            return (AbstractToolDescription) request.getNewObject();
        } else {
            return null;
        }
    }


    /**
     * Computes the real location where the element must be created from the raw
     * information passed in the request on host edit part.
     * 
     * @param request
     *            the creation request.
     * @return the real location where the element must be created.
     */
    protected Point getRealLocation(final CreateRequest request) {
        return getRealLocation(request, getHost());
    }
    
    /**
     * Computes the real location where the element must be created from the raw
     * information passed in the request.
     * 
     * @param request
     *            the creation request.
     * @param host
     *            the edit part to get the location from
     * @return the real location where the element must be created.
     */
    protected Point getRealLocation(final CreateRequest request, EditPart host) {
        Point location = request.getLocation().getCopy();
        final Point realLocation;
        if (location != null && host instanceof GraphicalEditPart) {
            final IFigure fig = ((GraphicalEditPart) host).getFigure();
            fig.translateToRelative(location);
            final Point containerLocation = fig.getBounds().getLocation();
            location = new Point(location.x - containerLocation.x, location.y - containerLocation.y);
            if (fig instanceof ResizableCompartmentFigure) {
                boolean isBorderNodeCreationRequest = new RequestQuery(request).isDropOrCreationOfBorderNode();
                Point scrollOffset;
                if (isBorderNodeCreationRequest) {
                    // Ignore scroll for border node, the border of the parent
                    // is always visible...
                    scrollOffset = new Point(0, 0);
                } else {
                    scrollOffset = ((ResizableCompartmentFigure) fig).getScrollPane().getViewport().getViewLocation();
                }
                final Point shiftFromMarginOffset = FigureUtilities.getShiftFromMarginOffset((ResizableCompartmentFigure) fig, isBorderNodeCreationRequest, host);
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
     * Returns the emf command factory.
     * 
     * @return the emf command factory.
     */
    private IDiagramCommandFactory getDiagramCommandFactory() {
        final DDiagramEditor diagramEditor = (DDiagramEditor) this.getHost().getViewer().getProperty(DDiagramEditor.EDITOR_ID);
        if (diagramEditor == null) {
            return null;
        }
        final TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(diagramEditor.getEditingDomain().getResourceSet());
        final Object adapter = diagramEditor.getAdapter(IDiagramCommandFactoryProvider.class);
        final IDiagramCommandFactoryProvider cmdFactoryProvider = (IDiagramCommandFactoryProvider) adapter;
        final IDiagramCommandFactory diagramCommandFactory = cmdFactoryProvider.getCommandFactory(transactionalEditingDomain);
        return diagramCommandFactory;
    }
}
