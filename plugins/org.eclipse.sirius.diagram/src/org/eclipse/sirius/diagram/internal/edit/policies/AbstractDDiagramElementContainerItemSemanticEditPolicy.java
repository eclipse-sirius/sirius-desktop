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
package org.eclipse.sirius.diagram.internal.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.graphical.edit.part.specific.BracketEdgeEditPart;
import org.eclipse.sirius.diagram.internal.edit.commands.DEdgeCreateCommand;
import org.eclipse.sirius.diagram.internal.edit.commands.DEdgeReorientCommand;
import org.eclipse.sirius.diagram.internal.edit.commands.DNode4CreateCommand;
import org.eclipse.sirius.diagram.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.diagram.internal.providers.SiriusElementTypes;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * Abstract policy to put common ex generated code for
 * DNodeContainerItemSemanticEditPolicy and DNodeListItemSemanticEditPolicy.
 * 
 * @was-generated
 */
public abstract class AbstractDDiagramElementContainerItemSemanticEditPolicy extends SiriusBaseItemSemanticEditPolicy {

    /**
     * @was-generated
     */
    protected Command getCreateCommand(CreateElementRequest req) {
        if (SiriusElementTypes.DNode_3012 == req.getElementType()) {
            if (req.getContainmentFeature() == null) {
                req.setContainmentFeature(ViewpointPackage.eINSTANCE.getAbstractDNode_OwnedBorderedNodes());
            }
            return getGEFWrapper(new DNode4CreateCommand(req));
        }
        return super.getCreateCommand(req);
    }

    /**
     * @was-generated
     */
    protected Command getDestroyElementCommand(DestroyElementRequest req) {
        CompoundCommand cc = getDestroyEdgesCommand();
        addDestroyChildNodesCommand(cc);
        addDestroyShortcutsCommand(cc);
        View view = (View) getHost().getModel();
        if (view.getEAnnotation("Shortcut") != null) { //$NON-NLS-1$
            req.setElementToDestroy(view);
        }
        cc.add(getGEFWrapper(new DestroyElementCommand(req)));
        return cc.unwrap();
    }

    /**
     * @was-generated
     */
    protected abstract void addDestroyChildNodesCommand(CompoundCommand cmd);

    /**
     * @was-generated
     */
    protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
        Command command = req.getTarget() == null ? getStartCreateRelationshipCommand(req) : getCompleteCreateRelationshipCommand(req);
        return command != null ? command : super.getCreateRelationshipCommand(req);
    }

    /**
     * @was-generated
     */
    protected Command getStartCreateRelationshipCommand(CreateRelationshipRequest req) {
        if (SiriusElementTypes.DEdge_4001 == req.getElementType()) {
            return getGEFWrapper(new DEdgeCreateCommand(req, req.getSource(), req.getTarget()));
        }
        return null;
    }

    /**
     * @was-generated
     */
    protected Command getCompleteCreateRelationshipCommand(CreateRelationshipRequest req) {
        if (SiriusElementTypes.DEdge_4001 == req.getElementType()) {
            return getGEFWrapper(new DEdgeCreateCommand(req, req.getSource(), req.getTarget()));
        }
        return null;
    }

    /**
     * Returns command to reorient EClass based link. New link target or source
     * should be the domain model element associated with this node.
     * 
     * @was-generated
     */
    protected Command getReorientRelationshipCommand(ReorientRelationshipRequest req) {
        switch (getVisualID(req)) {
        case DEdgeEditPart.VISUAL_ID:
        case BracketEdgeEditPart.VISUAL_ID:
            return getGEFWrapper(new DEdgeReorientCommand(req));
        }
        return super.getReorientRelationshipCommand(req);
    }

}
