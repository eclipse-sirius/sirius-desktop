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
package org.eclipse.sirius.viewpoint;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Meta Model Extension</b></em>'. <!--
 * end-user-doc -->
 *
 * <!-- begin-model-doc --> Represent a Meta Model extension. A meta model extension adds types, attributes and
 * references into an existing meta model <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.MetaModelExtension#getExtensionGroup <em>Extension Group</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getMetaModelExtension()
 * @model
 * @generated
 */
public interface MetaModelExtension extends IdentifiedElement {
    /**
     * Returns the value of the '<em><b>Extension Group</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * --> <!-- begin-model-doc --> The referenced meta model extension. It should be an instance of ExtensionGroup.
     * <!-- end-model-doc -->
     *
     * @return the value of the '<em>Extension Group</em>' reference.
     * @see #setExtensionGroup(EObject)
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getMetaModelExtension_ExtensionGroup()
     * @model required="true"
     * @generated
     */
    EObject getExtensionGroup();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.MetaModelExtension#getExtensionGroup <em>Extension
     * Group</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Extension Group</em>' reference.
     * @see #getExtensionGroup()
     * @generated
     */
    void setExtensionGroup(EObject value);

} // MetaModelExtension
