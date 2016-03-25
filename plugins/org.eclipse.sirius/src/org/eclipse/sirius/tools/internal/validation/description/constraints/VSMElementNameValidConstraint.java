/*******************************************************************************
 * Copyright (c) 2014, 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 * Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tools.internal.validation.description.constraints;

import java.text.MessageFormat;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.viewpoint.Messages;
import org.eclipse.sirius.viewpoint.description.AbstractVariable;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;

/**
 * Validation constraint to check that vsm elements name are not null or empty.
 * 
 * @author <a href="mailto:jessy.mallet@obeo.fr">Jessy Mallet</a>
 */
public class VSMElementNameValidConstraint extends AbstractCommonToolToAppliedOnConstraint {

    @Override
    public IStatus validate(IValidationContext ctx) {
        IStatus status = null;
        EObject target = ctx.getTarget();
        if (target instanceof IdentifiedElement) {
            String elementName = ((IdentifiedElement) target).getName();
            if (StringUtil.isEmpty(elementName)) {
                status = ctx.createFailureStatus(MessageFormat.format(Messages.VSMElementNameValidConstraint_invalidNameErrorMsg, getPath(target)));
            }
        } else if (target instanceof AbstractVariable) {
            String elementName = ((AbstractVariable) target).getName();
            if (StringUtil.isEmpty(elementName)) {
                status = ctx.createFailureStatus(MessageFormat.format(Messages.VSMElementNameValidConstraint_invalidNameErrorMsg, getPath(target)));
            }
        }
        if (status == null) {
            status = ctx.createSuccessStatus();
        }
        return status;
    }

}
