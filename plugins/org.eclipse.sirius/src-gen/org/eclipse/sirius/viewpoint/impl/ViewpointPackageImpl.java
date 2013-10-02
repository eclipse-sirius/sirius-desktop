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
package org.eclipse.sirius.viewpoint.impl;

import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.viewpoint.AbsoluteBoundsFilter;
import org.eclipse.sirius.viewpoint.AbstractDNode;
import org.eclipse.sirius.viewpoint.AlignmentKind;
import org.eclipse.sirius.viewpoint.AppliedCompositeFilters;
import org.eclipse.sirius.viewpoint.ArrangeConstraint;
import org.eclipse.sirius.viewpoint.BackgroundStyle;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.sirius.viewpoint.BeginLabelStyle;
import org.eclipse.sirius.viewpoint.BorderedStyle;
import org.eclipse.sirius.viewpoint.BracketEdgeStyle;
import org.eclipse.sirius.viewpoint.BundledImage;
import org.eclipse.sirius.viewpoint.BundledImageShape;
import org.eclipse.sirius.viewpoint.CenterLabelStyle;
import org.eclipse.sirius.viewpoint.CollapseFilter;
import org.eclipse.sirius.viewpoint.ComputedStyleDescriptionRegistry;
import org.eclipse.sirius.viewpoint.ContainerLayout;
import org.eclipse.sirius.viewpoint.ContainerShape;
import org.eclipse.sirius.viewpoint.ContainerStyle;
import org.eclipse.sirius.viewpoint.CustomStyle;
import org.eclipse.sirius.viewpoint.Customizable;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DAnalysisCustomData;
import org.eclipse.sirius.viewpoint.DAnalysisSessionEObject;
import org.eclipse.sirius.viewpoint.DContainer;
import org.eclipse.sirius.viewpoint.DDiagram;
import org.eclipse.sirius.viewpoint.DDiagramElement;
import org.eclipse.sirius.viewpoint.DDiagramElementContainer;
import org.eclipse.sirius.viewpoint.DDiagramLink;
import org.eclipse.sirius.viewpoint.DDiagramSet;
import org.eclipse.sirius.viewpoint.DEObjectLink;
import org.eclipse.sirius.viewpoint.DEdge;
import org.eclipse.sirius.viewpoint.DFeatureExtension;
import org.eclipse.sirius.viewpoint.DFile;
import org.eclipse.sirius.viewpoint.DFolder;
import org.eclipse.sirius.viewpoint.DLabelled;
import org.eclipse.sirius.viewpoint.DMappingBased;
import org.eclipse.sirius.viewpoint.DModel;
import org.eclipse.sirius.viewpoint.DNavigable;
import org.eclipse.sirius.viewpoint.DNavigationLink;
import org.eclipse.sirius.viewpoint.DNode;
import org.eclipse.sirius.viewpoint.DNodeContainer;
import org.eclipse.sirius.viewpoint.DNodeList;
import org.eclipse.sirius.viewpoint.DNodeListElement;
import org.eclipse.sirius.viewpoint.DProject;
import org.eclipse.sirius.viewpoint.DRefreshable;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationContainer;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DResource;
import org.eclipse.sirius.viewpoint.DResourceContainer;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.DSemanticDiagram;
import org.eclipse.sirius.viewpoint.DSourceFileLink;
import org.eclipse.sirius.viewpoint.DStylizable;
import org.eclipse.sirius.viewpoint.DValidable;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.Decoration;
import org.eclipse.sirius.viewpoint.Dot;
import org.eclipse.sirius.viewpoint.DragAndDropTarget;
import org.eclipse.sirius.viewpoint.EdgeArrows;
import org.eclipse.sirius.viewpoint.EdgeRouting;
import org.eclipse.sirius.viewpoint.EdgeStyle;
import org.eclipse.sirius.viewpoint.EdgeTarget;
import org.eclipse.sirius.viewpoint.Ellipse;
import org.eclipse.sirius.viewpoint.EndLabelStyle;
import org.eclipse.sirius.viewpoint.FilterVariableHistory;
import org.eclipse.sirius.viewpoint.FilterVariableValue;
import org.eclipse.sirius.viewpoint.FlatContainerStyle;
import org.eclipse.sirius.viewpoint.FoldingFilter;
import org.eclipse.sirius.viewpoint.FoldingPointFilter;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.GaugeCompositeStyle;
import org.eclipse.sirius.viewpoint.GaugeSection;
import org.eclipse.sirius.viewpoint.GraphicalFilter;
import org.eclipse.sirius.viewpoint.HideFilter;
import org.eclipse.sirius.viewpoint.HideLabelFilter;
import org.eclipse.sirius.viewpoint.IndirectlyCollapseFilter;
import org.eclipse.sirius.viewpoint.LabelAlignment;
import org.eclipse.sirius.viewpoint.LabelPosition;
import org.eclipse.sirius.viewpoint.LabelStyle;
import org.eclipse.sirius.viewpoint.LineStyle;
import org.eclipse.sirius.viewpoint.Lozenge;
import org.eclipse.sirius.viewpoint.MetaModelExtension;
import org.eclipse.sirius.viewpoint.NodeStyle;
import org.eclipse.sirius.viewpoint.Note;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.ResizeKind;
import org.eclipse.sirius.viewpoint.SessionManagerEObject;
import org.eclipse.sirius.viewpoint.ShapeContainerStyle;
import org.eclipse.sirius.viewpoint.Square;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.SyncStatus;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.WorkspaceImage;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.audit.AuditPackage;
import org.eclipse.sirius.viewpoint.description.audit.impl.AuditPackageImpl;
import org.eclipse.sirius.viewpoint.description.concern.ConcernPackage;
import org.eclipse.sirius.viewpoint.description.concern.impl.ConcernPackageImpl;
import org.eclipse.sirius.viewpoint.description.filter.FilterPackage;
import org.eclipse.sirius.viewpoint.description.filter.impl.FilterPackageImpl;
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
    private EClass dDiagramEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass dSemanticDiagramEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass dDiagramElementEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass graphicalFilterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass hideFilterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass hideLabelFilterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass foldingPointFilterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass foldingFilterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass appliedCompositeFiltersEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass absoluteBoundsFilterEClass = null;

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
    private EClass dDiagramLinkEClass = null;

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
    private EClass abstractDNodeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass dNodeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass dDiagramElementContainerEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass dNodeContainerEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass dNodeListEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass dNodeListElementEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass dEdgeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass dDiagramSetEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass nodeStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass dotEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass gaugeSectionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass containerStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass flatContainerStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass shapeContainerStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass squareEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass ellipseEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass lozengeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass bundledImageEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass workspaceImageEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass customStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass edgeTargetEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass edgeStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass bracketEdgeStyleEClass = null;

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
    private EClass computedStyleDescriptionRegistryEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass diagramElementMapping2ModelElementEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass modelElement2ViewVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass viewVariable2ContainerVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass containerVariable2StyleDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum containerLayoutEEnum = null;

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
    private EClass gaugeCompositeStyleEClass = null;

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
    private EClass borderedStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass noteEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass dragAndDropTargetEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass filterVariableHistoryEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass filterVariableValueEClass = null;

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
    private EClass collapseFilterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass indirectlyCollapseFilterEClass = null;

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
    private EClass beginLabelStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass centerLabelStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass endLabelStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum labelPositionEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum containerShapeEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum backgroundStyleEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum bundledImageShapeEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum lineStyleEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum edgeArrowsEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum edgeRoutingEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum alignmentKindEEnum = null;

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
    private EEnum resizeKindEEnum = null;

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
    private EEnum arrangeConstraintEEnum = null;

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
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#eNS_URI
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
     * This method is used to initialize {@link SiriusPackage#eINSTANCE} when
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
        FilterPackageImpl theFilterPackage = (FilterPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(FilterPackage.eNS_URI) instanceof FilterPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(FilterPackage.eNS_URI) : FilterPackage.eINSTANCE);
        ValidationPackageImpl theValidationPackage = (ValidationPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(ValidationPackage.eNS_URI) instanceof ValidationPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(ValidationPackage.eNS_URI) : ValidationPackage.eINSTANCE);
        AuditPackageImpl theAuditPackage = (AuditPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(AuditPackage.eNS_URI) instanceof AuditPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(AuditPackage.eNS_URI) : AuditPackage.eINSTANCE);
        ConcernPackageImpl theConcernPackage = (ConcernPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(ConcernPackage.eNS_URI) instanceof ConcernPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(ConcernPackage.eNS_URI) : ConcernPackage.eINSTANCE);

        // Create package meta-data objects
        theViewpointPackage.createPackageContents();
        theDescriptionPackage.createPackageContents();
        theStylePackage.createPackageContents();
        theToolPackage.createPackageContents();
        theFilterPackage.createPackageContents();
        theValidationPackage.createPackageContents();
        theAuditPackage.createPackageContents();
        theConcernPackage.createPackageContents();

        // Initialize created meta-data
        theViewpointPackage.initializePackageContents();
        theDescriptionPackage.initializePackageContents();
        theStylePackage.initializePackageContents();
        theToolPackage.initializePackageContents();
        theFilterPackage.initializePackageContents();
        theValidationPackage.initializePackageContents();
        theAuditPackage.initializePackageContents();
        theConcernPackage.initializePackageContents();

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
    public EReference getDRepresentationContainer_DiagramSet() {
        return (EReference) dRepresentationContainerEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDRepresentationContainer_Models() {
        return (EReference) dRepresentationContainerEClass.getEStructuralFeatures().get(1);
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
    public EClass getDDiagram() {
        return dDiagramEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDDiagram_OwnedDiagramElements() {
        return (EReference) dDiagramEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDDiagram_DiagramElements() {
        return (EReference) dDiagramEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDDiagram_Description() {
        return (EReference) dDiagramEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDDiagram_Info() {
        return (EAttribute) dDiagramEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDDiagram_SubDiagrams() {
        return (EReference) dDiagramEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDDiagram_Edges() {
        return (EReference) dDiagramEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDDiagram_Nodes() {
        return (EReference) dDiagramEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDDiagram_NodeListElements() {
        return (EReference) dDiagramEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDDiagram_Containers() {
        return (EReference) dDiagramEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDDiagram_CurrentConcern() {
        return (EReference) dDiagramEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDDiagram_ActivatedFilters() {
        return (EReference) dDiagramEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDDiagram_AllFilters() {
        return (EReference) dDiagramEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDDiagram_ActivatedRules() {
        return (EReference) dDiagramEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDDiagram_ActivateBehaviors() {
        return (EReference) dDiagramEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDDiagram_FilterVariableHistory() {
        return (EReference) dDiagramEClass.getEStructuralFeatures().get(14);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDDiagram_ActivatedLayers() {
        return (EReference) dDiagramEClass.getEStructuralFeatures().get(15);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDDiagram_Synchronized() {
        return (EAttribute) dDiagramEClass.getEStructuralFeatures().get(16);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDDiagram_HiddenElements() {
        return (EReference) dDiagramEClass.getEStructuralFeatures().get(17);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDDiagram_IsInLayoutingMode() {
        return (EAttribute) dDiagramEClass.getEStructuralFeatures().get(18);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDDiagram_HeaderHeight() {
        return (EAttribute) dDiagramEClass.getEStructuralFeatures().get(19);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDSemanticDiagram() {
        return dSemanticDiagramEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDDiagramElement() {
        return dDiagramElementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDDiagramElement_Visible() {
        return (EAttribute) dDiagramElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDDiagramElement_TooltipText() {
        return (EAttribute) dDiagramElementEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDDiagramElement_ParentLayers() {
        return (EReference) dDiagramElementEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDDiagramElement_Decorations() {
        return (EReference) dDiagramElementEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDDiagramElement_DiagramElementMapping() {
        return (EReference) dDiagramElementEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDDiagramElement_GraphicalFilters() {
        return (EReference) dDiagramElementEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getGraphicalFilter() {
        return graphicalFilterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getHideFilter() {
        return hideFilterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getHideLabelFilter() {
        return hideLabelFilterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getFoldingPointFilter() {
        return foldingPointFilterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getFoldingFilter() {
        return foldingFilterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getAppliedCompositeFilters() {
        return appliedCompositeFiltersEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getAppliedCompositeFilters_CompositeFilterDescriptions() {
        return (EReference) appliedCompositeFiltersEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getAbsoluteBoundsFilter() {
        return absoluteBoundsFilterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getAbsoluteBoundsFilter_X() {
        return (EAttribute) absoluteBoundsFilterEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getAbsoluteBoundsFilter_Y() {
        return (EAttribute) absoluteBoundsFilterEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getAbsoluteBoundsFilter_Height() {
        return (EAttribute) absoluteBoundsFilterEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getAbsoluteBoundsFilter_Width() {
        return (EAttribute) absoluteBoundsFilterEClass.getEStructuralFeatures().get(3);
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
    public EClass getDDiagramLink() {
        return dDiagramLinkEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDDiagramLink_Target() {
        return (EReference) dDiagramLinkEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDDiagramLink_Node() {
        return (EReference) dDiagramLinkEClass.getEStructuralFeatures().get(1);
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
    public EClass getAbstractDNode() {
        return abstractDNodeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getAbstractDNode_OwnedBorderedNodes() {
        return (EReference) abstractDNodeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getAbstractDNode_ArrangeConstraints() {
        return (EAttribute) abstractDNodeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDNode() {
        return dNodeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDNode_Width() {
        return (EAttribute) dNodeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDNode_Height() {
        return (EAttribute) dNodeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDNode_OwnedStyle() {
        return (EReference) dNodeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDNode_LabelPosition() {
        return (EAttribute) dNodeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDNode_OwnedDetails() {
        return (EReference) dNodeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDNode_ResizeKind() {
        return (EAttribute) dNodeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDNode_OriginalStyle() {
        return (EReference) dNodeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDNode_ActualMapping() {
        return (EReference) dNodeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDNode_CandidatesMapping() {
        return (EReference) dNodeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDDiagramElementContainer() {
        return dDiagramElementContainerEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDDiagramElementContainer_Nodes() {
        return (EReference) dDiagramElementContainerEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDDiagramElementContainer_Containers() {
        return (EReference) dDiagramElementContainerEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDDiagramElementContainer_Elements() {
        return (EReference) dDiagramElementContainerEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDDiagramElementContainer_OwnedStyle() {
        return (EReference) dDiagramElementContainerEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDDiagramElementContainer_OwnedDetails() {
        return (EReference) dDiagramElementContainerEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDDiagramElementContainer_OriginalStyle() {
        return (EReference) dDiagramElementContainerEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDDiagramElementContainer_ActualMapping() {
        return (EReference) dDiagramElementContainerEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDDiagramElementContainer_CandidatesMapping() {
        return (EReference) dDiagramElementContainerEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDDiagramElementContainer_Width() {
        return (EAttribute) dDiagramElementContainerEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDDiagramElementContainer_Height() {
        return (EAttribute) dDiagramElementContainerEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDNodeContainer() {
        return dNodeContainerEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDNodeContainer_OwnedDiagramElements() {
        return (EReference) dNodeContainerEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDNodeContainer_ChildrenPresentation() {
        return (EAttribute) dNodeContainerEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDNodeList() {
        return dNodeListEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDNodeList_OwnedElements() {
        return (EReference) dNodeListEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDNodeList_LineWidth() {
        return (EAttribute) dNodeListEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDNodeListElement() {
        return dNodeListElementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDNodeListElement_OwnedStyle() {
        return (EReference) dNodeListElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDNodeListElement_OriginalStyle() {
        return (EReference) dNodeListElementEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDNodeListElement_ActualMapping() {
        return (EReference) dNodeListElementEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDNodeListElement_CandidatesMapping() {
        return (EReference) dNodeListElementEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDEdge() {
        return dEdgeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDEdge_OwnedStyle() {
        return (EReference) dEdgeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDEdge_Size() {
        return (EAttribute) dEdgeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDEdge_SourceNode() {
        return (EReference) dEdgeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDEdge_TargetNode() {
        return (EReference) dEdgeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDEdge_ActualMapping() {
        return (EReference) dEdgeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDEdge_RoutingStyle() {
        return (EAttribute) dEdgeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDEdge_IsFold() {
        return (EAttribute) dEdgeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDEdge_IsMockEdge() {
        return (EAttribute) dEdgeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDEdge_OriginalStyle() {
        return (EReference) dEdgeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDEdge_Path() {
        return (EReference) dEdgeEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDEdge_ArrangeConstraints() {
        return (EAttribute) dEdgeEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDEdge_BeginLabel() {
        return (EAttribute) dEdgeEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDEdge_EndLabel() {
        return (EAttribute) dEdgeEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDDiagramSet() {
        return dDiagramSetEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDDiagramSet_Description() {
        return (EReference) dDiagramSetEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDDiagramSet_Diagrams() {
        return (EReference) dDiagramSetEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDDiagramSet_View() {
        return (EReference) dDiagramSetEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getNodeStyle() {
        return nodeStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getNodeStyle_LabelPosition() {
        return (EAttribute) nodeStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getNodeStyle_HideLabelByDefault() {
        return (EAttribute) nodeStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDot() {
        return dotEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDot_BackgroundColor() {
        return (EReference) dotEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDot_StrokeSizeComputationExpression() {
        return (EAttribute) dotEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getGaugeSection() {
        return gaugeSectionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getGaugeSection_Min() {
        return (EAttribute) gaugeSectionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getGaugeSection_Max() {
        return (EAttribute) gaugeSectionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getGaugeSection_Value() {
        return (EAttribute) gaugeSectionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getGaugeSection_Label() {
        return (EAttribute) gaugeSectionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getGaugeSection_BackgroundColor() {
        return (EReference) gaugeSectionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getGaugeSection_ForegroundColor() {
        return (EReference) gaugeSectionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getContainerStyle() {
        return containerStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getFlatContainerStyle() {
        return flatContainerStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getFlatContainerStyle_BackgroundStyle() {
        return (EAttribute) flatContainerStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getFlatContainerStyle_BackgroundColor() {
        return (EReference) flatContainerStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getFlatContainerStyle_ForegroundColor() {
        return (EReference) flatContainerStyleEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getShapeContainerStyle() {
        return shapeContainerStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getShapeContainerStyle_Shape() {
        return (EAttribute) shapeContainerStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getShapeContainerStyle_BackgroundColor() {
        return (EReference) shapeContainerStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getSquare() {
        return squareEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getSquare_Width() {
        return (EAttribute) squareEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getSquare_Height() {
        return (EAttribute) squareEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getSquare_Color() {
        return (EReference) squareEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getEllipse() {
        return ellipseEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getEllipse_HorizontalDiameter() {
        return (EAttribute) ellipseEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getEllipse_VerticalDiameter() {
        return (EAttribute) ellipseEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEllipse_Color() {
        return (EReference) ellipseEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getLozenge() {
        return lozengeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getLozenge_Width() {
        return (EAttribute) lozengeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getLozenge_Height() {
        return (EAttribute) lozengeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getLozenge_Color() {
        return (EReference) lozengeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getBundledImage() {
        return bundledImageEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getBundledImage_Shape() {
        return (EAttribute) bundledImageEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getBundledImage_Color() {
        return (EReference) bundledImageEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getWorkspaceImage() {
        return workspaceImageEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getWorkspaceImage_WorkspacePath() {
        return (EAttribute) workspaceImageEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getCustomStyle() {
        return customStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getCustomStyle_Id() {
        return (EAttribute) customStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getEdgeTarget() {
        return edgeTargetEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEdgeTarget_OutgoingEdges() {
        return (EReference) edgeTargetEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEdgeTarget_IncomingEdges() {
        return (EReference) edgeTargetEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getEdgeStyle() {
        return edgeStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEdgeStyle_StrokeColor() {
        return (EReference) edgeStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getEdgeStyle_LineStyle() {
        return (EAttribute) edgeStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getEdgeStyle_SourceArrow() {
        return (EAttribute) edgeStyleEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getEdgeStyle_TargetArrow() {
        return (EAttribute) edgeStyleEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getEdgeStyle_FoldingStyle() {
        return (EAttribute) edgeStyleEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getEdgeStyle_Size() {
        return (EAttribute) edgeStyleEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getEdgeStyle_RoutingStyle() {
        return (EAttribute) edgeStyleEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEdgeStyle_BeginLabelStyle() {
        return (EReference) edgeStyleEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEdgeStyle_CenterLabelStyle() {
        return (EReference) edgeStyleEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEdgeStyle_EndLabelStyle() {
        return (EReference) edgeStyleEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getBracketEdgeStyle() {
        return bracketEdgeStyleEClass;
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
    public EClass getComputedStyleDescriptionRegistry() {
        return computedStyleDescriptionRegistryEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getComputedStyleDescriptionRegistry_ComputedStyleDescriptions() {
        return (EReference) computedStyleDescriptionRegistryEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getComputedStyleDescriptionRegistry_Cache() {
        return (EReference) computedStyleDescriptionRegistryEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDiagramElementMapping2ModelElement() {
        return diagramElementMapping2ModelElementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDiagramElementMapping2ModelElement_Key() {
        return (EReference) diagramElementMapping2ModelElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDiagramElementMapping2ModelElement_Value() {
        return (EReference) diagramElementMapping2ModelElementEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getModelElement2ViewVariable() {
        return modelElement2ViewVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getModelElement2ViewVariable_Key() {
        return (EReference) modelElement2ViewVariableEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getModelElement2ViewVariable_Value() {
        return (EReference) modelElement2ViewVariableEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getViewVariable2ContainerVariable() {
        return viewVariable2ContainerVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getViewVariable2ContainerVariable_Key() {
        return (EReference) viewVariable2ContainerVariableEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getViewVariable2ContainerVariable_Value() {
        return (EReference) viewVariable2ContainerVariableEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getContainerVariable2StyleDescription() {
        return containerVariable2StyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getContainerVariable2StyleDescription_Key() {
        return (EReference) containerVariable2StyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getContainerVariable2StyleDescription_Value() {
        return (EReference) containerVariable2StyleDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getContainerLayout() {
        return containerLayoutEEnum;
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
    public EClass getGaugeCompositeStyle() {
        return gaugeCompositeStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getGaugeCompositeStyle_Alignment() {
        return (EAttribute) gaugeCompositeStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getGaugeCompositeStyle_Sections() {
        return (EReference) gaugeCompositeStyleEClass.getEStructuralFeatures().get(1);
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
    public EClass getBorderedStyle() {
        return borderedStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getBorderedStyle_BorderSize() {
        return (EAttribute) borderedStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getBorderedStyle_BorderSizeComputationExpression() {
        return (EAttribute) borderedStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getBorderedStyle_BorderColor() {
        return (EReference) borderedStyleEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getNote() {
        return noteEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getNote_Color() {
        return (EReference) noteEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDragAndDropTarget() {
        return dragAndDropTargetEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getFilterVariableHistory() {
        return filterVariableHistoryEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getFilterVariableHistory_OwnedValues() {
        return (EReference) filterVariableHistoryEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getFilterVariableValue() {
        return filterVariableValueEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getFilterVariableValue_VariableDefinition() {
        return (EReference) filterVariableValueEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getFilterVariableValue_ModelElement() {
        return (EReference) filterVariableValueEClass.getEStructuralFeatures().get(1);
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
    public EClass getCollapseFilter() {
        return collapseFilterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getCollapseFilter_Width() {
        return (EAttribute) collapseFilterEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getCollapseFilter_Height() {
        return (EAttribute) collapseFilterEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getIndirectlyCollapseFilter() {
        return indirectlyCollapseFilterEClass;
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
    public EClass getBeginLabelStyle() {
        return beginLabelStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getCenterLabelStyle() {
        return centerLabelStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getEndLabelStyle() {
        return endLabelStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getLabelPosition() {
        return labelPositionEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getContainerShape() {
        return containerShapeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getBackgroundStyle() {
        return backgroundStyleEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getBundledImageShape() {
        return bundledImageShapeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getLineStyle() {
        return lineStyleEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getEdgeArrows() {
        return edgeArrowsEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getEdgeRouting() {
        return edgeRoutingEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getAlignmentKind() {
        return alignmentKindEEnum;
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
    public EEnum getResizeKind() {
        return resizeKindEEnum;
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
    public EEnum getArrangeConstraint() {
        return arrangeConstraintEEnum;
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
        createEReference(dRepresentationContainerEClass, DREPRESENTATION_CONTAINER__DIAGRAM_SET);
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

        dDiagramEClass = createEClass(DDIAGRAM);
        createEReference(dDiagramEClass, DDIAGRAM__OWNED_DIAGRAM_ELEMENTS);
        createEReference(dDiagramEClass, DDIAGRAM__DIAGRAM_ELEMENTS);
        createEReference(dDiagramEClass, DDIAGRAM__DESCRIPTION);
        createEAttribute(dDiagramEClass, DDIAGRAM__INFO);
        createEReference(dDiagramEClass, DDIAGRAM__SUB_DIAGRAMS);
        createEReference(dDiagramEClass, DDIAGRAM__EDGES);
        createEReference(dDiagramEClass, DDIAGRAM__NODES);
        createEReference(dDiagramEClass, DDIAGRAM__NODE_LIST_ELEMENTS);
        createEReference(dDiagramEClass, DDIAGRAM__CONTAINERS);
        createEReference(dDiagramEClass, DDIAGRAM__CURRENT_CONCERN);
        createEReference(dDiagramEClass, DDIAGRAM__ACTIVATED_FILTERS);
        createEReference(dDiagramEClass, DDIAGRAM__ALL_FILTERS);
        createEReference(dDiagramEClass, DDIAGRAM__ACTIVATED_RULES);
        createEReference(dDiagramEClass, DDIAGRAM__ACTIVATE_BEHAVIORS);
        createEReference(dDiagramEClass, DDIAGRAM__FILTER_VARIABLE_HISTORY);
        createEReference(dDiagramEClass, DDIAGRAM__ACTIVATED_LAYERS);
        createEAttribute(dDiagramEClass, DDIAGRAM__SYNCHRONIZED);
        createEReference(dDiagramEClass, DDIAGRAM__HIDDEN_ELEMENTS);
        createEAttribute(dDiagramEClass, DDIAGRAM__IS_IN_LAYOUTING_MODE);
        createEAttribute(dDiagramEClass, DDIAGRAM__HEADER_HEIGHT);

        dSemanticDiagramEClass = createEClass(DSEMANTIC_DIAGRAM);

        dDiagramElementEClass = createEClass(DDIAGRAM_ELEMENT);
        createEAttribute(dDiagramElementEClass, DDIAGRAM_ELEMENT__VISIBLE);
        createEAttribute(dDiagramElementEClass, DDIAGRAM_ELEMENT__TOOLTIP_TEXT);
        createEReference(dDiagramElementEClass, DDIAGRAM_ELEMENT__PARENT_LAYERS);
        createEReference(dDiagramElementEClass, DDIAGRAM_ELEMENT__DECORATIONS);
        createEReference(dDiagramElementEClass, DDIAGRAM_ELEMENT__DIAGRAM_ELEMENT_MAPPING);
        createEReference(dDiagramElementEClass, DDIAGRAM_ELEMENT__GRAPHICAL_FILTERS);

        graphicalFilterEClass = createEClass(GRAPHICAL_FILTER);

        hideFilterEClass = createEClass(HIDE_FILTER);

        hideLabelFilterEClass = createEClass(HIDE_LABEL_FILTER);

        foldingPointFilterEClass = createEClass(FOLDING_POINT_FILTER);

        foldingFilterEClass = createEClass(FOLDING_FILTER);

        appliedCompositeFiltersEClass = createEClass(APPLIED_COMPOSITE_FILTERS);
        createEReference(appliedCompositeFiltersEClass, APPLIED_COMPOSITE_FILTERS__COMPOSITE_FILTER_DESCRIPTIONS);

        absoluteBoundsFilterEClass = createEClass(ABSOLUTE_BOUNDS_FILTER);
        createEAttribute(absoluteBoundsFilterEClass, ABSOLUTE_BOUNDS_FILTER__X);
        createEAttribute(absoluteBoundsFilterEClass, ABSOLUTE_BOUNDS_FILTER__Y);
        createEAttribute(absoluteBoundsFilterEClass, ABSOLUTE_BOUNDS_FILTER__HEIGHT);
        createEAttribute(absoluteBoundsFilterEClass, ABSOLUTE_BOUNDS_FILTER__WIDTH);

        decorationEClass = createEClass(DECORATION);
        createEReference(decorationEClass, DECORATION__DESCRIPTION);

        dNavigationLinkEClass = createEClass(DNAVIGATION_LINK);
        createEAttribute(dNavigationLinkEClass, DNAVIGATION_LINK__TARGET_TYPE);
        createEAttribute(dNavigationLinkEClass, DNAVIGATION_LINK__LABEL);

        deObjectLinkEClass = createEClass(DE_OBJECT_LINK);
        createEReference(deObjectLinkEClass, DE_OBJECT_LINK__TARGET);

        dDiagramLinkEClass = createEClass(DDIAGRAM_LINK);
        createEReference(dDiagramLinkEClass, DDIAGRAM_LINK__TARGET);
        createEReference(dDiagramLinkEClass, DDIAGRAM_LINK__NODE);

        dSourceFileLinkEClass = createEClass(DSOURCE_FILE_LINK);
        createEAttribute(dSourceFileLinkEClass, DSOURCE_FILE_LINK__FILE_PATH);
        createEAttribute(dSourceFileLinkEClass, DSOURCE_FILE_LINK__START_POSITION);
        createEAttribute(dSourceFileLinkEClass, DSOURCE_FILE_LINK__END_POSITION);

        abstractDNodeEClass = createEClass(ABSTRACT_DNODE);
        createEReference(abstractDNodeEClass, ABSTRACT_DNODE__OWNED_BORDERED_NODES);
        createEAttribute(abstractDNodeEClass, ABSTRACT_DNODE__ARRANGE_CONSTRAINTS);

        dNodeEClass = createEClass(DNODE);
        createEAttribute(dNodeEClass, DNODE__WIDTH);
        createEAttribute(dNodeEClass, DNODE__HEIGHT);
        createEReference(dNodeEClass, DNODE__OWNED_STYLE);
        createEAttribute(dNodeEClass, DNODE__LABEL_POSITION);
        createEReference(dNodeEClass, DNODE__OWNED_DETAILS);
        createEAttribute(dNodeEClass, DNODE__RESIZE_KIND);
        createEReference(dNodeEClass, DNODE__ORIGINAL_STYLE);
        createEReference(dNodeEClass, DNODE__ACTUAL_MAPPING);
        createEReference(dNodeEClass, DNODE__CANDIDATES_MAPPING);

        dDiagramElementContainerEClass = createEClass(DDIAGRAM_ELEMENT_CONTAINER);
        createEReference(dDiagramElementContainerEClass, DDIAGRAM_ELEMENT_CONTAINER__NODES);
        createEReference(dDiagramElementContainerEClass, DDIAGRAM_ELEMENT_CONTAINER__CONTAINERS);
        createEReference(dDiagramElementContainerEClass, DDIAGRAM_ELEMENT_CONTAINER__ELEMENTS);
        createEReference(dDiagramElementContainerEClass, DDIAGRAM_ELEMENT_CONTAINER__OWNED_STYLE);
        createEReference(dDiagramElementContainerEClass, DDIAGRAM_ELEMENT_CONTAINER__OWNED_DETAILS);
        createEReference(dDiagramElementContainerEClass, DDIAGRAM_ELEMENT_CONTAINER__ORIGINAL_STYLE);
        createEReference(dDiagramElementContainerEClass, DDIAGRAM_ELEMENT_CONTAINER__ACTUAL_MAPPING);
        createEReference(dDiagramElementContainerEClass, DDIAGRAM_ELEMENT_CONTAINER__CANDIDATES_MAPPING);
        createEAttribute(dDiagramElementContainerEClass, DDIAGRAM_ELEMENT_CONTAINER__WIDTH);
        createEAttribute(dDiagramElementContainerEClass, DDIAGRAM_ELEMENT_CONTAINER__HEIGHT);

        dNodeContainerEClass = createEClass(DNODE_CONTAINER);
        createEReference(dNodeContainerEClass, DNODE_CONTAINER__OWNED_DIAGRAM_ELEMENTS);
        createEAttribute(dNodeContainerEClass, DNODE_CONTAINER__CHILDREN_PRESENTATION);

        dNodeListEClass = createEClass(DNODE_LIST);
        createEReference(dNodeListEClass, DNODE_LIST__OWNED_ELEMENTS);
        createEAttribute(dNodeListEClass, DNODE_LIST__LINE_WIDTH);

        dNodeListElementEClass = createEClass(DNODE_LIST_ELEMENT);
        createEReference(dNodeListElementEClass, DNODE_LIST_ELEMENT__OWNED_STYLE);
        createEReference(dNodeListElementEClass, DNODE_LIST_ELEMENT__ORIGINAL_STYLE);
        createEReference(dNodeListElementEClass, DNODE_LIST_ELEMENT__ACTUAL_MAPPING);
        createEReference(dNodeListElementEClass, DNODE_LIST_ELEMENT__CANDIDATES_MAPPING);

        dEdgeEClass = createEClass(DEDGE);
        createEReference(dEdgeEClass, DEDGE__OWNED_STYLE);
        createEAttribute(dEdgeEClass, DEDGE__SIZE);
        createEReference(dEdgeEClass, DEDGE__SOURCE_NODE);
        createEReference(dEdgeEClass, DEDGE__TARGET_NODE);
        createEReference(dEdgeEClass, DEDGE__ACTUAL_MAPPING);
        createEAttribute(dEdgeEClass, DEDGE__ROUTING_STYLE);
        createEAttribute(dEdgeEClass, DEDGE__IS_FOLD);
        createEAttribute(dEdgeEClass, DEDGE__IS_MOCK_EDGE);
        createEReference(dEdgeEClass, DEDGE__ORIGINAL_STYLE);
        createEReference(dEdgeEClass, DEDGE__PATH);
        createEAttribute(dEdgeEClass, DEDGE__ARRANGE_CONSTRAINTS);
        createEAttribute(dEdgeEClass, DEDGE__BEGIN_LABEL);
        createEAttribute(dEdgeEClass, DEDGE__END_LABEL);

        dDiagramSetEClass = createEClass(DDIAGRAM_SET);
        createEReference(dDiagramSetEClass, DDIAGRAM_SET__DESCRIPTION);
        createEReference(dDiagramSetEClass, DDIAGRAM_SET__DIAGRAMS);
        createEReference(dDiagramSetEClass, DDIAGRAM_SET__VIEW);

        nodeStyleEClass = createEClass(NODE_STYLE);
        createEAttribute(nodeStyleEClass, NODE_STYLE__LABEL_POSITION);
        createEAttribute(nodeStyleEClass, NODE_STYLE__HIDE_LABEL_BY_DEFAULT);

        dotEClass = createEClass(DOT);
        createEReference(dotEClass, DOT__BACKGROUND_COLOR);
        createEAttribute(dotEClass, DOT__STROKE_SIZE_COMPUTATION_EXPRESSION);

        gaugeSectionEClass = createEClass(GAUGE_SECTION);
        createEAttribute(gaugeSectionEClass, GAUGE_SECTION__MIN);
        createEAttribute(gaugeSectionEClass, GAUGE_SECTION__MAX);
        createEAttribute(gaugeSectionEClass, GAUGE_SECTION__VALUE);
        createEAttribute(gaugeSectionEClass, GAUGE_SECTION__LABEL);
        createEReference(gaugeSectionEClass, GAUGE_SECTION__BACKGROUND_COLOR);
        createEReference(gaugeSectionEClass, GAUGE_SECTION__FOREGROUND_COLOR);

        containerStyleEClass = createEClass(CONTAINER_STYLE);

        flatContainerStyleEClass = createEClass(FLAT_CONTAINER_STYLE);
        createEAttribute(flatContainerStyleEClass, FLAT_CONTAINER_STYLE__BACKGROUND_STYLE);
        createEReference(flatContainerStyleEClass, FLAT_CONTAINER_STYLE__BACKGROUND_COLOR);
        createEReference(flatContainerStyleEClass, FLAT_CONTAINER_STYLE__FOREGROUND_COLOR);

        shapeContainerStyleEClass = createEClass(SHAPE_CONTAINER_STYLE);
        createEAttribute(shapeContainerStyleEClass, SHAPE_CONTAINER_STYLE__SHAPE);
        createEReference(shapeContainerStyleEClass, SHAPE_CONTAINER_STYLE__BACKGROUND_COLOR);

        squareEClass = createEClass(SQUARE);
        createEAttribute(squareEClass, SQUARE__WIDTH);
        createEAttribute(squareEClass, SQUARE__HEIGHT);
        createEReference(squareEClass, SQUARE__COLOR);

        ellipseEClass = createEClass(ELLIPSE);
        createEAttribute(ellipseEClass, ELLIPSE__HORIZONTAL_DIAMETER);
        createEAttribute(ellipseEClass, ELLIPSE__VERTICAL_DIAMETER);
        createEReference(ellipseEClass, ELLIPSE__COLOR);

        lozengeEClass = createEClass(LOZENGE);
        createEAttribute(lozengeEClass, LOZENGE__WIDTH);
        createEAttribute(lozengeEClass, LOZENGE__HEIGHT);
        createEReference(lozengeEClass, LOZENGE__COLOR);

        bundledImageEClass = createEClass(BUNDLED_IMAGE);
        createEAttribute(bundledImageEClass, BUNDLED_IMAGE__SHAPE);
        createEReference(bundledImageEClass, BUNDLED_IMAGE__COLOR);

        workspaceImageEClass = createEClass(WORKSPACE_IMAGE);
        createEAttribute(workspaceImageEClass, WORKSPACE_IMAGE__WORKSPACE_PATH);

        customStyleEClass = createEClass(CUSTOM_STYLE);
        createEAttribute(customStyleEClass, CUSTOM_STYLE__ID);

        edgeTargetEClass = createEClass(EDGE_TARGET);
        createEReference(edgeTargetEClass, EDGE_TARGET__OUTGOING_EDGES);
        createEReference(edgeTargetEClass, EDGE_TARGET__INCOMING_EDGES);

        edgeStyleEClass = createEClass(EDGE_STYLE);
        createEReference(edgeStyleEClass, EDGE_STYLE__STROKE_COLOR);
        createEAttribute(edgeStyleEClass, EDGE_STYLE__LINE_STYLE);
        createEAttribute(edgeStyleEClass, EDGE_STYLE__SOURCE_ARROW);
        createEAttribute(edgeStyleEClass, EDGE_STYLE__TARGET_ARROW);
        createEAttribute(edgeStyleEClass, EDGE_STYLE__FOLDING_STYLE);
        createEAttribute(edgeStyleEClass, EDGE_STYLE__SIZE);
        createEAttribute(edgeStyleEClass, EDGE_STYLE__ROUTING_STYLE);
        createEReference(edgeStyleEClass, EDGE_STYLE__BEGIN_LABEL_STYLE);
        createEReference(edgeStyleEClass, EDGE_STYLE__CENTER_LABEL_STYLE);
        createEReference(edgeStyleEClass, EDGE_STYLE__END_LABEL_STYLE);

        dAnalysisCustomDataEClass = createEClass(DANALYSIS_CUSTOM_DATA);
        createEAttribute(dAnalysisCustomDataEClass, DANALYSIS_CUSTOM_DATA__KEY);
        createEReference(dAnalysisCustomDataEClass, DANALYSIS_CUSTOM_DATA__DATA);

        gaugeCompositeStyleEClass = createEClass(GAUGE_COMPOSITE_STYLE);
        createEAttribute(gaugeCompositeStyleEClass, GAUGE_COMPOSITE_STYLE__ALIGNMENT);
        createEReference(gaugeCompositeStyleEClass, GAUGE_COMPOSITE_STYLE__SECTIONS);

        labelStyleEClass = createEClass(LABEL_STYLE);
        createEAttribute(labelStyleEClass, LABEL_STYLE__LABEL_ALIGNMENT);

        styleEClass = createEClass(STYLE);
        createEReference(styleEClass, STYLE__DESCRIPTION);

        borderedStyleEClass = createEClass(BORDERED_STYLE);
        createEAttribute(borderedStyleEClass, BORDERED_STYLE__BORDER_SIZE);
        createEAttribute(borderedStyleEClass, BORDERED_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION);
        createEReference(borderedStyleEClass, BORDERED_STYLE__BORDER_COLOR);

        noteEClass = createEClass(NOTE);
        createEReference(noteEClass, NOTE__COLOR);

        dragAndDropTargetEClass = createEClass(DRAG_AND_DROP_TARGET);

        filterVariableHistoryEClass = createEClass(FILTER_VARIABLE_HISTORY);
        createEReference(filterVariableHistoryEClass, FILTER_VARIABLE_HISTORY__OWNED_VALUES);

        filterVariableValueEClass = createEClass(FILTER_VARIABLE_VALUE);
        createEReference(filterVariableValueEClass, FILTER_VARIABLE_VALUE__VARIABLE_DEFINITION);
        createEReference(filterVariableValueEClass, FILTER_VARIABLE_VALUE__MODEL_ELEMENT);

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

        collapseFilterEClass = createEClass(COLLAPSE_FILTER);
        createEAttribute(collapseFilterEClass, COLLAPSE_FILTER__WIDTH);
        createEAttribute(collapseFilterEClass, COLLAPSE_FILTER__HEIGHT);

        indirectlyCollapseFilterEClass = createEClass(INDIRECTLY_COLLAPSE_FILTER);

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

        beginLabelStyleEClass = createEClass(BEGIN_LABEL_STYLE);

        centerLabelStyleEClass = createEClass(CENTER_LABEL_STYLE);

        endLabelStyleEClass = createEClass(END_LABEL_STYLE);

        bracketEdgeStyleEClass = createEClass(BRACKET_EDGE_STYLE);

        customizableEClass = createEClass(CUSTOMIZABLE);
        createEAttribute(customizableEClass, CUSTOMIZABLE__CUSTOM_FEATURES);

        computedStyleDescriptionRegistryEClass = createEClass(COMPUTED_STYLE_DESCRIPTION_REGISTRY);
        createEReference(computedStyleDescriptionRegistryEClass, COMPUTED_STYLE_DESCRIPTION_REGISTRY__COMPUTED_STYLE_DESCRIPTIONS);
        createEReference(computedStyleDescriptionRegistryEClass, COMPUTED_STYLE_DESCRIPTION_REGISTRY__CACHE);

        diagramElementMapping2ModelElementEClass = createEClass(DIAGRAM_ELEMENT_MAPPING2_MODEL_ELEMENT);
        createEReference(diagramElementMapping2ModelElementEClass, DIAGRAM_ELEMENT_MAPPING2_MODEL_ELEMENT__KEY);
        createEReference(diagramElementMapping2ModelElementEClass, DIAGRAM_ELEMENT_MAPPING2_MODEL_ELEMENT__VALUE);

        modelElement2ViewVariableEClass = createEClass(MODEL_ELEMENT2_VIEW_VARIABLE);
        createEReference(modelElement2ViewVariableEClass, MODEL_ELEMENT2_VIEW_VARIABLE__KEY);
        createEReference(modelElement2ViewVariableEClass, MODEL_ELEMENT2_VIEW_VARIABLE__VALUE);

        viewVariable2ContainerVariableEClass = createEClass(VIEW_VARIABLE2_CONTAINER_VARIABLE);
        createEReference(viewVariable2ContainerVariableEClass, VIEW_VARIABLE2_CONTAINER_VARIABLE__KEY);
        createEReference(viewVariable2ContainerVariableEClass, VIEW_VARIABLE2_CONTAINER_VARIABLE__VALUE);

        containerVariable2StyleDescriptionEClass = createEClass(CONTAINER_VARIABLE2_STYLE_DESCRIPTION);
        createEReference(containerVariable2StyleDescriptionEClass, CONTAINER_VARIABLE2_STYLE_DESCRIPTION__KEY);
        createEReference(containerVariable2StyleDescriptionEClass, CONTAINER_VARIABLE2_STYLE_DESCRIPTION__VALUE);

        // Create enums
        containerLayoutEEnum = createEEnum(CONTAINER_LAYOUT);
        labelPositionEEnum = createEEnum(LABEL_POSITION);
        containerShapeEEnum = createEEnum(CONTAINER_SHAPE);
        backgroundStyleEEnum = createEEnum(BACKGROUND_STYLE);
        bundledImageShapeEEnum = createEEnum(BUNDLED_IMAGE_SHAPE);
        lineStyleEEnum = createEEnum(LINE_STYLE);
        edgeArrowsEEnum = createEEnum(EDGE_ARROWS);
        edgeRoutingEEnum = createEEnum(EDGE_ROUTING);
        alignmentKindEEnum = createEEnum(ALIGNMENT_KIND);
        fontFormatEEnum = createEEnum(FONT_FORMAT);
        resizeKindEEnum = createEEnum(RESIZE_KIND);
        labelAlignmentEEnum = createEEnum(LABEL_ALIGNMENT);
        arrangeConstraintEEnum = createEEnum(ARRANGE_CONSTRAINT);
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
        ConcernPackage theConcernPackage = (ConcernPackage) EPackage.Registry.INSTANCE.getEPackage(ConcernPackage.eNS_URI);
        FilterPackage theFilterPackage = (FilterPackage) EPackage.Registry.INSTANCE.getEPackage(FilterPackage.eNS_URI);
        ValidationPackage theValidationPackage = (ValidationPackage) EPackage.Registry.INSTANCE.getEPackage(ValidationPackage.eNS_URI);
        ToolPackage theToolPackage = (ToolPackage) EPackage.Registry.INSTANCE.getEPackage(ToolPackage.eNS_URI);
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
        dDiagramEClass.getESuperTypes().add(this.getDRepresentation());
        dDiagramEClass.getESuperTypes().add(theDescriptionPackage.getDocumentedElement());
        dDiagramEClass.getESuperTypes().add(this.getDragAndDropTarget());
        dDiagramEClass.getESuperTypes().add(this.getDValidable());
        dDiagramEClass.getESuperTypes().add(this.getDContainer());
        dSemanticDiagramEClass.getESuperTypes().add(this.getDDiagram());
        dSemanticDiagramEClass.getESuperTypes().add(this.getDSemanticDecorator());
        dDiagramElementEClass.getESuperTypes().add(this.getDRepresentationElement());
        dDiagramElementEClass.getESuperTypes().add(this.getDValidable());
        dDiagramElementEClass.getESuperTypes().add(this.getDNavigable());
        hideFilterEClass.getESuperTypes().add(this.getGraphicalFilter());
        hideLabelFilterEClass.getESuperTypes().add(this.getGraphicalFilter());
        foldingPointFilterEClass.getESuperTypes().add(this.getGraphicalFilter());
        foldingFilterEClass.getESuperTypes().add(this.getGraphicalFilter());
        appliedCompositeFiltersEClass.getESuperTypes().add(this.getGraphicalFilter());
        absoluteBoundsFilterEClass.getESuperTypes().add(this.getGraphicalFilter());
        deObjectLinkEClass.getESuperTypes().add(this.getDNavigationLink());
        dDiagramLinkEClass.getESuperTypes().add(this.getDNavigationLink());
        dSourceFileLinkEClass.getESuperTypes().add(this.getDNavigationLink());
        abstractDNodeEClass.getESuperTypes().add(this.getDDiagramElement());
        dNodeEClass.getESuperTypes().add(this.getAbstractDNode());
        dNodeEClass.getESuperTypes().add(this.getEdgeTarget());
        dNodeEClass.getESuperTypes().add(this.getDragAndDropTarget());
        dDiagramElementContainerEClass.getESuperTypes().add(this.getAbstractDNode());
        dDiagramElementContainerEClass.getESuperTypes().add(this.getEdgeTarget());
        dDiagramElementContainerEClass.getESuperTypes().add(this.getDragAndDropTarget());
        dDiagramElementContainerEClass.getESuperTypes().add(this.getDContainer());
        dNodeContainerEClass.getESuperTypes().add(this.getDDiagramElementContainer());
        dNodeListEClass.getESuperTypes().add(this.getDDiagramElementContainer());
        dNodeListElementEClass.getESuperTypes().add(this.getAbstractDNode());
        dEdgeEClass.getESuperTypes().add(this.getDDiagramElement());
        dEdgeEClass.getESuperTypes().add(this.getEdgeTarget());
        nodeStyleEClass.getESuperTypes().add(this.getLabelStyle());
        nodeStyleEClass.getESuperTypes().add(this.getStyle());
        nodeStyleEClass.getESuperTypes().add(this.getBorderedStyle());
        dotEClass.getESuperTypes().add(this.getNodeStyle());
        gaugeSectionEClass.getESuperTypes().add(this.getCustomizable());
        containerStyleEClass.getESuperTypes().add(this.getLabelStyle());
        containerStyleEClass.getESuperTypes().add(this.getStyle());
        containerStyleEClass.getESuperTypes().add(this.getBorderedStyle());
        flatContainerStyleEClass.getESuperTypes().add(this.getContainerStyle());
        shapeContainerStyleEClass.getESuperTypes().add(this.getContainerStyle());
        squareEClass.getESuperTypes().add(this.getNodeStyle());
        ellipseEClass.getESuperTypes().add(this.getNodeStyle());
        lozengeEClass.getESuperTypes().add(this.getNodeStyle());
        bundledImageEClass.getESuperTypes().add(this.getNodeStyle());
        workspaceImageEClass.getESuperTypes().add(this.getNodeStyle());
        workspaceImageEClass.getESuperTypes().add(this.getContainerStyle());
        customStyleEClass.getESuperTypes().add(this.getNodeStyle());
        edgeStyleEClass.getESuperTypes().add(this.getStyle());
        gaugeCompositeStyleEClass.getESuperTypes().add(this.getNodeStyle());
        labelStyleEClass.getESuperTypes().add(this.getBasicLabelStyle());
        styleEClass.getESuperTypes().add(this.getDRefreshable());
        styleEClass.getESuperTypes().add(this.getCustomizable());
        borderedStyleEClass.getESuperTypes().add(this.getStyle());
        noteEClass.getESuperTypes().add(this.getNodeStyle());
        collapseFilterEClass.getESuperTypes().add(this.getGraphicalFilter());
        indirectlyCollapseFilterEClass.getESuperTypes().add(this.getCollapseFilter());
        dFileEClass.getESuperTypes().add(this.getDResource());
        dResourceContainerEClass.getESuperTypes().add(this.getDResource());
        dProjectEClass.getESuperTypes().add(this.getDResourceContainer());
        dFolderEClass.getESuperTypes().add(this.getDResourceContainer());
        dModelEClass.getESuperTypes().add(this.getDFile());
        basicLabelStyleEClass.getESuperTypes().add(this.getCustomizable());
        beginLabelStyleEClass.getESuperTypes().add(this.getBasicLabelStyle());
        centerLabelStyleEClass.getESuperTypes().add(this.getBasicLabelStyle());
        endLabelStyleEClass.getESuperTypes().add(this.getBasicLabelStyle());
        bracketEdgeStyleEClass.getESuperTypes().add(this.getEdgeStyle());

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
        initEReference(getDRepresentationContainer_DiagramSet(), this.getDDiagramSet(), null, "diagramSet", null, 0, -1, DRepresentationContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDRepresentationContainer_Models(), theEcorePackage.getEObject(), null, "models", null, 0, -1, DRepresentationContainer.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

        EOperation op = addEOperation(dRepresentationContainerEClass, null, "addSemanticDiagram", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getDSemanticDiagram(), "diagram", 0, 1, IS_UNIQUE, IS_ORDERED);

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

        op = addEOperation(dRepresentationEClass, null, "createContents", 0, 1, IS_UNIQUE, IS_ORDERED);
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

        initEClass(dDiagramEClass, DDiagram.class, "DDiagram", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDDiagram_OwnedDiagramElements(), this.getDDiagramElement(), null, "ownedDiagramElements", null, 0, -1, DDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDDiagram_DiagramElements(), this.getDDiagramElement(), null, "diagramElements", null, 0, -1, DDiagram.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDDiagram_Description(), theDescriptionPackage.getDiagramDescription(), null, "description", null, 0, 1, DDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDDiagram_Info(), ecorePackage.getEString(), "info", "", 0, 1, DDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
                IS_ORDERED);
        initEReference(getDDiagram_SubDiagrams(), this.getDDiagram(), null, "subDiagrams", null, 0, -1, DDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDDiagram_Edges(), this.getDEdge(), null, "edges", null, 0, -1, DDiagram.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDDiagram_Nodes(), this.getDNode(), null, "nodes", null, 0, -1, DDiagram.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDDiagram_NodeListElements(), this.getDNodeListElement(), null, "nodeListElements", null, 0, -1, DDiagram.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDDiagram_Containers(), this.getDDiagramElementContainer(), null, "containers", null, 0, -1, DDiagram.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDDiagram_CurrentConcern(), theConcernPackage.getConcernDescription(), null, "currentConcern", null, 0, 1, DDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDDiagram_ActivatedFilters(), theFilterPackage.getFilterDescription(), null, "activatedFilters", null, 0, -1, DDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDDiagram_AllFilters(), theFilterPackage.getFilterDescription(), null, "allFilters", null, 0, -1, DDiagram.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDDiagram_ActivatedRules(), theValidationPackage.getValidationRule(), null, "activatedRules", null, 0, -1, DDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDDiagram_ActivateBehaviors(), theToolPackage.getBehaviorTool(), null, "activateBehaviors", null, 0, -1, DDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDDiagram_FilterVariableHistory(), this.getFilterVariableHistory(), null, "filterVariableHistory", null, 1, 1, DDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDDiagram_ActivatedLayers(), theDescriptionPackage.getLayer(), null, "activatedLayers", null, 0, -1, DDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDDiagram_Synchronized(), theEcorePackage.getEBoolean(), "synchronized", "true", 0, 1, DDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDDiagram_HiddenElements(), this.getDDiagramElement(), null, "hiddenElements", null, 0, -1, DDiagram.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDDiagram_IsInLayoutingMode(), theEcorePackage.getEBoolean(), "isInLayoutingMode", null, 0, 1, DDiagram.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDDiagram_HeaderHeight(), theEcorePackage.getEInt(), "headerHeight", "1", 0, 1, DDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        addEOperation(dDiagramEClass, null, "clean", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(dDiagramEClass, this.getDNode(), "getNodesFromMapping", 0, -1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theDescriptionPackage.getNodeMapping(), "mapping", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(dDiagramEClass, this.getDEdge(), "getEdgesFromMapping", 0, -1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theDescriptionPackage.getEdgeMapping(), "mapping", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(dDiagramEClass, this.getDDiagramElementContainer(), "getContainersFromMapping", 0, -1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theDescriptionPackage.getContainerMapping(), "mapping", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(dDiagramEClass, this.getDDiagramElement(), "findDiagramElements", 0, -1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "semanticElement", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEClass(), "type", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(dSemanticDiagramEClass, DSemanticDiagram.class, "DSemanticDiagram", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        addEOperation(dSemanticDiagramEClass, theEcorePackage.getEObject(), "getRootContent", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(dDiagramElementEClass, DDiagramElement.class, "DDiagramElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDDiagramElement_Visible(), theEcorePackage.getEBoolean(), "visible", "true", 0, 1, DDiagramElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDDiagramElement_TooltipText(), theEcorePackage.getEString(), "tooltipText", null, 0, 1, DDiagramElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDDiagramElement_ParentLayers(), theDescriptionPackage.getLayer(), null, "parentLayers", null, 0, -1, DDiagramElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDDiagramElement_Decorations(), this.getDecoration(), null, "decorations", null, 0, -1, DDiagramElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDDiagramElement_DiagramElementMapping(), theDescriptionPackage.getDiagramElementMapping(), null, "diagramElementMapping", null, 0, 1, DDiagramElement.class, IS_TRANSIENT,
                IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDDiagramElement_GraphicalFilters(), this.getGraphicalFilter(), null, "graphicalFilters", null, 0, -1, DDiagramElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        addEOperation(dDiagramElementEClass, this.getDDiagram(), "getParentDiagram", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(dDiagramElementEClass, theEcorePackage.getEBoolean(), "isFold", 0, 1, IS_UNIQUE, IS_ORDERED);
        EGenericType g1 = createEGenericType(theEcorePackage.getEMap());
        EGenericType g2 = createEGenericType();
        g1.getETypeArguments().add(g2);
        g2 = createEGenericType();
        g1.getETypeArguments().add(g2);
        addEParameter(op, g1, "alreadyManagedElements", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(graphicalFilterEClass, GraphicalFilter.class, "GraphicalFilter", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(hideFilterEClass, HideFilter.class, "HideFilter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(hideLabelFilterEClass, HideLabelFilter.class, "HideLabelFilter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(foldingPointFilterEClass, FoldingPointFilter.class, "FoldingPointFilter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(foldingFilterEClass, FoldingFilter.class, "FoldingFilter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(appliedCompositeFiltersEClass, AppliedCompositeFilters.class, "AppliedCompositeFilters", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getAppliedCompositeFilters_CompositeFilterDescriptions(), theFilterPackage.getCompositeFilterDescription(), null, "compositeFilterDescriptions", null, 0, -1,
                AppliedCompositeFilters.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(absoluteBoundsFilterEClass, AbsoluteBoundsFilter.class, "AbsoluteBoundsFilter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbsoluteBoundsFilter_X(), ecorePackage.getEIntegerObject(), "x", null, 0, 1, AbsoluteBoundsFilter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbsoluteBoundsFilter_Y(), ecorePackage.getEIntegerObject(), "y", null, 0, 1, AbsoluteBoundsFilter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbsoluteBoundsFilter_Height(), ecorePackage.getEIntegerObject(), "height", null, 0, 1, AbsoluteBoundsFilter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbsoluteBoundsFilter_Width(), ecorePackage.getEIntegerObject(), "width", null, 0, 1, AbsoluteBoundsFilter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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

        initEClass(dDiagramLinkEClass, DDiagramLink.class, "DDiagramLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDDiagramLink_Target(), this.getDDiagram(), null, "target", null, 1, 1, DDiagramLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDDiagramLink_Node(), this.getEdgeTarget(), null, "node", null, 0, 1, DDiagramLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(dSourceFileLinkEClass, DSourceFileLink.class, "DSourceFileLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDSourceFileLink_FilePath(), theEcorePackage.getEString(), "filePath", null, 1, 1, DSourceFileLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDSourceFileLink_StartPosition(), theEcorePackage.getEInt(), "startPosition", "0", 0, 1, DSourceFileLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDSourceFileLink_EndPosition(), theEcorePackage.getEInt(), "endPosition", "1", 0, 1, DSourceFileLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(abstractDNodeEClass, AbstractDNode.class, "AbstractDNode", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getAbstractDNode_OwnedBorderedNodes(), this.getDNode(), null, "ownedBorderedNodes", null, 0, -1, AbstractDNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractDNode_ArrangeConstraints(), this.getArrangeConstraint(), "arrangeConstraints", "KEEP_LOCATION", 0, -1, AbstractDNode.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(dNodeEClass, DNode.class, "DNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDNode_Width(), theEcorePackage.getEIntegerObject(), "width", null, 0, 1, DNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDNode_Height(), theEcorePackage.getEIntegerObject(), "height", null, 0, 1, DNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEReference(getDNode_OwnedStyle(), this.getNodeStyle(), null, "ownedStyle", null, 0, 1, DNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDNode_LabelPosition(), this.getLabelPosition(), "labelPosition", null, 0, 1, DNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEReference(getDNode_OwnedDetails(), this.getDDiagram(), null, "ownedDetails", null, 0, -1, DNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDNode_ResizeKind(), this.getResizeKind(), "resizeKind", "NONE", 0, 1, DNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEReference(getDNode_OriginalStyle(), this.getStyle(), null, "originalStyle", null, 0, 1, DNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDNode_ActualMapping(), theDescriptionPackage.getNodeMapping(), null, "actualMapping", null, 1, 1, DNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDNode_CandidatesMapping(), theDescriptionPackage.getNodeMapping(), null, "candidatesMapping", null, 0, -1, DNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(dDiagramElementContainerEClass, DDiagramElementContainer.class, "DDiagramElementContainer", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDDiagramElementContainer_Nodes(), this.getDNode(), null, "nodes", null, 0, -1, DDiagramElementContainer.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDDiagramElementContainer_Containers(), this.getDDiagramElementContainer(), null, "containers", null, 0, -1, DDiagramElementContainer.class, IS_TRANSIENT, IS_VOLATILE,
                !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDDiagramElementContainer_Elements(), this.getDDiagramElement(), null, "elements", null, 0, -1, DDiagramElementContainer.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDDiagramElementContainer_OwnedStyle(), this.getContainerStyle(), null, "ownedStyle", null, 0, 1, DDiagramElementContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDDiagramElementContainer_OwnedDetails(), this.getDDiagram(), null, "ownedDetails", null, 0, -1, DDiagramElementContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDDiagramElementContainer_OriginalStyle(), this.getStyle(), null, "originalStyle", null, 0, 1, DDiagramElementContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDDiagramElementContainer_ActualMapping(), theDescriptionPackage.getContainerMapping(), null, "actualMapping", null, 1, 1, DDiagramElementContainer.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDDiagramElementContainer_CandidatesMapping(), theDescriptionPackage.getContainerMapping(), null, "candidatesMapping", null, 0, -1, DDiagramElementContainer.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDDiagramElementContainer_Width(), ecorePackage.getEIntegerObject(), "width", null, 0, 1, DDiagramElementContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDDiagramElementContainer_Height(), ecorePackage.getEIntegerObject(), "height", null, 0, 1, DDiagramElementContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        op = addEOperation(dDiagramElementContainerEClass, this.getDNode(), "getNodesFromMapping", 0, -1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theDescriptionPackage.getNodeMapping(), "mapping", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(dDiagramElementContainerEClass, this.getDDiagramElementContainer(), "getContainersFromMapping", 0, -1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theDescriptionPackage.getContainerMapping(), "mapping", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(dNodeContainerEClass, DNodeContainer.class, "DNodeContainer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDNodeContainer_OwnedDiagramElements(), this.getDDiagramElement(), null, "ownedDiagramElements", null, 0, -1, DNodeContainer.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDNodeContainer_ChildrenPresentation(), this.getContainerLayout(), "childrenPresentation", "FreeForm", 1, 1, DNodeContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(dNodeListEClass, DNodeList.class, "DNodeList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDNodeList_OwnedElements(), this.getDNodeListElement(), null, "ownedElements", null, 0, -1, DNodeList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDNodeList_LineWidth(), theEcorePackage.getEInt(), "lineWidth", "1", 0, 1, DNodeList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        initEClass(dNodeListElementEClass, DNodeListElement.class, "DNodeListElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDNodeListElement_OwnedStyle(), this.getNodeStyle(), null, "ownedStyle", null, 0, 1, DNodeListElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDNodeListElement_OriginalStyle(), this.getStyle(), null, "originalStyle", null, 0, 1, DNodeListElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDNodeListElement_ActualMapping(), theDescriptionPackage.getNodeMapping(), null, "actualMapping", null, 1, 1, DNodeListElement.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDNodeListElement_CandidatesMapping(), theDescriptionPackage.getNodeMapping(), null, "candidatesMapping", null, 0, -1, DNodeListElement.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(dEdgeEClass, DEdge.class, "DEdge", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDEdge_OwnedStyle(), this.getEdgeStyle(), null, "ownedStyle", null, 0, 1, DEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDEdge_Size(), ecorePackage.getEIntegerObject(), "size", "1", 0, 1, DEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
                IS_ORDERED);
        initEReference(getDEdge_SourceNode(), this.getEdgeTarget(), this.getEdgeTarget_OutgoingEdges(), "sourceNode", null, 1, 1, DEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDEdge_TargetNode(), this.getEdgeTarget(), this.getEdgeTarget_IncomingEdges(), "targetNode", null, 1, 1, DEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDEdge_ActualMapping(), theDescriptionPackage.getIEdgeMapping(), null, "actualMapping", null, 1, 1, DEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDEdge_RoutingStyle(), this.getEdgeRouting(), "routingStyle", "straight", 1, 1, DEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDEdge_IsFold(), theEcorePackage.getEBoolean(), "isFold", null, 0, 1, DEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
                IS_ORDERED);
        initEAttribute(getDEdge_IsMockEdge(), theEcorePackage.getEBoolean(), "isMockEdge", null, 0, 1, DEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEReference(getDEdge_OriginalStyle(), this.getStyle(), null, "originalStyle", null, 0, 1, DEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDEdge_Path(), this.getEdgeTarget(), null, "path", null, 0, -1, DEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDEdge_ArrangeConstraints(), this.getArrangeConstraint(), "arrangeConstraints", "KEEP_LOCATION", 0, -1, DEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDEdge_BeginLabel(), ecorePackage.getEString(), "beginLabel", "", 0, 1, DEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDEdge_EndLabel(), ecorePackage.getEString(), "endLabel", "", 0, 1, DEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
                IS_ORDERED);

        addEOperation(dEdgeEClass, theEcorePackage.getEBoolean(), "isRootFolding", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(dDiagramSetEClass, DDiagramSet.class, "DDiagramSet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDDiagramSet_Description(), theDescriptionPackage.getDiagramDescription(), null, "description", null, 0, 1, DDiagramSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDDiagramSet_Diagrams(), this.getDDiagram(), null, "diagrams", null, 0, -1, DDiagramSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, !IS_ORDERED);
        initEReference(getDDiagramSet_View(), this.getDRepresentationContainer(), null, "view", null, 0, 1, DDiagramSet.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(nodeStyleEClass, NodeStyle.class, "NodeStyle", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getNodeStyle_LabelPosition(), this.getLabelPosition(), "labelPosition", null, 0, 1, NodeStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getNodeStyle_HideLabelByDefault(), ecorePackage.getEBoolean(), "hideLabelByDefault", "false", 0, 1, NodeStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(dotEClass, Dot.class, "Dot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDot_BackgroundColor(), this.getRGBValues(), null, "backgroundColor", null, 0, 1, Dot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDot_StrokeSizeComputationExpression(), theDescriptionPackage.getInterpretedExpression(), "strokeSizeComputationExpression", "2", 0, 1, Dot.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(gaugeSectionEClass, GaugeSection.class, "GaugeSection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getGaugeSection_Min(), theEcorePackage.getEIntegerObject(), "min", null, 0, 1, GaugeSection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGaugeSection_Max(), theEcorePackage.getEIntegerObject(), "max", null, 0, 1, GaugeSection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGaugeSection_Value(), theEcorePackage.getEIntegerObject(), "value", null, 0, 1, GaugeSection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGaugeSection_Label(), theEcorePackage.getEString(), "label", null, 0, 1, GaugeSection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEReference(getGaugeSection_BackgroundColor(), this.getRGBValues(), null, "backgroundColor", null, 0, 1, GaugeSection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getGaugeSection_ForegroundColor(), this.getRGBValues(), null, "foregroundColor", null, 0, 1, GaugeSection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(containerStyleEClass, ContainerStyle.class, "ContainerStyle", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(flatContainerStyleEClass, FlatContainerStyle.class, "FlatContainerStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFlatContainerStyle_BackgroundStyle(), this.getBackgroundStyle(), "backgroundStyle", null, 1, 1, FlatContainerStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFlatContainerStyle_BackgroundColor(), this.getRGBValues(), null, "backgroundColor", null, 0, 1, FlatContainerStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFlatContainerStyle_ForegroundColor(), this.getRGBValues(), null, "foregroundColor", null, 0, 1, FlatContainerStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(shapeContainerStyleEClass, ShapeContainerStyle.class, "ShapeContainerStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getShapeContainerStyle_Shape(), this.getContainerShape(), "shape", null, 1, 1, ShapeContainerStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getShapeContainerStyle_BackgroundColor(), this.getRGBValues(), null, "backgroundColor", null, 1, 1, ShapeContainerStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(squareEClass, Square.class, "Square", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSquare_Width(), ecorePackage.getEIntegerObject(), "width", "0", 0, 1, Square.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSquare_Height(), ecorePackage.getEIntegerObject(), "height", "0", 0, 1, Square.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEReference(getSquare_Color(), this.getRGBValues(), null, "color", null, 0, 1, Square.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(ellipseEClass, Ellipse.class, "Ellipse", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getEllipse_HorizontalDiameter(), ecorePackage.getEIntegerObject(), "horizontalDiameter", "0", 0, 1, Ellipse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEllipse_VerticalDiameter(), ecorePackage.getEIntegerObject(), "verticalDiameter", "0", 0, 1, Ellipse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEllipse_Color(), this.getRGBValues(), null, "color", null, 0, 1, Ellipse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(lozengeEClass, Lozenge.class, "Lozenge", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getLozenge_Width(), ecorePackage.getEIntegerObject(), "width", "0", 0, 1, Lozenge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLozenge_Height(), ecorePackage.getEIntegerObject(), "height", "0", 0, 1, Lozenge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEReference(getLozenge_Color(), this.getRGBValues(), null, "color", null, 0, 1, Lozenge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(bundledImageEClass, BundledImage.class, "BundledImage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBundledImage_Shape(), this.getBundledImageShape(), "shape", null, 1, 1, BundledImage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEReference(getBundledImage_Color(), this.getRGBValues(), null, "color", null, 1, 1, BundledImage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(workspaceImageEClass, WorkspaceImage.class, "WorkspaceImage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getWorkspaceImage_WorkspacePath(), theEcorePackage.getEString(), "workspacePath", null, 1, 1, WorkspaceImage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(customStyleEClass, CustomStyle.class, "CustomStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getCustomStyle_Id(), theEcorePackage.getEString(), "id", null, 0, 1, CustomStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        initEClass(edgeTargetEClass, EdgeTarget.class, "EdgeTarget", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getEdgeTarget_OutgoingEdges(), this.getDEdge(), this.getDEdge_SourceNode(), "outgoingEdges", null, 0, -1, EdgeTarget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeTarget_IncomingEdges(), this.getDEdge(), this.getDEdge_TargetNode(), "incomingEdges", null, 0, -1, EdgeTarget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(edgeStyleEClass, EdgeStyle.class, "EdgeStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getEdgeStyle_StrokeColor(), this.getRGBValues(), null, "strokeColor", null, 0, 1, EdgeStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEdgeStyle_LineStyle(), this.getLineStyle(), "lineStyle", null, 0, 1, EdgeStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEdgeStyle_SourceArrow(), this.getEdgeArrows(), "sourceArrow", "NoDecoration", 1, 1, EdgeStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEdgeStyle_TargetArrow(), this.getEdgeArrows(), "targetArrow", "InputArrow", 1, 1, EdgeStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEdgeStyle_FoldingStyle(), theDescriptionPackage.getFoldingStyle(), "foldingStyle", "NONE", 1, 1, EdgeStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEdgeStyle_Size(), ecorePackage.getEIntegerObject(), "size", "1", 0, 1, EdgeStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEdgeStyle_RoutingStyle(), this.getEdgeRouting(), "routingStyle", "straight", 1, 1, EdgeStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeStyle_BeginLabelStyle(), this.getBeginLabelStyle(), null, "beginLabelStyle", null, 0, 1, EdgeStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeStyle_CenterLabelStyle(), this.getCenterLabelStyle(), null, "centerLabelStyle", null, 0, 1, EdgeStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeStyle_EndLabelStyle(), this.getEndLabelStyle(), null, "endLabelStyle", null, 0, 1, EdgeStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(dAnalysisCustomDataEClass, DAnalysisCustomData.class, "DAnalysisCustomData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDAnalysisCustomData_Key(), theEcorePackage.getEString(), "key", null, 1, 1, DAnalysisCustomData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDAnalysisCustomData_Data(), theEcorePackage.getEObject(), null, "data", null, 1, 1, DAnalysisCustomData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(gaugeCompositeStyleEClass, GaugeCompositeStyle.class, "GaugeCompositeStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getGaugeCompositeStyle_Alignment(), this.getAlignmentKind(), "alignment", "SQUARE", 0, 1, GaugeCompositeStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getGaugeCompositeStyle_Sections(), this.getGaugeSection(), null, "sections", null, 0, -1, GaugeCompositeStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(labelStyleEClass, LabelStyle.class, "LabelStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getLabelStyle_LabelAlignment(), this.getLabelAlignment(), "labelAlignment", null, 0, 1, LabelStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(styleEClass, Style.class, "Style", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getStyle_Description(), theStylePackage.getStyleDescription(), null, "description", null, 0, 1, Style.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(borderedStyleEClass, BorderedStyle.class, "BorderedStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBorderedStyle_BorderSize(), theEcorePackage.getEIntegerObject(), "borderSize", "0", 1, 1, BorderedStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBorderedStyle_BorderSizeComputationExpression(), theDescriptionPackage.getInterpretedExpression(), "borderSizeComputationExpression", "0", 0, 1, BorderedStyle.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getBorderedStyle_BorderColor(), this.getRGBValues(), null, "borderColor", null, 0, 1, BorderedStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(noteEClass, Note.class, "Note", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getNote_Color(), this.getRGBValues(), null, "color", null, 0, 1, Note.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(dragAndDropTargetEClass, DragAndDropTarget.class, "DragAndDropTarget", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        addEOperation(dragAndDropTargetEClass, theDescriptionPackage.getDragAndDropTargetDescription(), "getDragAndDropDescription", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(filterVariableHistoryEClass, FilterVariableHistory.class, "FilterVariableHistory", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getFilterVariableHistory_OwnedValues(), this.getFilterVariableValue(), null, "ownedValues", null, 0, -1, FilterVariableHistory.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(filterVariableValueEClass, FilterVariableValue.class, "FilterVariableValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getFilterVariableValue_VariableDefinition(), theFilterPackage.getFilterVariable(), null, "variableDefinition", null, 1, 1, FilterVariableValue.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFilterVariableValue_ModelElement(), theEcorePackage.getEObject(), null, "modelElement", null, 1, 1, FilterVariableValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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

        initEClass(collapseFilterEClass, CollapseFilter.class, "CollapseFilter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getCollapseFilter_Width(), ecorePackage.getEInt(), "width", null, 0, 1, CollapseFilter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getCollapseFilter_Height(), ecorePackage.getEInt(), "height", null, 0, 1, CollapseFilter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        initEClass(indirectlyCollapseFilterEClass, IndirectlyCollapseFilter.class, "IndirectlyCollapseFilter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

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

        initEClass(beginLabelStyleEClass, BeginLabelStyle.class, "BeginLabelStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        addEOperation(beginLabelStyleEClass, theStylePackage.getBasicLabelStyleDescription(), "getDescription", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(beginLabelStyleEClass, null, "setDescription", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theStylePackage.getBasicLabelStyleDescription(), "description", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(centerLabelStyleEClass, CenterLabelStyle.class, "CenterLabelStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        addEOperation(centerLabelStyleEClass, theStylePackage.getBasicLabelStyleDescription(), "getDescription", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(centerLabelStyleEClass, null, "setDescription", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theStylePackage.getBasicLabelStyleDescription(), "description", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(endLabelStyleEClass, EndLabelStyle.class, "EndLabelStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        addEOperation(endLabelStyleEClass, theStylePackage.getBasicLabelStyleDescription(), "getDescription", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(endLabelStyleEClass, null, "setDescription", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theStylePackage.getBasicLabelStyleDescription(), "description", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(bracketEdgeStyleEClass, BracketEdgeStyle.class, "BracketEdgeStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(customizableEClass, Customizable.class, "Customizable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getCustomizable_CustomFeatures(), ecorePackage.getEString(), "customFeatures", null, 0, -1, Customizable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(computedStyleDescriptionRegistryEClass, ComputedStyleDescriptionRegistry.class, "ComputedStyleDescriptionRegistry", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getComputedStyleDescriptionRegistry_ComputedStyleDescriptions(), theStylePackage.getStyleDescription(), null, "computedStyleDescriptions", null, 0, -1,
                ComputedStyleDescriptionRegistry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getComputedStyleDescriptionRegistry_Cache(), this.getDiagramElementMapping2ModelElement(), null, "cache", null, 0, -1, ComputedStyleDescriptionRegistry.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(diagramElementMapping2ModelElementEClass, Map.Entry.class, "DiagramElementMapping2ModelElement", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDiagramElementMapping2ModelElement_Key(), theDescriptionPackage.getDiagramElementMapping(), null, "key", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDiagramElementMapping2ModelElement_Value(), this.getModelElement2ViewVariable(), null, "value", null, 0, -1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(modelElement2ViewVariableEClass, Map.Entry.class, "ModelElement2ViewVariable", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
        initEReference(getModelElement2ViewVariable_Key(), theEcorePackage.getEObject(), null, "key", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getModelElement2ViewVariable_Value(), this.getViewVariable2ContainerVariable(), null, "value", null, 0, -1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(viewVariable2ContainerVariableEClass, Map.Entry.class, "ViewVariable2ContainerVariable", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
        initEReference(getViewVariable2ContainerVariable_Key(), theEcorePackage.getEObject(), null, "key", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getViewVariable2ContainerVariable_Value(), this.getContainerVariable2StyleDescription(), null, "value", null, 0, -1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(containerVariable2StyleDescriptionEClass, Map.Entry.class, "ContainerVariable2StyleDescription", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
        initEReference(getContainerVariable2StyleDescription_Key(), theEcorePackage.getEObject(), null, "key", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getContainerVariable2StyleDescription_Value(), theStylePackage.getStyleDescription(), null, "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(containerLayoutEEnum, ContainerLayout.class, "ContainerLayout");
        addEEnumLiteral(containerLayoutEEnum, ContainerLayout.FREE_FORM);
        addEEnumLiteral(containerLayoutEEnum, ContainerLayout.LIST);
        addEEnumLiteral(containerLayoutEEnum, ContainerLayout.HORIZONTAL_STACK);
        addEEnumLiteral(containerLayoutEEnum, ContainerLayout.VERTICAL_STACK);

        initEEnum(labelPositionEEnum, LabelPosition.class, "LabelPosition");
        addEEnumLiteral(labelPositionEEnum, LabelPosition.BORDER_LITERAL);
        addEEnumLiteral(labelPositionEEnum, LabelPosition.NODE_LITERAL);

        initEEnum(containerShapeEEnum, ContainerShape.class, "ContainerShape");
        addEEnumLiteral(containerShapeEEnum, ContainerShape.PARALLELOGRAM_LITERAL);

        initEEnum(backgroundStyleEEnum, BackgroundStyle.class, "BackgroundStyle");
        addEEnumLiteral(backgroundStyleEEnum, BackgroundStyle.GRADIENT_LEFT_TO_RIGHT_LITERAL);
        addEEnumLiteral(backgroundStyleEEnum, BackgroundStyle.LIQUID_LITERAL);
        addEEnumLiteral(backgroundStyleEEnum, BackgroundStyle.GRADIENT_TOP_TO_BOTTOM_LITERAL);

        initEEnum(bundledImageShapeEEnum, BundledImageShape.class, "BundledImageShape");
        addEEnumLiteral(bundledImageShapeEEnum, BundledImageShape.SQUARE_LITERAL);
        addEEnumLiteral(bundledImageShapeEEnum, BundledImageShape.STROKE_LITERAL);
        addEEnumLiteral(bundledImageShapeEEnum, BundledImageShape.TRIANGLE_LITERAL);
        addEEnumLiteral(bundledImageShapeEEnum, BundledImageShape.DOT_LITERAL);
        addEEnumLiteral(bundledImageShapeEEnum, BundledImageShape.RING_LITERAL);

        initEEnum(lineStyleEEnum, LineStyle.class, "LineStyle");
        addEEnumLiteral(lineStyleEEnum, LineStyle.SOLID_LITERAL);
        addEEnumLiteral(lineStyleEEnum, LineStyle.DASH_LITERAL);
        addEEnumLiteral(lineStyleEEnum, LineStyle.DOT_LITERAL);
        addEEnumLiteral(lineStyleEEnum, LineStyle.DASH_DOT_LITERAL);

        initEEnum(edgeArrowsEEnum, EdgeArrows.class, "EdgeArrows");
        addEEnumLiteral(edgeArrowsEEnum, EdgeArrows.NO_DECORATION_LITERAL);
        addEEnumLiteral(edgeArrowsEEnum, EdgeArrows.OUTPUT_ARROW_LITERAL);
        addEEnumLiteral(edgeArrowsEEnum, EdgeArrows.INPUT_ARROW_LITERAL);
        addEEnumLiteral(edgeArrowsEEnum, EdgeArrows.OUTPUT_CLOSED_ARROW_LITERAL);
        addEEnumLiteral(edgeArrowsEEnum, EdgeArrows.INPUT_CLOSED_ARROW_LITERAL);
        addEEnumLiteral(edgeArrowsEEnum, EdgeArrows.OUTPUT_FILL_CLOSED_ARROW_LITERAL);
        addEEnumLiteral(edgeArrowsEEnum, EdgeArrows.INPUT_FILL_CLOSED_ARROW_LITERAL);
        addEEnumLiteral(edgeArrowsEEnum, EdgeArrows.DIAMOND_LITERAL);
        addEEnumLiteral(edgeArrowsEEnum, EdgeArrows.FILL_DIAMOND_LITERAL);
        addEEnumLiteral(edgeArrowsEEnum, EdgeArrows.INPUT_ARROW_WITH_DIAMOND_LITERAL);
        addEEnumLiteral(edgeArrowsEEnum, EdgeArrows.INPUT_ARROW_WITH_FILL_DIAMOND_LITERAL);

        initEEnum(edgeRoutingEEnum, EdgeRouting.class, "EdgeRouting");
        addEEnumLiteral(edgeRoutingEEnum, EdgeRouting.STRAIGHT_LITERAL);
        addEEnumLiteral(edgeRoutingEEnum, EdgeRouting.MANHATTAN_LITERAL);
        addEEnumLiteral(edgeRoutingEEnum, EdgeRouting.TREE_LITERAL);

        initEEnum(alignmentKindEEnum, AlignmentKind.class, "AlignmentKind");
        addEEnumLiteral(alignmentKindEEnum, AlignmentKind.VERTICAL_LITERAL);
        addEEnumLiteral(alignmentKindEEnum, AlignmentKind.HORIZONTAL_LITERAL);
        addEEnumLiteral(alignmentKindEEnum, AlignmentKind.SQUARE_LITERAL);

        initEEnum(fontFormatEEnum, FontFormat.class, "FontFormat");
        addEEnumLiteral(fontFormatEEnum, FontFormat.NORMAL_LITERAL);
        addEEnumLiteral(fontFormatEEnum, FontFormat.ITALIC_LITERAL);
        addEEnumLiteral(fontFormatEEnum, FontFormat.BOLD_LITERAL);
        addEEnumLiteral(fontFormatEEnum, FontFormat.BOLD_ITALIC_LITERAL);

        initEEnum(resizeKindEEnum, ResizeKind.class, "ResizeKind");
        addEEnumLiteral(resizeKindEEnum, ResizeKind.NONE_LITERAL);
        addEEnumLiteral(resizeKindEEnum, ResizeKind.NSEW_LITERAL);
        addEEnumLiteral(resizeKindEEnum, ResizeKind.NORTH_SOUTH_LITERAL);
        addEEnumLiteral(resizeKindEEnum, ResizeKind.EAST_WEST_LITERAL);

        initEEnum(labelAlignmentEEnum, LabelAlignment.class, "LabelAlignment");
        addEEnumLiteral(labelAlignmentEEnum, LabelAlignment.CENTER);
        addEEnumLiteral(labelAlignmentEEnum, LabelAlignment.LEFT);
        addEEnumLiteral(labelAlignmentEEnum, LabelAlignment.RIGHT);

        initEEnum(arrangeConstraintEEnum, ArrangeConstraint.class, "ArrangeConstraint");
        addEEnumLiteral(arrangeConstraintEEnum, ArrangeConstraint.KEEP_LOCATION);
        addEEnumLiteral(arrangeConstraintEEnum, ArrangeConstraint.KEEP_SIZE);
        addEEnumLiteral(arrangeConstraintEEnum, ArrangeConstraint.KEEP_RATIO);

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
        // http://www.eclipse.org/sirius/interpreted/expression/returnType
        createReturnTypeAnnotations();
        // http://www.eclipse.org/sirius/interpreted/expression/variables
        createVariablesAnnotations();
        // Sirius
        createSiriusAnnotations();
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

    /**
     * Initializes the annotations for
     * <b>http://www.eclipse.org/sirius/interpreted/expression/returnType</b>.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void createReturnTypeAnnotations() {
        String source = "http://www.eclipse.org/sirius/interpreted/expression/returnType";
        addAnnotation(getDot_StrokeSizeComputationExpression(), source, new String[] { "returnType", "an integer." });
        addAnnotation(getBorderedStyle_BorderSizeComputationExpression(), source, new String[] { "returnType", "an integer." });
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
        addAnnotation(getDot_StrokeSizeComputationExpression(), source, new String[] {});
        addAnnotation(getBorderedStyle_BorderSizeComputationExpression(), source, new String[] {});
    }

    /**
     * Initializes the annotations for <b>Sirius</b>. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void createSiriusAnnotations() {
        String source = "Sirius";
        addAnnotation(edgeArrowsEEnum.getELiterals().get(0), source, new String[] { "imagePath", "icons/full/decorator/noDecoration.gif" });
        addAnnotation(edgeArrowsEEnum.getELiterals().get(1), source, new String[] { "imagePath", "icons/full/decorator/outputArrow.gif" });
        addAnnotation(edgeArrowsEEnum.getELiterals().get(2), source, new String[] { "imagePath", "icons/full/decorator/inputArrow.gif" });
        addAnnotation(edgeArrowsEEnum.getELiterals().get(3), source, new String[] { "imagePath", "icons/full/decorator/outputClosedArrow.gif" });
        addAnnotation(edgeArrowsEEnum.getELiterals().get(4), source, new String[] { "imagePath", "icons/full/decorator/inputClosedArrow.gif" });
        addAnnotation(edgeArrowsEEnum.getELiterals().get(5), source, new String[] { "imagePath", "icons/full/decorator/outputFillClosedArrow.gif" });
        addAnnotation(edgeArrowsEEnum.getELiterals().get(6), source, new String[] { "imagePath", "icons/full/decorator/inputFillClosedArrow.gif" });
        addAnnotation(edgeArrowsEEnum.getELiterals().get(7), source, new String[] { "imagePath", "icons/full/decorator/diamond.gif" });
        addAnnotation(edgeArrowsEEnum.getELiterals().get(8), source, new String[] { "imagePath", "icons/full/decorator/fillDiamond.gif" });
        addAnnotation(edgeArrowsEEnum.getELiterals().get(9), source, new String[] { "imagePath", "icons/full/decorator/inputArrowWithDiamond.gif" });
        addAnnotation(edgeArrowsEEnum.getELiterals().get(10), source, new String[] { "imagePath", "icons/full/decorator/inputArrowWithFillDiamond.gif" });
    }

} // SiriusPackageImpl
