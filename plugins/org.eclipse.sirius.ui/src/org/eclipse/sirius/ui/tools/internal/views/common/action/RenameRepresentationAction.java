/*******************************************************************************
 * Copyright (c) 2012, 2016 THALES GLOBAL SERVICES and others.
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
import org.eclipse.sirius.business.api.dialect.command.RenameRepresentationCommand;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.ui.tools.api.dialog.RenameDialog;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.swt.widgets.Display;

/**
 * Action to rename one or more Sirius representations.
 *
 * @author lredor
 */
public class RenameRepresentationAction extends Action {

    private Collection<DRepresentationDescriptor> selectedRepDescriptions;

    /**
     * Create a new instance.
     *
     * @param selectedRepDescriptions
     *            the representations to delete
     */
    public RenameRepresentationAction(Collection<DRepresentationDescriptor> selectedRepDescriptions) {
        super(Messages.RenameRepresentationAction_name);
        this.selectedRepDescriptions = selectedRepDescriptions;
    }

    @Override
    public void run() {
        for (final DRepresentationDescriptor repDesc : selectedRepDescriptions) {
            renameRepresentation(repDesc);
        }
    }

    /**
     * Launch a popup to rename the <code>representation</code>.
     *
     * @param repDescriptor
     *            The representation to rename.
     */
    private void renameRepresentation(final DRepresentationDescriptor repDescriptor) {
        final String oldName = repDescriptor.getName() != null ? repDescriptor.getName() : StringUtil.EMPTY_STRING;
        final RenameDialog dialog = new RenameDialog(Display.getCurrent().getActiveShell(), true, oldName);
        dialog.create();
        if (dialog.open() == Window.OK) {
            final String newName = dialog.getNewName();
            if (!oldName.equals(newName)) {
                final TransactionalEditingDomain transDomain = TransactionUtil.getEditingDomain(repDescriptor);
                transDomain.getCommandStack().execute(new RenameRepresentationCommand(transDomain, repDescriptor, newName));
            }
        }
    }

    /**
     * Overridden to ask to the {@link IPermissionAuthority} if we can rename
     * all selected {@link DRepresentationDescriptor}s. If we can't rename one
     * then the action is disabled.
     *
     * {@inheritDoc}
     */
    @Override
    public boolean isEnabled() {
        boolean isEnabled = super.isEnabled();
        if (isEnabled) {
            for (DRepresentationDescriptor dRepDescription : selectedRepDescriptions) {
                DRepresentation dRepresentation = dRepDescription.getRepresentation();
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
