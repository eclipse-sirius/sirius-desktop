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

/**
 * A node with an image-based style.
 *
 * @author sbegaudeau
 */
public class SiriusDiagramImageNode extends AbstractSiriusDiagramNode {
    /**
     * The type of the node.
     */
    private static final String TYPE = "node:image"; //$NON-NLS-1$

    /**
     * The path of the image.
     */
    private String imagePath;

    /**
     * The label.
     */
    private SiriusDiagramLabel label;

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
    public SiriusDiagramImageNode(String identifier, String semanticElementIdentifier) {
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
     * Return the ports.
     *
     * @return the ports
     */
    public List<AbstractSiriusDiagramElement> getPorts() {
        return this.ports;
    }

    /**
     * Creates a new image node.
     *
     * @param identifier
     *            The identifier
     * @param semanticElementIdentifier
     *            The identifier of the semantic element
     * @return A builder used to create the new image node
     */
    public static Builder newImageNode(String identifier, String semanticElementIdentifier) {
        return new Builder(identifier, semanticElementIdentifier);
    }

    /**
     * The builder used to create the image nodes.
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
         * The path of the image.
         */
        private String imagePath;

        /**
         * The ports.
         */
        private List<AbstractSiriusDiagramElement> ports;

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
         * Creates the list node.
         *
         * @return The list node created
         */
        public SiriusDiagramImageNode build() {
            SiriusDiagramImageNode imageNode = new SiriusDiagramImageNode(this.identifier, this.semanticElementIdentifier);
            imageNode.setImagePath(this.imagePath);
            imageNode.setLabel(this.label);
            imageNode.getPorts().addAll(this.ports);
            return imageNode;
        }
    }
}
