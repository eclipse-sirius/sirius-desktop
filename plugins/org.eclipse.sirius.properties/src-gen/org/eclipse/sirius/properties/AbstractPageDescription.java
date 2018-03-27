/**
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.properties;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Abstract Page Description</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.AbstractPageDescription#getLabelExpression <em>Label Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractPageDescription#getDomainClass <em>Domain Class</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractPageDescription#getSemanticCandidateExpression <em>Semantic
 * Candidate Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractPageDescription#getPreconditionExpression <em>Precondition
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractPageDescription#getGroups <em>Groups</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractPageDescription#getValidationSet <em>Validation Set</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractPageDescription#getActions <em>Actions</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractPageDescription#getExtends <em>Extends</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractPageDescription#getFilterGroupsFromExtendedPageExpression <em>Filter
 * Groups From Extended Page Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractPageDescription#getFilterValidationRulesFromExtendedPageExpression
 * <em>Filter Validation Rules From Extended Page Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractPageDescription#isIndented <em>Indented</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractPageDescription()
 * @model abstract="true"
 * @generated
 */
public interface AbstractPageDescription extends IdentifiedElement, DocumentedElement {
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
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractPageDescription_LabelExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getLabelExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractPageDescription#getLabelExpression <em>Label
     * Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Label Expression</em>' attribute.
     * @see #getLabelExpression()
     * @generated
     */
    void setLabelExpression(String value);

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
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractPageDescription_DomainClass()
     * @model dataType="org.eclipse.sirius.viewpoint.description.TypeName"
     * @generated
     */
    String getDomainClass();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractPageDescription#getDomainClass <em>Domain
     * Class</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Domain Class</em>' attribute.
     * @see #getDomainClass()
     * @generated
     */
    void setDomainClass(String value);

    /**
     * Returns the value of the '<em><b>Semantic Candidate Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Semantic Candidate Expression</em>' attribute isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Semantic Candidate Expression</em>' attribute.
     * @see #setSemanticCandidateExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractPageDescription_SemanticCandidateExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getSemanticCandidateExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.properties.AbstractPageDescription#getSemanticCandidateExpression <em>Semantic
     * Candidate Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Semantic Candidate Expression</em>' attribute.
     * @see #getSemanticCandidateExpression()
     * @generated
     */
    void setSemanticCandidateExpression(String value);

    /**
     * Returns the value of the '<em><b>Precondition Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Precondition Expression</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Precondition Expression</em>' attribute.
     * @see #setPreconditionExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractPageDescription_PreconditionExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getPreconditionExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractPageDescription#getPreconditionExpression
     * <em>Precondition Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Precondition Expression</em>' attribute.
     * @see #getPreconditionExpression()
     * @generated
     */
    void setPreconditionExpression(String value);

    /**
     * Returns the value of the '<em><b>Groups</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.sirius.properties.GroupDescription}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Groups</em>' reference list isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Groups</em>' reference list.
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractPageDescription_Groups()
     * @model keys="name"
     * @generated
     */
    EList<GroupDescription> getGroups();

    /**
     * Returns the value of the '<em><b>Validation Set</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Validation Set</em>' containment reference isn't clear, there really should be more of
     * a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Validation Set</em>' containment reference.
     * @see #setValidationSet(PageValidationSetDescription)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractPageDescription_ValidationSet()
     * @model containment="true"
     * @generated
     */
    PageValidationSetDescription getValidationSet();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractPageDescription#getValidationSet
     * <em>Validation Set</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Validation Set</em>' containment reference.
     * @see #getValidationSet()
     * @generated
     */
    void setValidationSet(PageValidationSetDescription value);

    /**
     * Returns the value of the '<em><b>Actions</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.properties.ToolbarAction}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Actions</em>' containment reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Actions</em>' containment reference list.
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractPageDescription_Actions()
     * @model containment="true"
     * @generated
     */
    EList<ToolbarAction> getActions();

    /**
     * Returns the value of the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Extends</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Extends</em>' reference.
     * @see #setExtends(PageDescription)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractPageDescription_Extends()
     * @model keys="name"
     * @generated
     */
    PageDescription getExtends();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractPageDescription#getExtends <em>Extends</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Extends</em>' reference.
     * @see #getExtends()
     * @generated
     */
    void setExtends(PageDescription value);

    /**
     * Returns the value of the '<em><b>Filter Groups From Extended Page Expression</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Filter Groups From Extended Page Expression</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Filter Groups From Extended Page Expression</em>' attribute.
     * @see #setFilterGroupsFromExtendedPageExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractPageDescription_FilterGroupsFromExtendedPageExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getFilterGroupsFromExtendedPageExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.properties.AbstractPageDescription#getFilterGroupsFromExtendedPageExpression
     * <em>Filter Groups From Extended Page Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Filter Groups From Extended Page Expression</em>' attribute.
     * @see #getFilterGroupsFromExtendedPageExpression()
     * @generated
     */
    void setFilterGroupsFromExtendedPageExpression(String value);

    /**
     * Returns the value of the '<em><b>Filter Validation Rules From Extended Page Expression</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Filter Validation Rules From Extended Page Expression</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Filter Validation Rules From Extended Page Expression</em>' attribute.
     * @see #setFilterValidationRulesFromExtendedPageExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractPageDescription_FilterValidationRulesFromExtendedPageExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getFilterValidationRulesFromExtendedPageExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.properties.AbstractPageDescription#getFilterValidationRulesFromExtendedPageExpression
     * <em>Filter Validation Rules From Extended Page Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Filter Validation Rules From Extended Page Expression</em>' attribute.
     * @see #getFilterValidationRulesFromExtendedPageExpression()
     * @generated
     */
    void setFilterValidationRulesFromExtendedPageExpression(String value);

    /**
     * Returns the value of the '<em><b>Indented</b></em>' attribute. The default value is <code>"false"</code>. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Indented</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Indented</em>' attribute.
     * @see #setIndented(boolean)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractPageDescription_Indented()
     * @model default="false"
     * @generated
     */
    boolean isIndented();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractPageDescription#isIndented
     * <em>Indented</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Indented</em>' attribute.
     * @see #isIndented()
     * @generated
     */
    void setIndented(boolean value);

} // AbstractPageDescription
