/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.description.tool;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Move Element</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> Move the element of the current context to another
 * container. <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.description.tool.MoveElement#getNewContainerExpression
 * <em>New Container Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.description.tool.MoveElement#getFeatureName
 * <em>Feature Name</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.description.tool.ToolPackage#getMoveElement()
 * @model
 * @generated
 */
public interface MoveElement extends ContainerModelOperation {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

    /**
     * Returns the value of the '<em><b>New Container Expression</b></em>'
     * attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>New Container Expression</em>' attribute isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> Acceleo expression that
     * allows to find the new container. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>New Container Expression</em>' attribute.
     * @see #setNewContainerExpression(String)
     * @see org.eclipse.sirius.description.tool.ToolPackage#getMoveElement_NewContainerExpression()
     * @model dataType="viewpoint.description.AcceleoExpression"
     * @generated
     */
    String getNewContainerExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.description.tool.MoveElement#getNewContainerExpression
     * <em>New Container Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>New Container Expression</em>'
     *            attribute.
     * @see #getNewContainerExpression()
     * @generated
     */
    void setNewContainerExpression(String value);

    /**
     * Returns the value of the '<em><b>Feature Name</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Feature Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The name of the reference
     * that contained the element. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Feature Name</em>' attribute.
     * @see #setFeatureName(String)
     * @see org.eclipse.sirius.description.tool.ToolPackage#getMoveElement_FeatureName()
     * @model dataType="viewpoint.description.ReferenceName" required="true"
     * @generated
     */
    String getFeatureName();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.description.tool.MoveElement#getFeatureName
     * <em>Feature Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Feature Name</em>' attribute.
     * @see #getFeatureName()
     * @generated
     */
    void setFeatureName(String value);

} // MoveElement
