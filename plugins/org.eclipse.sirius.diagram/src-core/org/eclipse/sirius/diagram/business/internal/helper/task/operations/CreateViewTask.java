/*******************************************************************************
 * Copyright (c) 2008, 2017 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.business.internal.helper.task.operations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.helper.task.ICommandTask;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.internal.helper.task.operations.AbstractOperationTask;
import org.eclipse.sirius.business.internal.helper.task.operations.InterpretedExpressionVariableTask;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.RefreshIdsHolder;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.ArrangeConstraint;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.Messages;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManagerRegistry;
import org.eclipse.sirius.diagram.business.api.helper.display.DisplayMode;
import org.eclipse.sirius.diagram.business.api.helper.display.DisplayService;
import org.eclipse.sirius.diagram.business.api.helper.display.DisplayServiceManager;
import org.eclipse.sirius.diagram.business.api.helper.graphicalfilters.HideFilterHelper;
import org.eclipse.sirius.diagram.business.api.query.DDiagramQuery;
import org.eclipse.sirius.diagram.business.api.query.DiagramElementMappingQuery;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.business.internal.experimental.sync.DNodeCandidate;
import org.eclipse.sirius.diagram.business.internal.experimental.sync.DDiagramElementSynchronizer;
import org.eclipse.sirius.diagram.business.internal.experimental.sync.DDiagramSynchronizer;
import org.eclipse.sirius.diagram.business.internal.experimental.sync.DEdgeCandidate;
import org.eclipse.sirius.diagram.business.internal.helper.decoration.DecorationHelperInternal;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.operations.AbstractNodeMappingSpecOperations;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.MappingBasedDecoration;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.tool.CreateEdgeView;
import org.eclipse.sirius.diagram.description.tool.CreateView;
import org.eclipse.sirius.diagram.description.tool.ToolPackage;
import org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramPreferencesKeys;
import org.eclipse.sirius.diagram.tools.api.refresh.BestMappingGetter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.MetaClassNotFoundException;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.SemanticBasedDecoration;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;

/**
 * Create a view according to a specific CreateView model element.
 * 
 * @author Yann Mortier (ymortier)
 */
public class CreateViewTask extends AbstractOperationTask {

    private final CreateView createViewOp;

    /**
     * Create a new {@link CreateViewTask}.
     * 
     * @param extPackage
     *            the extended package.
     * @param context
     *            the current context.
     * @param createViewOp
     *            the create view operation
     * @param interpreter
     *            the {@link IInterpreter} to be used
     */
    public CreateViewTask(final CommandContext context, final ModelAccessor extPackage, final CreateView createViewOp, final IInterpreter interpreter) {
        super(context, extPackage, interpreter);
        this.createViewOp = createViewOp;
    }

    @Override
    public String getLabel() {
        return Messages.CreateViewTask_label;
    }

    @Override
    public void execute() throws MetaClassNotFoundException, FeatureNotFoundException {
        DSemanticDecorator containerView = evaluateContainerViewExpression();
        DSemanticDiagram parentDDiagram = getDSemanticDiagram(containerView);
        Session session = new EObjectQuery(parentDDiagram).getSession();
        DDiagramElement createView = createView(parentDDiagram, containerView, session);
        if (createView != null) {
            initCreatedViewVariable(createView);
            setInitialVisibility(parentDDiagram, createView);
        }
    }

    private DSemanticDecorator evaluateContainerViewExpression() {
        DSemanticDecorator containerView = null;
        EObject evaluation = evalueExpression(ToolPackage.eINSTANCE.getCreateView_ContainerViewExpression());
        if (evaluation instanceof DSemanticDecorator) {
            containerView = (DSemanticDecorator) evaluation;
        }
        return containerView;
    }

    private EObject evalueExpression(EAttribute expressionEAttribute) {
        EObject evaluation = RuntimeLoggerManager.INSTANCE.decorate(interpreter).evaluateEObject(context.getCurrentTarget(), createViewOp, expressionEAttribute);
        return evaluation;
    }

