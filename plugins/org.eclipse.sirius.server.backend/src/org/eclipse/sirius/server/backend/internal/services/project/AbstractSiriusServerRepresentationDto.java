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
 * The superclass of all the representation DTOs.
 * 
 * @author sbegaudeau
 */
@SuppressWarnings({ "checkstyle::javadocmethod", "checkstyle::javadocfield" })
public abstract class AbstractSiriusServerRepresentationDto {
    private String kind;

    private String viewpointName;

    private String descriptionName;

    private String name;

    /**
     * The constructor.
     *
     * @param kind
     *            The kind
     * @param viewpointName
     *            The name of the viewpoint
     * @param descriptionName
     *            The name of the description
     * @param name
     *            The name
     */
    public AbstractSiriusServerRepresentationDto(String kind, String viewpointName, String descriptionName, String name) {
        this.kind = kind;
        this.descriptionName = descriptionName;
        this.name = name;
    }

    public String getKind() {
        return this.kind;
    }

    public String getViewpointName() {
        return this.viewpointName;
    }

    public String getDescriptionName() {
        return this.descriptionName;
    }

    public String getName() {
        return this.name;
    }
}
