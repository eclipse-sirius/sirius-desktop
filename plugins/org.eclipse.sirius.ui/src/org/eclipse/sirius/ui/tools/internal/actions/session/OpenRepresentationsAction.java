/*******************************************************************************
 * Copyright (c) 2011, 2016 THALES GLOBAL SERVICES and others.
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
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * Action to open the given representations and init/update the corresponding ui
 * session(s).
 *
 * @author mporhel
 */
public class OpenRepresentationsAction extends Action {

    private Collection<DRepresentationDescriptor> representationsToOpen;

    /**
     * Constructor.
     *
     * @param repDesriptors
     *            representations to open.
     */
    public OpenRepresentationsAction(Collection<DRepresentationDescriptor> repDesriptors) {
        super(Messages.OpenRepresentationsAction_name);

        if (repDesriptors != null) {
            this.representationsToOpen = Sets.newLinkedHashSet(Iterables.filter(repDesriptors, Predicates.notNull()));
        }
    }

    /**
     * Constructor.
     *
     * @param repDesc
     *            representation descriptor to open.
     */
    public OpenRepresentationsAction(DRepresentationDescriptor repDesc) {
        this(Collections.singletonList(repDesc));
    }

    @Override
    public void run() {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

        try {
            IRunnableContext context = new ProgressMonitorDialog(shell);
            IRunnableWithProgress runnable = new IRunnableWithProgress() {
                @Override
                public void run(final IProgressMonitor pm) {
                    openRepresentations(representationsToOpen, pm);
                }
            };
            PlatformUI.getWorkbench().getProgressService().runInUI(context, runnable, null);
        } catch (final InvocationTargetException e) {
            if (e.getCause() instanceof RuntimeException) {
                throw (RuntimeException) e.getCause();
            }
            throw new RuntimeException(e);
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void openRepresentations(final Collection<DRepresentationDescriptor> selection, final IProgressMonitor monitor) {
        String taskName = Messages.OpenRepresentationsAction_openRepresentationsTask;
        if (selection.size() > 1) {
            taskName = Messages.OpenRepresentationsAction_openRepresentationsTask;
        }
        try {
            monitor.beginTask(taskName, 5 * selection.size());

            for (DRepresentationDescriptor repDesc : selection) {
                Session session = new EObjectQuery(repDesc.getTarget()).getSession();
                monitor.worked(1);

                if (session != null) {
                    IEditorPart part = DialectUIManager.INSTANCE.openEditor(session, repDesc.getRepresentation(), new SubProgressMonitor(monitor, 3));
                    if (part instanceof DialectEditor) {
                        updateUISession((DialectEditor) part, session);
                        monitor.worked(1);
                    }
                }
            }
        } finally {
            monitor.done();
        }
    }

    private void updateUISession(final DialectEditor part, final Session session) {
        IEditingSession uiSession = SessionUIManager.INSTANCE.getOrCreateUISession(session);
        uiSession.open();
        uiSession.attachEditor(part);
    }

}
