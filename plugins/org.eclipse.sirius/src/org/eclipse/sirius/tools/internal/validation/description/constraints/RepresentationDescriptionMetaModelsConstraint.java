/*******************************************************************************
 * Copyright (c) 2016, 2021 Obeo.
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

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.tools.internal.validation.AbstractConstraint;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription;

/**
 * Constraint ensuring that there is at least one meta-model associated to RepresentationDescription.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class RepresentationDescriptionMetaModelsConstraint extends AbstractConstraint {

    @Override
    public IStatus validate(IValidationContext ctx) {
        final EObject target = ctx.getTarget();

        if (target instanceof RepresentationDescription || target instanceof RepresentationExtensionDescription) {
            Collection<EPackage> availableMetamodels = getAvailableMetamodels(target);
            if (availableMetamodels.isEmpty()) {
                return ctx.createFailureStatus(Messages.RepresentationDescriptionMetaModelsConstraint_noMetaModel);
            }
        }

        return ctx.createSuccessStatus();
    }

    /**
     * Return the Meta-models associated to the containing parent description.
     * 
     * @param parentDescription
     *            the parentDescription.
     * @return the available meta-models.
     */
    private Collection<EPackage> getAvailableMetamodels(final EObject parentDescription) {
        Collection<EPackage> metamodel = null;
        if (parentDescription instanceof RepresentationDescription) {
            metamodel = ((RepresentationDescription) parentDescription).getMetamodel();
        } else if (parentDescription instanceof RepresentationExtensionDescription) {
            metamodel = ((RepresentationExtensionDescription) parentDescription).getMetamodel();
        }
        return metamodel;
    }
}
