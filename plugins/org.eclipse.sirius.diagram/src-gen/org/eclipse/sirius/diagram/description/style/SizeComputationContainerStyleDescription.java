/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.diagram.description.style;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Size Computation Container Style Description</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.SizeComputationContainerStyleDescription#getWidthComputationExpression
 * <em>Width Computation Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.SizeComputationContainerStyleDescription#getHeightComputationExpression
 * <em>Height Computation Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.style.StylePackage#getSizeComputationContainerStyleDescription()
 * @model abstract="true"
 * @generated
 */
public interface SizeComputationContainerStyleDescription extends EObject {
    /**
     * Returns the value of the '<em><b>Width Computation Expression</b></em>'
     * attribute. The default value is <code>"-1"</code>. <!-- begin-user-doc
     * --> <!-- end-user-doc --> <!-- begin-model-doc --> Expression that
     * computes the size of the node. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Width Computation Expression</em>'
     *         attribute.
     * @see #setWidthComputationExpression(String)
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getSizeComputationContainerStyleDescription_WidthComputationExpression()
     * @model default="-1" dataType=
     *        "org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='an integer.'"
     * @generated
     */
    String getWidthComputationExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.style.SizeComputationContainerStyleDescription#getWidthComputationExpression
     * <em>Width Computation Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Width Computation Expression</em>'
     *            attribute.
     * @see #getWidthComputationExpression()
     * @generated
     */
    void setWidthComputationExpression(String value);

    /**
     * Returns the value of the '<em><b>Height Computation Expression</b></em>'
     * attribute. The default value is <code>"-1"</code>. <!-- begin-user-doc
     * --> <!-- end-user-doc --> <!-- begin-model-doc --> Expression that
     * computes the size of the node. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Height Computation Expression</em>'
     *         attribute.
     * @see #setHeightComputationExpression(String)
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getSizeComputationContainerStyleDescription_HeightComputationExpression()
     * @model default="-1" dataType=
     *        "org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='an integer.'"
     * @generated
     */
    String getHeightComputationExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.style.SizeComputationContainerStyleDescription#getHeightComputationExpression
     * <em>Height Computation Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Height Computation Expression</em>'
     *            attribute.
     * @see #getHeightComputationExpression()
     * @generated
     */
    void setHeightComputationExpression(String value);

} // SizeComputationContainerStyleDescription
