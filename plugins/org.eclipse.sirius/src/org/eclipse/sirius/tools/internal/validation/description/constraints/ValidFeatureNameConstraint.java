/*******************************************************************************
 * Copyright (c) 2017 OBEO.
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
import java.util.Optional;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.api.interpreter.TypeName;
import org.eclipse.sirius.common.tools.api.interpreter.VariableType;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.tools.api.interpreter.context.SiriusInterpreterContextFactory;
import org.eclipse.sirius.tools.internal.validation.AbstractConstraint;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;

import com.google.common.collect.Sets;

/**
 * Constraint ensuring that all Feature Name of the odesign are valid.
 * 
 * @author fbarbin
 * 
 */
public class ValidFeatureNameConstraint extends AbstractConstraint {

    @Override
    public IStatus validate(IValidationContext ctx) {
        final EObject target = ctx.getTarget();
        Collection<IStatus> statuses = Sets.newLinkedHashSet();

        // For each structural features of the element to validate
        for (EAttribute feature : target.eClass().getEAllAttributes()) {
            // If this feature is a Feature Name
            if (DescriptionPackage.eINSTANCE.getFeatureName().equals(feature.getEAttributeType())) {
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
        IStatus returnStatus = ctx.createSuccessStatus();
        String expression = (String) target.eGet(feature);
        IInterpreterContext context = SiriusInterpreterContextFactory.createInterpreterContext(target, feature);
        VariableType variableType = context.getTargetType();
        if (!StringUtil.isEmpty(expression)) {
            if (isTypeIdentified(variableType)) {
                Optional<EStructuralFeature> optional = variableType.getPossibleTypes().stream().flatMap(type -> type.search(context.getAvailableEPackages()).stream()).filter(EClass.class::isInstance)
                        .map(EClass.class::cast).flatMap(eClass -> eClass.getEAllStructuralFeatures().stream()).filter(f -> f.getName().equals(expression)).findFirst();
                if (!optional.isPresent()) {
                    returnStatus = ctx.createFailureStatus(expression, variableType);
                }
            }

        } else {
            returnStatus = ctx.createFailureStatus(expression, variableType);
        }
        return returnStatus;
    }

    private boolean isTypeIdentified(VariableType variableType) {
        return variableType.hasDefinition() && variableType.getPossibleTypes().stream().anyMatch(tn -> !TypeName.EOBJECT_TYPENAME.equals(tn));
    }
}
