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

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>TReturn Message Mapping</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.sequence.template.TReturnMessageMapping#getInvocationMapping <em>Invocation
 * Mapping</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.template.TReturnMessageMapping#getInvocationMessageFinderExpression
 * <em>Invocation Message Finder Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTReturnMessageMapping()
 * @model
 * @generated
 */
public interface TReturnMessageMapping extends TMessageMapping {
    /**
     * Returns the value of the '<em><b>Invocation Mapping</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Invocation Mapping</em>' reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Invocation Mapping</em>' reference.
     * @see #setInvocationMapping(TBasicMessageMapping)
     * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTReturnMessageMapping_InvocationMapping()
     * @model required="true"
     * @generated
     */
    TBasicMessageMapping getInvocationMapping();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.diagram.sequence.template.TReturnMessageMapping#getInvocationMapping <em>Invocation
     * Mapping</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Invocation Mapping</em>' reference.
     * @see #getInvocationMapping()
     * @generated
     */
    void setInvocationMapping(TBasicMessageMapping value);

    /**
     * Returns the value of the '<em><b>Invocation Message Finder Expression</b></em>' attribute. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Invocation Message Finder Expression</em>' attribute isn't clear, there really should
     * be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Invocation Message Finder Expression</em>' attribute.
     * @see #setInvocationMessageFinderExpression(String)
     * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTReturnMessageMapping_InvocationMessageFinderExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression" required="true"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='an EObject.'"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/variables"
     * @generated
     */
    String getInvocationMessageFinderExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.diagram.sequence.template.TReturnMessageMapping#getInvocationMessageFinderExpression
     * <em>Invocation Message Finder Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Invocation Message Finder Expression</em>' attribute.
     * @see #getInvocationMessageFinderExpression()
     * @generated
     */
    void setInvocationMessageFinderExpression(String value);

} // TReturnMessageMapping
