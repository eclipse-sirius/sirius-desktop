/*******************************************************************************
 * Copyright (c) 2010, 2022 THALES GLOBAL SERVICES.
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

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutUtils;

/**
 * Queries relative to a DNode.
 * 
 * @author mporhel
 */
public class DNodeQuery {

    private static final Dimension DEFAULT_NODE_DIMENSION = new Dimension(2, 2);

    private final DNode node;

    /**
     * Constructor.
     * 
     * @param node
     *            the node to query.
     */
    public DNodeQuery(DNode node) {
        this.node = Objects.requireNonNull(node);
    }

    /**
     * Return the default draw2D dimension according to the specified DNode.
     * 
     * @return the default draw2D dimension according to the specified DNode.
     */
    public Dimension getDefaultDimension() {
        final Dimension result = DEFAULT_NODE_DIMENSION.getCopy();

        if (node.getStyle() instanceof WorkspaceImage) {
            WorkspaceImageQuery imageQuery = new WorkspaceImageQuery((WorkspaceImage) node.getStyle());
            if (imageQuery.doesImageExist()) {
                if (node.getWidth() == null || Integer.valueOf(node.getWidth()) == -1) {
                    // Use default image size
                    Dimension imageSize = imageQuery.getDefaultDimension();
                    result.setWidth(imageSize.width);
                    result.setHeight(imageSize.height);
                } else {
                    // width is already defined, adapt height thanks to image ratio
                    double newHeight = node.getWidth().intValue() / imageQuery.getRatio();

                    // Adapt to draw2D
                    result.setWidth(node.getWidth().intValue() * LayoutUtils.SCALE);
                    result.setHeight((int) (newHeight * LayoutUtils.SCALE));
                }
            }
        } else {
            if (node.getWidth() != null) {
                result.setWidth(node.getWidth().intValue());
            }
            if (node.getHeight() != null) {
                result.setHeight(node.getHeight().intValue());
            }

            // Adapt to draw2D
            result.setWidth(result.width * LayoutUtils.SCALE);
            result.setHeight(result.height * LayoutUtils.SCALE);
        }

        return result;
    }

}
