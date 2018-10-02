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
package org.eclipse.sirius.tools.api.ui;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * Interface used by the {@link RefreshEditorsPrecommitListener} to launch
 * additional command after the refresh.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public interface PostRefreshCommandFactory {
    /**
     * Compute the command to execute after the refresh of the
     * {@link RefreshEditorsPrecommitListener}.
     * 
     * @param transactionalEditingDomain
     *            the current editing domain.
     * @param refreshedRepresentations
     *            the currently refreshed representations.
     * 
     * @return A command to execute.
     */
    Command getPostCommandToExecute(TransactionalEditingDomain transactionalEditingDomain, Collection<DRepresentation> refreshedRepresentations);
}
