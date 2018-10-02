/*******************************************************************************
 * Copyright (c) 2017 Obeo.
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
package org.eclipse.sirius.properties.core.internal.migration;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.sirius.properties.PropertiesFactory;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.PropertyValidationRule;
import org.eclipse.sirius.properties.WidgetDescription;

/**
 * The any type converter for the property validation rules.
 * 
 * @author sbegaudeau
 */
public class PropertyValidationRuleAnyTypeConverter extends DefaultAnyTypeConverter {
    @Override
    public boolean canConvert(Resource resource, AnyType anyType, EClass eClass) {
        return PropertiesPackage.Literals.PROPERTY_VALIDATION_RULE == eClass;
    }

    @Override
    protected void convertAnyAttribute(Resource resource, EObject eObject, Entry entry) {
        EStructuralFeature eStructuralFeature = eObject.eClass().getEStructuralFeature(entry.getEStructuralFeature().getName());
        if (eStructuralFeature == PropertiesPackage.Literals.PROPERTY_VALIDATION_RULE__TARGETS && eObject instanceof PropertyValidationRule) {
            PropertyValidationRule propertyValidationRules = (PropertyValidationRule) eObject;

            List<URI> proxyUris = URIMigrationUtils.createProxyURIsWithCategories(resource.getURI(), (String) entry.getValue());

            List<WidgetDescription> widgets = proxyUris.stream().map(proxyUri -> {
                WidgetDescription proxyEObject = PropertiesFactory.eINSTANCE.createCustomDescription();
                if (proxyEObject instanceof InternalEObject) {
                    InternalEObject internalEObject = (InternalEObject) proxyEObject;
                    internalEObject.eSetProxyURI(proxyUri);
                }
                return proxyEObject;
            }).collect(Collectors.toList());

            // We modify all the widgets at once in order NOT to trigger to
            // resolution of an old proxy
            propertyValidationRules.eSet(eStructuralFeature, widgets);
        } else {
            super.convertAnyAttribute(resource, eObject, entry);
        }
    }
}
