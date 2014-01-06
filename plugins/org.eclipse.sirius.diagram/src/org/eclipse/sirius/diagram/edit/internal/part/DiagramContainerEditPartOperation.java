/*******************************************************************************
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.edit.internal.part;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.ecore.EObject;
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
import org.eclipse.sirius.diagram.business.internal.edit.helpers.LabelAlignmentHelper;
import org.eclipse.sirius.diagram.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.edit.api.part.DiagramNameEditPartOperation;
import org.eclipse.sirius.diagram.edit.api.part.IDiagramContainerEditPart;
import org.eclipse.sirius.diagram.edit.api.part.IDiagramNameEditPart;
import org.eclipse.sirius.diagram.tools.api.graphical.edit.styles.IContainerLabelOffsets;
import org.eclipse.sirius.diagram.ui.tools.api.figure.GradientRoundedRectangle;
import org.eclipse.sirius.diagram.ui.tools.api.figure.IWorkspaceImageFigure;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SVGWorkspaceImageFigure;
import org.eclipse.sirius.diagram.ui.tools.api.figure.ViewNodeContainerFigureDesc;
import org.eclipse.sirius.diagram.ui.tools.api.figure.ViewNodeContainerParallelogram;
import org.eclipse.sirius.diagram.ui.tools.api.figure.ViewNodeContainerRectangleFigureDesc;
import org.eclipse.sirius.diagram.ui.tools.api.figure.WorkspaceImageFigure;
import org.eclipse.sirius.ext.swt.ImageFileFormat;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.viewpoint.DStylizable;
import org.eclipse.sirius.viewpoint.LabelAlignment;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.description.style.ContainerStyleDescription;

/**
 * Implementation of {@link IDiagramContainerEditPart}.
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
    public static void refreshForegroundColor(final IDiagramContainerEditPart self) {
        final EObject eObj = self.resolveSemanticElement();
        if (eObj instanceof DNodeContainer) {
            final DNodeContainer container = (DNodeContainer) eObj;
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
    public static void refreshBackgroundColor(final IDiagramContainerEditPart self) {
        final EObject eObj = self.resolveSemanticElement();
        if (eObj instanceof DNodeContainer) {
            final DNodeContainer container = (DNodeContainer) eObj;
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
    public static void refreshFont(final IDiagramContainerEditPart self) {
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
    public static void refreshVisuals(final IDiagramContainerEditPart self) {
        final DDiagramElement diagElement = self.resolveDiagramElement();
        if (diagElement instanceof DNodeContainer) {
            final DNodeContainer container = (DNodeContainer) diagElement;

            /* The background figure */
            if (self.getBackgroundFigure() instanceof IWorkspaceImageFigure && container.getOwnedStyle() != null) {
                ((IWorkspaceImageFigure) self.getBackgroundFigure()).refreshFigure(container.getOwnedStyle());
            } else if (self.getBackgroundFigure() == null && container.getOwnedStyle() != null) {
                self.createBackgroundFigure();
                if (self.getBackgroundFigure() != null) {
                    self.getFigure().add(self.getBackgroundFigure(), 0);
                }
            }

            final ContainerStyle style = container.getOwnedStyle();
            if (self instanceof AbstractDiagramContainerEditPart && DiagramContainerEditPartOperation.isPrimaryShapeChanging(self, style)) {
                ((AbstractDiagramContainerEditPart) self).reInitFigure();
            }

            int borderSize = style == null ? 0 : style.getBorderSize().intValue();
            if (borderSize == 0) {
                borderSize = 1;
            }

            ViewNodeContainerFigureDesc primaryShape = self.getPrimaryShape();
            if (borderSize > 0) {
                if (primaryShape instanceof Shape) {
                    ((Shape) primaryShape).setLineWidth(borderSize);
                } else if (primaryShape instanceof NodeFigure) {
                    ((NodeFigure) primaryShape).setLineWidth(borderSize);
                }
                if (primaryShape != null && primaryShape.getBorder() instanceof LineBorder) {
                    ((LineBorder) primaryShape.getBorder()).setWidth(borderSize);
                } else if (primaryShape != null && primaryShape.getBorder() instanceof MarginBorder) {
                    // Keep the old behavior : min margin size= 5
                    primaryShape.setBorder(new MarginBorder(borderSize + IContainerLabelOffsets.LABEL_OFFSET - 1, borderSize, borderSize, borderSize));
                }
            }

            if (style instanceof FlatContainerStyle) {
                // The gradient style
                final RGBValues rgb = ((FlatContainerStyle) style).getForegroundColor();
                if (rgb != null && primaryShape instanceof GradientRoundedRectangle) {
                    ((GradientRoundedRectangle) primaryShape).setGradientColor(VisualBindingManager.getDefault().getColorFromRGBValues(rgb));
                    ((GradientRoundedRectangle) primaryShape).setCornerDimensions(DiagramContainerEditPartOperation.getCornerDimension(self));
                }
            }
        }

        if (diagElement != null) {
            self.setTooltipText(diagElement.getTooltipText());
            DiagramContainerEditPartOperation.refreshLabelAlignment(self, diagElement);
        }
    }

    /**
     * 
     * @param self
     * @param style
     * @return if the shape has changed with the style
     */
    private static boolean isPrimaryShapeChanging(final IDiagramContainerEditPart self, final Style style) {
        // Test changed from ShapeContainerStyle
        boolean result = self.getPrimaryShape() instanceof ViewNodeContainerParallelogram && (style instanceof FlatContainerStyle || style instanceof WorkspaceImage);
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

    private static void refreshLabelAlignment(final IDiagramContainerEditPart self, final DDiagramElement diagElement) {
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
            if (workspacePath.toUpperCase().endsWith(ImageFileFormat.SVG.getName())) {
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
        final Dimension corner = new Dimension(0, 0);
        final EObject eObj = self.resolveSemanticElement();
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
