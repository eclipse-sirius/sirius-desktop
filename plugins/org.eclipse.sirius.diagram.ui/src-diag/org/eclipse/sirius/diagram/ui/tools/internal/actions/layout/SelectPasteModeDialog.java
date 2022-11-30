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
package org.eclipse.sirius.diagram.ui.tools.internal.actions.layout;

import static org.eclipse.swt.events.SelectionListener.widgetSelectedAdapter;

import java.util.Optional;

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * A dialog, inspired from {@link org.eclipse.jface.dialogs.MessageDialogWithToggle}, to ask the user which "Paste mode"
 * to use, and store the result in the preference.
 * 
 * @author Laurent Redor
 */
public class SelectPasteModeDialog extends MessageDialog {
    /**
     * A previous result to avoid to ask the question if several objects are selected in the target diagram. This value
     * must be reset by the PasteFormatAction or PasteLayoutAction through method {@link #tearDownPromptResult()}.
     */
    private static Optional<Boolean> previousPromptResult = Optional.empty();

    private Button absoluteRadioButton;

    private Button boundingBoxRadioButton;

    /**
     * The selected state of the remember button.
     */
    private boolean rememberState;

    /**
     * The preferences to use to get and store values.
     */
    private IPreferenceStore diagUiPreferenceStore = DiagramUIPlugin.getPlugin().getPreferenceStore();

    /**
     * Create a message dialog to prompt which mode to use to Paste layout. Note that the dialog will have no visual
     * representation (no widgets) until it is told to open.
     * 
     * @param parentShell
     *            the parent shell, or <code>null</code> to create a top-level shell
     */
    public SelectPasteModeDialog(Shell parentShell) {
        super(parentShell, Messages.SelectPasteModeDialog_title, null, Messages.SelectPasteModeDialog_message, MessageDialog.NONE,
                new String[] { Messages.SelectPasteModeDialog_pasteButtonLabel, IDialogConstants.CANCEL_LABEL }, IDialogConstants.OK_ID);
    }

    /**
     * Returns the preference for whether to use the "Absolute" paste mode, or the "Bounding box" paste mode. Consults
     * the preference and prompts the user if necessary (a bit like in
     * {@link org.eclipse.ui.actions.OpenResourceAction#promptToOpenWithReferences()}).<BR/>
     * As soon as the use of {@link SelectPasteModeDialog} is finished in the scope of an action, the method
     * {@link #tearDownPromptResult()} must be called.
     *
     * @return <code>true</code> if the "Absolute" paste mode should be used, and <code>false</code> otherwise, ie if
     *         the "Bounding box" paste mode should be used.
     * @throws OperationCanceledException
     *             is the user clicks on Cancel
     */
    public static boolean promptIsAbsolutePasteMode(Shell shell) {
        IPreferenceStore preferenceStore = DiagramUIPlugin.getPlugin().getPreferenceStore();
        boolean shouldPrompt = preferenceStore.getBoolean(SiriusDiagramUiPreferencesKeys.PREF_PROMPT_PASTE_MODE.name());
        boolean useAbsolutePasteMode = preferenceStore.getBoolean(SiriusDiagramUiPreferencesKeys.PREF_PASTE_MODE_ABSOLUTE.name());
        if (!shouldPrompt) {
            return useAbsolutePasteMode;
        }
        if (previousPromptResult.isEmpty()) {
            MessageDialog dialog = new SelectPasteModeDialog(shell);
            dialog.open();
            int result = dialog.getReturnCode();
            // the result is equal to SWT.DEFAULT if the user uses the 'esc' key to close the dialog
            if (result == Window.CANCEL || result == SWT.DEFAULT) {
                throw new OperationCanceledException();
            }
            // The dialog has changed the preference, so return it.
            previousPromptResult = Optional.of(preferenceStore.getBoolean(SiriusDiagramUiPreferencesKeys.PREF_PASTE_MODE_ABSOLUTE.name()));
        }

        return previousPromptResult.get();
    }

