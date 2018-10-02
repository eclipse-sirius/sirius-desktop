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
