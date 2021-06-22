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
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Cross Table Description</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.CrossTableDescription#getOwnedColumnMappings
 * <em>Owned Column Mappings</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.CrossTableDescription#getIntersection
 * <em>Intersection</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.CrossTableDescription#getCreateColumn <em>Create
 * Column</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getCrossTableDescription()
 * @model
 * @generated
 */
public interface CrossTableDescription extends TableDescription {
    /**
     * Returns the value of the '<em><b>Owned Column Mappings</b></em>' containment reference list. The list contents
     * are of type {@link org.eclipse.sirius.table.metamodel.table.description.ElementColumnMapping}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Owned Column Mappings</em>' containment reference list isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Owned Column Mappings</em>' containment reference list.
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getCrossTableDescription_OwnedColumnMappings()
     * @model containment="true" required="true"
     * @generated
     */
    EList<ElementColumnMapping> getOwnedColumnMappings();

    /**
     * Returns the value of the '<em><b>Intersection</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Intersection</em>' containment reference list isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Intersection</em>' containment reference list.
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getCrossTableDescription_Intersection()
     * @model containment="true"
     * @generated
     */
    EList<IntersectionMapping> getIntersection();

    /**
     * Returns the value of the '<em><b>Create Column</b></em>' containment reference list. The list contents are of
     * type {@link org.eclipse.sirius.table.metamodel.table.description.CreateCrossColumnTool}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Create Column</em>' containment reference list isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Create Column</em>' containment reference list.
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getCrossTableDescription_CreateColumn()
     * @model containment="true"
     * @generated
     */
    EList<CreateCrossColumnTool> getCreateColumn();

} // CrossTableDescription
