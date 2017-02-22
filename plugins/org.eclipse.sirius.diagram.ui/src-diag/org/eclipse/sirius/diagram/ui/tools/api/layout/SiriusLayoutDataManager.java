/*******************************************************************************
 * Copyright (c) 2009, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.layout;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.layoutdata.AbstractLayoutData;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * An interface for all the SiriusLayoutDataManager for mapping key (
 * {@link LayoutDataKey}) and layoutData ({@link AbstractLayoutData}).
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * @deprecated Since Sirius 4.1.0. Use
 *             {@link org.eclipse.sirius.diagram.ui.tools.api.format.SiriusFormatDataManager}
 *             instead.
 */
@Deprecated
public interface SiriusLayoutDataManager {

    /**
     * Get the layout data corresponding to the key.
     * 
     * @param key
     *            The key
     * @return the layout data corresponding to the key or null if not found.
     */
    AbstractLayoutData getLayoutData(LayoutDataKey key);

    /**
     * Add a layout data according to the key.
     * 
     * @param key
     *            The key
     * @param layoutData
     *            The layout data
     */
    void addLayoutData(LayoutDataKey key, AbstractLayoutData layoutData);

    /**
     * Create a key corresponding to the semanticDecorator and available for
     * this manager.
     * 
     * @param semanticDecorator
     *            the semantic decorator
     * @return a new key corresponding to the semanticDecorator and available
     *         for this manager.
     */
    LayoutDataKey createKey(DSemanticDecorator semanticDecorator);

    /**
     * Store the layout data for this edit part and all it's children.
     * 
     * @param rootEditPart
     *            the root of the editParts to store.
     */
    void storeLayoutData(IGraphicalEditPart rootEditPart);

    /**
     * Apply the current format data (layout data and style data) to the
     * rootEditPart.
     * 
     * @param rootEditPart
     *            the root edit from which we would try to apply the current
     *            stored format
     */
    void applyFormat(IGraphicalEditPart rootEditPart);

    /**
     * Apply the current layout data to the rootEditPart.
     * 
     * @param rootEditPart
     *            the root edit from which we would try to apply the current
     *            stored layout
     */
    void applyLayout(IGraphicalEditPart rootEditPart);

    /**
     * Apply the current style data to the rootEditPart.
     * 
     * @param rootEditPart
     *            the root edit from which we would try to apply the current
     *            stored style
     */
    void applyStyle(IGraphicalEditPart rootEditPart);

    /**
     * Check if the manager contains data.
     * 
     * @return true if the manager contains data, false otherwise.
     */
    boolean containsData();

    /**
     * Remove all the stored layout data.
     */
    void clearLayoutData();

}
