/**
 * Copyright (c) 2007, 2017 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.viewpoint.description;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Decoration Description</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.DecorationDescription#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.DecorationDescription#getPosition <em>Position</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.DecorationDescription#getDistributionDirection <em>Distribution
 * Direction</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.DecorationDescription#getPreconditionExpression <em>Precondition
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.DecorationDescription#getImageExpression <em>Image
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.DecorationDescription#getTooltipExpression <em>Tooltip
 * Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getDecorationDescription()
 * @model abstract="true"
 * @generated
 */
public interface DecorationDescription extends EObject {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The name of the decoration. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getDecorationDescription_Name()
     * @model required="true"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.DecorationDescription#getName
     * <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Position</b></em>' attribute. The default value is <code>"SOUTH_WEST"</code>.
     * The literals are from the enumeration {@link org.eclipse.sirius.viewpoint.description.Position}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Position</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Position</em>' attribute.
     * @see org.eclipse.sirius.viewpoint.description.Position
     * @see #setPosition(Position)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getDecorationDescription_Position()
     * @model default="SOUTH_WEST" required="true"
     * @generated
     */
    Position getPosition();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.DecorationDescription#getPosition
     * <em>Position</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Position</em>' attribute.
     * @see org.eclipse.sirius.viewpoint.description.Position
     * @see #getPosition()
     * @generated
     */
    void setPosition(Position value);

    /**
     * Returns the value of the '<em><b>Distribution Direction</b></em>' attribute. The literals are from the
     * enumeration {@link org.eclipse.sirius.viewpoint.description.DecorationDistributionDirection}. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Distribution Direction</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Distribution Direction</em>' attribute.
     * @see org.eclipse.sirius.viewpoint.description.DecorationDistributionDirection
     * @see #setDistributionDirection(DecorationDistributionDirection)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getDecorationDescription_DistributionDirection()
     * @model required="true"
     * @generated
     */
    DecorationDistributionDirection getDistributionDirection();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.DecorationDescription#getDistributionDirection <em>Distribution
     * Direction</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Distribution Direction</em>' attribute.
     * @see org.eclipse.sirius.viewpoint.description.DecorationDistributionDirection
     * @see #getDistributionDirection()
     * @generated
     */
    void setDistributionDirection(DecorationDistributionDirection value);

    /**
     * Returns the value of the '<em><b>Precondition Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> Expression that filters the elements on which we want to display the
     * decoration. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Precondition Expression</em>' attribute.
     * @see #setPreconditionExpression(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getDecorationDescription_PreconditionExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a boolean.'"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/variables
     *        view='viewpoint.DSemanticDecorator | the views of the checked elements.' element='ecore.EObject | the
     *        semantic element of the view.' containerView='viewpoint.DSemanticDecorator | the view that would contain
     *        the potential views of the checked elements.' container='ecore.EObject | the semantic element of the
     *        container view.' viewpoint='diagram.DSemanticDiagram | (deprecated) the current diagram.'
     *        diagram='diagram.DSemanticDiagram | the current diagram.'"
     * @generated
     */
    String getPreconditionExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.DecorationDescription#getPreconditionExpression <em>Precondition
     * Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Precondition Expression</em>' attribute.
     * @see #getPreconditionExpression()
     * @generated
     */
    void setPreconditionExpression(String value);

    /**
     * Returns the value of the '<em><b>Image Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * --> <!-- begin-model-doc --> Expression that provides the decoration as the following choices : * a path to an
     * image in the form of /myProjectID/path/to/image.png * an expression that gives a path to an image * an expression
     * that provides an instance of org.eclipse.swt.graphics.Image * an expression that provides an instance of
     * org.eclipse.draw2d.IFigure <!-- end-model-doc -->
     *
     * @return the value of the '<em>Image Expression</em>' attribute.
     * @see #setImageExpression(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getDecorationDescription_ImageExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression" required="true" annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a String, an Image or an
     *        IFigure'" annotation= "http://www.eclipse.org/sirius/interpreted/expression/variables
     *        containerView='viewpoint.DSemanticDecorator | the view that would contain the potential views of the
     *        checked elements.' container='ecore.EObject | the semantic element of the container view.'
     *        diagram='diagram.DDiagram | the current diagram.'"
     * @generated
     */
    String getImageExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.DecorationDescription#getImageExpression
     * <em>Image Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Image Expression</em>' attribute.
     * @see #getImageExpression()
     * @generated
     */
    void setImageExpression(String value);

    /**
     * Returns the value of the '<em><b>Tooltip Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> Expression that provides the tool-tip as the following choices : * a
     * fixed tool-tip string * an expression that provides a tool-tip string * an expression that provides an instance
     * of org.eclipse.draw2d.IFigure <!-- end-model-doc -->
     *
     * @return the value of the '<em>Tooltip Expression</em>' attribute.
     * @see #setTooltipExpression(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getDecorationDescription_TooltipExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression" annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a String, an Image or an
     *        IFigure'" annotation= "http://www.eclipse.org/sirius/interpreted/expression/variables
     *        containerView='viewpoint.DSemanticDecorator | the view that would contain the potential views of the
     *        checked elements.' container='ecore.EObject | the semantic element of the container view.'
     *        diagram='diagram.DDiagram | the current diagram.'"
     * @generated
     */
    String getTooltipExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.DecorationDescription#getTooltipExpression
     * <em>Tooltip Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Tooltip Expression</em>' attribute.
     * @see #getTooltipExpression()
     * @generated
     */
    void setTooltipExpression(String value);

} // DecorationDescription
