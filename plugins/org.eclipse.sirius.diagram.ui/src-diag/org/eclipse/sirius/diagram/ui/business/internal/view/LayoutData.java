/*******************************************************************************
 * Copyright (c) 2009, 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusGMFHelper;

/**
 * Store the layout information of an AbstractDNode at a given time.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class LayoutData extends AbstractLayoutData {
    /**
     * The target of this LayoutData (can only be a DDiagram or an
     * AbstractDNode).
     */
    private EObject target;

    private Dimension size;

    private Point location;

    private List<LayoutData> children;

    private List<EdgeLayoutData> outgoingEdgeLayoutDatas = new ArrayList<EdgeLayoutData>();

    private List<EdgeLayoutData> incomingEdgeLayoutDatas = new ArrayList<EdgeLayoutData>();

    /**
     * Constructor.
     * 
     */
    public LayoutData() {

    }

    /**
     * Constructor.
     * 
     * @param parent
     *            the parent layout data.
     * @param target
     *            The node to deal with
     * @param gmfNode
     *            the corresponding GMF node
     */
    public LayoutData(final LayoutData parent, final AbstractDNode target, final Node gmfNode) {
        setParent(parent);
        init(target, gmfNode);
    }

    /**
     * Initialize this object.
     * 
     * @param nodeTarget
     *            The node to deal with
     * @param gmfNode
     *            the corresponding GMF node
     */
    protected void init(final AbstractDNode nodeTarget, final Node gmfNode) {
        this.target = nodeTarget;
        final LayoutConstraint constaint = gmfNode.getLayoutConstraint();
        if (constaint instanceof Size) {
            this.size = new Dimension(((Size) constaint).getWidth(), ((Size) constaint).getHeight());
        }
        if (constaint instanceof Location) {
            this.location = new Point(((Location) constaint).getX(), ((Location) constaint).getY());
        }
        children = new ArrayList<LayoutData>();
        if (target instanceof DNode) {
            addNodeChildren();
        } else if (target instanceof DNodeContainer) {
            addNodeContainerChildren();
        } else if (target instanceof DNodeList) {
            addNodeListChildren();
        } else {
            DiagramPlugin.getDefault().logWarning("This kind of diagram element  (" + target.getClass().getName() + ") is not yet managed by the LayoutDataManager.");
        }
        if (target instanceof EdgeTarget) {
            addOutgoingEdge();
            addIncomingEdge();
        }
    }

    /**
     * Add children of the node.
     */
    protected void addNodeChildren() {
        for (DNode child : ((DNode) target).getOwnedBorderedNodes()) {
            // Search the GMF node corresponding to the child
            final Node gmfNode = SiriusGMFHelper.getGmfNode(child);
            if (gmfNode != null) {
                children.add(new LayoutData(this, child, gmfNode));
            }
        }
    }

    /**
     * Add children of the node.
     */
    protected void addNodeContainerChildren() {
        for (DDiagramElement child : ((DNodeContainer) target).getOwnedDiagramElements()) {
            if (child instanceof AbstractDNode) {
                // Search the GMF node corresponding to the child
                final Node gmfNode = SiriusGMFHelper.getGmfNode(child);
                if (gmfNode != null) {
                    children.add(new LayoutData(this, (AbstractDNode) child, gmfNode));
                }
            }
        }
        for (DNode child : ((DNodeContainer) target).getOwnedBorderedNodes()) {
            // Search the GMF node corresponding to the child
            final Node gmfNode = SiriusGMFHelper.getGmfNode(child);
            if (gmfNode != null) {
                children.add(new LayoutData(this, child, gmfNode));
            }
        }
    }

    /**
     * Add children of the node.
     */
    protected void addNodeListChildren() {
        for (DNodeListElement child : ((DNodeList) target).getOwnedElements()) {
            // Search the GMF node corresponding to the child
            final Node gmfNode = SiriusGMFHelper.getGmfNode(child);
            if (gmfNode != null) {
                children.add(new LayoutData(this, child, gmfNode));
            }
        }
        for (DNode child : ((DNodeList) target).getOwnedBorderedNodes()) {
            // Search the GMF node corresponding to the child
            final Node gmfNode = SiriusGMFHelper.getGmfNode(child);
            if (gmfNode != null) {
                children.add(new LayoutData(this, child, gmfNode));
            }
        }
    }

    /**
     * Add outgoing edge of the edgeTarget.
     */
    protected void addOutgoingEdge() {
        for (DEdge outgoingEdge : ((EdgeTarget) target).getOutgoingEdges()) {
            // Search the GMF edge corresponding to the child
            final Edge gmfEdge = SiriusGMFHelper.getGmfEdge(outgoingEdge);
            if (gmfEdge != null) {
                outgoingEdgeLayoutDatas.add(new EdgeLayoutData(this, outgoingEdge, gmfEdge));
            }
        }
    }

    /**
     * Add incoming edge of the edgeTarget.
     */
    protected void addIncomingEdge() {
        for (DEdge incomingEdge : ((EdgeTarget) target).getIncomingEdges()) {
            // Search the GMF edge corresponding to the child
            final Edge gmfEdge = SiriusGMFHelper.getGmfEdge(incomingEdge);
            if (gmfEdge != null) {
                incomingEdgeLayoutDatas.add(new EdgeLayoutData(this, incomingEdge, gmfEdge));
            }
        }
    }

    /**
     * Return the target of this layout data.
     * 
     * @return the target
     */
    public EObject getTarget() {
        return target;
    }

    /**
     * Return the children layout data.
     * 
     * @return the children
     */
    public List<LayoutData> getChildren() {
        return children;
    }

    /**
     * Search recursively in in all the LayoutData is there is one which have
     * the element for target.
     * 
     * @param node
     *            The search element
     * @param ignoreConsumeState
     *            true to ignore the consume state and to authorize to consume
     *            an already consumed data, false otherwise
     * @return the corresponding LayoutData or null if not found.
     */
    public LayoutData getData(final AbstractDNode node, boolean ignoreConsumeState) {
        LayoutData result = null;
        if ((ignoreConsumeState || !isConsume()) && getTarget().equals(node)) {
            result = this;
        } else {
            for (LayoutData layoutData : getChildren()) {
                result = layoutData.getData(node, ignoreConsumeState);
                if (result != null) {
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Search recursively in in all the LayoutData is there is one which have
     * the element for target.
     * 
     * @param edge
     *            The search element
     * @param ignoreConsumeState
     *            true to ignore the consume state and to authorize to consume
     *            an already consumed data, false otherwise
     * @return the corresponding LayoutData or null if not found.
     */
    public EdgeLayoutData getData(final DEdge edge, boolean ignoreConsumeState) {
        EdgeLayoutData result = null;
        for (EdgeLayoutData edgeLayoutData : outgoingEdgeLayoutDatas) {
            if ((ignoreConsumeState || !edgeLayoutData.isConsume()) && edgeLayoutData.getTarget().equals(edge)) {
                result = edgeLayoutData;
                break;
            }
        }
        if (result == null) {
            for (EdgeLayoutData edgeLayoutData : incomingEdgeLayoutDatas) {
                if ((ignoreConsumeState || !edgeLayoutData.isConsume()) && edgeLayoutData.getTarget().equals(edge)) {
                    result = edgeLayoutData;
                    break;
                }
            }
        }
        if (result == null) {
            for (LayoutData layoutData : getChildren()) {
                result = layoutData.getData(edge, ignoreConsumeState);
                if (result != null) {
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Return the size.
     * 
     * @return the size
     */
    public Dimension getSize() {
        return size;
    }

    /**
     * Return the location.
     * 
     * @return the location
     */
    public Point getLocation() {
        return location;
    }

    /**
     * Indicates if this layoutData and all his children has already been
     * consumed.
     * 
     * @return true if this layoutData and all his children has already been
     *         consumed, false otherwise
     */
    public boolean isAllConsume() {
        boolean isAllConsume = isConsume();
        for (LayoutData layoutData : getChildren()) {
            isAllConsume = isAllConsume && layoutData.isAllConsume();
            if (!isAllConsume) {
                break;
            }
        }
        for (EdgeLayoutData edgeLayoutData : outgoingEdgeLayoutDatas) {
            isAllConsume = isAllConsume && edgeLayoutData.isConsume();
            if (!isAllConsume) {
                break;
            }
        }
        if (isAllConsume) {
            for (EdgeLayoutData edgeLayoutData : incomingEdgeLayoutDatas) {
                isAllConsume = isAllConsume && edgeLayoutData.isConsume();
                if (!isAllConsume) {
                    break;
                }
            }
        }
        return isAllConsume;
    }

    /**
     * Set the target.
     * 
     * @param target
     *            the target to set
     */
    protected void setTarget(final EObject target) {
        if (!(target instanceof AbstractDNode || target instanceof DDiagram || target instanceof DEdge)) {
            throw new IllegalArgumentException("The target of a LayoutData can only be an AbstractDNode, a DEdge or a DDiagram.");
        }
        this.target = target;
    }

    /**
     * Set the target.
     * 
     * @param target
     *            the target to set
     */
    protected void setTarget(final DDiagram target) {
        this.target = target;
    }

    /**
     * Set the size.
     * 
     * @param size
     *            the size to set
     */
    protected void setSize(final Dimension size) {
        this.size = size;
    }

    /**
     * Set the location.
     * 
     * @param location
     *            the location to set
     */
    protected void setLocation(final Point location) {
        this.location = location;
    }

    /**
     * Set the children.
     * 
     * @param children
     *            the children to set
     */
    protected void setChildren(final List<LayoutData> children) {
        this.children = children;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        String toString = getTarget().toString();
        if (getLocation() != null) {
            toString += ", " + getLocation().toString(); //$NON-NLS-1$
        }
        if (getSize() != null) {
            toString += ", " + getSize().toString(); //$NON-NLS-1$
        }
        return toString;
    }
}
