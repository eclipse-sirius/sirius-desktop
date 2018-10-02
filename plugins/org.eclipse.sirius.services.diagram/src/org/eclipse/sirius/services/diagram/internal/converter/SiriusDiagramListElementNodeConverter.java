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

import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.services.diagram.api.entities.AbstractSiriusDiagramElement;
import org.eclipse.sirius.services.diagram.api.entities.SiriusDiagramListElementNode;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;

/**
 * The DNodeListElement converter.
 *
 * @author sbegaudeau
 */
public class SiriusDiagramListElementNodeConverter extends AbstractSiriusDiagramNodeConverter {

    /**
     * The DNodeListElement.
     */
    private DNodeListElement dNodeListElement;

    /**
     * The constructor.
     *
     * @param dNodeListElement
     *            The DNodeListElement
     */
    public SiriusDiagramListElementNodeConverter(DNodeListElement dNodeListElement) {
        this.dNodeListElement = dNodeListElement;
    }

    @Override
    protected AbstractDNode getDNode() {
        return this.dNodeListElement;
    }

    @Override
    public Optional<AbstractSiriusDiagramElement> convert() {
        // @formatter:off
        Optional<BasicLabelStyle> optionalStyle = Optional.of(this.dNodeListElement.getStyle())
                .filter(BasicLabelStyle.class::isInstance)
                .map(BasicLabelStyle.class::cast);

        return optionalStyle.map(style -> {
            String identifier = this.getIdentifier();

            return SiriusDiagramListElementNode.newListElementNode(identifier, this.getSemanticElementIdentifier())
                    .label(this.getLabel(identifier, style))
                    .imagePath(this.getImagePath())
                    .build();
        });
        // @formatter:on
    }

}
