/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.description.style.EdgeStyleDescription;
import org.eclipse.sirius.tools.internal.validation.AbstractConstraint;

/**
 * Constraint ensuring that all Interpreted Expressions of the Odesign are
 * valid.
 * 
 * @author alagarde
 * 
 */
public class RequiredEdgeStyleDescriptionSizeExpressionConstraint extends AbstractConstraint {

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
     */
    @Override
    public IStatus validate(IValidationContext ctx) {
        final EObject eObj = ctx.getTarget();
        final EMFEventType eventType = ctx.getEventType();

        // In the case of batch mode.
        if (eventType == EMFEventType.NULL) {
            if (eObj instanceof EdgeStyleDescription && StringUtil.isEmpty(((EdgeStyleDescription) eObj).getSizeComputationExpression())) {
                return ctx.createFailureStatus(eObj);
            }
        }
        return ctx.createSuccessStatus();
    }
}
