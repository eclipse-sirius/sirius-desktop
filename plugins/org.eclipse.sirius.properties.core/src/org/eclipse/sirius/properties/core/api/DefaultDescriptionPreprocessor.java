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
package org.eclipse.sirius.properties.core.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.properties.core.internal.SiriusPropertiesCorePlugin;

/**
 * Default {@link IDescriptionPreprocessor} implementation.
 * 
 * @author Florent Latombe <a href= "mailto:florent.latombe@obeo.fr">florent.latombe@obeo.fr</a>
 *
 * @param <SIRIUS>
 *            the type of description supported by this preprocessor.
 */
public class DefaultDescriptionPreprocessor<SIRIUS extends EObject> implements IDescriptionPreprocessor {

    /**
     * The name of the {@link EStructuralFeature} based on which the notion of inheritance is defined by default.
     */
    protected static final String EXTENDS_FEATURE_NAME = "extends"; //$NON-NLS-1$

    /**
     * The type of Sirius description for which this default preprocessor is configured.
     */
    protected Class<SIRIUS> descriptionClass;

    /**
     * Creates a new default preprocessor.
     * 
     * @param descriptionClass
     *            the SIRIUS_DESCRIPTION class.
     */
    public DefaultDescriptionPreprocessor(Class<SIRIUS> descriptionClass) {
        this.descriptionClass = descriptionClass;
    }

    @Override
    public EObject convert(EObject originalDescription, TransformationCache cache) {
        SIRIUS processedDescription = null;
        if (this.descriptionClass.isAssignableFrom(originalDescription.getClass())) {
            EObject createdDescription = originalDescription.eClass().getEPackage().getEFactoryInstance().create(originalDescription.eClass());

            if (this.descriptionClass.isAssignableFrom(createdDescription.getClass())) {
                processedDescription = this.descriptionClass.cast(createdDescription);
                cache.put(originalDescription, processedDescription);
                SIRIUS siriusOriginalDescription = this.descriptionClass.cast(originalDescription);
                this.processDescriptionPropertiesRecursively(processedDescription, siriusOriginalDescription, cache);
            }
        }
        return processedDescription;
    }

    /**
     * Processes all the properties of a description by resolving the inheritance relations.
     * 
     * @param processedDescription
     *            the resulting description.
     * @param originalDescription
     *            the original description.
     * @param cache
     *            the processing cache.
     */
    protected void processDescriptionPropertiesRecursively(SIRIUS processedDescription, SIRIUS originalDescription, TransformationCache cache) {
        Optional<SIRIUS> currentDescription = Optional.of(originalDescription);
        while (currentDescription.isPresent()) {
            for (EStructuralFeature feature : currentDescription.get().eClass().getEAllStructuralFeatures()) {
                this.processDescriptionFeature(feature, processedDescription, currentDescription.get(), cache);
            }
            currentDescription = this.getParentOf(currentDescription.get());
        }
    }

    /**
     * Provides the next parent description to consider. In this default implementation, we navigate through the
     * "extends" mono-valued EReference of the same type as the child description.
     * 
     * @param description
     *            any properties description.
     * @return the next parent description to consider.
     */
    protected Optional<SIRIUS> getParentOf(SIRIUS description) {
        Optional<SIRIUS> nextParent = Optional.empty();

        EStructuralFeature extensionFeature = description.eClass().getEStructuralFeature(EXTENDS_FEATURE_NAME);
        if (extensionFeature instanceof EReference) {
            EReference extendsEReference = (EReference) extensionFeature;
            if (extendsEReference.getEType().equals(description.eClass()) && !extendsEReference.isMany()) {
                Optional<Object> currentExtends = Optional.ofNullable(description.eGet(extendsEReference));
                nextParent = currentExtends.filter(ref -> description.getClass().isAssignableFrom(ref.getClass())).map(ref -> this.descriptionClass.cast(ref));
            }
        }
        return nextParent;
    }

    /**
     * Processes all the properties of the given current description.
     * 
     * @param feature
     *            the {@link EStructuralFeature} to process.
     * @param processedDescription
     *            the resulting description.
     * @param currentDescription
     *            the original description or one of its ancestors, from which properties are inherited.
     * @param cache
     *            the processing cache.
     */
    protected void processDescriptionFeature(EStructuralFeature feature, SIRIUS processedDescription, SIRIUS currentDescription, TransformationCache cache) {
        if (feature instanceof EAttribute) {
            if (!feature.isMany()) {
                this.processMonoValuedEAttribute((EAttribute) feature, processedDescription, currentDescription, cache);
            } else {
                this.processMultiValuedEAttribute((EAttribute) feature, processedDescription, currentDescription, cache);
            }
        } else if (feature instanceof EReference) {
            if (!feature.isMany()) {
                this.processMonoValuedEReference((EReference) feature, processedDescription, currentDescription, cache);
            } else {
                this.processMultiValuedEReference((EReference) feature, processedDescription, currentDescription, cache);
            }
        }
    }

