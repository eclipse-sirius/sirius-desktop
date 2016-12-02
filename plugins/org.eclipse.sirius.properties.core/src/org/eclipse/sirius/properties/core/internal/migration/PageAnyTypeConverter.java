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
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.PropertiesFactory;
import org.eclipse.sirius.properties.PropertiesPackage;

/**
 * The any type converter for the page description.
 * 
 * @author sbegaudeau
 */
public class PageAnyTypeConverter extends DefaultAnyTypeConverter {

    @Override
    public boolean canConvert(Resource resource, AnyType anyType, EClass eClass) {
        return PropertiesPackage.Literals.PAGE_DESCRIPTION == eClass;
    }

    @Override
    protected void convertAnyAttribute(Resource resource, EObject eObject, Entry entry) {
        EStructuralFeature eStructuralFeature = eObject.eClass().getEStructuralFeature(entry.getEStructuralFeature().getName());

        // Groups are a non-containment reference, as such they need to be
        // handled as a special use case with the creation of a proxy
        if (eStructuralFeature == PropertiesPackage.Literals.ABSTRACT_PAGE_DESCRIPTION__GROUPS && entry.getValue() instanceof String && eObject instanceof PageDescription) {
            PageDescription pageDescription = (PageDescription) eObject;

            List<URI> proxyUris = URIMigrationUtils.createProxyURIsWithCategories(resource.getURI(), (String) entry.getValue());

            List<GroupDescription> groups = proxyUris.stream().map(proxyUri -> {
                GroupDescription groupDescription = PropertiesFactory.eINSTANCE.createGroupDescription();
                if (groupDescription instanceof InternalEObject) {
                    InternalEObject internalEObject = (InternalEObject) groupDescription;
                    internalEObject.eSetProxyURI(proxyUri);
                }
                return groupDescription;
            }).collect(Collectors.toList());

            // We modify all the groups at once in order NOT to trigger to
            // resolution of an old proxy
            pageDescription.eSet(eStructuralFeature, groups);
        } else {
            super.convertAnyAttribute(resource, eObject, entry);
        }
    }
}