    private DSemanticDiagram getDSemanticDiagram(DSemanticDecorator containerView) {
        DSemanticDiagram dSemanticDiagram = null;
        if (containerView instanceof DSemanticDiagram) {
            dSemanticDiagram = (DSemanticDiagram) containerView;
        } else if (containerView instanceof DDiagramElement) {
            dSemanticDiagram = (DSemanticDiagram) ((DDiagramElement) containerView).getParentDiagram();
        }
        return dSemanticDiagram;
    }

    private DDiagramElement createView(DSemanticDiagram parentDDiagram, DSemanticDecorator containerView, Session session) {
        DDiagramElement createdView = null;
        EObject semanticElt = context.getCurrentTarget();
        if (createViewOp instanceof CreateEdgeView) {
            CreateEdgeView createEdgeView = (CreateEdgeView) createViewOp;
            createdView = createEdgeView(parentDDiagram, createEdgeView, session);
        } else if (containerView != null) {
            BestMappingGetter bestMappingGetter = new BestMappingGetter(containerView, semanticElt);
            DiagramElementMapping bestMapping = createViewOp.getMapping();
            if (bestMapping instanceof NodeMapping) {
                bestMapping = bestMappingGetter.getBestNodeMapping(Collections.singletonList((NodeMapping) createViewOp.getMapping()));
            } else if (bestMapping instanceof ContainerMapping) {
                bestMapping = bestMappingGetter.getBestContainerMapping(Collections.singletonList((ContainerMapping) createViewOp.getMapping()));
            }
            if (bestMapping == null) {
                bestMapping = createViewOp.getMapping();
            }

            if (bestMapping instanceof AbstractNodeMapping) {
                AbstractNodeMapping abstractNodeMapping = (AbstractNodeMapping) bestMapping;
                if (extPackage.eInstanceOf(semanticElt, abstractNodeMapping.getDomainClass())) {
                    DNodeCandidate abstractDNodeCandidate = new DNodeCandidate(abstractNodeMapping, semanticElt, (DragAndDropTarget) containerView,
                            RefreshIdsHolder.getOrCreateHolder(parentDDiagram));
                    DDiagramElementSynchronizer dDiagramElementSynchronizer = new DDiagramElementSynchronizer(parentDDiagram, interpreter, extPackage);
                    DiagramMappingsManager mappingManager = DiagramMappingsManagerRegistry.INSTANCE.getDiagramMappingsManager(session, parentDDiagram);
                    AbstractDNode createdAbstractDNode = dDiagramElementSynchronizer.createNewNode(mappingManager, abstractDNodeCandidate,
                            abstractNodeMapping.eContainingFeature() == DescriptionPackage.Literals.ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS);
                    AbstractNodeMappingSpecOperations.createBorderingNodes(abstractNodeMapping, semanticElt, createdAbstractDNode, Collections.emptyList(), parentDDiagram);
                    createdView = createdAbstractDNode;
                    if (isAutoPinOnCreateEnabled()) {
                        createdAbstractDNode.getArrangeConstraints().add(ArrangeConstraint.KEEP_LOCATION);
                        createdAbstractDNode.getArrangeConstraints().add(ArrangeConstraint.KEEP_RATIO);
                        createdAbstractDNode.getArrangeConstraints().add(ArrangeConstraint.KEEP_SIZE);
                    }
                }
            }
        }
        return createdView;
    }

    private void initCreatedViewVariable(DDiagramElement createView) throws MetaClassNotFoundException, FeatureNotFoundException {
        if (!StringUtil.isEmpty(createViewOp.getVariableName())) {
            ICommandTask childTask = new InterpretedExpressionVariableTask(context, extPackage, InterpretedExpressionVariableTask.KIND_SET, createViewOp.getVariableName(), createView, interpreter);
            childTask.execute();
        }
    }

