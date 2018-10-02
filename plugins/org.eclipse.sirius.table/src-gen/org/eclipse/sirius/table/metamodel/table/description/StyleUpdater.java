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

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Style Updater</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.StyleUpdater#getDefaultForeground <em>Default
 * Foreground</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.StyleUpdater#getForegroundConditionalStyle
 * <em>Foreground Conditional Style</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.StyleUpdater#getDefaultBackground <em>Default
 * Background</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.StyleUpdater#getBackgroundConditionalStyle
 * <em>Background Conditional Style</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getStyleUpdater()
 * @model abstract="true"
 * @generated
 */
public interface StyleUpdater extends EObject {
    /**
     * Returns the value of the '<em><b>Default Foreground</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Default Foreground</em>' containment reference isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Default Foreground</em>' containment reference.
     * @see #setDefaultForeground(ForegroundStyleDescription)
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getStyleUpdater_DefaultForeground()
     * @model containment="true"
     * @generated
     */
    ForegroundStyleDescription getDefaultForeground();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.table.metamodel.table.description.StyleUpdater#getDefaultForeground <em>Default
     * Foreground</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Default Foreground</em>' containment reference.
     * @see #getDefaultForeground()
     * @generated
     */
    void setDefaultForeground(ForegroundStyleDescription value);

    /**
     * Returns the value of the '<em><b>Foreground Conditional Style</b></em>' containment reference list. The list
     * contents are of type {@link org.eclipse.sirius.table.metamodel.table.description.ForegroundConditionalStyle}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Foreground Conditional Style</em>' containment reference list isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Foreground Conditional Style</em>' containment reference list.
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getStyleUpdater_ForegroundConditionalStyle()
     * @model containment="true"
     * @generated
     */
    EList<ForegroundConditionalStyle> getForegroundConditionalStyle();

    /**
     * Returns the value of the '<em><b>Default Background</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Default Background</em>' containment reference isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Default Background</em>' containment reference.
     * @see #setDefaultBackground(BackgroundStyleDescription)
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getStyleUpdater_DefaultBackground()
     * @model containment="true"
     * @generated
     */
    BackgroundStyleDescription getDefaultBackground();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.table.metamodel.table.description.StyleUpdater#getDefaultBackground <em>Default
     * Background</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Default Background</em>' containment reference.
     * @see #getDefaultBackground()
     * @generated
     */
    void setDefaultBackground(BackgroundStyleDescription value);

    /**
     * Returns the value of the '<em><b>Background Conditional Style</b></em>' containment reference list. The list
     * contents are of type {@link org.eclipse.sirius.table.metamodel.table.description.BackgroundConditionalStyle}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Background Conditional Style</em>' containment reference list isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Background Conditional Style</em>' containment reference list.
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getStyleUpdater_BackgroundConditionalStyle()
     * @model containment="true"
     * @generated
     */
    EList<BackgroundConditionalStyle> getBackgroundConditionalStyle();

} // StyleUpdater
