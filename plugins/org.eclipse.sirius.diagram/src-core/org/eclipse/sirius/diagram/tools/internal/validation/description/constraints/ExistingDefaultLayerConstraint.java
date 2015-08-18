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
package org.eclipse.sirius.diagram.tools.internal.validation.description.constraints;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.tools.internal.validation.AbstractConstraint;

/**
 * Constraint to validate that each diagram has a default layer.
 * 
 * @author bgrouhan
 */
public class ExistingDefaultLayerConstraint extends AbstractConstraint {

    @Override
    public IStatus validate(IValidationContext ctx) {
        IStatus status = ctx.createSuccessStatus();
        // In the case of batch mode.
        if (ctx.getEventType() == EMFEventType.NULL) {
            EObject eObj = ctx.getTarget();
            if (DescriptionPackage.eINSTANCE.getDiagramDescription().isSuperTypeOf(eObj.eClass())) {
                DiagramDescription diagram = (DiagramDescription) eObj;
                if (diagram.getDefaultLayer() == null) {
                    status = ctx.createFailureStatus(new Object[] { "'" + diagram.getName() + "'" }); //$NON-NLS-1$ //$NON-NLS-2$
                }
            }
        }
        return status;
    }
}
