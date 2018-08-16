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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.Square;
import org.eclipse.sirius.services.diagram.api.entities.AbstractSiriusDiagramElement;
import org.eclipse.sirius.services.diagram.api.entities.SiriusDiagramRGBColor;
import org.eclipse.sirius.services.diagram.api.entities.SiriusDiagramSquareNode;

/**
 * The DNode converter.
 *
 * @author sbegaudeau
 */
public class SiriusDiagramSquareNodeConverter extends AbstractSiriusDiagramNodeConverter {

    /**
     * The AbstractDNode.
     */
    private AbstractDNode abstractDNode;

    /**
     * The constructor.
     *
     * @param abstractDNode
     *            The AbstractDNode
     */
    public SiriusDiagramSquareNodeConverter(AbstractDNode abstractDNode) {
        this.abstractDNode = abstractDNode;
    }

    @Override
    protected AbstractDNode getDNode() {
        return this.abstractDNode;
    }

    @Override
    public Optional<AbstractSiriusDiagramElement> convert() {
        // @formatter:off
        Optional<Square> optionalStyle = Optional.of(this.abstractDNode.getStyle())
                .filter(Square.class::isInstance)
                .map(Square.class::cast);

        return optionalStyle.map(style -> {
            String identifier = this.getIdentifier();

            return SiriusDiagramSquareNode.newSquareNode(identifier, this.getSemanticElementIdentifier())
                    .color(this.getColor(style))
                    .bordercolor(this.getBorderColor(style))
                    .borderSize(this.getBorderSize(style))
                    .label(this.getLabel(identifier, style))
                    .imagePath(this.getImagePath())
                    .ports(this.getPorts())
                    .withChildren(this.getChildren())
                    .build();
        });
        // @formatter:on
    }

    /**
     * Returns the color.
     *
     * @param style
     *            The style
     * @return The color
     */
    private SiriusDiagramRGBColor getColor(Square style) {
        return SiriusDiagramColorConverter.convert(style.getColor());
    }

    /**
     * Returns the list of the children.
     *
     * @return The list of the children
     */
    private List<AbstractSiriusDiagramElement> getChildren() {
        // @formatter:off
        List<DDiagramElement> ownedElements = Optional.of(this.abstractDNode).filter(DNodeContainer.class::isInstance)
            .map(DNodeContainer.class::cast)
            .map(DNodeContainer::getOwnedDiagramElements)
            .orElseGet(BasicEList::new);

        return ownedElements.stream()
                .filter(DDiagramElement::isVisible)
                .map(new SiriusDiagramElementSwitch()::doSwitch)
                .map(ISiriusDiagramElementConverter::convert)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        // @formatter:on
    }

}
