/******************************************************************************
 * Copyright (c) 2006, 2021 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation
 *    Obeo - copied from org.eclipse.gmf.runtime.draw2d.ui.internal.graph.CompositeDirectedGraphLayout
 *           and adapted for Sirius
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.layout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.graph.DirectedGraph;
import org.eclipse.draw2d.graph.DirectedGraphLayout;
import org.eclipse.draw2d.graph.Edge;
import org.eclipse.draw2d.graph.EdgeList;
import org.eclipse.draw2d.graph.Node;
import org.eclipse.draw2d.graph.NodeList;
import org.eclipse.draw2d.graph.Subgraph;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.internal.graph.AdvancedSubGraph;
import org.eclipse.gmf.runtime.draw2d.ui.internal.graph.CompositeDirectedGraphLayout;
import org.eclipse.gmf.runtime.draw2d.ui.internal.graph.VirtualNode;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.model.business.internal.query.DDiagramElementContainerExperimentalQuery;
import org.eclipse.sirius.diagram.model.business.internal.query.DNodeContainerExperimentalQuery;
import org.eclipse.sirius.diagram.ui.edit.api.part.IAbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDNodeContainerCompartmentEditPart;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

/**
 * Customized version of the standard CompositeDirectedGraphLayout to improve auto-size handling. The original class
 * does not support extensibility, so its whole code has been copied here and adapted, from the version in GMF 1.0.101.
 * <p>
 * Changes from original:
 * <ul>
 * <li>Backport of the fix in version 1.4 of the fils in CVS (
 * <code>http://dev.eclipse.org/viewcvs/index.cgi/org.eclipse.gmf/plugins/org.eclipse.gmf.runtime.draw2d.ui/src/org/eclipse/gmf/runtime/draw2d/ui/internal/graph/CompositeDirectedGraphLayout.java?revision=1.4&root=Modeling_Project&view=markup</code>
 * )</li>
 * <LI>Add a graph direction variable as in GMF 1.3.0 to manage correctly the leftRight layout of CompositeLayout</LI>
 * <LI>Change the insets used in recursiveHandleVirtualNode to get the computed Insets of our CompositeLayout</LI>
 * </ul>
 * 
 * @author pcdavid
 */
// CHECKSTYLE:OFF
@SuppressWarnings("restriction")
public class AutoSizeAndRegionAwareGraphLayout extends CompositeDirectedGraphLayout {

    private int specificGraphDirection = PositionConstants.SOUTH;

    /*
     * (non-Javadoc)
     * @see org.eclipse.draw2d.graph.DirectedGraphLayout#visit(org.eclipse.draw2d .graph.DirectedGraph)
     */
    @Override
    public void visit(DirectedGraph graph) {
        specificGraphDirection = graph.getDirection();
        layoutNodes(graph.nodes, false);
    }

