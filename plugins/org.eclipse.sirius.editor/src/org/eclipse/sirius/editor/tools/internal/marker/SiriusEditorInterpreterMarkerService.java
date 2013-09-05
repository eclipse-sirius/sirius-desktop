/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.tools.internal.marker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EValidator;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Allows to manage markers that will be integrated in the Sirius ODesign
 * editor, when faced to errors when validating Interpreted Expressions.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class SiriusEditorInterpreterMarkerService {
    /**
     * "Target" attribute of an Interpreter error marker : URI of the target of
     * the error (e.g. a NodeMapping, a ModelOperation...).
     */
    public static final String TARGET_ATTRIBUTE = "target";

    /**
     * "Field" attribute of an Interpreter error marker : name fo the field that
     * contains the Interpreter errors (e.g "semanticCandidateExpression" for a
     * node mapping).
     */
    public static final String FIELD_ATTRIBUTE = "field";

    /**
     * This utility class does not need a constructor.
     */
    private SiriusEditorInterpreterMarkerService() {
        // Hides default constructor
    }

    /**
     * Returns all the Validation Error markers contained in the given resource
     * and relative to the given element only.
     * 
     * @param resource
     *            the resource to test
     * @param elementURI
     *            the URI of the element
     * @return all the Validation Error markers contained in the given resource
     *         and relative to the given element only
     */
    public static Collection<IMarker> getValidationMarkersForElement(IResource resource, final String elementURI) {
        IMarker[] markers = findResourceMarkers(resource, EValidator.MARKER);
        final Collection<IMarker> validationMarkers;
        if (markers != null) {
            validationMarkers = Lists.newArrayList(markers);
        } else {
            validationMarkers = Lists.newArrayList();
        }
        ArrayList<IMarker> validationMarkersRelativeToElement = Lists.newArrayList(Iterables.filter(validationMarkers, new Predicate<IMarker>() {

            public boolean apply(IMarker input) {
                String uriAttribute = input.getAttribute(EValidator.URI_ATTRIBUTE, null);
                return elementURI.equals(uriAttribute);
            }

        }));
        return validationMarkersRelativeToElement;
    }

    /**
     * Returns all the Validation Error markers contained in the given resource
     * and relative to the given element or one of its children.
     * 
     * @param resource
     *            the resource to test
     * @param elementURI
     *            the URI of the element
     * @return all the Validation Error markers contained in the given resource
     *         and relative to the given element or one of its children
     */
    public static Collection<IMarker> getValidationMarkersForElementAndChildren(IResource resource, String elementURI) {
        IMarker[] markers = findResourceMarkers(resource, EValidator.MARKER);
        final Collection<IMarker> validationMarkers;
        if (markers != null) {
            validationMarkers = Lists.newArrayList(markers);
        } else {
            validationMarkers = Lists.newArrayList();
        }
        Set<IMarker> elementMarkers = Sets.newLinkedHashSet();
        for (IMarker marker : validationMarkers) {

            Object attribute = marker.getAttribute(EValidator.URI_ATTRIBUTE, null);
            if (attribute != null) {
                if (((String) attribute).contains(elementURI)) {
                    elementMarkers.add(marker);
                }
            }
        }
        return elementMarkers;
    }

    /**
     * Shortcut to {@link IResource#findMarkers(String, boolean, int)}.
     * 
     * @param resource
     *            The resource we need all markers of.
     * @param type
     *            Type of the markers we seek.
     * @return All of this resource's markers which match <code>type</code>.
     */
    private static IMarker[] findResourceMarkers(IResource resource, String type) {
        try {
            return resource.findMarkers(type, true, IResource.DEPTH_INFINITE);
        } catch (CoreException e) {
            // Swallow exception, we won't touch this resource's markers
        }
        return null;
    }
}
