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
package org.eclipse.sirius.diagram.ui.edit.internal.part;

import java.util.BitSet;
import java.util.Collection;

import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.BackgroundStyle;
import org.eclipse.sirius.diagram.ContainerStyle;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.eclipse.sirius.diagram.LineStyle;
import org.eclipse.sirius.diagram.ShapeContainerStyle;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.description.style.ContainerStyleDescription;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.DiagramNameEditPartOperation;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.figure.GradientRoundedRectangle;
import org.eclipse.sirius.diagram.ui.tools.api.figure.IWorkspaceImageFigure;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SVGWorkspaceImageFigure;
import org.eclipse.sirius.diagram.ui.tools.api.figure.ViewNodeContainerParallelogram;
import org.eclipse.sirius.diagram.ui.tools.api.figure.ViewNodeContainerRectangleFigureDesc;
import org.eclipse.sirius.diagram.ui.tools.api.figure.WorkspaceImageFigure;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.RegionRoundedGradientRectangle;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.IContainerLabelOffsets;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.OneLineMarginBorder;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.RoundedCornerMarginBorder;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.ViewNodeContainerFigureDesc;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.viewpoint.DStylizable;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Common operations for container and list edit parts.
 * 
 * @author ymortier
 */
public final class DiagramContainerEditPartOperation {

    /**
     * Avoid instantiation.
     */
    private DiagramContainerEditPartOperation() {

    }

    /**
     * Refreshes the foreground color of the container.
     * 
     * @param self
     *            the edit part.
     */
    public static void refreshForegroundColor(final AbstractDiagramElementContainerEditPart self) {
        final EObject eObj = self.resolveSemanticElement();
        if (eObj instanceof DDiagramElementContainer) {
            final DDiagramElementContainer container = (DDiagramElementContainer) eObj;
            ContainerStyle style = container.getOwnedStyle();
            if (style != null) {
                RGBValues rgb = style.getBorderColor();
                if (rgb != null) {
                    self.getFigure().setForegroundColor(VisualBindingManager.getDefault().getColorFromRGBValues(rgb));
                }
            }
        }
    }

    /**
     * Refreshes the background color of the container.
     * 
     * @param self
     *            the edit part.
     */
    public static void refreshBackgroundColor(final AbstractDiagramElementContainerEditPart self) {
        final EObject eObj = self.resolveSemanticElement();
        if (eObj instanceof DDiagramElementContainer) {
            final DDiagramElementContainer container = (DDiagramElementContainer) eObj;
            RGBValues rgb = null;
            ContainerStyle style = container.getOwnedStyle();
            if (style instanceof FlatContainerStyle) {
                rgb = ((FlatContainerStyle) style).getBackgroundColor();
            } else if (style instanceof ShapeContainerStyle) {
                rgb = ((ShapeContainerStyle) style).getBackgroundColor();
            }
            if (rgb != null) {
                self.getFigure().setBackgroundColor(VisualBindingManager.getDefault().getColorFromRGBValues(rgb));
            }
        }
    }

    /**
     * Refreshes the font of the container.
     * 
     * @param self
     *            the edit part.
     */
    public static void refreshFont(final AbstractDiagramElementContainerEditPart self) {
        if (!self.getChildren().isEmpty()) {
            Object firstChild = self.getChildren().get(0);
            if (firstChild instanceof IDiagramNameEditPart) {
                DiagramNameEditPartOperation.refreshFont((IDiagramNameEditPart) firstChild);
            }
        }
    }

    /**
     * Refreshes the visuals of the container.
     * 
     * @param self
     *            the edit part.
     */
    public static void refreshVisuals(final AbstractDiagramElementContainerEditPart self) {
        final DDiagramElement diagElement = self.resolveDiagramElement();
        if (diagElement instanceof DDiagramElementContainer) {
            final DDiagramElementContainer ddec = (DDiagramElementContainer) diagElement;
            final ContainerStyle style = ddec.getOwnedStyle();

            // Change the primary shape if needed.
            refreshBackgroundFigure(self, style);

            final ViewNodeContainerFigureDesc primaryShape = self.getPrimaryShape();
            refreshBorder(self, primaryShape, style);

            if (primaryShape instanceof GradientRoundedRectangle) {
                refreshGradient(style, (GradientRoundedRectangle) primaryShape);
            }

            refreshCorners(self, diagElement, primaryShape);
            DiagramElementEditPartOperation.refreshLabelAlignment(self.getContentPane(), style);
        }

        if (diagElement != null) {
            self.setTooltipText(diagElement.getTooltipText());
        }
    }

