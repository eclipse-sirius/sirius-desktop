/*******************************************************************************
 * Copyright (c) 2008, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.wizards.pages;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.sirius.common.ui.tools.api.util.SWTUtil;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;

/**
 * A wizard page to choose Session kind.
 *
 * @author mchauvin
 */
public class CreateOrAddResourceWizardPage extends WizardPage {

    private Composite control;

    private boolean createResource = true;

    /**
     * Constructor.
     *
     * @param pageName
     *            the page name.
     */
    public CreateOrAddResourceWizardPage(final String pageName) {
        super(pageName);
        setDescription(Messages.CreateOrAddResourceWizardPage_description);
    }

    @Override
    public void createControl(final Composite parent) {

        control = SWTUtil.createCompositeBothFill(parent, 1, false);

        new Label(control, SWT.NONE);
        new Label(control, SWT.NONE);

        final Button createButton = new Button(control, SWT.RADIO);

        createButton.setSelection(createResource);

        createButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(final Event event) {
                final Button button = (Button) event.widget;
                createResource = button.getSelection();
                getContainer().updateButtons();
            }
        });

        createButton.setText(Messages.CreateOrAddResourceWizardPage_createResource_label);
        final Label emptyLabel = new Label(control, SWT.NONE);
        emptyLabel.setText("     " + Messages.CreateOrAddResourceWizardPage_createResource_details); //$NON-NLS-1$

        new Label(control, SWT.NONE);
        new Label(control, SWT.NONE);

        final Button resourceButton = new Button(control, SWT.RADIO);
        resourceButton.setText(Messages.CreateOrAddResourceWizardPage_addResource_label);
        final Label resourceLabel = new Label(control, SWT.NONE);
        resourceLabel.setText("     " + Messages.CreateOrAddResourceWizardPage_addResource_details); //$NON-NLS-1$

        setControl(control);
    }

    /**
     * Return whether the wizard should create a new resource.
     *
     * @return <code>true</code> if the wizard should create a new resource,
     *         <code>false</code> otherwise
     */
    public boolean createResource() {
        return createResource;
    }

}
