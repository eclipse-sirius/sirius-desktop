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
package org.eclipse.sirius.viewpoint.description.style;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Label Border Styles</b></em>'. <!--
 * end-user-doc -->
 *
 * <!-- begin-model-doc --> A group of LabelBorderStyleDescription to store in Environment.odesign. <!-- end-model-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.style.LabelBorderStyles#getLabelBorderStyleDescriptions <em>Label
 * Border Style Descriptions</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.style.StylePackage#getLabelBorderStyles()
 * @model
 * @generated
 */
public interface LabelBorderStyles extends EObject {
    /**
     * Returns the value of the '<em><b>Label Border Style Descriptions</b></em>' containment reference list. The list
     * contents are of type {@link org.eclipse.sirius.viewpoint.description.style.LabelBorderStyleDescription}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label Border Style Descriptions</em>' containment reference list isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Label Border Style Descriptions</em>' containment reference list.
     * @see org.eclipse.sirius.viewpoint.description.style.StylePackage#getLabelBorderStyles_LabelBorderStyleDescriptions()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<LabelBorderStyleDescription> getLabelBorderStyleDescriptions();

} // LabelBorderStyles
