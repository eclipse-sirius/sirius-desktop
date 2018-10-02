/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.api.componentization;

import org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * This interface specifies a filter to register if you need to filter the
 * accesible Viewpoints.
 * 
 * This interface is intended to be implemented.
 * 
 * @author mchauvin
 */
public interface ViewpointRegistryFilter {

    /**
     * Check if a viewpoint must be filtered.
     * 
     * @param viewpoint
     *            the viewpoint to test
     * @return return true if the viewpoint should be filtered, false otherwise)
     */
    boolean filter(Viewpoint viewpoint);

    /**
     * Check if a representation description extension must be filtered.
     * 
     * @param representationExtensionDescription
     *            the representation description extension to test
     * @return return true if the viewpoint should be filtered, false otherwise)
     */
    boolean filter(RepresentationExtensionDescription representationExtensionDescription);

    /**
     * Get the unique identifier for a filter.
     * 
     * @return an unique identifier. All instances of the same class sould use
     *         the same identifier
     */
    String getId();

}
