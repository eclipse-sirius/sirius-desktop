/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tools.api.refresh;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.AbstractDNode;
import org.eclipse.sirius.DDiagramElement;
import org.eclipse.sirius.DMappingBased;
import org.eclipse.sirius.DNodeContainer;
import org.eclipse.sirius.DNodeList;
import org.eclipse.sirius.DSemanticDecorator;
import org.eclipse.sirius.DSemanticDiagram;
import org.eclipse.sirius.DragAndDropTarget;
import org.eclipse.sirius.EdgeTarget;
import org.eclipse.sirius.business.api.componentization.DiagramMappingsManager;
import org.eclipse.sirius.business.api.componentization.DiagramMappingsManagerRegistry;
import org.eclipse.sirius.business.api.query.AbstractNodeMappingQuery;
import org.eclipse.sirius.business.api.query.DiagramElementMappingQuery;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.query.EdgeMappingQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.description.AbstractNodeMapping;
import org.eclipse.sirius.description.ContainerMapping;
import org.eclipse.sirius.description.EdgeMapping;
import org.eclipse.sirius.description.NodeMapping;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;

/**
 * Get the best mapping according to a contextual diagram, its enabled layers
 * and contributed diagram extension and default proposed mappings.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class BestMappingGetter {

    private Session session;

    private IInterpreter interpreter;

    private ModelAccessor modelAccessor;

    private DSemanticDiagram parentDDiagram;

    private DSemanticDecorator containerView;

    private EdgeTarget sourceView;

    private EdgeTarget targetView;

    private EObject semanticElt;

    /**
     * Constructor to get a {@link AbstractNodeMapping}.
     * 
     * @param containerView
     *            the container view on which a new view creation is requested
     * @param semanticElt
     *            the semantic element for which a view creation is requested
     */
    public BestMappingGetter(DSemanticDecorator containerView, EObject semanticElt) {
        EObjectQuery eObjectQuery = new EObjectQuery(containerView);
        this.session = eObjectQuery.getSession();
        this.containerView = containerView;
        this.semanticElt = semanticElt;
        this.parentDDiagram = (DSemanticDiagram) eObjectQuery.getParentDiagram().get();
        this.interpreter = session.getInterpreter();
        this.modelAccessor = session.getModelAccessor();
    }

    /**
     * Constructor to get a {@link EdgeMapping}.
     * 
     * @param sourceView
     *            the source view on which a new edge creation is requested
     * @param targetView
     *            the target view on which a new edge creation is requested
     * @param semanticElt
     *            the semantic element for which a view creation is requested
     */
    public BestMappingGetter(EdgeTarget sourceView, EdgeTarget targetView, EObject semanticElt) {
        EObjectQuery eObjectQuery = new EObjectQuery(sourceView);
        this.session = eObjectQuery.getSession();
        this.sourceView = sourceView;
        this.targetView = targetView;
        this.semanticElt = semanticElt;
        this.parentDDiagram = (DSemanticDiagram) eObjectQuery.getParentDiagram().get();
        this.containerView = parentDDiagram;
        this.interpreter = session.getInterpreter();
        this.modelAccessor = session.getModelAccessor();
    }

    /**
     * Get the most appropriate {@link ContainerMapping} to create a view under
     * the specified container view for the specified semantic element.
     * 
     * @param proposedContainerMappings
     *            a collection of proposed mappings, from a creation tool or a
     *            create view model operation for examples
     * @return the most appropriate {@link ContainerMapping} to create a view
     *         under the specified container view for the specified semantic
     *         element
     */
    public ContainerMapping getBestContainerMapping(Collection<ContainerMapping> proposedContainerMappings) {
        ContainerMapping bestContainerMapping = null;
        List<ContainerMapping> mappingCandidates = getAllContainerMappingsHierarchy(proposedContainerMappings);
        if (mappingCandidates.size() > 1) {
            AbstractNodeMapping firstValidMappingCandidate = getFirstValidAbstractNodeMappingCandidate(mappingCandidates);
            if (firstValidMappingCandidate instanceof ContainerMapping) {
                bestContainerMapping = (ContainerMapping) firstValidMappingCandidate;
            }
        } else if (mappingCandidates.size() == 1) {
            bestContainerMapping = mappingCandidates.get(0);
        }
        return bestContainerMapping;
    }

    /**
     * According to the contextual containerView, get a list of
     * {@link ContainerMapping} representing all mappings valid in this
     * <code>containerView</code> and which is owned by the inheritance
     * hierarchy of {@link ContainerMapping} existing in the
     * <code>proposedContainerMappings</code> collection in parameter. This
     * <code>proposedContainerMappings</code> collection parameter is useful to
     * restrict the resulting list. The resulting list is ordered from the most
     * specific {@link ContainerMapping} to the less one.
     * 
     * @param proposedContainerMappings
     *            a collection of proposed {@link ContainerMapping} to restrict
     *            the resulting {@link ContainerMapping} list
     * @return a list of {@link ContainerMapping} valid for the contextual
     *         <code>containerView</code> restricted by the
     *         {@link ContainerMapping} collection in parameter. This resulting
     *         list is ordered from the most specific {@link ContainerMapping}
     *         to the less one
     */
    public List<ContainerMapping> getAllContainerMappingsHierarchy(Collection<ContainerMapping> proposedContainerMappings) {
        List<ContainerMapping> allContainerMappingsHierarchy = new ArrayList<ContainerMapping>();
        Collection<ContainerMapping> mappingCandidates = new ArrayList<ContainerMapping>();
        // Get potential ContainerMapping children of the containerView
        DiagramMappingsManager diagramMappingsManager = DiagramMappingsManagerRegistry.INSTANCE.getDiagramMappingsManager(session, parentDDiagram);
        if (containerView instanceof DDiagramElement) {
            DDiagramElement containerViewDDiagramElt = (DDiagramElement) containerView;
            if (containerViewDDiagramElt instanceof DNodeContainer) {
                DNodeContainer containerViewDNodeContainer = (DNodeContainer) containerViewDDiagramElt;
                mappingCandidates.addAll(diagramMappingsManager.getContainerMappings(containerViewDNodeContainer, true));
            }
        } else {
            mappingCandidates.addAll(diagramMappingsManager.getContainerMappings());
        }
        // Keep only ContainerMapping which are in the import hierarchy of a
        // ContainerMapping in the proposedContainerMappings collection
        for (ContainerMapping potentialContainerMapping : mappingCandidates) {
            DiagramElementMappingQuery diagramElementMappingQuery = new DiagramElementMappingQuery(potentialContainerMapping);
            for (ContainerMapping proposedContainerMapping : proposedContainerMappings) {
                if (diagramElementMappingQuery.areInSameHiearchy(proposedContainerMapping)) {
                    allContainerMappingsHierarchy.add(potentialContainerMapping);
                    break;
                }
            }
        }
        return allContainerMappingsHierarchy;
    }

    /**
     * Get the most appropriate {@link NodeMapping} to create a view under the
     * specified container view for the specified semantic element.
     * 
     * @param proposedNodeMappings
     *            a collection of proposed mappings, from a creation tool or a
     *            create view model operation for examples
     * @return the most appropriate {@link NodeMapping} to create a view under
     *         the specified container view for the specified semantic element
     */
    public NodeMapping getBestNodeMapping(Collection<NodeMapping> proposedNodeMappings) {
        NodeMapping bestNodeMapping = null;
        List<NodeMapping> mappingCandidates = getAllNodeMappingsHierarchy(proposedNodeMappings);
        if (mappingCandidates.size() > 1) {
            AbstractNodeMapping firstValidMappingCandidate = getFirstValidAbstractNodeMappingCandidate(mappingCandidates);
            if (firstValidMappingCandidate instanceof NodeMapping) {
                bestNodeMapping = (NodeMapping) firstValidMappingCandidate;
            }
        } else if (mappingCandidates.size() == 1) {
            bestNodeMapping = mappingCandidates.get(0);
        }
        return bestNodeMapping;
    }

    /**
     * According to the contextual containerView, get a list of
     * {@link NodeMapping} representing all mappings valid in this
     * <code>containerView</code> and which is owned by the inheritance
     * hierarchy of {@link NodeMapping} existing in the
     * <code>proposedNodeMappings</code> collection in parameter. This
     * <code>proposedNodeMappings</code> collection parameter is useful to
     * restrict the resulting list. The resulting list is ordered from the most
     * specific {@link NodeMapping} to the less one.
     * 
     * @param proposedNodeMappings
     *            a collection of proposed {@link NodeMapping} to restrict the
     *            resulting {@link NodeMapping} list
     * @return a list of {@link NodeMapping} valid for the contextual
     *         <code>containerView</code> restricted by the {@link NodeMapping}
     *         collection in parameter. This resulting list is ordered from the
     *         most specific {@link NodeMapping} to the less one
     */
    public List<NodeMapping> getAllNodeMappingsHierarchy(Collection<NodeMapping> proposedNodeMappings) {
        List<NodeMapping> allNodeMappingsHierarchy = new ArrayList<NodeMapping>();
        Collection<NodeMapping> mappingCandidates = new ArrayList<NodeMapping>();
        // Get potential NodeMapping children of the containerView
        DiagramMappingsManager diagramMappingsManager = DiagramMappingsManagerRegistry.INSTANCE.getDiagramMappingsManager(session, parentDDiagram);
        if (containerView instanceof DDiagramElement) {
            DDiagramElement containerViewDDiagramElt = (DDiagramElement) containerView;
            if (containerViewDDiagramElt instanceof DNodeContainer) {
                DNodeContainer containerViewDNodeContainer = (DNodeContainer) containerViewDDiagramElt;
                mappingCandidates.addAll(diagramMappingsManager.getNodeMappings(containerViewDNodeContainer, true));
            } else if (containerViewDDiagramElt instanceof DNodeList) {
                DNodeList containerViewDNodeList = (DNodeList) containerViewDDiagramElt;
                mappingCandidates.addAll(diagramMappingsManager.getNodeMappings(containerViewDNodeList, true));
            }
            if (containerViewDDiagramElt instanceof AbstractDNode) {
                AbstractDNode containerViewAbstractDNode = (AbstractDNode) containerViewDDiagramElt;
                mappingCandidates.addAll(diagramMappingsManager.getBorderedNodeMappings(containerViewAbstractDNode, true));
            }
        } else {
            mappingCandidates.addAll(diagramMappingsManager.getNodeMappings());
        }
        // Keep only NodeMapping which are in the import hierarchy of a
        // NodeMapping in the proposedNodeMappings collection
        for (NodeMapping potentialContainerMapping : mappingCandidates) {
            DiagramElementMappingQuery diagramElementMappingQuery = new DiagramElementMappingQuery(potentialContainerMapping);
            for (NodeMapping proposedNodeMapping : proposedNodeMappings) {
                if (diagramElementMappingQuery.areInSameHiearchy(proposedNodeMapping)) {
                    allNodeMappingsHierarchy.add(potentialContainerMapping);
                    break;
                }
            }
        }
        return allNodeMappingsHierarchy;
    }

    /**
     * Get the most appropriate {@link EdgeMapping} to create a view under the
     * specified container view for the specified semantic element.
     * 
     * @param proposedEdgeMappings
     *            a collection of proposed mappings, from a creation tool or a
     *            create view model operation for examples
     * @return the most appropriate {@link EdgeMapping} to create a view under
     *         the specified container view for the specified semantic element
     */
    public EdgeMapping getBestEdgeMapping(Collection<EdgeMapping> proposedEdgeMappings) {
        EdgeMapping bestEdgeMapping = null;
        List<EdgeMapping> mappingCandidates = getAllEdgeMappingsHierarchy(proposedEdgeMappings);
        if (mappingCandidates.size() > 1) {
            EdgeMapping firstValidEdgeMappingCandidate = getFirstValidEdgeMappingCandidate(mappingCandidates);
            if (firstValidEdgeMappingCandidate != null) {
                bestEdgeMapping = firstValidEdgeMappingCandidate;
            }
        } else if (mappingCandidates.size() == 1) {
            bestEdgeMapping = mappingCandidates.get(0);
        }
        return bestEdgeMapping;
    }

    /**
     * Get a list of {@link EdgeMapping} representing all mappings valid in the
     * contextual diagram and which is owned by the inheritance hierarchy of
     * {@link EdgeMapping} existing in the <code>proposedEdgeMappings</code>
     * collection in parameter. This <code>proposedEdgeMappings</code>
     * collection parameter is useful to restrict the resulting list. The
     * resulting list is ordered from the most specific {@link EdgeMapping} to
     * the less one.
     * 
     * @param proposedEdgeMappings
     *            a collection of proposed {@link EdgeMapping} to restrict the
     *            resulting {@link EdgeMapping} list
     * @return a list of {@link EdgeMapping} valid for the contextual diagram
     *         restricted by the {@link EdgeMapping} collection in parameter.
     *         This resulting list is ordered from the most specific
     *         {@link EdgeMapping} to the less one
     */
    public List<EdgeMapping> getAllEdgeMappingsHierarchy(Collection<EdgeMapping> proposedEdgeMappings) {
        List<EdgeMapping> allEdgeMappingsHierarchy = new ArrayList<EdgeMapping>();
        DiagramMappingsManager diagramMappingsManager = DiagramMappingsManagerRegistry.INSTANCE.getDiagramMappingsManager(session, parentDDiagram);
        // Keep only EdgeMapping which are in the import hierarchy of a
        // EdgeMapping in the proposedNodeMappings collection
        for (EdgeMapping potentialEdgeMapping : diagramMappingsManager.getEdgeMappings()) {
            DiagramElementMappingQuery diagramElementMappingQuery = new DiagramElementMappingQuery(potentialEdgeMapping);
            for (EdgeMapping proposedEdgeMapping : proposedEdgeMappings) {
                if (diagramElementMappingQuery.areInSameHiearchy(proposedEdgeMapping)) {
                    allEdgeMappingsHierarchy.add(potentialEdgeMapping);
                    break;
                }
            }
        }
        return allEdgeMappingsHierarchy;
    }

    private AbstractNodeMapping getFirstValidAbstractNodeMappingCandidate(List<? extends AbstractNodeMapping> mappingCandidates) {
        AbstractNodeMapping firstValidMappingCandidate = null;
        for (AbstractNodeMapping mappingCandidate : mappingCandidates) {
            if (modelAccessor.eInstanceOf(semanticElt, mappingCandidate.getDomainClass())) {
                AbstractNodeMappingQuery abstractNodeMappingQuery = new AbstractNodeMappingQuery(mappingCandidate);
                Collection<EObject> semanticCandidates = getSemanticCandidates(abstractNodeMappingQuery);
                if (semanticCandidates.contains(semanticElt)) {
                    if (abstractNodeMappingQuery.evaluatePrecondition(parentDDiagram, (DragAndDropTarget) containerView, interpreter, semanticElt)) {
                        firstValidMappingCandidate = mappingCandidate;
                        break;
                    }
                }
            }
        }
        return firstValidMappingCandidate;
    }

    private EdgeMapping getFirstValidEdgeMappingCandidate(List<EdgeMapping> mappingCandidates) {
        EdgeMapping firstValidMappingCandidate = null;
        for (EdgeMapping mappingCandidate : mappingCandidates) {
            EdgeMappingQuery edgeMappingQuery = new EdgeMappingQuery(mappingCandidate);
            Collection<EObject> semanticCandidates = null;
            if (edgeMappingQuery.canCreate((DMappingBased) sourceView, (DMappingBased) targetView)) {
                // If candidate is domain based, check domain class of
                // semanticElt
                if (!mappingCandidate.isUseDomainElement() || modelAccessor.eInstanceOf(semanticElt, mappingCandidate.getDomainClass())) {
                    semanticCandidates = getSemanticCandidates(mappingCandidate, edgeMappingQuery);
                    if (semanticCandidates.contains(semanticElt)) {
                        if (evaluateEdgeMappingPrecondition(parentDDiagram, edgeMappingQuery)) {
                            firstValidMappingCandidate = mappingCandidate;
                            break;
                        }
                    }
                }
            }
        }
        return firstValidMappingCandidate;
    }

    private Collection<EObject> getSemanticCandidates(EdgeMapping mappingCandidate, EdgeMappingQuery edgeMappingQuery) {
        Collection<EObject> semanticCandidates;
        if (mappingCandidate.isUseDomainElement()) {
            semanticCandidates = getSemanticCandidates(edgeMappingQuery);
        } else {
            EObject sourceSemantic = ((DSemanticDecorator) sourceView).getTarget();
            semanticCandidates = new ArrayList<EObject>();
            semanticCandidates.add(sourceSemantic);
            semanticCandidates.addAll(edgeMappingQuery.evaluateTargetFinderExpression(parentDDiagram, interpreter, sourceSemantic));
        }
        return semanticCandidates;
    }

    private Collection<EObject> getSemanticCandidates(DiagramElementMappingQuery query) {
        Collection<EObject> semanticCandidates;
        if (query.hasCandidatesExpression()) {
            semanticCandidates = query.evaluateCandidateExpression(parentDDiagram, interpreter, (DragAndDropTarget) containerView);
        } else {
            semanticCandidates = query.getAllCandidates(session, modelAccessor);
        }
        return semanticCandidates;
    }

    private boolean evaluateEdgeMappingPrecondition(DSemanticDiagram dDiagram, EdgeMappingQuery edgeMappingQuery) {
        boolean edgeMappingPreconditionEvaluation = edgeMappingQuery.evaluatePrecondition(dDiagram, (DragAndDropTarget) containerView, interpreter, semanticElt, (DSemanticDecorator) sourceView,
                (DSemanticDecorator) targetView);
        return edgeMappingPreconditionEvaluation;
    }
}
