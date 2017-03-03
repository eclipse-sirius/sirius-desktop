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
import org.eclipse.sirius.properties.DialogModelOperation;
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.PageValidationSetDescription;
import org.eclipse.sirius.properties.PropertiesFactory;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.ViewExtensionDescription;
import org.eclipse.sirius.properties.core.api.DefaultDescriptionPreprocessorWithFiltering;
import org.eclipse.sirius.properties.core.api.IDescriptionPreprocessor;
import org.eclipse.sirius.properties.core.api.PreconfiguredPreprocessorUtils;
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
public class PageDescriptionPreprocessor extends DefaultDescriptionPreprocessorWithFiltering<PageDescription> {

    /**
     * This feature is handled separately.
     */
    protected static final EReference VALIDATIONSET_FEATURE = PropertiesPackage.Literals.ABSTRACT_PAGE_DESCRIPTION__VALIDATION_SET;

    /**
     * The constructor.
     */
    public PageDescriptionPreprocessor() {
        super(PageDescription.class, PreconfiguredPreprocessorUtils.getFeaturesToFilter(PropertiesPackage.Literals.PAGE_DESCRIPTION),
                PreconfiguredPreprocessorUtils.getFeaturesToCopy(PropertiesPackage.Literals.PAGE_DESCRIPTION));
    }

    @Override
    protected void processMonoValuedEReference(EReference eReference, PageDescription processedDescription, PageDescription currentDescription, TransformationCache cache) {
        if (!eReference.equals(VALIDATIONSET_FEATURE)) {
            super.processMonoValuedEReference(eReference, processedDescription, currentDescription, cache);
        } else {
            processValidationSet(processedDescription, currentDescription, cache);
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
     */
    private void processValidationSet(PageDescription processedDescription, PageDescription currentDescription, TransformationCache cache) {
        if (currentDescription.eIsSet(VALIDATIONSET_FEATURE)) {

            PageValidationSetDescription validationSet = Optional.ofNullable(processedDescription.getValidationSet()).orElse(PropertiesFactory.eINSTANCE.createPageValidationSetDescription());
            processedDescription.setValidationSet(validationSet);

            // Maintain the order: first the rules of the extended description,
            // then those contributed by the current description.
            List<SemanticValidationRule> newValue = new ArrayList<>();
            currentDescription.getValidationSet().getSemanticValidationRules().forEach(rule -> newValue.add(EcoreUtil.copy(rule)));
            newValue.addAll(processedDescription.getValidationSet().getSemanticValidationRules());

            processedDescription.getValidationSet().getSemanticValidationRules().clear();
            processedDescription.getValidationSet().getSemanticValidationRules().addAll(newValue);
        }
    }

    @Override
    protected void processMultiValuedEReference(EReference eReference, PageDescription processedDescription, PageDescription currentDescription, TransformationCache cache) {
        if (!eReference.equals(PropertiesPackage.Literals.ABSTRACT_PAGE_DESCRIPTION__GROUPS)) {
            super.processMultiValuedEReference(eReference, processedDescription, currentDescription, cache);
        } else {
            processGroups(processedDescription, currentDescription, cache);
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
     */
    private void processGroups(PageDescription processedDescription, PageDescription currentDescription, TransformationCache cache) {
        currentDescription.getGroups().forEach(groupDescription -> {
            Optional<Object> inputDescription = cache.getInput(processedDescription);
            Optional<PageDescription> optionalInputPageDescription = inputDescription.filter(PageDescription.class::isInstance).map(PageDescription.class::cast);
            Optional<IDescriptionPreprocessor> optionalDescriptionPreprocessor = SiriusPropertiesCorePlugin.getPlugin().getDescriptionPreprocessor(groupDescription);

            if (optionalInputPageDescription.isPresent() && this.shouldProcessGroup(optionalInputPageDescription.get(), groupDescription)) {
                optionalDescriptionPreprocessor.map(descriptionPreprocessor -> descriptionPreprocessor.convert(groupDescription, cache)).filter(GroupDescription.class::isInstance)
                        .map(GroupDescription.class::cast).map(processedGroup -> processedDescription.getGroups().add(processedGroup));
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

        return optionalPageDescriptionContainer.map(pageDescriptionContainer -> {
            return optionalGroupDescriptionContainer.map(groupDescriptionContainer -> !pageDescriptionContainer.equals(groupDescriptionContainer)).orElse(true);
        }).orElse(true);
    }

    /**
     * Returns the root container of the properties definition.
     * 
     * @param eObject
     *            An EObject of the Properties DSL
     * @return An optional with the first containing {@link ViewExtensionDescription} found or the first containing
     *         {@link DialogModelOperation} found or an empty optional if the given element is not contained in one of
     *         those elements.
     */
    private Optional<EObject> getPropertiesRootContainer(EObject eObject) {
        EObject eContainer = eObject;

        while (eContainer != null && !(eContainer instanceof ViewExtensionDescription || eContainer instanceof DialogModelOperation)) {
            eContainer = eContainer.eContainer();
        }

        return Optional.ofNullable(eContainer);
    }
}
