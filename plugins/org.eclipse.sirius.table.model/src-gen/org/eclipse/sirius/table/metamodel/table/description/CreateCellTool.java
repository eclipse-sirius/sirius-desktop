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

import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.EditMaskVariables;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Create Cell Tool</b></em>'. <!-- end-user-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.CreateCellTool#getMask <em>Mask</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.CreateCellTool#getMapping <em>Mapping</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getCreateCellTool()
 * @model
 * @generated
 */
public interface CreateCellTool extends TableTool, AbstractToolDescription {

    /**
     * Returns the value of the '<em><b>Mask</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * --> <!-- begin-model-doc --> The edit mask. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Mask</em>' containment reference.
     * @see #setMask(EditMaskVariables)
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getCreateCellTool_Mask()
     * @model containment="true" required="true"
     * @generated
     */
    EditMaskVariables getMask();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.table.metamodel.table.description.CreateCellTool#getMask
     * <em>Mask</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Mask</em>' containment reference.
     * @see #getMask()
     * @generated
     */
    void setMask(EditMaskVariables value);

    /**
     * Returns the value of the '<em><b>Mapping</b></em>' container reference. It is bidirectional and its opposite is '
     * {@link org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#getCreate <em>Create</em>}'. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Mapping</em>' container reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Mapping</em>' container reference.
     * @see #setMapping(IntersectionMapping)
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getCreateCellTool_Mapping()
     * @see org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#getCreate
     * @model opposite="create" required="true" transient="false"
     * @generated
     */
    IntersectionMapping getMapping();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.table.metamodel.table.description.CreateCellTool#getMapping
     * <em>Mapping</em>}' container reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Mapping</em>' container reference.
     * @see #getMapping()
     * @generated
     */
    void setMapping(IntersectionMapping value);
} // CreateCellTool
