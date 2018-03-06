/*******************************************************************************
 * Copyright (c) 2007, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.editor.properties.sections.description.doublelayoutoption;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.editor.properties.sections.common.AbstractTextPropertySection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * A section for the value property of a DoubleLayoutOption object.
 */
public class DoubleLayoutOptionValuePropertySection extends AbstractTextPropertySection {
    /**
     * @see org.eclipse.sirius.diagram.editor.properties.sections.AbstractTextPropertySection#getLabelText()
     */
    protected String getDefaultLabelText() {
        return "Value"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.sirius.diagram.editor.properties.sections.AbstractTextPropertySection#getLabelText()
     */
    protected String getLabelText() {
        String labelText;
        labelText = super.getLabelText() + ":"; //$NON-NLS-1$
        // Start of user code get label text

        // End of user code get label text
        return labelText;
    }

    /**
     * @see org.eclipse.sirius.diagram.editor.properties.sections.AbstractTextPropertySection#getFeature()
     */
    public EAttribute getFeature() {
        return DescriptionPackage.eINSTANCE.getDoubleLayoutOption_Value();
    }

    /**
     * @see org.eclipse.sirius.diagram.editor.properties.sections.AbstractTextPropertySection#getFeatureValue(String)
     */
    protected Object getFeatureValue(String newText) {
        return toDouble(newText);
    }

    /**
     * @see org.eclipse.sirius.diagram.editor.properties.sections.AbstractTextPropertySection#isEqual(String)
     */
    protected boolean isEqual(String newText) {
        boolean equal = true;
        if (toDouble(newText) != null)
            equal = getFeatureAsText().equals(toDouble(newText).toString());
        else
            refresh();
        return equal;
    }

    /**
     * Converts the given text to the double it represents if applicable.
     * 
     * @return The double the given text represents if applicable, <code>null</code> otherwise.
     */
    private Double toDouble(String text) {
        Double doubleValue = null;
        try {
            doubleValue = new Double(text);
        } catch (NumberFormatException e) {
            // Not a Double
        }
        return doubleValue;
    }

    /**
     * {@inheritDoc}
     */
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);
    }

    /**
     * {@inheritDoc}
     */
    protected String getPropertyDescription() {
        // TODO value description
        return null;
    }
}
