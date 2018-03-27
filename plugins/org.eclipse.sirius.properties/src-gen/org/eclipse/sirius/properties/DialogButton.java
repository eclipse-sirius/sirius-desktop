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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Dialog Button</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.DialogButton#getLabelExpression <em>Label Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.DialogButton#getIsEnabledExpression <em>Is Enabled Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.DialogButton#getInitialOperation <em>Initial Operation</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.DialogButton#isDefault <em>Default</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.DialogButton#isCloseDialogOnClick <em>Close Dialog On Click</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.DialogButton#isRollbackChangesOnClose <em>Rollback Changes On
 * Close</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getDialogButton()
 * @model
 * @generated
 */
public interface DialogButton extends EObject {
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
     * @see org.eclipse.sirius.properties.PropertiesPackage#getDialogButton_LabelExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getLabelExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.DialogButton#getLabelExpression <em>Label
     * Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Label Expression</em>' attribute.
     * @see #getLabelExpression()
     * @generated
     */
    void setLabelExpression(String value);

    /**
     * Returns the value of the '<em><b>Is Enabled Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Is Enabled Expression</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Is Enabled Expression</em>' attribute.
     * @see #setIsEnabledExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getDialogButton_IsEnabledExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getIsEnabledExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.DialogButton#getIsEnabledExpression <em>Is Enabled
     * Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Is Enabled Expression</em>' attribute.
     * @see #getIsEnabledExpression()
     * @generated
     */
    void setIsEnabledExpression(String value);

    /**
     * Returns the value of the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> Defines the behavior executed when the end-user will click on the
     * button. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Initial Operation</em>' containment reference.
     * @see #setInitialOperation(InitialOperation)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getDialogButton_InitialOperation()
     * @model containment="true" required="true"
     * @generated
     */
    InitialOperation getInitialOperation();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.DialogButton#getInitialOperation <em>Initial
     * Operation</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Initial Operation</em>' containment reference.
     * @see #getInitialOperation()
     * @generated
     */
    void setInitialOperation(InitialOperation value);

    /**
     * Returns the value of the '<em><b>Default</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Default</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Default</em>' attribute.
     * @see #setDefault(boolean)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getDialogButton_Default()
     * @model
     * @generated
     */
    boolean isDefault();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.DialogButton#isDefault <em>Default</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Default</em>' attribute.
     * @see #isDefault()
     * @generated
     */
    void setDefault(boolean value);

    /**
     * Returns the value of the '<em><b>Close Dialog On Click</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Close Dialog On Click</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Close Dialog On Click</em>' attribute.
     * @see #setCloseDialogOnClick(boolean)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getDialogButton_CloseDialogOnClick()
     * @model
     * @generated
     */
    boolean isCloseDialogOnClick();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.DialogButton#isCloseDialogOnClick <em>Close Dialog On
     * Click</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Close Dialog On Click</em>' attribute.
     * @see #isCloseDialogOnClick()
     * @generated
     */
    void setCloseDialogOnClick(boolean value);

    /**
     * Returns the value of the '<em><b>Rollback Changes On Close</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Rollback Changes On Close</em>' attribute isn't clear, there really should be more of
     * a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Rollback Changes On Close</em>' attribute.
     * @see #setRollbackChangesOnClose(boolean)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getDialogButton_RollbackChangesOnClose()
     * @model
     * @generated
     */
    boolean isRollbackChangesOnClose();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.DialogButton#isRollbackChangesOnClose <em>Rollback
     * Changes On Close</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Rollback Changes On Close</em>' attribute.
     * @see #isRollbackChangesOnClose()
     * @generated
     */
    void setRollbackChangesOnClose(boolean value);

} // DialogButton
