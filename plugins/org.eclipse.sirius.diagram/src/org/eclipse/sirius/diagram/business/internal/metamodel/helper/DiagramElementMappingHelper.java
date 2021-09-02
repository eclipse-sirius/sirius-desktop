/*******************************************************************************
 * Copyright (c) 2011, 2021 THALES GLOBAL SERVICES.
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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.tool.DirectEditLabel;
import org.eclipse.sirius.diagram.description.tool.ToolPackage;
import org.eclipse.sirius.diagram.tools.api.Messages;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tools.api.interpreter.IInterpreterMessages;
import org.eclipse.sirius.tools.api.ui.resource.ISiriusMessages;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.StylePackage;

import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;

/**
 * Utility class to factor customizations for DiagramElementMapping and related.
 * 
 * @author mporhel
 */
public final class DiagramElementMappingHelper {
    private DiagramElementMappingHelper() {
        // Prevent instantiation.
    }

    /**
     * Evaluate the semantic elements feature of the mapping and affect them to the given diagram element.
     * 
     * Add the semantic target if there is no computed elements, except the case of non domain based edges.
     * 
     * The semantic target of the current DDiagramElement must be set.
     * 
     * @param self
     *            the current DiagramElementMapping
     * @param dde
     *            the current created DDiagramElement.
     * @param interpreter
     *            the current interpreter.
     */
    public static void refreshSemanticElements(DiagramElementMapping self, DDiagramElement dde, final IInterpreter interpreter) {
        Collection<EObject> semanticElements = new ArrayList<>();

        if (!StringUtil.isEmpty(self.getSemanticElements())) {
            semanticElements = DiagramElementMappingHelper.evaluateSemanticElements(self, dde, interpreter);
        }

        if ((semanticElements == null || semanticElements.isEmpty()) && dde.getTarget() != null) {
            // Do not add the semantic target for relation based edges.
            if (!(self instanceof EdgeMapping) || ((EdgeMapping) self).isUseDomainElement()) {
                semanticElements = Collections.singletonList(dde.getTarget());
            }
        }

        if (!semanticElements.isEmpty() && !Iterables.elementsEqual(semanticElements, dde.getSemanticElements())) {
            dde.getSemanticElements().clear();
            dde.getSemanticElements().addAll(semanticElements);
        }
    }

    /**
     * Compute label.
     * 
     * @param diagramElement
     *            the diagram element.
     * @param diagram
     *            the parent diagram.
     * @param style
     *            the style.
     * @param interpreter
     *            the interpreter.
     * @return the label value.
     */
    public static String computeLabel(DDiagramElement diagramElement, BasicLabelStyleDescription style, DDiagram diagram, final IInterpreter interpreter) {
        String result = null;

        interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, diagram);
        interpreter.setVariable(IInterpreterSiriusVariables.VIEW, diagramElement);

        try {
            result = interpreter.evaluateString(diagramElement.getTarget(), style.getLabelExpression());
        } catch (final EvaluationException e) {
            RuntimeLoggerManager.INSTANCE.error(style, StylePackage.eINSTANCE.getBasicLabelStyleDescription_LabelExpression(), e);
            result = IInterpreterMessages.DEFAULT_NAME_ON_FACTORY_EXCEPTION;

        } finally {
            interpreter.unSetVariable(IInterpreterSiriusVariables.VIEW);
            interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
        }

