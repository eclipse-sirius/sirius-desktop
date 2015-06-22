/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.edit.internal.part;

import java.util.Collection;

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
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.BackgroundStyle;
import org.eclipse.sirius.diagram.ContainerStyle;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.eclipse.sirius.diagram.ShapeContainerStyle;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.description.style.ContainerStyleDescription;
import org.eclipse.sirius.diagram.ui.business.internal.edit.helpers.LabelAlignmentHelper;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.DiagramNameEditPartOperation;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.figure.GradientRoundedRectangle;
import org.eclipse.sirius.diagram.ui.tools.api.figure.IWorkspaceImageFigure;
import org.eclipse.sirius.diagram.ui.tools.api.figure.OneLineMarginBorder;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SVGWorkspaceImageFigure;
import org.eclipse.sirius.diagram.ui.tools.api.figure.ViewNodeContainerFigureDesc;
import org.eclipse.sirius.diagram.ui.tools.api.figure.ViewNodeContainerParallelogram;
import org.eclipse.sirius.diagram.ui.tools.api.figure.ViewNodeContainerRectangleFigureDesc;
import org.eclipse.sirius.diagram.ui.tools.api.figure.WorkspaceImageFigure;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IContainerLabelOffsets;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.viewpoint.DStylizable;
import org.eclipse.sirius.viewpoint.LabelAlignment;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.Style;

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

            /* The background figure */
            if (self.getBackgroundFigure() instanceof IWorkspaceImageFigure && ddec.getOwnedStyle() != null) {
                ((IWorkspaceImageFigure) self.getBackgroundFigure()).refreshFigure(ddec.getOwnedStyle());
            }

            final ContainerStyle style = ddec.getOwnedStyle();
            if (DiagramContainerEditPartOperation.isPrimaryShapeChanging(self, style)) {
                self.reInitFigure();
            }
            ViewNodeContainerFigureDesc primaryShape = self.getPrimaryShape();

            refreshBorder(self, primaryShape, style);
            if (primaryShape instanceof GradientRoundedRectangle) {
                refreshGradient(style, (GradientRoundedRectangle) primaryShape);
            }
            if (primaryShape instanceof RoundedRectangle) {
                refreshCorners(self, diagElement, (RoundedRectangle) primaryShape);
            }
        }

        if (diagElement != null) {
            self.setTooltipText(diagElement.getTooltipText());
            refreshLabelAlignment(self, diagElement);
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

    private static void refreshCorners(final AbstractDiagramElementContainerEditPart self, DDiagramElement diagElement, RoundedRectangle gradientRoundedShape) {
        Dimension cornerDimension = getCornerDimension(self);
        if (self.isRegion()) {
            // If the current stack is a Region, reuse the parent corner
            // dimension as max, to avoid to overlap its border.
            DNodeContainer regionContainer = (DNodeContainer) diagElement.eContainer();
            Dimension regionContainerCornerDimension = getCornerDimension(regionContainer);
            if (regionContainerCornerDimension.contains(cornerDimension)) {
                int parentStackDirection = self.getParentStackDirection();
                if (parentStackDirection == PositionConstants.NORTH_SOUTH && isLastRegionPart(self)) {
                    cornerDimension = Dimension.max(cornerDimension, regionContainerCornerDimension);
                    // TODO: Vertical stack and last region, we should have
                    // bottom rounded corners only;
                    updatePrecedingSiblingCorner(self);
                } else if (parentStackDirection == PositionConstants.EAST_WEST) {
                    boolean firstRegionPart = isFirstRegionPart(self);
                    boolean lastRegionPart = isLastRegionPart(self);
                    cornerDimension = (firstRegionPart || lastRegionPart) ? Dimension.max(cornerDimension, regionContainerCornerDimension) : cornerDimension;
                    // TODO: Horizontal stack and first/last region, we should
                    // have bottom
                    // left/right rounded corner only
                    if (lastRegionPart) {
                        updatePrecedingSiblingCorner(self);
                    }
                }
            }
        }

        // Update the corner dimension.
        gradientRoundedShape.setCornerDimensions(cornerDimension);
    }

    private static void updatePrecedingSiblingCorner(final AbstractDiagramElementContainerEditPart self) {
        // Update previous siblings: needed for the diagram
        // opening and the region container creation cases in
        // which each child will be the last element once.
        Collection<AbstractDiagramElementContainerEditPart> siblings = Lists.newArrayList(Iterables.filter(self.getParent().getChildren(), AbstractDiagramElementContainerEditPart.class));
        siblings.remove(self);
        AbstractDiagramElementContainerEditPart previous = siblings.isEmpty() ? null : Iterables.getLast(siblings);
        if (previous != null && previous.getPrimaryShape() instanceof GradientRoundedRectangle) {
            ((GradientRoundedRectangle) previous.getPrimaryShape()).setCornerDimensions(getCornerDimension(previous));
        }
    }

    private static ViewNodeContainerFigureDesc refreshBorder(final AbstractDiagramElementContainerEditPart self, final ViewNodeContainerFigureDesc primaryShape, final ContainerStyle style) {
        int borderSize = 0;
        if (style != null && style.getBorderSize() != null) {
            borderSize = style.getBorderSize().intValue();
        }
        if (primaryShape instanceof Shape) {
            ((Shape) primaryShape).setLineWidth(borderSize);
            if (borderSize == 0) {
                ((Shape) primaryShape).setOutline(false);
            } else {
                ((Shape) primaryShape).setOutline(true);
            }
        } else if (primaryShape instanceof NodeFigure) {
            ((NodeFigure) primaryShape).setLineWidth(borderSize);
        }

        // Do not add the container label offset margin if there is no
        // visible label.
        int labelOffset = IContainerLabelOffsets.LABEL_OFFSET;
        if (primaryShape.getLabelFigure() == null || !primaryShape.getLabelFigure().isVisible()) {
            labelOffset = 0;
        }

        if (primaryShape != null && primaryShape.getBorder() instanceof LineBorder) {
            ((LineBorder) primaryShape.getBorder()).setWidth(borderSize);
            if (primaryShape.getBorder() instanceof OneLineMarginBorder) {
                ((OneLineMarginBorder) primaryShape.getBorder()).setMargin(labelOffset, 0, 0, 0);
            }
        } else if (primaryShape != null && primaryShape.getBorder() instanceof MarginBorder) {
            MarginBorder margin = null;
            int borderMagin = borderSize;
            switch (self.getParentStackDirection()) {
            case PositionConstants.NORTH_SOUTH:
                borderMagin = isFirstRegionPart(self) ? 0 : borderSize - 1;
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
            if (!result) {
                // Test changed from WorkspaceImage
                result = self.getPrimaryShape() instanceof ViewNodeContainerRectangleFigureDesc && (style instanceof FlatContainerStyle || style instanceof ShapeContainerStyle);
            }
        }
        return result;
    }

    private static void refreshLabelAlignment(final AbstractDiagramElementContainerEditPart self, final DDiagramElement diagElement) {
        final LabelAlignment alignment = LabelAlignmentHelper.getLabelAlignementFor(diagElement);
        if (alignment != null) {
            final IFigure fig = self.getFigure();
            if (fig.getChildren().size() > 0) {
                final IFigure firstChild = (IFigure) fig.getChildren().get(0);
                if (firstChild != null && firstChild.getChildren().size() > 0) {
                    final IFigure firstGrandChild = (IFigure) firstChild.getChildren().get(0);
                    if (firstGrandChild != null && firstGrandChild.getLayoutManager() instanceof ConstrainedToolbarLayout) {
                        final ConstrainedToolbarLayout ctl = (ConstrainedToolbarLayout) firstGrandChild.getLayoutManager();
                        ctl.setMinorAlignment(LabelAlignmentHelper.getAsCTLMinorAlignment(alignment));
                    }
                }
            }
        }
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
            if ((container.getOwnedStyle() != null) && (container.getOwnedStyle() instanceof WorkspaceImage)) {
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
        if (eObj instanceof DStylizable) {
            final Style style = ((DStylizable) eObj).getStyle();
            if (style != null && style.getDescription() instanceof ContainerStyleDescription) {
                final ContainerStyleDescription description = (ContainerStyleDescription) style.getDescription();
                if (description.isRoundedCorner()) {
                    corner.height = description.getArcHeight();
                    corner.width = description.getArcWidth();
                }
            }
        }
        return corner;
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
}
