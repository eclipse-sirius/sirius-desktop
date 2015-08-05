/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.ui.tools.api.dialog;

import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

/**
 * This class overrides MessageDialogWithToggle be cause it does not manage the
 * provided button with custom labels (Different to Yes/No label for instance).
 * Notice that contrary to its superclass, the Toggle is not always displayed
 * (depends on constructor parameters).
 * 
 * @author smonnier
 */
public class SiriusMessageDialogWithToggle extends MessageDialogWithToggle {

    private Map<String, Integer> buttonsMap;

    /**
     * Indicates whether the ToogleArea should be created or not.
     */
    private boolean createToogleArea;

    /**
     * {@inheritDoc}.
     * 
     * @param parentShell
     *            the parent shell
     * @param dialogTitle
     *            the dialog title, or <code>null</code> if none
     * @param image
     *            the dialog title image, or <code>null</code> if none
     * @param message
     *            the dialog message
     * @param dialogImageType
     *            one of the following values:
     *            <ul>
     *            <li><code>MessageDialog.NONE</code> for a dialog with no image
     *            </li>
     *            <li><code>MessageDialog.ERROR</code> for a dialog with an
     *            error image</li>
     *            <li><code>MessageDialog.INFORMATION</code> for a dialog with
     *            an information image</li>
     *            <li><code>MessageDialog.QUESTION </code> for a dialog with a
     *            question image</li>
     *            <li><code>MessageDialog.WARNING</code> for a dialog with a
     *            warning image</li>
     *            </ul>
     * @param buttons
     *            an map of label/ID for the buttons in the button bar
     * @param defaultIndex
     *            the index in the button label array of the default button
     * @param toggleMessage
     *            the message for the toggle control, or <code>null</code> for
     *            the default message
     * @param toggleState
     *            the initial state for the toggle
     * @param createToogleArea
     *            indicates whether the toogle area should be created or not
     * 
     */
    // CHECKSTYLE:OFF
    public SiriusMessageDialogWithToggle(Shell parentShell, String dialogTitle, Image image, String message, int dialogImageType, Map<String, Integer> buttons, int defaultIndex,
            String toggleMessage, boolean toggleState, boolean createToogleArea) {
        // CHECKSTYLE:ON
        super(parentShell, dialogTitle, image, message, dialogImageType, buttons.keySet().toArray(new String[buttons.size()]), defaultIndex, toggleMessage, toggleState);
        this.buttonsMap = buttons;
        this.createToogleArea = createToogleArea;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.dialogs.MessageDialogWithToggle#createToggleButton(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Button createToggleButton(Composite parent) {
        if (createToogleArea) {
            return super.createToggleButton(parent);
        }
        return null;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.dialogs.MessageDialogWithToggle#setToggleButton(org.eclipse.swt.widgets.Button)
     */
    @Override
    protected void setToggleButton(Button button) {
        if (createToogleArea) {
            super.setToggleButton(button);
        }
    }

    /**
     * {@inheritDoc}.
     * 
     * Overridden to be able to extend mapButtonLabelToButtonID method.
     * 
     * @see Dialog#createButtonBar(Composite)
     */
    protected void createButtonsForButtonBar(Composite parent) {
        final String[] buttonLabels = getButtonLabels();
        final Button[] buttons = new Button[buttonLabels.length];
        final int defaultButtonIndex = getDefaultButtonIndex();

        int suggestedId = IDialogConstants.INTERNAL_ID;
        for (int i = 0; i < buttonLabels.length; i++) {
            String label = buttonLabels[i];
            // get the JFace button ID that matches the label, or use the
            // specified
            // id if there is no match.
            int id = mapButtonLabelToButtonID(label, suggestedId);

            // if the suggested id was used, increment the default for next use
            if (id == suggestedId) {
                suggestedId++;
            }

            Button button = createButton(parent, id, label, defaultButtonIndex == i);
            buttons[i] = button;

        }
        setButtons(buttons);
    }

    /**
     * Attempt to find a standard JFace button id that matches the specified
     * button label. If no match can be found, use the default id provided.
     * 
     * Overridden to investigate the provided buttons.
     * 
     * @param buttonLabel
     *            the button label whose id is sought
     * @param defaultId
     *            the id to use for the button if there is no standard id
     * @return the id for the specified button label
     */
    // CHECKSTYLE:OFF
    private int mapButtonLabelToButtonID(String buttonLabel, int defaultId) {
        // CHECKSTYLE:OON
        // Not pretty but does the job...
        if (IDialogConstants.OK_LABEL.equals(buttonLabel)) {
            return IDialogConstants.OK_ID;
        }

        if (IDialogConstants.YES_LABEL.equals(buttonLabel)) {
            return IDialogConstants.YES_ID;
        }

        if (IDialogConstants.NO_LABEL.equals(buttonLabel)) {
            return IDialogConstants.NO_ID;
        }

        if (IDialogConstants.CANCEL_LABEL.equals(buttonLabel)) {
            return IDialogConstants.CANCEL_ID;
        }

        if (IDialogConstants.YES_TO_ALL_LABEL.equals(buttonLabel)) {
            return IDialogConstants.YES_TO_ALL_ID;
        }

        if (IDialogConstants.SKIP_LABEL.equals(buttonLabel)) {
            return IDialogConstants.SKIP_ID;
        }

        if (IDialogConstants.STOP_LABEL.equals(buttonLabel)) {
            return IDialogConstants.STOP_ID;
        }

        if (IDialogConstants.ABORT_LABEL.equals(buttonLabel)) {
            return IDialogConstants.ABORT_ID;
        }

        if (IDialogConstants.RETRY_LABEL.equals(buttonLabel)) {
            return IDialogConstants.RETRY_ID;
        }

        if (IDialogConstants.IGNORE_LABEL.equals(buttonLabel)) {
            return IDialogConstants.IGNORE_ID;
        }

        if (IDialogConstants.PROCEED_LABEL.equals(buttonLabel)) {
            return IDialogConstants.PROCEED_ID;
        }

        if (IDialogConstants.OPEN_LABEL.equals(buttonLabel)) {
            return IDialogConstants.OPEN_ID;
        }

        if (IDialogConstants.CLOSE_LABEL.equals(buttonLabel)) {
            return IDialogConstants.CLOSE_ID;
        }

        if (IDialogConstants.BACK_LABEL.equals(buttonLabel)) {
            return IDialogConstants.BACK_ID;
        }

        if (IDialogConstants.NEXT_LABEL.equals(buttonLabel)) {
            return IDialogConstants.NEXT_ID;
        }

        if (IDialogConstants.FINISH_LABEL.equals(buttonLabel)) {
            return IDialogConstants.FINISH_ID;
        }

        if (IDialogConstants.HELP_LABEL.equals(buttonLabel)) {
            return IDialogConstants.HELP_ID;
        }

        if (IDialogConstants.NO_TO_ALL_LABEL.equals(buttonLabel)) {
            return IDialogConstants.NO_TO_ALL_ID;
        }

        if (IDialogConstants.SHOW_DETAILS_LABEL.equals(buttonLabel)) {
            return IDialogConstants.DETAILS_ID;
        }

        if (IDialogConstants.HIDE_DETAILS_LABEL.equals(buttonLabel)) {
            return IDialogConstants.DETAILS_ID;
        }

        for (Entry<String, Integer> providedButton : buttonsMap.entrySet()) {
            if (providedButton.getKey().equals(buttonLabel)) {
                return providedButton.getValue();
            }
        }

        // No XXX_LABEL in IDialogConstants for these. Unlikely
        // they would be used in a message dialog though.
        // public int SELECT_ALL_ID = 18;
        // public int DESELECT_ALL_ID = 19;
        // public int SELECT_TYPES_ID = 20;

        return defaultId;
    }
}
