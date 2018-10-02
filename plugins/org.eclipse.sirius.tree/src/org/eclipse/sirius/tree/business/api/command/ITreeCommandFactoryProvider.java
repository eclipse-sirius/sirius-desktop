/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tree.business.api.command;

import org.eclipse.emf.transaction.TransactionalEditingDomain;

/**
 * This interface need to be implanted if you want to provide an
 * TableCommandFactory.
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public interface ITreeCommandFactoryProvider {

    /**
     * Get the command factory.
     * 
     * @param editingDomain
     *            the editing domain to use
     * @return the command factory
     */
    ITreeCommandFactory getCommandFactory(TransactionalEditingDomain editingDomain);

}