    private void layoutNodes(NodeList nodes, boolean virtualPass) {
        EdgeList edges = new EdgeList();
        for (Iterator iter = nodes.iterator(); iter.hasNext();) {
            Node element = (Node) iter.next();
            if (element instanceof Subgraph && !(element instanceof VirtualNode)) {
                layoutNodes(((Subgraph) element).members, virtualPass);
            }
            for (Iterator edgesIter = element.outgoing.iterator(); edgesIter.hasNext();) {
                Edge edge = (Edge) edgesIter.next();
                if (nodes.contains(edge.target)) {
                    edges.add(edge);
                }
            }
        }
        if (!virtualPass) {
            VirtualNodesToNodes virtualNodesNodes = new VirtualNodesToNodes();
            createVirtualNodes(nodes, edges, virtualNodesNodes);
            NodeList vituralNodes = virtualNodesNodes.getVirtualNodes();
            int size = vituralNodes.size();
            if (size > 0) {
                edges = virtualNodesNodes.getEdges();
                for (Iterator iter = vituralNodes.iterator(); iter.hasNext();) {
                    Subgraph virtualNode = (Subgraph) iter.next();
                    layoutNodes(virtualNode.members, true);
                }
                adjustVirtualNodesWidthAndHeight(vituralNodes);
            }
        }

        Map nodeToOutGoing = new HashMap();
        Map nodeToIncomingGoing = new HashMap();
        removeDisconnectedEdges(nodes, nodeToOutGoing, nodeToIncomingGoing);
        if (nodes.size() > 0) {
            Node parent = getParent(nodes.getNode(0));
            DirectedGraph g = new DirectedGraph();
            g.setDirection(specificGraphDirection);
            g.nodes = nodes;
            g.edges = edges;

            final boolean concernRegions = (parent == null && areRegions(nodes)) || (parent != null && getRegionContainer(parent).some());
            /*
             * Handle regions : deactivate the default behavior where all nodes of a same row have the same vertical
             * bounds
             */
            if (!concernRegions) {
                DirectedGraphLayout layout = new DirectedGraphLayout();
                layout.visit(g);
            }

            /*
             * <handle_bordered_nodes> Translate all the elements at this level of the appropriate offsets to consider
             * their bordered nodes. Otherwise elements are put too close to their parent's border, and the bordered
             * nodes cause the appearance of scrollbars.
             */
            Dimension offsets = getOffsets(nodes);
            for (int j = 0; j < nodes.size(); j++) {
                Node n = nodes.getNode(j);
                n.x += offsets.width;
                n.y += offsets.height;
            }

            /*
             * Handle regions : deactivate the default behavior where all nodes of a same row have the same vertical
             * bounds
             */
            if (concernRegions) {
                adjustRegionsLayout(nodes, parent);
            }

            if (parent instanceof AdvancedSubGraph) {
                adjustAutoSizeNodeWidthAndHeight((AdvancedSubGraph) parent);
            }
        }

        restoreDisconnectedEdges(nodeToOutGoing, nodeToIncomingGoing);
    }

    private void adjustRegionsLayout(NodeList nodes, Node parent) {
        if (nodes.isEmpty())
            return;

        DNodeContainer regionContainer = getRegionContainer(nodes, parent);
        if (regionContainer != null) {
            DNodeContainerExperimentalQuery query = new DNodeContainerExperimentalQuery(regionContainer);
            if (query.isVerticalStackContainer()) {
                adjustRegionLayout(nodes, parent, true);
            } else if (query.isHorizontaltackContainer()) {
                adjustRegionLayout(nodes, parent, false);
            }
        }
    }

    /*
     * @param vertical : vertical if true, horizontal if false.
     */
    private void adjustRegionLayout(NodeList nodes, Node parent, boolean vertical) {
        int commonWidth = 0;
        int commonHeight = 0;
        for (int j = 0; j < nodes.size(); j++) {
            Node n = nodes.getNode(j);
            commonWidth = Math.max(commonWidth, n.width);
            commonHeight = Math.max(commonHeight, n.height);
        }

        int y = 0;
        int x = 0;
        for (int j = 0; j < nodes.size(); j++) {
            Node n = nodes.getNode(j);
            n.x = x;
            n.y = y;

            if (vertical) {
                n.width = commonWidth;
                y += n.height;
            } else {
                x += n.width;
                n.height = commonHeight;
            }
        }
    }

    private DNodeContainer getRegionContainer(NodeList nodes, Node parent) {
        DNodeContainer rc = null;
        if (parent != null) {
            rc = getRegionContainer(parent).get();
        } else {
            Option<DDiagramElementContainer> region = getRegion(nodes.getNode(0));
            rc = region.some() ? (DNodeContainer) region.get().eContainer() : null;
        }

        return rc;
    }

    /* no parent detect regions */
    private boolean areRegions(NodeList nodes) {
        boolean areRegions = false;
        for (int j = 0; j < nodes.size(); j++) {
            Node n = nodes.getNode(j);
            areRegions = areRegions || getRegion(n).some();
        }
        return areRegions;
    }

    private Option<DNodeContainer> getRegionContainer(Node node) {
        DNodeContainer dnc = null;
        if (node != null && (node.data instanceof IDiagramContainerEditPart || node.data instanceof AbstractDNodeContainerCompartmentEditPart)) {
            EObject element = ((IGraphicalEditPart) node.data).resolveSemanticElement();
            if (element instanceof DNodeContainer) {
                dnc = (DNodeContainer) element;
            }
        }

        if (dnc != null && new DNodeContainerExperimentalQuery(dnc).isRegionContainer()) {
            return Options.newSome(dnc);
        }
        return Options.newNone();
    }

