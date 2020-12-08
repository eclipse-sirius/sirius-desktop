/*******************************************************************************
 * Copyright (c) 2020 Obeo.
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
package org.eclipse.sirius.diagram.ui.tools.api.format.semantic;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.RefreshIdsHolder;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManagerRegistry;
import org.eclipse.sirius.diagram.business.internal.helper.decoration.DecorationHelperInternal;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.operations.AbstractNodeMappingSpecOperations;
import org.eclipse.sirius.diagram.business.internal.sync.DDiagramElementSynchronizer;
import org.eclipse.sirius.diagram.business.internal.sync.DEdgeCandidate;
import org.eclipse.sirius.diagram.business.internal.sync.DNodeCandidate;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.MappingBasedDecoration;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.util.DiagramSwitch;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.internal.SiriusCopierHelper;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.eclipse.sirius.viewpoint.description.SemanticBasedDecoration;

/**
 * A {@link DiagramSwitch} implementation to go over and duplicate diagram content. It will only duplicate elements
 * present in the constructor parameter map that have a representation in the sourceDiagram.
 * 
 * @author adieumegard
 */
public class MappingBasedDiagramContentDuplicationSwitch extends DiagramSwitch<Void> {

    /**
     * The diagram onto which we will copy elements.
     */
    DSemanticDiagram targetDiagram;

    /**
     * The diagram from which we will copy elements.
     */
    DDiagram sourceDiagram;

    /**
     * The current semantic element of the targetDiagram in which we will add sub diagram elements.
     */
    EObject targetContext;

    /**
     * The session holding the targetDiagram.
     */
    Session targetSession;

    /**
     * The semantic objects correspondence map.
     */
    final Map<EObject, EObject> correspondenceMap;

    /**
     * A map containing the diagram element that are present in the target diagram at initialization time.
     */
    Map<EObject, List<DDiagramElement>> alreadyCreatedDiagramElementMap;

    /**
     * A map keep trace of the {@link DDiagramElement} duplicated from source diagram {@link DDiagramElement} onto the
     * target diagram.
     */
    Map<DDiagramElement, DDiagramElement> sourceDDiagramElementToTargetDDiagramElementMap;

    /**
     * A list of edges to be duplicated.
     */
    List<DEdge> toBeDuplicatedEdges;

    /**
     * The mapping manager.
     */
    DiagramMappingsManager mappingManager;

    /**
     * The interpreter.
     */
    IInterpreter interpreter;

    /**
     * Constructor setting the target diagram in which the duplicated {@link DDiagramElement} elements will be added.
     * 
     * @param targetDiagram
     *            The diagram to populate
     * @param correspondenceMap
     *            The correspondence map between source diagram and target diagram semantic elements
     * @param targetSession
     *            The target diagram session
     */
    public MappingBasedDiagramContentDuplicationSwitch(DSemanticDiagram targetDiagram, final Map<EObject, EObject> correspondenceMap, Session targetSession) {
        this.targetDiagram = targetDiagram;
        this.targetSession = targetSession;
        this.correspondenceMap = correspondenceMap;
        this.sourceDDiagramElementToTargetDDiagramElementMap = new HashMap<DDiagramElement, DDiagramElement>();
        this.toBeDuplicatedEdges = new ArrayList<DEdge>();
        this.mappingManager = DiagramMappingsManagerRegistry.INSTANCE.getDiagramMappingsManager(targetSession, targetDiagram);
        this.interpreter = targetSession.getInterpreter();

        this.alreadyCreatedDiagramElementMap = new HashMap<EObject, List<DDiagramElement>>();
        targetDiagram.getDiagramElements().stream().forEach(dElem -> {
            List<DDiagramElement> list = this.alreadyCreatedDiagramElementMap.get(dElem.getTarget());
            if (list == null) {
                list = new ArrayList<DDiagramElement>();
            }
            if (!list.contains(dElem)) {
                list.add(dElem);
                this.alreadyCreatedDiagramElementMap.put(dElem.getTarget(), list);
            }
        });
    }

    @Override
    public Void caseDDiagram(DDiagram object) {
        // Navigate
        EObject originalTargetContext = targetContext;
        this.targetContext = getTargetDiagram();
        this.sourceDiagram = object;
        object.getOwnedDiagramElements().stream().forEach(elem -> {
            this.doSwitch(elem);
        });
        this.targetContext = originalTargetContext;

        // Handle edges duplication after all nodes duplication
        handleEdgesDuplication();

        return null;
    }

    @Override
    public Void caseDDiagramElement(DDiagramElement object) {
        // Inherited Navigate
        return super.caseDDiagramElement(object);
    }

