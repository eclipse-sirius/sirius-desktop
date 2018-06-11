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
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.eclipse.sirius.services.diagram.api.entities.AbstractSiriusDiagramElement;
import org.eclipse.sirius.services.diagram.api.entities.SiriusDiagramLabel;
import org.eclipse.sirius.services.diagram.api.entities.SiriusDiagramListNode;
import org.eclipse.sirius.services.diagram.api.entities.SiriusDiagramRGBColor;
import org.eclipse.sirius.viewpoint.Style;

/**
 * The DNodeList converter.
 *
 * @author sbegaudeau
 */
public class SiriusDiagramDNodeListConverter implements ISiriusDiagramElementConverter {

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
        Style style = this.dNodeList.getStyle();
        if (style instanceof FlatContainerStyle) {
            FlatContainerStyle flatContainerStyle = (FlatContainerStyle) style;
            String identifier = EcoreUtil.getURI(this.dNodeList).toString();
            String semanticElementIdentifier = EcoreUtil.getURI(this.dNodeList.getTarget()).toString();

            SiriusDiagramRGBColor labelColor = SiriusDiagramColorConverter.convert(flatContainerStyle.getLabelColor());

            SiriusDiagramRGBColor backgroundColor = SiriusDiagramColorConverter.convert(flatContainerStyle.getBackgroundColor());
            SiriusDiagramRGBColor borderColor = SiriusDiagramColorConverter.convert(flatContainerStyle.getBorderColor());
            int borderSize = Optional.ofNullable(flatContainerStyle.getBorderSize()).orElse(Integer.valueOf(1)).intValue();

            SiriusDiagramListNode node = new SiriusDiagramListNode(identifier, semanticElementIdentifier, backgroundColor, borderColor, borderSize);
            node.getChildren().add(new SiriusDiagramLabel(identifier + "__label", this.dNodeList.getName(), labelColor)); //$NON-NLS-1$

            return Optional.of(node);
        }
        return Optional.empty();
    }

}
