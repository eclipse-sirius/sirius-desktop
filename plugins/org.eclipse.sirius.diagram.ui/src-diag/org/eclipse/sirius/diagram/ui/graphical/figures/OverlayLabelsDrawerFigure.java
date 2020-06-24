/*******************************************************************************
 * Copyright (c) 2020 Obeo.
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
package org.eclipse.sirius.diagram.ui.graphical.figures;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.TreeSearch;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.ui.tools.internal.graphical.edit.part.DDiagramRootEditPart;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;

/**
 * A "virtual" figure that should be added to the {@link DDiagramRootEditPart#OVERLAY_LAYER} and which paints all the
 * overlay labels (instance of {@link OverlayLabel}) on top of the rest of the diagram to make sure they are always
 * readable.<BR>
 * This figure is currently only used by sequence diagrams.
 * 
 * @author pcdavid
 */
public final class OverlayLabelsDrawerFigure extends Figure {

    private final IFigure root;

    private final IGraphicalEditPart part;

    /**
     * Constructor.
     * 
     * @param root
     *            the root figure of the diagram.
     * @param part
     *            the diagram edit part. This is needed to obtain zoom/scroll information to adjust paint coordinates
     *            correctly.
     */
    public OverlayLabelsDrawerFigure(IFigure root, IGraphicalEditPart part) {
        this.root = Objects.requireNonNull(root);
        this.part = Objects.requireNonNull(part);
    }

    @Override
    public Rectangle getBounds() {
        return this.root.getBounds();
    }

    @Override
    public IFigure findFigureAt(int x, int y, TreeSearch search) {
        return null;
    }

    @Override
    public void paint(Graphics graphics) {
        try {
            OverlayLabel.PAINT_ENABLED.set(true);
            paintLabels(graphics, root);
        } finally {
            OverlayLabel.PAINT_ENABLED.set(false);
        }
    }

    private void paintLabels(Graphics graphics, IFigure figure) {
        if (figure instanceof OverlayLabel) {
            paintOverlayLabel(graphics, (OverlayLabel) figure);
        } else {
            List<Object> children = new ArrayList<Object>(figure.getChildren());
            for (Object child : children) {
                paintLabels(graphics, (IFigure) child);
            }
        }
    }

    private void paintOverlayLabel(Graphics graphics, OverlayLabel label) {
        Rectangle bounds = label.getBounds();

        Point delta = bounds.getTopLeft().getCopy();
        label.translateToAbsolute(delta);
        GraphicalHelper.screen2logical(delta, part);
        delta.translate(bounds.getTopLeft().getNegated());

        graphics.translate(delta.x, delta.y);
        label.paint(graphics);
        graphics.translate(-delta.x, -delta.y);
    }
}
