/*******************************************************************************
 * Copyright (c) 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.ui.tools.api.find;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.sirius.common.tools.api.find.AbstractFindLabelEngine;
import org.eclipse.sirius.common.tools.api.find.FindMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * An Abstract class that define a "Find" dialog for a GraphicalEditor.
 * 
 * @author glefur
 */
public abstract class AbstractFindLabelDialog extends Dialog {

    /**
     * The text to search
     */
    private Text findText;

    /**
     * The search engine
     */
    private AbstractFindLabelEngine engine;

    /**
     * Constructor.
     * 
     * @param parentShell
     *            the parent shell
     * @param engine
     *            the search engine to use
     */
    public AbstractFindLabelDialog(final Shell parentShell, final AbstractFindLabelEngine engine) {
        super(parentShell);
        // set style in 2 operations to avoid checkstyle error
        final int style = SWT.CLOSE | SWT.TITLE | SWT.BORDER | SWT.APPLICATION_MODAL;
        setShellStyle(style | SWT.RESIZE);
        this.engine = engine;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(final Composite parent) {
        final Composite container = new Composite(parent, SWT.NONE);
        final GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        container.setLayoutData(gd);
        getShell().setText(FindMessages.abstractFindLabelDialogDialogTitle);
        createFindInterface(container);
        return container;
    }

    /**
     * Create the main interface.
     * 
     * @param container
     *            the composite parent
     */
    protected void createFindInterface(final Composite container) {
        final GridLayout containerLayout = new GridLayout();
        containerLayout.numColumns = 2;
        container.setLayout(containerLayout);
        createInputArea(container);
        createButtonsArea(container);
    }

    /**
     * Create the widget which contain user input, i.e. the text to search and
     * the search options.
     */
    private void createInputArea(final Composite container) {
        final Composite findArea = new Composite(container, SWT.NONE);
        final GridData findAreaLayoutData = new GridData(GridData.FILL_HORIZONTAL);
        findAreaLayoutData.verticalAlignment = SWT.BEGINNING;
        findArea.setLayoutData(findAreaLayoutData);

        final GridLayout findAreaLayout = new GridLayout();
        findAreaLayout.numColumns = 2;
        findArea.setLayout(findAreaLayout);
        final Label findLabel = new Label(findArea, SWT.NONE);
        findLabel.setText(FindMessages.abstractFindLabelDialogFindLabel);
        findText = new Text(findArea, SWT.BORDER);
        final GridData findTextData = new GridData(GridData.FILL_HORIZONTAL);
        findText.setLayoutData(findTextData);
        findText.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetDefaultSelected(final SelectionEvent e) {
                nextClicked(findText.getText());
            }
        });
        createDirectionGroup(findArea);
    }

    /**
     * Create the widgets which allow the user to control the direction of the
     * search.
     */
    private void createDirectionGroup(final Composite findArea) {
        new Label(findArea, SWT.NONE).setText(FindMessages.abstractFindLabelDialogDirectionGroup);

        final Composite directionSection = new Composite(findArea, SWT.NONE);
        final GridData directionSectionData = new GridData(GridData.FILL_HORIZONTAL);
        directionSectionData.horizontalAlignment = SWT.BEGINNING;
        directionSectionData.horizontalSpan = 1;
        directionSection.setLayoutData(directionSectionData);
        final GridLayout directionGroupLayout = new GridLayout();
        directionGroupLayout.numColumns = 2;
        directionSection.setLayout(directionGroupLayout);

        createButton(directionSection, SWT.RADIO, FindMessages.abstractFindLabelDialogBackwardRadio, new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                engine.setDirection(AbstractFindLabelEngine.BACKWARD);
            }
        });

        final Button forward = createButton(directionSection, SWT.RADIO, FindMessages.abstractFindLabelDialogForwardRadio, new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                engine.setDirection(AbstractFindLabelEngine.FORWARD);
            }
        });
        forward.setSelection(true);
    }

    /**
     * Create the right side of the dialog, with the main interaction buttons.
     */
    private void createButtonsArea(final Composite container) {
        final Composite findPanel = new Composite(container, SWT.NONE);
        final GridData findPanelLayoutData = new GridData(GridData.FILL_HORIZONTAL);
        findPanelLayoutData.verticalAlignment = SWT.BEGINNING;
        findPanel.setLayoutData(findPanelLayoutData);
        final GridLayout findPanelLayout = new GridLayout();
        findPanelLayout.numColumns = 1;
        findPanel.setLayout(findPanelLayout);

        createButton(findPanel, SWT.PUSH, FindMessages.abstractFindLabelDialogNextButton, new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                nextClicked(findText.getText());
            }
        });

        createButton(findPanel, SWT.PUSH, FindMessages.abstractFindLabelDialogCancelButton, new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                close();
            }
        });
    }

    /**
     * Helper to setup a simple button.
     */
    private Button createButton(final Composite parent, final int style, final String text, final SelectionListener listener) {
        final Button b = new Button(parent, style);
        b.setText(text);
        b.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        b.addSelectionListener(listener);
        return b;
    }

    /**
     * This is called to select elements corresponding to the search.
     * 
     * @param element
     *            the Element that needs to be selected.
     */
    protected abstract void selectElement(Object element);

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected void createButtonsForButtonBar(final Composite parent) {
    }

    /**
     * Clients can override this method to hook a distinct behavior for the
     * "search" button.
     * 
     * @param search
     *            The search String used to seek objects.
     */
    protected void nextClicked(final String search) {
        if (search != null && !"".equals(search)) { //$NON-NLS-1$
            engine.initFind(search);
            final Object selection = engine.getNext();
            if (selection != null) {
                selectElement(selection);
            } else {
                MessageDialog.openError(getShell(), FindMessages.abstractFindLabelDialogErrorDialogTitle, FindMessages.abstractFindLabelDialogNoMatchingElementMessage);
            }
        }
    }

    @Override
    protected Point getInitialSize() {
        return new Point(450, 120);
    }

}
