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
package org.eclipse.sirius.properties.core.internal.preprocessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.common.interpreter.api.IInterpreter;
import org.eclipse.sirius.common.interpreter.api.IVariableManager;
import org.eclipse.sirius.properties.AbstractPageDescription;
import org.eclipse.sirius.properties.DialogModelOperation;
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.PageValidationSetDescription;
import org.eclipse.sirius.properties.PropertiesFactory;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.ViewExtensionDescription;
import org.eclipse.sirius.properties.WizardModelOperation;
import org.eclipse.sirius.properties.core.api.IDescriptionPreprocessor;
import org.eclipse.sirius.properties.core.api.OverridesProvider;
import org.eclipse.sirius.properties.core.api.PreconfiguredPreprocessor;
import org.eclipse.sirius.properties.core.api.TransformationCache;
import org.eclipse.sirius.properties.core.internal.SiriusPropertiesCorePlugin;
import org.eclipse.sirius.viewpoint.description.validation.SemanticValidationRule;

/**
 * Preprocessor for {@link PageDescription}.
 * <ul>
 * <li>The {@code validationSet} containment is handled manually.</li>
 * <li>The {@code groups} reference is handled manually.</li>
 * <li>The {@code extends} reference is not inheritable.</li>
 * <li>The {@code filterGroupsFromExtendedPageExpression} attribute is not inheritable.</li>
 * <li>The {@code filterValidationRulesFromExtendedPageExpression} attribute is not inheritable.</li>
 * </ul>
 * 
 * @author flatombe
 * @author mbats
 */
public class PageDescriptionPreprocessor extends PreconfiguredPreprocessor<AbstractPageDescription> {
    /**
     * This feature is handled separately.
     */
    protected static final EReference VALIDATIONSET_FEATURE = PropertiesPackage.Literals.ABSTRACT_PAGE_DESCRIPTION__VALIDATION_SET;

    /**
     * The constructor.
     */
    public PageDescriptionPreprocessor() {
        super(AbstractPageDescription.class, PropertiesPackage.Literals.PAGE_DESCRIPTION);
    }

    @Override
    protected void processMonoValuedEReference(EReference eReference, AbstractPageDescription processedDescription, AbstractPageDescription currentDescription, TransformationCache cache,
            IInterpreter interpreter, IVariableManager variableManager, OverridesProvider overridesProvider) {
        if (!eReference.equals(VALIDATIONSET_FEATURE)) {
            super.processMonoValuedEReference(eReference, processedDescription, currentDescription, cache, interpreter, variableManager, overridesProvider);
        } else {
            processValidationSet(processedDescription, currentDescription, cache, interpreter, variableManager, overridesProvider);
        }
    }

    /**
     * Special case for the validation set. A new set is created if need be. The rules of the parent description are
     * copied into the set.
     * 
     * @param processedDescription
     *            the resulting description.
     * @param currentDescription
     *            the original or parent description.
     * @param cache
     *            the processing cache.
     * @param interpreter
     *            the interpreter.
     * @param variableManager
     *            the variable manager.
     * @param overridesProvider
     *            Utility class used to provide the override descriptions
     */
    private void processValidationSet(AbstractPageDescription processedDescription, AbstractPageDescription currentDescription, TransformationCache cache, IInterpreter interpreter,
            IVariableManager variableManager, OverridesProvider overridesProvider) {
        if (currentDescription.eIsSet(VALIDATIONSET_FEATURE)) {
            PageValidationSetDescription validationSet = Optional.ofNullable(processedDescription.getValidationSet()).orElse(PropertiesFactory.eINSTANCE.createPageValidationSetDescription());
            processedDescription.setValidationSet(validationSet);

            // Maintain the order: first the rules of the extended description,
            // then those contributed by the current description.
            List<SemanticValidationRule> newValue = new ArrayList<>();
            currentDescription.getValidationSet().getSemanticValidationRules().forEach(rule -> {
                if (!this.isFiltered(PropertiesPackage.eINSTANCE.getPageValidationSetDescription_SemanticValidationRules(), processedDescription, rule, cache, interpreter, variableManager,
                        overridesProvider)) {
                    newValue.add(EcoreUtil.copy(rule));
                }
            });
            newValue.addAll(processedDescription.getValidationSet().getSemanticValidationRules());

            processedDescription.getValidationSet().getSemanticValidationRules().clear();
            processedDescription.getValidationSet().getSemanticValidationRules().addAll(newValue);
        }
    }

