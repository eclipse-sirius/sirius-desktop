/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.internal.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;

import org.eclipse.sirius.DDiagram;
import org.eclipse.sirius.DEdge;
import org.eclipse.sirius.EdgeTarget;
import org.eclipse.sirius.diagram.internal.edit.policies.SiriusBaseItemSemanticEditPolicy;

/**
 * @was-generated
 */
public class DEdgeReorientCommand extends EditElementCommand {

    /**
     * @was-generated
     */
    private final int reorientDirection;

    /**
     * @was-generated
     */
    private final EObject oldEnd;

    /**
     * @was-generated
     */
    private final EObject newEnd;

    /**
     * @was-generated
     */
    public DEdgeReorientCommand(ReorientRelationshipRequest request) {
        super(request.getLabel(), request.getRelationship(), request);
        reorientDirection = request.getDirection();
        oldEnd = request.getOldRelationshipEnd();
        newEnd = request.getNewRelationshipEnd();
    }

    /**
     * @was-generated
     */
    public boolean canExecute() {
        if (!(getElementToEdit() instanceof DEdge)) {
            return false;
        }
        if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
            return canReorientSource();
        }
        if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
            return canReorientTarget();
        }
        return false;
    }

    /**
     * @was-generated
     */
    protected boolean canReorientSource() {
        if (!(oldEnd instanceof EdgeTarget && newEnd instanceof EdgeTarget)) {
            return false;
        }
        EdgeTarget target = getLink().getTargetNode();
        if (!(getLink().eContainer() instanceof DDiagram)) {
            return false;
        }
        DDiagram container = (DDiagram) getLink().eContainer();
        return SiriusBaseItemSemanticEditPolicy.LinkConstraints.canExistDEdge_4001(container, getNewSource(), target);
    }

    /**
     * @was-generated
     */
    protected boolean canReorientTarget() {
        if (!(oldEnd instanceof EdgeTarget && newEnd instanceof EdgeTarget)) {
            return false;
        }
        EdgeTarget source = getLink().getSourceNode();
        if (!(getLink().eContainer() instanceof DDiagram)) {
            return false;
        }
        DDiagram container = (DDiagram) getLink().eContainer();
        return SiriusBaseItemSemanticEditPolicy.LinkConstraints.canExistDEdge_4001(container, source, getNewTarget());
    }

    /**
     * @was-generated
     */
    protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
        if (!canExecute()) {
            throw new ExecutionException("Invalid arguments in reorient link command"); //$NON-NLS-1$
        }
        if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
            return reorientSource();
        }
        if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
            return reorientTarget();
        }
        throw new IllegalStateException();
    }

    /**
     * @was-generated
     */
    protected CommandResult reorientSource() throws ExecutionException {
        getLink().setSourceNode(getNewSource());
        return CommandResult.newOKCommandResult(getLink());
    }

    /**
     * @was-generated
     */
    protected CommandResult reorientTarget() throws ExecutionException {
        getLink().setTargetNode(getNewTarget());
        return CommandResult.newOKCommandResult(getLink());
    }

    /**
     * @was-generated
     */
    protected DEdge getLink() {
        return (DEdge) getElementToEdit();
    }

    /**
     * @was-generated
     */
    protected EdgeTarget getOldSource() {
        return (EdgeTarget) oldEnd;
    }

    /**
     * @was-generated
     */
    protected EdgeTarget getNewSource() {
        return (EdgeTarget) newEnd;
    }

    /**
     * @was-generated
     */
    protected EdgeTarget getOldTarget() {
        return (EdgeTarget) oldEnd;
    }

    /**
     * @was-generated
     */
    protected EdgeTarget getNewTarget() {
        return (EdgeTarget) newEnd;
    }
}
