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
package org.eclipse.sirius.diagram;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Computed Style Description Registry</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.ComputedStyleDescriptionRegistry#getComputedStyleDescriptions
 * <em>Computed Style Descriptions</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.ComputedStyleDescriptionRegistry#getCache
 * <em>Cache</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.diagram.DiagramPackage#getComputedStyleDescriptionRegistry()
 * @model
 * @generated
 */
public interface ComputedStyleDescriptionRegistry extends EObject {
    /**
     * Returns the value of the '<em><b>Computed Style Descriptions</b></em>'
     * containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.style.StyleDescription}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Computed Style Descriptions</em>' containment
     * reference list isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Computed Style Descriptions</em>'
     *         containment reference list.
     * @see org.eclipse.sirius.diagram.DiagramPackage#getComputedStyleDescriptionRegistry_ComputedStyleDescriptions()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<StyleDescription> getComputedStyleDescriptions();

    /**
     * Returns the value of the '<em><b>Cache</b></em>' map. The key is of type
     * {@link org.eclipse.sirius.diagram.description.DiagramElementMapping}, and
     * the value is of type list of {@link java.util.Map.Entry
     * <org.eclipse.emf.ecore.EObject,
     * org.eclipse.emf.common.util.EMap<org.eclipse.emf.ecore.EObject,
     * org.eclipse.emf.common.util.EMap<org.eclipse.emf.ecore.EObject,
     * org.eclipse.sirius.viewpoint.description.style.StyleDescription>>>}, <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Cache</em>' map isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Cache</em>' map.
     * @see org.eclipse.sirius.diagram.DiagramPackage#getComputedStyleDescriptionRegistry_Cache()
     * @model mapType=
     *        "org.eclipse.sirius.diagram.DiagramElementMapping2ModelElement<org.eclipse.sirius.diagram.description.DiagramElementMapping, org.eclipse.sirius.viewpoint.ModelElement2ViewVariable>"
     * @generated
     */
    EMap<DiagramElementMapping, EMap<EObject, EMap<EObject, EMap<EObject, StyleDescription>>>> getCache();

} // ComputedStyleDescriptionRegistry
