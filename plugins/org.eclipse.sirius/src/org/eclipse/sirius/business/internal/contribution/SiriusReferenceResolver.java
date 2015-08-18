/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.contribution;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.description.contribution.ComputedEObjectReference;
import org.eclipse.sirius.description.contribution.DirectEObjectReference;
import org.eclipse.sirius.description.contribution.EObjectReference;
import org.eclipse.sirius.description.contribution.util.ContributionSwitch;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

import com.google.common.base.Preconditions;

/**
 * Resolves {@link EObjectReference}s using a Sirius {@link IInterpreter}
 * (typically from a session) for computed references.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class SiriusReferenceResolver implements ReferenceResolver {
    private static final String SOURCES_VARIABLE_NAME = "sources"; //$NON-NLS-1$

    /**
     * The interpreter to use for computed expressions.
     */
    private final IInterpreter interpreter;

    /**
     * Constructor.
     * 
     * @param interpreter
     *            the interpreter to use for computed expressions.
     */
    public SiriusReferenceResolver(IInterpreter interpreter) {
        this.interpreter = Preconditions.checkNotNull(interpreter);
    }

    /**
     * {@inheritDoc}
     */
    public Option<EObject> resolve(EObjectReference ref, final Map<String, Object> context) {
        Preconditions.checkNotNull(ref);

        ContributionSwitch<EObject> contributionSwitch = new ContributionSwitch<EObject>() {
            @Override
            public EObject caseDirectEObjectReference(DirectEObjectReference directRef) {
                return resolveDirectReference(directRef);
            }

            @Override
            public EObject caseComputedEObjectReference(ComputedEObjectReference computedRef) {
                return resolveComputedReference(computedRef, context);
            };
        };
        EObject result = contributionSwitch.doSwitch(ref);

        return Options.fromNullable(result);
    }

    private EObject resolveDirectReference(DirectEObjectReference directRef) {
        return directRef.getValue();
    }

    private EObject resolveComputedReference(ComputedEObjectReference computedRef, Map<String, Object> context) {
        EObject result = null;
        String expr = computedRef.getValueExpression();
        if (expr != null && interpreter.provides(expr) && context.get("self") instanceof EObject) { //$NON-NLS-1$
            try {
                interpreter.setVariable(SOURCES_VARIABLE_NAME, context.get(SOURCES_VARIABLE_NAME));
                result = interpreter.evaluateEObject((EObject) context.get("self"), expr); //$NON-NLS-1$
            } catch (EvaluationException e) {
                // TODO Log warning
            } finally {
                interpreter.unSetVariable(SOURCES_VARIABLE_NAME);
            }
        }
        return result;
    }
}
