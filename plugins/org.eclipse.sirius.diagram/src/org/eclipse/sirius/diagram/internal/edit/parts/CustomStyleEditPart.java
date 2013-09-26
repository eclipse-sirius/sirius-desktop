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
package org.eclipse.sirius.diagram.internal.edit.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.edit.api.part.AbstractNotSelectableShapeNodeEditPart;
import org.eclipse.sirius.diagram.edit.api.part.IDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.edit.api.part.IStyleEditPart;
import org.eclipse.sirius.diagram.edit.internal.part.DiagramBorderNodeEditPartOperation;
import org.eclipse.sirius.diagram.internal.edit.policies.NonResizableAndNonDuplicableEditPolicy;
import org.eclipse.sirius.diagram.internal.edit.policies.StyleItemSemanticEditPolicy;
import org.eclipse.sirius.diagram.ui.tools.api.figure.BundledImageFigure;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.viewpoint.BundledImage;
import org.eclipse.sirius.viewpoint.BundledImageShape;
import org.eclipse.sirius.viewpoint.CustomStyle;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.description.SystemColors;

/**
 * @was-generated
 */
public class CustomStyleEditPart extends AbstractNotSelectableShapeNodeEditPart implements IStyleEditPart {

    /**
     * @was-generated
     */
    public static final int VISUAL_ID = 3014;

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
    public CustomStyleEditPart(final View view) {
        super(view);
    }

    /**
     * @not-generated : prevent drag of elements
     */
    public DragTracker getDragTracker(final Request request) {
        return getParent().getDragTracker(request);
    }

    /**
     * @was-generated
     */
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new StyleItemSemanticEditPolicy());
        installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
    }

    /**
     * @not-generated
     */
    protected LayoutEditPolicy createLayoutEditPolicy() {
        final LayoutEditPolicy lep = new org.eclipse.sirius.diagram.tools.api.policies.LayoutEditPolicy() {

            protected EditPolicy createChildEditPolicy(final EditPart child) {
                EditPolicy result = child.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
                if (result == null) {
                    result = new NonResizableAndNonDuplicableEditPolicy();
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
        BundledImageFigure bif = (BundledImageFigure) BundledImageFigure.createImageFigure(bundledImage);
        EditPart parent = this.getParent();
        if (parent instanceof IDiagramBorderNodeEditPart) {
            DiagramBorderNodeEditPartOperation.updateTransparencyMode((IDiagramBorderNodeEditPart) parent, bif);
        }
        return primaryShape = bif;
    }

    private static BundledImage bundledImage = ViewpointFactory.eINSTANCE.createBundledImage();
    static {
        bundledImage.setColor(VisualBindingManager.getDefault().getRGBValuesFor(SystemColors.GREEN_LITERAL));
        bundledImage.setBorderColor(VisualBindingManager.getDefault().getRGBValuesFor(SystemColors.GREEN_LITERAL));
        bundledImage.setShape(BundledImageShape.SQUARE_LITERAL);
    }

    /**
     * @was-generated
     */
    public BundledImageFigure getPrimaryShape() {
        return (BundledImageFigure) primaryShape;
    }

    /**
     * @was-generated
     */
    protected NodeFigure createNodePlate() {
        final DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(getMapMode().DPtoLP(40), getMapMode().DPtoLP(40));
        return result;
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
     * @was-generated
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

    protected Class<?> getMetamodelType() {
        return CustomStyle.class;
    }

}
