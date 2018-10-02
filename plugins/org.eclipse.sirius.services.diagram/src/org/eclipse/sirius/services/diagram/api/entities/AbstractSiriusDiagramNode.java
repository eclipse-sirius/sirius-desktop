/*******************************************************************************
 * Copyright (c) 2018 TypeFox and others.
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
 * A node.
 *
 * @author sbegaudeau
 */
public abstract class AbstractSiriusDiagramNode extends AbstractSiriusDiagramElement {

    /**
     * The identifier of the semantic element.
     */
    private String semanticElementIdentifier;

    /**
     * The constructor.
     *
     * @param identifier
     *            The identifier
     * @param semanticElementIdentifier
     *            The identifier of the semantic element
     * @param type
     *            The type
     */
    public AbstractSiriusDiagramNode(String identifier, String semanticElementIdentifier, String type) {
        super(identifier, type);
        this.semanticElementIdentifier = semanticElementIdentifier;
    }

    /**
     * Return the semanticElementIdentifier.
     *
     * @return the semanticElementIdentifier
     */
    public String getSemanticElementIdentifier() {
        return this.semanticElementIdentifier;
    }

}
