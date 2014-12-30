/*******************************************************************************
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.sections.description.ereferencecustomization;

// Start of user code imports

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.sirius.business.api.query.EClassesQuery;
import org.eclipse.sirius.business.api.query.EStructuralFeaturesQuery;
import org.eclipse.sirius.editor.properties.sections.common.AbstractComboPropertySection;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.emf.EClassQuery;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.EReferenceCustomization;
import org.eclipse.sirius.viewpoint.description.style.StylePackage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

// End of user code imports

/**
 * A section for the value property of a EReferenceCustomization object.
 */
public class EReferenceCustomizationValuePropertySection extends AbstractComboPropertySection {
    /**
     * @see org.eclipse.sirius.editor.properties.sections.AbstractComboPropertySection#getDefaultLabelText()
     */
    protected String getDefaultLabelText() {
        return "Value"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.sirius.editor.properties.sections.AbstractComboPropertySection#getLabelText()
     */
    protected String getLabelText() {
        String labelText;
        labelText = super.getLabelText() + ":"; //$NON-NLS-1$
        // Start of user code get label text

        // End of user code get label text
        return labelText;
    }

    /**
     * @see org.eclipse.sirius.editor.properties.sections.AbstractComboPropertySection#getFeature()
     */
    protected EReference getFeature() {
        return DescriptionPackage.eINSTANCE.getEReferenceCustomization_Value();
    }

    /**
     * @see org.eclipse.sirius.editor.properties.sections.AbstractComboPropertySection#getFeatureValue(int)
     */
    protected Object getFeatureValue(int index) {
        return getFeatureValueAt(index);
    }

    /**
     * @see org.eclipse.sirius.editor.properties.sections.AbstractComboPropertySection#isEqual(int)
     */
    protected boolean isEqual(int index) {
        boolean isEqual = false;
        if (getFeatureValueAt(index) == null)
            isEqual = eObject.eGet(getFeature()) == null;
        else
            isEqual = getFeatureValueAt(index).equals(eObject.eGet(getFeature()));
        return isEqual;
    }

    /**
     * Returns the value at the specified index in the choice of values for the
     * feature.
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
    protected List<?> getChoiceOfValues() {
        List<?> values = Collections.emptyList();
        List<IItemPropertyDescriptor> propertyDescriptors = getDescriptors();
        for (Iterator<IItemPropertyDescriptor> iterator = propertyDescriptors.iterator(); iterator.hasNext();) {
            IItemPropertyDescriptor propertyDescriptor = iterator.next();
            if (((EStructuralFeature) propertyDescriptor.getFeature(eObject)) == getFeature())
                values = new ArrayList<Object>(propertyDescriptor.getChoiceOfValues(eObject));
        }

        // Start of user code choice of values
        if (eObject instanceof EReferenceCustomization) {
            EReferenceCustomization eReferenceCustomization = (EReferenceCustomization) eObject;
            // Get the common type to all references that have the expected name
            Option<EClass> commonType;
            if (eReferenceCustomization.isApplyOnAll()) {
                // We do not consider the appliedOn list but we compute them
                // dynamically. All references with expected name in subclasses
                // of StyleDescription (and its children) are considered.
                Set<EStructuralFeature> features = new LinkedHashSet<EStructuralFeature>();
                String referenceName = eReferenceCustomization.getReferenceName();
                EClass styleDescription = StylePackage.eINSTANCE.getStyleDescription();
                Iterator<EClass> it = Iterators.filter(StylePackage.eINSTANCE.eAllContents(), EClass.class);
                while (it.hasNext()) {
                    EClass metaModelEClass = it.next();
                    if (styleDescription.isSuperTypeOf(metaModelEClass)) {
                        // Search references in subTypes of StyleDescription
                        // (and its children)
                        features.addAll(new EClassQuery(metaModelEClass).getEStructuralFeatures(referenceName, new ArrayList<EClass>()));
                    }
                }
                commonType = new EStructuralFeaturesQuery(Lists.newArrayList(features)).getCommonType();
            } else {
                // Get the EClass of appliedOn list.
                List<EClass> appliedOnEClass = Lists.transform(eReferenceCustomization.getAppliedOn(), new Function<EObject, EClass>() {
                    public EClass apply(EObject eObject) {
                        return eObject.eClass();
                    };
                });
                commonType = new EClassesQuery(appliedOnEClass).getCommonTypeForReference(eReferenceCustomization.getReferenceName());
            }
            List<EObject> availableValues = new ArrayList<EObject>();
            if (commonType.some()) {
                // Get all EObject that have this common type as eType.
                for (EObject value : Iterables.filter(values, EObject.class)) {
                    if (commonType.get().isSuperTypeOf(value.eClass())) {
                        availableValues.add(value);
                    }
                }
            }
            values = availableValues;
        }
        // End of user code choice of values
        return values;
    }

    /**
     * {@inheritDoc}
     */
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);
        combo.setToolTipText("The new value to set for the property.");

        CLabel help = getWidgetFactory().createCLabel(composite, "");
        FormData data = new FormData();
        data.top = new FormAttachment(combo, 0, SWT.TOP);
        data.left = new FormAttachment(nameLabel);
        help.setLayoutData(data);
        help.setImage(getHelpIcon());
        help.setToolTipText("The new value to set for the property.");
        // Start of user code create controls

        // End of user code create controls
    }
    // Start of user code user operations

    // End of user code user operations
}