    @Override
    protected void processMultiValuedEReference(EReference eReference, AbstractPageDescription processedDescription, AbstractPageDescription currentDescription, TransformationCache cache,
            IInterpreter interpreter, IVariableManager variableManager, OverridesProvider overridesProvider) {
        if (!eReference.equals(PropertiesPackage.Literals.ABSTRACT_PAGE_DESCRIPTION__GROUPS)) {
            super.processMultiValuedEReference(eReference, processedDescription, currentDescription, cache, interpreter, variableManager, overridesProvider);
        } else {
            processGroups(processedDescription, currentDescription, cache, interpreter, variableManager, overridesProvider);
        }
    }

    /**
     * Special case for the groups. A new group is created if need be. The rules of the parent description are copied
     * into the group.
     * 
     * @param processedDescription
     *            the resulting description.
     * @param currentDescription
     *            the original or parent description.
     * @param cache
     *            the processing cache.
     * @param interpreter
     *            the interpreter.
     * @param variableManager
     *            the variable manager.
     * @param overridesProvider
     *            Utility class used to provide the override descriptions
     */
    private void processGroups(AbstractPageDescription processedDescription, AbstractPageDescription currentDescription, TransformationCache cache, IInterpreter interpreter,
            IVariableManager variableManager, OverridesProvider overridesProvider) {
        currentDescription.getGroups().forEach(groupDescription -> {
            Optional<Object> inputDescription = cache.getInput(processedDescription);
            Optional<PageDescription> optionalInputPageDescription = inputDescription.filter(PageDescription.class::isInstance).map(PageDescription.class::cast);
            Optional<IDescriptionPreprocessor> optionalDescriptionPreprocessor = SiriusPropertiesCorePlugin.getPlugin().getDescriptionPreprocessor(groupDescription);

            if (!this.isFiltered(PropertiesPackage.eINSTANCE.getAbstractPageDescription_Groups(), processedDescription, groupDescription, cache, interpreter, variableManager, overridesProvider)
                    && optionalInputPageDescription.isPresent() && this.shouldProcessGroup(optionalInputPageDescription.get(), groupDescription)) {
                // @formatter:off
                optionalDescriptionPreprocessor.map(descriptionPreprocessor -> descriptionPreprocessor.convert(groupDescription, cache, interpreter, variableManager, overridesProvider))
                    .filter(GroupDescription.class::isInstance)
                    .map(GroupDescription.class::cast)
                    .map(processedDescription.getGroups()::add);
                // @formatter:on
            } else {
                processedDescription.getGroups().add(groupDescription);
            }
        });
    }

    /**
     * Indicates if we should process the given group for the given page.
     * 
     * @param inputPageDescription
     *            The page description currently processed
     * @param groupDescription
     *            The group description
     * @return <code>true</code> if the group description should be processed, <code>false</code> otherwise
     */
    private boolean shouldProcessGroup(PageDescription inputPageDescription, GroupDescription groupDescription) {
        Optional<EObject> optionalPageDescriptionContainer = this.getPropertiesRootContainer(inputPageDescription);
        Optional<EObject> optionalGroupDescriptionContainer = this.getPropertiesRootContainer(groupDescription);

        if (optionalPageDescriptionContainer.isPresent() && optionalGroupDescriptionContainer.isPresent()) {
            return !optionalPageDescriptionContainer.get().equals(optionalGroupDescriptionContainer.get());
        }
        return true;
    }

    /**
     * Returns the root container of the properties definition.
     * 
     * @param eObject
     *            An EObject of the Properties DSL
     * @return An optional with the first containing {@link ViewExtensionDescription} found or the first containing
     *         {@link DialogModelOperation} found or the first containing {@link WizardModelOperation} found or an empty
     *         optional if the given element is not contained in one of those elements.
     */
    private Optional<EObject> getPropertiesRootContainer(EObject eObject) {
        EObject eContainer = eObject;

        while (eContainer != null && !(eContainer instanceof ViewExtensionDescription || eContainer instanceof DialogModelOperation || eContainer instanceof WizardModelOperation)) {
            eContainer = eContainer.eContainer();
        }

        return Optional.ofNullable(eContainer);
    }
}
