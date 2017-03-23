/*******************************************************************************
 * Copyright (c) 2012, 2017 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.actions.repair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.IdentityCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.business.api.repair.IRepairParticipant;
import org.eclipse.sirius.business.api.session.CustomDataConstants;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.migration.resource.session.commands.MigrationCommandExecutor;
import org.eclipse.sirius.business.internal.repair.commands.RemoveDiagramElementsCommand;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.VariableValue;
import org.eclipse.sirius.diagram.business.api.helper.concern.ConcernService;
import org.eclipse.sirius.diagram.business.api.helper.display.DisplayServiceManager;
import org.eclipse.sirius.diagram.business.api.query.DiagramElementMappingQuery;
import org.eclipse.sirius.diagram.business.api.refresh.CanonicalSynchronizer;
import org.eclipse.sirius.diagram.business.api.refresh.CanonicalSynchronizerFactory;
import org.eclipse.sirius.diagram.business.internal.repair.resource.DiagramKey;
import org.eclipse.sirius.diagram.business.internal.repair.resource.RepairRepresentationRefresher;
import org.eclipse.sirius.diagram.business.internal.repair.resource.session.diagram.data.LostEdgeData;
import org.eclipse.sirius.diagram.business.internal.repair.resource.session.diagram.data.LostElementFactory;
import org.eclipse.sirius.diagram.business.internal.repair.resource.session.diagram.data.LostNodeData;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.description.tool.BehaviorTool;
import org.eclipse.sirius.diagram.ui.internal.refresh.listeners.GMFDiagramUpdater;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.migration.DiagramCrossReferencer;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.repair.commands.RemoveInvalidViewsCommand;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.validation.ValidationRule;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * General diagram repair participant.
 * 
 * @author fbarbin
 * 
 */
public class DiagramRepairParticipant implements IRepairParticipant {

    /** Saves the state of the model's SiriusElements. */
    private final Map<Identifier, IDiagramElementState<DDiagramElement>> elementStatesMap = Maps.newHashMap();

    private DiagramElementStateFactory diagramElementStateFactory;

    private List<GMFDiagramUpdater> gmfDiagramUpdaters = new ArrayList<GMFDiagramUpdater>();

    private TransactionalEditingDomain editingDomain;

    private ListMultimap<DiagramKey, LostNodeData> lostNodesByDelete;

    private ListMultimap<DiagramKey, LostEdgeData> lostEdgesByDelete;

