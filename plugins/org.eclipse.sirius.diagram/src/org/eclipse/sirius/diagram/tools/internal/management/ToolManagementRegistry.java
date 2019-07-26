/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.tools.internal.management;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.eclipse.sirius.diagram.tools.api.management.ToolFilter;

/**
 * A registry for ToolFilter contributions.
 */
public final class ToolManagementRegistry {

    /**
     * The singleton instance of the ResourceStrategyRegistry.
     */
    private static final ToolManagementRegistry INSTANCE = new ToolManagementRegistry();

    private Collection<ToolFilter> providedToolFilters = new LinkedHashSet<>();

    private ToolManagementRegistry() {
    }

    public static ToolManagementRegistry getInstance() {
        return INSTANCE;
    }

    /**
     * Add a ToolFilter to the registry.
     * 
     * @param toolFilter
     *            the ToolFilter
     */
    public void addToolFilter(ToolFilter toolFilter) {
        providedToolFilters.add(toolFilter);
    }

    /**
     * Removes a ToolFilter from the registry.
     * 
     * @param toolFilter
     *            the ToolFilter
     */
    public void removeToolFilter(ToolFilter toolFilter) {
        providedToolFilters.remove(toolFilter);
    }

    /**
     * Get all ToolFilters provided by the registry.
     * 
     * @return the provided ToolFilters
     */
    public Collection<ToolFilter> getProvidedToolFilters() {
        return providedToolFilters;
    }

    /**
     * Removes all extensions from the registry. This will be called at plugin stopping.
     */
    public void dispose() {
        providedToolFilters.clear();
    }

}
