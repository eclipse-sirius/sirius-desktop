/*
 * Copyright (c) 2005-2008 Obeo
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 */

package org.eclipse.sirius.common.ui.tools.api.dialog;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.sirius.common.ui.Messages;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;

/**
 * A standard file creation dialog.
 * 
 * @author www.obeo.fr
 * 
 */
public class NewFileDialog extends FolderSelectionDialog {

    /**
     * The widget to type the new file name.
     */
    private Text newFileNameText;

    /**
     * The new file name.
     */
    private String newFileName;

    /**
     * Constructor.
     * 
     * @param defaultName
     *            is the default name of the file to create
     */
    public NewFileDialog(final String defaultName) {
        super(Messages.NewFileDialog_message);
        setTitle(Messages.NewFileDialog_title);
        newFileName = (defaultName != null) ? defaultName : ""; //$NON-NLS-1$
    }

    /**
     * Returns the file name.
     * 
     * @return the new file name
     */
    public String getNewFileName() {
        return newFileName;
    }

    @Override
    protected Control createDialogArea(final Composite parent) {
        final Control result = super.createDialogArea(parent);
        final Composite composite = new Composite(parent, SWT.NONE);
        final GridLayout layout = new GridLayout();
        layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
        layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
        layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
        layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
        composite.setLayout(layout);
        composite.setLayoutData(new GridData(GridData.FILL_BOTH));
        final Label label = new Label(composite, SWT.NULL);
        label.setText(Messages.NewFileDialog_fileLabel);
        newFileNameText = new Text(composite, SWT.BORDER);
        final GridData data = new GridData(GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL);
        data.heightHint = newFileNameText.getLineHeight();
        data.widthHint = newFileNameText.getLineHeight() * 30;
        newFileNameText.setLayoutData(data);
        newFileNameText.setText(newFileName);
        newFileNameText.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                updateOKStatus();
            }
        });
        return result;
    }

    @Override
    protected void updateOKStatus() {
        if (newFileNameText == null || newFileNameText.getText() == null || newFileNameText.getText().trim().length() == 0) {
            newFileName = ""; //$NON-NLS-1$
            updateStatus(new Status(IStatus.ERROR, SiriusTransPlugin.PLUGIN_ID, IStatus.ERROR, Messages.NewFileDialog_selectFileStatus, null));
        } else {
            newFileName = newFileNameText.getText();
            super.updateOKStatus();
        }
    }

}
