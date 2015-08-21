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
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.BundledImage;
import org.eclipse.sirius.diagram.BundledImageShape;
import org.eclipse.sirius.diagram.CustomStyle;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractNotSelectableShapeNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IStyleEditPart;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramBorderNodeEditPartOperation;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramElementEditPartOperation;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramNodeEditPartOperation;
import org.eclipse.sirius.diagram.ui.internal.edit.policies.NonResizableAndNonDuplicableEditPolicy;
import org.eclipse.sirius.diagram.ui.internal.edit.policies.StyleItemSemanticEditPolicy;
import org.eclipse.sirius.diagram.ui.tools.api.figure.BundledImageFigure;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
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
    @Override
    public DragTracker getDragTracker(final Request request) {
        return getParent().getDragTracker(request);
    }

    /**
     * @was-generated
     */
    @Override
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new StyleItemSemanticEditPolicy());
        installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
    }

    /**
     * @not-generated
     */
    protected LayoutEditPolicy createLayoutEditPolicy() {
        final LayoutEditPolicy lep = new org.eclipse.sirius.diagram.ui.tools.api.policies.LayoutEditPolicy() {

            @Override
            protected EditPolicy createChildEditPolicy(final EditPart child) {
                EditPolicy result = child.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
                if (result == null) {
                    result = new NonResizableAndNonDuplicableEditPolicy();
                }
                return result;
            }

            @Override
            protected Command getMoveChildrenCommand(final Request request) {
                return null;
            }

            @Override
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

    private static BundledImage bundledImage = DiagramFactory.eINSTANCE.createBundledImage();

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
     * Body of this method does not depend on settings in generation model so
     * you may safely remove <i>generated</i> tag and modify it.
     * 
     * @was-generated
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
    @Override
    public IFigure getContentPane() {
        if (contentPane != null) {
            return contentPane;
        }
        return super.getContentPane();
    }

    protected Class<?> getMetamodelType() {
        return CustomStyle.class;
    }

    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
        DiagramNodeEditPartOperation.refreshFigure(this);
        EObject element = this.resolveSemanticElement();
        if (element instanceof CustomStyle) {
            DiagramElementEditPartOperation.refreshLabelAlignment(((GraphicalEditPart) getParent()).getContentPane(), (CustomStyle) element);
        }
    }

}
