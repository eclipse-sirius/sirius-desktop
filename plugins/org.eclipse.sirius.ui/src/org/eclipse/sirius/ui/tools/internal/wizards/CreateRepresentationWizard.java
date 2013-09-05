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
package org.eclipse.sirius.ui.tools.internal.wizards;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.Wizard;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.tools.api.views.ViewHelper;
import org.eclipse.sirius.ui.tools.internal.actions.creation.CreateRepresentationAction;
import org.eclipse.sirius.ui.tools.internal.views.common.SessionLabelProvider;
import org.eclipse.sirius.ui.tools.internal.wizards.pages.RepresentationSelectionWizardPage;
import org.eclipse.sirius.ui.tools.internal.wizards.pages.SemanticElementSelectionWizardPage;

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
        setWindowTitle("Create Representation");
        setNeedsProgressMonitor(false);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    public boolean performFinish() {
        EObject element = selectElementPage.getSelectedElement();
        if (element != null) {
            CreateRepresentationAction action = new CreateRepresentationAction(session, (EObject) element, representationWizardPage.getRepresentation(), new SessionLabelProvider(
                    ViewHelper.INSTANCE.createAdapterFactory()));
            action.run();
        }
        return true;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    public void addPages() {
        representationWizardPage = new RepresentationSelectionWizardPage(session);
        selectElementPage = new SemanticElementSelectionWizardPage(session);
        representationWizardPage.setSelectionWizard(selectElementPage);
        addPage(representationWizardPage);
        addPage(selectElementPage);
    }

    /**
     * 
     * {@inheritDoc}
     */
    public void dispose() {
        super.dispose();
        selectElementPage.dispose();
        representationWizardPage.dispose();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.wizard.Wizard#canFinish()
     */
    public boolean canFinish() {
        return !representationWizardPage.isCurrentPageOnWizard() && super.canFinish();
    }

}
