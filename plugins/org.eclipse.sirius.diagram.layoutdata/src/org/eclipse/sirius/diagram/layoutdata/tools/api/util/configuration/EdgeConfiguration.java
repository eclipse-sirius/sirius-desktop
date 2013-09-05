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
package org.eclipse.sirius.diagram.layoutdata.tools.api.util.configuration;

/**
 * Configuration for edges.
 * 
 * @author dlecan
 */
public interface EdgeConfiguration {

    /**
     * Set the distance to use to compute edge bendpoints list layout.
     * 
     * @param distance
     *            Number such as distance (with
     *            {@link org.eclipse.draw2d.geometry.Point#getDistance(org.eclipse.draw2d.geometry.Point)}
     *            ) between original point and new point will be less or equal
     *            than the provided distance.
     */
    void setDistanceAroundPointsOfEdgeBendpointsList(double distance);

    /**
     * Returns the distance.
     * 
     * @return The distance.
     */
    double getDistanceAroundPointsOfEdgeBendpointsList();

}
