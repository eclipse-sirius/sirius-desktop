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
package org.eclipse.sirius.properties.core.internal.preprocessor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.properties.Category;
import org.eclipse.sirius.properties.DialogModelOperation;
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.GroupValidationSetDescription;
import org.eclipse.sirius.properties.ViewExtensionDescription;
import org.eclipse.sirius.properties.WidgetDescription;
import org.eclipse.sirius.properties.core.api.IDescriptionLinkResolver;
import org.eclipse.sirius.properties.core.api.TransformationCache;

/**
 * This class is used to resolve the links between the property validation rules and the widgets.
 * 
 * @author mbats
 * @author sbegaudeau
 */
public class PropertyValidationRulePreprocessorLinkResolver implements IDescriptionLinkResolver {

    @Override
    public void resolve(EObject eObject, TransformationCache cache) {
        Stream<GroupDescription> groups = Stream.empty();
        if (eObject instanceof ViewExtensionDescription) {
            List<Category> categories = ((ViewExtensionDescription) eObject).getCategories();
            groups = categories.stream().map(Category::getGroups).flatMap(Collection::stream);
        } else if (eObject instanceof DialogModelOperation) {
            groups = ((DialogModelOperation) eObject).getGroups().stream();
        }

        groups.forEach(group -> {
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
        });
    }
}
