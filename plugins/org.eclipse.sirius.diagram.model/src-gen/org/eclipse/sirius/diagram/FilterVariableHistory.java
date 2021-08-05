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
package org.eclipse.sirius.diagram;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.viewpoint.IdentifiedElement;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Filter Variable History</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.FilterVariableHistory#getOwnedValues <em>Owned Values</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.DiagramPackage#getFilterVariableHistory()
 * @model
 * @generated
 */
public interface FilterVariableHistory extends IdentifiedElement {
    /**
     * Returns the value of the '<em><b>Owned Values</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.VariableValue}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Owned Values</em>' containment reference list isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Owned Values</em>' containment reference list.
     * @see org.eclipse.sirius.diagram.DiagramPackage#getFilterVariableHistory_OwnedValues()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<VariableValue> getOwnedValues();

} // FilterVariableHistory