    @Override
    public Void caseDNodeContainer(DNodeContainer object) {
        // Duplicate and set
        DDiagramElement createdDDiagramElement = handleDDiagramElement(object);

        // Navigate
        if (createdDDiagramElement != null) {
            EObject originalTargetContext = targetContext;
            this.targetContext = createdDDiagramElement;
            object.getOwnedDiagramElements().stream().forEach(elem -> {
                this.doSwitch(elem);
            });
            object.getOwnedBorderedNodes().stream().forEach(elem -> {
                this.doSwitch(elem);
            });
            this.targetContext = originalTargetContext;
        }

        // Inherited Navigate
        return super.caseDNodeContainer(object);
    }

    @Override
    public Void caseDNodeList(DNodeList object) {
        // Duplicate and set
        DDiagramElement createdDDiagramElement = handleDDiagramElement(object);

        // Navigate
        if (createdDDiagramElement != null) {
            EObject originalTargetContext = targetContext;
            this.targetContext = createdDDiagramElement;
            object.getOwnedElements().stream().forEach(elem -> {
                this.doSwitch(elem);
            });
            object.getOwnedBorderedNodes().stream().forEach(elem -> {
                this.doSwitch(elem);
            });
            this.targetContext = originalTargetContext;
        }

        // Inherited Navigate
        return super.caseDNodeList(object);
    }

    @Override
    public Void caseDNode(DNode object) {
        // Duplicate and set
        DDiagramElement createdDDiagramElement = handleDDiagramElement(object);

        // Navigate
        if (createdDDiagramElement != null) {
            EObject originalTargetContext = targetContext;
            this.targetContext = createdDDiagramElement;
            object.getOwnedBorderedNodes().stream().forEach(elem -> {
                this.doSwitch(elem);
            });
            this.targetContext = originalTargetContext;
        }

        // Inherited Navigate
        return super.caseDNode(object);
    }

    @Override
    public Void caseDNodeListElement(DNodeListElement object) {
        // Duplicate and set
        DDiagramElement createdDDiagramElement = handleDDiagramElement(object);

        // Navigate
        if (createdDDiagramElement != null) {
            EObject originalTargetContext = targetContext;
            this.targetContext = createdDDiagramElement;
            object.getOwnedBorderedNodes().stream().forEach(elem -> {
                this.doSwitch(elem);
            });
            this.targetContext = originalTargetContext;
        }

        // Inherited Navigate
        return super.caseDNodeListElement(object);
    }

    @Override
    public Void caseDEdge(DEdge object) {
        // Mark for duplication
        handleDDiagramElement(object);

        // Inherited Navigate
        return super.caseDEdge(object);
    }

    /**
     * If {@code sourceDElement} is not already present in the target diagram and
     * <li>If {@code sourceDElement} is a DNode: duplicate and add in the current {@code targetContext}.</li>
     * <li>If {@code sourceDElement} is a DEdge: mark for duplication.</li>
     * 
     * @param sourceDElement
     *            The {@link DDiagramElement} to be duplicated.
     * @return The created {@link DDiagramElement} or the already existing one. Null is also a possible value and
     *         depicts an element for which there is no defined semantic mapping.
     */
    private DDiagramElement handleDDiagramElement(DDiagramElement sourceDElement) {
        EObject targetElement = correspondenceMap.get(sourceDElement.getTarget());
        if (targetElement != null) {
            DiagramElementMapping bestMapping = (DiagramElementMapping) sourceDElement.getMapping();
            return getOrCreateTargetDiagramElement(sourceDElement, targetElement, bestMapping);
        }
        return null;
    }

