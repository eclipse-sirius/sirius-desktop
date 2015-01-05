/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.internal.session;

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IJobRunnable;

/**
 * A {@link IJobRunnable} to save a {@link Session session}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class SaveSessionRunnable implements IJobRunnable {

    /** The {@link Session} to save.*/
    protected Session session;

    /**
     * Default constructor.
     * 
     * @param session
     *            the Session to save
     */
    public SaveSessionRunnable(Session session) {
        this.session = session;
    }

    @Override
    public IStatus run(IProgressMonitor monitor) {
        if (session != null) {
            IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(session);
            Collection<DialectEditor> editors = uiSession.getEditors();
            if (editors.isEmpty()) {
                session.save(monitor);
            } else {
                IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
                if (activeWorkbenchWindow != null) {
                    IEditorPart activeEditor = activeWorkbenchWindow.getActivePage().getActiveEditor();
                    if (editors.contains(activeEditor)) {
                        activeEditor.doSave(monitor);
                    }
                } else {
                    editors.iterator().next().doSave(monitor);
                }
            }
        }
        return Status.OK_STATUS;
    }

}
