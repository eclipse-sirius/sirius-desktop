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
package org.eclipse.sirius.diagram.ui.tools.api.format;

import java.util.Map;

import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.formatdata.AbstractFormatData;
import org.eclipse.sirius.diagram.formatdata.EdgeFormatData;
import org.eclipse.sirius.diagram.formatdata.NodeFormatData;
import org.eclipse.sirius.diagram.formatdata.Point;
import org.eclipse.sirius.diagram.ui.tools.internal.format.FormatDataHelperImpl;

/**
 * Helper to manage the format data.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public interface FormatDataHelper {
    /**
     * The singleton instance of the FormatDataHelper.
     */
    FormatDataHelper INSTANCE = new FormatDataHelperImpl();

    /**
     * Create a node formatData.
     * 
     * @param gmfNode
     *            The corresponding GMF view
     * @param editPart
     *            The corresponding editPart
     * @param parentFormatData
     *            The parent format data
     * @return a new NodeFormatData
     */
    NodeFormatData createNodeFormatData(Node gmfNode, IGraphicalEditPart editPart, NodeFormatData parentFormatData);

    /**
     * Create an edge formatData with the information of edge.
     * 
     * @param gmfEdge
     *            The corresponding GMF view
     * @param connectionEditPart
     *            The corresponding edit part
     * @return a new NodeFormatData
     */
    EdgeFormatData createEdgeFormatData(Edge gmfEdge, ConnectionEditPart connectionEditPart);

    /**
     * Create a label edge formatData with the location of the label (the width
     * and height of this {@link NodeFormatData} are not set).
     * 
     * @param labelNode
     *            the corresponding GMF view.
     * @return a new NodeFormatData
     */
    NodeFormatData createLabelFormatData(Node labelNode);

    /**
     * Compute the absolute location of the <code>nodeFormatData</code>.<BR>
     * Add recursively the location of its parent.
     * 
     * @param nodeFormatData
     *            The concern nodeFormatData
     * @return The absolute location
     */
    Point getAbsoluteLocation(NodeFormatData nodeFormatData);

    /**
     * Compute the relative location of the <code>nodeFormatData</code> to the
     * figure of the edit part.<BR>
     * 
     * @param formatData
     *            The concern nodeFormatData
     * @param editPart
     *            The corresponding edit part
     * @return The relative location
     */
    Point getRelativeLocation(NodeFormatData formatData, IGraphicalEditPart editPart);

    /**
     * Creates a new Point which is translated by the values of the provided
     * Point.
     * 
     * @param originalPoint
     *            The point to translate.
     * @param pt
     *            Point which provides the translation amounts.
     * @return A new Point
     */
    Point getTranslated(Point originalPoint, org.eclipse.draw2d.geometry.Point pt);

    /**
     * Filter collection to get only root format data.
     * 
     * @param collection
     *            Collection to filter.
     * @return Filtered collection.
     */
    Map<? extends FormatDataKey, ? extends AbstractFormatData> getRootFormatData(Map<? extends FormatDataKey, ? extends AbstractFormatData> collection);

    /**
     * Create key from node format data.
     * 
     * @param formatData
     *            Format data.
     * @return Created key.
     */
    FormatDataKey createKey(AbstractFormatData formatData);
}
