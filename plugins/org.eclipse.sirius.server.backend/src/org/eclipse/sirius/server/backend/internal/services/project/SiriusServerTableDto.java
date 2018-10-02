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
package org.eclipse.sirius.server.backend.internal.services.project;

/**
 * The DTO used to represent a table.
 *
 * @author sbegaudeau
 */
@SuppressWarnings({ "checkstyle::javadocmethod", "checkstyle::javadocfield" })
public class SiriusServerTableDto extends AbstractSiriusServerRepresentationDto {

    private static final String KIND = "table"; //$NON-NLS-1$

    /**
     * The constructor.
     *
     * @param viewpointName
     *            The name of the viewpoint
     * @param descriptionName
     *            The name of the description
     * @param name
     *            The name
     */
    public SiriusServerTableDto(String viewpointName, String descriptionName, String name) {
        super(KIND, viewpointName, descriptionName, name);
    }

}
