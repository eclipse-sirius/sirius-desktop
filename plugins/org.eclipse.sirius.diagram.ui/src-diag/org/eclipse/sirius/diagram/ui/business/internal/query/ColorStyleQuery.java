/*******************************************************************************
 * Copyright (c) 2018 Obeo.
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
package org.eclipse.sirius.diagram.ui.business.internal.query;

import java.util.Objects;
import java.util.Optional;

import org.eclipse.sirius.diagram.BorderedStyle;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.eclipse.sirius.diagram.NodeStyle;
import org.eclipse.sirius.diagram.ShapeContainerStyle;
import org.eclipse.sirius.diagram.business.api.query.NodeStyleQuery;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.Style;

/**
 * Helper to get the color from Sirius style model.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 *
 */
public class ColorStyleQuery {

    private final Style style;

    /**
     * Constructor.
     * 
     * @param style
     *            the style to query.
     */
    public ColorStyleQuery(Style style) {
        this.style = Objects.requireNonNull(style);
    }

    /**
     * Return the color of the label.</br>
     * This method applies on style that can be considered as a line.
     * 
     * @return the color.
     */
    public Optional<RGBValues> getLabelColor() {
        RGBValues rgbValues = null;

        if (style instanceof BasicLabelStyle) {
            rgbValues = ((BasicLabelStyle) style).getLabelColor();
        } else if (style instanceof EdgeStyle) {
            EdgeStyle edgeStyle = (EdgeStyle) style;
            BasicLabelStyle labelStyle = edgeStyle.getBeginLabelStyle();
            if (labelStyle == null) {
                labelStyle = edgeStyle.getCenterLabelStyle();
                if (labelStyle == null) {
                    labelStyle = edgeStyle.getEndLabelStyle();
                }
            }
            if (labelStyle != null) {
                rgbValues = labelStyle.getLabelColor();
            }
        }

        return Optional.ofNullable(rgbValues);
    }

    /**
     * Return the color of the line.</br>
     * This method applies on style that can be considered as a line.
     * 
     * @return the color.
     */
    public Optional<RGBValues> getLineColor() {
        RGBValues rgbValues = null;
        if (style instanceof EdgeStyle) {
            rgbValues = ((EdgeStyle) style).getStrokeColor();
        } else if (style instanceof BorderedStyle) {
            rgbValues = ((BorderedStyle) style).getBorderColor();
        }

        return Optional.ofNullable(rgbValues);
    }

    /**
     * Return the color of the fill.</br>
     * This method applies on style that can be considered as a fill.
     * 
     * @return the color.
     */
    public Optional<RGBValues> getFillColor() {
        RGBValues rgbValues = null;
        if (style instanceof NodeStyle) {
            rgbValues = new NodeStyleQuery((NodeStyle) style).getBackgroundColor().get();
        } else if (style instanceof FlatContainerStyle) {
            rgbValues = ((FlatContainerStyle) style).getBackgroundColor();
        } else if (style instanceof ShapeContainerStyle) {
            rgbValues = ((ShapeContainerStyle) style).getBackgroundColor();
        }

        return Optional.ofNullable(rgbValues);
    }
}