    /**
     * This method might refresh the background figure, or change it regarding
     * how the style has evolved.
     */
    private static void refreshBackgroundFigure(final AbstractDiagramElementContainerEditPart self, final ContainerStyle style) {
        // Update the background figure when the workspace image path changes.
        if (self.getBackgroundFigure() instanceof IWorkspaceImageFigure && style != null) {
            IWorkspaceImageFigure figure = (IWorkspaceImageFigure) self.getBackgroundFigure();
            // Check if the figure is the right one (SVGWorkspaceImageFigure for
            // SVG format, WorkspaceImageFigure for others kinds).
            boolean needFigureChange = false;
            if (style instanceof WorkspaceImage) {
                needFigureChange = DiagramContainerEditPartOperation.needFigureChange((WorkspaceImage) style, figure);
            }
            if (needFigureChange) {
                // Replace the wrong IWorkspaceImageFigure kind by the new one.
                self.reInitFigure();
            } else {
                // Refresh the right IWorkspaceImageFigure kind
                ((IWorkspaceImageFigure) self.getBackgroundFigure()).refreshFigure(style);
            }
        }

        // Update the background figure when the background gradient style
        ViewNodeContainerFigureDesc primaryShape = self.getPrimaryShape();
        if (primaryShape instanceof GradientRoundedRectangle && style instanceof FlatContainerStyle) {
            if (((GradientRoundedRectangle) primaryShape).getBackgroundStyle() != ((FlatContainerStyle) style).getBackgroundStyle().getValue()) {
                self.reInitFigure();
            }
        }

        // Update background figure when the primary shape should change.
        if (isPrimaryShapeChanging(self, style)) {
            self.reInitFigure();
        }
    }

    private static void refreshGradient(final ContainerStyle style, GradientRoundedRectangle gradientRoundedShape) {
        // The gradient style
        if (style instanceof FlatContainerStyle) {
            final RGBValues rgb = ((FlatContainerStyle) style).getForegroundColor();
            if (rgb != null) {
                gradientRoundedShape.setGradientColor(VisualBindingManager.getDefault().getColorFromRGBValues(rgb));
            }
        }
    }

    private static void refreshCorners(final AbstractDiagramElementContainerEditPart self, DDiagramElement diagElement, ViewNodeContainerFigureDesc primaryShape) {
        Dimension cornerDimension = getCornerDimension(self);
        Dimension specificDimension = cornerDimension;
        BitSet specificCornerPosition = new BitSet(PositionConstants.NSEW);
        if (self.isRegion()) {
            // If the current stack is a Region, check and avoid overlap of the
            // region container border.
            DNodeContainer regionContainer = (DNodeContainer) diagElement.eContainer();
            Dimension regionContainerCornerDimension = getCornerDimension(regionContainer);
            if (!regionContainerCornerDimension.getShrinked(cornerDimension).isEmpty()) {
                int parentStackDirection = self.getParentStackDirection();
                boolean firstRegionPart = isFirstRegionPart(self);
                boolean lastRegionPart = isLastRegionPart(self);
                if (parentStackDirection == PositionConstants.NORTH_SOUTH && lastRegionPart) {
                    cornerDimension = Dimension.max(cornerDimension, regionContainerCornerDimension);
                    if (specificDimension != cornerDimension) {
                        specificCornerPosition.set(PositionConstants.NORTH_WEST);
                        specificCornerPosition.set(PositionConstants.NORTH_EAST);
                    }
                    updatePrecedingSiblingCorner(self, PositionConstants.SOUTH_WEST, PositionConstants.SOUTH_EAST);
                } else if (parentStackDirection == PositionConstants.EAST_WEST && (firstRegionPart || lastRegionPart)) {
                    cornerDimension = Dimension.max(cornerDimension, regionContainerCornerDimension);

                    specificCornerPosition.set(PositionConstants.NORTH_WEST);
                    specificCornerPosition.set(PositionConstants.NORTH_EAST);
                    if (firstRegionPart && !lastRegionPart) {
                        specificCornerPosition.set(PositionConstants.SOUTH_EAST);
                    } else if (!firstRegionPart && lastRegionPart) {
                        specificCornerPosition.set(PositionConstants.SOUTH_WEST);
                    }

                    if (lastRegionPart && !firstRegionPart) {
                        updatePrecedingSiblingCorner(self, PositionConstants.SOUTH_EAST);
                    }
                }
            }
        }

        // Update the corner dimension.
        if (primaryShape instanceof RoundedRectangle) {
            ((RoundedRectangle) primaryShape).setCornerDimensions(cornerDimension);
            if (primaryShape instanceof RegionRoundedGradientRectangle) {
                ((RegionRoundedGradientRectangle) primaryShape).setAdditionalCornerDimensions(specificDimension, specificCornerPosition);
            }
        }
    }

