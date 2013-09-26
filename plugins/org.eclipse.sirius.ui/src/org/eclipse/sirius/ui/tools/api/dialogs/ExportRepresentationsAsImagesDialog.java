/******************************************************************************
 * Copyright (c) 2006, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation
 *    Mariot Chauvin   (Obeo) <mariot.chauvin@obeo.fr>   - modifications to export multiple images
 ****************************************************************************/

package org.eclipse.sirius.ui.tools.api.dialogs;

import java.io.File;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.sirius.common.ui.tools.api.util.SWTUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat.ExportDocumentFormat;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;

/**
 * Dialog used by the export diagram to image files action to prompt the user
 * for a destination and image format.
 * 
 * @author Mariot Chauvin
 */
@Deprecated
public class ExportRepresentationsAsImagesDialog extends Dialog {

    private static final ImageFileFormat DEFAULT_VALUE = ImageFileFormat.JPG;

    /**
     * The list of values for this enumerated type.
     */
    private static final ImageFileFormat[] SAFE_VALUES = { DEFAULT_VALUE, ImageFileFormat.PNG, ImageFileFormat.SVG };

    /**
     * the dialog window title.
     */
    private static final String DIALOG_TITLE = "Export representations as image file";

    /**
     * the folder label text.
     */
    private static final String FOLDER_LABEL = "F&older:";

    /**
     * the image format label text.
     */
    private static final String IMAGE_FORMAT_LABEL = "&Image Format:";

    /**
     * the browse button text.
     */
    private static final String BROWSE_LABEL = "&Browse...";

    /**
     * the browse button text.
     */
    private static final String EXPORT_TO_HTML_LABEL = "Export to HTML";

    /**
     * the directory dialog message.
     */
    private static final String DIRECTORY_DIALOG_MESSAGE = "Select the destination folder";

    /**
     * the directory dialog text.
     */
    private static final String DIRECTORY_DIALOG_TEXT = DIRECTORY_DIALOG_MESSAGE;

    /**
     * an error message.
     */
    private static final String FOLDER_BLANK_MESSAGE = "Folder cannot be blank";

    /**
     * an error message.
     */
    private static final String FOLDER_INVALID_MESSAGE = "Folder is not a valid path";

    /**
     * an error message.
     */
    private static final String FOLDER_NOT_EXIST_MESSAGE = "Folder does not exist";

    /**
     * The empty string.
     */
    private static final String EMPTY_STRING = ""; //$NON-NLS-1$

    /**
     * The id for the persistent settings for this dialog.
     */
    private static final String DIALOG_SETTINGS_ID = "ExportRepresentationsAsImagesDialog"; //$NON-NLS-1$

    /**
     * The id for the persistent folder setting for this dialog.
     */
    private static final String DIALOG_SETTINGS_FOLDER = "ExportRepresentationsAsImagesDialog.folder"; //$NON-NLS-1$

    /**
     * The id for the persistent image format setting for this dialog.
     */
    private static final String DIALOG_SETTINGS_IMAGE_FORMAT = "ExportRepresentationsAsImagesDialog.imageFormat"; //$NON-NLS-1$

    /**
     * the text entered into the folder text field
     */
    private String folder;

    /**
     * the image format selected in the image format pulldown field
     */
    private ImageFileFormat imageFormat;

    /**
     * the folder text field
     */
    private Text folderText;

    /**
     * the message image field, displays the error (X) icon when the file name
     * or folder is invalid
     */
    private Label messageImageLabel;

    /**
     * the message field, displays an error message when the file name or folder
     * is invalid
     */
    private Label messageLabel;

    /**
     * the export to HTML checkbox.
     */
    private Button exportToHTMLCheckbox;

    /**
     * true to export to HTML.
     */
    private boolean exportToHTML;

    /**
     * Creates an instance of the copy to image dialog.
     * 
     * @param shell
     *            the parent shell
     * @param path
     *            the default path to store the image or null
     */
    public ExportRepresentationsAsImagesDialog(final Shell shell, final IPath path) {
        super(shell);
        initDialogSettings(path);
    }

