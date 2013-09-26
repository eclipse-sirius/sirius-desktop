/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.session.danalysis;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * A {@link Job} to save a {@link Session}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class SaveSessionJob extends Job {

    /**
     * The family id for this kind of job.
     */
    public static final String FAMILY = SiriusPlugin.ID + ".saveSessionJob";

    private static final String ACTION_NAME = "Session saving";

    private Session session;

    /**
     * Default constructor.
     * 
     * @param session
     *            the {@link Session} to save
     */
    public SaveSessionJob(Session session) {
        super(ACTION_NAME);
        this.session = session;
    }

    /**
     * Overridden to save the {@link Session}.
     * 
     * {@inheritDoc}
     */
    @Override
    public IStatus run(IProgressMonitor monitor) {
        try {
            monitor.beginTask(ACTION_NAME, IProgressMonitor.UNKNOWN);
            if (session.isOpen() && SessionStatus.DIRTY == session.getStatus()) {
                session.save(monitor);
                monitor.worked(1);
            }
        } finally {
            monitor.done();
        }
        return Status.OK_STATUS;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.runtime.jobs.Job#belongsTo(java.lang.Object)
     */
    @Override
    public boolean belongsTo(Object family) {
        return FAMILY.equals(family);
    }

}
