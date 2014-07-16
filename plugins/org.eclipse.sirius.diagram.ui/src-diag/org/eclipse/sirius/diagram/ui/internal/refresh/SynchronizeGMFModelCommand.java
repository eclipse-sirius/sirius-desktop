/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.refresh;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.diagram.business.api.refresh.CanonicalSynchronizer;

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
     *            the {@link TransactionalEditingDomain} on which to execute
     *            this command
     * 
     * @param canonicalSynchronizer
     *            the {@link CanonicalSynchronizer} to execute
     */
    public SynchronizeGMFModelCommand(TransactionalEditingDomain domain, CanonicalSynchronizer canonicalSynchronizer) {
        super(domain, "Synchronize GMF Notation Model");
        this.canonicalSynchronizer = canonicalSynchronizer;
    }

    @Override
    protected void doExecute() {
        canonicalSynchronizer.synchronize();
    }
}
