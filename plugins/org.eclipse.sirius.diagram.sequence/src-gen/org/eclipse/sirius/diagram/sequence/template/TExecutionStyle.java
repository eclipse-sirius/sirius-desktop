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

import org.eclipse.sirius.viewpoint.description.ColorDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>TExecution Style</b></em>'. <!-- end-user-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.sequence.template.TExecutionStyle#getBorderSizeComputationExpression <em>Border
 * Size Computation Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.template.TExecutionStyle#getBorderColor <em>Border Color</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.template.TExecutionStyle#getBackgroundColor <em>Background Color</em>}
 * </li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTExecutionStyle()
 * @model
 * @generated
 */
public interface TExecutionStyle extends TTransformer {
    /**
     * Returns the value of the ' <em><b>Border Size Computation Expression</b></em>' attribute. The default value is
     * <code>"1"</code>. <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> An expression computing
     * the size of the border. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Border Size Computation Expression</em>' attribute.
     * @see #setBorderSizeComputationExpression(String)
     * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTExecutionStyle_BorderSizeComputationExpression()
     * @model default="1" dataType="org.eclipse.sirius.description.InterpretedExpression" annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='an integer.'"
     * @generated
     */
    String getBorderSizeComputationExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.diagram.sequence.template.TExecutionStyle#getBorderSizeComputationExpression
     * <em>Border Size Computation Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Border Size Computation Expression</em>' attribute.
     * @see #getBorderSizeComputationExpression()
     * @generated
     */
    void setBorderSizeComputationExpression(String value);

    /**
     * Returns the value of the '<em><b>Border Color</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Border Color</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Border Color</em>' reference.
     * @see #setBorderColor(ColorDescription)
     * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTExecutionStyle_BorderColor()
     * @model required="true"
     * @generated
     */
    ColorDescription getBorderColor();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.diagram.sequence.template.TExecutionStyle#getBorderColor
     * <em>Border Color</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Border Color</em>' reference.
     * @see #getBorderColor()
     * @generated
     */
    void setBorderColor(ColorDescription value);

    /**
     * Returns the value of the '<em><b>Background Color</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Background Color</em>' reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Background Color</em>' reference.
     * @see #setBackgroundColor(ColorDescription)
     * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTExecutionStyle_BackgroundColor()
     * @model required="true"
     * @generated
     */
    ColorDescription getBackgroundColor();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.diagram.sequence.template.TExecutionStyle#getBackgroundColor
     * <em>Background Color</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Background Color</em>' reference.
     * @see #getBackgroundColor()
     * @generated
     */
    void setBackgroundColor(ColorDescription value);

} // TExecutionStyle
