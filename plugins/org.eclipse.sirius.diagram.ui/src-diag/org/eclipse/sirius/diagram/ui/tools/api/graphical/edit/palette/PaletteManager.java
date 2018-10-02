/*******************************************************************************
 * Copyright (c) 2010, 2018 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.palette;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.tools.api.management.ToolFilter;
import org.eclipse.sirius.diagram.tools.api.management.ToolManagement;

/**
 * Manage the palette and the associated filters.
 * 
 * @author mchauvin
 * @since 0.9.0
 * @noimplement This interface is not intended to be implemented by clients.
 */
public interface PaletteManager {

    /**
     * Hide all tools provided by a layer.
     * 
     * @param layer
     *            the layer
     */
    @Deprecated
    void hideLayer(Layer layer);

    /**
     * Show all tools provided by a layer.
     * 
     * @deprecated layer activation/deactivation in now handled by {@link ToolManagement}.
     * @param layer
     *            the layer
     */
    @Deprecated
    void showLayer(Layer layer);

    /**
     * Add a filter (if not already added) to hide a tool .
     * 
     * @param toolFilter
     *            the filter to add
     * @deprecated replaced by {@link ToolManagement#addToolFilter(ToolFilter)}
     */
    @Deprecated
    void addToolFilter(ToolFilter toolFilter);

    /**
     * Remove a filter to hide a tool.
     * 
     * @param toolFilter
     *            the filter to remove
     * 
     * @deprecated replaced by {@link ToolManagement#removeToolFilter(ToolFilter)}
     */
    @Deprecated
    void removeToolFilter(ToolFilter toolFilter);

    /**
     * Update the palette, to take in consideration the currently associated filters.
     * <p>
     * Calling update(diagram) is equivalent to update(diagram, false);
     * </p>
     * 
     * @param diagram
     *            the diagram
     */
    void update(DDiagram diagram);

    /**
     * Update the palette, to take in consideration the currently associated filters.
     * 
     * @param diagram
     *            the diagram
     * @param clean
     *            <code>true</code> to clean the palette before the update.
     */
    void update(DDiagram diagram, boolean clean);

    /**
     * Dispose cleanly this palette manager.
     */
    void dispose();

    /**
     * Returns <code>true</code> if this palette manager has been disposed, and <code>false</code> otherwise.
     * <p>
     * This method gets the dispose state for the palette manager. When a palette manager has been disposed, it is an
     * error to invoke any other method (except {@link #dispose()}) using the palette manager.
     * </p>
     * 
     * @return <code>true</code> when the palette manager is disposed and <code>false</code> otherwise
     */
    boolean isDisposed();

}
