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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.common.interpreter.api.IInterpreter;
import org.eclipse.sirius.common.interpreter.api.IVariableManager;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.core.internal.SiriusPropertiesCorePlugin;

/**
 * {@link IDescriptionPreprocessor} implementation that supports most of the cases by ignoring :
 * <ul>
 * <li>the extends reference,</li>
 * <li>all the filters of the extension mechanism.</li>
 * </ul>
 * and by copying:
 * <ul>
 * <li>the actions</li>
 * <li>the initial operation,</li>
 * <li>the style,</li>
 * <li>the conditional styles.</li>
 * </ul>
 * 
 * @author mbats
 *
 * @param <SIRIUS>
 *            the type of description supported by this preprocessor.
 */
public class PreconfiguredPreprocessor<SIRIUS extends EObject> extends DefaultDescriptionPreprocessorWithFiltering<SIRIUS> {

    /**
     * The constructor.
     * 
     * @param descriptionClass
     *            the SIRIUS class
     * @param eClass
     *            the EClass of the Sirius object
     */
    public PreconfiguredPreprocessor(Class<SIRIUS> descriptionClass, EClass eClass) {
        super(descriptionClass, PreconfiguredPreprocessorUtils.getFeaturesToFilter(eClass), PreconfiguredPreprocessorUtils.getFeaturesToCopy(eClass));
    }

    @Override
    protected void processManyValuedFeatureByCopying(EStructuralFeature manyValuedFeature, SIRIUS processedDescription, SIRIUS currentDescription, TransformationCache cache, IInterpreter interpreter,
            IVariableManager variableManager, OverridesProvider overridesProvider) {
        if (!PropertiesPackage.Literals.WIDGET_ACTION.equals(manyValuedFeature.getEType())) {
            super.processManyValuedFeatureByCopying(manyValuedFeature, processedDescription, currentDescription, cache, interpreter, variableManager, overridesProvider);
        }

        Object processedValue = processedDescription.eGet(manyValuedFeature);
        Object currentValue = currentDescription.eGet(manyValuedFeature);
        if (currentValue instanceof Iterable<?> && processedValue instanceof Iterable<?>) {
            List<Object> newValue = new ArrayList<>();
            Iterable<?> currentIterable = (Iterable<?>) currentValue;
            Iterable<?> processedIterable = (Iterable<?>) processedValue;

            // For each widget action create a copy and set it in the new values
            StreamSupport.stream(currentIterable.spliterator(), false).filter(EObject.class::isInstance).map(EObject.class::cast).forEach(object -> {
                Optional<IDescriptionPreprocessor> preprocessor = SiriusPropertiesCorePlugin.getPlugin().getDescriptionPreprocessor(object);
                if (preprocessor.isPresent()) {
                    Object processedWidgetAction = preprocessor.get().convert(object, cache, interpreter, variableManager, overridesProvider);
                    newValue.add(processedWidgetAction);
                }
            });

            // Get all the already processed values
            processedIterable.forEach(newValue::add);

            // Set the new reference values
            processedDescription.eSet(manyValuedFeature, newValue);
        }
    }
}
