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
package org.eclipse.sirius;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.sirius.description.DecorationDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Decoration</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> Represent a decoration of a diagram element with a
 * specific icon, based on its relationships with MetaElements of the MetaModel.
 * 
 * <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.Decoration#getDescription <em>Description
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.SiriusPackage#getDecoration()
 * @model
 * @generated
 */
public interface Decoration extends EObject {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

    /**
     * Returns the value of the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * referenced DecorationDescription. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Description</em>' reference.
     * @see #setDescription(DecorationDescription)
     * @see org.eclipse.sirius.SiriusPackage#getDecoration_Description()
     * @model required="true"
     * @generated
     */
    DecorationDescription getDescription();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.Decoration#getDescription
     * <em>Description</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Description</em>' reference.
     * @see #getDescription()
     * @generated
     */
    void setDescription(DecorationDescription value);

} // Decoration
