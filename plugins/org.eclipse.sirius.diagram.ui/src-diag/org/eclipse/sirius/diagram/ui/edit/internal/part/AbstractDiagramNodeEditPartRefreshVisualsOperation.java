/*******************************************************************************
 * Copyright (c) 2009, 2024 THALES GLOBAL SERVICES and others.
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

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.LabelPosition;
import org.eclipse.sirius.diagram.NodeStyle;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.business.api.query.DNodeQuery;
import org.eclipse.sirius.diagram.ui.edit.api.part.IAbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.figure.IWorkspaceImageFigure;
import org.eclipse.sirius.diagram.ui.tools.api.figure.WorkspaceImageFigure;
import org.eclipse.sirius.diagram.ui.tools.api.figure.locator.DBorderItemLocator;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IBorderItemOffsets;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IStyleConfigurationRegistry;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.StyleConfiguration;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutUtils;
import org.eclipse.sirius.ext.draw2d.figure.StyledFigure;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.SiriusWrapLabel;
import org.eclipse.swt.graphics.Image;

/**
 * Class which handles the refresh on visuals.
 * 
 * @author mchauvin
 */
public class AbstractDiagramNodeEditPartRefreshVisualsOperation {

    /**
     * The editPart to refresh.
     */

    protected IAbstractDiagramNodeEditPart editPart;

    /**
     * The node of the edit part to refresh.
     */
    protected DNode node;

    /**
     * Construct a new instance.
     * 
     * @param editPart
     *            the editPart to refresh
     */
    protected AbstractDiagramNodeEditPartRefreshVisualsOperation(final IAbstractDiagramNodeEditPart editPart) {
        this.editPart = editPart;
        final EObject eObj = editPart.resolveSemanticElement();
        if (eObj instanceof DNode) {
            node = (DNode) eObj;
        }
    }

    /**
     * Check if refresh can occur.
     * 
     * @return <code>true</code> if refresh methods could be called safely, <code>false</code> otherwise
     */
    public boolean canRefresh() {
        return node != null;
    }

