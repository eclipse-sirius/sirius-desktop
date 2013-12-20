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
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.edit.api.part.AbstractNotSelectableShapeNodeEditPart;
import org.eclipse.sirius.diagram.edit.api.part.IDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.edit.api.part.IStyleEditPart;
import org.eclipse.sirius.diagram.edit.internal.part.DiagramBorderNodeEditPartOperation;
import org.eclipse.sirius.diagram.edit.internal.part.DiagramNodeEditPartOperation;
import org.eclipse.sirius.diagram.tools.internal.image.ImagesPath;
import org.eclipse.sirius.diagram.ui.tools.api.figure.AirStyleDefaultSizeNodeFigure;
import org.eclipse.sirius.diagram.ui.tools.api.figure.ITransparentFigure;
import org.eclipse.sirius.diagram.ui.tools.api.figure.IWorkspaceImageFigure;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SVGWorkspaceImageFigure;
import org.eclipse.sirius.diagram.ui.tools.api.figure.WorkspaceImageFigure;
import org.eclipse.sirius.ext.swt.ImageFileFormat;
import org.eclipse.sirius.viewpoint.WorkspaceImage;

/**
 * @was-generated
 */
public class WorkspaceImageEditPart extends AbstractNotSelectableShapeNodeEditPart implements IStyleEditPart {

    /**
     * @not-generated : prevent drag of elements
     */
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
    protected void createDefaultEditPolicies() {
        // Do nothing.
    }

    /**
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart#refreshVisuals()
     */
    protected void refreshVisuals() {
        super.refreshVisuals();
        IWorkspaceImageFigure figure = this.getPrimaryShape();
        EObject element = this.resolveSemanticElement();
        if (element instanceof WorkspaceImage && figure != null) {
            WorkspaceImage bundledImage = (WorkspaceImage) element;
            figure.refreshFigure(bundledImage);
            DiagramNodeEditPartOperation.refreshNodeLabelAlignment(figure, bundledImage);
            ((GraphicalEditPart) this.getParent()).setLayoutConstraint(this, this.getFigure(), new Rectangle(0, 0, figure.getPreferredSize().width, figure.getPreferredSize().height));
        }
    }

    /**
     * @not-generated
     */
    protected LayoutEditPolicy createLayoutEditPolicy() {
        LayoutEditPolicy lep = new org.eclipse.sirius.diagram.tools.api.policies.LayoutEditPolicy() {

            protected EditPolicy createChildEditPolicy(EditPart child) {
                EditPolicy result = child.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
                if (result == null) {
                    result = new NonResizableEditPolicy();
                }
                return result;
            }

            protected Command getMoveChildrenCommand(Request request) {
                return null;
            }

            protected Command getCreateCommand(CreateRequest request) {
                return null;
            }
        };
        return lep;
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
                wif = WorkspaceImageFigure.createImageFigure(ImagesPath.IMAGE_NOT_FOUND);
            } else if (workspacePath.toUpperCase().endsWith(ImageFileFormat.SVG.getName())) {
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

    protected Class<?> getMetamodelType() {
        return WorkspaceImage.class;
    }
}