    /**
     * {@inheritDoc}
     */
    @Override
    public void repairStarted() {
        diagramElementStateFactory = DiagramElementStateFactory.newInstance();
        lostNodesByDelete = ArrayListMultimap.create();
        lostEdgesByDelete = ArrayListMultimap.create();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void repairCompeleted() {
        Collection<IDiagramElementState<DDiagramElement>> values = elementStatesMap.values();

        for (IDiagramElementState<?> diagramElementState : values) {
            diagramElementState.dispose();
        }
        diagramElementStateFactory.dispose();
        lostEdgesByDelete.clear();
        lostNodesByDelete.clear();
        elementStatesMap.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void restoreModelElementState(DView view, IProgressMonitor monitor) {
        monitor.subTask(Messages.DiagramRepairParticipant_restoreModelStateTaskName);
        // Creates a new CrossReferencer before restoring the model state (This
        // cross referencer will return all the references named "element" on
        // GMF Nodes)
        Collection<Resource> resources = getAllDiagramResources(view);
        DiagramCrossReferencer crossReferencer = new DiagramCrossReferencer(resources);
        Iterator<EObject> analysisIterator = new DViewQuery(view).getAllContentInRepresentations(DDiagramElement.class::isInstance);

        Map<IDiagramElementState<DDiagramElement>, DDiagramElement> states = new LinkedHashMap<IDiagramElementState<DDiagramElement>, DDiagramElement>();
        while (analysisIterator.hasNext()) {
            final DDiagramElement diagramElement = (DDiagramElement) analysisIterator.next();

            final IDiagramElementState<DDiagramElement> state = getElementState(diagramElement);

            if (state == null) {
                // SiriusPlugin.getDefault().warning("No state for " +
                // diagramElement, null);
            } else {
                states.put(state, diagramElement);
            }
            monitor.worked(1);
        }

        for (Entry<IDiagramElementState<DDiagramElement>, DDiagramElement> entry : states.entrySet()) {
            IDiagramElementState<DDiagramElement> state = entry.getKey();
            DDiagramElement dDiagramElement = entry.getValue();
            state.restoreElementState(dDiagramElement);
        }

        this.setDefaultConcern(view);
        if (crossReferencer != null) {
            crossReferencer.clear();
            crossReferencer.clean();
        }
        if (editingDomain != null) {
            refreshVisibility(view);
        }
        // Synchronize the GMF views to remove orphan and create new after
        // migration
        List<Diagram> diagrams = getGMFDiagrams(resources);
        for (Diagram currentDiagram : diagrams) {
            if (ViewUtil.resolveSemanticElement(currentDiagram) != null) {
                CanonicalSynchronizer canonicalSynchronizer = CanonicalSynchronizerFactory.INSTANCE.createCanonicalSynchronizer(currentDiagram);
                canonicalSynchronizer.storeViewsToArrange(false);
                canonicalSynchronizer.synchronize();
            }
        }

    }

    private List<Diagram> getGMFDiagrams(Collection<Resource> resources) {
        List<Diagram> diagrams = new ArrayList<>();

        resources.stream().flatMap(res -> res.getContents().stream()).filter(DDiagram.class::isInstance).map(DDiagram.class::cast).forEach(dDiagram -> {
            new DRepresentationQuery(dDiagram).getAnnotation(CustomDataConstants.GMF_DIAGRAMS).forEach(annotation -> {
                EObject data = annotation.getData();
                if (data instanceof Diagram) {
                    diagrams.add((Diagram) data);
                }
            });
        });
        return diagrams;

    }

    /**
     * @param view
     */
    private void refreshVisibility(DView view) {
        for (final DRepresentation representation : new DViewQuery(view).getLoadedRepresentations()) {
            if (representation instanceof DDiagram) {

                Command refreshAllElementsVisibilityCommand = new IdentityCommand() {
                    @Override
                    public void execute() {
                        DisplayServiceManager.INSTANCE.getDisplayService().refreshAllElementsVisibility((DDiagram) representation);
                    }
                };
                new MigrationCommandExecutor().execute(editingDomain, refreshAllElementsVisibilityCommand);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void postRefreshOperations(TransactionalEditingDomain domain, Resource resource) {

        this.editingDomain = domain;
        Command removeInvalidViewsCommand = new RemoveInvalidViewsCommand(resource);
        new MigrationCommandExecutor().execute(domain, removeInvalidViewsCommand);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveModelElementState(DView view, IProgressMonitor monitor) {
        monitor.subTask(Messages.DiagramRepairParticipant_saveModelStateTaskName);

        // Creates a new CrossReferencer before saving the model state (This
        // cross referencer will return all the references named "element" on
        // GMF Nodes)
        DiagramCrossReferencer crossReferencer = new DiagramCrossReferencer(getAllDiagramResources(view));

        // Work only with DDiagramElement
        final Iterator<EObject> analysisIterator = new DViewQuery(view).getAllContentInRepresentations(DDiagramElement.class::isInstance);

        final List<EObject> elementsToRemove = Lists.newArrayList();

        while (analysisIterator.hasNext()) {
            final DDiagramElement diagramElement = (DDiagramElement) analysisIterator.next();

            if (diagramElementStateFactory.isValid(diagramElement)) {
                Identifier identifier = Identifier.createIdentifier(diagramElement);

                // Element is duplicated, remove it
                if (elementStatesMap.containsKey(identifier)) {
                    elementsToRemove.add(diagramElement);
                } else {
                    final IDiagramElementState<DDiagramElement> elementState = (IDiagramElementState<DDiagramElement>) diagramElementStateFactory.buildDiagramElementState(identifier, diagramElement,
                            crossReferencer);

                    elementStatesMap.put(elementState.getIdentifier(), elementState);
                }
            } else {
                // Element is not valid, remove it
                elementsToRemove.add(diagramElement);
            }

            monitor.worked(1);
        }

        for (EObject eObject : elementsToRemove) {
            EcoreUtil.remove(eObject);

        }
        if (crossReferencer != null) {
            crossReferencer.clear();
            crossReferencer.clean();
        }

    }

    private Collection<Resource> getAllDiagramResources(DView view) {
        // @formatter:off
        Collection<Resource> resources = view.getOwnedRepresentationDescriptors().stream()
        .map(DRepresentationDescriptor::getRepresentation).filter(Objects::nonNull)
        .map(EObject::eResource).filter(Objects::nonNull)
        .collect(Collectors.toSet());
        // @formatter:on

        return resources;
    }

    private void setDefaultConcern(final DView view) {
        for (final DRepresentation representation : new DViewQuery(view).getLoadedRepresentations()) {
            if (representation instanceof DDiagram) {
                final DDiagram diagram = (DDiagram) representation;
                final DiagramDescription description = diagram.getDescription();
                if (description != null && description.getDefaultConcern() != null && diagram.getCurrentConcern() != null && diagram.getCurrentConcern().equals(description.getDefaultConcern())) {
                    // Clean the context if the current context doesn't exist
                    // any more.
                    Resource currentConcernResource = diagram.getCurrentConcern().eResource();
                    if (currentConcernResource == null || !currentConcernResource.isLoaded()) {
                        ConcernService.setCurrentConcern(diagram, description.getDefaultConcern());
                    }
                }
            }
        }
    }

    /**
     * Returns the state of the DDiagramElement.
     * 
     * @param element
     *            the diagram element.
     * @return The state of the DDiagramElement or <code>null</code> if none can be found.
     */
    private IDiagramElementState<DDiagramElement> getElementState(final DDiagramElement element) {
        IDiagramElementState<DDiagramElement> result = null;

        if (diagramElementStateFactory.isValid(element)) {
            Identifier identifier = Identifier.createIdentifier(element);
            result = elementStatesMap.get(identifier);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startRepairOnView(Session session, DView view) {

        for (DRepresentation representation : new DViewQuery(view).getLoadedRepresentations()) {
            if (representation instanceof DDiagram) {
                GMFDiagramUpdater updater = new GMFDiagramUpdater(session, (DDiagram) representation);
                gmfDiagramUpdaters.add(updater);
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endRepairOnView() {
        for (GMFDiagramUpdater gmfDiagramUpdater : gmfDiagramUpdaters) {
            gmfDiagramUpdater.dispose();
        }
        gmfDiagramUpdaters.clear();

    }

    @Override
    public void removeElements(DView view, TransactionalEditingDomain domain, IProgressMonitor monitor) {
        final List<EObject> toBeRemoved = computeElementsToDeleteOrDeletedElements(view);
        removeDiagramElements(monitor, domain, toBeRemoved);
    }

    /**
     * Remove elements with isCreatedElement sets to true. Store data about the other.
     * 
     * @param view
     * @return the list of elements that can be safety removed.
     */
    private List<EObject> computeElementsToDeleteOrDeletedElements(final DView view) {
        final List<EObject> toBeRemoved = Lists.newArrayList();

        lostNodesByDelete.clear();
        lostEdgesByDelete.clear();

        // If an element has its mapping configured with isCreatedElements sets
        // to true, it will be removed and then recreated by diagram refresh.
        // But an element with isCreated sets to false won't be recreated by
        // refresh. Another process will recreate them after.

        final Iterator<EObject> viewIterator = new DViewQuery(view).getAllContentInRepresentations(DDiagramElement.class::isInstance);

        while (viewIterator.hasNext()) {
            final DDiagramElement diagElement = (DDiagramElement) viewIterator.next();
            if (new DiagramElementMappingQuery(diagElement.getDiagramElementMapping()).isSynchronizedAndCreateElement(diagElement)) {
                toBeRemoved.add(diagElement);
            } else {

                if (LostElementFactory.isCompleteDiagramElement(diagElement)) {
                    final DiagramKey diagramKey = DiagramKey.createDiagramKey(diagElement.getParentDiagram());
                    if (diagElement instanceof AbstractDNode) {
                        createLostNodeData(diagramKey, diagElement);

                    } else if (diagElement instanceof DEdge) {
                        createLostEdgeData(diagramKey, diagElement);
                    }
                }
            }
        }
        return toBeRemoved;
    }

    private void createLostEdgeData(final DiagramKey diagramKey, final DDiagramElement diagElement) {
        final LostEdgeData data = LostElementFactory.createLostEdgeData(diagElement);
        lostEdgesByDelete.put(diagramKey, data);
    }

    private void createLostNodeData(final DiagramKey diagramKey, final DDiagramElement diagElement) {
        final LostNodeData data = LostElementFactory.createLostNodeData(diagElement);
        lostNodesByDelete.put(diagramKey, data);
    }

    private void removeDiagramElements(final IProgressMonitor monitor, TransactionalEditingDomain transactionalEditingDomain, final List<EObject> toBeRemoved) {
        monitor.beginTask(Messages.DiagramRepairParticipant_removeDiagramElementTaskName, 1);
        Command removeDiagramElementsCommand = new RemoveDiagramElementsCommand(new NullProgressMonitor(), toBeRemoved);

        new MigrationCommandExecutor().execute(transactionalEditingDomain, removeDiagramElementsCommand);
        monitor.done();
    }

    @Override
    public List<DRepresentation> cleanRepresentations(EList<DRepresentation> representations) {
        final List<DRepresentation> representationsToRemove = new LinkedList<DRepresentation>();
        for (final DRepresentation representation : representations) {
            if (representation instanceof DDiagram) {
                final DDiagram next = (DDiagram) representation;
                // migration code, useless here.
                // handleNeedVisibilityMigration(localNeedVisibilityMigration,
                // next);

                // Clean filters variables, activated filters,
                // activated behaviors, activated rules
                cleanReferences(next);

                // Even if Description has cardinality [0:1] in
                // DDiagram, diagrams without description should not
                // be allowed
                if (representation instanceof DSemanticDiagram && ((DSemanticDiagram) representation).getDescription() == null) {
                    representationsToRemove.add(representation);
                }

            }
            if (representation instanceof DSemanticDecorator && (((DSemanticDecorator) representation).getTarget() == null || ((DSemanticDecorator) representation).getTarget().eResource() == null)) {
                representationsToRemove.add(representation);
            }
        }
        return representationsToRemove;
    }

    /**
     * Clean the references which could be problematic for the migration ((proxy not resolved or element not in a
     * eResource):
     * <UL>
     * <LI>Remove the filter variables cache if the element points by this variable is no longer exists</LI>
     * <LI>Disables the behaviors that were activated but no longer exists,</LI>
     * <LI>Disables the filters that were activated but no longer exists,</LI>
     * <LI>Disables the rules that were activated but no longer exists.</LI>
     * </UL>
     * 
     * @param diagram
     *            The diagram to clean.
     */
    private void cleanReferences(final DDiagram diagram) {
        // remove the variable caches..
        if (diagram.getFilterVariableHistory() != null && diagram.getFilterVariableHistory().getOwnedValues() != null) {
            // diagram.getFilterVariableHistory().getOwnedValues().clear();
            final Iterator<VariableValue> filterVariablesIterator = diagram.getFilterVariableHistory().getOwnedValues().iterator();
            while (filterVariablesIterator.hasNext()) {
                final VariableValue filterVariable = filterVariablesIterator.next();
                if (filterVariable.eIsProxy() || filterVariable.eResource() == null) {
                    filterVariablesIterator.remove();
                }
            }
        }
        // de-activate filters and behaviors (if needed)
        if (diagram.getActivateBehaviors() != null) {
            // next.getActivateBehaviors().clear();
            final Iterator<BehaviorTool> activatedBehaviorsIterator = diagram.getActivateBehaviors().iterator();
            while (activatedBehaviorsIterator.hasNext()) {
                final BehaviorTool activatedBehaviors = activatedBehaviorsIterator.next();
                if (activatedBehaviors.eIsProxy() || activatedBehaviors.eResource() == null) {
                    activatedBehaviorsIterator.remove();
                }
            }
        }
        if (diagram.getActivatedFilters() != null) {
            // next.getActivatedFilters().clear();
            final Iterator<FilterDescription> activatedFiltersIterator = diagram.getActivatedFilters().iterator();
            while (activatedFiltersIterator.hasNext()) {
                final FilterDescription filterDescription = activatedFiltersIterator.next();
                if (filterDescription.eIsProxy() || filterDescription.eResource() == null) {
                    activatedFiltersIterator.remove();
                }
            }
        }
        if (diagram.getActivatedRules() != null) {
            // next.getActivatedRules().clear();
            final Iterator<ValidationRule> activatedRulesIterator = diagram.getActivatedRules().iterator();
            while (activatedRulesIterator.hasNext()) {
                final ValidationRule activatedRule = activatedRulesIterator.next();
                if (activatedRule.eIsProxy() || activatedRule.eResource() == null) {
                    activatedRulesIterator.remove();
                }
            }
        }
    }

    @Override
    public void refreshRepresentations(DAnalysis dAnalysis, DView view) {
        RepairRepresentationRefresher refresher = new RepairRepresentationRefresher(lostNodesByDelete, lostEdgesByDelete);
        refresher.refreshRepresentations(dAnalysis, view);
    }
}
