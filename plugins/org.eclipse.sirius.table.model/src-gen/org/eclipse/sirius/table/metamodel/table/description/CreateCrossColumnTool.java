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

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Create Cross Column Tool</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.CreateCrossColumnTool#getMapping
 * <em>Mapping</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getCreateCrossColumnTool()
 * @model
 * @generated
 */
public interface CreateCrossColumnTool extends CreateTool {
    /**
     * Returns the value of the '<em><b>Mapping</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Mapping</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Mapping</em>' reference.
     * @see #setMapping(ElementColumnMapping)
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getCreateCrossColumnTool_Mapping()
     * @model required="true"
     * @generated
     */
    ElementColumnMapping getMapping();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.table.metamodel.table.description.CreateCrossColumnTool#getMapping <em>Mapping</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Mapping</em>' reference.
     * @see #getMapping()
     * @generated
     */
    void setMapping(ElementColumnMapping value);

} // CreateCrossColumnTool
