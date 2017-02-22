/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.palette;

import org.eclipse.sirius.diagram.description.Layer;

/**
 * An interface to implement for palette elements which need to knows layer
 * which contribute to them.
 * 
 * @author mchauvin
 */
public interface ILayerContributionRegister {

    /**
     * Add a layer as section contributor.
     * 
     * @param layer
     *            the layer to add
     */
    void addLayer(Layer layer);

    /**
     * Remove a layer as section contributor.
     * 
     * @param layer
     *            the layer to remove
     */
    void removeLayer(Layer layer);

    /**
     * Check if an activated layer contribute to this section drawer.
     * 
     * @return ..
     */
    boolean isEmptyOfContributors();

}
