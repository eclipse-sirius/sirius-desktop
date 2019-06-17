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
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Line Mapping</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.LineMapping#getOwnedSubLines <em>Owned Sub
 * Lines</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.LineMapping#getReusedSubLines <em>Reused Sub
 * Lines</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.LineMapping#getAllSubLines <em>All Sub
 * Lines</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.LineMapping#getReusedInMappings <em>Reused In
 * Mappings</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.LineMapping#getDomainClass <em>Domain
 * Class</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.LineMapping#getCreate <em>Create</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.LineMapping#getDelete <em>Delete</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.LineMapping#getSemanticCandidatesExpression
 * <em>Semantic Candidates Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.LineMapping#getHeaderLabelExpression <em>Header Label
 * Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getLineMapping()
 * @model
 * @generated
 */
public interface LineMapping extends TableMapping, StyleUpdater {
    /**
     * Returns the value of the '<em><b>Owned Sub Lines</b></em>' containment reference list. The list contents are of
     * type {@link org.eclipse.sirius.table.metamodel.table.description.LineMapping}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Owned Sub Lines</em>' containment reference list isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Owned Sub Lines</em>' containment reference list.
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getLineMapping_OwnedSubLines()
     * @model containment="true" keys="name"
     * @generated
     */
    EList<LineMapping> getOwnedSubLines();

    /**
     * Returns the value of the '<em><b>Reused Sub Lines</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.sirius.table.metamodel.table.description.LineMapping}. It is bidirectional and its opposite is
     * '{@link org.eclipse.sirius.table.metamodel.table.description.LineMapping#getReusedInMappings <em>Reused In
     * Mappings</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Reused Sub Lines</em>' reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Reused Sub Lines</em>' reference list.
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getLineMapping_ReusedSubLines()
     * @see org.eclipse.sirius.table.metamodel.table.description.LineMapping#getReusedInMappings
     * @model opposite="reusedInMappings"
     * @generated
     */
    EList<LineMapping> getReusedSubLines();

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
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getLineMapping_DomainClass()
     * @model dataType="org.eclipse.sirius.viewpoint.description.TypeName" required="true"
     * @generated
     */
    String getDomainClass();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.table.metamodel.table.description.LineMapping#getDomainClass
     * <em>Domain Class</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Domain Class</em>' attribute.
     * @see #getDomainClass()
     * @generated
     */
    void setDomainClass(String value);

    /**
     * Returns the value of the '<em><b>Create</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.table.metamodel.table.description.CreateLineTool}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Create</em>' containment reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Create</em>' containment reference list.
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getLineMapping_Create()
     * @model containment="true"
     * @generated
     */
    EList<CreateLineTool> getCreate();

    /**
     * Returns the value of the '<em><b>Delete</b></em>' containment reference. It is bidirectional and its opposite is
     * '{@link org.eclipse.sirius.table.metamodel.table.description.DeleteLineTool#getMapping <em>Mapping</em>}'. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Delete</em>' containment reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Delete</em>' containment reference.
     * @see #setDelete(DeleteLineTool)
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getLineMapping_Delete()
     * @see org.eclipse.sirius.table.metamodel.table.description.DeleteLineTool#getMapping
     * @model opposite="mapping" containment="true"
     * @generated
     */
    DeleteLineTool getDelete();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.table.metamodel.table.description.LineMapping#getDelete
     * <em>Delete</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Delete</em>' containment reference.
     * @see #getDelete()
     * @generated
     */
    void setDelete(DeleteLineTool value);

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
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getLineMapping_SemanticCandidatesExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a
     *        Collection&lt;EObject&gt; or an EObject.'"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/variables viewpoint='table.DTable |
     *        (deprecated) current DTable.' table='table.DTable | current DTable.' root='ecore.EObject | semantic target
     *        of $table.' containerView='table.LineContainer | current LineContainer (DLine or DTable).'
     *        container='ecore.EObject | semantic target of $containerView (if it is a DSemanticDecorator).'"
     * @generated
     */
    String getSemanticCandidatesExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.table.metamodel.table.description.LineMapping#getSemanticCandidatesExpression
     * <em>Semantic Candidates Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Semantic Candidates Expression</em>' attribute.
     * @see #getSemanticCandidatesExpression()
     * @generated
     */
    void setSemanticCandidatesExpression(String value);

    /**
     * Returns the value of the '<em><b>Header Label Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Header Label Expression</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Header Label Expression</em>' attribute.
     * @see #setHeaderLabelExpression(String)
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getLineMapping_HeaderLabelExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a string.'"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/variables"
     * @generated
     */
    String getHeaderLabelExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.table.metamodel.table.description.LineMapping#getHeaderLabelExpression <em>Header
     * Label Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Header Label Expression</em>' attribute.
     * @see #getHeaderLabelExpression()
     * @generated
     */
    void setHeaderLabelExpression(String value);

    /**
     * Returns the value of the '<em><b>All Sub Lines</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.sirius.table.metamodel.table.description.LineMapping}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>All Sub Lines</em>' reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>All Sub Lines</em>' reference list.
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getLineMapping_AllSubLines()
     * @model transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    EList<LineMapping> getAllSubLines();

    /**
     * Returns the value of the '<em><b>Reused In Mappings</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.sirius.table.metamodel.table.description.LineMapping}. It is bidirectional and its opposite is
     * '{@link org.eclipse.sirius.table.metamodel.table.description.LineMapping#getReusedSubLines <em>Reused Sub
     * Lines</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Reused In Mappings</em>' reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Reused In Mappings</em>' reference list.
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getLineMapping_ReusedInMappings()
     * @see org.eclipse.sirius.table.metamodel.table.description.LineMapping#getReusedSubLines
     * @model opposite="reusedSubLines"
     * @generated
     */
    EList<LineMapping> getReusedInMappings();

} // LineMapping
