/*******************************************************************************
 * Copyright (c) 2018-2019 Obeo.
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
package org.eclipse.sirius.common.ui.tools.api.selection;

import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * A custom {@link WizardDialog} that exposes the finish method.
 * 
 * @author <a href=mailto:pierre.guilet@obeo.fr>Pierre Guilet</a>
 *
 */
public class WizardDialogClosableByWizard extends WizardDialog {

    /**
     * Constructor.
     * 
     * @param parentShell
     *            parent shell.
     * @param newWizard
     *            the new wizard handled by this dialog.
     */
    public WizardDialogClosableByWizard(Shell parentShell, IWizard newWizard) {
        super(parentShell, newWizard);
    }

    @Override
    public void finishPressed() {
        super.finishPressed();
    }

}
