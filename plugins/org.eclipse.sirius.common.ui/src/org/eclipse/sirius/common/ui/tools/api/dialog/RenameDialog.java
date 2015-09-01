/*******************************************************************************
 * Copyright (c) 2000, 2006, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.ui.tools.api.dialog;

import java.util.ArrayList;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.ui.Messages;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.SelectionStatusDialog;

/**
 * A generic rename dialog.
 * 
 * @author mchauvin
 */
public class RenameDialog extends SelectionStatusDialog {
    private ArrayList<String> oldNames;

    private String oldName;

    private String newName;

    private Text text;

    private IStatus status;

    private boolean isCaseSensitive;

    private IInputValidator fValidator;

    /**
     * Create a new rename dialog instance for the given window.
     * 
     * @param shell
     *            The parent of the dialog
     * @param oldName
     *            Current name of the item being renamed
     */
    public RenameDialog(final Shell shell, final String oldName) {
        this(shell, false, null, oldName);
    }

    /**
     * Create a new rename dialog instance for the given window.
     * 
     * @param shell
     *            The parent of the dialog
     * @param isCaseSensitive
     *            Flags whether dialog will perform case sensitive checks
     *            against old names
     * @param oldName
     *            Current name of the item being renamed
     */
    public RenameDialog(final Shell shell, final boolean isCaseSensitive, final String oldName) {
        this(shell, isCaseSensitive, null, oldName);
    }

    /**
     * Create a new rename dialog instance for the given window.
     * 
     * @param shell
     *            The parent of the dialog
     * @param isCaseSensitive
     *            Flags whether dialog will perform case sensitive checks
     *            against old names
     * @param names
     *            Set of names which the user should not be allowed to rename to
     * @param oldName
     *            Current name of the item being renamed
     */
    public RenameDialog(final Shell shell, final boolean isCaseSensitive, final String[] names, final String oldName) {
        super(shell);
        setTitle(Messages.RenameDialog_title);
        this.isCaseSensitive = isCaseSensitive;
        initialize();
        if (names != null) {
            for (String name : names) {
                addOldName(name);
            }
        }
        setOldName(oldName);
        setHelpAvailable(false);
    }

    /**
     * Init the old name list.
     */
    public void initialize() {
        oldNames = new ArrayList<String>();
        setStatusLineAboveButtons(true);
    }

    /**
     * Add an old name.
     * 
     * @param name
     *            the name to add to the old name set
     */
    public void addOldName(final String name) {
        if (!oldNames.contains(name)) {
            oldNames.add(name);
        }

    }

    /**
     * Set the old name.
     * 
     * @param oldName
     *            the name to set as old name
     */
    public void setOldName(final String oldName) {
        this.oldName = oldName;
        addOldName(oldName);
        if (text != null) {
            text.setText(oldName);
        }
        this.newName = oldName;
    }

    /**
     * Set the default new name to display. This allows to directly set a new
     * name instead of the old one. This method must be called before Dialog
     * creation ({@link #create()}).
     * 
     * @param value
     *            the value to set
     * @since 2.0.0
     */
    public void setDefaultNewName(final String value) {
        this.newName = value;
    }

    @Override
    protected Control createDialogArea(final Composite parent) {
        final Composite container = new Composite(parent, SWT.NULL);
        final GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        layout.marginHeight = 9;
        layout.marginWidth = 9;

        container.setLayout(layout);

        GridData gd = new GridData(GridData.FILL_BOTH);
        container.setLayoutData(gd);

        final Label label = new Label(container, SWT.NULL);

        String labelText;
        if (!StringUtil.isEmpty(getMessage())) {
            labelText = getMessage();
        } else {
            if (isCaseSensitive) {
                labelText = Messages.RenameDialog_askNewName_caseSensitive;
            } else {
                labelText = Messages.RenameDialog_askNewName_caseInsensitive;
            }
        }
        label.setText(labelText);

        text = new Text(container, SWT.SINGLE | SWT.BORDER);
        if (newName != null) {
            // A default new name has been set
            text.setText(newName);
        } else {
            text.setText(oldName);
        }
        text.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                textChanged(text.getText());
            }
        });
        gd = new GridData(GridData.FILL_HORIZONTAL);
        text.setLayoutData(gd);
        Dialog.applyDialogFont(container);
        return container;
    }

    @Override
    public int open() {
        // Validate the name to enable or not the OK button.
        textChanged(text.getText());
        // Select the entered name
        text.selectAll();
        return super.open();
    }

    private void textChanged(final String newText) {
        final Button okButton = getButton(IDialogConstants.OK_ID);
        if (fValidator != null) {
            final String message = fValidator.isValid(newText);
            if (message != null) {
                status = new Status(IStatus.ERROR, SiriusTransPlugin.PLUGIN_ID, IStatus.ERROR, message, null);
                updateStatus(status);
                okButton.setEnabled(false);
                return;
            }
        }
        for (int i = 0; i < oldNames.size(); i++) {
            if ((isCaseSensitive && newText.equals(oldNames.get(i))) || (!isCaseSensitive && newText.equalsIgnoreCase(oldNames.get(i).toString()))) {
                status = new Status(IStatus.ERROR, SiriusTransPlugin.PLUGIN_ID, IStatus.ERROR, Messages.RenameDialog_errorNameClash, null);
                updateStatus(status);
                okButton.setEnabled(false);
                break;
            }
            okButton.setEnabled(true);
            status = new Status(IStatus.OK, SiriusTransPlugin.PLUGIN_ID, IStatus.OK, "", //$NON-NLS-1$
                    null);
            updateStatus(status);
        }
    }

    /**
     * Get the new entered name.
     * 
     * @return the new name.
     */
    public String getNewName() {
        return newName;
    }

    @Override
    protected void okPressed() {
        newName = text.getText().trim();
        super.okPressed();
    }

    @Override
    protected void computeResult() {
    }

    /**
     * Set an input validator.
     * 
     * @param validator
     *            the input validator.
     */
    public void setInputValidator(final IInputValidator validator) {
        fValidator = validator;
    }
}
