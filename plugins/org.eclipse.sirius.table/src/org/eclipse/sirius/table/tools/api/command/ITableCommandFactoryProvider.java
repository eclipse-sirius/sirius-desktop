/*******************************************************************************
 * Copyright (c) 2007 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.table.tools.api.command;

import org.eclipse.emf.transaction.TransactionalEditingDomain;

/**
 * This interface need to be implented if you want to provide an
 * TableCommandFactory.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public interface ITableCommandFactoryProvider {

    /**
     * Get the command factory.
     * 
     * @param editingDomain
     *            the editing domain to use
     * @return the command factory
     */
    ITableCommandFactory getCommandFactory(TransactionalEditingDomain editingDomain);

}
