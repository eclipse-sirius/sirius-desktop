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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.componentization.SiriusRegistry;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.util.DescriptionResourceFactoryImpl;

/**
 * A protocol parser for viewpoints URI.
 * 
 * @author cbrun
 */
public class SiriusProtocolParser extends DescriptionResourceFactoryImpl {
    /**
     * {@inheritDoc}
     */
    public Resource createResource(final URI uri) {
        SiriusProtocolParser.getSirius(uri);
        return super.createResource(uri);
    }

    /**
     * Get the viewpoint from a viewpoint uri.
     * 
     * @param uri
     *            the viewpoint uri
     * @return the viewpoint if found, throw an exception otherwise
     * @throws SiriusProtocolException
     *             if the uri could not be parsed or the viewpoint could not be
     *             found
     */
    public static Viewpoint getSirius(final URI uri) throws SiriusProtocolException {
        if (uri.segmentCount() == 2 && "viewpoint".equals(uri.scheme())) {

            final Set<Viewpoint> viewpoints = SiriusRegistry.getInstance().getSiriuss();
            final String pluginName = URI.decode(uri.segment(0));
            final String viewpointName = URI.decode(uri.lastSegment());

            final Set<Viewpoint> vpWithGoodName = SiriusProtocolParser.filterByNameAndId(viewpoints, viewpointName, pluginName);

            if (!vpWithGoodName.isEmpty()) {
                return vpWithGoodName.iterator().next();
            }
        } else {
            throw new SiriusProtocolException("URI " + uri + " is not valid.");
        }
        throw new SiriusProtocolException("No viewpoint is corresponding to " + uri);
    }

    private static Set<Viewpoint> filterByNameAndId(final Set<Viewpoint> viewpoints, final String viewpointName, final String pluginId) {
        final Set<Viewpoint> filtered = new LinkedHashSet<Viewpoint>();
        for (final Viewpoint viewpoint : viewpoints) {
            if (viewpointName.equals(viewpoint.getName())) {
                if (SiriusProtocolParser.hasGoodPluginID(viewpoint, pluginId)) {
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
     *            a viewpoint:/ based URI
     * @return true if the uri's are matching.
     */
    public static boolean match(final URI resourceSetURi, final URI viewpointURI) {
        if (resourceSetURi.isPlatform()) {
            final URI computedURI = SiriusProtocolParser.buildSiriusUri(resourceSetURi);
            return computedURI != null && viewpointURI.toString().equals(computedURI.toString());
        }
        return false;
    }

    /**
     * Build the viewpoint uri.
     * 
     * @param resourceSetURi
     *            the platform URI.
     * @return the uri converted to viewpoint protocol
     */
    public static URI buildSiriusUri(final URI resourceSetURi) {
        URI result = null;
        if (resourceSetURi.isPlatform()) {
            final String uriPluginID = SiriusProtocolParser.extractPluginID(resourceSetURi);
            final String viewpointName = SiriusProtocolParser.extractSiriusName(resourceSetURi);
            final String computedURI = "viewpoint:/" + uriPluginID + "/" + viewpointName;
            result = URI.createURI(computedURI);
        }
        return result;
    }

    private static String extractSiriusName(final URI resourceSetURi) {
        final String fragment = resourceSetURi.fragment();
        final String viewpointNameSeparator = "'";
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
