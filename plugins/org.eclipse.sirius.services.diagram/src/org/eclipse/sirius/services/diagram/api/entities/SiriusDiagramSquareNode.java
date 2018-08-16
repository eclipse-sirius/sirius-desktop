/*******************************************************************************
 * Copyright (c) 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.services.diagram.api.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.eclipse.sirius.services.diagram.internal.converter.SiriusDiagramColorConverter;

/**
 * A node with a square-based style.
 *
 * @author sbegaudeau
 */
public class SiriusDiagramSquareNode extends AbstractSiriusDiagramNode {

    /**
     * The type of the node.
     */
    private static final String TYPE = "node:square"; //$NON-NLS-1$

    /**
     * The label.
     */
    private SiriusDiagramLabel label;

    /**
     * The color.
     */
    private SiriusDiagramRGBColor color;

    /**
     * The border color.
     */
    private SiriusDiagramRGBColor borderColor;

    /**
     * The border size.
     */
    private int borderSize;

    /**
     * The path of the image.
     */
    private String imagePath;

    /**
     * The ports.
     */
    private List<AbstractSiriusDiagramElement> ports = new ArrayList<>();

    /**
     * The constructor.
     *
     * @param identifier
     *            The identifier
     * @param semanticElementIdentifier
     *            The identifier of the semantic element
     */
    public SiriusDiagramSquareNode(String identifier, String semanticElementIdentifier) {
        super(identifier, semanticElementIdentifier, TYPE);
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
     * Return the borderColor.
     *
     * @return the borderColor
     */
    public SiriusDiagramRGBColor getBorderColor() {
        return this.borderColor;
    }

    /**
     * Sets the borderColor.
     *
     * @param borderColor
     *            the borderColor to set
     */
    private void setBorderColor(SiriusDiagramRGBColor borderColor) {
        this.borderColor = borderColor;
    }

    /**
     * Return the borderSize.
     *
     * @return the borderSize
     */
    public int getBorderSize() {
        return this.borderSize;
    }

    /**
     * Sets the borderSize.
     *
     * @param borderSize
     *            the borderSize to set
     */
    private void setBorderSize(int borderSize) {
        this.borderSize = borderSize;
    }

    /**
     * Return the imagePath.
     *
     * @return the imagePath
     */
    public String getImagePath() {
        return this.imagePath;
    }

    /**
     * Sets the imagePath.
     *
     * @param imagePath
     *            the imagePath to set
     */
    private void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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
     * Return the ports.
     *
     * @return the ports
     */
    public List<AbstractSiriusDiagramElement> getPorts() {
        return this.ports;
    }

    /**
     * Creates a new square node.
     *
     * @param identifier
     *            The identifier
     * @param semanticElementIdentifier
     *            The identifier of the semantic element
     * @return A builder used to create the new square node
     */
    public static Builder newSquareNode(String identifier, String semanticElementIdentifier) {
        return new Builder(identifier, semanticElementIdentifier);
    }

    /**
     * The builder used to create the square nodes.
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
         * The identifier of the semantic element.
         */
        private String semanticElementIdentifier;

        /**
         * The color.
         */
        private SiriusDiagramRGBColor color = SiriusDiagramColorConverter.DEFAULT_COLOR;

        /**
         * The border color.
         */
        private SiriusDiagramRGBColor borderColor = SiriusDiagramColorConverter.DEFAULT_COLOR;

        /**
         * The border size.
         */
        private int borderSize = 1;

        /**
         * The label.
         */
        private SiriusDiagramLabel label;

        /**
         * The image path.
         */
        private String imagePath;

        /**
         * The ports.
         */
        private List<AbstractSiriusDiagramElement> ports;

        /**
         * The children.
         */
        private List<AbstractSiriusDiagramElement> children = new ArrayList<>();

        /**
         * The constructor.
         *
         * @param identifier
         *            The identifier
         * @param semanticElementIdentifier
         *            The identifier of the semantic element
         */
        private Builder(String identifier, String semanticElementIdentifier) {
            this.identifier = Objects.requireNonNull(identifier);
            this.semanticElementIdentifier = Objects.requireNonNull(semanticElementIdentifier);
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
         * Sets the border color.
         *
         * @param borderColor
         *            The border color
         * @return The builder
         */
        public Builder bordercolor(SiriusDiagramRGBColor borderColor) {
            this.borderColor = borderColor;
            return this;
        }

        /**
         * Sets the border size.
         *
         * @param borderSize
         *            The border size
         * @return The builder
         */
        public Builder borderSize(int borderSize) {
            this.borderSize = borderSize;
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
         * Sets the image path.
         *
         * @param imagePath
         *            The image path
         * @return The builder
         */
        public Builder imagePath(String imagePath) {
            this.imagePath = imagePath;
            return this;
        }

        /**
         * Sets the ports.
         *
         * @param ports
         *            The ports
         * @return The builder
         */
        public Builder ports(List<AbstractSiriusDiagramElement> ports) {
            this.ports = Objects.requireNonNull(ports);
            return this;
        }

        /**
         * Sets the children.
         *
         * @param children
         *            The children
         * @return The builder
         */
        public Builder withChildren(List<AbstractSiriusDiagramElement> children) {
            this.children = Objects.requireNonNull(children);
            return this;
        }

        /**
         * Creates the square node.
         *
         * @return The square node created
         */
        public SiriusDiagramSquareNode build() {
            SiriusDiagramSquareNode squareNode = new SiriusDiagramSquareNode(this.identifier, this.semanticElementIdentifier);
            squareNode.setColor(this.color);
            squareNode.setBorderColor(this.borderColor);
            squareNode.setBorderSize(this.borderSize);
            squareNode.setImagePath(this.imagePath);
            squareNode.setLabel(this.label);
            squareNode.getPorts().addAll(this.ports);
            squareNode.getChildren().addAll(this.children);
            return squareNode;
        }
    }

}
