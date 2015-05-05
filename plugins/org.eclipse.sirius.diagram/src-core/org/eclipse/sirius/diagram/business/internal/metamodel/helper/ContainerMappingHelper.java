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
package org.eclipse.sirius.diagram.business.internal.metamodel.helper;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.EObjectCouple;
import org.eclipse.sirius.common.tools.api.util.RefreshIdsHolder;
import org.eclipse.sirius.diagram.ContainerStyle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.business.api.query.ContainerMappingQuery;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.extensions.IContainerMappingExt;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.operations.SiriusElementMappingSpecOperations;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.style.ContainerStyleDescription;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.description.style.StylePackage;

import com.google.common.collect.Sets;

/**
 * Utility class to factor customizations for ContainerMapping and related.
 * 
 * @author pcdavid
 */
public final class ContainerMappingHelper {

    private IInterpreter interpreter;

    /**
     * Create the helper.
     * 
     * @param interpreter
     *            interpreter used to evaluate expressions.
     */
    public ContainerMappingHelper(IInterpreter interpreter) {
        this.interpreter = interpreter;
    }

    /**
     * Helper for {@link ContainerMapping#getAllNodeMappings()}. The result
     * should be wrapped in an appropriate EList by users.
     * 
     * @param self
     *            the container mapping.
     * @return the node mappings.
     */
    public static Collection<NodeMapping> getAllNodeMappings(ContainerMapping self) {
        return Sets.union(Sets.newLinkedHashSet(self.getSubNodeMappings()), Sets.newLinkedHashSet(self.getReusedNodeMappings()));
    }

    /**
     * Helper for {@link ContainerMapping#getAllContainerMappings()}. The result
     * should be wrapped in an appropriate EList by users.
     * 
     * @param self
     *            the container mapping.
     * @return the container mappings.
     */
    public static Collection<ContainerMapping> getAllContainerMappings(ContainerMapping self) {
        return Sets.union(Sets.newLinkedHashSet(self.getSubContainerMappings()), Sets.newLinkedHashSet(self.getReusedContainerMappings()));
    }

    /**
     * Implementation of {@link ContainerMapping#clearDNodesDone()}.
     * 
     * @param self
     *            the container mapping.
     */
    public static void clearDNodesDone(IContainerMappingExt self) {
        self.getViewContainerDone().clear();
        self.getCandidatesCache().clear();
    }

    /**
     * Implementation of {@link ContainerMapping#findDNodeFromEObject(EObject)}.
     * 
     * @param self
     *            the container mapping.
     * @param object
     *            the semantic element.
     * @return the node that has been created by this mapping and the specified
     *         EObject as semantic element.
     */
    public static EList<DDiagramElement> findDNodeFromEObject(IContainerMappingExt self, EObject object) {
        EList result = self.getViewContainerDone().get(object);
        if (result == null) {
            result = new BasicEList<DDiagramElement>();
        }
        return result;
    }

    /**
     * Implementation of
     * {@link ContainerMapping#addDoneNode(DSemanticDecorator)}.
     * 
     * @param self
     *            the container mapping.
     * @param node
     *            the node to add.
     */
    public static void addDoneNode(IContainerMappingExt self, DSemanticDecorator node) {
        EList<DSemanticDecorator> list = self.getViewContainerDone().get(node.getTarget());
        if (list == null) {
            list = new BasicEList<DSemanticDecorator>();
            self.getViewContainerDone().put(node.getTarget(), list);
        }
        list.add(node);
    }

