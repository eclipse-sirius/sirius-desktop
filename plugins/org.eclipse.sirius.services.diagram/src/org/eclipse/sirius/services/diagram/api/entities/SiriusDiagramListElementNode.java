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
package org.eclipse.sirius.services.diagram.api.entities;

/**
 * An element of a list-based node.
 *
 * @author sbegaudeau
 */
public class SiriusDiagramListElementNode extends AbstractSiriusDiagramNode {
    /**
     * The type of the element.
     */
    private static final String TYPE = "node:listelement"; //$NON-NLS-1$

    /**
     * The constructor.
     *
     * @param identifier
     *            The identifier
     * @param semanticElementIdentifier
     *            The semantic element identifier
     */
    public SiriusDiagramListElementNode(String identifier, String semanticElementIdentifier) {
        super(identifier, semanticElementIdentifier, TYPE);
    }
}
