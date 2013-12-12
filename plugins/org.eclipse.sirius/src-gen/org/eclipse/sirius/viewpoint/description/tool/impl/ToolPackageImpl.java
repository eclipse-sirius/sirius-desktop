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
package org.eclipse.sirius.viewpoint.description.tool.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.description.filter.FilterPackage;
import org.eclipse.sirius.diagram.description.filter.impl.FilterPackageImpl;
import org.eclipse.sirius.diagram.impl.DiagramPackageImpl;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.audit.AuditPackage;
import org.eclipse.sirius.viewpoint.description.audit.impl.AuditPackageImpl;
import org.eclipse.sirius.viewpoint.description.concern.ConcernPackage;
import org.eclipse.sirius.viewpoint.description.concern.impl.ConcernPackageImpl;
import org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl;
import org.eclipse.sirius.viewpoint.description.style.StylePackage;
import org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.AbstractVariable;
import org.eclipse.sirius.viewpoint.description.tool.AcceleoVariable;
import org.eclipse.sirius.viewpoint.description.tool.BehaviorTool;
import org.eclipse.sirius.viewpoint.description.tool.Case;
import org.eclipse.sirius.viewpoint.description.tool.ChangeContext;
import org.eclipse.sirius.viewpoint.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.viewpoint.description.tool.ContainerDropDescription;
import org.eclipse.sirius.viewpoint.description.tool.ContainerModelOperation;
import org.eclipse.sirius.viewpoint.description.tool.ContainerViewVariable;
import org.eclipse.sirius.viewpoint.description.tool.CreateEdgeView;
import org.eclipse.sirius.viewpoint.description.tool.CreateInstance;
import org.eclipse.sirius.viewpoint.description.tool.CreateView;
import org.eclipse.sirius.viewpoint.description.tool.Default;
import org.eclipse.sirius.viewpoint.description.tool.DeleteElementDescription;
import org.eclipse.sirius.viewpoint.description.tool.DeleteHook;
import org.eclipse.sirius.viewpoint.description.tool.DeleteHookParameter;
import org.eclipse.sirius.viewpoint.description.tool.DeleteView;
import org.eclipse.sirius.viewpoint.description.tool.DiagramCreationDescription;
import org.eclipse.sirius.viewpoint.description.tool.DiagramNavigationDescription;
import org.eclipse.sirius.viewpoint.description.tool.DialogVariable;
import org.eclipse.sirius.viewpoint.description.tool.DirectEditLabel;
import org.eclipse.sirius.viewpoint.description.tool.DoubleClickDescription;
import org.eclipse.sirius.viewpoint.description.tool.DragSource;
import org.eclipse.sirius.viewpoint.description.tool.DropContainerVariable;
import org.eclipse.sirius.viewpoint.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.viewpoint.description.tool.EditMaskVariables;
import org.eclipse.sirius.viewpoint.description.tool.ElementDeleteVariable;
import org.eclipse.sirius.viewpoint.description.tool.ElementDoubleClickVariable;
import org.eclipse.sirius.viewpoint.description.tool.ElementDropVariable;
import org.eclipse.sirius.viewpoint.description.tool.ElementSelectVariable;
import org.eclipse.sirius.viewpoint.description.tool.ElementVariable;
import org.eclipse.sirius.viewpoint.description.tool.ElementViewVariable;
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaAction;
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaActionCall;
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaActionParameter;
import org.eclipse.sirius.viewpoint.description.tool.FeatureChangeListener;
import org.eclipse.sirius.viewpoint.description.tool.For;
import org.eclipse.sirius.viewpoint.description.tool.If;
import org.eclipse.sirius.viewpoint.description.tool.InitEdgeCreationOperation;
import org.eclipse.sirius.viewpoint.description.tool.InitialContainerDropOperation;
import org.eclipse.sirius.viewpoint.description.tool.InitialNodeCreationOperation;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;
import org.eclipse.sirius.viewpoint.description.tool.MappingBasedToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.MenuItemDescription;
import org.eclipse.sirius.viewpoint.description.tool.MenuItemDescriptionReference;
import org.eclipse.sirius.viewpoint.description.tool.MenuItemOrRef;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;
import org.eclipse.sirius.viewpoint.description.tool.MoveElement;
import org.eclipse.sirius.viewpoint.description.tool.NameVariable;
import org.eclipse.sirius.viewpoint.description.tool.Navigation;
import org.eclipse.sirius.viewpoint.description.tool.NodeCreationDescription;
import org.eclipse.sirius.viewpoint.description.tool.NodeCreationVariable;
import org.eclipse.sirius.viewpoint.description.tool.OperationAction;
import org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription;
import org.eclipse.sirius.viewpoint.description.tool.PasteDescription;
import org.eclipse.sirius.viewpoint.description.tool.PopupMenu;
import org.eclipse.sirius.viewpoint.description.tool.ReconnectEdgeDescription;
import org.eclipse.sirius.viewpoint.description.tool.ReconnectionKind;
import org.eclipse.sirius.viewpoint.description.tool.RemoveElement;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription;
import org.eclipse.sirius.viewpoint.description.tool.RequestDescription;
import org.eclipse.sirius.viewpoint.description.tool.SelectContainerVariable;
import org.eclipse.sirius.viewpoint.description.tool.SelectModelElementVariable;
import org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription;
import org.eclipse.sirius.viewpoint.description.tool.SetObject;
import org.eclipse.sirius.viewpoint.description.tool.SetValue;
import org.eclipse.sirius.viewpoint.description.tool.SourceEdgeCreationVariable;
import org.eclipse.sirius.viewpoint.description.tool.SourceEdgeViewCreationVariable;
import org.eclipse.sirius.viewpoint.description.tool.SubVariable;
import org.eclipse.sirius.viewpoint.description.tool.Switch;
import org.eclipse.sirius.viewpoint.description.tool.SwitchChild;
import org.eclipse.sirius.viewpoint.description.tool.TargetEdgeCreationVariable;
import org.eclipse.sirius.viewpoint.description.tool.TargetEdgeViewCreationVariable;
import org.eclipse.sirius.viewpoint.description.tool.ToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolEntry;
import org.eclipse.sirius.viewpoint.description.tool.ToolFactory;
import org.eclipse.sirius.viewpoint.description.tool.ToolFilterDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolGroup;
import org.eclipse.sirius.viewpoint.description.tool.ToolGroupExtension;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.tool.ToolSection;
import org.eclipse.sirius.viewpoint.description.tool.Unset;
import org.eclipse.sirius.viewpoint.description.tool.VariableContainer;
import org.eclipse.sirius.viewpoint.description.validation.ValidationPackage;
import org.eclipse.sirius.viewpoint.description.validation.impl.ValidationPackageImpl;
import org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
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
    private EClass toolEntryEClass = null;

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
    private EClass abstractToolDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass mappingBasedToolDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass toolDescriptionEClass = null;

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
    private EClass containerDropDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass pasteDescriptionEClass = null;

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
    private EClass selectionWizardDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass paneBasedSelectionWizardDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass representationCreationDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass representationNavigationDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass menuItemOrRefEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass menuItemDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass menuItemDescriptionReferenceEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass operationActionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass externalJavaActionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass externalJavaActionCallEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass popupMenuEClass = null;

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
    private EClass abstractVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass variableContainerEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass acceleoVariableEClass = null;

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
    private EClass dialogVariableEClass = null;

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
    private EClass elementDropVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass elementSelectVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass elementVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass elementViewVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass elementDeleteVariableEClass = null;

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
    private EClass dropContainerVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass selectContainerVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass containerViewVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass selectModelElementVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass editMaskVariablesEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass containerModelOperationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass modelOperationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass initialNodeCreationOperationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass initialOperationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass initEdgeCreationOperationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass initialContainerDropOperationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass createInstanceEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass changeContextEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass setValueEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass setObjectEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass unsetEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass moveElementEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass removeElementEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass forEClass = null;

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
    private EClass ifEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass deleteViewEClass = null;

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
    private EClass nameVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass externalJavaActionParameterEClass = null;

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
    private EClass toolFilterDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass featureChangeListenerEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass caseEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass switchChildEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass defaultEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass switchEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum reconnectionKindEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum dragSourceEEnum = null;

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
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private ToolPackageImpl() {
        super(eNS_URI, ToolFactory.eINSTANCE);
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
     * This method is used to initialize {@link ToolPackage#eINSTANCE} when that
     * field is accessed. Clients should not invoke it directly. Instead, they
     * should simply access that field to obtain the package. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static ToolPackage init() {
        if (isInited)
            return (ToolPackage) EPackage.Registry.INSTANCE.getEPackage(ToolPackage.eNS_URI);

        // Obtain or create and register package
        ToolPackageImpl theToolPackage = (ToolPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ToolPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ToolPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        EcorePackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        ViewpointPackageImpl theViewpointPackage = (ViewpointPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(ViewpointPackage.eNS_URI) instanceof ViewpointPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(ViewpointPackage.eNS_URI) : ViewpointPackage.eINSTANCE);
        DescriptionPackageImpl theDescriptionPackage = (DescriptionPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI) instanceof DescriptionPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(DescriptionPackage.eNS_URI) : DescriptionPackage.eINSTANCE);
        StylePackageImpl theStylePackage = (StylePackageImpl) (EPackage.Registry.INSTANCE.getEPackage(StylePackage.eNS_URI) instanceof StylePackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(StylePackage.eNS_URI) : StylePackage.eINSTANCE);
        ValidationPackageImpl theValidationPackage = (ValidationPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(ValidationPackage.eNS_URI) instanceof ValidationPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(ValidationPackage.eNS_URI) : ValidationPackage.eINSTANCE);
        AuditPackageImpl theAuditPackage = (AuditPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(AuditPackage.eNS_URI) instanceof AuditPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(AuditPackage.eNS_URI) : AuditPackage.eINSTANCE);
        ConcernPackageImpl theConcernPackage = (ConcernPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(ConcernPackage.eNS_URI) instanceof ConcernPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(ConcernPackage.eNS_URI) : ConcernPackage.eINSTANCE);
        DiagramPackageImpl theDiagramPackage = (DiagramPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(DiagramPackage.eNS_URI) instanceof DiagramPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(DiagramPackage.eNS_URI) : DiagramPackage.eINSTANCE);
        org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl theDescriptionPackage_1 = (org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl) (EPackage.Registry.INSTANCE
                .getEPackage(org.eclipse.sirius.diagram.description.DescriptionPackage.eNS_URI) instanceof org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(org.eclipse.sirius.diagram.description.DescriptionPackage.eNS_URI) : org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE);
        FilterPackageImpl theFilterPackage = (FilterPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(FilterPackage.eNS_URI) instanceof FilterPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(FilterPackage.eNS_URI) : FilterPackage.eINSTANCE);

        // Create package meta-data objects
        theToolPackage.createPackageContents();
        theViewpointPackage.createPackageContents();
        theDescriptionPackage.createPackageContents();
        theStylePackage.createPackageContents();
        theValidationPackage.createPackageContents();
        theAuditPackage.createPackageContents();
        theConcernPackage.createPackageContents();
        theDiagramPackage.createPackageContents();
        theDescriptionPackage_1.createPackageContents();
        theFilterPackage.createPackageContents();

        // Initialize created meta-data
        theToolPackage.initializePackageContents();
        theViewpointPackage.initializePackageContents();
        theDescriptionPackage.initializePackageContents();
        theStylePackage.initializePackageContents();
        theValidationPackage.initializePackageContents();
        theAuditPackage.initializePackageContents();
        theConcernPackage.initializePackageContents();
        theDiagramPackage.initializePackageContents();
        theDescriptionPackage_1.initializePackageContents();
        theFilterPackage.initializePackageContents();

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
    public EClass getToolSection() {
        return toolSectionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getToolSection_Icon() {
        return (EAttribute) toolSectionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getToolSection_OwnedTools() {
        return (EReference) toolSectionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getToolSection_SubSections() {
        return (EReference) toolSectionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getToolSection_PopupMenus() {
        return (EReference) toolSectionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getToolSection_ReusedTools() {
        return (EReference) toolSectionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getToolSection_GroupExtensions() {
        return (EReference) toolSectionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getToolEntry() {
        return toolEntryEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getToolGroup() {
        return toolGroupEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getToolGroup_Tools() {
        return (EReference) toolGroupEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getToolGroupExtension() {
        return toolGroupExtensionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getToolGroupExtension_Group() {
        return (EReference) toolGroupExtensionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getToolGroupExtension_Tools() {
        return (EReference) toolGroupExtensionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getAbstractToolDescription() {
        return abstractToolDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getAbstractToolDescription_Precondition() {
        return (EAttribute) abstractToolDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getAbstractToolDescription_ForceRefresh() {
        return (EAttribute) abstractToolDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getAbstractToolDescription_Filters() {
        return (EReference) abstractToolDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getMappingBasedToolDescription() {
        return mappingBasedToolDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getToolDescription() {
        return toolDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getToolDescription_IconPath() {
        return (EAttribute) toolDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getToolDescription_Element() {
        return (EReference) toolDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getToolDescription_ElementView() {
        return (EReference) toolDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getToolDescription_InitialOperation() {
        return (EReference) toolDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getNodeCreationDescription() {
        return nodeCreationDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getNodeCreationDescription_NodeMappings() {
        return (EReference) nodeCreationDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getNodeCreationDescription_Variable() {
        return (EReference) nodeCreationDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getNodeCreationDescription_ViewVariable() {
        return (EReference) nodeCreationDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getNodeCreationDescription_InitialOperation() {
        return (EReference) nodeCreationDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getNodeCreationDescription_IconPath() {
        return (EAttribute) nodeCreationDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getNodeCreationDescription_ExtraMappings() {
        return (EReference) nodeCreationDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getEdgeCreationDescription() {
        return edgeCreationDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEdgeCreationDescription_EdgeMappings() {
        return (EReference) edgeCreationDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEdgeCreationDescription_SourceVariable() {
        return (EReference) edgeCreationDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEdgeCreationDescription_TargetVariable() {
        return (EReference) edgeCreationDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEdgeCreationDescription_SourceViewVariable() {
        return (EReference) edgeCreationDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEdgeCreationDescription_TargetViewVariable() {
        return (EReference) edgeCreationDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEdgeCreationDescription_InitialOperation() {
        return (EReference) edgeCreationDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getEdgeCreationDescription_IconPath() {
        return (EAttribute) edgeCreationDescriptionEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEdgeCreationDescription_ExtraSourceMappings() {
        return (EReference) edgeCreationDescriptionEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEdgeCreationDescription_ExtraTargetMappings() {
        return (EReference) edgeCreationDescriptionEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getEdgeCreationDescription_ConnectionStartPrecondition() {
        return (EAttribute) edgeCreationDescriptionEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getContainerCreationDescription() {
        return containerCreationDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getContainerCreationDescription_ContainerMappings() {
        return (EReference) containerCreationDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getContainerCreationDescription_Variable() {
        return (EReference) containerCreationDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getContainerCreationDescription_ViewVariable() {
        return (EReference) containerCreationDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getContainerCreationDescription_InitialOperation() {
        return (EReference) containerCreationDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getContainerCreationDescription_IconPath() {
        return (EAttribute) containerCreationDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getContainerCreationDescription_ExtraMappings() {
        return (EReference) containerCreationDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getContainerDropDescription() {
        return containerDropDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getContainerDropDescription_Mappings() {
        return (EReference) containerDropDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getContainerDropDescription_OldContainer() {
        return (EReference) containerDropDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getContainerDropDescription_NewContainer() {
        return (EReference) containerDropDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getContainerDropDescription_Element() {
        return (EReference) containerDropDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getContainerDropDescription_NewViewContainer() {
        return (EReference) containerDropDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getContainerDropDescription_InitialOperation() {
        return (EReference) containerDropDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getContainerDropDescription_DragSource() {
        return (EAttribute) containerDropDescriptionEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getContainerDropDescription_MoveEdges() {
        return (EAttribute) containerDropDescriptionEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getPasteDescription() {
        return pasteDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getPasteDescription_Container() {
        return (EReference) pasteDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getPasteDescription_ContainerView() {
        return (EReference) pasteDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getPasteDescription_CopiedView() {
        return (EReference) pasteDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getPasteDescription_CopiedElement() {
        return (EReference) pasteDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getPasteDescription_InitialOperation() {
        return (EReference) pasteDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDeleteElementDescription() {
        return deleteElementDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDeleteElementDescription_Element() {
        return (EReference) deleteElementDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDeleteElementDescription_ElementView() {
        return (EReference) deleteElementDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDeleteElementDescription_ContainerView() {
        return (EReference) deleteElementDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDeleteElementDescription_InitialOperation() {
        return (EReference) deleteElementDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDeleteElementDescription_Hook() {
        return (EReference) deleteElementDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDoubleClickDescription() {
        return doubleClickDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDoubleClickDescription_Mappings() {
        return (EReference) doubleClickDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDoubleClickDescription_Element() {
        return (EReference) doubleClickDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDoubleClickDescription_ElementView() {
        return (EReference) doubleClickDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDoubleClickDescription_InitialOperation() {
        return (EReference) doubleClickDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDeleteHook() {
        return deleteHookEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDeleteHook_Id() {
        return (EAttribute) deleteHookEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDeleteHook_Parameters() {
        return (EReference) deleteHookEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDeleteHookParameter() {
        return deleteHookParameterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDeleteHookParameter_Name() {
        return (EAttribute) deleteHookParameterEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDeleteHookParameter_Value() {
        return (EAttribute) deleteHookParameterEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getReconnectEdgeDescription() {
        return reconnectEdgeDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getReconnectEdgeDescription_ReconnectionKind() {
        return (EAttribute) reconnectEdgeDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getReconnectEdgeDescription_Source() {
        return (EReference) reconnectEdgeDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getReconnectEdgeDescription_Target() {
        return (EReference) reconnectEdgeDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getReconnectEdgeDescription_SourceView() {
        return (EReference) reconnectEdgeDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getReconnectEdgeDescription_TargetView() {
        return (EReference) reconnectEdgeDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getReconnectEdgeDescription_Element() {
        return (EReference) reconnectEdgeDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getReconnectEdgeDescription_InitialOperation() {
        return (EReference) reconnectEdgeDescriptionEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getReconnectEdgeDescription_EdgeView() {
        return (EReference) reconnectEdgeDescriptionEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getRequestDescription() {
        return requestDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getRequestDescription_Type() {
        return (EAttribute) requestDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getSelectionWizardDescription() {
        return selectionWizardDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getSelectionWizardDescription_Element() {
        return (EReference) selectionWizardDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getSelectionWizardDescription_ContainerView() {
        return (EReference) selectionWizardDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getSelectionWizardDescription_Container() {
        return (EReference) selectionWizardDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getSelectionWizardDescription_InitialOperation() {
        return (EReference) selectionWizardDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getSelectionWizardDescription_IconPath() {
        return (EAttribute) selectionWizardDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getSelectionWizardDescription_WindowTitle() {
        return (EAttribute) selectionWizardDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getSelectionWizardDescription_WindowImagePath() {
        return (EAttribute) selectionWizardDescriptionEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getPaneBasedSelectionWizardDescription() {
        return paneBasedSelectionWizardDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getPaneBasedSelectionWizardDescription_Element() {
        return (EReference) paneBasedSelectionWizardDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getPaneBasedSelectionWizardDescription_ContainerView() {
        return (EReference) paneBasedSelectionWizardDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getPaneBasedSelectionWizardDescription_Container() {
        return (EReference) paneBasedSelectionWizardDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getPaneBasedSelectionWizardDescription_InitialOperation() {
        return (EReference) paneBasedSelectionWizardDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getPaneBasedSelectionWizardDescription_IconPath() {
        return (EAttribute) paneBasedSelectionWizardDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getPaneBasedSelectionWizardDescription_WindowTitle() {
        return (EAttribute) paneBasedSelectionWizardDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getPaneBasedSelectionWizardDescription_WindowImagePath() {
        return (EAttribute) paneBasedSelectionWizardDescriptionEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getPaneBasedSelectionWizardDescription_Message() {
        return (EAttribute) paneBasedSelectionWizardDescriptionEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getPaneBasedSelectionWizardDescription_ChoiceOfValuesMessage() {
        return (EAttribute) paneBasedSelectionWizardDescriptionEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getPaneBasedSelectionWizardDescription_CandidatesExpression() {
        return (EAttribute) paneBasedSelectionWizardDescriptionEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getPaneBasedSelectionWizardDescription_Tree() {
        return (EAttribute) paneBasedSelectionWizardDescriptionEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getPaneBasedSelectionWizardDescription_RootExpression() {
        return (EAttribute) paneBasedSelectionWizardDescriptionEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getPaneBasedSelectionWizardDescription_ChildrenExpression() {
        return (EAttribute) paneBasedSelectionWizardDescriptionEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getPaneBasedSelectionWizardDescription_SelectedValuesMessage() {
        return (EAttribute) paneBasedSelectionWizardDescriptionEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getPaneBasedSelectionWizardDescription_PreSelectedCandidatesExpression() {
        return (EAttribute) paneBasedSelectionWizardDescriptionEClass.getEStructuralFeatures().get(14);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getRepresentationCreationDescription() {
        return representationCreationDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getRepresentationCreationDescription_TitleExpression() {
        return (EAttribute) representationCreationDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getRepresentationCreationDescription_BrowseExpression() {
        return (EAttribute) representationCreationDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getRepresentationCreationDescription_RepresentationDescription() {
        return (EReference) representationCreationDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getRepresentationCreationDescription_InitialOperation() {
        return (EReference) representationCreationDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getRepresentationCreationDescription_ContainerViewVariable() {
        return (EReference) representationCreationDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getRepresentationCreationDescription_RepresentationNameVariable() {
        return (EReference) representationCreationDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getRepresentationNavigationDescription() {
        return representationNavigationDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getRepresentationNavigationDescription_BrowseExpression() {
        return (EAttribute) representationNavigationDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getRepresentationNavigationDescription_NavigationNameExpression() {
        return (EAttribute) representationNavigationDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getRepresentationNavigationDescription_RepresentationDescription() {
        return (EReference) representationNavigationDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getRepresentationNavigationDescription_ContainerViewVariable() {
        return (EReference) representationNavigationDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getRepresentationNavigationDescription_ContainerVariable() {
        return (EReference) representationNavigationDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getRepresentationNavigationDescription_RepresentationNameVariable() {
        return (EReference) representationNavigationDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getMenuItemOrRef() {
        return menuItemOrRefEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getMenuItemDescription() {
        return menuItemDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getMenuItemDescription_Icon() {
        return (EAttribute) menuItemDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getMenuItemDescriptionReference() {
        return menuItemDescriptionReferenceEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getMenuItemDescriptionReference_Item() {
        return (EReference) menuItemDescriptionReferenceEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getOperationAction() {
        return operationActionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getOperationAction_View() {
        return (EReference) operationActionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getOperationAction_InitialOperation() {
        return (EReference) operationActionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getExternalJavaAction() {
        return externalJavaActionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getExternalJavaAction_Id() {
        return (EAttribute) externalJavaActionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getExternalJavaAction_Parameters() {
        return (EReference) externalJavaActionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getExternalJavaActionCall() {
        return externalJavaActionCallEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getExternalJavaActionCall_Action() {
        return (EReference) externalJavaActionCallEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getPopupMenu() {
        return popupMenuEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getPopupMenu_MenuItemDescription() {
        return (EReference) popupMenuEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDirectEditLabel() {
        return directEditLabelEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDirectEditLabel_Mask() {
        return (EReference) directEditLabelEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDirectEditLabel_InitialOperation() {
        return (EReference) directEditLabelEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDirectEditLabel_InputLabelExpression() {
        return (EAttribute) directEditLabelEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getBehaviorTool() {
        return behaviorToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getBehaviorTool_DomainClass() {
        return (EAttribute) behaviorToolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getBehaviorTool_InitialOperation() {
        return (EReference) behaviorToolEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getAbstractVariable() {
        return abstractVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getAbstractVariable_Name() {
        return (EAttribute) abstractVariableEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getVariableContainer() {
        return variableContainerEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getVariableContainer_SubVariables() {
        return (EReference) variableContainerEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getAcceleoVariable() {
        return acceleoVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getAcceleoVariable_ComputationExpression() {
        return (EAttribute) acceleoVariableEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getSubVariable() {
        return subVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDialogVariable() {
        return dialogVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDialogVariable_DialogPrompt() {
        return (EAttribute) dialogVariableEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getSourceEdgeCreationVariable() {
        return sourceEdgeCreationVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getSourceEdgeViewCreationVariable() {
        return sourceEdgeViewCreationVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTargetEdgeCreationVariable() {
        return targetEdgeCreationVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTargetEdgeViewCreationVariable() {
        return targetEdgeViewCreationVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getElementDropVariable() {
        return elementDropVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getElementSelectVariable() {
        return elementSelectVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getElementVariable() {
        return elementVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getElementViewVariable() {
        return elementViewVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getElementDeleteVariable() {
        return elementDeleteVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getElementDoubleClickVariable() {
        return elementDoubleClickVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getNodeCreationVariable() {
        return nodeCreationVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDropContainerVariable() {
        return dropContainerVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getSelectContainerVariable() {
        return selectContainerVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getContainerViewVariable() {
        return containerViewVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getSelectModelElementVariable() {
        return selectModelElementVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getEditMaskVariables() {
        return editMaskVariablesEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getEditMaskVariables_Mask() {
        return (EAttribute) editMaskVariablesEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getContainerModelOperation() {
        return containerModelOperationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getContainerModelOperation_SubModelOperations() {
        return (EReference) containerModelOperationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getModelOperation() {
        return modelOperationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getInitialNodeCreationOperation() {
        return initialNodeCreationOperationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getInitialNodeCreationOperation_FirstModelOperations() {
        return (EReference) initialNodeCreationOperationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getInitialOperation() {
        return initialOperationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getInitialOperation_FirstModelOperations() {
        return (EReference) initialOperationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getInitEdgeCreationOperation() {
        return initEdgeCreationOperationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getInitEdgeCreationOperation_FirstModelOperations() {
        return (EReference) initEdgeCreationOperationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getInitialContainerDropOperation() {
        return initialContainerDropOperationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getInitialContainerDropOperation_FirstModelOperations() {
        return (EReference) initialContainerDropOperationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getCreateInstance() {
        return createInstanceEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getCreateInstance_TypeName() {
        return (EAttribute) createInstanceEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getCreateInstance_ReferenceName() {
        return (EAttribute) createInstanceEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getCreateInstance_VariableName() {
        return (EAttribute) createInstanceEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getChangeContext() {
        return changeContextEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getChangeContext_BrowseExpression() {
        return (EAttribute) changeContextEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getSetValue() {
        return setValueEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getSetValue_FeatureName() {
        return (EAttribute) setValueEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getSetValue_ValueExpression() {
        return (EAttribute) setValueEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getSetObject() {
        return setObjectEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getSetObject_FeatureName() {
        return (EAttribute) setObjectEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getSetObject_Object() {
        return (EReference) setObjectEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getUnset() {
        return unsetEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getUnset_FeatureName() {
        return (EAttribute) unsetEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getUnset_ElementExpression() {
        return (EAttribute) unsetEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getMoveElement() {
        return moveElementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getMoveElement_NewContainerExpression() {
        return (EAttribute) moveElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getMoveElement_FeatureName() {
        return (EAttribute) moveElementEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getRemoveElement() {
        return removeElementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getFor() {
        return forEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getFor_Expression() {
        return (EAttribute) forEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getFor_IteratorName() {
        return (EAttribute) forEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getCreateView() {
        return createViewEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getCreateView_Mapping() {
        return (EReference) createViewEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getCreateView_ContainerViewExpression() {
        return (EAttribute) createViewEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getCreateView_VariableName() {
        return (EAttribute) createViewEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getCreateEdgeView() {
        return createEdgeViewEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getCreateEdgeView_SourceExpression() {
        return (EAttribute) createEdgeViewEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getCreateEdgeView_TargetExpression() {
        return (EAttribute) createEdgeViewEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getIf() {
        return ifEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getIf_ConditionExpression() {
        return (EAttribute) ifEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDeleteView() {
        return deleteViewEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getNavigation() {
        return navigationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getNavigation_CreateIfNotExistent() {
        return (EAttribute) navigationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getNavigation_DiagramDescription() {
        return (EReference) navigationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getNameVariable() {
        return nameVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getExternalJavaActionParameter() {
        return externalJavaActionParameterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getExternalJavaActionParameter_Name() {
        return (EAttribute) externalJavaActionParameterEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getExternalJavaActionParameter_Value() {
        return (EAttribute) externalJavaActionParameterEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDiagramCreationDescription() {
        return diagramCreationDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDiagramCreationDescription_DiagramDescription() {
        return (EReference) diagramCreationDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDiagramNavigationDescription() {
        return diagramNavigationDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDiagramNavigationDescription_DiagramDescription() {
        return (EReference) diagramNavigationDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getToolFilterDescription() {
        return toolFilterDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getToolFilterDescription_Precondition() {
        return (EAttribute) toolFilterDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getToolFilterDescription_ElementsToListen() {
        return (EAttribute) toolFilterDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getToolFilterDescription_Listeners() {
        return (EReference) toolFilterDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getFeatureChangeListener() {
        return featureChangeListenerEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getFeatureChangeListener_DomainClass() {
        return (EAttribute) featureChangeListenerEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getFeatureChangeListener_FeatureName() {
        return (EAttribute) featureChangeListenerEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getCase() {
        return caseEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getCase_ConditionExpression() {
        return (EAttribute) caseEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getSwitchChild() {
        return switchChildEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getSwitchChild_SubModelOperations() {
        return (EReference) switchChildEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDefault() {
        return defaultEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getSwitch() {
        return switchEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getSwitch_Cases() {
        return (EReference) switchEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getSwitch_Default() {
        return (EReference) switchEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getReconnectionKind() {
        return reconnectionKindEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getDragSource() {
        return dragSourceEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
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
        toolSectionEClass = createEClass(TOOL_SECTION);
        createEAttribute(toolSectionEClass, TOOL_SECTION__ICON);
        createEReference(toolSectionEClass, TOOL_SECTION__OWNED_TOOLS);
        createEReference(toolSectionEClass, TOOL_SECTION__SUB_SECTIONS);
        createEReference(toolSectionEClass, TOOL_SECTION__POPUP_MENUS);
        createEReference(toolSectionEClass, TOOL_SECTION__REUSED_TOOLS);
        createEReference(toolSectionEClass, TOOL_SECTION__GROUP_EXTENSIONS);

        toolEntryEClass = createEClass(TOOL_ENTRY);

        toolGroupEClass = createEClass(TOOL_GROUP);
        createEReference(toolGroupEClass, TOOL_GROUP__TOOLS);

        toolGroupExtensionEClass = createEClass(TOOL_GROUP_EXTENSION);
        createEReference(toolGroupExtensionEClass, TOOL_GROUP_EXTENSION__GROUP);
        createEReference(toolGroupExtensionEClass, TOOL_GROUP_EXTENSION__TOOLS);

        abstractToolDescriptionEClass = createEClass(ABSTRACT_TOOL_DESCRIPTION);
        createEAttribute(abstractToolDescriptionEClass, ABSTRACT_TOOL_DESCRIPTION__PRECONDITION);
        createEAttribute(abstractToolDescriptionEClass, ABSTRACT_TOOL_DESCRIPTION__FORCE_REFRESH);
        createEReference(abstractToolDescriptionEClass, ABSTRACT_TOOL_DESCRIPTION__FILTERS);

        mappingBasedToolDescriptionEClass = createEClass(MAPPING_BASED_TOOL_DESCRIPTION);

        toolDescriptionEClass = createEClass(TOOL_DESCRIPTION);
        createEAttribute(toolDescriptionEClass, TOOL_DESCRIPTION__ICON_PATH);
        createEReference(toolDescriptionEClass, TOOL_DESCRIPTION__ELEMENT);
        createEReference(toolDescriptionEClass, TOOL_DESCRIPTION__ELEMENT_VIEW);
        createEReference(toolDescriptionEClass, TOOL_DESCRIPTION__INITIAL_OPERATION);

        nodeCreationDescriptionEClass = createEClass(NODE_CREATION_DESCRIPTION);
        createEReference(nodeCreationDescriptionEClass, NODE_CREATION_DESCRIPTION__NODE_MAPPINGS);
        createEReference(nodeCreationDescriptionEClass, NODE_CREATION_DESCRIPTION__VARIABLE);
        createEReference(nodeCreationDescriptionEClass, NODE_CREATION_DESCRIPTION__VIEW_VARIABLE);
        createEReference(nodeCreationDescriptionEClass, NODE_CREATION_DESCRIPTION__INITIAL_OPERATION);
        createEAttribute(nodeCreationDescriptionEClass, NODE_CREATION_DESCRIPTION__ICON_PATH);
        createEReference(nodeCreationDescriptionEClass, NODE_CREATION_DESCRIPTION__EXTRA_MAPPINGS);

        edgeCreationDescriptionEClass = createEClass(EDGE_CREATION_DESCRIPTION);
        createEReference(edgeCreationDescriptionEClass, EDGE_CREATION_DESCRIPTION__EDGE_MAPPINGS);
        createEReference(edgeCreationDescriptionEClass, EDGE_CREATION_DESCRIPTION__SOURCE_VARIABLE);
        createEReference(edgeCreationDescriptionEClass, EDGE_CREATION_DESCRIPTION__TARGET_VARIABLE);
        createEReference(edgeCreationDescriptionEClass, EDGE_CREATION_DESCRIPTION__SOURCE_VIEW_VARIABLE);
        createEReference(edgeCreationDescriptionEClass, EDGE_CREATION_DESCRIPTION__TARGET_VIEW_VARIABLE);
        createEReference(edgeCreationDescriptionEClass, EDGE_CREATION_DESCRIPTION__INITIAL_OPERATION);
        createEAttribute(edgeCreationDescriptionEClass, EDGE_CREATION_DESCRIPTION__ICON_PATH);
        createEReference(edgeCreationDescriptionEClass, EDGE_CREATION_DESCRIPTION__EXTRA_SOURCE_MAPPINGS);
        createEReference(edgeCreationDescriptionEClass, EDGE_CREATION_DESCRIPTION__EXTRA_TARGET_MAPPINGS);
        createEAttribute(edgeCreationDescriptionEClass, EDGE_CREATION_DESCRIPTION__CONNECTION_START_PRECONDITION);

        containerCreationDescriptionEClass = createEClass(CONTAINER_CREATION_DESCRIPTION);
        createEReference(containerCreationDescriptionEClass, CONTAINER_CREATION_DESCRIPTION__CONTAINER_MAPPINGS);
        createEReference(containerCreationDescriptionEClass, CONTAINER_CREATION_DESCRIPTION__VARIABLE);
        createEReference(containerCreationDescriptionEClass, CONTAINER_CREATION_DESCRIPTION__VIEW_VARIABLE);
        createEReference(containerCreationDescriptionEClass, CONTAINER_CREATION_DESCRIPTION__INITIAL_OPERATION);
        createEAttribute(containerCreationDescriptionEClass, CONTAINER_CREATION_DESCRIPTION__ICON_PATH);
        createEReference(containerCreationDescriptionEClass, CONTAINER_CREATION_DESCRIPTION__EXTRA_MAPPINGS);

        containerDropDescriptionEClass = createEClass(CONTAINER_DROP_DESCRIPTION);
        createEReference(containerDropDescriptionEClass, CONTAINER_DROP_DESCRIPTION__MAPPINGS);
        createEReference(containerDropDescriptionEClass, CONTAINER_DROP_DESCRIPTION__OLD_CONTAINER);
        createEReference(containerDropDescriptionEClass, CONTAINER_DROP_DESCRIPTION__NEW_CONTAINER);
        createEReference(containerDropDescriptionEClass, CONTAINER_DROP_DESCRIPTION__ELEMENT);
        createEReference(containerDropDescriptionEClass, CONTAINER_DROP_DESCRIPTION__NEW_VIEW_CONTAINER);
        createEReference(containerDropDescriptionEClass, CONTAINER_DROP_DESCRIPTION__INITIAL_OPERATION);
        createEAttribute(containerDropDescriptionEClass, CONTAINER_DROP_DESCRIPTION__DRAG_SOURCE);
        createEAttribute(containerDropDescriptionEClass, CONTAINER_DROP_DESCRIPTION__MOVE_EDGES);

        pasteDescriptionEClass = createEClass(PASTE_DESCRIPTION);
        createEReference(pasteDescriptionEClass, PASTE_DESCRIPTION__CONTAINER);
        createEReference(pasteDescriptionEClass, PASTE_DESCRIPTION__CONTAINER_VIEW);
        createEReference(pasteDescriptionEClass, PASTE_DESCRIPTION__COPIED_VIEW);
        createEReference(pasteDescriptionEClass, PASTE_DESCRIPTION__COPIED_ELEMENT);
        createEReference(pasteDescriptionEClass, PASTE_DESCRIPTION__INITIAL_OPERATION);

        deleteElementDescriptionEClass = createEClass(DELETE_ELEMENT_DESCRIPTION);
        createEReference(deleteElementDescriptionEClass, DELETE_ELEMENT_DESCRIPTION__ELEMENT);
        createEReference(deleteElementDescriptionEClass, DELETE_ELEMENT_DESCRIPTION__ELEMENT_VIEW);
        createEReference(deleteElementDescriptionEClass, DELETE_ELEMENT_DESCRIPTION__CONTAINER_VIEW);
        createEReference(deleteElementDescriptionEClass, DELETE_ELEMENT_DESCRIPTION__INITIAL_OPERATION);
        createEReference(deleteElementDescriptionEClass, DELETE_ELEMENT_DESCRIPTION__HOOK);

        doubleClickDescriptionEClass = createEClass(DOUBLE_CLICK_DESCRIPTION);
        createEReference(doubleClickDescriptionEClass, DOUBLE_CLICK_DESCRIPTION__MAPPINGS);
        createEReference(doubleClickDescriptionEClass, DOUBLE_CLICK_DESCRIPTION__ELEMENT);
        createEReference(doubleClickDescriptionEClass, DOUBLE_CLICK_DESCRIPTION__ELEMENT_VIEW);
        createEReference(doubleClickDescriptionEClass, DOUBLE_CLICK_DESCRIPTION__INITIAL_OPERATION);

        deleteHookEClass = createEClass(DELETE_HOOK);
        createEAttribute(deleteHookEClass, DELETE_HOOK__ID);
        createEReference(deleteHookEClass, DELETE_HOOK__PARAMETERS);

        deleteHookParameterEClass = createEClass(DELETE_HOOK_PARAMETER);
        createEAttribute(deleteHookParameterEClass, DELETE_HOOK_PARAMETER__NAME);
        createEAttribute(deleteHookParameterEClass, DELETE_HOOK_PARAMETER__VALUE);

        reconnectEdgeDescriptionEClass = createEClass(RECONNECT_EDGE_DESCRIPTION);
        createEAttribute(reconnectEdgeDescriptionEClass, RECONNECT_EDGE_DESCRIPTION__RECONNECTION_KIND);
        createEReference(reconnectEdgeDescriptionEClass, RECONNECT_EDGE_DESCRIPTION__SOURCE);
        createEReference(reconnectEdgeDescriptionEClass, RECONNECT_EDGE_DESCRIPTION__TARGET);
        createEReference(reconnectEdgeDescriptionEClass, RECONNECT_EDGE_DESCRIPTION__SOURCE_VIEW);
        createEReference(reconnectEdgeDescriptionEClass, RECONNECT_EDGE_DESCRIPTION__TARGET_VIEW);
        createEReference(reconnectEdgeDescriptionEClass, RECONNECT_EDGE_DESCRIPTION__ELEMENT);
        createEReference(reconnectEdgeDescriptionEClass, RECONNECT_EDGE_DESCRIPTION__INITIAL_OPERATION);
        createEReference(reconnectEdgeDescriptionEClass, RECONNECT_EDGE_DESCRIPTION__EDGE_VIEW);

        requestDescriptionEClass = createEClass(REQUEST_DESCRIPTION);
        createEAttribute(requestDescriptionEClass, REQUEST_DESCRIPTION__TYPE);

        selectionWizardDescriptionEClass = createEClass(SELECTION_WIZARD_DESCRIPTION);
        createEReference(selectionWizardDescriptionEClass, SELECTION_WIZARD_DESCRIPTION__ELEMENT);
        createEReference(selectionWizardDescriptionEClass, SELECTION_WIZARD_DESCRIPTION__CONTAINER_VIEW);
        createEReference(selectionWizardDescriptionEClass, SELECTION_WIZARD_DESCRIPTION__CONTAINER);
        createEReference(selectionWizardDescriptionEClass, SELECTION_WIZARD_DESCRIPTION__INITIAL_OPERATION);
        createEAttribute(selectionWizardDescriptionEClass, SELECTION_WIZARD_DESCRIPTION__ICON_PATH);
        createEAttribute(selectionWizardDescriptionEClass, SELECTION_WIZARD_DESCRIPTION__WINDOW_TITLE);
        createEAttribute(selectionWizardDescriptionEClass, SELECTION_WIZARD_DESCRIPTION__WINDOW_IMAGE_PATH);

        paneBasedSelectionWizardDescriptionEClass = createEClass(PANE_BASED_SELECTION_WIZARD_DESCRIPTION);
        createEReference(paneBasedSelectionWizardDescriptionEClass, PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ELEMENT);
        createEReference(paneBasedSelectionWizardDescriptionEClass, PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CONTAINER_VIEW);
        createEReference(paneBasedSelectionWizardDescriptionEClass, PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CONTAINER);
        createEReference(paneBasedSelectionWizardDescriptionEClass, PANE_BASED_SELECTION_WIZARD_DESCRIPTION__INITIAL_OPERATION);
        createEAttribute(paneBasedSelectionWizardDescriptionEClass, PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ICON_PATH);
        createEAttribute(paneBasedSelectionWizardDescriptionEClass, PANE_BASED_SELECTION_WIZARD_DESCRIPTION__WINDOW_TITLE);
        createEAttribute(paneBasedSelectionWizardDescriptionEClass, PANE_BASED_SELECTION_WIZARD_DESCRIPTION__WINDOW_IMAGE_PATH);
        createEAttribute(paneBasedSelectionWizardDescriptionEClass, PANE_BASED_SELECTION_WIZARD_DESCRIPTION__MESSAGE);
        createEAttribute(paneBasedSelectionWizardDescriptionEClass, PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CHOICE_OF_VALUES_MESSAGE);
        createEAttribute(paneBasedSelectionWizardDescriptionEClass, PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CANDIDATES_EXPRESSION);
        createEAttribute(paneBasedSelectionWizardDescriptionEClass, PANE_BASED_SELECTION_WIZARD_DESCRIPTION__TREE);
        createEAttribute(paneBasedSelectionWizardDescriptionEClass, PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ROOT_EXPRESSION);
        createEAttribute(paneBasedSelectionWizardDescriptionEClass, PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CHILDREN_EXPRESSION);
        createEAttribute(paneBasedSelectionWizardDescriptionEClass, PANE_BASED_SELECTION_WIZARD_DESCRIPTION__SELECTED_VALUES_MESSAGE);
        createEAttribute(paneBasedSelectionWizardDescriptionEClass, PANE_BASED_SELECTION_WIZARD_DESCRIPTION__PRE_SELECTED_CANDIDATES_EXPRESSION);

        representationCreationDescriptionEClass = createEClass(REPRESENTATION_CREATION_DESCRIPTION);
        createEAttribute(representationCreationDescriptionEClass, REPRESENTATION_CREATION_DESCRIPTION__TITLE_EXPRESSION);
        createEAttribute(representationCreationDescriptionEClass, REPRESENTATION_CREATION_DESCRIPTION__BROWSE_EXPRESSION);
        createEReference(representationCreationDescriptionEClass, REPRESENTATION_CREATION_DESCRIPTION__REPRESENTATION_DESCRIPTION);
        createEReference(representationCreationDescriptionEClass, REPRESENTATION_CREATION_DESCRIPTION__INITIAL_OPERATION);
        createEReference(representationCreationDescriptionEClass, REPRESENTATION_CREATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE);
        createEReference(representationCreationDescriptionEClass, REPRESENTATION_CREATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE);

        representationNavigationDescriptionEClass = createEClass(REPRESENTATION_NAVIGATION_DESCRIPTION);
        createEAttribute(representationNavigationDescriptionEClass, REPRESENTATION_NAVIGATION_DESCRIPTION__BROWSE_EXPRESSION);
        createEAttribute(representationNavigationDescriptionEClass, REPRESENTATION_NAVIGATION_DESCRIPTION__NAVIGATION_NAME_EXPRESSION);
        createEReference(representationNavigationDescriptionEClass, REPRESENTATION_NAVIGATION_DESCRIPTION__REPRESENTATION_DESCRIPTION);
        createEReference(representationNavigationDescriptionEClass, REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE);
        createEReference(representationNavigationDescriptionEClass, REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VARIABLE);
        createEReference(representationNavigationDescriptionEClass, REPRESENTATION_NAVIGATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE);

        menuItemOrRefEClass = createEClass(MENU_ITEM_OR_REF);

        menuItemDescriptionEClass = createEClass(MENU_ITEM_DESCRIPTION);
        createEAttribute(menuItemDescriptionEClass, MENU_ITEM_DESCRIPTION__ICON);

        menuItemDescriptionReferenceEClass = createEClass(MENU_ITEM_DESCRIPTION_REFERENCE);
        createEReference(menuItemDescriptionReferenceEClass, MENU_ITEM_DESCRIPTION_REFERENCE__ITEM);

        operationActionEClass = createEClass(OPERATION_ACTION);
        createEReference(operationActionEClass, OPERATION_ACTION__VIEW);
        createEReference(operationActionEClass, OPERATION_ACTION__INITIAL_OPERATION);

        externalJavaActionEClass = createEClass(EXTERNAL_JAVA_ACTION);
        createEAttribute(externalJavaActionEClass, EXTERNAL_JAVA_ACTION__ID);
        createEReference(externalJavaActionEClass, EXTERNAL_JAVA_ACTION__PARAMETERS);

        externalJavaActionCallEClass = createEClass(EXTERNAL_JAVA_ACTION_CALL);
        createEReference(externalJavaActionCallEClass, EXTERNAL_JAVA_ACTION_CALL__ACTION);

        popupMenuEClass = createEClass(POPUP_MENU);
        createEReference(popupMenuEClass, POPUP_MENU__MENU_ITEM_DESCRIPTION);

        directEditLabelEClass = createEClass(DIRECT_EDIT_LABEL);
        createEReference(directEditLabelEClass, DIRECT_EDIT_LABEL__MASK);
        createEReference(directEditLabelEClass, DIRECT_EDIT_LABEL__INITIAL_OPERATION);
        createEAttribute(directEditLabelEClass, DIRECT_EDIT_LABEL__INPUT_LABEL_EXPRESSION);

        behaviorToolEClass = createEClass(BEHAVIOR_TOOL);
        createEAttribute(behaviorToolEClass, BEHAVIOR_TOOL__DOMAIN_CLASS);
        createEReference(behaviorToolEClass, BEHAVIOR_TOOL__INITIAL_OPERATION);

        abstractVariableEClass = createEClass(ABSTRACT_VARIABLE);
        createEAttribute(abstractVariableEClass, ABSTRACT_VARIABLE__NAME);

        variableContainerEClass = createEClass(VARIABLE_CONTAINER);
        createEReference(variableContainerEClass, VARIABLE_CONTAINER__SUB_VARIABLES);

        acceleoVariableEClass = createEClass(ACCELEO_VARIABLE);
        createEAttribute(acceleoVariableEClass, ACCELEO_VARIABLE__COMPUTATION_EXPRESSION);

        subVariableEClass = createEClass(SUB_VARIABLE);

        dialogVariableEClass = createEClass(DIALOG_VARIABLE);
        createEAttribute(dialogVariableEClass, DIALOG_VARIABLE__DIALOG_PROMPT);

        sourceEdgeCreationVariableEClass = createEClass(SOURCE_EDGE_CREATION_VARIABLE);

        sourceEdgeViewCreationVariableEClass = createEClass(SOURCE_EDGE_VIEW_CREATION_VARIABLE);

        targetEdgeCreationVariableEClass = createEClass(TARGET_EDGE_CREATION_VARIABLE);

        targetEdgeViewCreationVariableEClass = createEClass(TARGET_EDGE_VIEW_CREATION_VARIABLE);

        elementDropVariableEClass = createEClass(ELEMENT_DROP_VARIABLE);

        elementSelectVariableEClass = createEClass(ELEMENT_SELECT_VARIABLE);

        elementVariableEClass = createEClass(ELEMENT_VARIABLE);

        elementViewVariableEClass = createEClass(ELEMENT_VIEW_VARIABLE);

        elementDeleteVariableEClass = createEClass(ELEMENT_DELETE_VARIABLE);

        elementDoubleClickVariableEClass = createEClass(ELEMENT_DOUBLE_CLICK_VARIABLE);

        nodeCreationVariableEClass = createEClass(NODE_CREATION_VARIABLE);

        dropContainerVariableEClass = createEClass(DROP_CONTAINER_VARIABLE);

        selectContainerVariableEClass = createEClass(SELECT_CONTAINER_VARIABLE);

        containerViewVariableEClass = createEClass(CONTAINER_VIEW_VARIABLE);

        selectModelElementVariableEClass = createEClass(SELECT_MODEL_ELEMENT_VARIABLE);

        editMaskVariablesEClass = createEClass(EDIT_MASK_VARIABLES);
        createEAttribute(editMaskVariablesEClass, EDIT_MASK_VARIABLES__MASK);

        containerModelOperationEClass = createEClass(CONTAINER_MODEL_OPERATION);
        createEReference(containerModelOperationEClass, CONTAINER_MODEL_OPERATION__SUB_MODEL_OPERATIONS);

        modelOperationEClass = createEClass(MODEL_OPERATION);

        initialNodeCreationOperationEClass = createEClass(INITIAL_NODE_CREATION_OPERATION);
        createEReference(initialNodeCreationOperationEClass, INITIAL_NODE_CREATION_OPERATION__FIRST_MODEL_OPERATIONS);

        initialOperationEClass = createEClass(INITIAL_OPERATION);
        createEReference(initialOperationEClass, INITIAL_OPERATION__FIRST_MODEL_OPERATIONS);

        initEdgeCreationOperationEClass = createEClass(INIT_EDGE_CREATION_OPERATION);
        createEReference(initEdgeCreationOperationEClass, INIT_EDGE_CREATION_OPERATION__FIRST_MODEL_OPERATIONS);

        initialContainerDropOperationEClass = createEClass(INITIAL_CONTAINER_DROP_OPERATION);
        createEReference(initialContainerDropOperationEClass, INITIAL_CONTAINER_DROP_OPERATION__FIRST_MODEL_OPERATIONS);

        createInstanceEClass = createEClass(CREATE_INSTANCE);
        createEAttribute(createInstanceEClass, CREATE_INSTANCE__TYPE_NAME);
        createEAttribute(createInstanceEClass, CREATE_INSTANCE__REFERENCE_NAME);
        createEAttribute(createInstanceEClass, CREATE_INSTANCE__VARIABLE_NAME);

        changeContextEClass = createEClass(CHANGE_CONTEXT);
        createEAttribute(changeContextEClass, CHANGE_CONTEXT__BROWSE_EXPRESSION);

        setValueEClass = createEClass(SET_VALUE);
        createEAttribute(setValueEClass, SET_VALUE__FEATURE_NAME);
        createEAttribute(setValueEClass, SET_VALUE__VALUE_EXPRESSION);

        setObjectEClass = createEClass(SET_OBJECT);
        createEAttribute(setObjectEClass, SET_OBJECT__FEATURE_NAME);
        createEReference(setObjectEClass, SET_OBJECT__OBJECT);

        unsetEClass = createEClass(UNSET);
        createEAttribute(unsetEClass, UNSET__FEATURE_NAME);
        createEAttribute(unsetEClass, UNSET__ELEMENT_EXPRESSION);

        moveElementEClass = createEClass(MOVE_ELEMENT);
        createEAttribute(moveElementEClass, MOVE_ELEMENT__NEW_CONTAINER_EXPRESSION);
        createEAttribute(moveElementEClass, MOVE_ELEMENT__FEATURE_NAME);

        removeElementEClass = createEClass(REMOVE_ELEMENT);

        forEClass = createEClass(FOR);
        createEAttribute(forEClass, FOR__EXPRESSION);
        createEAttribute(forEClass, FOR__ITERATOR_NAME);

        createViewEClass = createEClass(CREATE_VIEW);
        createEReference(createViewEClass, CREATE_VIEW__MAPPING);
        createEAttribute(createViewEClass, CREATE_VIEW__CONTAINER_VIEW_EXPRESSION);
        createEAttribute(createViewEClass, CREATE_VIEW__VARIABLE_NAME);

        createEdgeViewEClass = createEClass(CREATE_EDGE_VIEW);
        createEAttribute(createEdgeViewEClass, CREATE_EDGE_VIEW__SOURCE_EXPRESSION);
        createEAttribute(createEdgeViewEClass, CREATE_EDGE_VIEW__TARGET_EXPRESSION);

        ifEClass = createEClass(IF);
        createEAttribute(ifEClass, IF__CONDITION_EXPRESSION);

        deleteViewEClass = createEClass(DELETE_VIEW);

        navigationEClass = createEClass(NAVIGATION);
        createEAttribute(navigationEClass, NAVIGATION__CREATE_IF_NOT_EXISTENT);
        createEReference(navigationEClass, NAVIGATION__DIAGRAM_DESCRIPTION);

        nameVariableEClass = createEClass(NAME_VARIABLE);

        externalJavaActionParameterEClass = createEClass(EXTERNAL_JAVA_ACTION_PARAMETER);
        createEAttribute(externalJavaActionParameterEClass, EXTERNAL_JAVA_ACTION_PARAMETER__NAME);
        createEAttribute(externalJavaActionParameterEClass, EXTERNAL_JAVA_ACTION_PARAMETER__VALUE);

        diagramCreationDescriptionEClass = createEClass(DIAGRAM_CREATION_DESCRIPTION);
        createEReference(diagramCreationDescriptionEClass, DIAGRAM_CREATION_DESCRIPTION__DIAGRAM_DESCRIPTION);

        diagramNavigationDescriptionEClass = createEClass(DIAGRAM_NAVIGATION_DESCRIPTION);
        createEReference(diagramNavigationDescriptionEClass, DIAGRAM_NAVIGATION_DESCRIPTION__DIAGRAM_DESCRIPTION);

        toolFilterDescriptionEClass = createEClass(TOOL_FILTER_DESCRIPTION);
        createEAttribute(toolFilterDescriptionEClass, TOOL_FILTER_DESCRIPTION__PRECONDITION);
        createEAttribute(toolFilterDescriptionEClass, TOOL_FILTER_DESCRIPTION__ELEMENTS_TO_LISTEN);
        createEReference(toolFilterDescriptionEClass, TOOL_FILTER_DESCRIPTION__LISTENERS);

        featureChangeListenerEClass = createEClass(FEATURE_CHANGE_LISTENER);
        createEAttribute(featureChangeListenerEClass, FEATURE_CHANGE_LISTENER__DOMAIN_CLASS);
        createEAttribute(featureChangeListenerEClass, FEATURE_CHANGE_LISTENER__FEATURE_NAME);

        caseEClass = createEClass(CASE);
        createEAttribute(caseEClass, CASE__CONDITION_EXPRESSION);

        switchChildEClass = createEClass(SWITCH_CHILD);
        createEReference(switchChildEClass, SWITCH_CHILD__SUB_MODEL_OPERATIONS);

        defaultEClass = createEClass(DEFAULT);

        switchEClass = createEClass(SWITCH);
        createEReference(switchEClass, SWITCH__CASES);
        createEReference(switchEClass, SWITCH__DEFAULT);

        // Create enums
        reconnectionKindEEnum = createEEnum(RECONNECTION_KIND);
        dragSourceEEnum = createEEnum(DRAG_SOURCE);
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
        org.eclipse.sirius.diagram.description.DescriptionPackage theDescriptionPackage_1 = (org.eclipse.sirius.diagram.description.DescriptionPackage) EPackage.Registry.INSTANCE
                .getEPackage(org.eclipse.sirius.diagram.description.DescriptionPackage.eNS_URI);
        DiagramPackage theDiagramPackage = (DiagramPackage) EPackage.Registry.INSTANCE.getEPackage(DiagramPackage.eNS_URI);
        ViewpointPackage theViewpointPackage = (ViewpointPackage) EPackage.Registry.INSTANCE.getEPackage(ViewpointPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        toolSectionEClass.getESuperTypes().add(theDescriptionPackage.getDocumentedElement());
        toolSectionEClass.getESuperTypes().add(theDescriptionPackage.getIdentifiedElement());
        toolEntryEClass.getESuperTypes().add(theDescriptionPackage.getDocumentedElement());
        toolEntryEClass.getESuperTypes().add(theDescriptionPackage.getIdentifiedElement());
        toolGroupEClass.getESuperTypes().add(this.getToolEntry());
        abstractToolDescriptionEClass.getESuperTypes().add(this.getToolEntry());
        mappingBasedToolDescriptionEClass.getESuperTypes().add(this.getAbstractToolDescription());
        toolDescriptionEClass.getESuperTypes().add(this.getMappingBasedToolDescription());
        nodeCreationDescriptionEClass.getESuperTypes().add(this.getMappingBasedToolDescription());
        edgeCreationDescriptionEClass.getESuperTypes().add(this.getMappingBasedToolDescription());
        containerCreationDescriptionEClass.getESuperTypes().add(this.getMappingBasedToolDescription());
        containerDropDescriptionEClass.getESuperTypes().add(this.getMappingBasedToolDescription());
        pasteDescriptionEClass.getESuperTypes().add(this.getMappingBasedToolDescription());
        deleteElementDescriptionEClass.getESuperTypes().add(this.getMappingBasedToolDescription());
        doubleClickDescriptionEClass.getESuperTypes().add(this.getMappingBasedToolDescription());
        reconnectEdgeDescriptionEClass.getESuperTypes().add(this.getMappingBasedToolDescription());
        requestDescriptionEClass.getESuperTypes().add(this.getAbstractToolDescription());
        selectionWizardDescriptionEClass.getESuperTypes().add(this.getAbstractToolDescription());
        selectionWizardDescriptionEClass.getESuperTypes().add(theDescriptionPackage.getSelectionDescription());
        paneBasedSelectionWizardDescriptionEClass.getESuperTypes().add(this.getAbstractToolDescription());
        representationCreationDescriptionEClass.getESuperTypes().add(this.getAbstractToolDescription());
        representationNavigationDescriptionEClass.getESuperTypes().add(this.getAbstractToolDescription());
        menuItemDescriptionEClass.getESuperTypes().add(this.getAbstractToolDescription());
        menuItemDescriptionEClass.getESuperTypes().add(this.getMenuItemOrRef());
        menuItemDescriptionReferenceEClass.getESuperTypes().add(this.getMenuItemOrRef());
        operationActionEClass.getESuperTypes().add(this.getMenuItemDescription());
        externalJavaActionEClass.getESuperTypes().add(this.getMenuItemDescription());
        externalJavaActionEClass.getESuperTypes().add(this.getContainerModelOperation());
        externalJavaActionCallEClass.getESuperTypes().add(this.getMenuItemDescription());
        externalJavaActionCallEClass.getESuperTypes().add(this.getContainerModelOperation());
        popupMenuEClass.getESuperTypes().add(this.getAbstractToolDescription());
        directEditLabelEClass.getESuperTypes().add(this.getMappingBasedToolDescription());
        behaviorToolEClass.getESuperTypes().add(this.getAbstractToolDescription());
        acceleoVariableEClass.getESuperTypes().add(this.getVariableContainer());
        acceleoVariableEClass.getESuperTypes().add(this.getSubVariable());
        subVariableEClass.getESuperTypes().add(this.getAbstractVariable());
        dialogVariableEClass.getESuperTypes().add(this.getAbstractVariable());
        sourceEdgeCreationVariableEClass.getESuperTypes().add(this.getAbstractVariable());
        sourceEdgeCreationVariableEClass.getESuperTypes().add(this.getVariableContainer());
        sourceEdgeViewCreationVariableEClass.getESuperTypes().add(this.getAbstractVariable());
        sourceEdgeViewCreationVariableEClass.getESuperTypes().add(this.getVariableContainer());
        targetEdgeCreationVariableEClass.getESuperTypes().add(this.getAbstractVariable());
        targetEdgeCreationVariableEClass.getESuperTypes().add(this.getVariableContainer());
        targetEdgeViewCreationVariableEClass.getESuperTypes().add(this.getAbstractVariable());
        targetEdgeViewCreationVariableEClass.getESuperTypes().add(this.getVariableContainer());
        elementDropVariableEClass.getESuperTypes().add(this.getAbstractVariable());
        elementDropVariableEClass.getESuperTypes().add(this.getVariableContainer());
        elementSelectVariableEClass.getESuperTypes().add(this.getAbstractVariable());
        elementVariableEClass.getESuperTypes().add(this.getAbstractVariable());
        elementVariableEClass.getESuperTypes().add(this.getVariableContainer());
        elementViewVariableEClass.getESuperTypes().add(this.getAbstractVariable());
        elementViewVariableEClass.getESuperTypes().add(this.getVariableContainer());
        elementDeleteVariableEClass.getESuperTypes().add(this.getAbstractVariable());
        elementDeleteVariableEClass.getESuperTypes().add(this.getVariableContainer());
        elementDoubleClickVariableEClass.getESuperTypes().add(this.getAbstractVariable());
        elementDoubleClickVariableEClass.getESuperTypes().add(this.getVariableContainer());
        nodeCreationVariableEClass.getESuperTypes().add(this.getAbstractVariable());
        nodeCreationVariableEClass.getESuperTypes().add(this.getVariableContainer());
        dropContainerVariableEClass.getESuperTypes().add(this.getAbstractVariable());
        dropContainerVariableEClass.getESuperTypes().add(this.getVariableContainer());
        selectContainerVariableEClass.getESuperTypes().add(this.getAbstractVariable());
        selectContainerVariableEClass.getESuperTypes().add(this.getVariableContainer());
        containerViewVariableEClass.getESuperTypes().add(this.getAbstractVariable());
        containerViewVariableEClass.getESuperTypes().add(this.getVariableContainer());
        selectModelElementVariableEClass.getESuperTypes().add(this.getSubVariable());
        selectModelElementVariableEClass.getESuperTypes().add(theDescriptionPackage.getSelectionDescription());
        containerModelOperationEClass.getESuperTypes().add(this.getModelOperation());
        createInstanceEClass.getESuperTypes().add(this.getContainerModelOperation());
        changeContextEClass.getESuperTypes().add(this.getContainerModelOperation());
        setValueEClass.getESuperTypes().add(this.getContainerModelOperation());
        setObjectEClass.getESuperTypes().add(this.getContainerModelOperation());
        unsetEClass.getESuperTypes().add(this.getContainerModelOperation());
        moveElementEClass.getESuperTypes().add(this.getContainerModelOperation());
        removeElementEClass.getESuperTypes().add(this.getContainerModelOperation());
        forEClass.getESuperTypes().add(this.getContainerModelOperation());
        createViewEClass.getESuperTypes().add(this.getContainerModelOperation());
        createEdgeViewEClass.getESuperTypes().add(this.getCreateView());
        ifEClass.getESuperTypes().add(this.getContainerModelOperation());
        deleteViewEClass.getESuperTypes().add(this.getContainerModelOperation());
        navigationEClass.getESuperTypes().add(this.getContainerModelOperation());
        nameVariableEClass.getESuperTypes().add(this.getAbstractVariable());
        diagramCreationDescriptionEClass.getESuperTypes().add(this.getRepresentationCreationDescription());
        diagramNavigationDescriptionEClass.getESuperTypes().add(this.getRepresentationNavigationDescription());
        caseEClass.getESuperTypes().add(this.getSwitchChild());
        defaultEClass.getESuperTypes().add(this.getSwitchChild());
        switchEClass.getESuperTypes().add(this.getModelOperation());

        // Initialize classes and features; add operations and parameters
        initEClass(toolSectionEClass, ToolSection.class, "ToolSection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getToolSection_Icon(), theEcorePackage.getEString(), "icon", null, 0, 1, ToolSection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEReference(getToolSection_OwnedTools(), this.getToolEntry(), null, "ownedTools", null, 0, -1, ToolSection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getToolSection_OwnedTools().getEKeys().add(theDescriptionPackage.getIdentifiedElement_Name());
        initEReference(getToolSection_SubSections(), this.getToolSection(), null, "subSections", null, 0, -1, ToolSection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getToolSection_SubSections().getEKeys().add(theDescriptionPackage.getIdentifiedElement_Name());
        initEReference(getToolSection_PopupMenus(), this.getPopupMenu(), null, "popupMenus", null, 0, -1, ToolSection.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getToolSection_ReusedTools(), this.getToolEntry(), null, "reusedTools", null, 0, -1, ToolSection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getToolSection_GroupExtensions(), this.getToolGroupExtension(), null, "groupExtensions", null, 0, -1, ToolSection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(toolEntryEClass, ToolEntry.class, "ToolEntry", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(toolGroupEClass, ToolGroup.class, "ToolGroup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getToolGroup_Tools(), this.getAbstractToolDescription(), null, "tools", null, 0, -1, ToolGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(toolGroupExtensionEClass, ToolGroupExtension.class, "ToolGroupExtension", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getToolGroupExtension_Group(), this.getToolGroup(), null, "group", null, 1, 1, ToolGroupExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getToolGroupExtension_Tools(), this.getAbstractToolDescription(), null, "tools", null, 0, -1, ToolGroupExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(abstractToolDescriptionEClass, AbstractToolDescription.class, "AbstractToolDescription", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbstractToolDescription_Precondition(), theDescriptionPackage.getInterpretedExpression(), "precondition", "", 0, 1, AbstractToolDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractToolDescription_ForceRefresh(), theEcorePackage.getEBoolean(), "forceRefresh", "false", 0, 1, AbstractToolDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getAbstractToolDescription_Filters(), this.getToolFilterDescription(), null, "filters", null, 0, -1, AbstractToolDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(mappingBasedToolDescriptionEClass, MappingBasedToolDescription.class, "MappingBasedToolDescription", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(toolDescriptionEClass, ToolDescription.class, "ToolDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getToolDescription_IconPath(), theEcorePackage.getEString(), "iconPath", "", 0, 1, ToolDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getToolDescription_Element(), this.getElementVariable(), null, "element", null, 1, 1, ToolDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getToolDescription_ElementView(), this.getElementViewVariable(), null, "elementView", null, 1, 1, ToolDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getToolDescription_InitialOperation(), this.getInitialOperation(), null, "initialOperation", null, 1, 1, ToolDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(nodeCreationDescriptionEClass, NodeCreationDescription.class, "NodeCreationDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getNodeCreationDescription_NodeMappings(), theDescriptionPackage_1.getNodeMapping(), null, "nodeMappings", null, 1, -1, NodeCreationDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getNodeCreationDescription_Variable(), this.getNodeCreationVariable(), null, "variable", null, 1, 1, NodeCreationDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getNodeCreationDescription_ViewVariable(), this.getContainerViewVariable(), null, "viewVariable", null, 1, 1, NodeCreationDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getNodeCreationDescription_InitialOperation(), this.getInitialNodeCreationOperation(), null, "initialOperation", null, 1, 1, NodeCreationDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getNodeCreationDescription_IconPath(), theEcorePackage.getEString(), "iconPath", "", 0, 1, NodeCreationDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getNodeCreationDescription_ExtraMappings(), theDescriptionPackage_1.getAbstractNodeMapping(), null, "extraMappings", null, 0, -1, NodeCreationDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(edgeCreationDescriptionEClass, EdgeCreationDescription.class, "EdgeCreationDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getEdgeCreationDescription_EdgeMappings(), theDescriptionPackage_1.getEdgeMapping(), null, "edgeMappings", null, 1, -1, EdgeCreationDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeCreationDescription_SourceVariable(), this.getSourceEdgeCreationVariable(), null, "sourceVariable", null, 1, 1, EdgeCreationDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeCreationDescription_TargetVariable(), this.getTargetEdgeCreationVariable(), null, "targetVariable", null, 1, 1, EdgeCreationDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeCreationDescription_SourceViewVariable(), this.getSourceEdgeViewCreationVariable(), null, "sourceViewVariable", null, 1, 1, EdgeCreationDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeCreationDescription_TargetViewVariable(), this.getTargetEdgeViewCreationVariable(), null, "targetViewVariable", null, 1, 1, EdgeCreationDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeCreationDescription_InitialOperation(), this.getInitEdgeCreationOperation(), null, "initialOperation", null, 1, 1, EdgeCreationDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEdgeCreationDescription_IconPath(), theEcorePackage.getEString(), "iconPath", "", 0, 1, EdgeCreationDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeCreationDescription_ExtraSourceMappings(), theDescriptionPackage_1.getDiagramElementMapping(), null, "extraSourceMappings", null, 0, -1, EdgeCreationDescription.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeCreationDescription_ExtraTargetMappings(), theDescriptionPackage_1.getDiagramElementMapping(), null, "extraTargetMappings", null, 0, -1, EdgeCreationDescription.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEdgeCreationDescription_ConnectionStartPrecondition(), theDescriptionPackage.getInterpretedExpression(), "connectionStartPrecondition", null, 0, 1,
                EdgeCreationDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        EOperation op = addEOperation(edgeCreationDescriptionEClass, theDescriptionPackage_1.getEdgeMapping(), "getBestMapping", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theDiagramPackage.getEdgeTarget(), "source", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theDiagramPackage.getEdgeTarget(), "target", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "createdElements", 0, -1, IS_UNIQUE, IS_ORDERED);

        initEClass(containerCreationDescriptionEClass, ContainerCreationDescription.class, "ContainerCreationDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getContainerCreationDescription_ContainerMappings(), theDescriptionPackage_1.getContainerMapping(), null, "containerMappings", null, 1, -1, ContainerCreationDescription.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getContainerCreationDescription_Variable(), this.getNodeCreationVariable(), null, "variable", null, 1, 1, ContainerCreationDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getContainerCreationDescription_ViewVariable(), this.getContainerViewVariable(), null, "viewVariable", null, 1, 1, ContainerCreationDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getContainerCreationDescription_InitialOperation(), this.getInitialNodeCreationOperation(), null, "initialOperation", null, 1, 1, ContainerCreationDescription.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getContainerCreationDescription_IconPath(), theEcorePackage.getEString(), "iconPath", "", 0, 1, ContainerCreationDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getContainerCreationDescription_ExtraMappings(), theDescriptionPackage_1.getAbstractNodeMapping(), null, "extraMappings", null, 0, -1, ContainerCreationDescription.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(containerDropDescriptionEClass, ContainerDropDescription.class, "ContainerDropDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getContainerDropDescription_Mappings(), theDescriptionPackage_1.getDiagramElementMapping(), null, "mappings", null, 0, -1, ContainerDropDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getContainerDropDescription_OldContainer(), this.getDropContainerVariable(), null, "oldContainer", null, 1, 1, ContainerDropDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getContainerDropDescription_NewContainer(), this.getDropContainerVariable(), null, "newContainer", null, 1, 1, ContainerDropDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getContainerDropDescription_Element(), this.getElementDropVariable(), null, "element", null, 1, 1, ContainerDropDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getContainerDropDescription_NewViewContainer(), this.getContainerViewVariable(), null, "newViewContainer", null, 1, 1, ContainerDropDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getContainerDropDescription_InitialOperation(), this.getInitialContainerDropOperation(), null, "initialOperation", null, 1, 1, ContainerDropDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getContainerDropDescription_DragSource(), this.getDragSource(), "dragSource", null, 1, 1, ContainerDropDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getContainerDropDescription_MoveEdges(), ecorePackage.getEBoolean(), "moveEdges", null, 1, 1, ContainerDropDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        op = addEOperation(containerDropDescriptionEClass, theDescriptionPackage_1.getDiagramElementMapping(), "getBestMapping", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theViewpointPackage.getDragAndDropTarget(), "targetContainer", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "droppedElement", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(containerDropDescriptionEClass, theDescriptionPackage.getDragAndDropTargetDescription(), "getContainers", 1, -1, IS_UNIQUE, IS_ORDERED);

        initEClass(pasteDescriptionEClass, PasteDescription.class, "PasteDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getPasteDescription_Container(), this.getDropContainerVariable(), null, "container", null, 1, 1, PasteDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPasteDescription_ContainerView(), this.getContainerViewVariable(), null, "containerView", null, 1, 1, PasteDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPasteDescription_CopiedView(), this.getElementViewVariable(), null, "copiedView", null, 1, 1, PasteDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPasteDescription_CopiedElement(), this.getElementVariable(), null, "copiedElement", null, 1, 1, PasteDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPasteDescription_InitialOperation(), this.getInitialOperation(), null, "initialOperation", null, 1, 1, PasteDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        addEOperation(pasteDescriptionEClass, theDescriptionPackage.getPasteTargetDescription(), "getContainers", 0, -1, IS_UNIQUE, IS_ORDERED);

        initEClass(deleteElementDescriptionEClass, DeleteElementDescription.class, "DeleteElementDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDeleteElementDescription_Element(), this.getElementDeleteVariable(), null, "element", null, 1, 1, DeleteElementDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDeleteElementDescription_ElementView(), this.getElementDeleteVariable(), null, "elementView", null, 0, 1, DeleteElementDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDeleteElementDescription_ContainerView(), this.getContainerViewVariable(), null, "containerView", null, 1, 1, DeleteElementDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDeleteElementDescription_InitialOperation(), this.getInitialOperation(), null, "initialOperation", null, 1, 1, DeleteElementDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDeleteElementDescription_Hook(), this.getDeleteHook(), null, "hook", null, 0, 1, DeleteElementDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        addEOperation(deleteElementDescriptionEClass, theDescriptionPackage_1.getDiagramElementMapping(), "getMappings", 1, -1, IS_UNIQUE, IS_ORDERED);

        initEClass(doubleClickDescriptionEClass, DoubleClickDescription.class, "DoubleClickDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDoubleClickDescription_Mappings(), theDescriptionPackage_1.getDiagramElementMapping(), theDescriptionPackage_1.getDiagramElementMapping_DoubleClickDescription(), "mappings",
                null, 1, -1, DoubleClickDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDoubleClickDescription_Element(), this.getElementDoubleClickVariable(), null, "element", null, 1, 1, DoubleClickDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDoubleClickDescription_ElementView(), this.getElementDoubleClickVariable(), null, "elementView", null, 0, 1, DoubleClickDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDoubleClickDescription_InitialOperation(), this.getInitialOperation(), null, "initialOperation", null, 1, 1, DoubleClickDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(deleteHookEClass, DeleteHook.class, "DeleteHook", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDeleteHook_Id(), theEcorePackage.getEString(), "id", null, 1, 1, DeleteHook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEReference(getDeleteHook_Parameters(), this.getDeleteHookParameter(), null, "parameters", null, 0, -1, DeleteHook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(deleteHookParameterEClass, DeleteHookParameter.class, "DeleteHookParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDeleteHookParameter_Name(), theEcorePackage.getEString(), "name", null, 1, 1, DeleteHookParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDeleteHookParameter_Value(), theDescriptionPackage.getInterpretedExpression(), "value", null, 0, 1, DeleteHookParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(reconnectEdgeDescriptionEClass, ReconnectEdgeDescription.class, "ReconnectEdgeDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getReconnectEdgeDescription_ReconnectionKind(), this.getReconnectionKind(), "reconnectionKind", "RECONNECT_TARGET", 1, 1, ReconnectEdgeDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getReconnectEdgeDescription_Source(), this.getSourceEdgeCreationVariable(), null, "source", null, 1, 1, ReconnectEdgeDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getReconnectEdgeDescription_Target(), this.getTargetEdgeCreationVariable(), null, "target", null, 1, 1, ReconnectEdgeDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getReconnectEdgeDescription_SourceView(), this.getSourceEdgeViewCreationVariable(), null, "sourceView", null, 1, 1, ReconnectEdgeDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getReconnectEdgeDescription_TargetView(), this.getTargetEdgeViewCreationVariable(), null, "targetView", null, 1, 1, ReconnectEdgeDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getReconnectEdgeDescription_Element(), this.getElementSelectVariable(), null, "element", null, 1, 1, ReconnectEdgeDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getReconnectEdgeDescription_InitialOperation(), this.getInitialOperation(), null, "initialOperation", null, 1, 1, ReconnectEdgeDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getReconnectEdgeDescription_EdgeView(), this.getElementSelectVariable(), null, "edgeView", null, 1, 1, ReconnectEdgeDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        addEOperation(reconnectEdgeDescriptionEClass, theDescriptionPackage_1.getEdgeMapping(), "getMappings", 1, -1, IS_UNIQUE, IS_ORDERED);

        initEClass(requestDescriptionEClass, RequestDescription.class, "RequestDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getRequestDescription_Type(), theEcorePackage.getEString(), "type", null, 1, 1, RequestDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(selectionWizardDescriptionEClass, SelectionWizardDescription.class, "SelectionWizardDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getSelectionWizardDescription_Element(), this.getElementSelectVariable(), null, "element", null, 1, 1, SelectionWizardDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getSelectionWizardDescription_ContainerView(), this.getContainerViewVariable(), null, "containerView", null, 1, 1, SelectionWizardDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getSelectionWizardDescription_Container(), this.getSelectContainerVariable(), null, "container", null, 1, 1, SelectionWizardDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getSelectionWizardDescription_InitialOperation(), this.getInitialOperation(), null, "initialOperation", null, 1, 1, SelectionWizardDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSelectionWizardDescription_IconPath(), theEcorePackage.getEString(), "iconPath", "/org.eclipse.sirius.ui/icons/full/obj16/NodeMapping.gif", 1, 1,
                SelectionWizardDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSelectionWizardDescription_WindowTitle(), theEcorePackage.getEString(), "windowTitle", "Selection Wizard", 1, 1, SelectionWizardDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSelectionWizardDescription_WindowImagePath(), theEcorePackage.getEString(), "windowImagePath", null, 0, 1, SelectionWizardDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(paneBasedSelectionWizardDescriptionEClass, PaneBasedSelectionWizardDescription.class, "PaneBasedSelectionWizardDescription", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getPaneBasedSelectionWizardDescription_Element(), this.getElementSelectVariable(), null, "element", null, 1, 1, PaneBasedSelectionWizardDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPaneBasedSelectionWizardDescription_ContainerView(), this.getContainerViewVariable(), null, "containerView", null, 1, 1, PaneBasedSelectionWizardDescription.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPaneBasedSelectionWizardDescription_Container(), this.getSelectContainerVariable(), null, "container", null, 1, 1, PaneBasedSelectionWizardDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPaneBasedSelectionWizardDescription_InitialOperation(), this.getInitialOperation(), null, "initialOperation", null, 1, 1, PaneBasedSelectionWizardDescription.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getPaneBasedSelectionWizardDescription_IconPath(), theEcorePackage.getEString(), "iconPath", "/org.eclipse.sirius.ui/icons/full/obj16/NodeMapping.gif", 1, 1,
                PaneBasedSelectionWizardDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getPaneBasedSelectionWizardDescription_WindowTitle(), theEcorePackage.getEString(), "windowTitle", "Selection Wizard", 1, 1, PaneBasedSelectionWizardDescription.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getPaneBasedSelectionWizardDescription_WindowImagePath(), theEcorePackage.getEString(), "windowImagePath", null, 0, 1, PaneBasedSelectionWizardDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getPaneBasedSelectionWizardDescription_Message(), theEcorePackage.getEString(), "message", null, 0, 1, PaneBasedSelectionWizardDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getPaneBasedSelectionWizardDescription_ChoiceOfValuesMessage(), theEcorePackage.getEString(), "choiceOfValuesMessage", "Choice of values", 0, 1,
                PaneBasedSelectionWizardDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getPaneBasedSelectionWizardDescription_CandidatesExpression(), theDescriptionPackage.getInterpretedExpression(), "candidatesExpression", null, 1, 1,
                PaneBasedSelectionWizardDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getPaneBasedSelectionWizardDescription_Tree(), theEcorePackage.getEBoolean(), "tree", null, 1, 1, PaneBasedSelectionWizardDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getPaneBasedSelectionWizardDescription_RootExpression(), theDescriptionPackage.getInterpretedExpression(), "rootExpression", null, 0, 1,
                PaneBasedSelectionWizardDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getPaneBasedSelectionWizardDescription_ChildrenExpression(), theDescriptionPackage.getInterpretedExpression(), "childrenExpression", null, 0, 1,
                PaneBasedSelectionWizardDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getPaneBasedSelectionWizardDescription_SelectedValuesMessage(), theEcorePackage.getEString(), "selectedValuesMessage", "Selected values", 0, 1,
                PaneBasedSelectionWizardDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getPaneBasedSelectionWizardDescription_PreSelectedCandidatesExpression(), theDescriptionPackage.getInterpretedExpression(), "preSelectedCandidatesExpression", null, 0, 1,
                PaneBasedSelectionWizardDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(representationCreationDescriptionEClass, RepresentationCreationDescription.class, "RepresentationCreationDescription", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getRepresentationCreationDescription_TitleExpression(), theDescriptionPackage.getInterpretedExpression(), "titleExpression", "", 0, 1, RepresentationCreationDescription.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getRepresentationCreationDescription_BrowseExpression(), theDescriptionPackage.getInterpretedExpression(), "browseExpression", null, 0, 1,
                RepresentationCreationDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getRepresentationCreationDescription_RepresentationDescription(), theDescriptionPackage.getRepresentationDescription(), null, "representationDescription", null, 1, 1,
                RepresentationCreationDescription.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getRepresentationCreationDescription_InitialOperation(), this.getInitialOperation(), null, "initialOperation", null, 0, 1, RepresentationCreationDescription.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getRepresentationCreationDescription_ContainerViewVariable(), this.getContainerViewVariable(), null, "containerViewVariable", null, 1, 1,
                RepresentationCreationDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getRepresentationCreationDescription_RepresentationNameVariable(), this.getNameVariable(), null, "representationNameVariable", null, 1, 1,
                RepresentationCreationDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        addEOperation(representationCreationDescriptionEClass, theDescriptionPackage.getRepresentationElementMapping(), "getMappings", 1, -1, IS_UNIQUE, IS_ORDERED);

        initEClass(representationNavigationDescriptionEClass, RepresentationNavigationDescription.class, "RepresentationNavigationDescription", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getRepresentationNavigationDescription_BrowseExpression(), theDescriptionPackage.getInterpretedExpression(), "browseExpression", null, 0, 1,
                RepresentationNavigationDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getRepresentationNavigationDescription_NavigationNameExpression(), theDescriptionPackage.getInterpretedExpression(), "navigationNameExpression", null, 0, 1,
                RepresentationNavigationDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getRepresentationNavigationDescription_RepresentationDescription(), theDescriptionPackage.getRepresentationDescription(), null, "representationDescription", null, 1, 1,
                RepresentationNavigationDescription.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getRepresentationNavigationDescription_ContainerViewVariable(), this.getContainerViewVariable(), null, "containerViewVariable", null, 1, 1,
                RepresentationNavigationDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getRepresentationNavigationDescription_ContainerVariable(), this.getElementSelectVariable(), null, "containerVariable", null, 1, 1, RepresentationNavigationDescription.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getRepresentationNavigationDescription_RepresentationNameVariable(), this.getNameVariable(), null, "representationNameVariable", null, 1, 1,
                RepresentationNavigationDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        addEOperation(representationNavigationDescriptionEClass, theDescriptionPackage.getRepresentationElementMapping(), "getMappings", 1, -1, IS_UNIQUE, IS_ORDERED);

        initEClass(menuItemOrRefEClass, MenuItemOrRef.class, "MenuItemOrRef", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(menuItemDescriptionEClass, MenuItemDescription.class, "MenuItemDescription", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getMenuItemDescription_Icon(), theEcorePackage.getEString(), "icon", null, 0, 1, MenuItemDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(menuItemDescriptionReferenceEClass, MenuItemDescriptionReference.class, "MenuItemDescriptionReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getMenuItemDescriptionReference_Item(), this.getMenuItemDescription(), null, "item", null, 1, 1, MenuItemDescriptionReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(operationActionEClass, OperationAction.class, "OperationAction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getOperationAction_View(), this.getContainerViewVariable(), null, "view", null, 1, 1, OperationAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getOperationAction_InitialOperation(), this.getInitialOperation(), null, "initialOperation", null, 1, 1, OperationAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(externalJavaActionEClass, ExternalJavaAction.class, "ExternalJavaAction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getExternalJavaAction_Id(), theEcorePackage.getEString(), "id", null, 1, 1, ExternalJavaAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getExternalJavaAction_Parameters(), this.getExternalJavaActionParameter(), null, "parameters", null, 0, -1, ExternalJavaAction.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(externalJavaActionCallEClass, ExternalJavaActionCall.class, "ExternalJavaActionCall", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getExternalJavaActionCall_Action(), this.getExternalJavaAction(), null, "action", null, 1, 1, ExternalJavaActionCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(popupMenuEClass, PopupMenu.class, "PopupMenu", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getPopupMenu_MenuItemDescription(), this.getMenuItemDescription(), null, "menuItemDescription", null, 1, -1, PopupMenu.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(directEditLabelEClass, DirectEditLabel.class, "DirectEditLabel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDirectEditLabel_Mask(), this.getEditMaskVariables(), null, "mask", null, 1, 1, DirectEditLabel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDirectEditLabel_InitialOperation(), this.getInitialOperation(), null, "initialOperation", null, 1, 1, DirectEditLabel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDirectEditLabel_InputLabelExpression(), theDescriptionPackage.getInterpretedExpression(), "inputLabelExpression", null, 0, 1, DirectEditLabel.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        addEOperation(directEditLabelEClass, theDescriptionPackage_1.getDiagramElementMapping(), "getMapping", 1, -1, IS_UNIQUE, IS_ORDERED);

        initEClass(behaviorToolEClass, BehaviorTool.class, "BehaviorTool", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBehaviorTool_DomainClass(), theDescriptionPackage.getTypeName(), "domainClass", "EObject", 1, 1, BehaviorTool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getBehaviorTool_InitialOperation(), this.getInitialOperation(), null, "initialOperation", null, 1, 1, BehaviorTool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(abstractVariableEClass, AbstractVariable.class, "AbstractVariable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbstractVariable_Name(), theEcorePackage.getEString(), "name", null, 0, 1, AbstractVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(variableContainerEClass, VariableContainer.class, "VariableContainer", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getVariableContainer_SubVariables(), this.getSubVariable(), null, "subVariables", null, 0, -1, VariableContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(acceleoVariableEClass, AcceleoVariable.class, "AcceleoVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAcceleoVariable_ComputationExpression(), theDescriptionPackage.getInterpretedExpression(), "computationExpression", null, 0, 1, AcceleoVariable.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(subVariableEClass, SubVariable.class, "SubVariable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(dialogVariableEClass, DialogVariable.class, "DialogVariable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDialogVariable_DialogPrompt(), theEcorePackage.getEString(), "dialogPrompt", null, 0, 1, DialogVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(sourceEdgeCreationVariableEClass, SourceEdgeCreationVariable.class, "SourceEdgeCreationVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(sourceEdgeViewCreationVariableEClass, SourceEdgeViewCreationVariable.class, "SourceEdgeViewCreationVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(targetEdgeCreationVariableEClass, TargetEdgeCreationVariable.class, "TargetEdgeCreationVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(targetEdgeViewCreationVariableEClass, TargetEdgeViewCreationVariable.class, "TargetEdgeViewCreationVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(elementDropVariableEClass, ElementDropVariable.class, "ElementDropVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(elementSelectVariableEClass, ElementSelectVariable.class, "ElementSelectVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(elementVariableEClass, ElementVariable.class, "ElementVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(elementViewVariableEClass, ElementViewVariable.class, "ElementViewVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(elementDeleteVariableEClass, ElementDeleteVariable.class, "ElementDeleteVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(elementDoubleClickVariableEClass, ElementDoubleClickVariable.class, "ElementDoubleClickVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(nodeCreationVariableEClass, NodeCreationVariable.class, "NodeCreationVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(dropContainerVariableEClass, DropContainerVariable.class, "DropContainerVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(selectContainerVariableEClass, SelectContainerVariable.class, "SelectContainerVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(containerViewVariableEClass, ContainerViewVariable.class, "ContainerViewVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(selectModelElementVariableEClass, SelectModelElementVariable.class, "SelectModelElementVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(editMaskVariablesEClass, EditMaskVariables.class, "EditMaskVariables", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getEditMaskVariables_Mask(), theEcorePackage.getEString(), "mask", null, 0, 1, EditMaskVariables.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(containerModelOperationEClass, ContainerModelOperation.class, "ContainerModelOperation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getContainerModelOperation_SubModelOperations(), this.getModelOperation(), null, "subModelOperations", null, 0, -1, ContainerModelOperation.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(modelOperationEClass, ModelOperation.class, "ModelOperation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(initialNodeCreationOperationEClass, InitialNodeCreationOperation.class, "InitialNodeCreationOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getInitialNodeCreationOperation_FirstModelOperations(), this.getModelOperation(), null, "firstModelOperations", null, 1, 1, InitialNodeCreationOperation.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(initialOperationEClass, InitialOperation.class, "InitialOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getInitialOperation_FirstModelOperations(), this.getModelOperation(), null, "firstModelOperations", null, 1, 1, InitialOperation.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(initEdgeCreationOperationEClass, InitEdgeCreationOperation.class, "InitEdgeCreationOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getInitEdgeCreationOperation_FirstModelOperations(), this.getModelOperation(), null, "firstModelOperations", null, 1, 1, InitEdgeCreationOperation.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(initialContainerDropOperationEClass, InitialContainerDropOperation.class, "InitialContainerDropOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getInitialContainerDropOperation_FirstModelOperations(), this.getModelOperation(), null, "firstModelOperations", null, 1, 1, InitialContainerDropOperation.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(createInstanceEClass, CreateInstance.class, "CreateInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getCreateInstance_TypeName(), theDescriptionPackage.getTypeName(), "typeName", null, 0, 1, CreateInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getCreateInstance_ReferenceName(), theDescriptionPackage.getFeatureName(), "referenceName", null, 1, 1, CreateInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getCreateInstance_VariableName(), theEcorePackage.getEString(), "variableName", "instance", 0, 1, CreateInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(changeContextEClass, ChangeContext.class, "ChangeContext", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getChangeContext_BrowseExpression(), theDescriptionPackage.getInterpretedExpression(), "browseExpression", null, 0, 1, ChangeContext.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(setValueEClass, SetValue.class, "SetValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSetValue_FeatureName(), theDescriptionPackage.getFeatureName(), "featureName", null, 1, 1, SetValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSetValue_ValueExpression(), theDescriptionPackage.getInterpretedExpression(), "valueExpression", null, 0, 1, SetValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(setObjectEClass, SetObject.class, "SetObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSetObject_FeatureName(), theDescriptionPackage.getFeatureName(), "featureName", null, 1, 1, SetObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getSetObject_Object(), theEcorePackage.getEObject(), null, "object", null, 0, 1, SetObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(unsetEClass, Unset.class, "Unset", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getUnset_FeatureName(), theDescriptionPackage.getFeatureName(), "featureName", "", 1, 1, Unset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getUnset_ElementExpression(), theDescriptionPackage.getInterpretedExpression(), "elementExpression", null, 0, 1, Unset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(moveElementEClass, MoveElement.class, "MoveElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getMoveElement_NewContainerExpression(), theDescriptionPackage.getInterpretedExpression(), "newContainerExpression", null, 1, 1, MoveElement.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMoveElement_FeatureName(), theDescriptionPackage.getFeatureName(), "featureName", null, 1, 1, MoveElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(removeElementEClass, RemoveElement.class, "RemoveElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(forEClass, For.class, "For", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFor_Expression(), theDescriptionPackage.getInterpretedExpression(), "expression", null, 1, 1, For.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFor_IteratorName(), theEcorePackage.getEString(), "iteratorName", "i", 1, 1, For.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        initEClass(createViewEClass, CreateView.class, "CreateView", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCreateView_Mapping(), theDescriptionPackage_1.getDiagramElementMapping(), null, "mapping", null, 1, 1, CreateView.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getCreateView_ContainerViewExpression(), theDescriptionPackage.getInterpretedExpression(), "containerViewExpression", "", 1, 1, CreateView.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getCreateView_VariableName(), theEcorePackage.getEString(), "variableName", "createdView", 0, 1, CreateView.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(createEdgeViewEClass, CreateEdgeView.class, "CreateEdgeView", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getCreateEdgeView_SourceExpression(), theDescriptionPackage.getInterpretedExpression(), "sourceExpression", null, 1, 1, CreateEdgeView.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getCreateEdgeView_TargetExpression(), theDescriptionPackage.getInterpretedExpression(), "targetExpression", null, 1, 1, CreateEdgeView.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(ifEClass, If.class, "If", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getIf_ConditionExpression(), theDescriptionPackage.getInterpretedExpression(), "conditionExpression", null, 0, 1, If.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(deleteViewEClass, DeleteView.class, "DeleteView", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(navigationEClass, Navigation.class, "Navigation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getNavigation_CreateIfNotExistent(), theEcorePackage.getEBoolean(), "createIfNotExistent", null, 0, 1, Navigation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getNavigation_DiagramDescription(), theDescriptionPackage_1.getDiagramDescription(), null, "diagramDescription", null, 1, 1, Navigation.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(nameVariableEClass, NameVariable.class, "NameVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(externalJavaActionParameterEClass, ExternalJavaActionParameter.class, "ExternalJavaActionParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getExternalJavaActionParameter_Name(), theEcorePackage.getEString(), "name", null, 1, 1, ExternalJavaActionParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExternalJavaActionParameter_Value(), theDescriptionPackage.getInterpretedExpression(), "value", null, 0, 1, ExternalJavaActionParameter.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(diagramCreationDescriptionEClass, DiagramCreationDescription.class, "DiagramCreationDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDiagramCreationDescription_DiagramDescription(), theDescriptionPackage_1.getDiagramDescription(), null, "diagramDescription", null, 1, 1, DiagramCreationDescription.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(diagramNavigationDescriptionEClass, DiagramNavigationDescription.class, "DiagramNavigationDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDiagramNavigationDescription_DiagramDescription(), theDescriptionPackage_1.getDiagramDescription(), null, "diagramDescription", null, 1, 1,
                DiagramNavigationDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(toolFilterDescriptionEClass, ToolFilterDescription.class, "ToolFilterDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getToolFilterDescription_Precondition(), theDescriptionPackage.getInterpretedExpression(), "precondition", null, 0, 1, ToolFilterDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getToolFilterDescription_ElementsToListen(), theDescriptionPackage.getInterpretedExpression(), "elementsToListen", null, 0, 1, ToolFilterDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getToolFilterDescription_Listeners(), this.getFeatureChangeListener(), null, "listeners", null, 1, -1, ToolFilterDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(featureChangeListenerEClass, FeatureChangeListener.class, "FeatureChangeListener", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFeatureChangeListener_DomainClass(), theDescriptionPackage.getTypeName(), "domainClass", null, 1, 1, FeatureChangeListener.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFeatureChangeListener_FeatureName(), theDescriptionPackage.getFeatureName(), "featureName", null, 1, 1, FeatureChangeListener.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(caseEClass, Case.class, "Case", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getCase_ConditionExpression(), theDescriptionPackage.getInterpretedExpression(), "conditionExpression", null, 0, 1, Case.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(switchChildEClass, SwitchChild.class, "SwitchChild", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getSwitchChild_SubModelOperations(), this.getModelOperation(), null, "subModelOperations", null, 0, -1, SwitchChild.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(defaultEClass, Default.class, "Default", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(switchEClass, Switch.class, "Switch", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getSwitch_Cases(), this.getCase(), null, "cases", null, 1, -1, Switch.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getSwitch_Default(), this.getDefault(), null, "default", null, 1, 1, Switch.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(reconnectionKindEEnum, ReconnectionKind.class, "ReconnectionKind");
        addEEnumLiteral(reconnectionKindEEnum, ReconnectionKind.RECONNECT_TARGET_LITERAL);
        addEEnumLiteral(reconnectionKindEEnum, ReconnectionKind.RECONNECT_SOURCE_LITERAL);
        addEEnumLiteral(reconnectionKindEEnum, ReconnectionKind.RECONNECT_BOTH_LITERAL);

        initEEnum(dragSourceEEnum, DragSource.class, "DragSource");
        addEEnumLiteral(dragSourceEEnum, DragSource.DIAGRAM_LITERAL);
        addEEnumLiteral(dragSourceEEnum, DragSource.PROJECT_EXPLORER_LITERAL);
        addEEnumLiteral(dragSourceEEnum, DragSource.BOTH_LITERAL);

        // Create annotations
        // http://www.eclipse.org/sirius/interpreted/expression/returnType
        createReturnTypeAnnotations();
        // http://www.eclipse.org/sirius/interpreted/expression/variables
        createVariablesAnnotations();
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
        addAnnotation(getAbstractToolDescription_Precondition(), source, new String[] { "returnType", "a boolean." });
        addAnnotation(getEdgeCreationDescription_ConnectionStartPrecondition(), source, new String[] { "returnType", "a boolean." });
        addAnnotation(getDeleteHookParameter_Value(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." });
        addAnnotation(getPaneBasedSelectionWizardDescription_CandidatesExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." });
        addAnnotation(getPaneBasedSelectionWizardDescription_RootExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." });
        addAnnotation(getPaneBasedSelectionWizardDescription_ChildrenExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." });
        addAnnotation(getPaneBasedSelectionWizardDescription_PreSelectedCandidatesExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." });
        addAnnotation(getRepresentationCreationDescription_TitleExpression(), source, new String[] { "returnType", "a string." });
        addAnnotation(getRepresentationCreationDescription_BrowseExpression(), source, new String[] { "returnType", "an EObject." });
        addAnnotation(getRepresentationNavigationDescription_BrowseExpression(), source, new String[] { "returnType", "an EObject." });
        addAnnotation(getRepresentationNavigationDescription_NavigationNameExpression(), source, new String[] { "returnType", "a string." });
        addAnnotation(getDirectEditLabel_InputLabelExpression(), source, new String[] { "returnType", "a string." });
        addAnnotation(getAcceleoVariable_ComputationExpression(), source, new String[] { "returnType", "a Collection<Object> or an Object." });
        addAnnotation(getChangeContext_BrowseExpression(), source, new String[] { "returnType", "an EObject." });
        addAnnotation(getSetValue_ValueExpression(), source, new String[] { "returnType", "any type supported by the feature." });
        addAnnotation(getUnset_ElementExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." });
        addAnnotation(getMoveElement_NewContainerExpression(), source, new String[] { "returnType", "an EObject." });
        addAnnotation(getFor_Expression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." });
        addAnnotation(getCreateView_ContainerViewExpression(), source, new String[] { "returnType", "a view (DNode, DEdge, DDiagram -> any DSemanticDecorator)." });
        addAnnotation(getCreateEdgeView_SourceExpression(), source, new String[] { "returnType", "an EObject." });
        addAnnotation(getCreateEdgeView_TargetExpression(), source, new String[] { "returnType", "an EObject." });
        addAnnotation(getIf_ConditionExpression(), source, new String[] { "returnType", "a boolean." });
        addAnnotation(getExternalJavaActionParameter_Value(), source, new String[] { "returnType", "any type supported by the corresponding java parameter." });
        addAnnotation(getToolFilterDescription_Precondition(), source, new String[] { "returnType", "a boolean." });
        addAnnotation(getToolFilterDescription_ElementsToListen(), source, new String[] { "returnType", "a collection." });
        addAnnotation(getCase_ConditionExpression(), source, new String[] { "returnType", "a boolean." });
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
        addAnnotation(getAbstractToolDescription_Precondition(), source, new String[] { "container", "ecore.EObject | the container." });
        addAnnotation(getToolDescription_Element(), source, new String[] { "type", "ecore.EObject" });
        addAnnotation(getToolDescription_ElementView(), source, new String[] { "type", "ecore.EObject" });
        addAnnotation(getNodeCreationDescription_Variable(), source, new String[] { "type", "ecore.EObject" });
        addAnnotation(getNodeCreationDescription_ViewVariable(), source, new String[] { "type", "viewpoint.DDiagramElementContainer" });
        addAnnotation(getEdgeCreationDescription_ConnectionStartPrecondition(), source, new String[] { "container", "ecore.EObject | the semantic element of diagram.", "preSourceView",
                "viewpoint.EdgeTarget | (edge only) the source view of the current potential edge.", "preSource", "viewpoint.EdgeTarget | (edge only) the semantic element of $preSourceView.",
                "diagram", "viewpoint.DDiagram | the diagram of the current potential edge" });
        addAnnotation(getDeleteHookParameter_Value(), source, new String[] {});
        addAnnotation(getPaneBasedSelectionWizardDescription_CandidatesExpression(), source, new String[] { "diagram", "viewpoint.DDiagram | the current DDiagram.", "containerView",
                "viewpoint.DSemanticDecorator | the selected view.", "container", "ecore.EObject | the semantic element of containerView." });
        addAnnotation(getPaneBasedSelectionWizardDescription_RootExpression(), source, new String[] { "diagram", "viewpoint.DDiagram | the current DDiagram.", "containerView",
                "viewpoint.DSemanticDecorator | the selected view.", "container", "ecore.EObject | the semantic element of containerView." });
        addAnnotation(getPaneBasedSelectionWizardDescription_ChildrenExpression(), source, new String[] { "diagram", "viewpoint.DDiagram | the current DDiagram.", "containerView",
                "viewpoint.DSemanticDecorator | the selected view.", "container", "ecore.EObject | the semantic element of containerView." });
        addAnnotation(getPaneBasedSelectionWizardDescription_PreSelectedCandidatesExpression(), source, new String[] { "diagram", "viewpoint.DDiagram | the current DDiagram.", "containerView",
                "viewpoint.DSemanticDecorator | the selected view.", "container", "ecore.EObject | the semantic element of containerView." });
        addAnnotation(getRepresentationCreationDescription_TitleExpression(), source, new String[] {});
        addAnnotation(getRepresentationCreationDescription_BrowseExpression(), source, new String[] {});
        addAnnotation(getRepresentationNavigationDescription_BrowseExpression(), source, new String[] {});
        addAnnotation(getRepresentationNavigationDescription_NavigationNameExpression(), source, new String[] { "name", "name of the targeted Representation." });
        addAnnotation(getDirectEditLabel_InputLabelExpression(), source, new String[] { "diagram", "viewpoint.DDiagram | the current DSemanticDiagram.", "view",
                "viewpoint.DDiagramElement | the current view for which the label is calculated." });
        addAnnotation(getAcceleoVariable_ComputationExpression(), source, new String[] {});
        addAnnotation(getChangeContext_BrowseExpression(), source, new String[] {});
        addAnnotation(getSetValue_ValueExpression(), source, new String[] {});
        addAnnotation(getUnset_ElementExpression(), source, new String[] {});
        addAnnotation(getMoveElement_NewContainerExpression(), source, new String[] {});
        addAnnotation(getFor_Expression(), source, new String[] {});
        addAnnotation(getCreateView_ContainerViewExpression(), source, new String[] {});
        addAnnotation(getCreateEdgeView_SourceExpression(), source, new String[] {});
        addAnnotation(getCreateEdgeView_TargetExpression(), source, new String[] {});
        addAnnotation(getIf_ConditionExpression(), source, new String[] {});
        addAnnotation(getExternalJavaActionParameter_Value(), source, new String[] {});
        addAnnotation(getToolFilterDescription_Precondition(), source, new String[] {});
        addAnnotation(getToolFilterDescription_ElementsToListen(), source, new String[] {});
        addAnnotation(getCase_ConditionExpression(), source, new String[] {});
    }

} // ToolPackageImpl
