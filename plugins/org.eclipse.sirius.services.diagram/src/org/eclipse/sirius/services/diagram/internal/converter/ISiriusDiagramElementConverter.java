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
