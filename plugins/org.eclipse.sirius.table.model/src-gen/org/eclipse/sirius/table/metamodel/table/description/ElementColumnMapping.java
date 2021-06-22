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
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Element Column Mapping</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.ElementColumnMapping#getDomainClass <em>Domain
 * Class</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.ElementColumnMapping#getSemanticCandidatesExpression
 * <em>Semantic Candidates Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.ElementColumnMapping#getCreate <em>Create</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.ElementColumnMapping#getDelete <em>Delete</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getElementColumnMapping()
 * @model
 * @generated
 */
public interface ElementColumnMapping extends ColumnMapping, StyleUpdater {
    /**
     * Returns the value of the '<em><b>Domain Class</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Domain Class</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Domain Class</em>' attribute.
     * @see #setDomainClass(String)
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getElementColumnMapping_DomainClass()
     * @model dataType="org.eclipse.sirius.viewpoint.description.TypeName" required="true"
     * @generated
     */
    String getDomainClass();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.table.metamodel.table.description.ElementColumnMapping#getDomainClass <em>Domain
     * Class</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Domain Class</em>' attribute.
     * @see #getDomainClass()
     * @generated
     */
    void setDomainClass(String value);

    /**
     * Returns the value of the '<em><b>Semantic Candidates Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Semantic Candidates Expression</em>' attribute isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Semantic Candidates Expression</em>' attribute.
     * @see #setSemanticCandidatesExpression(String)
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getElementColumnMapping_SemanticCandidatesExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a
     *        Collection&lt;EObject&gt; or an EObject.'"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/variables viewpoint='table.DTable |
     *        (deprecated) current DTable.' table='table.DTable | current DTable.' containerView='table.DTable | current
     *        DTable.' container='ecore.EObject | semantic element targeted by the current DTable.'"
     * @generated
     */
    String getSemanticCandidatesExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.table.metamodel.table.description.ElementColumnMapping#getSemanticCandidatesExpression
     * <em>Semantic Candidates Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Semantic Candidates Expression</em>' attribute.
     * @see #getSemanticCandidatesExpression()
     * @generated
     */
    void setSemanticCandidatesExpression(String value);

    /**
     * Returns the value of the '<em><b>Create</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.table.metamodel.table.description.CreateColumnTool}. It is bidirectional and its
     * opposite is '{@link org.eclipse.sirius.table.metamodel.table.description.CreateColumnTool#getMapping
     * <em>Mapping</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Create</em>' containment reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Create</em>' containment reference list.
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getElementColumnMapping_Create()
     * @see org.eclipse.sirius.table.metamodel.table.description.CreateColumnTool#getMapping
     * @model opposite="mapping" containment="true"
     * @generated
     */
    EList<CreateColumnTool> getCreate();

    /**
     * Returns the value of the '<em><b>Delete</b></em>' containment reference. It is bidirectional and its opposite is
     * '{@link org.eclipse.sirius.table.metamodel.table.description.DeleteColumnTool#getMapping <em>Mapping</em>}'. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Delete</em>' containment reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Delete</em>' containment reference.
     * @see #setDelete(DeleteColumnTool)
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getElementColumnMapping_Delete()
     * @see org.eclipse.sirius.table.metamodel.table.description.DeleteColumnTool#getMapping
     * @model opposite="mapping" containment="true"
     * @generated
     */
    DeleteColumnTool getDelete();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.table.metamodel.table.description.ElementColumnMapping#getDelete
     * <em>Delete</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Delete</em>' containment reference.
     * @see #getDelete()
     * @generated
     */
    void setDelete(DeleteColumnTool value);

} // ElementColumnMapping
