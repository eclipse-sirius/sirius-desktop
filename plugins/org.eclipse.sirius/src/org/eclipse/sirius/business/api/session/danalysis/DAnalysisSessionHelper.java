/*******************************************************************************
 * Copyright (c) 2007, 2020 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.business.api.session.danalysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.query.DAnalysisQuery;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.query.RepresentationDescriptionQuery;
import org.eclipse.sirius.business.api.query.URIQuery;
import org.eclipse.sirius.business.api.resource.ResourceDescriptor;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.collect.Iterators;

/**
 * Helper for {@link DAnalysisSession}.
 * 
 * @author ymortier
 */
public final class DAnalysisSessionHelper {

    /**
     * Avoid Instantiation.
     */
    private DAnalysisSessionHelper() {
        // empty.
    }

    /**
     * Add a new analysis resource in the analysis session.
     * 
     * @param session
     *            the analysis session
     * @param loadedResource
     *            the analysis resource to add
     */
    public static void addNewAnalysisResource(final DAnalysisSession session, final Resource loadedResource) {
        boolean newAird = true;
        final Iterator<Resource> iterator = session.getAllSessionResources().iterator();
        while (newAird && iterator.hasNext()) {
            final Resource resource = iterator.next();
            newAird = !resource.getURI().equals(loadedResource.getURI());
        }
        if (newAird) {
            session.addAnalysis(loadedResource);
        }
    }

    /**
     * Select an analysis from a viewpoint and added representation.
     * 
     * @param viewpoint
     *            the viewpoint
     * @param candidates
     *            the analysis candidates.
     * @param analysisSelector
     *            the analysis selector
     * @param addedRepresentation
     *            the added representation.
     * @return the selected analysis
     */
    public static DAnalysis selectAnalysis(final Viewpoint viewpoint, final Collection<DAnalysis> candidates, final DAnalysisSelector analysisSelector, final DRepresentation addedRepresentation) {

        if (candidates.size() > 1 && analysisSelector != null) {
            return analysisSelector.selectSmartlyAnalysisForAddedRepresentation(addedRepresentation, candidates);
        } else {
            return (DAnalysis) candidates.toArray()[0];
        }
    }

    /**
     * Select an analysis from a resource.
     * 
     * @param resource
     *            the resource
     * @param candidates
     *            the analysis candidates.
     * @param analysisSelector
     *            the analysis selector
     * @return the selected analysis
     */
    public DAnalysis selectAnalysis(final Resource resource, final Collection<DAnalysis> candidates, final DAnalysisSelector analysisSelector) {

        if (candidates.size() > 1 && analysisSelector != null) {
            return analysisSelector.selectSmartlyAnalysisForAddedResource(resource, candidates);
        } else {
            return (DAnalysis) candidates.toArray()[0];
        }
    }

    /**
     * Find an existing container.
     * 
     * @param semanticRoot
     *            the semantic root to match
     * @param viewpoint
     *            the viewpoint to match
     * @param all
     *            the candidates analysis
     * @param analysisSelector
     *            the selector
     * @return the free container if found or <code>null</code> otherwise.
     */
    public static DView findContainer(final EObject semanticRoot, final Viewpoint viewpoint, final Collection<DAnalysis> all, final DAnalysisSelector analysisSelector) {

        final Collection<DView> containers = getContainers(all, viewpoint);
        if (containers.isEmpty()) {
            return null;
        }

        final Collection<DAnalysis> candidates = new ArrayList<DAnalysis>();
        for (final DView container : containers) {
            if (container.eContainer() instanceof DAnalysis) {
                candidates.add((DAnalysis) container.eContainer());
            }
        }

        // The first DAnalysis candidates must be the main DAnalysis
        final DAnalysis analysis = candidates.iterator().next();

        DView freeContainer = null;

        for (final DView container : containers) {
            if (container.eContainer() == analysis) {
                freeContainer = container;
                break;
            }
        }

        return freeContainer;

    }

