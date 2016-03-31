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
package org.eclipse.sirius.diagram.description;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Additional Layer</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.AdditionalLayer#isActiveByDefault
 * <em>Active By Default</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.AdditionalLayer#isOptional
 * <em>Optional</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getAdditionalLayer()
 * @model
 * @generated
 */
public interface AdditionalLayer extends Layer {
    /**
     * Returns the value of the '<em><b>Active By Default</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Active By Default</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Active By Default</em>' attribute.
     * @see #setActiveByDefault(boolean)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getAdditionalLayer_ActiveByDefault()
     * @model
     * @generated
     */
    boolean isActiveByDefault();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.AdditionalLayer#isActiveByDefault
     * <em>Active By Default</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Active By Default</em>' attribute.
     * @see #isActiveByDefault()
     * @generated
     */
    void setActiveByDefault(boolean value);

    /**
     * Returns the value of the '<em><b>Optional</b></em>' attribute. The
     * default value is <code>"true"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Optional</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Optional</em>' attribute.
     * @see #setOptional(boolean)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getAdditionalLayer_Optional()
     * @model default="true"
     * @generated
     */
    boolean isOptional();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.AdditionalLayer#isOptional
     * <em>Optional</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Optional</em>' attribute.
     * @see #isOptional()
     * @generated
     */
    void setOptional(boolean value);

} // AdditionalLayer
