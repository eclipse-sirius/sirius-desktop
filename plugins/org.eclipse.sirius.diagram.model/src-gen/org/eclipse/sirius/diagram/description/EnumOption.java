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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Enum Option</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.description.EnumOption#getChoices <em>Choices</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getEnumOption()
 * @model abstract="true"
 * @generated
 */
public interface EnumOption extends LayoutOption {
    /**
     * Returns the value of the '<em><b>Choices</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.EnumLayoutValue}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Choices</em>' containment reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Choices</em>' containment reference list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getEnumOption_Choices()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<EnumLayoutValue> getChoices();

} // EnumOption
