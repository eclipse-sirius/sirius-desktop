/*******************************************************************************
 * Copyright (c) 2009, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.api.session;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.session.factory.SessionFactory;
import org.eclipse.sirius.tools.api.Messages;

/**
 * A common operation to create a session and open it.
 * 
 * @author edugueperoux
 * 
 * @since 0.9.0
 */
public class DefaultLocalSessionCreationOperation implements SessionCreationOperation {

    /**
     * The URI of the Session's Resource.
     */
    protected URI sessionResourceURI;

    /**
     * The created Session.
     */
    protected Session session;

    private IProgressMonitor monitor;

    /**
     * Constructor.
     * 
     * @param sessionResourceURI
     *            the {@link URI} of the Resource {@link Session} model
     * @param monitor
     *            {@link IProgressMonitor} to show progression of {@link Session} creation
     */
    public DefaultLocalSessionCreationOperation(URI sessionResourceURI, IProgressMonitor monitor) {
        this.sessionResourceURI = sessionResourceURI;
        this.monitor = monitor;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.session.SessionCreationOperation#execute()
     */
    @Override
    public void execute() throws CoreException {
        if (monitor == null) {
            monitor = new NullProgressMonitor();
        }
        try {
            monitor.beginTask(Messages.DefaultLocalSessionCreationOperation_createResoureMsg, 3);
            monitor.subTask(Messages.DefaultLocalSessionCreationOperation_createSessionMsg);
            session = SessionFactory.INSTANCE.createSession(sessionResourceURI, new SubProgressMonitor(monitor, 1));
            monitor.subTask(Messages.DefaultLocalSessionCreationOperation_sessionOpenMsg);
            session.open(new SubProgressMonitor(monitor, 1));
            monitor.subTask(Messages.DAnalysisSessionImpl_saveMsg);
            session.save(new SubProgressMonitor(monitor, 1));
        } finally {
            monitor.done();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.session.SessionCreationOperation#getCreatedSession()
     */
    @Override
    public Session getCreatedSession() {
        return session;
    }

}