    private Option<DDiagramElementContainer> getRegion(Node node) {
        if (node.data instanceof IAbstractDiagramNodeEditPart) {
            DDiagramElement element = ((IAbstractDiagramNodeEditPart) node.data).resolveDiagramElement();
            if (element instanceof DDiagramElementContainer && new DDiagramElementContainerExperimentalQuery((DDiagramElementContainer) element).isRegion()) {
                return Options.newSome((DDiagramElementContainer) element);
            }
        }
        return Options.newNone();
    }

    /**
     * Computes how much the specified nodes should be moved to make enough room for the parent container to contain
     * them <em>with their bordered nodes</em>.
     */
    private Dimension getOffsets(NodeList nodes) {
        Node node = nodes.getNode(0);
        int left = node.x, top = node.y;
        Rectangle bbox = ArrangeAllWithAutoSize.extendBoundingBoxWithBorderedNodes(node, new Rectangle(node.x, node.y, node.width, node.height));
        int leftBorder = bbox.x, topBorder = bbox.y;
        for (int i = 1; i < nodes.size(); i++) {
            node = nodes.getNode(i);
            left = Math.min(left, node.x);
            top = Math.min(top, node.y);
            bbox = ArrangeAllWithAutoSize.extendBoundingBoxWithBorderedNodes(node, new Rectangle(node.x, node.y, node.width, node.height));
            leftBorder = Math.min(leftBorder, bbox.x);
            topBorder = Math.min(topBorder, bbox.y);
        }
        Point diff = new Point();
        if (node.getParent() != null) {
            diff = new Point(node.getParent().x - node.x, node.getParent().y - node.y);
        }
        if (diff.x < 0 && diff.y < 0) {
            return new Dimension(Math.max(0, left - leftBorder), Math.max(0, top - topBorder));
        }
        return new Dimension(Math.max(0, Math.abs(left - leftBorder)), Math.max(0, Math.abs(top - topBorder)));
    }

    private void restoreDisconnectedEdges(Map nodeToOutGoing, Map nodeToIncomingGoing) {
        restoreEdges(nodeToOutGoing.entrySet(), true);
        restoreEdges(nodeToIncomingGoing.entrySet(), false);
    }

    private void removeDisconnectedEdges(NodeList nodes, Map nodeToOutGoing, Map nodeToIncomingGoing) {
        for (Iterator iter = nodes.iterator(); iter.hasNext();) {
            Node element = (Node) iter.next();
            pushExtraEdges(nodes, nodeToOutGoing, element, element.outgoing, false);
            pushExtraEdges(nodes, nodeToIncomingGoing, element, element.incoming, true);
        }
    }

    private void createVirtualNodes(NodeList nodes, EdgeList edges, VirtualNodesToNodes virtualNodesNodes) {
        Set handledEdges = new HashSet();
        recursiveHandleVirtualNode(nodes, edges, virtualNodesNodes, handledEdges, new HashSet(nodes));
    }

