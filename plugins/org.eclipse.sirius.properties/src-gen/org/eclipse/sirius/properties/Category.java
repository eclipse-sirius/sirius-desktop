/**
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.properties;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Category</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.Category#getPages <em>Pages</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.Category#getGroups <em>Groups</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.Category#getOverrides <em>Overrides</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getCategory()
 * @model
 * @generated
 */
public interface Category extends IdentifiedElement, DocumentedElement {
    /**
     * Returns the value of the '<em><b>Pages</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.properties.PageDescription}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Pages</em>' containment reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Pages</em>' containment reference list.
     * @see org.eclipse.sirius.properties.PropertiesPackage#getCategory_Pages()
     * @model containment="true"
     * @generated
     */
    EList<PageDescription> getPages();

    /**
     * Returns the value of the '<em><b>Groups</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.properties.GroupDescription}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Groups</em>' containment reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Groups</em>' containment reference list.
     * @see org.eclipse.sirius.properties.PropertiesPackage#getCategory_Groups()
     * @model containment="true"
     * @generated
     */
    EList<GroupDescription> getGroups();

    /**
     * Returns the value of the '<em><b>Overrides</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.properties.AbstractOverrideDescription}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Overrides</em>' containment reference list isn't clear, there really should be more of
     * a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Overrides</em>' containment reference list.
     * @see org.eclipse.sirius.properties.PropertiesPackage#getCategory_Overrides()
     * @model containment="true"
     * @generated
     */
    EList<AbstractOverrideDescription> getOverrides();

} // Category
