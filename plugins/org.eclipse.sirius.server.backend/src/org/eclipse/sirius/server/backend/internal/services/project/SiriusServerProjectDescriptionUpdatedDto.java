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
 * The DTO received by clients after the update of the project description.
 *
 * @author sbegaudeau
 */
@SuppressWarnings({ "checkstyle::javadocmethod", "checkstyle::javadocfield" })
public class SiriusServerProjectDescriptionUpdatedDto {
    private String description;

    /**
     * The constructor.
     * 
     * @param description
     *            The new description of the project
     */
    public SiriusServerProjectDescriptionUpdatedDto(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
