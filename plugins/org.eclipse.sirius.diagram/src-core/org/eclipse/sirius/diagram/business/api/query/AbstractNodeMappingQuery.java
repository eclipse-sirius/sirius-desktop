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
package org.eclipse.sirius.diagram.business.api.query;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * A Query for {@link AbstractNodeMapping}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class AbstractNodeMappingQuery extends DiagramElementMappingQuery {

    private AbstractNodeMapping abstractNodeMapping;

    /**
     * Default constructor.
     * 
     * @param abstractNodeMapping
     *            a {@link AbstractNodeMapping} to query
     */
    public AbstractNodeMappingQuery(AbstractNodeMapping abstractNodeMapping) {
        super(abstractNodeMapping);
        this.abstractNodeMapping = abstractNodeMapping;
    }

    /**
     * Evaluate a node mapping precondition.
     * 
     * @param diagram
     *            the {@link DSemanticDiagram} context of the evaluation
     * @param containerView
     *            the view container of the node corresponding to the specified
     *            semantic element
     * @param interpreter
     *            the {@link IInterpreter} used to evaluate the precondition
     *            expression
     * @param semantic
     *            the semantic context for expression evaluation
     * @return <code>true</code> if the precondition is valid,
     *         <code>false</code> otherwise
     */
    public boolean evaluatePrecondition(DSemanticDiagram diagram, DragAndDropTarget containerView, IInterpreter interpreter, EObject semantic) {
        DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.CHECK_PRECONDITION_KEY);
        boolean result = true;
        if (!StringUtil.isEmpty(abstractNodeMapping.getPreconditionExpression())) {
            EObject container = null;
            if (containerView instanceof DSemanticDecorator) {
                container = ((DSemanticDecorator) containerView).getTarget();
            }

            interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER_VIEW, containerView);
            interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER, container);
            interpreter.setVariable(IInterpreterSiriusVariables.VIEWPOINT, diagram);
            interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, diagram);
            try {
                result = interpreter.evaluateBoolean(semantic, abstractNodeMapping.getPreconditionExpression());
            } catch (final EvaluationException e) {
                SiriusPlugin.getDefault().warning("the following abstract node mapping precondition could not be correctly evaluated : " + abstractNodeMapping.getPreconditionExpression(), e);
            }

            interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER_VIEW);
            interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER);
            interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
            interpreter.unSetVariable(IInterpreterSiriusVariables.VIEWPOINT);

        }
        DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.CHECK_PRECONDITION_KEY);
        return result;
    }

}
