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
package org.eclipse.sirius.viewpoint.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.sirius.viewpoint.Customizable;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DAnalysisCustomData;
import org.eclipse.sirius.viewpoint.DAnalysisSessionEObject;
import org.eclipse.sirius.viewpoint.DContainer;
import org.eclipse.sirius.viewpoint.DEObjectLink;
import org.eclipse.sirius.viewpoint.DFeatureExtension;
import org.eclipse.sirius.viewpoint.DFile;
import org.eclipse.sirius.viewpoint.DFolder;
import org.eclipse.sirius.viewpoint.DLabelled;
import org.eclipse.sirius.viewpoint.DMappingBased;
import org.eclipse.sirius.viewpoint.DModel;
import org.eclipse.sirius.viewpoint.DNavigable;
import org.eclipse.sirius.viewpoint.DNavigationLink;
import org.eclipse.sirius.viewpoint.DProject;
import org.eclipse.sirius.viewpoint.DRefreshable;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationContainer;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DResource;
import org.eclipse.sirius.viewpoint.DResourceContainer;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.DSourceFileLink;
import org.eclipse.sirius.viewpoint.DStylizable;
import org.eclipse.sirius.viewpoint.DValidable;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.Decoration;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.LabelAlignment;
import org.eclipse.sirius.viewpoint.LabelStyle;
import org.eclipse.sirius.viewpoint.MetaModelExtension;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.SessionManagerEObject;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.SyncStatus;
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
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class ViewpointPackageImpl extends EPackageImpl implements ViewpointPackage {
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
    private EClass dValidableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass dNavigableEClass = null;

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
    private EClass dLabelledEClass = null;

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
    private EClass dContainerEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass dRepresentationContainerEClass = null;

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
    private EClass dNavigationLinkEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass deObjectLinkEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass dSourceFileLinkEClass = null;

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
    private EClass rgbValuesEClass = null;

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
    private EDataType extendedPackageEDataType = null;

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
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private ViewpointPackageImpl() {
        super(eNS_URI, ViewpointFactory.eINSTANCE);
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
     * This method is used to initialize {@link ViewpointPackage#eINSTANCE} when
     * that field is accessed. Clients should not invoke it directly. Instead,
     * they should simply access that field to obtain the package. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static ViewpointPackage init() {
        if (isInited)
            return (ViewpointPackage) EPackage.Registry.INSTANCE.getEPackage(ViewpointPackage.eNS_URI);

        // Obtain or create and register package
        ViewpointPackageImpl theViewpointPackage = (ViewpointPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ViewpointPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
                : new ViewpointPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        EcorePackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        DescriptionPackageImpl theDescriptionPackage = (DescriptionPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI) instanceof DescriptionPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(DescriptionPackage.eNS_URI) : DescriptionPackage.eINSTANCE);
        StylePackageImpl theStylePackage = (StylePackageImpl) (EPackage.Registry.INSTANCE.getEPackage(StylePackage.eNS_URI) instanceof StylePackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(StylePackage.eNS_URI) : StylePackage.eINSTANCE);
        ToolPackageImpl theToolPackage = (ToolPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(ToolPackage.eNS_URI) instanceof ToolPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(ToolPackage.eNS_URI) : ToolPackage.eINSTANCE);
        ValidationPackageImpl theValidationPackage = (ValidationPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(ValidationPackage.eNS_URI) instanceof ValidationPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(ValidationPackage.eNS_URI) : ValidationPackage.eINSTANCE);
        AuditPackageImpl theAuditPackage = (AuditPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(AuditPackage.eNS_URI) instanceof AuditPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(AuditPackage.eNS_URI) : AuditPackage.eINSTANCE);

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
    public EClass getDAnalysis() {
        return dAnalysisEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDAnalysis_ReferencedAnalysis() {
        return (EReference) dAnalysisEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDAnalysis_Models() {
        return (EReference) dAnalysisEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDAnalysis_EAnnotations() {
        return (EReference) dAnalysisEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDAnalysis_OwnedViews() {
        return (EReference) dAnalysisEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDAnalysis_SelectedViews() {
        return (EReference) dAnalysisEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDAnalysis_OwnedFeatureExtensions() {
        return (EReference) dAnalysisEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDAnalysis_Version() {
        return (EAttribute) dAnalysisEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDFeatureExtension() {
        return dFeatureExtensionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDFeatureExtension_Description() {
        return (EReference) dFeatureExtensionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDValidable() {
        return dValidableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDNavigable() {
        return dNavigableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDNavigable_OwnedNavigationLinks() {
        return (EReference) dNavigableEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDStylizable() {
        return dStylizableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDRefreshable() {
        return dRefreshableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDLabelled() {
        return dLabelledEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDMappingBased() {
        return dMappingBasedEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDContainer() {
        return dContainerEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDRepresentationContainer() {
        return dRepresentationContainerEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDRepresentationContainer_Models() {
        return (EReference) dRepresentationContainerEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDSemanticDecorator() {
        return dSemanticDecoratorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDSemanticDecorator_Target() {
        return (EReference) dSemanticDecoratorEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDRepresentation() {
        return dRepresentationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDRepresentation_OwnedRepresentationElements() {
        return (EReference) dRepresentationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDRepresentation_RepresentationElements() {
        return (EReference) dRepresentationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDRepresentation_Name() {
        return (EAttribute) dRepresentationEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDRepresentation_OwnedAnnotationEntries() {
        return (EReference) dRepresentationEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDRepresentationElement() {
        return dRepresentationElementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDRepresentationElement_Name() {
        return (EAttribute) dRepresentationElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDRepresentationElement_SemanticElements() {
        return (EReference) dRepresentationElementEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDView() {
        return dViewEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDView_OwnedRepresentations() {
        return (EReference) dViewEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDView_OwnedExtensions() {
        return (EReference) dViewEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDView_AllRepresentations() {
        return (EReference) dViewEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDView_HiddenRepresentations() {
        return (EReference) dViewEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDView_ReferencedRepresentations() {
        return (EReference) dViewEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDView_Initialized() {
        return (EAttribute) dViewEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDView_Viewpoint() {
        return (EReference) dViewEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getMetaModelExtension() {
        return metaModelExtensionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getMetaModelExtension_ExtensionGroup() {
        return (EReference) metaModelExtensionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDecoration() {
        return decorationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDecoration_Description() {
        return (EReference) decorationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDNavigationLink() {
        return dNavigationLinkEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDNavigationLink_TargetType() {
        return (EAttribute) dNavigationLinkEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDNavigationLink_Label() {
        return (EAttribute) dNavigationLinkEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDEObjectLink() {
        return deObjectLinkEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDEObjectLink_Target() {
        return (EReference) deObjectLinkEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDSourceFileLink() {
        return dSourceFileLinkEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDSourceFileLink_FilePath() {
        return (EAttribute) dSourceFileLinkEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDSourceFileLink_StartPosition() {
        return (EAttribute) dSourceFileLinkEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDSourceFileLink_EndPosition() {
        return (EAttribute) dSourceFileLinkEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDAnalysisCustomData() {
        return dAnalysisCustomDataEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDAnalysisCustomData_Key() {
        return (EAttribute) dAnalysisCustomDataEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDAnalysisCustomData_Data() {
        return (EReference) dAnalysisCustomDataEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getLabelStyle() {
        return labelStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getLabelStyle_LabelAlignment() {
        return (EAttribute) labelStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getStyle() {
        return styleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getStyle_Description() {
        return (EReference) styleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getRGBValues() {
        return rgbValuesEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getRGBValues_Red() {
        return (EAttribute) rgbValuesEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getRGBValues_Green() {
        return (EAttribute) rgbValuesEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getRGBValues_Blue() {
        return (EAttribute) rgbValuesEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDAnalysisSessionEObject() {
        return dAnalysisSessionEObjectEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDAnalysisSessionEObject_Open() {
        return (EAttribute) dAnalysisSessionEObjectEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDAnalysisSessionEObject_Blocked() {
        return (EAttribute) dAnalysisSessionEObjectEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDAnalysisSessionEObject_Resources() {
        return (EAttribute) dAnalysisSessionEObjectEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDAnalysisSessionEObject_ControlledResources() {
        return (EAttribute) dAnalysisSessionEObjectEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDAnalysisSessionEObject_ActivatedViewpoints() {
        return (EReference) dAnalysisSessionEObjectEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDAnalysisSessionEObject_Analyses() {
        return (EReference) dAnalysisSessionEObjectEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDAnalysisSessionEObject_SynchronizationStatus() {
        return (EAttribute) dAnalysisSessionEObjectEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getSessionManagerEObject() {
        return sessionManagerEObjectEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getSessionManagerEObject_OwnedSessions() {
        return (EReference) sessionManagerEObjectEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDResource() {
        return dResourceEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDResource_Name() {
        return (EAttribute) dResourceEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDResource_Path() {
        return (EAttribute) dResourceEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDFile() {
        return dFileEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDResourceContainer() {
        return dResourceContainerEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDResourceContainer_Members() {
        return (EReference) dResourceContainerEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDProject() {
        return dProjectEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDFolder() {
        return dFolderEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDModel() {
        return dModelEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getBasicLabelStyle() {
        return basicLabelStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getBasicLabelStyle_LabelSize() {
        return (EAttribute) basicLabelStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getBasicLabelStyle_LabelFormat() {
        return (EAttribute) basicLabelStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getBasicLabelStyle_ShowIcon() {
        return (EAttribute) basicLabelStyleEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getBasicLabelStyle_LabelColor() {
        return (EReference) basicLabelStyleEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getBasicLabelStyle_IconPath() {
        return (EAttribute) basicLabelStyleEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getCustomizable() {
        return customizableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getCustomizable_CustomFeatures() {
        return (EAttribute) customizableEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getFontFormat() {
        return fontFormatEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getLabelAlignment() {
        return labelAlignmentEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getSyncStatus() {
        return syncStatusEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EDataType getExtendedPackage() {
        return extendedPackageEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
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
        dAnalysisEClass = createEClass(DANALYSIS);
        createEReference(dAnalysisEClass, DANALYSIS__REFERENCED_ANALYSIS);
        createEReference(dAnalysisEClass, DANALYSIS__MODELS);
        createEReference(dAnalysisEClass, DANALYSIS__EANNOTATIONS);
        createEReference(dAnalysisEClass, DANALYSIS__OWNED_VIEWS);
        createEReference(dAnalysisEClass, DANALYSIS__SELECTED_VIEWS);
        createEReference(dAnalysisEClass, DANALYSIS__OWNED_FEATURE_EXTENSIONS);
        createEAttribute(dAnalysisEClass, DANALYSIS__VERSION);

        dFeatureExtensionEClass = createEClass(DFEATURE_EXTENSION);
        createEReference(dFeatureExtensionEClass, DFEATURE_EXTENSION__DESCRIPTION);

        dValidableEClass = createEClass(DVALIDABLE);

        dNavigableEClass = createEClass(DNAVIGABLE);
        createEReference(dNavigableEClass, DNAVIGABLE__OWNED_NAVIGATION_LINKS);

        dStylizableEClass = createEClass(DSTYLIZABLE);

        dRefreshableEClass = createEClass(DREFRESHABLE);

        dLabelledEClass = createEClass(DLABELLED);

        dMappingBasedEClass = createEClass(DMAPPING_BASED);

        dContainerEClass = createEClass(DCONTAINER);

        dRepresentationContainerEClass = createEClass(DREPRESENTATION_CONTAINER);
        createEReference(dRepresentationContainerEClass, DREPRESENTATION_CONTAINER__MODELS);

        dSemanticDecoratorEClass = createEClass(DSEMANTIC_DECORATOR);
        createEReference(dSemanticDecoratorEClass, DSEMANTIC_DECORATOR__TARGET);

        dRepresentationEClass = createEClass(DREPRESENTATION);
        createEReference(dRepresentationEClass, DREPRESENTATION__OWNED_REPRESENTATION_ELEMENTS);
        createEReference(dRepresentationEClass, DREPRESENTATION__REPRESENTATION_ELEMENTS);
        createEAttribute(dRepresentationEClass, DREPRESENTATION__NAME);
        createEReference(dRepresentationEClass, DREPRESENTATION__OWNED_ANNOTATION_ENTRIES);

        dRepresentationElementEClass = createEClass(DREPRESENTATION_ELEMENT);
        createEAttribute(dRepresentationElementEClass, DREPRESENTATION_ELEMENT__NAME);
        createEReference(dRepresentationElementEClass, DREPRESENTATION_ELEMENT__SEMANTIC_ELEMENTS);

        dViewEClass = createEClass(DVIEW);
        createEReference(dViewEClass, DVIEW__OWNED_REPRESENTATIONS);
        createEReference(dViewEClass, DVIEW__OWNED_EXTENSIONS);
        createEReference(dViewEClass, DVIEW__ALL_REPRESENTATIONS);
        createEReference(dViewEClass, DVIEW__HIDDEN_REPRESENTATIONS);
        createEReference(dViewEClass, DVIEW__REFERENCED_REPRESENTATIONS);
        createEAttribute(dViewEClass, DVIEW__INITIALIZED);
        createEReference(dViewEClass, DVIEW__VIEWPOINT);

        metaModelExtensionEClass = createEClass(META_MODEL_EXTENSION);
        createEReference(metaModelExtensionEClass, META_MODEL_EXTENSION__EXTENSION_GROUP);

        decorationEClass = createEClass(DECORATION);
        createEReference(decorationEClass, DECORATION__DESCRIPTION);

        dNavigationLinkEClass = createEClass(DNAVIGATION_LINK);
        createEAttribute(dNavigationLinkEClass, DNAVIGATION_LINK__TARGET_TYPE);
        createEAttribute(dNavigationLinkEClass, DNAVIGATION_LINK__LABEL);

        deObjectLinkEClass = createEClass(DE_OBJECT_LINK);
        createEReference(deObjectLinkEClass, DE_OBJECT_LINK__TARGET);

        dSourceFileLinkEClass = createEClass(DSOURCE_FILE_LINK);
        createEAttribute(dSourceFileLinkEClass, DSOURCE_FILE_LINK__FILE_PATH);
        createEAttribute(dSourceFileLinkEClass, DSOURCE_FILE_LINK__START_POSITION);
        createEAttribute(dSourceFileLinkEClass, DSOURCE_FILE_LINK__END_POSITION);

        dAnalysisCustomDataEClass = createEClass(DANALYSIS_CUSTOM_DATA);
        createEAttribute(dAnalysisCustomDataEClass, DANALYSIS_CUSTOM_DATA__KEY);
        createEReference(dAnalysisCustomDataEClass, DANALYSIS_CUSTOM_DATA__DATA);

        labelStyleEClass = createEClass(LABEL_STYLE);
        createEAttribute(labelStyleEClass, LABEL_STYLE__LABEL_ALIGNMENT);

        styleEClass = createEClass(STYLE);
        createEReference(styleEClass, STYLE__DESCRIPTION);

        rgbValuesEClass = createEClass(RGB_VALUES);
        createEAttribute(rgbValuesEClass, RGB_VALUES__RED);
        createEAttribute(rgbValuesEClass, RGB_VALUES__GREEN);
        createEAttribute(rgbValuesEClass, RGB_VALUES__BLUE);

        dAnalysisSessionEObjectEClass = createEClass(DANALYSIS_SESSION_EOBJECT);
        createEAttribute(dAnalysisSessionEObjectEClass, DANALYSIS_SESSION_EOBJECT__OPEN);
        createEAttribute(dAnalysisSessionEObjectEClass, DANALYSIS_SESSION_EOBJECT__BLOCKED);
        createEAttribute(dAnalysisSessionEObjectEClass, DANALYSIS_SESSION_EOBJECT__RESOURCES);
        createEAttribute(dAnalysisSessionEObjectEClass, DANALYSIS_SESSION_EOBJECT__CONTROLLED_RESOURCES);
        createEReference(dAnalysisSessionEObjectEClass, DANALYSIS_SESSION_EOBJECT__ACTIVATED_VIEWPOINTS);
        createEReference(dAnalysisSessionEObjectEClass, DANALYSIS_SESSION_EOBJECT__ANALYSES);
        createEAttribute(dAnalysisSessionEObjectEClass, DANALYSIS_SESSION_EOBJECT__SYNCHRONIZATION_STATUS);

        sessionManagerEObjectEClass = createEClass(SESSION_MANAGER_EOBJECT);
        createEReference(sessionManagerEObjectEClass, SESSION_MANAGER_EOBJECT__OWNED_SESSIONS);

        dResourceEClass = createEClass(DRESOURCE);
        createEAttribute(dResourceEClass, DRESOURCE__NAME);
        createEAttribute(dResourceEClass, DRESOURCE__PATH);

        dFileEClass = createEClass(DFILE);

        dResourceContainerEClass = createEClass(DRESOURCE_CONTAINER);
        createEReference(dResourceContainerEClass, DRESOURCE_CONTAINER__MEMBERS);

        dProjectEClass = createEClass(DPROJECT);

        dFolderEClass = createEClass(DFOLDER);

        dModelEClass = createEClass(DMODEL);

        basicLabelStyleEClass = createEClass(BASIC_LABEL_STYLE);
        createEAttribute(basicLabelStyleEClass, BASIC_LABEL_STYLE__LABEL_SIZE);
        createEAttribute(basicLabelStyleEClass, BASIC_LABEL_STYLE__LABEL_FORMAT);
        createEAttribute(basicLabelStyleEClass, BASIC_LABEL_STYLE__SHOW_ICON);
        createEReference(basicLabelStyleEClass, BASIC_LABEL_STYLE__LABEL_COLOR);
        createEAttribute(basicLabelStyleEClass, BASIC_LABEL_STYLE__ICON_PATH);

        customizableEClass = createEClass(CUSTOMIZABLE);
        createEAttribute(customizableEClass, CUSTOMIZABLE__CUSTOM_FEATURES);

        // Create enums
        fontFormatEEnum = createEEnum(FONT_FORMAT);
        labelAlignmentEEnum = createEEnum(LABEL_ALIGNMENT);
        syncStatusEEnum = createEEnum(SYNC_STATUS);

        // Create data types
        extendedPackageEDataType = createEDataType(EXTENDED_PACKAGE);
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
        DescriptionPackage theDescriptionPackage = (DescriptionPackage) EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI);
        EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
        StylePackage theStylePackage = (StylePackage) EPackage.Registry.INSTANCE.getEPackage(StylePackage.eNS_URI);

        // Add subpackages
        getESubpackages().add(theDescriptionPackage);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        dRepresentationContainerEClass.getESuperTypes().add(this.getDView());
        dRepresentationEClass.getESuperTypes().add(theDescriptionPackage.getDocumentedElement());
        dRepresentationEClass.getESuperTypes().add(this.getDRefreshable());
        dRepresentationEClass.getESuperTypes().add(theDescriptionPackage.getDModelElement());
        dRepresentationElementEClass.getESuperTypes().add(this.getDLabelled());
        dRepresentationElementEClass.getESuperTypes().add(this.getDMappingBased());
        dRepresentationElementEClass.getESuperTypes().add(this.getDStylizable());
        dRepresentationElementEClass.getESuperTypes().add(this.getDRefreshable());
        dRepresentationElementEClass.getESuperTypes().add(this.getDSemanticDecorator());
        dViewEClass.getESuperTypes().add(this.getDRefreshable());
        deObjectLinkEClass.getESuperTypes().add(this.getDNavigationLink());
        dSourceFileLinkEClass.getESuperTypes().add(this.getDNavigationLink());
        labelStyleEClass.getESuperTypes().add(this.getBasicLabelStyle());
        styleEClass.getESuperTypes().add(this.getDRefreshable());
        styleEClass.getESuperTypes().add(this.getCustomizable());
        dFileEClass.getESuperTypes().add(this.getDResource());
        dResourceContainerEClass.getESuperTypes().add(this.getDResource());
        dProjectEClass.getESuperTypes().add(this.getDResourceContainer());
        dFolderEClass.getESuperTypes().add(this.getDResourceContainer());
        dModelEClass.getESuperTypes().add(this.getDFile());
        basicLabelStyleEClass.getESuperTypes().add(this.getCustomizable());

        // Initialize classes and features; add operations and parameters
        initEClass(dAnalysisEClass, DAnalysis.class, "DAnalysis", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDAnalysis_ReferencedAnalysis(), this.getDAnalysis(), null, "referencedAnalysis", null, 0, -1, DAnalysis.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDAnalysis_Models(), theEcorePackage.getEObject(), null, "models", null, 0, -1, DAnalysis.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDAnalysis_EAnnotations(), theDescriptionPackage.getDAnnotationEntry(), null, "eAnnotations", null, 0, -1, DAnalysis.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDAnalysis_OwnedViews(), this.getDView(), null, "ownedViews", null, 0, -1, DAnalysis.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDAnalysis_SelectedViews(), this.getDView(), null, "selectedViews", null, 0, -1, DAnalysis.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDAnalysis_OwnedFeatureExtensions(), this.getDFeatureExtension(), null, "ownedFeatureExtensions", null, 0, -1, DAnalysis.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDAnalysis_Version(), theEcorePackage.getEString(), "version", null, 0, 1, DAnalysis.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        initEClass(dFeatureExtensionEClass, DFeatureExtension.class, "DFeatureExtension", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDFeatureExtension_Description(), theDescriptionPackage.getFeatureExtensionDescription(), null, "description", null, 1, 1, DFeatureExtension.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(dValidableEClass, DValidable.class, "DValidable", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        addEOperation(dValidableEClass, theEcorePackage.getEBoolean(), "validate", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(dNavigableEClass, DNavigable.class, "DNavigable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDNavigable_OwnedNavigationLinks(), this.getDNavigationLink(), null, "ownedNavigationLinks", null, 0, -1, DNavigable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(dStylizableEClass, DStylizable.class, "DStylizable", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        addEOperation(dStylizableEClass, this.getStyle(), "getStyle", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(dRefreshableEClass, DRefreshable.class, "DRefreshable", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        addEOperation(dRefreshableEClass, null, "refresh", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(dLabelledEClass, DLabelled.class, "DLabelled", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(dMappingBasedEClass, DMappingBased.class, "DMappingBased", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        addEOperation(dMappingBasedEClass, theDescriptionPackage.getRepresentationElementMapping(), "getMapping", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(dContainerEClass, DContainer.class, "DContainer", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(dRepresentationContainerEClass, DRepresentationContainer.class, "DRepresentationContainer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDRepresentationContainer_Models(), theEcorePackage.getEObject(), null, "models", null, 0, -1, DRepresentationContainer.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

        initEClass(dSemanticDecoratorEClass, DSemanticDecorator.class, "DSemanticDecorator", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDSemanticDecorator_Target(), ecorePackage.getEObject(), null, "target", null, 1, 1, DSemanticDecorator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(dRepresentationEClass, DRepresentation.class, "DRepresentation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDRepresentation_OwnedRepresentationElements(), this.getDRepresentationElement(), null, "ownedRepresentationElements", null, 0, -1, DRepresentation.class, IS_TRANSIENT,
                IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDRepresentation_RepresentationElements(), this.getDRepresentationElement(), null, "representationElements", null, 0, -1, DRepresentation.class, IS_TRANSIENT, IS_VOLATILE,
                !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getDRepresentation_Name(), ecorePackage.getEString(), "name", "", 0, 1, DRepresentation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEReference(getDRepresentation_OwnedAnnotationEntries(), theDescriptionPackage.getAnnotationEntry(), null, "ownedAnnotationEntries", null, 0, -1, DRepresentation.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        addEOperation(dRepresentationEClass, null, "createContents", 0, 1, IS_UNIQUE, IS_ORDERED);

        EOperation op = addEOperation(dRepresentationEClass, null, "createContents", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "rootElement", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(dRepresentationEClass, null, "updateContent", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(dRepresentationElementEClass, DRepresentationElement.class, "DRepresentationElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDRepresentationElement_Name(), ecorePackage.getEString(), "name", "", 0, 1, DRepresentationElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDRepresentationElement_SemanticElements(), theEcorePackage.getEObject(), null, "semanticElements", null, 0, -1, DRepresentationElement.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(dViewEClass, DView.class, "DView", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDView_OwnedRepresentations(), this.getDRepresentation(), null, "ownedRepresentations", null, 0, -1, DView.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDView_OwnedExtensions(), this.getMetaModelExtension(), null, "ownedExtensions", null, 0, 1, DView.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDView_AllRepresentations(), this.getDRepresentation(), null, "allRepresentations", null, 0, -1, DView.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDView_HiddenRepresentations(), this.getDRepresentation(), null, "hiddenRepresentations", null, 0, -1, DView.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDView_ReferencedRepresentations(), this.getDRepresentation(), null, "referencedRepresentations", null, 0, -1, DView.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDView_Initialized(), theEcorePackage.getEBoolean(), "initialized", null, 1, 1, DView.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEReference(getDView_Viewpoint(), theDescriptionPackage.getViewpoint(), null, "viewpoint", null, 1, 1, DView.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(metaModelExtensionEClass, MetaModelExtension.class, "MetaModelExtension", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getMetaModelExtension_ExtensionGroup(), theEcorePackage.getEObject(), null, "extensionGroup", null, 1, 1, MetaModelExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(decorationEClass, Decoration.class, "Decoration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDecoration_Description(), theDescriptionPackage.getDecorationDescription(), null, "description", null, 1, 1, Decoration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(dNavigationLinkEClass, DNavigationLink.class, "DNavigationLink", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDNavigationLink_TargetType(), theDescriptionPackage.getNavigationTargetType(), "targetType", null, 0, 1, DNavigationLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDNavigationLink_Label(), theEcorePackage.getEString(), "label", "link to...", 0, 1, DNavigationLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        addEOperation(dNavigationLinkEClass, theEcorePackage.getEBoolean(), "isAvailable", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(deObjectLinkEClass, DEObjectLink.class, "DEObjectLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDEObjectLink_Target(), theEcorePackage.getEObject(), null, "target", null, 1, 1, DEObjectLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(dSourceFileLinkEClass, DSourceFileLink.class, "DSourceFileLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDSourceFileLink_FilePath(), theEcorePackage.getEString(), "filePath", null, 1, 1, DSourceFileLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDSourceFileLink_StartPosition(), theEcorePackage.getEInt(), "startPosition", "0", 0, 1, DSourceFileLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDSourceFileLink_EndPosition(), theEcorePackage.getEInt(), "endPosition", "1", 0, 1, DSourceFileLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(dAnalysisCustomDataEClass, DAnalysisCustomData.class, "DAnalysisCustomData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDAnalysisCustomData_Key(), theEcorePackage.getEString(), "key", null, 1, 1, DAnalysisCustomData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDAnalysisCustomData_Data(), theEcorePackage.getEObject(), null, "data", null, 1, 1, DAnalysisCustomData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(labelStyleEClass, LabelStyle.class, "LabelStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getLabelStyle_LabelAlignment(), this.getLabelAlignment(), "labelAlignment", null, 0, 1, LabelStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(styleEClass, Style.class, "Style", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getStyle_Description(), theStylePackage.getStyleDescription(), null, "description", null, 0, 1, Style.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(rgbValuesEClass, RGBValues.class, "RGBValues", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getRGBValues_Red(), theEcorePackage.getEInt(), "red", null, 1, 1, RGBValues.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
                IS_ORDERED);
        initEAttribute(getRGBValues_Green(), theEcorePackage.getEInt(), "green", null, 1, 1, RGBValues.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getRGBValues_Blue(), theEcorePackage.getEInt(), "blue", null, 1, 1, RGBValues.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
                IS_ORDERED);

        initEClass(dAnalysisSessionEObjectEClass, DAnalysisSessionEObject.class, "DAnalysisSessionEObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDAnalysisSessionEObject_Open(), theEcorePackage.getEBoolean(), "open", null, 1, 1, DAnalysisSessionEObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDAnalysisSessionEObject_Blocked(), theEcorePackage.getEBoolean(), "blocked", null, 1, 1, DAnalysisSessionEObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDAnalysisSessionEObject_Resources(), theEcorePackage.getEResource(), "resources", null, 0, -1, DAnalysisSessionEObject.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDAnalysisSessionEObject_ControlledResources(), theEcorePackage.getEResource(), "controlledResources", null, 0, -1, DAnalysisSessionEObject.class, IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDAnalysisSessionEObject_ActivatedViewpoints(), theDescriptionPackage.getViewpoint(), null, "activatedViewpoints", null, 0, -1, DAnalysisSessionEObject.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDAnalysisSessionEObject_Analyses(), this.getDAnalysis(), null, "analyses", null, 0, -1, DAnalysisSessionEObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDAnalysisSessionEObject_SynchronizationStatus(), this.getSyncStatus(), "synchronizationStatus", "dirty", 1, 1, DAnalysisSessionEObject.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(sessionManagerEObjectEClass, SessionManagerEObject.class, "SessionManagerEObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getSessionManagerEObject_OwnedSessions(), this.getDAnalysisSessionEObject(), null, "ownedSessions", null, 0, -1, SessionManagerEObject.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(dResourceEClass, DResource.class, "DResource", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDResource_Name(), ecorePackage.getEString(), "name", null, 1, 1, DResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
                IS_ORDERED);
        initEAttribute(getDResource_Path(), ecorePackage.getEString(), "path", null, 1, 1, DResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
                IS_ORDERED);

        initEClass(dFileEClass, DFile.class, "DFile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(dResourceContainerEClass, DResourceContainer.class, "DResourceContainer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDResourceContainer_Members(), this.getDResource(), null, "members", null, 0, -1, DResourceContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(dProjectEClass, DProject.class, "DProject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(dFolderEClass, DFolder.class, "DFolder", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(dModelEClass, DModel.class, "DModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(basicLabelStyleEClass, BasicLabelStyle.class, "BasicLabelStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBasicLabelStyle_LabelSize(), theEcorePackage.getEInt(), "labelSize", "8", 0, 1, BasicLabelStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicLabelStyle_LabelFormat(), this.getFontFormat(), "labelFormat", "normal", 0, 1, BasicLabelStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicLabelStyle_ShowIcon(), ecorePackage.getEBoolean(), "showIcon", "true", 0, 1, BasicLabelStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getBasicLabelStyle_LabelColor(), this.getRGBValues(), null, "labelColor", null, 0, 1, BasicLabelStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicLabelStyle_IconPath(), theEcorePackage.getEString(), "iconPath", "", 0, 1, BasicLabelStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(customizableEClass, Customizable.class, "Customizable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getCustomizable_CustomFeatures(), ecorePackage.getEString(), "customFeatures", null, 0, -1, Customizable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(fontFormatEEnum, FontFormat.class, "FontFormat");
        addEEnumLiteral(fontFormatEEnum, FontFormat.NORMAL_LITERAL);
        addEEnumLiteral(fontFormatEEnum, FontFormat.ITALIC_LITERAL);
        addEEnumLiteral(fontFormatEEnum, FontFormat.BOLD_LITERAL);
        addEEnumLiteral(fontFormatEEnum, FontFormat.BOLD_ITALIC_LITERAL);

        initEEnum(labelAlignmentEEnum, LabelAlignment.class, "LabelAlignment");
        addEEnumLiteral(labelAlignmentEEnum, LabelAlignment.CENTER);
        addEEnumLiteral(labelAlignmentEEnum, LabelAlignment.LEFT);
        addEEnumLiteral(labelAlignmentEEnum, LabelAlignment.RIGHT);

        initEEnum(syncStatusEEnum, SyncStatus.class, "SyncStatus");
        addEEnumLiteral(syncStatusEEnum, SyncStatus.DIRTY);
        addEEnumLiteral(syncStatusEEnum, SyncStatus.SYNC);

        // Initialize data types
        initEDataType(extendedPackageEDataType, ModelAccessor.class, "ExtendedPackage", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

        // Create resource
        createResource(eNS_URI);

        // Create annotations
        // Tags
        createTagsAnnotations();
    }

    /**
     * Initializes the annotations for <b>Tags</b>. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    protected void createTagsAnnotations() {
        String source = "Tags";
        addAnnotation(this, source, new String[] { "deprecated", "Anything tagged as deprecated will disappear quite soon.\n\n", "to be renamed",
                "Anything tagged \"to be renamed\" will be renamed, at least in the UI\n" });
    }

} // ViewpointPackageImpl
