/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tools.internal.command;

import org.eclipse.emf.transaction.TransactionalEditingDomain;

import org.eclipse.sirius.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;

/**
 * This class is the default provider for a EMFCommandFactory instance.
 * 
 * @author mchauvin
 */
public class EMFCommandFactoryProvider implements IDiagramCommandFactoryProvider {

    /** the EMF command factory */
    private IDiagramCommandFactory commandFactory;

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.IDiagramCommandFactoryProvider#getCommandFactory(org.eclipse.emf.transaction.TransactionalEditingDomain)
     */
    public IDiagramCommandFactory getCommandFactory(final TransactionalEditingDomain editingDomain) {
        if (commandFactory == null) {
            commandFactory = new UndoRedoCapableEMFCommandFactory(editingDomain);
        }
        return commandFactory;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.IDiagramCommandFactoryProvider#getCommandFactory(org.eclipse.emf.transaction.TransactionalEditingDomain)
     * 
     * @deprecated since 4.0.0 use getCommandFactory(TransactionalEditingDomain)
     *             instead
     */
    @Deprecated
    public IDiagramCommandFactory getCommandFactory(final ModelAccessor modelAccessor, final TransactionalEditingDomain editingDomain) {
        if (commandFactory == null) {
            commandFactory = new UndoRedoCapableEMFCommandFactory(modelAccessor, editingDomain);
        }
        return commandFactory;
    }

}
