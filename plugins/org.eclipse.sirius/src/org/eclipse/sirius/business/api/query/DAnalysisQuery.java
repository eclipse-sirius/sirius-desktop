/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.DAnnotationEntry;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.collect.Sets;

/**
 * A class aggregating all the queries (read-only!) having a {@link DAnalysis}
 * as a starting point.
 * 
 * @author nlepine
 * 
 */
public class DAnalysisQuery {

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
     * Return all valid (i.e. not null) analysis referenced by the given
     * analysis (and its sub referenced analysis).
     * 
     * @return all valid (i.e. not null) analysis referenced by the given
     *         analysis (and its sub referenced analysis).
     */
    public Collection<DAnalysis> getAllReferencedAnalyses() {
        Collection<DAnalysis> referenced = Sets.newLinkedHashSet();
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
     * Get the first model of this analysis if it contains at least one model.
     * The first model is the model used to create the representations file.
     * 
     * @return an optional EObject representing the root of the main semantic
     *         model.
     */
    public Option<EObject> getMainModel() {
        if (analysis.getModels().isEmpty()) {
            return Options.newNone();
        } else {
            return Options.newSome(analysis.getModels().get(0));
        }
    }

    /**
     * Build a list of main models. <br/>
     * First, get the first model of this analysis if it contains at least one
     * model. The first model is the model used to create the representations
     * file. <br/>
     * Then add each non controlled models that is not a parent of the main
     * model (which can be a model fragment if the current DAnalysis is a
     * referenced analysis and has been created during a previous control).
     * 
     * @return an {@link Set} of EObject representing the root of the main
     *         semantic models.
     */
    public Set<EObject> getMainModels() {
        Option<EObject> optionalMainModel = getMainModel();
        // We need a list with the "main model" and other root models to allow
        // control on project with many models
        Set<EObject> releventModels = Sets.newLinkedHashSet();
        if (optionalMainModel.some()) {
            releventModels.add(optionalMainModel.get());
            for (EObject model : analysis.getModels()) {
                if (!AdapterFactoryEditingDomain.isControlled(model) && !(new EObjectQuery(optionalMainModel.get()).isContainedIn(model))) {
                    releventModels.add(model);
                }
            }
        }
        return releventModels;
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
     * Find the first orphan {@link DView} (i.e. for which
     * {@link DView#getSirius()} == null), null if no orphan {@link DView}
     * existing among the {@link DAnalysis#getOwnedViews()}.
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
