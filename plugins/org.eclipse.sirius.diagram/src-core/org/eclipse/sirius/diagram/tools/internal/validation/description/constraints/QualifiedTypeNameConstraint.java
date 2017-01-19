/*******************************************************************************
 * Copyright (c) 2017 Obeo
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
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.sirius.common.tools.api.interpreter.TypeName;
import org.eclipse.sirius.tools.internal.validation.AbstractConstraint;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;

import com.google.common.collect.Iterables;

/**
 * Validate that a TypeName has a package prefix.
 * 
 * 
 */
public class QualifiedTypeNameConstraint extends AbstractConstraint {

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
                    final Object value = eObj.eGet(feat);
                    if (value instanceof String) {
                        final TypeName className = TypeName.fromString((String) value);
                        if (!className.getPackagePrefix().some()) { // $NON-NLS-1$
                            return ctx.createFailureStatus(new Object[] { value, feat.getName() });
                        }
                    }
                }

            }
        }
        return ctx.createSuccessStatus();
    }

}
