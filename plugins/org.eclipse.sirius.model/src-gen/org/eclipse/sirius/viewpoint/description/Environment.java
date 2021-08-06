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
package org.eclipse.sirius.viewpoint.description;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.description.style.LabelBorderStyles;
import org.eclipse.sirius.viewpoint.description.tool.ToolEntry;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Environment</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.Environment#getSystemColors <em>System Colors</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.Environment#getDefaultTools <em>Default Tools</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.Environment#getLabelBorderStyles <em>Label Border
 * Styles</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getEnvironment()
 * @model
 * @generated
 */
public interface Environment extends EObject {
    /**
     * Returns the value of the '<em><b>System Colors</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>System Colors</em>' containment reference isn't clear, there really should be more of
     * a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>System Colors</em>' containment reference.
     * @see #setSystemColors(SytemColorsPalette)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getEnvironment_SystemColors()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    SytemColorsPalette getSystemColors();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.Environment#getSystemColors <em>System
     * Colors</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>System Colors</em>' containment reference.
     * @see #getSystemColors()
     * @generated
     */
    void setSystemColors(SytemColorsPalette value);

    /**
     * Returns the value of the '<em><b>Default Tools</b></em>' containment reference list. The list contents are of
     * type {@link org.eclipse.sirius.viewpoint.description.tool.ToolEntry}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Default Tools</em>' containment reference list isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Default Tools</em>' containment reference list.
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getEnvironment_DefaultTools()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<ToolEntry> getDefaultTools();

    /**
     * Returns the value of the '<em><b>Label Border Styles</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label Border Styles</em>' containment reference isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Label Border Styles</em>' containment reference.
     * @see #setLabelBorderStyles(LabelBorderStyles)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getEnvironment_LabelBorderStyles()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    LabelBorderStyles getLabelBorderStyles();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.Environment#getLabelBorderStyles <em>Label
     * Border Styles</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Label Border Styles</em>' containment reference.
     * @see #getLabelBorderStyles()
     * @generated
     */
    void setLabelBorderStyles(LabelBorderStyles value);

} // Environment
