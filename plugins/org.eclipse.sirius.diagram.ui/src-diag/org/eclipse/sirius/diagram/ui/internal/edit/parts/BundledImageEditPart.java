/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES and others.
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
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.BundledImage;
import org.eclipse.sirius.diagram.BundledImageShape;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.tools.internal.DiagramPlugin;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractNotSelectableShapeNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IStyleEditPart;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramBorderNodeEditPartOperation;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramElementEditPartOperation;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramNodeEditPartOperation;
import org.eclipse.sirius.diagram.ui.internal.edit.policies.FixedLayoutEditPolicy;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.figure.BundledImageFigure;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.AirStyleDefaultSizeNodeFigure;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.viewpoint.description.SystemColors;

/**
 * @was-generated
 */
public class BundledImageEditPart extends AbstractNotSelectableShapeNodeEditPart implements IStyleEditPart {

    /**
     * @not-generated : prevent drag of elements
     */
    @Override
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
    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
        final BundledImageFigure figure = this.getPrimaryShape();
        final EObject element = this.resolveSemanticElement();
        if (element instanceof BundledImage) {
            final BundledImage bundledImage = (BundledImage) element;
            figure.refreshFigure(bundledImage);
            DiagramNodeEditPartOperation.refreshFigure(this);
            DiagramElementEditPartOperation.refreshLabelAlignment(((GraphicalEditPart) getParent()).getContentPane(), bundledImage);
            ((GraphicalEditPart) this.getParent()).setLayoutConstraint(this, this.getFigure(), new Rectangle(0, 0, figure.getPreferredSize().width, figure.getPreferredSize().height));
        }
    }

    /**
     * @not-generated
     */
    @Override
    protected void createDefaultEditPolicies() {
        // Do nothing.
    }

    /**
     * @not-generated
     */
    protected LayoutEditPolicy createLayoutEditPolicy() {
        return new FixedLayoutEditPolicy();
    }

    /**
     * @not-generated
     */
    protected IFigure createNodeShape() {
        BundledImageFigure bif;
        if (this.getMetamodelType().isInstance(this.resolveSemanticElement())) {
            bif = (BundledImageFigure) BundledImageFigure.createImageFigure((BundledImage) resolveSemanticElement());
        } else {
            DiagramPlugin.getDefault().logWarning(Messages.BundledImageEditPart_notBundleImageMsg);
            final BundledImage bundle = DiagramFactory.eINSTANCE.createBundledImage();
            bundle.setShape(BundledImageShape.SQUARE_LITERAL);
            bundle.getLabelFormat().clear();
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
    @Override
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
     * Body of this method does not depend on settings in generation model so you may safely remove <i>generated</i> tag
     * and modify it.
     *
     * @not-generated
     */
    @Override
    protected NodeFigure createNodeFigure() {
        final NodeFigure figure = createNodePlate();
        figure.setLayoutManager(new StackLayout());
        final IFigure shape = createNodeShape();
        figure.add(shape);
        contentPane = setupContentPane(shape);
        return figure;
    }

    /**
     * Default implementation treats passed figure as content pane. Respects layout one may have set for generated
     * figure.
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
    @Override
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
