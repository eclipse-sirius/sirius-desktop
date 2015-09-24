/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *    IBM Corporation - The implementation of a constructor that come from
 *                      org.eclipse.jface.preference.IntegerFieldEditor
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.preference;

import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * IntegerFieldEditor with an help tooltip next to the checkbox text.
 * 
 * @author hmarchadour
 */
public class IntegerFieldEditorWithHelp extends IntegerFieldEditor {

    /** Full path of the help icon. */
    public static final String ICONS_PREFERENCES_HELP = "icons/full/others/prefshelp.gif"; //$NON-NLS-1$

    private static final int DEFAULT_TEXT_LIMIT = 10;

    /** Help tooltip text. */
    private String helpText;

    /**
     * Creates a integer field editor with an help tooltip in the default style.
     * 
     * @param name
     *            the name of the preference this field editor works on
     * @param label
     *            the label text of the field editor
     * @param helpText
     *            Help tooltip text
     * @param parent
     *            the parent of the field editor's control
     */
    public IntegerFieldEditorWithHelp(String name, String label, String helpText, Composite parent) {
        this.helpText = helpText;
        init(name, label);
        setTextLimit(DEFAULT_TEXT_LIMIT);
        setEmptyStringAllowed(false);
        setErrorMessage(JFaceResources.getString("IntegerFieldEditor.errorMessage")); //$NON-NLS-1$
        createControl(parent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doFillIntoGrid(Composite parent, int numColumns) {
        super.doFillIntoGrid(parent, numColumns - 1);
        Label image = new Label(parent, SWT.NONE);
        image.setImage(getHelpIcon());
        image.setToolTipText(this.helpText);
        GridData gd = new GridData();
        gd.horizontalSpan = 1;
        gd.grabExcessHorizontalSpace = true;
        image.setLayoutData(gd);
    }

    @Override
    public int getNumberOfControls() {
        return 3;
    }

    /**
     * Creates and return the help icon to show in our label.
     * 
     * @return The help icon to show in our label.
     */
    protected Image getHelpIcon() {
        ImageDescriptor findImageDescriptor = SiriusEditPlugin.Implementation.findImageDescriptor(ICONS_PREFERENCES_HELP);
        return SiriusEditPlugin.getPlugin().getImage(findImageDescriptor);
    }

}
