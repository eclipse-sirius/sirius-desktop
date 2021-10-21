/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.table.business.api.query;

import java.util.Objects;

import org.eclipse.sirius.table.metamodel.table.description.StyleUpdater;
import org.eclipse.sirius.viewpoint.description.ColorDescription;

/**
 * A class aggregating all the queries (read-only!) having a
 * {@link StyleUpdater} as starting point.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class StyleUpdaterQuery {

    private final StyleUpdater styleUpdater;

    /**
     * Creates a new query.
     * 
     * @param styleUpdater
     *            the styleUpdater to query.
     */
    public StyleUpdaterQuery(StyleUpdater styleUpdater) {
        Objects.requireNonNull(styleUpdater);
        this.styleUpdater = styleUpdater;
    }

    /**
     * Check if this color description is the default background color
     * description of this style updater.
     * 
     * @param colorDescription
     *            The color description to check
     * @return true is this is the default, false otherwise.
     */
    public boolean isDefaultBackgroundColor(ColorDescription colorDescription) {
        if (styleUpdater.getDefaultBackground() != null && styleUpdater.getDefaultBackground().getBackgroundColor() != null) {
            return styleUpdater.getDefaultBackground().getBackgroundColor().equals(colorDescription);
        }
        return false;
    }

    /**
     * Check if this color description is the default foreground color
     * description of this style updater.
     * 
     * @param colorDescription
     *            The color description to check
     * @return true is this is the default, false otherwise.
     */
    public boolean isDefaultForegroundColor(ColorDescription colorDescription) {
        if (styleUpdater.getDefaultForeground() != null && styleUpdater.getDefaultForeground().getForeGroundColor() != null) {
            return styleUpdater.getDefaultForeground().getForeGroundColor().equals(colorDescription);
        }
        return false;
    }
}
