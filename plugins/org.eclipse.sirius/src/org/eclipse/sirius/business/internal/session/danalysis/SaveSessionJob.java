/*******************************************************************************
 * Copyright (c) 2012, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.internal.session.danalysis;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.tools.api.SiriusPlugin;

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

    private Session session;

    /**
     * Default constructor.
     * 
     * @param session
     *            the {@link Session} to save
     */
    public SaveSessionJob(Session session) {
        super(Messages.SaveSessionJob_sessionSavingMsg);
        setSystem(true);
        this.session = session;
    }

    @Override
    public IStatus run(IProgressMonitor monitor) {
        IStatus result = Status.OK_STATUS;
        try {
            monitor.beginTask(Messages.SaveSessionJob_sessionSavingMsg, IProgressMonitor.UNKNOWN);
            if (session != null && session.isOpen() && SessionStatus.DIRTY == session.getStatus()) {
                result = performSave(monitor);
                monitor.worked(1);
            }
        } finally {
            monitor.done();
            // Set the session to null to avoid a leak. The job is potentially
            // kept by the ProgressManager.
            session = null;
        }
        return result;
    }

    /**
     * Perform the save operation.
     * 
     * Callers must ensure that session is not null, open and has a SessionStatus.DIRTY status before calling this
     * method.
     * 
     * WARNING : Be careful not to break default Sirius saving behavior when overriding this method.
     * 
     * @param monitor
     *            the progress monitor to associate to this operation.
     * @return resulting status of the run. The result must not be null
     */
    protected IStatus performSave(IProgressMonitor monitor) {
        if (session instanceof DAnalysisSessionImpl) {
            /*
             * We can never know when the job will be scheduled, and it might happen after the editing domain is
             * disposed. Even testing the state of the editing domain at the beginning of the save is not enough, as it
             * can be disposed by another thread during the save, in which case the commit() will throw an NPE and part
             * of the DASI.doSave() will not be executed, even if the files are actually saved.
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
        return Status.OK_STATUS;
    }

    @Override
    public boolean belongsTo(Object family) {
        return FAMILY.equals(family);
    }
}
