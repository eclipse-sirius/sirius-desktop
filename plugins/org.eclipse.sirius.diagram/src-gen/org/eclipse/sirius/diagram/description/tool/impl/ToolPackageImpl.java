/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.description.tool.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.concern.ConcernPackage;
import org.eclipse.sirius.diagram.description.concern.impl.ConcernPackageImpl;
import org.eclipse.sirius.diagram.description.filter.FilterPackage;
import org.eclipse.sirius.diagram.description.filter.impl.FilterPackageImpl;
import org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.diagram.description.style.impl.StylePackageImpl;
import org.eclipse.sirius.diagram.description.tool.BehaviorTool;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.diagram.description.tool.ContainerDropDescription;
import org.eclipse.sirius.diagram.description.tool.CreateEdgeView;
import org.eclipse.sirius.diagram.description.tool.CreateView;
import org.eclipse.sirius.diagram.description.tool.DeleteElementDescription;
import org.eclipse.sirius.diagram.description.tool.DeleteHook;
import org.eclipse.sirius.diagram.description.tool.DeleteHookParameter;
import org.eclipse.sirius.diagram.description.tool.DiagramCreationDescription;
import org.eclipse.sirius.diagram.description.tool.DiagramNavigationDescription;
import org.eclipse.sirius.diagram.description.tool.DirectEditLabel;
import org.eclipse.sirius.diagram.description.tool.DoubleClickDescription;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.ElementDoubleClickVariable;
import org.eclipse.sirius.diagram.description.tool.Navigation;
import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.NodeCreationVariable;
import org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription;
import org.eclipse.sirius.diagram.description.tool.ReconnectionKind;
import org.eclipse.sirius.diagram.description.tool.RequestDescription;
import org.eclipse.sirius.diagram.description.tool.SourceEdgeCreationVariable;
import org.eclipse.sirius.diagram.description.tool.SourceEdgeViewCreationVariable;
import org.eclipse.sirius.diagram.description.tool.TargetEdgeCreationVariable;
import org.eclipse.sirius.diagram.description.tool.TargetEdgeViewCreationVariable;
import org.eclipse.sirius.diagram.description.tool.ToolFactory;
import org.eclipse.sirius.diagram.description.tool.ToolGroup;
import org.eclipse.sirius.diagram.description.tool.ToolGroupExtension;
import org.eclipse.sirius.diagram.description.tool.ToolPackage;
import org.eclipse.sirius.diagram.description.tool.ToolSection;
import org.eclipse.sirius.diagram.impl.DiagramPackageImpl;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 *
 * @generated
 */
