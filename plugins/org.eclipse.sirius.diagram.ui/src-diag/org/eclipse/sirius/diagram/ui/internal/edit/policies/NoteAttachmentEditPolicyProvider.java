/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.edit.policies;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.NoteAttachmentEditPart;

/**
 * This policy provider is used to override connection role edit policy of
 * {@link NoteAttachmentEditPart} in order to avoid creating the GMF
 * CrossReferenceAdapter.
 * 
 * @author <a href="mailto:mickael.lanoe@obeo.fr">Mickael LANOE</a>
 * 
 */
@SuppressWarnings("restriction")
public class NoteAttachmentEditPolicyProvider extends AbstractCreateEditPolicyProvider {

    @Override
    protected boolean isValidEditPart(EditPart editPart) {
        return editPart instanceof NoteAttachmentEditPart;
    }

    @Override
    public void createEditPolicies(EditPart editPart) {
        editPart.installEditPolicy(EditPolicy.CONNECTION_ROLE, new SiriusConnectionEditPolicy());
    }

}
