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
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;

import org.eclipse.sirius.ContainerStyle;
import org.eclipse.sirius.DDiagramElement;
import org.eclipse.sirius.DNodeList;
import org.eclipse.sirius.FlatContainerStyle;
import org.eclipse.sirius.LabelAlignment;
import org.eclipse.sirius.RGBValues;
import org.eclipse.sirius.ShapeContainerStyle;
import org.eclipse.sirius.diagram.business.internal.edit.helpers.LabelAlignmentHelper;
import org.eclipse.sirius.diagram.edit.api.part.AbstractDiagramNameEditPart;
import org.eclipse.sirius.diagram.edit.api.part.DiagramNameEditPartOperation;
import org.eclipse.sirius.diagram.edit.api.part.IDiagramListEditPart;
import org.eclipse.sirius.diagram.edit.api.part.IDiagramNameEditPart;
import org.eclipse.sirius.diagram.tools.api.graphical.edit.styles.IContainerLabelOffsets;
import org.eclipse.sirius.diagram.ui.tools.api.figure.GradientRoundedRectangle;
import org.eclipse.sirius.diagram.ui.tools.api.figure.ViewNodeContainerFigureDesc;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;

/**
 * Common operations for list edit parts.
 * 
 * @author ymortier
 */
public final class DiagramListEditPartOperation {

    /**
     * Avoid instantiation.
     */
    private DiagramListEditPartOperation() {

    }

    /**
     * Manages the specified notification.
     * 
     * @param self
     *            the list edit part.
     * @param notification
     *            the notification.
     */
    public static void handleNotificationEvent(final IDiagramListEditPart self, final Notification notification) {
        // empty nothing to do.
    }

    /**
     * Refreshes the visuals of the list.
     * 
     * @param self
     *            the list edit part.
     */
    public static void refreshVisuals(final IDiagramListEditPart self) {
        final DDiagramElement diagElement = self.resolveDiagramElement();
        if (diagElement instanceof DNodeList) {
            final DNodeList list = (DNodeList) diagElement;

            ((Shape) self.getPrimaryShape()).setLineWidth(list.getLineWidth());
            if (list.getLineWidth() == 0) {
                ((Shape) self.getPrimaryShape()).setOutline(false);
            }

            /*
             * The figure.
             */
            if (self.getBackgroundFigure() == null && list.getOwnedStyle() != null) {
                // self.createBackgroundFigure();
                if (self.getBackgroundFigure() != null) {
                    self.getFigure().add(self.getBackgroundFigure(), 0);
                }
            }

            ContainerStyle style = list.getOwnedStyle();
            ViewNodeContainerFigureDesc primaryShape = self.getPrimaryShape();
            int borderSize = style == null ? 0 : style.getBorderSize().intValue();
            if (borderSize == 0) {
                borderSize = 1;
            }

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
            DiagramListEditPartOperation.refreshLabelAlignment(self, diagElement);
        }
    }

    private static void refreshLabelAlignment(final IDiagramListEditPart self, final DDiagramElement diagElement) {
        final LabelAlignment alignment = LabelAlignmentHelper.getLabelAlignementFor(diagElement);
        if (alignment != null) {
            final IFigure fig = self.getFigure();
            if (fig.getChildren().size() > 0) {
                final IFigure firstChild = (IFigure) fig.getChildren().get(0);
                if (firstChild.getChildren().size() > 0) {
                    final IFigure secondChild = (IFigure) firstChild.getChildren().get(0);
                    if (secondChild != null && secondChild.getLayoutManager() instanceof ConstrainedToolbarLayout) {
                        final ConstrainedToolbarLayout ctl = (ConstrainedToolbarLayout) secondChild.getLayoutManager();
                        ctl.setMinorAlignment(LabelAlignmentHelper.getAsCTLMinorAlignment(alignment));
                    }
                }
            }
        }
    }

    /**
     * Refreshes the foreground color of the list.
     * 
     * @param self
     *            the edit part.
     */
    public static void refreshForegroundColor(final IDiagramListEditPart self) {
        final EObject eObj = self.resolveSemanticElement();
        if (eObj instanceof DNodeList) {
            final DNodeList list = (DNodeList) eObj;
            ContainerStyle ownedStyle = list.getOwnedStyle();
            if (ownedStyle != null) {
                if (ownedStyle instanceof FlatContainerStyle) {
                    final RGBValues borderColor = ((FlatContainerStyle) ownedStyle).getBorderColor();
                    if (borderColor != null) {
                        self.getFigure().setForegroundColor(VisualBindingManager.getDefault().getColorFromRGBValues(borderColor));
                    }
                }

            }
        }
    }

    /**
     * Refreshes the background color of the list.
     * 
     * @param self
     *            the edit part.
     */
    public static void refreshBackgroundColor(final IDiagramListEditPart self) {
        final EObject eObj = self.resolveSemanticElement();
        if (eObj instanceof DNodeList) {
            final DNodeList list = (DNodeList) eObj;
            RGBValues rgb = null;
            ContainerStyle ownedStyle = list.getOwnedStyle();
            if (ownedStyle instanceof FlatContainerStyle) {
                rgb = ((FlatContainerStyle) ownedStyle).getBackgroundColor();
            } else if (ownedStyle instanceof ShapeContainerStyle) {
                rgb = ((ShapeContainerStyle) ownedStyle).getBackgroundColor();
            }
            if (rgb != null) {
                self.getFigure().setBackgroundColor(VisualBindingManager.getDefault().getColorFromRGBValues(rgb));
            }
        }
    }

    /**
     * Refreshes the font of the list.
     * 
     * @param self
     *            the edit part.
     */
    public static void refreshFont(final IDiagramListEditPart self) {
        if (!self.getChildren().isEmpty()) {
            Object firstChild = self.getChildren().get(0);
            if (firstChild instanceof IDiagramNameEditPart) {
                DiagramNameEditPartOperation.refreshFont((IDiagramNameEditPart) firstChild);
            }
            Object secondChild = self.getChildren().get(1);
            if (secondChild instanceof CompartmentEditPart) {
                for (Object listEP : ((CompartmentEditPart) secondChild).getChildren()) {
                    if (listEP instanceof AbstractDiagramNameEditPart) {
                        DiagramNameEditPartOperation.refreshFont((AbstractDiagramNameEditPart) listEP);
                    }
                }
            }
        }
    }
}