    /**
     * Refresh the size.
     */
    public void refreshSize() {

        // Width
        int width = refreshWidth();

        // Height
        int height = computeHeight(width);

        Dimension d = new Dimension(width, height);
        DefaultSizeNodeFigure nodePlate = getNodePlate();

        final int w = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Width())).intValue();
        final int h = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Height())).intValue();

        // Store the (-1, -1) auto-size values in the maxSize (value never return see
        // org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.AirDefaultSizeNodeFigure.getMaximumSize())
        Dimension maxd = d.getCopy();
        if (w == -1) {
            maxd.width = -1;
        }
        if (h == -1) {
            maxd.height = -1;
        }
        setSizes(d, maxd, nodePlate);

        final int x = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getLocation_X())).intValue();
        final int y = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getLocation_Y())).intValue();

        // style
        if (node.getStyle() != null && ((NodeStyle) node.getStyle()).getLabelPosition().getValue() == LabelPosition.NODE) {
            // The label is in the graphical element. We must adapt the
            // size.
            final SiriusWrapLabel nodeLabel = editPart.getNodeLabel();
            if (nodeLabel != null) {
                final StyleConfiguration styleConfiguration = IStyleConfigurationRegistry.INSTANCE.getStyleConfiguration(node.getDiagramElementMapping(), node.getStyle());

                if (new DNodeQuery(node).isAutoSize() && (w == -1 || h == -1)) {
                    Dimension fitToText = styleConfiguration.fitToText(node, nodeLabel, nodePlate);

                    // Keep the result coming from the size expression as default/minimum width
                    if (w == -1 || w == 0) {
                        width = Math.max(width, fitToText.width);
                    }

                    // Keep the result coming from the size expression as default/minimum height
                    if (h == -1 || h == 0) {
                        height = Math.max(height, fitToText.height);
                    }
                    d = new Dimension(width, height);
                    setSizes(d, d, nodePlate);
                }
            }

        }

        // we should not set the layout on a ViewNode2EditPart
        // or we'll get a classcast exception
        if (!AbstractDiagramNodeEditPartOperation.isBordered(editPart) && !AbstractDiagramNodeEditPartOperation.isBordered(editPart.getParent())) {
            ((GraphicalEditPart) editPart.getParent()).setLayoutConstraint(editPart, editPart.getFigure(), new Rectangle(x, y, width, height));
        } else if (editPart instanceof IBorderItemEditPart) {
            borderRefreshSizeAndLocation(x, y, width, height);
        }
    }

    private void setSizes(Dimension d, Dimension maxd, DefaultSizeNodeFigure nodePlate) {
        AbstractDiagramNodeEditPartOperation.setChildrenSize(editPart, d);

        StyledFigure styledFigure = DiagramElementEditPartOperation.getStyledFigure(editPart.getFigure());
        if (styledFigure != null) {
            styledFigure.setSize(d.width, d.height);
        }

        if (nodePlate != null) {
            nodePlate.setSize(d.width, d.height);
            nodePlate.setDefaultSize(d.width, d.height);
            nodePlate.getParent().setSize(d.width, d.height);
            nodePlate.setPreferredSize(d.width, d.height);
            nodePlate.setMinimumSize(d);
            nodePlate.setMaximumSize(maxd);

        }
        editPart.getFigure().setSize(d);
        editPart.getFigure().setMinimumSize(d);
        editPart.getFigure().setPreferredSize(d);
    }

    private void borderRefreshSizeAndLocation(final int x, final int y, final int width, final int height) {
        final IBorderItemEditPart borderPart = (IBorderItemEditPart) editPart;
        final IBorderItemLocator borderItemLocator = borderPart.getBorderItemLocator();
        if (borderItemLocator != null) {
            borderItemLocator.setConstraint(new Rectangle(x, y, width, height));
            if (new DDiagramElementQuery(node).isIndirectlyCollapsed()) {
                if (borderItemLocator instanceof DBorderItemLocator) {
                    ((DBorderItemLocator) borderItemLocator).setBorderItemOffset(IBorderItemOffsets.COLLAPSE_FILTER_OFFSET);
                }
            } else {
                if (borderItemLocator instanceof DBorderItemLocator) {
                    ((DBorderItemLocator) borderItemLocator).setBorderItemOffset(IBorderItemOffsets.DEFAULT_OFFSET);
                }
            }
            ((GraphicalEditPart) editPart.getParent()).setLayoutConstraint(editPart, editPart.getFigure(), borderItemLocator);
        }
    }

    private Dimension getWorkspaceImageFigureSize(final Dimension d) {
        var it = editPart.getChildren().iterator();
        while (it.hasNext()) {
            var child = it.next();
            if (child instanceof ShapeNodeEditPart shape && shape.getContentPane() instanceof IWorkspaceImageFigure) {
                shape.getContentPane().setSize(d);
                return shape.getContentPane().getSize();
            }
        }
        return d;
    }

    private int refreshWidth() {
        int width = LayoutUtils.DEFAULT_WIDTH;

        // Width
        width = 1;
        if (node.getWidth() != null) {
            width = node.getWidth().intValue();
        }
        if (width == 0) {
            // avoid nodes with width = 0.
            width = 1;
        }

        // Workspace image figure ? real image size ?
        if (width == -1 && node.getOwnedStyle() instanceof WorkspaceImage) {
            WorkspaceImage workspaceImage = (WorkspaceImage) node.getStyle();
            final String path = workspaceImage.getWorkspacePath();
            final Image image = WorkspaceImageFigure.getImageInstanceFromPath(path);
            width = image.getBounds().width;
        } else {
            width = width * LayoutUtils.SCALE;
        }

        final int tmpWidth = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Width())).intValue();
        DDiagramElementQuery query = new DDiagramElementQuery(node);
        if (tmpWidth > 0 && (new DNodeQuery(node).allowsHorizontalResize() || query.isCollapsed())) {
            width = tmpWidth;
        }

        // style
        if (node.getStyle() != null && ((NodeStyle) node.getStyle()).getLabelPosition().getValue() == LabelPosition.NODE) {
            // The label is in the graphical element. We must adapt the
            // size.
            final SiriusWrapLabel nodeLabel = editPart.getNodeLabel();
            if (nodeLabel != null) {
                final StyleConfiguration styleConfiguration = IStyleConfigurationRegistry.INSTANCE.getStyleConfiguration(node.getDiagramElementMapping(), node.getStyle());
                width = styleConfiguration.adaptViewNodeSizeWithLabel(node, nodeLabel, width);

            }

        }
        return width;
    }

    private int computeHeight(final int width) {
        int height = 0;

        // Height
        if (node.getHeight() != null) {
            height = node.getHeight().intValue() * LayoutUtils.SCALE;
        }
        DDiagramElementQuery query = new DDiagramElementQuery(node);
        if (height == 0 && (new DNodeQuery(node).allowsVerticalResize() || query.isCollapsed()) && !new DNodeQuery(node).isAutoSize()) {
            height = width;
        }

        final int tmpHeight = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Height())).intValue();
        if (tmpHeight > 0) {
            height = tmpHeight;
        }

        // workspace image ? keep ratio
        if (node.getStyle() instanceof WorkspaceImage) {
            height = getWorkspaceImageFigureSize(new Dimension(width, height)).height;
        }

        return height;

    }

    /**
     * Convenience method to retreive the value for the supplied value from the editpart's associated view element. Same
     * as calling <code> ViewUtil.getStructuralFeatureValue(getNotationView(),feature)</code> .
     * 
     * @param feature
     *            the feature
     * @return the value
     */
    private Object getStructuralFeatureValue(final EStructuralFeature feature) {
        if (editPart.getNotationView() != null) {
            return ViewUtil.getPropertyValue((View) editPart.getModel(), feature, feature.getEContainingClass());
        } else {
            return null;
        }
    }

    /**
     * Return the node plate of the edit part.
     * 
     * @return the node plate of the edit part.
     */
    protected DefaultSizeNodeFigure getNodePlate() {
        return AbstractDiagramNodeEditPartOperation.getNodePlate(editPart);
    }

}