public class ToolPackageImpl extends EPackageImpl implements ToolPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass toolSectionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass toolGroupEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass toolGroupExtensionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass nodeCreationDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass edgeCreationDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass containerCreationDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass deleteElementDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass doubleClickDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass deleteHookEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass deleteHookParameterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass reconnectEdgeDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass requestDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass directEditLabelEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass behaviorToolEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass sourceEdgeCreationVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass sourceEdgeViewCreationVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass targetEdgeCreationVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass targetEdgeViewCreationVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass elementDoubleClickVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass nodeCreationVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass createViewEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass createEdgeViewEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass navigationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass diagramCreationDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass diagramNavigationDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass containerDropDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EEnum reconnectionKindEEnum = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
     * EPackage.Registry} by the package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also
     * performs initialization of the package, or returns the registered package, if one already exists. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private ToolPackageImpl() {
        super(ToolPackage.eNS_URI, ToolFactory.eINSTANCE);
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
     * This method is used to initialize {@link ToolPackage#eINSTANCE} when that field is accessed. Clients should not
     * invoke it directly. Instead, they should simply access that field to obtain the package. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static ToolPackage init() {
        if (ToolPackageImpl.isInited) {
            return (ToolPackage) EPackage.Registry.INSTANCE.getEPackage(ToolPackage.eNS_URI);
        }

        // Obtain or create and register package
        ToolPackageImpl theToolPackage = (ToolPackageImpl) (EPackage.Registry.INSTANCE.get(ToolPackage.eNS_URI) instanceof ToolPackageImpl ? EPackage.Registry.INSTANCE.get(ToolPackage.eNS_URI)
                : new ToolPackageImpl());

        ToolPackageImpl.isInited = true;

        // Initialize simple dependencies
        EcorePackage.eINSTANCE.eClass();
        ViewpointPackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        DiagramPackageImpl theDiagramPackage = (DiagramPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(DiagramPackage.eNS_URI) instanceof DiagramPackageImpl
                ? EPackage.Registry.INSTANCE.getEPackage(DiagramPackage.eNS_URI)
                : DiagramPackage.eINSTANCE);
        DescriptionPackageImpl theDescriptionPackage = (DescriptionPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI) instanceof DescriptionPackageImpl
                ? EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI)
                : DescriptionPackage.eINSTANCE);
        StylePackageImpl theStylePackage = (StylePackageImpl) (EPackage.Registry.INSTANCE.getEPackage(StylePackage.eNS_URI) instanceof StylePackageImpl
                ? EPackage.Registry.INSTANCE.getEPackage(StylePackage.eNS_URI)
                : StylePackage.eINSTANCE);
        FilterPackageImpl theFilterPackage = (FilterPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(FilterPackage.eNS_URI) instanceof FilterPackageImpl
                ? EPackage.Registry.INSTANCE.getEPackage(FilterPackage.eNS_URI)
                : FilterPackage.eINSTANCE);
        ConcernPackageImpl theConcernPackage = (ConcernPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(ConcernPackage.eNS_URI) instanceof ConcernPackageImpl
                ? EPackage.Registry.INSTANCE.getEPackage(ConcernPackage.eNS_URI)
                : ConcernPackage.eINSTANCE);

        // Create package meta-data objects
        theToolPackage.createPackageContents();
        theDiagramPackage.createPackageContents();
        theDescriptionPackage.createPackageContents();
        theStylePackage.createPackageContents();
        theFilterPackage.createPackageContents();
        theConcernPackage.createPackageContents();

        // Initialize created meta-data
        theToolPackage.initializePackageContents();
        theDiagramPackage.initializePackageContents();
        theDescriptionPackage.initializePackageContents();
        theStylePackage.initializePackageContents();
        theFilterPackage.initializePackageContents();
        theConcernPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theToolPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(ToolPackage.eNS_URI, theToolPackage);
        return theToolPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getToolSection() {
        return toolSectionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getToolSection_Icon() {
        return (EAttribute) toolSectionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getToolSection_OwnedTools() {
        return (EReference) toolSectionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getToolSection_SubSections() {
        return (EReference) toolSectionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getToolSection_PopupMenus() {
        return (EReference) toolSectionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getToolSection_ReusedTools() {
        return (EReference) toolSectionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getToolSection_GroupExtensions() {
        return (EReference) toolSectionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getToolSection_Groups() {
        return (EReference) toolSectionEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getToolGroup() {
        return toolGroupEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getToolGroup_Tools() {
        return (EReference) toolGroupEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getToolGroupExtension() {
        return toolGroupExtensionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getToolGroupExtension_Group() {
        return (EReference) toolGroupExtensionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getToolGroupExtension_Tools() {
        return (EReference) toolGroupExtensionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getNodeCreationDescription() {
        return nodeCreationDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getNodeCreationDescription_NodeMappings() {
        return (EReference) nodeCreationDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getNodeCreationDescription_Variable() {
        return (EReference) nodeCreationDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getNodeCreationDescription_ViewVariable() {
        return (EReference) nodeCreationDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getNodeCreationDescription_InitialOperation() {
        return (EReference) nodeCreationDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getNodeCreationDescription_IconPath() {
        return (EAttribute) nodeCreationDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getNodeCreationDescription_ExtraMappings() {
        return (EReference) nodeCreationDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getEdgeCreationDescription() {
        return edgeCreationDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEdgeCreationDescription_EdgeMappings() {
        return (EReference) edgeCreationDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEdgeCreationDescription_SourceVariable() {
        return (EReference) edgeCreationDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEdgeCreationDescription_TargetVariable() {
        return (EReference) edgeCreationDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEdgeCreationDescription_SourceViewVariable() {
        return (EReference) edgeCreationDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEdgeCreationDescription_TargetViewVariable() {
        return (EReference) edgeCreationDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEdgeCreationDescription_InitialOperation() {
        return (EReference) edgeCreationDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEdgeCreationDescription_IconPath() {
        return (EAttribute) edgeCreationDescriptionEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEdgeCreationDescription_ExtraSourceMappings() {
        return (EReference) edgeCreationDescriptionEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEdgeCreationDescription_ExtraTargetMappings() {
        return (EReference) edgeCreationDescriptionEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEdgeCreationDescription_ConnectionStartPrecondition() {
        return (EAttribute) edgeCreationDescriptionEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getContainerCreationDescription() {
        return containerCreationDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getContainerCreationDescription_ContainerMappings() {
        return (EReference) containerCreationDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getContainerCreationDescription_Variable() {
        return (EReference) containerCreationDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getContainerCreationDescription_ViewVariable() {
        return (EReference) containerCreationDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getContainerCreationDescription_InitialOperation() {
        return (EReference) containerCreationDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getContainerCreationDescription_IconPath() {
        return (EAttribute) containerCreationDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getContainerCreationDescription_ExtraMappings() {
        return (EReference) containerCreationDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDeleteElementDescription() {
        return deleteElementDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDeleteElementDescription_Element() {
        return (EReference) deleteElementDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDeleteElementDescription_ElementView() {
        return (EReference) deleteElementDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDeleteElementDescription_ContainerView() {
        return (EReference) deleteElementDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDeleteElementDescription_InitialOperation() {
        return (EReference) deleteElementDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDeleteElementDescription_Hook() {
        return (EReference) deleteElementDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDoubleClickDescription() {
        return doubleClickDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDoubleClickDescription_Mappings() {
        return (EReference) doubleClickDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDoubleClickDescription_Element() {
        return (EReference) doubleClickDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDoubleClickDescription_ElementView() {
        return (EReference) doubleClickDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDoubleClickDescription_InitialOperation() {
        return (EReference) doubleClickDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDeleteHook() {
        return deleteHookEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDeleteHook_Id() {
        return (EAttribute) deleteHookEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDeleteHook_Parameters() {
        return (EReference) deleteHookEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDeleteHookParameter() {
        return deleteHookParameterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDeleteHookParameter_Name() {
        return (EAttribute) deleteHookParameterEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDeleteHookParameter_Value() {
        return (EAttribute) deleteHookParameterEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getReconnectEdgeDescription() {
        return reconnectEdgeDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getReconnectEdgeDescription_ReconnectionKind() {
        return (EAttribute) reconnectEdgeDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getReconnectEdgeDescription_Source() {
        return (EReference) reconnectEdgeDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getReconnectEdgeDescription_Target() {
        return (EReference) reconnectEdgeDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getReconnectEdgeDescription_SourceView() {
        return (EReference) reconnectEdgeDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getReconnectEdgeDescription_TargetView() {
        return (EReference) reconnectEdgeDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getReconnectEdgeDescription_Element() {
        return (EReference) reconnectEdgeDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getReconnectEdgeDescription_InitialOperation() {
        return (EReference) reconnectEdgeDescriptionEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getReconnectEdgeDescription_EdgeView() {
        return (EReference) reconnectEdgeDescriptionEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getRequestDescription() {
        return requestDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getRequestDescription_Type() {
        return (EAttribute) requestDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDirectEditLabel() {
        return directEditLabelEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDirectEditLabel_Mask() {
        return (EReference) directEditLabelEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDirectEditLabel_InitialOperation() {
        return (EReference) directEditLabelEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDirectEditLabel_InputLabelExpression() {
        return (EAttribute) directEditLabelEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getBehaviorTool() {
        return behaviorToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getBehaviorTool_DomainClass() {
        return (EAttribute) behaviorToolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getBehaviorTool_InitialOperation() {
        return (EReference) behaviorToolEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getSourceEdgeCreationVariable() {
        return sourceEdgeCreationVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getSourceEdgeViewCreationVariable() {
        return sourceEdgeViewCreationVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getTargetEdgeCreationVariable() {
        return targetEdgeCreationVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getTargetEdgeViewCreationVariable() {
        return targetEdgeViewCreationVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getElementDoubleClickVariable() {
        return elementDoubleClickVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getNodeCreationVariable() {
        return nodeCreationVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getCreateView() {
        return createViewEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getCreateView_Mapping() {
        return (EReference) createViewEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getCreateView_ContainerViewExpression() {
        return (EAttribute) createViewEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getCreateView_VariableName() {
        return (EAttribute) createViewEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getCreateEdgeView() {
        return createEdgeViewEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getCreateEdgeView_SourceExpression() {
        return (EAttribute) createEdgeViewEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getCreateEdgeView_TargetExpression() {
        return (EAttribute) createEdgeViewEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getNavigation() {
        return navigationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getNavigation_CreateIfNotExistent() {
        return (EAttribute) navigationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getNavigation_DiagramDescription() {
        return (EReference) navigationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDiagramCreationDescription() {
        return diagramCreationDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDiagramCreationDescription_DiagramDescription() {
        return (EReference) diagramCreationDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDiagramNavigationDescription() {
        return diagramNavigationDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDiagramNavigationDescription_DiagramDescription() {
        return (EReference) diagramNavigationDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getContainerDropDescription() {
        return containerDropDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getContainerDropDescription_Mappings() {
        return (EReference) containerDropDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getContainerDropDescription_OldContainer() {
        return (EReference) containerDropDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getContainerDropDescription_NewContainer() {
        return (EReference) containerDropDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getContainerDropDescription_Element() {
        return (EReference) containerDropDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getContainerDropDescription_NewViewContainer() {
        return (EReference) containerDropDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getContainerDropDescription_InitialOperation() {
        return (EReference) containerDropDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getContainerDropDescription_DragSource() {
        return (EAttribute) containerDropDescriptionEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getContainerDropDescription_MoveEdges() {
        return (EAttribute) containerDropDescriptionEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EEnum getReconnectionKind() {
        return reconnectionKindEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ToolFactory getToolFactory() {
        return (ToolFactory) getEFactoryInstance();
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
        toolSectionEClass = createEClass(ToolPackage.TOOL_SECTION);
        createEAttribute(toolSectionEClass, ToolPackage.TOOL_SECTION__ICON);
        createEReference(toolSectionEClass, ToolPackage.TOOL_SECTION__OWNED_TOOLS);
        createEReference(toolSectionEClass, ToolPackage.TOOL_SECTION__SUB_SECTIONS);
        createEReference(toolSectionEClass, ToolPackage.TOOL_SECTION__POPUP_MENUS);
        createEReference(toolSectionEClass, ToolPackage.TOOL_SECTION__REUSED_TOOLS);
        createEReference(toolSectionEClass, ToolPackage.TOOL_SECTION__GROUP_EXTENSIONS);
        createEReference(toolSectionEClass, ToolPackage.TOOL_SECTION__GROUPS);

        toolGroupEClass = createEClass(ToolPackage.TOOL_GROUP);
        createEReference(toolGroupEClass, ToolPackage.TOOL_GROUP__TOOLS);

        toolGroupExtensionEClass = createEClass(ToolPackage.TOOL_GROUP_EXTENSION);
        createEReference(toolGroupExtensionEClass, ToolPackage.TOOL_GROUP_EXTENSION__GROUP);
        createEReference(toolGroupExtensionEClass, ToolPackage.TOOL_GROUP_EXTENSION__TOOLS);

        nodeCreationDescriptionEClass = createEClass(ToolPackage.NODE_CREATION_DESCRIPTION);
        createEReference(nodeCreationDescriptionEClass, ToolPackage.NODE_CREATION_DESCRIPTION__NODE_MAPPINGS);
        createEReference(nodeCreationDescriptionEClass, ToolPackage.NODE_CREATION_DESCRIPTION__VARIABLE);
        createEReference(nodeCreationDescriptionEClass, ToolPackage.NODE_CREATION_DESCRIPTION__VIEW_VARIABLE);
        createEReference(nodeCreationDescriptionEClass, ToolPackage.NODE_CREATION_DESCRIPTION__INITIAL_OPERATION);
        createEAttribute(nodeCreationDescriptionEClass, ToolPackage.NODE_CREATION_DESCRIPTION__ICON_PATH);
        createEReference(nodeCreationDescriptionEClass, ToolPackage.NODE_CREATION_DESCRIPTION__EXTRA_MAPPINGS);

        edgeCreationDescriptionEClass = createEClass(ToolPackage.EDGE_CREATION_DESCRIPTION);
        createEReference(edgeCreationDescriptionEClass, ToolPackage.EDGE_CREATION_DESCRIPTION__EDGE_MAPPINGS);
        createEReference(edgeCreationDescriptionEClass, ToolPackage.EDGE_CREATION_DESCRIPTION__SOURCE_VARIABLE);
        createEReference(edgeCreationDescriptionEClass, ToolPackage.EDGE_CREATION_DESCRIPTION__TARGET_VARIABLE);
        createEReference(edgeCreationDescriptionEClass, ToolPackage.EDGE_CREATION_DESCRIPTION__SOURCE_VIEW_VARIABLE);
        createEReference(edgeCreationDescriptionEClass, ToolPackage.EDGE_CREATION_DESCRIPTION__TARGET_VIEW_VARIABLE);
        createEReference(edgeCreationDescriptionEClass, ToolPackage.EDGE_CREATION_DESCRIPTION__INITIAL_OPERATION);
        createEAttribute(edgeCreationDescriptionEClass, ToolPackage.EDGE_CREATION_DESCRIPTION__ICON_PATH);
        createEReference(edgeCreationDescriptionEClass, ToolPackage.EDGE_CREATION_DESCRIPTION__EXTRA_SOURCE_MAPPINGS);
        createEReference(edgeCreationDescriptionEClass, ToolPackage.EDGE_CREATION_DESCRIPTION__EXTRA_TARGET_MAPPINGS);
        createEAttribute(edgeCreationDescriptionEClass, ToolPackage.EDGE_CREATION_DESCRIPTION__CONNECTION_START_PRECONDITION);

        containerCreationDescriptionEClass = createEClass(ToolPackage.CONTAINER_CREATION_DESCRIPTION);
        createEReference(containerCreationDescriptionEClass, ToolPackage.CONTAINER_CREATION_DESCRIPTION__CONTAINER_MAPPINGS);
        createEReference(containerCreationDescriptionEClass, ToolPackage.CONTAINER_CREATION_DESCRIPTION__VARIABLE);
        createEReference(containerCreationDescriptionEClass, ToolPackage.CONTAINER_CREATION_DESCRIPTION__VIEW_VARIABLE);
        createEReference(containerCreationDescriptionEClass, ToolPackage.CONTAINER_CREATION_DESCRIPTION__INITIAL_OPERATION);
        createEAttribute(containerCreationDescriptionEClass, ToolPackage.CONTAINER_CREATION_DESCRIPTION__ICON_PATH);
        createEReference(containerCreationDescriptionEClass, ToolPackage.CONTAINER_CREATION_DESCRIPTION__EXTRA_MAPPINGS);

        deleteElementDescriptionEClass = createEClass(ToolPackage.DELETE_ELEMENT_DESCRIPTION);
        createEReference(deleteElementDescriptionEClass, ToolPackage.DELETE_ELEMENT_DESCRIPTION__ELEMENT);
        createEReference(deleteElementDescriptionEClass, ToolPackage.DELETE_ELEMENT_DESCRIPTION__ELEMENT_VIEW);
        createEReference(deleteElementDescriptionEClass, ToolPackage.DELETE_ELEMENT_DESCRIPTION__CONTAINER_VIEW);
        createEReference(deleteElementDescriptionEClass, ToolPackage.DELETE_ELEMENT_DESCRIPTION__INITIAL_OPERATION);
        createEReference(deleteElementDescriptionEClass, ToolPackage.DELETE_ELEMENT_DESCRIPTION__HOOK);

        doubleClickDescriptionEClass = createEClass(ToolPackage.DOUBLE_CLICK_DESCRIPTION);
        createEReference(doubleClickDescriptionEClass, ToolPackage.DOUBLE_CLICK_DESCRIPTION__MAPPINGS);
        createEReference(doubleClickDescriptionEClass, ToolPackage.DOUBLE_CLICK_DESCRIPTION__ELEMENT);
        createEReference(doubleClickDescriptionEClass, ToolPackage.DOUBLE_CLICK_DESCRIPTION__ELEMENT_VIEW);
        createEReference(doubleClickDescriptionEClass, ToolPackage.DOUBLE_CLICK_DESCRIPTION__INITIAL_OPERATION);

        deleteHookEClass = createEClass(ToolPackage.DELETE_HOOK);
        createEAttribute(deleteHookEClass, ToolPackage.DELETE_HOOK__ID);
        createEReference(deleteHookEClass, ToolPackage.DELETE_HOOK__PARAMETERS);

        deleteHookParameterEClass = createEClass(ToolPackage.DELETE_HOOK_PARAMETER);
        createEAttribute(deleteHookParameterEClass, ToolPackage.DELETE_HOOK_PARAMETER__NAME);
        createEAttribute(deleteHookParameterEClass, ToolPackage.DELETE_HOOK_PARAMETER__VALUE);

        reconnectEdgeDescriptionEClass = createEClass(ToolPackage.RECONNECT_EDGE_DESCRIPTION);
        createEAttribute(reconnectEdgeDescriptionEClass, ToolPackage.RECONNECT_EDGE_DESCRIPTION__RECONNECTION_KIND);
        createEReference(reconnectEdgeDescriptionEClass, ToolPackage.RECONNECT_EDGE_DESCRIPTION__SOURCE);
        createEReference(reconnectEdgeDescriptionEClass, ToolPackage.RECONNECT_EDGE_DESCRIPTION__TARGET);
        createEReference(reconnectEdgeDescriptionEClass, ToolPackage.RECONNECT_EDGE_DESCRIPTION__SOURCE_VIEW);
        createEReference(reconnectEdgeDescriptionEClass, ToolPackage.RECONNECT_EDGE_DESCRIPTION__TARGET_VIEW);
        createEReference(reconnectEdgeDescriptionEClass, ToolPackage.RECONNECT_EDGE_DESCRIPTION__ELEMENT);
        createEReference(reconnectEdgeDescriptionEClass, ToolPackage.RECONNECT_EDGE_DESCRIPTION__INITIAL_OPERATION);
        createEReference(reconnectEdgeDescriptionEClass, ToolPackage.RECONNECT_EDGE_DESCRIPTION__EDGE_VIEW);

        requestDescriptionEClass = createEClass(ToolPackage.REQUEST_DESCRIPTION);
        createEAttribute(requestDescriptionEClass, ToolPackage.REQUEST_DESCRIPTION__TYPE);

        directEditLabelEClass = createEClass(ToolPackage.DIRECT_EDIT_LABEL);
        createEReference(directEditLabelEClass, ToolPackage.DIRECT_EDIT_LABEL__MASK);
        createEReference(directEditLabelEClass, ToolPackage.DIRECT_EDIT_LABEL__INITIAL_OPERATION);
        createEAttribute(directEditLabelEClass, ToolPackage.DIRECT_EDIT_LABEL__INPUT_LABEL_EXPRESSION);

        behaviorToolEClass = createEClass(ToolPackage.BEHAVIOR_TOOL);
        createEAttribute(behaviorToolEClass, ToolPackage.BEHAVIOR_TOOL__DOMAIN_CLASS);
        createEReference(behaviorToolEClass, ToolPackage.BEHAVIOR_TOOL__INITIAL_OPERATION);

        sourceEdgeCreationVariableEClass = createEClass(ToolPackage.SOURCE_EDGE_CREATION_VARIABLE);

        sourceEdgeViewCreationVariableEClass = createEClass(ToolPackage.SOURCE_EDGE_VIEW_CREATION_VARIABLE);

        targetEdgeCreationVariableEClass = createEClass(ToolPackage.TARGET_EDGE_CREATION_VARIABLE);

        targetEdgeViewCreationVariableEClass = createEClass(ToolPackage.TARGET_EDGE_VIEW_CREATION_VARIABLE);

        elementDoubleClickVariableEClass = createEClass(ToolPackage.ELEMENT_DOUBLE_CLICK_VARIABLE);

        nodeCreationVariableEClass = createEClass(ToolPackage.NODE_CREATION_VARIABLE);

        createViewEClass = createEClass(ToolPackage.CREATE_VIEW);
        createEReference(createViewEClass, ToolPackage.CREATE_VIEW__MAPPING);
        createEAttribute(createViewEClass, ToolPackage.CREATE_VIEW__CONTAINER_VIEW_EXPRESSION);
        createEAttribute(createViewEClass, ToolPackage.CREATE_VIEW__VARIABLE_NAME);

        createEdgeViewEClass = createEClass(ToolPackage.CREATE_EDGE_VIEW);
        createEAttribute(createEdgeViewEClass, ToolPackage.CREATE_EDGE_VIEW__SOURCE_EXPRESSION);
        createEAttribute(createEdgeViewEClass, ToolPackage.CREATE_EDGE_VIEW__TARGET_EXPRESSION);

        navigationEClass = createEClass(ToolPackage.NAVIGATION);
        createEAttribute(navigationEClass, ToolPackage.NAVIGATION__CREATE_IF_NOT_EXISTENT);
        createEReference(navigationEClass, ToolPackage.NAVIGATION__DIAGRAM_DESCRIPTION);

        diagramCreationDescriptionEClass = createEClass(ToolPackage.DIAGRAM_CREATION_DESCRIPTION);
        createEReference(diagramCreationDescriptionEClass, ToolPackage.DIAGRAM_CREATION_DESCRIPTION__DIAGRAM_DESCRIPTION);

        diagramNavigationDescriptionEClass = createEClass(ToolPackage.DIAGRAM_NAVIGATION_DESCRIPTION);
        createEReference(diagramNavigationDescriptionEClass, ToolPackage.DIAGRAM_NAVIGATION_DESCRIPTION__DIAGRAM_DESCRIPTION);

        containerDropDescriptionEClass = createEClass(ToolPackage.CONTAINER_DROP_DESCRIPTION);
        createEReference(containerDropDescriptionEClass, ToolPackage.CONTAINER_DROP_DESCRIPTION__MAPPINGS);
        createEReference(containerDropDescriptionEClass, ToolPackage.CONTAINER_DROP_DESCRIPTION__OLD_CONTAINER);
        createEReference(containerDropDescriptionEClass, ToolPackage.CONTAINER_DROP_DESCRIPTION__NEW_CONTAINER);
        createEReference(containerDropDescriptionEClass, ToolPackage.CONTAINER_DROP_DESCRIPTION__ELEMENT);
        createEReference(containerDropDescriptionEClass, ToolPackage.CONTAINER_DROP_DESCRIPTION__NEW_VIEW_CONTAINER);
        createEReference(containerDropDescriptionEClass, ToolPackage.CONTAINER_DROP_DESCRIPTION__INITIAL_OPERATION);
        createEAttribute(containerDropDescriptionEClass, ToolPackage.CONTAINER_DROP_DESCRIPTION__DRAG_SOURCE);
        createEAttribute(containerDropDescriptionEClass, ToolPackage.CONTAINER_DROP_DESCRIPTION__MOVE_EDGES);

        // Create enums
        reconnectionKindEEnum = createEEnum(ToolPackage.RECONNECTION_KIND);
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
        setName(ToolPackage.eNAME);
        setNsPrefix(ToolPackage.eNS_PREFIX);
        setNsURI(ToolPackage.eNS_URI);

        // Obtain other dependent packages
        org.eclipse.sirius.viewpoint.description.DescriptionPackage theDescriptionPackage_1 = (org.eclipse.sirius.viewpoint.description.DescriptionPackage) EPackage.Registry.INSTANCE
                .getEPackage(org.eclipse.sirius.viewpoint.description.DescriptionPackage.eNS_URI);
        org.eclipse.sirius.viewpoint.description.tool.ToolPackage theToolPackage_1 = (org.eclipse.sirius.viewpoint.description.tool.ToolPackage) EPackage.Registry.INSTANCE
                .getEPackage(org.eclipse.sirius.viewpoint.description.tool.ToolPackage.eNS_URI);
        DescriptionPackage theDescriptionPackage = (DescriptionPackage) EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI);
        EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        toolSectionEClass.getESuperTypes().add(theDescriptionPackage_1.getDocumentedElement());
        toolSectionEClass.getESuperTypes().add(theDescriptionPackage_1.getIdentifiedElement());
        toolGroupEClass.getESuperTypes().add(theToolPackage_1.getToolEntry());
        nodeCreationDescriptionEClass.getESuperTypes().add(theToolPackage_1.getMappingBasedToolDescription());
        edgeCreationDescriptionEClass.getESuperTypes().add(theToolPackage_1.getMappingBasedToolDescription());
        containerCreationDescriptionEClass.getESuperTypes().add(theToolPackage_1.getMappingBasedToolDescription());
        deleteElementDescriptionEClass.getESuperTypes().add(theToolPackage_1.getMappingBasedToolDescription());
        doubleClickDescriptionEClass.getESuperTypes().add(theToolPackage_1.getMappingBasedToolDescription());
        reconnectEdgeDescriptionEClass.getESuperTypes().add(theToolPackage_1.getMappingBasedToolDescription());
        requestDescriptionEClass.getESuperTypes().add(theToolPackage_1.getAbstractToolDescription());
        directEditLabelEClass.getESuperTypes().add(theToolPackage_1.getMappingBasedToolDescription());
        behaviorToolEClass.getESuperTypes().add(theToolPackage_1.getAbstractToolDescription());
        sourceEdgeCreationVariableEClass.getESuperTypes().add(theDescriptionPackage_1.getAbstractVariable());
        sourceEdgeCreationVariableEClass.getESuperTypes().add(theToolPackage_1.getVariableContainer());
        sourceEdgeViewCreationVariableEClass.getESuperTypes().add(theDescriptionPackage_1.getAbstractVariable());
        sourceEdgeViewCreationVariableEClass.getESuperTypes().add(theToolPackage_1.getVariableContainer());
        targetEdgeCreationVariableEClass.getESuperTypes().add(theDescriptionPackage_1.getAbstractVariable());
        targetEdgeCreationVariableEClass.getESuperTypes().add(theToolPackage_1.getVariableContainer());
        targetEdgeViewCreationVariableEClass.getESuperTypes().add(theDescriptionPackage_1.getAbstractVariable());
        targetEdgeViewCreationVariableEClass.getESuperTypes().add(theToolPackage_1.getVariableContainer());
        elementDoubleClickVariableEClass.getESuperTypes().add(theDescriptionPackage_1.getAbstractVariable());
        elementDoubleClickVariableEClass.getESuperTypes().add(theToolPackage_1.getVariableContainer());
        nodeCreationVariableEClass.getESuperTypes().add(theDescriptionPackage_1.getAbstractVariable());
        nodeCreationVariableEClass.getESuperTypes().add(theToolPackage_1.getVariableContainer());
        createViewEClass.getESuperTypes().add(theToolPackage_1.getContainerModelOperation());
        createEdgeViewEClass.getESuperTypes().add(this.getCreateView());
        navigationEClass.getESuperTypes().add(theToolPackage_1.getContainerModelOperation());
        diagramCreationDescriptionEClass.getESuperTypes().add(theToolPackage_1.getRepresentationCreationDescription());
        diagramNavigationDescriptionEClass.getESuperTypes().add(theToolPackage_1.getRepresentationNavigationDescription());
        containerDropDescriptionEClass.getESuperTypes().add(theToolPackage_1.getMappingBasedToolDescription());

        // Initialize classes and features; add operations and parameters
        initEClass(toolSectionEClass, ToolSection.class, "ToolSection", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getToolSection_Icon(), theDescriptionPackage_1.getImagePath(), "icon", null, 0, 1, ToolSection.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getToolSection_OwnedTools(), theToolPackage_1.getToolEntry(), null, "ownedTools", null, 0, -1, ToolSection.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        getToolSection_OwnedTools().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(getToolSection_SubSections(), this.getToolSection(), null, "subSections", null, 0, -1, ToolSection.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        getToolSection_SubSections().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(getToolSection_PopupMenus(), theToolPackage_1.getPopupMenu(), null, "popupMenus", null, 0, -1, ToolSection.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                !EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getToolSection_ReusedTools(), theToolPackage_1.getToolEntry(), null, "reusedTools", null, 0, -1, ToolSection.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getToolSection_GroupExtensions(), this.getToolGroupExtension(), null, "groupExtensions", null, 0, -1, ToolSection.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getToolSection_Groups(), theToolPackage_1.getGroupMenu(), null, "groups", null, 0, -1, ToolSection.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                !EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(toolGroupEClass, ToolGroup.class, "ToolGroup", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getToolGroup_Tools(), theToolPackage_1.getAbstractToolDescription(), null, "tools", null, 0, -1, ToolGroup.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(toolGroupExtensionEClass, ToolGroupExtension.class, "ToolGroupExtension", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getToolGroupExtension_Group(), this.getToolGroup(), null, "group", null, 1, 1, ToolGroupExtension.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getToolGroupExtension_Tools(), theToolPackage_1.getAbstractToolDescription(), null, "tools", null, 0, -1, ToolGroupExtension.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(nodeCreationDescriptionEClass, NodeCreationDescription.class, "NodeCreationDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getNodeCreationDescription_NodeMappings(), theDescriptionPackage.getNodeMapping(), null, "nodeMappings", null, 1, -1, NodeCreationDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getNodeCreationDescription_Variable(), this.getNodeCreationVariable(), null, "variable", null, 1, 1, NodeCreationDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getNodeCreationDescription_ViewVariable(), theToolPackage_1.getContainerViewVariable(), null, "viewVariable", null, 1, 1, NodeCreationDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getNodeCreationDescription_InitialOperation(), theToolPackage_1.getInitialNodeCreationOperation(), null, "initialOperation", null, 1, 1, NodeCreationDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getNodeCreationDescription_IconPath(), theDescriptionPackage_1.getImagePath(), "iconPath", "", 0, 1, NodeCreationDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getNodeCreationDescription_ExtraMappings(), theDescriptionPackage.getAbstractNodeMapping(), null, "extraMappings", null, 0, -1, NodeCreationDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(edgeCreationDescriptionEClass, EdgeCreationDescription.class, "EdgeCreationDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getEdgeCreationDescription_EdgeMappings(), theDescriptionPackage.getEdgeMapping(), null, "edgeMappings", null, 1, -1, EdgeCreationDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getEdgeCreationDescription_SourceVariable(), this.getSourceEdgeCreationVariable(), null, "sourceVariable", null, 1, 1, EdgeCreationDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getEdgeCreationDescription_TargetVariable(), this.getTargetEdgeCreationVariable(), null, "targetVariable", null, 1, 1, EdgeCreationDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getEdgeCreationDescription_SourceViewVariable(), this.getSourceEdgeViewCreationVariable(), null, "sourceViewVariable", null, 1, 1, EdgeCreationDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getEdgeCreationDescription_TargetViewVariable(), this.getTargetEdgeViewCreationVariable(), null, "targetViewVariable", null, 1, 1, EdgeCreationDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getEdgeCreationDescription_InitialOperation(), theToolPackage_1.getInitEdgeCreationOperation(), null, "initialOperation", null, 1, 1, EdgeCreationDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getEdgeCreationDescription_IconPath(), theDescriptionPackage_1.getImagePath(), "iconPath", "", 0, 1, EdgeCreationDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getEdgeCreationDescription_ExtraSourceMappings(), theDescriptionPackage.getDiagramElementMapping(), null, "extraSourceMappings", null, 0, -1, EdgeCreationDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getEdgeCreationDescription_ExtraTargetMappings(), theDescriptionPackage.getDiagramElementMapping(), null, "extraTargetMappings", null, 0, -1, EdgeCreationDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getEdgeCreationDescription_ConnectionStartPrecondition(), theDescriptionPackage_1.getInterpretedExpression(), "connectionStartPrecondition", null, 0, 1, //$NON-NLS-1$
                EdgeCreationDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(containerCreationDescriptionEClass, ContainerCreationDescription.class, "ContainerCreationDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getContainerCreationDescription_ContainerMappings(), theDescriptionPackage.getContainerMapping(), null, "containerMappings", null, 1, -1, ContainerCreationDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getContainerCreationDescription_Variable(), this.getNodeCreationVariable(), null, "variable", null, 1, 1, ContainerCreationDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getContainerCreationDescription_ViewVariable(), theToolPackage_1.getContainerViewVariable(), null, "viewVariable", null, 1, 1, ContainerCreationDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getContainerCreationDescription_InitialOperation(), theToolPackage_1.getInitialNodeCreationOperation(), null, "initialOperation", null, 1, 1, ContainerCreationDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getContainerCreationDescription_IconPath(), theDescriptionPackage_1.getImagePath(), "iconPath", "", 0, 1, ContainerCreationDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getContainerCreationDescription_ExtraMappings(), theDescriptionPackage.getAbstractNodeMapping(), null, "extraMappings", null, 0, -1, ContainerCreationDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(deleteElementDescriptionEClass, DeleteElementDescription.class, "DeleteElementDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDeleteElementDescription_Element(), theToolPackage_1.getElementDeleteVariable(), null, "element", null, 1, 1, DeleteElementDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDeleteElementDescription_ElementView(), theToolPackage_1.getElementDeleteVariable(), null, "elementView", null, 0, 1, DeleteElementDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDeleteElementDescription_ContainerView(), theToolPackage_1.getContainerViewVariable(), null, "containerView", null, 1, 1, DeleteElementDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDeleteElementDescription_InitialOperation(), theToolPackage_1.getInitialOperation(), null, "initialOperation", null, 1, 1, DeleteElementDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDeleteElementDescription_Hook(), this.getDeleteHook(), null, "hook", null, 0, 1, DeleteElementDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        addEOperation(deleteElementDescriptionEClass, theDescriptionPackage.getDiagramElementMapping(), "getMappings", 1, -1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(doubleClickDescriptionEClass, DoubleClickDescription.class, "DoubleClickDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDoubleClickDescription_Mappings(), theDescriptionPackage.getDiagramElementMapping(), theDescriptionPackage.getDiagramElementMapping_DoubleClickDescription(), "mappings", //$NON-NLS-1$
                null, 1, -1, DoubleClickDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE,
                EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDoubleClickDescription_Element(), this.getElementDoubleClickVariable(), null, "element", null, 1, 1, DoubleClickDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDoubleClickDescription_ElementView(), this.getElementDoubleClickVariable(), null, "elementView", null, 0, 1, DoubleClickDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDoubleClickDescription_InitialOperation(), theToolPackage_1.getInitialOperation(), null, "initialOperation", null, 1, 1, DoubleClickDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(deleteHookEClass, DeleteHook.class, "DeleteHook", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getDeleteHook_Id(), theEcorePackage.getEString(), "id", null, 1, 1, DeleteHook.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDeleteHook_Parameters(), this.getDeleteHookParameter(), null, "parameters", null, 0, -1, DeleteHook.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(deleteHookParameterEClass, DeleteHookParameter.class, "DeleteHookParameter", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getDeleteHookParameter_Name(), theEcorePackage.getEString(), "name", null, 1, 1, DeleteHookParameter.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDeleteHookParameter_Value(), theDescriptionPackage_1.getInterpretedExpression(), "value", null, 0, 1, DeleteHookParameter.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(reconnectEdgeDescriptionEClass, ReconnectEdgeDescription.class, "ReconnectEdgeDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getReconnectEdgeDescription_ReconnectionKind(), this.getReconnectionKind(), "reconnectionKind", "RECONNECT_TARGET", 1, 1, ReconnectEdgeDescription.class, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getReconnectEdgeDescription_Source(), this.getSourceEdgeCreationVariable(), null, "source", null, 1, 1, ReconnectEdgeDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getReconnectEdgeDescription_Target(), this.getTargetEdgeCreationVariable(), null, "target", null, 1, 1, ReconnectEdgeDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getReconnectEdgeDescription_SourceView(), this.getSourceEdgeViewCreationVariable(), null, "sourceView", null, 1, 1, ReconnectEdgeDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getReconnectEdgeDescription_TargetView(), this.getTargetEdgeViewCreationVariable(), null, "targetView", null, 1, 1, ReconnectEdgeDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getReconnectEdgeDescription_Element(), theToolPackage_1.getElementSelectVariable(), null, "element", null, 1, 1, ReconnectEdgeDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getReconnectEdgeDescription_InitialOperation(), theToolPackage_1.getInitialOperation(), null, "initialOperation", null, 1, 1, ReconnectEdgeDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getReconnectEdgeDescription_EdgeView(), theToolPackage_1.getElementSelectVariable(), null, "edgeView", null, 1, 1, ReconnectEdgeDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        addEOperation(reconnectEdgeDescriptionEClass, theDescriptionPackage.getEdgeMapping(), "getMappings", 1, -1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(requestDescriptionEClass, RequestDescription.class, "RequestDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getRequestDescription_Type(), theEcorePackage.getEString(), "type", null, 1, 1, RequestDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(directEditLabelEClass, DirectEditLabel.class, "DirectEditLabel", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getDirectEditLabel_Mask(), theToolPackage_1.getEditMaskVariables(), null, "mask", null, 1, 1, DirectEditLabel.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getDirectEditLabel_InitialOperation(), theToolPackage_1.getInitialOperation(), null, "initialOperation", null, 1, 1, DirectEditLabel.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDirectEditLabel_InputLabelExpression(), theDescriptionPackage_1.getInterpretedExpression(), "inputLabelExpression", null, 0, 1, DirectEditLabel.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        addEOperation(directEditLabelEClass, theDescriptionPackage.getDiagramElementMapping(), "getMapping", 1, -1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(behaviorToolEClass, BehaviorTool.class, "BehaviorTool", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getBehaviorTool_DomainClass(), theDescriptionPackage_1.getTypeName(), "domainClass", "EObject", 1, 1, BehaviorTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getBehaviorTool_InitialOperation(), theToolPackage_1.getInitialOperation(), null, "initialOperation", null, 1, 1, BehaviorTool.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(sourceEdgeCreationVariableEClass, SourceEdgeCreationVariable.class, "SourceEdgeCreationVariable", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);

        initEClass(sourceEdgeViewCreationVariableEClass, SourceEdgeViewCreationVariable.class, "SourceEdgeViewCreationVariable", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);

        initEClass(targetEdgeCreationVariableEClass, TargetEdgeCreationVariable.class, "TargetEdgeCreationVariable", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);

        initEClass(targetEdgeViewCreationVariableEClass, TargetEdgeViewCreationVariable.class, "TargetEdgeViewCreationVariable", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);

        initEClass(elementDoubleClickVariableEClass, ElementDoubleClickVariable.class, "ElementDoubleClickVariable", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);

        initEClass(nodeCreationVariableEClass, NodeCreationVariable.class, "NodeCreationVariable", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(createViewEClass, CreateView.class, "CreateView", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getCreateView_Mapping(), theDescriptionPackage.getDiagramElementMapping(), null, "mapping", null, 1, 1, CreateView.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getCreateView_ContainerViewExpression(), theDescriptionPackage_1.getInterpretedExpression(), "containerViewExpression", "", 1, 1, CreateView.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getCreateView_VariableName(), theEcorePackage.getEString(), "variableName", "createdView", 0, 1, CreateView.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(createEdgeViewEClass, CreateEdgeView.class, "CreateEdgeView", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getCreateEdgeView_SourceExpression(), theDescriptionPackage_1.getInterpretedExpression(), "sourceExpression", null, 1, 1, CreateEdgeView.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getCreateEdgeView_TargetExpression(), theDescriptionPackage_1.getInterpretedExpression(), "targetExpression", null, 1, 1, CreateEdgeView.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(navigationEClass, Navigation.class, "Navigation", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getNavigation_CreateIfNotExistent(), theEcorePackage.getEBoolean(), "createIfNotExistent", null, 0, 1, Navigation.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getNavigation_DiagramDescription(), theDescriptionPackage.getDiagramDescription(), null, "diagramDescription", null, 1, 1, Navigation.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(diagramCreationDescriptionEClass, DiagramCreationDescription.class, "DiagramCreationDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDiagramCreationDescription_DiagramDescription(), theDescriptionPackage.getDiagramDescription(), null, "diagramDescription", null, 1, 1, DiagramCreationDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(diagramNavigationDescriptionEClass, DiagramNavigationDescription.class, "DiagramNavigationDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDiagramNavigationDescription_DiagramDescription(), theDescriptionPackage.getDiagramDescription(), null, "diagramDescription", null, 1, 1, DiagramNavigationDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(containerDropDescriptionEClass, ContainerDropDescription.class, "ContainerDropDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getContainerDropDescription_Mappings(), theDescriptionPackage.getDiagramElementMapping(), null, "mappings", null, 0, -1, ContainerDropDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getContainerDropDescription_OldContainer(), theToolPackage_1.getDropContainerVariable(), null, "oldContainer", null, 1, 1, ContainerDropDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getContainerDropDescription_NewContainer(), theToolPackage_1.getDropContainerVariable(), null, "newContainer", null, 1, 1, ContainerDropDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getContainerDropDescription_Element(), theToolPackage_1.getElementDropVariable(), null, "element", null, 1, 1, ContainerDropDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getContainerDropDescription_NewViewContainer(), theToolPackage_1.getContainerViewVariable(), null, "newViewContainer", null, 1, 1, ContainerDropDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getContainerDropDescription_InitialOperation(), theToolPackage_1.getInitialContainerDropOperation(), null, "initialOperation", null, 1, 1, ContainerDropDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getContainerDropDescription_DragSource(), theToolPackage_1.getDragSource(), "dragSource", null, 1, 1, ContainerDropDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getContainerDropDescription_MoveEdges(), theEcorePackage.getEBoolean(), "moveEdges", null, 1, 1, ContainerDropDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        addEOperation(containerDropDescriptionEClass, theDescriptionPackage.getDragAndDropTargetDescription(), "getContainers", 1, -1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        // Initialize enums and add enum literals
        initEEnum(reconnectionKindEEnum, ReconnectionKind.class, "ReconnectionKind"); //$NON-NLS-1$
        addEEnumLiteral(reconnectionKindEEnum, ReconnectionKind.RECONNECT_TARGET_LITERAL);
        addEEnumLiteral(reconnectionKindEEnum, ReconnectionKind.RECONNECT_SOURCE_LITERAL);
        addEEnumLiteral(reconnectionKindEEnum, ReconnectionKind.RECONNECT_BOTH_LITERAL);

        // Create annotations
        // http://www.eclipse.org/sirius/interpreted/expression/variables
        createVariablesAnnotations();
        // http://www.eclipse.org/sirius/interpreted/expression/returnType
        createReturnTypeAnnotations();
    }

    /**
     * Initializes the annotations for <b>http://www.eclipse.org/sirius/interpreted/expression/variables</b>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void createVariablesAnnotations() {
        String source = "http://www.eclipse.org/sirius/interpreted/expression/variables"; //$NON-NLS-1$
        addAnnotation(getNodeCreationDescription_Variable(), source, new String[] { "type", "ecore.EObject" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getNodeCreationDescription_ViewVariable(), source, new String[] { "type", "diagram.DDiagramElementContainer" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getEdgeCreationDescription_ConnectionStartPrecondition(), source, new String[] { "container", "ecore.EObject | the semantic element of diagram.", //$NON-NLS-1$ //$NON-NLS-2$
                "preSourceView", "diagram.EdgeTarget | (edge only) the source view of the current potential edge.", //$NON-NLS-1$ //$NON-NLS-2$
                "preSource", "ecore.EObject | (edge only) the semantic element of $preSourceView.", //$NON-NLS-1$ //$NON-NLS-2$
                "diagram", "diagram.DSemanticDiagram | the diagram of the current potential edge" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDeleteHookParameter_Value(), source, new String[] {});
        addAnnotation(getDirectEditLabel_InputLabelExpression(), source, new String[] { "diagram", "diagram.DSemanticDiagram | the current DSemanticDiagram.", //$NON-NLS-1$ //$NON-NLS-2$
                "view", "diagram.DDiagramElement | the current view for which the label is calculated." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getCreateView_ContainerViewExpression(), source, new String[] {});
        addAnnotation(getCreateEdgeView_SourceExpression(), source, new String[] {});
        addAnnotation(getCreateEdgeView_TargetExpression(), source, new String[] {});
    }

    /**
     * Initializes the annotations for <b>http://www.eclipse.org/sirius/interpreted/expression/returnType</b>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void createReturnTypeAnnotations() {
        String source = "http://www.eclipse.org/sirius/interpreted/expression/returnType"; //$NON-NLS-1$
        addAnnotation(getEdgeCreationDescription_ConnectionStartPrecondition(), source, new String[] { "returnType", "a boolean." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDeleteHookParameter_Value(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDirectEditLabel_InputLabelExpression(), source, new String[] { "returnType", "a string." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getCreateView_ContainerViewExpression(), source, new String[] { "returnType", "a view (DNode, DEdge, DDiagram -> any DSemanticDecorator)." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getCreateEdgeView_SourceExpression(), source, new String[] { "returnType", "an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getCreateEdgeView_TargetExpression(), source, new String[] { "returnType", "an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
    }

} // ToolPackageImpl
