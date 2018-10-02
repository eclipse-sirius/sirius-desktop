/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.metamodel.table.description;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.description.ColorDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Foreground Style Description</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.ForegroundStyleDescription#getLabelSize <em>Label
 * Size</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.ForegroundStyleDescription#getLabelFormat <em>Label
 * Format</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.ForegroundStyleDescription#getForeGroundColor
 * <em>Fore Ground Color</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getForegroundStyleDescription()
 * @model
 * @generated
 */
public interface ForegroundStyleDescription extends EObject {
    /**
     * Returns the value of the '<em><b>Label Size</b></em>' attribute. The default value is <code>"12"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The font size. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Label Size</em>' attribute.
     * @see #setLabelSize(int)
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getForegroundStyleDescription_LabelSize()
     * @model default="12"
     * @generated
     */
    int getLabelSize();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.table.metamodel.table.description.ForegroundStyleDescription#getLabelSize <em>Label
     * Size</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Label Size</em>' attribute.
     * @see #getLabelSize()
     * @generated
     */
    void setLabelSize(int value);

    /**
     * Returns the value of the '<em><b>Label Format</b></em>' attribute. The default value is <code>"normal"</code>.
     * The literals are from the enumeration {@link org.eclipse.sirius.viewpoint.FontFormat}. <!-- begin-user-doc -->
     * <!-- end-user-doc --> <!-- begin-model-doc --> The font format. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Label Format</em>' attribute.
     * @see org.eclipse.sirius.viewpoint.FontFormat
     * @see #setLabelFormat(FontFormat)
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getForegroundStyleDescription_LabelFormat()
     * @model default="normal"
     * @generated
     */
    EList<FontFormat> getLabelFormat();

    /**
     * Returns the value of the '<em><b>Fore Ground Color</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Fore Ground Color</em>' containment reference isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Fore Ground Color</em>' reference.
     * @see #setForeGroundColor(ColorDescription)
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getForegroundStyleDescription_ForeGroundColor()
     * @model required="true"
     * @generated
     */
    ColorDescription getForeGroundColor();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.table.metamodel.table.description.ForegroundStyleDescription#getForeGroundColor
     * <em>Fore Ground Color</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Fore Ground Color</em>' reference.
     * @see #getForeGroundColor()
     * @generated
     */
    void setForeGroundColor(ColorDescription value);

} // ForegroundStyleDescription
