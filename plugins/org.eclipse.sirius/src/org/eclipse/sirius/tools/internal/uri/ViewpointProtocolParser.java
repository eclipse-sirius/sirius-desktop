/*******************************************************************************
 * Copyright (c) 2009, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tools.internal.uri;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.PatternSyntaxException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.util.DescriptionResourceFactoryImpl;

/**
 * A protocol parser for viewpoints URI.
 * 
 * @author cbrun
 */
public class ViewpointProtocolParser extends DescriptionResourceFactoryImpl {
    /**
     * {@inheritDoc}
     */
    public Resource createResource(final URI uri) {
        ViewpointProtocolParser.getViewpoint(uri);
        return super.createResource(uri);
    }

    /**
     * Get the viewpoint from a viewpoint uri.
     * 
     * @param uri
     *            the viewpoint uri
     * @return the viewpoint if found, throw an exception otherwise
     * @throws ViewpointProtocolException
     *             if the uri could not be parsed or the viewpoint could not be
     *             found
     */
    public static Viewpoint getViewpoint(final URI uri) throws ViewpointProtocolException {
        if (uri.segmentCount() == 2 && "viewpoint".equals(uri.scheme())) { //$NON-NLS-1$

            final Set<Viewpoint> viewpoints = ViewpointRegistry.getInstance().getViewpoints();
            final String pluginName = URI.decode(uri.segment(0));
            final String viewpointName = URI.decode(uri.lastSegment());

            final Set<Viewpoint> vpWithGoodName = ViewpointProtocolParser.filterByNameAndId(viewpoints, viewpointName, pluginName);

            if (!vpWithGoodName.isEmpty()) {
                return vpWithGoodName.iterator().next();
            }
        } else {
            throw new ViewpointProtocolException("URI " + uri + " is not valid.");
        }
        throw new ViewpointProtocolException("No viewpoint is corresponding to " + uri);
    }

    private static Set<Viewpoint> filterByNameAndId(final Set<Viewpoint> viewpoints, final String viewpointName, final String pluginId) {
        final Set<Viewpoint> filtered = new LinkedHashSet<Viewpoint>();
        for (final Viewpoint viewpoint : viewpoints) {
            if (viewpointName.equals(viewpoint.getName())) {
                if (ViewpointProtocolParser.hasGoodPluginID(viewpoint, pluginId)) {
                    filtered.add(viewpoint);
                }
            }
        }
        return filtered;
    }

    private static boolean hasGoodPluginID(final Viewpoint viewpoint, final String pluginId) {
        final Resource resource = viewpoint.eResource();
        if (resource != null && resource.getURI() != null) {
            final URI vpURI = resource.getURI();
            if (vpURI.isPlatform()) {
                final String uriPluginId = vpURI.segment(1);
                return pluginId.equals(uriPluginId);
            }
        }
        return false;
    }

    /**
     * Tell whether a platform URI matches the given viewpoint:/ uri.
     * 
     * @param resourceSetURi
     *            the platform URI.
     * @param viewpointURI
     *            a viewpoint:/ based URI. Can be a regular expression that
     *            potentially matches the given resourceSetURi.
     * @return true if the uri's are matching.
     */
    public static boolean match(final URI resourceSetURi, final String viewpointURI) {
        boolean result = false;
        if (resourceSetURi.isPlatform()) {
            final URI computedURI = ViewpointProtocolParser.buildViewpointUri(resourceSetURi);
            if (computedURI != null) {
                if (URI.createURI(viewpointURI, false).toString().equals(computedURI.toString())) {
                    // Simple case same URI
                    result = true;
                } else {
                    try {
                        result = URI.decode(computedURI.toString()).matches(viewpointURI);
                    } catch (PatternSyntaxException e) {
                        // Nothing to do, the regex is not valid so it
                        // corresponds to nothing.
                    }
                }
            }
        }
        return result;
    }

    /**
     * Build the viewpoint uri.
     * 
     * @param resourceSetURi
     *            the platform URI.
     * @return the uri converted to viewpoint protocol
     */
    public static URI buildViewpointUri(final URI resourceSetURi) {
        URI result = null;
        if (resourceSetURi != null && resourceSetURi.isPlatform()) {
            final String uriPluginID = ViewpointProtocolParser.extractPluginID(resourceSetURi);
            final String viewpointName = ViewpointProtocolParser.extractViewpointName(resourceSetURi);
            final String computedURI = "viewpoint:/" + uriPluginID + "/" + viewpointName; //$NON-NLS-1$ //$NON-NLS-2$
            result = URI.createURI(computedURI);
        }
        return result;
    }

    private static String extractViewpointName(final URI resourceSetURi) {
        final String fragment = resourceSetURi.fragment();
        final String viewpointNameSeparator = "'"; //$NON-NLS-1$
        if (fragment.contains(viewpointNameSeparator)) {
            String name = fragment.substring(fragment.indexOf(viewpointNameSeparator) + 1);
            name = name.substring(0, name.indexOf(viewpointNameSeparator));
            return name;
        } else {
            return "unnamed";
        }

    }

    private static String extractPluginID(final URI resourceSetURi) {
        return resourceSetURi.segment(1);
    }

}
