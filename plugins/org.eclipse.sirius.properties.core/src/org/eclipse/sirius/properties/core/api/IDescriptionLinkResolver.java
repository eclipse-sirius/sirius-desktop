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

import org.eclipse.eef.EEFViewDescription;

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
     * @param view
     *            The view
     * @param cache
     *            The cache of the EEF objects created for the Sirius objects
     */
    void resolve(EEFViewDescription view, DescriptionCache cache);
}
