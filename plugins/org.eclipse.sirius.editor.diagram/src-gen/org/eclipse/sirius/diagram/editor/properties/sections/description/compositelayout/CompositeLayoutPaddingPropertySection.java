/*******************************************************************************
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.editor.properties.sections.description.compositelayout;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditor;
import org.eclipse.sirius.editor.properties.sections.common.AbstractSpinnerPropertySection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * A section for the padding property of a CompositeLayout object.
 */
public class CompositeLayoutPaddingPropertySection extends AbstractSpinnerPropertySection {
    /**
     * @see org.eclipse.sirius.diagram.editor.properties.sections.AbstractSpinnerPropertySection#getDefaultLabelText()
     */
    protected String getDefaultLabelText() {
        return "Padding"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.sirius.diagram.editor.properties.sections.AbstractSpinnerPropertySection#getLabelText()
     */
    protected String getLabelText() {
        String labelText;
        labelText = super.getLabelText() + "*:"; //$NON-NLS-1$
        // Start of user code get label text

        // End of user code get label text
        return labelText;
    }

    /**
     * @see org.eclipse.sirius.diagram.editor.properties.sections.AbstractSpinnerPropertySection#getFeature()
     */
    protected EAttribute getFeature() {
        return DescriptionPackage.eINSTANCE.getCompositeLayout_Padding();
    }

    /**
     * @see org.eclipse.sirius.diagram.editor.properties.sections.AbstractSpinnerPropertySection#getFeatureAsInteger()
     */
    protected String getFeatureAsText() {
        String value = new String();
        if (eObject.eGet(getFeature()) != null)
            value = toInteger(eObject.eGet(getFeature()).toString()).toString();
        return value;
    }

    /**
     * @see org.eclipse.sirius.diagram.editor.properties.sections.AbstractSpinnerPropertySection#isEqual(int)
     */
    protected boolean isEqual(String newText) {
        boolean equal = true;
        if (toInteger(newText) != null)
            equal = getFeatureAsText().equals(toInteger(newText).toString());
        else
            refresh();
        return equal;
    }

    /**
     * @see org.eclipse.sirius.diagram.editor.properties.sections.AbstractSpinnerPropertySection#getFeatureValue(int)
     */
    protected Object getFeatureValue(String newText) {
        return toInteger(newText);
    }

    /**
     * Converts the given text to the integer it represents if applicable.
     * 
     * @return The integer the given text represents if applicable,
     *         <code>null</code> otherwise.
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
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);

        nameLabel.setFont(SiriusEditor.getFontRegistry().get("required"));
        // Start of user code create controls

        // End of user code create controls
    }
}
