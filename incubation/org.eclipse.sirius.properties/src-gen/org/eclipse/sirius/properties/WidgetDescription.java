/**
 * Copyright (c) 2015 Obeo.
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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Widget Description</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.sirius.properties.WidgetDescription#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.eclipse.sirius.properties.WidgetDescription#getLabelExpression <em>Label Expression</em>}</li>
 *   <li>{@link org.eclipse.sirius.properties.WidgetDescription#getDomainCandidatesExpression <em>Domain Candidates Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getWidgetDescription()
 * @model abstract="true"
 * @generated
 */
public interface WidgetDescription extends EObject {
    /**
     * Returns the value of the '<em><b>Identifier</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Identifier</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Identifier</em>' attribute.
     * @see #setIdentifier(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getWidgetDescription_Identifier()
     * @model required="true"
     * @generated
     */
    String getIdentifier();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.WidgetDescription#getIdentifier <em>Identifier</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @param value the new value of the '<em>Identifier</em>' attribute.
     * @see #getIdentifier()
     * @generated
     */
    void setIdentifier(String value);

    /**
     * Returns the value of the '<em><b>Label Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label Expression</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Label Expression</em>' attribute.
     * @see #setLabelExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getWidgetDescription_LabelExpression()
     * @model dataType="org.eclipse.sirius.expression.Expression"
     * @generated
     */
    String getLabelExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.WidgetDescription#getLabelExpression <em>Label Expression</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @param value the new value of the '<em>Label Expression</em>' attribute.
     * @see #getLabelExpression()
     * @generated
     */
    void setLabelExpression(String value);

    /**
     * Returns the value of the '<em><b>Domain Candidates Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Domain Candidates Expression</em>' attribute
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Domain Candidates Expression</em>' attribute.
     * @see #setDomainCandidatesExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getWidgetDescription_DomainCandidatesExpression()
     * @model dataType="org.eclipse.sirius.expression.Expression"
     * @generated
     */
    String getDomainCandidatesExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.WidgetDescription#getDomainCandidatesExpression <em>Domain Candidates Expression</em>}' attribute.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Domain Candidates Expression</em>' attribute.
     * @see #getDomainCandidatesExpression()
     * @generated
     */
    void setDomainCandidatesExpression(String value);

} // WidgetDescription
