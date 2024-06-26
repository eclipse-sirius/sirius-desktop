/*******************************************************************************
 * Copyright (c) 2009, 2024 Kiel University and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Kiel University - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.elk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.IdentityAnchor;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.gmf.runtime.notation.RoutingStyle;
import org.eclipse.gmf.runtime.notation.Smoothness;
import org.eclipse.gmf.runtime.notation.StringValueStyle;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;

/**
 * Command used to apply layout.
 * 
 * Copied from org.eclipse.elk.conn.gmf.GmfLayoutCommand of commit e99248e44c71a5a02fe45bc4cd5150cd7f50c559.
 * 
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 * @see org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand
 * @see org.eclipse.gmf.runtime.diagram.ui.internal.commands.SetConnectionBendpointsCommand
 * @see org.eclipse.gmf.runtime.diagram.core.commands.SetConnectionAnchorsCommand
 */
@SuppressWarnings("restriction")
public class GmfLayoutCommand extends AbstractTransactionalCommand {

    /** Style name for serialized junction points. */
    public static final String JUNCTION_POINTS_STYLE_NAME = "junctionPoints";

    /** layout data for node shapes. */
    public static final class ShapeLayoutData {
        public View view;

        public Point location;

        public Dimension size;

        public ShapeLayoutData() {
        }
    }

    /** layout data for edges. */
    public static final class EdgeLayoutData {
        public Edge edge;

        public PointList bends;

        public String junctionPoints;

        public String sourceTerminal;

        public String targetTerminal;

        public Routing routingToApply;

        public EdgeLayoutData() {
        }
    }

    /** adapter for the view of the base diagram. */
    private IAdaptable diagramViewAdapter;

    /** list of shape layouts to be applied to nodes. */
    private List<ShapeLayoutData> shapeLayouts = new LinkedList<ShapeLayoutData>();

    /** list of edge layouts to be applied to edges. */
    private List<EdgeLayoutData> edgeLayouts = new LinkedList<EdgeLayoutData>();

    /**
     * Creates a command to apply layout.
     * 
     * @param domain
     *            the editing domain through which model changes are made
     * @param label
     *            the command label
     * @param adapter
     *            an adapter to the {@code View} of the base diagram
     */
    public GmfLayoutCommand(final TransactionalEditingDomain domain, final String label, final IAdaptable adapter) {
        super(domain, label, null);
        this.diagramViewAdapter = adapter;
    }

    /**
     * Adds the given shape layout data to this command.
     * 
     * @param view
     *            view from the GMF notation model
     * @param location
     *            new location for the view, or {@code null} if the location shall not be changed
     * @param size
     *            new size for the view, or {@code null} if the size shall not be changed
     */
    public void addShapeLayout(final View view, final Point location, final Dimension size) {
        assert view != null;
        ShapeLayoutData layout = new ShapeLayoutData();
        layout.view = view;
        layout.location = location;
        layout.size = size;
        shapeLayouts.add(layout);
    }

