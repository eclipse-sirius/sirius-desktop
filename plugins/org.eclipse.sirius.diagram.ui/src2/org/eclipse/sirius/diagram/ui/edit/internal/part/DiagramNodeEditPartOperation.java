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
package org.eclipse.sirius.diagram.edit.internal.part;

import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.Shape;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.figures.NoteFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.BorderedStyle;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.LabelPosition;
import org.eclipse.sirius.diagram.NodeStyle;
import org.eclipse.sirius.diagram.ResizeKind;
import org.eclipse.sirius.diagram.business.internal.edit.helpers.LabelAlignmentHelper;
import org.eclipse.sirius.diagram.business.internal.query.StyleConfigurationQuery;
import org.eclipse.sirius.diagram.edit.api.part.DiagramNameEditPartOperation;
import org.eclipse.sirius.diagram.edit.api.part.IAbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.edit.api.part.IDiagramNameEditPart;
import org.eclipse.sirius.diagram.edit.api.part.IDiagramNodeEditPart;
import org.eclipse.sirius.diagram.internal.edit.parts.NotationViewIDs;
import org.eclipse.sirius.diagram.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.diagram.tools.api.graphical.edit.styles.IStyleConfigurationRegistry;
import org.eclipse.sirius.diagram.tools.api.graphical.edit.styles.StyleConfiguration;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SiriusWrapLabel;
import org.eclipse.sirius.diagram.ui.tools.api.figure.StyledFigure;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.viewpoint.LabelAlignment;
import org.eclipse.sirius.viewpoint.LabelStyle;
import org.eclipse.sirius.viewpoint.RGBValues;

/**
 * Implementation of {@link IDiagramNodeEditPart}.
 * 
 * @author ymortier
 */
public final class DiagramNodeEditPartOperation {

    /**
     * Avoid instanciation.
     */
    private DiagramNodeEditPartOperation() {

    }

    /**
     * Refreshes the visuals of the specified node.
     * 
     * @param self
     *            the node edit part.
     */
    public static void refreshVisuals(final IDiagramNodeEditPart self) {
        final EObject eObj = self.resolveSemanticElement();
        if (eObj instanceof DNode) {
            if (self.getPrimaryFigure() != null) {
                self.refreshFigure();
            }
            DiagramNodeEditPartOperation.internalRefreshVisuals(self);
            if (self.getPrimaryFigure() != null) {
                self.refreshFigure();
                self.getPrimaryFigure().repaint();
            }
        }
    }

    /**
     * Refreshes the font of the specified node.
     * 
     * @param self
     *            the node edit part.
     */
    public static void refreshFont(final IDiagramNodeEditPart self) {
        if (!self.getChildren().isEmpty()) {
            Object firstChild = self.getChildren().get(0);
            if (firstChild instanceof IDiagramNameEditPart) {
                DiagramNameEditPartOperation.refreshFont((IDiagramNameEditPart) firstChild);
            }
        }
    }

    /**
     * Refreshes the visuals of the specified node.
     * 
     * @param self
     *            the node edit part.
     */
    public static void internalRefreshVisuals(final IDiagramNodeEditPart self) {
        final AbstractDiagramNodeEditPartRefreshVisualsOperation op = new AbstractDiagramNodeEditPartRefreshVisualsOperation(self);
        if (op.canRefresh()) {
            op.refreshSize();
            op.refreshFont();
            op.refreshColor();
        }
    }

    /**
     * Refreshes the figure of the node.
     * 
     * @param self
     *            the node edit part.
     */
    public static void refreshFigure(final IDiagramNodeEditPart self) {
        final StyledFigure styledFigure = DiagramElementEditPartOperation.getStyledFigure(self.getFigure());
        if (self.resolveDiagramElement() instanceof DNode) {
            final DNode viewNode = (DNode) self.resolveDiagramElement();
            if (styledFigure != null) {
                if (((NodeStyle) viewNode.getStyle()).getLabelPosition() != null && ((NodeStyle) viewNode.getStyle()).getLabelPosition() == LabelPosition.NODE_LITERAL
                        && !styledFigure.getChildren().contains(self.getNodeLabel())) {
                    styledFigure.add(self.getNodeLabel());
                }
                if (((NodeStyle) viewNode.getStyle()).getLabelPosition() != null && ((NodeStyle) viewNode.getStyle()).getLabelPosition() == LabelPosition.BORDER_LITERAL
                        && styledFigure.getChildren().contains(self.getNodeLabel())) {
                    styledFigure.remove(self.getNodeLabel());
                }
                if (styledFigure.getChildren().contains(self.getNodeLabel())) {
                    DiagramElementEditPartOperation.refreshFont(self, viewNode, self.getNodeLabel());
                    self.getNodeLabel().setText(viewNode.getName());
                    final StyleConfiguration styleConfiguration = IStyleConfigurationRegistry.INSTANCE.getStyleConfiguration(viewNode.getDiagramElementMapping(), viewNode.getStyle());
                    self.getNodeLabel().setIcon(new StyleConfigurationQuery(styleConfiguration).getLabelIcon(self.resolveDiagramElement(), self));
                    if (styleConfiguration != null) {
                        styleConfiguration.adaptNodeLabel(viewNode, self.getNodeLabel());
                    }

                    // The border.
                    if (viewNode.getStyle() instanceof BorderedStyle) {
                        final BorderedStyle borderedStyle = (BorderedStyle) viewNode.getStyle();
                        DiagramNodeEditPartOperation.refreshBorderFigure(borderedStyle, styledFigure);
                    }
                }
                self.setTooltipText(viewNode.getTooltipText());
                self.getNodeLabel().revalidate();
            }
        }
    }

