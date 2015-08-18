/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.common.action;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.business.api.dialect.command.MoveRepresentationCommand;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSessionHelper;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationContainer;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * An action to move selected representations.
 * 
 * @author <a href="mailto:mickael.lanoe@obeo.fr">Mickael LANOE</a>
 */
public class MoveRepresentationAction extends Action {
    private final Collection<DRepresentation> representations;

    private final DAnalysis targetAnalysis;

    private final Session session;

    /**
     * Construct a new instance.
     * 
     * @param session
     *            the current session
     * @param targetAnalysis
     *            target analysis for the selection
     * @param selection
     *            the selected representations to move
     */
    public MoveRepresentationAction(Session session, final DAnalysis targetAnalysis, Collection<DRepresentation> selection) {
        super();
        this.targetAnalysis = targetAnalysis;
        this.session = session;
        this.representations = selection;

        final ImageDescriptor descriptor = AbstractUIPlugin.imageDescriptorFromPlugin(SiriusEditPlugin.ID, "/icons/full/others/forward.gif"); //$NON-NLS-1$
        this.setImageDescriptor(descriptor);

        this.setText("move to " + targetAnalysis.eResource().getURI().toString());

        // Disable the action if the selection is not valid
        if (!isValidSelection()) {
            this.setEnabled(false);
        }
    }

    @Override
    public void run() {
        final IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(session);
        if (uiSession != null) {
            for (final DRepresentation representation : representations) {
                final IEditorPart editor = uiSession.getEditor(representation);
                if (editor != null) {
                    editor.getEditorSite().getPage().closeEditor(editor, false);
                }
            }
        }
        session.getTransactionalEditingDomain().getCommandStack().execute(new MoveRepresentationCommand(session, targetAnalysis, representations));
    }

    /**
     * Test if the selection is valid.
     * 
     * @return true if the selection is valid
     */
    private boolean isValidSelection() {

        boolean anyInvalidMove = Iterables.any(representations, new Predicate<DRepresentation>() {

            @Override
            public boolean apply(DRepresentation input) {
                boolean invalid = false; // false is the default value

                // Step 1: Check source representation container
                EObject container = input.eContainer();
                if (container instanceof DRepresentationContainer) {
                    IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(container);
                    if (permissionAuthority != null && !permissionAuthority.canDeleteInstance(input)) {
                        invalid = true;
                    }
                }

                // Step 2: Check target representation container
                if (!invalid) {
                    DRepresentationContainer targetContainer = DAnalysisSessionHelper.findContainerForAddedRepresentation(targetAnalysis, input);
                    if (targetContainer != null) {
                        IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(targetContainer);
                        if (permissionAuthority != null && !permissionAuthority.canCreateIn(targetContainer)) {
                            invalid = true;
                        }
                    }
                }

                return invalid;
            }
        });

        return !anyInvalidMove;
    }
}
