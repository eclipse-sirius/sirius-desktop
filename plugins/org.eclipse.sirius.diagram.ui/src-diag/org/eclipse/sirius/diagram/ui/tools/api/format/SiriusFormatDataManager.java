/*******************************************************************************
 * Copyright (c) 2009, 2022 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.api.format;

import java.util.List;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.formatdata.AbstractFormatData;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

/**
 * An interface for all the SiriusFormatDataManager for mapping key ( {@link FormatDataKey}) and formatData (
 * {@link AbstractFormatData}).
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public interface SiriusFormatDataManager {

    /**
     * Get the format data with the best corresponding to the key.
     * 
     * @param key
     *            The key
     * @param mapping
     *            the mapping used to discriminate returned {@link AbstractFormatData} when the key is attached to more
     *            than one format.
     * @return the format data corresponding to the key or null if not found.
     */
    AbstractFormatData getFormatData(FormatDataKey key, RepresentationElementMapping mapping);

    /**
     * Add a format data according to the key and the mapping information.
     * 
     * @param key
     *            The key
     * @param mapping
     *            the mapping used to discriminate returned {@link AbstractFormatData} when the key is attached to more
     *            than one format.
     * @param formatData
     *            The format data associated to the key and mapping.
     */
    void addFormatData(FormatDataKey key, RepresentationElementMapping mapping, AbstractFormatData formatData);

    /**
     * Create a key corresponding to the semanticDecorator and available for this manager.
     * 
     * @param semanticDecorator
     *            the semantic decorator
     * @return a new key corresponding to the semanticDecorator and available for this manager.
     */
    FormatDataKey createKey(DSemanticDecorator semanticDecorator);

    /**
     * Store the format data for this edit part and all it's children.
     * 
     * @param rootEditPart
     *            the root of the editParts to store.
     */
    void storeFormatData(IGraphicalEditPart rootEditPart);

    /**
     * Apply the current format data to the rootEditPart.
     * 
     * @param rootEditPart
     *            the root edit from which we would try to apply the current stored format
     * @deprecated Use {@link #applyFormat(IGraphicalEditPart, boolean)} instead. A default implementation is provided
     *             to use the previous existing mode "Similar locations in absolute coordinates". As consequence, you
     *             can already remove this method from your code.
     */
    @Deprecated
    default void applyFormat(IGraphicalEditPart rootEditPart) {
        applyFormat(rootEditPart, true);
    }

    /**
     * Apply the current layout data to the rootEditPart.
     * 
     * @param rootEditPart
     *            the root edit from which we would try to apply the current stored format
     * @deprecated Use {@link #applyLayout(IGraphicalEditPart, boolean)} instead. A default implementation is provided
     *             to use the previous existing mode "Similar locations in absolute coordinates". As consequence, you
     *             can already remove this method from your code.
     */
    @Deprecated
    default void applyLayout(IGraphicalEditPart rootEditPart) {
        applyLayout(rootEditPart, true);
    }

    /**
     * Apply the current format data to the rootEditPart.
     * 
     * @param rootEditPart
     *            the root edit from which we would try to apply the current stored format
     * @param absoluteCoordinates
     *            true if the paste format must apply the layout with absolute coordinates, false if the paste must
     *            apply layout with a conservative origin of the bounding box containing the elements to layout
     */
    void applyFormat(IGraphicalEditPart rootEditPart, boolean absoluteCoordinates);

    /**
     * Apply the current format data only to the subpart of the children of the parentEditPart. The current format is
     * not applied on parentEditPart.
     * 
     * @param parentEditPart
     *            the common parent of <code>childrenSubpart</code>
     * @param childrenSubpart
     *            a subpart of the children of parentEditPart
     * @param absoluteCoordinates
     *            true if the paste format must apply the layout with absolute coordinates, false if the paste must
     *            apply layout with a conservative origin of the bounding box containing the elements to layout
     */
    void applyFormat(IGraphicalEditPart parentEditPart, List<IGraphicalEditPart> childrenSubpart, boolean absoluteCoordinates);

    /**
     * Apply the current layout data to the rootEditPart.
     * 
     * @param rootEditPart
     *            the root edit from which we would try to apply the current stored format
     * @param absoluteCoordinates
     *            true if the paste format must apply the layout with absolute coordinates, false if the paste must
     *            apply layout with a conservative origin of the bounding box containing the elements to layout
     */
    void applyLayout(IGraphicalEditPart rootEditPart, boolean absoluteCoordinates);

    /**
     * Apply the current layout data only to the subpart of the children of the parentEditPart. The current layout is
     * not applied on parentEditPart.
     * 
     * @param parentEditPart
     *            the common parent of <code>childrenSubpart</code>
     * @param childrenSubpart
     *            a subpart of the children of parentEditPart
     * @param absoluteCoordinates
     *            true if the paste format must apply the layout with absolute coordinates, false if the paste must
     *            apply layout with a conservative origin of the bounding box containing the elements to layout
     */
    void applyLayout(IGraphicalEditPart rootEditPart, List<IGraphicalEditPart> childrenSubpart, boolean absoluteCoordinates);

    /**
     * Apply the current style data to the rootEditPart.
     * 
     * @param rootEditPart
     *            the root edit from which we would try to apply the current stored style
     */
    void applyStyle(IGraphicalEditPart rootEditPart);

    /**
     * Check if the manager contains data.
     * 
     * @return true if the manager contains data, false otherwise.
     */
    boolean containsData();

    /**
     * Remove all the stored format data.
     */
    void clearFormatData();

}
