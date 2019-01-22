/*******************************************************************************
 * Copyright (c) 2017-2019 OBEO.
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

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Optional;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.sirius.business.api.dialect.description.MultiLanguagesValidator;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterStatus;
import org.eclipse.sirius.common.tools.api.interpreter.TypeName;
import org.eclipse.sirius.common.tools.api.interpreter.VariableType;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.tools.internal.interpreter.SiriusInterpreterContextFactory;
import org.eclipse.sirius.tools.internal.validation.AbstractConstraint;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.tool.impl.CreateInstanceImpl;

import com.google.common.collect.Iterables;
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
        Collection<IStatus> statuses = new LinkedHashSet<>();

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
                } else if (target instanceof CreateInstanceImpl) {
                    EClassifier eType = optional.get().getEType();
                    VariableType refType =  VariableType.fromEClassifiers(Sets.newLinkedHashSet(Arrays.asList(eType)));
                    returnStatus = checkFeatureWithType(ctx, target, feature, refType);
                }
            }

        } else {
            returnStatus = ctx.createFailureStatus(expression, variableType);
        }
        return returnStatus;
    }

    /**
     * Check if referenceName type is consistent with type name.
     * 
     * @param ctx
     *            context used to display status error
     * @param target
     *            object to validate
     * @param feature
     *            referenceName feature
     * @return error status if referenceName type is not consistent with typeName, ok status otherwise.
     */
    private IStatus checkFeatureWithType(IValidationContext ctx, EObject target, EStructuralFeature feature, VariableType variableType) {
        IInterpreterContext context = SiriusInterpreterContextFactory.createInterpreterContext(target, feature);
        for (EAttribute feat : Iterables.filter(target.eClass().getEAllStructuralFeatures(), EAttribute.class)) {
            if (DescriptionPackage.Literals.TYPE_NAME == feat.getEType()) {
                Object value = target.eGet(feat);
                if (value instanceof String) {
                    TypeName className = TypeName.fromString((String) value);
                    if (className.getPackagePrefix().isPresent()) {
                        String expression = (String) target.eGet(feature);
                        context.getVariables().put("toCheck", variableType); //$NON-NLS-1$
                        Iterator<IInterpreterStatus> errors = MultiLanguagesValidator.getInstance().validateExpression(context, "aql:toCheck.oclIsKindOf(" + className.getCompleteName("::") + ")") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                                .getStatuses().iterator();
                        while (errors.hasNext()) {
                            IInterpreterStatus s = errors.next();
                            if (s.getMessage().contains("Always false")) { //$NON-NLS-1$
                                return ctx.createFailureStatus(expression, className.getCompleteName("::")); //$NON-NLS-1$
                            }
                        }
                    }
                }
            }
        }
        return ctx.createSuccessStatus();
    }

    private boolean isTypeIdentified(VariableType variableType) {
        return variableType.hasDefinition() && variableType.getPossibleTypes().stream().anyMatch(tn -> !TypeName.EOBJECT_TYPENAME.equals(tn));
    }
}
