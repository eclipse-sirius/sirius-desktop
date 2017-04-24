/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.properties.internal.wizard;

import java.util.Optional;

import org.eclipse.eef.common.ui.api.IEEFFormContainer;
import org.eclipse.eef.core.api.EEFPage;
import org.eclipse.eef.ide.ui.api.EEFTab;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.sirius.common.interpreter.api.IEvaluationResult;
import org.eclipse.sirius.properties.WizardModelOperation;
import org.eclipse.sirius.ui.properties.internal.dialog.DialogFormContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.ManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

/**
 * The wizard page parameterized by the Sirius properties.
 * 
 * @author sbegaudeau
 */
public class PropertiesWizardPage extends WizardPage {

    /**
     * The description of the wizard.
     */
    private WizardModelOperation wizardModelOperation;

    /**
     * The EEF Page.
     */
    private EEFPage eefPage;

    /**
     * The EEF Tab.
     */
    private EEFTab eefTab;

    /**
     * The constructor.
     * 
     * @param pageName
     *            The name of the page
     * @param title
     *            The title of the page
     * @param description
     *            The description of the page
     * @param titleImage
     *            The title image
     * @param wizardModelOperation
     *            The description of the wizard
     * @param eefPage
     *            The EEF Page
     */
    public PropertiesWizardPage(String pageName, String title, String description, ImageDescriptor titleImage, WizardModelOperation wizardModelOperation, EEFPage eefPage) {
        super(pageName, title, titleImage);
        this.setDescription(description);
        this.wizardModelOperation = wizardModelOperation;
        this.eefPage = eefPage;
    }

    @Override
    public void createControl(Composite parent) {
        FormToolkit toolkit = new FormToolkit(parent.getDisplay());
        ScrolledForm scrolledForm = toolkit.createScrolledForm(parent);
        scrolledForm.setLayoutData(new GridData(GridData.FILL_BOTH));

        IManagedForm managedForm = new ManagedForm(toolkit, scrolledForm);
        managedForm.getMessageManager().setDecorationPosition(SWT.TOP | SWT.LEFT);

        // @formatter:off
        Optional.ofNullable(this.eefPage.getDescription().getLabelExpression())
            .filter(exp -> !exp.isEmpty())
            .map(exp -> this.eefPage.getInterpreter().evaluateExpression(this.eefPage.getVariableManager().getVariables(), exp))
            .filter(IEvaluationResult::success)
            .map(IEvaluationResult::getValue)
            .filter(String.class::isInstance)
            .map(String.class::cast)
            .ifPresent(managedForm.getForm()::setText);
        // @formatter:on

        this.eefTab = new EEFTab(this.eefPage);
        IEEFFormContainer container = new DialogFormContainer(this.getShell(), managedForm.getForm().getForm());
        Composite composite = managedForm.getForm().getForm().getBody();
        parent.setBackground(composite.getBackground());

        FillLayout layout = new FillLayout();
        composite.setLayout(layout);

        int style = GridData.FILL_HORIZONTAL;
        GridData data = new GridData(style);
        composite.setLayoutData(data);

        eefTab.createControls(composite, container);

        this.setControl(scrolledForm);
    }

    @Override
    public boolean isPageComplete() {
        // @formatter:off
        return Optional.ofNullable(this.wizardModelOperation.getIsPageCompleteExpression())
                .filter(exp -> !exp.isEmpty())
                .map(exp -> this.eefPage.getInterpreter().evaluateExpression(this.eefPage.getVariableManager().getVariables(), exp))
                .filter(IEvaluationResult::success)
                .map(IEvaluationResult::getValue)
                .filter(Boolean.class::isInstance)
                .map(Boolean.class::cast)
                .orElseGet(super::isPageComplete);
        // @formatter:on
    }

    /**
     * This method should be called when the wizard page is about to be shown.
     */
    public void aboutToBeShown() {
        this.eefTab.aboutToBeShown();
    }

    /**
     * This method is used to refresh the content of the page.
     */
    public void refresh() {
        this.eefTab.refresh();
    }

    /**
     * This method should be called when the wizard page is about to be hidden.
     */
    public void aboutToBeHidden() {
        this.eefTab.aboutToBeHidden();
    }

    @Override
    public void dispose() {
        this.eefTab.dispose();
        super.dispose();
    }
}
