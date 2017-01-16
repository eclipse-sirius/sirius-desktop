/*******************************************************************************
 * Copyright (c) 2016, 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.properties.core.api;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.eef.EefFactory;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.sirius.properties.core.internal.Messages;

/**
 * This class can be used to convert any kind of Sirius object to an EEF one
 * using the name of their structural features.
 * 
 * @author sbegaudeau
 * 
 * @param <SIRIUS>
 *            The type of the Sirius EObject
 */
public class DefaultDescriptionConverter<SIRIUS extends EObject> extends AbstractDescriptionConverter {

    /**
     * The class of the Sirius EObject.
     */
    private Class<SIRIUS> siriusClass;

    /**
     * The EClass of the EEF EObject.
     */
    private EClass eefEClass;

    /**
     * The constructor.
     * 
     * @param siriusClass
     *            The class of the Sirius EObject
     * @param eefEClass
     *            The EClass of the EEF EObject
     */
    public DefaultDescriptionConverter(Class<SIRIUS> siriusClass, EClass eefEClass) {
        this.siriusClass = siriusClass;
        this.eefEClass = eefEClass;
    }

    @Override
    public boolean canHandle(EObject description) {
        return this.siriusClass.isAssignableFrom(description.getClass());
    }

    @Override
    public EObject convert(EObject description, Map<String, Object> parameters, DescriptionCache cache) {
        if (this.siriusClass.isAssignableFrom(description.getClass())) {
            SIRIUS siriusEObject = this.siriusClass.cast(description);

            EObject eefEObject = this.getEFactory().create(this.eefEClass);
            for (EAttribute eAttribute : siriusEObject.eClass().getEAllAttributes()) {
                this.convertEAttribute(siriusEObject, eefEObject, eAttribute, parameters);
            }

            cache.put(description, eefEObject);

            for (EReference eReference : siriusEObject.eClass().getEAllReferences()) {
                this.convertEReference(siriusEObject, eefEObject, eReference, parameters, cache);
            }

            return eefEObject;
        } else {
            throw new IllegalArgumentException(MessageFormat.format(Messages.IDescriptionConverter_InvalidDescriptionType, description.getClass().getName(), this.siriusClass.getName()));
        }
    }

    /**
     * Returns the factory to use to create the new EEF Object.
     * 
     * @return The {@link EFactory}
     */
    protected EFactory getEFactory() {
        return EefFactory.eINSTANCE;
    }

    /**
     * Converts the value of the given Sirius EAttribute from the Sirius EObject
     * to the given EEF EObject.
     * 
     * @param siriusEObject
     *            The Sirius EObject
     * @param eefEObject
     *            The EEF EObject
     * @param eAttribute
     *            The Sirius EAttribute
     * @param parameters
     *            The parameters
     */
    protected void convertEAttribute(SIRIUS siriusEObject, EObject eefEObject, EAttribute eAttribute, Map<String, Object> parameters) {
        EStructuralFeature eefEStructuralFeature = eefEObject.eClass().getEStructuralFeature(eAttribute.getName());
        if (eefEStructuralFeature instanceof EAttribute) {
            if (eefEStructuralFeature.getEType().getInstanceClass().equals(eAttribute.getEType().getInstanceClass())) {
                eefEObject.eSet(eefEStructuralFeature, siriusEObject.eGet(eAttribute));
            } else if (eefEStructuralFeature.getEType().equals(EcorePackage.Literals.EINT) && eAttribute.getEType().equals(EcorePackage.Literals.ESTRING)) {
                Object value = siriusEObject.eGet(eAttribute);
                eefEObject.eSet(eefEStructuralFeature, Integer.toString(Integer.valueOf(value.toString())));
            }
        }
    }

    /**
     * Converts the value of the given Sirius EReference from the Sirius EObject
     * to the given EEF EObject.
     * 
     * @param siriusEObject
     *            The Sirius EObject
     * @param eefEObject
     *            The EEF EObject
     * @param eReference
     *            The Sirius EReference
     * @param parameters
     *            The parameters
     * @param cache
     *            The cache
     */
    protected void convertEReference(SIRIUS siriusEObject, EObject eefEObject, EReference eReference, Map<String, Object> parameters, DescriptionCache cache) {
        EStructuralFeature eefEStructuralFeature = eefEObject.eClass().getEStructuralFeature(eReference.getName());
        if (eefEStructuralFeature instanceof EReference) {
            if (eefEStructuralFeature.isMany() && eReference.isMany()) {
                Object siriusValue = siriusEObject.eGet(eReference);
                Object eefValue = eefEObject.eGet(eefEStructuralFeature);
                if (siriusValue instanceof Collection<?> && eefValue instanceof Collection<?>) {
                    Collection<?> siriusCollectionValue = (Collection<?>) siriusValue;
                    List<?> convertedCollection = this.convertCollection(siriusCollectionValue, parameters, cache, eefEStructuralFeature.getEType().getInstanceClass());

                    Collection<?> eefCollectionValue = (Collection<?>) eefValue;
                    eefEObject.eSet(eefEStructuralFeature, this.addAll(this.addAll(new ArrayList<EObject>(), eefCollectionValue), convertedCollection));
                }
            } else if (!eefEStructuralFeature.isMany() && !eReference.isMany()) {
                Object siriusValue = siriusEObject.eGet(eReference);
                if (siriusValue instanceof EObject) {
                    Object convertedEObject = this.convertEObject((EObject) siriusValue, parameters, cache, eefEStructuralFeature.getEType().getInstanceClass());
                    eefEObject.eSet(eefEStructuralFeature, convertedEObject);
                }
            }
        }
    }

    /**
     * Adds all the elements of the given collection to the list of EObjects.
     * 
     * @param eObjects
     *            The list of EObjects
     * @param collection
     *            The collection of object
     * @return The list of EObjects
     */
    protected List<EObject> addAll(List<EObject> eObjects, Collection<?> collection) {
        for (Object object : collection) {
            if (object instanceof EObject) {
                eObjects.add((EObject) object);
            }
        }
        return eObjects;
    }

}
