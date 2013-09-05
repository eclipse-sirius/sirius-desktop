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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>DNavigable</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> Represents an element that can be navigate. The user
 * can see the details in another editor. <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.DNavigable#getOwnedNavigationLinks <em>Owned
 * Navigation Links</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.SiriusPackage#getDNavigable()
 * @model abstract="true"
 * @generated
 */
public interface DNavigable extends EObject {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

    /**
     * Returns the value of the '<em><b>Owned Navigation Links</b></em>'
     * containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.DNavigationLink}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Owned Navigation Links</em>' containment
     * reference list isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The links of the navigable
     * element. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Owned Navigation Links</em>' containment
     *         reference list.
     * @see org.eclipse.sirius.SiriusPackage#getNavigable_OwnedNavigationLinks()
     * @model type="viewpoint.DNavigationLink" containment="true"
     *        resolveProxies="true"
     * @generated
     */
    EList<DNavigationLink> getOwnedNavigationLinks();

} // DNavigable