    private static void updatePrecedingSiblingCorner(final AbstractDiagramElementContainerEditPart self, int... cornerToCorrect) {
        // Update previous siblings: needed for the diagram
        // opening and the region container creation cases in
        // which each child will be the last element once.
        Collection<AbstractDiagramElementContainerEditPart> siblings = Lists.newArrayList(Iterables.filter(self.getParent().getChildren(), AbstractDiagramElementContainerEditPart.class));
        siblings.remove(self);
        AbstractDiagramElementContainerEditPart previous = siblings.isEmpty() ? null : Iterables.getLast(siblings);
        if (previous != null && previous.getPrimaryShape() instanceof RegionRoundedGradientRectangle) {
            RegionRoundedGradientRectangle gradientRoundedRectangle = (RegionRoundedGradientRectangle) previous.getPrimaryShape();
            if (!gradientRoundedRectangle.getAdditionalCornerDimensions().equals(gradientRoundedRectangle.getCornerDimensions())) {
                for (int i : cornerToCorrect) {
                    gradientRoundedRectangle.getAdditionalDimensionCorners().set(i);
                }
            }

            if (gradientRoundedRectangle.getAdditionalDimensionCorners().cardinality() == 4) {
                // we do not need specific corner anymore
                gradientRoundedRectangle.getAdditionalDimensionCorners().clear();
                gradientRoundedRectangle.setCornerDimensions(gradientRoundedRectangle.getAdditionalCornerDimensions());
            }
        }
    }

    private static ViewNodeContainerFigureDesc refreshBorder(final AbstractDiagramElementContainerEditPart self, final ViewNodeContainerFigureDesc primaryShape, final ContainerStyle style) {
        final int parentStackDirection = self.getParentStackDirection();

        LineStyle borderLineStyle = LineStyle.SOLID_LITERAL;
        int borderSize = 0;
        if (style != null) {
            borderLineStyle = style.getBorderLineStyle();
            if (style.getBorderSize() != null) {
                borderSize = style.getBorderSize().intValue();
            }
        }

        // Refresh border size and style of the primary shape.
        if (primaryShape instanceof Shape) {
            Shape shape = (Shape) primaryShape;
            shape.setLineWidth(borderSize);
            // Deactivate the outline of the shape if there is no border or if
            // this is a region.
            shape.setOutline(borderSize > 0 && parentStackDirection == PositionConstants.NONE);
            DiagramElementEditPartOperation.setLineStyle(shape, borderLineStyle, false);
        } else if (primaryShape instanceof NodeFigure) {
            NodeFigure nodeFigure = (NodeFigure) primaryShape;
            nodeFigure.setLineWidth(borderSize);
            DiagramElementEditPartOperation.setLineStyle(nodeFigure, borderLineStyle);
        }

        if (primaryShape != null) {
            // We might need to reinit the kind of border : LineBorder will
            // always display a 1 pix line even if we configure it with a 0 line
            // width.
            configureBorder(self, primaryShape, borderSize != 0);

            // Do not add the container label offset margin if there is no
            // visible label.
            int labelOffset = IContainerLabelOffsets.LABEL_OFFSET;
            if (primaryShape.getLabelFigure() == null || !primaryShape.getLabelFigure().isVisible()) {
                labelOffset = 0;
            }

            // Handle border line style, corner dimension and/or margin
            if (primaryShape.getBorder() instanceof LineBorder) {
                LineBorder lineBorder = (LineBorder) primaryShape.getBorder();
                lineBorder.setWidth(borderSize);
                DiagramElementEditPartOperation.setLineStyle(lineBorder, borderLineStyle);
                if (lineBorder instanceof OneLineMarginBorder) {
                    OneLineMarginBorder oneLineBorder = (OneLineMarginBorder) lineBorder;
                    oneLineBorder.setMargin(labelOffset, 0, 0, 0);

                    if (parentStackDirection == PositionConstants.NORTH_SOUTH) {
                        oneLineBorder.setPosition(PositionConstants.TOP);
                    } else if (parentStackDirection == PositionConstants.EAST_WEST) {
                        oneLineBorder.setPosition(PositionConstants.LEFT);
                    }

                    if (self.isRegion() && lineBorder instanceof RoundedCornerMarginBorder) {
                        ((RoundedCornerMarginBorder) lineBorder).setCornerDimensions(getCornerDimension(self));
                    }
                }
            } else if (primaryShape.getBorder() instanceof MarginBorder) {
                MarginBorder margin = null;
                int borderMagin = borderSize;
                switch (parentStackDirection) {
                case PositionConstants.NORTH_SOUTH:
                    borderMagin = isFirstRegionPart(self) ? 0 : Math.max(0, borderSize - 1);
                    margin = new MarginBorder(borderMagin + labelOffset, 0, 0, 0);
                    break;
                case PositionConstants.EAST_WEST:
                    borderMagin = isFirstRegionPart(self) ? 0 : borderSize;
                    margin = new MarginBorder(labelOffset, borderMagin, 0, 0);
                    break;
                case PositionConstants.NONE:
                default:
                    // Keep the old behavior : min margin size= 4
                    // The current container is not a region, the figure has
                    // been added to the content pane.
                    margin = new MarginBorder(borderMagin + labelOffset - 1, borderMagin, borderMagin, borderMagin);
                }
                primaryShape.setBorder(margin);
            }
        }
        return primaryShape;
    }

