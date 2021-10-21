/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.api.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationTemplate;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * A class aggregating all the queries (read-only!) having a Sirius as a
 * starting point.
 * 
 * @author cbrun
 * 
 */
public class ViewpointQuery {

    private Viewpoint vp;

    /**
     * Create a new query.
     * 
     * @param vp
     *            the starting point.
     */
    public ViewpointQuery(Viewpoint vp) {
        this.vp = vp;
    }

    /**
     * return all the representation description to consider contained in this
     * viewpoint.
     * 
     * @return all the representation description to consider contained in this
     *         viewpoint.
     */
    public Iterable<RepresentationDescription> getAllRepresentationDescriptions() {
        List<RepresentationDescription> representations = new ArrayList<>();
        representations.addAll(vp.getOwnedRepresentations());
        for (RepresentationTemplate template : vp.getOwnedTemplates()) {
            representations.addAll(template.getOwnedRepresentations());
        }
        return representations;

    }

    /**
     * Return all the representation extension description to consider contained
     * in this viewpoint.
     * 
     * @return all the representation extension description to consider
     *         contained in this viewpoint.
     */
    public Iterable<RepresentationExtensionDescription> getAllRepresentationExtensionDescriptions() {
        List<RepresentationExtensionDescription> representations = new ArrayList<>();
        representations.addAll(vp.getOwnedRepresentationExtensions());
        return representations;
    }

    /**
     * Returns the logical Sirius URI identifying this viewpoint, if it is from
     * a platform resource.
     * 
     * @return the logical Sirius URI identifying this viewpoint.
     */
    public Option<URI> getViewpointURI() {
        Resource vpResource = vp.eResource();
        if (vpResource != null && vpResource.getURI().isPlatform()) {
            URI resourceURI = vpResource.getURI();
            String pluginId = resourceURI.segment(1);
            String uri = ViewpointURIQuery.VIEWPOINT_URI_SCHEME + ":/" + pluginId + "/" + vp.getName(); //$NON-NLS-1$ //$NON-NLS-2$
            return Options.newSome(URI.createURI(uri));
        }
        return Options.newNone();
    }

    /**
     * Tests whether the queried Sirius has the same logical Sirius URI as the
     * specified Sirius.
     * 
     * @param other
     *            another Sirius.
     * @return <code>true</code> iff the queried Sirius has the same logical URI
     *         as <code>other</code>.
     */
    public boolean hasSameSiriusURI(Viewpoint other) {
        Objects.requireNonNull(other);
        Option<URI> vpURI = getViewpointURI();
        Option<URI> otherURI = new ViewpointQuery(other).getViewpointURI();
        return vpURI.some() && otherURI.some() && vpURI.get().equals(otherURI.get());
    }

    /**
     * Tests whether this viewpoint can handle semantic model contained in files
     * with the specified extension. In this context, "handles" means that the
     * Sirius supports representations on semantic elements which may appear in
     * this kind of resources, as defined in the Sirius's
     * <code>modelFileExtension</code> attribute.
     * 
     * @param ext
     *            the file extension to test.
     * @return <code>true</code> if this Sirius can handle semantic models in
     *         file with the specified extension.
     */
    public boolean handlesSemanticModelExtension(String ext) {
        Objects.requireNonNull(ext);

        final String supportedExtensions;
        if (StringUtil.isEmpty(vp.getModelFileExtension()))
            supportedExtensions = StringUtil.JOKER_STRING;
        else {
            supportedExtensions = vp.getModelFileExtension().trim();
        }

        for (String supported : supportedExtensions.split(" +")) { //$NON-NLS-1$
            if (matches(ext, supported.trim())) {
                return true;
            }
        }
        return false;
    }

    private boolean matches(String ext, String supportedExtension) {
        if (StringUtil.JOKER_STRING.equals(supportedExtension)) {
            return true;
        }
        String semanticExtensionPattern = StringUtil.JOKER_STRING + "." + ext; //$NON-NLS-1$
        return ext.equals(supportedExtension) || semanticExtensionPattern.equals(supportedExtension);
    }
}
