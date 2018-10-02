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
package org.eclipse.sirius.tree.description;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Style Updater</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.tree.description.StyleUpdater#getDefaultStyle <em>Default Style</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.description.StyleUpdater#getConditionalStyles <em>Conditional Styles</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.tree.description.DescriptionPackage#getStyleUpdater()
 * @model
 * @generated
 */
public interface StyleUpdater extends EObject {
    /**
     * Returns the value of the '<em><b>Default Style</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Default Style</em>' reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Default Style</em>' containment reference.
     * @see #setDefaultStyle(TreeItemStyleDescription)
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getStyleUpdater_DefaultStyle()
     * @model containment="true" required="true"
     * @generated
     */
    TreeItemStyleDescription getDefaultStyle();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tree.description.StyleUpdater#getDefaultStyle <em>Default
     * Style</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Default Style</em>' containment reference.
     * @see #getDefaultStyle()
     * @generated
     */
    void setDefaultStyle(TreeItemStyleDescription value);

    /**
     * Returns the value of the '<em><b>Conditional Styles</b></em>' containment reference list. The list contents are
     * of type {@link org.eclipse.sirius.tree.description.ConditionalTreeItemStyleDescription}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Conditional Styles</em>' reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Conditional Styles</em>' containment reference list.
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getStyleUpdater_ConditionalStyles()
     * @model containment="true"
     * @generated
     */
    EList<ConditionalTreeItemStyleDescription> getConditionalStyles();

} // StyleUpdater
