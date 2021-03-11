/*******************************************************************************
 * Copyright (c) 2021 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.tools.internal.validation.constraints;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.sirius.table.metamodel.table.description.CellUpdater;

/**
 * Constraint ensuring that there is not both CellEditor tool and LabelEdit tool in a CellUpdater.
 * 
 * @author gplouhinec
 *
 */
public class BothCellEditorEditLabelToolConstraint extends AbstractModelConstraint {

    /**
     * {@inheritDoc}
     */
    @Override
    public IStatus validate(IValidationContext ctx) {
        EObject eObj = ctx.getTarget();
        EMFEventType eventType = ctx.getEventType();
        // In the case of batch mode.
        if (eventType == EMFEventType.NULL && eObj != null) {
            if (eObj instanceof CellUpdater) {
                CellUpdater updater = (CellUpdater) eObj;
                if (updater.getDirectEdit() != null && updater.getCellEditor() != null) {
                    return ctx.createFailureStatus(new Object[] { updater });
                }
            }
        }
        return ctx.createSuccessStatus();
    }

}
