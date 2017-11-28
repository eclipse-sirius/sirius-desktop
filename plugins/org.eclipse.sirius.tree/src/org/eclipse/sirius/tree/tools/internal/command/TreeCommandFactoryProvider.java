/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.tools.internal.command;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.tree.business.api.command.ITreeCommandFactory;
import org.eclipse.sirius.tree.business.api.command.ITreeCommandFactoryProvider;

/**
 * This class is the default provider for the TableCommandFactory instance.
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public class TreeCommandFactoryProvider implements ITreeCommandFactoryProvider {

    /** the table command factory */
    private ITreeCommandFactory commandFactory;

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.IDiagramCommandFactoryProvider.api.command.IEMFCommandFactoryProvider#getCommandFactory(org.eclipse.emf.transaction.TransactionalEditingDomain)
     */
    public ITreeCommandFactory getCommandFactory(final TransactionalEditingDomain editingDomain) {
        if (commandFactory == null) {
            commandFactory = new TreeCommandFactory(editingDomain);
        }
        return commandFactory;
    }
}
