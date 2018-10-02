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
package org.eclipse.sirius.services.diagram.api.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.eclipse.sirius.services.diagram.internal.converter.SiriusDiagramColorConverter;

/**
 * A node with a gradient-based style.
 *
 * @author sbegaudeau
 */
public class SiriusDiagramGradientNode extends AbstractSiriusDiagramNode {
    /**
     * The type of the node.
     */
    private static final String TYPE = "node:gradient"; //$NON-NLS-1$

    /**
     * The label.
     */
    private SiriusDiagramLabel label;

    /**
     * The background color.
     */
    private SiriusDiagramRGBColor backgroundColor;

    /**
     * The foreground color.
     */
    private SiriusDiagramRGBColor foregroundColor;

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
    public SiriusDiagramGradientNode(String identifier, String semanticElementIdentifier) {
        super(identifier, semanticElementIdentifier, TYPE);
    }

    /**
     * Return the backgroundColor.
     *
     * @return the backgroundColor
     */
    public SiriusDiagramRGBColor getBackgroundColor() {
        return this.backgroundColor;
    }

    /**
     * Sets the backgroundColor.
     *
     * @param backgroundColor
     *            the backgroundColor to set
     */
    private void setBackgroundColor(SiriusDiagramRGBColor backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * Return the foregroundColor.
     *
     * @return the foregroundColor
     */
    public SiriusDiagramRGBColor getForegroundColor() {
        return this.foregroundColor;
    }

    /**
     * Sets the foregroundColor.
     *
     * @param foregroundColor
     *            the foregroundColor to set
     */
    private void setForegroundColor(SiriusDiagramRGBColor foregroundColor) {
        this.foregroundColor = foregroundColor;
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
     * Creates a new gradient node.
     *
     * @param identifier
     *            The identifier
     * @param semanticElementIdentifier
     *            The identifier of the semantic element
     * @return A builder used to create the new gradient node
     */
    public static Builder newGradientNode(String identifier, String semanticElementIdentifier) {
        return new Builder(identifier, semanticElementIdentifier);
    }

    /**
     * The builder used to create the gradient nodes.
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
         * The background color.
         */
        private SiriusDiagramRGBColor backgroundColor = SiriusDiagramColorConverter.DEFAULT_COLOR;

        /**
         * The foreground color.
         */
        private SiriusDiagramRGBColor foregroundColor = SiriusDiagramColorConverter.DEFAULT_COLOR;

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
         * Sets the backgroundColor.
         *
         * @param backgroundColor
         *            The background color
         * @return The builder
         */
        public Builder backgroundColor(SiriusDiagramRGBColor backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        /**
         * Sets the foregroundColor.
         *
         * @param foregroundColor
         *            The foreground color
         * @return The builder
         */
        public Builder foregroundColor(SiriusDiagramRGBColor foregroundColor) {
            this.foregroundColor = foregroundColor;
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
         * Creates the gradient node.
         *
         * @return The gradient node created
         */
        public SiriusDiagramGradientNode build() {
            SiriusDiagramGradientNode gradientNode = new SiriusDiagramGradientNode(this.identifier, this.semanticElementIdentifier);
            gradientNode.setBackgroundColor(this.backgroundColor);
            gradientNode.setForegroundColor(this.foregroundColor);
            gradientNode.setBorderColor(this.borderColor);
            gradientNode.setBorderSize(this.borderSize);
            gradientNode.setImagePath(this.imagePath);
            gradientNode.setLabel(this.label);
            gradientNode.getPorts().addAll(this.ports);
            gradientNode.getChildren().addAll(this.children);
            return gradientNode;
        }
    }
}
