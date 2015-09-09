/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.actions.session;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ui.tools.internal.commands.CloseUISessionCommand;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.SelectionListenerAction;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * Action to close a list of sessions.
 * 
 * @author mporhel
 * 
 */
public class CloseSessionsAction extends SelectionListenerAction {

    private Collection<URI> sessionsToCloseURI = Sets.newLinkedHashSet();

    /**
     * Constructor.
     * 
     * @param text
     *            the action text.
     */
    public CloseSessionsAction(String text) {
        super(text);
    }

    /**
     * Looks for selected sessions to close.
     * 
     * {@inheritDoc}
     */
    @Override
    protected boolean updateSelection(IStructuredSelection selection) {
        if (selection != null) {
            for (Session concernedSession : Iterables.filter(selection.toList(), Session.class)) {
                sessionsToCloseURI.add(concernedSession.getSessionResource().getURI());
            }
            // This is a workaround to avoid the session to close to be stored
            // in the
            // selection field by the super class (BaseSelectionListener) and
            // hence create a memory leak
            if (!selection.isEmpty()) {
                super.selectionChanged(new StructuredSelection());
            }
        }

        return !sessionsToCloseURI.isEmpty();
    }

    @Override
    public void run() {
        super.run();

        for (final URI sessionToCloseURI : sessionsToCloseURI) {
            Session session = SessionManager.INSTANCE.getExistingSession(sessionToCloseURI);
            if (session != null) {
                internalClose(session);
            }
        }
        sessionsToCloseURI.clear();
    }

    private void internalClose(final Session session) {
        CloseUISessionCommand operation = new CloseUISessionCommand(session);
        try {
            new ProgressMonitorDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell()).run(true, false, operation);
        } catch (InvocationTargetException e) {
            SiriusEditPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, SiriusEditPlugin.ID, MessageFormat.format(Messages.CloseSessionsAction_error, e), e));
        } catch (InterruptedException e) {
            SiriusEditPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, SiriusEditPlugin.ID, MessageFormat.format(Messages.CloseSessionsAction_error, e), e));
        }
        if (session.isOpen()) {
            session.close(new NullProgressMonitor());
        }
    }
}
