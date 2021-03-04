/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.editor.properties.sections.description.foregroundstyledescription;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.sirius.editor.properties.sections.common.AbstractSpinnerPropertySection;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * A section for the labelSize property of a ForegroundStyleDescription object.
 */
public class ForegroundStyleDescriptionLabelSizePropertySection extends AbstractSpinnerPropertySection {
    /**
     * @see org.eclipse.sirius.table.editor.properties.sections.AbstractSpinnerPropertySection#getDefaultLabelText()
     */
    @Override
    protected String getDefaultLabelText() {
        return "LabelSize"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.sirius.table.editor.properties.sections.AbstractSpinnerPropertySection#getLabelText()
     */
    @Override
    protected String getLabelText() {
        String labelText;
        labelText = super.getLabelText() + ":"; //$NON-NLS-1$
        // Start of user code get label text

        // End of user code get label text
        return labelText;
    }

    /**
     * @see org.eclipse.sirius.table.editor.properties.sections.AbstractSpinnerPropertySection#getFeature()
     */
    @Override
    protected EAttribute getFeature() {
        return DescriptionPackage.eINSTANCE.getForegroundStyleDescription_LabelSize();
    }

    /**
     * @see org.eclipse.sirius.table.editor.properties.sections.AbstractSpinnerPropertySection#getFeatureAsInteger()
     */
    @Override
    protected String getFeatureAsText() {
        String value = new String();
        if (eObject.eGet(getFeature()) != null) {
            value = toInteger(eObject.eGet(getFeature()).toString()).toString();
        }
        return value;
    }

    /**
     * @see org.eclipse.sirius.table.editor.properties.sections.AbstractSpinnerPropertySection#isEqual(int)
     */
    @Override
    protected boolean isEqual(String newText) {
        boolean equal = true;
        if (toInteger(newText) != null) {
            equal = getFeatureAsText().equals(toInteger(newText).toString());
        } else {
            refresh();
        }
        return equal;
    }

    /**
     * @see org.eclipse.sirius.table.editor.properties.sections.AbstractSpinnerPropertySection#getFeatureValue(int)
     */
    @Override
    protected Object getFeatureValue(String newText) {
        return toInteger(newText);
    }

    /**
     * Converts the given text to the integer it represents if applicable.
     *
     * @return The integer the given text represents if applicable, <code>null</code> otherwise.
     */
    private Integer toInteger(String text) {
        Integer integerValue = null;
        try {
            integerValue = new Integer(text);
        } catch (NumberFormatException e) {
            // Not a Integer
        }
        return integerValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);

        // Start of user code create controls

        // End of user code create controls
    }
}
