/*******************************************************************************
 * Copyright (c) 2009, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ui.tools.internal.actions.copy;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.business.api.dialect.command.CopyRepresentationCommand;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.ui.tools.api.dialog.RenameDialog;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * An action to copy selected representations.
 *
 * @author mchauvin
 */
public class CopyRepresentationAction extends Action {

    final Session session;

    final Collection<DRepresentationDescriptor> repDescriptors;

    /**
     * Construct a new instance.
     *
     * @param session
     *            the current session
     * @param selection
     *            the selected representation to copy
     */
    public CopyRepresentationAction(final Session session, final Collection<DRepresentationDescriptor> selection) {
        super(Messages.CopyRepresentationAction_name);
        this.session = session;
        this.repDescriptors = selection;

        final ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
        this.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
        this.setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY_DISABLED));

        // Disable the action if the selection is not valid
        if (!isValidSelection()) {
            this.setEnabled(false);
        }
    }

    @Override
    public void run() {

        RenameDialog dialog;

        if (repDescriptors.size() == 1) {
            final String oldName = getOldName();
            dialog = new RenameDialog(Display.getCurrent().getActiveShell(), oldName);
            dialog.setTitle(Messages.CopyRepresentationAction_copyRepresentationDialog_title);
            dialog.create();
        } else {
            final String prefix = getPrefix();
            dialog = new RenameDialog(Display.getCurrent().getActiveShell(), prefix);
            dialog.setTitle(Messages.CopyRepresentationAction_copyRepresentationsDialog_title);
            dialog.setMessage(Messages.CopyRepresentationAction_copyRepresentationsDialog_message);
            dialog.setDefaultNewName(Messages.CopyRepresentationAction_copyRepresentationsDialog_defaultNewName);
            dialog.create();
        }

        if (dialog.open() == Window.OK) {
            final String newName = dialog.getNewName();
            DRepresentationDescriptor dRepDescriptor = repDescriptors.iterator().next();
            final TransactionalEditingDomain transDomain = TransactionUtil.getEditingDomain(dRepDescriptor);
            transDomain.getCommandStack().execute(new CopyRepresentationCommand(transDomain, repDescriptors, newName, session));
        }

    }

    private String getOldName() {
        final DRepresentationDescriptor dRepDescriptor = repDescriptors.iterator().next();
        return dRepDescriptor.getName() != null ? dRepDescriptor.getName() : StringUtil.EMPTY_STRING;
    }

    private String getPrefix() {
        return StringUtil.EMPTY_STRING;
    }

    /**
     * Test if the selection is valid.
     *
     * @return true if the selection is valid
     */
    private boolean isValidSelection() {

        boolean anyInvalidCopy = Iterables.any(repDescriptors, new Predicate<DRepresentationDescriptor>() {

            @Override
            public boolean apply(DRepresentationDescriptor input) {
                EObject container = input.eContainer();
                if (container instanceof DView) {
                    IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(container);
                    if (permissionAuthority != null && !permissionAuthority.canCreateIn(container)) {
                        return true;
                    }
                }
                return false;
            }
        });

        return !anyInvalidCopy;
    }
}
