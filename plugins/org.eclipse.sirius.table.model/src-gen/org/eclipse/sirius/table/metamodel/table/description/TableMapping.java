/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.table.metamodel.table.description;

import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Table Mapping</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.TableMapping#getSemanticElements <em>Semantic
 * Elements</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getTableMapping()
 * @model
 * @generated
 */
public interface TableMapping extends RepresentationElementMapping {
    /**
     * Returns the value of the '<em><b>Semantic Elements</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * --> <!-- begin-model-doc --> The elements that are represented by this mapping. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Semantic Elements</em>' attribute.
     * @see #setSemanticElements(String)
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getTableMapping_SemanticElements()
     * @model dataType="org.eclipse.sirius.description.InterpretedExpression" annotation=
     *        "http://www.eclipse.org/emf/2002/GenModel contentassist=''"
     * @generated
     */
    String getSemanticElements();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.table.metamodel.table.description.TableMapping#getSemanticElements <em>Semantic
     * Elements</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Semantic Elements</em>' attribute.
     * @see #getSemanticElements()
     * @generated
     */
    void setSemanticElements(String value);

} // TableMapping
