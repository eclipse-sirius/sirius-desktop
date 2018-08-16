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

import java.util.Objects;

/**
 * An element of a list-based node.
 *
 * @author sbegaudeau
 */
public class SiriusDiagramListElementNode extends AbstractSiriusDiagramNode {
    /**
     * The type of the element.
     */
    private static final String TYPE = "node:listelement"; //$NON-NLS-1$

    /**
     * The label.
     */
    private SiriusDiagramLabel label;

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
     *            The semantic element identifier
     */
    public SiriusDiagramListElementNode(String identifier, String semanticElementIdentifier) {
        super(identifier, semanticElementIdentifier, TYPE);
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
     * Creates a new list element node.
     *
     * @param identifier
     *            The identifier
     * @param semanticElementIdentifier
     *            The identifier of the semantic element
     * @return A builder used to create the new list element node
     */
    public static Builder newListElementNode(String identifier, String semanticElementIdentifier) {
        return new Builder(identifier, semanticElementIdentifier);
    }

    /**
     * The builder used to create the list element nodes.
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
         * The label.
         */
        private SiriusDiagramLabel label;

        /**
         * The image path.
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
        private Builder(String identifier, String semanticElementIdentifier) {
            this.identifier = Objects.requireNonNull(identifier);
            this.semanticElementIdentifier = Objects.requireNonNull(semanticElementIdentifier);
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
         * Creates the list element node.
         *
         * @return The list element node created
         */
        public SiriusDiagramListElementNode build() {
            SiriusDiagramListElementNode listNode = new SiriusDiagramListElementNode(this.identifier, this.semanticElementIdentifier);
            listNode.setImagePath(this.imagePath);
            listNode.setLabel(this.label);
            return listNode;
        }
    }
}
