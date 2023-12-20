/*******************************************************************************
 * Copyright (c) 2007, 2018 THALES GLOBAL SERVICES.
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

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DragDropEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.ui.business.internal.view.ShowingViewUtil;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.internal.part.CommonEditPartOperation;
import org.eclipse.sirius.diagram.ui.internal.edit.policies.BorderedNodeLayoutEditPolicy;
import org.eclipse.sirius.diagram.ui.internal.edit.policies.DNode4ItemSemanticEditPolicy;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IStyleConfigurationRegistry;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.StyleConfiguration;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.AirDefaultSizeNodeFigure;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.SiriusWrapLabel;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.ViewNodeFigure;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.util.AnchorProvider;
import org.eclipse.sirius.viewpoint.DStylizable;

/**
 * @was-generated
 */
public class DNode4EditPart extends AbstractDiagramBorderNodeEditPart {

    /**
     * @was-generated
     */
    public static final int VISUAL_ID = 3012;

    /**
     * @not-generated
     */
    protected DefaultSizeNodeFigure nodePlate = null;

    /**
     * @was-generated
     */
    protected IFigure contentPane;

    /**
     * @was-generated
     */
    protected IFigure primaryShape;

    private Object savedConstraint;

    /**
     * Construct the edit part from the view.
     * 
     * @param view
     *            the view.
     */
    public DNode4EditPart(final View view) {
        super(view);
    }

    /**
     */
    public IFigure getNodePlate() {
        return nodePlate;
    }

    /**
     * @not-generated
     */
    @Override
    protected void createDefaultEditPolicies() {
        installEditPolicy(EditPolicyRoles.CREATION_ROLE, new CreationEditPolicy());

        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, getPrimaryDragEditPolicy());
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new DNode4ItemSemanticEditPolicy());
        installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE, new DragDropEditPolicy());
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new BorderedNodeLayoutEditPolicy());

        /*
         * remove the connection items display
         */
        removeEditPolicy(EditPolicyRoles.CONNECTION_HANDLES_ROLE);
    }

    /**
     * @was-generated
     */
    protected IFigure createNodeShape() {
        return primaryShape = new ViewNodeFigure((View) getModel());
    }

    /**
     * @not-generated
     */
    public ViewNodeFigure getPrimaryShape() {
        return (ViewNodeFigure) primaryShape;
    }

    /**
     * @was-generated
     */
    @Override
    protected void addBorderItem(final IFigure borderItemContainer, final IBorderItemEditPart borderItemEditPart) {
        if (borderItemEditPart instanceof DNodeNameEditPart) {
            if (this.resolveSemanticElement() instanceof DNode) {
                final DNode node = (DNode) this.resolveSemanticElement();
                final StyleConfiguration styleConfiguration = IStyleConfigurationRegistry.INSTANCE.getStyleConfiguration(node.getDiagramElementMapping(), node.getStyle());
                final IBorderItemLocator locator = styleConfiguration.getNameBorderItemLocator(node, getMainFigure());
                borderItemContainer.add(borderItemEditPart.getFigure(), locator);
            }
        } else if (borderItemEditPart instanceof DNode4EditPart && borderItemEditPart.resolveSemanticElement() instanceof DDiagramElement) {
            IBorderItemLocator locator = null;
            if (!(this.savedConstraint instanceof BorderItemLocator)) {
                locator = this.createBorderItemLocator(getMainFigure(), (DDiagramElement) borderItemEditPart.resolveSemanticElement());
                locator.setConstraint(borderItemEditPart.getFigure().getBounds());
            } else {
                locator = (BorderItemLocator) this.savedConstraint;
                this.savedConstraint = null;
            }
            borderItemContainer.add(borderItemEditPart.getFigure(), locator);
        } else {
            super.addBorderItem(borderItemContainer, borderItemEditPart);
        }
    }

    /**
     * @not-generated
     */
    protected NodeFigure createNodePlate() {
        DefaultSizeNodeFigure result = null;
        final EObject eObj = resolveSemanticElement();
        if (eObj instanceof DStylizable && eObj instanceof DDiagramElement) {
            final DStylizable viewNode = (DStylizable) eObj;
            final StyleConfiguration styleConfiguration = IStyleConfigurationRegistry.INSTANCE.getStyleConfiguration(((DDiagramElement) eObj).getDiagramElementMapping(), viewNode.getStyle());
            final AnchorProvider anchorProvider = styleConfiguration.getAnchorProvider();
            result = new AirDefaultSizeNodeFigure(getMapMode().DPtoLP(5), getMapMode().DPtoLP(5), anchorProvider) {
                @Override
                public void paint(Graphics graphics) {
                    ShowingViewUtil.initGraphicsForVisibleAndInvisibleElements(this, graphics, (View) getModel());
                    try {
                        CommonEditPartOperation.setGraphicsTraceabilityId(graphics, () -> resolveTargetSemanticElement());
                        super.paint(graphics);
                        CommonEditPartOperation.setGraphicsTraceabilityId(graphics, null);
                        graphics.restoreState();
                    } finally {
                        graphics.popState();
                    }
                }
            };
            nodePlate = result;
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.AbstractDiagramBorderNodeEditPart#activate()
     */
    @Override
    public void activate() {
        if (nodePlate instanceof AirDefaultSizeNodeFigure)
            ((AirDefaultSizeNodeFigure) nodePlate).setZoomManager(getZoomManager());
        super.activate();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.AbstractDiagramBorderNodeEditPart#deactivate()
     */
    @Override
    public void deactivate() {
        super.deactivate();
        if (nodePlate instanceof AirDefaultSizeNodeFigure)
            ((AirDefaultSizeNodeFigure) nodePlate).setZoomManager(null);
    }

    /**
     * Creates figure for this edit part. Body of this method does not depend on settings in generation model so you may
     * safely remove <i>generated</i> tag and modify it.
     * 
     * @not-generated : remove the layout manager to fix the size
     */
    @Override
    protected NodeFigure createMainFigure() {
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
        if (nodeShape.getLayoutManager() == null) {
            final ConstrainedToolbarLayout layout = new ConstrainedToolbarLayout();
            layout.setSpacing(getMapMode().DPtoLP(5));
            nodeShape.setLayoutManager(layout);
        }
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
     * @was-generated
     */
    @Override
    public EditPart getPrimaryChildEditPart() {
        return getChildBySemanticHint(SiriusVisualIDRegistry.getType(NotationViewIDs.DNODE_NAME_4_EDIT_PART_VISUAL_ID));
    }

    /**
     * @not-generated
     */
    @Override
    public SiriusWrapLabel getNodeLabel() {
        return getPrimaryShape().getNodeLabel();
    }

    /**
     * @was-generated
     */
    @Override
    public IFigure getPrimaryFigure() {
        return getPrimaryShape();
    }

    @Override
    public Class<?> getMetamodelType() {
        return DNode.class;
    }
}
