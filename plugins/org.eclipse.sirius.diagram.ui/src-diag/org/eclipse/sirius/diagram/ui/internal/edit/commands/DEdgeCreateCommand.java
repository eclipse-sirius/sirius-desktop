/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.ui.internal.edit.policies.SiriusBaseItemSemanticEditPolicy;
import org.eclipse.sirius.diagram.ui.provider.Messages;

/**
 * @was-generated
 */
public class DEdgeCreateCommand extends CreateElementCommand {

    /**
     * @was-generated
     */
    private final EObject source;

    /**
     * @was-generated
     */
    private final EObject target;

    /**
     * @was-generated
     */
    private DDiagram container;

    /**
     * @was-generated
     */
    public DEdgeCreateCommand(CreateRelationshipRequest request, EObject source, EObject target) {
        super(request);
        this.source = source;
        this.target = target;
        if (request.getContainmentFeature() == null) {
            setContainmentFeature(DiagramPackage.eINSTANCE.getDDiagram_OwnedDiagramElements());
        }

        // Find container element for the new link.
        // Climb up by containment hierarchy starting from the source
        // and return the first element that is instance of the container class.
        for (EObject element = source; element != null; element = element.eContainer()) {
            if (element instanceof DDiagram) {
                container = (DDiagram) element;
                super.setElementToEdit(container);
                break;
            }
        }
    }

    /**
     * @was-generated
     */
    @Override
    public boolean canExecute() {
        if (source == null && target == null) {
            return false;
        }
        if (source != null && !(source instanceof EdgeTarget)) {
            return false;
        }
        if (target != null && !(target instanceof EdgeTarget)) {
            return false;
        }
        if (getSource() == null) {
            return true; // link creation is in progress; source is not defined
                         // yet
        }
        // target may be null here but it's possible to check constraint
        if (getContainer() == null) {
            return false;
        }
        return SiriusBaseItemSemanticEditPolicy.LinkConstraints.canCreateDEdge_4001(getContainer(), getSource(), getTarget());
    }

    /**
     * @was-generated
     */
    @Override
    protected EObject doDefaultElementCreation() {
        // org.eclipse.sirius.DEdge newElement = (org.eclipse.sirius.DEdge)
        // super.doDefaultElementCreation();
        DEdge newElement = DiagramFactory.eINSTANCE.createDEdge();
        getContainer().getOwnedDiagramElements().add(newElement);
        newElement.setSourceNode(getSource());
        newElement.setTargetNode(getTarget());
        return newElement;
    }

    /**
     * @was-generated
     */
    @Override
    protected EClass getEClassToEdit() {
        return DiagramPackage.eINSTANCE.getDDiagram();
    }

    /**
     * @was-generated
     */
    @Override
    protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
        if (!canExecute()) {
            throw new ExecutionException(Messages.DEdgeCreateCommand_executionErrorMsg);
        }
        return super.doExecuteWithResult(monitor, info);
    }

    /**
     * @was-generated
     */
    @Override
    protected ConfigureRequest createConfigureRequest() {
        ConfigureRequest request = super.createConfigureRequest();
        request.setParameter(CreateRelationshipRequest.SOURCE, getSource());
        request.setParameter(CreateRelationshipRequest.TARGET, getTarget());
        return request;
    }

    /**
     * @was-generated
     */
    @Override
    protected void setElementToEdit(EObject element) {
        throw new UnsupportedOperationException();
    }

    /**
     * @was-generated
     */
    protected EdgeTarget getSource() {
        return (EdgeTarget) source;
    }

    /**
     * @was-generated
     */
    protected EdgeTarget getTarget() {
        return (EdgeTarget) target;
    }

    /**
     * @was-generated
     */
    public DDiagram getContainer() {
        return container;
    }
}
