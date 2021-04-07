/*******************************************************************************
 * Copyright (c) 2009, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.business.internal.view;

import java.util.ArrayList;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.ui.tools.api.format.AbstractSiriusFormatDataManager;
import org.eclipse.sirius.diagram.ui.tools.api.util.EditPartTools;

/**
 * A RootLayoutData is the <i>root</i> of an LayoutDataHint.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class RootLayoutData extends LayoutData {

    private EditPartViewer viewer;

    /**
     * A Point representing the distance the EditPart has moved (in case of
     * drag'n'drop, null otherwise)
     */
    private Point moveDelta;

    /**
     * This constructor is used when you want to store the layout data of only
     * one element which the parent is the AbstractDNode or the DDiagram of
     * editPart. Use this to create new object.
     * 
     * @param editPart
     *            The parent edit part
     * @param location
     *            Location of the edit part
     * @param size
     *            the future size
     */
    public RootLayoutData(final EditPart editPart, final Point location, final Dimension size) {
        final Object adaptObject = EditPartTools.getParentOfType(editPart, IGraphicalEditPart.class).resolveSemanticElement();
        if (adaptObject instanceof AbstractDNode) {
            init((AbstractDNode) adaptObject, location, size);
        } else if (adaptObject instanceof DDiagram) {
            init((DDiagram) adaptObject, location, size);
        } else if (adaptObject instanceof DEdge) {
            init((DEdge) adaptObject, location, size);
        } else {
            AbstractSiriusFormatDataManager.logUnhandledDiagramElementKindMessage(adaptObject);
        }
    }

    /**
     * This constructor is used when you want to store the layout data of the
     * AbstractDNode of editPart and all its children.
     * 
     * @param editPart
     *            The drag'n'drop edit part.
     * @param target
     *            The target of the layoutData
     * @param viewer
     *            The viewer of the drag'n'drop edit part. The viewer
     *            responsible of the drag'n'drop edit part lifecycle
     * @param location
     *            The location of this layout data
     * @param moveDelta
     *            A Point representing the distance the EditPart has moved (in
     *            case of drag'n'drop).
     */
    public RootLayoutData(final AbstractDNode target, final ShapeEditPart editPart, final EditPartViewer viewer, final Point location, final Point moveDelta) {
        super();
        setViewer(viewer);
        this.moveDelta = moveDelta;
        init(target, (Node) editPart.getModel(), location);
    }

    /**
     * Default constructor from a parent
     * {@link org.eclipse.sirius.diagram.DDiagramElement}/{@link DDiagram}.
     * 
     * @param adaptObject
     *            The parent {@link org.eclipse.sirius.diagram.DDiagramElement}/
     *            {@link DDiagram}
     * @param location
     *            Location in the parent
     * @param size
     *            the future size
     */
    public RootLayoutData(final Object adaptObject, final Point location, final Dimension size) {
        super();
        if (adaptObject instanceof AbstractDNode) {
            init((AbstractDNode) adaptObject, location, size);
        } else if (adaptObject instanceof DDiagram) {
            init((DDiagram) adaptObject, location, size);
        } else if (adaptObject instanceof DEdge) {
            init((DEdge) adaptObject, location, size);
        } else {
            AbstractSiriusFormatDataManager.logUnhandledDiagramElementKindMessage(adaptObject);
        }
    }

    /**
     * Initialize this object (with a new location) and all its children.
     * 
     * @param nodeTarget
     *            The node to deal with
     * @param gmfNode
     *            the corresponding GMF node
     * @param futureLocation
     *            The future location of the node (or null if the editPart
     *            location is OK)
     */
    protected void init(final AbstractDNode nodeTarget, final Node gmfNode, final Point futureLocation) {
        super.init(nodeTarget, gmfNode);

        if (futureLocation != null) {
            // Override the location of the nodeTarget
            setLocation(futureLocation);
        }
    }

    /**
     * Initialize this object (with a new location and size). The children of
     * this object is not added because the
     * 
     * @param parentNodeTarget
     *            The parent of the node to deal with (AbstractDNode, DEdge or
     *            DDiagram)
     * @param futureLocation
     *            The future location of the node (or null if the editPart
     *            location is OK)
     * @param futureSize
     *            The future size of the node (or null if the editPart size is
     *            OK)
     */
    protected void init(final EObject parentNodeTarget, final Point futureLocation, final Dimension futureSize) {
        setTarget(parentNodeTarget);
        setSize(futureSize);
        setLocation(futureLocation);
        setChildren(new ArrayList<LayoutData>());
    }

    /**
     * Returns <code>this</code>.
     * 
     * @return this.
     */
    @Override
    public RootLayoutData getRoot() {
        return this;
    }

    /**
     * Search recursively in in all the DiagramLayoutData is there is one which
     * have the diagram for target.
     * 
     * @param diagram
     *            The search diagram
     * @param ignoreConsumeState
     *            true to ignore the consume state and to authorize to consume
     *            an already consumed data, false otherwise
     * @return the corresponding LayoutData or null if not found.
     */
    public LayoutData getData(final DDiagram diagram, boolean ignoreConsumeState) {
        final LayoutData result = (ignoreConsumeState || !isConsume()) && getTarget().equals(diagram) ? this : null;
        return result;
    }

    /**
     * Return the moveDelta.
     * 
     * @return the moveDelta
     */
    public Point getMoveDelta() {
        return moveDelta;
    }

    /**
     * Return the viewer.
     * 
     * @return the viewer
     */
    public EditPartViewer getViewer() {
        return viewer;
    }

    /**
     * Set the viewer of this layout data.
     * 
     * @param viewer
     *            the viewer to set
     */
    public void setViewer(final EditPartViewer viewer) {
        this.viewer = viewer;
    }

}
