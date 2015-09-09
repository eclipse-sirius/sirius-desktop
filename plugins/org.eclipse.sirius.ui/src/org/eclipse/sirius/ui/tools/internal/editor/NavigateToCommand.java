/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.editor;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.ui.IEditorPart;

/**
 * Specific command to navigate to another representation.
 *
 * @author mporhel
 */
public class NavigateToCommand extends RecordingCommand {

    private final Session session;

    private final DRepresentation representation;

    /**
     * Constructor.
     *
     * @param session
     *            the current session.
     * @param representation
     *            the source representation.
     */
    public NavigateToCommand(Session session, DRepresentation representation) {
        super(session.getTransactionalEditingDomain(), Messages.NavigateToCommand_name);
        this.session = session;
        this.representation = representation;
    }

    @Override
    protected void doExecute() {
        if (session == null || representation == null) {
            return;
        }
        final IEditingSession ui = SessionUIManager.INSTANCE.getUISession(session);
        final IEditorPart part = DialectUIManager.INSTANCE.openEditor(session, representation, new NullProgressMonitor());
        if (part != null && ui != null) {
            ui.attachEditor((DialectEditor) part);
        }
    }
}
