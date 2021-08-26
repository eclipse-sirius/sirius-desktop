/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
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
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.description.tool.ContainerDropDescription;
import org.eclipse.sirius.viewpoint.description.tool.DragSource;

/**
 * Checks that a Drag'n'Drop tool which accepts elements from the Model Content
 * view has an explicit precondition to check the type of the dragged element.
 * 
 * @author pcdavid
 */
public class PreconditionForDndToolsConstraint extends AbstractModelConstraint {

    /**
     * {@inheritDoc}
     */
    @Override
    public IStatus validate(IValidationContext ctx) {
        EObject eObj = ctx.getTarget();
        EMFEventType eventType = ctx.getEventType();
        // In the case of batch mode.

        if (eventType == EMFEventType.NULL) {
            Resource eObjResource = eObj.eResource();
            if (eObjResource != null && eObjResource.getResourceSet() != null && eObj instanceof ContainerDropDescription) {
                ContainerDropDescription cdd = (ContainerDropDescription) eObj;
                if (acceptsArbitraryElementsFromModel(cdd) && StringUtil.isEmpty(cdd.getPrecondition())) {
                    return ctx.createFailureStatus(new Object[] { new IdentifiedElementQuery(cdd).getLabel() });
                }
            }
        }
        return ctx.createSuccessStatus();
    }

    private boolean acceptsArbitraryElementsFromModel(ContainerDropDescription cdd) {
        return cdd.getDragSource() == DragSource.PROJECT_EXPLORER_LITERAL || cdd.getDragSource() == DragSource.BOTH_LITERAL;
    }
}
