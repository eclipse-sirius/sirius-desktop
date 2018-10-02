/*******************************************************************************
 * Copyright (c) 2017 Obeo.
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
package org.eclipse.sirius.ui.properties.internal.wizard;

import java.util.Optional;

import org.eclipse.eef.core.api.EEFView;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.interpreter.api.IInterpreter;
import org.eclipse.sirius.common.interpreter.api.IVariableManager;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.properties.WizardModelOperation;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * The form wizard dialog parameterized by the Sirius wizard description.
 * 
 * @author sbegaudeau
 */
public class PropertiesWizardDialog extends WizardDialog {

    /**
     * The constructor.
     * 
     * @param shell
     *            The shell
     * @param modelAccessor
     *            The model accessor
     * @param context
     *            The command context
     * @param session
     *            The Sirius session
     * @param interpreter
     *            The interpreter
     * @param variableManager
     *            The variable manager
     * @param wizardModelOperation
     *            The wizard model operation
     * @param eefView
     *            The EEF View
     */
    @SuppressWarnings("checkstyle:parameternumber")
    public PropertiesWizardDialog(Shell shell, ModelAccessor modelAccessor, CommandContext context, Session session, IInterpreter interpreter, IVariableManager variableManager,
            WizardModelOperation wizardModelOperation, EEFView eefView) {
        super(shell, new PropertiesWizard(wizardModelOperation, eefView, interpreter, variableManager, modelAccessor, context, session));
    }

    @Override
    protected void backPressed() {
        // @formatter:off
        Optional.ofNullable(this.getCurrentPage())
            .filter(PropertiesWizardPage.class::isInstance)
            .map(PropertiesWizardPage.class::cast)
            .ifPresent(PropertiesWizardPage::aboutToBeHidden);
        // @formatter:on

        super.backPressed();
    }

    @Override
    protected void nextPressed() {
        // @formatter:off
        Optional.ofNullable(this.getCurrentPage())
            .filter(PropertiesWizardPage.class::isInstance)
            .map(PropertiesWizardPage.class::cast)
            .ifPresent(PropertiesWizardPage::aboutToBeHidden);
        // @formatter:on

        super.nextPressed();
    }

    @Override
    protected Control createContents(Composite parent) {
        Control control = super.createContents(parent);

        // @formatter:off
        Optional.ofNullable(this.getCurrentPage())
            .filter(PropertiesWizardPage.class::isInstance)
            .map(PropertiesWizardPage.class::cast)
            .ifPresent(wizardPage -> {
                wizardPage.aboutToBeShown();
                wizardPage.refresh();
            });
        // @formatter:on

        return control;
    }

    @Override
    public void showPage(IWizardPage page) {
        super.showPage(page);

        // @formatter:off
        Optional.ofNullable(page)
            .filter(PropertiesWizardPage.class::isInstance)
            .map(PropertiesWizardPage.class::cast)
            .ifPresent(wizardPage -> {
                wizardPage.aboutToBeShown();
                wizardPage.refresh();
            });
        // @formatter:on
    }
}
