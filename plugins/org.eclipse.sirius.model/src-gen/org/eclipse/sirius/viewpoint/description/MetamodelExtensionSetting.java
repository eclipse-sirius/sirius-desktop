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
package org.eclipse.sirius.viewpoint.description;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Metamodel Extension Setting</b></em>'. <!--
 * end-user-doc -->
 *
 * <!-- begin-model-doc --> Describe the Meta Model extension to use. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.MetamodelExtensionSetting#getExtensionGroup <em>Extension
 * Group</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getMetamodelExtensionSetting()
 * @model
 * @generated
 */
public interface MetamodelExtensionSetting extends EObject {
    /**
     * Returns the value of the '<em><b>Extension Group</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * --> <!-- begin-model-doc --> The Meta Model extension. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Extension Group</em>' reference.
     * @see #setExtensionGroup(EObject)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getMetamodelExtensionSetting_ExtensionGroup()
     * @model
     * @generated
     */
    EObject getExtensionGroup();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.MetamodelExtensionSetting#getExtensionGroup <em>Extension
     * Group</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Extension Group</em>' reference.
     * @see #getExtensionGroup()
     * @generated
     */
    void setExtensionGroup(EObject value);

} // MetamodelExtensionSetting
