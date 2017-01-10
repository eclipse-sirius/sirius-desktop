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
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.properties.Category;
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.ViewExtensionDescription;
import org.eclipse.sirius.properties.core.api.IDescriptionLinkResolver;
import org.eclipse.sirius.properties.core.api.TransformationCache;

/**
 * This class is used to resolve the links between the property validation rules
 * and the widgets.
 * 
 * @author mbats
 */
public class GroupDescriptionPreprocessorLinkResolver implements IDescriptionLinkResolver {

    @Override
    public void resolve(EObject eObject, TransformationCache cache) {
        if (eObject instanceof ViewExtensionDescription) {
            ViewExtensionDescription viewExtensionDescription = (ViewExtensionDescription) eObject;

            List<Category> categories = viewExtensionDescription.getCategories();
            Stream<PageDescription> pages = categories.stream().map(Category::getPages).flatMap(Collection::stream);
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
                        }
                    }
                });
                page.getGroups().clear();
                page.getGroups().addAll(groups);
            });
        }
    }
}
