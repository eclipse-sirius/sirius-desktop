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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.sirius.business.internal.metamodel.description.tool.spec.ContainerDropDescriptionSpec;
import org.eclipse.sirius.business.internal.metamodel.description.tool.spec.DeleteElementDescriptionSpec;
import org.eclipse.sirius.business.internal.metamodel.description.tool.spec.DiagramCreationDescriptionSpec;
import org.eclipse.sirius.business.internal.metamodel.description.tool.spec.DiagramNavigationDescriptionSpec;
import org.eclipse.sirius.business.internal.metamodel.description.tool.spec.DirectEditLabelSpec;
import org.eclipse.sirius.business.internal.metamodel.description.tool.spec.EdgeCreationDescriptionSpec;
import org.eclipse.sirius.business.internal.metamodel.description.tool.spec.PasteDescriptionSpec;
import org.eclipse.sirius.business.internal.metamodel.description.tool.spec.ReconnectEdgeDescriptionSpec;
import org.eclipse.sirius.business.internal.metamodel.description.tool.spec.ToolSectionSpec;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.viewpoint.description.tool.AcceleoVariable;
import org.eclipse.sirius.viewpoint.description.tool.BehaviorTool;
import org.eclipse.sirius.viewpoint.description.tool.Case;
import org.eclipse.sirius.viewpoint.description.tool.ChangeContext;
import org.eclipse.sirius.viewpoint.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.viewpoint.description.tool.ContainerDropDescription;
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
import org.eclipse.sirius.viewpoint.description.tool.MenuItemDescriptionReference;
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
import org.eclipse.sirius.viewpoint.description.tool.RequestDescription;
import org.eclipse.sirius.viewpoint.description.tool.SelectContainerVariable;
import org.eclipse.sirius.viewpoint.description.tool.SelectModelElementVariable;
import org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription;
import org.eclipse.sirius.viewpoint.description.tool.SetObject;
import org.eclipse.sirius.viewpoint.description.tool.SetValue;
import org.eclipse.sirius.viewpoint.description.tool.SourceEdgeCreationVariable;
import org.eclipse.sirius.viewpoint.description.tool.SourceEdgeViewCreationVariable;
import org.eclipse.sirius.viewpoint.description.tool.Switch;
import org.eclipse.sirius.viewpoint.description.tool.TargetEdgeCreationVariable;
import org.eclipse.sirius.viewpoint.description.tool.TargetEdgeViewCreationVariable;
import org.eclipse.sirius.viewpoint.description.tool.ToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolFactory;
import org.eclipse.sirius.viewpoint.description.tool.ToolFilterDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolGroup;
import org.eclipse.sirius.viewpoint.description.tool.ToolGroupExtension;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.tool.ToolSection;
import org.eclipse.sirius.viewpoint.description.tool.Unset;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class ToolFactoryImpl extends EFactoryImpl implements ToolFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public static ToolFactory init() {
        try {
            ToolFactory theToolFactory = (ToolFactory) EPackage.Registry.INSTANCE.getEFactory(ToolPackage.eNS_URI);
            if (theToolFactory != null) {
                return theToolFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new ToolFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public ToolFactoryImpl() {
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
        case ToolPackage.TOOL_SECTION:
            return createToolSection();
        case ToolPackage.TOOL_GROUP:
            return createToolGroup();
        case ToolPackage.TOOL_GROUP_EXTENSION:
            return createToolGroupExtension();
        case ToolPackage.TOOL_DESCRIPTION:
            return createToolDescription();
        case ToolPackage.NODE_CREATION_DESCRIPTION:
            return createNodeCreationDescription();
        case ToolPackage.EDGE_CREATION_DESCRIPTION:
            return createEdgeCreationDescription();
        case ToolPackage.CONTAINER_CREATION_DESCRIPTION:
            return createContainerCreationDescription();
        case ToolPackage.CONTAINER_DROP_DESCRIPTION:
            return createContainerDropDescription();
        case ToolPackage.PASTE_DESCRIPTION:
            return createPasteDescription();
        case ToolPackage.DELETE_ELEMENT_DESCRIPTION:
            return createDeleteElementDescription();
        case ToolPackage.DOUBLE_CLICK_DESCRIPTION:
            return createDoubleClickDescription();
        case ToolPackage.DELETE_HOOK:
            return createDeleteHook();
        case ToolPackage.DELETE_HOOK_PARAMETER:
            return createDeleteHookParameter();
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION:
            return createReconnectEdgeDescription();
        case ToolPackage.REQUEST_DESCRIPTION:
            return createRequestDescription();
        case ToolPackage.SELECTION_WIZARD_DESCRIPTION:
            return createSelectionWizardDescription();
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION:
            return createPaneBasedSelectionWizardDescription();
        case ToolPackage.MENU_ITEM_DESCRIPTION_REFERENCE:
            return createMenuItemDescriptionReference();
        case ToolPackage.OPERATION_ACTION:
            return createOperationAction();
        case ToolPackage.EXTERNAL_JAVA_ACTION:
            return createExternalJavaAction();
        case ToolPackage.EXTERNAL_JAVA_ACTION_CALL:
            return createExternalJavaActionCall();
        case ToolPackage.POPUP_MENU:
            return createPopupMenu();
        case ToolPackage.DIRECT_EDIT_LABEL:
            return createDirectEditLabel();
        case ToolPackage.BEHAVIOR_TOOL:
            return createBehaviorTool();
        case ToolPackage.ACCELEO_VARIABLE:
            return createAcceleoVariable();
        case ToolPackage.SOURCE_EDGE_CREATION_VARIABLE:
            return createSourceEdgeCreationVariable();
        case ToolPackage.SOURCE_EDGE_VIEW_CREATION_VARIABLE:
            return createSourceEdgeViewCreationVariable();
        case ToolPackage.TARGET_EDGE_CREATION_VARIABLE:
            return createTargetEdgeCreationVariable();
        case ToolPackage.TARGET_EDGE_VIEW_CREATION_VARIABLE:
            return createTargetEdgeViewCreationVariable();
        case ToolPackage.ELEMENT_DROP_VARIABLE:
            return createElementDropVariable();
        case ToolPackage.ELEMENT_SELECT_VARIABLE:
            return createElementSelectVariable();
        case ToolPackage.ELEMENT_VARIABLE:
            return createElementVariable();
        case ToolPackage.ELEMENT_VIEW_VARIABLE:
            return createElementViewVariable();
        case ToolPackage.ELEMENT_DELETE_VARIABLE:
            return createElementDeleteVariable();
        case ToolPackage.ELEMENT_DOUBLE_CLICK_VARIABLE:
            return createElementDoubleClickVariable();
        case ToolPackage.NODE_CREATION_VARIABLE:
            return createNodeCreationVariable();
        case ToolPackage.DROP_CONTAINER_VARIABLE:
            return createDropContainerVariable();
        case ToolPackage.SELECT_CONTAINER_VARIABLE:
            return createSelectContainerVariable();
        case ToolPackage.CONTAINER_VIEW_VARIABLE:
            return createContainerViewVariable();
        case ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE:
            return createSelectModelElementVariable();
        case ToolPackage.EDIT_MASK_VARIABLES:
            return createEditMaskVariables();
        case ToolPackage.INITIAL_NODE_CREATION_OPERATION:
            return createInitialNodeCreationOperation();
        case ToolPackage.INITIAL_OPERATION:
            return createInitialOperation();
        case ToolPackage.INIT_EDGE_CREATION_OPERATION:
            return createInitEdgeCreationOperation();
        case ToolPackage.INITIAL_CONTAINER_DROP_OPERATION:
            return createInitialContainerDropOperation();
        case ToolPackage.CREATE_INSTANCE:
            return createCreateInstance();
        case ToolPackage.CHANGE_CONTEXT:
            return createChangeContext();
        case ToolPackage.SET_VALUE:
            return createSetValue();
        case ToolPackage.SET_OBJECT:
            return createSetObject();
        case ToolPackage.UNSET:
            return createUnset();
        case ToolPackage.MOVE_ELEMENT:
            return createMoveElement();
        case ToolPackage.REMOVE_ELEMENT:
            return createRemoveElement();
        case ToolPackage.FOR:
            return createFor();
        case ToolPackage.CREATE_VIEW:
            return createCreateView();
        case ToolPackage.CREATE_EDGE_VIEW:
            return createCreateEdgeView();
        case ToolPackage.IF:
            return createIf();
        case ToolPackage.DELETE_VIEW:
            return createDeleteView();
        case ToolPackage.NAVIGATION:
            return createNavigation();
        case ToolPackage.NAME_VARIABLE:
            return createNameVariable();
        case ToolPackage.EXTERNAL_JAVA_ACTION_PARAMETER:
            return createExternalJavaActionParameter();
        case ToolPackage.DIAGRAM_CREATION_DESCRIPTION:
            return createDiagramCreationDescription();
        case ToolPackage.DIAGRAM_NAVIGATION_DESCRIPTION:
            return createDiagramNavigationDescription();
        case ToolPackage.TOOL_FILTER_DESCRIPTION:
            return createToolFilterDescription();
        case ToolPackage.FEATURE_CHANGE_LISTENER:
            return createFeatureChangeListener();
        case ToolPackage.CASE:
            return createCase();
        case ToolPackage.DEFAULT:
            return createDefault();
        case ToolPackage.SWITCH:
            return createSwitch();
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
        case ToolPackage.RECONNECTION_KIND:
            return createReconnectionKindFromString(eDataType, initialValue);
        case ToolPackage.DRAG_SOURCE:
            return createDragSourceFromString(eDataType, initialValue);
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
        case ToolPackage.RECONNECTION_KIND:
            return convertReconnectionKindToString(eDataType, instanceValue);
        case ToolPackage.DRAG_SOURCE:
            return convertDragSourceToString(eDataType, instanceValue);
        default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public ToolSection createToolSection() {
        ToolSectionImpl toolSection = new ToolSectionSpec();
        return toolSection;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ToolGroup createToolGroup() {
        ToolGroupImpl toolGroup = new ToolGroupImpl();
        return toolGroup;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ToolGroupExtension createToolGroupExtension() {
        ToolGroupExtensionImpl toolGroupExtension = new ToolGroupExtensionImpl();
        return toolGroupExtension;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public ToolDescription createToolDescription() {
        ToolDescriptionImpl toolDescription = new ToolDescriptionImpl();
        ElementVariable elementVar = createElementVariable();
        elementVar.setName("element");
        toolDescription.setElement(elementVar);
        ElementViewVariable elementViewVar = createElementViewVariable();
        elementViewVar.setName("elementView");
        toolDescription.setElementView(elementViewVar);
        toolDescription.setInitialOperation(this.createInitialOperation());
        return toolDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public NodeCreationDescription createNodeCreationDescription() {
        NodeCreationDescriptionImpl nodeCreationDescription = new NodeCreationDescriptionImpl();
        NodeCreationVariable defaultVariable = createNodeCreationVariable();
        defaultVariable.setName("container");
        ContainerViewVariable containerViewVariable = createContainerViewVariable();
        containerViewVariable.setName("containerView");
        nodeCreationDescription.setVariable(defaultVariable);
        nodeCreationDescription.setViewVariable(containerViewVariable);
        InitialNodeCreationOperation init = createInitialNodeCreationOperation();
        nodeCreationDescription.setInitialOperation(init);
        return nodeCreationDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public EdgeCreationDescription createEdgeCreationDescription() {
        EdgeCreationDescriptionImpl edgeCreationDescription = new EdgeCreationDescriptionSpec();

        SourceEdgeCreationVariable sourceVariable = createSourceEdgeCreationVariable();
        sourceVariable.setName("source");

        TargetEdgeCreationVariable targetVariable = createTargetEdgeCreationVariable();
        targetVariable.setName("target");

        SourceEdgeViewCreationVariable sourceEdgeViewVariable = createSourceEdgeViewCreationVariable();
        sourceEdgeViewVariable.setName("sourceView");

        TargetEdgeViewCreationVariable targetEdgeViewVariable = createTargetEdgeViewCreationVariable();
        targetEdgeViewVariable.setName("targetView");

        edgeCreationDescription.setSourceVariable(sourceVariable);
        edgeCreationDescription.setTargetVariable(targetVariable);
        edgeCreationDescription.setSourceViewVariable(sourceEdgeViewVariable);
        edgeCreationDescription.setTargetViewVariable(targetEdgeViewVariable);

        InitEdgeCreationOperation init = createInitEdgeCreationOperation();
        edgeCreationDescription.setInitialOperation(init);

        return edgeCreationDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public ContainerCreationDescription createContainerCreationDescription() {
        ContainerCreationDescriptionImpl containerCreationDescription = new ContainerCreationDescriptionImpl();
        NodeCreationVariable defaultVariable = createNodeCreationVariable();
        defaultVariable.setName("container");
        ContainerViewVariable containerViewVariable = createContainerViewVariable();
        containerViewVariable.setName("containerView");
        containerCreationDescription.setVariable(defaultVariable);
        containerCreationDescription.setViewVariable(containerViewVariable);
        InitialNodeCreationOperation init = createInitialNodeCreationOperation();
        containerCreationDescription.setInitialOperation(init);
        return containerCreationDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public ContainerDropDescription createContainerDropDescription() {
        ContainerDropDescriptionImpl containerDropDescription = new ContainerDropDescriptionSpec();
        DropContainerVariable oldContainerVariable = createDropContainerVariable();
        oldContainerVariable.setName("oldSemanticContainer");
        DropContainerVariable newContainerVariable = createDropContainerVariable();
        newContainerVariable.setName("newSemanticContainer");
        ElementDropVariable elementDropVariable = createElementDropVariable();
        elementDropVariable.setName("element");
        ContainerViewVariable containerViewVariable = createContainerViewVariable();
        containerViewVariable.setName("newContainerView");

        containerDropDescription.setElement(elementDropVariable);
        containerDropDescription.setNewContainer(newContainerVariable);
        containerDropDescription.setNewViewContainer(containerViewVariable);
        containerDropDescription.setOldContainer(oldContainerVariable);

        InitialContainerDropOperation init = createInitialContainerDropOperation();
        containerDropDescription.setInitialOperation(init);

        return containerDropDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public PasteDescription createPasteDescription() {
        PasteDescriptionImpl pasteDescription = new PasteDescriptionSpec();

        ContainerViewVariable newViewContainer = createContainerViewVariable();
        newViewContainer.setName(IInterpreterSiriusVariables.CONTAINER_VIEW);
        DropContainerVariable newContainer = createDropContainerVariable();
        newContainer.setName(IInterpreterSiriusVariables.CONTAINER);

        ElementVariable copiedElement = createElementVariable();
        copiedElement.setName(IInterpreterSiriusVariables.COPIED_ELEMENT);
        ElementViewVariable copiedView = createElementViewVariable();
        copiedView.setName(IInterpreterSiriusVariables.COPIED_VIEW);

        pasteDescription.setCopiedElement(copiedElement);
        pasteDescription.setCopiedView(copiedView);
        pasteDescription.setContainer(newContainer);
        pasteDescription.setContainerView(newViewContainer);

        pasteDescription.setInitialOperation(createInitialOperation());

        // Force refresh by default
        pasteDescription.setForceRefresh(true);

        return pasteDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public DeleteElementDescription createDeleteElementDescription() {
        DeleteElementDescriptionImpl deleteElementDescription = new DeleteElementDescriptionSpec();
        ElementDeleteVariable elementDeleteVariable = createElementDeleteVariable();
        elementDeleteVariable.setName("element");
        deleteElementDescription.setElement(elementDeleteVariable);
        ContainerViewVariable containerViewVariable = createContainerViewVariable();
        containerViewVariable.setName("containerView");
        deleteElementDescription.setContainerView(containerViewVariable);
        ElementDeleteVariable elementDeleteVariable2 = createElementDeleteVariable();
        elementDeleteVariable2.setName("elementView");
        deleteElementDescription.setElementView(elementDeleteVariable2);
        InitialOperation init = createInitialOperation();
        deleteElementDescription.setInitialOperation(init);
        return deleteElementDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public DoubleClickDescription createDoubleClickDescription() {
        DoubleClickDescriptionImpl doubleClickDescription = new DoubleClickDescriptionImpl();
        ElementDoubleClickVariable elementDoubleClickVariable = createElementDoubleClickVariable();
        elementDoubleClickVariable.setName("element");
        doubleClickDescription.setElement(elementDoubleClickVariable);
        ElementDoubleClickVariable elementViewDoubleClickVariable = createElementDoubleClickVariable();
        elementViewDoubleClickVariable.setName("elementView");
        doubleClickDescription.setElementView(elementViewDoubleClickVariable);
        InitialOperation init = createInitialOperation();
        doubleClickDescription.setInitialOperation(init);
        return doubleClickDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DeleteHook createDeleteHook() {
        DeleteHookImpl deleteHook = new DeleteHookImpl();
        return deleteHook;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DeleteHookParameter createDeleteHookParameter() {
        DeleteHookParameterImpl deleteHookParameter = new DeleteHookParameterImpl();
        return deleteHookParameter;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public ReconnectEdgeDescription createReconnectEdgeDescription() {
        ReconnectEdgeDescriptionImpl reconnectEdgeDescription = new ReconnectEdgeDescriptionSpec();
        ElementSelectVariable elementSelectVariable = createElementSelectVariable();
        elementSelectVariable.setName("element");
        reconnectEdgeDescription.setElement(elementSelectVariable);
        SourceEdgeCreationVariable sourceEdgeCreationVariable = createSourceEdgeCreationVariable();
        sourceEdgeCreationVariable.setName("source");
        reconnectEdgeDescription.setSource(sourceEdgeCreationVariable);
        SourceEdgeViewCreationVariable sourceEdgeViewCreationVariable = createSourceEdgeViewCreationVariable();
        sourceEdgeViewCreationVariable.setName("sourceView");
        reconnectEdgeDescription.setSourceView(sourceEdgeViewCreationVariable);
        TargetEdgeCreationVariable targetEdgeCreationVariable = createTargetEdgeCreationVariable();
        targetEdgeCreationVariable.setName("target");
        reconnectEdgeDescription.setTarget(targetEdgeCreationVariable);
        TargetEdgeViewCreationVariable targetEdgeViewCreationVariable = createTargetEdgeViewCreationVariable();
        targetEdgeViewCreationVariable.setName("targetView");
        reconnectEdgeDescription.setTargetView(targetEdgeViewCreationVariable);
        ElementSelectVariable edgeVariable = createElementSelectVariable();
        edgeVariable.setName("edgeView");
        reconnectEdgeDescription.setEdgeView(edgeVariable);
        InitialOperation initialOperation = createInitialOperation();
        reconnectEdgeDescription.setInitialOperation(initialOperation);
        return reconnectEdgeDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public RequestDescription createRequestDescription() {
        RequestDescriptionImpl requestDescription = new RequestDescriptionImpl();
        return requestDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public SelectionWizardDescription createSelectionWizardDescription() {
        SelectionWizardDescriptionImpl selectionWizardDescription = new SelectionWizardDescriptionImpl();
        ElementSelectVariable elementSelectVariable = this.createElementSelectVariable();
        elementSelectVariable.setName("element");
        ContainerViewVariable containerViewVariable = this.createContainerViewVariable();
        containerViewVariable.setName("containerView");
        SelectContainerVariable selectContainerVariable = this.createSelectContainerVariable();
        selectContainerVariable.setName("container");
        selectionWizardDescription.setElement(elementSelectVariable);
        selectionWizardDescription.setContainer(selectContainerVariable);
        selectionWizardDescription.setContainerView(containerViewVariable);
        InitialOperation initialOperation = this.createInitialOperation();
        selectionWizardDescription.setInitialOperation(initialOperation);
        return selectionWizardDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public PaneBasedSelectionWizardDescription createPaneBasedSelectionWizardDescription() {
        PaneBasedSelectionWizardDescriptionImpl paneBasedSelectionWizardDescription = new PaneBasedSelectionWizardDescriptionImpl();
        ElementSelectVariable elementSelectVariable = this.createElementSelectVariable();
        elementSelectVariable.setName("element");
        ContainerViewVariable containerViewVariable = this.createContainerViewVariable();
        containerViewVariable.setName("containerView");
        SelectContainerVariable selectContainerVariable = this.createSelectContainerVariable();
        selectContainerVariable.setName("container");
        paneBasedSelectionWizardDescription.setElement(elementSelectVariable);
        paneBasedSelectionWizardDescription.setContainer(selectContainerVariable);
        paneBasedSelectionWizardDescription.setContainerView(containerViewVariable);
        InitialOperation initialOperation = this.createInitialOperation();
        paneBasedSelectionWizardDescription.setInitialOperation(initialOperation);
        return paneBasedSelectionWizardDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public MenuItemDescriptionReference createMenuItemDescriptionReference() {
        MenuItemDescriptionReferenceImpl menuItemDescriptionReference = new MenuItemDescriptionReferenceImpl();
        return menuItemDescriptionReference;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public OperationAction createOperationAction() {
        OperationActionImpl operationAction = new OperationActionImpl();
        ContainerViewVariable containerViewVariable = this.createContainerViewVariable();
        containerViewVariable.setName("views");
        InitialOperation initalOperation = this.createInitialOperation();
        operationAction.setView(containerViewVariable);
        operationAction.setInitialOperation(initalOperation);
        return operationAction;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ExternalJavaAction createExternalJavaAction() {
        ExternalJavaActionImpl externalJavaAction = new ExternalJavaActionImpl();
        return externalJavaAction;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ExternalJavaActionCall createExternalJavaActionCall() {
        ExternalJavaActionCallImpl externalJavaActionCall = new ExternalJavaActionCallImpl();
        return externalJavaActionCall;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public PopupMenu createPopupMenu() {
        PopupMenuImpl popupMenu = new PopupMenuImpl();
        return popupMenu;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public DirectEditLabel createDirectEditLabel() {
        DirectEditLabelImpl directEditLabel = new DirectEditLabelSpec();
        InitialOperation initialOperation = createInitialOperation();
        directEditLabel.setInitialOperation(initialOperation);
        EditMaskVariables editMaskVariables = createEditMaskVariables();
        directEditLabel.setMask(editMaskVariables);
        return directEditLabel;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public BehaviorTool createBehaviorTool() {
        BehaviorToolImpl behaviorTool = new BehaviorToolImpl();
        InitialOperation init = createInitialOperation();
        behaviorTool.setInitialOperation(init);
        return behaviorTool;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public AcceleoVariable createAcceleoVariable() {
        AcceleoVariableImpl acceleoVariable = new AcceleoVariableImpl();
        return acceleoVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public SourceEdgeCreationVariable createSourceEdgeCreationVariable() {
        SourceEdgeCreationVariableImpl sourceEdgeCreationVariable = new SourceEdgeCreationVariableImpl();
        return sourceEdgeCreationVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public SourceEdgeViewCreationVariable createSourceEdgeViewCreationVariable() {
        SourceEdgeViewCreationVariableImpl sourceEdgeViewCreationVariable = new SourceEdgeViewCreationVariableImpl();
        return sourceEdgeViewCreationVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TargetEdgeCreationVariable createTargetEdgeCreationVariable() {
        TargetEdgeCreationVariableImpl targetEdgeCreationVariable = new TargetEdgeCreationVariableImpl();
        return targetEdgeCreationVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TargetEdgeViewCreationVariable createTargetEdgeViewCreationVariable() {
        TargetEdgeViewCreationVariableImpl targetEdgeViewCreationVariable = new TargetEdgeViewCreationVariableImpl();
        return targetEdgeViewCreationVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ElementDropVariable createElementDropVariable() {
        ElementDropVariableImpl elementDropVariable = new ElementDropVariableImpl();
        return elementDropVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ElementSelectVariable createElementSelectVariable() {
        ElementSelectVariableImpl elementSelectVariable = new ElementSelectVariableImpl();
        return elementSelectVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ElementVariable createElementVariable() {
        ElementVariableImpl elementVariable = new ElementVariableImpl();
        return elementVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ElementViewVariable createElementViewVariable() {
        ElementViewVariableImpl elementViewVariable = new ElementViewVariableImpl();
        return elementViewVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ElementDeleteVariable createElementDeleteVariable() {
        ElementDeleteVariableImpl elementDeleteVariable = new ElementDeleteVariableImpl();
        return elementDeleteVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ElementDoubleClickVariable createElementDoubleClickVariable() {
        ElementDoubleClickVariableImpl elementDoubleClickVariable = new ElementDoubleClickVariableImpl();
        return elementDoubleClickVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NodeCreationVariable createNodeCreationVariable() {
        NodeCreationVariableImpl nodeCreationVariable = new NodeCreationVariableImpl();
        return nodeCreationVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DropContainerVariable createDropContainerVariable() {
        DropContainerVariableImpl dropContainerVariable = new DropContainerVariableImpl();
        return dropContainerVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public SelectContainerVariable createSelectContainerVariable() {
        SelectContainerVariableImpl selectContainerVariable = new SelectContainerVariableImpl();
        return selectContainerVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ContainerViewVariable createContainerViewVariable() {
        ContainerViewVariableImpl containerViewVariable = new ContainerViewVariableImpl();
        return containerViewVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public SelectModelElementVariable createSelectModelElementVariable() {
        SelectModelElementVariableImpl selectModelElementVariable = new SelectModelElementVariableImpl();
        return selectModelElementVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public EditMaskVariables createEditMaskVariables() {
        EditMaskVariablesImpl editMaskVariables = new EditMaskVariablesImpl();
        editMaskVariables.setMask("{0}");
        return editMaskVariables;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public InitialNodeCreationOperation createInitialNodeCreationOperation() {
        InitialNodeCreationOperationImpl initialNodeCreationOperation = new InitialNodeCreationOperationImpl();
        return initialNodeCreationOperation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public InitialOperation createInitialOperation() {
        InitialOperationImpl initialOperation = new InitialOperationImpl();
        return initialOperation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public InitEdgeCreationOperation createInitEdgeCreationOperation() {
        InitEdgeCreationOperationImpl initEdgeCreationOperation = new InitEdgeCreationOperationImpl();
        return initEdgeCreationOperation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public InitialContainerDropOperation createInitialContainerDropOperation() {
        InitialContainerDropOperationImpl initialContainerDropOperation = new InitialContainerDropOperationImpl();
        return initialContainerDropOperation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public CreateInstance createCreateInstance() {
        CreateInstanceImpl createInstance = new CreateInstanceImpl();
        return createInstance;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ChangeContext createChangeContext() {
        ChangeContextImpl changeContext = new ChangeContextImpl();
        return changeContext;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public SetValue createSetValue() {
        SetValueImpl setValue = new SetValueImpl();
        return setValue;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public SetObject createSetObject() {
        SetObjectImpl setObject = new SetObjectImpl();
        return setObject;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Unset createUnset() {
        UnsetImpl unset = new UnsetImpl();
        return unset;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public MoveElement createMoveElement() {
        MoveElementImpl moveElement = new MoveElementImpl();
        return moveElement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public RemoveElement createRemoveElement() {
        RemoveElementImpl removeElement = new RemoveElementImpl();
        return removeElement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public For createFor() {
        ForImpl for_ = new ForImpl();
        return for_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public CreateView createCreateView() {
        CreateViewImpl createView = new CreateViewImpl();
        return createView;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public CreateEdgeView createCreateEdgeView() {
        CreateEdgeViewImpl createEdgeView = new CreateEdgeViewImpl();
        return createEdgeView;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public If createIf() {
        IfImpl if_ = new IfImpl();
        return if_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DeleteView createDeleteView() {
        DeleteViewImpl deleteView = new DeleteViewImpl();
        return deleteView;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Navigation createNavigation() {
        NavigationImpl navigation = new NavigationImpl();
        return navigation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NameVariable createNameVariable() {
        NameVariableImpl nameVariable = new NameVariableImpl();
        return nameVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ExternalJavaActionParameter createExternalJavaActionParameter() {
        ExternalJavaActionParameterImpl externalJavaActionParameter = new ExternalJavaActionParameterImpl();
        return externalJavaActionParameter;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public DiagramCreationDescription createDiagramCreationDescription() {
        DiagramCreationDescriptionImpl diagramCreationDescription = new DiagramCreationDescriptionSpec();
        ContainerViewVariable containerViewVariable = createContainerViewVariable();
        containerViewVariable.setName("containerView");
        diagramCreationDescription.setContainerViewVariable(containerViewVariable);
        NameVariable diagramNameVariable = createNameVariable();
        diagramNameVariable.setName("diagramName");
        diagramCreationDescription.setRepresentationNameVariable(diagramNameVariable);
        InitialOperation init = createInitialOperation();
        diagramCreationDescription.setInitialOperation(init);
        return diagramCreationDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public DiagramNavigationDescription createDiagramNavigationDescription() {
        DiagramNavigationDescriptionImpl diagramNavigationDescription = new DiagramNavigationDescriptionSpec();
        ContainerViewVariable containerViewVariable = createContainerViewVariable();
        containerViewVariable.setName("containerView");
        diagramNavigationDescription.setContainerViewVariable(containerViewVariable);
        ElementSelectVariable containerVariable = createElementSelectVariable();
        containerVariable.setName("container");
        diagramNavigationDescription.setContainerVariable(containerVariable);
        NameVariable diagramNameVariable = createNameVariable();
        diagramNameVariable.setName("diagramName");
        diagramNavigationDescription.setRepresentationNameVariable(diagramNameVariable);
        return diagramNavigationDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ToolFilterDescription createToolFilterDescription() {
        ToolFilterDescriptionImpl toolFilterDescription = new ToolFilterDescriptionImpl();
        return toolFilterDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public FeatureChangeListener createFeatureChangeListener() {
        FeatureChangeListenerImpl featureChangeListener = new FeatureChangeListenerImpl();
        return featureChangeListener;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Case createCase() {
        CaseImpl case_ = new CaseImpl();
        return case_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Default createDefault() {
        DefaultImpl default_ = new DefaultImpl();
        return default_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Switch createSwitch() {
        SwitchImpl switch_ = new SwitchImpl();
        return switch_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ReconnectionKind createReconnectionKindFromString(EDataType eDataType, String initialValue) {
        ReconnectionKind result = ReconnectionKind.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertReconnectionKindToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DragSource createDragSourceFromString(EDataType eDataType, String initialValue) {
        DragSource result = DragSource.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertDragSourceToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ToolPackage getToolPackage() {
        return (ToolPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @deprecated
     * @generated
     */
    @Deprecated
    public static ToolPackage getPackage() {
        return ToolPackage.eINSTANCE;
    }

} // ToolFactoryImpl
