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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.common.interpreter.api.IEvaluationResult;
import org.eclipse.sirius.common.interpreter.api.IInterpreter;
import org.eclipse.sirius.common.interpreter.api.IVariableManager;
import org.eclipse.sirius.properties.AbstractOverrideDescription;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.core.internal.SiriusPropertiesCorePlugin;

/**
 * Default {@link IDescriptionPreprocessor} implementation.
 * 
 * @author flatombe
 * @author mbats
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
     * The type of Sirius description for which this default preprocessor is configured. A generic name representing the
     * semantic validation rules and the property validation rules.
     */
    private static final String VALIDATION_RULES_FEATURE_NAME = "validationRules"; //$NON-NLS-1$

    /**
     * The type of Sirius description for which this default preprocessor is configured. >>>>>>> ced119b... [496065] Add
     * support for the filters in the preprocessing of .odesign
     */
    protected Class<SIRIUS> descriptionClass;

    /**
     * Creates a new default preprocessor.
     * 
     * @param descriptionClass
     *            the SIRIUS class.
     */
    public DefaultDescriptionPreprocessor(Class<SIRIUS> descriptionClass) {
        this.descriptionClass = descriptionClass;
    }

    @Override
    public EObject convert(EObject originalDescription, TransformationCache cache, IInterpreter interpreter, IVariableManager variableManager, OverridesProvider overridesProvider) {
        SIRIUS processedDescription = null;
        if (this.descriptionClass.isAssignableFrom(originalDescription.getClass())) {
            EObject createdDescription = originalDescription.eClass().getEPackage().getEFactoryInstance().create(originalDescription.eClass());

            if (this.descriptionClass.isAssignableFrom(createdDescription.getClass())) {
                processedDescription = this.descriptionClass.cast(createdDescription);
                cache.put(originalDescription, processedDescription);
                SIRIUS siriusOriginalDescription = this.descriptionClass.cast(originalDescription);
                this.processDescriptionOverrides(processedDescription, siriusOriginalDescription, cache, interpreter, variableManager, overridesProvider);
                this.processDescriptionPropertiesRecursively(processedDescription, siriusOriginalDescription, cache, interpreter, variableManager, overridesProvider);
            }
        }
        return processedDescription;
    }

    /**
     * Processes all the properties of a description by resolving the inheritance relations.
     * 
     * @param processedDescription
     *            The processed description
     * @param originalDescription
     *            The original description
     * @param cache
     *            The transformation cache
     * @param interpreter
     *            The interpreter
     * @param variableManager
     *            The variable manager
     * @param overridesProvider
     *            Utility class used to provide the override description
     */
    private void processDescriptionOverrides(SIRIUS processedDescription, SIRIUS originalDescription, TransformationCache cache, IInterpreter interpreter, IVariableManager variableManager,
            OverridesProvider overridesProvider) {
        Optional<OverridesProvider> overridesProviderOpt = Optional.ofNullable(overridesProvider);
        overridesProviderOpt.ifPresent(provider -> {
            // We get the values of the first override we found, in case of
            // multiple overrides the others are ignored
            provider.getOverrideDescriptions(originalDescription).stream().findFirst().ifPresent(overrideDescription -> {
                // Copy all the structural feature values from the
                // override
                Optional<IDescriptionPreprocessor> optionalOverrideDescriptionPreprocessor = SiriusPropertiesCorePlugin.getPlugin().getDescriptionPreprocessor(overrideDescription);
                optionalOverrideDescriptionPreprocessor.ifPresent(overrideDescriptionPreprocessor -> {
                    IDescriptionPreprocessor descriptionPreprocessor = optionalOverrideDescriptionPreprocessor.get();
                    if (descriptionPreprocessor instanceof DefaultDescriptionPreprocessor<?>) {
                        DefaultDescriptionPreprocessor<?> preprocessor = (DefaultDescriptionPreprocessor<?>) descriptionPreprocessor;
                        EObject processedOverrideDescription = preprocessor.convert(overrideDescription, cache, interpreter, variableManager, overridesProvider);
                        for (EStructuralFeature eStructuralFeature : processedOverrideDescription.eClass().getEAllStructuralFeatures()) {
                            if (processedOverrideDescription.eIsSet(eStructuralFeature)) {
                                if (processedDescription.eClass().getEStructuralFeature(eStructuralFeature.getName()) != null) {
                                    processedDescription.eSet(eStructuralFeature, processedOverrideDescription.eGet(eStructuralFeature));
                                }
                            }
                        }
                    }
                });
            });
        });

    }

    /**
     * Processes all the properties of a description by resolving the inheritance relations. >>>>>>> dc995af... [496065]
     * Add support for the overrides in the preprocessing.
     * 
     * @param processedDescription
     *            the resulting description
     * @param originalDescription
     *            the original description
     * @param cache
     *            the processing cache
     * @param interpreter
     *            the interpreter
     * @param variableManager
     *            the variable manager
     * @param overridesProvider
     *            Utility class used to provide the override descriptions
     */
    protected void processDescriptionPropertiesRecursively(SIRIUS processedDescription, SIRIUS originalDescription, TransformationCache cache, IInterpreter interpreter,
            IVariableManager variableManager, OverridesProvider overridesProvider) {
        Optional<SIRIUS> currentDescription = Optional.of(originalDescription);
        while (currentDescription.isPresent()) {
            for (EStructuralFeature feature : currentDescription.get().eClass().getEAllStructuralFeatures()) {
                this.processDescriptionFeature(feature, processedDescription, currentDescription.get(), cache, interpreter, variableManager, overridesProvider);
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
            Optional<Object> currentExtends = Optional.ofNullable(description.eGet(extendsEReference));
            nextParent = currentExtends.map(ref -> this.descriptionClass.cast(ref));
        }
        return nextParent;
    }

    /**
     * Processes all the properties of the given current description.
     * 
     * @param feature
     *            the {@link EStructuralFeature} to process
     * @param processedDescription
     *            the resulting description
     * @param currentDescription
     *            the original description or one of its ancestors, from which properties are inherited.
     * @param cache
     *            the processing cache
     * @param interpreter
     *            the interpreter
     * @param variableManager
     *            the variable manager
     * @param overridesProvider
     *            Utility class used to provide the override descriptions
     */
    protected void processDescriptionFeature(EStructuralFeature feature, SIRIUS processedDescription, SIRIUS currentDescription, TransformationCache cache, IInterpreter interpreter,
            IVariableManager variableManager, OverridesProvider overridesProvider) {
        if (feature instanceof EAttribute) {
            if (!feature.isMany()) {
                this.processMonoValuedEAttribute((EAttribute) feature, processedDescription, currentDescription, cache);
            } else {
                this.processMultiValuedEAttribute((EAttribute) feature, processedDescription, currentDescription, cache);
            }
        } else if (feature instanceof EReference) {
            if (!feature.isMany()) {
                this.processMonoValuedEReference((EReference) feature, processedDescription, currentDescription, cache, interpreter, variableManager, overridesProvider);
            } else {
                this.processMultiValuedEReference((EReference) feature, processedDescription, currentDescription, cache, interpreter, variableManager, overridesProvider);
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
     * @param interpreter
     *            the interpreter.
     * @param variableManager
     *            the variable manager.
     * @param overridesProvider
     *            Utility class used to provide the override descriptions
     */
    protected void processMonoValuedEReference(EReference eReference, SIRIUS processedDescription, SIRIUS currentDescription, TransformationCache cache, IInterpreter interpreter,
            IVariableManager variableManager, OverridesProvider overridesProvider) {
        if (!processedDescription.eIsSet(eReference)) {
            Object currentValue = currentDescription.eGet(eReference);
            if (currentValue instanceof EObject) {
                Optional<IDescriptionPreprocessor> optionalPreprocessor = SiriusPropertiesCorePlugin.getPlugin().getDescriptionPreprocessor((EObject) currentValue);
                optionalPreprocessor.ifPresent(preprocessor -> {
                    Object processedValue = preprocessor.convert((EObject) currentValue, cache, interpreter, variableManager, overridesProvider);
                    processedDescription.eSet(eReference, processedValue);
                });
            }
        }
    }

    /**
     * Processes a multi-valued {@link EReference}.
     * 
     * @param eReference
     *            the {@link EReference} to process
     * @param processedDescription
     *            the resulting description
     * @param currentDescription
     *            the current description
     * @param cache
     *            the processing cache
     * @param interpreter
     *            the interpreter
     * @param variableManager
     *            the variable manager
     * @param overridesProvider
     *            Utility class used to provide the override descriptions
     */
    protected void processMultiValuedEReference(EReference eReference, SIRIUS processedDescription, SIRIUS currentDescription, TransformationCache cache, IInterpreter interpreter,
            IVariableManager variableManager, OverridesProvider overridesProvider) {
        Object processedValue = processedDescription.eGet(eReference);
        Object currentValue = currentDescription.eGet(eReference);
        if (currentValue instanceof Iterable<?> && processedValue instanceof Iterable<?>) {
            List<Object> newValue = new ArrayList<>();
            Iterable<?> currentIterable = (Iterable<?>) currentValue;
            Iterable<?> processedIterable = (Iterable<?>) processedValue;

            // For each current description, process it and add it to the
            // newValue
            StreamSupport.stream(currentIterable.spliterator(), false).filter(EObject.class::isInstance).map(EObject.class::cast).forEach(siriusDescription -> {
                if (!isFiltered(eReference, processedDescription, siriusDescription, cache, interpreter, variableManager, overridesProvider)) {
                    SiriusPropertiesCorePlugin.getPlugin().getDescriptionPreprocessor(siriusDescription)
                            .map(preprocessor -> preprocessor.convert(siriusDescription, cache, interpreter, variableManager, overridesProvider)).map(newValue::add);
                }
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

    /**
     * Check if an eObject must be filtered according to a filtering expression.
     * 
     * @param currentDescription
     *            The current description
     * @param eStructuralFeature
     *            The reference
     * @param processedDescription
     *            The processed description
     * @param cache
     *            The transformation cache
     * @param interpreter
     *            The interpreter
     * @param variableManager
     *            The variable manager
     * @param overridesProvider
     *            Utility class used to provide the override descriptions
     * @return True if the eObject must be filtered otherwise false
     */
    protected boolean isFiltered(EStructuralFeature eStructuralFeature, EObject processedDescription, EObject currentDescription, TransformationCache cache, IInterpreter interpreter,
            IVariableManager variableManager, OverridesProvider overridesProvider) {
        // check the override filters

        // @formatter:off
        List<AbstractOverrideDescription> overrideDescriptions = cache.getInput(processedDescription)
                .filter(EObject.class::isInstance)
                .map(EObject.class::cast)
                .map(overridesProvider::getOverrideDescriptions)
                .orElseGet(ArrayList::new);
        // @formatter:on

        boolean isFiltered = overrideDescriptions.stream().anyMatch(overrideDescription -> {
            return isFiltered(eStructuralFeature, overrideDescription, currentDescription, interpreter, variableManager, "Overridden"); //$NON-NLS-1$
        });

        // Check the extends filters
        if (!isFiltered) {
            isFiltered = isFiltered(eStructuralFeature, processedDescription, currentDescription, interpreter, variableManager, "Extended"); //$NON-NLS-1$
        }
        return isFiltered;
    }

    /**
     * Check if an eObject must be filtered according to a filtering expression.
     * 
     * @param eStructuralFeature
     *            the reference
     * @param processedDescription
     *            The processed description
     * @param currentDescription
     *            The current description
     * @param interpreter
     *            The interpreter
     * @param variableManager
     *            The variable manager
     * @param featureKind
     *            The feature kind, it could be Overridden or Extended
     * @return True if the eObject must be filtered otherwise false
     */
    private boolean isFiltered(EStructuralFeature eStructuralFeature, EObject processedDescription, EObject currentDescription, IInterpreter interpreter, IVariableManager variableManager,
            String featureKind) {
        // Get the name of the feature used in the filter name
        String filterFeatureName = this.getFilterFeatureName(eStructuralFeature);
        String parentTypeName = this.getTypeName(processedDescription);
        String filterName = this.getFilterName(filterFeatureName, parentTypeName, featureKind);

        Optional<EStructuralFeature> optionalFilterFeature = Optional.ofNullable(processedDescription.eClass().getEStructuralFeature(filterName));
        Optional<String> optionalFilterExpression = optionalFilterFeature.flatMap(filterFeature -> {
            return this.getFilterExpression(processedDescription, filterFeature);
        });

        return optionalFilterExpression.map(filteringExpression -> {
            Map<String, Object> variables = this.computeFilterVariables(variableManager, currentDescription, filterFeatureName);
            return this.evaluateFilter(filteringExpression, interpreter, variables);
        }).orElse(false);
    }

    /**
     * Returns the name of the structural feature associated to a filter in the metamodel. Mostly, the name is equal to
     * the name of the feature. The name of the feature equals the name of the parent type without the 'Description'
     * suffix, for example from the filtering expression of a page we will have access to a feature named 'group', for a
     * group to a feature named 'control'... It exists one special case for the validation rules and the property
     * validation rules, the feature name will be 'validationRule'.
     * 
     * @param eStructuralFeature
     *            The structural feature.
     * @return The name of the filter feature
     */
    private String getFilterFeatureName(EStructuralFeature eStructuralFeature) {
        String eStructuralFeatureName = eStructuralFeature.getName();
        if (PropertiesPackage.Literals.GROUP_VALIDATION_SET_DESCRIPTION__SEMANTIC_VALIDATION_RULES.getName().equals(eStructuralFeatureName)
                || PropertiesPackage.Literals.GROUP_VALIDATION_SET_DESCRIPTION__PROPERTY_VALIDATION_RULES.getName().equals(eStructuralFeatureName)) {
            eStructuralFeatureName = VALIDATION_RULES_FEATURE_NAME;
        }
        return eStructuralFeatureName;
    }

    /**
     * Get the name of the type of a Sirius description.
     * 
     * @param processedDescription
     *            A Sirius description
     * @return The type name of the description
     */
    private String getTypeName(EObject processedDescription) {
        return processedDescription.eClass().getName().replace("Description", "").replace("Override", ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    }

    /**
     * Returns the name of the filter expression for the given EStructuralFeature.
     * 
     * @param eStructuralFeatureName
     *            The name of the EStructuralFeature
     * @param parentTypeName
     *            The name of the parent type
     * @param featureKind
     *            The kind of feature
     * @return The name of the filter expression
     */
    private String getFilterName(String eStructuralFeatureName, String parentTypeName, String featureKind) {
        return "filter" + eStructuralFeatureName.substring(0, 1).toUpperCase() + eStructuralFeatureName.substring(1) + "From" + featureKind //$NON-NLS-1$ //$NON-NLS-2$
                + parentTypeName + "Expression"; //$NON-NLS-1$
    }

    /**
     * Get the filter expression associated to a filter feature.
     * 
     * @param processedDescription
     *            The Sirius description
     * @param eStructuralFeature
     *            The filter feature
     * @return The filter expression
     */
    private Optional<String> getFilterExpression(EObject processedDescription, EStructuralFeature eStructuralFeature) {
        // @formatter:off
        return Optional.ofNullable(processedDescription.eGet(eStructuralFeature))
                .filter(String.class::isInstance)
                .map(String.class::cast);
        // @formatter:on
    }

    /**
     * Compute the filter variables.
     * 
     * @param variableManager
     *            The variable manager
     * @param currentDescription
     *            The current description
     * @param eStructuralFeatureName
     *            The filter feature name
     * @return The variables
     */
    private Map<String, Object> computeFilterVariables(IVariableManager variableManager, EObject currentDescription, String eStructuralFeatureName) {
        Map<String, Object> variables = new HashMap<>();
        variables.putAll(variableManager.getVariables());
        String variableName = this.getVariableName(eStructuralFeatureName) + "Description"; //$NON-NLS-1$
        variables.put(variableName, currentDescription);
        return variables;
    }

    /**
     * Get the variable name. The variable name is equal to the feature name without the last 's', i.e: for a 'groups'
     * feature, the variable will be 'group'.
     * 
     * @param eStructuralFeatureName
     *            The structural feature name
     * @return The variable name
     */
    private String getVariableName(String eStructuralFeatureName) {
        return eStructuralFeatureName.substring(0, eStructuralFeatureName.length() - 1);
    }

    /**
     * Evaluate the filtering expression.
     * 
     * @param filteringExpression
     *            The filtering expression
     * @param interpreter
     *            The interpreter
     * @param variables
     *            The variables
     * @return The evaluation result
     */
    private boolean evaluateFilter(String filteringExpression, IInterpreter interpreter, Map<String, Object> variables) {
        IEvaluationResult evaluationResult = interpreter.evaluateExpression(variables, filteringExpression);

        // @formatter:off
        return Optional.ofNullable(evaluationResult)
                .filter(IEvaluationResult::success)
                .map(IEvaluationResult::getValue)
                .filter(Boolean.class::isInstance)
                .map(Boolean.class::cast)
                .orElse(false);
        // @formatter:on
    }
}
