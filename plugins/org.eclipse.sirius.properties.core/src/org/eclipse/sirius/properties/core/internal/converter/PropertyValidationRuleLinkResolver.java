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
package org.eclipse.sirius.properties.core.internal.converter;

import java.util.List;
import java.util.Optional;

import org.eclipse.eef.EEFPageDescription;
import org.eclipse.eef.EEFPropertyValidationRuleDescription;
import org.eclipse.eef.EEFViewDescription;
import org.eclipse.eef.EEFWidgetDescription;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.properties.PropertyValidationRule;
import org.eclipse.sirius.properties.core.api.IDescriptionLinkResolver;
import org.eclipse.sirius.properties.core.api.TransformationCache;

/**
 * This class is used to resolve the links between the property validation rules and the widgets.
 * 
 * @author sbegaudeau
 */
public class PropertyValidationRuleLinkResolver implements IDescriptionLinkResolver {

    @Override
    public void resolve(EObject view, TransformationCache cache) {
        if (view instanceof EEFViewDescription) {
            List<EEFPageDescription> pages = ((EEFViewDescription) view).getPages();

            // Resolve the links for all the property validation rules
            pages.forEach(page -> page.getGroups().forEach(group -> group.getPropertyValidationRules().forEach(propertyValidationRule -> this.resolve(propertyValidationRule, cache))));
        }
    }

    /**
     * Resolves the link between the property validation rule and the widgets.
     * 
     * @param eefPropertyValidationRule
     *            The property validation rule
     * @param cache
     *            The cache
     */
    private void resolve(EEFPropertyValidationRuleDescription eefPropertyValidationRule, TransformationCache cache) {
        Optional<Object> optionalSiriusDescription = cache.getInput(eefPropertyValidationRule);
        optionalSiriusDescription.filter(PropertyValidationRule.class::isInstance).map(PropertyValidationRule.class::cast).ifPresent(siriusPropertyValidationRule -> {
            siriusPropertyValidationRule.getTargets().forEach(siriusWidget -> {
                cache.getOutput(siriusWidget).filter(EEFWidgetDescription.class::isInstance).map(EEFWidgetDescription.class::cast).ifPresent(eefWidgetDescription -> {
                    eefPropertyValidationRule.getTargets().add(eefWidgetDescription);
                });
            });
        });
    }

}
