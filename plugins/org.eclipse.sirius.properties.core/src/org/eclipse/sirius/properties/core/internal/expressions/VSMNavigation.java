/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.properties.core.internal.expressions;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.eclipse.eef.common.api.utils.Util;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.common.tools.api.interpreter.TypeName;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.properties.Category;
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.ViewExtensionDescription;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.JavaExtension;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Utility methods to navigate inside a VSM, especially wrt properties
 * descriptions.
 * 
 * @author pcdavid
 */
public final class VSMNavigation {
    private VSMNavigation() {
        // Preven instanciation.
    }

    /**
     * Tests whether a model element is part of a Sirius properties view
     * description.
     * 
     * @param vsmElement
     *            the element to test.
     * @return <code>true</code> if the element is part of a Sirius properties
     *         view description.
     */
    public static boolean isInsideViewExtensionDescription(EObject vsmElement) {
        return new EObjectQuery(vsmElement).getFirstAncestorOfType(PropertiesPackage.Literals.VIEW_EXTENSION_DESCRIPTION).some();
    }

    /**
     * Returns the domain class of a given {@link PageDescription}, defaulting
     * to a generic catch-all type if no value was set explicitly.
     * 
     * @param page
     *            a {@link PageDescription}.
     * @return the names of potential domain classes for that page.
     */
    public static Collection<String> getPageDomainClass(PageDescription page) {
        if (page != null && !Util.isBlank(page.getDomainClass())) {
            return Collections.singleton(page.getDomainClass());
        } else {
            return Collections.singleton(TypeName.EOBJECT_TYPENAME.getCompleteName());
        }
    }

    /**
     * Returns the domain class of a given {@link GroupDescription}, defaulting
     * to the union of all the possible domain classes from pages which
     * reference that group if no value was set explicitly.
     * 
     * @param group
     *            a {@link GroupDescription}.
     * @return the names of potential domain classes for that group.
     */
    public static Collection<String> getGroupDomainClass(GroupDescription group) {
        if (group != null && !Util.isBlank(group.getDomainClass())) {
            return Collections.singleton(group.getDomainClass());
        } else {
            Collection<String> result = Sets.newLinkedHashSet();
            for (PageDescription page : VSMNavigation.findReferencingPages(group)) {
                result.addAll(getPageDomainClass(page));
            }
            return result;
        }
    }

    /**
     * Returns the domain class of a VSM element from inside a
     * {@link GroupDescription} (for example a widget).
     * 
     * @param vsmElement
     *            the VSM element.
     * @return the domain class of the VSM element, as determined by the
     *         enclosing {@link GroupDescription}.
     */
    public static Option<Collection<String>> getDomainClassFromContainingGroup(EObject vsmElement) {
        Option<Collection<String>> result = Options.newNone();
        GroupDescription group = VSMNavigation.findClosestGroupDescription(vsmElement);
        if (group != null) {
            result = Options.newSome(getGroupDomainClass(group));
        }
        return result;
    }

    /**
     * Get all the representation description defined in the same VSM as a given
     * element.
     * 
     * @param vsmElement
     *            a VSM element.
     * @return all the representation description defined in the same VSM.
     */
    public static Collection<RepresentationDescription> getRepresentationDescriptionsInVSM(EObject vsmElement) {
        Collection<RepresentationDescription> result = Lists.newArrayList();
        Option<EObject> answer = getVSMRoot(vsmElement);
        if (answer.some()) {
            Group group = (Group) answer.get();
            for (Viewpoint viewpoint : group.getOwnedViewpoints()) {
                result.addAll(viewpoint.getOwnedRepresentations());
            }
        }
        return result;
    }

    /**
     * Finds all the Java extensions registered in the VSM of the specified
     * element.
     * 
     * @param vsmElement
     *            an element from a VSM model.
     * @return the qualified names of all the Java extensions registered in the
     *         same VSM.
     */
    public static Collection<String> getJavaExtensionsInVSM(EObject vsmElement) {
        Collection<String> result = Lists.newArrayList();
        Option<EObject> answer = getVSMRoot(vsmElement);
        if (answer.some()) {
            Group group = (Group) answer.get();
            for (Viewpoint vp : group.getOwnedViewpoints()) {
                for (JavaExtension dep : vp.getOwnedJavaExtensions()) {
                    if (!StringUtil.isEmpty(dep.getQualifiedClassName())) {
                        result.add(dep.getQualifiedClassName());
                    }
                }
            }
        }
        return result;
    }

    private static Option<EObject> getVSMRoot(EObject vsmElement) {
        return new EObjectQuery(vsmElement).getFirstAncestorOfType(DescriptionPackage.Literals.GROUP);
    }

    /**
     * Find which pages reference a given group in a VSM. Groups are not
     * contained insides pages, but referenced from pages defined inside the
     * same {@link ViewExtensionDescription}.
     * 
     * @param group
     *            a group.
     * @return all the pages inside the same ViewExtensionDescription as the
     *         group that reference it.
     */
    public static Set<PageDescription> findReferencingPages(GroupDescription group) {
        EObject container = group.eContainer();
        if (container instanceof ViewExtensionDescription) {
            ViewExtensionDescription ved = (ViewExtensionDescription) container;
            Set<PageDescription> result = Sets.newLinkedHashSet();
            for (Category category : ved.getCategories()) {
                for (PageDescription page : category.getPages()) {
                    if (page.getGroups().contains(group)) {
                        result.add(page);
                    }
                }
            }
            return result;
        } else {
            return Collections.emptySet();
        }
    }

    /**
     * Returns the {@link GroupDescription} enclosing a given VSM element, if
     * any.
     * 
     * @param vsmElement
     *            a VSM element.
     * @return the {@link GroupDescription} enclosing the element, or
     *         <code>null</code> if none could be found.
     */
    public static GroupDescription findClosestGroupDescription(EObject vsmElement) {
        if (vsmElement instanceof GroupDescription) {
            return (GroupDescription) vsmElement;
        } else {
            Option<EObject> answer = new EObjectQuery(vsmElement).getFirstAncestorOfType(PropertiesPackage.Literals.GROUP_DESCRIPTION);
            return answer.some() ? (GroupDescription) answer.get() : null;
        }
    }
}
