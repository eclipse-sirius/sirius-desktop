/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tools.internal.validation;

import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.sirius.business.api.query.ResourceQuery;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription;

/**
 * The base class of viewpoint constraints.
 * 
 * @author ymortier
 */
public abstract class AbstractConstraint extends AbstractModelConstraint {

    /**
     * Return <code>true</code> if the specified object is contained in a
     * Viewpoint that knows the MM to use.
     * 
     * @param instance
     *            the instance.
     * @return <code>true</code> if the specified object is contained in a
     *         Viewpoint that knows the MM to use.
     */
    protected boolean isElementContainedInAKnownMetamodel(final EObject instance) {
        boolean aware = false;
        final EObject desc = getParentDescription(instance);
        if (desc instanceof RepresentationDescription) {
            aware = ((RepresentationDescription) desc).getMetamodel() != null;
        } else if (desc instanceof RepresentationExtensionDescription) {
            aware = ((RepresentationExtensionDescription) desc).getMetamodel() != null;
        }
        return aware;
    }

    /**
     * Return the parent description of different dialects if known.
     * 
     * @param instance
     *            the instance.
     * @return the parent description (or null if not known)
     */
    protected EObject getParentDescription(EObject instance) {
        return null;
    }

    /**
     * <p>
     * Check that the expression contains variables that are present in
     * <code>variablesNames</code>. Return a failure status if the acceleo
     * expression contains an invalid variable.
     * </p>
     * <p>
     * Return a success status if <code>expression</code> is <code>null</code>
     * or empty.
     * 
     * @param ctx
     *            the validation context.
     * @param expression
     *            the expression to test.
     * @param variablesNames
     *            the variables.
     * @param eObj
     *            the current {@link EObject}.
     * @param featureName
     *            the name of the feature that has the expression.
     * @return a success status if the expression doesn't use invalid variable,
     *         return a failure status otherwise.
     */
    protected IStatus checkVariables(final IValidationContext ctx, final String expression, final Set<String> variablesNames, final EObject eObj, final String featureName) {
        return ctx.createSuccessStatus();
    }

    /**
     * Tell whether the constraint rule should be evaluated or not.
     * 
     * @param instance
     *            any instance from the AIR model.
     * @return true if the constraint should be evaluated, false otherwise.
     */
    protected boolean shouldValidate(final EObject instance) {
        boolean result = true;
        Resource instanceResource = instance.eResource();
        if (instanceResource != null && instanceResource.getResourceSet() != null) {
            final Iterator<Resource> it = instanceResource.getResourceSet().getResources().iterator();
            while (result && it.hasNext()) {
                final Resource resource = it.next();
                ResourceQuery resourceQuery = new ResourceQuery(resource);
                if (resourceQuery.isRepresentationsResource()) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
}
