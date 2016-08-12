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

import java.util.Map;

import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.layoutdata.AbstractLayoutData;
import org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData;
import org.eclipse.sirius.diagram.layoutdata.NodeLayoutData;
import org.eclipse.sirius.diagram.layoutdata.Point;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.LayoutDataHelperImpl;

/**
 * Helper to manage the layout data.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * @deprecated since Sirius 4.1.0. Use
 *             {@link org.eclipse.sirius.diagram.ui.tools.api.format.FormatDataHelper}
 *             instead.
 */
@Deprecated
public interface LayoutDataHelper {
    /**
     * The singleton instance of the LayoutDataHelper.
     */
    LayoutDataHelper INSTANCE = new LayoutDataHelperImpl();

    /**
     * Create a node layoutData.
     * 
     * @param gmfNode
     *            The corresponding GMF view
     * @param editPart
     *            The corresponding editPart
     * @param parentLayoutData
     *            The parent layout data
     * @return a new NodeLayoutData
     */
    NodeLayoutData createNodeLayoutData(Node gmfNode, IGraphicalEditPart editPart, NodeLayoutData parentLayoutData);

    /**
     * Create an edge layoutData with the information of edge.
     * 
     * @param gmfEdge
     *            The corresponding GMF view
     * @param connectionEditPart
     *            The corresponding edit part
     * @return a new NodeLayoutData
     */
    EdgeLayoutData createEdgeLayoutData(Edge gmfEdge, ConnectionEditPart connectionEditPart);

    /**
     * Create a label edge layoutData with the location of the label (the width
     * and height of this {@link NodeLayoutData} are not set).
     * 
     * @param labelNode
     *            the corresponding GMF view.
     * @return a new NodeLayoutData
     */
    NodeLayoutData createLabelLayoutData(Node labelNode);

    /**
     * Compute the absolute location of the <code>nodeLayoutData</code>.<BR>
     * Add recursively the location of its parent.
     * 
     * @param nodeLayoutData
     *            The concern nodeLayoutData
     * @return The absolute location
     */
    Point getAbsoluteLocation(NodeLayoutData nodeLayoutData);

    /**
     * Compute the relative location of the <code>nodeLayoutData</code> to the
     * figure of the edit part.<BR>
     * 
     * @param layoutData
     *            The concern nodeLayoutData
     * @param editPart
     *            The corresponding edit part
     * @return The relative location
     */
    Point getRelativeLocation(NodeLayoutData layoutData, IGraphicalEditPart editPart);

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
     * Filter collection to get only root layout data.
     * 
     * @param collection
     *            Collection to filter.
     * @return Filtered collection.
     */
    Map<? extends LayoutDataKey, ? extends AbstractLayoutData> getRootLayoutData(Map<? extends LayoutDataKey, ? extends AbstractLayoutData> collection);

    /**
     * Create key from node layout data.
     * 
     * @param layoutData
     *            Layout data.
     * @return Created key.
     */
    LayoutDataKey createKey(AbstractLayoutData layoutData);
}
