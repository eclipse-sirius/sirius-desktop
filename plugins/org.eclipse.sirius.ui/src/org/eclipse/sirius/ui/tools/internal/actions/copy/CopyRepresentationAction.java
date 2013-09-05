/*******************************************************************************
 * Copyright (c) 2009, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.actions.copy;

import java.util.Collection;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.ui.tools.api.dialog.RenameDialog;
import org.eclipse.sirius.DRepresentation;
import org.eclipse.sirius.business.api.dialect.command.CopyRepresentationCommand;
import org.eclipse.sirius.business.api.session.Session;

/**
 * An action to copy selected representations.
 * 
 * @author mchauvin
 */
public class CopyRepresentationAction extends Action {

    final Session session;

    final Collection<DRepresentation> representations;

    /**
     * Construct a new instance.
     * 
     * @param session
     *            the current session
     * @param selection
     *            the selected representation to copy
     */
    public CopyRepresentationAction(final Session session, final Collection<DRepresentation> selection) {
        super("Copy");
        this.session = session;
        this.representations = selection;

        final ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
        this.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
        this.setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY_DISABLED));
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {

        RenameDialog dialog;

        if (representations.size() == 1) {
            final String oldName = getOldName();
            dialog = new RenameDialog(Display.getCurrent().getActiveShell(), oldName);
            dialog.create();
            dialog.setTitle("Enter the name for the copied representation");
        } else {
            final String prefix = getPrefix();
            dialog = new RenameDialog(Display.getCurrent().getActiveShell(), prefix);
            dialog.create();
            dialog.setTitle("Enter the prefix for the copied representations name");
            dialog.setText("Copy of");
        }

        if (dialog.open() == Window.OK) {
            final String newName = dialog.getNewName();
            DRepresentation dRepresentation = representations.iterator().next();
            final TransactionalEditingDomain transDomain = TransactionUtil.getEditingDomain(dRepresentation);
            transDomain.getCommandStack().execute(new CopyRepresentationCommand(transDomain, representations, newName, session));
        }

    }

    private String getOldName() {
        final DRepresentation representation = representations.iterator().next();
        return representation.getName() != null ? representation.getName() : StringUtil.EMPTY_STRING;
    }

    private String getPrefix() {
        return StringUtil.EMPTY_STRING;
    }
}
