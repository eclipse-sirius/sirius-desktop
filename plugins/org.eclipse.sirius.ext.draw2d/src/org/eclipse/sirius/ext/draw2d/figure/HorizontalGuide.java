/*******************************************************************************
 * Copyright (c) 2010, 2018 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ext.draw2d.figure;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

/**
 * A horizontal line across the whole diagram, useful as feedback for sequence
 * diagrams where the vertical location of elements relative to each others is
 * significant.
 * 
 * @author pcdavid
 */
public class HorizontalGuide extends Figure {
    private final int y;

    /**
     * Creates a new horizontal guide.
     * 
     * @param color
     *            the color of the line.
     * @param y
     *            the vertical location of the guide.
     */
    public HorizontalGuide(Color color, int y) {
        this.y = y;
        setForegroundColor(color);
        super.setOpaque(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paintFigure(Graphics graphics) {
        Rectangle area = getClientArea();
        graphics.drawLine(area.x, y, area.x + area.width, y);
    }
}
