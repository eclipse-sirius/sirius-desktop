/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES.
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
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractNotSelectableShapeNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IStyleEditPart;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramBorderNodeEditPartOperation;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramContainerEditPartOperation;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramElementEditPartOperation;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramNodeEditPartOperation;
import org.eclipse.sirius.diagram.ui.internal.edit.policies.FixedLayoutEditPolicy;
import org.eclipse.sirius.diagram.ui.tools.api.figure.AirStyleDefaultSizeNodeFigure;
import org.eclipse.sirius.diagram.ui.tools.api.figure.ITransparentFigure;
import org.eclipse.sirius.diagram.ui.tools.api.figure.IWorkspaceImageFigure;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SVGWorkspaceImageFigure;
import org.eclipse.sirius.diagram.ui.tools.api.figure.WorkspaceImageFigure;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;

/**
 * @was-generated
 */
public class WorkspaceImageEditPart extends AbstractNotSelectableShapeNodeEditPart implements IStyleEditPart {

    /**
     * @not-generated : prevent drag of elements
     */
    @Override
    public DragTracker getDragTracker(Request request) {
        return getParent().getDragTracker(request);
    }

    /**
     * @was-generated
     */
    public static final int VISUAL_ID = 3005;

    /**
     * @was-generated
     */
    protected IFigure contentPane;

    /**
     * @was-generated
     */
    protected IWorkspaceImageFigure primaryShape;

    /**
     * @was-generated
     */
    public WorkspaceImageEditPart(View view) {
        super(view);
    }

    /**
     * @not-generated
     */
    @Override
    protected void createDefaultEditPolicies() {
        // Do nothing.
    }

    /**
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart#refreshVisuals()
     */
    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
        IWorkspaceImageFigure figure = this.getPrimaryShape();
        EObject element = this.resolveSemanticElement();
        if (element instanceof WorkspaceImage && figure != null) {
            WorkspaceImage bundledImage = (WorkspaceImage) element;
            // Check if the figure is the right one (SVGWorkspaceImageFigure for
            // SVG format, WorkspaceImageFigure for others kinds).
            if (DiagramContainerEditPartOperation.needFigureChange(bundledImage, figure)) {
                // Replace the wrong IWorkspaceImageFigure kind by the new one.
                getFigure().remove(figure);
                IFigure shape = createNodeShape();
                getFigure().add(shape);
                contentPane = setupContentPane(shape);
            } else {
                // Refresh the right IWorkspaceImageFigure kind
                figure.refreshFigure(bundledImage);
            }
            DiagramNodeEditPartOperation.refreshFigure(this);
            DiagramElementEditPartOperation.refreshLabelAlignment(((GraphicalEditPart) getParent()).getContentPane(), bundledImage);
            ((GraphicalEditPart) this.getParent()).setLayoutConstraint(this, this.getFigure(), new Rectangle(0, 0, figure.getPreferredSize().width, figure.getPreferredSize().height));
        }
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
        WorkspaceImage wkImage = (WorkspaceImage) resolveSemanticElement();
        IWorkspaceImageFigure wif = null;
        if (wkImage != null) {
            String workspacePath = wkImage.getWorkspacePath();
            if (StringUtil.isEmpty(workspacePath)) {
                wif = WorkspaceImageFigure.createImageFigure(DiagramImagesPath.IMAGE_NOT_FOUND);
            } else if (WorkspaceImageFigure.isSvgImage(workspacePath)) {
                wif = SVGWorkspaceImageFigure.createImageFigure(wkImage);
            } else {
                wif = WorkspaceImageFigure.createImageFigure(wkImage);
            }
            EditPart parent = this.getParent();
            if (parent instanceof IDiagramBorderNodeEditPart && wif instanceof ITransparentFigure) {
                DiagramBorderNodeEditPartOperation.updateTransparencyMode((IDiagramBorderNodeEditPart) parent, (ITransparentFigure) wif);
            }
        }
        return primaryShape = wif;
    }

    /**
     * @not-generated
     */
    public IWorkspaceImageFigure getPrimaryShape() {
        return primaryShape;
    }

    /**
     * @not-generated custom size node.
     */
    protected NodeFigure createNodePlate() {
        DefaultSizeNodeFigure result = new AirStyleDefaultSizeNodeFigure(getMapMode().DPtoLP(40), getMapMode().DPtoLP(40));
        return result;
    }

    /**
     * @was-generated
     */
    @Override
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
     * @not-generated
     */
    @Override
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
    @Override
    public IFigure getContentPane() {
        if (contentPane != null) {
            return contentPane;
        }
        return super.getContentPane();
    }

    protected Class<?> getMetamodelType() {
        return WorkspaceImage.class;
    }
}
