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
     * The color.
     */
    private SiriusDiagramRGBColor color;

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
     * @param color
     *            The color
     * @param lineStyle
     *            The style of the line
     * @param sourceArrowStyle
     *            The style of the source arrow
     * @param targetArrowStyle
     *            The style of the target arrow
     */
    public SiriusDiagramEdge(String identifier, String sourceId, String targetId, SiriusDiagramRGBColor color, SiriusDiagramEdgeLineStyle lineStyle, SiriusDiagramEdgeArrowStyle sourceArrowStyle,
            SiriusDiagramEdgeArrowStyle targetArrowStyle) {
        super(identifier, TYPE);
        this.sourceId = sourceId;
        this.targetId = targetId;
        this.color = color;
        this.lineStyle = lineStyle;
        this.sourceArrowStyle = sourceArrowStyle;
        this.targetArrowStyle = targetArrowStyle;
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
     * Return the lineStyle.
     *
     * @return the lineStyle
     */
    public SiriusDiagramEdgeLineStyle getLineStyle() {
        return this.lineStyle;
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
     * Return the targetArrowStyle.
     *
     * @return the targetArrowStyle
     */
    public SiriusDiagramEdgeArrowStyle getTargetArrowStyle() {
        return this.targetArrowStyle;
    }

}
