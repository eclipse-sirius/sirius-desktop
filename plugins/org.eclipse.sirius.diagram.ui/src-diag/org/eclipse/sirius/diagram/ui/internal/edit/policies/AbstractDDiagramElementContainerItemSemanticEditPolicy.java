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
package org.eclipse.sirius.diagram.ui.internal.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.ui.internal.edit.commands.DEdgeCreateCommand;
import org.eclipse.sirius.diagram.ui.internal.edit.commands.DNode4CreateCommand;
import org.eclipse.sirius.diagram.ui.internal.providers.SiriusElementTypes;

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
    @Override
    protected Command getCreateCommand(CreateElementRequest req) {
        if (SiriusElementTypes.DNode_3012 == req.getElementType()) {
            if (req.getContainmentFeature() == null) {
                req.setContainmentFeature(DiagramPackage.eINSTANCE.getAbstractDNode_OwnedBorderedNodes());
            }
            return getGEFWrapper(new DNode4CreateCommand(req));
        }
        return super.getCreateCommand(req);
    }

    /**
     * @was-generated
     */
    @Override
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
    @Override
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

}
