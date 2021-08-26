/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.tools.api.SiriusPlugin;
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
     * @return <code>true</code> if the predicate of style if checked.
     */
    public static boolean checkPredicate(final ConditionalStyleDescription style, final EObject modelElement, final EObject viewVariable, final EObject containerVariable) {
        final IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(modelElement);
        return checkPredicate(style, modelElement, viewVariable, containerVariable, interpreter);
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
     * @param interpreter
     *            the interpreter to use to evaluate expressions.
     * @return <code>true</code> if the predicate of style if checked.
     */
    public static boolean checkPredicate(final ConditionalStyleDescription style, final EObject modelElement, final EObject viewVariable, final EObject containerVariable,
            final IInterpreter interpreter) {
        interpreter.setVariable(IInterpreterSiriusVariables.VIEW, viewVariable);
        interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER, containerVariable);

        final boolean result = RuntimeLoggerManager.INSTANCE.decorate(interpreter).evaluateBoolean(modelElement, style,
                DescriptionPackage.eINSTANCE.getConditionalStyleDescription_PredicateExpression());

        interpreter.unSetVariable(IInterpreterSiriusVariables.VIEW);
        interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER);
        return result;
    }
}
