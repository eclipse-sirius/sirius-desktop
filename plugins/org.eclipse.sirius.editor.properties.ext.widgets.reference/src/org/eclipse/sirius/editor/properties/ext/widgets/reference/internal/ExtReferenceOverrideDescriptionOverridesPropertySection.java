/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.ext.widgets.reference.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.sirius.editor.properties.sections.common.AbstractComboPropertySection;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.PropertiesExtWidgetsReferencePackage;

public class ExtReferenceOverrideDescriptionOverridesPropertySection extends AbstractComboPropertySection {
    @Override
    protected String getDefaultLabelText() {
        return "Overrides"; //$NON-NLS-1$
    }

    @Override
    protected String getLabelText() {
        return super.getLabelText() + ":"; //$NON-NLS-1$
    }

    @Override
    protected EReference getFeature() {
        return PropertiesExtWidgetsReferencePackage.eINSTANCE.getExtReferenceOverrideDescription_Overrides();
    }

    @Override
    protected Object getFeatureValue(int index) {
        return getFeatureValueAt(index);
    }

    @Override
    protected boolean isEqual(int index) {
        boolean isEqual = false;
        if (getFeatureValueAt(index) == null) {
            isEqual = eObject.eGet(getFeature()) == null;
        } else {
            isEqual = getFeatureValueAt(index).equals(eObject.eGet(getFeature()));
        }
        return isEqual;
    }

    /**
     * Returns the value at the specified index in the choice of values for the feature.
     *
     * @param index
     *            Index of the value.
     * @return the value at the specified index in the choice of values.
     */
    protected Object getFeatureValueAt(int index) {
        List<?> values = getChoiceOfValues();
        if (values.size() < index || values.size() == 0 || index == -1) {
            return null;
        }
        return values.get(index);
    }

    /**
     * Fetches the list of available values for the feature.
     *
     * @return The list of available values for the feature.
     */
    @Override
    protected List<?> getChoiceOfValues() {
        List<?> values = Collections.emptyList();
        List<IItemPropertyDescriptor> propertyDescriptors = getDescriptors();
        for (IItemPropertyDescriptor propertyDescriptor : propertyDescriptors) {
            if (((EStructuralFeature) propertyDescriptor.getFeature(eObject)) == getFeature()) {
                values = new ArrayList<Object>(propertyDescriptor.getChoiceOfValues(eObject));
            }
        }
        return values;
    }
}
