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
package org.eclipse.sirius.viewpoint.description;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Decoration Description</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.DecorationDescription#getName
 * <em>Name</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.DecorationDescription#getPosition
 * <em>Position</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.DecorationDescription#getDecoratorPath
 * <em>Decorator Path</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.DecorationDescription#getPreconditionExpression
 * <em>Precondition Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getDecorationDescription()
 * @model abstract="true"
 * @generated
 */
public interface DecorationDescription extends EObject {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * name of the decoration. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getDecorationDescription_Name()
     * @model required="true"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.description.DecorationDescription#getName
     * <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Position</b></em>' attribute. The
     * default value is <code>"SOUTH_WEST"</code>. The literals are from the
     * enumeration {@link org.eclipse.sirius.viewpoint.description.Position}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Position</em>' attribute isn't clear, there
     * really should be more of a description here...
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
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.description.DecorationDescription#getPosition
     * <em>Position</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Position</em>' attribute.
     * @see org.eclipse.sirius.viewpoint.description.Position
     * @see #getPosition()
     * @generated
     */
    void setPosition(Position value);

    /**
     * Returns the value of the '<em><b>Decorator Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * path of the icon of the decoration. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Decorator Path</em>' attribute.
     * @see #setDecoratorPath(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getDecorationDescription_DecoratorPath()
     * @model required="true"
     * @generated
     */
    String getDecoratorPath();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.description.DecorationDescription#getDecoratorPath
     * <em>Decorator Path</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Decorator Path</em>' attribute.
     * @see #getDecoratorPath()
     * @generated
     */
    void setDecoratorPath(String value);

    /**
     * Returns the value of the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> Expression that filters the elements on which we want
     * to display the decoration. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Precondition Expression</em>' attribute.
     * @see #setPreconditionExpression(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getDecorationDescription_PreconditionExpression()
     * @model dataType=
     *        "org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a boolean.'"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/variables containerView='viewpoint.DSemanticDecorator | the view that would contain the potential views of the checked elements.' container='ecore.EObject | the semantic element of the container view.' viewpoint='diagram.DDiagram | (deprecated) the current diagram.' diagram='diagram.DDiagram | the current diagram.'"
     * @generated
     */
    String getPreconditionExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.description.DecorationDescription#getPreconditionExpression
     * <em>Precondition Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Precondition Expression</em>'
     *            attribute.
     * @see #getPreconditionExpression()
     * @generated
     */
    void setPreconditionExpression(String value);

} // DecorationDescription