    /**
     * Find an existing container.
     * 
     * @param semanticRoot
     *            the semantic root to match
     * @param viewpoint
     *            the viewpoint to match
     * @param all
     *            the candidates analysis
     * @param analysisSelector
     *            the selector
     * @param representation
     *            the added representation.
     * @return the free container if found or <code>null</code> otherwise.
     */
    public static DView findContainerForAddedRepresentation(final EObject semanticRoot, final Viewpoint viewpoint, final Collection<DAnalysis> all, final DAnalysisSelector analysisSelector,
            final DRepresentation representation) {

        final Collection<DView> containers = getContainers(all, viewpoint);

        if (containers.isEmpty()) {
            return null;
        }

        final Collection<DAnalysis> candidates = new LinkedHashSet<>();
        for (final DView container : containers) {
            if (container.eContainer() instanceof DAnalysis) {
                candidates.add((DAnalysis) container.eContainer());
            }
        }

        final DAnalysis analysis = selectAnalysis(viewpoint, candidates, analysisSelector, representation);

        DView freeContainer = null;

        for (final DView container : containers) {
            if (container.eContainer() == analysis) {
                freeContainer = container;
                break;
            }
        }

        // if the DView of the selected DAnalysis does not
        // exist yet, and if it is located on a CDORepository
        if (freeContainer == null && URIQuery.CDO_URI_SCHEME.equals(analysis.eResource().getURI().scheme())) {
            // We create this representation container
            freeContainer = createContainer(analysis, viewpoint);
        }

        return freeContainer;
    }

    /**
     * Find an existing container.
     * 
     * @param analysis
     *            selected analysis
     * @param representationDescription
     *            the representationDescription we are looking the DView for.
     * @return the free container if found or <code>null</code> otherwise.
     */
    public static DView findDViewForAddedRepresentation(DAnalysis analysis, final RepresentationDescription representationDescription) {
        final Viewpoint viewpoint = new RepresentationDescriptionQuery(representationDescription).getParentViewpoint();
        return getDView(analysis, viewpoint);
    }

    /**
     * Find a free container.
     * 
     * @param viewpoint
     *            the viewpoint to match
     * @param analyses
     *            the candidates analyses
     * @param analysisSelector
     *            the selector
     * @return the free container if found or <code>null</code> otherwise.
     * @since 0.9.0
     */
    public static DView findFreeContainer(final Viewpoint viewpoint, final Collection<DAnalysis> analyses, final DAnalysisSelector analysisSelector) {

        final Collection<DView> views = getContainers(analyses, null);
        if (views.isEmpty()) {
            return null;
        }

        final Collection<DAnalysis> candidates = new ArrayList<DAnalysis>();
        for (final DView view : views) {
            if (view.eContainer() instanceof DAnalysis) {
                candidates.add((DAnalysis) view.eContainer());
            }
        }

        // The first DAnalysis candidates must be the main DAnalysis
        final DAnalysis analysis = candidates.iterator().next();

        DView freeView = null;

        for (final DView view : views) {
            if (view.eContainer() == analysis) {
                freeView = view;
                break;
            }
        }

        return freeView;
    }

    /**
     * Find a free container for added representation.
     * 
     * @param semantic
     *            the semantic root to match
     * @param viewpoint
     *            the viewpoint to match
     * @param analyses
     *            the candidates analyses
     * @param analysisSelector
     *            the selector
     * @param representation
     *            the added representation.
     * @return the free container if found or <code>null</code> otherwise.
     */
    public static DView findFreeContainerForAddedRepresentation(final Viewpoint viewpoint, final EObject semantic, final Collection<DAnalysis> analyses, final DAnalysisSelector analysisSelector,
            final DRepresentation representation) {

        final Collection<DView> views = getContainers(analyses, null);
        if (views.isEmpty()) {
            return null;
        }

        final Collection<DAnalysis> candidates = new ArrayList<DAnalysis>();
        for (final DView view : views) {
            if (view.eContainer() instanceof DAnalysis) {
                candidates.add((DAnalysis) view.eContainer());
            }
        }

        final DAnalysis analysis = DAnalysisSessionHelper.selectAnalysis(viewpoint, candidates, analysisSelector, representation);

        DView freeView = null;

        for (final DView view : views) {
            if (view.eContainer() == analysis) {
                freeView = view;
                break;
            }
        }

        return freeView;
    }

    /**
     * Get the semantic resource associated with this diagram resource.
     * 
     * @param diagramResource
     *            the diagram resource
     * @return the semantic resource or <code>null</code> if it can not be founded.
     */
    public static Collection<Resource> getSemanticResource(final Resource diagramResource) {
        final Collection<Resource> semanticResources = new HashSet<Resource>();
        if (diagramResource.getContents().size() > 0) {
            final EObject root = diagramResource.getContents().get(0);
            if (root instanceof DAnalysis) {
                for (final EObject model : ((DAnalysis) root).getModels()) {
                    Resource modelResource = model.eResource();
                    if (modelResource != null) {
                        semanticResources.add(modelResource);
                    }
                }
            }
        }
        return semanticResources;
    }

