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
import org.eclipse.sirius.properties.Category;
import org.eclipse.sirius.properties.GroupValidationSetDescription;
import org.eclipse.sirius.properties.ViewExtensionDescription;
import org.eclipse.sirius.properties.WidgetDescription;
import org.eclipse.sirius.properties.core.api.IDescriptionLinkResolver;
import org.eclipse.sirius.properties.core.api.TransformationCache;

/**
 * This class is used to resolve the links between the property validation rules
 * and the widgets.
 * 
 * @author mbats
 */
public class PropertyValidationRulePreprocessorLinkResolver implements IDescriptionLinkResolver {
    @Override
    public void resolve(EObject view, TransformationCache cache) {
        if (view instanceof ViewExtensionDescription) {
            List<Category> categories = ((ViewExtensionDescription) view).getCategories();
            categories.forEach(category -> category.getGroups().forEach(group -> {
                Optional<GroupValidationSetDescription> validationSetOptional = Optional.ofNullable(group.getValidationSet());
                // If validation set exists, resolve the targets associated to
                // each property validation rules
                validationSetOptional.ifPresent(validationSet -> validationSet.getPropertyValidationRules().forEach(rule -> {
                    List<WidgetDescription> processedTargets = new ArrayList<WidgetDescription>();
                    // Get processed targets
                    rule.getTargets().forEach(originalTarget -> {
                        cache.getOutput(originalTarget).filter(WidgetDescription.class::isInstance).map(WidgetDescription.class::cast).map(target -> processedTargets.add(target));
                    });
                    rule.getTargets().clear();
                    rule.getTargets().addAll(processedTargets);
                }));
            }));
        }
    }
}
