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
package org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.palette;

import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.diagram.description.Layer;

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
    void hideLayer(Layer layer);

    /**
     * Show all tools provided by a layer.
     * 
     * @param layer
     *            the layer
     */
    void showLayer(Layer layer);

    /**
     * Add a filter (if not already added) to hide a tool .
     * 
     * @param toolFilter
     *            the filter to add
     */
    void addToolFilter(ToolFilter toolFilter);

    /**
     * Remove a filter to hide a tool.
     * 
     * @param toolFilter
     *            the filter to remove
     */
    void removeToolFilter(ToolFilter toolFilter);

    /**
     * Update the palette, to take in consideration the currently associated
     * filters.
     * <p>
     * Calling update(diagram) is equivalent to update(diagram, false);
     * </p>
     * 
     * @param diagram
     *            the diagram
     */
    void update(Diagram diagram);

    /**
     * Update the palette, to take in consideration the currently associated
     * filters.
     * 
     * @param diagram
     *            the diagram
     * @param clean
     *            <code>true</code> to clean the palette before the update.
     */
    void update(Diagram diagram, boolean clean);

    /**
     * Dispose cleanly this palette manager.
     */
    void dispose();

    /**
     * Returns <code>true</code> if this palette manager has been disposed, and
     * <code>false</code> otherwise.
     * <p>
     * This method gets the dispose state for the palette manager. When a
     * palette manager has been disposed, it is an error to invoke any other
     * method (except {@link #dispose()}) using the palette manager.
     * </p>
     * 
     * @return <code>true</code> when the palette manager is disposed and
     *         <code>false</code> otherwise
     */
    boolean isDisposed();

}