    /**
     * Processes a mono-valued {@link EAttribute}.
     * 
     * @param eAttribute
     *            the {@link EAttribute} to process.
     * @param processedDescription
     *            the resulting description.
     * @param currentDescription
     *            the current description.
     * @param cache
     *            the processing cache.
     */
    protected void processMonoValuedEAttribute(EAttribute eAttribute, SIRIUS processedDescription, SIRIUS currentDescription, TransformationCache cache) {
        if (!processedDescription.eIsSet(eAttribute)) {
            processedDescription.eSet(eAttribute, currentDescription.eGet(eAttribute));
        }
    }

    /**
     * Processes a multi-valued {@link EAttribute}.
     * 
     * @param eAttribute
     *            the {@link EAttribute} to process.
     * @param processedDescription
     *            the resulting description.
     * @param currentDescription
     *            the current description.
     * @param cache
     *            the processing cache.
     */
    protected void processMultiValuedEAttribute(EAttribute eAttribute, SIRIUS processedDescription, SIRIUS currentDescription, TransformationCache cache) {
        Object processedValue = processedDescription.eGet(eAttribute);
        Object currentValue = currentDescription.eGet(eAttribute);
        if (currentValue instanceof Iterable<?> && processedValue instanceof Iterable<?>) {
            List<Object> newValue = new ArrayList<>();
            Iterable<?> currentIterable = (Iterable<?>) currentValue;
            Iterable<?> processedIterable = (Iterable<?>) processedValue;
            currentIterable.forEach(newValue::add);
            processedIterable.forEach(newValue::add);
            processedDescription.eSet(eAttribute, newValue);
        }
    }

    /**
     * Processes a mono-valued {@link EReference}.
     * 
     * @param eReference
     *            the {@link EReference} to process.
     * @param processedDescription
     *            the resulting description.
     * @param currentDescription
     *            the current description.
     * @param cache
     *            the processing cache.
     */
    protected void processMonoValuedEReference(EReference eReference, SIRIUS processedDescription, SIRIUS currentDescription, TransformationCache cache) {
        if (!processedDescription.eIsSet(eReference)) {
            Object currentValue = currentDescription.eGet(eReference);
            if (currentValue instanceof EObject) {
                SiriusPropertiesCorePlugin.getPlugin().getDescriptionPreprocessor((EObject) currentValue).ifPresent(preprocessor -> {
                    Object processedValue = preprocessor.convert((EObject) currentValue, cache);
                    processedDescription.eSet(eReference, processedValue);
                });
            }
        }
    }

    /**
     * Processes a multi-valued {@link EReference}.
     * 
     * @param eReference
     *            the {@link EReference} to process.
     * @param processedDescription
     *            the resulting description.
     * @param currentDescription
     *            the current description.
     * @param cache
     *            the processing cache.
     */
    protected void processMultiValuedEReference(EReference eReference, SIRIUS processedDescription, SIRIUS currentDescription, TransformationCache cache) {
        Object processedValue = processedDescription.eGet(eReference);
        Object currentValue = currentDescription.eGet(eReference);
        if (currentValue instanceof Iterable<?> && processedValue instanceof Iterable<?>) {
            List<Object> newValue = new ArrayList<>();
            Iterable<?> currentIterable = (Iterable<?>) currentValue;
            Iterable<?> processedIterable = (Iterable<?>) processedValue;

            // For each current description, process it and add it to the
            // newValue
            StreamSupport.stream(currentIterable.spliterator(), false).filter(EObject.class::isInstance).map(EObject.class::cast).forEach(object -> {
                SiriusPropertiesCorePlugin.getPlugin().getDescriptionPreprocessor(object).map(preprocessor -> preprocessor.convert(object, cache)).map(newValue::add);
            });

            // Then add already processed descriptions
            processedIterable.forEach(newValue::add);

            // Set the reference new values
            processedDescription.eSet(eReference, newValue);
        }
    }

    @Override
    public boolean canHandle(EObject description) {
        return true;
    }
}
