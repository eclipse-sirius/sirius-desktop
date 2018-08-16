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

import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.eclipse.sirius.services.diagram.api.entities.AbstractSiriusDiagramElement;
import org.eclipse.sirius.services.diagram.api.entities.SiriusDiagramListNode;
import org.eclipse.sirius.services.diagram.api.entities.SiriusDiagramRGBColor;

/**
 * The DNodeList converter.
 *
 * @author sbegaudeau
 */
public class SiriusDiagramListNodeConverter extends AbstractSiriusDiagramNodeConverter {

    /**
     * The DNodeList.
     */
    private DNodeList dNodeList;

    /**
     * The constructor.
     *
     * @param dNodeList
     *            The DNodeList
     */
    public SiriusDiagramListNodeConverter(DNodeList dNodeList) {
        this.dNodeList = dNodeList;
    }

    @Override
    protected AbstractDNode getDNode() {
        return this.dNodeList;
    }

    @Override
    public Optional<AbstractSiriusDiagramElement> convert() {
        // @formatter:off
        Optional<FlatContainerStyle> optionalStyle = Optional.of(this.dNodeList.getStyle())
                .filter(FlatContainerStyle.class::isInstance)
                .map(FlatContainerStyle.class::cast);

        return optionalStyle.map(style -> {
            String identifier = this.getIdentifier();

            return SiriusDiagramListNode.newListNode(identifier, this.getSemanticElementIdentifier())
                    .backgroundColor(this.getBackgroundColor(style))
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
     * Returns the background color.
     *
     * @param style
     *            The style
     * @return The background color
     */
    private SiriusDiagramRGBColor getBackgroundColor(FlatContainerStyle style) {
        return SiriusDiagramColorConverter.convert(style.getBackgroundColor());
    }

    /**
     * Returns the list of the children.
     *
     * @return The list of the children
     */
    private List<AbstractSiriusDiagramElement> getChildren() {
        // @formatter:off
        return this.dNodeList.getOwnedElements().stream()
                .filter(DDiagramElement::isVisible)
                .map(new SiriusDiagramElementSwitch()::doSwitch)
                .map(ISiriusDiagramElementConverter::convert)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        // @formatter:on
    }

}
