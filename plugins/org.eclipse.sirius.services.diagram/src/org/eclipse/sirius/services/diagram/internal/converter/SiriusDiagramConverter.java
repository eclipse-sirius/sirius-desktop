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

import java.util.Objects;
import java.util.stream.Stream;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.services.diagram.api.entities.AbstractSiriusDiagramElement;
import org.eclipse.sirius.services.diagram.api.entities.SiriusDiagram;

/**
 * The converter is responsible for the transformation of the elements of the
 * Sirius DDiagram into SiriusDiagram.
 *
 * @author sbegaudeau
 */
public class SiriusDiagramConverter {
    /**
     * The Sirius diagram to convert.
     */
    private DDiagram dDiagram;

    /**
     * The constructor.
     *
     * @param dDiagram
     *            The diagram to convert
     */
    public SiriusDiagramConverter(DDiagram dDiagram) {
        this.dDiagram = Objects.requireNonNull(dDiagram);
    }

    /**
     * Computes the SiriusDiagram from the DDiagram.
     *
     * @return The SiriusDiagram created
     */
    public SiriusDiagram convert() {
        SiriusDiagram siriusDiagram = new SiriusDiagram(this.dDiagram.getUid());

        // @formatter:off
        this.dDiagram.getOwnedDiagramElements().stream()
            .filter(DDiagramElement::isVisible)
            .flatMap(this::convert)
            .forEach(siriusDiagram.getChildren()::add);
        // @formatter:on

        return siriusDiagram;
    }

    /**
     * Converts the given DDiagramElement into a Sirius diagram element.
     *
     * @param dDiagramElement
     *            The DDiagramElement to convert
     * @return The converted diagram element
     */
    private Stream<AbstractSiriusDiagramElement> convert(DDiagramElement dDiagramElement) {
        ISiriusDiagramElementConverter converter = new SiriusDiagramElementSwitch().doSwitch(dDiagramElement);
        return converter.convert().map(Stream::of).orElseGet(Stream::empty);
    }
}
