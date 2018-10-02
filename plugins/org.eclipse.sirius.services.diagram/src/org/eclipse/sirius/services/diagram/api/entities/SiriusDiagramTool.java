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
package org.eclipse.sirius.services.diagram.api.entities;

/**
 * A tool from the diagram.
 *
 * @author sbegaudeau
 */
public class SiriusDiagramTool {

    /**
     * The type of the node creation tools.
     */
    public static final String NODE_CREATION_TYPE = "nodeCreation"; //$NON-NLS-1$

    /**
     * The identifier.
     */
    private String identifier;

    /**
     * The name.
     */
    private String name;

    /**
     * The type.
     */
    private String type;

    /**
     * The constructor.
     * 
     * @param identifier
     *            The identifier
     * @param name
     *            The name
     * @param type
     *            The type
     */
    public SiriusDiagramTool(String identifier, String name, String type) {
        this.identifier = identifier;
        this.name = name;
        this.type = type;
    }

    /**
     * Return the identifier.
     *
     * @return the identifier
     */
    public String getIdentifier() {
        return this.identifier;
    }

    /**
     * Return the name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Return the type.
     *
     * @return the type
     */
    public String getType() {
        return this.type;
    }

}
