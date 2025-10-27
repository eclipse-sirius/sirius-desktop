/******************************************************************************
 * Copyright (c) 2002, 2023 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation
 *    Mariot Chauvin <mariot.chauvin@obeo.fr> - bug 243888
 *    Obeo - parts copied from org.eclipse.gmf.runtime.diagram.ui.providers.internal.DefaultProvider
 *           and org.eclipse.gmf.runtime.diagram.ui.providers.internal.CompositeLayoutProvider
 *           and adapted for Sirius.
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.layout.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.graph.DirectedGraph;
import org.eclipse.draw2d.graph.DirectedGraphLayout;
import org.eclipse.draw2d.graph.Edge;
import org.eclipse.draw2d.graph.EdgeList;
import org.eclipse.draw2d.graph.Node;
import org.eclipse.draw2d.graph.NodeList;
import org.eclipse.draw2d.graph.Subgraph;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GroupEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.providers.internal.CompositeLayoutProvider;
import org.eclipse.gmf.runtime.draw2d.ui.internal.graph.AdvancedSubGraph;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.MapModeUtil;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.business.api.query.EditPartQuery;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutExtender;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.ExtendableLayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.ArrangeAllWithAutoSize;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.AutoSizeAndRegionAwareGraphLayout;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.DiagramLayoutCustomization;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

/**
 * Abstract base class to factor out the common code between
 * {@link CompositeLeftRightLayoutProvider},
 * {@link CompositeDownTopLayoutProvider} and
 * {@link CompositeTopDownLayoutProvider}.
 * 
 * @author pcdavid
 */
@SuppressWarnings("restriction")
public abstract class AbstractCompositeLayoutProvider extends CompositeLayoutProvider implements ExtendableLayoutProvider {

    private final LayoutExtender extender = new LayoutExtender(this);

    private final DiagramLayoutCustomization padder = new DiagramLayoutCustomization();

    private final ArrangeAllWithAutoSize autoSize = new ArrangeAllWithAutoSize();

    private Predicate<Object> validateAllElementInArrayListAreIDiagramElementEditPart = new Predicate<Object>() {

        @Override
        public boolean apply(Object input) {
            return input instanceof IDiagramElementEditPart;
        }
    };

    private ArrayList<IDiagramElementEditPart> elementsToKeepFixed = new ArrayList<>();

    /**
     * {@inheritDoc}
     * 
     * Made public to be available from ArrangeAllWithAutoSize.
     */
    @Override
    public abstract Rectangle translateToGraph(Rectangle r);

