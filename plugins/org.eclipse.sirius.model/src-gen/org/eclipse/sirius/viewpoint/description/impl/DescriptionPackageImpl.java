/**
 * Copyright (c) 2007, 2017 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.viewpoint.description.AbstractVariable;
import org.eclipse.sirius.viewpoint.description.AnnotationEntry;
import org.eclipse.sirius.viewpoint.description.ColorDescription;
import org.eclipse.sirius.viewpoint.description.ColorStep;
import org.eclipse.sirius.viewpoint.description.Component;
import org.eclipse.sirius.viewpoint.description.ComputedColor;
import org.eclipse.sirius.viewpoint.description.ConditionalStyleDescription;
import org.eclipse.sirius.viewpoint.description.Customization;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.DAnnotationEntry;
import org.eclipse.sirius.viewpoint.description.DModelElement;
import org.eclipse.sirius.viewpoint.description.DecorationDescription;
import org.eclipse.sirius.viewpoint.description.DecorationDescriptionsSet;
import org.eclipse.sirius.viewpoint.description.DecorationDistributionDirection;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.EAttributeCustomization;
import org.eclipse.sirius.viewpoint.description.EReferenceCustomization;
import org.eclipse.sirius.viewpoint.description.EStructuralFeatureCustomization;
import org.eclipse.sirius.viewpoint.description.EndUserDocumentedElement;
import org.eclipse.sirius.viewpoint.description.Environment;
import org.eclipse.sirius.viewpoint.description.Extension;
import org.eclipse.sirius.viewpoint.description.FeatureExtensionDescription;
import org.eclipse.sirius.viewpoint.description.FixedColor;
import org.eclipse.sirius.viewpoint.description.GenericDecorationDescription;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.IVSMElementCustomization;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.InteractiveVariableDescription;
import org.eclipse.sirius.viewpoint.description.InterpolatedColor;
import org.eclipse.sirius.viewpoint.description.JavaExtension;
import org.eclipse.sirius.viewpoint.description.MetamodelExtensionSetting;
import org.eclipse.sirius.viewpoint.description.PasteTargetDescription;
import org.eclipse.sirius.viewpoint.description.Position;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationImportDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationTemplate;
import org.eclipse.sirius.viewpoint.description.SelectionDescription;
import org.eclipse.sirius.viewpoint.description.SemanticBasedDecoration;
import org.eclipse.sirius.viewpoint.description.SubVariable;
import org.eclipse.sirius.viewpoint.description.SystemColor;
import org.eclipse.sirius.viewpoint.description.SystemColors;
import org.eclipse.sirius.viewpoint.description.SytemColorsPalette;
import org.eclipse.sirius.viewpoint.description.TypedVariable;
import org.eclipse.sirius.viewpoint.description.UserColor;
import org.eclipse.sirius.viewpoint.description.UserColorsPalette;
import org.eclipse.sirius.viewpoint.description.UserFixedColor;
import org.eclipse.sirius.viewpoint.description.VSMElementCustomization;
import org.eclipse.sirius.viewpoint.description.VSMElementCustomizationReuse;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.audit.AuditPackage;
import org.eclipse.sirius.viewpoint.description.audit.impl.AuditPackageImpl;
import org.eclipse.sirius.viewpoint.description.style.StylePackage;
import org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl;
import org.eclipse.sirius.viewpoint.description.validation.ValidationPackage;
import org.eclipse.sirius.viewpoint.description.validation.impl.ValidationPackageImpl;
import org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
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
    private EClass extensionEClass = null;

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
    private EClass abstractMappingImportEClass = null;

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
    private EClass pasteTargetDescriptionEClass = null;

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
    private EClass semanticBasedDecorationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass genericDecorationDescriptionEClass = null;

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
    private EClass abstractVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass subVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass interactiveVariableDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass typedVariableEClass = null;

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
    private EEnum decorationDistributionDirectionEEnum = null;

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
    private EDataType imagePathEDataType = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EDataType uriEDataType = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EDataType translatableMessageEDataType = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
     * EPackage.Registry} by the package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also
     * performs initialization of the package, or returns the registered package, if one already exists. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private DescriptionPackageImpl() {
        super(DescriptionPackage.eNS_URI, DescriptionFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     *
     * <p>
     * This method is used to initialize {@link DescriptionPackage#eINSTANCE} when that field is accessed. Clients
     * should not invoke it directly. Instead, they should simply access that field to obtain the package. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static DescriptionPackage init() {
        if (DescriptionPackageImpl.isInited) {
            return (DescriptionPackage) EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI);
        }

        // Obtain or create and register package
        Object registeredDescriptionPackage = EPackage.Registry.INSTANCE.get(DescriptionPackage.eNS_URI);
        DescriptionPackageImpl theDescriptionPackage = registeredDescriptionPackage instanceof DescriptionPackageImpl ? (DescriptionPackageImpl) registeredDescriptionPackage
                : new DescriptionPackageImpl();

        DescriptionPackageImpl.isInited = true;

        // Initialize simple dependencies
        EcorePackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        Object registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ViewpointPackage.eNS_URI);
        ViewpointPackageImpl theViewpointPackage = (ViewpointPackageImpl) (registeredPackage instanceof ViewpointPackageImpl ? registeredPackage : ViewpointPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(StylePackage.eNS_URI);
        StylePackageImpl theStylePackage = (StylePackageImpl) (registeredPackage instanceof StylePackageImpl ? registeredPackage : StylePackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ToolPackage.eNS_URI);
        ToolPackageImpl theToolPackage = (ToolPackageImpl) (registeredPackage instanceof ToolPackageImpl ? registeredPackage : ToolPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ValidationPackage.eNS_URI);
        ValidationPackageImpl theValidationPackage = (ValidationPackageImpl) (registeredPackage instanceof ValidationPackageImpl ? registeredPackage : ValidationPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(AuditPackage.eNS_URI);
        AuditPackageImpl theAuditPackage = (AuditPackageImpl) (registeredPackage instanceof AuditPackageImpl ? registeredPackage : AuditPackage.eINSTANCE);

        // Create package meta-data objects
        theDescriptionPackage.createPackageContents();
        theViewpointPackage.createPackageContents();
        theStylePackage.createPackageContents();
        theToolPackage.createPackageContents();
        theValidationPackage.createPackageContents();
        theAuditPackage.createPackageContents();

        // Initialize created meta-data
        theDescriptionPackage.initializePackageContents();
        theViewpointPackage.initializePackageContents();
        theStylePackage.initializePackageContents();
        theToolPackage.initializePackageContents();
        theValidationPackage.initializePackageContents();
        theAuditPackage.initializePackageContents();

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
    @Override
    public EClass getGroup() {
        return groupEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getGroup_Name() {
        return (EAttribute) groupEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getGroup_OwnedViewpoints() {
        return (EReference) groupEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getGroup_SystemColorsPalette() {
        return (EReference) groupEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getGroup_UserColorsPalettes() {
        return (EReference) groupEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getGroup_Version() {
        return (EAttribute) groupEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getGroup_Extensions() {
        return (EReference) groupEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getExtension() {
        return extensionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getComponent() {
        return componentEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getViewpoint() {
        return viewpointEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getViewpoint_ModelFileExtension() {
        return (EAttribute) viewpointEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getViewpoint_ValidationSet() {
        return (EReference) viewpointEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getViewpoint_OwnedRepresentations() {
        return (EReference) viewpointEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getViewpoint_OwnedRepresentationExtensions() {
        return (EReference) viewpointEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getViewpoint_OwnedJavaExtensions() {
        return (EReference) viewpointEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getViewpoint_OwnedMMExtensions() {
        return (EReference) viewpointEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getViewpoint_OwnedFeatureExtensions() {
        return (EReference) viewpointEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getViewpoint_Icon() {
        return (EAttribute) viewpointEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getViewpoint_OwnedTemplates() {
        return (EReference) viewpointEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getViewpoint_Conflicts() {
        return (EAttribute) viewpointEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getViewpoint_Reuses() {
        return (EAttribute) viewpointEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getViewpoint_Customizes() {
        return (EAttribute) viewpointEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getFeatureExtensionDescription() {
        return featureExtensionDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getRepresentationDescription() {
        return representationDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getRepresentationDescription_TitleExpression() {
        return (EAttribute) representationDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getRepresentationDescription_Initialisation() {
        return (EAttribute) representationDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getRepresentationDescription_Metamodel() {
        return (EReference) representationDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getRepresentationDescription_ShowOnStartup() {
        return (EAttribute) representationDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getRepresentationTemplate() {
        return representationTemplateEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getRepresentationTemplate_Name() {
        return (EAttribute) representationTemplateEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getRepresentationTemplate_OwnedRepresentations() {
        return (EReference) representationTemplateEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getRepresentationImportDescription() {
        return representationImportDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getRepresentationExtensionDescription() {
        return representationExtensionDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getRepresentationExtensionDescription_Name() {
        return (EAttribute) representationExtensionDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getRepresentationExtensionDescription_ViewpointURI() {
        return (EAttribute) representationExtensionDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getRepresentationExtensionDescription_RepresentationName() {
        return (EAttribute) representationExtensionDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getRepresentationExtensionDescription_Metamodel() {
        return (EReference) representationExtensionDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getMetamodelExtensionSetting() {
        return metamodelExtensionSettingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getMetamodelExtensionSetting_ExtensionGroup() {
        return (EReference) metamodelExtensionSettingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getJavaExtension() {
        return javaExtensionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getJavaExtension_QualifiedClassName() {
        return (EAttribute) javaExtensionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getRepresentationElementMapping() {
        return representationElementMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getRepresentationElementMapping_DetailDescriptions() {
        return (EReference) representationElementMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getRepresentationElementMapping_NavigationDescriptions() {
        return (EReference) representationElementMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getAbstractMappingImport() {
        return abstractMappingImportEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractMappingImport_HideSubMappings() {
        return (EAttribute) abstractMappingImportEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractMappingImport_InheritsAncestorFilters() {
        return (EAttribute) abstractMappingImportEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDocumentedElement() {
        return documentedElementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDocumentedElement_Documentation() {
        return (EAttribute) documentedElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDModelElement() {
        return dModelElementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDModelElement_EAnnotations() {
        return (EReference) dModelElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDAnnotation() {
        return dAnnotationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDAnnotation_Source() {
        return (EAttribute) dAnnotationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDAnnotation_Details() {
        return (EReference) dAnnotationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDAnnotation_References() {
        return (EReference) dAnnotationEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getConditionalStyleDescription() {
        return conditionalStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getConditionalStyleDescription_PredicateExpression() {
        return (EAttribute) conditionalStyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getPasteTargetDescription() {
        return pasteTargetDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getPasteTargetDescription_PasteDescriptions() {
        return (EReference) pasteTargetDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDecorationDescriptionsSet() {
        return decorationDescriptionsSetEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDecorationDescriptionsSet_DecorationDescriptions() {
        return (EReference) decorationDescriptionsSetEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDecorationDescription() {
        return decorationDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDecorationDescription_Name() {
        return (EAttribute) decorationDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDecorationDescription_Position() {
        return (EAttribute) decorationDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDecorationDescription_DistributionDirection() {
        return (EAttribute) decorationDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDecorationDescription_PreconditionExpression() {
        return (EAttribute) decorationDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDecorationDescription_ImageExpression() {
        return (EAttribute) decorationDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDecorationDescription_TooltipExpression() {
        return (EAttribute) decorationDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getSemanticBasedDecoration() {
        return semanticBasedDecorationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getSemanticBasedDecoration_DomainClass() {
        return (EAttribute) semanticBasedDecorationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getGenericDecorationDescription() {
        return genericDecorationDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getCustomization() {
        return customizationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getCustomization_VsmElementCustomizations() {
        return (EReference) customizationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getIVSMElementCustomization() {
        return ivsmElementCustomizationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getVSMElementCustomization() {
        return vsmElementCustomizationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getVSMElementCustomization_PredicateExpression() {
        return (EAttribute) vsmElementCustomizationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getVSMElementCustomization_FeatureCustomizations() {
        return (EReference) vsmElementCustomizationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getVSMElementCustomizationReuse() {
        return vsmElementCustomizationReuseEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getVSMElementCustomizationReuse_Reuse() {
        return (EReference) vsmElementCustomizationReuseEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getVSMElementCustomizationReuse_AppliedOn() {
        return (EReference) vsmElementCustomizationReuseEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getEStructuralFeatureCustomization() {
        return eStructuralFeatureCustomizationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEStructuralFeatureCustomization_AppliedOn() {
        return (EReference) eStructuralFeatureCustomizationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEStructuralFeatureCustomization_ApplyOnAll() {
        return (EAttribute) eStructuralFeatureCustomizationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getEAttributeCustomization() {
        return eAttributeCustomizationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEAttributeCustomization_AttributeName() {
        return (EAttribute) eAttributeCustomizationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEAttributeCustomization_Value() {
        return (EAttribute) eAttributeCustomizationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getEReferenceCustomization() {
        return eReferenceCustomizationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEReferenceCustomization_ReferenceName() {
        return (EAttribute) eReferenceCustomizationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEReferenceCustomization_Value() {
        return (EReference) eReferenceCustomizationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getSelectionDescription() {
        return selectionDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getSelectionDescription_CandidatesExpression() {
        return (EAttribute) selectionDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getSelectionDescription_Multiple() {
        return (EAttribute) selectionDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getSelectionDescription_Tree() {
        return (EAttribute) selectionDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getSelectionDescription_RootExpression() {
        return (EAttribute) selectionDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getSelectionDescription_ChildrenExpression() {
        return (EAttribute) selectionDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getSelectionDescription_Message() {
        return (EAttribute) selectionDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getColorDescription() {
        return colorDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getSystemColor() {
        return systemColorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getSystemColor_Name() {
        return (EAttribute) systemColorEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getInterpolatedColor() {
        return interpolatedColorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getInterpolatedColor_ColorValueComputationExpression() {
        return (EAttribute) interpolatedColorEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getInterpolatedColor_MinValueComputationExpression() {
        return (EAttribute) interpolatedColorEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getInterpolatedColor_MaxValueComputationExpression() {
        return (EAttribute) interpolatedColorEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getInterpolatedColor_ColorSteps() {
        return (EReference) interpolatedColorEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getColorStep() {
        return colorStepEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getColorStep_AssociatedValue() {
        return (EAttribute) colorStepEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getColorStep_AssociatedColor() {
        return (EReference) colorStepEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getFixedColor() {
        return fixedColorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getFixedColor_Red() {
        return (EAttribute) fixedColorEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getFixedColor_Green() {
        return (EAttribute) fixedColorEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getFixedColor_Blue() {
        return (EAttribute) fixedColorEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getUserFixedColor() {
        return userFixedColorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getUserColor() {
        return userColorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getUserColor_Name() {
        return (EAttribute) userColorEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getEnvironment() {
        return environmentEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEnvironment_SystemColors() {
        return (EReference) environmentEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEnvironment_DefaultTools() {
        return (EReference) environmentEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEnvironment_LabelBorderStyles() {
        return (EReference) environmentEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getSytemColorsPalette() {
        return sytemColorsPaletteEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getSytemColorsPalette_Entries() {
        return (EReference) sytemColorsPaletteEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getUserColorsPalette() {
        return userColorsPaletteEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getUserColorsPalette_Name() {
        return (EAttribute) userColorsPaletteEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getUserColorsPalette_Entries() {
        return (EReference) userColorsPaletteEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getAnnotationEntry() {
        return annotationEntryEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAnnotationEntry_Source() {
        return (EAttribute) annotationEntryEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAnnotationEntry_Data() {
        return (EReference) annotationEntryEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getEndUserDocumentedElement() {
        return endUserDocumentedElementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEndUserDocumentedElement_EndUserDocumentation() {
        return (EAttribute) endUserDocumentedElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getIdentifiedElement() {
        return identifiedElementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getIdentifiedElement_Name() {
        return (EAttribute) identifiedElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getIdentifiedElement_Label() {
        return (EAttribute) identifiedElementEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getComputedColor() {
        return computedColorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getComputedColor_Red() {
        return (EAttribute) computedColorEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getComputedColor_Green() {
        return (EAttribute) computedColorEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getComputedColor_Blue() {
        return (EAttribute) computedColorEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDAnnotationEntry() {
        return dAnnotationEntryEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDAnnotationEntry_Source() {
        return (EAttribute) dAnnotationEntryEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDAnnotationEntry_Details() {
        return (EAttribute) dAnnotationEntryEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getAbstractVariable() {
        return abstractVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractVariable_Name() {
        return (EAttribute) abstractVariableEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getSubVariable() {
        return subVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getInteractiveVariableDescription() {
        return interactiveVariableDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getInteractiveVariableDescription_UserDocumentation() {
        return (EAttribute) interactiveVariableDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getTypedVariable() {
        return typedVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getTypedVariable_DefaultValueExpression() {
        return (EAttribute) typedVariableEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getTypedVariable_ValueType() {
        return (EReference) typedVariableEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EEnum getPosition() {
        return positionEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EEnum getDecorationDistributionDirection() {
        return decorationDistributionDirectionEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EEnum getSystemColors() {
        return systemColorsEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EDataType getTypeName() {
        return typeNameEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EDataType getInterpretedExpression() {
        return interpretedExpressionEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EDataType getFeatureName() {
        return featureNameEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EDataType getImagePath() {
        return imagePathEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EDataType getURI() {
        return uriEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EDataType getTranslatableMessage() {
        return translatableMessageEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
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
     * Creates the meta-model objects for the package. This method is guarded to have no affect on any invocation but
     * its first. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public void createPackageContents() {
        if (isCreated) {
            return;
        }
        isCreated = true;

        // Create classes and their features
        groupEClass = createEClass(DescriptionPackage.GROUP);
        createEAttribute(groupEClass, DescriptionPackage.GROUP__NAME);
        createEReference(groupEClass, DescriptionPackage.GROUP__OWNED_VIEWPOINTS);
        createEReference(groupEClass, DescriptionPackage.GROUP__SYSTEM_COLORS_PALETTE);
        createEReference(groupEClass, DescriptionPackage.GROUP__USER_COLORS_PALETTES);
        createEAttribute(groupEClass, DescriptionPackage.GROUP__VERSION);
        createEReference(groupEClass, DescriptionPackage.GROUP__EXTENSIONS);

        extensionEClass = createEClass(DescriptionPackage.EXTENSION);

        componentEClass = createEClass(DescriptionPackage.COMPONENT);

        viewpointEClass = createEClass(DescriptionPackage.VIEWPOINT);
        createEAttribute(viewpointEClass, DescriptionPackage.VIEWPOINT__MODEL_FILE_EXTENSION);
        createEReference(viewpointEClass, DescriptionPackage.VIEWPOINT__VALIDATION_SET);
        createEReference(viewpointEClass, DescriptionPackage.VIEWPOINT__OWNED_REPRESENTATIONS);
        createEReference(viewpointEClass, DescriptionPackage.VIEWPOINT__OWNED_REPRESENTATION_EXTENSIONS);
        createEReference(viewpointEClass, DescriptionPackage.VIEWPOINT__OWNED_JAVA_EXTENSIONS);
        createEReference(viewpointEClass, DescriptionPackage.VIEWPOINT__OWNED_MM_EXTENSIONS);
        createEReference(viewpointEClass, DescriptionPackage.VIEWPOINT__OWNED_FEATURE_EXTENSIONS);
        createEAttribute(viewpointEClass, DescriptionPackage.VIEWPOINT__ICON);
        createEReference(viewpointEClass, DescriptionPackage.VIEWPOINT__OWNED_TEMPLATES);
        createEAttribute(viewpointEClass, DescriptionPackage.VIEWPOINT__CONFLICTS);
        createEAttribute(viewpointEClass, DescriptionPackage.VIEWPOINT__REUSES);
        createEAttribute(viewpointEClass, DescriptionPackage.VIEWPOINT__CUSTOMIZES);

        featureExtensionDescriptionEClass = createEClass(DescriptionPackage.FEATURE_EXTENSION_DESCRIPTION);

        representationDescriptionEClass = createEClass(DescriptionPackage.REPRESENTATION_DESCRIPTION);
        createEAttribute(representationDescriptionEClass, DescriptionPackage.REPRESENTATION_DESCRIPTION__TITLE_EXPRESSION);
        createEAttribute(representationDescriptionEClass, DescriptionPackage.REPRESENTATION_DESCRIPTION__INITIALISATION);
        createEReference(representationDescriptionEClass, DescriptionPackage.REPRESENTATION_DESCRIPTION__METAMODEL);
        createEAttribute(representationDescriptionEClass, DescriptionPackage.REPRESENTATION_DESCRIPTION__SHOW_ON_STARTUP);

        representationTemplateEClass = createEClass(DescriptionPackage.REPRESENTATION_TEMPLATE);
        createEAttribute(representationTemplateEClass, DescriptionPackage.REPRESENTATION_TEMPLATE__NAME);
        createEReference(representationTemplateEClass, DescriptionPackage.REPRESENTATION_TEMPLATE__OWNED_REPRESENTATIONS);

        representationImportDescriptionEClass = createEClass(DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION);

        representationExtensionDescriptionEClass = createEClass(DescriptionPackage.REPRESENTATION_EXTENSION_DESCRIPTION);
        createEAttribute(representationExtensionDescriptionEClass, DescriptionPackage.REPRESENTATION_EXTENSION_DESCRIPTION__NAME);
        createEAttribute(representationExtensionDescriptionEClass, DescriptionPackage.REPRESENTATION_EXTENSION_DESCRIPTION__VIEWPOINT_URI);
        createEAttribute(representationExtensionDescriptionEClass, DescriptionPackage.REPRESENTATION_EXTENSION_DESCRIPTION__REPRESENTATION_NAME);
        createEReference(representationExtensionDescriptionEClass, DescriptionPackage.REPRESENTATION_EXTENSION_DESCRIPTION__METAMODEL);

        metamodelExtensionSettingEClass = createEClass(DescriptionPackage.METAMODEL_EXTENSION_SETTING);
        createEReference(metamodelExtensionSettingEClass, DescriptionPackage.METAMODEL_EXTENSION_SETTING__EXTENSION_GROUP);

        javaExtensionEClass = createEClass(DescriptionPackage.JAVA_EXTENSION);
        createEAttribute(javaExtensionEClass, DescriptionPackage.JAVA_EXTENSION__QUALIFIED_CLASS_NAME);

        representationElementMappingEClass = createEClass(DescriptionPackage.REPRESENTATION_ELEMENT_MAPPING);
        createEReference(representationElementMappingEClass, DescriptionPackage.REPRESENTATION_ELEMENT_MAPPING__DETAIL_DESCRIPTIONS);
        createEReference(representationElementMappingEClass, DescriptionPackage.REPRESENTATION_ELEMENT_MAPPING__NAVIGATION_DESCRIPTIONS);

        abstractMappingImportEClass = createEClass(DescriptionPackage.ABSTRACT_MAPPING_IMPORT);
        createEAttribute(abstractMappingImportEClass, DescriptionPackage.ABSTRACT_MAPPING_IMPORT__HIDE_SUB_MAPPINGS);
        createEAttribute(abstractMappingImportEClass, DescriptionPackage.ABSTRACT_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS);

        documentedElementEClass = createEClass(DescriptionPackage.DOCUMENTED_ELEMENT);
        createEAttribute(documentedElementEClass, DescriptionPackage.DOCUMENTED_ELEMENT__DOCUMENTATION);

        dModelElementEClass = createEClass(DescriptionPackage.DMODEL_ELEMENT);
        createEReference(dModelElementEClass, DescriptionPackage.DMODEL_ELEMENT__EANNOTATIONS);

        dAnnotationEClass = createEClass(DescriptionPackage.DANNOTATION);
        createEAttribute(dAnnotationEClass, DescriptionPackage.DANNOTATION__SOURCE);
        createEReference(dAnnotationEClass, DescriptionPackage.DANNOTATION__DETAILS);
        createEReference(dAnnotationEClass, DescriptionPackage.DANNOTATION__REFERENCES);

        conditionalStyleDescriptionEClass = createEClass(DescriptionPackage.CONDITIONAL_STYLE_DESCRIPTION);
        createEAttribute(conditionalStyleDescriptionEClass, DescriptionPackage.CONDITIONAL_STYLE_DESCRIPTION__PREDICATE_EXPRESSION);

        pasteTargetDescriptionEClass = createEClass(DescriptionPackage.PASTE_TARGET_DESCRIPTION);
        createEReference(pasteTargetDescriptionEClass, DescriptionPackage.PASTE_TARGET_DESCRIPTION__PASTE_DESCRIPTIONS);

        decorationDescriptionsSetEClass = createEClass(DescriptionPackage.DECORATION_DESCRIPTIONS_SET);
        createEReference(decorationDescriptionsSetEClass, DescriptionPackage.DECORATION_DESCRIPTIONS_SET__DECORATION_DESCRIPTIONS);

        decorationDescriptionEClass = createEClass(DescriptionPackage.DECORATION_DESCRIPTION);
        createEAttribute(decorationDescriptionEClass, DescriptionPackage.DECORATION_DESCRIPTION__NAME);
        createEAttribute(decorationDescriptionEClass, DescriptionPackage.DECORATION_DESCRIPTION__POSITION);
        createEAttribute(decorationDescriptionEClass, DescriptionPackage.DECORATION_DESCRIPTION__DISTRIBUTION_DIRECTION);
        createEAttribute(decorationDescriptionEClass, DescriptionPackage.DECORATION_DESCRIPTION__PRECONDITION_EXPRESSION);
        createEAttribute(decorationDescriptionEClass, DescriptionPackage.DECORATION_DESCRIPTION__IMAGE_EXPRESSION);
        createEAttribute(decorationDescriptionEClass, DescriptionPackage.DECORATION_DESCRIPTION__TOOLTIP_EXPRESSION);

        semanticBasedDecorationEClass = createEClass(DescriptionPackage.SEMANTIC_BASED_DECORATION);
        createEAttribute(semanticBasedDecorationEClass, DescriptionPackage.SEMANTIC_BASED_DECORATION__DOMAIN_CLASS);

        genericDecorationDescriptionEClass = createEClass(DescriptionPackage.GENERIC_DECORATION_DESCRIPTION);

        customizationEClass = createEClass(DescriptionPackage.CUSTOMIZATION);
        createEReference(customizationEClass, DescriptionPackage.CUSTOMIZATION__VSM_ELEMENT_CUSTOMIZATIONS);

        ivsmElementCustomizationEClass = createEClass(DescriptionPackage.IVSM_ELEMENT_CUSTOMIZATION);

        vsmElementCustomizationEClass = createEClass(DescriptionPackage.VSM_ELEMENT_CUSTOMIZATION);
        createEAttribute(vsmElementCustomizationEClass, DescriptionPackage.VSM_ELEMENT_CUSTOMIZATION__PREDICATE_EXPRESSION);
        createEReference(vsmElementCustomizationEClass, DescriptionPackage.VSM_ELEMENT_CUSTOMIZATION__FEATURE_CUSTOMIZATIONS);

        vsmElementCustomizationReuseEClass = createEClass(DescriptionPackage.VSM_ELEMENT_CUSTOMIZATION_REUSE);
        createEReference(vsmElementCustomizationReuseEClass, DescriptionPackage.VSM_ELEMENT_CUSTOMIZATION_REUSE__REUSE);
        createEReference(vsmElementCustomizationReuseEClass, DescriptionPackage.VSM_ELEMENT_CUSTOMIZATION_REUSE__APPLIED_ON);

        eStructuralFeatureCustomizationEClass = createEClass(DescriptionPackage.ESTRUCTURAL_FEATURE_CUSTOMIZATION);
        createEReference(eStructuralFeatureCustomizationEClass, DescriptionPackage.ESTRUCTURAL_FEATURE_CUSTOMIZATION__APPLIED_ON);
        createEAttribute(eStructuralFeatureCustomizationEClass, DescriptionPackage.ESTRUCTURAL_FEATURE_CUSTOMIZATION__APPLY_ON_ALL);

        eAttributeCustomizationEClass = createEClass(DescriptionPackage.EATTRIBUTE_CUSTOMIZATION);
        createEAttribute(eAttributeCustomizationEClass, DescriptionPackage.EATTRIBUTE_CUSTOMIZATION__ATTRIBUTE_NAME);
        createEAttribute(eAttributeCustomizationEClass, DescriptionPackage.EATTRIBUTE_CUSTOMIZATION__VALUE);

        eReferenceCustomizationEClass = createEClass(DescriptionPackage.EREFERENCE_CUSTOMIZATION);
        createEAttribute(eReferenceCustomizationEClass, DescriptionPackage.EREFERENCE_CUSTOMIZATION__REFERENCE_NAME);
        createEReference(eReferenceCustomizationEClass, DescriptionPackage.EREFERENCE_CUSTOMIZATION__VALUE);

        selectionDescriptionEClass = createEClass(DescriptionPackage.SELECTION_DESCRIPTION);
        createEAttribute(selectionDescriptionEClass, DescriptionPackage.SELECTION_DESCRIPTION__CANDIDATES_EXPRESSION);
        createEAttribute(selectionDescriptionEClass, DescriptionPackage.SELECTION_DESCRIPTION__MULTIPLE);
        createEAttribute(selectionDescriptionEClass, DescriptionPackage.SELECTION_DESCRIPTION__TREE);
        createEAttribute(selectionDescriptionEClass, DescriptionPackage.SELECTION_DESCRIPTION__ROOT_EXPRESSION);
        createEAttribute(selectionDescriptionEClass, DescriptionPackage.SELECTION_DESCRIPTION__CHILDREN_EXPRESSION);
        createEAttribute(selectionDescriptionEClass, DescriptionPackage.SELECTION_DESCRIPTION__MESSAGE);

        colorDescriptionEClass = createEClass(DescriptionPackage.COLOR_DESCRIPTION);

        systemColorEClass = createEClass(DescriptionPackage.SYSTEM_COLOR);
        createEAttribute(systemColorEClass, DescriptionPackage.SYSTEM_COLOR__NAME);

        interpolatedColorEClass = createEClass(DescriptionPackage.INTERPOLATED_COLOR);
        createEAttribute(interpolatedColorEClass, DescriptionPackage.INTERPOLATED_COLOR__COLOR_VALUE_COMPUTATION_EXPRESSION);
        createEAttribute(interpolatedColorEClass, DescriptionPackage.INTERPOLATED_COLOR__MIN_VALUE_COMPUTATION_EXPRESSION);
        createEAttribute(interpolatedColorEClass, DescriptionPackage.INTERPOLATED_COLOR__MAX_VALUE_COMPUTATION_EXPRESSION);
        createEReference(interpolatedColorEClass, DescriptionPackage.INTERPOLATED_COLOR__COLOR_STEPS);

        colorStepEClass = createEClass(DescriptionPackage.COLOR_STEP);
        createEAttribute(colorStepEClass, DescriptionPackage.COLOR_STEP__ASSOCIATED_VALUE);
        createEReference(colorStepEClass, DescriptionPackage.COLOR_STEP__ASSOCIATED_COLOR);

        fixedColorEClass = createEClass(DescriptionPackage.FIXED_COLOR);
        createEAttribute(fixedColorEClass, DescriptionPackage.FIXED_COLOR__RED);
        createEAttribute(fixedColorEClass, DescriptionPackage.FIXED_COLOR__GREEN);
        createEAttribute(fixedColorEClass, DescriptionPackage.FIXED_COLOR__BLUE);

        userFixedColorEClass = createEClass(DescriptionPackage.USER_FIXED_COLOR);

        userColorEClass = createEClass(DescriptionPackage.USER_COLOR);
        createEAttribute(userColorEClass, DescriptionPackage.USER_COLOR__NAME);

        environmentEClass = createEClass(DescriptionPackage.ENVIRONMENT);
        createEReference(environmentEClass, DescriptionPackage.ENVIRONMENT__SYSTEM_COLORS);
        createEReference(environmentEClass, DescriptionPackage.ENVIRONMENT__DEFAULT_TOOLS);
        createEReference(environmentEClass, DescriptionPackage.ENVIRONMENT__LABEL_BORDER_STYLES);

        sytemColorsPaletteEClass = createEClass(DescriptionPackage.SYTEM_COLORS_PALETTE);
        createEReference(sytemColorsPaletteEClass, DescriptionPackage.SYTEM_COLORS_PALETTE__ENTRIES);

        userColorsPaletteEClass = createEClass(DescriptionPackage.USER_COLORS_PALETTE);
        createEAttribute(userColorsPaletteEClass, DescriptionPackage.USER_COLORS_PALETTE__NAME);
        createEReference(userColorsPaletteEClass, DescriptionPackage.USER_COLORS_PALETTE__ENTRIES);

        annotationEntryEClass = createEClass(DescriptionPackage.ANNOTATION_ENTRY);
        createEAttribute(annotationEntryEClass, DescriptionPackage.ANNOTATION_ENTRY__SOURCE);
        createEReference(annotationEntryEClass, DescriptionPackage.ANNOTATION_ENTRY__DATA);

        endUserDocumentedElementEClass = createEClass(DescriptionPackage.END_USER_DOCUMENTED_ELEMENT);
        createEAttribute(endUserDocumentedElementEClass, DescriptionPackage.END_USER_DOCUMENTED_ELEMENT__END_USER_DOCUMENTATION);

        identifiedElementEClass = createEClass(DescriptionPackage.IDENTIFIED_ELEMENT);
        createEAttribute(identifiedElementEClass, DescriptionPackage.IDENTIFIED_ELEMENT__NAME);
        createEAttribute(identifiedElementEClass, DescriptionPackage.IDENTIFIED_ELEMENT__LABEL);

        computedColorEClass = createEClass(DescriptionPackage.COMPUTED_COLOR);
        createEAttribute(computedColorEClass, DescriptionPackage.COMPUTED_COLOR__RED);
        createEAttribute(computedColorEClass, DescriptionPackage.COMPUTED_COLOR__GREEN);
        createEAttribute(computedColorEClass, DescriptionPackage.COMPUTED_COLOR__BLUE);

        dAnnotationEntryEClass = createEClass(DescriptionPackage.DANNOTATION_ENTRY);
        createEAttribute(dAnnotationEntryEClass, DescriptionPackage.DANNOTATION_ENTRY__SOURCE);
        createEAttribute(dAnnotationEntryEClass, DescriptionPackage.DANNOTATION_ENTRY__DETAILS);

        abstractVariableEClass = createEClass(DescriptionPackage.ABSTRACT_VARIABLE);
        createEAttribute(abstractVariableEClass, DescriptionPackage.ABSTRACT_VARIABLE__NAME);

        subVariableEClass = createEClass(DescriptionPackage.SUB_VARIABLE);

        interactiveVariableDescriptionEClass = createEClass(DescriptionPackage.INTERACTIVE_VARIABLE_DESCRIPTION);
        createEAttribute(interactiveVariableDescriptionEClass, DescriptionPackage.INTERACTIVE_VARIABLE_DESCRIPTION__USER_DOCUMENTATION);

        typedVariableEClass = createEClass(DescriptionPackage.TYPED_VARIABLE);
        createEAttribute(typedVariableEClass, DescriptionPackage.TYPED_VARIABLE__DEFAULT_VALUE_EXPRESSION);
        createEReference(typedVariableEClass, DescriptionPackage.TYPED_VARIABLE__VALUE_TYPE);

        // Create enums
        positionEEnum = createEEnum(DescriptionPackage.POSITION);
        decorationDistributionDirectionEEnum = createEEnum(DescriptionPackage.DECORATION_DISTRIBUTION_DIRECTION);
        systemColorsEEnum = createEEnum(DescriptionPackage.SYSTEM_COLORS);

        // Create data types
        typeNameEDataType = createEDataType(DescriptionPackage.TYPE_NAME);
        interpretedExpressionEDataType = createEDataType(DescriptionPackage.INTERPRETED_EXPRESSION);
        featureNameEDataType = createEDataType(DescriptionPackage.FEATURE_NAME);
        imagePathEDataType = createEDataType(DescriptionPackage.IMAGE_PATH);
        uriEDataType = createEDataType(DescriptionPackage.URI);
        translatableMessageEDataType = createEDataType(DescriptionPackage.TRANSLATABLE_MESSAGE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model. This method is guarded to have no affect on any
     * invocation but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized) {
            return;
        }
        isInitialized = true;

        // Initialize package
        setName(DescriptionPackage.eNAME);
        setNsPrefix(DescriptionPackage.eNS_PREFIX);
        setNsURI(DescriptionPackage.eNS_URI);

        // Obtain other dependent packages
        StylePackage theStylePackage = (StylePackage) EPackage.Registry.INSTANCE.getEPackage(StylePackage.eNS_URI);
        ToolPackage theToolPackage = (ToolPackage) EPackage.Registry.INSTANCE.getEPackage(ToolPackage.eNS_URI);
        ValidationPackage theValidationPackage = (ValidationPackage) EPackage.Registry.INSTANCE.getEPackage(ValidationPackage.eNS_URI);
        AuditPackage theAuditPackage = (AuditPackage) EPackage.Registry.INSTANCE.getEPackage(AuditPackage.eNS_URI);
        EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
        ViewpointPackage theViewpointPackage = (ViewpointPackage) EPackage.Registry.INSTANCE.getEPackage(ViewpointPackage.eNS_URI);

        // Add subpackages
        getESubpackages().add(theStylePackage);
        getESubpackages().add(theToolPackage);
        getESubpackages().add(theValidationPackage);
        getESubpackages().add(theAuditPackage);

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
        representationElementMappingEClass.getESuperTypes().add(this.getIdentifiedElement());
        dAnnotationEClass.getESuperTypes().add(theViewpointPackage.getIdentifiedElement());
        semanticBasedDecorationEClass.getESuperTypes().add(this.getDecorationDescription());
        genericDecorationDescriptionEClass.getESuperTypes().add(this.getDecorationDescription());
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
        annotationEntryEClass.getESuperTypes().add(theViewpointPackage.getIdentifiedElement());
        computedColorEClass.getESuperTypes().add(this.getUserColor());
        computedColorEClass.getESuperTypes().add(this.getColorDescription());
        dAnnotationEntryEClass.getESuperTypes().add(theViewpointPackage.getIdentifiedElement());
        subVariableEClass.getESuperTypes().add(this.getAbstractVariable());
        typedVariableEClass.getESuperTypes().add(this.getInteractiveVariableDescription());
        typedVariableEClass.getESuperTypes().add(this.getSubVariable());

        // Initialize classes and features; add operations and parameters
        initEClass(groupEClass, Group.class, "Group", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getGroup_Name(), theEcorePackage.getEString(), "name", "", 0, 1, Group.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getGroup_OwnedViewpoints(), this.getViewpoint(), null, "ownedViewpoints", null, 0, -1, Group.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        getGroup_OwnedViewpoints().getEKeys().add(this.getIdentifiedElement_Name());
        initEReference(getGroup_SystemColorsPalette(), this.getSytemColorsPalette(), null, "systemColorsPalette", null, 1, 1, Group.class, EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getGroup_UserColorsPalettes(), this.getUserColorsPalette(), null, "userColorsPalettes", null, 0, -1, Group.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        getGroup_UserColorsPalettes().getEKeys().add(this.getUserColorsPalette_Name());
        initEAttribute(getGroup_Version(), theEcorePackage.getEString(), "version", null, 0, 1, Group.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getGroup_Extensions(), this.getExtension(), null, "extensions", null, 0, -1, Group.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(extensionEClass, Extension.class, "Extension", EPackageImpl.IS_ABSTRACT, EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(componentEClass, Component.class, "Component", EPackageImpl.IS_ABSTRACT, EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(viewpointEClass, Viewpoint.class, "Viewpoint", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getViewpoint_ModelFileExtension(), theEcorePackage.getEString(), "modelFileExtension", "*", 0, 1, Viewpoint.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getViewpoint_ValidationSet(), theValidationPackage.getValidationSet(), null, "validationSet", null, 0, 1, Viewpoint.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        getViewpoint_ValidationSet().getEKeys().add(theValidationPackage.getValidationSet_Name());
        initEReference(getViewpoint_OwnedRepresentations(), this.getRepresentationDescription(), null, "ownedRepresentations", null, 0, -1, Viewpoint.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        getViewpoint_OwnedRepresentations().getEKeys().add(this.getIdentifiedElement_Name());
        initEReference(getViewpoint_OwnedRepresentationExtensions(), this.getRepresentationExtensionDescription(), null, "ownedRepresentationExtensions", null, 0, -1, Viewpoint.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        getViewpoint_OwnedRepresentationExtensions().getEKeys().add(this.getRepresentationExtensionDescription_Name());
        initEReference(getViewpoint_OwnedJavaExtensions(), this.getJavaExtension(), null, "ownedJavaExtensions", null, 0, -1, Viewpoint.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getViewpoint_OwnedMMExtensions(), this.getMetamodelExtensionSetting(), null, "ownedMMExtensions", null, 0, -1, Viewpoint.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getViewpoint_OwnedFeatureExtensions(), this.getFeatureExtensionDescription(), null, "ownedFeatureExtensions", null, 0, -1, Viewpoint.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getViewpoint_Icon(), this.getImagePath(), "icon", null, 0, 1, Viewpoint.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getViewpoint_OwnedTemplates(), this.getRepresentationTemplate(), null, "ownedTemplates", null, 0, -1, Viewpoint.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        getViewpoint_OwnedTemplates().getEKeys().add(this.getRepresentationTemplate_Name());
        initEAttribute(getViewpoint_Conflicts(), this.getURI(), "conflicts", null, 0, -1, Viewpoint.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getViewpoint_Reuses(), this.getURI(), "reuses", null, 0, -1, Viewpoint.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getViewpoint_Customizes(), this.getURI(), "customizes", null, 0, -1, Viewpoint.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        EOperation op = addEOperation(viewpointEClass, null, "initView", 0, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        addEParameter(op, theEcorePackage.getEObject(), "model", 0, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(featureExtensionDescriptionEClass, FeatureExtensionDescription.class, "FeatureExtensionDescription", EPackageImpl.IS_ABSTRACT, EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);

        initEClass(representationDescriptionEClass, RepresentationDescription.class, "RepresentationDescription", EPackageImpl.IS_ABSTRACT, EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getRepresentationDescription_TitleExpression(), this.getInterpretedExpression(), "titleExpression", "", 0, 1, RepresentationDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getRepresentationDescription_Initialisation(), theEcorePackage.getEBoolean(), "initialisation", null, 1, 1, RepresentationDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getRepresentationDescription_Metamodel(), theEcorePackage.getEPackage(), null, "metamodel", null, 0, -1, RepresentationDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getRepresentationDescription_ShowOnStartup(), theEcorePackage.getEBoolean(), "showOnStartup", null, 0, 1, RepresentationDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(representationTemplateEClass, RepresentationTemplate.class, "RepresentationTemplate", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getRepresentationTemplate_Name(), theEcorePackage.getEString(), "name", null, 1, 1, RepresentationTemplate.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getRepresentationTemplate_OwnedRepresentations(), this.getRepresentationDescription(), null, "ownedRepresentations", null, 0, -1, RepresentationTemplate.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(representationImportDescriptionEClass, RepresentationImportDescription.class, "RepresentationImportDescription", EPackageImpl.IS_ABSTRACT, EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);

        initEClass(representationExtensionDescriptionEClass, RepresentationExtensionDescription.class, "RepresentationExtensionDescription", EPackageImpl.IS_ABSTRACT, EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getRepresentationExtensionDescription_Name(), theEcorePackage.getEString(), "name", null, 1, 1, RepresentationExtensionDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getRepresentationExtensionDescription_ViewpointURI(), theEcorePackage.getEString(), "viewpointURI", null, 1, 1, RepresentationExtensionDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getRepresentationExtensionDescription_RepresentationName(), theEcorePackage.getEString(), "representationName", null, 1, 1, RepresentationExtensionDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getRepresentationExtensionDescription_Metamodel(), theEcorePackage.getEPackage(), null, "metamodel", null, 0, -1, RepresentationExtensionDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(metamodelExtensionSettingEClass, MetamodelExtensionSetting.class, "MetamodelExtensionSetting", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getMetamodelExtensionSetting_ExtensionGroup(), theEcorePackage.getEObject(), null, "extensionGroup", null, 0, 1, MetamodelExtensionSetting.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(javaExtensionEClass, JavaExtension.class, "JavaExtension", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getJavaExtension_QualifiedClassName(), theEcorePackage.getEString(), "qualifiedClassName", null, 1, 1, JavaExtension.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(representationElementMappingEClass, RepresentationElementMapping.class, "RepresentationElementMapping", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getRepresentationElementMapping_DetailDescriptions(), theToolPackage.getRepresentationCreationDescription(), null, "detailDescriptions", null, 0, -1, //$NON-NLS-1$
                RepresentationElementMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES,
                !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getRepresentationElementMapping_NavigationDescriptions(), theToolPackage.getRepresentationNavigationDescription(), null, "navigationDescriptions", null, 0, -1, //$NON-NLS-1$
                RepresentationElementMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES,
                !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(abstractMappingImportEClass, AbstractMappingImport.class, "AbstractMappingImport", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getAbstractMappingImport_HideSubMappings(), theEcorePackage.getEBoolean(), "hideSubMappings", "false", 0, 1, AbstractMappingImport.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getAbstractMappingImport_InheritsAncestorFilters(), theEcorePackage.getEBoolean(), "inheritsAncestorFilters", "true", 0, 1, AbstractMappingImport.class, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(documentedElementEClass, DocumentedElement.class, "DocumentedElement", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getDocumentedElement_Documentation(), theEcorePackage.getEString(), "documentation", "", 0, 1, DocumentedElement.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(dModelElementEClass, DModelElement.class, "DModelElement", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getDModelElement_EAnnotations(), this.getDAnnotation(), null, "eAnnotations", null, 0, -1, DModelElement.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        op = addEOperation(dModelElementEClass, this.getDAnnotation(), "getDAnnotation", 0, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        addEParameter(op, theEcorePackage.getEString(), "source", 0, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(dAnnotationEClass, DAnnotation.class, "DAnnotation", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getDAnnotation_Source(), theEcorePackage.getEString(), "source", null, 0, 1, DAnnotation.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDAnnotation_Details(), theEcorePackage.getEStringToStringMapEntry(), null, "details", null, 0, -1, DAnnotation.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getDAnnotation_References(), theEcorePackage.getEObject(), null, "references", null, 0, -1, DAnnotation.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(conditionalStyleDescriptionEClass, ConditionalStyleDescription.class, "ConditionalStyleDescription", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getConditionalStyleDescription_PredicateExpression(), this.getInterpretedExpression(), "predicateExpression", null, 1, 1, ConditionalStyleDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        op = addEOperation(conditionalStyleDescriptionEClass, theEcorePackage.getEBoolean(), "checkPredicate", 0, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        addEParameter(op, theEcorePackage.getEObject(), "modelElement", 0, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        addEParameter(op, theEcorePackage.getEObject(), "viewVariable", 0, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        addEParameter(op, theEcorePackage.getEObject(), "containerVariable", 0, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(pasteTargetDescriptionEClass, PasteTargetDescription.class, "PasteTargetDescription", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getPasteTargetDescription_PasteDescriptions(), theToolPackage.getPasteDescription(), null, "pasteDescriptions", null, 0, -1, PasteTargetDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(decorationDescriptionsSetEClass, DecorationDescriptionsSet.class, "DecorationDescriptionsSet", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDecorationDescriptionsSet_DecorationDescriptions(), this.getDecorationDescription(), null, "decorationDescriptions", null, 0, -1, DecorationDescriptionsSet.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        getDecorationDescriptionsSet_DecorationDescriptions().getEKeys().add(this.getDecorationDescription_Name());

        initEClass(decorationDescriptionEClass, DecorationDescription.class, "DecorationDescription", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getDecorationDescription_Name(), theEcorePackage.getEString(), "name", null, 1, 1, DecorationDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDecorationDescription_Position(), this.getPosition(), "position", "SOUTH_WEST", 1, 1, DecorationDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDecorationDescription_DistributionDirection(), this.getDecorationDistributionDirection(), "distributionDirection", null, 1, 1, DecorationDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getDecorationDescription_PreconditionExpression(), this.getInterpretedExpression(), "preconditionExpression", null, 0, 1, DecorationDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getDecorationDescription_ImageExpression(), this.getInterpretedExpression(), "imageExpression", null, 1, 1, DecorationDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDecorationDescription_TooltipExpression(), this.getInterpretedExpression(), "tooltipExpression", null, 0, 1, DecorationDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(semanticBasedDecorationEClass, SemanticBasedDecoration.class, "SemanticBasedDecoration", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSemanticBasedDecoration_DomainClass(), this.getTypeName(), "domainClass", null, 1, 1, SemanticBasedDecoration.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(genericDecorationDescriptionEClass, GenericDecorationDescription.class, "GenericDecorationDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);

        initEClass(customizationEClass, Customization.class, "Customization", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getCustomization_VsmElementCustomizations(), this.getIVSMElementCustomization(), null, "vsmElementCustomizations", null, 1, -1, Customization.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(ivsmElementCustomizationEClass, IVSMElementCustomization.class, "IVSMElementCustomization", EPackageImpl.IS_ABSTRACT, EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);

        initEClass(vsmElementCustomizationEClass, VSMElementCustomization.class, "VSMElementCustomization", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getVSMElementCustomization_PredicateExpression(), this.getInterpretedExpression(), "predicateExpression", null, 0, 1, VSMElementCustomization.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getVSMElementCustomization_FeatureCustomizations(), this.getEStructuralFeatureCustomization(), null, "featureCustomizations", null, 1, -1, VSMElementCustomization.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(vsmElementCustomizationReuseEClass, VSMElementCustomizationReuse.class, "VSMElementCustomizationReuse", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getVSMElementCustomizationReuse_Reuse(), this.getEStructuralFeatureCustomization(), null, "reuse", null, 1, -1, VSMElementCustomizationReuse.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getVSMElementCustomizationReuse_AppliedOn(), theEcorePackage.getEObject(), null, "appliedOn", null, 1, -1, VSMElementCustomizationReuse.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(eStructuralFeatureCustomizationEClass, EStructuralFeatureCustomization.class, "EStructuralFeatureCustomization", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getEStructuralFeatureCustomization_AppliedOn(), theEcorePackage.getEObject(), null, "appliedOn", null, 0, -1, EStructuralFeatureCustomization.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getEStructuralFeatureCustomization_ApplyOnAll(), theEcorePackage.getEBoolean(), "applyOnAll", null, 0, 1, EStructuralFeatureCustomization.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(eAttributeCustomizationEClass, EAttributeCustomization.class, "EAttributeCustomization", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getEAttributeCustomization_AttributeName(), theEcorePackage.getEString(), "attributeName", null, 1, 1, EAttributeCustomization.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getEAttributeCustomization_Value(), this.getInterpretedExpression(), "value", null, 0, 1, EAttributeCustomization.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(eReferenceCustomizationEClass, EReferenceCustomization.class, "EReferenceCustomization", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getEReferenceCustomization_ReferenceName(), theEcorePackage.getEString(), "referenceName", null, 1, 1, EReferenceCustomization.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getEReferenceCustomization_Value(), theEcorePackage.getEObject(), null, "value", null, 0, 1, EReferenceCustomization.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(selectionDescriptionEClass, SelectionDescription.class, "SelectionDescription", EPackageImpl.IS_ABSTRACT, EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getSelectionDescription_CandidatesExpression(), this.getInterpretedExpression(), "candidatesExpression", null, 1, 1, SelectionDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getSelectionDescription_Multiple(), theEcorePackage.getEBoolean(), "multiple", null, 1, 1, SelectionDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getSelectionDescription_Tree(), theEcorePackage.getEBoolean(), "tree", null, 1, 1, SelectionDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getSelectionDescription_RootExpression(), this.getInterpretedExpression(), "rootExpression", null, 0, 1, SelectionDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getSelectionDescription_ChildrenExpression(), this.getInterpretedExpression(), "childrenExpression", null, 0, 1, SelectionDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getSelectionDescription_Message(), this.getTranslatableMessage(), "message", null, 0, 1, SelectionDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(colorDescriptionEClass, ColorDescription.class, "ColorDescription", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(systemColorEClass, SystemColor.class, "SystemColor", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getSystemColor_Name(), theEcorePackage.getEString(), "name", null, 1, 1, SystemColor.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(interpolatedColorEClass, InterpolatedColor.class, "InterpolatedColor", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getInterpolatedColor_ColorValueComputationExpression(), this.getInterpretedExpression(), "colorValueComputationExpression", "1", 1, 1, InterpolatedColor.class, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getInterpolatedColor_MinValueComputationExpression(), this.getInterpretedExpression(), "minValueComputationExpression", "0", 1, 1, InterpolatedColor.class, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getInterpolatedColor_MaxValueComputationExpression(), this.getInterpretedExpression(), "maxValueComputationExpression", "10", 1, 1, InterpolatedColor.class, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getInterpolatedColor_ColorSteps(), this.getColorStep(), null, "colorSteps", null, 0, -1, InterpolatedColor.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(colorStepEClass, ColorStep.class, "ColorStep", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getColorStep_AssociatedValue(), this.getInterpretedExpression(), "associatedValue", "", 1, 1, ColorStep.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getColorStep_AssociatedColor(), this.getFixedColor(), null, "associatedColor", null, 1, 1, ColorStep.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(fixedColorEClass, FixedColor.class, "FixedColor", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getFixedColor_Red(), theEcorePackage.getEInt(), "red", "125", 1, 1, FixedColor.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getFixedColor_Green(), theEcorePackage.getEInt(), "green", "125", 1, 1, FixedColor.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getFixedColor_Blue(), theEcorePackage.getEInt(), "blue", "125", 1, 1, FixedColor.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(userFixedColorEClass, UserFixedColor.class, "UserFixedColor", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(userColorEClass, UserColor.class, "UserColor", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getUserColor_Name(), theEcorePackage.getEString(), "name", null, 1, 1, UserColor.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(environmentEClass, Environment.class, "Environment", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getEnvironment_SystemColors(), this.getSytemColorsPalette(), null, "systemColors", null, 0, 1, Environment.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getEnvironment_DefaultTools(), theToolPackage.getToolEntry(), null, "defaultTools", null, 0, -1, Environment.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getEnvironment_LabelBorderStyles(), theStylePackage.getLabelBorderStyles(), null, "labelBorderStyles", null, 0, 1, Environment.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(sytemColorsPaletteEClass, SytemColorsPalette.class, "SytemColorsPalette", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getSytemColorsPalette_Entries(), this.getSystemColor(), null, "entries", null, 0, -1, SytemColorsPalette.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        getSytemColorsPalette_Entries().getEKeys().add(this.getSystemColor_Name());

        initEClass(userColorsPaletteEClass, UserColorsPalette.class, "UserColorsPalette", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getUserColorsPalette_Name(), theEcorePackage.getEString(), "name", null, 1, 1, UserColorsPalette.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getUserColorsPalette_Entries(), this.getUserColor(), null, "entries", null, 0, -1, UserColorsPalette.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        getUserColorsPalette_Entries().getEKeys().add(this.getUserColor_Name());

        initEClass(annotationEntryEClass, AnnotationEntry.class, "AnnotationEntry", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getAnnotationEntry_Source(), theEcorePackage.getEString(), "source", null, 0, 1, AnnotationEntry.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAnnotationEntry_Data(), theEcorePackage.getEObject(), null, "data", null, 0, 1, AnnotationEntry.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(endUserDocumentedElementEClass, EndUserDocumentedElement.class, "EndUserDocumentedElement", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getEndUserDocumentedElement_EndUserDocumentation(), theEcorePackage.getEString(), "endUserDocumentation", "", 0, 1, EndUserDocumentedElement.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(identifiedElementEClass, IdentifiedElement.class, "IdentifiedElement", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getIdentifiedElement_Name(), theEcorePackage.getEString(), "name", "", 1, 1, IdentifiedElement.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getIdentifiedElement_Label(), this.getTranslatableMessage(), "label", null, 0, 1, IdentifiedElement.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(computedColorEClass, ComputedColor.class, "ComputedColor", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getComputedColor_Red(), this.getInterpretedExpression(), "red", "", 1, 1, ComputedColor.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getComputedColor_Green(), this.getInterpretedExpression(), "green", "", 1, 1, ComputedColor.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getComputedColor_Blue(), this.getInterpretedExpression(), "blue", "", 1, 1, ComputedColor.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(dAnnotationEntryEClass, DAnnotationEntry.class, "DAnnotationEntry", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getDAnnotationEntry_Source(), theEcorePackage.getEString(), "source", null, 0, 1, DAnnotationEntry.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDAnnotationEntry_Details(), theEcorePackage.getEString(), "details", null, 0, -1, DAnnotationEntry.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(abstractVariableEClass, AbstractVariable.class, "AbstractVariable", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getAbstractVariable_Name(), theEcorePackage.getEString(), "name", null, 0, 1, AbstractVariable.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(subVariableEClass, SubVariable.class, "SubVariable", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(interactiveVariableDescriptionEClass, InteractiveVariableDescription.class, "InteractiveVariableDescription", EPackageImpl.IS_ABSTRACT, EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getInteractiveVariableDescription_UserDocumentation(), theEcorePackage.getEString(), "userDocumentation", null, 0, 1, InteractiveVariableDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(typedVariableEClass, TypedVariable.class, "TypedVariable", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getTypedVariable_DefaultValueExpression(), this.getInterpretedExpression(), "defaultValueExpression", null, 0, 1, TypedVariable.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getTypedVariable_ValueType(), theEcorePackage.getEDataType(), null, "valueType", null, 1, 1, TypedVariable.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(positionEEnum, Position.class, "Position"); //$NON-NLS-1$
        addEEnumLiteral(positionEEnum, Position.NORTH_LITERAL);
        addEEnumLiteral(positionEEnum, Position.WEST_LITERAL);
        addEEnumLiteral(positionEEnum, Position.SOUTH_LITERAL);
        addEEnumLiteral(positionEEnum, Position.EAST_LITERAL);
        addEEnumLiteral(positionEEnum, Position.NORTH_WEST_LITERAL);
        addEEnumLiteral(positionEEnum, Position.NORTH_EAST_LITERAL);
        addEEnumLiteral(positionEEnum, Position.SOUTH_WEST_LITERAL);
        addEEnumLiteral(positionEEnum, Position.SOUTH_EAST_LITERAL);
        addEEnumLiteral(positionEEnum, Position.CENTER_LITERAL);

        initEEnum(decorationDistributionDirectionEEnum, DecorationDistributionDirection.class, "DecorationDistributionDirection"); //$NON-NLS-1$
        addEEnumLiteral(decorationDistributionDirectionEEnum, DecorationDistributionDirection.VERTICAL);
        addEEnumLiteral(decorationDistributionDirectionEEnum, DecorationDistributionDirection.HORIZONTAL);

        initEEnum(systemColorsEEnum, SystemColors.class, "SystemColors"); //$NON-NLS-1$
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
        initEDataType(typeNameEDataType, String.class, "TypeName", EPackageImpl.IS_SERIALIZABLE, !EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEDataType(interpretedExpressionEDataType, String.class, "InterpretedExpression", EPackageImpl.IS_SERIALIZABLE, !EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEDataType(featureNameEDataType, String.class, "FeatureName", EPackageImpl.IS_SERIALIZABLE, !EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEDataType(imagePathEDataType, String.class, "ImagePath", EPackageImpl.IS_SERIALIZABLE, !EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEDataType(uriEDataType, org.eclipse.emf.common.util.URI.class, "URI", EPackageImpl.IS_SERIALIZABLE, !EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEDataType(translatableMessageEDataType, String.class, "TranslatableMessage", EPackageImpl.IS_SERIALIZABLE, !EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        // Create annotations
        // http://www.eclipse.org/sirius/interpreted/expression/returnType
        createReturnTypeAnnotations();
        // http://www.eclipse.org/sirius/interpreted/expression/variables
        createVariablesAnnotations();
    }

    /**
     * Initializes the annotations for <b>http://www.eclipse.org/sirius/interpreted/expression/returnType</b>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void createReturnTypeAnnotations() {
        String source = "http://www.eclipse.org/sirius/interpreted/expression/returnType"; //$NON-NLS-1$
        addAnnotation(getRepresentationDescription_TitleExpression(), source, new String[] { "returnType", "a string." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getConditionalStyleDescription_PredicateExpression(), source, new String[] { "returnType", "a boolean." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDecorationDescription_PreconditionExpression(), source, new String[] { "returnType", "a boolean." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDecorationDescription_ImageExpression(), source, new String[] { "returnType", "a String, an Image or an IFigure" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDecorationDescription_TooltipExpression(), source, new String[] { "returnType", "a String, an Image or an IFigure" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getVSMElementCustomization_PredicateExpression(), source,
                new String[] { "returnType", "a boolean result. True to enable the customization, false to disabled it. True by default." //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getEAttributeCustomization_Value(), source, new String[] { "returnType", "A java Object to affect as new value of a EAttribute, for example a java primitive." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getSelectionDescription_CandidatesExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getSelectionDescription_RootExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getSelectionDescription_ChildrenExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getInterpolatedColor_ColorValueComputationExpression(), source, new String[] { "returnType", "an integer." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getInterpolatedColor_MinValueComputationExpression(), source, new String[] { "returnType", "an integer." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getInterpolatedColor_MaxValueComputationExpression(), source, new String[] { "returnType", "an integer." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getColorStep_AssociatedValue(), source, new String[] { "returnType", "an integer." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getComputedColor_Red(), source, new String[] { "returnType", "an integer." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getComputedColor_Green(), source, new String[] { "returnType", "an integer." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getComputedColor_Blue(), source, new String[] { "returnType", "an integer." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getTypedVariable_DefaultValueExpression(), source, new String[] { "returnType", "Must return an instance of type valueType." //$NON-NLS-1$ //$NON-NLS-2$
        });
    }

    /**
     * Initializes the annotations for <b>http://www.eclipse.org/sirius/interpreted/expression/variables</b>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void createVariablesAnnotations() {
        String source = "http://www.eclipse.org/sirius/interpreted/expression/variables"; //$NON-NLS-1$
        addAnnotation(getRepresentationDescription_TitleExpression(), source, new String[] {});
        addAnnotation(getConditionalStyleDescription_PredicateExpression(), source, new String[] { "view", "ecore.EObject | the current view.", //$NON-NLS-1$ //$NON-NLS-2$
                "container", "ecore.EObject | the semantic container." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDecorationDescription_PreconditionExpression(), source, new String[] { "view", "viewpoint.DSemanticDecorator | the views of the checked elements.", //$NON-NLS-1$ //$NON-NLS-2$
                "element", "ecore.EObject | the semantic element of the view.", //$NON-NLS-1$ //$NON-NLS-2$
                "containerView", "viewpoint.DSemanticDecorator | the view that would contain the potential views of the checked elements.", //$NON-NLS-1$ //$NON-NLS-2$
                "container", "ecore.EObject | the semantic element of the container view.", //$NON-NLS-1$ //$NON-NLS-2$
                "viewpoint", "diagram.DSemanticDiagram | (deprecated) the current diagram.", //$NON-NLS-1$ //$NON-NLS-2$
                "diagram", "diagram.DSemanticDiagram | the current diagram." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDecorationDescription_ImageExpression(), source,
                new String[] { "containerView", "viewpoint.DSemanticDecorator | the view that would contain the potential views of the checked elements.", //$NON-NLS-1$ //$NON-NLS-2$
                        "container", "ecore.EObject | the semantic element of the container view.", //$NON-NLS-1$ //$NON-NLS-2$
                        "diagram", "diagram.DDiagram | the current diagram." //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getDecorationDescription_TooltipExpression(), source,
                new String[] { "containerView", "viewpoint.DSemanticDecorator | the view that would contain the potential views of the checked elements.", //$NON-NLS-1$ //$NON-NLS-2$
                        "container", "ecore.EObject | the semantic element of the container view.", //$NON-NLS-1$ //$NON-NLS-2$
                        "diagram", "diagram.DDiagram | the current diagram." //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getVSMElementCustomization_PredicateExpression(), source, new String[] { "view", "ecore.EObject | the current view.", //$NON-NLS-1$ //$NON-NLS-2$
                "container", "ecore.EObject | the semantic container." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getEAttributeCustomization_Value(), source, new String[] { "view", "ecore.EObject | the current view.", //$NON-NLS-1$ //$NON-NLS-2$
                "container", "ecore.EObject | the semantic container." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getSelectionDescription_CandidatesExpression(), source, new String[] { "diagram", "diagram.DSemanticDiagram | the current DDiagram.", //$NON-NLS-1$ //$NON-NLS-2$
                "containerView", "viewpoint.DSemanticDecorator | the view of the container.", //$NON-NLS-1$ //$NON-NLS-2$
                "container", "ecore.EObject | the semantic element of the container." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getSelectionDescription_RootExpression(), source, new String[] { "diagram", "diagram.DSemanticDiagram | the current DDiagram.", //$NON-NLS-1$ //$NON-NLS-2$
                "containerView", "viewpoint.DSemanticDecorator | the view of the container.", //$NON-NLS-1$ //$NON-NLS-2$
                "container", "ecore.EObject | the semantic element of the container." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getSelectionDescription_ChildrenExpression(), source, new String[] { "diagram", "diagram.DSemanticDiagram | the current DDiagram.", //$NON-NLS-1$ //$NON-NLS-2$
                "containerView", "viewpoint.DSemanticDecorator | the view of the container.", //$NON-NLS-1$ //$NON-NLS-2$
                "container", "ecore.EObject | the semantic element of the container." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getInterpolatedColor_ColorValueComputationExpression(), source, new String[] {});
        addAnnotation(getInterpolatedColor_MinValueComputationExpression(), source, new String[] {});
        addAnnotation(getInterpolatedColor_MaxValueComputationExpression(), source, new String[] {});
        addAnnotation(getColorStep_AssociatedValue(), source, new String[] {});
        addAnnotation(getComputedColor_Red(), source, new String[] {});
        addAnnotation(getComputedColor_Green(), source, new String[] {});
        addAnnotation(getComputedColor_Blue(), source, new String[] {});
        addAnnotation(getTypedVariable_DefaultValueExpression(), source, new String[] { "diagram", "diagram.DSemanticDiagram | the current DDiagram." //$NON-NLS-1$ //$NON-NLS-2$
        });
    }

} // DescriptionPackageImpl
