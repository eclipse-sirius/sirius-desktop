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
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>DSemantic Decorator</b></em>'. <!--
 * end-user-doc -->
 *
 * <!-- begin-model-doc --> An element that has a link to the semantic model. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.DSemanticDecorator#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDSemanticDecorator()
 * @model abstract="true"
 * @generated
 */
public interface DSemanticDecorator extends IdentifiedElement {
    /**
     * Returns the value of the '<em><b>Target</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The referenced EObject. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Target</em>' reference.
     * @see #setTarget(EObject)
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDSemanticDecorator_Target()
     * @model required="true"
     * @generated
     */
    EObject getTarget();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.DSemanticDecorator#getTarget <em>Target</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Target</em>' reference.
     * @see #getTarget()
     * @generated
     */
    void setTarget(EObject value);

} // DSemanticDecorator
