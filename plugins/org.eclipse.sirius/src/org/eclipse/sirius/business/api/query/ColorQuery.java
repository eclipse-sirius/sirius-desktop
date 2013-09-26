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
package org.eclipse.sirius.business.api.query;

import org.eclipse.sirius.viewpoint.description.ColorDescription;
import org.eclipse.sirius.viewpoint.description.SystemColor;
import org.eclipse.sirius.viewpoint.description.UserColor;

/**
 * A query for color.
 * 
 * @author mchauvin
 */
public class ColorQuery {

    private ColorDescription color;

    /**
     * Instantiate a new query.
     * 
     * @param color
     *            the color
     */
    public ColorQuery(ColorDescription color) {
        this.color = color;
    }

    /**
     * Get the label of the color.
     * 
     * @return <code>null</code> if it can't be computed
     */
    public String getLabel() {
        String label = null;
        if (color instanceof UserColor)
            label = ((UserColor) color).getName();
        else if (color instanceof SystemColor)
            label = ((SystemColor) color).getName();
        return label;
    }

}
