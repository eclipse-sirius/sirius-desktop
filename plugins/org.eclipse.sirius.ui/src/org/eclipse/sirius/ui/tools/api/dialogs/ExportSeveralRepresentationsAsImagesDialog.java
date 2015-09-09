/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.api.dialogs;

import java.io.File;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.sirius.common.ui.tools.api.util.SWTUtil;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Dialog used by the export several diagrams to image files action to prompt
 * the user for a destination and image format.
 *
 * @author jdupont
 *
 */
public class ExportSeveralRepresentationsAsImagesDialog extends AbstractExportRepresentationsAsImagesDialog {

    /**
     * The id for the persistent folder setting for this dialog.
     */
    private static final String DIALOG_SETTINGS_FOLDER = "ExportRepresentationsAsImagesDialog.folder"; //$NON-NLS-1$

    /**
     * the directory dialog message.
     */
    private static final String DIRECTORY_DIALOG_MESSAGE = Messages.ExportSeveralRepresentationsAsImagesDialog_dialogMessage;

    /**
     * the directory dialog text.
     */
    private static final String DIRECTORY_DIALOG_TEXT = ExportSeveralRepresentationsAsImagesDialog.DIRECTORY_DIALOG_MESSAGE;

    /**
     * Popup file field label.
     */
    private static final String TO_DIRECTORY = Messages.ExportSeveralRepresentationsAsImagesDialog_toDirectory;

    /**
     * the dialog window title.
     */
    private static final String DIALOG_TITLE = Messages.ExportSeveralRepresentationsAsImagesDialog_dialogTitle;

    /**
     * Creates an instance of the copy to image dialog.
     *
     * @param shell
     *            the parent shell
     * @param path
     *            the default path to store the image or null
     */
    public ExportSeveralRepresentationsAsImagesDialog(Shell shell, IPath path) {
        super(shell, path);
        initDialogSettings(path);
    }

    @Override
    protected void handleBrowseButtonPressed() {
        final DirectoryDialog dialog = new DirectoryDialog(Display.getCurrent().getActiveShell());
        dialog.setMessage(ExportSeveralRepresentationsAsImagesDialog.DIRECTORY_DIALOG_MESSAGE);
        dialog.setText(ExportSeveralRepresentationsAsImagesDialog.DIRECTORY_DIALOG_TEXT);

        final String dirName = folderText.getText();
        if (!dirName.equals(AbstractExportRepresentationsAsImagesDialog.EMPTY_STRING)) {
            final File path = new File(dirName);
            if (path.exists()) {
                dialog.setFilterPath(new Path(dirName).toOSString());
            }
        }

        final String selectedDirectory = dialog.open();
        if (selectedDirectory != null) {
            folderText.setText(selectedDirectory);
        }
    }

