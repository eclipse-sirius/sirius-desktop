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
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ui.tools.internal.wizards.ExtractRepresentationsWizard;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationContainer;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * An action to extract selected representations.
 * 
 * @author <a href="mailto:mickael.lanoe@obeo.fr">Mickael LANOE</a>
 */
public class ExtractRepresentationAction extends Action {
    private final Session session;

    private final Collection<DRepresentation> representations;

    /**
     * Construct a new instance.
     * 
     * @param session
     *            the current session
     * @param selection
     *            the selected representations to extract
     */
    public ExtractRepresentationAction(Session session, Collection<DRepresentation> selection) {
        super();
        this.session = session;
        this.representations = selection;

        final ImageDescriptor descriptor = AbstractUIPlugin.imageDescriptorFromPlugin(SiriusEditPlugin.ID, "/icons/full/others/export.gif"); //$NON-NLS-1$
        this.setImageDescriptor(descriptor);

        this.setText("Extract to ." + SiriusUtil.SESSION_RESOURCE_EXTENSION + " file ...");

        // Disable the action if the selection is not valid
        if (!isValidSelection()) {
            this.setEnabled(false);
        }
    }

    @Override
    public void run() {
        final TransactionalEditingDomain transDomain = session.getTransactionalEditingDomain();
        final ExtractRepresentationsWizard wizard = new ExtractRepresentationsWizard(session, transDomain, representations);
        final Shell defaultShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        final WizardDialog dialog = new WizardDialog(defaultShell, wizard);
        dialog.create();
        dialog.getShell().setText("Extract Representation Wizard");
        dialog.open();
    }

    /**
     * Test if the selection is valid.
     * 
     * @return true if the selection is valid
     */
    private boolean isValidSelection() {

        boolean anyInvalidExtract = Iterables.any(representations, new Predicate<DRepresentation>() {

            @Override
            public boolean apply(DRepresentation input) {
                EObject container = input.eContainer();
                if (container instanceof DRepresentationContainer) {
                    IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(container);
                    if (permissionAuthority != null && !permissionAuthority.canDeleteInstance(input)) {
                        return true;
                    }
                }
                return false;
            }
        });

        return !anyInvalidExtract;
    }
}
