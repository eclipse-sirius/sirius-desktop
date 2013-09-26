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
package org.eclipse.sirius.ui.tools.internal.views.common.action;

import java.util.Collection;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.ui.tools.api.dialog.RenameDialog;
import org.eclipse.sirius.business.api.dialect.command.RenameRepresentationCommand;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * Action to rename one or more Sirius representations.
 * 
 * @author lredor
 */
public class RenameRepresentationAction extends Action {

    private Collection<DRepresentation> selectedRepresentations;

    /**
     * Create a new instance.
     * 
     * @param representations
     *            the representations to delete
     */
    public RenameRepresentationAction(Collection<DRepresentation> representations) {
        super("Rename");
        this.selectedRepresentations = representations;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        for (final DRepresentation representation : selectedRepresentations) {
            renameRepresentation(representation);
        }
    }

    /**
     * Launch a popup to rename the <code>representation</code>.
     * 
     * @param representation
     *            The representation to rename.
     */
    private void renameRepresentation(final DRepresentation representation) {
        final String oldName = representation.getName() != null ? representation.getName() : StringUtil.EMPTY_STRING;
        final RenameDialog dialog = new RenameDialog(Display.getCurrent().getActiveShell(), true, oldName);
        dialog.create();
        if (dialog.open() == Window.OK) {
            final String newName = dialog.getNewName();
            if (!oldName.equals(newName)) {
                final TransactionalEditingDomain transDomain = TransactionUtil.getEditingDomain(representation);
                transDomain.getCommandStack().execute(new RenameRepresentationCommand(transDomain, representation, newName));
            }
        }
    }

    /**
     * Overridden to ask to the {@link IPermissionAuthority} if we can delete
     * all selected {@link DRepresentation}s, if we can't delete one then the
     * action is disabled.
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean isEnabled() {
        boolean isEnabled = super.isEnabled();
        if (isEnabled) {
            for (DRepresentation dRepresentation : selectedRepresentations) {
                IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(dRepresentation);
                if (!permissionAuthority.canEditFeature(dRepresentation, ViewpointPackage.eINSTANCE.getDRepresentation_Name().getName())) {
                    isEnabled = false;
                    break;
                }
            }
        }
        return isEnabled;
    }

}
