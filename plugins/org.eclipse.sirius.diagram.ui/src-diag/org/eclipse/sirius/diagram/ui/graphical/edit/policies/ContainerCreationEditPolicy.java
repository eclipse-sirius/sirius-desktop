/*******************************************************************************
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;

/**
 * Edit policy for diagram containers.
 * 
 * @author ymortier
 */
public class ContainerCreationEditPolicy extends SiriusContainerEditPolicy {
    /**
     * {@inheritDoc}
     */
    @Override
    protected Command getCreateCommand(CreateRequest request) {
        if (!(getHost().getModel() instanceof View)) {
            return null;
        }

        EObject containerElement = ((View) this.getHost().getModel()).getElement();
        AbstractToolDescription tool = getTool(request);
        /*
         * Dispatch to the appropriate specialized command depending on the type
         * of the container element and the nature of the tool.
         */
        Command result = null;
        if (tool != null) {
            if (containerElement instanceof DDiagram) {
                DDiagram diagram = (DDiagram) containerElement;
                if (tool instanceof NodeCreationDescription) {
                    result = getCreateNodeOnDiagramCommand(request, (NodeCreationDescription) tool, diagram);
                } else if (tool instanceof ContainerCreationDescription) {
                    result = getCreateContainerOnDiagramCommand(request, (ContainerCreationDescription) tool, diagram);
                }
            } else if (containerElement instanceof DNodeContainer) {
                DNodeContainer nodeContainer = (DNodeContainer) containerElement;
                if (tool instanceof ContainerCreationDescription) {
                    result = getCreateContainerOnContainerCommand(request, (ContainerCreationDescription) tool, nodeContainer);
                }
            }
        }

        return result;
    }

    /**
     * Returns the command to create a new container in a container.
     * 
     * @param request
     *            the original request.
     * @param ccdTool
     *            the container creation tool description.
     * @param nodeContainer
     *            the container on which to create the new (bordered) node.
     * @return a command to create the new container.
     */
    protected Command getCreateContainerOnContainerCommand(CreateRequest request, ContainerCreationDescription ccdTool, DNodeContainer nodeContainer) {
        TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(nodeContainer);
        IDiagramCommandFactory diagramCommandFactory = getDiagramCommandFactory(transactionalEditingDomain);
        CreationUtil creationUtil = new CreationUtil(request, diagramCommandFactory, getRealLocation(request), getHost());
        return creationUtil.getContainerCreationDescription(nodeContainer, ccdTool);
    }

    /**
     * Returns the command to create a new container on a diagram.
     * 
     * @param request
     *            the original request.
     * @param ccdTool
     *            the node creation tool description.
     * @param diagram
     *            the diagram on which to create the new (bordered) node.
     * @return a command to create the new container.
     */
    protected Command getCreateContainerOnDiagramCommand(CreateRequest request, ContainerCreationDescription ccdTool, DDiagram diagram) {
        TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(diagram);
        IDiagramCommandFactory diagramCommandFactory = getDiagramCommandFactory(transactionalEditingDomain);
        CreationUtil creationUtil = new CreationUtil(request, diagramCommandFactory, getRealLocation(request), getHost());
        return creationUtil.getContainerCreationDescription(diagram, ccdTool);
    }

    /**
     * Returns the command to create a new node on a diagram.
     * 
     * @param request
     *            the original request.
     * @param ncdTool
     *            the node creation tool description.
     * @param diagram
     *            the diagram on which to create the new (bordered) node.
     * @return a command to create the new node.
     */
    protected Command getCreateNodeOnDiagramCommand(CreateRequest request, NodeCreationDescription ncdTool, DDiagram diagram) {
        TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(diagram);
        IDiagramCommandFactory diagramCommandFactory = getDiagramCommandFactory(transactionalEditingDomain);
        CreationUtil creationUtil = new CreationUtil(request, diagramCommandFactory, getRealLocation(request), getHost());
        return creationUtil.getNodeCreationCommand(diagram, ncdTool);
    }

    /**
     * Finds the Sirius tool description associated with the request, if any.
     * 
     * @param request
     *            the creation request.
     * @return the Sirius tool description associated with the request, if
     *         any.
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
     * information passed in the request.
     * 
     * @param request
     *            the creation request.
     * @return the real location where the element must be created.
     */
    protected Point getRealLocation(CreateRequest request) {
        Point location = request.getLocation().getCopy();
        if (location != null && getHost() instanceof GraphicalEditPart) {
            ((GraphicalEditPart) getHost()).getFigure().translateToRelative(location);
        }
        return location;
    }

    /**
     * Returns the emf command factory.
     * 
     * @param transactionalEditingDomain
     * 
     * @return the emf command factory.
     */
    private IDiagramCommandFactory getDiagramCommandFactory(TransactionalEditingDomain transactionalEditingDomain) {
        final DDiagramEditor diagramEditor = (DDiagramEditor) this.getHost().getViewer().getProperty(DDiagramEditor.EDITOR_ID);
        if (diagramEditor == null) {
            return null;
        }
        final Object adapter = diagramEditor.getAdapter(IDiagramCommandFactoryProvider.class);
        final IDiagramCommandFactoryProvider cmdFactoryProvider = (IDiagramCommandFactoryProvider) adapter;
        final IDiagramCommandFactory diagramCommandFactory = cmdFactoryProvider.getCommandFactory(transactionalEditingDomain);
        return diagramCommandFactory;
    }
}