    /**
     * Allow to reset a previous stored result. This method should be called as soon as the use of
     * {@link SelectPasteModeDialog} is finished in the scope of an action.
     */
    public static void tearDownPromptResult() {
        previousPromptResult = Optional.empty();
    }

    @Override
    protected void buttonPressed(int buttonId) {
        boolean isAbsoluteRadioButtonSelected = absoluteRadioButton.getSelection();
        super.buttonPressed(buttonId);

        if (buttonId == IDialogConstants.OK_ID) {
            if (rememberState) {
                diagUiPreferenceStore.setValue(SiriusDiagramUiPreferencesKeys.PREF_PROMPT_PASTE_MODE.name(), rememberState);
            }
            if (isAbsoluteRadioButtonSelected) {
                diagUiPreferenceStore.setValue(SiriusDiagramUiPreferencesKeys.PREF_PASTE_MODE_ABSOLUTE.name(), true);
            } else {
                diagUiPreferenceStore.setValue(SiriusDiagramUiPreferencesKeys.PREF_PASTE_MODE_ABSOLUTE.name(), false);
            }

        }
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite dialogAreaComposite = (Composite) super.createDialogArea(parent);
        createToggleButton(dialogAreaComposite);
        return dialogAreaComposite;
    }

    @Override
    protected Control createCustomArea(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 10;
        composite.setLayout(layout);
        GridData data = new GridData(GridData.FILL_BOTH);
        data.horizontalSpan = 2;
        composite.setLayoutData(data);
        // Create the radio buttons
        absoluteRadioButton = new Button(composite, SWT.RADIO | SWT.LEFT);
        absoluteRadioButton.setText(Messages.SelectPasteModeDialog_absoluteModeLabel);
        absoluteRadioButton.setToolTipText(Messages.SelectPasteModeDialog_absoluteModeTooltip);
        absoluteRadioButton.setFont(parent.getFont());
        boundingBoxRadioButton = new Button(composite, SWT.RADIO | SWT.LEFT);
        boundingBoxRadioButton.setText(Messages.SelectPasteModeDialog_boundingBoxModeLabel);
        boundingBoxRadioButton.setToolTipText(Messages.SelectPasteModeDialog_boundingBoxModeTooltip);
        boundingBoxRadioButton.setFont(parent.getFont());
        // No selection listener is added to the radio buttons, the value will be stored only on buttonPressed method
        if (diagUiPreferenceStore.getBoolean(SiriusDiagramUiPreferencesKeys.PREF_PASTE_MODE_ABSOLUTE.name())) {
            absoluteRadioButton.setSelection(true);
        } else {
            boundingBoxRadioButton.setSelection(true);
        }

        GridDataFactory.fillDefaults().align(SWT.FILL, SWT.BEGINNING).grab(true, false).hint(convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH), SWT.DEFAULT)
                .applyTo(absoluteRadioButton);
        GridDataFactory.fillDefaults().align(SWT.FILL, SWT.BEGINNING).grab(true, false).hint(convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH), SWT.DEFAULT)
                .applyTo(boundingBoxRadioButton);
        return composite;
    }

    /**
     * Creates a toggle button without any text or state. The text and state will be created by
     * <code>createDialogArea</code>.
     *
     * @param parent
     *            The composite in which the toggle button should be placed; must not be <code>null</code>.
     * @return The added toggle button; never <code>null</code>.
     */
    protected Button createToggleButton(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.marginHeight = 10;
        layout.marginWidth = 0;
        composite.setLayout(layout);
        GridData data = new GridData(GridData.FILL_BOTH);
        data.horizontalSpan = 2;
        composite.setLayoutData(data);
        // Create the "remember me" check box
        final Button button = new Button(composite, SWT.CHECK | SWT.LEFT);
        button.setText(Messages.SelectPasteModeDialog_rememberButtonLabel);
        button.setToolTipText(Messages.SelectPasteModeDialog_rememberButtonTooltip);
        button.setSelection(rememberState);
        data = new GridData(SWT.NONE);
        button.setLayoutData(data);
        button.setFont(parent.getFont());

        button.addSelectionListener(widgetSelectedAdapter(e -> rememberState = button.getSelection()));

        return button;
    }
}
