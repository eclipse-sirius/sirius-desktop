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
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.eclipse.sirius.services.diagram.api.entities.AbstractSiriusDiagramElement;
import org.eclipse.sirius.services.diagram.api.entities.SiriusDiagramLabel;
import org.eclipse.sirius.services.diagram.api.entities.SiriusDiagramRGBColor;
import org.eclipse.sirius.services.diagram.api.entities.SiriusDiagramSquareNode;
import org.eclipse.sirius.viewpoint.Style;

/**
 * The DNodeContainer converter.
 *
 * @author sbegaudeau
 */
public class SiriusDiagramDNodeContainerConverter implements ISiriusDiagramElementConverter {

    /**
     * The DNodeContainer.
     */
    private DNodeContainer dNodeContainer;

    /**
     * The constructor.
     *
     * @param dNodeContainer
     *            The DNodeContainer
     */
    public SiriusDiagramDNodeContainerConverter(DNodeContainer dNodeContainer) {
        this.dNodeContainer = dNodeContainer;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.services.diagram.internal.converter.ISiriusDiagramElementConverter#convert()
     */
    @Override
    public Optional<AbstractSiriusDiagramElement> convert() {
        Style style = this.dNodeContainer.getStyle();
        if (style instanceof FlatContainerStyle) {
            FlatContainerStyle flatContainerStyle = (FlatContainerStyle) style;
            String identifier = EcoreUtil.getURI(this.dNodeContainer).toString();
            String semanticElementIdentifier = EcoreUtil.getURI(this.dNodeContainer.getTarget()).toString();

            SiriusDiagramRGBColor labelColor = SiriusDiagramColorConverter.convert(flatContainerStyle.getLabelColor());

            SiriusDiagramRGBColor backgroundColor = SiriusDiagramColorConverter.convert(flatContainerStyle.getBackgroundColor());
            SiriusDiagramRGBColor borderColor = SiriusDiagramColorConverter.convert(flatContainerStyle.getBorderColor());
            int borderSize = Optional.ofNullable(flatContainerStyle.getBorderSize()).orElse(Integer.valueOf(1)).intValue();

            SiriusDiagramSquareNode node = new SiriusDiagramSquareNode(identifier, semanticElementIdentifier, backgroundColor, borderColor, borderSize);
            node.getChildren().add(new SiriusDiagramLabel(identifier + "__label", this.dNodeContainer.getName(), labelColor)); //$NON-NLS-1$
            return Optional.of(node);
        }
        return Optional.empty();
    }

}
