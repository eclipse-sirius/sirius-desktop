/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import org.eclipse.sirius.DSemanticDecorator;
import org.eclipse.sirius.business.api.delete.IDeleteHook;

/**
 * A example delete hook to confirm deletion.
 * 
 * @author mchauvin
 * @since 2.1
 */
public class ConfirmationDialogDeleteHook implements IDeleteHook {
    /**
     * {@inheritDoc}
     */
    public IStatus beforeDeleteCommandExecution(Collection<DSemanticDecorator> selections, Map<String, Object> parameters) {
        Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
        boolean confirmation = MessageDialog.openConfirm(shell, "Deletion confirmation", "Are you sure you want to delete the selected elements?");
        if (confirmation) {
            return Status.OK_STATUS;
        } else {
            return Status.CANCEL_STATUS;
        }
    }
}
