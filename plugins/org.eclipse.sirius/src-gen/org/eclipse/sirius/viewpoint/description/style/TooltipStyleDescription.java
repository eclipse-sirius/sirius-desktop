/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.viewpoint.description.style;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Tooltip Style Description</b></em>'.
 *
 * @since 0.9.0<!-- end-user-doc -->
 *
 *        <p>
 *        The following features are supported:
 *        </p>
 *        <ul>
 *        <li>{@link org.eclipse.sirius.viewpoint.description.style.TooltipStyleDescription#getTooltipExpression
 *        <em>Tooltip Expression</em>}</li>
 *        </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.style.StylePackage#getTooltipStyleDescription()
 * @model
 * @generated
 */
public interface TooltipStyleDescription extends EObject {
    /**
     * Returns the value of the '<em><b>Tooltip Expression</b></em>' attribute. The default value is <code>""</code>.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> This expression is used to compute the
     * text of the optional tooltip shown when the user leaves the mouse on an element. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Tooltip Expression</em>' attribute.
     * @see #setTooltipExpression(String)
     * @see org.eclipse.sirius.viewpoint.description.style.StylePackage#getTooltipStyleDescription_TooltipExpression()
     * @model default="" dataType= "org.eclipse.sirius.viewpoint.description.InterpretedExpression" annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a string.'" annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/variables view='viewpoint.DSemanticDecorator | the
     *        current view.'"
     * @generated
     */
    String getTooltipExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.style.TooltipStyleDescription#getTooltipExpression <em>Tooltip
     * Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Tooltip Expression</em>' attribute.
     * @see #getTooltipExpression()
     * @generated
     */
    void setTooltipExpression(String value);

} // TooltipStyleDescription
