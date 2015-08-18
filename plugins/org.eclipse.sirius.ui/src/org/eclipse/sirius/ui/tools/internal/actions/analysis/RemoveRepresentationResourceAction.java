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
package org.eclipse.sirius.ui.tools.internal.actions.analysis;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.common.ui.tools.api.util.SWTUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.ISaveablePart2;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.google.common.collect.Sets;

/**
 * 
 * Action to remove a representation resource from a session.
 * 
 * @author cbrun
 * 
 */
public class RemoveRepresentationResourceAction extends Action {

    private final HashSet<Resource> diagramResources;

    private final Session session;

    /**
     * Create the action.
     * 
     * @param diagramResources
     *            resource to remove.
     * @param session
     *            current session.
     */
    public RemoveRepresentationResourceAction(final Collection<Resource> diagramResources, final Session session) {
        super("Remove from representations file", AbstractUIPlugin.imageDescriptorFromPlugin(SiriusEditPlugin.ID, "/icons/full/others/remove.gif")); //$NON-NLS-2$
        this.diagramResources = Sets.newHashSet(diagramResources);
        this.session = session;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        final IEditingSession ui = SessionUIManager.INSTANCE.getUISession(session);
        int choice = ISaveablePart2.YES;
        if (session.getStatus() == SessionStatus.DIRTY) {
            /* Show a dialog. */
            choice = SWTUtil.showSaveDialog(session, "Save representations file before removing the resource ?", true);
        }
        if (ui != null) {
            if (choice != ISaveablePart2.CANCEL) {
                if (choice == ISaveablePart2.YES) {
                    session.save(new NullProgressMonitor());
                }
                final Collection<DialectEditor> editorsToClose = new HashSet<DialectEditor>();
                for (final DRepresentation representation : DialectManager.INSTANCE.getAllRepresentations(session)) {
                    if (diagramResources.contains(representation.eResource())) {
                        final DialectEditor editor = ui.getEditor(representation);
                        if (editor != null) {
                            editorsToClose.add(editor);
                        }
                    }
                }
                ui.closeEditors(choice == ISaveablePart2.YES, editorsToClose);
            }
        }
        if (choice != ISaveablePart2.CANCEL) {
            if (session instanceof DAnalysisSession) {
                TransactionalEditingDomain transDomain = session.getTransactionalEditingDomain();
                RecordingCommand cmd = new RemoveRepresentationResourcesCommand((DAnalysisSession) session, diagramResources);
                transDomain.getCommandStack().execute(cmd);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnabled() {
        boolean mainResDeletion = diagramResources.contains(session.getSessionResource());
        boolean referencedAnalysisDeletionEnabled = !diagramResources.isEmpty() && session instanceof DAnalysisSession && ((DAnalysisSession) session).getAllSessionResources().size() > 1;
        return super.isEnabled() && !mainResDeletion && referencedAnalysisDeletionEnabled;
    }

    /**
     * Command to remove an analysis from the session.
     * 
     * @author mporhel
     */
    private class RemoveRepresentationResourcesCommand extends RecordingCommand {

        private Collection<Resource> analysisResources = Sets.newHashSet();

        private final DAnalysisSession session;

        public RemoveRepresentationResourcesCommand(DAnalysisSession session, Collection<Resource> analysisResourcesToRemove) {
            super(session.getTransactionalEditingDomain());
            this.session = session;

            if (analysisResourcesToRemove != null && !analysisResourcesToRemove.isEmpty()) {
                this.analysisResources.addAll(analysisResourcesToRemove);
            }
        }

        @Override
        protected void doExecute() {
            for (final Resource res : analysisResources) {
                session.removeAnalysis(res);
            }
        }
    }
}
