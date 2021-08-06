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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of
 * the model. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.viewpoint.description.style.StylePackage
 * @generated
 */
public interface StyleFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    StyleFactory eINSTANCE = org.eclipse.sirius.viewpoint.description.style.impl.StyleFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Basic Label Style Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>Basic Label Style Description</em>'.
     * @generated
     */
    BasicLabelStyleDescription createBasicLabelStyleDescription();

    /**
     * Returns a new object of class '<em>Label Style Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Label Style Description</em>'.
     * @generated
     */
    LabelStyleDescription createLabelStyleDescription();

    /**
     * Returns a new object of class '<em>Label Border Styles</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Label Border Styles</em>'.
     * @generated
     */
    LabelBorderStyles createLabelBorderStyles();

    /**
     * Returns a new object of class '<em>Label Border Style Description</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Label Border Style Description</em>'.
     * @generated
     */
    LabelBorderStyleDescription createLabelBorderStyleDescription();

    /**
     * Returns a new object of class '<em>Tooltip Style Description</em>'. <!-- begin-user-doc -->
     *
     * @since 0.9.0 <!-- end-user-doc -->
     * @return a new object of class '<em>Tooltip Style Description</em>'.
     * @generated
     */
    TooltipStyleDescription createTooltipStyleDescription();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the package supported by this factory.
     * @generated
     */
    StylePackage getStylePackage();

} // StyleFactory
