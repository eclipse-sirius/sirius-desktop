/*******************************************************************************
 * Copyright (c) 2011, 2017 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ui.tools.internal.actions.session;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * Action to open the given representations and init/update the corresponding ui session(s).
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
        try {
            IRunnableWithProgress runnable = new IRunnableWithProgress() {
                @Override
                public void run(final IProgressMonitor pm) {
                    openRepresentations(pm);
                }
            };
            PlatformUI.getWorkbench().getProgressService().run(true, false, runnable);
        } catch (final InvocationTargetException e) {
            if (e.getCause() instanceof RuntimeException) {
                throw (RuntimeException) e.getCause();
            }
            throw new RuntimeException(e);
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Open the representations associated to {@link DRepresentationDescriptor}.
     * 
     * @param monitor
     *            the progress monitor to use for reporting progress to the user, or null indicating that no progress
     *            should be reported and the operation cannot be cancelled.
     */
    private void openRepresentations(final IProgressMonitor monitor) {
        String taskName = Messages.OpenRepresentationsAction_openRepresentationTask;
        if (representationsToOpen.size() > 1) {
            taskName = Messages.OpenRepresentationsAction_openRepresentationsTask;
        }
        try {
            int nbRepToLoad = (int) representationsToOpen.stream().filter(repDesc -> !repDesc.isLoadedRepresentation()).count();
            SubMonitor subMonitor = SubMonitor.convert(monitor, taskName, 6 * representationsToOpen.size() + 4 * nbRepToLoad);

            for (DRepresentationDescriptor repDesc : representationsToOpen) {
                // force representation resource loading if needed
                if (!repDesc.isLoadedRepresentation()) {
                    repDesc.getRepresentation();
                    subMonitor.worked(4);
                }

                Session session = new EObjectQuery(repDesc.getTarget()).getSession();
                subMonitor.worked(1);

                if (session != null) {
                    IEditorPart part = DialectUIManager.INSTANCE.openEditor(session, repDesc.getRepresentation(), subMonitor.split(4));
                    if (part instanceof DialectEditor) {
                        updateUISession((DialectEditor) part, session);
                        subMonitor.worked(1);
                    }
                }
            }
        } finally {
            SubMonitor.done(monitor);
        }
    }

    private void updateUISession(final DialectEditor part, final Session session) {
        IEditingSession uiSession = SessionUIManager.INSTANCE.getOrCreateUISession(session);
        uiSession.open();
        uiSession.attachEditor(part);
    }

}
