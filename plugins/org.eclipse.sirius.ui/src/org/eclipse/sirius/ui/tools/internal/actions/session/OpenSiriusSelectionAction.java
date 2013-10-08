/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.actions.session;

import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.PlatformUI;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelection;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;

/**
 * An actions opening a dialog allowing to change the {@link ViewpointSelection}
 * .
 * 
 * @author alagarde
 * 
 */
public class OpenSiriusSelectionAction extends Action {

    private URI sessionURI;

    /**
     * Constructor.
     * 
     * @param session
     *            the session on which viewpoint selection should be changed
     */
    public OpenSiriusSelectionAction(Session session) {
        this(session.getSessionResource().getURI());
    }

    /**
     * Constructor.
     * 
     * @param sessionURI
     *            the {@link URI} of session on which viewpoint selection should
     *            be changed
     */
    public OpenSiriusSelectionAction(URI sessionURI) {
        super("Viewpoints Selection", SiriusEditPlugin.INSTANCE.getImageDescriptor("full/obj16/Sirius.gif"));
        this.sessionURI = sessionURI;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        final Session session = SessionManager.INSTANCE.getExistingSession(sessionURI);
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
            public void run() {
                ViewpointSelection.openSiriussSelectionDialog(session);
            }
        });
    }
}
