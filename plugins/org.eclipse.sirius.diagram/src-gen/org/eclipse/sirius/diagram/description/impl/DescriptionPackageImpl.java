/**
 * Copyright (c) 2007, 2018 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.description.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.AdditionalLayer;
import org.eclipse.sirius.diagram.description.BooleanLayoutOption;
import org.eclipse.sirius.diagram.description.CenteringStyle;
import org.eclipse.sirius.diagram.description.CompositeLayout;
import org.eclipse.sirius.diagram.description.ConditionalContainerStyleDescription;
import org.eclipse.sirius.diagram.description.ConditionalEdgeStyleDescription;
import org.eclipse.sirius.diagram.description.ConditionalNodeStyleDescription;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.ContainerMappingImport;
import org.eclipse.sirius.diagram.description.CustomLayoutConfiguration;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.DiagramExtensionDescription;
import org.eclipse.sirius.diagram.description.DiagramImportDescription;
import org.eclipse.sirius.diagram.description.DoubleLayoutOption;
import org.eclipse.sirius.diagram.description.DragAndDropTargetDescription;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.EdgeMappingImport;
import org.eclipse.sirius.diagram.description.EnumLayoutOption;
import org.eclipse.sirius.diagram.description.EnumLayoutValue;
import org.eclipse.sirius.diagram.description.EnumOption;
import org.eclipse.sirius.diagram.description.EnumSetLayoutOption;
import org.eclipse.sirius.diagram.description.FoldingStyle;
import org.eclipse.sirius.diagram.description.IEdgeMapping;
import org.eclipse.sirius.diagram.description.IntegerLayoutOption;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.Layout;
import org.eclipse.sirius.diagram.description.LayoutDirection;
import org.eclipse.sirius.diagram.description.LayoutOption;
import org.eclipse.sirius.diagram.description.LayoutOptionTarget;
import org.eclipse.sirius.diagram.description.MappingBasedDecoration;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.NodeMappingImport;
import org.eclipse.sirius.diagram.description.OrderedTreeLayout;
import org.eclipse.sirius.diagram.description.StringLayoutOption;
import org.eclipse.sirius.diagram.description.concern.ConcernPackage;
import org.eclipse.sirius.diagram.description.concern.impl.ConcernPackageImpl;
import org.eclipse.sirius.diagram.description.filter.FilterPackage;
import org.eclipse.sirius.diagram.description.filter.impl.FilterPackageImpl;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.diagram.description.style.impl.StylePackageImpl;
import org.eclipse.sirius.diagram.description.tool.ToolPackage;
import org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl;
import org.eclipse.sirius.diagram.impl.DiagramPackageImpl;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.validation.ValidationPackage;

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
    private EClass customLayoutConfigurationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass layoutOptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass booleanLayoutOptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass stringLayoutOptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass integerLayoutOptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass doubleLayoutOptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass enumLayoutOptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass enumSetLayoutOptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass enumOptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass enumLayoutValueEClass = null;

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
    private EClass dragAndDropTargetDescriptionEClass = null;

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
    private EEnum centeringStyleEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EEnum layoutOptionTargetEEnum = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
     * EPackage.Registry} by the package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also
     * performs initialization of the package, or returns the registered package, if one already exists. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#eNS_URI
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
        ViewpointPackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        Object registeredPackage = EPackage.Registry.INSTANCE.getEPackage(DiagramPackage.eNS_URI);
        DiagramPackageImpl theDiagramPackage = (DiagramPackageImpl) (registeredPackage instanceof DiagramPackageImpl ? registeredPackage : DiagramPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(StylePackage.eNS_URI);
        StylePackageImpl theStylePackage = (StylePackageImpl) (registeredPackage instanceof StylePackageImpl ? registeredPackage : StylePackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ToolPackage.eNS_URI);
        ToolPackageImpl theToolPackage = (ToolPackageImpl) (registeredPackage instanceof ToolPackageImpl ? registeredPackage : ToolPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(FilterPackage.eNS_URI);
        FilterPackageImpl theFilterPackage = (FilterPackageImpl) (registeredPackage instanceof FilterPackageImpl ? registeredPackage : FilterPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ConcernPackage.eNS_URI);
        ConcernPackageImpl theConcernPackage = (ConcernPackageImpl) (registeredPackage instanceof ConcernPackageImpl ? registeredPackage : ConcernPackage.eINSTANCE);

        // Create package meta-data objects
        theDescriptionPackage.createPackageContents();
        theDiagramPackage.createPackageContents();
        theStylePackage.createPackageContents();
        theToolPackage.createPackageContents();
        theFilterPackage.createPackageContents();
        theConcernPackage.createPackageContents();

        // Initialize created meta-data
        theDescriptionPackage.initializePackageContents();
        theDiagramPackage.initializePackageContents();
        theStylePackage.initializePackageContents();
        theToolPackage.initializePackageContents();
        theFilterPackage.initializePackageContents();
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
    @Override
    public EClass getDiagramDescription() {
        return diagramDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDiagramDescription_Filters() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDiagramDescription_ValidationSet() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDiagramDescription_Concerns() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDiagramDescription_DomainClass() {
        return (EAttribute) diagramDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDiagramDescription_PreconditionExpression() {
        return (EAttribute) diagramDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDiagramDescription_DefaultConcern() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDiagramDescription_RootExpression() {
        return (EAttribute) diagramDescriptionEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDiagramDescription_Init() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDiagramDescription_Layout() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDiagramDescription_DiagramInitialisation() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDiagramDescription_DefaultLayer() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDiagramDescription_AdditionalLayers() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDiagramDescription_NodeMappings() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDiagramDescription_EdgeMappings() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDiagramDescription_EdgeMappingImports() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(14);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDiagramDescription_ContainerMappings() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(15);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDiagramDescription_ReusedMappings() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(16);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDiagramDescription_ToolSection() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(17);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDiagramDescription_ReusedTools() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(18);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDiagramDescription_EnablePopupBars() {
        return (EAttribute) diagramDescriptionEClass.getEStructuralFeatures().get(19);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDiagramDescription_BackgroundColor() {
        return (EReference) diagramDescriptionEClass.getEStructuralFeatures().get(20);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDiagramImportDescription() {
        return diagramImportDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDiagramImportDescription_ImportedDiagram() {
        return (EReference) diagramImportDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDiagramExtensionDescription() {
        return diagramExtensionDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDiagramExtensionDescription_Layers() {
        return (EReference) diagramExtensionDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDiagramExtensionDescription_ValidationSet() {
        return (EReference) diagramExtensionDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDiagramExtensionDescription_Concerns() {
        return (EReference) diagramExtensionDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDiagramElementMapping() {
        return diagramElementMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDiagramElementMapping_PreconditionExpression() {
        return (EAttribute) diagramElementMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDiagramElementMapping_DeletionDescription() {
        return (EReference) diagramElementMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDiagramElementMapping_LabelDirectEdit() {
        return (EReference) diagramElementMappingEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDiagramElementMapping_SemanticCandidatesExpression() {
        return (EAttribute) diagramElementMappingEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDiagramElementMapping_CreateElements() {
        return (EAttribute) diagramElementMappingEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDiagramElementMapping_SemanticElements() {
        return (EAttribute) diagramElementMappingEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDiagramElementMapping_DoubleClickDescription() {
        return (EReference) diagramElementMappingEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDiagramElementMapping_SynchronizationLock() {
        return (EAttribute) diagramElementMappingEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getAbstractNodeMapping() {
        return abstractNodeMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractNodeMapping_DomainClass() {
        return (EAttribute) abstractNodeMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractNodeMapping_BorderedNodeMappings() {
        return (EReference) abstractNodeMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractNodeMapping_ReusedBorderedNodeMappings() {
        return (EReference) abstractNodeMappingEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getNodeMapping() {
        return nodeMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getNodeMapping_Style() {
        return (EReference) nodeMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getNodeMapping_ConditionnalStyles() {
        return (EReference) nodeMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getContainerMapping() {
        return containerMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getContainerMapping_SubNodeMappings() {
        return (EReference) containerMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getContainerMapping_ReusedNodeMappings() {
        return (EReference) containerMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getContainerMapping_SubContainerMappings() {
        return (EReference) containerMappingEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getContainerMapping_ReusedContainerMappings() {
        return (EReference) containerMappingEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getContainerMapping_Style() {
        return (EReference) containerMappingEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getContainerMapping_ConditionnalStyles() {
        return (EReference) containerMappingEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getContainerMapping_ChildrenPresentation() {
        return (EAttribute) containerMappingEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getNodeMappingImport() {
        return nodeMappingImportEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getNodeMappingImport_ImportedMapping() {
        return (EReference) nodeMappingImportEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getContainerMappingImport() {
        return containerMappingImportEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getContainerMappingImport_ImportedMapping() {
        return (EReference) containerMappingImportEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getEdgeMapping() {
        return edgeMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEdgeMapping_SourceMapping() {
        return (EReference) edgeMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEdgeMapping_TargetMapping() {
        return (EReference) edgeMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEdgeMapping_TargetFinderExpression() {
        return (EAttribute) edgeMappingEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEdgeMapping_SourceFinderExpression() {
        return (EAttribute) edgeMappingEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEdgeMapping_Style() {
        return (EReference) edgeMappingEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEdgeMapping_ConditionnalStyles() {
        return (EReference) edgeMappingEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEdgeMapping_TargetExpression() {
        return (EAttribute) edgeMappingEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEdgeMapping_DomainClass() {
        return (EAttribute) edgeMappingEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEdgeMapping_UseDomainElement() {
        return (EAttribute) edgeMappingEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEdgeMapping_Reconnections() {
        return (EReference) edgeMappingEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEdgeMapping_PathExpression() {
        return (EAttribute) edgeMappingEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEdgeMapping_PathNodeMapping() {
        return (EReference) edgeMappingEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getIEdgeMapping() {
        return iEdgeMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getEdgeMappingImport() {
        return edgeMappingImportEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEdgeMappingImport_ImportedMapping() {
        return (EReference) edgeMappingImportEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEdgeMappingImport_ConditionnalStyles() {
        return (EReference) edgeMappingImportEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEdgeMappingImport_InheritsAncestorFilters() {
        return (EAttribute) edgeMappingImportEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getConditionalNodeStyleDescription() {
        return conditionalNodeStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getConditionalNodeStyleDescription_Style() {
        return (EReference) conditionalNodeStyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getConditionalEdgeStyleDescription() {
        return conditionalEdgeStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getConditionalEdgeStyleDescription_Style() {
        return (EReference) conditionalEdgeStyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getConditionalContainerStyleDescription() {
        return conditionalContainerStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getConditionalContainerStyleDescription_Style() {
        return (EReference) conditionalContainerStyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getLayout() {
        return layoutEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getOrderedTreeLayout() {
        return orderedTreeLayoutEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getOrderedTreeLayout_ChildrenExpression() {
        return (EAttribute) orderedTreeLayoutEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getOrderedTreeLayout_NodeMapping() {
        return (EReference) orderedTreeLayoutEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getCompositeLayout() {
        return compositeLayoutEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getCompositeLayout_Padding() {
        return (EAttribute) compositeLayoutEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getCompositeLayout_Direction() {
        return (EAttribute) compositeLayoutEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getCustomLayoutConfiguration() {
        return customLayoutConfigurationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getCustomLayoutConfiguration_Id() {
        return (EAttribute) customLayoutConfigurationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getCustomLayoutConfiguration_Label() {
        return (EAttribute) customLayoutConfigurationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getCustomLayoutConfiguration_Description() {
        return (EAttribute) customLayoutConfigurationEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getCustomLayoutConfiguration_LayoutOptions() {
        return (EReference) customLayoutConfigurationEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getLayoutOption() {
        return layoutOptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getLayoutOption_Id() {
        return (EAttribute) layoutOptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getLayoutOption_Label() {
        return (EAttribute) layoutOptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getLayoutOption_Description() {
        return (EAttribute) layoutOptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getLayoutOption_Targets() {
        return (EAttribute) layoutOptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getBooleanLayoutOption() {
        return booleanLayoutOptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getBooleanLayoutOption_Value() {
        return (EAttribute) booleanLayoutOptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getStringLayoutOption() {
        return stringLayoutOptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getStringLayoutOption_Value() {
        return (EAttribute) stringLayoutOptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getIntegerLayoutOption() {
        return integerLayoutOptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getIntegerLayoutOption_Value() {
        return (EAttribute) integerLayoutOptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDoubleLayoutOption() {
        return doubleLayoutOptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDoubleLayoutOption_Value() {
        return (EAttribute) doubleLayoutOptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getEnumLayoutOption() {
        return enumLayoutOptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEnumLayoutOption_Value() {
        return (EReference) enumLayoutOptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getEnumSetLayoutOption() {
        return enumSetLayoutOptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEnumSetLayoutOption_Values() {
        return (EReference) enumSetLayoutOptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getEnumOption() {
        return enumOptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEnumOption_Choices() {
        return (EReference) enumOptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getEnumLayoutValue() {
        return enumLayoutValueEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEnumLayoutValue_Name() {
        return (EAttribute) enumLayoutValueEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEnumLayoutValue_Description() {
        return (EAttribute) enumLayoutValueEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getMappingBasedDecoration() {
        return mappingBasedDecorationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getMappingBasedDecoration_Mappings() {
        return (EReference) mappingBasedDecorationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getLayer() {
        return layerEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getLayer_NodeMappings() {
        return (EReference) layerEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getLayer_EdgeMappings() {
        return (EReference) layerEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getLayer_EdgeMappingImports() {
        return (EReference) layerEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getLayer_ContainerMappings() {
        return (EReference) layerEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getLayer_ReusedMappings() {
        return (EReference) layerEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getLayer_AllTools() {
        return (EReference) layerEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getLayer_ToolSections() {
        return (EReference) layerEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getLayer_ReusedTools() {
        return (EReference) layerEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getLayer_DecorationDescriptionsSet() {
        return (EReference) layerEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getLayer_Icon() {
        return (EAttribute) layerEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getLayer_Customization() {
        return (EReference) layerEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getAdditionalLayer() {
        return additionalLayerEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAdditionalLayer_ActiveByDefault() {
        return (EAttribute) additionalLayerEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAdditionalLayer_Optional() {
        return (EAttribute) additionalLayerEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDragAndDropTargetDescription() {
        return dragAndDropTargetDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDragAndDropTargetDescription_DropDescriptions() {
        return (EReference) dragAndDropTargetDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EEnum getFoldingStyle() {
        return foldingStyleEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EEnum getLayoutDirection() {
        return layoutDirectionEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EEnum getCenteringStyle() {
        return centeringStyleEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EEnum getLayoutOptionTarget() {
        return layoutOptionTargetEEnum;
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
        diagramDescriptionEClass = createEClass(DescriptionPackage.DIAGRAM_DESCRIPTION);
        createEReference(diagramDescriptionEClass, DescriptionPackage.DIAGRAM_DESCRIPTION__FILTERS);
        createEReference(diagramDescriptionEClass, DescriptionPackage.DIAGRAM_DESCRIPTION__VALIDATION_SET);
        createEReference(diagramDescriptionEClass, DescriptionPackage.DIAGRAM_DESCRIPTION__CONCERNS);
        createEAttribute(diagramDescriptionEClass, DescriptionPackage.DIAGRAM_DESCRIPTION__DOMAIN_CLASS);
        createEAttribute(diagramDescriptionEClass, DescriptionPackage.DIAGRAM_DESCRIPTION__PRECONDITION_EXPRESSION);
        createEReference(diagramDescriptionEClass, DescriptionPackage.DIAGRAM_DESCRIPTION__DEFAULT_CONCERN);
        createEAttribute(diagramDescriptionEClass, DescriptionPackage.DIAGRAM_DESCRIPTION__ROOT_EXPRESSION);
        createEReference(diagramDescriptionEClass, DescriptionPackage.DIAGRAM_DESCRIPTION__INIT);
        createEReference(diagramDescriptionEClass, DescriptionPackage.DIAGRAM_DESCRIPTION__LAYOUT);
        createEReference(diagramDescriptionEClass, DescriptionPackage.DIAGRAM_DESCRIPTION__DIAGRAM_INITIALISATION);
        createEReference(diagramDescriptionEClass, DescriptionPackage.DIAGRAM_DESCRIPTION__DEFAULT_LAYER);
        createEReference(diagramDescriptionEClass, DescriptionPackage.DIAGRAM_DESCRIPTION__ADDITIONAL_LAYERS);
        createEReference(diagramDescriptionEClass, DescriptionPackage.DIAGRAM_DESCRIPTION__NODE_MAPPINGS);
        createEReference(diagramDescriptionEClass, DescriptionPackage.DIAGRAM_DESCRIPTION__EDGE_MAPPINGS);
        createEReference(diagramDescriptionEClass, DescriptionPackage.DIAGRAM_DESCRIPTION__EDGE_MAPPING_IMPORTS);
        createEReference(diagramDescriptionEClass, DescriptionPackage.DIAGRAM_DESCRIPTION__CONTAINER_MAPPINGS);
        createEReference(diagramDescriptionEClass, DescriptionPackage.DIAGRAM_DESCRIPTION__REUSED_MAPPINGS);
        createEReference(diagramDescriptionEClass, DescriptionPackage.DIAGRAM_DESCRIPTION__TOOL_SECTION);
        createEReference(diagramDescriptionEClass, DescriptionPackage.DIAGRAM_DESCRIPTION__REUSED_TOOLS);
        createEAttribute(diagramDescriptionEClass, DescriptionPackage.DIAGRAM_DESCRIPTION__ENABLE_POPUP_BARS);
        createEReference(diagramDescriptionEClass, DescriptionPackage.DIAGRAM_DESCRIPTION__BACKGROUND_COLOR);

        diagramImportDescriptionEClass = createEClass(DescriptionPackage.DIAGRAM_IMPORT_DESCRIPTION);
        createEReference(diagramImportDescriptionEClass, DescriptionPackage.DIAGRAM_IMPORT_DESCRIPTION__IMPORTED_DIAGRAM);

        diagramExtensionDescriptionEClass = createEClass(DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION);
        createEReference(diagramExtensionDescriptionEClass, DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__LAYERS);
        createEReference(diagramExtensionDescriptionEClass, DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__VALIDATION_SET);
        createEReference(diagramExtensionDescriptionEClass, DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__CONCERNS);

        diagramElementMappingEClass = createEClass(DescriptionPackage.DIAGRAM_ELEMENT_MAPPING);
        createEAttribute(diagramElementMappingEClass, DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__PRECONDITION_EXPRESSION);
        createEReference(diagramElementMappingEClass, DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__DELETION_DESCRIPTION);
        createEReference(diagramElementMappingEClass, DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__LABEL_DIRECT_EDIT);
        createEAttribute(diagramElementMappingEClass, DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION);
        createEAttribute(diagramElementMappingEClass, DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__CREATE_ELEMENTS);
        createEAttribute(diagramElementMappingEClass, DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__SEMANTIC_ELEMENTS);
        createEReference(diagramElementMappingEClass, DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__DOUBLE_CLICK_DESCRIPTION);
        createEAttribute(diagramElementMappingEClass, DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__SYNCHRONIZATION_LOCK);

        abstractNodeMappingEClass = createEClass(DescriptionPackage.ABSTRACT_NODE_MAPPING);
        createEAttribute(abstractNodeMappingEClass, DescriptionPackage.ABSTRACT_NODE_MAPPING__DOMAIN_CLASS);
        createEReference(abstractNodeMappingEClass, DescriptionPackage.ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS);
        createEReference(abstractNodeMappingEClass, DescriptionPackage.ABSTRACT_NODE_MAPPING__REUSED_BORDERED_NODE_MAPPINGS);

        nodeMappingEClass = createEClass(DescriptionPackage.NODE_MAPPING);
        createEReference(nodeMappingEClass, DescriptionPackage.NODE_MAPPING__STYLE);
        createEReference(nodeMappingEClass, DescriptionPackage.NODE_MAPPING__CONDITIONNAL_STYLES);

        containerMappingEClass = createEClass(DescriptionPackage.CONTAINER_MAPPING);
        createEReference(containerMappingEClass, DescriptionPackage.CONTAINER_MAPPING__SUB_NODE_MAPPINGS);
        createEReference(containerMappingEClass, DescriptionPackage.CONTAINER_MAPPING__REUSED_NODE_MAPPINGS);
        createEReference(containerMappingEClass, DescriptionPackage.CONTAINER_MAPPING__SUB_CONTAINER_MAPPINGS);
        createEReference(containerMappingEClass, DescriptionPackage.CONTAINER_MAPPING__REUSED_CONTAINER_MAPPINGS);
        createEReference(containerMappingEClass, DescriptionPackage.CONTAINER_MAPPING__STYLE);
        createEReference(containerMappingEClass, DescriptionPackage.CONTAINER_MAPPING__CONDITIONNAL_STYLES);
        createEAttribute(containerMappingEClass, DescriptionPackage.CONTAINER_MAPPING__CHILDREN_PRESENTATION);

        nodeMappingImportEClass = createEClass(DescriptionPackage.NODE_MAPPING_IMPORT);
        createEReference(nodeMappingImportEClass, DescriptionPackage.NODE_MAPPING_IMPORT__IMPORTED_MAPPING);

        containerMappingImportEClass = createEClass(DescriptionPackage.CONTAINER_MAPPING_IMPORT);
        createEReference(containerMappingImportEClass, DescriptionPackage.CONTAINER_MAPPING_IMPORT__IMPORTED_MAPPING);

        edgeMappingEClass = createEClass(DescriptionPackage.EDGE_MAPPING);
        createEReference(edgeMappingEClass, DescriptionPackage.EDGE_MAPPING__SOURCE_MAPPING);
        createEReference(edgeMappingEClass, DescriptionPackage.EDGE_MAPPING__TARGET_MAPPING);
        createEAttribute(edgeMappingEClass, DescriptionPackage.EDGE_MAPPING__TARGET_FINDER_EXPRESSION);
        createEAttribute(edgeMappingEClass, DescriptionPackage.EDGE_MAPPING__SOURCE_FINDER_EXPRESSION);
        createEReference(edgeMappingEClass, DescriptionPackage.EDGE_MAPPING__STYLE);
        createEReference(edgeMappingEClass, DescriptionPackage.EDGE_MAPPING__CONDITIONNAL_STYLES);
        createEAttribute(edgeMappingEClass, DescriptionPackage.EDGE_MAPPING__TARGET_EXPRESSION);
        createEAttribute(edgeMappingEClass, DescriptionPackage.EDGE_MAPPING__DOMAIN_CLASS);
        createEAttribute(edgeMappingEClass, DescriptionPackage.EDGE_MAPPING__USE_DOMAIN_ELEMENT);
        createEReference(edgeMappingEClass, DescriptionPackage.EDGE_MAPPING__RECONNECTIONS);
        createEAttribute(edgeMappingEClass, DescriptionPackage.EDGE_MAPPING__PATH_EXPRESSION);
        createEReference(edgeMappingEClass, DescriptionPackage.EDGE_MAPPING__PATH_NODE_MAPPING);

        iEdgeMappingEClass = createEClass(DescriptionPackage.IEDGE_MAPPING);

        edgeMappingImportEClass = createEClass(DescriptionPackage.EDGE_MAPPING_IMPORT);
        createEReference(edgeMappingImportEClass, DescriptionPackage.EDGE_MAPPING_IMPORT__IMPORTED_MAPPING);
        createEReference(edgeMappingImportEClass, DescriptionPackage.EDGE_MAPPING_IMPORT__CONDITIONNAL_STYLES);
        createEAttribute(edgeMappingImportEClass, DescriptionPackage.EDGE_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS);

        conditionalNodeStyleDescriptionEClass = createEClass(DescriptionPackage.CONDITIONAL_NODE_STYLE_DESCRIPTION);
        createEReference(conditionalNodeStyleDescriptionEClass, DescriptionPackage.CONDITIONAL_NODE_STYLE_DESCRIPTION__STYLE);

        conditionalEdgeStyleDescriptionEClass = createEClass(DescriptionPackage.CONDITIONAL_EDGE_STYLE_DESCRIPTION);
        createEReference(conditionalEdgeStyleDescriptionEClass, DescriptionPackage.CONDITIONAL_EDGE_STYLE_DESCRIPTION__STYLE);

        conditionalContainerStyleDescriptionEClass = createEClass(DescriptionPackage.CONDITIONAL_CONTAINER_STYLE_DESCRIPTION);
        createEReference(conditionalContainerStyleDescriptionEClass, DescriptionPackage.CONDITIONAL_CONTAINER_STYLE_DESCRIPTION__STYLE);

        layoutEClass = createEClass(DescriptionPackage.LAYOUT);

        orderedTreeLayoutEClass = createEClass(DescriptionPackage.ORDERED_TREE_LAYOUT);
        createEAttribute(orderedTreeLayoutEClass, DescriptionPackage.ORDERED_TREE_LAYOUT__CHILDREN_EXPRESSION);
        createEReference(orderedTreeLayoutEClass, DescriptionPackage.ORDERED_TREE_LAYOUT__NODE_MAPPING);

        compositeLayoutEClass = createEClass(DescriptionPackage.COMPOSITE_LAYOUT);
        createEAttribute(compositeLayoutEClass, DescriptionPackage.COMPOSITE_LAYOUT__PADDING);
        createEAttribute(compositeLayoutEClass, DescriptionPackage.COMPOSITE_LAYOUT__DIRECTION);

        customLayoutConfigurationEClass = createEClass(DescriptionPackage.CUSTOM_LAYOUT_CONFIGURATION);
        createEAttribute(customLayoutConfigurationEClass, DescriptionPackage.CUSTOM_LAYOUT_CONFIGURATION__ID);
        createEAttribute(customLayoutConfigurationEClass, DescriptionPackage.CUSTOM_LAYOUT_CONFIGURATION__LABEL);
        createEAttribute(customLayoutConfigurationEClass, DescriptionPackage.CUSTOM_LAYOUT_CONFIGURATION__DESCRIPTION);
        createEReference(customLayoutConfigurationEClass, DescriptionPackage.CUSTOM_LAYOUT_CONFIGURATION__LAYOUT_OPTIONS);

        layoutOptionEClass = createEClass(DescriptionPackage.LAYOUT_OPTION);
        createEAttribute(layoutOptionEClass, DescriptionPackage.LAYOUT_OPTION__ID);
        createEAttribute(layoutOptionEClass, DescriptionPackage.LAYOUT_OPTION__LABEL);
        createEAttribute(layoutOptionEClass, DescriptionPackage.LAYOUT_OPTION__DESCRIPTION);
        createEAttribute(layoutOptionEClass, DescriptionPackage.LAYOUT_OPTION__TARGETS);

        booleanLayoutOptionEClass = createEClass(DescriptionPackage.BOOLEAN_LAYOUT_OPTION);
        createEAttribute(booleanLayoutOptionEClass, DescriptionPackage.BOOLEAN_LAYOUT_OPTION__VALUE);

        stringLayoutOptionEClass = createEClass(DescriptionPackage.STRING_LAYOUT_OPTION);
        createEAttribute(stringLayoutOptionEClass, DescriptionPackage.STRING_LAYOUT_OPTION__VALUE);

        integerLayoutOptionEClass = createEClass(DescriptionPackage.INTEGER_LAYOUT_OPTION);
        createEAttribute(integerLayoutOptionEClass, DescriptionPackage.INTEGER_LAYOUT_OPTION__VALUE);

        doubleLayoutOptionEClass = createEClass(DescriptionPackage.DOUBLE_LAYOUT_OPTION);
        createEAttribute(doubleLayoutOptionEClass, DescriptionPackage.DOUBLE_LAYOUT_OPTION__VALUE);

        enumLayoutOptionEClass = createEClass(DescriptionPackage.ENUM_LAYOUT_OPTION);
        createEReference(enumLayoutOptionEClass, DescriptionPackage.ENUM_LAYOUT_OPTION__VALUE);

        enumSetLayoutOptionEClass = createEClass(DescriptionPackage.ENUM_SET_LAYOUT_OPTION);
        createEReference(enumSetLayoutOptionEClass, DescriptionPackage.ENUM_SET_LAYOUT_OPTION__VALUES);

        enumOptionEClass = createEClass(DescriptionPackage.ENUM_OPTION);
        createEReference(enumOptionEClass, DescriptionPackage.ENUM_OPTION__CHOICES);

        enumLayoutValueEClass = createEClass(DescriptionPackage.ENUM_LAYOUT_VALUE);
        createEAttribute(enumLayoutValueEClass, DescriptionPackage.ENUM_LAYOUT_VALUE__NAME);
        createEAttribute(enumLayoutValueEClass, DescriptionPackage.ENUM_LAYOUT_VALUE__DESCRIPTION);

        mappingBasedDecorationEClass = createEClass(DescriptionPackage.MAPPING_BASED_DECORATION);
        createEReference(mappingBasedDecorationEClass, DescriptionPackage.MAPPING_BASED_DECORATION__MAPPINGS);

        layerEClass = createEClass(DescriptionPackage.LAYER);
        createEReference(layerEClass, DescriptionPackage.LAYER__NODE_MAPPINGS);
        createEReference(layerEClass, DescriptionPackage.LAYER__EDGE_MAPPINGS);
        createEReference(layerEClass, DescriptionPackage.LAYER__EDGE_MAPPING_IMPORTS);
        createEReference(layerEClass, DescriptionPackage.LAYER__CONTAINER_MAPPINGS);
        createEReference(layerEClass, DescriptionPackage.LAYER__REUSED_MAPPINGS);
        createEReference(layerEClass, DescriptionPackage.LAYER__ALL_TOOLS);
        createEReference(layerEClass, DescriptionPackage.LAYER__TOOL_SECTIONS);
        createEReference(layerEClass, DescriptionPackage.LAYER__REUSED_TOOLS);
        createEReference(layerEClass, DescriptionPackage.LAYER__DECORATION_DESCRIPTIONS_SET);
        createEAttribute(layerEClass, DescriptionPackage.LAYER__ICON);
        createEReference(layerEClass, DescriptionPackage.LAYER__CUSTOMIZATION);

        additionalLayerEClass = createEClass(DescriptionPackage.ADDITIONAL_LAYER);
        createEAttribute(additionalLayerEClass, DescriptionPackage.ADDITIONAL_LAYER__ACTIVE_BY_DEFAULT);
        createEAttribute(additionalLayerEClass, DescriptionPackage.ADDITIONAL_LAYER__OPTIONAL);

        dragAndDropTargetDescriptionEClass = createEClass(DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION);
        createEReference(dragAndDropTargetDescriptionEClass, DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION__DROP_DESCRIPTIONS);

        // Create enums
        foldingStyleEEnum = createEEnum(DescriptionPackage.FOLDING_STYLE);
        layoutDirectionEEnum = createEEnum(DescriptionPackage.LAYOUT_DIRECTION);
        centeringStyleEEnum = createEEnum(DescriptionPackage.CENTERING_STYLE);
        layoutOptionTargetEEnum = createEEnum(DescriptionPackage.LAYOUT_OPTION_TARGET);
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
        FilterPackage theFilterPackage = (FilterPackage) EPackage.Registry.INSTANCE.getEPackage(FilterPackage.eNS_URI);
        ConcernPackage theConcernPackage = (ConcernPackage) EPackage.Registry.INSTANCE.getEPackage(ConcernPackage.eNS_URI);
        org.eclipse.sirius.viewpoint.description.DescriptionPackage theDescriptionPackage_1 = (org.eclipse.sirius.viewpoint.description.DescriptionPackage) EPackage.Registry.INSTANCE
                .getEPackage(org.eclipse.sirius.viewpoint.description.DescriptionPackage.eNS_URI);
        ValidationPackage theValidationPackage = (ValidationPackage) EPackage.Registry.INSTANCE.getEPackage(ValidationPackage.eNS_URI);
        org.eclipse.sirius.viewpoint.description.tool.ToolPackage theToolPackage_1 = (org.eclipse.sirius.viewpoint.description.tool.ToolPackage) EPackage.Registry.INSTANCE
                .getEPackage(org.eclipse.sirius.viewpoint.description.tool.ToolPackage.eNS_URI);
        EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
        DiagramPackage theDiagramPackage = (DiagramPackage) EPackage.Registry.INSTANCE.getEPackage(DiagramPackage.eNS_URI);

        // Add subpackages
        getESubpackages().add(theStylePackage);
        getESubpackages().add(theToolPackage);
        getESubpackages().add(theFilterPackage);
        getESubpackages().add(theConcernPackage);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        diagramDescriptionEClass.getESuperTypes().add(this.getDragAndDropTargetDescription());
        diagramDescriptionEClass.getESuperTypes().add(theDescriptionPackage_1.getRepresentationDescription());
        diagramDescriptionEClass.getESuperTypes().add(theDescriptionPackage_1.getPasteTargetDescription());
        diagramImportDescriptionEClass.getESuperTypes().add(theDescriptionPackage_1.getRepresentationImportDescription());
        diagramImportDescriptionEClass.getESuperTypes().add(this.getDiagramDescription());
        diagramExtensionDescriptionEClass.getESuperTypes().add(theDescriptionPackage_1.getRepresentationExtensionDescription());
        diagramElementMappingEClass.getESuperTypes().add(theDescriptionPackage_1.getRepresentationElementMapping());
        diagramElementMappingEClass.getESuperTypes().add(theDescriptionPackage_1.getPasteTargetDescription());
        abstractNodeMappingEClass.getESuperTypes().add(this.getDiagramElementMapping());
        abstractNodeMappingEClass.getESuperTypes().add(theDescriptionPackage_1.getDocumentedElement());
        nodeMappingEClass.getESuperTypes().add(this.getAbstractNodeMapping());
        nodeMappingEClass.getESuperTypes().add(this.getDragAndDropTargetDescription());
        containerMappingEClass.getESuperTypes().add(this.getAbstractNodeMapping());
        containerMappingEClass.getESuperTypes().add(this.getDragAndDropTargetDescription());
        nodeMappingImportEClass.getESuperTypes().add(this.getNodeMapping());
        nodeMappingImportEClass.getESuperTypes().add(theDescriptionPackage_1.getAbstractMappingImport());
        containerMappingImportEClass.getESuperTypes().add(this.getContainerMapping());
        containerMappingImportEClass.getESuperTypes().add(theDescriptionPackage_1.getAbstractMappingImport());
        edgeMappingEClass.getESuperTypes().add(this.getDiagramElementMapping());
        edgeMappingEClass.getESuperTypes().add(theDescriptionPackage_1.getDocumentedElement());
        edgeMappingEClass.getESuperTypes().add(this.getIEdgeMapping());
        edgeMappingImportEClass.getESuperTypes().add(theDescriptionPackage_1.getDocumentedElement());
        edgeMappingImportEClass.getESuperTypes().add(this.getIEdgeMapping());
        edgeMappingImportEClass.getESuperTypes().add(theDescriptionPackage_1.getIdentifiedElement());
        conditionalNodeStyleDescriptionEClass.getESuperTypes().add(theDescriptionPackage_1.getConditionalStyleDescription());
        conditionalEdgeStyleDescriptionEClass.getESuperTypes().add(theDescriptionPackage_1.getConditionalStyleDescription());
        conditionalContainerStyleDescriptionEClass.getESuperTypes().add(theDescriptionPackage_1.getConditionalStyleDescription());
        layoutEClass.getESuperTypes().add(theDescriptionPackage_1.getDocumentedElement());
        orderedTreeLayoutEClass.getESuperTypes().add(this.getLayout());
        compositeLayoutEClass.getESuperTypes().add(this.getLayout());
        customLayoutConfigurationEClass.getESuperTypes().add(this.getLayout());
        booleanLayoutOptionEClass.getESuperTypes().add(this.getLayoutOption());
        stringLayoutOptionEClass.getESuperTypes().add(this.getLayoutOption());
        integerLayoutOptionEClass.getESuperTypes().add(this.getLayoutOption());
        doubleLayoutOptionEClass.getESuperTypes().add(this.getLayoutOption());
        enumLayoutOptionEClass.getESuperTypes().add(this.getEnumOption());
        enumSetLayoutOptionEClass.getESuperTypes().add(this.getEnumOption());
        enumOptionEClass.getESuperTypes().add(this.getLayoutOption());
        mappingBasedDecorationEClass.getESuperTypes().add(theDescriptionPackage_1.getDecorationDescription());
        layerEClass.getESuperTypes().add(theDescriptionPackage_1.getDocumentedElement());
        layerEClass.getESuperTypes().add(theDescriptionPackage_1.getEndUserDocumentedElement());
        layerEClass.getESuperTypes().add(theDescriptionPackage_1.getIdentifiedElement());
        additionalLayerEClass.getESuperTypes().add(this.getLayer());

        // Initialize classes and features; add operations and parameters
        initEClass(diagramDescriptionEClass, DiagramDescription.class, "DiagramDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getDiagramDescription_Filters(), theFilterPackage.getFilterDescription(), null, "filters", null, 0, -1, DiagramDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        getDiagramDescription_Filters().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(getDiagramDescription_ValidationSet(), theValidationPackage.getValidationSet(), null, "validationSet", null, 0, 1, DiagramDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDiagramDescription_Concerns(), theConcernPackage.getConcernSet(), null, "concerns", null, 0, 1, DiagramDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDiagramDescription_DomainClass(), theDescriptionPackage_1.getTypeName(), "domainClass", null, 1, 1, DiagramDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDiagramDescription_PreconditionExpression(), theDescriptionPackage_1.getInterpretedExpression(), "preconditionExpression", null, 0, 1, DiagramDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getDiagramDescription_DefaultConcern(), theConcernPackage.getConcernDescription(), null, "defaultConcern", null, 0, 1, DiagramDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDiagramDescription_RootExpression(), theDescriptionPackage_1.getInterpretedExpression(), "rootExpression", null, 0, 1, DiagramDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDiagramDescription_Init(), theToolPackage_1.getRepresentationCreationDescription(), null, "init", null, 0, 1, DiagramDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDiagramDescription_Layout(), this.getLayout(), null, "layout", null, 0, 1, DiagramDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getDiagramDescription_DiagramInitialisation(), theToolPackage_1.getInitialOperation(), null, "diagramInitialisation", null, 0, 1, DiagramDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDiagramDescription_DefaultLayer(), this.getLayer(), null, "defaultLayer", null, 0, 1, DiagramDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getDiagramDescription_AdditionalLayers(), this.getAdditionalLayer(), null, "additionalLayers", null, 0, -1, DiagramDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        getDiagramDescription_AdditionalLayers().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(getDiagramDescription_NodeMappings(), this.getNodeMapping(), null, "nodeMappings", null, 0, -1, DiagramDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        getDiagramDescription_NodeMappings().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(getDiagramDescription_EdgeMappings(), this.getEdgeMapping(), null, "edgeMappings", null, 0, -1, DiagramDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        getDiagramDescription_EdgeMappings().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(getDiagramDescription_EdgeMappingImports(), this.getEdgeMappingImport(), null, "edgeMappingImports", null, 0, -1, DiagramDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        getDiagramDescription_EdgeMappingImports().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(getDiagramDescription_ContainerMappings(), this.getContainerMapping(), null, "containerMappings", null, 0, -1, DiagramDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        getDiagramDescription_ContainerMappings().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(getDiagramDescription_ReusedMappings(), this.getDiagramElementMapping(), null, "reusedMappings", null, 0, -1, DiagramDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDiagramDescription_ToolSection(), theToolPackage.getToolSection(), null, "toolSection", null, 0, 1, DiagramDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        getDiagramDescription_ToolSection().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(getDiagramDescription_ReusedTools(), theToolPackage_1.getAbstractToolDescription(), null, "reusedTools", null, 0, -1, DiagramDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDiagramDescription_EnablePopupBars(), theEcorePackage.getEBoolean(), "enablePopupBars", null, 0, 1, DiagramDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDiagramDescription_BackgroundColor(), theDescriptionPackage_1.getColorDescription(), null, "backgroundColor", null, 0, 1, DiagramDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        addEOperation(diagramDescriptionEClass, theDiagramPackage.getDSemanticDiagram(), "createDiagram", 0, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(diagramImportDescriptionEClass, DiagramImportDescription.class, "DiagramImportDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDiagramImportDescription_ImportedDiagram(), this.getDiagramDescription(), null, "importedDiagram", null, 0, 1, DiagramImportDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(diagramExtensionDescriptionEClass, DiagramExtensionDescription.class, "DiagramExtensionDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDiagramExtensionDescription_Layers(), this.getAdditionalLayer(), null, "layers", null, 0, -1, DiagramExtensionDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        getDiagramExtensionDescription_Layers().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(getDiagramExtensionDescription_ValidationSet(), theValidationPackage.getValidationSet(), null, "validationSet", null, 0, 1, DiagramExtensionDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDiagramExtensionDescription_Concerns(), theConcernPackage.getConcernSet(), null, "concerns", null, 0, 1, DiagramExtensionDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(diagramElementMappingEClass, DiagramElementMapping.class, "DiagramElementMapping", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getDiagramElementMapping_PreconditionExpression(), theDescriptionPackage_1.getInterpretedExpression(), "preconditionExpression", "", 0, 1, DiagramElementMapping.class, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getDiagramElementMapping_DeletionDescription(), theToolPackage.getDeleteElementDescription(), null, "deletionDescription", null, 0, 1, DiagramElementMapping.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDiagramElementMapping_LabelDirectEdit(), theToolPackage.getDirectEditLabel(), null, "labelDirectEdit", null, 0, 1, DiagramElementMapping.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDiagramElementMapping_SemanticCandidatesExpression(), theDescriptionPackage_1.getInterpretedExpression(), "semanticCandidatesExpression", null, 0, 1, //$NON-NLS-1$
                DiagramElementMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDiagramElementMapping_CreateElements(), theEcorePackage.getEBoolean(), "createElements", "true", 1, 1, DiagramElementMapping.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDiagramElementMapping_SemanticElements(), theDescriptionPackage_1.getInterpretedExpression(), "semanticElements", null, 0, 1, DiagramElementMapping.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getDiagramElementMapping_DoubleClickDescription(), theToolPackage.getDoubleClickDescription(), theToolPackage.getDoubleClickDescription_Mappings(), "doubleClickDescription", //$NON-NLS-1$
                null, 0, 1, DiagramElementMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES,
                !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDiagramElementMapping_SynchronizationLock(), theEcorePackage.getEBoolean(), "synchronizationLock", "false", 0, 1, DiagramElementMapping.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(abstractNodeMappingEClass, AbstractNodeMapping.class, "AbstractNodeMapping", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getAbstractNodeMapping_DomainClass(), theDescriptionPackage_1.getTypeName(), "domainClass", null, 1, 1, AbstractNodeMapping.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractNodeMapping_BorderedNodeMappings(), this.getNodeMapping(), null, "borderedNodeMappings", null, 0, -1, AbstractNodeMapping.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        getAbstractNodeMapping_BorderedNodeMappings().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(getAbstractNodeMapping_ReusedBorderedNodeMappings(), this.getNodeMapping(), null, "reusedBorderedNodeMappings", null, 0, -1, AbstractNodeMapping.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        EOperation op = addEOperation(abstractNodeMappingEClass, theDiagramPackage.getDDiagramElement(), "findDNodeFromEObject", 0, -1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        addEParameter(op, theEcorePackage.getEObject(), "eObject", 0, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(nodeMappingEClass, NodeMapping.class, "NodeMapping", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getNodeMapping_Style(), theStylePackage.getNodeStyleDescription(), null, "style", null, 0, 1, NodeMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getNodeMapping_ConditionnalStyles(), this.getConditionalNodeStyleDescription(), null, "conditionnalStyles", null, 0, -1, NodeMapping.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(containerMappingEClass, ContainerMapping.class, "ContainerMapping", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getContainerMapping_SubNodeMappings(), this.getNodeMapping(), null, "subNodeMappings", null, 0, -1, ContainerMapping.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        getContainerMapping_SubNodeMappings().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(getContainerMapping_ReusedNodeMappings(), this.getNodeMapping(), null, "reusedNodeMappings", null, 0, -1, ContainerMapping.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getContainerMapping_SubContainerMappings(), this.getContainerMapping(), null, "subContainerMappings", null, 0, -1, ContainerMapping.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        getContainerMapping_SubContainerMappings().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(getContainerMapping_ReusedContainerMappings(), this.getContainerMapping(), null, "reusedContainerMappings", null, 0, -1, ContainerMapping.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getContainerMapping_Style(), theStylePackage.getContainerStyleDescription(), null, "style", null, 0, 1, ContainerMapping.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getContainerMapping_ConditionnalStyles(), this.getConditionalContainerStyleDescription(), null, "conditionnalStyles", null, 0, -1, ContainerMapping.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getContainerMapping_ChildrenPresentation(), theDiagramPackage.getContainerLayout(), "childrenPresentation", "FreeForm", 1, 1, ContainerMapping.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(nodeMappingImportEClass, NodeMappingImport.class, "NodeMappingImport", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getNodeMappingImport_ImportedMapping(), this.getNodeMapping(), null, "importedMapping", null, 1, 1, NodeMappingImport.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(containerMappingImportEClass, ContainerMappingImport.class, "ContainerMappingImport", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getContainerMappingImport_ImportedMapping(), this.getContainerMapping(), null, "importedMapping", null, 1, 1, ContainerMappingImport.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(edgeMappingEClass, EdgeMapping.class, "EdgeMapping", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getEdgeMapping_SourceMapping(), this.getDiagramElementMapping(), null, "sourceMapping", null, 1, -1, EdgeMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getEdgeMapping_TargetMapping(), this.getDiagramElementMapping(), null, "targetMapping", null, 1, -1, EdgeMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getEdgeMapping_TargetFinderExpression(), theDescriptionPackage_1.getInterpretedExpression(), "targetFinderExpression", "", 1, 1, EdgeMapping.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getEdgeMapping_SourceFinderExpression(), theDescriptionPackage_1.getInterpretedExpression(), "sourceFinderExpression", null, 0, 1, EdgeMapping.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getEdgeMapping_Style(), theStylePackage.getEdgeStyleDescription(), null, "style", null, 0, 1, EdgeMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getEdgeMapping_ConditionnalStyles(), this.getConditionalEdgeStyleDescription(), null, "conditionnalStyles", null, 0, -1, EdgeMapping.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getEdgeMapping_TargetExpression(), theDescriptionPackage_1.getInterpretedExpression(), "targetExpression", null, 0, 1, EdgeMapping.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getEdgeMapping_DomainClass(), theDescriptionPackage_1.getTypeName(), "domainClass", null, 0, 1, EdgeMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getEdgeMapping_UseDomainElement(), theEcorePackage.getEBoolean(), "useDomainElement", "false", 0, 1, EdgeMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getEdgeMapping_Reconnections(), theToolPackage.getReconnectEdgeDescription(), null, "reconnections", null, 0, -1, EdgeMapping.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getEdgeMapping_PathExpression(), theDescriptionPackage_1.getInterpretedExpression(), "pathExpression", null, 0, 1, EdgeMapping.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getEdgeMapping_PathNodeMapping(), this.getAbstractNodeMapping(), null, "pathNodeMapping", null, 0, -1, EdgeMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(iEdgeMappingEClass, IEdgeMapping.class, "IEdgeMapping", EPackageImpl.IS_ABSTRACT, EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(edgeMappingImportEClass, EdgeMappingImport.class, "EdgeMappingImport", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getEdgeMappingImport_ImportedMapping(), this.getIEdgeMapping(), null, "importedMapping", null, 1, 1, EdgeMappingImport.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getEdgeMappingImport_ConditionnalStyles(), this.getConditionalEdgeStyleDescription(), null, "conditionnalStyles", null, 0, -1, EdgeMappingImport.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getEdgeMappingImport_InheritsAncestorFilters(), theEcorePackage.getEBoolean(), "inheritsAncestorFilters", "true", 0, 1, EdgeMappingImport.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(conditionalNodeStyleDescriptionEClass, ConditionalNodeStyleDescription.class, "ConditionalNodeStyleDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getConditionalNodeStyleDescription_Style(), theStylePackage.getNodeStyleDescription(), null, "style", null, 0, 1, ConditionalNodeStyleDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(conditionalEdgeStyleDescriptionEClass, ConditionalEdgeStyleDescription.class, "ConditionalEdgeStyleDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getConditionalEdgeStyleDescription_Style(), theStylePackage.getEdgeStyleDescription(), null, "style", null, 0, 1, ConditionalEdgeStyleDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(conditionalContainerStyleDescriptionEClass, ConditionalContainerStyleDescription.class, "ConditionalContainerStyleDescription", !EPackageImpl.IS_ABSTRACT, //$NON-NLS-1$
                !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getConditionalContainerStyleDescription_Style(), theStylePackage.getContainerStyleDescription(), null, "style", null, 0, 1, ConditionalContainerStyleDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(layoutEClass, Layout.class, "Layout", EPackageImpl.IS_ABSTRACT, EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(orderedTreeLayoutEClass, OrderedTreeLayout.class, "OrderedTreeLayout", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getOrderedTreeLayout_ChildrenExpression(), theDescriptionPackage_1.getInterpretedExpression(), "childrenExpression", null, 1, 1, OrderedTreeLayout.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getOrderedTreeLayout_NodeMapping(), this.getAbstractNodeMapping(), null, "nodeMapping", null, 1, -1, OrderedTreeLayout.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(compositeLayoutEClass, CompositeLayout.class, "CompositeLayout", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getCompositeLayout_Padding(), theEcorePackage.getEInt(), "padding", "30", 1, 1, CompositeLayout.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getCompositeLayout_Direction(), this.getLayoutDirection(), "direction", "topToBottom", 1, 1, CompositeLayout.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(customLayoutConfigurationEClass, CustomLayoutConfiguration.class, "CustomLayoutConfiguration", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getCustomLayoutConfiguration_Id(), theEcorePackage.getEString(), "id", null, 0, 1, CustomLayoutConfiguration.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getCustomLayoutConfiguration_Label(), theEcorePackage.getEString(), "label", null, 0, 1, CustomLayoutConfiguration.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getCustomLayoutConfiguration_Description(), theEcorePackage.getEString(), "description", null, 0, 1, CustomLayoutConfiguration.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getCustomLayoutConfiguration_LayoutOptions(), this.getLayoutOption(), null, "layoutOptions", null, 0, -1, CustomLayoutConfiguration.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(layoutOptionEClass, LayoutOption.class, "LayoutOption", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getLayoutOption_Id(), ecorePackage.getEString(), "id", null, 0, 1, LayoutOption.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getLayoutOption_Label(), ecorePackage.getEString(), "label", null, 0, 1, LayoutOption.class, EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getLayoutOption_Description(), theEcorePackage.getEString(), "description", null, 0, 1, LayoutOption.class, EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getLayoutOption_Targets(), this.getLayoutOptionTarget(), "targets", null, 0, -1, LayoutOption.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(booleanLayoutOptionEClass, BooleanLayoutOption.class, "BooleanLayoutOption", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getBooleanLayoutOption_Value(), theEcorePackage.getEBoolean(), "value", null, 0, 1, BooleanLayoutOption.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(stringLayoutOptionEClass, StringLayoutOption.class, "StringLayoutOption", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getStringLayoutOption_Value(), theEcorePackage.getEString(), "value", null, 0, 1, StringLayoutOption.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(integerLayoutOptionEClass, IntegerLayoutOption.class, "IntegerLayoutOption", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getIntegerLayoutOption_Value(), ecorePackage.getEInt(), "value", null, 0, 1, IntegerLayoutOption.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(doubleLayoutOptionEClass, DoubleLayoutOption.class, "DoubleLayoutOption", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getDoubleLayoutOption_Value(), theEcorePackage.getEDouble(), "value", null, 0, 1, DoubleLayoutOption.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(enumLayoutOptionEClass, EnumLayoutOption.class, "EnumLayoutOption", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getEnumLayoutOption_Value(), this.getEnumLayoutValue(), null, "value", null, 0, 1, EnumLayoutOption.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(enumSetLayoutOptionEClass, EnumSetLayoutOption.class, "EnumSetLayoutOption", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getEnumSetLayoutOption_Values(), this.getEnumLayoutValue(), null, "values", null, 0, -1, EnumSetLayoutOption.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(enumOptionEClass, EnumOption.class, "EnumOption", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getEnumOption_Choices(), this.getEnumLayoutValue(), null, "choices", null, 0, -1, EnumOption.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(enumLayoutValueEClass, EnumLayoutValue.class, "EnumLayoutValue", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getEnumLayoutValue_Name(), ecorePackage.getEString(), "name", null, 0, 1, EnumLayoutValue.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getEnumLayoutValue_Description(), theEcorePackage.getEString(), "description", null, 0, 1, EnumLayoutValue.class, EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(mappingBasedDecorationEClass, MappingBasedDecoration.class, "MappingBasedDecoration", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getMappingBasedDecoration_Mappings(), this.getDiagramElementMapping(), null, "mappings", null, 1, -1, MappingBasedDecoration.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(layerEClass, Layer.class, "Layer", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getLayer_NodeMappings(), this.getNodeMapping(), null, "nodeMappings", null, 0, -1, Layer.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        getLayer_NodeMappings().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(getLayer_EdgeMappings(), this.getEdgeMapping(), null, "edgeMappings", null, 0, -1, Layer.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        getLayer_EdgeMappings().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(getLayer_EdgeMappingImports(), this.getEdgeMappingImport(), null, "edgeMappingImports", null, 0, -1, Layer.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        getLayer_EdgeMappingImports().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(getLayer_ContainerMappings(), this.getContainerMapping(), null, "containerMappings", null, 0, -1, Layer.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        getLayer_ContainerMappings().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(getLayer_ReusedMappings(), this.getDiagramElementMapping(), null, "reusedMappings", null, 0, -1, Layer.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getLayer_AllTools(), theToolPackage_1.getAbstractToolDescription(), null, "allTools", null, 0, -1, Layer.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                !EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getLayer_ToolSections(), theToolPackage.getToolSection(), null, "toolSections", null, 0, -1, Layer.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getLayer_ReusedTools(), theToolPackage_1.getAbstractToolDescription(), null, "reusedTools", null, 0, -1, Layer.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getLayer_DecorationDescriptionsSet(), theDescriptionPackage_1.getDecorationDescriptionsSet(), null, "decorationDescriptionsSet", null, 0, 1, Layer.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getLayer_Icon(), theDescriptionPackage_1.getImagePath(), "icon", null, 0, 1, Layer.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getLayer_Customization(), theDescriptionPackage_1.getCustomization(), null, "customization", null, 0, 1, Layer.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(additionalLayerEClass, AdditionalLayer.class, "AdditionalLayer", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getAdditionalLayer_ActiveByDefault(), theEcorePackage.getEBoolean(), "activeByDefault", null, 0, 1, AdditionalLayer.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getAdditionalLayer_Optional(), theEcorePackage.getEBoolean(), "optional", "true", 0, 1, AdditionalLayer.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(dragAndDropTargetDescriptionEClass, DragAndDropTargetDescription.class, "DragAndDropTargetDescription", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDragAndDropTargetDescription_DropDescriptions(), theToolPackage.getContainerDropDescription(), null, "dropDescriptions", null, 0, -1, DragAndDropTargetDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(foldingStyleEEnum, FoldingStyle.class, "FoldingStyle"); //$NON-NLS-1$
        addEEnumLiteral(foldingStyleEEnum, FoldingStyle.NONE_LITERAL);
        addEEnumLiteral(foldingStyleEEnum, FoldingStyle.SOURCE_LITERAL);
        addEEnumLiteral(foldingStyleEEnum, FoldingStyle.TARGET_LITERAL);

        initEEnum(layoutDirectionEEnum, LayoutDirection.class, "LayoutDirection"); //$NON-NLS-1$
        addEEnumLiteral(layoutDirectionEEnum, LayoutDirection.TOP_TO_BOTTOM);
        addEEnumLiteral(layoutDirectionEEnum, LayoutDirection.LEFT_TO_RIGHT);
        addEEnumLiteral(layoutDirectionEEnum, LayoutDirection.BOTTOM_TO_TOP);

        initEEnum(centeringStyleEEnum, CenteringStyle.class, "CenteringStyle"); //$NON-NLS-1$
        addEEnumLiteral(centeringStyleEEnum, CenteringStyle.NONE);
        addEEnumLiteral(centeringStyleEEnum, CenteringStyle.BOTH);
        addEEnumLiteral(centeringStyleEEnum, CenteringStyle.SOURCE);
        addEEnumLiteral(centeringStyleEEnum, CenteringStyle.TARGET);

        initEEnum(layoutOptionTargetEEnum, LayoutOptionTarget.class, "LayoutOptionTarget"); //$NON-NLS-1$
        addEEnumLiteral(layoutOptionTargetEEnum, LayoutOptionTarget.PARENT);
        addEEnumLiteral(layoutOptionTargetEEnum, LayoutOptionTarget.NODE);
        addEEnumLiteral(layoutOptionTargetEEnum, LayoutOptionTarget.EDGE);
        addEEnumLiteral(layoutOptionTargetEEnum, LayoutOptionTarget.PORTS);
        addEEnumLiteral(layoutOptionTargetEEnum, LayoutOptionTarget.LABEL);

        // Create annotations
        // http://www.eclipse.org/sirius/interpreted/expression/returnType
        createReturnTypeAnnotations();
        // http://www.eclipse.org/sirius/interpreted/expression/variables
        createVariablesAnnotations();
        // TagValues
        createTagValuesAnnotations();
    }

    /**
     * Initializes the annotations for <b>http://www.eclipse.org/sirius/interpreted/expression/returnType</b>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void createReturnTypeAnnotations() {
        String source = "http://www.eclipse.org/sirius/interpreted/expression/returnType"; //$NON-NLS-1$
        addAnnotation(getDiagramDescription_PreconditionExpression(), source, new String[] { "returnType", "a boolean." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDiagramDescription_RootExpression(), source, new String[] { "returnType", "an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDiagramElementMapping_PreconditionExpression(), source, new String[] { "returnType", "a boolean." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDiagramElementMapping_SemanticCandidatesExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDiagramElementMapping_SemanticElements(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getEdgeMapping_TargetFinderExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getEdgeMapping_SourceFinderExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getEdgeMapping_TargetExpression(), source, new String[] { "returnType", "an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getEdgeMapping_PathExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getOrderedTreeLayout_ChildrenExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." //$NON-NLS-1$ //$NON-NLS-2$
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
        addAnnotation(getDiagramDescription_PreconditionExpression(), source, new String[] {});
        addAnnotation(getDiagramDescription_RootExpression(), source, new String[] { "diagram", "diagram.DSemanticDiagram | the current DDiagram.", //$NON-NLS-1$ //$NON-NLS-2$
                "viewpoint", "diagram.DSemanticDiagram | (deprecated) the current DDiagram." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDiagramElementMapping_PreconditionExpression(), source,
                new String[] { "containerView", "diagram.DragAndDropTarget | the view that should contain the potential views of the checked elements.", //$NON-NLS-1$ //$NON-NLS-2$
                        "container", "ecore.EObject | the semantic element of containerView.", //$NON-NLS-1$ //$NON-NLS-2$
                        "viewpoint", "diagram.DSemanticDiagram | (deprecated) the current DSemanticDiagram.", //$NON-NLS-1$ //$NON-NLS-2$
                        "diagram", "diagram.DSemanticDiagram | the current DSemanticDiagram.", //$NON-NLS-1$ //$NON-NLS-2$
                        "sourceView", "viewpoint.DSemanticDecorator | (edge only) the source view of the current potential edge.", //$NON-NLS-1$ //$NON-NLS-2$
                        "source", "ecore.EObject | (edge only) the semantic element of sourceView.", //$NON-NLS-1$ //$NON-NLS-2$
                        "targetView", "viewpoint.DSemanticDecorator | (edge only) the target view of the current potential edge.", //$NON-NLS-1$ //$NON-NLS-2$
                        "target", "ecore.EObject | (edge only) the semantic element of targetView." //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getDiagramElementMapping_SemanticCandidatesExpression(), source, new String[] { "containerView", "diagram.DSemanticDiagram | the parent view of potential candidates.", //$NON-NLS-1$ //$NON-NLS-2$
                "diagram", "diagram.DSemanticDiagram | the current DDiagram.", //$NON-NLS-1$ //$NON-NLS-2$
                "viewpoint", "diagram.DSemanticDiagram | (deprecated) the current DDiagram.", //$NON-NLS-1$ //$NON-NLS-2$
                "viewPoint", "diagram.DSemanticDiagram | (deprecated) the current DDiagram." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDiagramElementMapping_SemanticElements(), source, new String[] { "diagram", "diagram.DSemanticDiagram | the current DSemanticDiagram.", //$NON-NLS-1$ //$NON-NLS-2$
                "view", "diagram.DDiagramElement | the current view created from the current mapping.", //$NON-NLS-1$ //$NON-NLS-2$
                "viewpoint", "diagram.DSemanticDiagram | (deprecated) the current DSemanticDiagram." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getEdgeMapping_TargetFinderExpression(), source, new String[] { "diagram", "diagram.DSemanticDiagram | the current DDiagram.", //$NON-NLS-1$ //$NON-NLS-2$
                "viewpoint", "diagram.DSemanticDiagram | (deprecated) the current DDiagram.", //$NON-NLS-1$ //$NON-NLS-2$
                "viewPoint", "diagram.DSemanticDiagram | (deprecated) the current DDiagram." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getEdgeMapping_SourceFinderExpression(), source, new String[] { "diagram", "diagram.DSemanticDiagram | the current DDiagram.", //$NON-NLS-1$ //$NON-NLS-2$
                "viewpoint", "diagram.DSemanticDiagram | (deprecated) the current DDiagram.", //$NON-NLS-1$ //$NON-NLS-2$
                "viewPoint", "diagram.DSemanticDiagram | (deprecated) the current DDiagram." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getEdgeMapping_TargetExpression(), source, new String[] { "diagram", "diagram.DSemanticDiagram | the current DDiagram.", //$NON-NLS-1$ //$NON-NLS-2$
                "viewpoint", "diagram.DSemanticDiagram | (deprecated) the current DDiagram.", //$NON-NLS-1$ //$NON-NLS-2$
                "viewPoint", "diagram.DSemanticDiagram | (deprecated) the current DDiagram." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getEdgeMapping_PathExpression(), source, new String[] { "diagram", "diagram.DSemanticDiagram | the current DDiagram.", //$NON-NLS-1$ //$NON-NLS-2$
                "viewpoint", "diagram.DSemanticDiagram | (deprecated) the current DDiagram.", //$NON-NLS-1$ //$NON-NLS-2$
                "element", "ecore.EObject | the semantic element  of the current edge.", //$NON-NLS-1$ //$NON-NLS-2$
                "source", "ecore.EObject | the semantic target of the current source node.", //$NON-NLS-1$ //$NON-NLS-2$
                "target", "ecore.EObject | the semantic element of the current target node." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getOrderedTreeLayout_ChildrenExpression(), source, new String[] {});
    }

    /**
     * Initializes the annotations for <b>TagValues</b>. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void createTagValuesAnnotations() {
        String source = "TagValues"; //$NON-NLS-1$
        addAnnotation(getDiagramDescription_DefaultConcern(), source, new String[] {});
        addAnnotation(getDiagramDescription_Init(), source, new String[] {});
        addAnnotation(getDiagramDescription_NodeMappings(), source, new String[] {});
        addAnnotation(getDiagramDescription_EdgeMappings(), source, new String[] {});
        addAnnotation(getDiagramDescription_ContainerMappings(), source, new String[] {});
        addAnnotation(getDiagramDescription_ReusedMappings(), source, new String[] {});
        addAnnotation(getDiagramDescription_ReusedTools(), source, new String[] {});
    }

} // DescriptionPackageImpl