    /**
     * If {@code sourceDElement} is already present in the diagram return the already existing element else
     * <li>If {@code sourceDElement} is a node, duplicate and place it in the current {@code targetContext}.</li>
     * <li>If {@code sourceDElement} is a {@link DEdge} mark for duplication.</li>
     * 
     * @param sourceDElement
     *            The element to duplicate.
     * @param targetElement
     *            The semantic target element for which to create a new view.
     * @param bestMapping
     *            The mapping computed for this new DDiagramElement creation.
     * @return
     */
    private DDiagramElement getOrCreateTargetDiagramElement(DDiagramElement sourceDElement, EObject targetElement, DiagramElementMapping bestMapping) {
        // Do something only if targetElement representation have not already been created by diagram init
        DDiagramElement result = isAlreadyCreated(targetElement, bestMapping);
        if (result != null) {
            getSourceDDiagramElementToTargetDDiagramElementMap().put(sourceDElement, result);
            replicateShowHideAndFiltersOnElement(sourceDElement, result);
        } else if (sourceDElement instanceof DEdge) {
            toBeDuplicatedEdges.add((DEdge) sourceDElement);
        } else {

            // Setup node creation
            DNodeCandidate abstractDNodeCandidate = new DNodeCandidate((AbstractNodeMapping) bestMapping, targetElement, (DragAndDropTarget) targetContext,
                    RefreshIdsHolder.getOrCreateHolder(getTargetDiagram()));
            ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(targetElement);

            // Create new node
            DDiagramElementSynchronizer sync = new DDiagramElementSynchronizer(getTargetDiagram(), interpreter, accessor);
            AbstractDNode createdTargetElement = sync.createNewNode(mappingManager, abstractDNodeCandidate, isBorderedNode(sourceDElement));

            if (createdTargetElement != null) {
                AbstractNodeMappingSpecOperations.createBorderingNodes((AbstractNodeMapping) bestMapping, targetElement, createdTargetElement, Collections.emptyList(), getTargetDiagram());

                replicateShowHideAndFiltersOnElement(sourceDElement, createdTargetElement);

                getSourceDDiagramElementToTargetDDiagramElementMap().put(sourceDElement, createdTargetElement);

                result = createdTargetElement;
            } else {
                throw new IllegalArgumentException(
                        MessageFormat.format(Messages.MappingBasedDiagramContentDuplicationSwitch_ErrorImpossibleToCreateNodeFromNodeCandidate, abstractDNodeCandidate, sourceDElement));
            }
        }

        return result;
    }

    private void replicateShowHideAndFiltersOnElement(DDiagramElement sourceDElement, DDiagramElement targetDElement) {
        targetDElement.getGraphicalFilters().clear();
        targetDElement.getGraphicalFilters().addAll(SiriusCopierHelper.copyAllWithNoUidDuplication(sourceDElement.getGraphicalFilters()));

        targetDElement.setVisible(sourceDElement.isVisible());
    }

    /**
     * Check whether or not {@code targetElement} have a {@link DDiagramElement} created with {@code bestMapping}
     * mapping in the target diagram. This only takes into account mappings that are
     * {@link DiagramElementMapping#isCreateElements()}.
     * 
     * @param targetElement
     *            The element to check.
     * @param bestMapping
     *            The mapping used to create the element.
     * @return
     */
    private DDiagramElement isAlreadyCreated(EObject targetElement, DiagramElementMapping bestMapping) {
        List<DDiagramElement> dDiagramElements = alreadyCreatedDiagramElementMap.get(targetElement);
        if (dDiagramElements != null && bestMapping.isCreateElements()) {
            for (DDiagramElement dDiagramElement : dDiagramElements) {
                if (dDiagramElement.getMapping().equals(bestMapping)) {
                    return dDiagramElement;
                }
            }
        }
        return null;
    }

    /**
     * Check whether or not {@code object} is a bordered node.
     * 
     * @param object
     *            The object to check.
     * @return
     */
    private boolean isBorderedNode(DDiagramElement object) {
        return object.eContainingFeature().equals(DescriptionPackage.Literals.ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS);
    }

    /**
     * Duplicate edges
     * 
     */
    private void handleEdgesDuplication() {
        List<DEdge> edgesToEdge = new ArrayList<DEdge>();
        List<DEdge> handledEdges = new ArrayList<DEdge>();
        for (DEdge edge : toBeDuplicatedEdges) {
            handleEdge(edge, edgesToEdge, handledEdges);
        }
        handleEdgesToEdges(edgesToEdge, handledEdges);
    }

