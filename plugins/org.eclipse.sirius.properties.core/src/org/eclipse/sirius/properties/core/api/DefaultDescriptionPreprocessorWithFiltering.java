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
package org.eclipse.sirius.properties.core.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.StreamSupport;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.common.interpreter.api.IInterpreter;
import org.eclipse.sirius.common.interpreter.api.IVariableManager;
import org.eclipse.sirius.properties.core.internal.converter.SiriusInitialOperationAdapter;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;

/**
 * {@link IDescriptionPreprocessor} implementation that supports ignoring some features and copying the value of some
 * other features. This means that in the models resulting from the preprocessing, the ignored values are not copied or
 * inherited. Features that must be copied are copied using {@link org.eclipse.emf.ecore.util.EcoreUtil#copy(EObject)}.
 * 
 * @author flatombe
 * @author mbats
 *
 * @param <SIRIUS>
 *            the type of description supported by this preprocessor.
 */
public class DefaultDescriptionPreprocessorWithFiltering<SIRIUS extends EObject> extends DefaultDescriptionPreprocessor<SIRIUS> {

    /**
     * The features to ignore.
     */
    private Collection<EStructuralFeature> featuresToFilter = new ArrayList<EStructuralFeature>();

    /**
     * The features to copy.
     */
    private Collection<EStructuralFeature> featuresToCopy = new ArrayList<EStructuralFeature>();

    /**
     * The constructor.
     * 
     * @param descriptionClass
     *            the SIRIUS class
     * @param featuresToFilter
     *            the collection of features of {@code descriptionClass} that must be ignored during the preprocessing.
     * @param featuresToCopy
     *            the collection of features of {@code descriptionClass} that must be copied during the preprocessing
     */
    public DefaultDescriptionPreprocessorWithFiltering(Class<SIRIUS> descriptionClass, Collection<EStructuralFeature> featuresToFilter, Collection<EStructuralFeature> featuresToCopy) {
        super(descriptionClass);
        this.featuresToCopy.addAll(featuresToCopy);
        this.featuresToFilter.addAll(featuresToFilter);
    }

    /**
     * Processes the given feature if it is not to be ignored. If the feature is to be copied, then re-directs to the
     * methods that do the copying.
     * 
     * @param feature
     *            the {@link EStructuralFeature} to process
     * @param processedDescription
     *            the resulting description
     * @param currentDescription
     *            the original description or one of its ancestors, from which properties are inherited
     * @param cache
     *            the processing cache
     * @param interpreter
     *            the interpreter
     * @param variableManager
     *            the variable manager
     * @param overridesProvider
     *            Utility class used to provide the override descriptions
     */
    @Override
    protected void processDescriptionFeature(EStructuralFeature feature, SIRIUS processedDescription, SIRIUS currentDescription, TransformationCache cache, IInterpreter interpreter,
            IVariableManager variableManager, OverridesProvider overridesProvider) {
        if (!featuresToFilter.contains(feature)) {
            if (featuresToCopy.contains(feature)) {
                if (!feature.isMany()) {
                    processMonoValuedFeatureByCopying(feature, processedDescription, currentDescription, cache);
                } else {
                    processManyValuedFeatureByCopying(feature, processedDescription, currentDescription, cache, interpreter, variableManager, overridesProvider);
                }
            } else {
                super.processDescriptionFeature(feature, processedDescription, currentDescription, cache, interpreter, variableManager, overridesProvider);
            }
        }
    }

    /**
     * Processes the given {@link EStructuralFeature} by copying the value of the current description.
     * 
     * @param monoValuedfeature
     *            the mono-valued feature being processed.
     * @param processedDescription
     *            the resulting description.
     * @param currentDescription
     *            the original or an ancestor description.
     */
    private void processMonoValuedFeatureByCopying(EStructuralFeature monoValuedfeature, SIRIUS processedDescription, SIRIUS currentDescription, TransformationCache cache) {
        if (!processedDescription.eIsSet(monoValuedfeature)) {
            Object existingValue = currentDescription.eGet(monoValuedfeature);
            if (existingValue instanceof EObject) {
                EObject newValue = EcoreUtil.copy((EObject) existingValue);
                cache.put(existingValue, newValue);
                processedDescription.eSet(monoValuedfeature, newValue);

                if (existingValue instanceof InitialOperation) {
                    newValue.eAdapters().add(new SiriusInitialOperationAdapter(EcoreUtil.getURI((InitialOperation) existingValue)));
                }
            }
        }
    }

    /**
     * Processes the given {@link EStructuralFeature} by adding to the processed description's feature value a copy of
     * the values held by the current description.
     * 
     * @param manyValuedFeature
     *            the many-valued feature being processed.
     * @param processedDescription
     *            the resulting description.
     * @param currentDescription
     *            the original or an ancestor description.
     * @param cache
     *            the cache
     * @param interpreter
     *            The interpreter
     * @param variableManager
     *            The variable manager
     * @param overridesProvider
     *            Utility class used to provide the override descriptions
     */
    protected void processManyValuedFeatureByCopying(EStructuralFeature manyValuedFeature, SIRIUS processedDescription, SIRIUS currentDescription, TransformationCache cache, IInterpreter interpreter,
            IVariableManager variableManager, OverridesProvider overridesProvider) {
        Object processedValue = processedDescription.eGet(manyValuedFeature);
        Object currentValue = currentDescription.eGet(manyValuedFeature);
        if (currentValue instanceof Iterable<?> && processedValue instanceof Iterable<?>) {
            List<Object> newValue = new ArrayList<>();
            Iterable<?> currentIterable = (Iterable<?>) currentValue;
            Iterable<?> processedIterable = (Iterable<?>) processedValue;

            // For each current description create a copy and set it in the new
            // values
            StreamSupport.stream(currentIterable.spliterator(), false).filter(EObject.class::isInstance).map(EObject.class::cast).forEach(object -> {
                if (!this.isFiltered(manyValuedFeature, processedDescription, object, cache, interpreter, variableManager, overridesProvider)) {
                    EObject newEObject = EcoreUtil.copy(object);
                    cache.put(object, newEObject);
                    newValue.add(newEObject);

                    if (object instanceof InitialOperation) {
                        newEObject.eAdapters().add(new SiriusInitialOperationAdapter(EcoreUtil.getURI(object)));
                    }
                }
            });

            // Get all the already processed values
            processedIterable.forEach(newValue::add);

            // Set the new reference values
            processedDescription.eSet(manyValuedFeature, newValue);
        }
    }
}
