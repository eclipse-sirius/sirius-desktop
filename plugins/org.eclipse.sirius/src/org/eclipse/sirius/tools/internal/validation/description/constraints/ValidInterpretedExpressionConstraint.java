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
package org.eclipse.sirius.tools.internal.validation.description.constraints;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.sirius.common.tools.api.interpreter.CompoundInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterStatus;
import org.eclipse.sirius.tools.api.interpreter.context.SiriusInterpreterContextFactory;
import org.eclipse.sirius.tools.internal.validation.AbstractConstraint;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;

import com.google.common.collect.Sets;

/**
 * Constraint ensuring that all Interpreted Expressions of the Odesign are
 * valid.
 * 
 * @author alagarde
 * 
 */
public class ValidInterpretedExpressionConstraint extends AbstractConstraint {

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
     */
    @Override
    public IStatus validate(IValidationContext ctx) {
        final EObject target = ctx.getTarget();
        Collection<IStatus> statuses = Sets.newLinkedHashSet();

        // For each structural features of the element to validate
        for (EAttribute feature : target.eClass().getEAllAttributes()) {
            // If this feature is an Interpreted expression
            if (DescriptionPackage.eINSTANCE.getInterpretedExpression().equals(feature.getEAttributeType())) {
                IStatus status = checkExpression(ctx, target, feature);
                if (status != null) {
                    statuses.add(status);
                }
            }
        }
        if (statuses.isEmpty()) {
            return ctx.createSuccessStatus();
        }

        final IStatus returnStatus;
        if (statuses.size() == 1) {
            returnStatus = statuses.iterator().next();
        } else {
            returnStatus = ConstraintStatus.createMultiStatus(ctx, statuses);
        }
        return returnStatus;
    }

    private IStatus checkExpression(IValidationContext ctx, EObject target, EStructuralFeature feature) {
        String expression = (String) target.eGet(feature);
        IInterpreter interpreterForExpression = CompoundInterpreter.INSTANCE.getInterpreterForExpression(expression);

        Collection<IInterpreterStatus> errors = Sets.newLinkedHashSet();
        if (interpreterForExpression.supportsValidation()) {
            IInterpreterContext context = SiriusInterpreterContextFactory.createInterpreterContext(target, feature);

            errors = interpreterForExpression.validateExpression(context, expression);
        }

        if (errors.isEmpty()) {
            return null;
        }

        Collection<IStatus> statuses = Sets.newLinkedHashSet();
        Collection<EObject> locus = Collections.singleton(target);
        for (IInterpreterStatus interpreterStatus : errors) {
            statuses.add(ConstraintStatus.createStatus(ctx, locus, interpreterStatus.getMessage()));
        }

        final IStatus returnStatus;
        if (statuses.size() == 1) {
            returnStatus = statuses.iterator().next();
        } else {
            returnStatus = ConstraintStatus.createMultiStatus(ctx, statuses);
        }
        return returnStatus;
    }
}
