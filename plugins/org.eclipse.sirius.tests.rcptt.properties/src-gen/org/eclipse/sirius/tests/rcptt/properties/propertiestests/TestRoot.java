/**
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.htm
 */
package org.eclipse.sirius.tests.rcptt.properties.propertiestests;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Test Root</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestRoot#getElements <em>Elements</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestRoot()
 * @model
 * @generated
 */
public interface TestRoot extends EObject {
    /**
     * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Elements</em>' containment reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Elements</em>' containment reference list.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestRoot_Elements()
     * @model containment="true"
     * @generated
     */
    EList<TestElement> getElements();

} // TestRoot
