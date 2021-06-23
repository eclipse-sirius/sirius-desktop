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

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Delimited Event Mapping</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.sequence.description.DelimitedEventMapping#getStartingEndFinderExpression
 * <em>Starting End Finder Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.description.DelimitedEventMapping#getFinishingEndFinderExpression
 * <em>Finishing End Finder Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.sequence.description.DescriptionPackage#getDelimitedEventMapping()
 * @model abstract="true"
 * @generated
 */
public interface DelimitedEventMapping extends EventMapping {
    /**
     * Returns the value of the '<em><b>Starting End Finder Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Starting End Finder Expression</em>' attribute isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Starting End Finder Expression</em>' attribute.
     * @see #setStartingEndFinderExpression(String)
     * @see org.eclipse.sirius.diagram.sequence.description.DescriptionPackage#getDelimitedEventMapping_StartingEndFinderExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression" required="true"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='an EObject.'"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/variables"
     * @generated
     */
    String getStartingEndFinderExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.diagram.sequence.description.DelimitedEventMapping#getStartingEndFinderExpression
     * <em>Starting End Finder Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Starting End Finder Expression</em>' attribute.
     * @see #getStartingEndFinderExpression()
     * @generated
     */
    void setStartingEndFinderExpression(String value);

    /**
     * Returns the value of the '<em><b>Finishing End Finder Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Finishing End Finder Expression</em>' attribute isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Finishing End Finder Expression</em>' attribute.
     * @see #setFinishingEndFinderExpression(String)
     * @see org.eclipse.sirius.diagram.sequence.description.DescriptionPackage#getDelimitedEventMapping_FinishingEndFinderExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression" required="true"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='an EObject.'"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/variables"
     * @generated
     */
    String getFinishingEndFinderExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.diagram.sequence.description.DelimitedEventMapping#getFinishingEndFinderExpression
     * <em>Finishing End Finder Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Finishing End Finder Expression</em>' attribute.
     * @see #getFinishingEndFinderExpression()
     * @generated
     */
    void setFinishingEndFinderExpression(String value);

} // DelimitedEventMapping
