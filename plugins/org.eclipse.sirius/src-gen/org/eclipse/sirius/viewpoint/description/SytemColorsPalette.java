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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Sytem Colors Palette</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.SytemColorsPalette#getEntries <em>Entries</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getSytemColorsPalette()
 * @model
 * @generated
 */
public interface SytemColorsPalette extends EObject {
    /**
     * Returns the value of the '<em><b>Entries</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.SystemColor}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Entries</em>' containment reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Entries</em>' containment reference list.
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getSytemColorsPalette_Entries()
     * @model containment="true" resolveProxies="true" keys="name"
     * @generated
     */
    EList<SystemColor> getEntries();

} // SytemColorsPalette
