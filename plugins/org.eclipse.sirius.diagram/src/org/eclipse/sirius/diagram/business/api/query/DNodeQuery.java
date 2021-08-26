/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.api.query;

import java.util.Collections;
import java.util.List;

import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.LabelPosition;
import org.eclipse.sirius.diagram.NodeStyle;
import org.eclipse.sirius.diagram.ResizeKind;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.style.NodeStyleDescription;
import org.eclipse.sirius.diagram.description.style.Side;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;

/**
 * Queries for DNodes.
 * 
 * @author pcdavid
 */
public class DNodeQuery {
    private final DNode node;

    /**
     * Constructor.
     * 
     * @param node
     *            the DNode to query.
     */
    public DNodeQuery(DNode node) {
        this.node = node;
    }

    /**
     * Tests whether or not the node allows the user to change its vertical
     * size, according to the {@link ResizeKind} specified in the VSM.
     * 
     * @return <code>true</code> if the node allow the user to change its
     *         vertical size.
     */
    public boolean allowsVerticalResize() {
        ResizeKind allowed = node.getResizeKind();
        return allowed == ResizeKind.NSEW_LITERAL || allowed == ResizeKind.NORTH_SOUTH_LITERAL;
    }

    /**
     * Tests whether or not the node allows the user to change its horizontal
     * size, according to the {@link ResizeKind} specified in the VSM.
     * 
     * @return <code>true</code> if the node allow the user to change its
     *         horizontal size.
     */
    public boolean allowsHorizontalResize() {
        ResizeKind allowed = node.getResizeKind();
        return allowed == ResizeKind.NSEW_LITERAL || allowed == ResizeKind.EAST_WEST_LITERAL;
    }

    /**
     * Indicates if the label of the node is on border.
     * 
     * @return true if the label of this node is on border, false otherwise
     */
    public boolean hasLabelOnBorder() {
        NodeMapping actualMapping = node.getActualMapping();
        if (actualMapping != null) {
            NodeStyleDescription style = actualMapping.getStyle();
            if (style != null) {
                LabelPosition labelPosition = style.getLabelPosition();
                return labelPosition.equals(LabelPosition.BORDER_LITERAL);
            }
        }
        return false;
    }

    /**
     * Indicates if the node as a non-empty name definition, i.e. that its label
     * expression don't always return an empty string.
     * 
     * @return true if the node as a non-null and non-empty name, false
     *         otherwise
     */
    public boolean hasNonEmptyNameDefinition() {
        boolean hasEmptyNameDefinition = true;
        NodeMapping actualMapping = node.getActualMapping();
        if (actualMapping != null) {
            NodeStyleDescription style = actualMapping.getStyle();
            if (style != null) {
                String labelExpression = style.getLabelExpression();
                hasEmptyNameDefinition = labelExpression.trim().length() != 0;
            }
        }
        return hasEmptyNameDefinition;
    }

    /**
     * Returns the specified forbidden sides for the current DNode. This feature
     * makes sense for border nodes only.
     * 
     * @return the list of forbidden {@link Side}
     */
    public List<Side> getForbiddenSide() {
        NodeStyle nodeStyle = node.getOwnedStyle();
        StyleDescription styleDescription = nodeStyle.getDescription();
        if (styleDescription instanceof NodeStyleDescription) {
            return ((NodeStyleDescription) styleDescription).getForbiddenSides();
        }
        return Collections.emptyList();
    }

}
