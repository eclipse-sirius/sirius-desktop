/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.internal.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.ui.internal.edit.commands.DNodeListElementCreateCommand;
import org.eclipse.sirius.diagram.ui.internal.providers.SiriusElementTypes;

/**
 * @was-generated
 */
public class DNodeListViewNodeListCompartmentItemSemanticEditPolicy extends SiriusBaseItemSemanticEditPolicy {

    /**
     * @was-generated
     */
    protected Command getCreateCommand(CreateElementRequest req) {
        if (SiriusElementTypes.DNodeListElement_3010 == req.getElementType()) {
            if (req.getContainmentFeature() == null) {
                req.setContainmentFeature(DiagramPackage.eINSTANCE.getDNodeList_OwnedElements());
            }
            return getGEFWrapper(new DNodeListElementCreateCommand(req));
        }
        return super.getCreateCommand(req);
    }

}
