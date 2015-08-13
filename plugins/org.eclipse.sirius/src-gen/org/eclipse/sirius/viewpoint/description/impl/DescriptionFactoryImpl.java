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
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.internal.metamodel.description.spec.DAnnotationSpec;
import org.eclipse.sirius.business.internal.metamodel.description.spec.MetamodelExtensionSettingSpec;
import org.eclipse.sirius.business.internal.metamodel.description.spec.ViewpointSpec;
import org.eclipse.sirius.business.internal.metamodel.spec.FixedColorSpec;
import org.eclipse.sirius.business.internal.metamodel.spec.InterpolatedColorSpec;
import org.eclipse.sirius.tools.api.ui.color.EnvironmentSystemColorFactory;
import org.eclipse.sirius.viewpoint.description.AnnotationEntry;
import org.eclipse.sirius.viewpoint.description.ColorStep;
import org.eclipse.sirius.viewpoint.description.ComputedColor;
import org.eclipse.sirius.viewpoint.description.Customization;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.DAnnotationEntry;
import org.eclipse.sirius.viewpoint.description.DecorationDescriptionsSet;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.EAttributeCustomization;
import org.eclipse.sirius.viewpoint.description.EReferenceCustomization;
import org.eclipse.sirius.viewpoint.description.Environment;
import org.eclipse.sirius.viewpoint.description.FixedColor;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.InterpolatedColor;
import org.eclipse.sirius.viewpoint.description.JavaExtension;
import org.eclipse.sirius.viewpoint.description.MetamodelExtensionSetting;
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
        case DescriptionPackage.METAMODEL_EXTENSION_SETTING:
            return createMetamodelExtensionSetting();
        case DescriptionPackage.JAVA_EXTENSION:
            return createJavaExtension();
        case DescriptionPackage.DANNOTATION:
            return createDAnnotation();
        case DescriptionPackage.DECORATION_DESCRIPTIONS_SET:
            return createDecorationDescriptionsSet();
        case DescriptionPackage.SEMANTIC_BASED_DECORATION:
            return createSemanticBasedDecoration();
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
        case DescriptionPackage.IMAGE_PATH:
            return createImagePathFromString(eDataType, initialValue);
        case DescriptionPackage.URI:
            return createURIFromString(eDataType, initialValue);
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
        case DescriptionPackage.IMAGE_PATH:
            return convertImagePathToString(eDataType, instanceValue);
        case DescriptionPackage.URI:
            return convertURIToString(eDataType, instanceValue);
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
    public Group createGroup() {
        GroupImpl group = new GroupImpl();
        group.setSystemColorsPalette(forgeSystemPaletteProxy());
        return group;
    }

    private SytemColorsPalette forgeSystemPaletteProxy() {
        final EObject environment = EcoreUtil.create(DescriptionPackage.eINSTANCE.getSytemColorsPalette());
        URI paletteURI = URI.createURI(SiriusUtil.VIEWPOINT_ENVIRONMENT_RESOURCE_URI + "#/0/@systemColors"); //$NON-NLS-1$
        ((InternalEObject) environment).eSetProxyURI(paletteURI);
        return ((SytemColorsPalette) environment);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public Viewpoint createViewpoint() {
        ViewpointImpl viewpoint = new ViewpointSpec();
        return viewpoint;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public MetamodelExtensionSetting createMetamodelExtensionSetting() {
        MetamodelExtensionSettingImpl metamodelExtensionSetting = new MetamodelExtensionSettingSpec();
        return metamodelExtensionSetting;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public JavaExtension createJavaExtension() {
        JavaExtensionImpl javaExtension = new JavaExtensionImpl();
        return javaExtension;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public DAnnotation createDAnnotation() {
        DAnnotationImpl dAnnotation = new DAnnotationSpec();
        return dAnnotation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DecorationDescriptionsSet createDecorationDescriptionsSet() {
        DecorationDescriptionsSetImpl decorationDescriptionsSet = new DecorationDescriptionsSetImpl();
        return decorationDescriptionsSet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public SemanticBasedDecoration createSemanticBasedDecoration() {
        SemanticBasedDecorationImpl semanticBasedDecoration = new SemanticBasedDecorationImpl();
        return semanticBasedDecoration;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Customization createCustomization() {
        CustomizationImpl customization = new CustomizationImpl();
        return customization;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public VSMElementCustomization createVSMElementCustomization() {
        VSMElementCustomizationImpl vsmElementCustomization = new VSMElementCustomizationImpl();
        return vsmElementCustomization;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public VSMElementCustomizationReuse createVSMElementCustomizationReuse() {
        VSMElementCustomizationReuseImpl vsmElementCustomizationReuse = new VSMElementCustomizationReuseImpl();
        return vsmElementCustomizationReuse;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttributeCustomization createEAttributeCustomization() {
        EAttributeCustomizationImpl eAttributeCustomization = new EAttributeCustomizationImpl();
        return eAttributeCustomization;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReferenceCustomization createEReferenceCustomization() {
        EReferenceCustomizationImpl eReferenceCustomization = new EReferenceCustomizationImpl();
        return eReferenceCustomization;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public SystemColor createSystemColor() {
        SystemColorImpl systemColor = new SystemColorImpl();
        return systemColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public InterpolatedColor createInterpolatedColor() {
        InterpolatedColorImpl interpolatedColor = new InterpolatedColorSpec();
        return interpolatedColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public ColorStep createColorStep() {
        ColorStepImpl colorStep = new ColorStepImpl();
        FixedColor defaultColor = EnvironmentSystemColorFactory.getDefault().getSystemColorDescription("black"); //$NON-NLS-1$
        colorStep.setAssociatedColor(defaultColor);
        return colorStep;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public FixedColor createFixedColor() {
        FixedColorImpl fixedColor = new FixedColorSpec();
        return fixedColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public UserFixedColor createUserFixedColor() {
        UserFixedColorImpl userFixedColor = new UserFixedColorImpl();
        return userFixedColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Environment createEnvironment() {
        EnvironmentImpl environment = new EnvironmentImpl();
        return environment;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public SytemColorsPalette createSytemColorsPalette() {
        SytemColorsPaletteImpl sytemColorsPalette = new SytemColorsPaletteImpl();
        return sytemColorsPalette;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public UserColorsPalette createUserColorsPalette() {
        UserColorsPaletteImpl userColorsPalette = new UserColorsPaletteImpl();
        return userColorsPalette;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public AnnotationEntry createAnnotationEntry() {
        AnnotationEntryImpl annotationEntry = new AnnotationEntryImpl();
        return annotationEntry;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public IdentifiedElement createIdentifiedElement() {
        IdentifiedElementImpl identifiedElement = new IdentifiedElementImpl();
        return identifiedElement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ComputedColor createComputedColor() {
        ComputedColorImpl computedColor = new ComputedColorImpl();
        return computedColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DAnnotationEntry createDAnnotationEntry() {
        DAnnotationEntryImpl dAnnotationEntry = new DAnnotationEntryImpl();
        return dAnnotationEntry;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Position createPositionFromString(EDataType eDataType, String initialValue) {
        Position result = Position.get(initialValue);
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
     * @generated
     */
    public String createImagePathFromString(EDataType eDataType, String initialValue) {
        return (String) super.createFromString(eDataType, initialValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertImagePathToString(EDataType eDataType, Object instanceValue) {
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
