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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Edition Table Description</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.EditionTableDescription#getOwnedColumnMappings
 * <em>Owned Column Mappings</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.EditionTableDescription#getReusedColumnMappings
 * <em>Reused Column Mappings</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.EditionTableDescription#getAllColumnMappings <em>All
 * Column Mappings</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getEditionTableDescription()
 * @model
 * @generated
 */
public interface EditionTableDescription extends TableDescription {
    /**
     * Returns the value of the '<em><b>Owned Column Mappings</b></em>' containment reference list. The list contents
     * are of type {@link org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Owned Column Mappings</em>' containment reference list isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Owned Column Mappings</em>' containment reference list.
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getEditionTableDescription_OwnedColumnMappings()
     * @model containment="true" keys="name" required="true"
     * @generated
     */
    EList<FeatureColumnMapping> getOwnedColumnMappings();

    /**
     * Returns the value of the '<em><b>Reused Column Mappings</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Reused Column Mappings</em>' reference list isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Reused Column Mappings</em>' reference list.
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getEditionTableDescription_ReusedColumnMappings()
     * @model keys="name"
     * @generated
     */
    EList<FeatureColumnMapping> getReusedColumnMappings();

    /**
     * Returns the value of the '<em><b>All Column Mappings</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>All Column Mappings</em>' reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>All Column Mappings</em>' reference list.
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getEditionTableDescription_AllColumnMappings()
     * @model keys="name" required="true" transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    EList<FeatureColumnMapping> getAllColumnMappings();

} // EditionTableDescription
