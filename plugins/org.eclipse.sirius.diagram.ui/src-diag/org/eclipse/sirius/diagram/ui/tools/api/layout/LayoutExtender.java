/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.layout;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.graph.Node;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ListItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.ExtendableLayoutProvider;

import com.google.common.collect.Iterables;

/**
 * Class providing extended service for {@link ExtendableLayoutProvider}s
 * implementing the {@link ExtendableLayoutProvider} contract.
 * 
 * @author cbrun
 * 
 */
public class LayoutExtender {

    private final Map<IGraphicalEditPart, Rectangle> updatedLocations = new WeakHashMap<IGraphicalEditPart, Rectangle>();

    private final ExtendableLayoutProvider layouter;

    /**
     * Create a new extender.
     * 
     * @param layouter
     *            the layouter to extend.
     */
    public LayoutExtender(final ExtendableLayoutProvider layouter) {
        this.layouter = layouter;
    }

    /**
     * return all the updated bounds for each edit parts.
     * 
     * @return all the updated bounds for each edit parts.
     */
    public Map<IGraphicalEditPart, Rectangle> getUpdatedBounds() {
        return updatedLocations;
    }

    /**
     * Notify the extender that edit parts layout is starting.
     */
    public void startLayouting() {
        this.updatedLocations.clear();

    }

    /**
     * Filter the relevant connections.
     * 
     * @param editPartToNodeDict
     *            dict of edit parts.
     * @param list
     *            list to filter.
     * @return filtered list of connections.
     */
    public List getRelevantConnections(final Hashtable editPartToNodeDict, final List list) {
        final Iterator iterConnections = list.iterator();
        final boolean shouldHandleListItems = layouter.handleConnectableListItems();
        while (iterConnections.hasNext()) {
            final Object next = iterConnections.next();
            if (next instanceof ConnectionEditPart) {
                final ConnectionEditPart poly = (ConnectionEditPart) next;
                EditPart from = poly.getSource();
                EditPart to = poly.getTarget();
                if (from instanceof IBorderItemEditPart) {
                    from = from.getParent();
                } else if (shouldHandleListItems && from instanceof ListItemEditPart) {
                    from = getFirstAnscestorinNodesMap(from, editPartToNodeDict);
                }
                if (to instanceof IBorderItemEditPart) {
                    to = to.getParent();
                } else if (shouldHandleListItems && to instanceof ListItemEditPart) {
                    to = getFirstAnscestorinNodesMap(to, editPartToNodeDict);
                }
                if (from == null || to == null) {
                    iterConnections.remove();
                }
            }
        }
        return list;
    }

    /**
     * Called to keep track of every location change.
     * 
     * @param nodes
     *            nodes to change.
     * @param diff
     *            diff with the original location.
     */
    public void keepLocationChanges(final List nodes, final Point diff) {
        for (Node node : Iterables.filter(nodes, Node.class)) {
            if (node.data instanceof ShapeEditPart) {
                final IGraphicalEditPart gep = (IGraphicalEditPart) node.data;
                final Rectangle intrinsicBounds = gep.getFigure().getBounds();
                final Rectangle nodeExt = layouter.provideNodeMetrics(node);
                final Rectangle newBounds = new Rectangle(nodeExt.x + diff.x, nodeExt.y + diff.y, intrinsicBounds.width, intrinsicBounds.height);
                updatedLocations.put(gep, newBounds);
            }
        }
    }

    /**
     * Filter edges for layouting.
     * 
     * @param selectedObjects
     *            objects to layout.
     * @param editPartToNodeDict
     *            dict of editparts to nodes.
     * @return the filtered list of elements to layout.
     */
    public List filterEdges(final List selectedObjects, final Map editPartToNodeDict) {
        final List tmp = new LinkedList(selectedObjects);
        final Iterator iterConnections = tmp.iterator();
        final boolean shouldHandleListItems = layouter.handleConnectableListItems();
        while (iterConnections.hasNext()) {
            final Object next = iterConnections.next();
            if (next instanceof ConnectionEditPart) {
                final ConnectionEditPart poly = (ConnectionEditPart) next;
                EditPart from = poly.getSource();
                EditPart to = poly.getTarget();
                if (from instanceof IBorderItemEditPart) {
                    from = from.getParent();
                } else if (shouldHandleListItems && from instanceof ListItemEditPart) {
                    from = getFirstAnscestorinNodesMap(from, editPartToNodeDict);
                }
                if (to instanceof IBorderItemEditPart) {
                    to = to.getParent();
                } else if (shouldHandleListItems && to instanceof ListItemEditPart) {
                    to = getFirstAnscestorinNodesMap(to, editPartToNodeDict);
                }
                if (from == null || to == null) {
                    iterConnections.remove();
                }
            }
        }
        return tmp;
    }

    private EditPart getFirstAnscestorinNodesMap(final EditPart editPart, final Map editPartToNodeDict) {
        EditPart ancestor = editPart;
        while (ancestor != null) {
            if (editPartToNodeDict.get(ancestor) != null) {
                return ancestor;
            }
            ancestor = ancestor.getParent();
        }
        return null;
    }

}
