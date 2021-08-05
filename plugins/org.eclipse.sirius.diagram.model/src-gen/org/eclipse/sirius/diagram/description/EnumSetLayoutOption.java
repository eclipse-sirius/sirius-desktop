/**
 * Copyright (c) 2007, 2017 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.description;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Enum Set Layout Option</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.description.EnumSetLayoutOption#getValues <em>Values</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getEnumSetLayoutOption()
 * @model
 * @generated
 */
public interface EnumSetLayoutOption extends EnumOption {
    /**
     * Returns the value of the '<em><b>Values</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.EnumLayoutValue}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Values</em>' containment reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Values</em>' containment reference list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getEnumSetLayoutOption_Values()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<EnumLayoutValue> getValues();

} // EnumSetLayoutOption
