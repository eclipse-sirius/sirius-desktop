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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.sirius.viewpoint.description.ColorDescription;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;

/**
 * Default implementation of the {@link IAnyTypeConverter}.
 * 
 * @author sbegaudeau
 */
public class DefaultAnyTypeConverter implements IAnyTypeConverter {

    /**
     * The name of the old identifier feature.
     */
    private static final String IDENTIFIER = "identifier"; //$NON-NLS-1$

    @Override
    public boolean canConvert(Resource resource, AnyType anyType, EClass eClass) {
        return !eClass.isAbstract() && !eClass.isInterface();
    }

    @Override
    public Optional<EObject> convert(Resource resource, AnyType anyType, EClass eClass) {
        // Creates a new instance of the given EClass and set all its properties
        // from the given AnyType object
        EFactory eFactory = eClass.getEPackage().getEFactoryInstance();
        Optional<EObject> optionalEObject = Optional.ofNullable(eFactory.create(eClass));

        optionalEObject.ifPresent(eObject -> {
            // Iterate on all the attribute, in this case, the EAttribute and
            // non-containment EReference
            anyType.getAnyAttribute().forEach(entry -> this.convertAnyAttribute(resource, eObject, entry));

            // Iterate on all the mixed properties (containment EReference and
            // useless pieces of data)
            anyType.getMixed().forEach(entry -> this.convertMixed(resource, eObject, entry));
        });

        return optionalEObject;
    }

    /**
     * Converts all the attributes.
     * 
     * @param resource
     *            The resource
     * @param eObject
     *            The EObject to update
     * @param entry
     *            The any type entry for an any type attribute
     */
    protected void convertAnyAttribute(Resource resource, EObject eObject, Entry entry) {
        if (IDENTIFIER.equals(entry.getEStructuralFeature().getName()) && DescriptionPackage.Literals.IDENTIFIED_ELEMENT.isInstance(eObject) && entry.getValue() instanceof String) {
            eObject.eSet(DescriptionPackage.Literals.IDENTIFIED_ELEMENT__NAME, entry.getValue());
        }

        Optional.ofNullable(eObject.eClass().getEStructuralFeature(entry.getEStructuralFeature().getName())).ifPresent(eStructuralFeature -> {
            if (eStructuralFeature instanceof EReference && DescriptionPackage.Literals.COLOR_DESCRIPTION == ((EReference) eStructuralFeature).getEReferenceType()
                    && entry.getValue() instanceof String) {
                ColorDescription colorDescription = DescriptionFactory.eINSTANCE.createFixedColor();
                if (colorDescription instanceof InternalEObject) {
                    InternalEObject internalEObject = (InternalEObject) colorDescription;
                    internalEObject.eSetProxyURI(resource.getURI().appendFragment((String) entry.getValue()));
                }
                eObject.eSet(eStructuralFeature, colorDescription);
            } else if (eStructuralFeature instanceof EAttribute && entry.getValue() instanceof String) {
                EAttribute eAttribute = (EAttribute) eStructuralFeature;
                EDataType eDataType = eAttribute.getEAttributeType();
                String stringValue = (String) entry.getValue();
                eObject.eSet(eStructuralFeature, EcoreUtil.createFromString(eDataType, stringValue));
            } else {
                eObject.eSet(eStructuralFeature, entry.getValue());
            }
        });
    }

    /**
     * Convert the mixed elements of an any type.
     * 
     * @param resource
     *            The resource
     * @param eObject
     *            The EObject to update
     * @param entry
     *            The any type entry for the mixed element
     */
    protected void convertMixed(Resource resource, EObject eObject, Entry entry) {
        EStructuralFeature eStructuralFeature = eObject.eClass().getEStructuralFeature(entry.getEStructuralFeature().getName());
        if (eStructuralFeature instanceof EReference) {
            EReference eReference = (EReference) eStructuralFeature;
            if (!eReference.isMany() && entry.getValue() instanceof AnyType) {
                this.convertMonoValuedReference(resource, eObject, eReference, (AnyType) entry.getValue());
            } else if (eReference.isMany() && entry.getValue() instanceof AnyType) {
                this.convertMultiValuedReference(resource, eObject, eReference, (AnyType) entry.getValue());
            } else if (!eReference.isMany() && eReference.getEReferenceType().isInstance(entry.getValue())) {
                // EcoreUtil#copy in order to prevent concurrent modification
                // exceptions because the container of entry.getValue() would
                // change
                eObject.eSet(eReference, EcoreUtil.copy((EObject) entry.getValue()));
            } else if (eReference.isMany() && eReference.getEReferenceType().isInstance(entry.getValue())) {
                Object value = eObject.eGet(eReference);
                if (value instanceof Collection<?>) {
                    // EcoreUtil#copy in order to prevent concurrent
                    // modification exceptions because the container of
                    // entry.getValue() would change
                    List<Object> objects = new ArrayList<>();
                    Collection<?> values = (Collection<?>) value;
                    values.forEach(objects::add);
                    objects.add(EcoreUtil.copy((EObject) entry.getValue()));
                    eObject.eSet(eReference, objects);
                }
            }
        }
    }

    /**
     * Converts the value from the given any type.
     * 
     * @param resource
     *            The resource
     * @param eObject
     *            The EObject
     * @param eReference
     *            The EReference
     * @param anyType
     *            The any type value
     */
    protected void convertMonoValuedReference(Resource resource, EObject eObject, EReference eReference, AnyType anyType) {
        EClass eClass = eReference.getEReferenceType();
        Optional<IAnyTypeConverter> optionalConverter = AnyTypeConverterRegistry.getConverter(resource, anyType, eClass);
        Optional<EObject> optionalReferencedEObject = optionalConverter.flatMap(converter -> converter.convert(resource, anyType, eClass));
        optionalReferencedEObject.ifPresent(referencedEObject -> eObject.eSet(eReference, referencedEObject));
    }

    /**
     * Converts the value from the given any type.
     * 
     * @param resource
     *            The resource
     * @param eObject
     *            The EObject
     * @param eReference
     *            The EReference
     * @param anyType
     *            The any type value
     */
    protected void convertMultiValuedReference(Resource resource, EObject eObject, EReference eReference, AnyType anyType) {
        EClass eClass = eReference.getEReferenceType();
        Optional<IAnyTypeConverter> optionalConverter = AnyTypeConverterRegistry.getConverter(resource, anyType, eClass);
        Optional<EObject> optionalReferencedEObject = optionalConverter.flatMap(converter -> converter.convert(resource, anyType, eClass));
        optionalReferencedEObject.ifPresent(referencedEObject -> {
            Object value = eObject.eGet(eReference);
            if (value instanceof Collection<?>) {
                List<Object> objects = new ArrayList<>();
                Collection<?> values = (Collection<?>) value;
                values.forEach(objects::add);
                objects.add(referencedEObject);
                eObject.eSet(eReference, objects);
            }
        });
    }

}
