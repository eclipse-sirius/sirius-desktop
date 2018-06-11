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
     * The constructor.
     *
     * @param identifier
     *            The identifier
     * @param semanticElementIdentifier
     *            The identifier of the semantic element
     * @param imagePath
     *            The path of the image
     */
    public SiriusDiagramImageNode(String identifier, String semanticElementIdentifier, String imagePath) {
        super(identifier, semanticElementIdentifier, TYPE);
        this.imagePath = imagePath;
    }

    /**
     * Return the imagePath.
     *
     * @return the imagePath
     */
    public String getImagePath() {
        return this.imagePath;
    }

}
