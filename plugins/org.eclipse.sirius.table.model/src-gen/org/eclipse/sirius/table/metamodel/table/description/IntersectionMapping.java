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
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Intersection Mapping</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#getLineMapping <em>Line
 * Mapping</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#getColumnMapping <em>Column
 * Mapping</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#getLabelExpression <em>Label
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#isUseDomainClass <em>Use Domain
 * Class</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#getColumnFinderExpression
 * <em>Column Finder Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#getLineFinderExpression <em>Line
 * Finder Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#getSemanticCandidatesExpression
 * <em>Semantic Candidates Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#getDomainClass <em>Domain
 * Class</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#getPreconditionExpression
 * <em>Precondition Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#getCreate <em>Create</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getIntersectionMapping()
 * @model
 * @generated
 */
public interface IntersectionMapping extends TableMapping, CellUpdater, StyleUpdater {
    /**
     * Returns the value of the '<em><b>Line Mapping</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.sirius.table.metamodel.table.description.LineMapping}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Line Mapping</em>' reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Line Mapping</em>' reference list.
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getIntersectionMapping_LineMapping()
     * @model keys="name" required="true"
     * @generated
     */
    EList<LineMapping> getLineMapping();

    /**
     * Returns the value of the '<em><b>Column Mapping</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Column Mapping</em>' reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Column Mapping</em>' reference.
     * @see #setColumnMapping(ColumnMapping)
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getIntersectionMapping_ColumnMapping()
     * @model required="true"
     * @generated
     */
    ColumnMapping getColumnMapping();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#getColumnMapping <em>Column
     * Mapping</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Column Mapping</em>' reference.
     * @see #getColumnMapping()
     * @generated
     */
    void setColumnMapping(ColumnMapping value);

    /**
     * Returns the value of the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label Expression</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Label Expression</em>' attribute.
     * @see #setLabelExpression(String)
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getIntersectionMapping_LabelExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a string.'"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/variables root='ecore.EObject | semantic
     *        target of the current DTable.' line='table.DLine | DLine of the current DCell.'
     *        lineSemantic='ecore.EObject | semantic target of $line' container='ecore.EObject | semantic target of
     *        $line.' column='table.DColumn | DColumn of the current DCell.' columnSemantic='ecore.EObject | semantic
     *        target of $column'"
     * @generated
     */
    String getLabelExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#getLabelExpression <em>Label
     * Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Label Expression</em>' attribute.
     * @see #getLabelExpression()
     * @generated
     */
    void setLabelExpression(String value);

    /**
     * Returns the value of the '<em><b>Use Domain Class</b></em>' attribute. The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Use Domain Class</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Use Domain Class</em>' attribute.
     * @see #setUseDomainClass(boolean)
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getIntersectionMapping_UseDomainClass()
     * @model default="false" required="true"
     * @generated
     */
    boolean isUseDomainClass();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#isUseDomainClass <em>Use Domain
     * Class</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Use Domain Class</em>' attribute.
     * @see #isUseDomainClass()
     * @generated
     */
    void setUseDomainClass(boolean value);

    /**
     * Returns the value of the '<em><b>Column Finder Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Column Finder Expression</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Column Finder Expression</em>' attribute.
     * @see #setColumnFinderExpression(String)
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getIntersectionMapping_ColumnFinderExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression" required="true"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a
     *        Collection&lt;EObject&gt; or an EObject.'"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/variables"
     * @generated
     */
    String getColumnFinderExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#getColumnFinderExpression
     * <em>Column Finder Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Column Finder Expression</em>' attribute.
     * @see #getColumnFinderExpression()
     * @generated
     */
    void setColumnFinderExpression(String value);

    /**
     * Returns the value of the '<em><b>Line Finder Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Line Finder Expression</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Line Finder Expression</em>' attribute.
     * @see #setLineFinderExpression(String)
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getIntersectionMapping_LineFinderExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a
     *        Collection&lt;EObject&gt; or an EObject.'"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/variables"
     * @generated
     */
    String getLineFinderExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#getLineFinderExpression <em>Line
     * Finder Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Line Finder Expression</em>' attribute.
     * @see #getLineFinderExpression()
     * @generated
     */
    void setLineFinderExpression(String value);

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
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getIntersectionMapping_SemanticCandidatesExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a
     *        Collection&lt;EObject&gt; or an EObject.'"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/variables"
     * @generated
     */
    String getSemanticCandidatesExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#getSemanticCandidatesExpression
     * <em>Semantic Candidates Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Semantic Candidates Expression</em>' attribute.
     * @see #getSemanticCandidatesExpression()
     * @generated
     */
    void setSemanticCandidatesExpression(String value);

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
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getIntersectionMapping_DomainClass()
     * @model dataType="org.eclipse.sirius.viewpoint.description.TypeName"
     * @generated
     */
    String getDomainClass();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#getDomainClass <em>Domain
     * Class</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Domain Class</em>' attribute.
     * @see #getDomainClass()
     * @generated
     */
    void setDomainClass(String value);

    /**
     * Returns the value of the '<em><b>Precondition Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Precondition Expression</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> An expression guarding the effect if evaluated to false. <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>Precondition Expression</em>' attribute.
     * @see #setPreconditionExpression(String)
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getIntersectionMapping_PreconditionExpression()
     * @model dataType="org.eclipse.sirius.description.InterpretedExpression" annotation =
     *        "http://www.eclipse.org/emf/2002/GenModel contentassist=''" annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a boolean.'" annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/variables line='table.DLine | the source view of the
     *        current potential line.' lineSemantic='ecore.EObject | the semantic element of $line.'
     *        column='table.DColumn | the source view of the current potential column.' columnSemantic='ecore.EObject |
     *        the semantic element of $column.' table='table.DTable | the current DTable.'"
     * @generated
     */
    String getPreconditionExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#getPreconditionExpression
     * <em>Precondition Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Precondition Expression</em>' attribute.
     * @see #getPreconditionExpression()
     * @generated
     */
    void setPreconditionExpression(String value);

    /**
     * Returns the value of the '<em><b>Create</b></em>' containment reference. It is bidirectional and its opposite is
     * '{@link org.eclipse.sirius.table.metamodel.table.description.CreateCellTool#getMapping <em>Mapping</em>}'. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Create</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Create</em>' containment reference.
     * @see #setCreate(CreateCellTool)
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getIntersectionMapping_Create()
     * @see org.eclipse.sirius.table.metamodel.table.description.CreateCellTool#getMapping
     * @model opposite="mapping" containment="true"
     * @generated
     */
    CreateCellTool getCreate();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#getCreate
     * <em>Create</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Create</em>' containment reference.
     * @see #getCreate()
     * @generated
     */
    void setCreate(CreateCellTool value);

} // IntersectionMapping
