/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.diagram.description;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.diagram.description.DescriptionPackage
 * @generated
 */
public interface DescriptionFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    DescriptionFactory eINSTANCE = org.eclipse.sirius.diagram.description.impl.DescriptionFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Diagram Description</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Diagram Description</em>'.
     * @generated
     */
    DiagramDescription createDiagramDescription();

    /**
     * Returns a new object of class '<em>Diagram Import Description</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Diagram Import Description</em>'.
     * @generated
     */
    DiagramImportDescription createDiagramImportDescription();

    /**
     * Returns a new object of class '<em>Diagram Extension Description</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Diagram Extension Description</em>'.
     * @generated
     */
    DiagramExtensionDescription createDiagramExtensionDescription();

    /**
     * Returns a new object of class '<em>Node Mapping</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Node Mapping</em>'.
     * @generated
     */
    NodeMapping createNodeMapping();

    /**
     * Returns a new object of class '<em>Container Mapping</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Container Mapping</em>'.
     * @generated
     */
    ContainerMapping createContainerMapping();

    /**
     * Returns a new object of class '<em>Node Mapping Import</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Node Mapping Import</em>'.
     * @generated
     */
    NodeMappingImport createNodeMappingImport();

    /**
     * Returns a new object of class '<em>Container Mapping Import</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Container Mapping Import</em>'.
     * @generated
     */
    ContainerMappingImport createContainerMappingImport();

    /**
     * Returns a new object of class '<em>Edge Mapping</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Edge Mapping</em>'.
     * @generated
     */
    EdgeMapping createEdgeMapping();

    /**
     * Returns a new object of class '<em>Edge Mapping</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Edge Mapping</em>'.
     * @not-generated
     */
    EdgeMapping createEdgeMappingUsingDomainElement();

    /**
     * Returns a new object of class '<em>Edge Mapping Import</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Edge Mapping Import</em>'.
     * @generated
     */
    EdgeMappingImport createEdgeMappingImport();

    /**
     * Returns a new object of class '
     * <em>Conditional Node Style Description</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '
     *         <em>Conditional Node Style Description</em>'.
     * @generated
     */
    ConditionalNodeStyleDescription createConditionalNodeStyleDescription();

    /**
     * Returns a new object of class '
     * <em>Conditional Edge Style Description</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '
     *         <em>Conditional Edge Style Description</em>'.
     * @generated
     */
    ConditionalEdgeStyleDescription createConditionalEdgeStyleDescription();

    /**
     * Returns a new object of class '
     * <em>Conditional Container Style Description</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return a new object of class '
     *         <em>Conditional Container Style Description</em>'.
     * @generated
     */
    ConditionalContainerStyleDescription createConditionalContainerStyleDescription();

    /**
     * Returns a new object of class '<em>Ordered Tree Layout</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Ordered Tree Layout</em>'.
     * @generated
     */
    OrderedTreeLayout createOrderedTreeLayout();

    /**
     * Returns a new object of class '<em>Composite Layout</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Composite Layout</em>'.
     * @generated
     */
    CompositeLayout createCompositeLayout();

    /**
     * Returns a new object of class '<em>Mapping Based Decoration</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Mapping Based Decoration</em>'.
     * @generated
     */
    MappingBasedDecoration createMappingBasedDecoration();

    /**
     * Returns a new object of class '<em>Layer</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Layer</em>'.
     * @generated
     */
    Layer createLayer();

    /**
     * Returns a new object of class '<em>Additional Layer</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Additional Layer</em>'.
     * @generated
     */
    AdditionalLayer createAdditionalLayer();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the package supported by this factory.
     * @generated
     */
    DescriptionPackage getDescriptionPackage();

} // DescriptionFactory
