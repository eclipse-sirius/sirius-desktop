/*******************************************************************************
 * Copyright (c) 2016, 2017 Obeo.
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
package org.eclipse.sirius.properties.core.api;

import org.eclipse.emf.ecore.EObject;

/**
 * Implementations of this interface will be used to resolve links in the
 * converted description.
 * 
 * @author sbegaudeau
 */
public interface IDescriptionLinkResolver {
    /**
     * Resolves the link in the view.
     * 
     * @param eObject
     *            The eObject
     * @param cache
     *            The cache of the input objects created for the output objects
     */
    void resolve(EObject eObject, TransformationCache cache);
}
