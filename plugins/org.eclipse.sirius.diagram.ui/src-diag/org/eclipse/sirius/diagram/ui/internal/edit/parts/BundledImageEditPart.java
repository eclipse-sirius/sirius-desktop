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
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.BundledImage;
import org.eclipse.sirius.diagram.BundledImageShape;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractNotSelectableShapeNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IStyleEditPart;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramBorderNodeEditPartOperation;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramNodeEditPartOperation;
import org.eclipse.sirius.diagram.ui.tools.api.figure.AirStyleDefaultSizeNodeFigure;
import org.eclipse.sirius.diagram.ui.tools.api.figure.BundledImageFigure;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.description.SystemColors;

/**
 * @was-generated
 */
public class BundledImageEditPart extends AbstractNotSelectableShapeNodeEditPart implements IStyleEditPart {

    /**
     * @not-generated : prevent drag of elements
     */
    public DragTracker getDragTracker(final Request request) {
        return getParent().getDragTracker(request);
    }

    /**
     * @was-generated
     */
    public static final int VISUAL_ID = 3004;

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
    public BundledImageEditPart(final View view) {
        super(view);
    }

    /**
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart#refreshVisuals()
     * @not-generated : refresh the figure.
     */
    protected void refreshVisuals() {
        super.refreshVisuals();
        final BundledImageFigure figure = this.getPrimaryShape();
        final EObject element = this.resolveSemanticElement();
        if (element instanceof BundledImage) {
            final BundledImage bundledImage = (BundledImage) element;
            figure.refreshFigure(bundledImage);
            DiagramNodeEditPartOperation.refreshNodeLabelAlignment(figure, bundledImage);
            ((GraphicalEditPart) this.getParent()).setLayoutConstraint(this, this.getFigure(), new Rectangle(0, 0, figure.getPreferredSize().width, figure.getPreferredSize().height));
        }
    }

    /**
     * @not-generated
     */
    protected void createDefaultEditPolicies() {
        // Do nothing.
    }

    /**
     * @not-generated
     */
    protected LayoutEditPolicy createLayoutEditPolicy() {
        final LayoutEditPolicy lep = new org.eclipse.sirius.diagram.ui.tools.api.policies.LayoutEditPolicy() {

            protected EditPolicy createChildEditPolicy(final EditPart child) {
                EditPolicy result = child.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
                if (result == null) {
                    result = new NonResizableEditPolicy();
                }
                return result;
            }

            protected Command getMoveChildrenCommand(final Request request) {
                return null;
            }

            protected Command getCreateCommand(final CreateRequest request) {
                return null;
            }
        };
        return lep;
    }

    /**
     * @not-generated
     */
    protected IFigure createNodeShape() {
        BundledImageFigure bif;
        if (this.getMetamodelType().isInstance(this.resolveSemanticElement())) {
            bif = (BundledImageFigure) BundledImageFigure.createImageFigure((BundledImage) resolveSemanticElement());
        } else {
            DiagramPlugin.getInstance().logWarning("The element is not a BundledImage");
            final BundledImage bundle = DiagramFactory.eINSTANCE.createBundledImage();
            bundle.setShape(BundledImageShape.SQUARE_LITERAL);
            bundle.setLabelFormat(FontFormat.NORMAL_LITERAL);
            bundle.setLabelSize(10);
            bundle.setColor(VisualBindingManager.getDefault().getRGBValuesFor(SystemColors.BLUE_LITERAL));
            bundle.setBorderColor(VisualBindingManager.getDefault().getRGBValuesFor(SystemColors.DARK_BLUE_LITERAL));
            bif = (BundledImageFigure) BundledImageFigure.createImageFigure(bundle);
        }

        EditPart parent = this.getParent();
        if (parent instanceof IDiagramBorderNodeEditPart) {
            DiagramBorderNodeEditPartOperation.updateTransparencyMode((IDiagramBorderNodeEditPart) parent, bif);
        }
        return primaryShape = bif;
    }

    /**
     * @was-generated
     */
    public BundledImageFigure getPrimaryShape() {
        return (BundledImageFigure) primaryShape;
    }

    /**
     * @not-generated
     */
    protected NodeFigure createNodePlate() {
        return new AirStyleDefaultSizeNodeFigure(getMapMode().DPtoLP(40), getMapMode().DPtoLP(40));
    }

    /**
     * @was-generated
     */
    public EditPolicy getPrimaryDragEditPolicy() {
        final EditPolicy result = super.getPrimaryDragEditPolicy();
        if (result instanceof ResizableEditPolicy) {
            final ResizableEditPolicy ep = (ResizableEditPolicy) result;
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
     * @not-generated
     */
    protected NodeFigure createNodeFigure() {
        final NodeFigure figure = createNodePlate();
        figure.setLayoutManager(new StackLayout());
        final IFigure shape = createNodeShape();
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
    protected IFigure setupContentPane(final IFigure nodeShape) {
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
     * @see org.eclipse.sirius.diagram.graphical.edit.part.specific.SpecificShapeNodeEditPart#getMetamodelType()
     */
    protected Class<?> getMetamodelType() {
        return BundledImage.class;
    }

}
