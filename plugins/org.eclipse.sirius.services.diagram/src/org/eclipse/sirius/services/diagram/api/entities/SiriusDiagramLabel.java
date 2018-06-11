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

/**
 * The label of an element.
 *
 * @author sbegaudeau
 */
public class SiriusDiagramLabel extends AbstractSiriusDiagramElement {

    /**
     * The type of the element.
     */
    private static final String TYPE = "label"; //$NON-NLS-1$

    /**
     * The text of the label.
     */
    private String text;

    /**
     * The color of the label.
     */
    private SiriusDiagramRGBColor color;

    /**
     * The constructor.
     *
     * @param identifier
     *            The identifier
     * @param text
     *            The text
     * @param color
     *            The color
     */
    public SiriusDiagramLabel(String identifier, String text, SiriusDiagramRGBColor color) {
        super(identifier, TYPE);
        this.text = text;
        this.color = color;
    }

    /**
     * Return the text.
     *
     * @return the text
     */
    public String getText() {
        return this.text;
    }

    /**
     * Return the color.
     *
     * @return the color
     */
    public SiriusDiagramRGBColor getColor() {
        return this.color;
    }

}
