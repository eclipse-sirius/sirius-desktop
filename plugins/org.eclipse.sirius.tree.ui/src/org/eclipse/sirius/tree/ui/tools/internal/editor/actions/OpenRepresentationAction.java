/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.ui.tools.internal.editor.actions;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorPart;

import org.eclipse.sirius.DRepresentation;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;

public final class OpenRepresentationAction extends Action {

    private final DRepresentation representation;

    private final Session session;

    private TransactionalEditingDomain editingDomain;

    /**
     * Constructor.
     * 
     * @param text
     *            the action's text, or <code>null</code> if there is no text
     * @param image
     *            the action's image, or <code>null</code> if there is no image
     * @param representation
     *            the representation to open.
     * @param session
     *            the session in which to open it.
     */
    public OpenRepresentationAction(String text, ImageDescriptor image, DRepresentation representation, Session session) {
        super(text, image);
        this.representation = representation;
        this.session = session;
        this.editingDomain = session.getTransactionalEditingDomain();
    }

    /**
     * Constructor.
     * 
     * @param representation
     *            the representation to open.
     * @param session
     *            the session in which to open it.
     */
    public OpenRepresentationAction(DRepresentation representation, Session session) {
        super();
        this.representation = representation;
        this.session = session;
        this.editingDomain = session.getTransactionalEditingDomain();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        super.run();
        editingDomain.getCommandStack().execute(new AttachEditorRecordingCommand(editingDomain));
    }

    private class AttachEditorRecordingCommand extends RecordingCommand {

        public AttachEditorRecordingCommand(TransactionalEditingDomain domain) {
            super(domain);
        }

        @Override
        protected void doExecute() {
            final IEditingSession ui = SessionUIManager.INSTANCE.getUISession(session);
            DialectManager.INSTANCE.refresh(representation, new NullProgressMonitor());
            final IEditorPart part = DialectUIManager.INSTANCE.openEditor(session, representation);
            if (part != null && ui != null) {
                ui.attachEditor((DialectEditor) part);
            }
        }

    }
}
