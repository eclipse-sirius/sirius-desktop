/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.viewpoint.description;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 * 
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage
 * @generated
 */
public interface DescriptionFactory extends EFactory {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.\nAll rights reserved. This program and the accompanying materials\nare made available under the terms of the Eclipse Public License v1.0\nwhich accompanies this distribution, and is available at\nhttp://www.eclipse.org/legal/epl-v10.html\n\nContributors:\n   Obeo - initial API and implementation\n";

    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    DescriptionFactory eINSTANCE = org.eclipse.sirius.viewpoint.description.impl.DescriptionFactoryImpl.init();

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
     * Returns a new object of class '<em>Metamodel Extension Setting</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Metamodel Extension Setting</em>'.
     * @generated
     */
    MetamodelExtensionSetting createMetamodelExtensionSetting();

    /**
     * Returns a new object of class '<em>Java Extension</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Java Extension</em>'.
     * @generated
     */
    JavaExtension createJavaExtension();

    /**
     * Returns a new object of class '<em>Node Mapping</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Node Mapping</em>'.
     * @generated
     */
    NodeMapping createNodeMapping();

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
     * Returns a new object of class '<em>Container Mapping</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Container Mapping</em>'.
     * @generated
     */
    ContainerMapping createContainerMapping();

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
     * Returns a new object of class '<em>DAnnotation</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>DAnnotation</em>'.
     * @generated
     */
    DAnnotation createDAnnotation();

    /**
     * Returns a new object of class '<em>Group</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Group</em>'.
     * @generated
     */
    Group createGroup();

    /**
     * Returns a new object of class '<em>Viewpoint</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Viewpoint</em>'.
     * @generated
     */
    Viewpoint createViewpoint();

    /**
     * Returns a new object of class '<em>Conditional Node Style</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Conditional Node Style</em>'.
     * @generated
     */
    ConditionalNodeStyleDescription createConditionalNodeStyleDescription();

    /**
     * Returns a new object of class '<em>Conditional Edge Style</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Conditional Edge Style</em>'.
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
     * Returns a new object of class '<em>Decoration Descriptions Set</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Decoration Descriptions Set</em>'.
     * @generated
     */
    DecorationDescriptionsSet createDecorationDescriptionsSet();

    /**
     * Returns a new object of class '<em>Mapping Based Decoration</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Mapping Based Decoration</em>'.
     * @generated
     */
    MappingBasedDecoration createMappingBasedDecoration();

    /**
     * Returns a new object of class '<em>Semantic Based Decoration</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Semantic Based Decoration</em>'.
     * @generated
     */
    SemanticBasedDecoration createSemanticBasedDecoration();

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
     * Returns a new object of class '<em>Customization</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Customization</em>'.
     * @generated
     */
    Customization createCustomization();

    /**
     * Returns a new object of class '<em>VSM Element Customization</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>VSM Element Customization</em>'.
     * @generated
     */
    VSMElementCustomization createVSMElementCustomization();

    /**
     * Returns a new object of class '<em>VSM Element Customization Reuse</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>VSM Element Customization Reuse</em>'.
     * @generated
     */
    VSMElementCustomizationReuse createVSMElementCustomizationReuse();

    /**
     * Returns a new object of class '<em>EAttribute Customization</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>EAttribute Customization</em>'.
     * @generated
     */
    EAttributeCustomization createEAttributeCustomization();

    /**
     * Returns a new object of class '<em>EReference Customization</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>EReference Customization</em>'.
     * @generated
     */
    EReferenceCustomization createEReferenceCustomization();

    /**
     * Returns a new object of class '<em>System Color</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>System Color</em>'.
     * @generated
     */
    SystemColor createSystemColor();

    /**
     * Returns a new object of class '<em>Interpolated Color</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Interpolated Color</em>'.
     * @generated
     */
    InterpolatedColor createInterpolatedColor();

    /**
     * Returns a new object of class '<em>Color Step</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Color Step</em>'.
     * @generated
     */
    ColorStep createColorStep();

    /**
     * Returns a new object of class '<em>Fixed Color</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Fixed Color</em>'.
     * @generated
     */
    FixedColor createFixedColor();

    /**
     * Returns a new object of class '<em>User Fixed Color</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>User Fixed Color</em>'.
     * @generated
     */
    UserFixedColor createUserFixedColor();

    /**
     * Returns a new object of class '<em>Environment</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Environment</em>'.
     * @generated
     */
    Environment createEnvironment();

    /**
     * Returns a new object of class '<em>Sytem Colors Palette</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Sytem Colors Palette</em>'.
     * @generated
     */
    SytemColorsPalette createSytemColorsPalette();

    /**
     * Returns a new object of class '<em>User Colors Palette</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>User Colors Palette</em>'.
     * @generated
     */
    UserColorsPalette createUserColorsPalette();

    /**
     * Returns a new object of class '<em>Annotation Entry</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Annotation Entry</em>'.
     * @generated
     */
    AnnotationEntry createAnnotationEntry();

    /**
     * Returns a new object of class '<em>Identified Element</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Identified Element</em>'.
     * @generated
     */
    IdentifiedElement createIdentifiedElement();

    /**
     * Returns a new object of class '<em>Computed Color</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Computed Color</em>'.
     * @generated
     */
    ComputedColor createComputedColor();

    /**
     * Returns a new object of class '<em>DAnnotation Entry</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>DAnnotation Entry</em>'.
     * @generated
     */
    DAnnotationEntry createDAnnotationEntry();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the package supported by this factory.
     * @generated
     */
    DescriptionPackage getDescriptionPackage();

} // DescriptionFactory
