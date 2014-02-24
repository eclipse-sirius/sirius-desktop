/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.metamodel.description.operations;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.business.api.query.DiagramElementMappingQuery;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.DMappingBased;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * Implementation of DiagramElementMappingImpl.java.
 * 
 * @author cbrun
 */
public final class SiriusElementMappingSpecOperations {

    /**
     * Avoid instantiation.
     */
    private SiriusElementMappingSpecOperations() {
        // empty.
    }

    /**
     * Check the precondition of the mapping for the object
     * <code>modelElement</code>.
     * 
     * @param mapping
     *            the mapping.
     * @param modelElement
     *            the element to check.
     * @param container
     *            the semantic element of the view that contains the potential
     *            view of <code>modelElement</code>.
     * @param containerView
     *            the view that contains the potential view of
     *            <code>modelElement</code>.
     * @return <code>true</code> if the precondition is checked, false
     *         otherwise.
     */
    public static boolean checkPrecondition(final DiagramElementMapping mapping, final EObject modelElement, final EObject container, final EObject containerView) {
        final IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(modelElement);
        boolean result = true;
        if (container != null) {
            interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER, container);
        }
        if (containerView != null) {
            interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER_VIEW, containerView);
        }
        if (mapping.getPreconditionExpression() != null && !StringUtil.isEmpty(mapping.getPreconditionExpression().trim())) {
            result = false;
            try {
                result = interpreter.evaluateBoolean(modelElement, mapping.getPreconditionExpression());
            } catch (final EvaluationException e) {
                // nothing special, keep silent
            }
        }

        if (container != null) {
            interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER);
        }
        if (containerView != null) {
            interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER_VIEW);
        }

        return result;
    }

    /**
     * Implementation of {@link DiagramElementMapping#isTypeOf(DMappingBased)}.
     * 
     * @param mapping
     *            the mapping.
     * @param element
     *            the mapping base.
     * @return <code>true</code> if the element has been created by the mapping.
     */
    public static boolean isFrom(final DiagramElementMapping mapping, final DMappingBased element) {
        if (element.getMapping() != null) {
            return new DiagramElementMappingQuery(mapping).isTypeOf(element);
        }
        return false;
    }

}
