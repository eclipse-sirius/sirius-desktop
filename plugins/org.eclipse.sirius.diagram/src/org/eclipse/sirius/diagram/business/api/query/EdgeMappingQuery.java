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
package org.eclipse.sirius.diagram.business.api.query;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.tools.api.Messages;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.viewpoint.DMappingBased;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * A class aggregating all the queries (read-only!) having a EdgeMapping as a starting point.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class EdgeMappingQuery extends DiagramElementMappingQuery {

    private EdgeMapping mapping;

    /**
     * Create a new query.
     * 
     * @param mapping
     *            the element to query.
     */
    public EdgeMappingQuery(EdgeMapping mapping) {
        super(mapping);
        this.mapping = mapping;
    }

    /**
     * return true if the edge could be created on the current source and target checking the mapping consistency.
     * 
     * @param source
     *            any source element
     * @param target
     *            any target element
     * @return true if the edge could be created on the current source and target checking the mapping consistency.
     */
    public boolean canCreate(DMappingBased source, DMappingBased target) {
        return new DMappingBasedQuery(source).isFromAnyMapping(mapping.getSourceMapping()) && new DMappingBasedQuery(target).isFromAnyMapping(mapping.getTargetMapping());
    }

    /**
     * Evaluate an edge mapping precondition.
     * 
     * @param diagram
     *            the {@link DSemanticDiagram} context of the evaluation
     * @param containerView
     *            the view container of the node corresponding to the specified semantic element
     * @param interpreter
     *            the {@link IInterpreter} used to evaluate the precondition expression
     * @param semantic
     *            the semantic context for expression evaluation
     * @param sourceView
     *            the diagram element source
     * @param targetView
     *            the diagram element target
     * @return <code>true</code> if the precondition is valid, <code>false</code> otherwise
     */
    public boolean evaluatePrecondition(DSemanticDiagram diagram, DragAndDropTarget containerView, IInterpreter interpreter, EObject semantic, DSemanticDecorator sourceView,
            DSemanticDecorator targetView) {
        DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.CHECK_PRECONDITION_KEY);
        boolean result = true;
        if (!StringUtil.isEmpty(mapping.getPreconditionExpression())) {
            EObject container = null;
            if (containerView instanceof DSemanticDecorator) {
                container = ((DSemanticDecorator) containerView).getTarget();
            }

            interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER_VIEW, containerView);
            interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER, container);
            interpreter.setVariable(IInterpreterSiriusVariables.VIEWPOINT, diagram);
            interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, diagram);
            interpreter.setVariable(IInterpreterSiriusVariables.SOURCE_VIEW, sourceView);
            interpreter.setVariable(IInterpreterSiriusVariables.TARGET_VIEW, targetView);
            interpreter.setVariable(IInterpreterSiriusVariables.SOURCE, sourceView.getTarget());
            interpreter.setVariable(IInterpreterSiriusVariables.TARGET, targetView.getTarget());

            try {
                result = interpreter.evaluateBoolean(semantic, mapping.getPreconditionExpression());
            } catch (final EvaluationException e) {
                SiriusPlugin.getDefault().warning(MessageFormat.format(Messages.EdgeMappingQuery_preconditionEvaluationErrorMsg, mapping.getPreconditionExpression()), e);
            }
            interpreter.unSetVariable(IInterpreterSiriusVariables.TARGET);
            interpreter.unSetVariable(IInterpreterSiriusVariables.SOURCE);
            interpreter.unSetVariable(IInterpreterSiriusVariables.TARGET_VIEW);
            interpreter.unSetVariable(IInterpreterSiriusVariables.SOURCE_VIEW);
            interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER);
            interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER_VIEW);
            interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
            interpreter.unSetVariable(IInterpreterSiriusVariables.VIEWPOINT);
        }
        DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.CHECK_PRECONDITION_KEY);
        return result;
    }

    /**
     * Evaluate a the source finder expression.
     * 
     * @param diagram
     *            the {@link DSemanticDiagram} context of the evaluation
     * @param interpreter
     *            the {@link IInterpreter} used to evaluate the precondition expression
     * @param sourceSemantic
     *            the source view semantic element
     * @return the result of the evaluation
     */
    public Collection<EObject> evaluateSourceFinderExpression(DSemanticDiagram diagram, IInterpreter interpreter, EObject sourceSemantic) {
        Collection<EObject> result = new ArrayList<EObject>();

        interpreter.setVariable(IInterpreterSiriusVariables.VIEWPOINT_2, diagram);
        interpreter.setVariable(IInterpreterSiriusVariables.VIEWPOINT, diagram);
        interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, diagram);

        try {
            Collection<EObject> sourceSemantics = interpreter.evaluateCollection(sourceSemantic, mapping.getSourceFinderExpression());
            if (sourceSemantics != null) {
                result.addAll(sourceSemantics);
            }
        } catch (final EvaluationException e) {
            SiriusPlugin.getDefault().warning(MessageFormat.format(Messages.EdgeMappingQuery_sourceFinderEvaluationErrorMsg, mapping.getSourceFinderExpression()), e);
        } finally {
            interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
            interpreter.unSetVariable(IInterpreterSiriusVariables.VIEWPOINT);
            interpreter.unSetVariable(IInterpreterSiriusVariables.VIEWPOINT_2);
        }
        return result;
    }

    /**
     * return true if the mapping defines a target finder expression.
     * 
     * @return true if the mapping defines a target finder expression.
     */
    public boolean hasTargetFinderExpression() {
        return mapping.getTargetFinderExpression() != null && !StringUtil.isEmpty(mapping.getTargetFinderExpression());
    }

    /**
     * Evaluate a the source finder expression.
     * 
     * @param diagram
     *            the {@link DSemanticDiagram} context of the evaluation
     * @param interpreter
     *            the {@link IInterpreter} used to evaluate the precondition expression
     * @param sourceSemantic
     *            the source view semantic element
     * @return the result of the evaluation
     */
    public Collection<EObject> evaluateTargetFinderExpression(DSemanticDiagram diagram, IInterpreter interpreter, EObject sourceSemantic) {
        Collection<EObject> result = new ArrayList<EObject>();

        interpreter.setVariable(IInterpreterSiriusVariables.VIEWPOINT_2, diagram);
        interpreter.setVariable(IInterpreterSiriusVariables.VIEWPOINT, diagram);
        interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, diagram);
        try {
            Collection<EObject> targetSemantics = interpreter.evaluateCollection(sourceSemantic, mapping.getTargetFinderExpression());
            if (targetSemantics != null) {
                result.addAll(targetSemantics);
            }
        } catch (final EvaluationException e) {
            SiriusPlugin.getDefault().warning(MessageFormat.format(Messages.EdgeMappingQuery_targetFinderEvaluationErrorMsg, mapping.getTargetFinderExpression()), e);
        } finally {
            interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
            interpreter.unSetVariable(IInterpreterSiriusVariables.VIEWPOINT);
            interpreter.unSetVariable(IInterpreterSiriusVariables.VIEWPOINT_2);
        }
        return result;
    }
}
