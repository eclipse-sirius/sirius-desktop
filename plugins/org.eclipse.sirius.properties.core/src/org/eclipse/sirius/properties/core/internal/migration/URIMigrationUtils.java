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
package org.eclipse.sirius.properties.core.internal.migration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.URI;

/**
 * Utility class used to migrate old URIs into new URIs.
 * 
 * @author sbegaudeau
 */
public final class URIMigrationUtils {

    /**
     * The separator of the segments of an {@link URI}.
     */
    private static final String SEGMENT_SEPARATOR = "/"; //$NON-NLS-1$

    /**
     * The separator between a resource and a fragment.
     */
    private static final String FRAGMENT_SEPARATOR = "#"; //$NON-NLS-1$

    /**
     * The prefix of the view extension description segment.
     */
    private static final String VIEW_EXTENSION_DESCRIPTION_SEGMENT = "@extensions."; //$NON-NLS-1$

    /**
     * The segment of the first category.
     */
    private static final String FIRST_CATEGORY_SEGMENT = "/@categories.0"; //$NON-NLS-1$

    /**
     * The constructor.
     */
    private URIMigrationUtils() {
        // prevent instantiation
    }

    /**
     * Creates the proxy URI of an element from the given value.
     * 
     * @param resourceURI
     *            the URI of the resource
     * @param values
     *            The old URI of the element
     * @return The proxy URI to use to reference the element
     */
    public static List<URI> createProxyURIsWithCategories(URI resourceURI, String values) {
        List<URI> uris = new ArrayList<>();

        StringTokenizer tokenizer = new StringTokenizer(values, " "); //$NON-NLS-1$
        while (tokenizer.hasMoreTokens()) {
            String value = tokenizer.nextToken();
            String[] segments = value.split(SEGMENT_SEPARATOR);

            String proxyURI = Arrays.stream(segments).map(segment -> {
                if (segment.contains(VIEW_EXTENSION_DESCRIPTION_SEGMENT)) {
                    return segment + FIRST_CATEGORY_SEGMENT;
                }
                return segment;
            }).collect(Collectors.joining(SEGMENT_SEPARATOR));

            if (proxyURI.contains(FRAGMENT_SEPARATOR)) {
                uris.add(URI.createURI(proxyURI));
            } else {
                uris.add(resourceURI.appendFragment(proxyURI));
            }
        }

        return uris;
    }
}
