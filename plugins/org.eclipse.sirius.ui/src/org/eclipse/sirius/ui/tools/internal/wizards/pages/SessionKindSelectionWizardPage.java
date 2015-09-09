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
package org.eclipse.sirius.ui.tools.internal.wizards.pages;

import org.eclipse.jface.wizard.IWizardPage;
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
public class SessionKindSelectionWizardPage extends WizardPage {

    private Composite control;

    private boolean emptyModel = true;

    /**
     * Create the page.
     *
     * @param pageName
     *            the page's name.
     * @param defaultToEmptyModel
     *            whether of not the page should default to an empty session
     *            with no semantic model.
     */
    public SessionKindSelectionWizardPage(final String pageName, final boolean defaultToEmptyModel) {
        super(pageName);
        setTitle(Messages.SessionKindSelectionWizardPage_title);
        setDescription(Messages.SessionKindSelectionWizardPage_description);
        this.emptyModel = defaultToEmptyModel;
    }

    @Override
    public void createControl(final Composite parent) {

        control = SWTUtil.createCompositeBothFill(parent, 1, false);

        new Label(control, SWT.NONE);
        new Label(control, SWT.NONE);

        final Button emptyButton = new Button(control, SWT.RADIO);

        emptyButton.setSelection(emptyModel);

        emptyButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(final Event event) {
                final Button button = (Button) event.widget;
                emptyModel = button.getSelection();
            }
        });

        emptyButton.setText(Messages.SessionKindSelectionWizardPage_emptyFile_label);
        final Label emptyLabel = new Label(control, SWT.NONE);
        emptyLabel.setText("     " + Messages.SessionKindSelectionWizardPage_emptyFile_details); //$NON-NLS-1$

        new Label(control, SWT.NONE);
        new Label(control, SWT.NONE);

        final Button resourceButton = new Button(control, SWT.RADIO);
        resourceButton.setText(Messages.SessionKindSelectionWizardPage_initialization_label);
        resourceButton.setSelection(!emptyModel);
        final Label resourceLabel = new Label(control, SWT.NONE);
        resourceLabel.setText("     " + Messages.SessionKindSelectionWizardPage_initialization_details); //$NON-NLS-1$

        setControl(control);
    }

    @Override
    public IWizardPage getNextPage() {
        if (!emptyModel) {
            return super.getNextPage();
        } else {
            return super.getNextPage().getNextPage();
        }
    }

    /**
     * Return whether the selected session is an empty one.
     *
     * @return <code>true</code> if the selected session is an empty one,
     *         <code>false</code> otherwise
     */
    public boolean emptySession() {
        return emptyModel;
    }
}
