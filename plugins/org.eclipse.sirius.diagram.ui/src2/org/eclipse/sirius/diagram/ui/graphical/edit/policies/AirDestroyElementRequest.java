/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
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
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

/**
 * Specific request to handle delete from diagram versus delete from model
 * stuffs.
 * 
 * @author cbrun
 * 
 */
public class AirDestroyElementRequest extends DestroyElementRequest {

    private boolean destroySemantic = true;

    /**
     * Create a new specific request.
     * 
     * @param editingDomain
     *            the editing domain
     * @param confirmationRequired
     *            <code>true</code> if the user should be prompted to confirm
     *            the element deletion, <code>false</code> otherwise
     * @param destroySemanticElement
     *            <code>true</code> ifthe semantic element should be destroy,
     *            <code>false</code> othewise.
     */
    public AirDestroyElementRequest(final TransactionalEditingDomain editingDomain, final boolean confirmationRequired, final boolean destroySemanticElement) {
        super(editingDomain, confirmationRequired);
        destroySemantic = destroySemanticElement;
    }

    /**
     * Check if the semantic element should be destroyed.
     * 
     * @return <code>true</code> if the semantic element should be destroyed,
     *         <code>false</code> otherwise.
     */
    public boolean shouldDestroySemantic() {
        return destroySemantic;
    }

}
