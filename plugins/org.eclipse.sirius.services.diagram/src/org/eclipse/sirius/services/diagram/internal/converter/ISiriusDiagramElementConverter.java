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

import org.eclipse.sirius.services.diagram.api.entities.AbstractSiriusDiagramElement;

/**
 * Used to convert diagram elements.
 *
 * @author sbegaudeau
 */
public interface ISiriusDiagramElementConverter {
    /**
     * Converts the diagram element.
     *
     * @return The diagram element converted
     */
    Optional<AbstractSiriusDiagramElement> convert();
}
