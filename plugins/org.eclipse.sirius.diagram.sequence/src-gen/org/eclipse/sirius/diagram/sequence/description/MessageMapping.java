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
package org.eclipse.sirius.diagram.sequence.description;

import org.eclipse.sirius.diagram.description.EdgeMapping;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Message Mapping</b></em>'. <!-- end-user-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.sequence.description.MessageMapping#getSendingEndFinderExpression <em>Sending
 * End Finder Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.description.MessageMapping#getReceivingEndFinderExpression
 * <em>Receiving End Finder Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.sequence.description.DescriptionPackage#getMessageMapping()
 * @model abstract="true"
 * @generated
 */
public interface MessageMapping extends EdgeMapping, EventMapping {

    /**
     * Returns the value of the '<em><b>Sending End Finder Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sending End Finder Expression</em>' attribute isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Sending End Finder Expression</em>' attribute.
     * @see #setSendingEndFinderExpression(String)
     * @see org.eclipse.sirius.diagram.sequence.description.DescriptionPackage#getMessageMapping_SendingEndFinderExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression" required="true"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='an EObject.'"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/variables"
     * @generated
     */
    String getSendingEndFinderExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.diagram.sequence.description.MessageMapping#getSendingEndFinderExpression <em>Sending
     * End Finder Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Sending End Finder Expression</em>' attribute.
     * @see #getSendingEndFinderExpression()
     * @generated
     */
    void setSendingEndFinderExpression(String value);

    /**
     * Returns the value of the '<em><b>Receiving End Finder Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Receiving End Finder Expression</em>' attribute isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Receiving End Finder Expression</em>' attribute.
     * @see #setReceivingEndFinderExpression(String)
     * @see org.eclipse.sirius.diagram.sequence.description.DescriptionPackage#getMessageMapping_ReceivingEndFinderExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression" required="true"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='an EObject.'"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/variables"
     * @generated
     */
    String getReceivingEndFinderExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.diagram.sequence.description.MessageMapping#getReceivingEndFinderExpression
     * <em>Receiving End Finder Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Receiving End Finder Expression</em>' attribute.
     * @see #getReceivingEndFinderExpression()
     * @generated
     */
    void setReceivingEndFinderExpression(String value);

} // MessageMapping