    private static boolean isFirstRegionPart(AbstractDiagramElementContainerEditPart self) {
        EditPart parent = self.getParent();
        if (parent instanceof AbstractDNodeContainerCompartmentEditPart) {
            Iterable<AbstractDiagramElementContainerEditPart> regionParts = Iterables.filter(parent.getChildren(), AbstractDiagramElementContainerEditPart.class);
            return !Iterables.isEmpty(regionParts) && regionParts.iterator().next() == self;
        }
        return false;
    }

    private static boolean isLastRegionPart(AbstractDiagramElementContainerEditPart self) {
        EditPart parent = self.getParent();
        if (parent instanceof AbstractDNodeContainerCompartmentEditPart) {
            Iterable<AbstractDiagramElementContainerEditPart> regionParts = Iterables.filter(parent.getChildren(), AbstractDiagramElementContainerEditPart.class);
            return !Iterables.isEmpty(regionParts) && Iterables.getLast(regionParts) == self;
        }
        return false;
    }

    /**
     * 
     * @param self
     * @param style
     * @return if the shape has changed with the style
     */
    private static boolean isPrimaryShapeChanging(final AbstractDiagramElementContainerEditPart self, final Style style) {
        boolean result = false;

        // Test changed from ShapeContainerStyle
        result = self.getPrimaryShape() instanceof ViewNodeContainerParallelogram && (style instanceof FlatContainerStyle || style instanceof WorkspaceImage);
        if (!result) {
            // Test changed from FlatContainerStyle
            result = self.getPrimaryShape() instanceof GradientRoundedRectangle && (style instanceof ShapeContainerStyle || style instanceof WorkspaceImage);
        }
        if (!result) {
            // Test changed from WorkspaceImage
            result = self.getPrimaryShape() instanceof ViewNodeContainerRectangleFigureDesc && (style instanceof FlatContainerStyle || style instanceof ShapeContainerStyle);
        }
        if (!result) {
            // Test changed from WorkspaceImage.workspacePath
            result = style instanceof WorkspaceImage && (self.getBackgroundFigure() == null ^ StringUtil.isEmpty(((WorkspaceImage) style).getWorkspacePath()));
        }
        return result;
    }

    /**
     * Creates the background image.
     * 
     * @param self
     *            the container edit part.
     * @return the created image.
     */
    public static IFigure createBackgroundFigure(final IGraphicalEditPart self) {
        final EObject eObj = self.resolveSemanticElement();
        if (eObj instanceof DDiagramElementContainer) {
            final DDiagramElementContainer container = (DDiagramElementContainer) eObj;
            if (container.getOwnedStyle() instanceof WorkspaceImage) {
                final WorkspaceImage img = (WorkspaceImage) container.getOwnedStyle();
                return createWkpImageFigure(img);
            }
        }
        return null;
    }

    private static IFigure createWkpImageFigure(final WorkspaceImage img) {
        IFigure imageFigure = null;
        if (img != null && !StringUtil.isEmpty(img.getWorkspacePath())) {
            String workspacePath = img.getWorkspacePath();
            if (WorkspaceImageFigure.isSvgImage(workspacePath)) {
                imageFigure = SVGWorkspaceImageFigure.createImageFigure(img);
            } else {
                imageFigure = WorkspaceImageFigure.createImageFigure(workspacePath);
            }
        }
        return imageFigure;
    }

    /**
     * Gets the corner dimensions.
     * 
     * @param self
     *            the container edit part.
     * @return the corner dimensions.
     */
    public static Dimension getCornerDimension(final IGraphicalEditPart self) {
        final EObject eObj = self.resolveSemanticElement();
        return getCornerDimension(eObj);
    }

