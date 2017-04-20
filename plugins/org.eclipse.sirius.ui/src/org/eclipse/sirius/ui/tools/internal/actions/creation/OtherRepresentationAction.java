/*******************************************************************************
 * Copyright (c) 2017 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.actions.creation;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.tools.internal.viewpoint.ViewpointHelper;
import org.eclipse.sirius.ui.tools.internal.wizards.CreateRepresentationWizard;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.ui.PlatformUI;

/**
 * Action launching the wizard allowing to create representations from which domain model target corresponds to a
 * semantic model element either they belong to an activated or deactivated viewpoint.
 * 
 * If no representation can be created from the semantic element, the action is disabled.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class OtherRepresentationAction extends Action {

    /**
     * The session target of the representation creation.
     */
    private Session session;

    /**
     * The semantic element from which we will initialize the wizard allowing to create a compatible representation
     * instance.
     */
    private EObject semanticSelection;

    /**
     * Instantiate a new action to create a new representation from a semantic element.
     * 
     * @param session
     *            The session target of the representation creation.
     * @param theSemanticSelection
     *            The semantic element from which we will initialize the wizard allowing to create a compatible
     *            representation instance.
     */
    public OtherRepresentationAction(final Session session, final EObject theSemanticSelection) {
        super();
        this.session = session;
        this.semanticSelection = theSemanticSelection;
        initializeLabelAndState();
    }

    /**
     * Initialize action's label and enabling state.
     */
    private void initializeLabelAndState() {

        this.setToolTipText(Messages.OtherRepresentationAction_tooltip);
        Collection<Viewpoint> availableViewpoints = ViewpointHelper.getAvailableViewpoints(session);
        boolean representationForSemanticSelectionAvailableForCreation = availableViewpoints.stream().anyMatch(viewpoint -> {
            EList<RepresentationDescription> ownedRepresentations = viewpoint.getOwnedRepresentations();
            return ownedRepresentations.stream().anyMatch(repDesc -> {
                boolean canCreate = DialectManager.INSTANCE.canCreate(semanticSelection, repDesc, false);
                return canCreate;
            });
        });

        if (representationForSemanticSelectionAvailableForCreation) {
            this.setEnabled(true);
            this.setText(Messages.OtherRepresentationAction_label);
        } else {
            this.setEnabled(false);
            this.setText(Messages.OtherRepresentationAction_noRepresentation_label);
        }

    }

    @Override
    public void run() {
        super.run();
        CreateRepresentationWizard wizard = new CreateRepresentationWizard(session, semanticSelection);
        wizard.init();
        final WizardDialog dialog = new WizardDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), wizard);
        dialog.setMinimumPageSize(CreateRepresentationWizard.MIN_PAGE_WIDTH, CreateRepresentationWizard.MIN_PAGE_HEIGHT);
        dialog.create();
        dialog.getShell().setText(org.eclipse.sirius.viewpoint.provider.Messages.CreateRepresentationFromSessionAction_wizardTitle);
        dialog.open();

    }

}
