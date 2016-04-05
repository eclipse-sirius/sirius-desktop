/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.properties.editor.properties.sections.properties.filllayoutdescription;

// Start of user code imports

import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.sirius.editor.properties.sections.common.AbstractRadioButtonPropertySection;
import org.eclipse.sirius.properties.FILL_LAYOUT_ORIENTATION;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

// End of user code imports

/**
 * A section for the orientation property of a FillLayoutDescription object.
 */
public class FillLayoutDescriptionOrientationPropertySection extends AbstractRadioButtonPropertySection {
    /**
     * @see org.eclipse.sirius.properties.editor.properties.sections.AbstractRadioButtonPropertySection#getDefaultLabelText()
     */
    @Override
    protected String getDefaultLabelText() {
        return "Orientation"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.sirius.properties.editor.properties.sections.AbstractRadioButtonPropertySection#getLabelText()
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
     * @see org.eclipse.sirius.properties.editor.properties.sections.AbstractRadioButtonPropertySection#getFeature()
     */
    @Override
    protected EAttribute getFeature() {
        return PropertiesPackage.eINSTANCE.getFillLayoutDescription_Orientation();
    }

    /**
     * @see org.eclipse.sirius.properties.editor.properties.sections.AbstractRadioButtonPropertySection#getFeatureValue(int)
     */
    @Override
    protected Object getFeatureValue(int index) {
        return getChoiceOfValues().get(index);
    }

    /**
     * @see org.eclipse.sirius.properties.editor.properties.sections.AbstractRadioButtonPropertySection#isEqual(int)
     */
    @Override
    protected boolean isEqual(int index) {
        return getChoiceOfValues().get(index).equals(eObject.eGet(getFeature()));
    }

    /**
     * @see org.eclipse.sirius.properties.editor.properties.sections.AbstractRadioButtonPropertySection#getEnumerationFeatureValues()
     */
    @Override
    protected List<?> getChoiceOfValues() {
        return FILL_LAYOUT_ORIENTATION.VALUES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);

    }
}