    private void recursiveHandleVirtualNode(NodeList nodes, EdgeList edges, VirtualNodesToNodes virtualNodesNodes, Set handledEdges, Set nodesSnapeShot) {
        for (Iterator edgeIter = edges.iterator(); edgeIter.hasNext();) {
            Edge element = (Edge) edgeIter.next();
            if (handledEdges.contains(element)) {
                continue;
            }
            handledEdges.add(element);
            if (!nodesSnapeShot.contains(element.source) || !nodesSnapeShot.contains(element.target)) {
                continue;
            }
            Node source = element.source;
            Node target = element.target;
            boolean sourceHandled = true;
            boolean targetHandled = true;
            Subgraph sg = virtualNodesNodes.getVirtualContainer(source);
            Subgraph sg1 = virtualNodesNodes.getVirtualContainer(target);
            if (sg == null) {
                sourceHandled = false;
                sg = sg1;
            }
            if (sg1 == null) {
                targetHandled = false;
            }
            if (sourceHandled == false && targetHandled == false) {
                sg = new VirtualNode(null, source.getParent());
                sg.setPadding(new Insets(source.getPadding()));
                if (source.getParent() == null) {
                    nodes.add(sg);
                }
            }
            if (!sourceHandled) {
                addNode(sg, source, nodes);
                virtualNodesNodes.addNode(sg, source);
            }
            if (!targetHandled) {
                addNode(sg, target, nodes);
                virtualNodesNodes.addNode(sg, target);
            }
            // order is important; so we should start handling the outgoing and
            // the incoming
            // edges only after the source and target had been handled
            if (!sourceHandled) {
                recursiveHandleVirtualNode(nodes, source.outgoing, virtualNodesNodes, handledEdges, nodesSnapeShot);
                recursiveHandleVirtualNode(nodes, source.incoming, virtualNodesNodes, handledEdges, nodesSnapeShot);
            }
            if (!targetHandled) {
                recursiveHandleVirtualNode(nodes, target.outgoing, virtualNodesNodes, handledEdges, nodesSnapeShot);
                recursiveHandleVirtualNode(nodes, target.incoming, virtualNodesNodes, handledEdges, nodesSnapeShot);
            }
        }
    }

    private void pushExtraEdges(NodeList nodes, Map nodeToIncomingGoing, Node element, List list, boolean sourceCheck) {
        List edges = new ArrayList();
        for (Iterator iterator = list.iterator(); iterator.hasNext();) {
            Edge edge = (Edge) iterator.next();
            Node nodeToCheck = sourceCheck ? edge.source : edge.target;
            if (!nodes.contains(nodeToCheck)) {
                edges.add(edge);
                iterator.remove();
                Node sourceNode = null;
                Node targetNode = null;
                sourceNode = getParent(edge.source);
                targetNode = getParent(edge.target);
                sourceNode = (!sourceCheck || sourceNode != null) ? sourceNode : edge.source;
                targetNode = (sourceCheck || targetNode != null) ? targetNode : edge.target;
                if (!sourceCheck && sourceNode != null && targetNode != null && sourceNode != targetNode && (edge.source != sourceNode || edge.target != targetNode)) {
                    Edge virtualEdge = new Edge(sourceNode, targetNode, edge.getDelta(), edge.weight);
                    virtualEdge.setPadding(edge.getPadding());
                }
            }
        }
        if (!edges.isEmpty()) {
            nodeToIncomingGoing.put(element, edges);
        }
    }

    private Node getParent(Node node) {
        Node parent = node.getParent();
        if (parent instanceof VirtualNode) {
            parent = parent.getParent();
        }
        return parent;
    }

    private void restoreEdges(Set entries, boolean outgoing) {
        for (Iterator iter = entries.iterator(); iter.hasNext();) {
            Map.Entry entry = (Map.Entry) iter.next();
            Node node = (Node) entry.getKey();
            List edgesList = (List) entry.getValue();
            for (Iterator iterator = edgesList.iterator(); iterator.hasNext();) {
                Edge edgeToRestore = (Edge) iterator.next();
                if (outgoing) {
                    node.outgoing.add(edgeToRestore);
                } else {
                    node.incoming.add(edgeToRestore);
                }
            }

        }
    }

    private void adjustVirtualNodesWidthAndHeight(NodeList vituralNodes) {
        for (Iterator iter = vituralNodes.iterator(); iter.hasNext();) {
            Subgraph subGraph = (Subgraph) iter.next();
            adjustVirtualNodeWidthAndHeight(subGraph);
        }

    }

    private void adjustVirtualNodeWidthAndHeight(Subgraph subGraph) {
        NodeList nodes = subGraph.members;
        if (nodes.isEmpty()) {
            return;
        }
        int size = nodes.size();
        Node node = nodes.getNode(0);
        int top = node.y, left = node.x, bottom = top + node.height, right = left + node.width;
        for (int index = 1; index < size; index++) {
            node = (Node) nodes.get(index);
            if (top > node.y) {
                top = node.y;
            }
            if (bottom < (node.y + node.height)) {
                bottom = node.y + node.height;
            }
            if (left > node.x) {
                left = node.x;
            }
            if (right < (node.x + node.width)) {
                right = node.x + node.width;
            }
        }
        subGraph.width = right - left;
        subGraph.height = bottom - top;
    }

