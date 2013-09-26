/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.query;

import java.util.List;

import org.eclipse.emf.common.util.URI;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.common.tools.api.util.Options;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
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
public class SiriusQuery {

    private Viewpoint vp;

    /**
     * Create a new query.
     * 
     * @param vp
     *            the starting point.
     */
    public SiriusQuery(Viewpoint vp) {
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
        List<RepresentationDescription> representations = Lists.newArrayList();
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
        List<RepresentationExtensionDescription> representations = Lists.newArrayList();
        representations.addAll(vp.getOwnedRepresentationExtensions());
        return representations;
    }

    /**
     * Returns the logical Sirius URI identifying this viewpoint, if it is
     * from a platform resource.
     * 
     * @return the logical Sirius URI identifying this viewpoint.
     */
    public Option<URI> getSiriusURI() {
        if (vp.eResource() != null && vp.eResource().getURI().isPlatform()) {
            URI resourceURI = vp.eResource().getURI();
            String pluginId = resourceURI.segment(1);
            String uri = SiriusURIQuery.VIEWPOINT_URI_SCHEME + ":/" + pluginId + "/" + vp.getName();
            return Options.newSome(URI.createURI(uri));
        }
        return Options.newNone();
    }

    /**
     * Tests whether the queried Sirius has the same logical Sirius URI as
     * the specified Sirius.
     * 
     * @param other
     *            another Sirius.
     * @return <code>true</code> iff the queried Sirius has the same logical
     *         URI as <code>other</code>.
     */
    public boolean hasSameSiriusURI(Viewpoint other) {
        Preconditions.checkNotNull(other);
        Option<URI> vpURI = getSiriusURI();
        Option<URI> otherURI = new SiriusQuery(other).getSiriusURI();
        return vpURI.some() && otherURI.some() && vpURI.get().equals(otherURI.get());
    }

    /**
     * Tests whether this viewpoint can handle semantic model contained in files
     * with the specified extension. In this context, "handles" means that the
     * Sirius supports representations on semantic elements which may appear
     * in this kind of resources, as defined in the Sirius's
     * <code>modelFileExtension</code> attribute.
     * 
     * @param ext
     *            the file extension to test.
     * @return <code>true</code> if this Sirius can handle semantic models in
     *         file with the specified extension.
     */
    public boolean handlesSemanticModelExtension(String ext) {
        Preconditions.checkNotNull(ext);

        final String supportedExtensions;
        if (StringUtil.isEmpty(vp.getModelFileExtension()))
            supportedExtensions = StringUtil.JOKER_STRING;
        else {
            supportedExtensions = vp.getModelFileExtension().trim();
        }

        for (String supported : supportedExtensions.split(" +")) {
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
        String semanticExtensionPattern = StringUtil.JOKER_STRING + "." + ext;
        return ext.equals(supportedExtension) || semanticExtensionPattern.equals(supportedExtension);
    }
}
