/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.contribution;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.description.contribution.EObjectReference;
import org.eclipse.sirius.ext.base.Option;

/**
 * Resolves {@link EObjectReference}s.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public interface ReferenceResolver {
    /**
     * Resolves an {@link EObjectReference} to a concrete {@link EObject}.
     * 
     * @param ref
     *            the reference to resolve.
     * @param context
     *            the context of the resolution, as a set of variables bindings.
     * @return the {@link EObject} denoted by the reference, if it could be
     *         determined.
     */
    Option<EObject> resolve(EObjectReference ref, Map<String, Object> context);
}
