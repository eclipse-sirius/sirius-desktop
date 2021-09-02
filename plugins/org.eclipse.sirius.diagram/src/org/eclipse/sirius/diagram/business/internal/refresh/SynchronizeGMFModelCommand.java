/*******************************************************************************
 * Copyright (c) 2011, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.business.internal.refresh;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.diagram.business.api.refresh.CanonicalSynchronizer;
import org.eclipse.sirius.diagram.tools.api.Messages;

/**
 * EMF Command to execute the {@link CanonicalSynchronizer}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class SynchronizeGMFModelCommand extends RecordingCommand {

    private CanonicalSynchronizer canonicalSynchronizer;

    /**
     * Default constructor.
     * 
     * @param domain
     *            the {@link TransactionalEditingDomain} on which to execute this command
     *            Messages.SynchronizeGMFModelCommand_label
     * @param canonicalSynchronizer
     *            the {@link CanonicalSynchronizer} to execute
     */
    public SynchronizeGMFModelCommand(TransactionalEditingDomain domain, CanonicalSynchronizer canonicalSynchronizer) {
        super(domain, Messages.SynchronizeGMFModelCommand_label);
        this.canonicalSynchronizer = canonicalSynchronizer;
    }

    @Override
    protected void doExecute() {
        canonicalSynchronizer.synchronize();
    }
}
