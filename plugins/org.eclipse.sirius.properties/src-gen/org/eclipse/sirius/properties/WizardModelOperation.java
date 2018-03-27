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
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Wizard Model Operation</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.WizardModelOperation#getWindowTitleExpression <em>Window Title
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.WizardModelOperation#getTitleExpression <em>Title Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.WizardModelOperation#getDescriptionExpression <em>Description
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.WizardModelOperation#getIsPageCompleteExpression <em>Is Page Complete
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.WizardModelOperation#getPages <em>Pages</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.WizardModelOperation#getGroups <em>Groups</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.WizardModelOperation#getInitialOperation <em>Initial Operation</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getWizardModelOperation()
 * @model
 * @generated
 */
public interface WizardModelOperation extends ModelOperation {
    /**
     * Returns the value of the '<em><b>Window Title Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Window Title Expression</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Window Title Expression</em>' attribute.
     * @see #setWindowTitleExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getWizardModelOperation_WindowTitleExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getWindowTitleExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.WizardModelOperation#getWindowTitleExpression
     * <em>Window Title Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Window Title Expression</em>' attribute.
     * @see #getWindowTitleExpression()
     * @generated
     */
    void setWindowTitleExpression(String value);

    /**
     * Returns the value of the '<em><b>Title Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Title Expression</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Title Expression</em>' attribute.
     * @see #setTitleExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getWizardModelOperation_TitleExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getTitleExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.WizardModelOperation#getTitleExpression <em>Title
     * Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Title Expression</em>' attribute.
     * @see #getTitleExpression()
     * @generated
     */
    void setTitleExpression(String value);

    /**
     * Returns the value of the '<em><b>Description Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Description Expression</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Description Expression</em>' attribute.
     * @see #setDescriptionExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getWizardModelOperation_DescriptionExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getDescriptionExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.WizardModelOperation#getDescriptionExpression
     * <em>Description Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Description Expression</em>' attribute.
     * @see #getDescriptionExpression()
     * @generated
     */
    void setDescriptionExpression(String value);

    /**
     * Returns the value of the '<em><b>Is Page Complete Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Is Page Complete Expression</em>' attribute isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Is Page Complete Expression</em>' attribute.
     * @see #setIsPageCompleteExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getWizardModelOperation_IsPageCompleteExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getIsPageCompleteExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.WizardModelOperation#getIsPageCompleteExpression
     * <em>Is Page Complete Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Is Page Complete Expression</em>' attribute.
     * @see #getIsPageCompleteExpression()
     * @generated
     */
    void setIsPageCompleteExpression(String value);

    /**
     * Returns the value of the '<em><b>Pages</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.properties.PageDescription}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Pages</em>' containment reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Pages</em>' containment reference list.
     * @see org.eclipse.sirius.properties.PropertiesPackage#getWizardModelOperation_Pages()
     * @model containment="true" required="true"
     * @generated
     */
    EList<PageDescription> getPages();

    /**
     * Returns the value of the '<em><b>Groups</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.properties.GroupDescription}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Groups</em>' containment reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Groups</em>' containment reference list.
     * @see org.eclipse.sirius.properties.PropertiesPackage#getWizardModelOperation_Groups()
     * @model containment="true"
     * @generated
     */
    EList<GroupDescription> getGroups();

    /**
     * Returns the value of the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> Defines the behavior executed when the end-user updates the value of
     * the radio. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Initial Operation</em>' containment reference.
     * @see #setInitialOperation(InitialOperation)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getWizardModelOperation_InitialOperation()
     * @model containment="true" required="true"
     * @generated
     */
    InitialOperation getInitialOperation();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.WizardModelOperation#getInitialOperation <em>Initial
     * Operation</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Initial Operation</em>' containment reference.
     * @see #getInitialOperation()
     * @generated
     */
    void setInitialOperation(InitialOperation value);

} // WizardModelOperation
