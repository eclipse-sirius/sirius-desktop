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
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Abstract Mapping Import</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.AbstractMappingImport#isHideSubMappings <em>Hide Sub
 * Mappings</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.AbstractMappingImport#isInheritsAncestorFilters <em>Inherits
 * Ancestor Filters</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getAbstractMappingImport()
 * @model abstract="true"
 * @generated
 */
public interface AbstractMappingImport extends EObject {
    /**
     * Returns the value of the '<em><b>Hide Sub Mappings</b></em>' attribute. The default value is <code>"false"</code>
     * . <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Set to true if you don't want to inherit
     * the sub mappings of the imported mapping. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Hide Sub Mappings</em>' attribute.
     * @see #setHideSubMappings(boolean)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getAbstractMappingImport_HideSubMappings()
     * @model default="false"
     * @generated
     */
    boolean isHideSubMappings();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.AbstractMappingImport#isHideSubMappings
     * <em>Hide Sub Mappings</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Hide Sub Mappings</em>' attribute.
     * @see #isHideSubMappings()
     * @generated
     */
    void setHideSubMappings(boolean value);

    /**
     * Returns the value of the '<em><b>Inherits Ancestor Filters</b></em>' attribute. The default value is
     * <code>"true"</code>. <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Set to true if you
     * want the filters applying on the imported mappings apply on this one. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Inherits Ancestor Filters</em>' attribute.
     * @see #setInheritsAncestorFilters(boolean)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getAbstractMappingImport_InheritsAncestorFilters()
     * @model default="true"
     * @generated
     */
    boolean isInheritsAncestorFilters();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.AbstractMappingImport#isInheritsAncestorFilters <em>Inherits
     * Ancestor Filters</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Inherits Ancestor Filters</em>' attribute.
     * @see #isInheritsAncestorFilters()
     * @generated
     */
    void setInheritsAncestorFilters(boolean value);

} // AbstractMappingImport
