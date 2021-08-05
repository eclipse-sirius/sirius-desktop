/******************************************************************************
 * Copyright (c) 2006, 2021 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.sirius.tools.api.command;

import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.sirius.tools.api.Messages;

/**
 * An {@link IUndoContext} that tags an EMF operation with the editing domain that it affects. Two editing domain
 * contexts match if and only if they reference the same {@link EditingDomain} instance.
 * 
 * @author ldamus
 * @since 0.9.0
 */
public final class EditingDomainUndoContext implements IUndoContext {

    private final EditingDomain editingDomain;

    /**
     * Initializes me with the editing domain that I represent.
     * 
     * @param domain
     *            the editing domain
     */
    public EditingDomainUndoContext(final EditingDomain domain) {
        this.editingDomain = domain;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.commands.operations.IUndoContext#getLabel()
     */
    @Override
    public String getLabel() {
        return Messages.EditingDomainUndoContext_label;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.commands.operations.IUndoContext#matches(org.eclipse.core.commands.operations.IUndoContext)
     * 
     *      I match another <code>context</code> if it is a <code>EditingDomainUndoContext</code> representing the same
     *      editing domain as I.
     */
    @Override
    public boolean matches(final IUndoContext context) {
        return this.equals(context);
    }

    /**
     * {@inheritDoc} I am equal to other <code>EditingDomainUndoContext</code> on the same editing domain as mine.
     */
    @Override
    public boolean equals(final Object o) {
        boolean result = false;

        if (o instanceof EditingDomainUndoContext) {
            result = getEditingDomain() == ((EditingDomainUndoContext) o).getEditingDomain();
        }

        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return editingDomain == null ? 0 : editingDomain.hashCode();
    }

    /**
     * Obtains the editing domain.
     * 
     * @return my editing domain
     */
    public EditingDomain getEditingDomain() {
        return editingDomain;
    }
}
