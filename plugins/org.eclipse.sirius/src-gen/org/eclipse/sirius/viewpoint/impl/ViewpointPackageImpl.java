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
package org.eclipse.sirius.viewpoint.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.sirius.business.api.resource.ResourceDescriptor;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.sirius.viewpoint.Customizable;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DAnalysisCustomData;
import org.eclipse.sirius.viewpoint.DAnalysisSessionEObject;
import org.eclipse.sirius.viewpoint.DFeatureExtension;
import org.eclipse.sirius.viewpoint.DFile;
import org.eclipse.sirius.viewpoint.DFolder;
import org.eclipse.sirius.viewpoint.DMappingBased;
import org.eclipse.sirius.viewpoint.DModel;
import org.eclipse.sirius.viewpoint.DProject;
import org.eclipse.sirius.viewpoint.DRefreshable;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DResource;
import org.eclipse.sirius.viewpoint.DResourceContainer;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.DStylizable;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.Decoration;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.IdentifiedElement;
import org.eclipse.sirius.viewpoint.LabelAlignment;
import org.eclipse.sirius.viewpoint.LabelStyle;
import org.eclipse.sirius.viewpoint.MetaModelExtension;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.SessionManagerEObject;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.SyncStatus;
import org.eclipse.sirius.viewpoint.ToolGroupInstance;
import org.eclipse.sirius.viewpoint.ToolInstance;
import org.eclipse.sirius.viewpoint.ToolSectionInstance;
import org.eclipse.sirius.viewpoint.UIState;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.audit.AuditPackage;
import org.eclipse.sirius.viewpoint.description.audit.impl.AuditPackageImpl;
import org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl;
import org.eclipse.sirius.viewpoint.description.style.StylePackage;
import org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl;
import org.eclipse.sirius.viewpoint.description.validation.ValidationPackage;
import org.eclipse.sirius.viewpoint.description.validation.impl.ValidationPackageImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 *
 * @generated
 */
