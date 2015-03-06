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
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.core.service.IProviderChangeListener;
import org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.CreateEditPoliciesOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.IEditPolicyProvider;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;

/**
 * Abstract policy provider used after the creation of an edit part.
 * 
 * @author <a href="mailto:mickael.lanoe@obeo.fr">Mickael LANOE</a>
 * 
 */
public abstract class AbstractCreateEditPolicyProvider implements IEditPolicyProvider {

    /**
     * Check that the edit part is valid for the provider.
     * 
     * @param editPart
     *            edit part to check
     * @return true if the edit part is valid
     */
    protected abstract boolean isValidEditPart(EditPart editPart);

    @Override
    public boolean provides(IOperation operation) {
        if (operation instanceof CreateEditPoliciesOperation) {
            EditPart editPart = ((CreateEditPoliciesOperation) operation).getEditPart();
            Object model = editPart.getModel();
            if (model instanceof View && isValidEditPart(editPart)) {
                View view = (View) model;
                if (DDiagramEditPart.MODEL_ID.equals(SiriusVisualIDRegistry.getModelID(view))) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void addProviderChangeListener(IProviderChangeListener listener) {
    }

    @Override
    public void removeProviderChangeListener(IProviderChangeListener listener) {
    }
}
