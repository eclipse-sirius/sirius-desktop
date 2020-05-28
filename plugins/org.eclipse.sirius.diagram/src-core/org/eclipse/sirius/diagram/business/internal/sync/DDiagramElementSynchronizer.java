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
package org.eclipse.sirius.diagram.business.internal.sync;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.AbstractDNode;
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
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.NodeStyle;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramDescriptionMappingsManager;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager;
import org.eclipse.sirius.diagram.business.api.helper.SiriusDiagramHelper;
import org.eclipse.sirius.diagram.business.api.helper.display.DisplayMode;
import org.eclipse.sirius.diagram.business.api.helper.display.DisplayService;
import org.eclipse.sirius.diagram.business.api.helper.display.DisplayServiceManager;
import org.eclipse.sirius.diagram.business.api.helper.graphicalfilters.HideFilterHelper;
import org.eclipse.sirius.diagram.business.api.query.ContainerMappingQuery;
import org.eclipse.sirius.diagram.business.api.query.DiagramDescriptionMappingManagerQuery;
import org.eclipse.sirius.diagram.business.api.query.IEdgeMappingQuery;
import org.eclipse.sirius.diagram.business.internal.componentization.mappings.DiagramDescriptionMappingsManagerImpl;
import org.eclipse.sirius.diagram.business.internal.helper.decoration.DecorationHelperInternal;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.DiagramElementMappingHelper;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.MappingWithInterpreterHelper;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.StyleHelper;
import org.eclipse.sirius.diagram.business.internal.metamodel.operations.DDiagramSpecOperations;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.IEdgeMapping;
import org.eclipse.sirius.diagram.description.MappingBasedDecoration;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.style.ContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.EdgeStyleDescription;
import org.eclipse.sirius.diagram.description.style.EllipseNodeDescription;
import org.eclipse.sirius.diagram.description.style.LozengeNodeDescription;
import org.eclipse.sirius.diagram.description.style.NodeStyleDescription;
import org.eclipse.sirius.diagram.description.style.SquareDescription;
import org.eclipse.sirius.diagram.description.style.WorkspaceImageDescription;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tools.api.interpreter.IInterpreterMessages;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.description.SemanticBasedDecoration;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.LabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;
import org.eclipse.sirius.viewpoint.description.style.StylePackage;
import org.eclipse.sirius.viewpoint.description.style.TooltipStyleDescription;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;

/**
 * This class is able to synchronize diagram elements and styles.
 * 
 * @author cbrun
 */
public class DDiagramElementSynchronizer {

    private final IInterpreter interpreter;

    private final DSemanticDiagram diagram;

    private final ModelAccessor accessor;

    private final StyleHelper styleHelper;

    private final MappingWithInterpreterHelper mappingHelper;

    private final DecorationHelperInternal decorationHelper;

    /**
     * Create a new synchronizer for the given diagram.
     * 
     * @param diagram
     *            diagram to synchronize.
     * @param interpreter
     *            current interpreter.
     * @param accessor
     *            model accessor
     */
    public DDiagramElementSynchronizer(final DSemanticDiagram diagram, final IInterpreter interpreter, final ModelAccessor accessor) {
        super();
        this.diagram = diagram;
        this.interpreter = interpreter;
        this.accessor = accessor;
        this.styleHelper = new StyleHelper(interpreter);
        this.mappingHelper = new MappingWithInterpreterHelper(interpreter);
        this.decorationHelper = new DecorationHelperInternal(diagram, interpreter, accessor);
    }

    /**
     * Create completely a new Node with its style and so on from a candidate.
     * 
     * @param mappingManager
     *            the manager used to handle return the Mappings to consider regarding the enablement of Viewpoints.
     * @param candidate
     *            the node candidate, it contains the mapping and the semantic target.
     * @param isBorder
     *            true if the element is a border one.
     * @return newly created node.
     */
    public AbstractDNode createNewNode(DiagramMappingsManager mappingManager, final DNodeCandidate candidate, final boolean isBorder) {
        return createNewNode(mappingManager, candidate, isBorder, -1);
    }

    /**
     * Build a new Node from a candidate.
     * 
     * @param mappingManager
     *            the manager used to handle return the Mappings to consider regarding the enablement of Viewpoints.
     * @param candidate
     *            the node candidate, it contains the mapping and the semantic target.
     * @param isBorder
     *            true if the element is a border one.
     * @param insertionIndex
     *            the insertion index. give a negative value if you don't care
     * @return newly created node.
     */
    public AbstractDNode createNewNode(DiagramMappingsManager mappingManager, final DNodeCandidate candidate, final boolean isBorder, final int insertionIndex) {
        final DragAndDropTarget container = candidate.getViewContainer();
        final AbstractDNode newNode = createAbstractNode(container, candidate, isBorder);
        if (insertionIndex > 0) {
            SiriusDiagramHelper.addNodeInContainer(container, isBorder, newNode, insertionIndex);
        } else {
            SiriusDiagramHelper.addNodeInContainer(container, isBorder, newNode);
        }
        refresh(newNode);
        refreshSemanticElements(newNode, candidate.getMapping());
        createStyle(newNode, candidate.getMapping());
        computeVisibilityOnCreation(mappingManager, newNode);
        return newNode;
    }

