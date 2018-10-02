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
 * The DTO used to represent a semantic resource.
 *
 * @author sbegaudeau
 */
@SuppressWarnings({ "checkstyle::javadocmethod", "checkstyle::javadocfield" })
public class SiriusServerSemanticResourceDto {
    private String path;

    private String name;

    private String size;

    /**
     * The constructor.
     *
     * @param path
     *            The path
     * @param name
     *            The name
     * @param size
     *            The size
     */
    public SiriusServerSemanticResourceDto(String path, String name, String size) {
        this.path = path;
        this.name = name;
        this.size = size;
    }

    public String getPath() {
        return this.path;
    }

    public String getName() {
        return this.name;
    }

    public String getSize() {
        return this.size;
    }
}
