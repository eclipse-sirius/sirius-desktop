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

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.GroupValidationSetDescription;
import org.eclipse.sirius.properties.PropertiesFactory;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.PropertyValidationRule;
import org.eclipse.sirius.properties.core.api.PreconfiguredPreprocessor;
import org.eclipse.sirius.properties.core.api.TransformationCache;
import org.eclipse.sirius.viewpoint.description.validation.SemanticValidationRule;

/**
 * Preprocessor for {@link GroupDescription}.
 * <ul>
 * <li>The {@code filterConditionalStylesFromExtendedGroupExpression} attribute
 * is ignored.</li>
 * <li>The {@code filterControlsFromExtendedGroupExpression} attribute is
 * ignored.</li>
 * <li>The {@code filterValidationRulesFromExtendedGroupExpression} attribute is
 * ignored.</li>
 * <li>The {@code validationSet} containment is handled manually.</li>
 * <li>The {@code controls} containment is handled manually.</li>
 * <li>The {@code style} containment value is copied.</li>
 * <li>The {@code conditionalStyles} containment value is copied.</li>
 * </ul>
 * 
 * @author flatombe
 * @author mbats
 */
public class GroupDescriptionPreprocessor extends PreconfiguredPreprocessor<GroupDescription> {

    /**
     * The groups feature is handled separately.
     */
    protected static final EReference CONTROLS_FEATURE = PropertiesPackage.Literals.ABSTRACT_GROUP_DESCRIPTION__CONTROLS;

    /**
     * The validation set feature is handled separately.
     */
    protected static final EReference VALIDATIONSET_FEATURE = PropertiesPackage.Literals.ABSTRACT_GROUP_DESCRIPTION__VALIDATION_SET;

    /**
     * The constructor.
     */
    public GroupDescriptionPreprocessor() {
        super(GroupDescription.class, PropertiesPackage.Literals.GROUP_DESCRIPTION);
    }

    @Override
    protected void processMonoValuedEReference(EReference eReference, GroupDescription processedDescription, GroupDescription currentDescription, TransformationCache cache) {
        if (!eReference.equals(VALIDATIONSET_FEATURE)) {
            super.processMonoValuedEReference(eReference, processedDescription, currentDescription, cache);
        } else {
            processValidationSet(processedDescription, currentDescription, cache);
        }
    }

    /**
     * Special case for the validation set. A new set is created if need be. The
     * rules of the parent description are copied into the set.
     * 
     * @param processedDescription
     *            the resulting description.
     * @param currentDescription
     *            the original or parent description.
     * @param cache
     *            the processing cache.
     */
    private void processValidationSet(GroupDescription processedDescription, GroupDescription currentDescription, TransformationCache cache) {
        if (currentDescription.eIsSet(VALIDATIONSET_FEATURE)) {
            GroupValidationSetDescription validationSet = Optional.ofNullable(processedDescription.getValidationSet()).orElse(PropertiesFactory.eINSTANCE.createGroupValidationSetDescription());
            processedDescription.setValidationSet(validationSet);

            // Maintain the order: first the rules of the extended description,
            // then those contributed by the current description.
            // Copy all the semantic validation rules
            List<SemanticValidationRule> newSemanticValidationRules = new ArrayList<>();
            currentDescription.getValidationSet().getSemanticValidationRules().forEach(rule -> newSemanticValidationRules.add(EcoreUtil.copy(rule)));
            newSemanticValidationRules.addAll(processedDescription.getValidationSet().getSemanticValidationRules());
            processedDescription.getValidationSet().getSemanticValidationRules().clear();
            processedDescription.getValidationSet().getSemanticValidationRules().addAll(newSemanticValidationRules);

            // Copy all the property validation rules
            List<PropertyValidationRule> newPropertyValidationRules = new ArrayList<>();
            currentDescription.getValidationSet().getPropertyValidationRules().forEach(rule -> newPropertyValidationRules.add(EcoreUtil.copy(rule)));
            newPropertyValidationRules.addAll(processedDescription.getValidationSet().getPropertyValidationRules());
            processedDescription.getValidationSet().getPropertyValidationRules().clear();
            processedDescription.getValidationSet().getPropertyValidationRules().addAll(newPropertyValidationRules);
        }
    }
}
