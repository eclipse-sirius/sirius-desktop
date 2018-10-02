/*******************************************************************************
 * Copyright (c) 2013, 2017 THALES GLOBAL SERVICES and others.
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

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.tools.internal.editor.NavigateToCommand;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

public final class OpenRepresentationAction extends Action {

    private final DRepresentationDescriptor representationDescriptor;

    private final Session session;

    private TransactionalEditingDomain editingDomain;

    /**
     * Constructor.
     * 
     * @param text
     *            the action's text, or <code>null</code> if there is no text
     * @param image
     *            the action's image, or <code>null</code> if there is no image
     * @param repDesc
     *            the representation to open. Caller has to check this is not a dangling representation, see
     *            {@link org.eclipse.sirius.business.api.query.DRepresentationQuery. isDanglingRepresentation()}.
     * @param session
     *            the session in which to open it.
     */
    public OpenRepresentationAction(String text, ImageDescriptor image, DRepresentationDescriptor repDesc, Session session) {
        super(text, image);
        this.representationDescriptor = repDesc;
        this.session = session;
        this.editingDomain = session.getTransactionalEditingDomain();
    }

    /**
     * Constructor.
     * 
     * @param repDesc
     *            the representationDescriptor to open.
     * @param session
     *            the session in which to open it.
     */
    public OpenRepresentationAction(DRepresentationDescriptor repDesc, Session session) {
        super();
        this.representationDescriptor = repDesc;
        this.session = session;
        this.editingDomain = session.getTransactionalEditingDomain();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        DRepresentation representation = representationDescriptor.getRepresentation();
        editingDomain.getCommandStack().execute(new NavigateToCommand(session, representation));
    }
}
