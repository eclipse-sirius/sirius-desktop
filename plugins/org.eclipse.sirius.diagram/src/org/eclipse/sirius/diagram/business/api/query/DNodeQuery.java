/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES.
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

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.LabelPosition;
import org.eclipse.sirius.diagram.NodeStyle;
import org.eclipse.sirius.diagram.ResizeKind;
import org.eclipse.sirius.diagram.Square;
import org.eclipse.sirius.diagram.business.api.diagramtype.DiagramTypeDescriptorRegistry;
import org.eclipse.sirius.diagram.business.api.diagramtype.IDiagramDescriptionProvider;
import org.eclipse.sirius.diagram.business.api.diagramtype.IDiagramTypeDescriptor;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.style.NodeStyleDescription;
import org.eclipse.sirius.diagram.description.style.Side;
import org.eclipse.sirius.diagram.description.style.SquareDescription;
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
     * Tests whether or not the node allows the user to change its vertical size, according to the {@link ResizeKind}
     * specified in the VSM.
     * 
     * @return <code>true</code> if the node allow the user to change its vertical size.
     */
    public boolean allowsVerticalResize() {
        ResizeKind allowed = node.getResizeKind();
        return allowed == ResizeKind.NSEW_LITERAL || allowed == ResizeKind.NORTH_SOUTH_LITERAL;
    }

    /**
     * Tests whether or not the node allows the user to change its horizontal size, according to the {@link ResizeKind}
     * specified in the VSM.
     * 
     * @return <code>true</code> if the node allow the user to change its horizontal size.
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
     * Indicates if the node as a non-empty name definition, i.e. that its label expression don't always return an empty
     * string.
     * 
     * @return true if the node as a non-null and non-empty name, false otherwise
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
     * Returns the specified forbidden sides for the current DNode. This feature makes sense for border nodes only.
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

    /**
     * Return {@code true} if the specified DNode is in "auto size" mode, i.e one of width or height is equals to -1
     * (computed from Style description width/height or size expression).
     * 
     * <br/>
     * See org.eclipse.sirius.diagram.ui.business.internal.query.DNodeQuery.supportNodeStyleAutoSize(NodeStyle,
     * NodeStyleDescription) for ability to support during style computation or when the style has not been created yet.
     * 
     * <br/>
     * See computations in callers of
     * org.eclipse.sirius.diagram.business.internal.metamodel.helper.StyleHelper.supportNodeStyleAutoSize(DNode,
     * NodeStyleDescription)
     * 
     * @return {@code true} if the specified DNode is in "auto size" mode, i.e one of width or height is equals to -1
     *         {@code false} otherwise.
     * 
     */
    public boolean isAutoSize() {
        NodeStyle ownedStyle = node.getOwnedStyle();

        boolean supportsAutoSize = false;
        if (ownedStyle != null && ownedStyle.getDescription() instanceof NodeStyleDescription nodeStyleDesc) {
            supportsAutoSize = supportNodeStyleAutoSize(ownedStyle, nodeStyleDesc);
        }
        return supportsAutoSize && node.getStyle() instanceof Square square && (square.getWidth() == -1 && square.getHeight() == -1);
    }

    /**
     * Return {@code true} if the specified DNode can support "auto size" mode defined from its style, i.e one of width
     * or height is equals to -1 (computed from Style description width/height or size expression).
     * 
     * <br/>
     * See org.eclipse.sirius.diagram.ui.business.internal.query.DNodeQuery.isAutoSize() for auto-size status when
     * DNode, style and size have been computed and auto-size state needs to be used.
     * 
     * <br/>
     * See computations in callers of
     * org.eclipse.sirius.diagram.business.internal.metamodel.helper.StyleHelper.supportNodeStyleAutoSize(DNode,
     * NodeStyleDescription)
     * 
     * @return {@code true} if the specified DNode can be in "auto size" mode, i.e one of width or height is equals to
     *         -1 {@code false} otherwise.
     */
    public boolean supportNodeStyleAutoSize(NodeStyle style, NodeStyleDescription description) {
        // Cannot use DNodeQuery : style might not be attached to the node yet.
        boolean supportAutoSizeStyle = true;
        // If diagram is not null, we search for a possible
        // DiagramDescriptionProvider handling this type of diagram
        DDiagram parentDiagram = node.getParentDiagram();
        if (parentDiagram != null) {
            for (final IDiagramTypeDescriptor diagramTypeDescriptor : DiagramTypeDescriptorRegistry.getInstance().getAllDiagramTypeDescriptors()) {
                if (diagramTypeDescriptor.getDiagramDescriptionProvider().handles(parentDiagram.getDescription().eClass().getEPackage())) {
                    // This DiagramDescriptionProvider may forbid copy/paste format.
                    final IDiagramDescriptionProvider provider = diagramTypeDescriptor.getDiagramDescriptionProvider();
                    supportAutoSizeStyle = provider.allowsAutoSizeNodeStyle(node);
                    break;
                }
            }
        }
        if (style != null) {
            supportAutoSizeStyle = supportAutoSizeStyle && style instanceof Square && style.getLabelPosition().getValue() == LabelPosition.NODE;
        } else if (description != null) {
            supportAutoSizeStyle = supportAutoSizeStyle && description instanceof SquareDescription && description.getLabelPosition().getValue() == LabelPosition.NODE;
        }
        return supportAutoSizeStyle;
    }

}
