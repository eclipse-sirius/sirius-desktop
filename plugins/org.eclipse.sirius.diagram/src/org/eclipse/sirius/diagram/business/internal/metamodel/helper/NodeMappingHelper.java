/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.metamodel.helper;

import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.NodeStyle;
import org.eclipse.sirius.diagram.ResizeKind;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.operations.AbstractNodeMappingSpecOperations;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.style.NodeStyleDescription;
import org.eclipse.sirius.diagram.model.business.internal.description.extensions.INodeMappingExt;
import org.eclipse.sirius.diagram.model.business.internal.helper.MappingExtHelper;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;
import org.eclipse.sirius.viewpoint.description.style.StylePackage;

/**
 * Utility class to factor customizations for ContainerMapping and related.
 * 
 * @author pcdavid
 */
public final class NodeMappingHelper {
    private IInterpreter interpreter;

    private StyleHelper styleHelper;

    /**
     * Create the helper.
     * 
     * @param interpreter
     *            interpreter used to evaluate expressions.
     */
    public NodeMappingHelper(IInterpreter interpreter) {
        this.interpreter = interpreter;
        this.styleHelper = new StyleHelper(interpreter);
    }

    /**
     * Implementation of {@link NodeMapping#createNode(EObject, EObject, DDiagram)}.
     * 
     * @param self
     *            the node mapping.
     * @param modelElement
     *            the semantic element.
     * @param container
     *            the semantic container of the element.
     * @param diagram
     *            the diagram.
     * @return the node created.
     */
    public DNode createNode(INodeMappingExt self, EObject modelElement, EObject container, DDiagram diagram) {
        final DNode newNode = DiagramFactory.eINSTANCE.createDNode();

        // getting the right style description : default or conditional
        final NodeStyleDescription style = (NodeStyleDescription) new MappingWithInterpreterHelper(interpreter).getBestStyleDescription(self, modelElement, newNode, container, diagram);

        newNode.setTarget(modelElement);
        newNode.setActualMapping(self);

        DiagramElementMappingHelper.refreshSemanticElements(self, newNode, interpreter);

        interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, diagram);
        interpreter.setVariable(IInterpreterSiriusVariables.VIEW, newNode);
        if (style != null && style.getLabelExpression() != null) {
            try {
                final String name = interpreter.evaluateString(modelElement, style.getLabelExpression());
                newNode.setName(name);
            } catch (final EvaluationException e) {
                RuntimeLoggerManager.INSTANCE.error(style, StylePackage.eINSTANCE.getBasicLabelStyleDescription_LabelExpression(), e);
            }
        }
        if (style != null) {
            newNode.setResizeKind(style.getResizeKind());

            styleHelper.setComputedSize(newNode, style);
        }
        final NodeStyle bestStyle = (NodeStyle) new MappingWithInterpreterHelper(interpreter).getBestStyle(self, modelElement, newNode, container, diagram);
        if (bestStyle != null) {
            newNode.setOwnedStyle(bestStyle);
        }

        MappingExtHelper.addDoneNode(self, newNode);

