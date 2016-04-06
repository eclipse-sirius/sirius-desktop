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
package org.eclipse.sirius.diagram.description.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.spec.AdditionalLayerSpec;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.spec.ConditionalContainerStyleDescriptionSpec;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.spec.ConditionalEdgeStyleDescriptionSpec;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.spec.ConditionalNodeStyleDescriptionSpec;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.spec.ContainerMappingImportSpec;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.spec.ContainerMappingSpec;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.spec.DiagramDescriptionSpec;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.spec.DiagramImportDescriptionSpec;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.spec.EdgeMappingSpec;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.spec.LayerSpec;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.spec.NodeMappingImportSpec;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.spec.NodeMappingSpec;
import org.eclipse.sirius.diagram.description.AdditionalLayer;
import org.eclipse.sirius.diagram.description.CenteringStyle;
import org.eclipse.sirius.diagram.description.CompositeLayout;
import org.eclipse.sirius.diagram.description.ConditionalContainerStyleDescription;
import org.eclipse.sirius.diagram.description.ConditionalEdgeStyleDescription;
import org.eclipse.sirius.diagram.description.ConditionalNodeStyleDescription;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.ContainerMappingImport;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramExtensionDescription;
import org.eclipse.sirius.diagram.description.DiagramImportDescription;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.EdgeMappingImport;
import org.eclipse.sirius.diagram.description.FoldingStyle;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.LayoutDirection;
import org.eclipse.sirius.diagram.description.MappingBasedDecoration;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.NodeMappingImport;
import org.eclipse.sirius.diagram.description.OrderedTreeLayout;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 *
 * @generated
 */
