/*******************************************************************************
 * Copyright (c) 2010, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.query;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.common.tools.api.util.Options;
import org.eclipse.sirius.business.api.session.resource.AirdResource;
import org.eclipse.sirius.business.internal.resource.AirDCrossReferenceAdapter;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.description.DAnnotationEntry;

/**
 * A class aggregating all the queries (read-only!) having a {@link AirDResouce}
 * as a starting point.
 * 
 * @author nlepine
 * 
 */
public class AirDResouceQuery {

    /**
     * the annotation source.
     */
    public static final String ANNOTATION_SOURCE = "Migration";

    private AirdResource resource;

    private DAnalysis analysis;

    /**
     * Create a new query.
     * 
     * @param resource
     *            the element to query.
     */
    public AirDResouceQuery(AirdResource resource) {
        this.resource = resource;
        if (resource != null) {
            if (!resource.getContents().isEmpty() && resource.getContents().get(0) instanceof DAnalysis) {
                analysis = (DAnalysis) resource.getContents().get(0);
            }
        }
    }

    /**
     * return the DAnalysis of the resource.
     * 
     * @return the DAnalysis of the resource
     */
    public Option<DAnalysis> getDAnalysis() {
        if (analysis != null) {
            return Options.newSome(analysis);
        }
        return Options.newNone();
    }

    /**
     * Check if the analysis contains the migration annotation with
     * <code>tag</code>.
     * 
     * @param tag
     *            the tag to search.
     * @return true if the analysis contains the migration annotation with
     *         <code>tag</code>.
     */
    public boolean hasMigrationTag(String tag) {
        if (analysis == null) {
            return true;
        }
        final Option<DAnnotationEntry> annotation = new DAnalysisQuery(analysis).getAnnotation(ANNOTATION_SOURCE, tag);
        return annotation.some();
    }

    /**
     * Return the airDCrossReferenceAdapter of this resource.
     * 
     * @return the airDCrossReferenceAdapter of this resource.
     */
    public Option<AirDCrossReferenceAdapter> getAirDCrossReferenceAdapter() {
        for (Adapter adapter : resource.eAdapters()) {
            if (adapter instanceof AirDCrossReferenceAdapter) {
                return Options.newSome((AirDCrossReferenceAdapter) adapter);
            }
        }
        return Options.newNone();
    }
}
