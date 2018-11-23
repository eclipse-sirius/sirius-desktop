/*******************************************************************************
 * Copyright (c) 2007, 2023 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.edit.internal.part;

import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.Shape;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.figures.NoteFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.BorderedStyle;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.LabelPosition;
import org.eclipse.sirius.diagram.LineStyle;
import org.eclipse.sirius.diagram.NodeStyle;
import org.eclipse.sirius.diagram.ResizeKind;
import org.eclipse.sirius.diagram.ui.business.internal.query.StyleConfigurationQuery;
import org.eclipse.sirius.diagram.ui.edit.api.part.DiagramNameEditPartOperation;
import org.eclipse.sirius.diagram.ui.edit.api.part.IAbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IStyleEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.NotationViewIDs;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IStyleConfigurationRegistry;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.StyleConfiguration;
import org.eclipse.sirius.ext.draw2d.figure.StyledFigure;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.SiriusWrapLabel;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
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
        }
    }

    /**
     * Refreshes the figure of the styleEditPart.
     * 
     * @param styleEditPart
     *            the styleEditPart.
     */
    public static void refreshFigure(final IStyleEditPart styleEditPart) {
        IFigure figure = styleEditPart.getContentPane();
        if (figure instanceof StyledFigure) {
            StyledFigure styledFigure = (StyledFigure) figure;
            EditPart parent = styleEditPart.getParent();
            if (parent instanceof IAbstractDiagramNodeEditPart) {
                IAbstractDiagramNodeEditPart self = (IAbstractDiagramNodeEditPart) parent;
                DDiagramElement dDiagramElement = self.resolveDiagramElement();
                if (dDiagramElement instanceof DNode) {
                    DNode viewNode = (DNode) dDiagramElement;
                    NodeStyle nodeStyle = (NodeStyle) viewNode.getStyle();

                    LabelPosition labelPosition = nodeStyle.getLabelPosition();
                    if (labelPosition != null && labelPosition == LabelPosition.NODE_LITERAL && !styledFigure.getChildren().contains(self.getNodeLabel())) {
                        SiriusWrapLabel nodeLabel = self.getNodeLabel();
                        styledFigure.add(nodeLabel);
                        nodeLabel.setLayoutManager(styledFigure.getLayoutManager());
                    }
                    if (labelPosition != null && labelPosition == LabelPosition.BORDER_LITERAL && styledFigure.getChildren().contains(self.getNodeLabel())) {
                        styledFigure.remove(self.getNodeLabel());
                    }
                    if (styledFigure.getChildren().contains(self.getNodeLabel())) {
                        DiagramElementEditPartOperation.refreshFont(self, viewNode, self.getNodeLabel());
                        self.getNodeLabel().setText(viewNode.getName());
                        StyleConfiguration styleConfiguration = IStyleConfigurationRegistry.INSTANCE.getStyleConfiguration(viewNode.getDiagramElementMapping(), nodeStyle);
                        self.getNodeLabel().setIcon(new StyleConfigurationQuery(styleConfiguration).getLabelIcon(dDiagramElement, self));
                        styleConfiguration.adaptNodeLabel(viewNode, self.getNodeLabel());
                    }

                    DiagramNodeEditPartOperation.refreshBorderFigure(nodeStyle, styledFigure);
                    self.setTooltipText(viewNode.getTooltipText());
                    self.getNodeLabel().revalidate();
                }
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
        int borderSize = 0;
        if (borderedStyle.getBorderSize() != null) {
            borderSize = borderedStyle.getBorderSize().intValue();
        }
        lineBorder.setWidth(borderSize);
        final RGBValues borderColor = borderedStyle.getBorderColor();
        if (borderColor != null) {
            lineBorder.setColor(VisualBindingManager.getDefault().getColorFromRGBValues(borderColor));
        }

        refreshBorderLineStyle(borderedStyle, styledFigure, lineBorder);

        if (borderSize == 0) {
            /* NoteFigure in GMF does not expect a null figure since GMF 2.2 */
            if (!(styledFigure instanceof NoteFigure)) {
                styledFigure.setBorder(null);
            }
        }
    }

    private static void refreshBorderLineStyle(final BorderedStyle borderedStyle, final StyledFigure styledFigure, LineBorder lineBorder) {
        final LineStyle borderLineStyle = borderedStyle.getBorderLineStyle();
        if (styledFigure instanceof Shape) {
            DiagramElementEditPartOperation.setLineStyle((Shape) styledFigure, borderLineStyle, false);
        } else if (styledFigure instanceof NodeFigure) {
            DiagramElementEditPartOperation.setLineStyle((NodeFigure) styledFigure, borderLineStyle);
        }

        DiagramElementEditPartOperation.setLineStyle(lineBorder, borderLineStyle);
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