public class ViewpointPackageImpl extends EPackageImpl implements ViewpointPackage {

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
    private EClass dAnalysisEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass dFeatureExtensionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass dStylizableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass dRefreshableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass dMappingBasedEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass dSemanticDecoratorEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass dRepresentationDescriptorEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass dRepresentationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass dRepresentationElementEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass dViewEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass metaModelExtensionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass decorationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass dAnalysisCustomDataEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass labelStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass styleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass dAnalysisSessionEObjectEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass sessionManagerEObjectEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass dResourceEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass dFileEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass dResourceContainerEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass dProjectEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass dFolderEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass dModelEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass basicLabelStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass customizableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass uiStateEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass toolInstanceEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass toolGroupInstanceEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass toolSectionInstanceEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EEnum fontFormatEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EEnum labelAlignmentEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EEnum syncStatusEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EDataType rgbValuesEDataType = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EDataType resourceDescriptorEDataType = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
     * EPackage.Registry} by the package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also
     * performs initialization of the package, or returns the registered package, if one already exists. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private ViewpointPackageImpl() {
        super(ViewpointPackage.eNS_URI, ViewpointFactory.eINSTANCE);
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
     * This method is used to initialize {@link ViewpointPackage#eINSTANCE} when that field is accessed. Clients should
     * not invoke it directly. Instead, they should simply access that field to obtain the package. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static ViewpointPackage init() {
        if (ViewpointPackageImpl.isInited) {
            return (ViewpointPackage) EPackage.Registry.INSTANCE.getEPackage(ViewpointPackage.eNS_URI);
        }

        // Obtain or create and register package
        Object registeredViewpointPackage = EPackage.Registry.INSTANCE.get(ViewpointPackage.eNS_URI);
        ViewpointPackageImpl theViewpointPackage = registeredViewpointPackage instanceof ViewpointPackageImpl ? (ViewpointPackageImpl) registeredViewpointPackage : new ViewpointPackageImpl();

        ViewpointPackageImpl.isInited = true;

        // Initialize simple dependencies
        EcorePackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        Object registeredPackage = EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI);
        DescriptionPackageImpl theDescriptionPackage = (DescriptionPackageImpl) (registeredPackage instanceof DescriptionPackageImpl ? registeredPackage : DescriptionPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(StylePackage.eNS_URI);
        StylePackageImpl theStylePackage = (StylePackageImpl) (registeredPackage instanceof StylePackageImpl ? registeredPackage : StylePackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ToolPackage.eNS_URI);
        ToolPackageImpl theToolPackage = (ToolPackageImpl) (registeredPackage instanceof ToolPackageImpl ? registeredPackage : ToolPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ValidationPackage.eNS_URI);
        ValidationPackageImpl theValidationPackage = (ValidationPackageImpl) (registeredPackage instanceof ValidationPackageImpl ? registeredPackage : ValidationPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(AuditPackage.eNS_URI);
        AuditPackageImpl theAuditPackage = (AuditPackageImpl) (registeredPackage instanceof AuditPackageImpl ? registeredPackage : AuditPackage.eINSTANCE);

        // Create package meta-data objects
        theViewpointPackage.createPackageContents();
        theDescriptionPackage.createPackageContents();
        theStylePackage.createPackageContents();
        theToolPackage.createPackageContents();
        theValidationPackage.createPackageContents();
        theAuditPackage.createPackageContents();

        // Initialize created meta-data
        theViewpointPackage.initializePackageContents();
        theDescriptionPackage.initializePackageContents();
        theStylePackage.initializePackageContents();
        theToolPackage.initializePackageContents();
        theValidationPackage.initializePackageContents();
        theAuditPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theViewpointPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(ViewpointPackage.eNS_URI, theViewpointPackage);
        return theViewpointPackage;
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
    public EAttribute getIdentifiedElement_Uid() {
        return (EAttribute) identifiedElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDAnalysis() {
        return dAnalysisEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDAnalysis_ReferencedAnalysis() {
        return (EReference) dAnalysisEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDAnalysis_SemanticResources() {
        return (EAttribute) dAnalysisEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDAnalysis_Models() {
        return (EReference) dAnalysisEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDAnalysis_EAnnotations() {
        return (EReference) dAnalysisEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDAnalysis_OwnedViews() {
        return (EReference) dAnalysisEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDAnalysis_SelectedViews() {
        return (EReference) dAnalysisEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDAnalysis_OwnedFeatureExtensions() {
        return (EReference) dAnalysisEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDAnalysis_Version() {
        return (EAttribute) dAnalysisEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDFeatureExtension() {
        return dFeatureExtensionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDFeatureExtension_Description() {
        return (EReference) dFeatureExtensionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDStylizable() {
        return dStylizableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDRefreshable() {
        return dRefreshableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDMappingBased() {
        return dMappingBasedEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDSemanticDecorator() {
        return dSemanticDecoratorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDSemanticDecorator_Target() {
        return (EReference) dSemanticDecoratorEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDRepresentationDescriptor() {
        return dRepresentationDescriptorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDRepresentationDescriptor_Name() {
        return (EAttribute) dRepresentationDescriptorEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDRepresentationDescriptor_Description() {
        return (EReference) dRepresentationDescriptorEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDRepresentationDescriptor_Target() {
        return (EReference) dRepresentationDescriptorEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDRepresentationDescriptor_Representation() {
        return (EReference) dRepresentationDescriptorEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDRepresentationDescriptor_RepPath() {
        return (EAttribute) dRepresentationDescriptorEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDRepresentationDescriptor_ChangeId() {
        return (EAttribute) dRepresentationDescriptorEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDRepresentation() {
        return dRepresentationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDRepresentation_OwnedRepresentationElements() {
        return (EReference) dRepresentationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDRepresentation_RepresentationElements() {
        return (EReference) dRepresentationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDRepresentation_OwnedAnnotationEntries() {
        return (EReference) dRepresentationEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDRepresentation_UiState() {
        return (EReference) dRepresentationEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDRepresentationElement() {
        return dRepresentationElementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDRepresentationElement_Name() {
        return (EAttribute) dRepresentationElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDRepresentationElement_SemanticElements() {
        return (EReference) dRepresentationElementEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDView() {
        return dViewEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDView_Viewpoint() {
        return (EReference) dViewEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDView_OwnedRepresentationDescriptors() {
        return (EReference) dViewEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDView_OwnedExtensions() {
        return (EReference) dViewEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDView_Models() {
        return (EReference) dViewEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getMetaModelExtension() {
        return metaModelExtensionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getMetaModelExtension_ExtensionGroup() {
        return (EReference) metaModelExtensionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDecoration() {
        return decorationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDecoration_Description() {
        return (EReference) decorationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDAnalysisCustomData() {
        return dAnalysisCustomDataEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDAnalysisCustomData_Key() {
        return (EAttribute) dAnalysisCustomDataEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDAnalysisCustomData_Data() {
        return (EReference) dAnalysisCustomDataEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getLabelStyle() {
        return labelStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getLabelStyle_LabelAlignment() {
        return (EAttribute) labelStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getStyle() {
        return styleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getStyle_Description() {
        return (EReference) styleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDAnalysisSessionEObject() {
        return dAnalysisSessionEObjectEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDAnalysisSessionEObject_Open() {
        return (EAttribute) dAnalysisSessionEObjectEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDAnalysisSessionEObject_Resources() {
        return (EAttribute) dAnalysisSessionEObjectEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDAnalysisSessionEObject_ControlledResources() {
        return (EAttribute) dAnalysisSessionEObjectEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDAnalysisSessionEObject_ActivatedViewpoints() {
        return (EReference) dAnalysisSessionEObjectEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDAnalysisSessionEObject_Analyses() {
        return (EReference) dAnalysisSessionEObjectEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDAnalysisSessionEObject_SynchronizationStatus() {
        return (EAttribute) dAnalysisSessionEObjectEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getSessionManagerEObject() {
        return sessionManagerEObjectEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getSessionManagerEObject_OwnedSessions() {
        return (EReference) sessionManagerEObjectEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDResource() {
        return dResourceEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDResource_Name() {
        return (EAttribute) dResourceEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDResource_Path() {
        return (EAttribute) dResourceEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDFile() {
        return dFileEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDResourceContainer() {
        return dResourceContainerEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDResourceContainer_Members() {
        return (EReference) dResourceContainerEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDProject() {
        return dProjectEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDFolder() {
        return dFolderEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDModel() {
        return dModelEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getBasicLabelStyle() {
        return basicLabelStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getBasicLabelStyle_LabelSize() {
        return (EAttribute) basicLabelStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getBasicLabelStyle_LabelFormat() {
        return (EAttribute) basicLabelStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getBasicLabelStyle_ShowIcon() {
        return (EAttribute) basicLabelStyleEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getBasicLabelStyle_IconPath() {
        return (EAttribute) basicLabelStyleEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getBasicLabelStyle_LabelColor() {
        return (EAttribute) basicLabelStyleEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getCustomizable() {
        return customizableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getCustomizable_CustomFeatures() {
        return (EAttribute) customizableEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getUIState() {
        return uiStateEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getUIState_InverseSelectionOrder() {
        return (EAttribute) uiStateEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getUIState_ElementsToSelect() {
        return (EReference) uiStateEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getUIState_DecorationImage() {
        return (EAttribute) uiStateEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getUIState_ToolSections() {
        return (EReference) uiStateEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getUIState_SubDiagramDecorationDescriptors() {
        return (EAttribute) uiStateEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getToolInstance() {
        return toolInstanceEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getToolInstance_Id() {
        return (EAttribute) toolInstanceEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getToolInstance_Enabled() {
        return (EAttribute) toolInstanceEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getToolInstance_Visible() {
        return (EAttribute) toolInstanceEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getToolInstance_ToolEntry() {
        return (EReference) toolInstanceEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getToolInstance_Filtered() {
        return (EAttribute) toolInstanceEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getToolGroupInstance() {
        return toolGroupInstanceEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getToolGroupInstance_Tools() {
        return (EReference) toolGroupInstanceEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getToolGroupInstance_Group() {
        return (EReference) toolGroupInstanceEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getToolSectionInstance() {
        return toolSectionInstanceEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getToolSectionInstance_Tools() {
        return (EReference) toolSectionInstanceEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getToolSectionInstance_Section() {
        return (EReference) toolSectionInstanceEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getToolSectionInstance_SubSections() {
        return (EReference) toolSectionInstanceEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EEnum getFontFormat() {
        return fontFormatEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EEnum getLabelAlignment() {
        return labelAlignmentEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EEnum getSyncStatus() {
        return syncStatusEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EDataType getRGBValues() {
        return rgbValuesEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EDataType getResourceDescriptor() {
        return resourceDescriptorEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ViewpointFactory getViewpointFactory() {
        return (ViewpointFactory) getEFactoryInstance();
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
        identifiedElementEClass = createEClass(ViewpointPackage.IDENTIFIED_ELEMENT);
        createEAttribute(identifiedElementEClass, ViewpointPackage.IDENTIFIED_ELEMENT__UID);

        dAnalysisEClass = createEClass(ViewpointPackage.DANALYSIS);
        createEReference(dAnalysisEClass, ViewpointPackage.DANALYSIS__REFERENCED_ANALYSIS);
        createEAttribute(dAnalysisEClass, ViewpointPackage.DANALYSIS__SEMANTIC_RESOURCES);
        createEReference(dAnalysisEClass, ViewpointPackage.DANALYSIS__MODELS);
        createEReference(dAnalysisEClass, ViewpointPackage.DANALYSIS__EANNOTATIONS);
        createEReference(dAnalysisEClass, ViewpointPackage.DANALYSIS__OWNED_VIEWS);
        createEReference(dAnalysisEClass, ViewpointPackage.DANALYSIS__SELECTED_VIEWS);
        createEReference(dAnalysisEClass, ViewpointPackage.DANALYSIS__OWNED_FEATURE_EXTENSIONS);
        createEAttribute(dAnalysisEClass, ViewpointPackage.DANALYSIS__VERSION);

        dFeatureExtensionEClass = createEClass(ViewpointPackage.DFEATURE_EXTENSION);
        createEReference(dFeatureExtensionEClass, ViewpointPackage.DFEATURE_EXTENSION__DESCRIPTION);

        dStylizableEClass = createEClass(ViewpointPackage.DSTYLIZABLE);

        dRefreshableEClass = createEClass(ViewpointPackage.DREFRESHABLE);

        dMappingBasedEClass = createEClass(ViewpointPackage.DMAPPING_BASED);

        dSemanticDecoratorEClass = createEClass(ViewpointPackage.DSEMANTIC_DECORATOR);
        createEReference(dSemanticDecoratorEClass, ViewpointPackage.DSEMANTIC_DECORATOR__TARGET);

        dRepresentationDescriptorEClass = createEClass(ViewpointPackage.DREPRESENTATION_DESCRIPTOR);
        createEAttribute(dRepresentationDescriptorEClass, ViewpointPackage.DREPRESENTATION_DESCRIPTOR__NAME);
        createEReference(dRepresentationDescriptorEClass, ViewpointPackage.DREPRESENTATION_DESCRIPTOR__DESCRIPTION);
        createEReference(dRepresentationDescriptorEClass, ViewpointPackage.DREPRESENTATION_DESCRIPTOR__TARGET);
        createEReference(dRepresentationDescriptorEClass, ViewpointPackage.DREPRESENTATION_DESCRIPTOR__REPRESENTATION);
        createEAttribute(dRepresentationDescriptorEClass, ViewpointPackage.DREPRESENTATION_DESCRIPTOR__REP_PATH);
        createEAttribute(dRepresentationDescriptorEClass, ViewpointPackage.DREPRESENTATION_DESCRIPTOR__CHANGE_ID);

        dRepresentationEClass = createEClass(ViewpointPackage.DREPRESENTATION);
        createEReference(dRepresentationEClass, ViewpointPackage.DREPRESENTATION__OWNED_REPRESENTATION_ELEMENTS);
        createEReference(dRepresentationEClass, ViewpointPackage.DREPRESENTATION__REPRESENTATION_ELEMENTS);
        createEReference(dRepresentationEClass, ViewpointPackage.DREPRESENTATION__OWNED_ANNOTATION_ENTRIES);
        createEReference(dRepresentationEClass, ViewpointPackage.DREPRESENTATION__UI_STATE);

        dRepresentationElementEClass = createEClass(ViewpointPackage.DREPRESENTATION_ELEMENT);
        createEAttribute(dRepresentationElementEClass, ViewpointPackage.DREPRESENTATION_ELEMENT__NAME);
        createEReference(dRepresentationElementEClass, ViewpointPackage.DREPRESENTATION_ELEMENT__SEMANTIC_ELEMENTS);

        dViewEClass = createEClass(ViewpointPackage.DVIEW);
        createEReference(dViewEClass, ViewpointPackage.DVIEW__VIEWPOINT);
        createEReference(dViewEClass, ViewpointPackage.DVIEW__OWNED_REPRESENTATION_DESCRIPTORS);
        createEReference(dViewEClass, ViewpointPackage.DVIEW__OWNED_EXTENSIONS);
        createEReference(dViewEClass, ViewpointPackage.DVIEW__MODELS);

        metaModelExtensionEClass = createEClass(ViewpointPackage.META_MODEL_EXTENSION);
        createEReference(metaModelExtensionEClass, ViewpointPackage.META_MODEL_EXTENSION__EXTENSION_GROUP);

        decorationEClass = createEClass(ViewpointPackage.DECORATION);
        createEReference(decorationEClass, ViewpointPackage.DECORATION__DESCRIPTION);

        dAnalysisCustomDataEClass = createEClass(ViewpointPackage.DANALYSIS_CUSTOM_DATA);
        createEAttribute(dAnalysisCustomDataEClass, ViewpointPackage.DANALYSIS_CUSTOM_DATA__KEY);
        createEReference(dAnalysisCustomDataEClass, ViewpointPackage.DANALYSIS_CUSTOM_DATA__DATA);

        labelStyleEClass = createEClass(ViewpointPackage.LABEL_STYLE);
        createEAttribute(labelStyleEClass, ViewpointPackage.LABEL_STYLE__LABEL_ALIGNMENT);

        styleEClass = createEClass(ViewpointPackage.STYLE);
        createEReference(styleEClass, ViewpointPackage.STYLE__DESCRIPTION);

        dAnalysisSessionEObjectEClass = createEClass(ViewpointPackage.DANALYSIS_SESSION_EOBJECT);
        createEAttribute(dAnalysisSessionEObjectEClass, ViewpointPackage.DANALYSIS_SESSION_EOBJECT__OPEN);
        createEAttribute(dAnalysisSessionEObjectEClass, ViewpointPackage.DANALYSIS_SESSION_EOBJECT__RESOURCES);
        createEAttribute(dAnalysisSessionEObjectEClass, ViewpointPackage.DANALYSIS_SESSION_EOBJECT__CONTROLLED_RESOURCES);
        createEReference(dAnalysisSessionEObjectEClass, ViewpointPackage.DANALYSIS_SESSION_EOBJECT__ACTIVATED_VIEWPOINTS);
        createEReference(dAnalysisSessionEObjectEClass, ViewpointPackage.DANALYSIS_SESSION_EOBJECT__ANALYSES);
        createEAttribute(dAnalysisSessionEObjectEClass, ViewpointPackage.DANALYSIS_SESSION_EOBJECT__SYNCHRONIZATION_STATUS);

        sessionManagerEObjectEClass = createEClass(ViewpointPackage.SESSION_MANAGER_EOBJECT);
        createEReference(sessionManagerEObjectEClass, ViewpointPackage.SESSION_MANAGER_EOBJECT__OWNED_SESSIONS);

        dResourceEClass = createEClass(ViewpointPackage.DRESOURCE);
        createEAttribute(dResourceEClass, ViewpointPackage.DRESOURCE__NAME);
        createEAttribute(dResourceEClass, ViewpointPackage.DRESOURCE__PATH);

        dFileEClass = createEClass(ViewpointPackage.DFILE);

        dResourceContainerEClass = createEClass(ViewpointPackage.DRESOURCE_CONTAINER);
        createEReference(dResourceContainerEClass, ViewpointPackage.DRESOURCE_CONTAINER__MEMBERS);

        dProjectEClass = createEClass(ViewpointPackage.DPROJECT);

        dFolderEClass = createEClass(ViewpointPackage.DFOLDER);

        dModelEClass = createEClass(ViewpointPackage.DMODEL);

        basicLabelStyleEClass = createEClass(ViewpointPackage.BASIC_LABEL_STYLE);
        createEAttribute(basicLabelStyleEClass, ViewpointPackage.BASIC_LABEL_STYLE__LABEL_SIZE);
        createEAttribute(basicLabelStyleEClass, ViewpointPackage.BASIC_LABEL_STYLE__LABEL_FORMAT);
        createEAttribute(basicLabelStyleEClass, ViewpointPackage.BASIC_LABEL_STYLE__SHOW_ICON);
        createEAttribute(basicLabelStyleEClass, ViewpointPackage.BASIC_LABEL_STYLE__ICON_PATH);
        createEAttribute(basicLabelStyleEClass, ViewpointPackage.BASIC_LABEL_STYLE__LABEL_COLOR);

        customizableEClass = createEClass(ViewpointPackage.CUSTOMIZABLE);
        createEAttribute(customizableEClass, ViewpointPackage.CUSTOMIZABLE__CUSTOM_FEATURES);

        uiStateEClass = createEClass(ViewpointPackage.UI_STATE);
        createEAttribute(uiStateEClass, ViewpointPackage.UI_STATE__INVERSE_SELECTION_ORDER);
        createEReference(uiStateEClass, ViewpointPackage.UI_STATE__ELEMENTS_TO_SELECT);
        createEAttribute(uiStateEClass, ViewpointPackage.UI_STATE__DECORATION_IMAGE);
        createEReference(uiStateEClass, ViewpointPackage.UI_STATE__TOOL_SECTIONS);
        createEAttribute(uiStateEClass, ViewpointPackage.UI_STATE__SUB_DIAGRAM_DECORATION_DESCRIPTORS);

        toolInstanceEClass = createEClass(ViewpointPackage.TOOL_INSTANCE);
        createEAttribute(toolInstanceEClass, ViewpointPackage.TOOL_INSTANCE__ID);
        createEAttribute(toolInstanceEClass, ViewpointPackage.TOOL_INSTANCE__ENABLED);
        createEAttribute(toolInstanceEClass, ViewpointPackage.TOOL_INSTANCE__VISIBLE);
        createEReference(toolInstanceEClass, ViewpointPackage.TOOL_INSTANCE__TOOL_ENTRY);
        createEAttribute(toolInstanceEClass, ViewpointPackage.TOOL_INSTANCE__FILTERED);

        toolGroupInstanceEClass = createEClass(ViewpointPackage.TOOL_GROUP_INSTANCE);
        createEReference(toolGroupInstanceEClass, ViewpointPackage.TOOL_GROUP_INSTANCE__TOOLS);
        createEReference(toolGroupInstanceEClass, ViewpointPackage.TOOL_GROUP_INSTANCE__GROUP);

        toolSectionInstanceEClass = createEClass(ViewpointPackage.TOOL_SECTION_INSTANCE);
        createEReference(toolSectionInstanceEClass, ViewpointPackage.TOOL_SECTION_INSTANCE__TOOLS);
        createEReference(toolSectionInstanceEClass, ViewpointPackage.TOOL_SECTION_INSTANCE__SECTION);
        createEReference(toolSectionInstanceEClass, ViewpointPackage.TOOL_SECTION_INSTANCE__SUB_SECTIONS);

        // Create enums
        fontFormatEEnum = createEEnum(ViewpointPackage.FONT_FORMAT);
        labelAlignmentEEnum = createEEnum(ViewpointPackage.LABEL_ALIGNMENT);
        syncStatusEEnum = createEEnum(ViewpointPackage.SYNC_STATUS);

        // Create data types
        rgbValuesEDataType = createEDataType(ViewpointPackage.RGB_VALUES);
        resourceDescriptorEDataType = createEDataType(ViewpointPackage.RESOURCE_DESCRIPTOR);
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
        setName(ViewpointPackage.eNAME);
        setNsPrefix(ViewpointPackage.eNS_PREFIX);
        setNsURI(ViewpointPackage.eNS_URI);

        // Obtain other dependent packages
        DescriptionPackage theDescriptionPackage = (DescriptionPackage) EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI);
        EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
        StylePackage theStylePackage = (StylePackage) EPackage.Registry.INSTANCE.getEPackage(StylePackage.eNS_URI);
        ToolPackage theToolPackage = (ToolPackage) EPackage.Registry.INSTANCE.getEPackage(ToolPackage.eNS_URI);

        // Add subpackages
        getESubpackages().add(theDescriptionPackage);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        dAnalysisEClass.getESuperTypes().add(this.getIdentifiedElement());
        dFeatureExtensionEClass.getESuperTypes().add(this.getIdentifiedElement());
        dSemanticDecoratorEClass.getESuperTypes().add(this.getIdentifiedElement());
        dRepresentationDescriptorEClass.getESuperTypes().add(this.getIdentifiedElement());
        dRepresentationDescriptorEClass.getESuperTypes().add(theDescriptionPackage.getDModelElement());
        dRepresentationDescriptorEClass.getESuperTypes().add(theDescriptionPackage.getDocumentedElement());
        dRepresentationEClass.getESuperTypes().add(this.getIdentifiedElement());
        dRepresentationEClass.getESuperTypes().add(theDescriptionPackage.getDModelElement());
        dRepresentationEClass.getESuperTypes().add(this.getDRefreshable());
        dRepresentationElementEClass.getESuperTypes().add(this.getIdentifiedElement());
        dRepresentationElementEClass.getESuperTypes().add(this.getDMappingBased());
        dRepresentationElementEClass.getESuperTypes().add(this.getDStylizable());
        dRepresentationElementEClass.getESuperTypes().add(this.getDRefreshable());
        dRepresentationElementEClass.getESuperTypes().add(this.getDSemanticDecorator());
        dViewEClass.getESuperTypes().add(this.getIdentifiedElement());
        dViewEClass.getESuperTypes().add(this.getDRefreshable());
        metaModelExtensionEClass.getESuperTypes().add(this.getIdentifiedElement());
        decorationEClass.getESuperTypes().add(this.getIdentifiedElement());
        dAnalysisCustomDataEClass.getESuperTypes().add(this.getIdentifiedElement());
        labelStyleEClass.getESuperTypes().add(this.getBasicLabelStyle());
        styleEClass.getESuperTypes().add(this.getIdentifiedElement());
        styleEClass.getESuperTypes().add(this.getDRefreshable());
        styleEClass.getESuperTypes().add(this.getCustomizable());
        dFileEClass.getESuperTypes().add(this.getIdentifiedElement());
        dFileEClass.getESuperTypes().add(this.getDResource());
        dResourceContainerEClass.getESuperTypes().add(this.getIdentifiedElement());
        dResourceContainerEClass.getESuperTypes().add(this.getDResource());
        dProjectEClass.getESuperTypes().add(this.getDResourceContainer());
        dFolderEClass.getESuperTypes().add(this.getDResourceContainer());
        dModelEClass.getESuperTypes().add(this.getDFile());
        basicLabelStyleEClass.getESuperTypes().add(this.getCustomizable());
        customizableEClass.getESuperTypes().add(this.getIdentifiedElement());
        toolGroupInstanceEClass.getESuperTypes().add(this.getToolInstance());
        toolSectionInstanceEClass.getESuperTypes().add(this.getToolInstance());

        // Initialize classes and features; add operations and parameters
        initEClass(identifiedElementEClass, IdentifiedElement.class, "IdentifiedElement", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getIdentifiedElement_Uid(), theEcorePackage.getEString(), "uid", null, 0, 1, IdentifiedElement.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(dAnalysisEClass, DAnalysis.class, "DAnalysis", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getDAnalysis_ReferencedAnalysis(), this.getDAnalysis(), null, "referencedAnalysis", null, 0, -1, DAnalysis.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getDAnalysis_SemanticResources(), this.getResourceDescriptor(), "semanticResources", null, 0, -1, DAnalysis.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDAnalysis_Models(), theEcorePackage.getEObject(), null, "models", null, 0, -1, DAnalysis.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getDAnalysis_EAnnotations(), theDescriptionPackage.getDAnnotationEntry(), null, "eAnnotations", null, 0, -1, DAnalysis.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDAnalysis_OwnedViews(), this.getDView(), null, "ownedViews", null, 0, -1, DAnalysis.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDAnalysis_SelectedViews(), this.getDView(), null, "selectedViews", null, 0, -1, DAnalysis.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getDAnalysis_OwnedFeatureExtensions(), this.getDFeatureExtension(), null, "ownedFeatureExtensions", null, 0, -1, DAnalysis.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDAnalysis_Version(), theEcorePackage.getEString(), "version", null, 0, 1, DAnalysis.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(dFeatureExtensionEClass, DFeatureExtension.class, "DFeatureExtension", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getDFeatureExtension_Description(), theDescriptionPackage.getFeatureExtensionDescription(), null, "description", null, 1, 1, DFeatureExtension.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(dStylizableEClass, DStylizable.class, "DStylizable", EPackageImpl.IS_ABSTRACT, EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        addEOperation(dStylizableEClass, this.getStyle(), "getStyle", 0, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(dRefreshableEClass, DRefreshable.class, "DRefreshable", EPackageImpl.IS_ABSTRACT, EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(dMappingBasedEClass, DMappingBased.class, "DMappingBased", EPackageImpl.IS_ABSTRACT, EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        addEOperation(dMappingBasedEClass, theDescriptionPackage.getRepresentationElementMapping(), "getMapping", 0, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(dSemanticDecoratorEClass, DSemanticDecorator.class, "DSemanticDecorator", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getDSemanticDecorator_Target(), theEcorePackage.getEObject(), null, "target", null, 1, 1, DSemanticDecorator.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(dRepresentationDescriptorEClass, DRepresentationDescriptor.class, "DRepresentationDescriptor", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDRepresentationDescriptor_Name(), theEcorePackage.getEString(), "name", "", 0, 1, DRepresentationDescriptor.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDRepresentationDescriptor_Description(), theDescriptionPackage.getRepresentationDescription(), null, "description", null, 1, 1, DRepresentationDescriptor.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDRepresentationDescriptor_Target(), theEcorePackage.getEObject(), null, "target", null, 1, 1, DRepresentationDescriptor.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDRepresentationDescriptor_Representation(), this.getDRepresentation(), null, "representation", null, 1, 1, DRepresentationDescriptor.class, EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDRepresentationDescriptor_RepPath(), this.getResourceDescriptor(), "repPath", null, 1, 1, DRepresentationDescriptor.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDRepresentationDescriptor_ChangeId(), ecorePackage.getEString(), "changeId", null, 0, 1, DRepresentationDescriptor.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(dRepresentationEClass, DRepresentation.class, "DRepresentation", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getDRepresentation_OwnedRepresentationElements(), this.getDRepresentationElement(), null, "ownedRepresentationElements", null, 0, -1, DRepresentation.class, //$NON-NLS-1$
                EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, !EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDRepresentation_RepresentationElements(), this.getDRepresentationElement(), null, "representationElements", null, 0, -1, DRepresentation.class, EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                EPackageImpl.IS_VOLATILE, !EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDRepresentation_OwnedAnnotationEntries(), theDescriptionPackage.getAnnotationEntry(), null, "ownedAnnotationEntries", null, 0, -1, DRepresentation.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDRepresentation_UiState(), this.getUIState(), null, "uiState", null, 0, 1, DRepresentation.class, EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(dRepresentationElementEClass, DRepresentationElement.class, "DRepresentationElement", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDRepresentationElement_Name(), theEcorePackage.getEString(), "name", "", 0, 1, DRepresentationElement.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDRepresentationElement_SemanticElements(), theEcorePackage.getEObject(), null, "semanticElements", null, 0, -1, DRepresentationElement.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(dViewEClass, DView.class, "DView", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getDView_Viewpoint(), theDescriptionPackage.getViewpoint(), null, "viewpoint", null, 1, 1, DView.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getDView_OwnedRepresentationDescriptors(), this.getDRepresentationDescriptor(), null, "ownedRepresentationDescriptors", null, 0, -1, DView.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDView_OwnedExtensions(), this.getMetaModelExtension(), null, "ownedExtensions", null, 0, 1, DView.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getDView_Models(), theEcorePackage.getEObject(), null, "models", null, 0, -1, DView.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, !EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(metaModelExtensionEClass, MetaModelExtension.class, "MetaModelExtension", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getMetaModelExtension_ExtensionGroup(), theEcorePackage.getEObject(), null, "extensionGroup", null, 1, 1, MetaModelExtension.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(decorationEClass, Decoration.class, "Decoration", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getDecoration_Description(), theDescriptionPackage.getDecorationDescription(), null, "description", null, 1, 1, Decoration.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(dAnalysisCustomDataEClass, DAnalysisCustomData.class, "DAnalysisCustomData", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getDAnalysisCustomData_Key(), theEcorePackage.getEString(), "key", null, 1, 1, DAnalysisCustomData.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDAnalysisCustomData_Data(), theEcorePackage.getEObject(), null, "data", null, 1, 1, DAnalysisCustomData.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(labelStyleEClass, LabelStyle.class, "LabelStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getLabelStyle_LabelAlignment(), this.getLabelAlignment(), "labelAlignment", null, 0, 1, LabelStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(styleEClass, Style.class, "Style", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getStyle_Description(), theStylePackage.getStyleDescription(), null, "description", null, 0, 1, Style.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(dAnalysisSessionEObjectEClass, DAnalysisSessionEObject.class, "DAnalysisSessionEObject", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDAnalysisSessionEObject_Open(), theEcorePackage.getEBoolean(), "open", null, 1, 1, DAnalysisSessionEObject.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDAnalysisSessionEObject_Resources(), theEcorePackage.getEResource(), "resources", null, 0, -1, DAnalysisSessionEObject.class, EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDAnalysisSessionEObject_ControlledResources(), theEcorePackage.getEResource(), "controlledResources", null, 0, -1, DAnalysisSessionEObject.class, EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDAnalysisSessionEObject_ActivatedViewpoints(), theDescriptionPackage.getViewpoint(), null, "activatedViewpoints", null, 0, -1, DAnalysisSessionEObject.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDAnalysisSessionEObject_Analyses(), this.getDAnalysis(), null, "analyses", null, 0, -1, DAnalysisSessionEObject.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getDAnalysisSessionEObject_SynchronizationStatus(), this.getSyncStatus(), "synchronizationStatus", "dirty", 1, 1, DAnalysisSessionEObject.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(sessionManagerEObjectEClass, SessionManagerEObject.class, "SessionManagerEObject", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getSessionManagerEObject_OwnedSessions(), this.getDAnalysisSessionEObject(), null, "ownedSessions", null, 0, -1, SessionManagerEObject.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(dResourceEClass, DResource.class, "DResource", EPackageImpl.IS_ABSTRACT, EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getDResource_Name(), theEcorePackage.getEString(), "name", null, 1, 1, DResource.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDResource_Path(), theEcorePackage.getEString(), "path", null, 1, 1, DResource.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(dFileEClass, DFile.class, "DFile", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(dResourceContainerEClass, DResourceContainer.class, "DResourceContainer", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getDResourceContainer_Members(), this.getDResource(), null, "members", null, 0, -1, DResourceContainer.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(dProjectEClass, DProject.class, "DProject", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(dFolderEClass, DFolder.class, "DFolder", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(dModelEClass, DModel.class, "DModel", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(basicLabelStyleEClass, BasicLabelStyle.class, "BasicLabelStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getBasicLabelStyle_LabelSize(), theEcorePackage.getEInt(), "labelSize", "8", 0, 1, BasicLabelStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getBasicLabelStyle_LabelFormat(), this.getFontFormat(), "labelFormat", null, 0, 4, BasicLabelStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getBasicLabelStyle_ShowIcon(), theEcorePackage.getEBoolean(), "showIcon", "true", 0, 1, BasicLabelStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getBasicLabelStyle_IconPath(), theEcorePackage.getEString(), "iconPath", "", 0, 1, BasicLabelStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getBasicLabelStyle_LabelColor(), this.getRGBValues(), "labelColor", "0,0,0", 0, 1, BasicLabelStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(customizableEClass, Customizable.class, "Customizable", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getCustomizable_CustomFeatures(), theEcorePackage.getEString(), "customFeatures", null, 0, -1, Customizable.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(uiStateEClass, UIState.class, "UIState", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getUIState_InverseSelectionOrder(), theEcorePackage.getEBoolean(), "inverseSelectionOrder", "false", 0, 1, UIState.class, EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getUIState_ElementsToSelect(), theEcorePackage.getEObject(), null, "elementsToSelect", null, 0, -1, UIState.class, EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        EGenericType g1 = createEGenericType(theEcorePackage.getEMap());
        EGenericType g2 = createEGenericType(this.getDecoration());
        g1.getETypeArguments().add(g2);
        g2 = createEGenericType(ecorePackage.getEJavaObject());
        g1.getETypeArguments().add(g2);
        initEAttribute(getUIState_DecorationImage(), g1, "decorationImage", null, 0, 1, UIState.class, EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getUIState_ToolSections(), this.getToolSectionInstance(), null, "toolSections", null, 0, -1, UIState.class, EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        g1 = createEGenericType(theEcorePackage.getEMap());
        g2 = createEGenericType(ecorePackage.getEJavaObject());
        g1.getETypeArguments().add(g2);
        g2 = createEGenericType(ecorePackage.getEJavaObject());
        g1.getETypeArguments().add(g2);
        initEAttribute(getUIState_SubDiagramDecorationDescriptors(), g1, "subDiagramDecorationDescriptors", null, 0, 1, UIState.class, EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(toolInstanceEClass, ToolInstance.class, "ToolInstance", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getToolInstance_Id(), ecorePackage.getEString(), "id", null, 0, 1, ToolInstance.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getToolInstance_Enabled(), ecorePackage.getEBoolean(), "enabled", "true", 0, 1, ToolInstance.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getToolInstance_Visible(), ecorePackage.getEBoolean(), "visible", null, 0, 1, ToolInstance.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getToolInstance_ToolEntry(), theToolPackage.getToolEntry(), null, "toolEntry", null, 0, 1, ToolInstance.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getToolInstance_Filtered(), ecorePackage.getEBoolean(), "filtered", null, 0, 1, ToolInstance.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(toolGroupInstanceEClass, ToolGroupInstance.class, "ToolGroupInstance", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getToolGroupInstance_Tools(), this.getToolInstance(), null, "tools", null, 0, -1, ToolGroupInstance.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getToolGroupInstance_Group(), theEcorePackage.getEObject(), null, "group", null, 0, 1, ToolGroupInstance.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(toolSectionInstanceEClass, ToolSectionInstance.class, "ToolSectionInstance", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getToolSectionInstance_Tools(), this.getToolInstance(), null, "tools", null, 0, -1, ToolSectionInstance.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getToolSectionInstance_Section(), theEcorePackage.getEObject(), null, "section", null, 0, 1, ToolSectionInstance.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getToolSectionInstance_SubSections(), this.getToolSectionInstance(), null, "subSections", null, 0, -1, ToolSectionInstance.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(fontFormatEEnum, FontFormat.class, "FontFormat"); //$NON-NLS-1$
        addEEnumLiteral(fontFormatEEnum, FontFormat.ITALIC_LITERAL);
        addEEnumLiteral(fontFormatEEnum, FontFormat.BOLD_LITERAL);
        addEEnumLiteral(fontFormatEEnum, FontFormat.UNDERLINE_LITERAL);
        addEEnumLiteral(fontFormatEEnum, FontFormat.STRIKE_THROUGH_LITERAL);

        initEEnum(labelAlignmentEEnum, LabelAlignment.class, "LabelAlignment"); //$NON-NLS-1$
        addEEnumLiteral(labelAlignmentEEnum, LabelAlignment.CENTER);
        addEEnumLiteral(labelAlignmentEEnum, LabelAlignment.LEFT);
        addEEnumLiteral(labelAlignmentEEnum, LabelAlignment.RIGHT);

        initEEnum(syncStatusEEnum, SyncStatus.class, "SyncStatus"); //$NON-NLS-1$
        addEEnumLiteral(syncStatusEEnum, SyncStatus.DIRTY);
        addEEnumLiteral(syncStatusEEnum, SyncStatus.SYNC);

        // Initialize data types
        initEDataType(rgbValuesEDataType, RGBValues.class, "RGBValues", EPackageImpl.IS_SERIALIZABLE, !EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEDataType(resourceDescriptorEDataType, ResourceDescriptor.class, "ResourceDescriptor", EPackageImpl.IS_SERIALIZABLE, !EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        // Create resource
        createResource(ViewpointPackage.eNS_URI);

        // Create annotations
        // Tags
        createTagsAnnotations();
    }

    /**
     * Initializes the annotations for <b>Tags</b>. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void createTagsAnnotations() {
        String source = "Tags"; //$NON-NLS-1$
        addAnnotation(this, source, new String[] { "deprecated", "Anything tagged as deprecated will disappear quite soon.\n\n", //$NON-NLS-1$ //$NON-NLS-2$
                "to be renamed", "Anything tagged \"to be renamed\" will be renamed, at least in the UI\n" //$NON-NLS-1$ //$NON-NLS-2$
        });
    }
} // ViewpointPackageImpl
