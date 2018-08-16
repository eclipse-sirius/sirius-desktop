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
import java.util.Optional;

import org.eclipse.sirius.services.diagram.internal.converter.SiriusDiagramColorConverter;

/**
 * A list-based node.
 *
 * @author sbegaudeau
 */
public final class SiriusDiagramListNode extends AbstractSiriusDiagramNode {

    /**
     * The type of the element.
     */
    private static final String TYPE = "node:listflatcontainer"; //$NON-NLS-1$

    /**
     * The background color.
     */
    private SiriusDiagramRGBColor backgroundColor;

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
     * The constructor.
     *
     * @param identifier
     *            The identifier
     * @param semanticElementIdentifier
     *            The identifier of the semantic element
     */
    private SiriusDiagramListNode(String identifier, String semanticElementIdentifier) {
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
     * Creates a new list node.
     *
     * @param identifier
     *            The identifier
     * @param semanticElementIdentifier
     *            The identifier of the semantic element
     * @return A builder used to create the new list node
     */
    public static Builder newListNode(String identifier, String semanticElementIdentifier) {
        return new Builder(identifier, semanticElementIdentifier);
    }

    /**
     * The builder used to create the list nodes.
     *
     * @author sbegaudeau
     */
    // fields hidden by design
    @SuppressWarnings({ "checkstyle:HiddenField", "hiding" })
    public static class Builder {

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
        public Builder(String identifier, String semanticElementIdentifier) {
            this.identifier = Objects.requireNonNull(identifier);
            this.semanticElementIdentifier = Objects.requireNonNull(semanticElementIdentifier);
        }

        /**
         * Sets the background color.
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
         * Creates the list node.
         *
         * @return The list node created
         */
        public SiriusDiagramListNode build() {
            SiriusDiagramListNode listNode = new SiriusDiagramListNode(this.identifier, this.semanticElementIdentifier);
            listNode.setBackgroundColor(this.backgroundColor);
            listNode.setBorderColor(this.borderColor);
            listNode.setBorderSize(this.borderSize);
            listNode.setImagePath(this.imagePath);

            Optional.ofNullable(this.label).ifPresent(listNode.getChildren()::add);
            listNode.getChildren().addAll(this.children);
            return listNode;
        }
    }

}
