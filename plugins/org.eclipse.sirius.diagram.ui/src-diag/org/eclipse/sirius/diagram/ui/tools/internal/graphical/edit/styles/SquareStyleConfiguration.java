/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.graphical.edit.styles;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.internal.figures.BorderItemContainerFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.SlidableAnchor;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.ResizeKind;
import org.eclipse.sirius.diagram.ui.tools.api.figure.AirDefaultSizeNodeFigure;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SiriusWrapLabel;
import org.eclipse.sirius.diagram.ui.tools.api.figure.anchor.AnchorProvider;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.BorderItemLocatorProvider;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.DefaultBorderItemLocatorProvider;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.SimpleStyleConfiguration;

/**
 * A
 * {@link org.eclipse.sirius.diagram.tools.api.graphical.edit.styles.StyleConfiguration}
 * for {@link org.eclipse.sirius.viewpoint.BundledImage} square.
 * 
 * @author ymortier
 */
class SquareStyleConfiguration extends SimpleStyleConfiguration {

    /** The x1 percent. */
    private static final double X1 = 15.333;

    /** the x2 percent. */
    private static final double X2 = 8;

    /** The y1 percent. */
    private static final double Y1 = 6.993;

    /** the y2 percent. */
    private static final double Y2 = 20.979;

    @Override
    public BorderItemLocatorProvider getBorderItemLocatorProvider() {
        return DefaultBorderItemLocatorProvider.getInstance();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.SimpleStyleConfiguration#adaptNodeLabel(org.eclipse.sirius.viewpoint.DNode,
     *      org.eclipse.sirius.common.ui.tools.api.draw2d.ui.figures.SiriusWrapLabel)
     */
    @Override
    public void adaptNodeLabel(final DNode node, final SiriusWrapLabel nodeLabel) {
        if (nodeLabel.getParent() != null) {
            final Rectangle origin = nodeLabel.getParent().getBounds();
            final int x = (int) (origin.width * X1 / 100);
            final int y = (int) (origin.height * Y1 / 100);
            final int width = (int) (origin.width - (origin.width * X1 / 100 + origin.width * X2 / 100));
            final int height = (int) (origin.height - (origin.height * Y1 / 100 + origin.height * Y2 / 100));
            final Rectangle bounds = new Rectangle(x, y, width, height);
            nodeLabel.setBounds(bounds);
            // Don't set a Rectangle as constraint for BorderItemContainerFigure
            // as a IBorderItemLocator is expected for constraint consumer
            if (!(nodeLabel.getParent() instanceof BorderItemContainerFigure)) {
                nodeLabel.getParent().setConstraint(nodeLabel, bounds);
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.SimpleStyleConfiguration#adaptViewNodeSizeWithLabel(org.eclipse.sirius.viewpoint.DNode,
     *      org.eclipse.sirius.common.ui.tools.api.draw2d.ui.figures.SiriusWrapLabel,
     *      int)
     */
    @Override
    public int adaptViewNodeSizeWithLabel(final DNode viewNode, final SiriusWrapLabel nodeLabel, final int nodeWidth) {
        if (viewNode.getResizeKind() != ResizeKind.NONE_LITERAL) {
            // int labelWidth = nodeLabel.getFont() != null ?
            // nodeLabel.getPreferredSize().width : 0;
            // int tmpWidth = Math.max(labelWidth + 10, nodeWidth);
            // if (nodeWidth > 0) {
            // while (tmpWidth > nodeWidth * 2.5) {
            // tmpWidth = (int) (tmpWidth / 1.1);
            // }
            // }
            // return tmpWidth;
            return nodeWidth;
        }
        return nodeWidth;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.SimpleStyleConfiguration#getAnchorProvider()
     */
    @Override
    public AnchorProvider getAnchorProvider() {
        return SquareAnchorProvider.INSTANCE;
    }

    /**
     * Square anchor provider.
     * 
     * @author ymortier
     */
    private static final class SquareAnchorProvider implements AnchorProvider {
        /**
         * Singleton instance.
         */
        public static final SquareAnchorProvider INSTANCE = new SquareAnchorProvider();

        private SquareAnchorProvider() {
            // empty.
        }

        /**
         * @see org.eclipse.sirius.transversal.figure.anchor.AnchorProvider#createAnchor(org.eclipse.sirius.diagram.ui.tools.api.figure.common.ui.tool.api.graphical.figure.AirDefaultSizeNodeFigure,
         *      org.eclipse.draw2d.geometry.PrecisionPoint)
         */
        @Override
        public ConnectionAnchor createAnchor(final AirDefaultSizeNodeFigure figure, final PrecisionPoint p) {
            if (p == null) {
                return createDefaultAnchor(figure);
            }
            return new SquareStyleSlidableAnchor(figure, p);
        }

        /**
         * @see org.eclipse.sirius.transversal.figure.anchor.AnchorProvider#createDefaultAnchor(org.eclipse.sirius.diagram.ui.tools.api.figure.common.ui.tool.api.graphical.figure.AirDefaultSizeNodeFigure)
         */
        @Override
        public ConnectionAnchor createDefaultAnchor(final AirDefaultSizeNodeFigure figure) {
            return new SquareStyleSlidableAnchor(figure);
        }

    }

    /**
     * An anchor for the square style.
     * 
     * @author ymortier
     */
    private static class SquareStyleSlidableAnchor extends SlidableAnchor {
        /**
         * Creates an anchor for a square bundled image.
         * 
         * @param f
         *            the figure (the bundled image).
         * @param p
         *            the position of the anchor.
         */
        SquareStyleSlidableAnchor(final IFigure f, final PrecisionPoint p) {
            super(f, p);
        }

        /**
         * Creates an anchor for a square bundled image.
         * 
         * @param f
         *            the figure (the bundled image).
         */
        SquareStyleSlidableAnchor(final IFigure f) {
            super(f);
        }

        /**
         * Return the bounds of the image.
         * 
         * @see org.eclipse.gmf.runtime.gef.ui.figures.SlidableAnchor#getBox()
         */
        @Override
        protected Rectangle getBox() {
            final Rectangle origin = super.getBox();
            final int x = (int) (origin.width * X1 / 100) + origin.x;
            final int y = (int) (origin.height * Y1 / 100) + origin.y;
            final int width = (int) (origin.width - (origin.width * X1 / 100 + origin.width * X2 / 100));
            final int height = (int) (origin.height - (origin.height * Y1 / 100 + origin.height * Y2 / 100));
            final Rectangle box = new Rectangle(x, y, width, height);
            return box;
        }
    }

}
