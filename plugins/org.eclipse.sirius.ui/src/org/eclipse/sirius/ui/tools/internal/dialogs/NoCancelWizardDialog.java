/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.dialogs;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * {@link WizardDialog} implementation without any cancel button.
 * 
 * @author cbrun
 * 
 */
public class NoCancelWizardDialog extends WizardDialog {
    /**
     * Create a new wizard dialog without any cancel button.
     * 
     * @param parentShell
     *            the parent shell.
     * @param newWizard
     *            wizard to wrapp.
     */
    public NoCancelWizardDialog(final Shell parentShell, final IWizard newWizard) {
        super(parentShell, newWizard);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected Control createContents(final Composite parent) {
        final Control result = super.createContents(parent);
        final Button cancelButton = getButton(IDialogConstants.CANCEL_ID);
        ((GridLayout) cancelButton.getParent().getLayout()).numColumns--;
        cancelButton.setVisible(false);
        cancelButton.setEnabled(false);
        return result;
    }

}