    /**
     * Implementation of
     * {@link ContainerMapping#getNodesCandidates(EObject, EObject, EObject)}.
     * 
     * @param self
     *            the container mapping.
     * @param semanticOrigin
     *            the origin of the computation.
     * @param container
     *            the semantic element (DSemanticDecorator.getTarget()) of the
     *            DDiagramElement (or DDiagramElementContainer or DDiagram) that
     *            contains the containers.
     * @param containerView
     *            the DDiagramElement (or DDiagramElementContainer or DDiagram)
     *            that contains the containers.
     * @return all semantic candidates for this mapping. It checks the
     *         DiagramElementMapping.getPreconditionExpression() and return all
     *         objects that satisfied the expression.
     */
    public static EList<EObject> getNodesCandidates(IContainerMappingExt self, EObject semanticOrigin, EObject container, EObject containerView) {
        DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.GET_CONTAINER_CANDIDATES_KEY);
        //
        // find the parent viewpoint.
        DDiagram parentVp = null;
        if (containerView instanceof DDiagramElement) {
            parentVp = ((DDiagramElement) containerView).getParentDiagram();
        }
        //
        // The container variable.
        EObject safeContainer;
        if (container == null) {
            safeContainer = semanticOrigin;
        } else {
            safeContainer = container;
        }
        final EObjectCouple couple = new EObjectCouple(semanticOrigin, safeContainer, RefreshIdsHolder.getOrCreateHolder(parentVp));
        EList<EObject> result = self.getCandidatesCache().get(couple);
        if (result == null) {
            result = new UniqueEList<EObject>();
            Iterator<EObject> it;
            it = DiagramElementMappingHelper.getSemanticIterator(self, semanticOrigin, parentVp);
            if (self.getDomainClass() != null) {
                while (it.hasNext()) {
                    final EObject eObj = it.next();
                    if (ContainerMappingHelper.isInstanceOf(eObj, self.getDomainClass()) && SiriusElementMappingSpecOperations.checkPrecondition(self, eObj, safeContainer, containerView)) {
                        result.add(eObj);
                    }
                }
            } else {
                SiriusPlugin.getDefault().error("Error creating nodes : domain class is not defined on a mapping", new RuntimeException());
            }
            self.getCandidatesCache().put(couple, result);
        }
        DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.GET_CONTAINER_CANDIDATES_KEY);
        return result;
    }

    /**
     * Implementation of
     * {@link ContainerMapping#createContainer(EObject, EObject, DDiagram)}.
     * 
     * @param self
     *            the container mapping.
     * @param modelElement
     *            the semantic model element.
     * @param container
     *            the semantic container.
     * @param dDiagram
     *            the viewpoint element.
     * @return the created container.
     */
    public DDiagramElementContainer createContainer(IContainerMappingExt self, EObject modelElement, EObject container, DDiagram dDiagram) {

        DDiagramElementContainer newContainer = null;
        if (new ContainerMappingQuery(self).isListContainer()) {
            newContainer = DiagramFactory.eINSTANCE.createDNodeList();
        } else {
            // Other behaviors : ContainerLayout.FreeForm/VerticalStack
            newContainer = DiagramFactory.eINSTANCE.createDNodeContainer();
            DNodeContainer nodeContainer = DiagramFactory.eINSTANCE.createDNodeContainer();
            nodeContainer.setChildrenPresentation(self.getChildrenPresentation());
            newContainer = nodeContainer;
        }

        // getting the right style description : default or conditional
        final ContainerStyleDescription style = (ContainerStyleDescription) new MappingHelper(interpreter).getBestStyleDescription(self, modelElement, newContainer, container, dDiagram);

        newContainer.setTarget(modelElement);
        newContainer.setActualMapping(self);

        DiagramElementMappingHelper.refreshSemanticElements(self, newContainer, interpreter);

        interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, dDiagram);
        interpreter.setVariable(IInterpreterSiriusVariables.VIEW, newContainer);
        if (style != null && style.getLabelExpression() != null) {
            String name;
            try {
                name = interpreter.evaluateString(modelElement, style.getLabelExpression());
                newContainer.setName(name);
            } catch (final EvaluationException e) {
                RuntimeLoggerManager.INSTANCE.error(style, StylePackage.eINSTANCE.getBasicLabelStyleDescription_LabelExpression(), e);
            }
        }

        /*
         * handling style
         */
        final ContainerStyle containerStyle = (ContainerStyle) new MappingHelper(interpreter).getBestStyle(self, modelElement, newContainer, container, dDiagram);
        if (containerStyle != null) {
            newContainer.setOwnedStyle(containerStyle);
        }
        if (newContainer.getOwnedStyle() != null) {
            Option<ContainerStyle> noPreviousStyle = Options.newNone();
            new StyleHelper(interpreter).refreshStyle(newContainer.getOwnedStyle(), noPreviousStyle);
        }
        self.addDoneNode(newContainer);

        interpreter.unSetVariable(IInterpreterSiriusVariables.VIEW);
        interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);

        /*
         * Iterate over the border mapping to initialize the border nodes.
         */
        self.createBorderingNodes(modelElement, newContainer, Collections.EMPTY_LIST, dDiagram);

        return newContainer;
    }

    /**
     * Implementation of
     * {@link ContainerMapping#updateContainer(DDiagramElementContainer)}.
     * 
     * @param self
     *            the container mapping.
     * @param container
     *            the container to update.
     */
    public void updateContainer(IContainerMappingExt self, DDiagramElementContainer container) {

        // getting the right style description : default or conditional
        final DSemanticDecorator cContainer = (DSemanticDecorator) container.eContainer();
        ContainerStyleDescription style = null;

        if (cContainer != null) {
            style = (ContainerStyleDescription) new MappingHelper(interpreter).getBestStyleDescription(self, container.getTarget(), container, container, container.getParentDiagram());
        }

        interpreter.setVariable(IInterpreterSiriusVariables.VIEW, container);
        if (style != null && style.getLabelExpression() != null) {

            try {
                final String name = interpreter.evaluateString(container.getTarget(), style.getLabelExpression());
                container.setName(name);
            } catch (final EvaluationException e) {
                RuntimeLoggerManager.INSTANCE.error(style, StylePackage.eINSTANCE.getBasicLabelStyleDescription_LabelExpression(), e);
            }
        }

        if (style != null && style.getTooltipExpression() != null) {
            try {
                interpreter.setVariable(IInterpreterSiriusVariables.VIEW, container);
                final String tooltip = interpreter.evaluateString(container.getTarget(), style.getTooltipExpression());
                container.setTooltipText(tooltip);
            } catch (final EvaluationException e) {
                RuntimeLoggerManager.INSTANCE.error(style, StylePackage.eINSTANCE.getTooltipStyleDescription_TooltipExpression(), e);
            } finally {
                interpreter.unSetVariable(IInterpreterSiriusVariables.VIEW);
            }
        }
        interpreter.unSetVariable(IInterpreterSiriusVariables.VIEW);

        // semantic elements
        DiagramElementMappingHelper.refreshSemanticElements(self, container, interpreter);

        /*
         * handling style
         */
        EObject containerVariable = null;
        if (container.eContainer() instanceof DSemanticDecorator) {
            containerVariable = ((DSemanticDecorator) container.eContainer()).getTarget();
        }
        final Style currentStyle = container.getStyle();
        final Style bestStyle = new MappingHelper(interpreter).getBestStyle(self, container.getTarget(), container, containerVariable, container.getParentDiagram());

        StyleHelper sHelper = new StyleHelper(interpreter);
        if (currentStyle == null) {
            sHelper.setAndRefreshStyle(container, null, bestStyle);
        } else if (currentStyle.getCustomFeatures().isEmpty()) {
            if (currentStyle.getDescription() != bestStyle.getDescription() || !currentStyle.equals(bestStyle)) {
                sHelper.setAndRefreshStyle(container, currentStyle, bestStyle);
            } else {
                sHelper.refreshStyle(currentStyle);
            }
        }

        self.addDoneNode(container);
    }

    /**
     * Implementation of {@link ContainerMapping#getAllMappings()}.
     * 
     * @param self
     *            the container mapping.
     * @return all sub mappings.
     */
    public static EList<DiagramElementMapping> getAllMappings(ContainerMapping self) {
        final BasicEList<DiagramElementMapping> allMappings = new BasicEList<DiagramElementMapping>();
        allMappings.addAll(self.getAllContainerMappings());
        allMappings.addAll(self.getAllNodeMappings());
        allMappings.addAll(self.getAllBorderedNodeMappings());
        return new BasicEList.UnmodifiableEList<DiagramElementMapping>(allMappings.size(), allMappings.toArray());
    }

    private static boolean isInstanceOf(EObject eObj, final String typename) {
        return SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(eObj).eInstanceOf(eObj, typename);
    }
}
