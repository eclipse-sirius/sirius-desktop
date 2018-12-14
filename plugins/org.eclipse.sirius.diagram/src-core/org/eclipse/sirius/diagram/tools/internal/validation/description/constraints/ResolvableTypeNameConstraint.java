/*******************************************************************************
 * Copyright (c) 2017-2019 Obeo
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
package org.eclipse.sirius.diagram.tools.internal.validation.description.constraints;

import java.util.Iterator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.sirius.business.api.dialect.description.MultiLanguagesValidator;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterStatus;
import org.eclipse.sirius.common.tools.api.interpreter.TypeName;
import org.eclipse.sirius.common.tools.api.interpreter.VariableType;
import org.eclipse.sirius.tools.api.interpreter.context.SiriusInterpreterContextFactory;
import org.eclipse.sirius.tools.internal.validation.AbstractConstraint;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;

import com.google.common.collect.Iterables;

/**
 * Validate that a TypeName can be resolved in the current context. By resolved
 * it means: an interpreter can, using the plugin dependencies, and VSM
 * references, access to the type.
 * 
 * 
 */
public class ResolvableTypeNameConstraint extends AbstractConstraint {

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public IStatus validate(final IValidationContext ctx) {
        final EObject eObj = ctx.getTarget();
        final EMFEventType eventType = ctx.getEventType();
        // In the case of batch mode.
        if (eventType == EMFEventType.NULL && eObj != null) {
            for (EAttribute feat : Iterables.filter(eObj.eClass().getEAllStructuralFeatures(), EAttribute.class)) {
                if (DescriptionPackage.Literals.TYPE_NAME == feat.getEType()) {
                    Object[] result = checkIsResolvable(feat, eObj);
                    if (result != null) {
                        return ctx.createFailureStatus(result);
                    }
                }

            }
        }
        return ctx.createSuccessStatus();
    }

    private Object[] checkIsResolvable(EAttribute domainClassFeature, EObject eObj) {
        final Object value = eObj.eGet(domainClassFeature);
        if (value instanceof String) {
            final TypeName className = TypeName.fromString((String) value);
            /*
             * the domain class does exist, let's make sure we can resolve it.
             * As we use AQL to validate that we need a qualified type.
             */
            if (className.getPackagePrefix().isPresent()) { // $NON-NLS-1$
                IInterpreterContext context = SiriusInterpreterContextFactory.createInterpreterContext(eObj, domainClassFeature);
                context.getVariables().put("toCheck", VariableType.ANY_EOBJECT); //$NON-NLS-1$
                Iterator<IInterpreterStatus> errors = MultiLanguagesValidator.getInstance().validateExpression(context, "aql:toCheck.oclIsKindOf(" + className.getCompleteName("::") + ")") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        .getStatuses().iterator();
                while (errors.hasNext()) {
                    IInterpreterStatus s = errors.next();
                    if (IInterpreterStatus.ERROR.equals(s.getSeverity())) {
                        return new Object[] { value, domainClassFeature.getName() };
                    }
                }
            }
        }
        return null;
    }

}
