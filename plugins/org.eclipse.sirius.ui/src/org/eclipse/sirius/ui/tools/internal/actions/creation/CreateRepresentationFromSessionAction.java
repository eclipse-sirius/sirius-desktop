/*******************************************************************************
 * Copyright (c) 2011, 2017 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.actions.creation;

import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ui.tools.internal.wizards.CreateRepresentationWizard;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.ui.PlatformUI;

/**
 * Action to create a representation.
 * 
 * @author nlepine
 */
public class CreateRepresentationFromSessionAction extends Action {

    private URI sessionURI;

    /**
     * Instantiate a new action to create a new representation.
     * 
     * @param session
     *            the session to create the representation on
     * @param descriptor
     *            {@link ImageDescriptor}
     */
    public CreateRepresentationFromSessionAction(final Session session, ImageDescriptor descriptor) {
        this(session.getSessionResource().getURI(), descriptor);
    }

    /**
     * Instantiate a new action to create a new representation.
     * 
     * @param sessionURI
     *            the session's {@link URI}
     * @param descriptor
     *            {@link ImageDescriptor}
     */
    public CreateRepresentationFromSessionAction(final URI sessionURI, ImageDescriptor descriptor) {
        super(Messages.CreateRepresentationFromSessionAction_name, descriptor);
        this.sessionURI = sessionURI;
    }

    @Override
    public void run() {
        super.run();
        Session session = SessionManager.INSTANCE.getExistingSession(sessionURI);
        if (session != null) {
            CreateRepresentationWizard wizard = new CreateRepresentationWizard(session);
            wizard.init();
            final WizardDialog dialog = new WizardDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), wizard);
            dialog.setMinimumPageSize(CreateRepresentationWizard.MIN_PAGE_WIDTH, CreateRepresentationWizard.MIN_PAGE_HEIGHT);
            dialog.create();
            dialog.getShell().setText(Messages.CreateRepresentationFromSessionAction_wizardTitle);
            dialog.open();
        }
    }
}
