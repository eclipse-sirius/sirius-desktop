/*******************************************************************************
 * Copyright (c) 2019 Obeo.
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
package org.eclipse.sirius.services.graphql.emf.internal.schema;

import org.eclipse.emf.ecore.EClass;

/**
 * Interface used to compute the name of the type created for the given EClass.
 * 
 * @author sbegaudeau
 */
public interface INameProvider {
    /**
     * Indicates if we are considering the EClass for the name of a type or the name of an interface.
     * 
     * @author sbegaudeau
     */
    enum NameKind {
    /**
     * Indicates that we are looking for the name of a type.
     */
    TYPE,
    /**
     * Indicates that we are looking for the name of an interface.
     */
    INTERFACE
    }

    /**
     * Returns the name to use for the given EClass.
     * 
     * @param eClass
     *            The EClass
     * @param nameKind
     *            The kind of name that we are looking for
     * @return The name of the GraphQL type
     */
    String getName(EClass eClass, NameKind nameKind);
}