    private void adjustAutoSizeNodeWidthAndHeight(AdvancedSubGraph subGraph) {
        if (!subGraph.isAutoSize()) {
            return;
        }

        NodeList nodes = subGraph.members;
        if (nodes.isEmpty()) {
            return;
        }
        int size = nodes.size();
        Node node = nodes.getNode(0);
        int top = node.y, left = node.x, bottom = top + node.height, right = left + node.width;
        Node topNode, leftNode;
        topNode = leftNode = node;
        for (int index = 1; index < size; index++) {
            node = (Node) nodes.get(index);
            if (top > node.y) {
                top = node.y;
                topNode = node;
            }
            if (bottom < (node.y + node.height)) {
                bottom = node.y + node.height;
            }
            if (left > node.x) {
                left = node.x;
                leftNode = node;
            }
            if (right < (node.x + node.width)) {
                right = node.x + node.width;
            }
        }
        int xDiff = 0;
        int yDiff = 0;
        if (subGraph.isHasBufferedZone()) {
            xDiff = leftNode.x;
            yDiff = topNode.y;
        }
        subGraph.width = right - left + xDiff;
        subGraph.height = bottom - top + yDiff;

        /*
         * <auto-size_fix> This algorithm comes from org.eclipse.gmf.runtime.diagram.ui.layout.FreeFormLayoutEx
         * .layout(IFigure parent).
         */
        if (subGraph.data instanceof IGraphicalEditPart) {
            IGraphicalEditPart gep = (IGraphicalEditPart) subGraph.data;
            IFigure f = gep.getFigure();

            LayoutManager lm = f.getParent().getLayoutManager();
            Point offset;
            if (lm instanceof XYLayout) {
                offset = ((XYLayout) lm).getOrigin(f.getParent());
            } else {
                offset = new Point();
            }

            Rectangle bounds = f.getBounds().getCopy();

            if (bounds.width == -1 || bounds.height == -1) {
                Dimension _preferredSize = f.getPreferredSize(bounds.width, bounds.height);
                bounds = bounds.getCopy();
                if (bounds.width == -1) {
                    bounds.width = _preferredSize.width;
                }
                if (bounds.height == -1) {
                    bounds.height = _preferredSize.height;
                }
            }
            Dimension min = f.getMinimumSize();
            Dimension max = f.getMaximumSize();

            if (min.width > bounds.width) {
                bounds.width = min.width;
            } else if (max.width < bounds.width) {
                bounds.width = max.width;
            }

            if (min.height > bounds.height) {
                bounds.height = min.height;
            } else if (max.height < bounds.height) {
                bounds.height = max.height;
            }
            bounds = bounds.getTranslated(offset);

            subGraph.width = Math.max(subGraph.width, bounds.width);
            subGraph.height = Math.max(subGraph.height, bounds.height);
        }
        /*
         * </auto-size_fix>
         */
    }

    private void addNode(Subgraph parent, Node node, NodeList nodes) {
        if (node.getParent() != null) {
            node.getParent().members.remove(node);
        }
        node.setParent(parent);
        parent.addMember(node);
        nodes.remove(node);
    }

    @SuppressWarnings("serial")
    private static class VirtualNodesToNodes extends HashMap {
        Set virtualNodes = new HashSet();

        public void addNode(Subgraph sg, Node node) {
            virtualNodes.add(sg);
            put(node, sg);
        }

        public EdgeList getEdges() {
            EdgeList edges = new EdgeList();
            for (Iterator iter = virtualNodes.iterator(); iter.hasNext();) {
                Node element = (Node) iter.next();
                for (Iterator iterator = element.outgoing.iterator(); iterator.hasNext();) {
                    Edge edge = (Edge) iterator.next();
                    if (virtualNodes.contains(edge.target)) {
                        edges.add(edge);
                    }

                }
            }
            return edges;
        }

        public Subgraph getVirtualContainer(Node node) {
            return (Subgraph) get(node);
        }

        public NodeList getVirtualNodes() {
            NodeList nodeList = new NodeList();
            nodeList.addAll(virtualNodes);
            return nodeList;
        }
    }
}