    /**
     * Handle {@code toHandleEdge} duplication. If the {@link DEdge} targets to or originates from an other
     * {@link DEdge} then it is added to {@code edgesToEdge}. If it is correctly duplicated then it is added to
     * {@code handledEdges}.
     * 
     * @param toHandleEdge
     *            The {@link DEdge} to duplicate in target diagram.
     * @param edgesToEdge
     *            A collection of {@link DEdge} targeting or originating from a {@link DEdge}.
     * @param handledEdges
     *            A collection of the {@link DEdge} that have been handled.
     */
    private void handleEdge(DEdge toHandleEdge, List<DEdge> edgesToEdge, List<DEdge> handledEdges) {
        EObject targetElement = correspondenceMap.get(toHandleEdge.getTarget());
        if (targetElement != null) {
            EdgeTarget sourceNode = toHandleEdge.getSourceNode();
            EdgeTarget targetNode = toHandleEdge.getTargetNode();

            // In this method we only handle edges to DSemantic
            if (sourceNode instanceof DEdge || targetNode instanceof DEdge) {
                edgesToEdge.add(toHandleEdge);
                return;
            }

            DDiagramElement sourceDiagramSourceEdgeTarget = getSourceDDiagramElementToTargetDDiagramElementMap().get(sourceNode);
            DDiagramElement sourceDiagramTargetEdgeTarget = getSourceDDiagramElementToTargetDDiagramElementMap().get(targetNode);

            if (sourceDiagramSourceEdgeTarget != null && sourceDiagramTargetEdgeTarget != null && sourceDiagramSourceEdgeTarget instanceof EdgeTarget
                    && sourceDiagramTargetEdgeTarget instanceof EdgeTarget) {
                RepresentationElementMapping mapping = toHandleEdge.getMapping();

                // Create new edge candidate
                DEdgeCandidate abstractDEdgeCandidate = new DEdgeCandidate((EdgeMapping) mapping, targetElement, (EdgeTarget) sourceDiagramSourceEdgeTarget, (EdgeTarget) sourceDiagramTargetEdgeTarget,
                        RefreshIdsHolder.getOrCreateHolder(getTargetDiagram()));
                ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(targetElement);
                DDiagramElementSynchronizer sync = new DDiagramElementSynchronizer(getTargetDiagram(), interpreter, accessor);

                /* maps for decorations */
                Map<EdgeMapping, Collection<MappingBasedDecoration>> edgeToMappingBasedDecoration = new HashMap<EdgeMapping, Collection<MappingBasedDecoration>>();
                Map<String, Collection<SemanticBasedDecoration>> edgeToSemanticBasedDecoration = new HashMap<String, Collection<SemanticBasedDecoration>>();

                /* create the mapping to edge targets map */
                Map<DiagramElementMapping, Collection<EdgeTarget>> mappingsToEdgeTargets = sync.computeMappingsToEdgeTargets(targetSession.getSelectedViewpoints(false));
                new DecorationHelperInternal(targetDiagram, interpreter, accessor).computeDecorations(mappingsToEdgeTargets, edgeToSemanticBasedDecoration, edgeToMappingBasedDecoration);

                DEdge createdNewEdge = sync.createNewEdge(mappingManager, abstractDEdgeCandidate, mappingsToEdgeTargets, edgeToMappingBasedDecoration, edgeToSemanticBasedDecoration);
                if (createdNewEdge != null) {
                    replicateShowHideAndFiltersOnElement(toHandleEdge, createdNewEdge);
                    handledEdges.add(toHandleEdge);
                    getSourceDDiagramElementToTargetDDiagramElementMap().put(toHandleEdge, createdNewEdge);
                } else {
                    throw new IllegalArgumentException(
                            MessageFormat.format(Messages.MappingBasedDiagramContentDuplicationSwitch_ErrorImpossibleToCreateEdgeFromEdgeCandidate, abstractDEdgeCandidate, toHandleEdge));
                }
            } else {
                if (!handledEdges.contains(toHandleEdge)) {
                    edgesToEdge.add(toHandleEdge);
                }
                return;
            }
        }
    }

    /**
     * Method duplicated edges targeting to or originating from an other {@link DEdge}. Duplicated {@link DEdge} are
     * added in the {@code handledEdges} collection.
     * 
     * @param edgesToEdge
     *            The {@link DEdge} to duplicate.
     * @param handledEdges
     *            The {@link DEdge} that have been duplicated.
     */
    private void handleEdgesToEdges(List<DEdge> edgesToEdge, List<DEdge> handledEdges) {
        List<DEdge> newEdgesToEdge = new ArrayList<DEdge>(edgesToEdge);
        List<DEdge> newlyHandledEdges = handledEdges;
        for (DEdge edge : edgesToEdge) {
            EdgeTarget sourceNode = edge.getSourceNode();
            EdgeTarget targetNode = edge.getTargetNode();

            boolean sourceHandlable = !(sourceNode instanceof DEdge) || handledEdges.contains(sourceNode);
            boolean targetHandlable = !(targetNode instanceof DEdge) || handledEdges.contains(targetNode);

            if (sourceHandlable && targetHandlable) {
                handleEdge(edge, newEdgesToEdge, newlyHandledEdges);
                newlyHandledEdges.add(edge);
                newEdgesToEdge.remove(edge);
            }
        }
        // There are still edges to convert
        if (newEdgesToEdge.size() > 0) {
            handleEdgesToEdges(newEdgesToEdge, newlyHandledEdges);
        }
    }

    public DSemanticDiagram getTargetDiagram() {
        return targetDiagram;
    }

    public Map<DDiagramElement, DDiagramElement> getSourceDDiagramElementToTargetDDiagramElementMap() {
        return sourceDDiagramElementToTargetDDiagramElementMap;
    }

}
