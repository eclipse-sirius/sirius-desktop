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

import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Center Label Style</b></em>'. <!-- end-user-doc
 * -->
 *
 * <!-- begin-model-doc --> The style of a label. <!-- end-model-doc -->
 *
 *
 * @see org.eclipse.sirius.diagram.DiagramPackage#getCenterLabelStyle()
 * @model
 * @generated
 */
public interface CenterLabelStyle extends BasicLabelStyle {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model kind="operation"
     * @generated
     */
    BasicLabelStyleDescription getDescription();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model
     * @generated
     */
    void setDescription(BasicLabelStyleDescription description);

} // CenterLabelStyle
