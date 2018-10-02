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
package org.eclipse.sirius.server.backend.internal.services.pages;

import java.util.List;

/**
 * The DTO used to receive the details of a page.
 *
 * @author sbegaudeau
 */
@SuppressWarnings({ "checkstyle::javadocmethod", "checkstyle::javadocfield" })
public class SiriusServerPageDto {
    private String identifier;

    private String name;

    private String description;

    private List<SiriusServerSectionDto> sections;

    /**
     * The constructor.
     * 
     * @param identifier
     *            The identifier
     * @param name
     *            The name
     * @param description
     *            The description
     * @param sections
     *            The sections
     */
    public SiriusServerPageDto(String identifier, String name, String description, List<SiriusServerSectionDto> sections) {
        this.identifier = identifier;
        this.name = name;
        this.description = description;
        this.sections = sections;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public List<SiriusServerSectionDto> getSections() {
        return this.sections;
    }

}
