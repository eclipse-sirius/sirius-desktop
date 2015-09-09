/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.wizards;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.tools.api.views.ViewHelper;
import org.eclipse.sirius.ui.tools.internal.actions.creation.CreateRepresentationAction;
import org.eclipse.sirius.ui.tools.internal.views.common.SessionLabelProvider;
import org.eclipse.sirius.ui.tools.internal.wizards.pages.RepresentationSelectionWizardPage;
import org.eclipse.sirius.ui.tools.internal.wizards.pages.SemanticElementSelectionWizardPage;
import org.eclipse.sirius.viewpoint.provider.Messages;

/**
 * This wizard creates a representation, choosing the representation type and
 * the semantic element.
 *
 * @author nlepine
 *
 */
public class CreateRepresentationWizard extends Wizard {

    private SemanticElementSelectionWizardPage selectElementPage;

    private final Session session;

    private RepresentationSelectionWizardPage representationWizardPage;

    /**
     * Constructor.
     *
     * @param session
     *            origin session.
     */
    public CreateRepresentationWizard(final Session session) {
        this.session = session;
    }

    /**
     * Initialize the wizard.
     */
    public void init() {
        setWindowTitle(Messages.CreateRepresentationWizard_title);
        setNeedsProgressMonitor(false);
    }

    @Override
    public boolean performFinish() {
        EObject element = selectElementPage.getSelectedElement();
        if (element != null) {
            CreateRepresentationAction action = new CreateRepresentationAction(session, element, representationWizardPage.getRepresentation(), new SessionLabelProvider(
                    ViewHelper.INSTANCE.createAdapterFactory()));
            action.run();
        }
        return true;
    }

    @Override
    public void addPages() {
        representationWizardPage = new RepresentationSelectionWizardPage(session);
        selectElementPage = new SemanticElementSelectionWizardPage(session);
        representationWizardPage.setSelectionWizard(selectElementPage);
        addPage(representationWizardPage);
        addPage(selectElementPage);
    }

    @Override
    public void dispose() {
        super.dispose();
        selectElementPage.dispose();
        representationWizardPage.dispose();
    }

    @Override
    public boolean canFinish() {
        return !representationWizardPage.isCurrentPageOnWizard() && super.canFinish();
    }

}
