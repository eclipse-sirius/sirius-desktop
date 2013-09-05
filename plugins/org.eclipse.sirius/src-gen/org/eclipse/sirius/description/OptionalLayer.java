/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.description;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Optional Layer</b></em>'.
 * 
 * @since 2.0 <!-- end-user-doc -->
 * 
 *        <p>
 *        The following features are supported:
 *        <ul>
 *        <li>
 *        {@link org.eclipse.sirius.description.OptionalLayer#isActiveByDefault
 *        <em>Active By Default</em>}</li>
 *        </ul>
 *        </p>
 * 
 * @see org.eclipse.sirius.description.DescriptionPackage#getOptionalLayer()
 * @model
 * @generated
 */
public interface OptionalLayer extends Layer {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

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
     * @see org.eclipse.sirius.description.DescriptionPackage#getOptionalLayer_ActiveByDefault()
     * @model
     * @generated
     */
    boolean isActiveByDefault();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.description.OptionalLayer#isActiveByDefault
     * <em>Active By Default</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Active By Default</em>' attribute.
     * @see #isActiveByDefault()
     * @generated
     */
    void setActiveByDefault(boolean value);

} // OptionalLayer
