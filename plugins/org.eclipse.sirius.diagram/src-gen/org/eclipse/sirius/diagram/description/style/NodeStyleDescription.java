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

import org.eclipse.sirius.diagram.LabelPosition;
import org.eclipse.sirius.diagram.ResizeKind;
import org.eclipse.sirius.viewpoint.description.style.LabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;
import org.eclipse.sirius.viewpoint.description.style.TooltipStyleDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Node Style Description</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> Style of a node. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.NodeStyleDescription#getSizeComputationExpression
 * <em>Size Computation Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.NodeStyleDescription#getLabelPosition
 * <em>Label Position</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.NodeStyleDescription#getResizeKind
 * <em>Resize Kind</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.diagram.description.style.StylePackage#getNodeStyleDescription()
 * @model abstract="true"
 * @generated
 */
public interface NodeStyleDescription extends StyleDescription, BorderedStyleDescription, LabelStyleDescription, TooltipStyleDescription, HideLabelCapabilityStyleDescription {
    /**
     * Returns the value of the '<em><b>Size Computation Expression</b></em>'
     * attribute. The default value is <code>"3"</code>. <!-- begin-user-doc -->
     * <!-- end-user-doc --> <!-- begin-model-doc --> Expression that computes
     * the size of the node. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Size Computation Expression</em>'
     *         attribute.
     * @see #setSizeComputationExpression(String)
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getNodeStyleDescription_SizeComputationExpression()
     * @model default="3" dataType=
     *        "org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='an integer.'"
     * @generated
     */
    String getSizeComputationExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.style.NodeStyleDescription#getSizeComputationExpression
     * <em>Size Computation Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Size Computation Expression</em>'
     *            attribute.
     * @see #getSizeComputationExpression()
     * @generated
     */
    void setSizeComputationExpression(String value);

    /**
     * Returns the value of the '<em><b>Label Position</b></em>' attribute. The
     * default value is <code>"border"</code>. The literals are from the
     * enumeration {@link org.eclipse.sirius.diagram.LabelPosition}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * relative position of the label to the node. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Label Position</em>' attribute.
     * @see org.eclipse.sirius.diagram.LabelPosition
     * @see #setLabelPosition(LabelPosition)
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getNodeStyleDescription_LabelPosition()
     * @model default="border"
     * @generated
     */
    LabelPosition getLabelPosition();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.style.NodeStyleDescription#getLabelPosition
     * <em>Label Position</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Label Position</em>' attribute.
     * @see org.eclipse.sirius.diagram.LabelPosition
     * @see #getLabelPosition()
     * @generated
     */
    void setLabelPosition(LabelPosition value);

    /**
     * Returns the value of the '<em><b>Resize Kind</b></em>' attribute. The
     * default value is <code>"NONE"</code>. The literals are from the
     * enumeration {@link org.eclipse.sirius.diagram.ResizeKind}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Define
     * the directions the user is able to resize. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Resize Kind</em>' attribute.
     * @see org.eclipse.sirius.diagram.ResizeKind
     * @see #setResizeKind(ResizeKind)
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getNodeStyleDescription_ResizeKind()
     * @model default="NONE" required="true"
     * @generated
     */
    ResizeKind getResizeKind();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.style.NodeStyleDescription#getResizeKind
     * <em>Resize Kind</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Resize Kind</em>' attribute.
     * @see org.eclipse.sirius.diagram.ResizeKind
     * @see #getResizeKind()
     * @generated
     */
    void setResizeKind(ResizeKind value);

} // NodeStyleDescription
