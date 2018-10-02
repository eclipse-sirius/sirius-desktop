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
package org.eclipse.sirius.services.diagram.api;

/**
 * Common superclass of all the actions.
 *
 * @author sbegaudeau
 */
public abstract class AbstractSiriusDiagramAction {

    /**
     * The kind.
     */
    private String kind;

    /**
     * The constructor.
     *
     * @param kind
     *            The kind of the action
     */
    public AbstractSiriusDiagramAction(String kind) {
        this.kind = kind;
    }

    /**
     * Return the kind.
     *
     * @return the kind
     */
    public String getKind() {
        return this.kind;
    }
}
