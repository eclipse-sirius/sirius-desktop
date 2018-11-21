/**
 * 
 */
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
