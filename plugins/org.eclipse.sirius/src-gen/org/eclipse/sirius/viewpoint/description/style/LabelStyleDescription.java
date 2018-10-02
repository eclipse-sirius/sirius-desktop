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

import org.eclipse.sirius.viewpoint.LabelAlignment;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Label Style Description</b></em>'. <!--
 * end-user-doc -->
 *
 * <!-- begin-model-doc --> The style of a label. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.style.LabelStyleDescription#getLabelAlignment <em>Label
 * Alignment</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.style.StylePackage#getLabelStyleDescription()
 * @model
 * @generated
 */
public interface LabelStyleDescription extends BasicLabelStyleDescription {
    /**
     * Returns the value of the '<em><b>Label Alignment</b></em>' attribute. The literals are from the enumeration
     * {@link org.eclipse.sirius.viewpoint.LabelAlignment}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label Alignment</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     *
     *
     * @since 0.9.0 <!-- end-user-doc -->
     * @return the value of the '<em>Label Alignment</em>' attribute.
     * @see org.eclipse.sirius.viewpoint.LabelAlignment
     * @see #setLabelAlignment(LabelAlignment)
     * @see org.eclipse.sirius.viewpoint.description.style.StylePackage#getLabelStyleDescription_LabelAlignment()
     * @model
     * @generated
     */
    LabelAlignment getLabelAlignment();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.style.LabelStyleDescription#getLabelAlignment <em>Label
     * Alignment</em>}' attribute. <!-- begin-user-doc -->
     *
     * @since 0.9.0 <!--end-user-doc -->
     * @param value
     *            the new value of the '<em>Label Alignment</em>' attribute.
     * @see org.eclipse.sirius.viewpoint.LabelAlignment
     * @see #getLabelAlignment()
     * @generated
     */
    void setLabelAlignment(LabelAlignment value);

} // LabelStyleDescription
