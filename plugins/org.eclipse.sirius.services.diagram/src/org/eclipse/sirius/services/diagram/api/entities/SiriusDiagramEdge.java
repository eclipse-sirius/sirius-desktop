/*******************************************************************************
 * Copyright (c) 2018 TypeFox and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.services.diagram.api.entities;

import org.eclipse.sirius.services.diagram.internal.converter.SiriusDiagramColorConverter;

/**
 * An edge.
 *
 * @author sbegaudeau
 */
public class SiriusDiagramEdge extends AbstractSiriusDiagramElement {

    /**
     * The type of the element.
     */
    private static final String TYPE = "edge:straight"; //$NON-NLS-1$

    /**
     * The identifier of the source.
     */
    private String sourceId;

    /**
     * The identifier of the target.
     */
    private String targetId;

    /**
     * The label.
     */
    private SiriusDiagramLabel label;

    /**
     * The color.
     */
    private SiriusDiagramRGBColor color;

    /**
     * The size.
     */
    private int size;

    /**
     * The style of the line.
     */
    private SiriusDiagramEdgeLineStyle lineStyle;

    /**
     * The style of the source arrow.
     */
    private SiriusDiagramEdgeArrowStyle sourceArrowStyle;

    /**
     * The style of the target arrow.
     */
    private SiriusDiagramEdgeArrowStyle targetArrowStyle;

    /**
     * The constructor.
     *
     * @param identifier
     *            The identifier
     * @param sourceId
     *            The identifier of the source
     * @param targetId
     *            The identifier of the target
     */
    public SiriusDiagramEdge(String identifier, String sourceId, String targetId) {
        super(identifier, TYPE);
        this.sourceId = sourceId;
        this.targetId = targetId;
    }

    /**
     * Return the sourceId.
     *
     * @return the sourceId
     */
    public String getSourceId() {
        return this.sourceId;
    }

    /**
     * Return the targetId.
     *
     * @return the targetId
     */
    public String getTargetId() {
        return this.targetId;
    }

    /**
     * Return the color.
     *
     * @return the color
     */
    public SiriusDiagramRGBColor getColor() {
        return this.color;
    }

    /**
     * Sets the color.
     *
     * @param color
     *            the color to set
     */
    private void setColor(SiriusDiagramRGBColor color) {
        this.color = color;
    }

    /**
     * Return the size.
     *
     * @return the size
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Sets the size.
     *
     * @param size
     *            the size to set
     */
    private void setSize(int size) {
        this.size = size;
    }

    /**
     * Return the lineStyle.
     *
     * @return the lineStyle
     */
    public SiriusDiagramEdgeLineStyle getLineStyle() {
        return this.lineStyle;
    }

    /**
     * Sets the lineStyle.
     *
     * @param lineStyle
     *            the lineStyle to set
     */
    private void setLineStyle(SiriusDiagramEdgeLineStyle lineStyle) {
        this.lineStyle = lineStyle;
    }

    /**
     * Return the sourceArrowStyle.
     *
     * @return the sourceArrowStyle
     */
    public SiriusDiagramEdgeArrowStyle getSourceArrowStyle() {
        return this.sourceArrowStyle;
    }

    /**
     * Sets the sourceArrowStyle.
     *
     * @param sourceArrowStyle
     *            the sourceArrowStyle to set
     */
    private void setSourceArrowStyle(SiriusDiagramEdgeArrowStyle sourceArrowStyle) {
        this.sourceArrowStyle = sourceArrowStyle;
    }

    /**
     * Return the targetArrowStyle.
     *
     * @return the targetArrowStyle
     */
    public SiriusDiagramEdgeArrowStyle getTargetArrowStyle() {
        return this.targetArrowStyle;
    }

    /**
     * Sets the targetArrowStyle.
     *
     * @param targetArrowStyle
     *            the targetArrowStyle to set
     */
    private void setTargetArrowStyle(SiriusDiagramEdgeArrowStyle targetArrowStyle) {
        this.targetArrowStyle = targetArrowStyle;
    }

    /**
     * Return the label.
     *
     * @return the label
     */
    public SiriusDiagramLabel getLabel() {
        return this.label;
    }

