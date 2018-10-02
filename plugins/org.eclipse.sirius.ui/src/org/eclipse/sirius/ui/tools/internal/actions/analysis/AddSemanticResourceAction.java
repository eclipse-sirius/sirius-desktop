/*******************************************************************************
 * Copyright (c) 2008, 2015 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ui.tools.internal.actions.analysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ui.tools.api.actions.analysis.IAddModelDependencyWizard;
import org.eclipse.sirius.ui.tools.internal.wizards.CreateOrAddResourceWizard;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * Action to add a semantic resource in a session.
 * 
 * @author ymortier
 */
public class AddSemanticResourceAction extends Action {

    private Collection<URI> sessionsURI = new LinkedHashSet<>();

    /**
     * Creates a new {@link AddSemanticResourceAction}.
     * 
     * @param sessions
     *            the selected session.
     */
    public AddSemanticResourceAction(final List<Session> sessions) {
        this(collectURIsFromSessions(sessions));
    }

    /**
     * Creates a new {@link AddSemanticResourceAction}.
     * 
     * @param sessionsURIs
     *            the URI of each session on which to add a semantic resource
     */
    public AddSemanticResourceAction(final Collection<URI> sessionsURIs) {
        super(Messages.AddSemanticResourceAction_title);
        final ImageDescriptor descriptor = AbstractUIPlugin.imageDescriptorFromPlugin(SiriusEditPlugin.ID, "/icons/full/others/add.gif"); //$NON-NLS-1$
        setImageDescriptor(descriptor);
        this.sessionsURI = sessionsURIs;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        super.run();

        List<Session> sessions = new ArrayList<>();
        for (final URI sessionURI : sessionsURI) {
            Session session = SessionManager.INSTANCE.getExistingSession(sessionURI);
            if (session != null) {
                sessions.add(session);
            }
        }
        IAddModelDependencyWizard wizard = IAddModelDependencyWizardRegistry.getCreateOrAddModelDependencyWizard(sessions);
        if (wizard == null) {
            // If no custom CreateOrAddResourceWizard can be applied, we use a
            // default add resource wizard
            wizard = new CreateOrAddResourceWizard();
        }
        wizard.setSessions(sessions);
        final WizardDialog dialog = new WizardDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), wizard);
        dialog.create();
        dialog.getShell().setText(wizard.getWizardTitle());
        dialog.open();
        sessionsURI.clear();
    }

    /**
     * Returns the list of {@link URI}s corresponding to the given
     * {@link Session}s.
     * 
     * @param sessions
     *            The list of {@link Session}s to get the {@link URI} from
     * @return the list of {@link URI}s corresponding to the given
     *         {@link Session}s
     */
    private static Collection<URI> collectURIsFromSessions(List<Session> sessions) {
        Collection<URI> sessionsURIs = new LinkedHashSet<>();
        for (Session concernedSession : sessions) {
            sessionsURIs.add(concernedSession.getSessionResource().getURI());
        }
        return sessionsURIs;
    }
}
