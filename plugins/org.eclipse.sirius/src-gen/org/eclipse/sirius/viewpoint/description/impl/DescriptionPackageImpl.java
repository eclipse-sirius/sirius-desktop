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
package org.eclipse.sirius.viewpoint.description.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.AbstractMappingImport;
import org.eclipse.sirius.viewpoint.description.AbstractNodeMapping;
import org.eclipse.sirius.viewpoint.description.AdditionalLayer;
import org.eclipse.sirius.viewpoint.description.AnnotationEntry;
import org.eclipse.sirius.viewpoint.description.ColorDescription;
import org.eclipse.sirius.viewpoint.description.ColorStep;
import org.eclipse.sirius.viewpoint.description.Component;
import org.eclipse.sirius.viewpoint.description.CompositeLayout;
import org.eclipse.sirius.viewpoint.description.ComputedColor;
import org.eclipse.sirius.viewpoint.description.ConditionalContainerStyleDescription;
import org.eclipse.sirius.viewpoint.description.ConditionalEdgeStyleDescription;
import org.eclipse.sirius.viewpoint.description.ConditionalNodeStyleDescription;
import org.eclipse.sirius.viewpoint.description.ConditionalStyleDescription;
import org.eclipse.sirius.viewpoint.description.ContainerMapping;
import org.eclipse.sirius.viewpoint.description.ContainerMappingImport;
import org.eclipse.sirius.viewpoint.description.Customization;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.DAnnotationEntry;
import org.eclipse.sirius.viewpoint.description.DModelElement;
import org.eclipse.sirius.viewpoint.description.DecorationDescription;
import org.eclipse.sirius.viewpoint.description.DecorationDescriptionsSet;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.DiagramDescription;
import org.eclipse.sirius.viewpoint.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.description.DiagramExtensionDescription;
import org.eclipse.sirius.viewpoint.description.DiagramImportDescription;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.DragAndDropTargetDescription;
import org.eclipse.sirius.viewpoint.description.EAttributeCustomization;
import org.eclipse.sirius.viewpoint.description.EReferenceCustomization;
import org.eclipse.sirius.viewpoint.description.EStructuralFeatureCustomization;
import org.eclipse.sirius.viewpoint.description.EdgeMapping;
import org.eclipse.sirius.viewpoint.description.EdgeMappingImport;
import org.eclipse.sirius.viewpoint.description.EndUserDocumentedElement;
import org.eclipse.sirius.viewpoint.description.Environment;
import org.eclipse.sirius.viewpoint.description.FeatureExtensionDescription;
import org.eclipse.sirius.viewpoint.description.FixedColor;
import org.eclipse.sirius.viewpoint.description.FoldingStyle;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.IEdgeMapping;
import org.eclipse.sirius.viewpoint.description.IVSMElementCustomization;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.InterpolatedColor;
import org.eclipse.sirius.viewpoint.description.JavaExtension;
import org.eclipse.sirius.viewpoint.description.Layer;
import org.eclipse.sirius.viewpoint.description.Layout;
import org.eclipse.sirius.viewpoint.description.LayoutDirection;
import org.eclipse.sirius.viewpoint.description.MappingBasedDecoration;
import org.eclipse.sirius.viewpoint.description.MetamodelExtensionSetting;
import org.eclipse.sirius.viewpoint.description.NavigationTargetType;
import org.eclipse.sirius.viewpoint.description.NodeMapping;
import org.eclipse.sirius.viewpoint.description.NodeMappingImport;
import org.eclipse.sirius.viewpoint.description.OrderedTreeLayout;
import org.eclipse.sirius.viewpoint.description.PasteTargetDescription;
import org.eclipse.sirius.viewpoint.description.Position;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationImportDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationTemplate;
import org.eclipse.sirius.viewpoint.description.SelectionDescription;
import org.eclipse.sirius.viewpoint.description.SemanticBasedDecoration;
import org.eclipse.sirius.viewpoint.description.SystemColor;
import org.eclipse.sirius.viewpoint.description.SystemColors;
import org.eclipse.sirius.viewpoint.description.SytemColorsPalette;
import org.eclipse.sirius.viewpoint.description.UserColor;
import org.eclipse.sirius.viewpoint.description.UserColorsPalette;
import org.eclipse.sirius.viewpoint.description.UserFixedColor;
import org.eclipse.sirius.viewpoint.description.VSMElementCustomization;
import org.eclipse.sirius.viewpoint.description.VSMElementCustomizationReuse;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.audit.AuditPackage;
import org.eclipse.sirius.viewpoint.description.audit.impl.AuditPackageImpl;
import org.eclipse.sirius.viewpoint.description.concern.ConcernPackage;
import org.eclipse.sirius.viewpoint.description.concern.impl.ConcernPackageImpl;
import org.eclipse.sirius.viewpoint.description.filter.FilterPackage;
import org.eclipse.sirius.viewpoint.description.filter.impl.FilterPackageImpl;
import org.eclipse.sirius.viewpoint.description.style.StylePackage;
import org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl;
import org.eclipse.sirius.viewpoint.description.validation.ValidationPackage;
import org.eclipse.sirius.viewpoint.description.validation.impl.ValidationPackageImpl;
import org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class DescriptionPackageImpl extends EPackageImpl implements DescriptionPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass groupEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass componentEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass viewpointEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass featureExtensionDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass representationDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass representationTemplateEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass representationImportDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass representationExtensionDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass diagramDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass diagramImportDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass diagramExtensionDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass metamodelExtensionSettingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass javaExtensionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass representationElementMappingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass diagramElementMappingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass abstractNodeMappingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass nodeMappingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass containerMappingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass abstractMappingImportEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass nodeMappingImportEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass containerMappingImportEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass edgeMappingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass iEdgeMappingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass edgeMappingImportEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass documentedElementEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass dModelElementEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass dAnnotationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass conditionalStyleDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass conditionalNodeStyleDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass conditionalEdgeStyleDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass conditionalContainerStyleDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass dragAndDropTargetDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass pasteTargetDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass layoutEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass orderedTreeLayoutEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass compositeLayoutEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass decorationDescriptionsSetEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass decorationDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass mappingBasedDecorationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass semanticBasedDecorationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass layerEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass additionalLayerEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass customizationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass ivsmElementCustomizationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass vsmElementCustomizationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass vsmElementCustomizationReuseEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass eStructuralFeatureCustomizationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass eAttributeCustomizationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass eReferenceCustomizationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass selectionDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass colorDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass systemColorEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass interpolatedColorEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass colorStepEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass fixedColorEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass userFixedColorEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass userColorEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass environmentEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass sytemColorsPaletteEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass userColorsPaletteEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass annotationEntryEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass endUserDocumentedElementEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass identifiedElementEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass computedColorEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass dAnnotationEntryEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum navigationTargetTypeEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum foldingStyleEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum layoutDirectionEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum positionEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum systemColorsEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EDataType typeNameEDataType = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EDataType interpretedExpressionEDataType = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EDataType featureNameEDataType = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EDataType uriEDataType = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the
     * package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory
     * method {@link #init init()}, which also performs initialization of the
     * package, or returns the registered package, if one already exists. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private DescriptionPackageImpl() {
        super(eNS_URI, DescriptionFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model,
     * and for any others upon which it depends.
     * 
     * <p>
     * This method is used to initialize {@link DescriptionPackage#eINSTANCE}
     * when that field is accessed. Clients should not invoke it directly.
     * Instead, they should simply access that field to obtain the package. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static DescriptionPackage init() {
        if (isInited)
            return (DescriptionPackage) EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI);

        // Obtain or create and register package
        DescriptionPackageImpl theDescriptionPackage = (DescriptionPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof DescriptionPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
                : new DescriptionPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        EcorePackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        ViewpointPackageImpl theViewpointPackage = (ViewpointPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(ViewpointPackage.eNS_URI) instanceof ViewpointPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(ViewpointPackage.eNS_URI) : ViewpointPackage.eINSTANCE);
        StylePackageImpl theStylePackage = (StylePackageImpl) (EPackage.Registry.INSTANCE.getEPackage(StylePackage.eNS_URI) instanceof StylePackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(StylePackage.eNS_URI) : StylePackage.eINSTANCE);
        ToolPackageImpl theToolPackage = (ToolPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(ToolPackage.eNS_URI) instanceof ToolPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(ToolPackage.eNS_URI) : ToolPackage.eINSTANCE);
        FilterPackageImpl theFilterPackage = (FilterPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(FilterPackage.eNS_URI) instanceof FilterPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(FilterPackage.eNS_URI) : FilterPackage.eINSTANCE);
        ValidationPackageImpl theValidationPackage = (ValidationPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(ValidationPackage.eNS_URI) instanceof ValidationPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(ValidationPackage.eNS_URI) : ValidationPackage.eINSTANCE);
        AuditPackageImpl theAuditPackage = (AuditPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(AuditPackage.eNS_URI) instanceof AuditPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(AuditPackage.eNS_URI) : AuditPackage.eINSTANCE);
        ConcernPackageImpl theConcernPackage = (ConcernPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(ConcernPackage.eNS_URI) instanceof ConcernPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(ConcernPackage.eNS_URI) : ConcernPackage.eINSTANCE);

        // Create package meta-data objects
        theDescriptionPackage.createPackageContents();
        theViewpointPackage.createPackageContents();
        theStylePackage.createPackageContents();
        theToolPackage.createPackageContents();
        theFilterPackage.createPackageContents();
        theValidationPackage.createPackageContents();
        theAuditPackage.createPackageContents();
        theConcernPackage.createPackageContents();

        // Initialize created meta-data
        theDescriptionPackage.initializePackageContents();
        theViewpointPackage.initializePackageContents();
        theStylePackage.initializePackageContents();
        theToolPackage.initializePackageContents();
        theFilterPackage.initializePackageContents();
        theValidationPackage.initializePackageContents();
        theAuditPackage.initializePackageContents();
        theConcernPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theDescriptionPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(DescriptionPackage.eNS_URI, theDescriptionPackage);
        return theDescriptionPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getGroup() {
        return groupEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getGroup_Name() {
        return (EAttribute) groupEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getGroup_OwnedViewpoints() {
        return (EReference) groupEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getGroup_SystemColorsPalette() {
        return (EReference) groupEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getGroup_UserColorsPalettes() {
        return (EReference) groupEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getGroup_Version() {
        return (EAttribute) groupEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getComponent() {
        return componentEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getViewpoint() {
        return viewpointEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getViewpoint_ModelFileExtension() {
        return (EAttribute) viewpointEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getViewpoint_ValidationSet() {
        return (EReference) viewpointEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getViewpoint_OwnedRepresentations() {
        return (EReference) viewpointEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getViewpoint_OwnedRepresentationExtensions() {
        return (EReference) viewpointEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getViewpoint_OwnedJavaExtensions() {
        return (EReference) viewpointEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getViewpoint_OwnedMMExtensions() {
        return (EReference) viewpointEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getViewpoint_OwnedFeatureExtensions() {
        return (EReference) viewpointEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getViewpoint_Icon() {
        return (EAttribute) viewpointEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getViewpoint_OwnedTemplates() {
        return (EReference) viewpointEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getViewpoint_Conflicts() {
        return (EAttribute) viewpointEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getViewpoint_Reuses() {
        return (EAttribute) viewpointEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getViewpoint_Customizes() {
        return (EAttribute) viewpointEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getFeatureExtensionDescription() {
        return featureExtensionDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getRepresentationDescription() {
        return representationDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getRepresentationDescription_TitleExpression() {
        return (EAttribute) representationDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getRepresentationDescription_Initialisation() {
        return (EAttribute) representationDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getRepresentationDescription_Metamodel() {
        return (EReference) representationDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getRepresentationDescription_ShowOnStartup() {
        return (EAttribute) representationDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getRepresentationTemplate() {
        return representationTemplateEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getRepresentationTemplate_Name() {
        return (EAttribute) representationTemplateEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getRepresentationTemplate_OwnedRepresentations() {
        return (EReference) representationTemplateEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getRepresentationImportDescription() {
        return representationImportDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getRepresentationExtensionDescription() {
        return representationExtensionDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getRepresentationExtensionDescription_Name() {
        return (EAttribute) representationExtensionDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getRepresentationExtensionDescription_ViewpointURI() {
        return (EAttribute) representationExtensionDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getRepresentationExtensionDescription_RepresentationName() {
        return (EAttribute) representationExtensionDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getRepresentationExtensionDescription_Metamodel() {
        return (EReference) representationExtensionDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDiagramDescription() {
        return diagramDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDiagramDescription_Filters() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDiagramDescription_AllEdgeMappings() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDiagramDescription_AllActivatedEdgeMappings() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDiagramDescription_AllNodeMappings() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDiagramDescription_AllContainerMappings() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDiagramDescription_ValidationSet() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDiagramDescription_Concerns() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDiagramDescription_InformationSections() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDiagramDescription_AllTools() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDiagramDescription_DomainClass() {
        return (EAttribute) diagramDescriptionEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDiagramDescription_PreconditionExpression() {
        return (EAttribute) diagramDescriptionEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDiagramDescription_DefaultConcern() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDiagramDescription_RootExpression() {
        return (EAttribute) diagramDescriptionEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDiagramDescription_Init() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDiagramDescription_Layout() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(14);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDiagramDescription_DiagramInitialisation() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(15);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDiagramDescription_DefaultLayer() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(16);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDiagramDescription_AdditionalLayers() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(17);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDiagramDescription_AllLayers() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(18);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDiagramDescription_AllActivatedTools() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(19);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDiagramDescription_NodeMappings() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(20);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDiagramDescription_EdgeMappings() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(21);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDiagramDescription_EdgeMappingImports() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(22);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDiagramDescription_ContainerMappings() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(23);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDiagramDescription_ReusedMappings() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(24);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDiagramDescription_ToolSection() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(25);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDiagramDescription_ReusedTools() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(26);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDiagramDescription_EnablePopupBars() {
        return (EAttribute) diagramDescriptionEClass.getEStructuralFeatures().get(27);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDiagramImportDescription() {
        return diagramImportDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDiagramImportDescription_ImportedDiagram() {
        return (EReference) diagramImportDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDiagramExtensionDescription() {
        return diagramExtensionDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDiagramExtensionDescription_Layers() {
        return (EReference) diagramExtensionDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDiagramExtensionDescription_ValidationSet() {
        return (EReference) diagramExtensionDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDiagramExtensionDescription_Concerns() {
        return (EReference) diagramExtensionDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getMetamodelExtensionSetting() {
        return metamodelExtensionSettingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getMetamodelExtensionSetting_ExtensionGroup() {
        return (EReference) metamodelExtensionSettingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getJavaExtension() {
        return javaExtensionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getJavaExtension_QualifiedClassName() {
        return (EAttribute) javaExtensionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getRepresentationElementMapping() {
        return representationElementMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getRepresentationElementMapping_DetailDescriptions() {
        return (EReference) representationElementMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getRepresentationElementMapping_NavigationDescriptions() {
        return (EReference) representationElementMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDiagramElementMapping() {
        return diagramElementMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDiagramElementMapping_PreconditionExpression() {
        return (EAttribute) diagramElementMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDiagramElementMapping_DeletionDescription() {
        return (EReference) diagramElementMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDiagramElementMapping_LabelDirectEdit() {
        return (EReference) diagramElementMappingEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDiagramElementMapping_SemanticCandidatesExpression() {
        return (EAttribute) diagramElementMappingEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDiagramElementMapping_CreateElements() {
        return (EAttribute) diagramElementMappingEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDiagramElementMapping_SemanticElements() {
        return (EAttribute) diagramElementMappingEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDiagramElementMapping_DoubleClickDescription() {
        return (EReference) diagramElementMappingEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDiagramElementMapping_SynchronizationLock() {
        return (EAttribute) diagramElementMappingEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getAbstractNodeMapping() {
        return abstractNodeMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getAbstractNodeMapping_DomainClass() {
        return (EAttribute) abstractNodeMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getAbstractNodeMapping_BorderedNodeMappings() {
        return (EReference) abstractNodeMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getAbstractNodeMapping_ReusedBorderedNodeMappings() {
        return (EReference) abstractNodeMappingEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getNodeMapping() {
        return nodeMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getNodeMapping_Style() {
        return (EReference) nodeMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getNodeMapping_ConditionnalStyles() {
        return (EReference) nodeMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getContainerMapping() {
        return containerMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getContainerMapping_SubNodeMappings() {
        return (EReference) containerMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getContainerMapping_AllNodeMappings() {
        return (EReference) containerMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getContainerMapping_ReusedNodeMappings() {
        return (EReference) containerMappingEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getContainerMapping_SubContainerMappings() {
        return (EReference) containerMappingEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getContainerMapping_ReusedContainerMappings() {
        return (EReference) containerMappingEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getContainerMapping_AllContainerMappings() {
        return (EReference) containerMappingEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getContainerMapping_Style() {
        return (EReference) containerMappingEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getContainerMapping_ConditionnalStyles() {
        return (EReference) containerMappingEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getContainerMapping_ChildrenPresentation() {
        return (EAttribute) containerMappingEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getAbstractMappingImport() {
        return abstractMappingImportEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getAbstractMappingImport_HideSubMappings() {
        return (EAttribute) abstractMappingImportEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getAbstractMappingImport_InheritsAncestorFilters() {
        return (EAttribute) abstractMappingImportEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getNodeMappingImport() {
        return nodeMappingImportEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getNodeMappingImport_ImportedMapping() {
        return (EReference) nodeMappingImportEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getContainerMappingImport() {
        return containerMappingImportEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getContainerMappingImport_ImportedMapping() {
        return (EReference) containerMappingImportEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getEdgeMapping() {
        return edgeMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEdgeMapping_SourceMapping() {
        return (EReference) edgeMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEdgeMapping_TargetMapping() {
        return (EReference) edgeMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getEdgeMapping_TargetFinderExpression() {
        return (EAttribute) edgeMappingEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getEdgeMapping_SourceFinderExpression() {
        return (EAttribute) edgeMappingEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEdgeMapping_Style() {
        return (EReference) edgeMappingEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEdgeMapping_ConditionnalStyles() {
        return (EReference) edgeMappingEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getEdgeMapping_TargetExpression() {
        return (EAttribute) edgeMappingEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getEdgeMapping_DomainClass() {
        return (EAttribute) edgeMappingEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getEdgeMapping_UseDomainElement() {
        return (EAttribute) edgeMappingEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEdgeMapping_Reconnections() {
        return (EReference) edgeMappingEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getEdgeMapping_PathExpression() {
        return (EAttribute) edgeMappingEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEdgeMapping_PathNodeMapping() {
        return (EReference) edgeMappingEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getIEdgeMapping() {
        return iEdgeMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getEdgeMappingImport() {
        return edgeMappingImportEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEdgeMappingImport_ImportedMapping() {
        return (EReference) edgeMappingImportEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEdgeMappingImport_ConditionnalStyles() {
        return (EReference) edgeMappingImportEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getEdgeMappingImport_InheritsAncestorFilters() {
        return (EAttribute) edgeMappingImportEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDocumentedElement() {
        return documentedElementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDocumentedElement_Documentation() {
        return (EAttribute) documentedElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDModelElement() {
        return dModelElementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDModelElement_EAnnotations() {
        return (EReference) dModelElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDAnnotation() {
        return dAnnotationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDAnnotation_Source() {
        return (EAttribute) dAnnotationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDAnnotation_Details() {
        return (EReference) dAnnotationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getConditionalStyleDescription() {
        return conditionalStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getConditionalStyleDescription_PredicateExpression() {
        return (EAttribute) conditionalStyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getConditionalNodeStyleDescription() {
        return conditionalNodeStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getConditionalNodeStyleDescription_Style() {
        return (EReference) conditionalNodeStyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getConditionalEdgeStyleDescription() {
        return conditionalEdgeStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getConditionalEdgeStyleDescription_Style() {
        return (EReference) conditionalEdgeStyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getConditionalContainerStyleDescription() {
        return conditionalContainerStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getConditionalContainerStyleDescription_Style() {
        return (EReference) conditionalContainerStyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDragAndDropTargetDescription() {
        return dragAndDropTargetDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDragAndDropTargetDescription_DropDescriptions() {
        return (EReference) dragAndDropTargetDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getPasteTargetDescription() {
        return pasteTargetDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getPasteTargetDescription_PasteDescriptions() {
        return (EReference) pasteTargetDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getLayout() {
        return layoutEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getOrderedTreeLayout() {
        return orderedTreeLayoutEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getOrderedTreeLayout_ChildrenExpression() {
        return (EAttribute) orderedTreeLayoutEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getOrderedTreeLayout_NodeMapping() {
        return (EReference) orderedTreeLayoutEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getCompositeLayout() {
        return compositeLayoutEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getCompositeLayout_Padding() {
        return (EAttribute) compositeLayoutEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getCompositeLayout_Direction() {
        return (EAttribute) compositeLayoutEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDecorationDescriptionsSet() {
        return decorationDescriptionsSetEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDecorationDescriptionsSet_DecorationDescriptions() {
        return (EReference) decorationDescriptionsSetEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDecorationDescription() {
        return decorationDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDecorationDescription_Name() {
        return (EAttribute) decorationDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDecorationDescription_Position() {
        return (EAttribute) decorationDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDecorationDescription_DecoratorPath() {
        return (EAttribute) decorationDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDecorationDescription_PreconditionExpression() {
        return (EAttribute) decorationDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getMappingBasedDecoration() {
        return mappingBasedDecorationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getMappingBasedDecoration_Mappings() {
        return (EReference) mappingBasedDecorationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getSemanticBasedDecoration() {
        return semanticBasedDecorationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getSemanticBasedDecoration_DomainClass() {
        return (EAttribute) semanticBasedDecorationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getLayer() {
        return layerEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getLayer_NodeMappings() {
        return (EReference) layerEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getLayer_EdgeMappings() {
        return (EReference) layerEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getLayer_EdgeMappingImports() {
        return (EReference) layerEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getLayer_ContainerMappings() {
        return (EReference) layerEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getLayer_ReusedMappings() {
        return (EReference) layerEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getLayer_AllTools() {
        return (EReference) layerEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getLayer_ToolSections() {
        return (EReference) layerEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getLayer_ReusedTools() {
        return (EReference) layerEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getLayer_DecorationDescriptionsSet() {
        return (EReference) layerEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getLayer_Icon() {
        return (EAttribute) layerEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getLayer_AllEdgeMappings() {
        return (EReference) layerEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getLayer_AllActivatedEdgeMappings() {
        return (EReference) layerEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getLayer_Customization() {
        return (EReference) layerEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getAdditionalLayer() {
        return additionalLayerEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getAdditionalLayer_ActiveByDefault() {
        return (EAttribute) additionalLayerEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getAdditionalLayer_Optional() {
        return (EAttribute) additionalLayerEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getCustomization() {
        return customizationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getCustomization_VsmElementCustomizations() {
        return (EReference) customizationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getIVSMElementCustomization() {
        return ivsmElementCustomizationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getVSMElementCustomization() {
        return vsmElementCustomizationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getVSMElementCustomization_PredicateExpression() {
        return (EAttribute) vsmElementCustomizationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getVSMElementCustomization_FeatureCustomizations() {
        return (EReference) vsmElementCustomizationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getVSMElementCustomizationReuse() {
        return vsmElementCustomizationReuseEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getVSMElementCustomizationReuse_Reuse() {
        return (EReference) vsmElementCustomizationReuseEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getVSMElementCustomizationReuse_AppliedOn() {
        return (EReference) vsmElementCustomizationReuseEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getEStructuralFeatureCustomization() {
        return eStructuralFeatureCustomizationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEStructuralFeatureCustomization_AppliedOn() {
        return (EReference) eStructuralFeatureCustomizationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getEStructuralFeatureCustomization_ApplyOnAll() {
        return (EAttribute) eStructuralFeatureCustomizationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getEAttributeCustomization() {
        return eAttributeCustomizationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getEAttributeCustomization_AttributeName() {
        return (EAttribute) eAttributeCustomizationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getEAttributeCustomization_Value() {
        return (EAttribute) eAttributeCustomizationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getEReferenceCustomization() {
        return eReferenceCustomizationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getEReferenceCustomization_ReferenceName() {
        return (EAttribute) eReferenceCustomizationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEReferenceCustomization_Value() {
        return (EReference) eReferenceCustomizationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getSelectionDescription() {
        return selectionDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getSelectionDescription_CandidatesExpression() {
        return (EAttribute) selectionDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getSelectionDescription_Multiple() {
        return (EAttribute) selectionDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getSelectionDescription_Tree() {
        return (EAttribute) selectionDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getSelectionDescription_RootExpression() {
        return (EAttribute) selectionDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getSelectionDescription_ChildrenExpression() {
        return (EAttribute) selectionDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getSelectionDescription_Message() {
        return (EAttribute) selectionDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getColorDescription() {
        return colorDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getSystemColor() {
        return systemColorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getSystemColor_Name() {
        return (EAttribute) systemColorEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getInterpolatedColor() {
        return interpolatedColorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getInterpolatedColor_ColorValueComputationExpression() {
        return (EAttribute) interpolatedColorEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getInterpolatedColor_MinValueComputationExpression() {
        return (EAttribute) interpolatedColorEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getInterpolatedColor_MaxValueComputationExpression() {
        return (EAttribute) interpolatedColorEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getInterpolatedColor_ColorSteps() {
        return (EReference) interpolatedColorEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getColorStep() {
        return colorStepEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getColorStep_AssociatedValue() {
        return (EAttribute) colorStepEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getColorStep_AssociatedColor() {
        return (EReference) colorStepEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getFixedColor() {
        return fixedColorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getFixedColor_Red() {
        return (EAttribute) fixedColorEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getFixedColor_Green() {
        return (EAttribute) fixedColorEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getFixedColor_Blue() {
        return (EAttribute) fixedColorEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getUserFixedColor() {
        return userFixedColorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getUserColor() {
        return userColorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getUserColor_Name() {
        return (EAttribute) userColorEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getEnvironment() {
        return environmentEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEnvironment_SystemColors() {
        return (EReference) environmentEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEnvironment_DefaultTools() {
        return (EReference) environmentEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEnvironment_LabelBorderStyles() {
        return (EReference) environmentEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getSytemColorsPalette() {
        return sytemColorsPaletteEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getSytemColorsPalette_Entries() {
        return (EReference) sytemColorsPaletteEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getUserColorsPalette() {
        return userColorsPaletteEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getUserColorsPalette_Name() {
        return (EAttribute) userColorsPaletteEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getUserColorsPalette_Entries() {
        return (EReference) userColorsPaletteEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getAnnotationEntry() {
        return annotationEntryEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getAnnotationEntry_Source() {
        return (EAttribute) annotationEntryEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getAnnotationEntry_Data() {
        return (EReference) annotationEntryEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getEndUserDocumentedElement() {
        return endUserDocumentedElementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getEndUserDocumentedElement_EndUserDocumentation() {
        return (EAttribute) endUserDocumentedElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getIdentifiedElement() {
        return identifiedElementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getIdentifiedElement_Name() {
        return (EAttribute) identifiedElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getIdentifiedElement_Label() {
        return (EAttribute) identifiedElementEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getComputedColor() {
        return computedColorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getComputedColor_Red() {
        return (EAttribute) computedColorEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getComputedColor_Green() {
        return (EAttribute) computedColorEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getComputedColor_Blue() {
        return (EAttribute) computedColorEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDAnnotationEntry() {
        return dAnnotationEntryEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDAnnotationEntry_Source() {
        return (EAttribute) dAnnotationEntryEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDAnnotationEntry_Details() {
        return (EAttribute) dAnnotationEntryEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getNavigationTargetType() {
        return navigationTargetTypeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getFoldingStyle() {
        return foldingStyleEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getLayoutDirection() {
        return layoutDirectionEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getPosition() {
        return positionEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getSystemColors() {
        return systemColorsEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EDataType getTypeName() {
        return typeNameEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EDataType getInterpretedExpression() {
        return interpretedExpressionEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EDataType getFeatureName() {
        return featureNameEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EDataType getURI() {
        return uriEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DescriptionFactory getDescriptionFactory() {
        return (DescriptionFactory) getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package. This method is guarded to
     * have no affect on any invocation but its first. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void createPackageContents() {
        if (isCreated)
            return;
        isCreated = true;

        // Create classes and their features
        groupEClass = createEClass(GROUP);
        createEAttribute(groupEClass, GROUP__NAME);
        createEReference(groupEClass, GROUP__OWNED_VIEWPOINTS);
        createEReference(groupEClass, GROUP__SYSTEM_COLORS_PALETTE);
        createEReference(groupEClass, GROUP__USER_COLORS_PALETTES);
        createEAttribute(groupEClass, GROUP__VERSION);

        componentEClass = createEClass(COMPONENT);

        viewpointEClass = createEClass(VIEWPOINT);
        createEAttribute(viewpointEClass, VIEWPOINT__MODEL_FILE_EXTENSION);
        createEReference(viewpointEClass, VIEWPOINT__VALIDATION_SET);
        createEReference(viewpointEClass, VIEWPOINT__OWNED_REPRESENTATIONS);
        createEReference(viewpointEClass, VIEWPOINT__OWNED_REPRESENTATION_EXTENSIONS);
        createEReference(viewpointEClass, VIEWPOINT__OWNED_JAVA_EXTENSIONS);
        createEReference(viewpointEClass, VIEWPOINT__OWNED_MM_EXTENSIONS);
        createEReference(viewpointEClass, VIEWPOINT__OWNED_FEATURE_EXTENSIONS);
        createEAttribute(viewpointEClass, VIEWPOINT__ICON);
        createEReference(viewpointEClass, VIEWPOINT__OWNED_TEMPLATES);
        createEAttribute(viewpointEClass, VIEWPOINT__CONFLICTS);
        createEAttribute(viewpointEClass, VIEWPOINT__REUSES);
        createEAttribute(viewpointEClass, VIEWPOINT__CUSTOMIZES);

        featureExtensionDescriptionEClass = createEClass(FEATURE_EXTENSION_DESCRIPTION);

        representationDescriptionEClass = createEClass(REPRESENTATION_DESCRIPTION);
        createEAttribute(representationDescriptionEClass, REPRESENTATION_DESCRIPTION__TITLE_EXPRESSION);
        createEAttribute(representationDescriptionEClass, REPRESENTATION_DESCRIPTION__INITIALISATION);
        createEReference(representationDescriptionEClass, REPRESENTATION_DESCRIPTION__METAMODEL);
        createEAttribute(representationDescriptionEClass, REPRESENTATION_DESCRIPTION__SHOW_ON_STARTUP);

        representationTemplateEClass = createEClass(REPRESENTATION_TEMPLATE);
        createEAttribute(representationTemplateEClass, REPRESENTATION_TEMPLATE__NAME);
        createEReference(representationTemplateEClass, REPRESENTATION_TEMPLATE__OWNED_REPRESENTATIONS);

        representationImportDescriptionEClass = createEClass(REPRESENTATION_IMPORT_DESCRIPTION);

        representationExtensionDescriptionEClass = createEClass(REPRESENTATION_EXTENSION_DESCRIPTION);
        createEAttribute(representationExtensionDescriptionEClass, REPRESENTATION_EXTENSION_DESCRIPTION__NAME);
        createEAttribute(representationExtensionDescriptionEClass, REPRESENTATION_EXTENSION_DESCRIPTION__VIEWPOINT_URI);
        createEAttribute(representationExtensionDescriptionEClass, REPRESENTATION_EXTENSION_DESCRIPTION__REPRESENTATION_NAME);
        createEReference(representationExtensionDescriptionEClass, REPRESENTATION_EXTENSION_DESCRIPTION__METAMODEL);

        diagramDescriptionEClass = createEClass(DIAGRAM_DESCRIPTION);
        createEReference(diagramDescriptionEClass, DIAGRAM_DESCRIPTION__FILTERS);
        createEReference(diagramDescriptionEClass, DIAGRAM_DESCRIPTION__ALL_EDGE_MAPPINGS);
        createEReference(diagramDescriptionEClass, DIAGRAM_DESCRIPTION__ALL_ACTIVATED_EDGE_MAPPINGS);
        createEReference(diagramDescriptionEClass, DIAGRAM_DESCRIPTION__ALL_NODE_MAPPINGS);
        createEReference(diagramDescriptionEClass, DIAGRAM_DESCRIPTION__ALL_CONTAINER_MAPPINGS);
        createEReference(diagramDescriptionEClass, DIAGRAM_DESCRIPTION__VALIDATION_SET);
        createEReference(diagramDescriptionEClass, DIAGRAM_DESCRIPTION__CONCERNS);
        createEReference(diagramDescriptionEClass, DIAGRAM_DESCRIPTION__INFORMATION_SECTIONS);
        createEReference(diagramDescriptionEClass, DIAGRAM_DESCRIPTION__ALL_TOOLS);
        createEAttribute(diagramDescriptionEClass, DIAGRAM_DESCRIPTION__DOMAIN_CLASS);
        createEAttribute(diagramDescriptionEClass, DIAGRAM_DESCRIPTION__PRECONDITION_EXPRESSION);
        createEReference(diagramDescriptionEClass, DIAGRAM_DESCRIPTION__DEFAULT_CONCERN);
        createEAttribute(diagramDescriptionEClass, DIAGRAM_DESCRIPTION__ROOT_EXPRESSION);
        createEReference(diagramDescriptionEClass, DIAGRAM_DESCRIPTION__INIT);
        createEReference(diagramDescriptionEClass, DIAGRAM_DESCRIPTION__LAYOUT);
        createEReference(diagramDescriptionEClass, DIAGRAM_DESCRIPTION__DIAGRAM_INITIALISATION);
        createEReference(diagramDescriptionEClass, DIAGRAM_DESCRIPTION__DEFAULT_LAYER);
        createEReference(diagramDescriptionEClass, DIAGRAM_DESCRIPTION__ADDITIONAL_LAYERS);
        createEReference(diagramDescriptionEClass, DIAGRAM_DESCRIPTION__ALL_LAYERS);
        createEReference(diagramDescriptionEClass, DIAGRAM_DESCRIPTION__ALL_ACTIVATED_TOOLS);
        createEReference(diagramDescriptionEClass, DIAGRAM_DESCRIPTION__NODE_MAPPINGS);
        createEReference(diagramDescriptionEClass, DIAGRAM_DESCRIPTION__EDGE_MAPPINGS);
        createEReference(diagramDescriptionEClass, DIAGRAM_DESCRIPTION__EDGE_MAPPING_IMPORTS);
        createEReference(diagramDescriptionEClass, DIAGRAM_DESCRIPTION__CONTAINER_MAPPINGS);
        createEReference(diagramDescriptionEClass, DIAGRAM_DESCRIPTION__REUSED_MAPPINGS);
        createEReference(diagramDescriptionEClass, DIAGRAM_DESCRIPTION__TOOL_SECTION);
        createEReference(diagramDescriptionEClass, DIAGRAM_DESCRIPTION__REUSED_TOOLS);
        createEAttribute(diagramDescriptionEClass, DIAGRAM_DESCRIPTION__ENABLE_POPUP_BARS);

        diagramImportDescriptionEClass = createEClass(DIAGRAM_IMPORT_DESCRIPTION);
        createEReference(diagramImportDescriptionEClass, DIAGRAM_IMPORT_DESCRIPTION__IMPORTED_DIAGRAM);

        diagramExtensionDescriptionEClass = createEClass(DIAGRAM_EXTENSION_DESCRIPTION);
        createEReference(diagramExtensionDescriptionEClass, DIAGRAM_EXTENSION_DESCRIPTION__LAYERS);
        createEReference(diagramExtensionDescriptionEClass, DIAGRAM_EXTENSION_DESCRIPTION__VALIDATION_SET);
        createEReference(diagramExtensionDescriptionEClass, DIAGRAM_EXTENSION_DESCRIPTION__CONCERNS);

        metamodelExtensionSettingEClass = createEClass(METAMODEL_EXTENSION_SETTING);
        createEReference(metamodelExtensionSettingEClass, METAMODEL_EXTENSION_SETTING__EXTENSION_GROUP);

        javaExtensionEClass = createEClass(JAVA_EXTENSION);
        createEAttribute(javaExtensionEClass, JAVA_EXTENSION__QUALIFIED_CLASS_NAME);

        representationElementMappingEClass = createEClass(REPRESENTATION_ELEMENT_MAPPING);
        createEReference(representationElementMappingEClass, REPRESENTATION_ELEMENT_MAPPING__DETAIL_DESCRIPTIONS);
        createEReference(representationElementMappingEClass, REPRESENTATION_ELEMENT_MAPPING__NAVIGATION_DESCRIPTIONS);

        diagramElementMappingEClass = createEClass(DIAGRAM_ELEMENT_MAPPING);
        createEAttribute(diagramElementMappingEClass, DIAGRAM_ELEMENT_MAPPING__PRECONDITION_EXPRESSION);
        createEReference(diagramElementMappingEClass, DIAGRAM_ELEMENT_MAPPING__DELETION_DESCRIPTION);
        createEReference(diagramElementMappingEClass, DIAGRAM_ELEMENT_MAPPING__LABEL_DIRECT_EDIT);
        createEAttribute(diagramElementMappingEClass, DIAGRAM_ELEMENT_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION);
        createEAttribute(diagramElementMappingEClass, DIAGRAM_ELEMENT_MAPPING__CREATE_ELEMENTS);
        createEAttribute(diagramElementMappingEClass, DIAGRAM_ELEMENT_MAPPING__SEMANTIC_ELEMENTS);
        createEReference(diagramElementMappingEClass, DIAGRAM_ELEMENT_MAPPING__DOUBLE_CLICK_DESCRIPTION);
        createEAttribute(diagramElementMappingEClass, DIAGRAM_ELEMENT_MAPPING__SYNCHRONIZATION_LOCK);

        abstractNodeMappingEClass = createEClass(ABSTRACT_NODE_MAPPING);
        createEAttribute(abstractNodeMappingEClass, ABSTRACT_NODE_MAPPING__DOMAIN_CLASS);
        createEReference(abstractNodeMappingEClass, ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS);
        createEReference(abstractNodeMappingEClass, ABSTRACT_NODE_MAPPING__REUSED_BORDERED_NODE_MAPPINGS);

        nodeMappingEClass = createEClass(NODE_MAPPING);
        createEReference(nodeMappingEClass, NODE_MAPPING__STYLE);
        createEReference(nodeMappingEClass, NODE_MAPPING__CONDITIONNAL_STYLES);

        containerMappingEClass = createEClass(CONTAINER_MAPPING);
        createEReference(containerMappingEClass, CONTAINER_MAPPING__SUB_NODE_MAPPINGS);
        createEReference(containerMappingEClass, CONTAINER_MAPPING__ALL_NODE_MAPPINGS);
        createEReference(containerMappingEClass, CONTAINER_MAPPING__REUSED_NODE_MAPPINGS);
        createEReference(containerMappingEClass, CONTAINER_MAPPING__SUB_CONTAINER_MAPPINGS);
        createEReference(containerMappingEClass, CONTAINER_MAPPING__REUSED_CONTAINER_MAPPINGS);
        createEReference(containerMappingEClass, CONTAINER_MAPPING__ALL_CONTAINER_MAPPINGS);
        createEReference(containerMappingEClass, CONTAINER_MAPPING__STYLE);
        createEReference(containerMappingEClass, CONTAINER_MAPPING__CONDITIONNAL_STYLES);
        createEAttribute(containerMappingEClass, CONTAINER_MAPPING__CHILDREN_PRESENTATION);

        abstractMappingImportEClass = createEClass(ABSTRACT_MAPPING_IMPORT);
        createEAttribute(abstractMappingImportEClass, ABSTRACT_MAPPING_IMPORT__HIDE_SUB_MAPPINGS);
        createEAttribute(abstractMappingImportEClass, ABSTRACT_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS);

        nodeMappingImportEClass = createEClass(NODE_MAPPING_IMPORT);
        createEReference(nodeMappingImportEClass, NODE_MAPPING_IMPORT__IMPORTED_MAPPING);

        containerMappingImportEClass = createEClass(CONTAINER_MAPPING_IMPORT);
        createEReference(containerMappingImportEClass, CONTAINER_MAPPING_IMPORT__IMPORTED_MAPPING);

        edgeMappingEClass = createEClass(EDGE_MAPPING);
        createEReference(edgeMappingEClass, EDGE_MAPPING__SOURCE_MAPPING);
        createEReference(edgeMappingEClass, EDGE_MAPPING__TARGET_MAPPING);
        createEAttribute(edgeMappingEClass, EDGE_MAPPING__TARGET_FINDER_EXPRESSION);
        createEAttribute(edgeMappingEClass, EDGE_MAPPING__SOURCE_FINDER_EXPRESSION);
        createEReference(edgeMappingEClass, EDGE_MAPPING__STYLE);
        createEReference(edgeMappingEClass, EDGE_MAPPING__CONDITIONNAL_STYLES);
        createEAttribute(edgeMappingEClass, EDGE_MAPPING__TARGET_EXPRESSION);
        createEAttribute(edgeMappingEClass, EDGE_MAPPING__DOMAIN_CLASS);
        createEAttribute(edgeMappingEClass, EDGE_MAPPING__USE_DOMAIN_ELEMENT);
        createEReference(edgeMappingEClass, EDGE_MAPPING__RECONNECTIONS);
        createEAttribute(edgeMappingEClass, EDGE_MAPPING__PATH_EXPRESSION);
        createEReference(edgeMappingEClass, EDGE_MAPPING__PATH_NODE_MAPPING);

        iEdgeMappingEClass = createEClass(IEDGE_MAPPING);

        edgeMappingImportEClass = createEClass(EDGE_MAPPING_IMPORT);
        createEReference(edgeMappingImportEClass, EDGE_MAPPING_IMPORT__IMPORTED_MAPPING);
        createEReference(edgeMappingImportEClass, EDGE_MAPPING_IMPORT__CONDITIONNAL_STYLES);
        createEAttribute(edgeMappingImportEClass, EDGE_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS);

        documentedElementEClass = createEClass(DOCUMENTED_ELEMENT);
        createEAttribute(documentedElementEClass, DOCUMENTED_ELEMENT__DOCUMENTATION);

        dModelElementEClass = createEClass(DMODEL_ELEMENT);
        createEReference(dModelElementEClass, DMODEL_ELEMENT__EANNOTATIONS);

        dAnnotationEClass = createEClass(DANNOTATION);
        createEAttribute(dAnnotationEClass, DANNOTATION__SOURCE);
        createEReference(dAnnotationEClass, DANNOTATION__DETAILS);

        conditionalStyleDescriptionEClass = createEClass(CONDITIONAL_STYLE_DESCRIPTION);
        createEAttribute(conditionalStyleDescriptionEClass, CONDITIONAL_STYLE_DESCRIPTION__PREDICATE_EXPRESSION);

        conditionalNodeStyleDescriptionEClass = createEClass(CONDITIONAL_NODE_STYLE_DESCRIPTION);
        createEReference(conditionalNodeStyleDescriptionEClass, CONDITIONAL_NODE_STYLE_DESCRIPTION__STYLE);

        conditionalEdgeStyleDescriptionEClass = createEClass(CONDITIONAL_EDGE_STYLE_DESCRIPTION);
        createEReference(conditionalEdgeStyleDescriptionEClass, CONDITIONAL_EDGE_STYLE_DESCRIPTION__STYLE);

        conditionalContainerStyleDescriptionEClass = createEClass(CONDITIONAL_CONTAINER_STYLE_DESCRIPTION);
        createEReference(conditionalContainerStyleDescriptionEClass, CONDITIONAL_CONTAINER_STYLE_DESCRIPTION__STYLE);

        dragAndDropTargetDescriptionEClass = createEClass(DRAG_AND_DROP_TARGET_DESCRIPTION);
        createEReference(dragAndDropTargetDescriptionEClass, DRAG_AND_DROP_TARGET_DESCRIPTION__DROP_DESCRIPTIONS);

        pasteTargetDescriptionEClass = createEClass(PASTE_TARGET_DESCRIPTION);
        createEReference(pasteTargetDescriptionEClass, PASTE_TARGET_DESCRIPTION__PASTE_DESCRIPTIONS);

        layoutEClass = createEClass(LAYOUT);

        orderedTreeLayoutEClass = createEClass(ORDERED_TREE_LAYOUT);
        createEAttribute(orderedTreeLayoutEClass, ORDERED_TREE_LAYOUT__CHILDREN_EXPRESSION);
        createEReference(orderedTreeLayoutEClass, ORDERED_TREE_LAYOUT__NODE_MAPPING);

        compositeLayoutEClass = createEClass(COMPOSITE_LAYOUT);
        createEAttribute(compositeLayoutEClass, COMPOSITE_LAYOUT__PADDING);
        createEAttribute(compositeLayoutEClass, COMPOSITE_LAYOUT__DIRECTION);

        decorationDescriptionsSetEClass = createEClass(DECORATION_DESCRIPTIONS_SET);
        createEReference(decorationDescriptionsSetEClass, DECORATION_DESCRIPTIONS_SET__DECORATION_DESCRIPTIONS);

        decorationDescriptionEClass = createEClass(DECORATION_DESCRIPTION);
        createEAttribute(decorationDescriptionEClass, DECORATION_DESCRIPTION__NAME);
        createEAttribute(decorationDescriptionEClass, DECORATION_DESCRIPTION__POSITION);
        createEAttribute(decorationDescriptionEClass, DECORATION_DESCRIPTION__DECORATOR_PATH);
        createEAttribute(decorationDescriptionEClass, DECORATION_DESCRIPTION__PRECONDITION_EXPRESSION);

        mappingBasedDecorationEClass = createEClass(MAPPING_BASED_DECORATION);
        createEReference(mappingBasedDecorationEClass, MAPPING_BASED_DECORATION__MAPPINGS);

        semanticBasedDecorationEClass = createEClass(SEMANTIC_BASED_DECORATION);
        createEAttribute(semanticBasedDecorationEClass, SEMANTIC_BASED_DECORATION__DOMAIN_CLASS);

        layerEClass = createEClass(LAYER);
        createEReference(layerEClass, LAYER__NODE_MAPPINGS);
        createEReference(layerEClass, LAYER__EDGE_MAPPINGS);
        createEReference(layerEClass, LAYER__EDGE_MAPPING_IMPORTS);
        createEReference(layerEClass, LAYER__CONTAINER_MAPPINGS);
        createEReference(layerEClass, LAYER__REUSED_MAPPINGS);
        createEReference(layerEClass, LAYER__ALL_TOOLS);
        createEReference(layerEClass, LAYER__TOOL_SECTIONS);
        createEReference(layerEClass, LAYER__REUSED_TOOLS);
        createEReference(layerEClass, LAYER__DECORATION_DESCRIPTIONS_SET);
        createEAttribute(layerEClass, LAYER__ICON);
        createEReference(layerEClass, LAYER__ALL_EDGE_MAPPINGS);
        createEReference(layerEClass, LAYER__ALL_ACTIVATED_EDGE_MAPPINGS);
        createEReference(layerEClass, LAYER__CUSTOMIZATION);

        additionalLayerEClass = createEClass(ADDITIONAL_LAYER);
        createEAttribute(additionalLayerEClass, ADDITIONAL_LAYER__ACTIVE_BY_DEFAULT);
        createEAttribute(additionalLayerEClass, ADDITIONAL_LAYER__OPTIONAL);

        customizationEClass = createEClass(CUSTOMIZATION);
        createEReference(customizationEClass, CUSTOMIZATION__VSM_ELEMENT_CUSTOMIZATIONS);

        ivsmElementCustomizationEClass = createEClass(IVSM_ELEMENT_CUSTOMIZATION);

        vsmElementCustomizationEClass = createEClass(VSM_ELEMENT_CUSTOMIZATION);
        createEAttribute(vsmElementCustomizationEClass, VSM_ELEMENT_CUSTOMIZATION__PREDICATE_EXPRESSION);
        createEReference(vsmElementCustomizationEClass, VSM_ELEMENT_CUSTOMIZATION__FEATURE_CUSTOMIZATIONS);

        vsmElementCustomizationReuseEClass = createEClass(VSM_ELEMENT_CUSTOMIZATION_REUSE);
        createEReference(vsmElementCustomizationReuseEClass, VSM_ELEMENT_CUSTOMIZATION_REUSE__REUSE);
        createEReference(vsmElementCustomizationReuseEClass, VSM_ELEMENT_CUSTOMIZATION_REUSE__APPLIED_ON);

        eStructuralFeatureCustomizationEClass = createEClass(ESTRUCTURAL_FEATURE_CUSTOMIZATION);
        createEReference(eStructuralFeatureCustomizationEClass, ESTRUCTURAL_FEATURE_CUSTOMIZATION__APPLIED_ON);
        createEAttribute(eStructuralFeatureCustomizationEClass, ESTRUCTURAL_FEATURE_CUSTOMIZATION__APPLY_ON_ALL);

        eAttributeCustomizationEClass = createEClass(EATTRIBUTE_CUSTOMIZATION);
        createEAttribute(eAttributeCustomizationEClass, EATTRIBUTE_CUSTOMIZATION__ATTRIBUTE_NAME);
        createEAttribute(eAttributeCustomizationEClass, EATTRIBUTE_CUSTOMIZATION__VALUE);

        eReferenceCustomizationEClass = createEClass(EREFERENCE_CUSTOMIZATION);
        createEAttribute(eReferenceCustomizationEClass, EREFERENCE_CUSTOMIZATION__REFERENCE_NAME);
        createEReference(eReferenceCustomizationEClass, EREFERENCE_CUSTOMIZATION__VALUE);

        selectionDescriptionEClass = createEClass(SELECTION_DESCRIPTION);
        createEAttribute(selectionDescriptionEClass, SELECTION_DESCRIPTION__CANDIDATES_EXPRESSION);
        createEAttribute(selectionDescriptionEClass, SELECTION_DESCRIPTION__MULTIPLE);
        createEAttribute(selectionDescriptionEClass, SELECTION_DESCRIPTION__TREE);
        createEAttribute(selectionDescriptionEClass, SELECTION_DESCRIPTION__ROOT_EXPRESSION);
        createEAttribute(selectionDescriptionEClass, SELECTION_DESCRIPTION__CHILDREN_EXPRESSION);
        createEAttribute(selectionDescriptionEClass, SELECTION_DESCRIPTION__MESSAGE);

        colorDescriptionEClass = createEClass(COLOR_DESCRIPTION);

        systemColorEClass = createEClass(SYSTEM_COLOR);
        createEAttribute(systemColorEClass, SYSTEM_COLOR__NAME);

        interpolatedColorEClass = createEClass(INTERPOLATED_COLOR);
        createEAttribute(interpolatedColorEClass, INTERPOLATED_COLOR__COLOR_VALUE_COMPUTATION_EXPRESSION);
        createEAttribute(interpolatedColorEClass, INTERPOLATED_COLOR__MIN_VALUE_COMPUTATION_EXPRESSION);
        createEAttribute(interpolatedColorEClass, INTERPOLATED_COLOR__MAX_VALUE_COMPUTATION_EXPRESSION);
        createEReference(interpolatedColorEClass, INTERPOLATED_COLOR__COLOR_STEPS);

        colorStepEClass = createEClass(COLOR_STEP);
        createEAttribute(colorStepEClass, COLOR_STEP__ASSOCIATED_VALUE);
        createEReference(colorStepEClass, COLOR_STEP__ASSOCIATED_COLOR);

        fixedColorEClass = createEClass(FIXED_COLOR);
        createEAttribute(fixedColorEClass, FIXED_COLOR__RED);
        createEAttribute(fixedColorEClass, FIXED_COLOR__GREEN);
        createEAttribute(fixedColorEClass, FIXED_COLOR__BLUE);

        userFixedColorEClass = createEClass(USER_FIXED_COLOR);

        userColorEClass = createEClass(USER_COLOR);
        createEAttribute(userColorEClass, USER_COLOR__NAME);

        environmentEClass = createEClass(ENVIRONMENT);
        createEReference(environmentEClass, ENVIRONMENT__SYSTEM_COLORS);
        createEReference(environmentEClass, ENVIRONMENT__DEFAULT_TOOLS);
        createEReference(environmentEClass, ENVIRONMENT__LABEL_BORDER_STYLES);

        sytemColorsPaletteEClass = createEClass(SYTEM_COLORS_PALETTE);
        createEReference(sytemColorsPaletteEClass, SYTEM_COLORS_PALETTE__ENTRIES);

        userColorsPaletteEClass = createEClass(USER_COLORS_PALETTE);
        createEAttribute(userColorsPaletteEClass, USER_COLORS_PALETTE__NAME);
        createEReference(userColorsPaletteEClass, USER_COLORS_PALETTE__ENTRIES);

        annotationEntryEClass = createEClass(ANNOTATION_ENTRY);
        createEAttribute(annotationEntryEClass, ANNOTATION_ENTRY__SOURCE);
        createEReference(annotationEntryEClass, ANNOTATION_ENTRY__DATA);

        endUserDocumentedElementEClass = createEClass(END_USER_DOCUMENTED_ELEMENT);
        createEAttribute(endUserDocumentedElementEClass, END_USER_DOCUMENTED_ELEMENT__END_USER_DOCUMENTATION);

        identifiedElementEClass = createEClass(IDENTIFIED_ELEMENT);
        createEAttribute(identifiedElementEClass, IDENTIFIED_ELEMENT__NAME);
        createEAttribute(identifiedElementEClass, IDENTIFIED_ELEMENT__LABEL);

        computedColorEClass = createEClass(COMPUTED_COLOR);
        createEAttribute(computedColorEClass, COMPUTED_COLOR__RED);
        createEAttribute(computedColorEClass, COMPUTED_COLOR__GREEN);
        createEAttribute(computedColorEClass, COMPUTED_COLOR__BLUE);

        dAnnotationEntryEClass = createEClass(DANNOTATION_ENTRY);
        createEAttribute(dAnnotationEntryEClass, DANNOTATION_ENTRY__SOURCE);
        createEAttribute(dAnnotationEntryEClass, DANNOTATION_ENTRY__DETAILS);

        // Create enums
        navigationTargetTypeEEnum = createEEnum(NAVIGATION_TARGET_TYPE);
        foldingStyleEEnum = createEEnum(FOLDING_STYLE);
        layoutDirectionEEnum = createEEnum(LAYOUT_DIRECTION);
        positionEEnum = createEEnum(POSITION);
        systemColorsEEnum = createEEnum(SYSTEM_COLORS);

        // Create data types
        typeNameEDataType = createEDataType(TYPE_NAME);
        interpretedExpressionEDataType = createEDataType(INTERPRETED_EXPRESSION);
        featureNameEDataType = createEDataType(FEATURE_NAME);
        uriEDataType = createEDataType(URI);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model. This
     * method is guarded to have no affect on any invocation but its first. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized)
            return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        StylePackage theStylePackage = (StylePackage) EPackage.Registry.INSTANCE.getEPackage(StylePackage.eNS_URI);
        ToolPackage theToolPackage = (ToolPackage) EPackage.Registry.INSTANCE.getEPackage(ToolPackage.eNS_URI);
        FilterPackage theFilterPackage = (FilterPackage) EPackage.Registry.INSTANCE.getEPackage(FilterPackage.eNS_URI);
        ValidationPackage theValidationPackage = (ValidationPackage) EPackage.Registry.INSTANCE.getEPackage(ValidationPackage.eNS_URI);
        AuditPackage theAuditPackage = (AuditPackage) EPackage.Registry.INSTANCE.getEPackage(AuditPackage.eNS_URI);
        ConcernPackage theConcernPackage = (ConcernPackage) EPackage.Registry.INSTANCE.getEPackage(ConcernPackage.eNS_URI);
        EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
        ViewpointPackage theViewpointPackage = (ViewpointPackage) EPackage.Registry.INSTANCE.getEPackage(ViewpointPackage.eNS_URI);

        // Add subpackages
        getESubpackages().add(theStylePackage);
        getESubpackages().add(theToolPackage);
        getESubpackages().add(theFilterPackage);
        getESubpackages().add(theValidationPackage);
        getESubpackages().add(theAuditPackage);
        getESubpackages().add(theConcernPackage);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        groupEClass.getESuperTypes().add(this.getDModelElement());
        groupEClass.getESuperTypes().add(this.getDocumentedElement());
        viewpointEClass.getESuperTypes().add(this.getDocumentedElement());
        viewpointEClass.getESuperTypes().add(this.getComponent());
        viewpointEClass.getESuperTypes().add(this.getEndUserDocumentedElement());
        viewpointEClass.getESuperTypes().add(this.getIdentifiedElement());
        representationDescriptionEClass.getESuperTypes().add(this.getDocumentedElement());
        representationDescriptionEClass.getESuperTypes().add(this.getEndUserDocumentedElement());
        representationDescriptionEClass.getESuperTypes().add(this.getIdentifiedElement());
        representationImportDescriptionEClass.getESuperTypes().add(this.getRepresentationDescription());
        diagramDescriptionEClass.getESuperTypes().add(this.getDragAndDropTargetDescription());
        diagramDescriptionEClass.getESuperTypes().add(this.getRepresentationDescription());
        diagramDescriptionEClass.getESuperTypes().add(this.getPasteTargetDescription());
        diagramImportDescriptionEClass.getESuperTypes().add(this.getRepresentationImportDescription());
        diagramImportDescriptionEClass.getESuperTypes().add(this.getDiagramDescription());
        diagramExtensionDescriptionEClass.getESuperTypes().add(this.getRepresentationExtensionDescription());
        representationElementMappingEClass.getESuperTypes().add(this.getIdentifiedElement());
        diagramElementMappingEClass.getESuperTypes().add(this.getRepresentationElementMapping());
        diagramElementMappingEClass.getESuperTypes().add(this.getPasteTargetDescription());
        abstractNodeMappingEClass.getESuperTypes().add(this.getDiagramElementMapping());
        abstractNodeMappingEClass.getESuperTypes().add(this.getDocumentedElement());
        nodeMappingEClass.getESuperTypes().add(this.getAbstractNodeMapping());
        nodeMappingEClass.getESuperTypes().add(this.getDragAndDropTargetDescription());
        containerMappingEClass.getESuperTypes().add(this.getAbstractNodeMapping());
        containerMappingEClass.getESuperTypes().add(this.getDragAndDropTargetDescription());
        nodeMappingImportEClass.getESuperTypes().add(this.getNodeMapping());
        nodeMappingImportEClass.getESuperTypes().add(this.getAbstractMappingImport());
        containerMappingImportEClass.getESuperTypes().add(this.getContainerMapping());
        containerMappingImportEClass.getESuperTypes().add(this.getAbstractMappingImport());
        edgeMappingEClass.getESuperTypes().add(this.getDiagramElementMapping());
        edgeMappingEClass.getESuperTypes().add(this.getDocumentedElement());
        edgeMappingEClass.getESuperTypes().add(this.getIEdgeMapping());
        edgeMappingImportEClass.getESuperTypes().add(this.getDocumentedElement());
        edgeMappingImportEClass.getESuperTypes().add(this.getIEdgeMapping());
        edgeMappingImportEClass.getESuperTypes().add(this.getIdentifiedElement());
        conditionalNodeStyleDescriptionEClass.getESuperTypes().add(this.getConditionalStyleDescription());
        conditionalEdgeStyleDescriptionEClass.getESuperTypes().add(this.getConditionalStyleDescription());
        conditionalContainerStyleDescriptionEClass.getESuperTypes().add(this.getConditionalStyleDescription());
        layoutEClass.getESuperTypes().add(this.getDocumentedElement());
        orderedTreeLayoutEClass.getESuperTypes().add(this.getLayout());
        compositeLayoutEClass.getESuperTypes().add(this.getLayout());
        mappingBasedDecorationEClass.getESuperTypes().add(this.getDecorationDescription());
        semanticBasedDecorationEClass.getESuperTypes().add(this.getDecorationDescription());
        layerEClass.getESuperTypes().add(this.getDocumentedElement());
        layerEClass.getESuperTypes().add(this.getEndUserDocumentedElement());
        layerEClass.getESuperTypes().add(this.getIdentifiedElement());
        additionalLayerEClass.getESuperTypes().add(this.getLayer());
        vsmElementCustomizationEClass.getESuperTypes().add(this.getIVSMElementCustomization());
        vsmElementCustomizationReuseEClass.getESuperTypes().add(this.getIVSMElementCustomization());
        eAttributeCustomizationEClass.getESuperTypes().add(this.getEStructuralFeatureCustomization());
        eReferenceCustomizationEClass.getESuperTypes().add(this.getEStructuralFeatureCustomization());
        systemColorEClass.getESuperTypes().add(this.getFixedColor());
        interpolatedColorEClass.getESuperTypes().add(this.getColorDescription());
        interpolatedColorEClass.getESuperTypes().add(this.getUserColor());
        fixedColorEClass.getESuperTypes().add(this.getColorDescription());
        userFixedColorEClass.getESuperTypes().add(this.getFixedColor());
        userFixedColorEClass.getESuperTypes().add(this.getUserColor());
        computedColorEClass.getESuperTypes().add(this.getUserColor());
        computedColorEClass.getESuperTypes().add(this.getColorDescription());

        // Initialize classes and features; add operations and parameters
        initEClass(groupEClass, Group.class, "Group", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getGroup_Name(), ecorePackage.getEString(), "name", "", 0, 1, Group.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
                IS_ORDERED);
        initEReference(getGroup_OwnedViewpoints(), this.getViewpoint(), null, "ownedViewpoints", null, 0, -1, Group.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getGroup_OwnedViewpoints().getEKeys().add(this.getIdentifiedElement_Name());
        initEReference(getGroup_SystemColorsPalette(), this.getSytemColorsPalette(), null, "systemColorsPalette", null, 1, 1, Group.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getGroup_UserColorsPalettes(), this.getUserColorsPalette(), null, "userColorsPalettes", null, 0, -1, Group.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getGroup_UserColorsPalettes().getEKeys().add(this.getUserColorsPalette_Name());
        initEAttribute(getGroup_Version(), theEcorePackage.getEString(), "version", null, 0, 1, Group.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        initEClass(componentEClass, Component.class, "Component", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(viewpointEClass, Viewpoint.class, "Viewpoint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getViewpoint_ModelFileExtension(), ecorePackage.getEString(), "modelFileExtension", "*", 0, 1, Viewpoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getViewpoint_ValidationSet(), theValidationPackage.getValidationSet(), null, "validationSet", null, 0, 1, Viewpoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getViewpoint_ValidationSet().getEKeys().add(theValidationPackage.getValidationSet_Name());
        initEReference(getViewpoint_OwnedRepresentations(), this.getRepresentationDescription(), null, "ownedRepresentations", null, 0, -1, Viewpoint.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getViewpoint_OwnedRepresentations().getEKeys().add(this.getIdentifiedElement_Name());
        initEReference(getViewpoint_OwnedRepresentationExtensions(), this.getRepresentationExtensionDescription(), null, "ownedRepresentationExtensions", null, 0, -1, Viewpoint.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getViewpoint_OwnedRepresentationExtensions().getEKeys().add(this.getRepresentationExtensionDescription_Name());
        initEReference(getViewpoint_OwnedJavaExtensions(), this.getJavaExtension(), null, "ownedJavaExtensions", null, 0, -1, Viewpoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getViewpoint_OwnedMMExtensions(), this.getMetamodelExtensionSetting(), null, "ownedMMExtensions", null, 0, -1, Viewpoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getViewpoint_OwnedFeatureExtensions(), this.getFeatureExtensionDescription(), null, "ownedFeatureExtensions", null, 0, -1, Viewpoint.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getViewpoint_Icon(), theEcorePackage.getEString(), "icon", null, 0, 1, Viewpoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEReference(getViewpoint_OwnedTemplates(), this.getRepresentationTemplate(), null, "ownedTemplates", null, 0, -1, Viewpoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getViewpoint_OwnedTemplates().getEKeys().add(this.getRepresentationTemplate_Name());
        initEAttribute(getViewpoint_Conflicts(), this.getURI(), "conflicts", null, 0, -1, Viewpoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
                IS_ORDERED);
        initEAttribute(getViewpoint_Reuses(), this.getURI(), "reuses", null, 0, -1, Viewpoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
                IS_ORDERED);
        initEAttribute(getViewpoint_Customizes(), this.getURI(), "customizes", null, 0, -1, Viewpoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        EOperation op = addEOperation(viewpointEClass, null, "initView", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "model", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(featureExtensionDescriptionEClass, FeatureExtensionDescription.class, "FeatureExtensionDescription", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(representationDescriptionEClass, RepresentationDescription.class, "RepresentationDescription", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getRepresentationDescription_TitleExpression(), this.getInterpretedExpression(), "titleExpression", "", 0, 1, RepresentationDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getRepresentationDescription_Initialisation(), ecorePackage.getEBoolean(), "initialisation", null, 1, 1, RepresentationDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getRepresentationDescription_Metamodel(), theEcorePackage.getEPackage(), null, "metamodel", null, 0, -1, RepresentationDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getRepresentationDescription_ShowOnStartup(), theEcorePackage.getEBoolean(), "showOnStartup", null, 0, 1, RepresentationDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(representationTemplateEClass, RepresentationTemplate.class, "RepresentationTemplate", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getRepresentationTemplate_Name(), theEcorePackage.getEString(), "name", null, 1, 1, RepresentationTemplate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getRepresentationTemplate_OwnedRepresentations(), this.getRepresentationDescription(), null, "ownedRepresentations", null, 0, -1, RepresentationTemplate.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(representationImportDescriptionEClass, RepresentationImportDescription.class, "RepresentationImportDescription", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(representationExtensionDescriptionEClass, RepresentationExtensionDescription.class, "RepresentationExtensionDescription", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getRepresentationExtensionDescription_Name(), theEcorePackage.getEString(), "name", null, 1, 1, RepresentationExtensionDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getRepresentationExtensionDescription_ViewpointURI(), theEcorePackage.getEString(), "viewpointURI", null, 1, 1, RepresentationExtensionDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getRepresentationExtensionDescription_RepresentationName(), theEcorePackage.getEString(), "representationName", null, 1, 1, RepresentationExtensionDescription.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getRepresentationExtensionDescription_Metamodel(), theEcorePackage.getEPackage(), null, "metamodel", null, 0, -1, RepresentationExtensionDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(diagramDescriptionEClass, DiagramDescription.class, "DiagramDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDiagramDescription_Filters(), theFilterPackage.getFilterDescription(), null, "filters", null, 0, -1, DiagramDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getDiagramDescription_Filters().getEKeys().add(this.getIdentifiedElement_Name());
        initEReference(getDiagramDescription_AllEdgeMappings(), this.getEdgeMapping(), null, "allEdgeMappings", null, 0, -1, DiagramDescription.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDiagramDescription_AllActivatedEdgeMappings(), this.getEdgeMapping(), null, "allActivatedEdgeMappings", null, 0, -1, DiagramDescription.class, IS_TRANSIENT, IS_VOLATILE,
                !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDiagramDescription_AllNodeMappings(), this.getNodeMapping(), null, "allNodeMappings", null, 0, -1, DiagramDescription.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDiagramDescription_AllContainerMappings(), this.getContainerMapping(), null, "allContainerMappings", null, 0, -1, DiagramDescription.class, IS_TRANSIENT, IS_VOLATILE,
                !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDiagramDescription_ValidationSet(), theValidationPackage.getValidationSet(), null, "validationSet", null, 0, 1, DiagramDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDiagramDescription_Concerns(), theConcernPackage.getConcernSet(), null, "concerns", null, 0, 1, DiagramDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDiagramDescription_InformationSections(), theAuditPackage.getInformationSection(), null, "informationSections", null, 0, -1, DiagramDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDiagramDescription_AllTools(), theToolPackage.getAbstractToolDescription(), null, "allTools", null, 0, -1, DiagramDescription.class, IS_TRANSIENT, IS_VOLATILE,
                !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getDiagramDescription_DomainClass(), this.getTypeName(), "domainClass", null, 1, 1, DiagramDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDiagramDescription_PreconditionExpression(), this.getInterpretedExpression(), "preconditionExpression", null, 0, 1, DiagramDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDiagramDescription_DefaultConcern(), theConcernPackage.getConcernDescription(), null, "defaultConcern", null, 0, 1, DiagramDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDiagramDescription_RootExpression(), this.getInterpretedExpression(), "rootExpression", null, 0, 1, DiagramDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDiagramDescription_Init(), theToolPackage.getRepresentationCreationDescription(), null, "init", null, 0, 1, DiagramDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDiagramDescription_Layout(), this.getLayout(), null, "layout", null, 0, 1, DiagramDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDiagramDescription_DiagramInitialisation(), theToolPackage.getInitialOperation(), null, "diagramInitialisation", null, 0, 1, DiagramDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDiagramDescription_DefaultLayer(), this.getLayer(), null, "defaultLayer", null, 0, 1, DiagramDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDiagramDescription_AdditionalLayers(), this.getAdditionalLayer(), null, "additionalLayers", null, 0, -1, DiagramDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getDiagramDescription_AdditionalLayers().getEKeys().add(this.getIdentifiedElement_Name());
        initEReference(getDiagramDescription_AllLayers(), this.getLayer(), null, "allLayers", null, 0, -1, DiagramDescription.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDiagramDescription_AllActivatedTools(), theToolPackage.getAbstractToolDescription(), null, "allActivatedTools", null, 0, -1, DiagramDescription.class, IS_TRANSIENT,
                IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDiagramDescription_NodeMappings(), this.getNodeMapping(), null, "nodeMappings", null, 0, -1, DiagramDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getDiagramDescription_NodeMappings().getEKeys().add(this.getIdentifiedElement_Name());
        initEReference(getDiagramDescription_EdgeMappings(), this.getEdgeMapping(), null, "edgeMappings", null, 0, -1, DiagramDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getDiagramDescription_EdgeMappings().getEKeys().add(this.getIdentifiedElement_Name());
        initEReference(getDiagramDescription_EdgeMappingImports(), this.getEdgeMappingImport(), null, "edgeMappingImports", null, 0, -1, DiagramDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getDiagramDescription_EdgeMappingImports().getEKeys().add(this.getIdentifiedElement_Name());
        initEReference(getDiagramDescription_ContainerMappings(), this.getContainerMapping(), null, "containerMappings", null, 0, -1, DiagramDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getDiagramDescription_ContainerMappings().getEKeys().add(this.getIdentifiedElement_Name());
        initEReference(getDiagramDescription_ReusedMappings(), this.getDiagramElementMapping(), null, "reusedMappings", null, 0, -1, DiagramDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDiagramDescription_ToolSection(), theToolPackage.getToolSection(), null, "toolSection", null, 0, 1, DiagramDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getDiagramDescription_ToolSection().getEKeys().add(this.getIdentifiedElement_Name());
        initEReference(getDiagramDescription_ReusedTools(), theToolPackage.getAbstractToolDescription(), null, "reusedTools", null, 0, -1, DiagramDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDiagramDescription_EnablePopupBars(), theEcorePackage.getEBoolean(), "enablePopupBars", null, 0, 1, DiagramDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        addEOperation(diagramDescriptionEClass, theViewpointPackage.getDSemanticDiagram(), "createDiagram", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(diagramImportDescriptionEClass, DiagramImportDescription.class, "DiagramImportDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDiagramImportDescription_ImportedDiagram(), this.getDiagramDescription(), null, "importedDiagram", null, 0, 1, DiagramImportDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(diagramExtensionDescriptionEClass, DiagramExtensionDescription.class, "DiagramExtensionDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDiagramExtensionDescription_Layers(), this.getAdditionalLayer(), null, "layers", null, 0, -1, DiagramExtensionDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getDiagramExtensionDescription_Layers().getEKeys().add(this.getIdentifiedElement_Name());
        initEReference(getDiagramExtensionDescription_ValidationSet(), theValidationPackage.getValidationSet(), null, "validationSet", null, 0, 1, DiagramExtensionDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDiagramExtensionDescription_Concerns(), theConcernPackage.getConcernSet(), null, "concerns", null, 0, 1, DiagramExtensionDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(metamodelExtensionSettingEClass, MetamodelExtensionSetting.class, "MetamodelExtensionSetting", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getMetamodelExtensionSetting_ExtensionGroup(), theEcorePackage.getEObject(), null, "extensionGroup", null, 0, 1, MetamodelExtensionSetting.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(javaExtensionEClass, JavaExtension.class, "JavaExtension", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getJavaExtension_QualifiedClassName(), ecorePackage.getEString(), "qualifiedClassName", null, 1, 1, JavaExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(representationElementMappingEClass, RepresentationElementMapping.class, "RepresentationElementMapping", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getRepresentationElementMapping_DetailDescriptions(), theToolPackage.getRepresentationCreationDescription(), null, "detailDescriptions", null, 0, -1,
                RepresentationElementMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getRepresentationElementMapping_NavigationDescriptions(), theToolPackage.getRepresentationNavigationDescription(), null, "navigationDescriptions", null, 0, -1,
                RepresentationElementMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(diagramElementMappingEClass, DiagramElementMapping.class, "DiagramElementMapping", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDiagramElementMapping_PreconditionExpression(), this.getInterpretedExpression(), "preconditionExpression", "", 0, 1, DiagramElementMapping.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDiagramElementMapping_DeletionDescription(), theToolPackage.getDeleteElementDescription(), null, "deletionDescription", null, 0, 1, DiagramElementMapping.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDiagramElementMapping_LabelDirectEdit(), theToolPackage.getDirectEditLabel(), null, "labelDirectEdit", null, 0, 1, DiagramElementMapping.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDiagramElementMapping_SemanticCandidatesExpression(), this.getInterpretedExpression(), "semanticCandidatesExpression", null, 0, 1, DiagramElementMapping.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDiagramElementMapping_CreateElements(), theEcorePackage.getEBoolean(), "createElements", "true", 1, 1, DiagramElementMapping.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDiagramElementMapping_SemanticElements(), this.getInterpretedExpression(), "semanticElements", null, 0, 1, DiagramElementMapping.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDiagramElementMapping_DoubleClickDescription(), theToolPackage.getDoubleClickDescription(), theToolPackage.getDoubleClickDescription_Mappings(), "doubleClickDescription",
                null, 0, 1, DiagramElementMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDiagramElementMapping_SynchronizationLock(), ecorePackage.getEBoolean(), "synchronizationLock", "false", 0, 1, DiagramElementMapping.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        op = addEOperation(diagramElementMappingEClass, theEcorePackage.getEBoolean(), "checkPrecondition", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "modelElement", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "container", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "containerView", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(diagramElementMappingEClass, this.getDiagramElementMapping(), "getAllMappings", 0, -1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(diagramElementMappingEClass, theEcorePackage.getEBoolean(), "isFrom", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theViewpointPackage.getDMappingBased(), "element", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(abstractNodeMappingEClass, AbstractNodeMapping.class, "AbstractNodeMapping", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbstractNodeMapping_DomainClass(), this.getTypeName(), "domainClass", null, 1, 1, AbstractNodeMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getAbstractNodeMapping_BorderedNodeMappings(), this.getNodeMapping(), null, "borderedNodeMappings", null, 0, -1, AbstractNodeMapping.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getAbstractNodeMapping_BorderedNodeMappings().getEKeys().add(this.getIdentifiedElement_Name());
        initEReference(getAbstractNodeMapping_ReusedBorderedNodeMappings(), this.getNodeMapping(), null, "reusedBorderedNodeMappings", null, 0, -1, AbstractNodeMapping.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        addEOperation(abstractNodeMappingEClass, theViewpointPackage.getDDiagramElement(), "getDNodesDone", 0, -1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(abstractNodeMappingEClass, theViewpointPackage.getDDiagramElement(), "findDNodeFromEObject", 0, -1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "eObject", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(abstractNodeMappingEClass, null, "clearDNodesDone", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(abstractNodeMappingEClass, null, "addDoneNode", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theViewpointPackage.getDSemanticDecorator(), "node", 1, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(abstractNodeMappingEClass, this.getNodeMapping(), "getAllBorderedNodeMappings", 0, -1, IS_UNIQUE, IS_ORDERED);

        initEClass(nodeMappingEClass, NodeMapping.class, "NodeMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getNodeMapping_Style(), theStylePackage.getNodeStyleDescription(), null, "style", null, 0, 1, NodeMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getNodeMapping_ConditionnalStyles(), this.getConditionalNodeStyleDescription(), null, "conditionnalStyles", null, 0, -1, NodeMapping.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        op = addEOperation(nodeMappingEClass, theViewpointPackage.getDNode(), "createNode", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "modelElement", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "container", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theViewpointPackage.getDDiagram(), "viewPoint", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(nodeMappingEClass, null, "updateNode", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theViewpointPackage.getDNode(), "node", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(nodeMappingEClass, theViewpointPackage.getNodeStyle(), "getBestStyle", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "modelElement", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "viewVariable", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "containerVariable", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(nodeMappingEClass, theViewpointPackage.getDNodeListElement(), "createListElement", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "modelElement", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theViewpointPackage.getDDiagram(), "diagram", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(nodeMappingEClass, null, "updateListElement", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theViewpointPackage.getDNodeListElement(), "listElement", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(nodeMappingEClass, theEcorePackage.getEObject(), "getNodesCandidates", 0, -1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "semanticOrigin", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "container", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(nodeMappingEClass, theEcorePackage.getEObject(), "getNodesCandidates", 0, -1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "semanticOrigin", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "container", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "containerView", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(containerMappingEClass, ContainerMapping.class, "ContainerMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getContainerMapping_SubNodeMappings(), this.getNodeMapping(), null, "subNodeMappings", null, 0, -1, ContainerMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getContainerMapping_SubNodeMappings().getEKeys().add(this.getIdentifiedElement_Name());
        initEReference(getContainerMapping_AllNodeMappings(), this.getNodeMapping(), null, "allNodeMappings", null, 0, -1, ContainerMapping.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getContainerMapping_ReusedNodeMappings(), this.getNodeMapping(), null, "reusedNodeMappings", null, 0, -1, ContainerMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getContainerMapping_SubContainerMappings(), this.getContainerMapping(), null, "subContainerMappings", null, 0, -1, ContainerMapping.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getContainerMapping_SubContainerMappings().getEKeys().add(this.getIdentifiedElement_Name());
        initEReference(getContainerMapping_ReusedContainerMappings(), this.getContainerMapping(), null, "reusedContainerMappings", null, 0, -1, ContainerMapping.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getContainerMapping_AllContainerMappings(), this.getContainerMapping(), null, "allContainerMappings", null, 0, -1, ContainerMapping.class, IS_TRANSIENT, IS_VOLATILE,
                !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getContainerMapping_Style(), theStylePackage.getContainerStyleDescription(), null, "style", null, 0, 1, ContainerMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getContainerMapping_ConditionnalStyles(), this.getConditionalContainerStyleDescription(), null, "conditionnalStyles", null, 0, -1, ContainerMapping.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getContainerMapping_ChildrenPresentation(), theViewpointPackage.getContainerLayout(), "childrenPresentation", "FreeForm", 1, 1, ContainerMapping.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        op = addEOperation(containerMappingEClass, theViewpointPackage.getDDiagramElementContainer(), "createContainer", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "modelElement", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "container", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theViewpointPackage.getDDiagram(), "viewPoint", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(containerMappingEClass, null, "updateContainer", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theViewpointPackage.getDDiagramElementContainer(), "node", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(containerMappingEClass, theViewpointPackage.getContainerStyle(), "getBestStyle", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "modelElement", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "viewVariable", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "containerVariable", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(containerMappingEClass, theEcorePackage.getEObject(), "getNodesCandidates", 0, -1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "semanticOrigin", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "container", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(containerMappingEClass, theEcorePackage.getEObject(), "getNodesCandidates", 0, -1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "semanticOrigin", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "container", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "containerView", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(abstractMappingImportEClass, AbstractMappingImport.class, "AbstractMappingImport", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbstractMappingImport_HideSubMappings(), theEcorePackage.getEBoolean(), "hideSubMappings", "false", 0, 1, AbstractMappingImport.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractMappingImport_InheritsAncestorFilters(), ecorePackage.getEBoolean(), "inheritsAncestorFilters", "true", 0, 1, AbstractMappingImport.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(nodeMappingImportEClass, NodeMappingImport.class, "NodeMappingImport", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getNodeMappingImport_ImportedMapping(), this.getNodeMapping(), null, "importedMapping", null, 1, 1, NodeMappingImport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(containerMappingImportEClass, ContainerMappingImport.class, "ContainerMappingImport", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getContainerMappingImport_ImportedMapping(), this.getContainerMapping(), null, "importedMapping", null, 1, 1, ContainerMappingImport.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(edgeMappingEClass, EdgeMapping.class, "EdgeMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getEdgeMapping_SourceMapping(), this.getDiagramElementMapping(), null, "sourceMapping", null, 1, -1, EdgeMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeMapping_TargetMapping(), this.getDiagramElementMapping(), null, "targetMapping", null, 1, -1, EdgeMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEdgeMapping_TargetFinderExpression(), this.getInterpretedExpression(), "targetFinderExpression", "", 1, 1, EdgeMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEdgeMapping_SourceFinderExpression(), this.getInterpretedExpression(), "sourceFinderExpression", null, 0, 1, EdgeMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeMapping_Style(), theStylePackage.getEdgeStyleDescription(), null, "style", null, 0, 1, EdgeMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeMapping_ConditionnalStyles(), this.getConditionalEdgeStyleDescription(), null, "conditionnalStyles", null, 0, -1, EdgeMapping.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEdgeMapping_TargetExpression(), this.getInterpretedExpression(), "targetExpression", null, 0, 1, EdgeMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEdgeMapping_DomainClass(), this.getTypeName(), "domainClass", null, 0, 1, EdgeMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEdgeMapping_UseDomainElement(), theEcorePackage.getEBoolean(), "useDomainElement", "false", 0, 1, EdgeMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeMapping_Reconnections(), theToolPackage.getReconnectEdgeDescription(), null, "reconnections", null, 0, -1, EdgeMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEdgeMapping_PathExpression(), this.getInterpretedExpression(), "pathExpression", null, 0, 1, EdgeMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeMapping_PathNodeMapping(), this.getAbstractNodeMapping(), null, "pathNodeMapping", null, 0, -1, EdgeMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        op = addEOperation(edgeMappingEClass, theViewpointPackage.getDEdge(), "createEdge", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theViewpointPackage.getEdgeTarget(), "source", 1, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theViewpointPackage.getEdgeTarget(), "target", 1, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "semanticTarget", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(edgeMappingEClass, theViewpointPackage.getDEdge(), "createEdge", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theViewpointPackage.getEdgeTarget(), "source", 1, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theViewpointPackage.getEdgeTarget(), "target", 1, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "container", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "semanticTarget", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(edgeMappingEClass, theViewpointPackage.getEdgeStyle(), "getBestStyle", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "modelElement", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "viewVariable", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "containerVariable", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(edgeMappingEClass, null, "updateEdge", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theViewpointPackage.getDEdge(), "viewEdge", 1, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(edgeMappingEClass, theEcorePackage.getEObject(), "getEdgeTargetCandidates", 0, -1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "semanticOrigin", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theViewpointPackage.getDDiagram(), "viewPoint", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(edgeMappingEClass, theEcorePackage.getEObject(), "getEdgeSourceCandidates", 0, -1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "semanticOrigin", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theViewpointPackage.getDDiagram(), "viewPoint", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(edgeMappingEClass, theEcorePackage.getEObject(), "getEdgeTargetCandidates", 0, -1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "semanticOrigin", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "container", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "containerView", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(iEdgeMappingEClass, IEdgeMapping.class, "IEdgeMapping", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        op = addEOperation(iEdgeMappingEClass, theViewpointPackage.getEdgeStyle(), "getBestStyle", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "modelElement", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "viewVariable", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "containerVariable", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(edgeMappingImportEClass, EdgeMappingImport.class, "EdgeMappingImport", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getEdgeMappingImport_ImportedMapping(), this.getIEdgeMapping(), null, "importedMapping", null, 1, 1, EdgeMappingImport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeMappingImport_ConditionnalStyles(), this.getConditionalEdgeStyleDescription(), null, "conditionnalStyles", null, 0, -1, EdgeMappingImport.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEdgeMappingImport_InheritsAncestorFilters(), ecorePackage.getEBoolean(), "inheritsAncestorFilters", "true", 0, 1, EdgeMappingImport.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(documentedElementEClass, DocumentedElement.class, "DocumentedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDocumentedElement_Documentation(), ecorePackage.getEString(), "documentation", "", 0, 1, DocumentedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(dModelElementEClass, DModelElement.class, "DModelElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDModelElement_EAnnotations(), this.getDAnnotation(), null, "eAnnotations", null, 0, -1, DModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        op = addEOperation(dModelElementEClass, this.getDAnnotation(), "getDAnnotation", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "source", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(dAnnotationEClass, DAnnotation.class, "DAnnotation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDAnnotation_Source(), ecorePackage.getEString(), "source", null, 0, 1, DAnnotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEReference(getDAnnotation_Details(), theEcorePackage.getEStringToStringMapEntry(), null, "details", null, 0, -1, DAnnotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(conditionalStyleDescriptionEClass, ConditionalStyleDescription.class, "ConditionalStyleDescription", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getConditionalStyleDescription_PredicateExpression(), this.getInterpretedExpression(), "predicateExpression", null, 1, 1, ConditionalStyleDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        op = addEOperation(conditionalStyleDescriptionEClass, theEcorePackage.getEBoolean(), "checkPredicate", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "modelElement", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "viewVariable", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "containerVariable", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(conditionalNodeStyleDescriptionEClass, ConditionalNodeStyleDescription.class, "ConditionalNodeStyleDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getConditionalNodeStyleDescription_Style(), theStylePackage.getNodeStyleDescription(), null, "style", null, 0, 1, ConditionalNodeStyleDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(conditionalEdgeStyleDescriptionEClass, ConditionalEdgeStyleDescription.class, "ConditionalEdgeStyleDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getConditionalEdgeStyleDescription_Style(), theStylePackage.getEdgeStyleDescription(), null, "style", null, 0, 1, ConditionalEdgeStyleDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(conditionalContainerStyleDescriptionEClass, ConditionalContainerStyleDescription.class, "ConditionalContainerStyleDescription", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getConditionalContainerStyleDescription_Style(), theStylePackage.getContainerStyleDescription(), null, "style", null, 0, 1, ConditionalContainerStyleDescription.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(dragAndDropTargetDescriptionEClass, DragAndDropTargetDescription.class, "DragAndDropTargetDescription", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDragAndDropTargetDescription_DropDescriptions(), theToolPackage.getContainerDropDescription(), null, "dropDescriptions", null, 0, -1, DragAndDropTargetDescription.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(pasteTargetDescriptionEClass, PasteTargetDescription.class, "PasteTargetDescription", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getPasteTargetDescription_PasteDescriptions(), theToolPackage.getPasteDescription(), null, "pasteDescriptions", null, 0, -1, PasteTargetDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(layoutEClass, Layout.class, "Layout", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(orderedTreeLayoutEClass, OrderedTreeLayout.class, "OrderedTreeLayout", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getOrderedTreeLayout_ChildrenExpression(), this.getInterpretedExpression(), "childrenExpression", null, 1, 1, OrderedTreeLayout.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getOrderedTreeLayout_NodeMapping(), this.getAbstractNodeMapping(), null, "nodeMapping", null, 1, -1, OrderedTreeLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(compositeLayoutEClass, CompositeLayout.class, "CompositeLayout", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getCompositeLayout_Padding(), theEcorePackage.getEInt(), "padding", "30", 1, 1, CompositeLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getCompositeLayout_Direction(), this.getLayoutDirection(), "direction", "topToBottom", 1, 1, CompositeLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(decorationDescriptionsSetEClass, DecorationDescriptionsSet.class, "DecorationDescriptionsSet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDecorationDescriptionsSet_DecorationDescriptions(), this.getDecorationDescription(), null, "decorationDescriptions", null, 0, -1, DecorationDescriptionsSet.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getDecorationDescriptionsSet_DecorationDescriptions().getEKeys().add(this.getDecorationDescription_Name());

        initEClass(decorationDescriptionEClass, DecorationDescription.class, "DecorationDescription", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDecorationDescription_Name(), theEcorePackage.getEString(), "name", null, 1, 1, DecorationDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDecorationDescription_Position(), this.getPosition(), "position", "SOUTH_WEST", 1, 1, DecorationDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDecorationDescription_DecoratorPath(), theEcorePackage.getEString(), "decoratorPath", null, 1, 1, DecorationDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDecorationDescription_PreconditionExpression(), this.getInterpretedExpression(), "preconditionExpression", null, 0, 1, DecorationDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(mappingBasedDecorationEClass, MappingBasedDecoration.class, "MappingBasedDecoration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getMappingBasedDecoration_Mappings(), this.getDiagramElementMapping(), null, "mappings", null, 1, -1, MappingBasedDecoration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(semanticBasedDecorationEClass, SemanticBasedDecoration.class, "SemanticBasedDecoration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSemanticBasedDecoration_DomainClass(), theEcorePackage.getEString(), "domainClass", null, 0, 1, SemanticBasedDecoration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(layerEClass, Layer.class, "Layer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getLayer_NodeMappings(), this.getNodeMapping(), null, "nodeMappings", null, 0, -1, Layer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getLayer_NodeMappings().getEKeys().add(this.getIdentifiedElement_Name());
        initEReference(getLayer_EdgeMappings(), this.getEdgeMapping(), null, "edgeMappings", null, 0, -1, Layer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getLayer_EdgeMappings().getEKeys().add(this.getIdentifiedElement_Name());
        initEReference(getLayer_EdgeMappingImports(), this.getEdgeMappingImport(), null, "edgeMappingImports", null, 0, -1, Layer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getLayer_EdgeMappingImports().getEKeys().add(this.getIdentifiedElement_Name());
        initEReference(getLayer_ContainerMappings(), this.getContainerMapping(), null, "containerMappings", null, 0, -1, Layer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getLayer_ContainerMappings().getEKeys().add(this.getIdentifiedElement_Name());
        initEReference(getLayer_ReusedMappings(), this.getDiagramElementMapping(), null, "reusedMappings", null, 0, -1, Layer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getLayer_AllTools(), theToolPackage.getAbstractToolDescription(), null, "allTools", null, 0, -1, Layer.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getLayer_ToolSections(), theToolPackage.getToolSection(), null, "toolSections", null, 0, -1, Layer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getLayer_ReusedTools(), theToolPackage.getAbstractToolDescription(), null, "reusedTools", null, 0, -1, Layer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getLayer_DecorationDescriptionsSet(), this.getDecorationDescriptionsSet(), null, "decorationDescriptionsSet", null, 0, 1, Layer.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLayer_Icon(), theEcorePackage.getEString(), "icon", null, 0, 1, Layer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
                IS_ORDERED);
        initEReference(getLayer_AllEdgeMappings(), this.getEdgeMapping(), null, "allEdgeMappings", null, 0, -1, Layer.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getLayer_AllActivatedEdgeMappings(), this.getEdgeMapping(), null, "allActivatedEdgeMappings", null, 0, -1, Layer.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getLayer_Customization(), this.getCustomization(), null, "customization", null, 0, 1, Layer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(additionalLayerEClass, AdditionalLayer.class, "AdditionalLayer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAdditionalLayer_ActiveByDefault(), theEcorePackage.getEBoolean(), "activeByDefault", null, 0, 1, AdditionalLayer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAdditionalLayer_Optional(), ecorePackage.getEBoolean(), "optional", "true", 0, 1, AdditionalLayer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(customizationEClass, Customization.class, "Customization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCustomization_VsmElementCustomizations(), this.getIVSMElementCustomization(), null, "vsmElementCustomizations", null, 1, -1, Customization.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(ivsmElementCustomizationEClass, IVSMElementCustomization.class, "IVSMElementCustomization", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(vsmElementCustomizationEClass, VSMElementCustomization.class, "VSMElementCustomization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getVSMElementCustomization_PredicateExpression(), this.getInterpretedExpression(), "predicateExpression", null, 0, 1, VSMElementCustomization.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getVSMElementCustomization_FeatureCustomizations(), this.getEStructuralFeatureCustomization(), null, "featureCustomizations", null, 1, -1, VSMElementCustomization.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(vsmElementCustomizationReuseEClass, VSMElementCustomizationReuse.class, "VSMElementCustomizationReuse", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getVSMElementCustomizationReuse_Reuse(), this.getEStructuralFeatureCustomization(), null, "reuse", null, 1, -1, VSMElementCustomizationReuse.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEStructuralFeatureCustomization_AppliedOn(), theEcorePackage.getEObject(), null, "appliedOn", null, 0, -1, EStructuralFeatureCustomization.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(eStructuralFeatureCustomizationEClass, EStructuralFeatureCustomization.class, "EStructuralFeatureCustomization", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getEStructuralFeatureCustomization_AppliedOn(), theEcorePackage.getEObject(), null, "appliedOn", null, 1, -1, EStructuralFeatureCustomization.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEStructuralFeatureCustomization_ApplyOnAll(), ecorePackage.getEBoolean(), "applyOnAll", null, 0, 1, EStructuralFeatureCustomization.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(eAttributeCustomizationEClass, EAttributeCustomization.class, "EAttributeCustomization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getEAttributeCustomization_AttributeName(), theEcorePackage.getEString(), "attributeName", null, 1, 1, EAttributeCustomization.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEAttributeCustomization_Value(), this.getInterpretedExpression(), "value", null, 0, 1, EAttributeCustomization.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(eReferenceCustomizationEClass, EReferenceCustomization.class, "EReferenceCustomization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getEReferenceCustomization_ReferenceName(), theEcorePackage.getEString(), "referenceName", null, 1, 1, EReferenceCustomization.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEReferenceCustomization_Value(), theEcorePackage.getEObject(), null, "value", null, 0, 1, EReferenceCustomization.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(selectionDescriptionEClass, SelectionDescription.class, "SelectionDescription", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSelectionDescription_CandidatesExpression(), this.getInterpretedExpression(), "candidatesExpression", null, 1, 1, SelectionDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSelectionDescription_Multiple(), theEcorePackage.getEBoolean(), "multiple", null, 1, 1, SelectionDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSelectionDescription_Tree(), theEcorePackage.getEBoolean(), "tree", null, 1, 1, SelectionDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSelectionDescription_RootExpression(), this.getInterpretedExpression(), "rootExpression", null, 0, 1, SelectionDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSelectionDescription_ChildrenExpression(), this.getInterpretedExpression(), "childrenExpression", null, 0, 1, SelectionDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSelectionDescription_Message(), theEcorePackage.getEString(), "message", null, 0, 1, SelectionDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(colorDescriptionEClass, ColorDescription.class, "ColorDescription", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(systemColorEClass, SystemColor.class, "SystemColor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSystemColor_Name(), ecorePackage.getEString(), "name", null, 1, 1, SystemColor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        initEClass(interpolatedColorEClass, InterpolatedColor.class, "InterpolatedColor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getInterpolatedColor_ColorValueComputationExpression(), this.getInterpretedExpression(), "colorValueComputationExpression", "<%eContents().nSize%>", 1, 1,
                InterpolatedColor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getInterpolatedColor_MinValueComputationExpression(), this.getInterpretedExpression(), "minValueComputationExpression", "0", 1, 1, InterpolatedColor.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getInterpolatedColor_MaxValueComputationExpression(), this.getInterpretedExpression(), "maxValueComputationExpression", "10", 1, 1, InterpolatedColor.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getInterpolatedColor_ColorSteps(), this.getColorStep(), null, "colorSteps", null, 0, -1, InterpolatedColor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(colorStepEClass, ColorStep.class, "ColorStep", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getColorStep_AssociatedValue(), this.getInterpretedExpression(), "associatedValue", "", 1, 1, ColorStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getColorStep_AssociatedColor(), this.getFixedColor(), null, "associatedColor", null, 1, 1, ColorStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(fixedColorEClass, FixedColor.class, "FixedColor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFixedColor_Red(), theEcorePackage.getEInt(), "red", "125", 1, 1, FixedColor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFixedColor_Green(), theEcorePackage.getEInt(), "green", "125", 1, 1, FixedColor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFixedColor_Blue(), theEcorePackage.getEInt(), "blue", "125", 1, 1, FixedColor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        initEClass(userFixedColorEClass, UserFixedColor.class, "UserFixedColor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(userColorEClass, UserColor.class, "UserColor", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getUserColor_Name(), ecorePackage.getEString(), "name", null, 1, 1, UserColor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
                IS_ORDERED);

        initEClass(environmentEClass, Environment.class, "Environment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getEnvironment_SystemColors(), this.getSytemColorsPalette(), null, "systemColors", null, 0, 1, Environment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEnvironment_DefaultTools(), theToolPackage.getToolEntry(), null, "defaultTools", null, 0, -1, Environment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEnvironment_LabelBorderStyles(), theStylePackage.getLabelBorderStyles(), null, "labelBorderStyles", null, 0, 1, Environment.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(sytemColorsPaletteEClass, SytemColorsPalette.class, "SytemColorsPalette", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getSytemColorsPalette_Entries(), this.getSystemColor(), null, "entries", null, 0, -1, SytemColorsPalette.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getSytemColorsPalette_Entries().getEKeys().add(this.getSystemColor_Name());

        initEClass(userColorsPaletteEClass, UserColorsPalette.class, "UserColorsPalette", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getUserColorsPalette_Name(), theEcorePackage.getEString(), "name", null, 1, 1, UserColorsPalette.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getUserColorsPalette_Entries(), this.getUserColor(), null, "entries", null, 0, -1, UserColorsPalette.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getUserColorsPalette_Entries().getEKeys().add(this.getUserColor_Name());

        initEClass(annotationEntryEClass, AnnotationEntry.class, "AnnotationEntry", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAnnotationEntry_Source(), ecorePackage.getEString(), "source", null, 0, 1, AnnotationEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getAnnotationEntry_Data(), theEcorePackage.getEObject(), null, "data", null, 0, 1, AnnotationEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(endUserDocumentedElementEClass, EndUserDocumentedElement.class, "EndUserDocumentedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getEndUserDocumentedElement_EndUserDocumentation(), ecorePackage.getEString(), "endUserDocumentation", "", 0, 1, EndUserDocumentedElement.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(identifiedElementEClass, IdentifiedElement.class, "IdentifiedElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getIdentifiedElement_Name(), ecorePackage.getEString(), "name", "", 1, 1, IdentifiedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getIdentifiedElement_Label(), ecorePackage.getEString(), "label", null, 0, 1, IdentifiedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(computedColorEClass, ComputedColor.class, "ComputedColor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getComputedColor_Red(), this.getInterpretedExpression(), "red", "", 1, 1, ComputedColor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getComputedColor_Green(), this.getInterpretedExpression(), "green", "", 1, 1, ComputedColor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getComputedColor_Blue(), this.getInterpretedExpression(), "blue", "", 1, 1, ComputedColor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        initEClass(dAnnotationEntryEClass, DAnnotationEntry.class, "DAnnotationEntry", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDAnnotationEntry_Source(), ecorePackage.getEString(), "source", null, 0, 1, DAnnotationEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDAnnotationEntry_Details(), ecorePackage.getEString(), "details", null, 0, -1, DAnnotationEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(navigationTargetTypeEEnum, NavigationTargetType.class, "NavigationTargetType");
        addEEnumLiteral(navigationTargetTypeEEnum, NavigationTargetType.MODEL_LITERAL);
        addEEnumLiteral(navigationTargetTypeEEnum, NavigationTargetType.FILE_LITERAL);

        initEEnum(foldingStyleEEnum, FoldingStyle.class, "FoldingStyle");
        addEEnumLiteral(foldingStyleEEnum, FoldingStyle.NONE_LITERAL);
        addEEnumLiteral(foldingStyleEEnum, FoldingStyle.SOURCE_LITERAL);
        addEEnumLiteral(foldingStyleEEnum, FoldingStyle.TARGET_LITERAL);

        initEEnum(layoutDirectionEEnum, LayoutDirection.class, "LayoutDirection");
        addEEnumLiteral(layoutDirectionEEnum, LayoutDirection.TOP_TO_BOTTOM);
        addEEnumLiteral(layoutDirectionEEnum, LayoutDirection.LEFT_TO_RIGHT);
        addEEnumLiteral(layoutDirectionEEnum, LayoutDirection.BOTTOM_TO_TOP);

        initEEnum(positionEEnum, Position.class, "Position");
        addEEnumLiteral(positionEEnum, Position.NORTH_LITERAL);
        addEEnumLiteral(positionEEnum, Position.WEST_LITERAL);
        addEEnumLiteral(positionEEnum, Position.SOUTH_LITERAL);
        addEEnumLiteral(positionEEnum, Position.EAST_LITERAL);
        addEEnumLiteral(positionEEnum, Position.NORTH_WEST_LITERAL);
        addEEnumLiteral(positionEEnum, Position.NORTH_EAST_LITERAL);
        addEEnumLiteral(positionEEnum, Position.SOUTH_WEST_LITERAL);
        addEEnumLiteral(positionEEnum, Position.SOUTH_EAST_LITERAL);
        addEEnumLiteral(positionEEnum, Position.CENTER_LITERAL);

        initEEnum(systemColorsEEnum, SystemColors.class, "SystemColors");
        addEEnumLiteral(systemColorsEEnum, SystemColors.BLACK_LITERAL);
        addEEnumLiteral(systemColorsEEnum, SystemColors.BLUE_LITERAL);
        addEEnumLiteral(systemColorsEEnum, SystemColors.RED_LITERAL);
        addEEnumLiteral(systemColorsEEnum, SystemColors.GREEN_LITERAL);
        addEEnumLiteral(systemColorsEEnum, SystemColors.YELLOW_LITERAL);
        addEEnumLiteral(systemColorsEEnum, SystemColors.PURPLE_LITERAL);
        addEEnumLiteral(systemColorsEEnum, SystemColors.ORANGE_LITERAL);
        addEEnumLiteral(systemColorsEEnum, SystemColors.CHOCOLATE_LITERAL);
        addEEnumLiteral(systemColorsEEnum, SystemColors.GRAY_LITERAL);
        addEEnumLiteral(systemColorsEEnum, SystemColors.WHITE_LITERAL);
        addEEnumLiteral(systemColorsEEnum, SystemColors.DARK_BLUE_LITERAL);
        addEEnumLiteral(systemColorsEEnum, SystemColors.DARK_RED_LITERAL);
        addEEnumLiteral(systemColorsEEnum, SystemColors.DARK_GREEN_LITERAL);
        addEEnumLiteral(systemColorsEEnum, SystemColors.DARK_YELLOW_LITERAL);
        addEEnumLiteral(systemColorsEEnum, SystemColors.DARK_PURPLE_LITERAL);
        addEEnumLiteral(systemColorsEEnum, SystemColors.DARK_ORANGE_LITERAL);
        addEEnumLiteral(systemColorsEEnum, SystemColors.DARK_CHOCOLATE_LITERAL);
        addEEnumLiteral(systemColorsEEnum, SystemColors.DARK_GRAY_LITERAL);
        addEEnumLiteral(systemColorsEEnum, SystemColors.LIGHT_BLUE_LITERAL);
        addEEnumLiteral(systemColorsEEnum, SystemColors.LIGHT_RED_LITERAL);
        addEEnumLiteral(systemColorsEEnum, SystemColors.LIGHT_GREEN_LITERAL);
        addEEnumLiteral(systemColorsEEnum, SystemColors.LIGHT_YELLOW_LITERAL);
        addEEnumLiteral(systemColorsEEnum, SystemColors.LIGHT_PURPLE_LITERAL);
        addEEnumLiteral(systemColorsEEnum, SystemColors.LIGHT_ORANGE_LITERAL);
        addEEnumLiteral(systemColorsEEnum, SystemColors.LIGHT_CHOCOLATE_LITERAL);
        addEEnumLiteral(systemColorsEEnum, SystemColors.LIGHT_GRAY_LITERAL);

        // Initialize data types
        initEDataType(typeNameEDataType, String.class, "TypeName", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
        initEDataType(interpretedExpressionEDataType, String.class, "InterpretedExpression", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
        initEDataType(featureNameEDataType, String.class, "FeatureName", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
        initEDataType(uriEDataType, org.eclipse.emf.common.util.URI.class, "URI", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

        // Create annotations
        // http://www.eclipse.org/sirius/interpreted/expression/returnType
        createReturnTypeAnnotations();
        // http://www.eclipse.org/sirius/interpreted/expression/variables
        createVariablesAnnotations();
        // TagValues
        createTagValuesAnnotations();
    }

    /**
     * Initializes the annotations for <b>TagValues</b>. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void createTagValuesAnnotations() {
        String source = "TagValues";
        addAnnotation(getDiagramDescription_DefaultConcern(), source, new String[] {});
        addAnnotation(getDiagramDescription_Init(), source, new String[] {});
        addAnnotation(getDiagramDescription_NodeMappings(), source, new String[] {});
        addAnnotation(getDiagramDescription_EdgeMappings(), source, new String[] {});
        addAnnotation(getDiagramDescription_ContainerMappings(), source, new String[] {});
        addAnnotation(getDiagramDescription_ReusedMappings(), source, new String[] {});
        addAnnotation(getDiagramDescription_ReusedTools(), source, new String[] {});
    }

    /**
     * Initializes the annotations for
     * <b>http://www.eclipse.org/sirius/interpreted/expression/returnType</b>.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void createReturnTypeAnnotations() {
        String source = "http://www.eclipse.org/sirius/interpreted/expression/returnType";
        addAnnotation(getRepresentationDescription_TitleExpression(), source, new String[] { "returnType", "a string." });
        addAnnotation(getDiagramDescription_PreconditionExpression(), source, new String[] { "returnType", "a boolean." });
        addAnnotation(getDiagramDescription_RootExpression(), source, new String[] { "returnType", "an EObject." });
        addAnnotation(getDiagramElementMapping_PreconditionExpression(), source, new String[] { "returnType", "a boolean." });
        addAnnotation(getDiagramElementMapping_SemanticCandidatesExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." });
        addAnnotation(getDiagramElementMapping_SemanticElements(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." });
        addAnnotation(getEdgeMapping_TargetFinderExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." });
        addAnnotation(getEdgeMapping_SourceFinderExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." });
        addAnnotation(getEdgeMapping_TargetExpression(), source, new String[] { "returnType", "an EObject." });
        addAnnotation(getEdgeMapping_PathExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." });
        addAnnotation(getConditionalStyleDescription_PredicateExpression(), source, new String[] { "returnType", "a boolean." });
        addAnnotation(getOrderedTreeLayout_ChildrenExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." });
        addAnnotation(getDecorationDescription_PreconditionExpression(), source, new String[] { "returnType", "a boolean." });
        addAnnotation(getVSMElementCustomization_PredicateExpression(), source, new String[] { "returnType",
                "a boolean result. True to enable the customization, false to disabled it. True by default." });
        addAnnotation(getEAttributeCustomization_Value(), source, new String[] { "returnType", "A java Object to affect as new value of a EAttribute, for example a java primitive." });
        addAnnotation(getSelectionDescription_CandidatesExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." });
        addAnnotation(getSelectionDescription_RootExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." });
        addAnnotation(getSelectionDescription_ChildrenExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." });
        addAnnotation(getInterpolatedColor_ColorValueComputationExpression(), source, new String[] { "returnType", "an integer." });
        addAnnotation(getInterpolatedColor_MinValueComputationExpression(), source, new String[] { "returnType", "an integer." });
        addAnnotation(getInterpolatedColor_MaxValueComputationExpression(), source, new String[] { "returnType", "an integer." });
        addAnnotation(getColorStep_AssociatedValue(), source, new String[] { "returnType", "an integer." });
        addAnnotation(getComputedColor_Red(), source, new String[] { "returnType", "an integer." });
        addAnnotation(getComputedColor_Green(), source, new String[] { "returnType", "an integer." });
        addAnnotation(getComputedColor_Blue(), source, new String[] { "returnType", "an integer." });
    }

    /**
     * Initializes the annotations for
     * <b>http://www.eclipse.org/sirius/interpreted/expression/variables</b>.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void createVariablesAnnotations() {
        String source = "http://www.eclipse.org/sirius/interpreted/expression/variables";
        addAnnotation(getRepresentationDescription_TitleExpression(), source, new String[] {});
        addAnnotation(getDiagramDescription_PreconditionExpression(), source, new String[] {});
        addAnnotation(getDiagramDescription_RootExpression(), source, new String[] { "diagram", "viewpoint.DDiagram | the current DDiagram.", "viewpoint",
                "viewpoint.DDiagram | (deprecated) the current DDiagram." });
        addAnnotation(getDiagramElementMapping_PreconditionExpression(), source, new String[] { "containerView",
                "viewpoint.DragAndDropTarget | the view that sould contain the potential views of the checked elements.", "container", "ecore.EObject | the semantic element of containerView.",
                "viewpoint", "viewpoint.DSemanticDiagram | (deprecated) the current DSemanticDiagram.", "diagram", "viewpoint.DSemanticDiagram | the current DSemanticDiagram.", "sourceView",
                "viewpoint.DSemanticDecorator | (edge only) the source view of the current potential edge.", "source", "ecore.EObject | (edge only) the semantic element of sourceView.", "targetView",
                "viewpoint.DSemanticDecorator | (edge only) the target view of the current potential edge.", "target", "ecore.EObject | (edge only) the semantic element of targetView." });
        addAnnotation(getDiagramElementMapping_SemanticCandidatesExpression(), source, new String[] { "containerView", "viewpoint.DDiagram | the parent view of potential candidates.", "diagram",
                "viewpoint.DDiagram | the current DDiagram.", "viewpoint", "viewpoint.DDiagram | (deprecated) the current DDiagram.", "viewPoint",
                "viewpoint.DDiagram | (deprecated) the current DDiagram." });
        addAnnotation(getDiagramElementMapping_SemanticElements(), source, new String[] { "diagram", "viewpoint.DDiagram | the current DSemanticDiagram.", "view",
                "viewpoint.DDiagramElement | the current view created from the current mapping.", "viewpoint", "viewpoint.DDiagram | (deprecated) the current DSemanticDiagram." });
        addAnnotation(getEdgeMapping_TargetFinderExpression(), source, new String[] { "diagram", "viewpoint.DDiagram | the current DDiagram.", "viewpoint",
                "viewpoint.DDiagram | (deprecated) the current DDiagram.", "viewPoint", "viewpoint.DDiagram | (deprecated) the current DDiagram." });
        addAnnotation(getEdgeMapping_SourceFinderExpression(), source, new String[] { "diagram", "viewpoint.DDiagram | the current DDiagram.", "viewpoint",
                "viewpoint.DDiagram | (deprecated) the current DDiagram.", "viewPoint", "viewpoint.DDiagram | (deprecated) the current DDiagram." });
        addAnnotation(getEdgeMapping_TargetExpression(), source, new String[] { "diagram", "viewpoint.DDiagram | the current DDiagram.", "viewpoint",
                "viewpoint.DDiagram | (deprecated) the current DDiagram.", "viewPoint", "viewpoint.DDiagram | (deprecated) the current DDiagram." });
        addAnnotation(getEdgeMapping_PathExpression(), source, new String[] { "diagram", "viewpoint.DDiagram | the current DDiagram.", "viewpoint",
                "viewpoint.DDiagram | (deprecated) the current DDiagram.", "element", "ecore.EObject | the semantic element  of the current edge.", "source",
                "ecore.EObject | the semantic target of the current source node.", "target", "ecore.EObject | the semantic element of the current target node." });
        addAnnotation(getConditionalStyleDescription_PredicateExpression(), source,
                new String[] { "view", "ecore.EObject | the current view.", "container", "ecore.EObject | the semantic container." });
        addAnnotation(getOrderedTreeLayout_ChildrenExpression(), source, new String[] {});
        addAnnotation(getDecorationDescription_PreconditionExpression(), source, new String[] { "containerView",
                "viewpoint.DSemanticDecorator | the view that would contain the potential views of the checked elements.", "container", "ecore.EObject | the semantic element of the container view.",
                "viewpoint", "viewpoint.DDiagram | (deprecated) the current diagram.", "diagram", "viewpoint.DDiagram | the current diagram." });
        addAnnotation(getVSMElementCustomization_PredicateExpression(), source, new String[] { "view", "ecore.EObject | the current view.", "container", "ecore.EObject | the semantic container." });
        addAnnotation(getEAttributeCustomization_Value(), source, new String[] { "view", "ecore.EObject | the current view.", "container", "ecore.EObject | the semantic container." });
        addAnnotation(getSelectionDescription_CandidatesExpression(), source, new String[] { "diagram", "viewpoint.DDiagram | the current DDiagram.", "containerView",
                "viewpoint.DSemanticDecorator | the view of the container.", "container", "ecore.EObject | the semantic element of the container." });
        addAnnotation(getSelectionDescription_RootExpression(), source, new String[] { "diagram", "viewpoint.DDiagram | the current DDiagram.", "containerView",
                "viewpoint.DSemanticDecorator | the view of the container.", "container", "ecore.EObject | the semantic element of the container." });
        addAnnotation(getSelectionDescription_ChildrenExpression(), source, new String[] { "diagram", "viewpoint.DDiagram | the current DDiagram.", "containerView",
                "viewpoint.DSemanticDecorator | the view of the container.", "container", "ecore.EObject | the semantic element of the container." });
        addAnnotation(getInterpolatedColor_ColorValueComputationExpression(), source, new String[] {});
        addAnnotation(getInterpolatedColor_MinValueComputationExpression(), source, new String[] {});
        addAnnotation(getInterpolatedColor_MaxValueComputationExpression(), source, new String[] {});
        addAnnotation(getColorStep_AssociatedValue(), source, new String[] {});
        addAnnotation(getComputedColor_Red(), source, new String[] {});
        addAnnotation(getComputedColor_Green(), source, new String[] {});
        addAnnotation(getComputedColor_Blue(), source, new String[] {});
    }

} // DescriptionPackageImpl