    /**
     * Complete the models references of the DAnalysis according to all the {@link DSemanticDecorator} of the
     * {@link DRepresentation} contained(precisely referenced by the DRepresentationDescriptor contained by the dView)
     * in the dView.<BR>
     * For each {@link DSemanticDecorator} we add the root container of the target to the <code>models</code> references
     * (except if this root is the main semantic root of a referenced analysis).
     * 
     * @param dView
     *            An iterator on each DSemanticDecorator to check.
     */
    public static void updateModelsReferences(DView dView) {
        DAnalysis analysis = (DAnalysis) dView.eContainer();
        List<DRepresentationDescriptor> repDescriptors = dView.getOwnedRepresentationDescriptors();
        for (DRepresentationDescriptor repDescriptor : repDescriptors) {
            if (repDescriptor.getRepresentation() != null) {
                updateModelsReferences(analysis, repDescriptor.getTarget());

                Iterator<DSemanticDecorator> iterator = Iterators.filter(repDescriptor.getRepresentation().eAllContents(), DSemanticDecorator.class);
                while (iterator.hasNext()) {
                    EObject semanticTarget = iterator.next().getTarget();
                    updateModelsReferences(analysis, semanticTarget);
                }
            }
        }
    }

    /**
     * Complete the models references of the DAnalysis according to a semantic object<BR>
     * 
     * @param analysis
     *            The DAnalysis for which the <code>models</code> references must be complete.
     * @param semanticObject
     *            the semantic object
     */
    private static void updateModelsReferences(DAnalysis analysis, EObject semanticObject) {
        if (semanticObject != null) {
            EObject rootContainerInSameResource = new EObjectQuery(semanticObject).getResourceContainer();
            // rootContainerInSameResource can be null in case of
            // proxy
            if (rootContainerInSameResource != null) {
                boolean isMainModelOfReferencedAnalysis = false;
                for (DAnalysis referencedAnalysis : new DAnalysisQuery(analysis).getAllReferencedAnalyses()) {
                    Option<EObject> optionalMainModel = new DAnalysisQuery(referencedAnalysis).getMainModel();
                    if (optionalMainModel.some() && optionalMainModel.get().equals(rootContainerInSameResource)) {
                        isMainModelOfReferencedAnalysis = true;
                    }
                }
                if (!isMainModelOfReferencedAnalysis) {
                    analysis.getSemanticResources().add(new ResourceDescriptor(rootContainerInSameResource.eResource().getURI()));
                }
            }
        }
    }

    /**
     * Get all representation containers for a viewpoint.
     * 
     * @param allAnalysis
     *            all analysis to iterate on
     * @param viewpoint
     *            selected viewpoint
     * @return all representation container found
     */
    private static Collection<DView> getContainers(Iterable<DAnalysis> allAnalysis, Viewpoint viewpoint) {
        final List<DView> containers = new ArrayList<DView>();

        for (DAnalysis analysis : allAnalysis) {
            DView container = getDView(analysis, viewpoint);
            if (container != null) {
                containers.add(container);
            }
        }

        return containers;
    }

    /**
     * Get a {@link DView} for a viewpoint.
     * 
     * @param analysis
     *            the analysis to iterate on
     * @param viewpoint
     *            selected viewpoint
     * @return the first {@link DView} found
     */
    private static DView getDView(DAnalysis analysis, Viewpoint viewpoint) {
        DView result = null;
        for (final DView view : analysis.getOwnedViews()) {
            if (viewpoint != null && EqualityHelper.areEquals(viewpoint, view.getViewpoint())) {
                result = view;
                break;
            }
        }
        return result;
    }

    /**
     * Create a representation container for a viewpoint.
     * 
     * @param analysis
     *            the analysis that will contain the new representation container
     * @param viewpoint
     *            selected viewpoint
     * @return the new representation container
     */
    private static DView createContainer(DAnalysis analysis, Viewpoint viewpoint) {
        DView newContainer = ViewpointFactory.eINSTANCE.createDView();
        newContainer.setViewpoint(viewpoint);
        analysis.getOwnedViews().add(newContainer);
        analysis.getSelectedViews().add(newContainer);
        return newContainer;
    }
}
