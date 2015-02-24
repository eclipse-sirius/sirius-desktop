/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.edit.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.Dot;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractNotSelectableShapeNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IStyleEditPart;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramBorderNodeEditPartOperation;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramNodeEditPartOperation;
import org.eclipse.sirius.diagram.ui.internal.edit.policies.FixedLayoutEditPolicy;
import org.eclipse.sirius.diagram.ui.tools.api.figure.AbstractTransparentEllipse;
import org.eclipse.sirius.diagram.ui.tools.api.figure.AirStyleDefaultSizeNodeFigure;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.viewpoint.LabelStyle;
import org.eclipse.sirius.viewpoint.RGBValues;

/**
 * @was-generated
 */
public class DotEditPart extends AbstractNotSelectableShapeNodeEditPart implements IStyleEditPart {

    /**
     * @no-generated : prevent drag of elements
     */
    public DragTracker getDragTracker(Request request) {
        return getParent().getDragTracker(request);
    }

    /**
     * @was-generated
     */
    public static final int VISUAL_ID = 3002;

    /**
     * @was-generated
     */
    protected IFigure contentPane;

    /**
     * @was-generated
     */
    protected IFigure primaryShape;

    /**
     * @was-generated
     */
    public DotEditPart(View view) {
        super(view);
    }

    /**
     * @not-generated
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#refreshBackgroundColor()
     */
    protected void refreshBackgroundColor() {
        super.refreshBackgroundColor();
        final EObject element = this.resolveSemanticElement();
        if (this.getMetamodelType().isInstance(element)) {
            final RGBValues rgb = ((Dot) element).getBackgroundColor();
            if (rgb != null)
                this.getPrimaryShape().setBackgroundColor(VisualBindingManager.getDefault().getColorFromRGBValues(rgb));
        }
    }

    /**
     * Overridden to refresh the label alignment.
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart#refreshVisuals()
     */
    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
        final EObject element = this.resolveSemanticElement();
        if (element instanceof LabelStyle) {
            if (element instanceof Dot) {
                Dot dot = (Dot) element;
                int borderSize = 0;
                if (dot.getBorderSize() != null) {
                    borderSize = dot.getBorderSize().intValue();
                }
                this.getPrimaryShape().setLineWidth(borderSize);
            }
            DiagramNodeEditPartOperation.refreshNodeLabelAlignment(getPrimaryShape(), (LabelStyle) element);
        }
    }

    /**
     * @not-generated
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#refreshForegroundColor()
     */
    protected void refreshForegroundColor() {
        if (getMetamodelType().isInstance(resolveSemanticElement())) {
            Dot dot = (Dot) this.resolveSemanticElement();
            if (dot.getBorderColor() != null) {
                this.getPrimaryShape().setForegroundColor(VisualBindingManager.getDefault().getColorFromRGBValues(dot.getBorderColor()));
            }
        }
    }

    /**
     * @not-generated no edit policies brings better editing :)
     */
    protected void createDefaultEditPolicies() {
        // Do nothing.
    }

    /**
     * @was-generated
     */
    protected LayoutEditPolicy createLayoutEditPolicy() {
        return new FixedLayoutEditPolicy();
    }

    /**
     * @not-generated
     */
    protected IFigure createNodeShape() {
        DotFigure dot = new DotFigure();
        EditPart parent = this.getParent();
        if (parent instanceof IDiagramBorderNodeEditPart) {
            DiagramBorderNodeEditPartOperation.updateTransparencyMode((IDiagramBorderNodeEditPart) parent, dot);
        }
        return primaryShape = dot;
    }

    /**
     * @was-generated
     */
    public DotFigure getPrimaryShape() {
        return (DotFigure) primaryShape;
    }

    /**
     * @not-generated
     */
    protected NodeFigure createNodePlate() {
        DefaultSizeNodeFigure result = new AirStyleDefaultSizeNodeFigure(getMapMode().DPtoLP(40), getMapMode().DPtoLP(40));
        nodePlate = result;
        return result;
    }

    /**
     * @was-generated
     */
    public EditPolicy getPrimaryDragEditPolicy() {
        EditPolicy result = super.getPrimaryDragEditPolicy();
        if (result instanceof ResizableEditPolicy) {
            ResizableEditPolicy ep = (ResizableEditPolicy) result;
            ep.setResizeDirections(PositionConstants.NONE);
        }
        return result;
    }

    /**
     * Creates figure for this edit part.
     * 
     * Body of this method does not depend on settings in generation model so
     * you may safely remove <i>generated</i> tag and modify it.
     * 
     * @was-generated
     */
    protected NodeFigure createNodeFigure() {
        NodeFigure figure = createNodePlate();
        figure.setLayoutManager(new StackLayout());
        IFigure shape = createNodeShape();
        figure.add(shape);
        contentPane = setupContentPane(shape);
        return figure;
    }

    /**
     * Default implementation treats passed figure as content pane. Respects
     * layout one may have set for generated figure.
     * 
     * @param nodeShape
     *            instance of generated figure class
     * @was-generated
     */
    protected IFigure setupContentPane(IFigure nodeShape) {
        return nodeShape; // use nodeShape itself as contentPane
    }

    /**
     * @was-generated
     */
    public IFigure getContentPane() {
        if (contentPane != null) {
            return contentPane;
        }
        return super.getContentPane();
    }

    /**
     * @not-generated
     */
    public static class DotFigure extends AbstractTransparentEllipse {

        /**
         * @was-generated
         */
        public DotFigure() {
            this.setLineWidth(3);
        }
    }

    private IFigure nodePlate;

    /**
     * @return the figure node-plate
     */
    public IFigure getNodePlate() {
        return nodePlate;
    }

    protected Class<?> getMetamodelType() {
        return Dot.class;
    }

}