    /**
     * Sets the label.
     *
     * @param label
     *            the label to set
     */
    private void setLabel(SiriusDiagramLabel label) {
        this.label = label;
    }

    /**
     * Creates a new edge.
     *
     * @param identifier
     *            The identifier
     * @param sourceId
     *            The identifier of the source of the edge
     * @param targetId
     *            The identifier of the target of the edge
     * @return A builder used to create the new edge
     */
    public static Builder newEdge(String identifier, String sourceId, String targetId) {
        return new Builder(identifier, sourceId, targetId);
    }

    /**
     * The builder used to create the edges.
     *
     * @author sbegaudeau
     */
    // fields hidden by design
    @SuppressWarnings({ "checkstyle:HiddenField", "hiding" })
    public static final class Builder {

        /**
         * The identifier.
         */
        private String identifier;

        /**
         * The identifier of the source of the edge.
         */
        private String sourceId;

        /**
         * The identifier of the target of the edge.
         */
        private String targetId;

        /**
         * The color.
         */
        private SiriusDiagramRGBColor color = SiriusDiagramColorConverter.DEFAULT_COLOR;

        /**
         * The size.
         */
        private int size = 1;

        /**
         * The label.
         */
        private SiriusDiagramLabel label;

        /**
         * The style of the line.
         */
        private SiriusDiagramEdgeLineStyle lineStyle = SiriusDiagramEdgeLineStyle.SOLID;

        /**
         * The style of the source arrow.
         */
        private SiriusDiagramEdgeArrowStyle sourceArrowStyle = SiriusDiagramEdgeArrowStyle.NO_DECORATION;

        /**
         * The style of the target arrow.
         */
        private SiriusDiagramEdgeArrowStyle targetArrowStyle = SiriusDiagramEdgeArrowStyle.NO_DECORATION;

        /**
         * The constructor.
         *
         * @param identifier
         *            The identifier
         * @param sourceId
         *            The identifier of the source of the edge
         * @param targetId
         *            The identifier of the target of the edge
         */
        private Builder(String identifier, String sourceId, String targetId) {
            this.identifier = identifier;
            this.sourceId = sourceId;
            this.targetId = targetId;
        }

        /**
         * Sets the color.
         *
         * @param color
         *            The color
         * @return The builder
         */
        public Builder color(SiriusDiagramRGBColor color) {
            this.color = color;
            return this;
        }

        /**
         * Sets the size.
         *
         * @param size
         *            The size
         * @return The builder
         */
        public Builder size(int size) {
            this.size = size;
            return this;
        }

        /**
         * Sets the line style.
         *
         * @param lineStyle
         *            The line style
         * @return The builder
         */
        public Builder lineStyle(SiriusDiagramEdgeLineStyle lineStyle) {
            this.lineStyle = lineStyle;
            return this;
        }

        /**
         * Sets the source arrow style.
         * 
         * @param sourceArrowStyle
         *            The source arrow style
         * @return The builder
         */
        public Builder sourceArrowStyle(SiriusDiagramEdgeArrowStyle sourceArrowStyle) {
            this.sourceArrowStyle = sourceArrowStyle;
            return this;
        }

        /**
         * Sets the target arrow style.
         * 
         * @param targetArrowStyle
         *            The target arrow style
         * @return The builder
         */
        public Builder targetArrowStyle(SiriusDiagramEdgeArrowStyle targetArrowStyle) {
            this.targetArrowStyle = targetArrowStyle;
            return this;
        }

        /**
         * Sets the label.
         *
         * @param label
         *            The label
         * @return The builder
         */
        public Builder label(SiriusDiagramLabel label) {
            this.label = label;
            return this;
        }

        /**
         * Creates the edge.
         *
         * @return The edge created
         */
        public SiriusDiagramEdge build() {
            SiriusDiagramEdge edge = new SiriusDiagramEdge(this.identifier, this.sourceId, this.targetId);
            edge.setColor(this.color);
            edge.setSize(this.size);
            edge.setLineStyle(this.lineStyle);
            edge.setSourceArrowStyle(this.sourceArrowStyle);
            edge.setTargetArrowStyle(this.targetArrowStyle);
            edge.setLabel(this.label);
            return edge;
        }
    }
}
