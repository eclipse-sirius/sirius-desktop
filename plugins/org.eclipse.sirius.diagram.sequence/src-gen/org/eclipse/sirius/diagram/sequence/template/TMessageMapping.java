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
package org.eclipse.sirius.diagram.sequence.template;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>TMessage Mapping</b></em>'. <!-- end-user-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.sequence.template.TMessageMapping#getSendingEndFinderExpression <em>Sending End
 * Finder Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.template.TMessageMapping#getReceivingEndFinderExpression <em>Receiving
 * End Finder Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.template.TMessageMapping#getStyle <em>Style</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.template.TMessageMapping#getConditionalStyle <em>Conditional
 * Style</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTMessageMapping()
 * @model abstract="true"
 * @generated
 */
public interface TMessageMapping extends TAbstractMapping {
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
     * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTMessageMapping_SendingEndFinderExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression" required="true"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='an EObject.'"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/variables"
     * @generated
     */
    String getSendingEndFinderExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.diagram.sequence.template.TMessageMapping#getSendingEndFinderExpression <em>Sending
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
     * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTMessageMapping_ReceivingEndFinderExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression" required="true"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='an EObject.'"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/variables"
     * @generated
     */
    String getReceivingEndFinderExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.diagram.sequence.template.TMessageMapping#getReceivingEndFinderExpression
     * <em>Receiving End Finder Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Receiving End Finder Expression</em>' attribute.
     * @see #getReceivingEndFinderExpression()
     * @generated
     */
    void setReceivingEndFinderExpression(String value);

    /**
     * Returns the value of the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Style</em>' containment reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Style</em>' containment reference.
     * @see #setStyle(TMessageStyle)
     * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTMessageMapping_Style()
     * @model containment="true" required="true"
     * @generated
     */
    TMessageStyle getStyle();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.diagram.sequence.template.TMessageMapping#getStyle
     * <em>Style</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Style</em>' containment reference.
     * @see #getStyle()
     * @generated
     */
    void setStyle(TMessageStyle value);

    /**
     * Returns the value of the '<em><b>Conditional Style</b></em>' containment reference list. The list contents are of
     * type {@link org.eclipse.sirius.diagram.sequence.template.TConditionalMessageStyle}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Conditional Style</em>' containment reference list isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Conditional Style</em>' containment reference list.
     * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTMessageMapping_ConditionalStyle()
     * @model containment="true"
     * @generated
     */
    EList<TConditionalMessageStyle> getConditionalStyle();

} // TMessageMapping