    private void setInitialVisibility(final DDiagram diagram, final DDiagramElement diagramElement) {
        final DisplayService service = DisplayServiceManager.INSTANCE.getDisplayService(DisplayMode.CREATION);
        if (service != null && diagramElement != null && diagram != null) {
            Session session = SessionManager.INSTANCE.getSession(diagramElement.getTarget());
            DiagramMappingsManager mappingManager = DiagramMappingsManagerRegistry.INSTANCE.getDiagramMappingsManager(session, diagram);
            diagramElement.setVisible(service.computeVisibility(mappingManager, diagram, diagramElement));
            if (!service.computeLabelVisibility(diagram, diagramElement)) {
                HideFilterHelper.INSTANCE.hideLabel(diagramElement);
            }
        }
    }

    /**
     * Create a {@link DEdge}.
     * 
     * @param parentDDiagram
     *            the {@link DDiagram} which must own the created {@link DEdge}
     * 
     * @param createEdgeView
     *            the {@link CreateEdgeView} to use to create the {@link DEdge}
     * @param session
     * @return the created {@link DEdge}
     */
    private DEdge createEdgeView(DSemanticDiagram parentDDiagram, CreateEdgeView createEdgeView, Session session) {
        DEdge createdDEdge = null;

        DDiagramSynchronizer dDiagramSynchronizer = new DDiagramSynchronizer(interpreter, parentDDiagram.getDescription(), extPackage);
        dDiagramSynchronizer.setDiagram(parentDDiagram);
        dDiagramSynchronizer.setTool(true);
        DDiagramElementSynchronizer dDiagramElementSynchronizer = dDiagramSynchronizer.getElementSynchronizer();
        /* maps for decorations */
        Map<EdgeMapping, Collection<MappingBasedDecoration>> edgeToMappingBasedDecoration = new HashMap<EdgeMapping, Collection<MappingBasedDecoration>>();
        Map<String, Collection<SemanticBasedDecoration>> edgeToSemanticBasedDecoration = new HashMap<String, Collection<SemanticBasedDecoration>>();

        /* create the mapping to edge targets map */
        Map<DiagramElementMapping, Collection<EdgeTarget>> mappingsToEdgeTargets = dDiagramElementSynchronizer.computeMappingsToEdgeTargets(session.getSelectedViewpoints(false));
        new DecorationHelperInternal(parentDDiagram, interpreter, extPackage).computeDecorations(mappingsToEdgeTargets, edgeToSemanticBasedDecoration, edgeToMappingBasedDecoration);

        EObject source = evalueExpression(ToolPackage.eINSTANCE.getCreateEdgeView_SourceExpression());
        if (source == null) {
            source = context.getCurrentTarget();
        }
        EObject target = evalueExpression(ToolPackage.eINSTANCE.getCreateEdgeView_TargetExpression());
        if (target == null) {
            target = context.getCurrentTarget();
        }
        AbstractToolDescription tool = getAbstractToolDescription();
        EdgeMapping bestEdgeMapping = (EdgeMapping) createEdgeView.getMapping();
        boolean needsPathUpdate = needsPathUpdate(bestEdgeMapping, tool);
        DiagramMappingsManager diagramMappingsManager = DiagramMappingsManagerRegistry.INSTANCE.getDiagramMappingsManager(session, parentDDiagram);
        Collection<DiagramElementMapping> sourceMappingAndTheirMappingImports = getAbstractNodeMappingImports(diagramMappingsManager, bestEdgeMapping.getSourceMapping());
        sourceMappingAndTheirMappingImports.addAll(bestEdgeMapping.getSourceMapping());
        Collection<DiagramElementMapping> targetMappingAndTheirMappingImports = getAbstractNodeMappingImports(diagramMappingsManager, bestEdgeMapping.getTargetMapping());
        targetMappingAndTheirMappingImports.addAll(bestEdgeMapping.getTargetMapping());
        Collection<DDiagramElement> sourceViews = getNodes(source, parentDDiagram, sourceMappingAndTheirMappingImports);
        Collection<DDiagramElement> targetViews = getNodes(target, parentDDiagram, targetMappingAndTheirMappingImports);
        for (DDiagramElement sourceView : sourceViews) {
            if (sourceView instanceof EdgeTarget) {
                EdgeTarget sourceEdgeTarget = (EdgeTarget) sourceView;
                for (DDiagramElement targetView : targetViews) {
                    if (targetView instanceof EdgeTarget) {
                        EdgeTarget targetEdgeTarget = (EdgeTarget) targetView;
                        BestMappingGetter bestMappingGetter = new BestMappingGetter(sourceEdgeTarget, targetEdgeTarget, context.getCurrentTarget());
                        EdgeMapping edgeMapping = bestMappingGetter.getBestEdgeMapping(Collections.singletonList((EdgeMapping) createViewOp.getMapping()));
                        if (edgeMapping == null && createEdgeView.getMapping() instanceof EdgeMapping) {
                            edgeMapping = (EdgeMapping) createEdgeView.getMapping();
                        }
                        DEdgeCandidate dEdgeCandidate = new DEdgeCandidate(edgeMapping, context.getCurrentTarget(), sourceEdgeTarget, targetEdgeTarget,
                                RefreshIdsHolder.getOrCreateHolder(parentDDiagram));
                        DiagramMappingsManager mappingManager = DiagramMappingsManagerRegistry.INSTANCE.getDiagramMappingsManager(session, parentDDiagram);
                        createdDEdge = dDiagramElementSynchronizer.createNewEdge(mappingManager, dEdgeCandidate, mappingsToEdgeTargets, edgeToMappingBasedDecoration, edgeToSemanticBasedDecoration);
                        if (needsPathUpdate) {
                            dDiagramElementSynchronizer.updatePath(createdDEdge, edgeMapping, mappingsToEdgeTargets);
                        }
                    }
                }
            }
        }
        return createdDEdge;

    }

