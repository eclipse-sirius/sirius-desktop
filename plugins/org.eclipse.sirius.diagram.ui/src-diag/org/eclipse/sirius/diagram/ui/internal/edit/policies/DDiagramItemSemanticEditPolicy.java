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
package org.eclipse.sirius.diagram.ui.internal.edit.policies;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.ui.internal.edit.commands.DNodeContainerCreateCommand;
import org.eclipse.sirius.diagram.ui.internal.edit.commands.DNodeCreateCommand;
import org.eclipse.sirius.diagram.ui.internal.edit.commands.DNodeListCreateCommand;
import org.eclipse.sirius.diagram.ui.internal.providers.SiriusElementTypes;

/**
 * @was-generated
 */
public class DDiagramItemSemanticEditPolicy extends SiriusBaseItemSemanticEditPolicy {

    /**
     * @was-generated
     */
    protected Command getCreateCommand(CreateElementRequest req) {
        if (SiriusElementTypes.DNode_2001 == req.getElementType()) {
            if (req.getContainmentFeature() == null) {
                req.setContainmentFeature(DiagramPackage.eINSTANCE.getDDiagram_OwnedDiagramElements());
            }
            return getGEFWrapper(new DNodeCreateCommand(req));
        }
        if (SiriusElementTypes.DNodeContainer_2002 == req.getElementType()) {
            if (req.getContainmentFeature() == null) {
                req.setContainmentFeature(DiagramPackage.eINSTANCE.getDDiagram_OwnedDiagramElements());
            }
            return getGEFWrapper(new DNodeContainerCreateCommand(req));
        }
        if (SiriusElementTypes.DNodeList_2003 == req.getElementType()) {
            if (req.getContainmentFeature() == null) {
                req.setContainmentFeature(DiagramPackage.eINSTANCE.getDDiagram_OwnedDiagramElements());
            }
            return getGEFWrapper(new DNodeListCreateCommand(req));
        }
        return super.getCreateCommand(req);
    }

    /**
     * @was-generated
     */
    protected Command getDuplicateCommand(DuplicateElementsRequest req) {
        TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();
        return getGEFWrapper(new DuplicateAnythingCommand(editingDomain, req));
    }

    /**
     * @was-generated
     */
    private static class DuplicateAnythingCommand extends DuplicateEObjectsCommand {

        /**
         * @was-generated
         */
        public DuplicateAnythingCommand(TransactionalEditingDomain editingDomain, DuplicateElementsRequest req) {
            super(editingDomain, req.getLabel(), req.getElementsToBeDuplicated(), req.getAllDuplicatedElementsMap());
        }

    }

}
