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

import java.util.List;

/**
 * The DTO used to return a project to clients.
 *
 * @author sbegaudeau
 */
@SuppressWarnings({ "checkstyle::javadocmethod", "checkstyle::javadocfield" })
public class SiriusServerProjectDto {
    private String name;

    private List<AbstractSiriusServerRepresentationDto> representations;

    private List<SiriusServerSemanticResourceDto> semanticResources;

    /**
     * The constructor.
     *
     * @param name
     *            The name of the project
     * @param representations
     *            The representations
     * @param semanticResources
     *            The semantic resources
     */
    public SiriusServerProjectDto(String name, List<AbstractSiriusServerRepresentationDto> representations, List<SiriusServerSemanticResourceDto> semanticResources) {
        this.name = name;
        this.representations = representations;
        this.semanticResources = semanticResources;
    }

    public String getName() {
        return this.name;
    }

    public List<AbstractSiriusServerRepresentationDto> getRepresentations() {
        return this.representations;
    }

    public List<SiriusServerSemanticResourceDto> getSemanticResources() {
        return this.semanticResources;
    }

}
