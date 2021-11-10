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

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Hide Label Filter</b></em>'. <!-- end-user-doc
 * -->
 *
 *
 * @see org.eclipse.sirius.diagram.DiagramPackage#getHideLabelFilter()
 * @model
 * @generated
 */
public interface HideLabelFilter extends GraphicalFilter {

    /**
     * Returns the value of the '<em><b>Hidden Labels</b></em>' attribute list. The list contents are of type
     * {@link java.lang.Integer}. <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> List of
     * VisualIDs of the labels that should be filtered. This feature is only used for the labels of a DEdge <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>Hidden Labels</em>' attribute list.
     * @see org.eclipse.sirius.diagram.DiagramPackage#getHideLabelFilter_HiddenLabels()
     * @model
     * @generated
     */
    EList<Integer> getHiddenLabels();
} // HideLabelFilter