    /**
     * Build a new Node from a candidate.
     * 
     * @param container
     *            the object who will contain the new node.
     * @param candidate
     *            the node candidate, it contains the mapping and the semantic target.
     * @param border
     *            true if the element is a border one.
     * @return newly created node.
     */
    private AbstractDNode createAbstractNode(final DragAndDropTarget container, final DNodeCandidate candidate, final boolean border) {
        AbstractDNode result = null;
        if (candidate.getMapping() instanceof NodeMapping) {
            final NodeMapping mapping = (NodeMapping) candidate.getMapping();
            if (container instanceof DNodeList) {
                if (!border) {
                    final DNodeListElement newNode = DiagramFactory.eINSTANCE.createDNodeListElement();
                    newNode.setTarget(candidate.getSemantic());
                    newNode.setActualMapping(mapping);
                    result = newNode;
                } else {
                    final DNode newNode = DiagramFactory.eINSTANCE.createDNode();
                    newNode.setTarget(candidate.getSemantic());
                    newNode.setActualMapping(mapping);
                    result = newNode;
                }
            } else {
                final DNode newNode = DiagramFactory.eINSTANCE.createDNode();
                newNode.setTarget(candidate.getSemantic());
                newNode.setActualMapping(mapping);
                result = newNode;

            }

        } else if (candidate.getMapping() instanceof ContainerMapping) {
            final ContainerMapping mapping = (ContainerMapping) candidate.getMapping();
            DDiagramElementContainer newContainer = null;
            if (new ContainerMappingQuery(mapping).isListContainer()) {
                newContainer = DiagramFactory.eINSTANCE.createDNodeList();
            } else {
                // Other behaviors : ContainerLayout.FreeForm/VerticalStack
                DNodeContainer nodeContainer = DiagramFactory.eINSTANCE.createDNodeContainer();
                nodeContainer.setChildrenPresentation(mapping.getChildrenPresentation());
                newContainer = nodeContainer;
            }
            newContainer.setTarget(candidate.getSemantic());
            newContainer.setActualMapping(mapping);
            result = newContainer;
        }
        return result;
    }

    /**
     * Compute a mappings to edge target map for a given diagram.
     * 
     * @param enabledVp
     *            the list of viewpoints to consider
     * 
     * @return the requested map
     */
    public Map<DiagramElementMapping, Collection<EdgeTarget>> computeMappingsToEdgeTargets(Collection<Viewpoint> enabledVp) {
        final Map<DiagramElementMapping, Collection<EdgeTarget>> mappingsToEdgeTargets = new HashMap<DiagramElementMapping, Collection<EdgeTarget>>();

        final DiagramDescription description = diagram.getDescription();

        final DiagramDescriptionMappingsManager mappingsManager = new DiagramDescriptionMappingsManagerImpl(description);
        mappingsManager.computeMappings(enabledVp);
        final Set<DiagramElementMapping> allMappings = new DiagramDescriptionMappingManagerQuery(mappingsManager).computeAllMappings();
        final Iterable<NodeMapping> allNodeMappings = Iterables.filter(allMappings, NodeMapping.class);
        final Iterable<ContainerMapping> allContainerMappings = Iterables.filter(allMappings, ContainerMapping.class);
        final Iterable<EdgeMapping> allEdgeMappings = Iterables.filter(allMappings, EdgeMapping.class);

        for (final NodeMapping nodeMapping : allNodeMappings) {
            final List<DNode> nodes = DDiagramSpecOperations.getNodesFromMapping(diagram, nodeMapping);
            if (!nodes.isEmpty()) {
                mappingsToEdgeTargets.put(nodeMapping, DDiagramElementSynchronizer.convertNodesAndContainersToEdgeTarget(nodes));
            }
        }

        for (final ContainerMapping containerMapping : allContainerMappings) {
            final List<DDiagramElementContainer> containers = DDiagramSpecOperations.getContainersFromMapping(diagram, containerMapping);
            if (!containers.isEmpty()) {
                mappingsToEdgeTargets.put(containerMapping, DDiagramElementSynchronizer.convertNodesAndContainersToEdgeTarget(containers));
            }
        }

        for (final EdgeMapping edgeMapping : allEdgeMappings) {
            final List<DEdge> edges = DDiagramSpecOperations.getEdgesFromMapping(diagram, edgeMapping);
            if (!edges.isEmpty()) {
                mappingsToEdgeTargets.put(edgeMapping, DDiagramElementSynchronizer.convertNodesAndContainersToEdgeTarget(edges));
            }
        }

        return mappingsToEdgeTargets;
    }

