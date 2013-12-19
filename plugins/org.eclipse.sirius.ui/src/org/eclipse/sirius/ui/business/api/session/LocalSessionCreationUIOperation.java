/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.api.session;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionCreationOperation;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

/**
 * //TODOCBR comment this.
 * 
 * @author cbrun
 */
public class LocalSessionCreationUIOperation extends WorkspaceModifyOperation {

    private SessionCreationOperation op;

    /**
     * Constructor.
     * 
     * @param op
     *            {@link SessionCreationOperation}
     */
    public LocalSessionCreationUIOperation(final SessionCreationOperation op) {
        super(null);
        this.op = op;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void execute(final IProgressMonitor monitor) throws CoreException, InterruptedException {
        op.execute();
    }

    public Session getCreatedSession() {
        return op.getCreatedSession();
    }
}