    private Collection<DiagramElementMapping> getAbstractNodeMappingImports(DiagramMappingsManager diagramMappingsManager, Collection<DiagramElementMapping> mappings) {
        Collection<DiagramElementMapping> mappingImports = new ArrayList<DiagramElementMapping>();
        for (DiagramElementMapping mapping : mappings) {
            Collection<DiagramElementMapping> allAbstractNodeMappings = new ArrayList<DiagramElementMapping>(diagramMappingsManager.getNodeMappings());
            allAbstractNodeMappings.addAll(diagramMappingsManager.getContainerMappings());
            for (DiagramElementMapping diagramElementMapping : allAbstractNodeMappings) {
                if (new DiagramElementMappingQuery(diagramElementMapping).areInSameHiearchy(mapping)) {
                    mappingImports.add(diagramElementMapping);
                    break;
                }
            }
        }
        return mappingImports;
    }

    private boolean needsPathUpdate(final EdgeMapping edgeMapping, final AbstractToolDescription tool) {
        if (edgeMapping != null && tool != null) {
            return !tool.isForceRefresh() && edgeMapping.getPathExpression() != null && !StringUtil.isEmpty(edgeMapping.getPathExpression()) && edgeMapping.getPathNodeMapping() != null;
        }
        return false;
    }

    private AbstractToolDescription getAbstractToolDescription() {
        if (this.createViewOp != null) {
            EObject tmp = this.createViewOp;
            while (tmp.eContainer() != null) {
                if (tmp instanceof AbstractToolDescription) {
                    return (AbstractToolDescription) tmp;
                }
                tmp = tmp.eContainer();
            }

        }
        return null;
    }

    private Collection<DDiagramElement> getNodes(final EObject semanticElement, final DSemanticDiagram dSemanticDiagram, final Collection<DiagramElementMapping> mappings) {
        Set<DDiagramElement> result = new HashSet<DDiagramElement>();
        if (semanticElement != null) {
            for (DDiagramElement dDiagramElement : new DDiagramQuery(dSemanticDiagram).getAllDiagramElements()) {
                if (mappings.contains(dDiagramElement.getMapping()) && semanticElement.equals(dDiagramElement.getTarget())) {
                    result.add(dDiagramElement);
                }
            }
        }
        return result;
    }

    private boolean isAutoPinOnCreateEnabled() {
        return Platform.getPreferencesService().getBoolean(DiagramPlugin.ID, SiriusDiagramPreferencesKeys.PREF_AUTO_PIN_ON_CREATE.name(), false, null);
    }
}