    private static Dimension getCornerDimension(EObject eObj) {
        final Dimension corner = new Dimension(0, 0);
        StyleDescription styleDescription = getStyleDecription(eObj);
        if (styleDescription instanceof ContainerStyleDescription) {
            final ContainerStyleDescription description = (ContainerStyleDescription) styleDescription;
            if (description.isRoundedCorner()) {
                corner.setHeight(description.getArcHeight());
                corner.setWidth(description.getArcWidth());
            }
        }
        return corner;
    }

    private static StyleDescription getStyleDecription(EObject eObj) {
        if (eObj instanceof DStylizable) {
            final Style style = ((DStylizable) eObj).getStyle();
            if (style != null) {
                return style.getDescription();
            }
        }
        return null;
    }

    /**
     * Gets the background style.
     * 
     * @param self
     *            the container edit part.
     * @return the background style.
     */
    public static BackgroundStyle getBackgroundStyle(final IGraphicalEditPart self) {
        BackgroundStyle bgStyle = BackgroundStyle.GRADIENT_LEFT_TO_RIGHT_LITERAL;
        final EObject eObj = self.resolveSemanticElement();
        if (eObj instanceof DStylizable) {
            final Style style = ((DStylizable) eObj).getStyle();
            if (style instanceof FlatContainerStyle) {
                bgStyle = ((FlatContainerStyle) style).getBackgroundStyle();
            }
        }
        return bgStyle;
    }

    /**
     * Configure the border : set or replace the border when it needs to be
     * changed. This can occurs for Regions when the region position changes or
     * when the border size changes from 0 or to 0.
     * 
     * The refreshBorder method will then update all the border features during
     * refreshVisual().
     * 
     * @param self
     *            the current part
     * @param shapeFigure
     *            the shape to configure
     */
    public static void configureBorder(AbstractDiagramElementContainerEditPart self, IFigure shapeFigure) {
        boolean border = true;
        final DDiagramElement diagElement = self.resolveDiagramElement();
        if (diagElement instanceof DDiagramElementContainer) {
            final DDiagramElementContainer ddec = (DDiagramElementContainer) diagElement;
            final ContainerStyle style = ddec.getOwnedStyle();
            if (style != null && style.getBorderSize() != null) {
                border = style.getBorderSize().intValue() != 0;
            }
        }
        configureBorder(self, shapeFigure, border);
    }

    private static void configureBorder(AbstractDiagramElementContainerEditPart self, IFigure shapeFigure, boolean border) {
        if (self.isRegion() && shapeFigure != null) {
            Border newBorder = null;
            if (isFirstRegionPart(self) || !border) {
                if (!(shapeFigure.getBorder() instanceof MarginBorder)) {
                    newBorder = new MarginBorder(IContainerLabelOffsets.LABEL_OFFSET, 0, 0, 0);
                }
            } else if (!(shapeFigure.getBorder() instanceof RoundedCornerMarginBorder)) {
                int position = PositionConstants.TOP;
                if (self.getParentStackDirection() == PositionConstants.EAST_WEST) {
                    position = PositionConstants.LEFT;
                }

                RoundedCornerMarginBorder oneLineBorder = new RoundedCornerMarginBorder(position);
                oneLineBorder.setCornerDimensions(getCornerDimension(self));
                oneLineBorder.setMargin(IContainerLabelOffsets.LABEL_OFFSET, 0, 0, 0);

                newBorder = oneLineBorder;
            }

            if (newBorder != null) {
                shapeFigure.setBorder(newBorder);
            }
        }
    }

    /**
     * Check if the current <code>figure</code> is the right one
     * (SVGWorkspaceImageFigure for SVG format, WorkspaceImageFigure for others
     * kinds) according to the {@link WorkspaceImage} style.
     * 
     * @param bundledImage
     *            the {@link WorkspaceImage} style
     * @param figure
     *            The figure to check.
     * @return true if a new figure must be create according to style
     *         <code>bundledImage</code>, false if the current figure can be
     *         used.
     */
    public static boolean needFigureChange(final WorkspaceImage bundledImage, IWorkspaceImageFigure figure) {
        boolean result = false;
        String workspacePath = bundledImage.getWorkspacePath();
        if (!StringUtil.isEmpty(workspacePath) && WorkspaceImageFigure.isSvgImage(workspacePath)) {
            result = !(figure instanceof SVGWorkspaceImageFigure);
        } else {
            result = !(figure instanceof WorkspaceImageFigure);
        }
        return result;
    }
}
