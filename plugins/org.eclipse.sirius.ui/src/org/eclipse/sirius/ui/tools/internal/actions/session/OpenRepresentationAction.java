/*******************************************************************************
 * Copyright (c) 2013, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.actions.session;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.tools.internal.editor.NavigateToCommand;
import org.eclipse.sirius.viewpoint.DRepresentation;

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
     *            the representation to open. Caller has to check this is not a
     *            dangling representation, see {@link
     *            org.eclipse.sirius.business.api.query.DRepresentationQuery.
     *            isDanglingRepresentation()}.
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
        editingDomain.getCommandStack().execute(new NavigateToCommand(session, representation));
    }
}
