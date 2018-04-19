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
package org.eclipse.sirius.server.backend.internal.services.projects;

import java.util.ArrayList;
import java.util.List;

/**
 * The DTO used to return the list of projects.
 *
 * @author sbegaudeau
 */
@SuppressWarnings({ "checkstyle::javadocmethod", "checkstyle::javadocfield" })
public class SiriusServerProjectsDto {
    private List<SiriusServerProjectDto> projects = new ArrayList<>();

    /**
     * The constructor.
     *
     * @param projects
     *            The projects
     */
    public SiriusServerProjectsDto(List<SiriusServerProjectDto> projects) {
        this.projects = projects;
    }

    public List<SiriusServerProjectDto> getProjects() {
        return this.projects;
    }
}