    /**
     * {@inheritDoc}
     * 
     * Made public to be available from ArrangeAllWithAutoSize.
     */
    @Override
    public abstract Rectangle translateFromGraph(Rectangle rect);

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean provides(final IOperation operation) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean handleConnectableListItems() {
        return shouldHandleConnectableListItems();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle provideNodeMetrics(final Node node) {
        return getNodeMetrics(node);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LayoutExtender getExtender() {
        return extender;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command layoutEditParts(final List selectedObjects, final IAdaptable layoutHint) {
        padder.initializePaddingWithEditParts(selectedObjects);
        extender.startLayouting();

        // Clear the list of elements to keep fixed.
        elementsToKeepFixed.clear();
        // Finds if there are unpinned diagram elements to keep fixed stored in
        // the LayoutHint as a Collection
        if (layoutHint.getAdapter(Collection.class) instanceof ArrayList<?>
                && Iterables.all((ArrayList<?>) layoutHint.getAdapter(Collection.class), validateAllElementInArrayListAreIDiagramElementEditPart)) {
            elementsToKeepFixed = new ArrayList<IDiagramElementEditPart>(layoutHint.getAdapter(Collection.class));
        }
        // return super.layoutEditParts(selectedObjects, layoutHint);
        Command result = super.layoutEditParts(selectedObjects, layoutHint);

        // Clear the list of elements to keep fixed (to avoid memory leak)
        elementsToKeepFixed.clear();

        return result;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected DirectedGraphLayout createGraphLayout() {
        return new AutoSizeAndRegionAwareGraphLayout();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("rawtypes")
    @Override
    protected List getRelevantConnections(final Hashtable editPartToNodeDict) {
        /*
         * We're wrapping the original hashtable with this forwarding one not
         * failling when called with a null get because we've got cases where
         * editparts are linked through connections not having source/target
         * (for instance during folding).
         */
        @SuppressWarnings("serial")
        final List list = super.getRelevantConnections(new Hashtable() {

            @Override
            public synchronized Object get(Object key) {
                if (key != null) {
                    return editPartToNodeDict.get(key);
                }
                return null;
            }

            @Override
            public synchronized Enumeration keys() {
                return editPartToNodeDict.keys();
            }

        });
        return extender.getRelevantConnections(editPartToNodeDict, list);
    }

    // CHECKSTYLE:OFF

    /*
     * The code in the methods below is copy/pasted and slightly adapted from
     * GMF, as it could not be overridden cleanly.
     */

    @Override
    protected Command update_diagram(org.eclipse.gef.GraphicalEditPart diagramEP, DirectedGraph g, boolean isLayoutForSelected) {
        /*
         * We define the layout default margin at 0 otherwise consecutive calls
         * to the ArrangeAll actions will always move the nodes in containers.
         */

        CompoundCommand cc = new CompoundCommand(""); //$NON-NLS-1$

        final Point diff = getLayoutPositionDelta(g, isLayoutForSelected);
        layoutDefaultMargin = MapModeUtil.getMapMode(diagramEP.getFigure()).DPtoLP(25);

        Command cmd = createNodeChangeBoundCommands(g, diff);
        if (cmd != null) {
            cc.add(cmd);
        }

        cmd = createEdgesChangeBoundsCommands(g, diff);
        if (cmd != null) {
            cc.add(cmd);
        }

        return cc;
    }

    /**
     * Only used if ENABLE_PARTIAL_OVERLAP_FIX is true. This is an almost
     * verbatim copy of the version in the parent class, except for the use of
     * the custom getNodeMetricsConsideringBorderedNodes.
     */
    private Point getLayoutPositionDelta(DirectedGraph g, boolean isLayoutForSelected) {
        // If laying out selected objects, use diff variables to
        // position objects at topleft corner of enclosing rectangle.
        if (isLayoutForSelected) {
            ListIterator vi;
            vi = g.nodes.listIterator();
            Point ptLayoutMin = new Point(-1, -1);
            while (vi.hasNext()) {
                Node node = (Node) vi.next();
                // ignore ghost node
                if (node.data != null) {
                    Rectangle nodeExt = getNodeMetricsConsideringBorderedNodes(node);
                    if (ptLayoutMin.x == -1) {
                        ptLayoutMin.setX(nodeExt.x);
                        ptLayoutMin.setY(nodeExt.y);
                    } else {
                        ptLayoutMin.setX(Math.min(ptLayoutMin.x, nodeExt.x));
                        ptLayoutMin.setY(Math.min(ptLayoutMin.y, nodeExt.y));
                    }
                }
            }

            return new Point(this.minX - ptLayoutMin.x, this.minY - ptLayoutMin.y);
        }

        return new Point(layoutDefaultMargin, layoutDefaultMargin);
    }

    private Rectangle getNodeMetricsConsideringBorderedNodes(Node node) {
        Rectangle result = getNodeMetrics(node);
        if (node instanceof Subgraph && node.data instanceof IGraphicalEditPart) {
            Subgraph subGraph = (Subgraph) node;
            NodeList children = subGraph.members;
            for (int i = 0; i < children.size(); i++) {
                Node child = children.getNode(i);
                if (child instanceof IBorderItemEditPart) {
                    result.union(getNodeMetrics(child));
                }
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.providers.internal.DefaultProvider#build_edges(java.util.List,
     *      java.util.Map)
     */
    @Override
    protected EdgeList buildEdges(final List selectedObjects, final Map editPartToNodeDict) {
        return super.buildEdges(extender.filterEdges(selectedObjects, editPartToNodeDict), editPartToNodeDict);
    }

    @Override
    protected NodeList buildNodes(List selectedObjects, Map editPartToNodeDict, Subgraph rootGraph) {
        ListIterator li = selectedObjects.listIterator();
        // <added for auto-size>
        autoSize.prepareForArrangeAll(Iterators.filter(li, AbstractDiagramElementContainerEditPart.class), elementsToKeepFixed);
        // </added for auto-size>

        NodeList nodes = new NodeList();
        li = selectedObjects.listIterator();
        while (li.hasNext()) {
            IGraphicalEditPart gep = (IGraphicalEditPart) li.next();
            boolean hasChildren = hasChildren(gep);
            if (!(gep instanceof IBorderItemEditPart) && (gep instanceof ShapeEditPart || gep instanceof ShapeCompartmentEditPart)) {
                GraphicalEditPart ep = (GraphicalEditPart) gep;
                Point position = ep.getFigure().getBounds().getLocation();
                if (minX == -1) {
                    minX = position.x;
                    minY = position.y;
                } else {
                    minX = Math.min(minX, position.x);
                    minY = Math.min(minY, position.y);
                }
                Node n = null;
                if (hasChildren && !(gep instanceof GroupEditPart)) {
                    AdvancedSubGraph subGraph = null;
                    if (rootGraph != null) {
                        subGraph = new AdvancedSubGraph(ep, rootGraph);
                    } else {
                        subGraph = new AdvancedSubGraph(ep);
                    }
                    subGraph.setAutoSize(isAutoSizeOn(subGraph, ep));
                    if (gep instanceof CompartmentEditPart) {
                        subGraph.setHasBufferedZone(true);
                    }
                    subGraph.setDirection(getLayoutDirection(ep));
                    n = subGraph;
                } else {
                    if (rootGraph != null) {
                        n = new Node(ep, rootGraph);
                    } else {
                        n = new Node(ep);
                    }
                }
                adjustNodePadding(n, editPartToNodeDict);
                // <modified for auto-size>
                Dimension size = autoSize.getSizeToConsiderDuringArrangeAll(ep);
                // </modified for auto-size>
                setNodeMetrics(n, new Rectangle(position.x, position.y, size.width, size.height));
                editPartToNodeDict.put(ep, n);
                nodes.add(n);
                if (hasChildren && !(gep instanceof GroupEditPart)) {
                    buildNodes(gep.getChildren(), editPartToNodeDict, (Subgraph) n);
                }
            }
        }
        return nodes;
    }

    private boolean isAutoSizeOn(AdvancedSubGraph subGraph, IGraphicalEditPart gEP) {
        if (gEP instanceof CompartmentEditPart && subGraph.getParent() instanceof AdvancedSubGraph) {
            if (((AdvancedSubGraph) subGraph.getParent()).isAutoSize()) {
                return true;
            }
        } else {
            View notationView = gEP.getNotationView();
            if (notationView != null && notationView instanceof org.eclipse.gmf.runtime.notation.Node) {
                org.eclipse.gmf.runtime.notation.Node node = (org.eclipse.gmf.runtime.notation.Node) notationView;
                LayoutConstraint contraint = node.getLayoutConstraint();
                if (contraint instanceof Size) {
                    Size size = (Size) contraint;
                    if (size.getHeight() != -1 && size.getWidth() != -1) {
                        return ArrangeAllWithAutoSize.isEnabled();
                    }
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    protected void createSubCommands(Point diff, ListIterator vi, CompoundCommand cc) {
        final List nodes = Lists.newArrayList(vi);
        if (ArrangeAllWithAutoSize.isEnabled()) {
            autoSize.createSubCommands(diff, nodes.listIterator(), cc, this, minX, minY);
        } else {
            // No auto-size : region will not be moved.
            super.createSubCommands(diff, nodes.listIterator(), cc);
        }
        extender.keepLocationChanges(nodes, diff);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void adjustNodePadding(final Node node, final Map editPartToNodeDict) {
        // <modification>
        final GraphicalEditPart ep = (GraphicalEditPart) node.data;
        final Insets padding = padder.getNodePadding(ep);
        // </modification>
        // check if the direct parent is added already to the graph
        final GraphicalEditPart parent = (GraphicalEditPart) ep.getParent();
        if (parent != null && node.getParent() != null && editPartToNodeDict.get(parent) != node.getParent()) {
            // now the direct parent is not added to the graph so, we had
            // to adjust the padding of the node to consider the parent
            final IFigure thisFigure = parent.getFigure();
            final IFigure parentFigure = ((GraphicalEditPart) node.getParent().data).getFigure();
            final Point parentLocation = parentFigure.getBounds().getLocation();
            final Point nodeLocation = thisFigure.getBounds().getLocation();
            thisFigure.translateToAbsolute(nodeLocation);
            parentFigure.translateToAbsolute(parentLocation);
            final Dimension delta = nodeLocation.getDifference(parentLocation);
            final Rectangle rect = translateToGraph(new Rectangle(delta.width, delta.height, 0, 0));
            padding.top += rect.y;
            padding.left += rect.x;
        }
        node.setPadding(padding);
    }

    /**
     * Method override to avoid move of edges that have considered as fixed.
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.providers.internal.CompositeLayoutProvider#routeThrough(org.eclipse.draw2d.graph.Edge,
     *      org.eclipse.gef.ConnectionEditPart, org.eclipse.draw2d.graph.Node,
     *      org.eclipse.draw2d.graph.Node,
     *      org.eclipse.draw2d.geometry.PointList,
     *      org.eclipse.draw2d.geometry.Point)
     */
    @Override
    protected Command routeThrough(Edge edge, ConnectionEditPart connectEP, Node source, Node target, PointList points, Point diff) {
        if (connectEP instanceof IGraphicalEditPart && isPinned((IGraphicalEditPart) connectEP) || (elementsToKeepFixed != null && elementsToKeepFixed.contains(connectEP))) {
            return null;
        }
        return super.routeThrough(edge, connectEP, source, target, points, diff);
    }

    /**
     * Tests whether an edit part should be considered as pinned (fixed size and
     * location) during the layout.
     * 
     * @param part
     *            the edit part.
     * @return <code>true</code> if the edit part should be considered as
     *         pinned.
     */
    protected boolean isPinned(final IGraphicalEditPart part) {
        return new EditPartQuery(part).isPinned();
    }
}
