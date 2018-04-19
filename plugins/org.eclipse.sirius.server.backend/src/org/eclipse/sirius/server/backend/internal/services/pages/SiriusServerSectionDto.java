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
package org.eclipse.sirius.server.backend.internal.services.pages;

import java.util.List;

/**
 * The DTO used to represent a section of the workflow page.
 *
 * @author sbegaudeau
 */
@SuppressWarnings({ "checkstyle::javadocmethod", "checkstyle::javadocfield" })
public class SiriusServerSectionDto {
    private String identifier;

    private String name;

    private List<SiriusServerActivityDto> activities;

    /**
     * The constructor.
     * 
     * @param identifier
     *            The identifier
     * @param name
     *            The name
     * @param activities
     *            The activities
     */
    public SiriusServerSectionDto(String identifier, String name, List<SiriusServerActivityDto> activities) {
        this.identifier = identifier;
        this.name = name;
        this.activities = activities;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public String getName() {
        return this.name;
    }

    public List<SiriusServerActivityDto> getActivities() {
        return this.activities;
    }
}
