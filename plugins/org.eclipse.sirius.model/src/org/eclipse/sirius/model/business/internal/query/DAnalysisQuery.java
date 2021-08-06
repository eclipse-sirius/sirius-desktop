/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.model.business.internal.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.DAnnotationEntry;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * A class aggregating all the queries (read-only!) having a {@link DAnalysis} as a starting point.
 * 
 * @author nlepine
 * 
 */
public class DAnalysisQuery {

    /**
     * The current {@link DAnalysis}.
     */
    private DAnalysis analysis;

    /**
     * Create a new query.
     * 
     * @param analysis
     *            the element to query.
     */
    public DAnalysisQuery(DAnalysis analysis) {
        this.analysis = analysis;
    }

    public DAnalysis getDAnalysis() {
        return analysis;
    }

    /**
     * Get the annotation of a representation with id source and data eObject.
     * 
     * @param source
     *            the source of the annotation
     * @param detail
     *            the data of the annotation
     * @return the annotation entry
     */
    public Option<DAnnotationEntry> getAnnotation(final String source, final String detail) {
        for (DAnnotationEntry annotation : analysis.getEAnnotations()) {
            if (source.equals(annotation.getSource()) && annotation.getDetails().contains(detail)) {
                return Options.newSome(annotation);
            }
        }
        return Options.newNone();
    }

    /**
     * Get all the annotations of a representation with id source.
     * 
     * @param source
     *            the source of the annotation
     * @return the annotation entries
     */
    public Option<DAnnotationEntry> getAnnotation(final String source) {
        for (DAnnotationEntry annotation : analysis.getEAnnotations()) {
            if (source.equals(annotation.getSource())) {
                return Options.newSome(annotation);
            }
        }
        return Options.newNone();
    }

    /**
     * Return all valid (i.e. not null) analysis referenced by the given analysis (and its sub referenced analysis).
     * 
     * @return all valid (i.e. not null) analysis referenced by the given analysis (and its sub referenced analysis).
     */
    public Collection<DAnalysis> getAllReferencedAnalyses() {
        Collection<DAnalysis> referenced = new LinkedHashSet<>();
        for (final DAnalysis referencedAnalysis : this.analysis.getReferencedAnalysis()) {
            /* analysis could be null */
            if (referencedAnalysis != null) {
                referenced.add(referencedAnalysis);
                referenced.addAll(new DAnalysisQuery(referencedAnalysis).getAllReferencedAnalyses());
            }
        }
        return referenced;

    }

    /**
     * Get selected {@link Viewpoint}s for the current {@link DAnalysis}.
     * 
     * @return selected {@link Viewpoint}s for the current {@link DAnalysis}
     */
    public Collection<Viewpoint> getSelectedViewpoints() {
        Collection<Viewpoint> selectedViewpoints = new ArrayList<Viewpoint>();
        Collection<DView> selectedDViews = analysis.getSelectedViews();
        for (final DView dView : selectedDViews) {
            final Viewpoint viewpoint = dView.getViewpoint();
            if (viewpoint != null) {
                selectedViewpoints.add(viewpoint);
            }
        }
        return selectedViewpoints;
    }

    /**
     * Find the first orphan {@link DView} (i.e. for which {@link DView#getSirius()} == null), null if no orphan
     * {@link DView} existing among the {@link DAnalysis#getOwnedViews()}.
     * 
     * @return the first orphan {@link DView}
     */
    public DView getFirstOrphanDView() {
        DView firstOrphanDView = null;
        for (final DView ownedDView : analysis.getOwnedViews()) {
            if (ownedDView.getViewpoint() == null) {
                firstOrphanDView = ownedDView;
                break;
            }
        }
        return firstOrphanDView;
    }
}