public class DescriptionFactoryImpl extends EFactoryImpl implements DescriptionFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    public static DescriptionFactory init() {
        try {
            DescriptionFactory theDescriptionFactory = (DescriptionFactory) EPackage.Registry.INSTANCE.getEFactory(DescriptionPackage.eNS_URI);
            if (theDescriptionFactory != null) {
                return theDescriptionFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new DescriptionFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    public DescriptionFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
        case DescriptionPackage.DIAGRAM_DESCRIPTION:
            return createDiagramDescription();
        case DescriptionPackage.DIAGRAM_IMPORT_DESCRIPTION:
            return createDiagramImportDescription();
        case DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION:
            return createDiagramExtensionDescription();
        case DescriptionPackage.NODE_MAPPING:
            return createNodeMapping();
        case DescriptionPackage.CONTAINER_MAPPING:
            return createContainerMapping();
        case DescriptionPackage.NODE_MAPPING_IMPORT:
            return createNodeMappingImport();
        case DescriptionPackage.CONTAINER_MAPPING_IMPORT:
            return createContainerMappingImport();
        case DescriptionPackage.EDGE_MAPPING:
            return createEdgeMapping();
        case DescriptionPackage.EDGE_MAPPING_IMPORT:
            return createEdgeMappingImport();
        case DescriptionPackage.CONDITIONAL_NODE_STYLE_DESCRIPTION:
            return createConditionalNodeStyleDescription();
        case DescriptionPackage.CONDITIONAL_EDGE_STYLE_DESCRIPTION:
            return createConditionalEdgeStyleDescription();
        case DescriptionPackage.CONDITIONAL_CONTAINER_STYLE_DESCRIPTION:
            return createConditionalContainerStyleDescription();
        case DescriptionPackage.ORDERED_TREE_LAYOUT:
            return createOrderedTreeLayout();
        case DescriptionPackage.COMPOSITE_LAYOUT:
            return createCompositeLayout();
        case DescriptionPackage.MAPPING_BASED_DECORATION:
            return createMappingBasedDecoration();
        case DescriptionPackage.LAYER:
            return createLayer();
        case DescriptionPackage.ADDITIONAL_LAYER:
            return createAdditionalLayer();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
        case DescriptionPackage.FOLDING_STYLE:
            return createFoldingStyleFromString(eDataType, initialValue);
        case DescriptionPackage.LAYOUT_DIRECTION:
            return createLayoutDirectionFromString(eDataType, initialValue);
        case DescriptionPackage.CENTERING_STYLE:
            return createCenteringStyleFromString(eDataType, initialValue);
        default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
        case DescriptionPackage.FOLDING_STYLE:
            return convertFoldingStyleToString(eDataType, instanceValue);
        case DescriptionPackage.LAYOUT_DIRECTION:
            return convertLayoutDirectionToString(eDataType, instanceValue);
        case DescriptionPackage.CENTERING_STYLE:
            return convertCenteringStyleToString(eDataType, instanceValue);
        default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public DiagramDescription createDiagramDescription() {
        DiagramDescriptionImpl diagramDescription = new DiagramDescriptionSpec();
        return diagramDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public DiagramImportDescription createDiagramImportDescription() {
        DiagramImportDescription diagramImportDescription = new DiagramImportDescriptionSpec();
        return diagramImportDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DiagramExtensionDescription createDiagramExtensionDescription() {
        DiagramExtensionDescriptionImpl diagramExtensionDescription = new DiagramExtensionDescriptionImpl();
        return diagramExtensionDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public NodeMapping createNodeMapping() {
        NodeMappingImpl nodeMapping = new NodeMappingSpec();
        return nodeMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public ContainerMapping createContainerMapping() {
        ContainerMappingImpl containerMapping = new ContainerMappingSpec();
        return containerMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public NodeMappingImport createNodeMappingImport() {
        NodeMappingImport nodeMappingImport = new NodeMappingImportSpec();
        return nodeMappingImport;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public ContainerMappingImport createContainerMappingImport() {
        ContainerMappingImport containerMappingImport = new ContainerMappingImportSpec();
        return containerMappingImport;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public EdgeMapping createEdgeMapping() {
        EdgeMappingImpl edgeMapping = new EdgeMappingSpec();
        edgeMapping.setUseDomainElement(false);
        return edgeMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public EdgeMapping createEdgeMappingUsingDomainElement() {
        EdgeMappingImpl edgeMapping = new EdgeMappingSpec();
        edgeMapping.setUseDomainElement(true);
        return edgeMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EdgeMappingImport createEdgeMappingImport() {
        EdgeMappingImportImpl edgeMappingImport = new EdgeMappingImportImpl();
        return edgeMappingImport;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public ConditionalNodeStyleDescription createConditionalNodeStyleDescription() {
        ConditionalNodeStyleDescriptionImpl conditionalNodeStyleDescription = new ConditionalNodeStyleDescriptionSpec();
        return conditionalNodeStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public ConditionalEdgeStyleDescription createConditionalEdgeStyleDescription() {
        ConditionalEdgeStyleDescriptionImpl conditionalEdgeStyleDescription = new ConditionalEdgeStyleDescriptionSpec();
        return conditionalEdgeStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public ConditionalContainerStyleDescription createConditionalContainerStyleDescription() {
        ConditionalContainerStyleDescriptionImpl conditionalContainerStyleDescription = new ConditionalContainerStyleDescriptionSpec();
        return conditionalContainerStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public OrderedTreeLayout createOrderedTreeLayout() {
        OrderedTreeLayoutImpl orderedTreeLayout = new OrderedTreeLayoutImpl();
        return orderedTreeLayout;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public CompositeLayout createCompositeLayout() {
        CompositeLayoutImpl compositeLayout = new CompositeLayoutImpl();
        return compositeLayout;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public MappingBasedDecoration createMappingBasedDecoration() {
        MappingBasedDecorationImpl mappingBasedDecoration = new MappingBasedDecorationImpl();
        return mappingBasedDecoration;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public Layer createLayer() {
        LayerImpl layer = new LayerSpec();
        return layer;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public AdditionalLayer createAdditionalLayer() {
        AdditionalLayerImpl additionalLayer = new AdditionalLayerSpec();
        return additionalLayer;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public FoldingStyle createFoldingStyleFromString(EDataType eDataType, String initialValue) {
        FoldingStyle result = FoldingStyle.get(initialValue);
        if (result == null) {
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String convertFoldingStyleToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public LayoutDirection createLayoutDirectionFromString(EDataType eDataType, String initialValue) {
        LayoutDirection result = LayoutDirection.get(initialValue);
        if (result == null) {
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String convertLayoutDirectionToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public CenteringStyle createCenteringStyleFromString(EDataType eDataType, String initialValue) {
        CenteringStyle result = CenteringStyle.get(initialValue);
        if (result == null) {
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String convertCenteringStyleToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DescriptionPackage getDescriptionPackage() {
        return (DescriptionPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @deprecated
     * @generated
     */
    @Deprecated
    public static DescriptionPackage getPackage() {
        return DescriptionPackage.eINSTANCE;
    }

} // DescriptionFactoryImpl
