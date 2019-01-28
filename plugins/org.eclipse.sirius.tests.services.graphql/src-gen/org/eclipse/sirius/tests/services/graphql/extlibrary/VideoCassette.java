/**
 *  Copyright (c) 2019 Obeo.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * 
 *  Contributors:
 *     Obeo - initial API and implementation
 */
package org.eclipse.sirius.tests.services.graphql.extlibrary;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Video Cassette</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.sirius.tests.services.graphql.extlibrary.VideoCassette#getCast <em>Cast</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.tests.services.graphql.extlibrary.ExtlibraryPackage#getVideoCassette()
 * @model
 * @generated
 */
public interface VideoCassette extends AudioVisualItem {
    /**
     * Returns the value of the '<em><b>Cast</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.sirius.tests.services.graphql.extlibrary.Person}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Cast</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Cast</em>' reference list.
     * @see org.eclipse.sirius.tests.services.graphql.extlibrary.ExtlibraryPackage#getVideoCassette_Cast()
     * @model
     * @generated
     */
    EList<Person> getCast();

} // VideoCassette
