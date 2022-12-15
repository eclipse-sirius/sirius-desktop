/*******************************************************************************
 * Copyright (c) 2007, 2022 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.metamodel.description.operations;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.ConditionalStyleDescription;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;

/**
 * Implementation of ConditionalStyleDescriptionImpl.java.
 * 
 * @author cbrun
 */
public final class ConditionalStyleSpecOperations {

    /**
     * Avoid instantiation.
     */
    private ConditionalStyleSpecOperations() {

    }

    /**
     * Implementation of. {@link ConditionalStyleDescription#checkPredicate(EObject, EObject, EObject)}
     * 
     * @param style
     *            the current conditional style
     * @param modelElement
     *            the semantic element.
     * @param viewVariable
     *            the view.
     * @param containerVariable
     *            the semantic element of the container.
     * @param dDiagram
     *            the diagram
     * @return <code>true</code> if the predicate of style if checked.
     */
    public static boolean checkPredicate(final ConditionalStyleDescription style, final EObject modelElement, final EObject viewVariable, final EObject containerVariable, DDiagram dDiagram) {
        final IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(modelElement);
        return checkPredicate(style, modelElement, viewVariable, containerVariable, dDiagram, interpreter);
    }

    /**
     * Implementation of. {@link ConditionalStyleDescription#checkPredicate(EObject, EObject, EObject)}
     * 
     * @param style
     *            the current conditional style
     * @param modelElement
     *            the semantic element.
     * @param viewVariable
     *            the view.
     * @param containerVariable
     *            the semantic element of the container.
     * @param dDiagram
     *            the diagram
     * @param interpreter
     *            the interpreter to use to evaluate expressions.
     * @return <code>true</code> if the predicate of style if checked.
     */
    public static boolean checkPredicate(final ConditionalStyleDescription style, final EObject modelElement, final EObject viewVariable, final EObject containerVariable, DDiagram dDiagram,
            final IInterpreter interpreter) {
        Set<String> variablesToUnset = new LinkedHashSet<>();
        if (viewVariable instanceof DDiagramElement) {
            interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, dDiagram);
            variablesToUnset.add(IInterpreterSiriusVariables.DIAGRAM);
            interpreter.setVariable(IInterpreterSiriusVariables.VIEW, viewVariable);
            variablesToUnset.add(IInterpreterSiriusVariables.VIEW);
            if (viewVariable instanceof DEdge) {
                EObject sourceNode = ((DEdge) viewVariable).getSourceNode();
                EObject targetNode = ((DEdge) viewVariable).getTargetNode();
                interpreter.setVariable(IInterpreterSiriusVariables.SOURCE_VIEW, sourceNode);
                variablesToUnset.add(IInterpreterSiriusVariables.SOURCE_VIEW);
                interpreter.setVariable(IInterpreterSiriusVariables.TARGET_VIEW, targetNode);
                variablesToUnset.add(IInterpreterSiriusVariables.TARGET_VIEW);
            }
            EObject eContainer = viewVariable.eContainer();
            if (eContainer != null) {
                interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER_VIEW, eContainer);
                variablesToUnset.add(IInterpreterSiriusVariables.CONTAINER_VIEW);
                if (eContainer instanceof DSemanticDecorator) {
                    EObject semanticContainer = ((DSemanticDecorator) eContainer).getTarget();
                    interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER, semanticContainer);
                    variablesToUnset.add(IInterpreterSiriusVariables.CONTAINER);
                }
            }
        }

        boolean result = false;
        try {
            result = RuntimeLoggerManager.INSTANCE.decorate(interpreter).evaluateBoolean(modelElement, style, DescriptionPackage.eINSTANCE.getConditionalStyleDescription_PredicateExpression());
        } finally {
            variablesToUnset.forEach(interpreter::unSetVariable);
        }

        return result;
    }
}
