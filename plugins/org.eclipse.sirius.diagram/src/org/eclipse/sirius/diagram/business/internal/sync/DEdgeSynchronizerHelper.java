/*******************************************************************************
 * Copyright (c) 2009, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.business.internal.sync;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.CartesianProduct;
import org.eclipse.sirius.common.tools.api.util.EObjectCouple;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.api.query.DiagramElementMappingQuery;
import org.eclipse.sirius.diagram.business.api.query.EdgeMappingQuery;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ext.base.collect.MultipleCollection;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * An helper class to handle edges specific operations.
 * 
 * @author mchauvin
 */
public class DEdgeSynchronizerHelper extends AbstractSynchronizerHelper {

    /**
     * Constructor.
     * 
     * @param sync
     *            the main synchronizer
     * @param accessor
     *            the model accessor
     * @param diagram
     *            the semantic diagram
     */
    public DEdgeSynchronizerHelper(final DDiagramSynchronizer sync, final DSemanticDiagram diagram, final ModelAccessor accessor) {
        super(sync, diagram, accessor);
    }

    /**
     * Compute edge candidates to be added to this diagram for the given mapping.
     * 
     * @param mapping
     *            the mapping for which to compute candidates
     * 
     * @param mappingsToEdgeTargets
     *            the map which enables to retrieve edge targets from mappings
     * @return all edge candidates
     */
    public Collection<DEdgeCandidate> computeNowEdgeCandidates(final EdgeMapping mapping, final Map<DiagramElementMapping, Collection<EdgeTarget>> mappingsToEdgeTargets) {
        DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.GET_EDGE_CANDIDATES_KEY);

        Collection<DEdgeCandidate> edgeCandidates = null;

        if (!mapping.isUseDomainElement()) {
            Predicate<? super EdgeTarget> isValidTarget = input -> {
                if (input instanceof DSemanticDecorator) {
                    // The target can be null or without resource in case of
                    // modification outside the editor and manual refresh.
                    EObject target = ((DSemanticDecorator) input).getTarget();
                    return target != null && target.eResource() != null;
                }
                return true;
            };
            final Iterable<EdgeTarget> sourceViews = Iterables.filter(this.computeTargetElements(this.diagram, mapping.getSourceMapping(), mappingsToEdgeTargets), isValidTarget);
            final Map<EObject, Collection<EdgeTarget>> targetViewsSemantics = new HashMap<EObject, Collection<EdgeTarget>>();
            final Iterable<EdgeTarget> targetViews = Iterables.filter(this.computeTargetElements(this.diagram, mapping.getTargetMapping(), mappingsToEdgeTargets, targetViewsSemantics), isValidTarget);
            edgeCandidates = computeEdgeCandidatesWithoutDomain(sourceViews, targetViews, targetViewsSemantics, mapping);
        } else {
            final Map<EObject, Collection<EdgeTarget>> sourceViewsSemantics = new HashMap<EObject, Collection<EdgeTarget>>();
            final Map<EObject, Collection<EdgeTarget>> targetViewsSemantics = new HashMap<EObject, Collection<EdgeTarget>>();
            this.computeTargetElements(this.diagram, mapping.getSourceMapping(), mappingsToEdgeTargets, sourceViewsSemantics);
            this.computeTargetElements(this.diagram, mapping.getTargetMapping(), mappingsToEdgeTargets, targetViewsSemantics);
            edgeCandidates = computeEdgeCandidatesWithDomain(sourceViewsSemantics, targetViewsSemantics, mapping);
        }

        DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.GET_EDGE_CANDIDATES_KEY);
        return edgeCandidates;
    }

    private Collection<DEdgeCandidate> computeEdgeCandidatesWithDomain(final Map<EObject, Collection<EdgeTarget>> sourceViewsSemantics, final Map<EObject, Collection<EdgeTarget>> targetViewsSemantics,
            final EdgeMapping mapping) {

        DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.GET_EDGE_CANDIDATES_KEY);

        final Collection<DEdgeCandidate> result = new ArrayList<DEdgeCandidate>();

        final Set<EObject> targetCandidates = Sets.newLinkedHashSet(getSemanticCandidates(diagram, mapping));
        if (tool || new DiagramElementMappingQuery(mapping).isSynchronizedAndCreateElement(diagram)) {
            /*
             * Now we've got the semantic element from which we should try to create Edges we can start evaluating
             * possible candidates.
             */
            for (final EObject target : targetCandidates) {
                handleCandidatesFromSemanticTargets(result, target, mapping, sourceViewsSemantics, targetViewsSemantics);
            }
        } else {
            sync.forceRetrieve();
            Set<DDiagramElement> previousDiagramElements = sync.getPreviousDiagramElements(diagram, mapping);
            sync.resetforceRetrieve();
            Predicate<DDiagramElement> stillCandidate = new Predicate<DDiagramElement>() {
                public boolean apply(DDiagramElement input) {
                    return input != null && input.getTarget() != null && targetCandidates.contains(input.getTarget());
                }
            };
            handlePreviousCandidates(result, Iterables.filter(previousDiagramElements, stillCandidate), mapping, sourceViewsSemantics, targetViewsSemantics);
        }
        DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.GET_EDGE_CANDIDATES_KEY);
        return result;
    }

    private void handlePreviousCandidates(final Collection<DEdgeCandidate> result, final Iterable<DDiagramElement> previousDiagramElements, final EdgeMapping mapping,
            final Map<EObject, Collection<EdgeTarget>> sourceViewsSemantics, final Map<EObject, Collection<EdgeTarget>> targetViewsSemantics) {

        final EdgeMappingQuery edgeMappingQuery = new EdgeMappingQuery(mapping);

        Predicate<DEdge> stillCandidate = new Predicate<DEdge>() {
            public boolean apply(DEdge input) {
                // Validate source node (and its semantic target) exists in the
                // sourceViewsSemantics
                boolean stillCandidate = validateNode(input.getSourceNode(), sourceViewsSemantics);
                // Validate target node (and its semantic target) exists in the
                // targetViewsSemantics
                stillCandidate = stillCandidate && validateNode(input.getTargetNode(), targetViewsSemantics);
                if (stillCandidate) {
                    EObject target = input.getTarget();
                    if (mapping.isUseDomainElement()) {
                        // Validate that semantic source is always in the list
                        // returns by the source finder expression for this
                        // edge. This is necessary only for edge that use domain
                        // element as target.
                        Collection<EObject> edgeSourceSemantics = edgeMappingQuery.evaluateSourceFinderExpression(diagram, interpreter, target);
                        stillCandidate = edgeSourceSemantics.contains(((DSemanticDecorator) input.getSourceNode()).getTarget());
                    }
                    if (stillCandidate) {
                        // Validate that semantic target is always in the list
                        // returns by the target finder expression for this edge
                        Collection<EObject> edgeTargetSemantics = edgeMappingQuery.evaluateTargetFinderExpression(diagram, interpreter, target);
                        stillCandidate = edgeTargetSemantics.contains(((DSemanticDecorator) input.getTargetNode()).getTarget());
                    }
                }
                return stillCandidate;
            }
        };

        for (DEdge prevDEdge : Iterables.filter(Iterables.filter(previousDiagramElements, DEdge.class), stillCandidate)) {
            final EdgeTarget sourceView = prevDEdge.getSourceNode();
            final EdgeTarget targetView = prevDEdge.getTargetNode();

            EObject target = prevDEdge.getTarget();
            if (edgeMappingQuery.evaluatePrecondition(diagram, diagram, interpreter, target, (DSemanticDecorator) sourceView, (DSemanticDecorator) targetView)) {
                result.add(new DEdgeCandidate(mapping, target, sourceView, targetView, sync.getFactory()));
            }
        }
    }

    private boolean validateNode(final EdgeTarget edgeTarget, Map<EObject, Collection<EdgeTarget>> viewsSemantics) {
        EObject semanticEdgeTarget = edgeTarget == null ? null : ((DSemanticDecorator) edgeTarget).getTarget();
        boolean valid = semanticEdgeTarget != null;
        if (viewsSemantics != null) {
            valid = valid && viewsSemantics.containsKey(semanticEdgeTarget) && viewsSemantics.get(semanticEdgeTarget).contains(edgeTarget);
        }
        return valid;
    }

    private void handleCandidatesFromSemanticTargets(Collection<DEdgeCandidate> result, EObject target, EdgeMapping mapping, Map<EObject, Collection<EdgeTarget>> sourceViewsSemantics,
            Map<EObject, Collection<EdgeTarget>> targetViewsSemantics) {
        EdgeMappingQuery edgeMappingQuery = new EdgeMappingQuery(mapping);
        Collection<EObject> edgeSourceSemantics = edgeMappingQuery.evaluateSourceFinderExpression(diagram, interpreter, target);
        Collection<EObject> edgeTargetSemantics = edgeMappingQuery.evaluateTargetFinderExpression(diagram, interpreter, target);

        for (final EObjectCouple couple : new CartesianProduct(edgeSourceSemantics, edgeTargetSemantics, sync.getFactory())) {
            final Collection<EdgeTarget> sourceViews = sourceViewsSemantics.get(couple.getObj1());
            if (sourceViews != null) {
                final Collection<EdgeTarget> targetViews = targetViewsSemantics.get(couple.getObj2());
                if (targetViews != null) {

                    for (final EObjectCouple viewsCouple : new CartesianProduct(sourceViews, targetViews, sync.getFactory())) {

                        final EdgeTarget sourceView = (EdgeTarget) viewsCouple.getObj1();
                        final EdgeTarget targetView = (EdgeTarget) viewsCouple.getObj2();

                        if (edgeMappingQuery.evaluatePrecondition(diagram, diagram, interpreter, target, (DSemanticDecorator) sourceView, (DSemanticDecorator) targetView)) {
                            result.add(new DEdgeCandidate(mapping, target, sourceView, targetView, sync.getFactory()));
                        }
                    }
                }
            }
        }
    }

    private Collection<DEdgeCandidate> computeEdgeCandidatesWithoutDomain(final Iterable<EdgeTarget> sourceViews, final Iterable<EdgeTarget> targetViews,
            final Map<EObject, Collection<EdgeTarget>> targetViewsSemantics, final EdgeMapping mapping) {

        DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.GET_EDGE_CANDIDATES_KEY);

        final Collection<DEdgeCandidate> result = new ArrayList<DEdgeCandidate>();

        if (tool || new DiagramElementMappingQuery(mapping).isSynchronizedAndCreateElement(diagram)) {
            for (final EdgeTarget sourceView : sourceViews) {
                handleCandidatesFromSourceView(result, sourceView, mapping, targetViews, targetViewsSemantics);
            }
        } else {
            Collection<DDiagramElement> previousDiagramElements = sync.getPreviousDiagramElements(diagram, mapping);
            handlePreviousCandidates(result, previousDiagramElements, mapping, null, targetViewsSemantics);
        }
        DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.GET_EDGE_CANDIDATES_KEY);
        return result;
    }

    private void handleCandidatesFromSourceView(final Collection<DEdgeCandidate> result, final EdgeTarget sourceView, final EdgeMapping mapping, final Iterable<EdgeTarget> targetViews,
            final Map<EObject, Collection<EdgeTarget>> targetViewsSemantics) {
        EObject sourceSemantic = ((DSemanticDecorator) sourceView).getTarget();
        Collection<EObject> potentialTargetSemantics = new ArrayList<EObject>();

        interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, diagram);
        interpreter.setVariable(IInterpreterSiriusVariables.VIEWPOINT, diagram);
        interpreter.setVariable(IInterpreterSiriusVariables.VIEWPOINT_2, diagram);

        if (!StringUtil.isEmpty(mapping.getTargetExpression())) {
            try {
                sourceSemantic = interpreter.evaluateEObject(sourceSemantic, mapping.getTargetExpression());
            } catch (final EvaluationException e) {
                RuntimeLoggerManager.INSTANCE.error(mapping, DescriptionPackage.eINSTANCE.getEdgeMapping_TargetExpression(), e);
            }
        }
        try {
            potentialTargetSemantics = interpreter.evaluateCollection(sourceSemantic, mapping.getTargetFinderExpression());
        } catch (final EvaluationException e) {
            // silent catch
        }
        interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
        interpreter.unSetVariable(IInterpreterSiriusVariables.VIEWPOINT);
        interpreter.unSetVariable(IInterpreterSiriusVariables.VIEWPOINT_2);

        EdgeMappingQuery edgeMappingQuery = new EdgeMappingQuery(mapping);

        /*
         * The minimize set is used to prevent double creation of Edges if the targetSemantics list contains doubles..
         */
        final Set<EObject> minimize = new HashSet<EObject>();
        for (final EObject potentialTargetSemantic : potentialTargetSemantics) {
            final Collection<EdgeTarget> targetEdgeTarget = targetViewsSemantics.get(potentialTargetSemantic);
            if (targetEdgeTarget != null && !minimize.contains(potentialTargetSemantic)) {
                minimize.add(potentialTargetSemantic);
                for (final EdgeTarget targetView : targetEdgeTarget) {
                    if (edgeMappingQuery.evaluatePrecondition(diagram, diagram, interpreter, sourceSemantic, (DSemanticDecorator) sourceView, (DSemanticDecorator) targetView)) {
                        result.add(new DEdgeCandidate(mapping, sourceSemantic, sourceView, targetView, sync.getFactory()));
                    }
                }
            }
        }
    }

    private Collection<EdgeTarget> computeTargetElements(final DSemanticDiagram diagram2, final List<DiagramElementMapping> sourceMappings,
            final Map<DiagramElementMapping, Collection<EdgeTarget>> mappingsToEdgeTargets) {
        return computeTargetElements(diagram2, sourceMappings, mappingsToEdgeTargets, null);
    }

    /**
     * .
     * 
     * @param diagram2
     *            .
     * @param targetMappings
     *            .
     * @param mappingsToEdgeTargets
     *            .
     * @param viewsSemantics
     *            .
     * @return .
     */
    private Collection<EdgeTarget> computeTargetElements(final DSemanticDiagram diagram2, final List<DiagramElementMapping> targetMappings,
            final Map<DiagramElementMapping, Collection<EdgeTarget>> mappingsToEdgeTargets, final Map<EObject, Collection<EdgeTarget>> viewsSemantics) {
        DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.COMPUTE_TARGET_ELEMENTS_KEY);
        final Collection<EdgeTarget> result = new MultipleCollection<EdgeTarget>();

        for (final DiagramElementMapping mapping : targetMappings) {
            // Get all elements corresponding to this mapping and its sub
            // mappings.
            final Collection<EdgeTarget> elements = getElementsFromMapping(mappingsToEdgeTargets, mapping);
            if (elements != null) {
                /*
                 * If no list for the views semantics are passed, then we don't have to gather them while iterating,
                 * otherwise it's better to do it now than waiting for doing it later.
                 */
                if (viewsSemantics == null) {
                    result.addAll(elements);
                } else {
                    for (final EdgeTarget edgeTarget : Iterables.filter(elements, EdgeTarget.class)) {
                        result.add(edgeTarget);
                        if (edgeTarget instanceof DSemanticDecorator) {
                            final EObject semanticElement = ((DSemanticDecorator) edgeTarget).getTarget();
                            Collection<EdgeTarget> existingTargets = viewsSemantics.get(semanticElement);
                            if (existingTargets != null) {
                                existingTargets.add(edgeTarget);
                            } else {
                                existingTargets = new ArrayList<EdgeTarget>();
                                existingTargets.add(edgeTarget);
                                viewsSemantics.put(semanticElement, existingTargets);
                            }
                        }
                    }
                }
            }
        }
        DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.COMPUTE_TARGET_ELEMENTS_KEY);
        return result;
    }

    /**
     * Compute all elements corresponding to this mapping and its sub mappings.
     * 
     * @param mappingsToEdgeTargets
     *            the map which enables to retrieve edge targets from mappings
     * @param mapping
     *            The current mapping
     * @return all elements corresponding to this mapping and its sub mappings.
     */
    private Collection<EdgeTarget> getElementsFromMapping(final Map<DiagramElementMapping, Collection<EdgeTarget>> mappingsToEdgeTargets, final DiagramElementMapping mapping) {
        Iterator<Map.Entry<DiagramElementMapping, Collection<EdgeTarget>>> iterator = mappingsToEdgeTargets.entrySet().iterator();

        EList<EdgeTarget> elements = new BasicEList<EdgeTarget>();
        Collection<EdgeTarget> tempList = mappingsToEdgeTargets.get(mapping);
        if (tempList != null) {
            elements.addAll(tempList);
        }

        while (iterator.hasNext()) {
            Map.Entry<DiagramElementMapping, Collection<EdgeTarget>> entry = iterator.next();
            if (!entry.getKey().equals(mapping)) {
                boolean isSubType = new DiagramElementMappingQuery(entry.getKey()).isSubTypeOf(mapping);
                if (isSubType) {
                    if (entry.getValue() != null) {
                        elements.addAll(entry.getValue());
                    }
                }
            }
        }

        return ImmutableSet.copyOf(Iterables.filter(elements, EdgeTarget.class));
    }
}
