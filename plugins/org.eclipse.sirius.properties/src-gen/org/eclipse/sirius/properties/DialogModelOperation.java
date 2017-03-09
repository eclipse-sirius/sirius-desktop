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
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Dialog Model Operation</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.DialogModelOperation#getTitleExpression <em>Title Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.DialogModelOperation#getButtons <em>Buttons</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.DialogModelOperation#getPage <em>Page</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.DialogModelOperation#getGroups <em>Groups</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getDialogModelOperation()
 * @model
 * @generated
 */
public interface DialogModelOperation extends ModelOperation {
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
     * @see org.eclipse.sirius.properties.PropertiesPackage#getDialogModelOperation_TitleExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getTitleExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.DialogModelOperation#getTitleExpression <em>Title
     * Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Title Expression</em>' attribute.
     * @see #getTitleExpression()
     * @generated
     */
    void setTitleExpression(String value);

    /**
     * Returns the value of the '<em><b>Buttons</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.properties.DialogButton}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Buttons</em>' containment reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Buttons</em>' containment reference list.
     * @see org.eclipse.sirius.properties.PropertiesPackage#getDialogModelOperation_Buttons()
     * @model containment="true"
     * @generated
     */
    EList<DialogButton> getButtons();

    /**
     * Returns the value of the '<em><b>Page</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Page</em>' containment reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Page</em>' containment reference.
     * @see #setPage(PageDescription)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getDialogModelOperation_Page()
     * @model containment="true" required="true"
     * @generated
     */
    PageDescription getPage();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.DialogModelOperation#getPage <em>Page</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Page</em>' containment reference.
     * @see #getPage()
     * @generated
     */
    void setPage(PageDescription value);

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
     * @see org.eclipse.sirius.properties.PropertiesPackage#getDialogModelOperation_Groups()
     * @model containment="true"
     * @generated
     */
    EList<GroupDescription> getGroups();

} // DialogModelOperation
