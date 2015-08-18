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
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelection;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.PlatformUI;

/**
 * An actions opening a dialog allowing to change the {@link ViewpointSelection}
 * .
 * 
 * @author alagarde
 * 
 */
public class OpenViewpointSelectionAction extends Action {

    private static final String VIEWPOINTS_SELECTION_ACTION_TEXT = "Viewpoints Selection";

    private URI sessionURI;

    /**
     * Constructor.
     * 
     * @param session
     *            the session on which viewpoints selection should be changed
     */
    public OpenViewpointSelectionAction(Session session) {
        this(session.getSessionResource().getURI());
    }

    /**
     * Constructor.
     * 
     * @param sessionURI
     *            the {@link URI} of session on which viewpoints selection
     *            should be changed
     */
    public OpenViewpointSelectionAction(URI sessionURI) {
        super(VIEWPOINTS_SELECTION_ACTION_TEXT, SiriusEditPlugin.INSTANCE.getImageDescriptor("full/obj16/Viewpoint.gif")); //$NON-NLS-1$
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
                ViewpointSelection.openViewpointsSelectionDialog(session);
            }
        });
    }
}
