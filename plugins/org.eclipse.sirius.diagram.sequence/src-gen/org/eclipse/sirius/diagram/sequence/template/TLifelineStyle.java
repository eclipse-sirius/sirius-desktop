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
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>TLifeline Style</b></em>'. <!-- end-user-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.sequence.template.TLifelineStyle#getLifelineWidthComputationExpression
 * <em>Lifeline Width Computation Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.template.TLifelineStyle#getLifelineColor <em>Lifeline Color</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTLifelineStyle()
 * @model
 * @generated
 */
public interface TLifelineStyle extends TTransformer {
    /**
     * Returns the value of the ' <em><b>Lifeline Width Computation Expression</b></em>' attribute. The default value is
     * <code>"0"</code>. <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> An expression computing
     * the size of the border. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Lifeline Width Computation Expression</em>' attribute.
     * @see #setLifelineWidthComputationExpression(String)
     * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTLifelineStyle_LifelineWidthComputationExpression()
     * @model default="0" dataType="org.eclipse.sirius.description.InterpretedExpression" annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='an integer.'"
     * @generated
     */
    String getLifelineWidthComputationExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.diagram.sequence.template.TLifelineStyle#getLifelineWidthComputationExpression
     * <em>Lifeline Width Computation Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Lifeline Width Computation Expression</em>' attribute.
     * @see #getLifelineWidthComputationExpression()
     * @generated
     */
    void setLifelineWidthComputationExpression(String value);

    /**
     * Returns the value of the '<em><b>Lifeline Color</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Lifeline Color</em>' reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Lifeline Color</em>' reference.
     * @see #setLifelineColor(ColorDescription)
     * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTLifelineStyle_LifelineColor()
     * @model required="true"
     * @generated
     */
    ColorDescription getLifelineColor();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.diagram.sequence.template.TLifelineStyle#getLifelineColor
     * <em>Lifeline Color</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Lifeline Color</em>' reference.
     * @see #getLifelineColor()
     * @generated
     */
    void setLifelineColor(ColorDescription value);

} // TLifelineStyle
