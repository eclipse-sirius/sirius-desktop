/**
 * Copyright (c) 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.viewpoint;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>UI State</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> This abstraction is used to store transient UI
 * informations. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.UIState#isInverseSelectionOrder <em>
 * Inverse Selection Order</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.UIState#getElementsToSelect <em>
 * Elements To Select</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getUIState()
 * @model
 * @generated
 */
public interface UIState extends EObject {
    /**
     * Returns the value of the '<em><b>Inverse Selection Order</b></em>'
     * attribute. The default value is <code>"false"</code>. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Inverse Selection Order</em>' attribute isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Inverse Selection Order</em>' attribute.
     * @see #setInverseSelectionOrder(boolean)
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getUIState_InverseSelectionOrder()
     * @model default="false" transient="true"
     * @generated
     */
    boolean isInverseSelectionOrder();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.UIState#isInverseSelectionOrder
     * <em>Inverse Selection Order</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Inverse Selection Order</em>'
     *            attribute.
     * @see #isInverseSelectionOrder()
     * @generated
     */
    void setInverseSelectionOrder(boolean value);

    /**
     * Returns the value of the '<em><b>Elements To Select</b></em>' reference
     * list. The list contents are of type {@link org.eclipse.emf.ecore.EObject}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Elements To Select</em>' reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Elements To Select</em>' reference list.
     * @see #isSetElementsToSelect()
     * @see #unsetElementsToSelect()
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getUIState_ElementsToSelect()
     * @model resolveProxies="false" unsettable="true" transient="true"
     * @generated
     */
    EList<EObject> getElementsToSelect();

    /**
     * Unsets the value of the '
     * {@link org.eclipse.sirius.viewpoint.UIState#getElementsToSelect
     * <em>Elements To Select</em>}' reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #isSetElementsToSelect()
     * @see #getElementsToSelect()
     * @generated
     */
    void unsetElementsToSelect();

    /**
     * Returns whether the value of the '
     * {@link org.eclipse.sirius.viewpoint.UIState#getElementsToSelect
     * <em>Elements To Select</em>}' reference list is set. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return whether the value of the '<em>Elements To Select</em>' reference
     *         list is set.
     * @see #unsetElementsToSelect()
     * @see #getElementsToSelect()
     * @generated
     */
    boolean isSetElementsToSelect();

} // UIState
