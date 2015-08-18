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
    public static final String FAMILY = SiriusPlugin.ID + ".saveSessionJob"; //$NON-NLS-1$

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

    @Override
    public IStatus run(IProgressMonitor monitor) {
        try {
            monitor.beginTask(ACTION_NAME, IProgressMonitor.UNKNOWN);
            if (session != null && session.isOpen() && SessionStatus.DIRTY == session.getStatus()) {
                if (session instanceof DAnalysisSessionImpl) {
                    /*
                     * We can never know when the job will be scheduled, and it
                     * might happen after the editing domain is disposed. Even
                     * testing the state of the editing domain at the beginning
                     * of the save is not enough, as it can be disposed by
                     * another thread during the save, in which case the
                     * commit() will throw an NPE and part of the DASI.doSave()
                     * will not be executed, even if the files are actually
                     * saved.
                     */
                    boolean exclusiveSetting = ((DAnalysisSessionImpl) session).isSaveInExclusiveTransaction();
                    ((DAnalysisSessionImpl) session).setSaveInExclusiveTransaction(false);
                    try {
                        session.save(monitor);
                    } finally {
                        ((DAnalysisSessionImpl) session).setSaveInExclusiveTransaction(exclusiveSetting);
                    }
                } else {
                    session.save(monitor);
                }
                monitor.worked(1);
            }
        } finally {
            monitor.done();
            // Set the session to null to avoid a leak. The job is potentially
            // kept by the ProgressManager.
            session = null;
        }
        return Status.OK_STATUS;
    }

    @Override
    public boolean belongsTo(Object family) {
        return FAMILY.equals(family);
    }
}