    private static void refreshBorderFigure(final BorderedStyle borderedStyle, final StyledFigure styledFigure) {

        LineBorder lineBorder = null;
        if (styledFigure.getBorder() instanceof LineBorder) {
            lineBorder = (LineBorder) styledFigure.getBorder();
        } else {
            lineBorder = new LineBorder();
            if (styledFigure instanceof Shape) {
                styledFigure.setBorder(null);
            } else {
                styledFigure.setBorder(lineBorder);
            }
        }

        lineBorder.setWidth(borderedStyle.getBorderSize().intValue());
        final RGBValues borderColor = borderedStyle.getBorderColor();
        if (borderColor != null) {
            lineBorder.setColor(VisualBindingManager.getDefault().getColorFromRGBValues(borderColor));
        }

        if (borderedStyle.getBorderSize().intValue() == 0) {
            /* NoteFigure in GMF does not expect a null figure since GMF 2.2 */
            if (!(styledFigure instanceof NoteFigure)) {
                styledFigure.setBorder(null);
            }
        }
    }

    /**
     * Removes the node label from model children.
     * 
     * @param self
     *            the edit part.
     * @param modelChildren
     *            the children of the node.
     */
    public static void removeLabel(final IAbstractDiagramNodeEditPart self, final List<?> modelChildren) {
        EObject label = null;
        final Iterator<?> iterChildren = modelChildren.iterator();
        while (iterChildren.hasNext() && label == null) {
            final Object current = iterChildren.next();
            if (current instanceof View) {
                final int visualId = SiriusVisualIDRegistry.getVisualID(((View) current).getType());
                if (visualId == NotationViewIDs.DNODE_NAME_EDIT_PART_VISUAL_ID || visualId == NotationViewIDs.DNODE_NAME_2_EDIT_PART_VISUAL_ID
                        || visualId == NotationViewIDs.DNODE_NAME_3_EDIT_PART_VISUAL_ID || visualId == NotationViewIDs.DNODE_NAME_4_EDIT_PART_VISUAL_ID) {
                    label = (EObject) current;
                }
            }
        }
        if (label != null) {
            modelChildren.remove(label);
        }
    }

    /**
     * Updates the alignment of the main label of a Node edit-part.
     * 
     * @param figure
     *            the primary shape of the node edit-part.
     * @param style
     *            the style of the edit-part.
     */
    public static void refreshNodeLabelAlignment(final IFigure figure, final LabelStyle style) {
        if (figure != null && figure.getChildren().size() > 0) {
            final IFigure firstChild = (IFigure) figure.getChildren().get(0);
            if (firstChild instanceof SiriusWrapLabel) {
                final LabelAlignment alignment = style.getLabelAlignment();
                ((SiriusWrapLabel) firstChild).setLabelAlignment(LabelAlignmentHelper.getAsPositionConstant(alignment));
            }
        }
    }

    /**
     * Update the resize direction regarding the node's resize kind.
     * 
     * @param ep
     *            the resizable edit policy.
     * @param node
     *            the current node.
     */
    public static void updateResizeKind(ResizableEditPolicy ep, DNode node) {
        switch (node.getResizeKind().getValue()) {
        case ResizeKind.NONE:
            ep.setResizeDirections(PositionConstants.NONE);
            break;
        case ResizeKind.NORTH_SOUTH:
            ep.setResizeDirections(PositionConstants.NORTH_SOUTH);
            break;
        case ResizeKind.EAST_WEST:
            ep.setResizeDirections(PositionConstants.EAST_WEST);
            break;
        case ResizeKind.NSEW:
            ep.setResizeDirections(PositionConstants.NSEW);
            break;
        default:
            break;
        }
    }
}
