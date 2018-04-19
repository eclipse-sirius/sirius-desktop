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

/**
 * The DTO used to return a project.
 *
 * @author sbegaudeau
 */
@SuppressWarnings({ "checkstyle::javadocmethod", "checkstyle::javadocfield" })
public class SiriusServerProjectDto {
    private String name;

    /**
     * The constructor.
     *
     * @param name
     *            The name of the project
     */
    public SiriusServerProjectDto(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
