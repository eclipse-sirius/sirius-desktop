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
package org.eclipse.sirius.server.backend.internal.services.project;

/**
 * The DTO used to represent a tree.
 *
 * @author sbegaudeau
 */
@SuppressWarnings({ "checkstyle::javadocmethod", "checkstyle::javadocfield" })
public class SiriusServerTreeDto extends AbstractSiriusServerRepresentationDto {

    private static final String KIND = "tree"; //$NON-NLS-1$

    /**
     * The constructor.
     *
     * @param viewpointName
     *            The name of the viewpoint
     *
     * @param descriptionName
     *            The name of the description
     * @param name
     *            The name
     */
    public SiriusServerTreeDto(String viewpointName, String descriptionName, String name) {
        super(KIND, viewpointName, descriptionName, name);
    }

}
