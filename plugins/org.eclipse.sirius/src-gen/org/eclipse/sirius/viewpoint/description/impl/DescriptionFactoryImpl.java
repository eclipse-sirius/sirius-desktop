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
package org.eclipse.sirius.viewpoint.description.impl;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.internal.metamodel.description.spec.AdditionalLayerSpec;
import org.eclipse.sirius.business.internal.metamodel.description.spec.ConditionalContainerStyleDescriptionSpec;
import org.eclipse.sirius.business.internal.metamodel.description.spec.ConditionalEdgeStyleDescriptionSpec;
import org.eclipse.sirius.business.internal.metamodel.description.spec.ConditionalNodeStyleDescriptionSpec;
import org.eclipse.sirius.business.internal.metamodel.description.spec.ContainerMappingImportSpec;
import org.eclipse.sirius.business.internal.metamodel.description.spec.ContainerMappingSpec;
import org.eclipse.sirius.business.internal.metamodel.description.spec.DAnnotationSpec;
import org.eclipse.sirius.business.internal.metamodel.description.spec.DiagramDescriptionSpec;
import org.eclipse.sirius.business.internal.metamodel.description.spec.DiagramImportDescriptionSpec;
import org.eclipse.sirius.business.internal.metamodel.description.spec.EdgeMappingSpec;
import org.eclipse.sirius.business.internal.metamodel.description.spec.LayerSpec;
import org.eclipse.sirius.business.internal.metamodel.description.spec.MetamodelExtensionSettingSpec;
import org.eclipse.sirius.business.internal.metamodel.description.spec.NodeMappingImportSpec;
import org.eclipse.sirius.business.internal.metamodel.description.spec.NodeMappingSpec;
import org.eclipse.sirius.business.internal.metamodel.description.spec.ViewpointSpec;
import org.eclipse.sirius.business.internal.metamodel.spec.FixedColorSpec;
import org.eclipse.sirius.business.internal.metamodel.spec.InterpolatedColorSpec;
import org.eclipse.sirius.tools.api.ui.color.EnvironmentSystemColorFactory;
import org.eclipse.sirius.viewpoint.description.AdditionalLayer;
import org.eclipse.sirius.viewpoint.description.AnnotationEntry;
import org.eclipse.sirius.viewpoint.description.ColorStep;
import org.eclipse.sirius.viewpoint.description.CompositeLayout;
import org.eclipse.sirius.viewpoint.description.ComputedColor;
import org.eclipse.sirius.viewpoint.description.ConditionalContainerStyleDescription;
import org.eclipse.sirius.viewpoint.description.ConditionalEdgeStyleDescription;
import org.eclipse.sirius.viewpoint.description.ConditionalNodeStyleDescription;
import org.eclipse.sirius.viewpoint.description.ContainerMapping;
import org.eclipse.sirius.viewpoint.description.ContainerMappingImport;
import org.eclipse.sirius.viewpoint.description.Customization;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.DAnnotationEntry;
import org.eclipse.sirius.viewpoint.description.DecorationDescriptionsSet;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.DiagramDescription;
import org.eclipse.sirius.viewpoint.description.DiagramExtensionDescription;
import org.eclipse.sirius.viewpoint.description.DiagramImportDescription;
import org.eclipse.sirius.viewpoint.description.EAttributeCustomization;
import org.eclipse.sirius.viewpoint.description.EReferenceCustomization;
import org.eclipse.sirius.viewpoint.description.EdgeMapping;
import org.eclipse.sirius.viewpoint.description.EdgeMappingImport;
import org.eclipse.sirius.viewpoint.description.Environment;
import org.eclipse.sirius.viewpoint.description.FixedColor;
import org.eclipse.sirius.viewpoint.description.FoldingStyle;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.InterpolatedColor;
import org.eclipse.sirius.viewpoint.description.JavaExtension;
import org.eclipse.sirius.viewpoint.description.Layer;
import org.eclipse.sirius.viewpoint.description.LayoutDirection;
import org.eclipse.sirius.viewpoint.description.MappingBasedDecoration;
import org.eclipse.sirius.viewpoint.description.MetamodelExtensionSetting;
import org.eclipse.sirius.viewpoint.description.NavigationTargetType;
import org.eclipse.sirius.viewpoint.description.NodeMapping;
import org.eclipse.sirius.viewpoint.description.NodeMappingImport;
import org.eclipse.sirius.viewpoint.description.OrderedTreeLayout;
import org.eclipse.sirius.viewpoint.description.Position;
import org.eclipse.sirius.viewpoint.description.SemanticBasedDecoration;
import org.eclipse.sirius.viewpoint.description.SystemColor;
import org.eclipse.sirius.viewpoint.description.SystemColors;
import org.eclipse.sirius.viewpoint.description.SytemColorsPalette;
import org.eclipse.sirius.viewpoint.description.UserColorsPalette;
import org.eclipse.sirius.viewpoint.description.UserFixedColor;
import org.eclipse.sirius.viewpoint.description.VSMElementCustomization;
import org.eclipse.sirius.viewpoint.description.VSMElementCustomizationReuse;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

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
        case DescriptionPackage.GROUP:
            return createGroup();
        case DescriptionPackage.VIEWPOINT:
            return createViewpoint();
        case DescriptionPackage.DIAGRAM_DESCRIPTION:
            return createDiagramDescription();
        case DescriptionPackage.DIAGRAM_IMPORT_DESCRIPTION:
            return createDiagramImportDescription();
        case DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION:
            return createDiagramExtensionDescription();
        case DescriptionPackage.METAMODEL_EXTENSION_SETTING:
            return createMetamodelExtensionSetting();
        case DescriptionPackage.JAVA_EXTENSION:
            return createJavaExtension();
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
        case DescriptionPackage.DANNOTATION:
            return createDAnnotation();
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
        case DescriptionPackage.DECORATION_DESCRIPTIONS_SET:
            return createDecorationDescriptionsSet();
        case DescriptionPackage.MAPPING_BASED_DECORATION:
            return createMappingBasedDecoration();
        case DescriptionPackage.SEMANTIC_BASED_DECORATION:
            return createSemanticBasedDecoration();
        case DescriptionPackage.LAYER:
            return createLayer();
        case DescriptionPackage.ADDITIONAL_LAYER:
            return createAdditionalLayer();
        case DescriptionPackage.CUSTOMIZATION:
            return createCustomization();
        case DescriptionPackage.VSM_ELEMENT_CUSTOMIZATION:
            return createVSMElementCustomization();
        case DescriptionPackage.VSM_ELEMENT_CUSTOMIZATION_REUSE:
            return createVSMElementCustomizationReuse();
        case DescriptionPackage.EATTRIBUTE_CUSTOMIZATION:
            return createEAttributeCustomization();
        case DescriptionPackage.EREFERENCE_CUSTOMIZATION:
            return createEReferenceCustomization();
        case DescriptionPackage.SYSTEM_COLOR:
            return createSystemColor();
        case DescriptionPackage.INTERPOLATED_COLOR:
            return createInterpolatedColor();
        case DescriptionPackage.COLOR_STEP:
            return createColorStep();
        case DescriptionPackage.FIXED_COLOR:
            return createFixedColor();
        case DescriptionPackage.USER_FIXED_COLOR:
            return createUserFixedColor();
        case DescriptionPackage.ENVIRONMENT:
            return createEnvironment();
        case DescriptionPackage.SYTEM_COLORS_PALETTE:
            return createSytemColorsPalette();
        case DescriptionPackage.USER_COLORS_PALETTE:
            return createUserColorsPalette();
        case DescriptionPackage.ANNOTATION_ENTRY:
            return createAnnotationEntry();
        case DescriptionPackage.IDENTIFIED_ELEMENT:
            return createIdentifiedElement();
        case DescriptionPackage.COMPUTED_COLOR:
            return createComputedColor();
        case DescriptionPackage.DANNOTATION_ENTRY:
            return createDAnnotationEntry();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
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
        case DescriptionPackage.NAVIGATION_TARGET_TYPE:
            return createNavigationTargetTypeFromString(eDataType, initialValue);
        case DescriptionPackage.FOLDING_STYLE:
            return createFoldingStyleFromString(eDataType, initialValue);
        case DescriptionPackage.LAYOUT_DIRECTION:
            return createLayoutDirectionFromString(eDataType, initialValue);
        case DescriptionPackage.POSITION:
            return createPositionFromString(eDataType, initialValue);
        case DescriptionPackage.SYSTEM_COLORS:
            return createSystemColorsFromString(eDataType, initialValue);
        case DescriptionPackage.TYPE_NAME:
            return createTypeNameFromString(eDataType, initialValue);
        case DescriptionPackage.INTERPRETED_EXPRESSION:
            return createInterpretedExpressionFromString(eDataType, initialValue);
        case DescriptionPackage.FEATURE_NAME:
            return createFeatureNameFromString(eDataType, initialValue);
        case DescriptionPackage.URI:
            return createURIFromString(eDataType, initialValue);
        default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
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
        case DescriptionPackage.NAVIGATION_TARGET_TYPE:
            return convertNavigationTargetTypeToString(eDataType, instanceValue);
        case DescriptionPackage.FOLDING_STYLE:
            return convertFoldingStyleToString(eDataType, instanceValue);
        case DescriptionPackage.LAYOUT_DIRECTION:
            return convertLayoutDirectionToString(eDataType, instanceValue);
        case DescriptionPackage.POSITION:
            return convertPositionToString(eDataType, instanceValue);
        case DescriptionPackage.SYSTEM_COLORS:
            return convertSystemColorsToString(eDataType, instanceValue);
        case DescriptionPackage.TYPE_NAME:
            return convertTypeNameToString(eDataType, instanceValue);
        case DescriptionPackage.INTERPRETED_EXPRESSION:
            return convertInterpretedExpressionToString(eDataType, instanceValue);
        case DescriptionPackage.FEATURE_NAME:
            return convertFeatureNameToString(eDataType, instanceValue);
        case DescriptionPackage.URI:
            return convertURIToString(eDataType, instanceValue);
        default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public Group createGroup() {
        GroupImpl group = new GroupImpl();
        group.setSystemColorsPalette(forgeSystemPaletteProxy());
        return group;
    }

    private SytemColorsPalette forgeSystemPaletteProxy() {
        final EObject environment = EcoreUtil.create(DescriptionPackage.eINSTANCE.getSytemColorsPalette());
        URI paletteURI = URI.createURI("environment:/viewpoint#/0/@systemColors");
        ((InternalEObject) environment).eSetProxyURI(paletteURI);
        return ((SytemColorsPalette) environment);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public Viewpoint createViewpoint() {
        ViewpointImpl viewpoint = new ViewpointSpec();
        return viewpoint;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public DiagramDescription createDiagramDescription() {
        DiagramDescriptionImpl diagramDescription = new DiagramDescriptionSpec();
        return diagramDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public DiagramImportDescription createDiagramImportDescription() {
        DiagramImportDescription diagramImportDescription = new DiagramImportDescriptionSpec();
        return diagramImportDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DiagramExtensionDescription createDiagramExtensionDescription() {
        DiagramExtensionDescriptionImpl diagramExtensionDescription = new DiagramExtensionDescriptionImpl();
        return diagramExtensionDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public MetamodelExtensionSetting createMetamodelExtensionSetting() {
        MetamodelExtensionSettingImpl metamodelExtensionSetting = new MetamodelExtensionSettingSpec();
        return metamodelExtensionSetting;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public JavaExtension createJavaExtension() {
        JavaExtensionImpl javaExtension = new JavaExtensionImpl();
        return javaExtension;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public NodeMapping createNodeMapping() {
        NodeMappingImpl nodeMapping = new NodeMappingSpec();
        return nodeMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public ContainerMapping createContainerMapping() {
        ContainerMappingImpl containerMapping = new ContainerMappingSpec();
        return containerMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public NodeMappingImport createNodeMappingImport() {
        NodeMappingImport nodeMappingImport = new NodeMappingImportSpec();
        return nodeMappingImport;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public ContainerMappingImport createContainerMappingImport() {
        ContainerMappingImport containerMappingImport = new ContainerMappingImportSpec();
        return containerMappingImport;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
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
    public EdgeMappingImport createEdgeMappingImport() {
        EdgeMappingImportImpl edgeMappingImport = new EdgeMappingImportImpl();
        return edgeMappingImport;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated-NOT
     */
    public DAnnotation createDAnnotation() {
        DAnnotationImpl dAnnotation = new DAnnotationSpec();
        return dAnnotation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public ConditionalNodeStyleDescription createConditionalNodeStyleDescription() {
        ConditionalNodeStyleDescriptionImpl conditionalNodeStyleDescription = new ConditionalNodeStyleDescriptionSpec();
        return conditionalNodeStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public ConditionalEdgeStyleDescription createConditionalEdgeStyleDescription() {
        ConditionalEdgeStyleDescriptionImpl conditionalEdgeStyleDescription = new ConditionalEdgeStyleDescriptionSpec();
        return conditionalEdgeStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public ConditionalContainerStyleDescription createConditionalContainerStyleDescription() {
        ConditionalContainerStyleDescriptionImpl conditionalContainerStyleDescription = new ConditionalContainerStyleDescriptionSpec();
        return conditionalContainerStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public OrderedTreeLayout createOrderedTreeLayout() {
        OrderedTreeLayoutImpl orderedTreeLayout = new OrderedTreeLayoutImpl();
        return orderedTreeLayout;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public CompositeLayout createCompositeLayout() {
        CompositeLayoutImpl compositeLayout = new CompositeLayoutImpl();
        return compositeLayout;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DecorationDescriptionsSet createDecorationDescriptionsSet() {
        DecorationDescriptionsSetImpl decorationDescriptionsSet = new DecorationDescriptionsSetImpl();
        return decorationDescriptionsSet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public MappingBasedDecoration createMappingBasedDecoration() {
        MappingBasedDecorationImpl mappingBasedDecoration = new MappingBasedDecorationImpl();
        return mappingBasedDecoration;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public SemanticBasedDecoration createSemanticBasedDecoration() {
        SemanticBasedDecorationImpl semanticBasedDecoration = new SemanticBasedDecorationImpl();
        return semanticBasedDecoration;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public Layer createLayer() {
        LayerImpl layer = new LayerSpec();
        layer.setName("Default");
        return layer;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public AdditionalLayer createAdditionalLayer() {
        AdditionalLayerImpl additionalLayer = new AdditionalLayerSpec();
        return additionalLayer;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Customization createCustomization() {
        CustomizationImpl customization = new CustomizationImpl();
        return customization;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public VSMElementCustomization createVSMElementCustomization() {
        VSMElementCustomizationImpl vsmElementCustomization = new VSMElementCustomizationImpl();
        return vsmElementCustomization;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public VSMElementCustomizationReuse createVSMElementCustomizationReuse() {
        VSMElementCustomizationReuseImpl vsmElementCustomizationReuse = new VSMElementCustomizationReuseImpl();
        return vsmElementCustomizationReuse;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttributeCustomization createEAttributeCustomization() {
        EAttributeCustomizationImpl eAttributeCustomization = new EAttributeCustomizationImpl();
        return eAttributeCustomization;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReferenceCustomization createEReferenceCustomization() {
        EReferenceCustomizationImpl eReferenceCustomization = new EReferenceCustomizationImpl();
        return eReferenceCustomization;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public SystemColor createSystemColor() {
        SystemColorImpl systemColor = new SystemColorImpl();
        return systemColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public InterpolatedColor createInterpolatedColor() {
        InterpolatedColorImpl interpolatedColor = new InterpolatedColorSpec();
        return interpolatedColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public ColorStep createColorStep() {
        ColorStepImpl colorStep = new ColorStepImpl();
        FixedColor defaultColor = EnvironmentSystemColorFactory.getDefault().getSystemColorDescription("black");
        colorStep.setAssociatedColor(defaultColor);
        return colorStep;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public FixedColor createFixedColor() {
        FixedColorImpl fixedColor = new FixedColorSpec();
        return fixedColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public UserFixedColor createUserFixedColor() {
        UserFixedColorImpl userFixedColor = new UserFixedColorImpl();
        return userFixedColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Environment createEnvironment() {
        EnvironmentImpl environment = new EnvironmentImpl();
        return environment;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public SytemColorsPalette createSytemColorsPalette() {
        SytemColorsPaletteImpl sytemColorsPalette = new SytemColorsPaletteImpl();
        return sytemColorsPalette;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public UserColorsPalette createUserColorsPalette() {
        UserColorsPaletteImpl userColorsPalette = new UserColorsPaletteImpl();
        return userColorsPalette;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public AnnotationEntry createAnnotationEntry() {
        AnnotationEntryImpl annotationEntry = new AnnotationEntryImpl();
        return annotationEntry;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public IdentifiedElement createIdentifiedElement() {
        IdentifiedElementImpl identifiedElement = new IdentifiedElementImpl();
        return identifiedElement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ComputedColor createComputedColor() {
        ComputedColorImpl computedColor = new ComputedColorImpl();
        return computedColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DAnnotationEntry createDAnnotationEntry() {
        DAnnotationEntryImpl dAnnotationEntry = new DAnnotationEntryImpl();
        return dAnnotationEntry;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NavigationTargetType createNavigationTargetTypeFromString(EDataType eDataType, String initialValue) {
        NavigationTargetType result = NavigationTargetType.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertNavigationTargetTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public FoldingStyle createFoldingStyleFromString(EDataType eDataType, String initialValue) {
        FoldingStyle result = FoldingStyle.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
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
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
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
    public Position createPositionFromString(EDataType eDataType, String initialValue) {
        Position result = Position.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertPositionToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public SystemColors createSystemColorsFromString(EDataType eDataType, String initialValue) {
        SystemColors result = SystemColors.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertSystemColorsToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String createTypeNameFromString(EDataType eDataType, String initialValue) {
        return (String) super.createFromString(eDataType, initialValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertTypeNameToString(EDataType eDataType, Object instanceValue) {
        return super.convertToString(eDataType, instanceValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String createInterpretedExpressionFromString(EDataType eDataType, String initialValue) {
        return (String) super.createFromString(eDataType, initialValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertInterpretedExpressionToString(EDataType eDataType, Object instanceValue) {
        return super.convertToString(eDataType, instanceValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String createFeatureNameFromString(EDataType eDataType, String initialValue) {
        return (String) super.createFromString(eDataType, initialValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertFeatureNameToString(EDataType eDataType, Object instanceValue) {
        return super.convertToString(eDataType, instanceValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public URI createURIFromString(EDataType eDataType, String initialValue) {
        return URI.createURI(initialValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public String convertURIToString(EDataType eDataType, Object instanceValue) {
        return instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
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
