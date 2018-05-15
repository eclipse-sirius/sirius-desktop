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
 * The DTO sent by clients to update the description of a project.
 *
 * @author sbegaudeau
 */
@SuppressWarnings({ "checkstyle::javadocmethod", "checkstyle::javadocfield" })
public class SiriusServerUpdateProjectDescriptionDto {
    private String description;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