    /**
     * {@inheritDoc} Creates and returns the contents of the upper part of this
     * dialog (above the button bar).
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(final Composite parent) {
        final Composite composite = (Composite) super.createDialogArea(parent);
        createFolderGroup(composite);
        createImageFormatGroup(composite);
        if (DialectUIManager.INSTANCE.canExport(new ExportFormat(ExportDocumentFormat.HTML, null))) {
            createGenerateHTMLGroup(composite);
        }
        createMessageGroup(composite);

        return composite;
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
        shell.setText(DIALOG_TITLE);
    }

    /**
     * Create the folder group in the dialog.
     * 
     * @param parent
     *            the parent widget
     */
    private void createFolderGroup(final Composite parent) {
        final Composite composite = SWTUtil.createCompositeHorizontalFill(parent, 4, false);
        SWTUtil.createLabel(composite, FOLDER_LABEL);

        folderText = new Text(composite, SWT.BORDER);
        folderText.setText(folder);
        folderText.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                validateFolderText();
            }
        });
        final GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
        gridData.widthHint = 350;
        folderText.setLayoutData(gridData);

        final Button button = new Button(composite, SWT.PUSH);
        button.setText(BROWSE_LABEL);
        button.setLayoutData(ExportRepresentationsAsImagesDialog.makeButtonData(button));
        button.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent event) {
                handleBrowseButtonPressed();
            }
        });
    }

    /**
     * Returns height and width data in a GridData for the button that was
     * passed in. You can call button.setLayoutData with the returned data.
     * 
     * @param button
     *            which has already been made. We'll be making the GridData for
     *            this button, so be sure that the text has already been set.
     * @return GridData for this button with the suggested height and width
     */
    public static GridData makeButtonData(Button button) {
        GC gc = new GC(button);
        gc.setFont(button.getFont());

        GridData data = new GridData();
        data.heightHint = Dialog.convertVerticalDLUsToPixels(gc.getFontMetrics(), 14);
        data.widthHint = Math.max(Dialog.convertHorizontalDLUsToPixels(gc.getFontMetrics(), IDialogConstants.BUTTON_WIDTH), button.computeSize(SWT.DEFAULT, SWT.DEFAULT, true).x);

        gc.dispose();

        return data;
    }

    /**
     * Create the image format group in the dialog.
     * 
     * @param parent
     *            the parent widget
     */
    private void createImageFormatGroup(final Composite parent) {
        final Composite composite = SWTUtil.createCompositeHorizontalFill(parent, 3, false);
        SWTUtil.createLabel(composite, IMAGE_FORMAT_LABEL);

        String[] imageSafeFormatItems = getImageSafeFormatItems();

        Control imageFormatControl;
        if (imageSafeFormatItems != null && imageSafeFormatItems.length > 0) {

            if (imageSafeFormatItems.length == 1) {
                imageFormatControl = SWTUtil.createLabel(composite, imageSafeFormatItems[0]);

            } else {
                final Combo imageFormatCombo = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
                imageFormatCombo.setItems(imageSafeFormatItems);
                imageFormatCombo.setText(imageFormat.getName());
                imageFormatCombo.addSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetSelected(final SelectionEvent e) {
                        imageFormat = ExportRepresentationsAsImagesDialog.resolveImageFormat(imageFormatCombo.getSelectionIndex());
                    }
                });
                imageFormatControl = imageFormatCombo;
            }
        } else {
            imageFormatControl = SWTUtil.createLabel(composite, DEFAULT_VALUE.getName());
        }
        final GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true, true);
        imageFormatControl.setLayoutData(gridData);

    }

    private void createGenerateHTMLGroup(Composite parent) {

        Composite composite = SWTUtil.createCompositeHorizontalFill(parent, 1, false);
        exportToHTMLCheckbox = new Button(composite, SWT.CHECK | SWT.LEFT);
        exportToHTMLCheckbox.setText(EXPORT_TO_HTML_LABEL);
        GridData data = new GridData(GridData.FILL_HORIZONTAL);
        exportToHTMLCheckbox.setLayoutData(data);
        exportToHTMLCheckbox.setSelection(exportToHTML);
        exportToHTMLCheckbox.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent event) {
                exportToHTML = exportToHTMLCheckbox.getSelection();
            }
        });
    }

    /**
     * Create the message group in the dialog used to display error messages.
     * 
     * @param parent
     *            the parent widget
     */
    private void createMessageGroup(final Composite parent) {
        final Composite composite = SWTUtil.createCompositeHorizontalFill(parent, 2, false);

        messageImageLabel = new Label(composite, SWT.NONE);
        messageImageLabel.setImage(JFaceResources.getImage(DLG_IMG_MESSAGE_ERROR));
        messageImageLabel.setVisible(false);

        messageLabel = new Label(composite, SWT.NONE);
        final GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.FILL_HORIZONTAL);
        gridData.widthHint = 300;
        messageLabel.setLayoutData(gridData);
        messageLabel.setVisible(false);
    }

    /**
     * get the supported image formats from the enumerated type.
     * 
     * @return array of supported image formats.
     */
    private String[] getImageSafeFormatItems() {
        final String[] items = new String[ExportRepresentationsAsImagesDialog.SAFE_VALUES.length];
        for (int i = 0; i < ExportRepresentationsAsImagesDialog.SAFE_VALUES.length; i++) {
            items[i] = ExportRepresentationsAsImagesDialog.SAFE_VALUES[i].getName();
        }
        return items;
    }

    /**
     * Returns the output path.
     * 
     * @return folder
     */
    public IPath getOutputPath() {
        return new Path(folder);
    }

    /**
     * Returns the destination image file format selected by the user.
     * 
     * @return the selected image file format.
     */
    public ImageFileFormat getImageFormat() {
        return imageFormat;
    }

    /**
     * handle a browse button pressed selection.
     */
    private void handleBrowseButtonPressed() {
        final DirectoryDialog dialog = new DirectoryDialog(Display.getCurrent().getActiveShell());
        dialog.setMessage(DIRECTORY_DIALOG_MESSAGE);
        dialog.setText(DIRECTORY_DIALOG_TEXT);

        final String dirName = folderText.getText();
        if (!dirName.equals(EMPTY_STRING)) {
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

    /**
     * validate the folder text field.
     */
    private void validateFolderText() {

        if (folderText.getText().equals(EMPTY_STRING)) {

            setDialogErrorState(FOLDER_BLANK_MESSAGE);

        } else {

            final IPath path = new Path(EMPTY_STRING);
            if (!path.isValidPath(folderText.getText())) {
                setDialogErrorState(FOLDER_INVALID_MESSAGE);
                return;
            }

            final File file = new File(folderText.getText());
            if (!file.exists()) {
                setDialogErrorState(FOLDER_NOT_EXIST_MESSAGE);
                return;
            }

            folder = folderText.getText();
            setDialogOKState();
        }
    }

    /**
     * Set the dialog into error state mode. The error image (x) label and error
     * label are made visible and the ok button is disabled.
     * 
     * @param message
     *            the error message
     */
    private void setDialogErrorState(final String message) {
        messageLabel.setText(message);
        messageImageLabel.setVisible(true);
        messageLabel.setVisible(true);
        getButton(IDialogConstants.OK_ID).setEnabled(false);
        getButton(IDialogConstants.CANCEL_ID).getShell().setDefaultButton(getButton(IDialogConstants.CANCEL_ID));
    }

    /**
     * Set the dialog into ok state mode. The error image (x) label and error
     * label and made not visible and the ok button is enabled.
     */
    private void setDialogOKState() {
        messageImageLabel.setVisible(false);
        messageLabel.setVisible(false);
        getButton(IDialogConstants.OK_ID).setEnabled(true);
        getButton(IDialogConstants.OK_ID).getShell().setDefaultButton(getButton(IDialogConstants.OK_ID));
    }

    /**
     * Retrieves the persistent settings for this dialog.
     * 
     * @return the persistent settings for this dialog.
     */
    private IDialogSettings getDialogSettings() {
        IDialogSettings settings = SiriusEditPlugin.getPlugin().getDialogSettings();
        settings = settings.getSection(DIALOG_SETTINGS_ID);
        if (settings == null) {
            settings = SiriusEditPlugin.getPlugin().getDialogSettings().addNewSection(DIALOG_SETTINGS_ID);
        }
        return settings;
    }

    /**
     * Initialize the settings for this dialog.
     * 
     * @param path
     *            the default path to use for the diagram file or null if the
     *            dialog should use some default path.
     */
    private void initDialogSettings(final IPath path) {
        final IDialogSettings dialogSettings = getDialogSettings();

        // By default, the folder will be the root of the filesystem, where ever
        // that may be on a system.
        folder = "/"; //$NON-NLS-1$

        if (path == null) {
            final String persistentFolder = dialogSettings.get(DIALOG_SETTINGS_FOLDER);
            if (persistentFolder != null) {
                folder = persistentFolder;
            }
        } else {
            folder = path.toOSString();
        }

        final String persistentImageFormat = dialogSettings.get(DIALOG_SETTINGS_IMAGE_FORMAT);
        if (persistentImageFormat == null) {
            imageFormat = ExportRepresentationsAsImagesDialog.getDefaultImageFormat();
        } else {
            imageFormat = ExportRepresentationsAsImagesDialog.resolveImageFormat(persistentImageFormat);
        }

    }

    /**
     * Retrieves the persistent settings for this dialog.
     */
    private void saveDialogSettings() {
        final IDialogSettings dialogSettings = getDialogSettings();
        dialogSettings.put(DIALOG_SETTINGS_FOLDER, folder);
        dialogSettings.put(DIALOG_SETTINGS_IMAGE_FORMAT, imageFormat.getName().toLowerCase());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.dialogs.Dialog#okPressed()
     */
    @Override
    protected void okPressed() {
        super.okPressed();
        saveDialogSettings();
    }

    /**
     * resolve the selected image format to an enumerated type.
     * 
     * @param ordinal
     *            the selected format in the pulldown
     * @return the image format enumerated type
     */
    public static ImageFileFormat resolveImageFormat(final int ordinal) {
        return ExportRepresentationsAsImagesDialog.SAFE_VALUES[ordinal];
    }

    /**
     * Resolve the selected image format to an enumerated type.
     * 
     * @param imageFormat
     *            the selected format.
     * @return the image format enumerated type
     */
    public static ImageFileFormat resolveImageFormat(final String imageFormat) {
        for (ImageFileFormat element : ExportRepresentationsAsImagesDialog.SAFE_VALUES) {
            if (element.getName().toLowerCase().equals(imageFormat.toLowerCase())) {
                return element;
            }
        }

        return ExportRepresentationsAsImagesDialog.getDefaultImageFormat();
    }

    /**
     * Retrieves the default image format.
     * 
     * @return the default image format.
     */
    public static ImageFileFormat getDefaultImageFormat() {
        return ImageFileFormat.JPG;
    }

    /**
     * Check if the user choose to export as Html.
     * 
     * @return <code>true</code> if it decided to export as Html ,
     *         <code>false</code> otherwise
     */
    public boolean isExportToHtml() {
        return exportToHTML;
    }
}
