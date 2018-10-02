/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.api.componentization;

import java.util.Collection;

import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * A listener to know {@link DiagramDescriptionMappingsManager} recompute
 * mappings.
 * 
 * @author mchauvin
 * @since 0.9.0
 */
public interface DiagramDescriptionMappingsManagerListener {

    /**
     * This method is called when mappings have been computed.
     * @param enabledViewpoints 
     */
    void mappingsComputed(Collection<Viewpoint> enabledViewpoints);

    /**
     * This method is called when the description mappings manager will be
     * disposed.
     */
    void dispose();

}
