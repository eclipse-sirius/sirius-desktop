/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.table.tools.internal.command;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactoryProvider;

/**
 * This class is the default provider for the TableCommandFactory instance.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class TableCommandFactoryProvider implements ITableCommandFactoryProvider {

    /** the table command factory */
    private ITableCommandFactory commandFactory;

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.IDiagramCommandFactoryProvider.api.command.IEMFCommandFactoryProvider#getCommandFactory(org.eclipse.emf.transaction.TransactionalEditingDomain)
     */
    public ITableCommandFactory getCommandFactory(final TransactionalEditingDomain editingDomain) {
        if (commandFactory == null) {
            commandFactory = new TableCommandFactory(editingDomain);
        }
        return commandFactory;
    }

}
