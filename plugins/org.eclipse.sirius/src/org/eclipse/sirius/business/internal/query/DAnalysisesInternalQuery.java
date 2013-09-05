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
package org.eclipse.sirius.business.internal.query;

import java.util.ArrayList;
import java.util.Collection;

import com.google.common.collect.Sets;

import org.eclipse.sirius.DAnalysis;

/**
 * A class defining all queries (read-only) having a collection of DAnalysis as
 * root. Allows for example to get all the referenced analysises from a
 * collection of root DAnalysises.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class DAnalysisesInternalQuery {

    /**
     * The {@link DAnalysis}s which are the target of the Query.
     */
    protected Collection<DAnalysis> analysises;

    /**
     * Creates a new DAnalysisQuery.
     * 
     * @param analysises
     *            the collection of {@link DAnalysis} to query
     */
    public DAnalysisesInternalQuery(Collection<DAnalysis> analysises) {
        this.analysises = analysises;
    }

    /**
     * Return all valid (i.e. not null) analyses owned or referenced by the
     * given collection of analyses.
     * 
     * @return all valid (i.e. not null) analyses owned or referenced by the
     *         given collection of analyses
     */
    public Collection<DAnalysis> getAllAnalyses() {
        Collection<DAnalysis> analysisAndReferenced = Sets.newLinkedHashSet();
        for (final DAnalysis analysis : new ArrayList<DAnalysis>(analysises)) {
            /* analysis could be null */
            if (analysis != null) {
                analysisAndReferenced.add(analysis);
                addAllReferencedAnalyses(analysisAndReferenced, analysis);
            }
        }
        return analysisAndReferenced;

    }

    private void addAllReferencedAnalyses(final Collection<DAnalysis> analysisAndReferenced, final DAnalysis analysis) {
        for (final DAnalysis referenced : analysis.getReferencedAnalysis()) {
            if (!analysisAndReferenced.contains(referenced) && referenced.eResource() != null) {
                analysisAndReferenced.add(referenced);
                addAllReferencedAnalyses(analysisAndReferenced, referenced);
            }
        }
    }
}
