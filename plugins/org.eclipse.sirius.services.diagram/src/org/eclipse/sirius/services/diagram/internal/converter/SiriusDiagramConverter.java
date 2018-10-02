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

import java.util.Objects;
import java.util.Optional;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
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
            .map(new SiriusDiagramElementSwitch()::doSwitch)
            .map(ISiriusDiagramElementConverter::convert)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .forEach(siriusDiagram.getChildren()::add);
        // @formatter:on

        return siriusDiagram;
    }
}
