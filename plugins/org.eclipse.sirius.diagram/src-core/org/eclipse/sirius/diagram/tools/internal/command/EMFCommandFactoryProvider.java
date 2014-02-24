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
package org.eclipse.sirius.diagram.tools.internal.command;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactoryProvider;

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
     * @see org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactoryProvider#getCommandFactory(org.eclipse.emf.transaction.TransactionalEditingDomain)
     */
    public IDiagramCommandFactory getCommandFactory(final TransactionalEditingDomain editingDomain) {
        if (commandFactory == null) {
            commandFactory = new UndoRedoCapableEMFCommandFactory(editingDomain);
        }
        return commandFactory;
    }
}