    /**
     * Adds the given edge layout data to this command.
     * 
     * @param edge
     *            edge from the GMF notation model
     * @param bends
     *            list of bend points for the edge, or {@code null} if the bend points shall not be changed
     * @param junctionPoints
     *            list of junction points to draw on the edge, encoded as string, or {@code null} if no junction points
     *            shall be drawn
     * @param sourceTerminal
     *            new source terminal, encoded as string, or {@code null} if the source terminal shall not be changed
     * @param targetTerminal
     *            new target terminal, encoded as string, or {@code null} if the target terminal shall not be changed
     * @param routingToApply
     *            the routing to apply on the DEdge
     */
    public void addEdgeLayout(final Edge edge, final PointList bends, final String sourceTerminal, final String targetTerminal, final String junctionPoints, final Routing routingToApply) {
        assert edge != null;
        EdgeLayoutData layout = new EdgeLayoutData();
        layout.edge = edge;
        layout.bends = bends;
        layout.junctionPoints = junctionPoints;
        layout.sourceTerminal = sourceTerminal;
        layout.targetTerminal = targetTerminal;
        layout.routingToApply = routingToApply;
        edgeLayouts.add(layout);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    protected CommandResult doExecuteWithResult(final IProgressMonitor monitor, final IAdaptable info) throws ExecutionException {
        monitor.beginTask(getLabel(), 1);
        // process shape layout data
        for (ShapeLayoutData shapeLayout : shapeLayouts) {
            // set new location of the element
            if (shapeLayout.location != null) {
                ViewUtil.setStructuralFeatureValue(shapeLayout.view, NotationPackage.eINSTANCE.getLocation_X(), Integer.valueOf(shapeLayout.location.x));
                ViewUtil.setStructuralFeatureValue(shapeLayout.view, NotationPackage.eINSTANCE.getLocation_Y(), Integer.valueOf(shapeLayout.location.y));
            }
            // set new size of the element
            if (shapeLayout.size != null) {
                ViewUtil.setStructuralFeatureValue(shapeLayout.view, NotationPackage.eINSTANCE.getSize_Width(), Integer.valueOf(shapeLayout.size.width));
                ViewUtil.setStructuralFeatureValue(shapeLayout.view, NotationPackage.eINSTANCE.getSize_Height(), Integer.valueOf(shapeLayout.size.height));
            }
        }
        shapeLayouts.clear();

        // process edge layout data
        for (EdgeLayoutData edgeLayout : edgeLayouts) {
            // set new bend points of the edge
            if (edgeLayout.bends != null) {
                List<RelativeBendpoint> newBendpoints = new ArrayList<RelativeBendpoint>(edgeLayout.bends.size());
                Point sourcePoint = edgeLayout.bends.getFirstPoint();
                Point targetPoint = edgeLayout.bends.getLastPoint();
                for (int i = 0; i < edgeLayout.bends.size(); i++) {
                    Point bend = edgeLayout.bends.getPoint(i);
                    newBendpoints.add(new RelativeBendpoint(bend.x - sourcePoint.x, bend.y - sourcePoint.y, bend.x - targetPoint.x, bend.y - targetPoint.y));
                }
                RelativeBendpoints points = (RelativeBendpoints) edgeLayout.edge.getBendpoints();
                points.setPoints(newBendpoints);
            }

            // set new source anchor point of the edge
            if (edgeLayout.sourceTerminal != null) {
                IdentityAnchor anchor = (IdentityAnchor) edgeLayout.edge.getSourceAnchor();
                if (anchor == null) {
                    anchor = NotationFactory.eINSTANCE.createIdentityAnchor();
                    edgeLayout.edge.setSourceAnchor(anchor);
                }
                anchor.setId(edgeLayout.sourceTerminal);
            }
            // set new target anchor point of the edge
            if (edgeLayout.targetTerminal != null) {
                IdentityAnchor anchor = (IdentityAnchor) edgeLayout.edge.getTargetAnchor();
                if (anchor == null) {
                    anchor = NotationFactory.eINSTANCE.createIdentityAnchor();
                    edgeLayout.edge.setTargetAnchor(anchor);
                }
                anchor.setId(edgeLayout.targetTerminal);
            }

            // set junction points as style
            StringValueStyle style = (StringValueStyle) edgeLayout.edge.getNamedStyle(NotationPackage.eINSTANCE.getStringValueStyle(), JUNCTION_POINTS_STYLE_NAME);
            if (edgeLayout.junctionPoints == null) {
                if (style != null) {
                    edgeLayout.edge.getStyles().remove(style);
                }
            } else {
                if (style == null) {
                    style = NotationFactory.eINSTANCE.createStringValueStyle();
                    style.setName(JUNCTION_POINTS_STYLE_NAME);
                    edgeLayout.edge.getStyles().add(style);
                }
                style.setStringValue(edgeLayout.junctionPoints);
            }

            // Set routing style retrieved in ELK parent of edges
            RoutingStyle routingStyle = (RoutingStyle) edgeLayout.edge.getStyle(NotationPackage.eINSTANCE.getRoutingStyle());
            if (routingStyle != null && edgeLayout.routingToApply != null) {
                routingStyle.setRouting(edgeLayout.routingToApply);
                routingStyle.setSmoothness(Smoothness.NONE_LITERAL);
            }
        }
        edgeLayouts.clear();

        monitor.done();
        return CommandResult.newOKCommandResult();
    }

    /**
     * Provides the {@link EdgeLayoutData}.
     * 
     * @return a unmodifiableList.
     */
    public List<EdgeLayoutData> getEdgeLayouts() {
        return Collections.unmodifiableList(edgeLayouts);
    }

    /**
     * Provides the {@link ShapeLayoutData}.
     * 
     * @return a unmodifiableList.
     */
    public List<ShapeLayoutData> getShapeLayouts() {
        return Collections.unmodifiableList(shapeLayouts);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<?> getAffectedFiles() {
        if (diagramViewAdapter != null) {
            View view = diagramViewAdapter.getAdapter(View.class);
            if (view != null) {
                return getWorkspaceFiles(view);
            }
        }
        return super.getAffectedFiles();
    }

}
