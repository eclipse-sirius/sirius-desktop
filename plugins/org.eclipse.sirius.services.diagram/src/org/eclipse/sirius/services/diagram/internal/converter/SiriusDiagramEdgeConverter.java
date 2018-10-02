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
package org.eclipse.sirius.services.diagram.internal.converter;

import java.util.Optional;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeArrows;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.services.diagram.api.entities.AbstractSiriusDiagramElement;
import org.eclipse.sirius.services.diagram.api.entities.SiriusDiagramEdge;
import org.eclipse.sirius.services.diagram.api.entities.SiriusDiagramEdgeArrowStyle;
import org.eclipse.sirius.services.diagram.api.entities.SiriusDiagramEdgeLineStyle;
import org.eclipse.sirius.services.diagram.api.entities.SiriusDiagramLabel;
import org.eclipse.sirius.services.diagram.api.entities.SiriusDiagramRGBColor;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;

/**
 * The DEdge converter.
 *
 * @author sbegaudeau
 */
public class SiriusDiagramEdgeConverter implements ISiriusDiagramElementConverter {

    /**
     * The DEdge.
     */
    private DEdge dEdge;

    /**
     * The constructor.
     *
     * @param dEdge
     *            The DEdge
     */
    public SiriusDiagramEdgeConverter(DEdge dEdge) {
        this.dEdge = dEdge;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.services.diagram.internal.converter.ISiriusDiagramElementConverter#convert()
     */
    @Override
    public Optional<AbstractSiriusDiagramElement> convert() {
        // @formatter:off
        Optional<EdgeStyle> optionalStyle = Optional.of(this.dEdge.getStyle())
                .filter(EdgeStyle.class::isInstance)
                .map(EdgeStyle.class::cast);

        return optionalStyle.map(style -> {
            String identifier = this.getIdentifier();

            return SiriusDiagramEdge.newEdge(identifier, this.getSourceId(), this.getTargetId())
                    .color(this.getColor(style))
                    .size(this.getSize(style))
                    .lineStyle(this.getLineStyle(style))
                    .sourceArrowStyle(this.getArrowStyle(style.getSourceArrow()))
                    .targetArrowStyle(this.getArrowStyle(style.getTargetArrow()))
                    .label(this.getLabel(identifier, style.getCenterLabelStyle()))
                    .build();
        });
        // @formatter:on
    }

    /**
     * Returns the identifier.
     *
     * @return The identifier
     */
    private String getIdentifier() {
        return EcoreUtil.getURI(this.dEdge).toString();
    }

    /**
     * Returns the identifier of the source of the edge.
     *
     * @return The identifier of the source of the edge
     */
    private String getSourceId() {
        return EcoreUtil.getURI(this.dEdge.getSourceNode()).toString();
    }

    /**
     * Returns the identifier of the target of the edge.
     *
     * @return The identifier of the target of the edge
     */
    private String getTargetId() {
        return EcoreUtil.getURI(this.dEdge.getTargetNode()).toString();
    }

    /**
     * Returns the color of the edge.
     *
     * @param style
     *            The style
     * @return The color of the edge
     */
    private SiriusDiagramRGBColor getColor(EdgeStyle style) {
        return SiriusDiagramColorConverter.convert(style.getStrokeColor());
    }

    /**
     * Returns the size of the edge.
     *
     * @param style
     *            The style
     * @return The size of the edge
     */
    private int getSize(EdgeStyle style) {
        return Optional.ofNullable(style.getSize()).orElse(Integer.valueOf(1)).intValue();
    }

    /**
     * Returns the label.
     *
     * @param identifier
     *            The identifier
     * @param style
     *            The style
     * @return The label
     */
    private SiriusDiagramLabel getLabel(String identifier, BasicLabelStyle style) {
        SiriusDiagramRGBColor labelColor = SiriusDiagramColorConverter.convert(style.getLabelColor());
        return new SiriusDiagramLabel(identifier + SiriusDiagramLabel.LABEL_SUFFIX, this.dEdge.getName(), labelColor);
    }

    /**
     * Converts the Sirius line style.
     *
     * @param lineStyle
     *            The line style
     * @return The converted Sirius line style
     */
    private SiriusDiagramEdgeLineStyle getLineStyle(EdgeStyle style) {
        SiriusDiagramEdgeLineStyle diagramEdgeLineStyle = SiriusDiagramEdgeLineStyle.SOLID;
        switch (style.getLineStyle()) {
        case SOLID_LITERAL:
            diagramEdgeLineStyle = SiriusDiagramEdgeLineStyle.SOLID;
            break;
        case DASH_DOT_LITERAL:
            diagramEdgeLineStyle = SiriusDiagramEdgeLineStyle.DASH_DOT;
            break;
        case DASH_LITERAL:
            diagramEdgeLineStyle = SiriusDiagramEdgeLineStyle.DASH;
            break;
        case DOT_LITERAL:
            diagramEdgeLineStyle = SiriusDiagramEdgeLineStyle.DOT;
            break;
        default:
            break;
        }
        return diagramEdgeLineStyle;
    }

    /**
     * Converts the style of the arrow.
     *
     * @param arrowStyle
     *            The style of the arrow
     * @return The converted style of the arrow
     */
    private SiriusDiagramEdgeArrowStyle getArrowStyle(EdgeArrows arrowStyle) {
        SiriusDiagramEdgeArrowStyle diagramEdgeArrowStyle = SiriusDiagramEdgeArrowStyle.NO_DECORATION;
        switch (arrowStyle) {
        case DIAMOND_LITERAL:
            diagramEdgeArrowStyle = SiriusDiagramEdgeArrowStyle.DIAMOND;
            break;
        case FILL_DIAMOND_LITERAL:
            diagramEdgeArrowStyle = SiriusDiagramEdgeArrowStyle.FILL_DIAMOND;
            break;
        case INPUT_ARROW_LITERAL:
            diagramEdgeArrowStyle = SiriusDiagramEdgeArrowStyle.INPUT_ARROW;
            break;
        case INPUT_ARROW_WITH_DIAMOND_LITERAL:
            diagramEdgeArrowStyle = SiriusDiagramEdgeArrowStyle.INPUT_ARROW_WITH_DIAMOND;
            break;
        case INPUT_ARROW_WITH_FILL_DIAMOND_LITERAL:
            diagramEdgeArrowStyle = SiriusDiagramEdgeArrowStyle.INPUT_ARROW_WITH_FILL_DIAMOND;
            break;
        case INPUT_CLOSED_ARROW_LITERAL:
            diagramEdgeArrowStyle = SiriusDiagramEdgeArrowStyle.INPUT_CLOSED_ARROW;
            break;
        case INPUT_FILL_CLOSED_ARROW_LITERAL:
            diagramEdgeArrowStyle = SiriusDiagramEdgeArrowStyle.INPUT_FILL_CLOSED_ARROW;
            break;
        case NO_DECORATION_LITERAL:
            diagramEdgeArrowStyle = SiriusDiagramEdgeArrowStyle.NO_DECORATION;
            break;
        case OUTPUT_ARROW_LITERAL:
            diagramEdgeArrowStyle = SiriusDiagramEdgeArrowStyle.OUTPUT_ARROW;
            break;
        case OUTPUT_CLOSED_ARROW_LITERAL:
            diagramEdgeArrowStyle = SiriusDiagramEdgeArrowStyle.OUTPUT_CLOSED_ARROW;
            break;
        case OUTPUT_FILL_CLOSED_ARROW_LITERAL:
            diagramEdgeArrowStyle = SiriusDiagramEdgeArrowStyle.OUTPUT_FILL_CLOSED_ARROW;
            break;
        default:
            break;
        }
        return diagramEdgeArrowStyle;
    }

}
