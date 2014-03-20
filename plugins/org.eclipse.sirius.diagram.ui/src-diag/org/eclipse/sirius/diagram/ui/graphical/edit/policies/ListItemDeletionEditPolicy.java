/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IInsertableEditPart;
import org.eclipse.gmf.runtime.notation.View;

/**
 * This {@link org.eclipse.gef.EditPolicy} is used to handle the deletion of a
 * list item element. It delete the semantic element in the meantime.
 * 
 * @author mporhel
 */
public class ListItemDeletionEditPolicy extends NodeDeletionEditPolicy {

    /**
     * Build the edit policy.
     * 
     * @param editingDomain
     *            current {@link org.eclipse.emf.edit.domain.EditingDomain}.
     */
    public ListItemDeletionEditPolicy(final TransactionalEditingDomain editingDomain) {
        super(editingDomain);
    }

    /**
     * Returns the view element to be deleted.
     * 
     * @return the host's view element.
     * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.ListItemComponentEditPolicy#getView()
     */
    protected View getView() {
        return (View) getHost().getModel();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.ListItemComponentEditPolicy
     *      #getInsertableEditPart()
     */
    protected IInsertableEditPart getInsertableEditPart() {
        // get the container of the host list item
        EditPart container = getHost().getParent();
        if (container instanceof IInsertableEditPart) {
            return (IInsertableEditPart) container;
        }

        return null;
    }
}
