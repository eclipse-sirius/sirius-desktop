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

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.ui.internal.edit.commands.DNode3CreateCommand;
import org.eclipse.sirius.diagram.ui.internal.edit.commands.DNodeContainer2CreateCommand;
import org.eclipse.sirius.diagram.ui.internal.edit.commands.DNodeList2CreateCommand;
import org.eclipse.sirius.diagram.ui.internal.providers.SiriusElementTypes;

/**
 * @was-generated
 */
public class DNodeContainerViewNodeContainerCompartmentItemSemanticEditPolicy extends SiriusBaseItemSemanticEditPolicy {

    /**
     * @was-generated
     */
    protected Command getCreateCommand(CreateElementRequest req) {
        if (SiriusElementTypes.DNode_3007 == req.getElementType()) {
            if (req.getContainmentFeature() == null) {
                req.setContainmentFeature(DiagramPackage.eINSTANCE.getDNodeContainer_OwnedDiagramElements());
            }
            return getGEFWrapper(new DNode3CreateCommand(req));
        }
        if (SiriusElementTypes.DNodeContainer_3008 == req.getElementType()) {
            if (req.getContainmentFeature() == null) {
                req.setContainmentFeature(DiagramPackage.eINSTANCE.getDNodeContainer_OwnedDiagramElements());
            }
            return getGEFWrapper(new DNodeContainer2CreateCommand(req));
        }
        if (SiriusElementTypes.DNodeList_3009 == req.getElementType()) {
            if (req.getContainmentFeature() == null) {
                req.setContainmentFeature(DiagramPackage.eINSTANCE.getDNodeContainer_OwnedDiagramElements());
            }
            return getGEFWrapper(new DNodeList2CreateCommand(req));
        }
        return super.getCreateCommand(req);
    }

}
