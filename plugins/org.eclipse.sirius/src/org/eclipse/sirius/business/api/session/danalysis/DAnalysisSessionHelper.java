/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.session.danalysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.query.DAnalysisQuery;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.query.RepresentationDescriptionQuery;
import org.eclipse.sirius.business.api.query.URIQuery;
import org.eclipse.sirius.business.api.query.ViewpointQuery;
import org.eclipse.sirius.business.internal.movida.ViewpointSelection;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationContainer;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

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
    public static DRepresentationContainer findContainer(final EObject semanticRoot, final Viewpoint viewpoint, final Collection<DAnalysis> all, final DAnalysisSelector analysisSelector) {

        final Collection<DRepresentationContainer> containers = getContainers(all, viewpoint);
        if (containers.isEmpty()) {
            return null;
        }

        final Collection<DAnalysis> candidates = new ArrayList<DAnalysis>();
        for (final DRepresentationContainer container : containers) {
            if (container.eContainer() instanceof DAnalysis) {
                candidates.add((DAnalysis) container.eContainer());
            }
        }

        // The first DAnalysis candidates must be the main DAnalysis
        final DAnalysis analysis = candidates.iterator().next();

        DRepresentationContainer freeContainer = null;

        for (final DRepresentationContainer container : containers) {
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
    public static DRepresentationContainer findContainerForAddedRepresentation(final EObject semanticRoot, final Viewpoint viewpoint, final Collection<DAnalysis> all,
            final DAnalysisSelector analysisSelector, final DRepresentation representation) {

        final Collection<DRepresentationContainer> containers = getContainers(all, viewpoint);

        if (containers.isEmpty()) {
            return null;
        }

        final Collection<DAnalysis> candidates = Sets.newLinkedHashSet();
        for (final DRepresentationContainer container : containers) {
            if (container.eContainer() instanceof DAnalysis) {
                candidates.add((DAnalysis) container.eContainer());
            }
        }

        final DAnalysis analysis = selectAnalysis(viewpoint, candidates, analysisSelector, representation);

        DRepresentationContainer freeContainer = null;

        for (final DRepresentationContainer container : containers) {
            if (container.eContainer() == analysis) {
                freeContainer = container;
                break;
            }
        }

        // if the DRepresentationContainer of the selected DAnalysis does not
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
     * @param representation
     *            the added representation.
     * @return the free container if found or <code>null</code> otherwise.
     */
    public static DRepresentationContainer findContainerForAddedRepresentation(DAnalysis analysis, final DRepresentation representation) {
        final Viewpoint viewpoint = new RepresentationDescriptionQuery(DialectManager.INSTANCE.getDescription(representation)).getParentViewpoint();
        return getContainer(analysis, viewpoint);
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
    public static DRepresentationContainer findFreeContainer(final Viewpoint viewpoint, final Collection<DAnalysis> analyses, final DAnalysisSelector analysisSelector) {

        final Collection<DRepresentationContainer> views = getContainers(analyses, null);
        if (views.isEmpty()) {
            return null;
        }

        final Collection<DAnalysis> candidates = new ArrayList<DAnalysis>();
        for (final DRepresentationContainer view : views) {
            if (view.eContainer() instanceof DAnalysis) {
                candidates.add((DAnalysis) view.eContainer());
            }
        }

        // The first DAnalysis candidates must be the main DAnalysis
        final DAnalysis analysis = candidates.iterator().next();

        DRepresentationContainer freeView = null;

        for (final DRepresentationContainer view : views) {
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
    public static DRepresentationContainer findFreeContainerForAddedRepresentation(final Viewpoint viewpoint, final EObject semantic, final Collection<DAnalysis> analyses,
            final DAnalysisSelector analysisSelector, final DRepresentation representation) {

        final Collection<DRepresentationContainer> views = getContainers(analyses, null);
        if (views.isEmpty()) {
            return null;
        }

        final Collection<DAnalysis> candidates = new ArrayList<DAnalysis>();
        for (final DRepresentationContainer view : views) {
            if (view.eContainer() instanceof DAnalysis) {
                candidates.add((DAnalysis) view.eContainer());
            }
        }

        final DAnalysis analysis = DAnalysisSessionHelper.selectAnalysis(viewpoint, candidates, analysisSelector, representation);

        DRepresentationContainer freeView = null;

        for (final DRepresentationContainer view : views) {
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
     * @return the semantic resource or <code>null</code> if it can not be
     *         founded.
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
     * Creates a {@link ViewpointSelection} representing the set of currently
     * selected/enabled viewpoints in the specified session.
     * 
     * @param registry
     *            the registry to be used by the selection.
     * @param session
     *            the session.
     * @return the set of currently selected viewpoints in the session.
     */
    public static ViewpointSelection getViewpointSelection(org.eclipse.sirius.business.internal.movida.registry.ViewpointRegistry registry, DAnalysisSession session) {
        ViewpointSelection selection = new ViewpointSelection(registry);
        Set<URI> selectedBefore = Sets.newHashSet(Iterables.transform(session.getSelectedViewpoints(false), new Function<Viewpoint, URI>() {
            @Override
            public URI apply(Viewpoint from) {
                return new ViewpointQuery(from).getViewpointURI().get();
            }
        }));
        selection.setSelected(selectedBefore);
        return selection;
    }

    /**
     * Complete the models references of the DAnalysis according to the list of
     * DSemanticDecorator.<BR>
     * For each {@link DSemanticDecorator} we add the root container of the
     * target to the <code>models</code> references (except if this root is the
     * main semantic root of a referenced analysis).
     * 
     * @param analysis
     *            The DAnalysis for which the <code>models</code> references
     *            must be complete.
     * @param iterator
     *            An iterator on each DSemanticDecorator to check.
     */
    public static void updateModelsReferences(DAnalysis analysis, Iterator<DSemanticDecorator> iterator) {
        while (iterator.hasNext()) {
            EObject semanticTarget = iterator.next().getTarget();
            if (semanticTarget != null) {
                EObject rootContainerInSameResource = new EObjectQuery(semanticTarget).getResourceContainer();
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
                        analysis.getModels().add(rootContainerInSameResource);
                    }
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
    private static Collection<DRepresentationContainer> getContainers(Iterable<DAnalysis> allAnalysis, Viewpoint viewpoint) {
        final List<DRepresentationContainer> containers = new ArrayList<DRepresentationContainer>();

        for (DAnalysis analysis : allAnalysis) {
            DRepresentationContainer container = getContainer(analysis, viewpoint);
            if (container != null) {
                containers.add(container);
            }
        }

        return containers;
    }

    /**
     * Get a representation container for a viewpoint.
     * 
     * @param analysis
     *            the analysis to iterate on
     * @param viewpoint
     *            selected viewpoint
     * @return the first representation container found
     */
    private static DRepresentationContainer getContainer(DAnalysis analysis, Viewpoint viewpoint) {
        DRepresentationContainer result = null;
        for (final DView view : analysis.getOwnedViews()) {
            if (view instanceof DRepresentationContainer && viewpoint == view.getViewpoint()) {
                result = (DRepresentationContainer) view;
                break;
            }
        }
        return result;
    }

    /**
     * Create a representation container for a viewpoint.
     * 
     * @param analysis
     *            the analysis that will contain the new representation
     *            container
     * @param viewpoint
     *            selected viewpoint
     * @return the new representation container
     */
    private static DRepresentationContainer createContainer(DAnalysis analysis, Viewpoint viewpoint) {
        DRepresentationContainer newContainer = ViewpointFactory.eINSTANCE.createDRepresentationContainer();
        newContainer.setViewpoint(viewpoint);
        newContainer.setInitialized(true);
        analysis.getOwnedViews().add(newContainer);
        analysis.getSelectedViews().add(newContainer);
        return newContainer;
    }
}
