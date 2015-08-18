/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.experimental.sync;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.helper.task.TaskHelper;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.listener.Notification;
import org.eclipse.sirius.common.tools.api.listener.NotificationUtil;
import org.eclipse.sirius.common.tools.api.util.EObjectCouple;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.common.tools.api.util.RefreshIdsHolder;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.ComputedStyleDescriptionRegistry;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManagerRegistry;
import org.eclipse.sirius.diagram.business.api.helper.SiriusDiagramHelper;
import org.eclipse.sirius.diagram.business.api.helper.concern.ConcernService;
import org.eclipse.sirius.diagram.business.api.helper.display.DisplayServiceManager;
import org.eclipse.sirius.diagram.business.api.helper.layers.LayerService;
import org.eclipse.sirius.diagram.business.api.query.ContainerMappingQuery;
import org.eclipse.sirius.diagram.business.api.query.DiagramElementMappingQuery;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.business.api.query.IEdgeMappingQuery;
import org.eclipse.sirius.diagram.business.api.refresh.RefreshExtensionService;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.operations.EdgeMappingImportWrapper;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.DiagramComponentizationHelper;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.EdgeMappingHelper;
import org.eclipse.sirius.diagram.business.internal.query.DDiagramInternalQuery;
import org.eclipse.sirius.diagram.business.internal.query.DNodeContainerExperimentalQuery;
import org.eclipse.sirius.diagram.business.internal.sync.visitor.DiagramElementsHierarchyVisitor;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.AdditionalLayer;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.ContainerMappingImport;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.EdgeMappingImport;
import org.eclipse.sirius.diagram.description.IEdgeMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.MappingBasedDecoration;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.NodeMappingImport;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.MetaClassNotFoundException;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ext.base.cache.KeyCache;
import org.eclipse.sirius.ext.base.collect.GSetIntersection;
import org.eclipse.sirius.ext.base.collect.MultipleCollection;
import org.eclipse.sirius.ext.base.collect.SetIntersection;
import org.eclipse.sirius.tools.api.command.ui.NoUICallback;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.viewpoint.description.DecorationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.eclipse.sirius.viewpoint.description.SemanticBasedDecoration;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;

/**
 * This class is able to synchronize a {@link DSemanticDiagram} instance from a
 * semantic model and a {@link SimpleMapping}.
 * 
 * @author cbrun
 */
public class DDiagramSynchronizer {
    /**
     * A dummy, non-null value used to build valid EObjectCouples when we do not
     * have an actual mapping (even a proxy one) to pass as the second
     * parameter. This can happen in collab mode because CDO can not always
     * create proxies for non-existing VSM elements and uses null instead, but
     * EObjectCouple needs a non-null value.
     */
    private static final EObject FAKE_MAPPING = EcoreFactory.eINSTANCE.createEObject();

    private static Map<String, String> visualIDMap = Collections.unmodifiableMap(new VisualIDMap());

    private DSemanticDiagram diagram;

    private final IInterpreter interpreter;

    private final DiagramDescription description;

    private final ModelAccessor accessor;

    private DDiagramElementSynchronizer sync;

    private final Collection<EObjectCouple> ignoredDuringRefreshProcess = new ArrayList<EObjectCouple>();

    private Session session;

    private DEdgeSynchronizerHelper edgeHelper;

    private DNodeSynchronizerHelper nodHelper;

    private DiagramMappingsManager diagramMappingsManager;

    private SetMultimap<EObjectCouple, DDiagramElement> previousCandidatesCache;

    private MappingsUpdater mappingsUpdater;

    private HashSet<DDiagramElement> edgesDones;

    private boolean forceRetrieve;

    private RefreshIdsHolder ids;

    /**
     * Create a new synchronizer.
     * 
     * @param interpreter
     *            expression interpreter.
     * @param description
     *            diagram specification.
     * @param accessor
     *            model access layer.
     */
    public DDiagramSynchronizer(final IInterpreter interpreter, final DiagramDescription description, final ModelAccessor accessor) {
        this.interpreter = interpreter;
        this.description = description;
        this.accessor = accessor;
    }

    /**
     * return the diagram.
     * 
     * @return the diagram
     */
    public DSemanticDiagram getDiagram() {
        return this.diagram;
    }

    public RefreshIdsHolder getFactory() {
        return this.ids;
    }

    /**
     * return the diagram.
     * 
     * @return the diagram
     */
    public DDiagramElementSynchronizer getElementSynchronizer() {
        return this.sync;
    }

    /**
     * set a new diagram.
     * 
     * @param diagram
     *            the diagram to set
     */
    public void setDiagram(final DSemanticDiagram diagram) {
        this.diagram = diagram;
        this.session = SessionManager.INSTANCE.getSession(diagram.getTarget());
        this.sync = new DDiagramElementSynchronizer(this.diagram, this.interpreter, this.accessor);
        initDiagramRelatedFields();
    }

    /**
     * Initialize the diagram to synchronize.
     * 
     * @param name
     *            name of the diagram.
     * @param target
     *            the semantic element corresponding to the diagram root.
     * @param monitor
     *            to track the progress.
     */
    public void initDiagram(final String name, final EObject target, final IProgressMonitor monitor) {
        try {
            monitor.beginTask("Init diagram", 4);
            this.session = SessionManager.INSTANCE.getSession(target);
            this.diagram = createEmptyDiagram(name, target);
            monitor.worked(1);
            applyInitializationOperation(target);
            monitor.worked(1);
            this.sync = new DDiagramElementSynchronizer(this.diagram, this.interpreter, this.accessor);
            ConcernService.setCurrentConcern(this.diagram, this.description.getDefaultConcern());
            activateInitialLayers();
            monitor.worked(1);
            if (this.diagram.getFilterVariableHistory() == null) {
                this.diagram.setFilterVariableHistory(DiagramFactory.eINSTANCE.createFilterVariableHistory());
            }
            initDiagramRelatedFields();
            monitor.worked(1);
        } finally {
            monitor.done();
        }
    }

    private DSemanticDiagram createEmptyDiagram(String name, EObject target) {
        DSemanticDiagram result = description.createDiagram();
        result.setName(name);
        result.setDescription(description);
        result.setTarget(target);
        return result;
    }

    private void applyInitializationOperation(final EObject target) {
        if (description.getDiagramInitialisation() != null && description.getDiagramInitialisation().getFirstModelOperations() != null) {
            try {
                new TaskHelper(accessor, createUICallBack()).buildTaskFromModelOperation(this.diagram, target, description.getDiagramInitialisation().getFirstModelOperations()).execute();
            } catch (MetaClassNotFoundException e) {
                // TODO should we log something
            } catch (FeatureNotFoundException e) {
                // TODO should we log something
            }
        }
    }

    private UICallBack createUICallBack() {
        return new NoUICallback();
    }

    private void initDiagramRelatedFields() {
        this.ids = RefreshIdsHolder.getOrCreateHolder(diagram);
        this.edgeHelper = new DEdgeSynchronizerHelper(this, diagram, accessor);
        this.nodHelper = new DNodeSynchronizerHelper(this, diagram, accessor);
        this.diagramMappingsManager = DiagramMappingsManagerRegistry.INSTANCE.getDiagramMappingsManager(session, diagram);
        this.mappingsUpdater = new MappingsUpdater(diagram, diagramMappingsManager, this, ids);

    }

    private void activateInitialLayers() {
        final Collection<Layer> layersToActivate = getAllInitialyActiveLayers();
        this.diagram.getActivatedLayers().addAll(layersToActivate);
    }

    private Collection<Layer> getAllInitialyActiveLayers() {
        final Predicate<Layer> isActiveByDefault = new Predicate<Layer>() {
            @Override
            public boolean apply(final Layer layer) {
                return (layer instanceof AdditionalLayer) && (((AdditionalLayer) layer).isActiveByDefault() || !((AdditionalLayer) layer).isOptional());
            }
        };

        final Collection<Layer> result = new ArrayList<Layer>();
        final Layer mandatoryLayer = this.description.getDefaultLayer();
        if (mandatoryLayer != null) {
            result.add(mandatoryLayer);
        }
        result.addAll(Collections2.filter(this.description.getAdditionalLayers(), isActiveByDefault));
        result.addAll(Collections2.filter(DiagramComponentizationHelper.getContributedLayers(this.description, this.session.getSelectedViewpoints(false)), isActiveByDefault));
        return result;
    }

    /**
     * If new additional layers have been added into the VSM, we have to
     * activate them.
     */
    private void activateNewMandatoryAdditionalLayers() {
        Collection<Layer> allMandatoryAdditionalLayers = getAllMandatoriesAdditionalLayers();
        Collection<Layer> allMandatoryAdditionalLayersToAdd = new ArrayList<Layer>();

        EList<Layer> activatedLayers = diagram.getActivatedLayers();

        for (Layer layer : allMandatoryAdditionalLayers) {
            if (!activatedLayers.contains(layer)) {
                allMandatoryAdditionalLayersToAdd.add(layer);
            }
        }

        diagram.getActivatedLayers().addAll(allMandatoryAdditionalLayersToAdd);
    }

