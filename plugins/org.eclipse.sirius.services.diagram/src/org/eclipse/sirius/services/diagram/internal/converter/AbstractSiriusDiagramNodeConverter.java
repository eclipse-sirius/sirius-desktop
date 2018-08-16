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

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.BorderedStyle;
import org.eclipse.sirius.services.diagram.api.entities.AbstractSiriusDiagramElement;
import org.eclipse.sirius.services.diagram.api.entities.SiriusDiagramLabel;
import org.eclipse.sirius.services.diagram.api.entities.SiriusDiagramRGBColor;
import org.eclipse.sirius.services.diagram.internal.SiriusDiagramPlugin;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;

/**
 * Common suprtclass of the AbstractDNode converters.
 *
 * @author sbegaudeau
 */
public abstract class AbstractSiriusDiagramNodeConverter implements ISiriusDiagramElementConverter {

    /**
     * Returns the AbstractDNode to convert.
     *
     * @return The AbstractDNode to convert
     */
    protected abstract AbstractDNode getDNode();

    /**
     * Returns the identifier.
     *
     * @return The identifier
     */
    protected String getIdentifier() {
        return EcoreUtil.getURI(this.getDNode()).toString();
    }

    /**
     * Returns the identifier of the semantic element.
     *
     * @return The identifier of the semantic element
     */
    protected String getSemanticElementIdentifier() {
        return EcoreUtil.getURI(this.getDNode().getTarget()).toString();
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
    protected SiriusDiagramLabel getLabel(String identifier, BasicLabelStyle style) {
        SiriusDiagramRGBColor labelColor = SiriusDiagramColorConverter.convert(style.getLabelColor());
        return new SiriusDiagramLabel(identifier + SiriusDiagramLabel.LABEL_SUFFIX, this.getDNode().getName(), labelColor);
    }

    /**
     * Returns the border color.
     *
     * @param style
     *            The style
     * @return The border color
     */
    protected SiriusDiagramRGBColor getBorderColor(BorderedStyle style) {
        return SiriusDiagramColorConverter.convert(style.getBorderColor());
    }

    /**
     * Returns the border size.
     *
     * @param style
     *            The style
     * @return The border size
     */
    protected int getBorderSize(BorderedStyle style) {
        return Optional.ofNullable(style.getBorderSize()).orElse(Integer.valueOf(1)).intValue();
    }

    /**
     * Returns the ports.
     *
     * @return The ports
     */
    protected List<AbstractSiriusDiagramElement> getPorts() {
        // @formatter:off
        return this.getDNode().getOwnedBorderedNodes().stream()
                .map(new SiriusDiagramElementSwitch()::doSwitch)
                .map(ISiriusDiagramElementConverter::convert)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        // @formatter:on
    }

    /**
     * Returns the path of the image.
     *
     * @return The path of the image
     */
    protected String getImagePath() {
        // @formatter:off
        return SiriusDiagramPlugin.getPlugin().getImagePathProvider()
                    .flatMap(provider -> provider.getLabelProviderImagePath(this.getDNode().getTarget()))
                    .orElse(null);
        // @formatter:on
    }
}
