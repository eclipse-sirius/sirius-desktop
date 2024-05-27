/*******************************************************************************
 * Copyright (c) 2007, 2024 THALES GLOBAL SERVICES.
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

import java.util.Optional;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sirius.diagram.model.business.internal.helper.MappingHelper;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.diagram.ui.business.internal.query.RequestQuery;
import org.eclipse.sirius.diagram.ui.business.internal.view.RootLayoutData;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDNodeListCompartmentEditPart;
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

        AbstractToolDescription tool = getTool(request);
        GraphicalEditPart hostEditPartToUse = (GraphicalEditPart) getHost();
        if (getHost() instanceof GraphicalEditPart) {
            boolean isBorderNodeCreationRequest = new RequestQuery(request).isDropOrCreationOfBorderNode();
            if (tool instanceof NodeCreationDescription nodeCreationDescriptionTool && isBorderNodeCreationRequest) {
                // Search the correct edit part for this tool. It can be different than the host in case of "extra
                // mappings" defined in the tool.
                Optional<GraphicalEditPart> optionalEditPartToUse = getParentEditPartWithExpectedMapping((GraphicalEditPart) getHost(), nodeCreationDescriptionTool.getNodeMappings());
                if (optionalEditPartToUse.isPresent()) {
                    hostEditPartToUse = optionalEditPartToUse.get();
                }
            }
        }
        if (!(hostEditPartToUse.getModel() instanceof Node)) {
            return null;
        }
        EObject containerElement = ((Node) hostEditPartToUse.getModel()).getElement();

        /*
         * Dispatch to the appropriate specialized command depending on the type of the container element and the nature
         * of the tool.
         */
        Command result = null;
        if (containerElement instanceof DDiagramElementContainer) {
            DDiagramElementContainer viewNodeContainer = (DDiagramElementContainer) containerElement;
            if (tool instanceof NodeCreationDescription) {
                result = getCreateNodeInContainerCommand(request, (NodeCreationDescription) tool, viewNodeContainer, hostEditPartToUse);
            } else if (tool instanceof ContainerCreationDescription) {
                result = getCreateContainerInContainerCommand(request, (ContainerCreationDescription) tool, viewNodeContainer);
            }
        } else if (containerElement instanceof DNode) {
            DNode viewNode = (DNode) containerElement;
            if (tool instanceof NodeCreationDescription) {
                result = getCreateNodeOnNodeCommand(request, (NodeCreationDescription) tool, viewNode, hostEditPartToUse);
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
    protected Command getCreateNodeOnNodeCommand(CreateRequest request, NodeCreationDescription tool, DNode viewnode, EditPart parentEditPartToUse) {
        CreationUtil creationUtil = new CreationUtil(getDiagramCommandFactory(), getRealLayoutData(request, parentEditPartToUse), parentEditPartToUse);
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
        CreationUtil creationUtil = new CreationUtil(request, getDiagramCommandFactory(), getRealLocation(request, getHost()), getHost());
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
     * @param parentEditPartToUse
     *            parent EditPart to retrieve layout data (can be same as getHost(), but can also be another edit part
     *            according to extra mapping defined in tool, for border nodes for example).
     * @return a command to create the new node.
     */
    protected Command getCreateNodeInContainerCommand(CreateRequest request, NodeCreationDescription tool, DDiagramElementContainer viewNodeContainer, EditPart parentEditPartToUse) {
        CreationUtil creationUtil = new CreationUtil(getDiagramCommandFactory(), getRealLayoutData(request, parentEditPartToUse), parentEditPartToUse);
        return creationUtil.getNodeCreationCommand(viewNodeContainer, tool);
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
     * Computes the real location where the element must be created from the raw information passed in the request.
     * 
     * @param request
     *            the creation request.
     * @param parentEditPartToUse
     *            parent EditPart to retrieve layout data (can be same as getHost(), but can also be another edit part
     *            according to extra mapping defined in tool, for border nodes for example).
     * @return the real location where the element must be created.
     */
    protected Point getRealLocation(final CreateRequest request, EditPart parentEditPartToUse) {
        return getRealLayoutData(request, parentEditPartToUse).getLocation();
    }

    /**
     * Computes the real location where the element must be created from the raw information passed in the request.
     * 
     * @param request
     *            the creation request.
     * @param parentEditPartToUse
     *            parent EditPart to retrieve layout data (can be same as getHost(), but can also be another edit part
     *            according to extra mapping defined in tool, for border nodes for example).
     * @return the real location where the element must be created.
     */
    protected RootLayoutData getRealLayoutData(final CreateRequest request, EditPart parentEditPartToUse) {
        Point location = request.getLocation().getCopy();
        final Point realLocation;
        if (location != null && parentEditPartToUse instanceof GraphicalEditPart graphicalParentEditPart) {
            final IFigure fig = graphicalParentEditPart.getFigure();
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
                final Point shiftFromMarginOffset = FigureUtilities.getShiftFromMarginOffset((ResizableCompartmentFigure) fig, isBorderNodeCreationRequest, parentEditPartToUse);
                realLocation = new Point(location.x + scrollOffset.x - shiftFromMarginOffset.x, location.y + scrollOffset.y - shiftFromMarginOffset.y);

            } else {
                realLocation = location;
            }
        } else {
            realLocation = location;
        }
        return new RootLayoutData(parentEditPartToUse, realLocation.getCopy(), CreationUtil.adaptRequestSizeToZoomFactor(request, parentEditPartToUse));
    }

    private Optional<GraphicalEditPart> getParentEditPartWithExpectedMapping(GraphicalEditPart editPart, EList<NodeMapping> nodeMappings) {
        Optional<GraphicalEditPart> result = Optional.empty();
        if (editPart instanceof AbstractDNodeListCompartmentEditPart) {
            result = getParentEditPartWithExpectedMapping((GraphicalEditPart) editPart.getParent(), nodeMappings);
        } else if (editPart.getParent() instanceof AbstractDiagramContainerEditPart && ((AbstractDiagramContainerEditPart) editPart.getParent()).isRegionContainer()) {
            result = getParentEditPartWithExpectedMapping((AbstractDiagramContainerEditPart) editPart.getParent(), nodeMappings);
        } else {
            for (NodeMapping nodeMapping : nodeMappings) {
                if (editPart != null && editPart.getModel() instanceof Node node && node.getElement() instanceof AbstractDNode abstractDNode
                        && abstractDNode.getMapping() instanceof AbstractNodeMapping abstractNodegetMapping) {
                    if (MappingHelper.getAllBorderedNodeMappings(abstractNodegetMapping).contains(nodeMapping)) {
                        result = Optional.of(editPart);
                        break;
                    }
                }
            }
        }
        if (result.isEmpty() && editPart.getParent() instanceof GraphicalEditPart graphicalEditPart) {
            result = getParentEditPartWithExpectedMapping(graphicalEditPart, nodeMappings);
        }
        return result;
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
