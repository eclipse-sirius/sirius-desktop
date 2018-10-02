/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.ui.tools.internal.preference;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * ScaleWithLegendFieldEditor with an help tooltip next to the scale control.
 * 
 * @author jmallet
 */
public class ScaleWithLegendFieldEditorWithHelp extends ScaleWithLegendFieldEditor {

    /**
     * Full path of the help icon.
     */
    public static final String ICONS_PREFERENCES_HELP = "icons/full/others/prefshelp.gif"; //$NON-NLS-1$

    /**
     * This label will display an help icon.
     */
    protected Label image;

    /**
     * Help tooltip text.
     */
    private String helpText;

    /**
     * Creates a boolean field editor with an help tooltip in the default style.
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
    public ScaleWithLegendFieldEditorWithHelp(String name, String label, String helpText, Composite parent) {
        init(name, label);
        this.helpText = helpText;
        createControl(parent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doFillIntoGrid(Composite parent, int numColumns) {
        super.doFillIntoGrid(parent, 1);

        image = new Label(parent, SWT.NONE);
        image.setImage(getHelpIcon());
        image.setToolTipText(helpText);
        GridData gd = new GridData();
        gd.horizontalSpan = 1;
        gd.grabExcessHorizontalSpace = false;
        image.setLayoutData(gd);
    }

    @Override
    public int getNumberOfControls() {
        return 2;
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
