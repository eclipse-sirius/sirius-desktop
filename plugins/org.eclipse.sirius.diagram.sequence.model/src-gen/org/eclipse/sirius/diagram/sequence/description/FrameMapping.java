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

import org.eclipse.sirius.diagram.description.ContainerMapping;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Frame Mapping</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.sequence.description.FrameMapping#getCoveredLifelinesExpression <em>Covered
 * Lifelines Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.description.FrameMapping#getCenterLabelExpression <em>Center Label
 * Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.sequence.description.DescriptionPackage#getFrameMapping()
 * @model abstract="true"
 * @generated
 */
public interface FrameMapping extends ContainerMapping, DelimitedEventMapping {
    /**
     * Returns the value of the '<em><b>Covered Lifelines Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Covered Lifelines Expression</em>' attribute isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Covered Lifelines Expression</em>' attribute.
     * @see #setCoveredLifelinesExpression(String)
     * @see org.eclipse.sirius.diagram.sequence.description.DescriptionPackage#getFrameMapping_CoveredLifelinesExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression" required="true"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='the list of
     *        semantic EObjects representing the lifelines which are semantically covered by the frame.'"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/variables"
     * @generated
     */
    String getCoveredLifelinesExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.diagram.sequence.description.FrameMapping#getCoveredLifelinesExpression <em>Covered
     * Lifelines Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Covered Lifelines Expression</em>' attribute.
     * @see #getCoveredLifelinesExpression()
     * @generated
     */
    void setCoveredLifelinesExpression(String value);

    /**
     * Returns the value of the '<em><b>Center Label Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Center Label Expression</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Center Label Expression</em>' attribute.
     * @see #setCenterLabelExpression(String)
     * @see org.eclipse.sirius.diagram.sequence.description.DescriptionPackage#getFrameMapping_CenterLabelExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='the text to show
     *        in the center of the IU'" annotation="http://www.eclipse.org/sirius/interpreted/expression/variables"
     * @generated
     */
    String getCenterLabelExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.diagram.sequence.description.FrameMapping#getCenterLabelExpression <em>Center Label
     * Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Center Label Expression</em>' attribute.
     * @see #getCenterLabelExpression()
     * @generated
     */
    void setCenterLabelExpression(String value);

} // FrameMapping
