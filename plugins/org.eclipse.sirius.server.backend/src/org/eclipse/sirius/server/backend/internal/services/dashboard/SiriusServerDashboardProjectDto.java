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
package org.eclipse.sirius.server.backend.internal.services.dashboard;

/**
 * The DTO used to represent one project in the dashboard.
 * 
 * @author sbegaudeau
 */
@SuppressWarnings({ "checkstyle::javadocmethod", "checkstyle::javadocfield" })
public class SiriusServerDashboardProjectDto {
    private String name;

    private int semanticResourcesCount;

    private int representationsCount;

    /**
     * The constructor.
     *
     * @param name
     *            The name of the project
     * @param semanticResourcesCount
     *            The number of semantic resources
     * @param representationsCount
     *            The number of representations
     */
    public SiriusServerDashboardProjectDto(String name, int semanticResourcesCount, int representationsCount) {
        this.name = name;
        this.semanticResourcesCount = semanticResourcesCount;
        this.representationsCount = representationsCount;
    }

    public String getName() {
        return this.name;
    }

    public int getSemanticResourcesCount() {
        return this.semanticResourcesCount;
    }

    public int getRepresentationsCount() {
        return this.representationsCount;
    }

}
