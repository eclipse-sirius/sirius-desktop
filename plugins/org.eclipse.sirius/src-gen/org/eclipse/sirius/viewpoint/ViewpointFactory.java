/**
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.viewpoint;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of
 * the model. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.viewpoint.ViewpointPackage
 * @generated
 */
public interface ViewpointFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    ViewpointFactory eINSTANCE = org.eclipse.sirius.viewpoint.impl.ViewpointFactoryImpl.init();

    /**
     * Returns a new object of class '<em>DAnalysis</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>DAnalysis</em>'.
     * @generated
     */
    DAnalysis createDAnalysis();

    /**
     * Returns a new object of class '<em>DRepresentation Descriptor</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>DRepresentation Descriptor</em>'.
     * @generated
     */
    DRepresentationDescriptor createDRepresentationDescriptor();

    /**
     * Returns a new object of class '<em>DView</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>DView</em>'.
     * @generated
     */
    DView createDView();

    /**
     * Returns a new object of class '<em>Meta Model Extension</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Meta Model Extension</em>'.
     * @generated
     */
    MetaModelExtension createMetaModelExtension();

    /**
     * Returns a new object of class '<em>Decoration</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Decoration</em>'.
     * @generated
     */
    Decoration createDecoration();

    /**
     * Returns a new object of class '<em>DAnalysis Custom Data</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>DAnalysis Custom Data</em>'.
     * @generated
     */
    DAnalysisCustomData createDAnalysisCustomData();

    /**
     * Returns a new object of class '<em>Label Style</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Label Style</em>'.
     * @generated
     */
    LabelStyle createLabelStyle();

    /**
     * Returns a new object of class '<em>DAnalysis Session EObject</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>DAnalysis Session EObject</em>'.
     * @generated
     */
    DAnalysisSessionEObject createDAnalysisSessionEObject();

    /**
     * Returns a new object of class '<em>Session Manager EObject</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Session Manager EObject</em>'.
     * @generated
     */
    SessionManagerEObject createSessionManagerEObject();

    /**
     * Returns a new object of class '<em>DFile</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>DFile</em>'.
     * @generated
     */
    DFile createDFile();

    /**
     * Returns a new object of class '<em>DResource Container</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>DResource Container</em>'.
     * @generated
     */
    DResourceContainer createDResourceContainer();

    /**
     * Returns a new object of class '<em>DProject</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>DProject</em>'.
     * @generated
     */
    DProject createDProject();

    /**
     * Returns a new object of class '<em>DFolder</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>DFolder</em>'.
     * @generated
     */
    DFolder createDFolder();

    /**
     * Returns a new object of class '<em>DModel</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>DModel</em>'.
     * @generated
     */
    DModel createDModel();

    /**
     * Returns a new object of class '<em>Basic Label Style</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Basic Label Style</em>'.
     * @generated
     */
    BasicLabelStyle createBasicLabelStyle();

    /**
     * Returns a new object of class '<em>UI State</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>UI State</em>'.
     * @generated
     */
    UIState createUIState();

    /**
     * Returns a new object of class '<em>Tool Instance</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Tool Instance</em>'.
     * @generated
     */
    ToolInstance createToolInstance();

    /**
     * Returns a new object of class '<em>Tool Group Instance</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Tool Group Instance</em>'.
     * @generated
     */
    ToolGroupInstance createToolGroupInstance();

    /**
     * Returns a new object of class '<em>Tool Section Instance</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Tool Section Instance</em>'.
     * @generated
     */
    ToolSectionInstance createToolSectionInstance();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the package supported by this factory.
     * @generated
     */
    ViewpointPackage getViewpointPackage();

    RGBValues createRGBValues();

} // ViewpointFactory
