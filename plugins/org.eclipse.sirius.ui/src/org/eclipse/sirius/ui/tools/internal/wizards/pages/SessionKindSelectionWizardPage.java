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
package org.eclipse.sirius.ui.tools.internal.wizards.pages;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;

import org.eclipse.sirius.common.ui.tools.api.util.SWTUtil;

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
        setTitle("Representations File");
        setDescription("Choose initialization kind");
        this.emptyModel = defaultToEmptyModel;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(final Composite parent) {

        control = SWTUtil.createCompositeBothFill(parent, 1, false);

        new Label(control, SWT.NONE);
        new Label(control, SWT.NONE);

        final Button emptyButton = new Button(control, SWT.RADIO);

        emptyButton.setSelection(emptyModel);

        emptyButton.addListener(SWT.Selection, new Listener() {

            public void handleEvent(final Event event) {
                final Button button = (Button) event.widget;
                emptyModel = button.getSelection();
            }
        });

        emptyButton.setText("Empty file");
        final Label emptyLabel = new Label(control, SWT.NONE);
        emptyLabel.setText("     Create a new empty representations file. You will be able after to associate this file with one or more semantic resources.");

        new Label(control, SWT.NONE);
        new Label(control, SWT.NONE);

        final Button resourceButton = new Button(control, SWT.RADIO);
        resourceButton.setText("Initialization from a semantic resource");
        resourceButton.setSelection(!emptyModel);
        final Label resourceLabel = new Label(control, SWT.NONE);
        resourceLabel.setText("     Link the new representations file to a semantic resource. You will be able to associate this representations file with other semantic ressources.");

        setControl(control);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.wizard.WizardPage#getNextPage()
     */
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
