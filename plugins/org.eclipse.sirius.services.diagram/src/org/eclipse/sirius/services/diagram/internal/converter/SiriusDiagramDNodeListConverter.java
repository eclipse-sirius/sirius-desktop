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
import java.util.stream.Stream;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.eclipse.sirius.services.diagram.api.entities.AbstractSiriusDiagramElement;
import org.eclipse.sirius.services.diagram.api.entities.SiriusDiagramLabel;
import org.eclipse.sirius.services.diagram.api.entities.SiriusDiagramListNode;
import org.eclipse.sirius.services.diagram.api.entities.SiriusDiagramRGBColor;
import org.eclipse.sirius.services.diagram.internal.SiriusDiagramPlugin;

/**
 * The DNodeList converter.
 *
 * @author sbegaudeau
 */
public class SiriusDiagramDNodeListConverter implements ISiriusDiagramElementConverter {

    /**
     * The suffix of the label.
     */
    private static final String LABEL_SUFFIX = "__label"; //$NON-NLS-1$

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
    public SiriusDiagramDNodeListConverter(DNodeList dNodeList) {
        this.dNodeList = dNodeList;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.services.diagram.internal.converter.ISiriusDiagramElementConverter#convert()
     */
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
                    .withChildren(this.getChildren())
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
        return EcoreUtil.getURI(this.dNodeList).toString();
    }

    /**
     * Returns the identifier of the semantic element.
     * 
     * @return The identifier of the semantic element
     */
    private String getSemanticElementIdentifier() {
        return EcoreUtil.getURI(this.dNodeList.getTarget()).toString();
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
     * Returns the border color.
     * 
     * @param style
     *            The style
     * @return The border color
     */
    private SiriusDiagramRGBColor getBorderColor(FlatContainerStyle style) {
        return SiriusDiagramColorConverter.convert(style.getBorderColor());
    }

    /**
     * Returns the border size.
     * 
     * @param style
     *            The style
     * @return The border size
     */
    private int getBorderSize(FlatContainerStyle style) {
        return Optional.ofNullable(style.getBorderSize()).orElse(Integer.valueOf(1)).intValue();
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
    private SiriusDiagramLabel getLabel(String identifier, FlatContainerStyle style) {
        SiriusDiagramRGBColor labelColor = SiriusDiagramColorConverter.convert(style.getLabelColor());
        return new SiriusDiagramLabel(identifier + LABEL_SUFFIX, this.dNodeList.getName(), labelColor);
    }

    /**
     * Returns the path of the image.
     * 
     * @return The path of the image
     */
    private String getImagePath() {
        // @formatter:off
        return SiriusDiagramPlugin.getPlugin().getImagePathProvider()
                    .flatMap(provider -> provider.getLabelProviderImagePath(this.dNodeList.getTarget()))
                    .orElse(null);
        // @formatter:on
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
            .flatMap(this::convertDiagramElement)
            .collect(Collectors.toList());
        // @formatter:on
    }

    /**
     * Converts the given DDiagramElement into a Sirius diagram element.
     *
     * @param dDiagramElement
     *            The DDiagramElement to convert
     * @return The converted diagram element
     */
    private Stream<AbstractSiriusDiagramElement> convertDiagramElement(DDiagramElement dDiagramElement) {
        ISiriusDiagramElementConverter converter = new SiriusDiagramElementSwitch().doSwitch(dDiagramElement);

        // @formatter:off
        return converter.convert()
                .map(Stream::of)
                .orElseGet(Stream::empty);
        // @formatter:on
    }

}
