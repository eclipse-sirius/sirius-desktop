/*******************************************************************************
 * Copyright (c) 2016, 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
