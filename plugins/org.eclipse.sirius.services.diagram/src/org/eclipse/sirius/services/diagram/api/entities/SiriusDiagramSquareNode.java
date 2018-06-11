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
     * The constructor.
     *
     * @param identifier
     *            The identifier
     * @param semanticElementIdentifier
     *            The identifier of the semantic element
     * @param backgroundColor
     *            The background color
     * @param borderColor
     *            The border color
     * @param borderSize
     *            The border size
     */
    public SiriusDiagramSquareNode(String identifier, String semanticElementIdentifier, SiriusDiagramRGBColor backgroundColor, SiriusDiagramRGBColor borderColor, int borderSize) {
        super(identifier, semanticElementIdentifier, TYPE);
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.borderSize = borderSize;
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
     * Return the borderColor.
     *
     * @return the borderColor
     */
    public SiriusDiagramRGBColor getBorderColor() {
        return this.borderColor;
    }

    /**
     * Return the borderSize.
     *
     * @return the borderSize
     */
    public int getBorderSize() {
        return this.borderSize;
    }

}