    private static Collection<EdgeTarget> convertNodesAndContainersToEdgeTarget(final Collection<? extends DDiagramElement> diagramElements) {
        return ImmutableSet.copyOf(Iterables.filter(diagramElements, EdgeTarget.class));
    }

    /**
     * Create completely a new Edge with it style path and so on from a candidate and the mappings maps.
     * 
     * @param mappingManager
     *            the manager used to handle return the Mappings to consider regarding the enablement of Viewpoints.
     * @param candidate
     *            the edge candidate
     * @param mappingsToEdgeTargets
     *            mappings to edge targets map
     * @param edgeToMappingBasedDecoration
     *            a map with for key a mapping and for value the associated decorations
     * @param edgeToSemanticBasedDecoration
     *            a map with for key a domain class as string and for value the associated decorations
     * @return the created edge
     */
    public DEdge createNewEdge(DiagramMappingsManager mappingManager, final DEdgeCandidate candidate, final Map<DiagramElementMapping, Collection<EdgeTarget>> mappingsToEdgeTargets,
            final Map<EdgeMapping, Collection<MappingBasedDecoration>> edgeToMappingBasedDecoration, final Map<String, Collection<SemanticBasedDecoration>> edgeToSemanticBasedDecoration) {

        DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.CREATE_MISSING_EDGES_KEY);
        DEdge newEdge = createAndAttachEdge(candidate);

        Option<EdgeMapping> edgeMapping = new IEdgeMappingQuery(candidate.getMapping()).getEdgeMapping();
        if (edgeMapping.some()) {
            refreshSemanticElements(newEdge, edgeMapping.get());
            createStyle(newEdge, edgeMapping.get(), diagram);
            refresh(newEdge);
            updatePath(newEdge, edgeMapping.get(), mappingsToEdgeTargets);
        }

        computeEdgeDecorations(newEdge, edgeToMappingBasedDecoration, edgeToSemanticBasedDecoration);
        computeVisibilityOnCreation(mappingManager, newEdge);

        DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.CREATE_MISSING_EDGES_KEY);
        return newEdge;
    }

    /**
     * Create a new Edge from a candidate.
     * 
     * @param candidate
     *            the edge candidate containing the mapping and the edge semantic target (among other things).
     * @return the newly created edge.
     */
    private DEdge createAndAttachEdge(final DEdgeCandidate candidate) {
        final DEdge newEdge = DiagramFactory.eINSTANCE.createDEdge();
        diagram.getOwnedDiagramElements().add(newEdge);
        newEdge.setTarget(candidate.getSemantic());
        newEdge.setActualMapping(candidate.getMapping());
        newEdge.setSourceNode(candidate.getSourceView());
        newEdge.setTargetNode(candidate.getTargetView());
        return newEdge;
    }

    private void computeVisibilityOnCreation(DiagramMappingsManager mappingManager, final DDiagramElement element) {
        final DisplayService service = DisplayServiceManager.INSTANCE.getDisplayService(DisplayMode.CREATION);
        if (service == null) {
            return;
        }

        DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.IS_VISIBLE_KEY);
        element.setVisible(service.computeVisibility(mappingManager, diagram, element));
        if (!service.computeLabelVisibility(diagram, element)) {
            HideFilterHelper.INSTANCE.hideLabel(element);
        }
        DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.IS_VISIBLE_KEY);
    }

    /**
     * Compute edge decorations.
     * 
     * @param edge
     *            the edge
     * @param edgeToMappingBasedDecoration
     *            a map with for key a mapping and for value the associated decorations
     * @param edgeToSemanticBasedDecoration
     *            a map with for key a domain class as string and for value the associated decorations
     */
    public void computeEdgeDecorations(final DEdge edge, final Map<EdgeMapping, Collection<MappingBasedDecoration>> edgeToMappingBasedDecoration,
            final Map<String, Collection<SemanticBasedDecoration>> edgeToSemanticBasedDecoration) {
        /* semantic based decorations */
        IEdgeMapping actualMapping = edge.getActualMapping();
        final Option<EdgeMapping> edgeMapping = new IEdgeMappingQuery(actualMapping).getEdgeMapping();
        if (edgeMapping.some() && edgeMapping.get().isUseDomainElement() && edgeToSemanticBasedDecoration.containsKey(edgeMapping.get().getDomainClass())) {
            final Collection<SemanticBasedDecoration> semanticBasedDecorations = edgeToSemanticBasedDecoration.get(edgeMapping.get().getDomainClass());
            for (final SemanticBasedDecoration semanticBasedDecoration : semanticBasedDecorations) {
                decorationHelper.addDecoration(edge, semanticBasedDecoration);
            }
        }
        /* mapping based decoration */
        if (edgeToMappingBasedDecoration.containsKey(actualMapping)) {
            final Collection<MappingBasedDecoration> mappingBasedDecorations = edgeToMappingBasedDecoration.get(actualMapping);
            for (final MappingBasedDecoration mappingBasedDecoration : mappingBasedDecorations) {
                decorationHelper.addDecoration(edge, mappingBasedDecoration);
            }
        }
    }

    /**
     * Refresh an edge computing it's new size and style.
     * 
     * @param edge
     *            edge to be refreshed.
     */
    protected void refresh(final DEdge edge) {
        EObject containerVariable = null;
        if (edge.eContainer() instanceof DSemanticDecorator) {
            containerVariable = ((DSemanticDecorator) edge.eContainer()).getTarget();
        }
        // Recompute the conditional style
        this.interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, this.diagram);
        this.interpreter.setVariable(IInterpreterSiriusVariables.VIEW, edge);
        this.interpreter.setVariable(IInterpreterSiriusVariables.SOURCE_VIEW, edge.getSourceNode());
        this.interpreter.setVariable(IInterpreterSiriusVariables.TARGET_VIEW, edge.getTargetNode());

        this.mappingHelper.affectAndRefreshStyle(edge.getDiagramElementMapping(), edge, edge.getTarget(), containerVariable, (DDiagram) edge.eContainer());

        this.interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
        this.interpreter.unSetVariable(IInterpreterSiriusVariables.VIEW);
        this.interpreter.unSetVariable(IInterpreterSiriusVariables.SOURCE_VIEW);
        this.interpreter.unSetVariable(IInterpreterSiriusVariables.TARGET_VIEW);

        final EdgeStyle edgeStyle = edge.getOwnedStyle();
        if (edgeStyle != null) {
            final EdgeStyleDescription edgeStyleDescription = (EdgeStyleDescription) edgeStyle.getDescription();
            // LABEL
            refreshCenterLabel(edge, edgeStyleDescription);
            refreshBeginLabel(edge, edgeStyleDescription);
            refreshEndLabel(edge, edgeStyleDescription);
        }

        // semantic elements
        final Option<EdgeMapping> actualMapping = new IEdgeMappingQuery(edge.getActualMapping()).getEdgeMapping();
        if (actualMapping.some()) {
            refreshSemanticElements(edge, actualMapping.get());
        }

        // update decorations
        decorationHelper.updateDecoration(edge);
    }

    /**
     * @param edge
     * @param edgeStyleDescription
     */
    private void refreshEndLabel(final DEdge edge, final EdgeStyleDescription edgeStyleDescription) {
        if (edgeStyleDescription.getEndLabelStyleDescription() != null && !StringUtil.isEmpty(edgeStyleDescription.getEndLabelStyleDescription().getLabelExpression())) {
            final String label = computeLabel(edge, edgeStyleDescription.getEndLabelStyleDescription());
            edge.setEndLabel(label);
            if (!("".equals(edge.getEndLabel()) && label == null) && !StringUtil.equals(edge.getEndLabel(), label)) { //$NON-NLS-1$
                // This is a workaround for a CDO issue in legacy mode
                // (https://bugs.eclipse.org/bugs/show_bug.cgi?id=404152)
                edge.setEndLabel(label);
            }
        } else if (!StringUtil.isEmpty(edge.getEndLabel())) {
            edge.setEndLabel(""); //$NON-NLS-1$
        }
    }

    /**
     * @param edge
     * @param edgeStyleDescription
     */
    private void refreshBeginLabel(final DEdge edge, final EdgeStyleDescription edgeStyleDescription) {
        if (edgeStyleDescription.getBeginLabelStyleDescription() != null && !StringUtil.isEmpty(edgeStyleDescription.getBeginLabelStyleDescription().getLabelExpression())) {
            final String label = computeLabel(edge, edgeStyleDescription.getBeginLabelStyleDescription());
            if (!("".equals(edge.getBeginLabel()) && label == null) && !StringUtil.equals(edge.getBeginLabel(), label)) { //$NON-NLS-1$
                // This is a workaround for a CDO issue in legacy mode
                // (https://bugs.eclipse.org/bugs/show_bug.cgi?id=404152)
                edge.setBeginLabel(label);
            }
        } else if (!StringUtil.isEmpty(edge.getBeginLabel())) {
            edge.setBeginLabel(""); //$NON-NLS-1$
        }
    }

    /**
     * @param edge
     * @param edgeStyleDescription
     */
    private void refreshCenterLabel(final DEdge edge, final EdgeStyleDescription edgeStyleDescription) {
        if (edgeStyleDescription.getCenterLabelStyleDescription() != null && !StringUtil.isEmpty(edgeStyleDescription.getCenterLabelStyleDescription().getLabelExpression())) {
            final String label = computeLabel(edge, edgeStyleDescription.getCenterLabelStyleDescription());
            if (!("".equals(edge.getName()) && label == null) && !StringUtil.equals(edge.getName(), label)) { //$NON-NLS-1$
                // This is a workaround for a CDO issue in legacy mode
                // (https://bugs.eclipse.org/bugs/show_bug.cgi?id=404152)
                edge.setName(label);
            }
        } else if (!StringUtil.isEmpty(edge.getName())) {
            edge.setName(""); //$NON-NLS-1$
        }
    }

    /**
     * Refresh a DDiagramElement computing it's label, size and style.
     * 
     * @param dde
     *            node to be refreshed.
     */
    public void refresh(final DDiagramElement dde) {
        if (accessor.getPermissionAuthority().canEditInstance(dde)) {
            if (dde instanceof DNode) {
                refresh((DNode) dde);
            } else if (dde instanceof DNodeListElement) {
                refresh((DNodeListElement) dde);
            } else if (dde instanceof DDiagramElementContainer) {
                refresh((DDiagramElementContainer) dde);
            } else if (dde instanceof DEdge) {
                refresh((DEdge) dde);
            }
        }
    }

    /**
     * Refresh a list element.
     * 
     * @param newNode
     *            list element to be refreshed.
     */
    protected void refresh(final DNodeListElement newNode) {
        final DSemanticDecorator container = (DSemanticDecorator) newNode.eContainer();
        if (container != null) {
            final NodeStyleDescription style = (NodeStyleDescription) this.mappingHelper.getBestStyleDescription(newNode.getActualMapping(), newNode.getTarget(), newNode, container.getTarget(),
                    diagram);
            if (style != null) {
                refreshLabel(newNode, style);
                refreshTooltip(newNode, style);
            }
        }
        if (newNode.getOwnedStyle() != null) {
            Option<NodeStyle> noPreviousStyle = Options.newNone();
            styleHelper.refreshStyle(newNode.getOwnedStyle(), noPreviousStyle);
        }
        // clean decorations
        decorationHelper.updateDecoration(newNode);
        refreshSemanticElements(newNode, newNode.getDiagramElementMapping());
    }

    /**
     * Refresh a container.
     * 
     * @param container
     *            element to be refreshed.
     */
    protected void refresh(final DDiagramElementContainer container) {
        final DSemanticDecorator cContainer = (DSemanticDecorator) container.eContainer();
        ContainerMapping containerMapping = container.getActualMapping();
        if (cContainer != null) {
            ContainerStyleDescription containerStyleDescription = null;
            if (hasSafeTarget(container) && hasSafeTarget(cContainer)) {
                containerStyleDescription = (ContainerStyleDescription) this.mappingHelper.getBestStyleDescription(containerMapping, container.getTarget(), container, cContainer.getTarget(), diagram);
            }
            if (containerStyleDescription != null) {
                refreshLabel(container, containerStyleDescription);
                refreshTooltip(container, containerStyleDescription);
                refreshStyle(container, containerMapping);
            }
        }
        // clean decorations
        decorationHelper.updateDecoration(container);
        refreshSemanticElements(container, containerMapping);
    }

    /**
     * Refresh a {@link DNode}.
     * 
     * @param newNode
     *            node to be refreshed.
     */
    protected void refresh(final DNode newNode) {
        final DSemanticDecorator container = (DSemanticDecorator) newNode.eContainer();
        if (container != null) {
            NodeStyleDescription nodeStyleDescription = null;
            NodeMapping nodeMapping = newNode.getActualMapping();
            if (hasSafeTarget(newNode) && hasSafeTarget(container)) {
                nodeStyleDescription = (NodeStyleDescription) this.mappingHelper.getBestStyleDescription(nodeMapping, newNode.getTarget(), newNode, container.getTarget(), diagram);
            }
            if (nodeStyleDescription != null) {
                if (newNode.getResizeKind() != nodeStyleDescription.getResizeKind()) {
                    newNode.setResizeKind(nodeStyleDescription.getResizeKind());
                }

                // Don't set width and height here for Ellipse, Lozenge, Square
                // or WorkspaceImage styles
                boolean customSizeRefresh = nodeStyleDescription instanceof EllipseNodeDescription || nodeStyleDescription instanceof LozengeNodeDescription
                        || nodeStyleDescription instanceof SquareDescription || nodeStyleDescription instanceof WorkspaceImageDescription;

                if (!StringUtil.isEmpty(nodeStyleDescription.getSizeComputationExpression()) && !customSizeRefresh) {
                    styleHelper.setComputedSize(newNode, nodeStyleDescription);
                }

                refreshLabel(newNode, nodeStyleDescription);
                refreshTooltip(newNode, nodeStyleDescription);
                refreshStyle(newNode, nodeMapping);
            }
        }

        // update decorations
        decorationHelper.updateDecoration(newNode);
        refreshSemanticElements(newNode, newNode.getDiagramElementMapping());
    }

    private void refreshLabel(final DDiagramElement dde, LabelStyleDescription labelStyleDescription) {
        if (!StringUtil.isEmpty(labelStyleDescription.getLabelExpression())) {
            String computedLabel = computeLabel(dde, labelStyleDescription);
            if (!("".equals(dde.getName()) && computedLabel == null) && !StringUtil.equals(dde.getName(), computedLabel)) { //$NON-NLS-1$
                // This is a workaround for a CDO issue in legacy mode
                // (https://bugs.eclipse.org/bugs/show_bug.cgi?id=404152)
                dde.setName(computedLabel);
            }
        }
    }

    private void refreshTooltip(final DDiagramElement elt, final TooltipStyleDescription style) {
        if (!StringUtil.isEmpty(style.getTooltipExpression())) {
            final String oldValue = elt.getTooltipText();
            final String newValue = computeTooltip(elt, style);
            if (!Objects.equal(newValue, oldValue)) {
                elt.setTooltipText(newValue);
            }
        }
    }

    /**
     * Refreshes a node style.
     * 
     * @param node
     *            Node which style is to be refreshed.
     * @param mapping
     *            Mapping that is to be used to compute the best style for this node.
     */
    public void refreshStyle(final AbstractDNode node, final AbstractNodeMapping mapping) {
        EObject containerVariable = null;
        if (node.eContainer() != null) {
            this.interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER_VIEW, node.eContainer());
            if (node.eContainer() instanceof DSemanticDecorator) {
                containerVariable = ((DSemanticDecorator) node.eContainer()).getTarget();
                this.interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER, containerVariable);

            }
        }
        this.interpreter.setVariable(IInterpreterSiriusVariables.VIEWPOINT, this.diagram);
        this.interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, this.diagram);
        this.interpreter.setVariable(IInterpreterSiriusVariables.VIEW, node);

        StyleDescription bestStyleDescription = null;
        if (hasSafeTarget(node)) {
            bestStyleDescription = this.mappingHelper.getBestStyleDescription(mapping, node.getTarget(), node, containerVariable, diagram);
        }
        Style style = node.getStyle();
        if (style != null) {
            Style bestStyle = this.mappingHelper.getBestStyle(mapping, node.getTarget(), node, containerVariable, diagram);
            // In case the current Style is a customized
            if (bestStyleDescription != null && !isSameDescription(bestStyleDescription, style, bestStyle)) {
                final Style currentStyle = style;
                if (isCustomizedWorkspaceImageWorkspacePath(currentStyle)) {
                    styleHelper.refreshStyle(style);
                } else {
                    styleHelper.setAndRefreshStyle(node, currentStyle, styleHelper.createStyle(bestStyleDescription));
                }
            } else {
                styleHelper.refreshStyle(style);
            }
        } else {
            styleHelper.setAndRefreshStyle(node, null, styleHelper.createStyle(bestStyleDescription));
        }

        this.interpreter.unSetVariable(IInterpreterSiriusVariables.VIEW);
        this.interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
        this.interpreter.unSetVariable(IInterpreterSiriusVariables.VIEWPOINT);
        if (node.eContainer() != null) {
            this.interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER_VIEW);
            if (node.eContainer() instanceof DSemanticDecorator) {
                this.interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER);
            }
        }
    }

    private boolean isCustomizedWorkspaceImageWorkspacePath(Style style) {
        boolean isCustomizedWorkspaceImageWorkspacePath = false;
        if (style instanceof WorkspaceImage) {
            WorkspaceImage workspaceImage = (WorkspaceImage) style;
            isCustomizedWorkspaceImageWorkspacePath = workspaceImage.getCustomFeatures().contains(DiagramPackage.Literals.WORKSPACE_IMAGE__WORKSPACE_PATH.getName());
        }
        return isCustomizedWorkspaceImageWorkspacePath;
    }

    /**
     * 
     * @param bestStyleDescription
     *            the description
     * @param style
     *            the current style
     * @param bestStyle
     *            the best style
     * @return if the description is the same type as the style
     */
    private boolean isSameDescription(StyleDescription bestStyleDescription, Style style, Style bestStyle) {
        return bestStyleDescription == style.getDescription() && bestStyle.eClass().equals(style.eClass());
    }

    /**
     * Refreshes an edge style.
     * 
     * @param edge
     *            Edge which style is to be refreshed.
     * @param mapping
     *            Mapping that is to be used to compute the best style for this node.
     * @param parentDiagram
     *            the parent diagram of the edge
     */
    public void refreshStyle(final DEdge edge, final EdgeMapping mapping, final DDiagram parentDiagram) {
        this.interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, this.diagram);
        this.interpreter.setVariable(IInterpreterSiriusVariables.VIEW, edge);
        this.interpreter.setVariable(IInterpreterSiriusVariables.SOURCE_VIEW, edge.getSourceNode());
        this.interpreter.setVariable(IInterpreterSiriusVariables.TARGET_VIEW, edge.getTargetNode());

        EObject containerVariable = null;
        if (edge.eContainer() instanceof DSemanticDecorator) {
            containerVariable = ((DSemanticDecorator) edge.eContainer()).getTarget();
        }
        StyleDescription bestStyleDescription = null;
        if (edge.getTarget() != null) {
            bestStyleDescription = this.mappingHelper.getBestStyleDescription(mapping, edge.getTarget(), edge, containerVariable, parentDiagram);
        }
        if (bestStyleDescription != null && bestStyleDescription != edge.getStyle().getDescription()) {
            final Style currentStyle = edge.getStyle();
            styleHelper.setAndRefreshStyle(edge, currentStyle, styleHelper.createStyle(bestStyleDescription));
        } else if (edge.getStyle() != null) {
            styleHelper.refreshStyle(edge.getStyle());
        }

        this.interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
        this.interpreter.unSetVariable(IInterpreterSiriusVariables.VIEW);
        this.interpreter.unSetVariable(IInterpreterSiriusVariables.SOURCE_VIEW);
        this.interpreter.unSetVariable(IInterpreterSiriusVariables.TARGET_VIEW);
    }

    /**
     * Creates a node style.
     * 
     * @param node
     *            node to refresh.
     * @param mapping
     *            mapping to use to compute the style.
     */
    public void createStyle(final AbstractDNode node, final AbstractNodeMapping mapping) {
        EObject containerVariable = null;
        if (node.eContainer() != null) {
            this.interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER_VIEW, node.eContainer());
            if (node.eContainer() instanceof DSemanticDecorator) {
                containerVariable = ((DSemanticDecorator) node.eContainer()).getTarget();
                this.interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER, containerVariable);
            }
        }
        this.interpreter.setVariable(IInterpreterSiriusVariables.VIEWPOINT, this.diagram);
        this.interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, this.diagram);
        this.interpreter.setVariable(IInterpreterSiriusVariables.VIEW, node);

        this.mappingHelper.affectAndRefreshStyle(mapping, node, node.getTarget(), containerVariable, diagram);

        if (node.eContainer() != null) {
            this.interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER_VIEW);
            if (node.eContainer() instanceof DSemanticDecorator) {
                this.interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER);
            }
        }
        this.interpreter.unSetVariable(IInterpreterSiriusVariables.VIEW);
        this.interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
        this.interpreter.unSetVariable(IInterpreterSiriusVariables.VIEWPOINT);
    }

    /**
     * Creates an edge style.
     * 
     * @param edge
     *            edge to refresh.
     * 
     * @param mapping
     *            mapping to use to compute the new style.
     * @param parentDiagram
     *            the parent diagram of the edge
     */
    public void createStyle(final DEdge edge, final EdgeMapping mapping, final DDiagram parentDiagram) {
        this.interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, this.diagram);
        this.interpreter.setVariable(IInterpreterSiriusVariables.VIEW, edge);
        this.interpreter.setVariable(IInterpreterSiriusVariables.SOURCE_VIEW, edge.getSourceNode());
        this.interpreter.setVariable(IInterpreterSiriusVariables.TARGET_VIEW, edge.getTargetNode());

        if (edge.getStyle() == null || edge.getStyle().getCustomFeatures().isEmpty()) {
            EObject containerVariable = null;
            if (edge.eContainer() instanceof DSemanticDecorator) {
                containerVariable = ((DSemanticDecorator) edge.eContainer()).getTarget();
            }
            this.mappingHelper.affectAndRefreshStyle(mapping, edge, edge.getTarget(), containerVariable, parentDiagram);
        } else {
            if (edge.getStyle().getCustomFeatures().isEmpty()) {
                Option<EdgeStyle> noPreviousStyle = Options.newNone();
                styleHelper.refreshStyle(edge.getOwnedStyle(), noPreviousStyle);
            } else {
                styleHelper.refreshStyle(edge.getOwnedStyle(), Options.newSome(edge.getOwnedStyle()));
            }
        }

        this.interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
        this.interpreter.unSetVariable(IInterpreterSiriusVariables.VIEW);
        this.interpreter.unSetVariable(IInterpreterSiriusVariables.SOURCE_VIEW);
        this.interpreter.unSetVariable(IInterpreterSiriusVariables.TARGET_VIEW);
    }

    private String computeLabel(final DDiagramElement view, final EObject descriptionObject) {
        String result = IInterpreterMessages.DEFAULT_NAME_ON_FACTORY_EXCEPTION;

        if (descriptionObject instanceof BasicLabelStyleDescription) {
            result = DiagramElementMappingHelper.computeLabel(view, (BasicLabelStyleDescription) descriptionObject, this.diagram, interpreter);
        }

        return result;
    }

    private String computeTooltip(final DSemanticDecorator view, final EObject descriptionObject) {
        String result = StylePackage.eINSTANCE.getTooltipStyleDescription_TooltipExpression().getDefaultValueLiteral();

        if (descriptionObject instanceof TooltipStyleDescription) {
            String tooltipExpression = ((TooltipStyleDescription) descriptionObject).getTooltipExpression();

            try {
                interpreter.setVariable(IInterpreterSiriusVariables.VIEW, view);
                result = interpreter.evaluateString(view.getTarget(), tooltipExpression);
            } catch (final EvaluationException e) {
                RuntimeLoggerManager.INSTANCE.error(descriptionObject, StylePackage.eINSTANCE.getTooltipStyleDescription_TooltipExpression(), e);
            } finally {
                interpreter.unSetVariable(IInterpreterSiriusVariables.VIEW);
            }
        }

        return result;
    }

    /**
     * Create the edge path if needed path.
     * 
     * @param edge
     *            edge to update.
     * @param mapping
     *            the edge mapping used for the path computing.
     * @param mappingsToEdgeTargets
     *            map for the Mapping eobject to view elements.
     */
    public void updatePath(final DEdge edge, final EdgeMapping mapping, final Map<DiagramElementMapping, Collection<EdgeTarget>> mappingsToEdgeTargets) {
        if (mapping.getPathExpression() != null && !StringUtil.isEmpty(mapping.getPathExpression()) && mapping.getPathNodeMapping() != null) {

            List<EObject> pathSemanticCandidates = Collections.<EObject> emptyList();
            // variables.
            interpreter.setVariable(IInterpreterSiriusVariables.VIEWPOINT, diagram);
            interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, diagram);
            interpreter.setVariable(IInterpreterSiriusVariables.ELEMENT, edge.getTarget());
            if (edge.getSourceNode() instanceof DSemanticDecorator) {
                interpreter.setVariable(IInterpreterSiriusVariables.SOURCE, ((DSemanticDecorator) edge.getSourceNode()).getTarget());
            }
            if (edge.getTargetNode() instanceof DSemanticDecorator) {
                interpreter.setVariable(IInterpreterSiriusVariables.TARGET, ((DSemanticDecorator) edge.getTargetNode()).getTarget());
            }

            try {
                pathSemanticCandidates = new ArrayList<EObject>(interpreter.evaluateCollection(edge.getTarget(), mapping.getPathExpression()));
            } catch (final EvaluationException e) {
                RuntimeLoggerManager.INSTANCE.error(mapping, DescriptionPackage.eINSTANCE.getEdgeMapping_PathExpression(), e);
            } finally {
                interpreter.unSetVariable(IInterpreterSiriusVariables.VIEWPOINT);
                interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
                interpreter.unSetVariable(IInterpreterSiriusVariables.ELEMENT);
                interpreter.unSetVariable(IInterpreterSiriusVariables.SOURCE);
                interpreter.unSetVariable(IInterpreterSiriusVariables.TARGET);
            }

            final Map<EObject, EdgeTarget> availableElements = getAvailableElements(mappingsToEdgeTargets, mapping.getPathNodeMapping());

            final Collection<EObject> semanticAvailableElements = availableElements.keySet();

            EList<EdgeTarget> newPath = new BasicEList<EdgeTarget>();

            for (final EObject pathSemanticCandidate : pathSemanticCandidates) {
                if (semanticAvailableElements.contains(pathSemanticCandidate)) {
                    newPath.add(availableElements.get(pathSemanticCandidate));
                }
            }

            if (!newPath.isEmpty() && !edge.getPath().equals(newPath)) {
                edge.getPath().clear();
                edge.getPath().addAll(newPath);
            }
        }
    }

    private Map<EObject, EdgeTarget> getAvailableElements(final Map<DiagramElementMapping, Collection<EdgeTarget>> mappingsToEdgeTargets, final Collection<AbstractNodeMapping> mappings) {
        final Map<EObject, EdgeTarget> semanticToEdgeTargets = new HashMap<EObject, EdgeTarget>();
        for (final DiagramElementMapping availableMapping : mappings) {
            final Collection<EdgeTarget> edgeTargets = mappingsToEdgeTargets.get(availableMapping);
            if (edgeTargets != null) {
                for (final EdgeTarget edgeTarget : edgeTargets) {
                    if (edgeTarget instanceof DSemanticDecorator) {
                        semanticToEdgeTargets.put(((DSemanticDecorator) edgeTarget).getTarget(), edgeTarget);
                    }
                }
            }
        }
        return semanticToEdgeTargets;
    }

    private void refreshSemanticElements(final DDiagramElement element, final DiagramElementMapping mapping) {
        DiagramElementMappingHelper.refreshSemanticElements(mapping, element, this.interpreter);
    }

    /**
     * Check if the target of this decorator is not null and is in a eResource.
     * 
     * @param decorator
     *            The decorator to check
     * @return true if the target is OK, false otherwise.
     */
    private boolean hasSafeTarget(DSemanticDecorator semDec) {
        EObject target = semDec.getTarget();
        return target != null && (target.eContainer() != null || target.eResource() != null);
    }
}
