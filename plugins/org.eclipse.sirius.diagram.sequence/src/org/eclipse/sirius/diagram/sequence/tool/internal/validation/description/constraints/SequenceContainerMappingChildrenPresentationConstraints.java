/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.tool.internal.validation.description.constraints;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.sirius.diagram.business.api.query.ContainerMappingQuery;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.sequence.description.FrameMapping;
import org.eclipse.sirius.diagram.sequence.description.OperandMapping;

/**
 * Container children presentation related validation rules.
 * 
 * @author mporhel
 */
public class SequenceContainerMappingChildrenPresentationConstraints extends AbstractModelConstraint {

    /**
     * Sequence children presentation rule id.
     */
    public static final String SEQUENCE_NO_REGION_RULE_ID = "org.eclipse.sirius.diagram.sequence.NoSequenceContainerRegionChildrenPresentation"; //$NON-NLS-1$

    @Override
    public IStatus validate(IValidationContext ctx) {
        IStatus result = ctx.createSuccessStatus();
        final EObject eObj = ctx.getTarget();
        if (eObj instanceof ContainerMapping) {
            ContainerMapping containerMapping = (ContainerMapping) eObj;
            if (SEQUENCE_NO_REGION_RULE_ID.equals(ctx.getCurrentConstraintId())) {
                result = validateNoRegionContainerMapping(ctx, containerMapping);
            }
        }
        return result;
    }

    private IStatus validateNoRegionContainerMapping(IValidationContext ctx, ContainerMapping containerMapping) {
        if (containerMapping instanceof FrameMapping || containerMapping instanceof OperandMapping) {
            if (new ContainerMappingQuery(containerMapping).isRegionContainer()) {
                return ctx.createFailureStatus(new Object[] { containerMapping });
            }
        }
        return ctx.createSuccessStatus();
    }

}
