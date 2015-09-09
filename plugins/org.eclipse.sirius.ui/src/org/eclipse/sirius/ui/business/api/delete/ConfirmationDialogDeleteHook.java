/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.api.delete;

import java.util.Collection;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.sirius.business.api.delete.IDeleteHook;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * A example delete hook to confirm deletion.
 *
 * @author mchauvin
 * @since 0.9.0
 */
public class ConfirmationDialogDeleteHook implements IDeleteHook {
    @Override
    public IStatus beforeDeleteCommandExecution(Collection<DSemanticDecorator> selections, Map<String, Object> parameters) {
        Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
        boolean confirmation = MessageDialog.openConfirm(shell, Messages.ConfirmationDialogDeleteHook_dialogTitle, Messages.ConfirmationDialogDeleteHook_dialogMessage);
        if (confirmation) {
            return Status.OK_STATUS;
        } else {
            return Status.CANCEL_STATUS;
        }
    }
}
