/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.internal.quickfix;

import java.text.MessageFormat;

import org.eclipse.core.internal.resources.OS;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.sirius.business.api.query.URIQuery;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * A dialog to replace the image paths.
 * 
 * @author lfasani
 *
 */
public class ChangeMassivelyImagePathDialog extends Dialog {

    private String oldPathPrefix;

    private String newPathPrefix;

    private Text textOldPath;

    private Text textNewPath;

    private Label invalidText;

    private Composite invalidComposite;

    private boolean processDiagramContent = true;

    /**
     * Create a new instance of {@link ChangeMassivelyImagePathDialog}.
     * 
     * @param parentShell
     *            the parent shell
     * @param oldPathPrefix
     *            the initial value of the path to replace
     */
    public ChangeMassivelyImagePathDialog(Shell parentShell, String oldPathPrefix) {
        super(parentShell);
        this.oldPathPrefix = oldPathPrefix;
        this.newPathPrefix = oldPathPrefix;
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        createWarningMessage(parent);

        Composite container = (Composite) super.createDialogArea(parent);
        GridLayout layout = new GridLayout(3, false);
        container.setLayout(layout);
        GridData layoutData = new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1);
        layoutData.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH);
        container.setLayoutData(layoutData);

        // Old path
        Label labelOldPath = new Label(container, SWT.WRAP);
        labelOldPath.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        labelOldPath.setText(Messages.ChangeMassivelyImagePathDialog_oldLabel);
        labelOldPath.setFont(container.getFont());

        Label infoIconLabelOldPath = new Label(container, SWT.NONE);
        infoIconLabelOldPath.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
        infoIconLabelOldPath.setImage(JFaceResources.getImage(Dialog.DLG_IMG_HELP));
        infoIconLabelOldPath.setToolTipText(Messages.ChangeMassivelyImagePathDialog_oldTooltip);

        textOldPath = new Text(container, SWT.SINGLE | SWT.BORDER);
        textOldPath.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
        textOldPath.setText(oldPathPrefix);
        textOldPath.setFont(container.getFont());

        textOldPath.addModifyListener(e -> {
            validateNewText();
        });

        // Old path
        Label labelNewPath = new Label(container, SWT.WRAP);
        labelNewPath.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        labelNewPath.setText(Messages.ChangeMassivelyImagePathDialog_newLabel);
        labelNewPath.setFont(container.getFont());

        Label infoIconLabelNewPath = new Label(container, SWT.NONE);
        infoIconLabelNewPath.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
        infoIconLabelNewPath.setImage(JFaceResources.getImage(Dialog.DLG_IMG_HELP));
        infoIconLabelNewPath.setToolTipText(Messages.ChangeMassivelyImagePathDialog_newTooltip);

        textNewPath = new Text(container, SWT.SINGLE | SWT.BORDER);
        textNewPath.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
        textNewPath.setText(newPathPrefix);
        textNewPath.setFont(container.getFont());

        textNewPath.addModifyListener(e -> {
            validateNewText();
        });

        invalidComposite = new Composite(container, SWT.NONE);
        invalidComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 3, 1));

        invalidComposite.setLayout(new GridLayout(2, false));
        invalidComposite.setVisible(false);

        Label errorIcon = new Label(invalidComposite, SWT.NONE);
        errorIcon.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        errorIcon.setImage(JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_ERROR));

        invalidText = new Label(invalidComposite, SWT.WRAP);
        invalidText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1));

        Button processDiagramContentbutton = new Button(container, SWT.CHECK);
        processDiagramContentbutton.setLayoutData(new GridData(SWT.BEGINNING, SWT.FILL, true, false, 3, 1));
        processDiagramContentbutton.setText(Messages.ChangeMassivelyImagePathDialog_processDiagramContentLabel);
        processDiagramContentbutton.setToolTipText(Messages.ChangeMassivelyImagePathDialog_processDiagramContentTooltip);
        processDiagramContentbutton.setSelection(processDiagramContent);
        processDiagramContentbutton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                processDiagramContent = ((Button) e.widget).getSelection();
            }
        });

        return container;
    }

    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        super.createButtonsForButtonBar(parent);
        getButton(IDialogConstants.OK_ID).setEnabled(false);
    }

    private void validateNewText() {
        String text = textNewPath.getText();

        String cdoPrefix = URIQuery.CDO_URI_SCHEME + ":/"; //$NON-NLS-1$
        String path = text.startsWith(cdoPrefix) ? text.substring(cdoPrefix.length()) : text;

        boolean invalid = false;
        if (path.startsWith("/")) { //$NON-NLS-1$
            invalidText.setText(Messages.ChangeMassivelyImagePathDialog_badLeadingCharacter);
            invalid = true;
        } else {
            if (!path.isEmpty()) {
                String[] segments = path.split("/"); //$NON-NLS-1$
                char[] chars = OS.INVALID_RESOURCE_CHARACTERS;
                for (String segment : segments) {
                    if (segment.isEmpty()) {
                        invalidText.setText(MessageFormat.format(Messages.ChangeMassivelyImagePathDialog_invalidCharInName, String.valueOf("/"))); //$NON-NLS-1$
                        invalid = true;
                        break;
                    } else {
                        for (char c : chars) {
                            if (segment.indexOf(c) != -1) {
                                invalidText.setText(MessageFormat.format(Messages.ChangeMassivelyImagePathDialog_invalidCharInName, String.valueOf(c))); // $NON-NLS-1$
                                invalid = true;
                                break;
                            }
                        }
                        if (invalid == true) {
                            break;
                        }
                    }
                }
            }
        }

        if (invalid) {
            invalidComposite.setVisible(true);
            getButton(IDialogConstants.OK_ID).setEnabled(false);
        } else {
            invalidText.setText(""); //$NON-NLS-1$
            invalidComposite.setVisible(false);
            getButton(IDialogConstants.OK_ID).setEnabled(!textOldPath.getText().equals(textNewPath.getText()) && !textNewPath.getText().isBlank());
        }
    }

    /**
     * Create and return the warning composite, if there is a message to display.
     * 
     * @param parent
     *            the parent Composite.
     * @return the created composite if it exists; null otherwise
     */
    private Control createWarningMessage(Composite parent) {
        Composite warningComposite = null;
        warningComposite = new Composite(parent, SWT.NONE);
        warningComposite.setLayout(new GridLayout(2, false));
        GridData layoutData = new GridData(SWT.FILL, SWT.TOP, true, true, 1, 1);
        // layoutData.width = 600;
        warningComposite.setLayoutData(layoutData);

        Label descriptionWarning = new Label(warningComposite, SWT.NONE);
        descriptionWarning.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        descriptionWarning.setImage(JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_WARNING));

        Label descriptionLabel = new Label(warningComposite, SWT.WRAP);
        descriptionLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        descriptionLabel.setText(Messages.ChangeMassivelyImagePathDialog_warning);
        return warningComposite;

    }

    @Override
    protected void buttonPressed(int buttonId) {
        if (buttonId == IDialogConstants.OK_ID) {
            oldPathPrefix = textOldPath.getText();
            newPathPrefix = textNewPath.getText();
        }
        super.buttonPressed(buttonId);
    }

    @Override
    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText(Messages.ChangeMassivelyImagePathDialog_title);
    }

    @Override
    protected boolean isResizable() {
        return false;
    }

    public String getNewPathPrefix() {
        return newPathPrefix;
    }

    public String getOldPathPrefix() {
        return oldPathPrefix;
    }

    public boolean processDiagramContent() {
        return processDiagramContent;
    }
}
