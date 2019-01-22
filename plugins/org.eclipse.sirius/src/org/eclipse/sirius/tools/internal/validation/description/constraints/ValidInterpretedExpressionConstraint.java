/*******************************************************************************
 * Copyright (c) 2013, 2016 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tools.internal.validation.description.constraints;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.emf.validation.service.ConstraintRegistry;
import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.eclipse.sirius.business.api.dialect.description.MultiLanguagesValidator;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterStatus;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.tools.internal.interpreter.SiriusInterpreterContextFactory;
import org.eclipse.sirius.tools.internal.validation.AbstractConstraint;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;

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
        Collection<IStatus> statuses = new LinkedHashSet<>();

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

        Collection<IStatus> statuses = new LinkedHashSet<>();
        Collection<IInterpreterStatus> errors = new LinkedHashSet<>();
        Collection<EObject> locus = Collections.singleton(target);
        if (!StringUtil.isEmpty(expression)) {
            try {
                IInterpreterContext context = SiriusInterpreterContextFactory.createInterpreterContext(target, feature);
                errors = MultiLanguagesValidator.getInstance().validateExpression(context, expression).getStatuses();

                // CHECKSTYLE:OFF
                /*
                 * the rationale for catching *any* exception is as such :
                 * whatever happens in an interpreter implementation the
                 * exception should not bubble up to the emf validation
                 * framework or it will blacklist the constraint and will not
                 * launch it anymore. Better to catch the exception and report a
                 * status so that the end user knows there is something wrong
                 * (hopefully the exception message give a clue).
                 */
            } catch (Exception e) {
                statuses.add(ConstraintStatus.createStatus(ctx, locus, e.getMessage()));
            }
            // CHECKSTYLE:ON
        }

        if (errors.isEmpty()) {
            return null;
        }

        for (IInterpreterStatus interpreterStatus : errors) {
            statuses.add(createStatus(ctx, locus, interpreterStatus));
        }

        final IStatus returnStatus;
        if (statuses.size() == 1) {
            returnStatus = statuses.iterator().next();
        } else {
            returnStatus = ConstraintStatus.createMultiStatus(ctx, statuses);
        }
        return returnStatus;
    }

    private ConstraintStatus createStatus(IValidationContext ctx, Collection<EObject> resultLocus, IInterpreterStatus status, Object... messageArguments) {

        IConstraintDescriptor desc = ConstraintRegistry.getInstance().getDescriptor(ctx.getCurrentConstraintId());

        int severity = desc.getSeverity().toIStatusSeverity();
        if (IInterpreterStatus.WARNING.equals(status.getSeverity())) {
            severity = IStatus.WARNING;
        } else if (IInterpreterStatus.ERROR.equals(status.getSeverity())) {
            severity = IStatus.ERROR;
        } else if (IInterpreterStatus.INFO.equals(status.getSeverity())) {
            severity = IStatus.INFO;
        }

        return ConstraintStatus.createStatus(ctx, null, resultLocus, severity, desc.getStatusCode(), status.getMessage(), messageArguments);
    }

}
