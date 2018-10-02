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
import java.util.stream.Stream;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.properties.Category;
import org.eclipse.sirius.properties.DialogModelOperation;
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.ViewExtensionDescription;
import org.eclipse.sirius.properties.core.api.IDescriptionLinkResolver;
import org.eclipse.sirius.properties.core.api.TransformationCache;

/**
 * This class is used to resolve the links between the property validation rules and the widgets.
 * 
 * @author mbats
 * @author sbegaudeau
 */
public class GroupDescriptionPreprocessorLinkResolver implements IDescriptionLinkResolver {

    @Override
    public void resolve(EObject eObject, TransformationCache cache) {
        Stream<PageDescription> pages = Stream.empty();
        if (eObject instanceof ViewExtensionDescription) {
            ViewExtensionDescription viewExtensionDescription = (ViewExtensionDescription) eObject;
            pages = viewExtensionDescription.getCategories().stream().map(Category::getPages).flatMap(Collection::stream);
        } else if (eObject instanceof DialogModelOperation) {
            DialogModelOperation dialogModelOperation = (DialogModelOperation) eObject;
            pages = Stream.of(dialogModelOperation.getPage());
        }

        pages.forEach(page -> {
            List<GroupDescription> groups = new ArrayList<GroupDescription>();
            page.getGroups().forEach(group -> {
                if (group.eResource() != null) {
                    cache.getOutput(group).filter(GroupDescription.class::isInstance).map(GroupDescription.class::cast).ifPresent(groups::add);
                } else if (group.eContainer() == null) {
                    EObject eContainer = page.eContainer();
                    if (eContainer instanceof Category) {
                        Category category = (Category) eContainer;
                        category.getGroups().add(group);
                        groups.add(group);
                    } else if (eContainer instanceof DialogModelOperation) {
                        DialogModelOperation dialogModelOperation = (DialogModelOperation) eContainer;
                        dialogModelOperation.getGroups().add(group);
                        groups.add(group);
                    }
                }
            });
            page.getGroups().clear();
            page.getGroups().addAll(groups);
        });
    }
}