    /**
     * Get from descriptions the list of mandatories layers.
     * 
     * @return all mandatories layers.
     */
    private Collection<Layer> getAllMandatoriesAdditionalLayers() {
        final Predicate<Layer> isMandatory = new Predicate<Layer>() {
            @Override
            public boolean apply(final Layer layer) {
                return (layer instanceof AdditionalLayer) && !((AdditionalLayer) layer).isOptional();
            }
        };

        final Collection<Layer> result = new ArrayList<Layer>();
        final Layer mandatoryLayer = this.description.getDefaultLayer();
        if (mandatoryLayer != null) {
            result.add(mandatoryLayer);
        }
        result.addAll(Collections2.filter(this.description.getAdditionalLayers(), isMandatory));
        result.addAll(Collections2.filter(DiagramComponentizationHelper.getContributedLayers(this.description, this.session.getSelectedViewpoints(false)), isMandatory));
        return result;
    }

    /**
     * refresh the content.
     * 
     * @param monitor
     *            progress monitor for the processing.
     */
    public void refresh(final IProgressMonitor monitor) {
        DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.REFRESH_DIAGRAM_KEY);
        refreshOperation(monitor);
        DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.REFRESH_DIAGRAM_KEY);
    }

    private void refreshOperation(final IProgressMonitor monitor) {
        try {
            KeyCache.DEFAULT.clear();
            // Semantic changes should be possible when a representation
            // representation
            // is locked (CDO)
            // We launch a refresh only if the diagram can be edited
            // so that the refresh does not lead to the modification of
            // non-editable
            // elements and hence cause a Rollback
            if (this.accessor.getPermissionAuthority().canEditInstance(diagram)) {
                RuntimeLoggerManager.INSTANCE.clear(this.description);

                activateNewMandatoryAdditionalLayers();

                final Map<DiagramElementMapping, Collection<EdgeTarget>> mappingsToEdgeTargets = new HashMap<DiagramElementMapping, Collection<EdgeTarget>>();
                final Map<EdgeMapping, Collection<MappingBasedDecoration>> edgeToMappingBasedDecoration = new HashMap<EdgeMapping, Collection<MappingBasedDecoration>>();
                final Map<String, Collection<SemanticBasedDecoration>> edgeToSemanticBasedDecoration = new HashMap<String, Collection<SemanticBasedDecoration>>();

                /*
                 * retrieve mappings
                 */
                final List<NodeMapping> nodeMappings = diagramMappingsManager.getNodeMappings();
                final List<EdgeMapping> edgeMappings = diagramMappingsManager.getEdgeMappings();
                final List<ContainerMapping> containerMappings = diagramMappingsManager.getContainerMappings();

                RefreshExtensionService.getInstance().beforeRefresh(this.diagram);

                final int mappingNumbers = nodeMappings.size() + edgeMappings.size() + containerMappings.size();
                monitor.beginTask("Refreshing mappings", mappingNumbers);

                /*
                 * compute a first time the cache with old mappings => updater
                 * need the cache
                 */
                computePreviousCandidatesCache();
                /* update mappings */
                mappingsUpdater.updateMappings();
                /* compute a second time the cache with updated mapping */
                computePreviousCandidatesCache();

                fillIgnoredElements();

                final Set<AbstractDNodeCandidate> elementsCreated = LayerService.withoutLayersMode(description) ? null : new HashSet<AbstractDNodeCandidate>();

                /* Let's refresh the node mappings. */
                for (final NodeMapping mapping : nodeMappings) {
                    refreshNodeMapping(mappingsToEdgeTargets, this.diagram, mapping, elementsCreated, new SubProgressMonitor(monitor, 1));
                }

                /* Let's refresh the container mappings */
                if (elementsCreated != null) {
                    elementsCreated.clear();
                }

                for (final ContainerMapping mapping : containerMappings) {
                    refreshContainerMapping(mappingsToEdgeTargets, this.diagram, mapping, elementsCreated, false, false, new SubProgressMonitor(monitor, 1));
                }

                /* handle multiple importers . */
                handleImportersIssues();

                /* Compute the decorations. */
                computeDecorations(mappingsToEdgeTargets, edgeToSemanticBasedDecoration, edgeToMappingBasedDecoration);

                /*
                 * now all the nodes/containers are done and ready in the
                 * mappintToEdgeTarget map.
                 */
                edgesDones = new HashSet<DDiagramElement>();

                processEdgeMappingsRefresh(edgeMappings, mappingsToEdgeTargets, edgeToMappingBasedDecoration, edgeToSemanticBasedDecoration, monitor);

                edgesDones.clear();

                deleteIgnoredElementsAndDuplicates();

                /* Garbage collect orphan computed StyleDescription. */
                removeOrphanComputedStyleDescriptions();

                RefreshExtensionService.getInstance().postRefresh(this.diagram);
                /* We can now clear the cache. */
                clearCache();
            }
            KeyCache.DEFAULT.clear();
        } finally {
            monitor.done();
        }
    }

    /**
     * Order and process edge mappings refresh to have both end of the edge
     * refreshed before its own refresh.
     * 
     * @param edgeMappings
     *            list of EdgeMapping to refresh
     * @param mappingsToEdgeTargets
     *            map of refreshed EdgeMapping
     * @param edgeToMappingBasedDecoration
     * @param edgeToSemanticBasedDecoration
     * @param monitor
     *            the current Monitor
     */
    private void processEdgeMappingsRefresh(List<EdgeMapping> edgeMappings, final Map<DiagramElementMapping, Collection<EdgeTarget>> mappingsToEdgeTargets,
            Map<EdgeMapping, Collection<MappingBasedDecoration>> edgeToMappingBasedDecoration, Map<String, Collection<SemanticBasedDecoration>> edgeToSemanticBasedDecoration,
            final IProgressMonitor monitor) {

        final IsMappingOfCurrentDiagramDescription isMappingOfCurrentDiagramDescription = new IsMappingOfCurrentDiagramDescription(description);

        Predicate<EdgeMapping> edgeMappingWithoutEdgeAsSourceOrTarget = new Predicate<EdgeMapping>() {
            @Override
            public boolean apply(EdgeMapping input) {
                // Valid if source mapping and target mapping are not
                // EdgeMappings
                Iterable<EdgeMapping> edgeSourceMappings = Iterables.filter(Iterables.filter(input.getSourceMapping(), EdgeMapping.class), isMappingOfCurrentDiagramDescription);
                Iterable<EdgeMapping> edgeTargetMappings = Iterables.filter(Iterables.filter(input.getTargetMapping(), EdgeMapping.class), isMappingOfCurrentDiagramDescription);
                return Iterables.isEmpty(edgeSourceMappings) && Iterables.isEmpty(edgeTargetMappings);
            }
        };

        final Predicate<EdgeMapping> refreshedEdgeMapping = new Predicate<EdgeMapping>() {
            @Override
            public boolean apply(EdgeMapping input) {
                // Valid if edge mapping has been refreshed or is not in the
                // activated layers
                return mappingsToEdgeTargets.keySet().contains(input)
                        || !getDiagram().getActivatedLayers().contains(new EObjectQuery(input).getFirstAncestorOfType(DescriptionPackage.eINSTANCE.getLayer()));
            }
        };

        Predicate<EdgeMapping> unrefreshedEdgeMappingWithRefreshedEdgeAsSourceOrTarget = new Predicate<EdgeMapping>() {
            @Override
            public boolean apply(EdgeMapping input) {
                // Valid if the EdgeMapping is not refresh and the source or
                // target EdgeMapping has been refreshed
                boolean result = !mappingsToEdgeTargets.keySet().contains(input);
                Iterable<EdgeMapping> edgeSourceMappings = Iterables.filter(Iterables.filter(input.getSourceMapping(), EdgeMapping.class), isMappingOfCurrentDiagramDescription);
                Iterable<EdgeMapping> edgeTargetMappings = Iterables.filter(Iterables.filter(input.getTargetMapping(), EdgeMapping.class), isMappingOfCurrentDiagramDescription);
                return result && Iterables.all(edgeSourceMappings, refreshedEdgeMapping) && Iterables.all(edgeTargetMappings, refreshedEdgeMapping);
            }
        };

        // Firstly, we need to refresh the EdgeMapping having no other
        // EdgeMapping as source neither as target
        for (final EdgeMapping mapping : Iterables.filter(edgeMappings, edgeMappingWithoutEdgeAsSourceOrTarget)) {
            refreshEdgeMapping(diagramMappingsManager, mappingsToEdgeTargets, mapping, edgeToMappingBasedDecoration, edgeToSemanticBasedDecoration, new SubProgressMonitor(monitor, 1));
            mappingsToEdgeTargets.put(mapping, (Collection) diagram.getEdgesFromMapping(mapping));
        }

        // The list of EdgeMappings that have not been processed.
        ArrayList<EdgeMapping> remaingEdgeMappingsToRefresh = Lists.newArrayList(edgeMappings);
        remaingEdgeMappingsToRefresh.removeAll(mappingsToEdgeTargets.keySet());

        boolean noRefreshImpliesCycleDetected = true;

        // Then, we will refresh the other EdgeMapping. An EdgeMapping with
        // another EdgeMapping as source or target can be refreshed only if this
        // other EdgeMapping has been refreshed (therefore is included in the
        // mappingsToEdgeTargets map).
        while (!mappingsToEdgeTargets.keySet().containsAll(edgeMappings)) {
            for (final EdgeMapping mapping : Iterables.filter(remaingEdgeMappingsToRefresh, unrefreshedEdgeMappingWithRefreshedEdgeAsSourceOrTarget)) {
                refreshEdgeMapping(diagramMappingsManager, mappingsToEdgeTargets, mapping, edgeToMappingBasedDecoration, edgeToSemanticBasedDecoration, new SubProgressMonitor(monitor, 1));
                mappingsToEdgeTargets.put(mapping, (Collection) diagram.getEdgesFromMapping(mapping));
                noRefreshImpliesCycleDetected = false;
            }

            remaingEdgeMappingsToRefresh = Lists.newArrayList(edgeMappings);
            remaingEdgeMappingsToRefresh.removeAll(mappingsToEdgeTargets.keySet());
            if (noRefreshImpliesCycleDetected) {
                // No refresh has been done. That means that there are
                // EdgeMapping that need refresh but have as source or target an
                // EdgeMapping that is not refreshed because of a cycle between
                // EdgeMappings. Therefore, we stop the refresh here in order to
                // avoid a dead lock.
                return;
            }
            noRefreshImpliesCycleDetected = true;
        }

    }

    private void computePreviousCandidatesCache() {
        previousCandidatesCache = LinkedHashMultimap.create();
        retrievePreviousCandidates(diagram, previousCandidatesCache);
    }

    /**
     * Clear the cache.
     */
    private void clearCache() {
        previousCandidatesCache = null;
    }

    private void removeOrphanComputedStyleDescriptions() {
        DDiagramInternalQuery dDiagramInternalQuery = new DDiagramInternalQuery(diagram);
        ComputedStyleDescriptionRegistry computedStyleDescriptionRegistry = dDiagramInternalQuery.getComputedStyleDescriptionRegistry(false);
        if (computedStyleDescriptionRegistry != null) {
            Collection<StyleDescription> usedComputedStyleDescritions = dDiagramInternalQuery.getUsedComputedStyleDescritions();
            computedStyleDescriptionRegistry.getComputedStyleDescriptions().retainAll(usedComputedStyleDescritions);
        }
    }

    private void handleImportersIssues() {
        for (final DiagramElementMapping mapping : this.diagramMappingsManager.getOtherImportersMappings()) {
            EObjectCouple key = new EObjectCouple(this.diagram, mapping, ids);
            this.ignoredDuringRefreshProcess.remove(key);
            final Collection<DDiagramElement> elements = previousCandidatesCache.get(key);

            if (elements == null) {
                return;
            }

            for (final DiagramElementMapping subMapping : mapping.getAllMappings()) {
                for (final DDiagramElement element : elements) {
                    key = new EObjectCouple(element, subMapping, ids);
                    this.ignoredDuringRefreshProcess.remove(key);
                }
            }
        }
    }

    private void fillIgnoredElements() {
        this.ignoredDuringRefreshProcess.addAll(this.previousCandidatesCache.keySet());
    }

    private void deleteIgnoredElementsAndDuplicates() {
        DisplayServiceManager.INSTANCE.getDisplayService().activateCache();
        DiagramMappingsManager mappingManager = DiagramMappingsManagerRegistry.INSTANCE.getDiagramMappingsManager(session, this.diagram);
        NotificationUtil.sendNotification(this.diagram, Notification.Kind.START, Notification.VISIBILITY);

        for (final EObjectCouple ignoredElement : this.ignoredDuringRefreshProcess) {
            final Collection<DDiagramElement> elements = previousCandidatesCache.get(ignoredElement);
            for (final DDiagramElement element : elements) {
                final EObject semanticElement = element.getTarget();
                /*
                 * if semantic element is null then node is a remaining dust
                 * that we should delete
                 */
                if (semanticElement == null) {
                    this.accessor.eDelete(element, session != null ? session.getSemanticCrossReferencer() : null);
                }

                if (!shouldKeepElement(mappingManager, element)) {
                    this.accessor.eDelete(element, session != null ? session.getSemanticCrossReferencer() : null);
                } else {
                    hideElementToUser(element);
                }
            }
        }

        NotificationUtil.sendNotification(this.diagram, Notification.Kind.STOP, Notification.VISIBILITY);
        DisplayServiceManager.INSTANCE.getDisplayService().deactivateCache();
    }

    private void hideElementToUser(final DDiagramElement element) {
        if (element.isVisible()) {
            element.setVisible(false);
        }
    }

    private boolean shouldKeepElement(DiagramMappingsManager mappingManager, final DDiagramElement element) {
        return element.getParentDiagram() != null && !DisplayServiceManager.INSTANCE.getDisplayService().computeVisibility(mappingManager, this.diagram, element);
    }

    /**
     * compute all decorations.
     * 
     * @param mappingsToEdgeTargets
     *            the mapping to edge targets
     * @param edgeToSemanticBasedDecoration
     *            an empty map
     * @param edgeToMappingBasedDecoration
     *            an empty map
     */
    public void computeDecorations(final Map<DiagramElementMapping, Collection<EdgeTarget>> mappingsToEdgeTargets,
            final Map<String, Collection<SemanticBasedDecoration>> edgeToSemanticBasedDecoration, final Map<EdgeMapping, Collection<MappingBasedDecoration>> edgeToMappingBasedDecoration) {
        final List<Layer> activatedLayers = diagram.getActivatedLayers();

        for (final Layer layer : activatedLayers) {
            if (layer.getDecorationDescriptionsSet() != null && layer.getDecorationDescriptionsSet().getDecorationDescriptions().size() > 0) {
                for (final DecorationDescription decorationDescription : layer.getDecorationDescriptionsSet().getDecorationDescriptions()) {
                    if (decorationDescription instanceof MappingBasedDecoration) {
                        computeDecoration((MappingBasedDecoration) decorationDescription, mappingsToEdgeTargets, edgeToMappingBasedDecoration);
                    } else if (decorationDescription instanceof SemanticBasedDecoration) {
                        computeDecoration((SemanticBasedDecoration) decorationDescription, mappingsToEdgeTargets, edgeToSemanticBasedDecoration);
                    }
                }
            }
        }
    }

    private void computeDecoration(final SemanticBasedDecoration decorationDescription, final Map<DiagramElementMapping, Collection<EdgeTarget>> mappingsToEdgeTargets,
            final Map<String, Collection<SemanticBasedDecoration>> edgeToSemanticBasedDecoration) {
        final String domainClass = decorationDescription.getDomainClass();
        for (final Collection<EdgeTarget> collection : mappingsToEdgeTargets.values()) {
            for (final DDiagramElement element : Iterables.filter(collection, DDiagramElement.class)) {
                if (accessor.eInstanceOf(element.getTarget(), decorationDescription.getDomainClass())) {
                    this.sync.addDecoration(element, decorationDescription);
                }
            }
        }
        if (!edgeToSemanticBasedDecoration.containsKey(domainClass)) {
            edgeToSemanticBasedDecoration.put(domainClass, new HashSet<SemanticBasedDecoration>());
        }
        edgeToSemanticBasedDecoration.get(domainClass).add(decorationDescription);
    }

    private void computeDecoration(final MappingBasedDecoration decorationDescription, final Map<DiagramElementMapping, Collection<EdgeTarget>> mappingsToEdgeTargets,
            final Map<EdgeMapping, Collection<MappingBasedDecoration>> edgeToMappingBasedDecoration) {
        for (final DiagramElementMapping mapping : decorationDescription.getMappings()) {
            if (mapping instanceof AbstractNodeMapping) {
                final Collection<EdgeTarget> targets = mappingsToEdgeTargets.get(mapping);
                if (targets != null) {
                    for (final DDiagramElement element : Iterables.filter(targets, DDiagramElement.class)) {
                        this.sync.addDecoration(element, decorationDescription);
                    }
                }
            } else if (mapping instanceof EdgeMapping) {
                if (!edgeToMappingBasedDecoration.containsKey(mapping)) {
                    edgeToMappingBasedDecoration.put((EdgeMapping) mapping, new HashSet<MappingBasedDecoration>());
                }
                edgeToMappingBasedDecoration.get(mapping).add(decorationDescription);
            }
        }

    }

    private void convertType(final EObject eObject) {
        final EStructuralFeature typeEFeature = eObject.eClass().getEStructuralFeature("type"); //$NON-NLS-1$
        if (typeEFeature != null) {
            if (visualIDMap.containsKey(eObject.eGet(typeEFeature))) {
                eObject.eSet(typeEFeature, visualIDMap.get(eObject.eGet(typeEFeature)));
            }
        }
    }

    private AbstractDNode handleListAttributeChangeOnMapping(final AbstractDNode node, final ContainerMapping containerMapping, final DragAndDropTarget viewContainer, IProgressMonitor monitor) {
        final boolean isListContainer = new ContainerMappingQuery(containerMapping).isListContainer();
        if ((node instanceof DNodeContainer && isListContainer) || (node instanceof DNodeList && !isListContainer)) {
            final AbstractDNodeCandidate newCandidate = new AbstractDNodeCandidate(containerMapping, node.getTarget(), viewContainer, ids);

            final Set<AbstractDNodeCandidate> newCandidates = new HashSet<AbstractDNodeCandidate>(1);
            newCandidates.add(newCandidate);
            List<AbstractDNode> newContents = createNewContent(newCandidates, true, viewContainer, containerMapping, false, monitor);
            if (!newContents.isEmpty()) {
                SiriusDiagramHelper.removeNodeFromContainer(viewContainer, false, node);

                final AbstractDNode newNode = newContents.get(0);
                convertGmfModelTypes(node, newNode);
                return newNode;
            }
        }
        return node;
    }

    private void convertGmfModelTypes(final AbstractDNode node, final AbstractDNode newNode) {
        final Collection<Setting> settings = session.getSemanticCrossReferencer().getInverseReferences(node);
        for (final Setting setting : settings) {
            final EObject referencer = setting.getEObject();
            if (setting.getEStructuralFeature().isMany()) {
                List values = Lists.newArrayList((List) setting.get(false));
                values.remove(referencer);
                values.add(newNode);
                setting.set(values);
            }  else {
               setting.set(newNode);
            }

            convertType(referencer);

            final List<?> children = getChildren(referencer);
            for (final Object eChild : children) {
                if (eChild instanceof EObject) {
                    convertType((EObject) eChild);
                    final List<EObject> toRemove = new ArrayList<EObject>();
                    for (final Object childOfChild : getChildren((EObject) eChild)) {
                        if (childOfChild instanceof EObject) {
                            toRemove.add((EObject) childOfChild);
                        }
                    }
                    for (final EObject eObject : toRemove) {
                        EcoreUtil.remove(eObject);
                    }
                }
            }
        }
    }

    private List<?> getChildren(final EObject eObject) {
        final EStructuralFeature childrenEFeature = eObject.eClass().getEStructuralFeature("children"); //$NON-NLS-1$
        if (childrenEFeature != null) {
            final EList<?> children = (EList<?>) eObject.eGet(childrenEFeature);
            return children;
        }
        return Collections.emptyList();
    }

    private AbstractDNode handleListAttributeChangeOnContainerMapping(final AbstractDNode node, final AbstractNodeMapping nodeMapping, final DDiagramElementContainer diagramElementContainer,
            IProgressMonitor monitor) {
        final ContainerMapping containerMapping = diagramElementContainer.getActualMapping();
        final boolean isListContainer = new ContainerMappingQuery(containerMapping).isListContainer();
        if ((node instanceof DNode && isListContainer) || (node instanceof DNodeListElement && !isListContainer)) {
            final AbstractDNodeCandidate newCandidate = new AbstractDNodeCandidate(nodeMapping, node.getTarget(), diagramElementContainer, ids);
            final Set<AbstractDNodeCandidate> newCandidates = new HashSet<AbstractDNodeCandidate>(1);
            newCandidates.add(newCandidate);

            List<AbstractDNode> newContents = createNewContent(newCandidates, true, diagramElementContainer, nodeMapping, false, monitor);

            if (!newContents.isEmpty()) {
                SiriusDiagramHelper.removeNodeFromContainer(diagramElementContainer, false, node);
                return newContents.get(0);
            }
        }
        return node;
    }

    /**
     * Refresh a container mapping putting in the given mappingsToEdgeTargets
     * newly created and kept containers.
     * 
     * @param mappingsToEdgeTargets
     *            map containing for each mapping the edge targets existing in
     *            the diagram.
     * @param viewContainer
     *            current view container.
     * @param mapping
     *            current mapping to refresh.
     * @param border
     *            true if refreshing borders, false otherwise.
     * @param orderBySemantic
     *            tells if children must be grouped by mapping and ordered by
     *            semantics.
     * @param monitor
     *            a {@link IProgressMonitor} to show progression of refresh of
     *            container
     */
    private void refreshContainerMapping(final Map<DiagramElementMapping, Collection<EdgeTarget>> mappingsToEdgeTargets, final DragAndDropTarget viewContainer, final ContainerMapping mapping,
            final Set<AbstractDNodeCandidate> semanticFilter, final boolean border, final boolean orderBySemantic, IProgressMonitor monitor) {
        /* computed added/removed candidates */
        final SetIntersection<AbstractDNodeCandidate> status = computeCurrentStatus(viewContainer, mapping, semanticFilter);
        Collection<AbstractDNodeCandidate> newElements = status.getNewElements();
        try {
            monitor.beginTask("Refresh containers", 3);

            deleteCandidatesToRemove(status, new SubProgressMonitor(monitor, 1));
            // kept and created nodes
            final Collection<AbstractDNode> keptNodes = Lists.newArrayList();
            final Collection<AbstractDNode> createdNodes = Lists.newArrayList();

            if (orderBySemantic) {
                handleKeptAndNewNodesWithOrder(viewContainer, mapping, status, keptNodes, createdNodes, new SubProgressMonitor(monitor, 1));
            } else {
                handleKeptNodes(viewContainer, status, keptNodes, false, new SubProgressMonitor(monitor, 1));
                createdNodes.addAll(createNewContent(newElements, viewContainer, mapping, border, new SubProgressMonitor(monitor, 1)));
            }

            /* kept candidates and new ones are handled in the same way. */
            final Collection<DDiagramElement> addedNodes = new MultipleCollection<DDiagramElement>();
            addedNodes.addAll(createdNodes);
            addedNodes.addAll(keptNodes);
            putOrAdd(mappingsToEdgeTargets, mapping, addedNodes);

            IProgressMonitor subMonitor = new SubProgressMonitor(monitor, 1);
            try {
                subMonitor.beginTask("", addedNodes.size()); //$NON-NLS-1$
                /* now refresh the mappings owned by container mappings */
                for (final DDiagramElement newElem : addedNodes) {
                    if (newElem instanceof DDiagramElementContainer) {
                        final DDiagramElementContainer newNode = (DDiagramElementContainer) newElem;
                        refreshMappingsInContainerMapping(mappingsToEdgeTargets, newNode, new SubProgressMonitor(subMonitor, 1));
                    }
                }
            } finally {
                subMonitor.done();
            }
        } finally {
            monitor.done();
        }
    }

    private void refreshMappingsInContainerMapping(final Map<DiagramElementMapping, Collection<EdgeTarget>> mappingsToEdgeTargets, final DDiagramElementContainer newNode, IProgressMonitor monitor) {
        if (this.accessor.getPermissionAuthority().canEditInstance(newNode)) {
            try {
                monitor.beginTask("Refresh container childs", 1);

                /* Refresh border node mappings */
                refreshBorderNodeMapping(mappingsToEdgeTargets, newNode, null, new SubProgressMonitor(monitor, 1));

                /* If it's a DNodeContainer then we can have sub-containers. */
                if (newNode instanceof DNodeContainer) {
                    DNodeContainer newContainer = (DNodeContainer) newNode;
                    /* here we have a recursive call for recursive containment. */
                    final List<ContainerMapping> childMappings = diagramMappingsManager.getContainerMappings(newContainer);
                    final List<NodeMapping> childNodeMappings = diagramMappingsManager.getNodeMappings(newContainer);

                    final Set<AbstractDNodeCandidate> elementsCreated = LayerService.withoutLayersMode(description) ? null : new HashSet<AbstractDNodeCandidate>();

                    boolean stackContainer = new DNodeContainerExperimentalQuery(newContainer).isRegionContainer();
                    for (final ContainerMapping child : childMappings) {
                        refreshContainerMapping(mappingsToEdgeTargets, newNode, child, elementsCreated, false, stackContainer, new SubProgressMonitor(monitor, 1));
                    }

                    if (elementsCreated != null) {
                        elementsCreated.clear();
                    }

                    if (!stackContainer) {
                        for (final NodeMapping child : childNodeMappings) {
                            refreshNodeMapping(mappingsToEdgeTargets, newNode, child, elementsCreated, new SubProgressMonitor(monitor, 1));
                        }
                    }
                } else if (newNode instanceof DNodeList) {
                    final List<NodeMapping> childNodeMappings = diagramMappingsManager.getNodeMappings((DNodeList) newNode);
                    final Set<AbstractDNodeCandidate> elementsCreated = LayerService.withoutLayersMode(description) ? null : new HashSet<AbstractDNodeCandidate>();

                    for (final NodeMapping child : childNodeMappings) {
                        refreshNodeMapping(mappingsToEdgeTargets, newNode, child, elementsCreated, new SubProgressMonitor(monitor, 1));
                    }
                }
            } finally {
                monitor.done();
            }
        }
    }

    /**
     * Refresh a node mapping.
     * 
     * @param mappingsToAbstractNodes
     *            this map will be updated with newly created and kept nodes.
     * @param viewContainer
     *            the current view container.
     * @param mapping
     *            the mapping to update.
     * @param monitor
     *            a {@link IProgressMonitor} to show progression of nodes
     *            refresh
     */
    private void refreshNodeMapping(final Map<DiagramElementMapping, Collection<EdgeTarget>> mappingsToAbstractNodes, final DragAndDropTarget viewContainer, final NodeMapping mapping,
            final Set<AbstractDNodeCandidate> semanticFilter, IProgressMonitor monitor) {
        /* compute added/removed candidates. */
        final SetIntersection<AbstractDNodeCandidate> status = computeCurrentStatus(viewContainer, mapping, semanticFilter);
        try {
            monitor.beginTask("Nodes refresh", 3);

            deleteCandidatesToRemove(status, new SubProgressMonitor(monitor, 1));

            // kept and created nodes
            final Collection<AbstractDNode> keptNodes = Lists.newArrayList();
            final Collection<AbstractDNode> createdNodes = Lists.newArrayList();

            handleKeptAndNewNodesWithOrder(viewContainer, mapping, status, keptNodes, createdNodes, new SubProgressMonitor(monitor, 1));

            /* kept candidates and new ones are handled in the same way. */
            final Collection<DDiagramElement> addedNodes = new MultipleCollection<DDiagramElement>();
            addedNodes.addAll(createdNodes);
            addedNodes.addAll(keptNodes);
            putOrAdd(mappingsToAbstractNodes, mapping, addedNodes);

            /* now try to create bordered nodes from the created nodes. */
            for (final AbstractDNode newNode : Iterables.filter(addedNodes, AbstractDNode.class)) {
                refreshBorderNodeMapping(mappingsToAbstractNodes, newNode, semanticFilter, new SubProgressMonitor(monitor, 1));
            }
        } finally {
            monitor.done();
        }
    }

    /**
     * Refresh border node mappings of a node.
     * 
     * @param mappingsToAbstractNodes
     *            map which will get updated with the kept and newly created
     *            nodes.
     * @param newNode
     *            new node to refresh.
     * @param monitor
     *            a {@link IProgressMonitor} to show progression of refresh of
     *            borderedNodes
     */
    private void refreshBorderNodeMapping(final Map<DiagramElementMapping, Collection<EdgeTarget>> mappingsToAbstractNodes, final AbstractDNode newNode,
            final Set<AbstractDNodeCandidate> semanticFilter, IProgressMonitor monitor) {
        if (newNode instanceof DragAndDropTarget && this.accessor.getPermissionAuthority().canEditInstance(newNode)) {
            final DragAndDropTarget newNodeDDT = (DragAndDropTarget) newNode;
            List<NodeMapping> borderedNodeMappings = diagramMappingsManager.getBorderedNodeMappings(newNode);
            try {
                monitor.beginTask("BorderedNodes refresh", 4 * borderedNodeMappings.size());

                for (final NodeMapping bordermapping : borderedNodeMappings) {
                    final SetIntersection<AbstractDNodeCandidate> borderStatus = computeCurrentStatus(newNodeDDT, bordermapping, semanticFilter);
                    deleteCandidatesToRemove(borderStatus, new SubProgressMonitor(monitor, 1));

                    // kept and created nodes
                    final Collection<AbstractDNode> keptNodes = Lists.newArrayList();
                    final Collection<AbstractDNode> createdNodes = Lists.newArrayList();

                    createdNodes.addAll(createNewContent(borderStatus.getNewElements(), newNodeDDT, bordermapping, true, new SubProgressMonitor(monitor, 1)));
                    handleKeptNodes(newNodeDDT, borderStatus, keptNodes, true, new SubProgressMonitor(monitor, 1));

                    /* kept candidates and new ones are handled in the same way. */
                    final Collection<DDiagramElement> addedBorderNodes = new MultipleCollection<DDiagramElement>();
                    addedBorderNodes.addAll(createdNodes);
                    addedBorderNodes.addAll(keptNodes);
                    putOrAdd(mappingsToAbstractNodes, bordermapping, addedBorderNodes);

                    /* Now try to create bordered nodes from the created nodes. */
                    for (final AbstractDNode bNewNode : Iterables.filter(addedBorderNodes, AbstractDNode.class)) {
                        refreshBorderNodeMapping(mappingsToAbstractNodes, bNewNode, semanticFilter, new SubProgressMonitor(monitor, 1));
                    }
                    monitor.worked(1);
                }
            } finally {
                monitor.done();
            }
        }
    }

    private SetIntersection<AbstractDNodeCandidate> computeCurrentStatus(final DragAndDropTarget viewcontainer, final AbstractNodeMapping mapping, final Set<AbstractDNodeCandidate> semanticFilter) {
        final SetIntersection<AbstractDNodeCandidate> biSet = new GSetIntersection<AbstractDNodeCandidate>();
        addPreviousCandidates(biSet, viewcontainer, mapping);
        addNowNodeCandidates(biSet, viewcontainer, mapping, semanticFilter);
        return biSet;
    }

    private void deleteCandidatesToRemove(final SetIntersection<AbstractDNodeCandidate> status, IProgressMonitor monitor) {
        try {
            final Iterable<AbstractDNodeCandidate> candidatesToRemove = status.getRemovedElements();
            monitor.beginTask("Nodes deletion", Iterables.size(candidatesToRemove));
            for (final AbstractDNodeCandidate nodeToDelete : candidatesToRemove) {
                if (nodeToDelete.comesFromDiagramElement()) {
                    this.accessor.eDelete(nodeToDelete.getOriginalElement(), session != null ? session.getSemanticCrossReferencer() : null);
                    monitor.worked(1);
                }
            }
        } finally {
            monitor.done();
        }
    }

    private void handleKeptAndNewNodesWithOrder(final DragAndDropTarget viewContainer, final DiagramElementMapping mapping, final SetIntersection<AbstractDNodeCandidate> status,
            final Collection<AbstractDNode> keptNodes, final Collection<AbstractDNode> createdNodes, IProgressMonitor monitor) {
        try {
            Iterable<AbstractDNodeCandidate> allElements = status.getAllElements();
            monitor.beginTask("", Iterables.size(allElements)); //$NON-NLS-1$

            final boolean createContents = this.accessor.getPermissionAuthority().canCreateIn(viewContainer) && new DiagramElementMappingQuery(mapping).isSynchronizedAndCreateElement(diagram);
            final RefreshOrderHelper refreshOrderHelper = new RefreshOrderHelper(viewContainer, mapping);
            int mappingBeginIndex = refreshOrderHelper.getMappingBeginIndex();
            int currentGlobalPosition = 0;
            /*
             * The status returns all the candidates in the right order through
             * getAllElements(). We'll iterate and for each candidate determine
             * if it has to be added to the current appending position, only
             * refreshed, or moved to another position.
             */
            for (final AbstractDNodeCandidate candidate : allElements) {
                int positionToAppend = mappingBeginIndex + currentGlobalPosition;
                if (candidate.getOriginalElement() != null) {
                    AbstractDNode node = handleKeptNode(viewContainer, candidate, keptNodes, false, new SubProgressMonitor(monitor, 1));

                    /* Let's check if the element is at the right position. */
                    if (!refreshOrderHelper.getOldGlobalPosition(node).equals(positionToAppend)) {
                        refreshOrderHelper.getOwnedDDiagramElements().move(positionToAppend, node);
                    }
                } else {
                    // Original element doesn't exist, it's a new element
                    if (createContents) {
                        final AbstractDNode createdNode = this.sync.createNewNode(this.diagramMappingsManager, candidate, false, positionToAppend);
                        createdNodes.add(createdNode);
                    }
                }
                /* increment the insertion index */
                currentGlobalPosition++;
                monitor.worked(1);
            }
        } finally {
            monitor.done();
        }
    }

    private void handleKeptNodes(final DragAndDropTarget viewContainer, final SetIntersection<AbstractDNodeCandidate> status, Collection<AbstractDNode> keptNodes, boolean border,
            IProgressMonitor monitor) {
        final Iterable<AbstractDNodeCandidate> keptNodeCandidates = status.getKeptElements();
        try {
            monitor.beginTask("Refresh Nodes", Iterables.size(keptNodeCandidates));
            for (final AbstractDNodeCandidate keptCandidate : keptNodeCandidates) {
                handleKeptNode(viewContainer, keptCandidate, keptNodes, border, new SubProgressMonitor(monitor, 1));
            }
        } finally {
            monitor.done();
        }
    }

    private AbstractDNode handleKeptNode(final DragAndDropTarget viewContainer, AbstractDNodeCandidate keptCandidate, final Collection<AbstractDNode> keptNodes, boolean border,
            IProgressMonitor monitor) {
        /* The element is already here. */
        AbstractDNode keptNode = keptCandidate.getOriginalElement();
        try {
            monitor.beginTask("Node refresh", 3);

            final AbstractNodeMapping nodeMapping = (AbstractNodeMapping) keptNode.getDiagramElementMapping();

            if (keptNode instanceof DDiagramElementContainer) {
                final ContainerMapping keptNodeMapping = ((DDiagramElementContainer) keptNode).getActualMapping();
                /* handle list attribute change on mapping */
                keptNode = handleListAttributeChangeOnMapping(keptNode, keptNodeMapping, viewContainer, new SubProgressMonitor(monitor, 1));

                if (keptNode instanceof DNodeContainer) {
                    DNodeContainer dnc = (DNodeContainer) keptNode;
                    dnc.setChildrenPresentation(dnc.getActualMapping().getChildrenPresentation());
                }
            } else if (!border && viewContainer instanceof DDiagramElementContainer && nodeMapping instanceof NodeMapping) {
                /* Handle list attribute change on container mapping */
                keptNode = handleListAttributeChangeOnContainerMapping(keptNode, nodeMapping, (DDiagramElementContainer) viewContainer, new SubProgressMonitor(monitor, 1));
            }

            this.sync.refresh(keptNode);
            monitor.worked(1);
            this.sync.refreshStyle(keptNode, nodeMapping);
            monitor.worked(1);

            keptNodes.add(keptNode);
        } finally {
            monitor.done();
        }
        return keptNode;
    }

    /**
     * Compute edge candidates to be added to this diagram for the given
     * mapping.
     * 
     * @param mapping
     *            the mapping for which to compute candidates
     * 
     * @param mappingsToEdgeTargets
     *            the map which enables to retrieve edge targets from mappings
     * @return all edge candidates
     */
    public Collection<DEdgeCandidate> computeEdgeCandidates(final EdgeMapping mapping, final Map<DiagramElementMapping, Collection<EdgeTarget>> mappingsToEdgeTargets) {
        return edgeHelper.computeNowEdgeCandidates(mapping, mappingsToEdgeTargets);
    }

    /**
     * .
     * 
     * @param container
     *            .
     * @param mapping
     *            .
     * @return .
     */
    protected Set<DDiagramElement> getPreviousDiagramElements(final DragAndDropTarget container, final DiagramElementMapping mapping) {
        if (forceRetrieve) {
            final Set<DDiagramElement> previousDiagramElements = Sets.newLinkedHashSet();
            for (final DDiagramElement element : DiagramElementsHierarchyVisitor.INSTANCE.getChildren(container)) {
                final DiagramElementMapping elementMapping = element.getDiagramElementMapping();
                if (elementMapping instanceof AbstractNodeMapping && new DiagramElementMappingQuery(elementMapping).areInSameHiearchy(mapping)) {
                    previousDiagramElements.add(element);
                } else if (elementMapping instanceof IEdgeMapping && new DiagramElementMappingQuery(elementMapping).areInSameHiearchy(mapping)) {
                    previousDiagramElements.add(element);
                }
            }
            return previousDiagramElements;
        } else {
            final EObjectCouple key = new EObjectCouple(container, mapping, ids);
            // when Unsynchronized mode is active, NPE occurs because
            // previousCandidatesCache is null
            if (previousCandidatesCache == null) {
                // We simply compute the previous candidates cache
                computePreviousCandidatesCache();
            }
            return previousCandidatesCache.get(key);
        }
    }

    private void addNowNodeCandidates(final SetIntersection<AbstractDNodeCandidate> biSet, final DragAndDropTarget container, final AbstractNodeMapping mapping,
            final Set<AbstractDNodeCandidate> semanticFilter) {
        final Collection<AbstractDNodeCandidate> nowCandidates = computeNodeCandidates(container, mapping, semanticFilter);
        for (final AbstractDNodeCandidate candidate : nowCandidates) {
            biSet.addInNew(candidate);
        }
    }

    /**
     * Force the synchronizer to retrieve elements, instead of using the cache.
     */
    protected void forceRetrieve() {
        forceRetrieve = true;
    }

    /**
     * Reset to false the force retrieve, the synchronizer should use the cache.
     */
    protected void resetforceRetrieve() {
        forceRetrieve = false;
    }

    /**
     * Compute node candidates to be added to the given container for the given
     * mapping.
     * 
     * @param container
     *            the container in which candidates will be added
     * @param mapping
     *            the mapping
     * @param semanticFilter
     *            the filter which contains candidates to not add in the
     *            returned collection
     * @return all node candidates
     */
    public Collection<AbstractDNodeCandidate> computeNodeCandidates(final DragAndDropTarget container, final AbstractNodeMapping mapping, final Set<AbstractDNodeCandidate> semanticFilter) {
        return nodHelper.computeNodeCandidates(container, mapping, semanticFilter, ids);
    }

    /**
     * This method browse the current container to update the given map with
     * Container/Mapping => diagram elements information.
     * 
     * @param container
     *            the container to browse.
     * @param candidates
     *            the map to update.
     */
    private void retrievePreviousCandidates(DragAndDropTarget container, Multimap<EObjectCouple, DDiagramElement> candidates) {
        for (final DDiagramElement child : DiagramElementsHierarchyVisitor.INSTANCE.getChildren(container)) {
            final EObjectCouple key;
            if (child.getMapping() == null) {
                // We need a non-null argument to create the EObjectCouple, as
                // the element needs to be registered as a "previous candidate"
                // or it will not be removed from the diagram.
                key = new EObjectCouple(container, DDiagramSynchronizer.FAKE_MAPPING, ids);
            } else {
                key = new EObjectCouple(container, child.getMapping(), ids);
            }
            candidates.put(key, child);
            /*
             * Now let's recursively call this in containers.
             */
            if (child instanceof DragAndDropTarget) {
                retrievePreviousCandidates((DragAndDropTarget) child, candidates);
            }
        }
    }

    /**
     * Browse the given container and add existing graphical elements having the
     * given mapping.
     * 
     * @param biSet
     *            the status that will get's updated.
     * @param container
     *            view container to browse.
     * @param mapping
     *            mapping of the elements to add in the status.
     */
    private void addPreviousCandidates(final SetIntersection<AbstractDNodeCandidate> biSet, final DragAndDropTarget container, final AbstractNodeMapping mapping) {
        final EObjectCouple key = new EObjectCouple(container, mapping, ids);
        this.ignoredDuringRefreshProcess.remove(key);
        Collection<DDiagramElement> candidates = previousCandidatesCache.get(key);
        if (candidates == null) {
            candidates = Collections.emptySet();
        }

        addCandidates(biSet, candidates.iterator(), mapping);
    }

    private <T extends DDiagramElement> void addCandidates(final SetIntersection<AbstractDNodeCandidate> biSet, final Iterator<T> it, final AbstractNodeMapping mapping) {
        while (it.hasNext()) {
            final DDiagramElement element = it.next();
            final DiagramElementMapping elementMapping = element.getDiagramElementMapping();
            if (EqualityHelper.areEquals(elementMapping, mapping) || mapping.eResource() == null) {
                final AbstractDNodeCandidate abstractDNodeCandidate = new AbstractDNodeCandidate((AbstractDNode) element, ids);
                biSet.addInOld(abstractDNodeCandidate);
            }
        }
    }

    /**
     * Create the candidates and return the created nodes.
     * 
     * @param candidatesToCreate
     *            collection of candidates to create in the container.
     * @param container
     *            the view container .
     * @param mapping
     *            the mapping of the candidates to create.
     * @param border
     *            true if the elements should be created on the border, false
     *            otherwise.
     * @param monitor
     *            a {@link IProgressMonitor} to show progression of
     *            {@link AbstractDNode}s creation
     * @return the newly created nodes.
     */
    private List<AbstractDNode> createNewContent(final Collection<AbstractDNodeCandidate> candidatesToCreate, final DragAndDropTarget container, final AbstractNodeMapping mapping,
            final boolean border, IProgressMonitor monitor) {
        return createNewContent(candidatesToCreate, false, container, mapping, border, monitor);
    }

    private List<AbstractDNode> createNewContent(final Collection<AbstractDNodeCandidate> candidatesToCreate, boolean force, final DragAndDropTarget container, final AbstractNodeMapping mapping,
            final boolean border, IProgressMonitor monitor) {
        try {
            monitor.beginTask("Nodes creation", candidatesToCreate.size());
            /* check first that there is candidates */
            if (!candidatesToCreate.isEmpty() && this.accessor.getPermissionAuthority().canCreateIn(container)
                    && (force || new DiagramElementMappingQuery(mapping).isSynchronizedAndCreateElement(diagram))) {
                final List<AbstractDNode> createdNodes = new ArrayList<AbstractDNode>(candidatesToCreate.size());
                for (final AbstractDNodeCandidate candidate : candidatesToCreate) {
                    final AbstractDNode createdNode = this.sync.createNewNode(diagramMappingsManager, candidate, border);
                    createdNodes.add(createdNode);
                    monitor.worked(1);
                }
                return createdNodes;
            }
        } finally {
            monitor.done();
        }
        return new ArrayList<AbstractDNode>();
    }

    /**
     * Create the edges candidates.
     * 
     * @param mappingManager
     *            the manager used to handle return the Mappings to consider
     *            regarding the enablement of Viewpoints.
     * @param mappingsToEdgeTargets
     *            edge target
     * @param mapping
     *            the mapping of the candidates to create.
     * @param edgeToMappingBasedDecoration
     *            mapping based decorations.
     * @param edgeToSemanticBasedDecoration
     *            semantic based decorations
     * @return an Option on DEdge
     */
    public Option<DEdge> createEdgeMapping(DiagramMappingsManager mappingManager, final Map<DiagramElementMapping, Collection<EdgeTarget>> mappingsToEdgeTargets, final EdgeMapping mapping,
            final Map<EdgeMapping, Collection<MappingBasedDecoration>> edgeToMappingBasedDecoration, final Map<String, Collection<SemanticBasedDecoration>> edgeToSemanticBasedDecoration) {

        edgesDones = new HashSet<DDiagramElement>();
        Collection<DEdge> newEdges = createDEdgeOnMapping(mappingManager, mappingsToEdgeTargets, mapping, edgeToMappingBasedDecoration, edgeToSemanticBasedDecoration);
        edgesDones.clear();
        if (!newEdges.isEmpty()) {
            return Options.newSome(newEdges.iterator().next());
        }
        return Options.newNone();
    }

    private Collection<DEdge> createDEdgeOnMapping(DiagramMappingsManager mappingManager, final Map<DiagramElementMapping, Collection<EdgeTarget>> mappingsToEdgeTargets, final EdgeMapping mapping,
            final Map<EdgeMapping, Collection<MappingBasedDecoration>> edgeToMappingBasedDecoration, final Map<String, Collection<SemanticBasedDecoration>> edgeToSemanticBasedDecoration) {
        Collection<DEdge> newEdges = new HashSet<DEdge>();
        if (this.accessor.getPermissionAuthority().canEditInstance(this.diagram)) {
            final SetIntersection<DEdgeCandidate> status = createEdgeCandidates(mappingsToEdgeTargets, mapping, edgeToMappingBasedDecoration, edgeToSemanticBasedDecoration);

            for (final DEdgeCandidate candidate : status.getNewElements()) {
                newEdges.add(this.sync.createNewEdge(mappingManager, candidate, mappingsToEdgeTargets, edgeToMappingBasedDecoration, edgeToSemanticBasedDecoration));
            }
        }
        return newEdges;
    }

    private void refreshEdgeMapping(DiagramMappingsManager mappingManager, final Map<DiagramElementMapping, Collection<EdgeTarget>> mappingsToEdgeTargets, final EdgeMapping mapping,
            final Map<EdgeMapping, Collection<MappingBasedDecoration>> edgeToMappingBasedDecoration, final Map<String, Collection<SemanticBasedDecoration>> edgeToSemanticBasedDecoration,
            IProgressMonitor monitor) {
        if (this.accessor.getPermissionAuthority().canEditInstance(this.diagram)) {
            final SetIntersection<DEdgeCandidate> status = createEdgeCandidates(mappingsToEdgeTargets, mapping, edgeToMappingBasedDecoration, edgeToSemanticBasedDecoration);

            if (new DiagramElementMappingQuery(mapping).isSynchronizedAndCreateElement(diagram)) {
                try {
                    Collection<DEdgeCandidate> newElements = status.getNewElements();
                    monitor.beginTask("Refresh edges", newElements.size());
                    for (final DEdgeCandidate candidate : newElements) {
                        edgesDones.add(this.sync.createNewEdge(mappingManager, candidate, mappingsToEdgeTargets, edgeToMappingBasedDecoration, edgeToSemanticBasedDecoration));
                        monitor.worked(1);
                    }
                } finally {
                    monitor.done();
                }
            }
        }
    }

    /**
     * @param mappingsToEdgeTargets
     * @param mapping
     * @param edgeToMappingBasedDecoration
     * @param edgeToSemanticBasedDecoration
     * @return
     */
    private SetIntersection<DEdgeCandidate> createEdgeCandidates(final Map<DiagramElementMapping, Collection<EdgeTarget>> mappingsToEdgeTargets, final EdgeMapping mapping,
            final Map<EdgeMapping, Collection<MappingBasedDecoration>> edgeToMappingBasedDecoration, final Map<String, Collection<SemanticBasedDecoration>> edgeToSemanticBasedDecoration) {
        final SetIntersection<DEdgeCandidate> status = new GSetIntersection<DEdgeCandidate>();
        final EObjectCouple key = new EObjectCouple(diagram, mapping, ids);
        this.ignoredDuringRefreshProcess.remove(key);
        // Remove also the sub mapping if mapping is ImportedMapping and the
        // superMapping
        handleSubMappings(mapping);
        handleSuperMappings(mapping);

        final List<DEdgeCandidate> invalidCandidates = Lists.newArrayList();

        /*
         * Collect existing edges.
         */
        for (final DEdge edge : this.diagram.getEdgesFromMapping(mapping)) {
            final DEdgeCandidate edgeCandidate = new DEdgeCandidate(edge, ids);
            if (edgeCandidate.isInvalid()) {
                invalidCandidates.add(edgeCandidate);
            } else {
                status.addInOld(edgeCandidate);
            }
        }
        /* compute edge candidates */
        final Collection<DEdgeCandidate> edgesCandidates = this.computeEdgeCandidates(mapping, mappingsToEdgeTargets);
        for (final DEdgeCandidate edgeCandidate : edgesCandidates) {
            status.addInNew(edgeCandidate);
        }

        /*
         * Now we've got the status we can delete the edge to delete, create the
         * new ones and refresh the existing ones.
         */
        for (final DEdgeCandidate edgeCandidate : Iterables.concat(status.getRemovedElements(), invalidCandidates)) {
            if (!isDefinedInAnotherLayer(edgeCandidate, mappingsToEdgeTargets)) {
                DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.CLEANING_EDGES_KEY);
                this.accessor.eDelete(edgeCandidate.getEdge(), session != null ? session.getSemanticCrossReferencer() : null);
                DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.CLEANING_EDGES_KEY);
            }
        }

        for (final DEdgeCandidate edgeCandidate : status.getKeptElements()) {
            DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.UPDATE_ALL_EDGES_KEY);
            // Change mapping of the edge if not the actual mapping (case of
            // EdgeImportMapping)

            if (!edgesDones.contains(edgeCandidate.getEdge())) {
                if (edgeCandidate.getEdge().getActualMapping() != mapping) {
                    edgeCandidate.getEdge().setActualMapping(mapping);
                }
                this.sync.computeEdgeDecorations(edgeCandidate.getEdge(), edgeToMappingBasedDecoration, edgeToSemanticBasedDecoration);
                Option<EdgeMapping> edgeMapping = new IEdgeMappingQuery(edgeCandidate.getEdge().getActualMapping()).getEdgeMapping();

                if (edgeMapping.some()) {
                    this.sync.createStyle(edgeCandidate.getEdge(), edgeMapping.get(), diagram);
                    this.sync.refresh(edgeCandidate.getEdge());
                    this.sync.refreshStyle(edgeCandidate.getEdge(), edgeMapping.get(), diagram);
                    this.sync.updatePath(edgeCandidate.getEdge(), edgeMapping.get(), mappingsToEdgeTargets);
                }
                edgesDones.add(edgeCandidate.getEdge());
            }
            DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.UPDATE_ALL_EDGES_KEY);
        }
        return status;

    }

    /**
     * Remove the subMapping from the ignoredDuringRefreshProcess list.
     * 
     * @param mapping
     *            The mapping to deal with
     */
    private void handleSubMappings(final IEdgeMapping mapping) {
        if (mapping instanceof EdgeMappingImport) {
            final IEdgeMapping importedEdgeMaping = ((EdgeMappingImport) mapping).getImportedMapping();
            final EObjectCouple key = new EObjectCouple(diagram, importedEdgeMaping, ids);
            this.ignoredDuringRefreshProcess.remove(key);
            handleSubMappings(importedEdgeMaping);
        }
    }

    /**
     * Remove the superMapping from the ignoredDuringRefreshProcess list.
     * 
     * @param mapping
     *            The mapping to deal with
     */
    private void handleSuperMappings(final IEdgeMapping mapping) {

        for (final Iterator<EObjectCouple> iterator = this.ignoredDuringRefreshProcess.iterator(); iterator.hasNext(); /**/) {
            final EObjectCouple couple = iterator.next();
            EdgeMappingImport edgeMappingImport = null;
            if (couple.getObj2() instanceof EdgeMappingImportWrapper) {
                edgeMappingImport = ((EdgeMappingImportWrapper) couple.getObj2()).getWrappedEdgeMappingImport();
            } else if (couple.getObj2() instanceof EdgeMappingImport) {
                edgeMappingImport = (EdgeMappingImport) couple.getObj2();
            }
            if (edgeMappingImport != null && couple.getObj1().equals(diagram) && EdgeMappingHelper.isImported(mapping, edgeMappingImport)) {
                iterator.remove();
            }
        }
    }

    /**
     * Returns <code>true</code> if the given edge is displayed in another
     * specific layer configuration.
     * 
     * @param edgeCandidate
     *            the edge to check.
     * @param mappingsToEdgeTargets
     *            mapping to edges, it contains nodes that are actually
     *            displayed (with the actual layer configuration).
     * @return <code>true</code> if the given edge is displayed in a specific
     *         layer configuration.
     */
    private boolean isDefinedInAnotherLayer(final DEdgeCandidate edgeCandidate, final Map<DiagramElementMapping, Collection<EdgeTarget>> mappingsToEdgeTargets) {
        if (edgeCandidate.getEdge() == null) {
            return false;
        }
        final DEdge edge = edgeCandidate.getEdge();
        final EdgeTarget source = edge.getSourceNode();
        final EdgeTarget target = edge.getTargetNode();
        /*
         * If the source of target is not a DDiagramElement then i don't know
         * how to manage it. I expect that the behavior is managed by another
         * component.
         */
        final boolean sourceIsValid = source != null && (!(source instanceof DDiagramElement) || ((DDiagramElement) source).getParentDiagram() == this.diagram);
        final boolean targetIsValid = target != null && (!(target instanceof DDiagramElement) || ((DDiagramElement) target).getParentDiagram() == this.diagram);
        /*
         * If the source and the target are valid then we need to check that
         * there are not displayed on the diagram (it means that the edge is not
         * displayed).
         */
        final Option<EdgeMapping> actualMapping = new IEdgeMappingQuery(edge.getActualMapping()).getEdgeMapping();

        final Iterator<DiagramElementMapping> sourceMappings;
        final Iterator<DiagramElementMapping> targetMappings;

        if (actualMapping.some()) {
            sourceMappings = actualMapping.get().getSourceMapping().iterator();
            targetMappings = actualMapping.get().getTargetMapping().iterator();
        } else {
            sourceMappings = Iterators.emptyIterator();
            targetMappings = Iterators.emptyIterator();
        }

        final boolean sourceIsNotDisplayed = nodeIsNotDisplayed(sourceMappings, source, mappingsToEdgeTargets);
        final boolean targetIsNotDisplayed = nodeIsNotDisplayed(targetMappings, target, mappingsToEdgeTargets);

        return sourceIsValid && targetIsValid && (sourceIsNotDisplayed || targetIsNotDisplayed);
    }

    private boolean nodeIsNotDisplayed(final Iterator<DiagramElementMapping> mappingsToConsider, final EdgeTarget node, final Map<DiagramElementMapping, Collection<EdgeTarget>> elementsInDiagram) {
        boolean sourceIsNotDisplayed = true;
        while (mappingsToConsider.hasNext() && sourceIsNotDisplayed) {

            final DiagramElementMapping sourceMapping = mappingsToConsider.next();
            final Collection<EdgeTarget> elementsFromMapping = getDiagramElementFromMapping(sourceMapping, elementsInDiagram);

            if (elementsFromMapping != null && elementsFromMapping.contains(node)) {
                sourceIsNotDisplayed = false;
            }

        }
        return sourceIsNotDisplayed;
    }

    private Collection<EdgeTarget> getDiagramElementFromMapping(final DiagramElementMapping sourceMapping, final Map<DiagramElementMapping, Collection<EdgeTarget>> mappingsToEdgeTargets) {
        return mappingsToEdgeTargets.get(sourceMapping);
    }

    /**
     * Put or add the given nodes in the Map.
     * 
     * @param map
     *            the map to update.
     * @param mapping
     *            the key
     * @param addedNodes
     */
    private void putOrAdd(final Map map, final DiagramElementMapping mapping, final Collection<DDiagramElement> addedNodes) {
        Collection<DDiagramElement> existing = (Collection<DDiagramElement>) map.get(mapping);
        if (existing == null) {
            existing = new MultipleCollection<DDiagramElement>();
            existing.addAll(addedNodes);
            map.put(mapping, existing);
        } else {
            existing.addAll(addedNodes);
        }
        AbstractNodeMapping mappingToAdd = null;
        if (mapping instanceof ContainerMappingImport) {
            mappingToAdd = ((ContainerMappingImport) mapping).getImportedMapping();
        } else if (mapping instanceof NodeMappingImport) {
            mappingToAdd = ((NodeMappingImport) mapping).getImportedMapping();
        }
        if (mappingToAdd != null) {
            putOrAdd(map, mappingToAdd, addedNodes);
        }
    }

    /**
     * Action comes from tool or refresh.
     * 
     * @param b
     *            boolean
     */
    public void setTool(boolean b) {
        this.edgeHelper.setTool(b);
    }

    /**
     * Helper extraction of sort capabilities.
     */
    private static final class RefreshOrderHelper {
        final DragAndDropTarget viewContainer;

        final Map<EObject, Integer> oldChildPositionInGlobalList = Maps.newHashMap();

        private RepresentationElementMapping mapping;

        private int mappingBeginIndex;

        public RefreshOrderHelper(DragAndDropTarget viewContainer, RepresentationElementMapping mapping) {
            this.viewContainer = viewContainer;
            this.mapping = mapping;
            init();
        }

        public void init() {
            EList<EObject> ownedDDiagramElements = getOwnedDDiagramElements();
            mappingBeginIndex = ownedDDiagramElements.size();
            /*
             * First step : we might need to move elements' position in their
             * list. To avoid un-necessary iterations on this list while
             * iterating on the candidate, we start by computing a map of
             * Elements->positions In the meantime we try to locate the position
             * of the very first element being of this mapping.
             */
            int position = 0;
            for (DDiagramElement diagElement : Iterables.filter(ownedDDiagramElements, DDiagramElement.class)) {
                if (mapping.equals(diagElement.getMapping())) {
                    if (position < mappingBeginIndex) {
                        mappingBeginIndex = position;
                    }
                    oldChildPositionInGlobalList.put(diagElement, position);
                }
                position++;
            }
            /*
             * if no element from the same mapping already exists, just append
             * to the end of the list.
             */
            if (mappingBeginIndex < 0) {
                mappingBeginIndex = ownedDDiagramElements.size() - 1;
            }
        }

        protected Object getOldGlobalPosition(DDiagramElement element) {
            if (oldChildPositionInGlobalList.containsKey(element)) {
                return oldChildPositionInGlobalList.get(element);
            }
            return -1;
        }

        protected int getMappingBeginIndex() {
            return mappingBeginIndex;
        }

        /*
         * The code here only get a loosely typed viewContainer, we first need
         * to retrieve the actual EList which we have to update. To do so we
         * first have to find the corresponding EReference depending on the
         * viewContainer.
         */
        protected EReference getOwnedDiagramElementsToUpdate() {
            EReference ref = null;
            if (viewContainer instanceof DDiagram) {
                ref = DiagramPackage.eINSTANCE.getDDiagram_OwnedDiagramElements();
            } else if (viewContainer instanceof DNodeList) {
                ref = DiagramPackage.eINSTANCE.getDNodeList_OwnedElements();
            } else if (viewContainer instanceof DNodeContainer) {
                ref = DiagramPackage.eINSTANCE.getDNodeContainer_OwnedDiagramElements();
            }
            if (ref == null) {
                /*
                 * No way we ends up here, the ViewContainer is either a diagram
                 * or a node container when asking to refresh a node mapping.
                 */
                throw new IllegalArgumentException();
            }
            return ref;
        }

        protected EList<EObject> getOwnedDDiagramElements() {
            EList<EObject> eGetResult = (EList<EObject>) viewContainer.eGet(getOwnedDiagramElementsToUpdate());
            return eGetResult;
        }
    }
}