    @Override
    protected void createFolderGroup(final Composite parent) {
        final Composite composite = SWTUtil.createCompositeHorizontalFill(parent, 4, false);
        SWTUtil.createLabel(composite, ExportSeveralRepresentationsAsImagesDialog.TO_DIRECTORY);

        folderText = new Combo(composite, SWT.SINGLE | SWT.BORDER);
        restoreWidgetValues();
        folderText.setText(folder);
        final GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
        gridData.widthHint = 350;
        folderText.setLayoutData(gridData);

        final Button button = new Button(composite, SWT.PUSH);
        button.setText(AbstractExportRepresentationsAsImagesDialog.BROWSE_LABEL);
        button.setLayoutData(AbstractExportRepresentationsAsImagesDialog.makeButtonData(button));
        button.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent event) {
                handleBrowseButtonPressed();
            }
        });
    }

    @Override
    protected void createImageFormatGroup(final Composite parent) {
        final Composite composite = SWTUtil.createCompositeHorizontalFill(parent, 3, false);
        SWTUtil.createLabel(composite, AbstractExportRepresentationsAsImagesDialog.IMAGE_FORMAT_LABEL);

        String[] imageSafeFormatItems = getImageSafeFormatItems();

        Control imageFormatControl;
        if (imageSafeFormatItems != null && imageSafeFormatItems.length > 0) {

            if (imageSafeFormatItems.length == 1) {
                imageFormatControl = SWTUtil.createLabel(composite, imageSafeFormatItems[0]);

            } else {
                imageFormatCombo = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
                imageFormatCombo.setItems(imageSafeFormatItems);
                imageFormatCombo.setText(imageFormat.getName());
                imageFormatControl = imageFormatCombo;
            }
        } else {
            imageFormatControl = SWTUtil.createLabel(composite, AbstractExportRepresentationsAsImagesDialog.DEFAULT_VALUE.getName());
        }
        final GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true, true);
        imageFormatControl.setLayoutData(gridData);

    }

    /**
     * {@inheritDoc} Configures the shell in preparation for opening this window
     * in it.
     *
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    @Override
    protected void configureShell(final Shell shell) {
        super.configureShell(shell);
        shell.setText(ExportSeveralRepresentationsAsImagesDialog.DIALOG_TITLE);
    }

    @Override
    protected void restoreWidgetValues() {
        IDialogSettings settings = getDialogSettings();
        if (settings != null) {
            String[] directoryNames = settings.getArray(ExportSeveralRepresentationsAsImagesDialog.DIALOG_SETTINGS_FOLDER);
            if (directoryNames == null || directoryNames.length == 0) {
                return; // ie.- no settings stored
            }
            folder = directoryNames[0];
            // destination
            for (String directoryName : directoryNames) {
                folderText.add(directoryName);
            }
        }
    }

    @Override
    protected void initDialogSettings(final IPath path) {
        final IDialogSettings dialogSettings = getDialogSettings();

        // By default, the folder will be the root of the filesystem, where ever
        // that may be on a system.
        folder = "/"; //$NON-NLS-1$

        if (path == null) {
            final String persistentFolder = dialogSettings.get(ExportSeveralRepresentationsAsImagesDialog.DIALOG_SETTINGS_FOLDER);
            if (persistentFolder != null) {
                folder = persistentFolder;
            }
        } else {
            folder = path.toOSString();
        }

        final String persistentImageFormat = dialogSettings.get(AbstractExportRepresentationsAsImagesDialog.DIALOG_SETTINGS_IMAGE_FORMAT);
        if (persistentImageFormat == null) {
            imageFormat = AbstractExportRepresentationsAsImagesDialog.getDefaultImageFormat();
        } else {
            imageFormat = AbstractExportRepresentationsAsImagesDialog.resolveImageFormat(persistentImageFormat);
        }
    }

    @Override
    protected void saveDialogSettings() {
        final IDialogSettings dialogSettings = getDialogSettings();
        if (dialogSettings != null) {
            String[] directoryNames = dialogSettings.getArray(ExportSeveralRepresentationsAsImagesDialog.DIALOG_SETTINGS_FOLDER);
            if (directoryNames == null || directoryNames.length == 0) {
                directoryNames = new String[1];
                directoryNames[0] = folder;
            }
            directoryNames = addToHistory(directoryNames, folder);
            dialogSettings.put(ExportSeveralRepresentationsAsImagesDialog.DIALOG_SETTINGS_FOLDER, directoryNames);
            dialogSettings.put(AbstractExportRepresentationsAsImagesDialog.DIALOG_SETTINGS_IMAGE_FORMAT, imageFormat.getName());
        }
    }

    @Override
    protected void validateFolderText() {
        super.validateFolderText();
        final File file = new File(folderText.getText());

        if (!file.exists()) {
            setDialogErrorState(AbstractExportRepresentationsAsImagesDialog.FOLDER_NOT_EXIST_MESSAGE);
            return;
        }

    }

    @Override
    protected void initListeners() {
        folderText.addModifyListener(new ModifyListener() {
            @Override
            public void modifyText(final ModifyEvent e) {
                validateFolderText();
            }
        });
        imageFormatCombo.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                imageFormat = AbstractExportRepresentationsAsImagesDialog.resolveImageFormat(imageFormatCombo.getSelectionIndex());
            }
        });
    }

}