        interpreter.unSetVariable(IInterpreterSiriusVariables.VIEW);
        interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);

        /*
         * Iterate over the border mapping of the node to initialize the border nodes.
         */
        AbstractNodeMappingSpecOperations.createBorderingNodes(self, modelElement, newNode, Collections.EMPTY_LIST, diagram);
        if (newNode.getOwnedStyle() != null) {
            Option<NodeStyle> noPreviousStyle = Options.newNone();
            new StyleHelper(interpreter).refreshStyle(newNode.getOwnedStyle(), noPreviousStyle);
        }
        return newNode;
    }

    /**
     * Implementation of {@link NodeMapping#updateNode(DNode)}.
     * 
     * @param self
     *            the node mapping.
     * @param node
     *            the node to update.
     */
    public void updateNode(NodeMapping self, DNode node) {
        final EObject modelElement = node.getTarget();

        final DSemanticDecorator container = (DSemanticDecorator) node.eContainer();
        NodeStyleDescription style = null;

        // getting the right style description : default or conditional
        DDiagram parentDiagram = node.getParentDiagram();
        if (container != null) {
            style = (NodeStyleDescription) new MappingWithInterpreterHelper(interpreter).getBestStyleDescription(self, modelElement, node, container.getTarget(), parentDiagram);
        }

        if (style != null && style.getLabelExpression() != null) {
            try {
                interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, parentDiagram);
                interpreter.setVariable(IInterpreterSiriusVariables.VIEW, node);
                final String name = interpreter.evaluateString(modelElement, style.getLabelExpression());
                node.setName(name);
            } catch (final EvaluationException e) {
                RuntimeLoggerManager.INSTANCE.error(style, StylePackage.eINSTANCE.getBasicLabelStyleDescription_LabelExpression(), e);
            } finally {
                interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
                interpreter.unSetVariable(IInterpreterSiriusVariables.VIEW);
            }
        }

        if (style != null && style.getTooltipExpression() != null) {
            try {
                interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, parentDiagram);
                interpreter.setVariable(IInterpreterSiriusVariables.VIEW, node);
                final String tooltip = interpreter.evaluateString(modelElement, style.getTooltipExpression());
                node.setTooltipText(tooltip);
            } catch (final EvaluationException e) {
                RuntimeLoggerManager.INSTANCE.error(style, StylePackage.eINSTANCE.getTooltipStyleDescription_TooltipExpression(), e);
            } finally {
                interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
                interpreter.unSetVariable(IInterpreterSiriusVariables.VIEW);
            }
        }

        /*
         * Getting the node size
         */
        if (node.getResizeKind() == ResizeKind.NONE_LITERAL) {
            styleHelper.setComputedSize(node, style);
        }

        // semantic elements
        DiagramElementMappingHelper.refreshSemanticElements(self, node, interpreter);

        EObject containerVariable = null;
        if (node.eContainer() instanceof DSemanticDecorator) {
            containerVariable = ((DSemanticDecorator) node.eContainer()).getTarget();
        }

        final StyleDescription bestStyleDescription = new MappingWithInterpreterHelper(interpreter).getBestStyleDescription(self, modelElement, node, containerVariable, parentDiagram);
        Style bestStyle = node.getStyle();
        if ((bestStyle == null || bestStyle.getDescription() != bestStyleDescription) && bestStyleDescription != null) {
            bestStyle = styleHelper.createStyle(bestStyleDescription);
        }

        styleHelper.setAndRefreshStyle(node, node.getStyle(), bestStyle);

        MappingExtHelper.addDoneNode(self, node);
    }

    /**
     * Implementation of {@link NodeMapping#createListElement(EObject)}.
     * 
     * @param self
     *            the node mapping.
     * @param modelElement
     *            the semantic model element.
     * @param diagram
     *            the diagram.
     * @return the element created.
     */
    public DNodeListElement createListElement(NodeMapping self, EObject modelElement, DDiagram diagram) {

        final DNodeListElement newNode = DiagramFactory.eINSTANCE.createDNodeListElement();

        // getting the right style description : default or conditional
        final NodeStyleDescription style = (NodeStyleDescription) new MappingWithInterpreterHelper(interpreter).getBestStyleDescription(self, modelElement, newNode, null, diagram);

        newNode.setTarget(modelElement);
        newNode.setActualMapping(self);

        // semantic elements
        DiagramElementMappingHelper.refreshSemanticElements(self, newNode, interpreter);

        if (style != null && !StringUtil.isEmpty(style.getLabelExpression())) {
            newNode.setName(DiagramElementMappingHelper.computeLabel(newNode, style, diagram, interpreter));
        }

        /*
         * Getting the node size
         */
        MappingExtHelper.addDoneNode(self, newNode);

        final NodeStyle bestStyle = (NodeStyle) new MappingWithInterpreterHelper(interpreter).getBestStyle(self, modelElement, newNode, null, diagram);
        if (bestStyle != null) {
            newNode.setOwnedStyle(bestStyle);
        }

        return newNode;
    }

    /**
     * Implementation of {@link NodeMapping#updateListElement(DNodeListElement)} .
     * 
     * @param self
     *            the node mapping.
     * @param listElement
     *            the view node list element to update.
     */
    public void updateListElement(NodeMapping self, DNodeListElement listElement) {

        final EObject modelElement = listElement.getTarget();
        final DSemanticDecorator container = (DSemanticDecorator) listElement.eContainer();
        NodeStyleDescription style = null;

        // getting the right style description : default or conditional
        DDiagram parentDiagram = listElement.getParentDiagram();
        if (container != null) {
            style = (NodeStyleDescription) new MappingWithInterpreterHelper(interpreter).getBestStyleDescription(self, modelElement, listElement, container.getTarget(), parentDiagram);
        }

        if (style != null && !StringUtil.isEmpty(style.getLabelExpression())) {
            listElement.setName(DiagramElementMappingHelper.computeLabel(listElement, style, parentDiagram, interpreter));
        }

        EObject containerVariable = null;
        if (listElement.eContainer() instanceof DSemanticDecorator) {
            containerVariable = ((DSemanticDecorator) listElement.eContainer()).getTarget();
        }

        // semantic elements
        DiagramElementMappingHelper.refreshSemanticElements(self, listElement, interpreter);

        final NodeStyle bestStyle = (NodeStyle) new MappingWithInterpreterHelper(interpreter).getBestStyle(self, modelElement, listElement, containerVariable, parentDiagram);

        if ((bestStyle == null || bestStyle.getDescription() != style) && style != null) {
            listElement.setOwnedStyle(bestStyle);
        }
        styleHelper.setAndRefreshStyle(listElement, listElement.getStyle(), bestStyle);
        MappingExtHelper.addDoneNode(self, listElement);
    }

    /**
     * Return <code>true</code> if <code>eObj</code> is an instance of <code>typename</code>.
     * 
     * @param eObj
     *            an object.
     * @param typename
     *            the name of the type to check.
     * @return <code>true</code> if <code>eObj</code> is an instance of <code>typename</code>.
     */
    private static boolean isInstanceOf(final EObject eObj, final String typename) {
        DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.INSTANCE_OF_KEY);
        final boolean result = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(eObj).eInstanceOf(eObj, typename);
        DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.INSTANCE_OF_KEY);
        return result;
    }

}
