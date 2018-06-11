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
package org.eclipse.sirius.services.diagram.internal.converter;

import java.util.Optional;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeArrows;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.LineStyle;
import org.eclipse.sirius.services.diagram.api.entities.AbstractSiriusDiagramElement;
import org.eclipse.sirius.services.diagram.api.entities.SiriusDiagramEdge;
import org.eclipse.sirius.services.diagram.api.entities.SiriusDiagramEdgeArrowStyle;
import org.eclipse.sirius.services.diagram.api.entities.SiriusDiagramEdgeLineStyle;
import org.eclipse.sirius.services.diagram.api.entities.SiriusDiagramLabel;
import org.eclipse.sirius.services.diagram.api.entities.SiriusDiagramRGBColor;
import org.eclipse.sirius.viewpoint.Style;

/**
 * The DEdge converter.
 *
 * @author sbegaudeau
 */
public class SiriusDiagramDEdgeConverter implements ISiriusDiagramElementConverter {

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
    public SiriusDiagramDEdgeConverter(DEdge dEdge) {
        this.dEdge = dEdge;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.services.diagram.internal.converter.ISiriusDiagramElementConverter#convert()
     */
    @Override
    public Optional<AbstractSiriusDiagramElement> convert() {
        Style style = this.dEdge.getStyle();
        if (style instanceof EdgeStyle) {
            EdgeStyle edgeStyle = (EdgeStyle) style;
            String identifier = EcoreUtil.getURI(this.dEdge).toString();

            SiriusDiagramRGBColor labelColor = SiriusDiagramColorConverter.convert(edgeStyle.getCenterLabelStyle().getLabelColor());
            String sourceId = EcoreUtil.getURI(this.dEdge.getSourceNode()).toString();
            String targetId = EcoreUtil.getURI(this.dEdge.getTargetNode()).toString();

            // @formatter:off
            SiriusDiagramRGBColor color = Optional.of(this.dEdge.getStyle())
                    .filter(EdgeStyle.class::isInstance)
                    .map(EdgeStyle.class::cast)
                    .map(EdgeStyle::getStrokeColor)
                    .map(SiriusDiagramColorConverter::convert)
                    .orElse(SiriusDiagramColorConverter.DEFAULT_COLOR);
            // @formatter:on

            SiriusDiagramEdgeLineStyle lineStyle = this.convertLineStyle(edgeStyle.getLineStyle());
            SiriusDiagramEdgeArrowStyle sourceArrowStyle = this.convertArrowStyle(edgeStyle.getSourceArrow());
            SiriusDiagramEdgeArrowStyle targetArrowStyle = this.convertArrowStyle(edgeStyle.getTargetArrow());

            SiriusDiagramEdge edge = new SiriusDiagramEdge(identifier, sourceId, targetId, color, lineStyle, sourceArrowStyle, targetArrowStyle);

            edge.getChildren().add(new SiriusDiagramLabel(identifier + "__label", this.dEdge.getName(), labelColor)); //$NON-NLS-1$

            return Optional.of(edge);
        }
        return Optional.empty();
    }

    /**
     * Converts the Sirius line style.
     *
     * @param lineStyle
     *            The line style
     * @return The converted Sirius line style
     */
    private SiriusDiagramEdgeLineStyle convertLineStyle(LineStyle lineStyle) {
        SiriusDiagramEdgeLineStyle diagramEdgeLineStyle = SiriusDiagramEdgeLineStyle.SOLID;
        switch (lineStyle) {
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
    private SiriusDiagramEdgeArrowStyle convertArrowStyle(EdgeArrows arrowStyle) {
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
