/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.internal.session;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.WorkspaceEditingDomainFactory;
import org.eclipse.sirius.common.tools.api.editing.FileStatusPrecommitListener;
import org.eclipse.sirius.common.tools.api.editing.IEditingDomainFactory;

/**
 * Default {@link IEditingDomainFactory} used in Session to support undo/redo.
 * 
 * @author edugueperoux
 */
public class LocalSessionEditingDomainFactory extends WorkspaceEditingDomainFactory implements IEditingDomainFactory {

    /**
     * Default constructor.
     */
    public LocalSessionEditingDomainFactory() {
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.workspace.WorkspaceEditingDomainFactory#createEditingDomain()
     */
    @Override
    public TransactionalEditingDomain createEditingDomain() {
        TransactionalEditingDomain result = super.createEditingDomain();
        result.addResourceSetListener(new FileStatusPrecommitListener());
        return result;
    }

}