        return result;
    }

    /**
     * Compute input label.
     * 
     * @param diagramElement
     *            the diagram element.
     * @param diagram
     *            the parent diagram.
     * @param labelDirectEdit
     *            the labelDirectEdit.
     * @param interpreter
     *            the interpreter.
     * @return the label value.
     */
    public static String computeInputLabelOfDirectEditLabel(DDiagramElement diagramElement, DDiagram diagram, DirectEditLabel labelDirectEdit, final IInterpreter interpreter) {
        String result = null;

        interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, diagram);
        interpreter.setVariable(IInterpreterSiriusVariables.VIEW, diagramElement);

        try {
            result = interpreter.evaluateString(diagramElement.getTarget(), labelDirectEdit.getInputLabelExpression());
        } catch (final EvaluationException e) {
            RuntimeLoggerManager.INSTANCE.error(labelDirectEdit, ToolPackage.eINSTANCE.getDirectEditLabel_InputLabelExpression(), e);
            result = IInterpreterMessages.DEFAULT_NAME_ON_FACTORY_EXCEPTION;

        } finally {
            interpreter.unSetVariable(IInterpreterSiriusVariables.VIEW);
            interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
        }

        return result;
    }

    private static Collection<EObject> evaluateSemanticElements(DiagramElementMapping self, DDiagramElement dde, final IInterpreter interpreter) {
        Collection<EObject> semanticElements = Collections.emptyList();
        if (dde.getTarget() != null && !StringUtil.isEmpty(self.getSemanticElements())) {
            final EObject context = dde.getTarget();
            try {
                interpreter.setVariable(IInterpreterSiriusVariables.VIEW, dde);

                DDiagram parentDiagram = dde.getParentDiagram();
                interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, parentDiagram);
                interpreter.setVariable(IInterpreterSiriusVariables.VIEWPOINT, parentDiagram);

                if (dde.eContainer() != null) {
                    interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER_VIEW, dde.eContainer());
                    if (dde.eContainer() instanceof DSemanticDecorator) {
                        interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER, ((DSemanticDecorator) dde.eContainer()).getTarget());
                    }
                }

                if (self instanceof EdgeMapping && dde instanceof DEdge) {
                    DEdge edge = (DEdge) dde;
                    interpreter.setVariable(IInterpreterSiriusVariables.SOURCE, DiagramElementMappingHelper.getSemanticTarget(edge.getSourceNode()));
                    interpreter.setVariable(IInterpreterSiriusVariables.TARGET, DiagramElementMappingHelper.getSemanticTarget(edge.getTargetNode()));
                    interpreter.setVariable(IInterpreterSiriusVariables.SOURCE_VIEW, edge.getSourceNode());
                    interpreter.setVariable(IInterpreterSiriusVariables.TARGET_VIEW, edge.getTargetNode());
                }

                semanticElements = interpreter.evaluateCollection(context, self.getSemanticElements());
            } catch (final EvaluationException e) {
                RuntimeLoggerManager.INSTANCE.error(self, DescriptionPackage.eINSTANCE.getDiagramElementMapping_SemanticElements(), e);
            } finally {
                interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
                interpreter.unSetVariable(IInterpreterSiriusVariables.VIEW);
                interpreter.unSetVariable(IInterpreterSiriusVariables.VIEWPOINT);

                if (dde.eContainer() != null) {
                    interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER_VIEW);
                    if (dde.eContainer() instanceof DSemanticDecorator) {
                        interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER);
                    }
                }

                if (self instanceof EdgeMapping && dde instanceof DEdge) {
                    interpreter.unSetVariable(IInterpreterSiriusVariables.SOURCE);
                    interpreter.unSetVariable(IInterpreterSiriusVariables.TARGET);
                    interpreter.unSetVariable(IInterpreterSiriusVariables.SOURCE_VIEW);
                    interpreter.unSetVariable(IInterpreterSiriusVariables.TARGET_VIEW);
                }
            }
        }
        return semanticElements;
    }

    /**
     * 
     * Evaluates the semantic candidates and return an iterator.
     * 
     * @param self
     *            the current AbstractNodeMapping to request.
     * @param context
     *            the current contaxt.
     * @param diagram
     *            the current diagram.
     * @return an iterator on computed semantic candidates.
     */
    public static Iterator<EObject> getSemanticIterator(AbstractNodeMapping self, EObject context, final EObject diagram) {
        Iterator<EObject> result;
        if (!StringUtil.isEmpty(self.getSemanticCandidatesExpression())) {
            try {
                final IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(context);
                interpreter.setVariable(IInterpreterSiriusVariables.VIEWPOINT, diagram);
                interpreter.setVariable(IInterpreterSiriusVariables.VIEWPOINT_2, diagram);
                interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, diagram);
                interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER_VIEW, diagram);
                final Collection<EObject> resCollection = interpreter.evaluateCollection(context, self.getSemanticCandidatesExpression());
                interpreter.unSetVariable(IInterpreterSiriusVariables.VIEWPOINT);
                interpreter.unSetVariable(IInterpreterSiriusVariables.VIEWPOINT_2);
                interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER_VIEW);
                interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
                return resCollection.iterator();
            } catch (final EvaluationException e) {
                RuntimeLoggerManager.INSTANCE.error(self, DescriptionPackage.eINSTANCE.getDiagramElementMapping_SemanticCandidatesExpression(), e);
            }
            result = Collections.<EObject> emptyList().iterator();
        } else {
            result = DiagramElementMappingHelper.extEAllContents(context);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    private static Iterator<EObject> extEAllContents(final EObject eObj) {
        List<Iterator<EObject>> eAllContentsIterators = new ArrayList<>();
        final ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(eObj);
        final Session session = SessionManager.INSTANCE.getSession(eObj);
        for (final Resource resource : session.getSemanticResources()) {
            for (final EObject root : resource.getContents()) {
                eAllContentsIterators.add(accessor.eAllContents(root));
            }
        }
        return Iterators.concat(Iterables.toArray(eAllContentsIterators, Iterator.class));
    }

    /**
     * return the semantic element of the {@link EdgeTarget}.
     * 
     * @param edgeTarget
     *            the edge target.
     * @return the semantic element of the {@link EdgeTarget}.
     */
    private static EObject getSemanticTarget(final EdgeTarget edgeTarget) {
        if (edgeTarget instanceof DSemanticDecorator) {
            return ((DSemanticDecorator) edgeTarget).getTarget();
        } else if (edgeTarget != null) {
            SiriusPlugin.getDefault().warning(MessageFormat.format(Messages.DiagramElementMappingHelper_edgeTargetMsg, String.valueOf(edgeTarget), ISiriusMessages.IS_NOT_A_DECORATE_SEMANTIC_ELEMENT),
                    null);
        }
        return null;
    }
}
