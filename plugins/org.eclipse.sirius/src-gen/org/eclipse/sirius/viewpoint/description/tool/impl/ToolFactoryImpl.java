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
import org.eclipse.sirius.business.internal.metamodel.description.tool.spec.PasteDescriptionSpec;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.viewpoint.description.tool.AcceleoVariable;
import org.eclipse.sirius.viewpoint.description.tool.Case;
import org.eclipse.sirius.viewpoint.description.tool.ChangeContext;
import org.eclipse.sirius.viewpoint.description.tool.ContainerViewVariable;
import org.eclipse.sirius.viewpoint.description.tool.CreateInstance;
import org.eclipse.sirius.viewpoint.description.tool.Default;
import org.eclipse.sirius.viewpoint.description.tool.DeleteView;
import org.eclipse.sirius.viewpoint.description.tool.DragSource;
import org.eclipse.sirius.viewpoint.description.tool.DropContainerVariable;
import org.eclipse.sirius.viewpoint.description.tool.EditMaskVariables;
import org.eclipse.sirius.viewpoint.description.tool.ElementDeleteVariable;
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
import org.eclipse.sirius.viewpoint.description.tool.OperationAction;
import org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription;
import org.eclipse.sirius.viewpoint.description.tool.PasteDescription;
import org.eclipse.sirius.viewpoint.description.tool.PopupMenu;
import org.eclipse.sirius.viewpoint.description.tool.RemoveElement;
import org.eclipse.sirius.viewpoint.description.tool.SelectContainerVariable;
import org.eclipse.sirius.viewpoint.description.tool.SelectModelElementVariable;
import org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription;
import org.eclipse.sirius.viewpoint.description.tool.SetObject;
import org.eclipse.sirius.viewpoint.description.tool.SetValue;
import org.eclipse.sirius.viewpoint.description.tool.Switch;
import org.eclipse.sirius.viewpoint.description.tool.ToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolFactory;
import org.eclipse.sirius.viewpoint.description.tool.ToolFilterDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
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
        case ToolPackage.TOOL_DESCRIPTION:
            return createToolDescription();
        case ToolPackage.PASTE_DESCRIPTION:
            return createPasteDescription();
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
        case ToolPackage.ACCELEO_VARIABLE:
            return createAcceleoVariable();
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
        case ToolPackage.IF:
            return createIf();
        case ToolPackage.DELETE_VIEW:
            return createDeleteView();
        case ToolPackage.NAME_VARIABLE:
            return createNameVariable();
        case ToolPackage.EXTERNAL_JAVA_ACTION_PARAMETER:
            return createExternalJavaActionParameter();
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
        case ToolPackage.DRAG_SOURCE:
            return createDragSourceFromString(eDataType, initialValue);
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
        case ToolPackage.DRAG_SOURCE:
            return convertDragSourceToString(eDataType, instanceValue);
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
    public ToolDescription createToolDescription() {
        ToolDescriptionImpl toolDescription = new ToolDescriptionImpl();
        ElementVariable elementVar = createElementVariable();
        elementVar.setName("element"); //$NON-NLS-1$
        toolDescription.setElement(elementVar);
        ElementViewVariable elementViewVar = createElementViewVariable();
        elementViewVar.setName("elementView"); //$NON-NLS-1$
        toolDescription.setElementView(elementViewVar);
        toolDescription.setInitialOperation(this.createInitialOperation());
        return toolDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
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
    @Override
    public SelectionWizardDescription createSelectionWizardDescription() {
        SelectionWizardDescriptionImpl selectionWizardDescription = new SelectionWizardDescriptionImpl();
        ElementSelectVariable elementSelectVariable = this.createElementSelectVariable();
        elementSelectVariable.setName("element"); //$NON-NLS-1$
        ContainerViewVariable containerViewVariable = this.createContainerViewVariable();
        containerViewVariable.setName("containerView"); //$NON-NLS-1$
        SelectContainerVariable selectContainerVariable = this.createSelectContainerVariable();
        selectContainerVariable.setName("container"); //$NON-NLS-1$
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
    @Override
    public PaneBasedSelectionWizardDescription createPaneBasedSelectionWizardDescription() {
        PaneBasedSelectionWizardDescriptionImpl paneBasedSelectionWizardDescription = new PaneBasedSelectionWizardDescriptionImpl();
        ElementSelectVariable elementSelectVariable = this.createElementSelectVariable();
        elementSelectVariable.setName("element"); //$NON-NLS-1$
        ContainerViewVariable containerViewVariable = this.createContainerViewVariable();
        containerViewVariable.setName("containerView"); //$NON-NLS-1$
        SelectContainerVariable selectContainerVariable = this.createSelectContainerVariable();
        selectContainerVariable.setName("container"); //$NON-NLS-1$
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
    @Override
    public MenuItemDescriptionReference createMenuItemDescriptionReference() {
        MenuItemDescriptionReferenceImpl menuItemDescriptionReference = new MenuItemDescriptionReferenceImpl();
        return menuItemDescriptionReference;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public OperationAction createOperationAction() {
        OperationActionImpl operationAction = new OperationActionImpl();
        ContainerViewVariable containerViewVariable = this.createContainerViewVariable();
        containerViewVariable.setName("views"); //$NON-NLS-1$
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
    @Override
    public ExternalJavaAction createExternalJavaAction() {
        ExternalJavaActionImpl externalJavaAction = new ExternalJavaActionImpl();
        return externalJavaAction;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ExternalJavaActionCall createExternalJavaActionCall() {
        ExternalJavaActionCallImpl externalJavaActionCall = new ExternalJavaActionCallImpl();
        return externalJavaActionCall;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public PopupMenu createPopupMenu() {
        PopupMenuImpl popupMenu = new PopupMenuImpl();
        return popupMenu;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public AcceleoVariable createAcceleoVariable() {
        AcceleoVariableImpl acceleoVariable = new AcceleoVariableImpl();
        return acceleoVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ElementDropVariable createElementDropVariable() {
        ElementDropVariableImpl elementDropVariable = new ElementDropVariableImpl();
        return elementDropVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ElementSelectVariable createElementSelectVariable() {
        ElementSelectVariableImpl elementSelectVariable = new ElementSelectVariableImpl();
        return elementSelectVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ElementVariable createElementVariable() {
        ElementVariableImpl elementVariable = new ElementVariableImpl();
        return elementVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ElementViewVariable createElementViewVariable() {
        ElementViewVariableImpl elementViewVariable = new ElementViewVariableImpl();
        return elementViewVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ElementDeleteVariable createElementDeleteVariable() {
        ElementDeleteVariableImpl elementDeleteVariable = new ElementDeleteVariableImpl();
        return elementDeleteVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DropContainerVariable createDropContainerVariable() {
        DropContainerVariableImpl dropContainerVariable = new DropContainerVariableImpl();
        return dropContainerVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public SelectContainerVariable createSelectContainerVariable() {
        SelectContainerVariableImpl selectContainerVariable = new SelectContainerVariableImpl();
        return selectContainerVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ContainerViewVariable createContainerViewVariable() {
        ContainerViewVariableImpl containerViewVariable = new ContainerViewVariableImpl();
        return containerViewVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public SelectModelElementVariable createSelectModelElementVariable() {
        SelectModelElementVariableImpl selectModelElementVariable = new SelectModelElementVariableImpl();
        return selectModelElementVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public EditMaskVariables createEditMaskVariables() {
        EditMaskVariablesImpl editMaskVariables = new EditMaskVariablesImpl();
        editMaskVariables.setMask("{0}"); //$NON-NLS-1$
        return editMaskVariables;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public InitialNodeCreationOperation createInitialNodeCreationOperation() {
        InitialNodeCreationOperationImpl initialNodeCreationOperation = new InitialNodeCreationOperationImpl();
        return initialNodeCreationOperation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public InitialOperation createInitialOperation() {
        InitialOperationImpl initialOperation = new InitialOperationImpl();
        return initialOperation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public InitEdgeCreationOperation createInitEdgeCreationOperation() {
        InitEdgeCreationOperationImpl initEdgeCreationOperation = new InitEdgeCreationOperationImpl();
        return initEdgeCreationOperation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public InitialContainerDropOperation createInitialContainerDropOperation() {
        InitialContainerDropOperationImpl initialContainerDropOperation = new InitialContainerDropOperationImpl();
        return initialContainerDropOperation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public CreateInstance createCreateInstance() {
        CreateInstanceImpl createInstance = new CreateInstanceImpl();
        return createInstance;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ChangeContext createChangeContext() {
        ChangeContextImpl changeContext = new ChangeContextImpl();
        return changeContext;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public SetValue createSetValue() {
        SetValueImpl setValue = new SetValueImpl();
        return setValue;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public SetObject createSetObject() {
        SetObjectImpl setObject = new SetObjectImpl();
        return setObject;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Unset createUnset() {
        UnsetImpl unset = new UnsetImpl();
        return unset;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public MoveElement createMoveElement() {
        MoveElementImpl moveElement = new MoveElementImpl();
        return moveElement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public RemoveElement createRemoveElement() {
        RemoveElementImpl removeElement = new RemoveElementImpl();
        return removeElement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public For createFor() {
        ForImpl for_ = new ForImpl();
        return for_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public If createIf() {
        IfImpl if_ = new IfImpl();
        return if_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DeleteView createDeleteView() {
        DeleteViewImpl deleteView = new DeleteViewImpl();
        return deleteView;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NameVariable createNameVariable() {
        NameVariableImpl nameVariable = new NameVariableImpl();
        return nameVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ExternalJavaActionParameter createExternalJavaActionParameter() {
        ExternalJavaActionParameterImpl externalJavaActionParameter = new ExternalJavaActionParameterImpl();
        return externalJavaActionParameter;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ToolFilterDescription createToolFilterDescription() {
        ToolFilterDescriptionImpl toolFilterDescription = new ToolFilterDescriptionImpl();
        return toolFilterDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public FeatureChangeListener createFeatureChangeListener() {
        FeatureChangeListenerImpl featureChangeListener = new FeatureChangeListenerImpl();
        return featureChangeListener;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Case createCase() {
        CaseImpl case_ = new CaseImpl();
        return case_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Default createDefault() {
        DefaultImpl default_ = new DefaultImpl();
        return default_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Switch createSwitch() {
        SwitchImpl switch_ = new SwitchImpl();
        return switch_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DragSource createDragSourceFromString(EDataType eDataType, String initialValue) {
        DragSource result = DragSource.get(initialValue);
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
    public String convertDragSourceToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
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
