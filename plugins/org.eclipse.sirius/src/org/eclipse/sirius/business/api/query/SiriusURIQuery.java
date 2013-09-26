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
package org.eclipse.sirius.business.api.query;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;

import com.google.common.base.Preconditions;

import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.common.tools.api.util.Options;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * Queries about Sirius URIs of the form
 * <code>viewpoint:/pluginId/SiriusId</code>.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class SiriusURIQuery {
    /**
     * THe URI scheme used for Sirius URIs.
     */
    public static final String VIEWPOINT_URI_SCHEME = "viewpoint";

    /**
     * The URI to query.
     */
    private final URI uri;

    /**
     * Constructor.
     * 
     * @param uri
     *            the URI to query. Must be a {@link #isValidSiriusURI(URI)
     *            valid} Sirius URI.
     */
    public SiriusURIQuery(URI uri) {
        Preconditions.checkArgument(SiriusURIQuery.isValidSiriusURI(uri));
        this.uri = uri;
    }

    /**
     * Checks whether a URI is a valid Sirius URI.
     * 
     * @param uri
     *            the URI to test.
     * @return <code>true</code> of <code>uri</code> is a valid Sirius URI.
     */
    public static boolean isValidSiriusURI(URI uri) {
        boolean usesSiriusScheme = uri != null && SiriusURIQuery.VIEWPOINT_URI_SCHEME.equals(uri.scheme());
        return usesSiriusScheme && uri.segmentCount() >= 2;
    }

    /**
     * Attempts to convert a concrete URI to a element from a VSM into a
     * corresponding logical Sirius URI.
     * 
     * @param uri
     *            the concrete URI to convert.
     * @param resourceSet
     *            a ResourceSet in which the concrete URI can be resolved into a
     *            VSM element.
     * @return a logical Sirius URI equivalent to the concrete URI, if the
     *         conversion was successful.
     */
    public static Option<URI> asSiriusURI(URI uri, ResourceSet resourceSet) {
        Option<URI> result = Options.newNone();
        if (uri.isPlatform()) {
            EObject target = null;
            try {
                target = resourceSet.getEObject(uri, true);
            } catch (WrappedException e) {
                if (e.getCause() instanceof IOException && uri.isPlatformPlugin()) {
                    URI convertedResourceUri = URI.createPlatformResourceURI(uri.toPlatformString(true)).appendFragment(uri.fragment());
                    // this resource is potentially in the workspace and should
                    // be a PlatformResource instead of ResourceResource
                    target = resourceSet.getEObject(convertedResourceUri, true);
                }
            }
            if (target != null) {
                String pluginId = uri.segment(1);
                if (target instanceof Viewpoint) {
                    String viewpointName = ((Viewpoint) target).getName();
                    URI logicalSiriusUri = URI.createURI(SiriusURIQuery.VIEWPOINT_URI_SCHEME + ":/" + pluginId + "/" + viewpointName);
                    result = Options.newSome(logicalSiriusUri);
                } else {
                    Option<EObject> viewpointContext = new EObjectQuery(target).getFirstAncestorOfType(DescriptionPackage.eINSTANCE.getViewpoint());
                    if (viewpointContext.some()) {
                        String viewpointName = ((Viewpoint) viewpointContext.get()).getName();
                        URI logicalSiriusUri = URI.createURI(SiriusURIQuery.VIEWPOINT_URI_SCHEME + ":/" + pluginId + "/" + viewpointName);
                        result = Options.newSome(logicalSiriusUri.appendFragment(uri.fragment()));
                    }
                }
            }
        }
        return result;
    }

    /**
     * Return the identifier of the plug-in in which the viewpoint element
     * referenced by this URI is defined.
     * 
     * @return the plug-in identifier part of the URI.
     */
    public String getPluginId() {
        return uri.segment(0);
    }

    /**
     * Returns the name of the Sirius in which the element referenced by this
     * URI is defined.
     * 
     * @return the Sirius name part of the URI.
     */
    public String getSiriusName() {
        return URI.decode(uri.segment(1));
    }

    /**
     * Returns the Sirius URI denoting the Sirius itself.
     * 
     * @return the URI of the Sirius
     */
    public URI getBaseURI() {
        return uri.trimFragment();
    }
}
