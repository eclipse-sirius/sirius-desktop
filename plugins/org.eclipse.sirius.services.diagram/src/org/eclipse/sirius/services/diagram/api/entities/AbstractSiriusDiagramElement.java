/*******************************************************************************
 * Copyright (c) 2018 TypeFox and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.services.diagram.api.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Common superclass of all the diagram elements.
 *
 * @author sbegaudeau
 */
public abstract class AbstractSiriusDiagramElement {
    /**
     * The identifier.
     */
    private String id;

    /**
     * The type.
     */
    private String type;

    /**
     * The children.
     */
    private List<AbstractSiriusDiagramElement> children = new ArrayList<>();

    /**
     * The constructor.
     *
     * @param identifier
     *            The identifier
     * @param type
     *            The type
     */
    public AbstractSiriusDiagramElement(String identifier, String type) {
        this.id = identifier;
        this.type = type;
    }

    /**
     * Return the id.
     *
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * Return the type.
     *
     * @return the type
     */
    public String getType() {
        return this.type;
    }

    /**
     * Return the children.
     *
     * @return the children
     */
    public List<AbstractSiriusDiagramElement> getChildren() {
        return this.children;
    }
}
