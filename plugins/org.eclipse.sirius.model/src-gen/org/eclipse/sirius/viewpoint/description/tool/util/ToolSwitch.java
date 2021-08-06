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
package org.eclipse.sirius.viewpoint.description.tool.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.description.AbstractVariable;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.InteractiveVariableDescription;
import org.eclipse.sirius.viewpoint.description.SelectionDescription;
import org.eclipse.sirius.viewpoint.description.SubVariable;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.AcceleoVariable;
import org.eclipse.sirius.viewpoint.description.tool.Case;
import org.eclipse.sirius.viewpoint.description.tool.ChangeContext;
import org.eclipse.sirius.viewpoint.description.tool.ContainerModelOperation;
import org.eclipse.sirius.viewpoint.description.tool.ContainerViewVariable;
import org.eclipse.sirius.viewpoint.description.tool.CreateInstance;
import org.eclipse.sirius.viewpoint.description.tool.Default;
import org.eclipse.sirius.viewpoint.description.tool.DeleteView;
import org.eclipse.sirius.viewpoint.description.tool.DialogVariable;
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
import org.eclipse.sirius.viewpoint.description.tool.GroupMenu;
import org.eclipse.sirius.viewpoint.description.tool.GroupMenuItem;
import org.eclipse.sirius.viewpoint.description.tool.If;
import org.eclipse.sirius.viewpoint.description.tool.InitEdgeCreationOperation;
import org.eclipse.sirius.viewpoint.description.tool.InitialContainerDropOperation;
import org.eclipse.sirius.viewpoint.description.tool.InitialNodeCreationOperation;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;
import org.eclipse.sirius.viewpoint.description.tool.Let;
import org.eclipse.sirius.viewpoint.description.tool.MappingBasedToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.MenuItemDescription;
import org.eclipse.sirius.viewpoint.description.tool.MenuItemDescriptionReference;
import org.eclipse.sirius.viewpoint.description.tool.MenuItemDescriptionWithIcon;
import org.eclipse.sirius.viewpoint.description.tool.MenuItemOrRef;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;
import org.eclipse.sirius.viewpoint.description.tool.MoveElement;
import org.eclipse.sirius.viewpoint.description.tool.NameVariable;
import org.eclipse.sirius.viewpoint.description.tool.OperationAction;
import org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription;
import org.eclipse.sirius.viewpoint.description.tool.PasteDescription;
import org.eclipse.sirius.viewpoint.description.tool.PopupMenu;
import org.eclipse.sirius.viewpoint.description.tool.RemoveElement;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription;
import org.eclipse.sirius.viewpoint.description.tool.SelectContainerVariable;
import org.eclipse.sirius.viewpoint.description.tool.SelectModelElementVariable;
import org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription;
import org.eclipse.sirius.viewpoint.description.tool.SetObject;
import org.eclipse.sirius.viewpoint.description.tool.SetValue;
import org.eclipse.sirius.viewpoint.description.tool.Switch;
import org.eclipse.sirius.viewpoint.description.tool.SwitchChild;
import org.eclipse.sirius.viewpoint.description.tool.ToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolEntry;
import org.eclipse.sirius.viewpoint.description.tool.ToolFilterDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.tool.Unset;
import org.eclipse.sirius.viewpoint.description.tool.VariableContainer;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the call
 * {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the inheritance hierarchy until a non-null result is
 * returned, which is the result of the switch. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage
 * @generated
 */
public class ToolSwitch<T> {
    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static ToolPackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ToolSwitch() {
        if (ToolSwitch.modelPackage == null) {
            ToolSwitch.modelPackage = ToolPackage.eINSTANCE;
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that
     * result. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    public T doSwitch(EObject theEObject) {
        return doSwitch(theEObject.eClass(), theEObject);
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that
     * result. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected T doSwitch(EClass theEClass, EObject theEObject) {
        if (theEClass.eContainer() == ToolSwitch.modelPackage) {
            return doSwitch(theEClass.getClassifierID(), theEObject);
        } else {
            List<EClass> eSuperTypes = theEClass.getESuperTypes();
            return eSuperTypes.isEmpty() ? defaultCase(theEObject) : doSwitch(eSuperTypes.get(0), theEObject);
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that
     * result. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
        case ToolPackage.TOOL_ENTRY: {
            ToolEntry toolEntry = (ToolEntry) theEObject;
            T result = caseToolEntry(toolEntry);
            if (result == null) {
                result = caseDocumentedElement(toolEntry);
            }
            if (result == null) {
                result = caseIdentifiedElement(toolEntry);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.ABSTRACT_TOOL_DESCRIPTION: {
            AbstractToolDescription abstractToolDescription = (AbstractToolDescription) theEObject;
            T result = caseAbstractToolDescription(abstractToolDescription);
            if (result == null) {
                result = caseToolEntry(abstractToolDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(abstractToolDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(abstractToolDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION: {
            MappingBasedToolDescription mappingBasedToolDescription = (MappingBasedToolDescription) theEObject;
            T result = caseMappingBasedToolDescription(mappingBasedToolDescription);
            if (result == null) {
                result = caseAbstractToolDescription(mappingBasedToolDescription);
            }
            if (result == null) {
                result = caseToolEntry(mappingBasedToolDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(mappingBasedToolDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(mappingBasedToolDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.TOOL_DESCRIPTION: {
            ToolDescription toolDescription = (ToolDescription) theEObject;
            T result = caseToolDescription(toolDescription);
            if (result == null) {
                result = caseMappingBasedToolDescription(toolDescription);
            }
            if (result == null) {
                result = caseAbstractToolDescription(toolDescription);
            }
            if (result == null) {
                result = caseToolEntry(toolDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(toolDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(toolDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.PASTE_DESCRIPTION: {
            PasteDescription pasteDescription = (PasteDescription) theEObject;
            T result = casePasteDescription(pasteDescription);
            if (result == null) {
                result = caseMappingBasedToolDescription(pasteDescription);
            }
            if (result == null) {
                result = caseAbstractToolDescription(pasteDescription);
            }
            if (result == null) {
                result = caseToolEntry(pasteDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(pasteDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(pasteDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.SELECTION_WIZARD_DESCRIPTION: {
            SelectionWizardDescription selectionWizardDescription = (SelectionWizardDescription) theEObject;
            T result = caseSelectionWizardDescription(selectionWizardDescription);
            if (result == null) {
                result = caseAbstractToolDescription(selectionWizardDescription);
            }
            if (result == null) {
                result = caseSelectionDescription(selectionWizardDescription);
            }
            if (result == null) {
                result = caseToolEntry(selectionWizardDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(selectionWizardDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(selectionWizardDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION: {
            PaneBasedSelectionWizardDescription paneBasedSelectionWizardDescription = (PaneBasedSelectionWizardDescription) theEObject;
            T result = casePaneBasedSelectionWizardDescription(paneBasedSelectionWizardDescription);
            if (result == null) {
                result = caseAbstractToolDescription(paneBasedSelectionWizardDescription);
            }
            if (result == null) {
                result = caseToolEntry(paneBasedSelectionWizardDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(paneBasedSelectionWizardDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(paneBasedSelectionWizardDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.REPRESENTATION_CREATION_DESCRIPTION: {
            RepresentationCreationDescription representationCreationDescription = (RepresentationCreationDescription) theEObject;
            T result = caseRepresentationCreationDescription(representationCreationDescription);
            if (result == null) {
                result = caseAbstractToolDescription(representationCreationDescription);
            }
            if (result == null) {
                result = caseToolEntry(representationCreationDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(representationCreationDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(representationCreationDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION: {
            RepresentationNavigationDescription representationNavigationDescription = (RepresentationNavigationDescription) theEObject;
            T result = caseRepresentationNavigationDescription(representationNavigationDescription);
            if (result == null) {
                result = caseAbstractToolDescription(representationNavigationDescription);
            }
            if (result == null) {
                result = caseToolEntry(representationNavigationDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(representationNavigationDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(representationNavigationDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.MENU_ITEM_OR_REF: {
            MenuItemOrRef menuItemOrRef = (MenuItemOrRef) theEObject;
            T result = caseMenuItemOrRef(menuItemOrRef);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.MENU_ITEM_DESCRIPTION: {
            MenuItemDescription menuItemDescription = (MenuItemDescription) theEObject;
            T result = caseMenuItemDescription(menuItemDescription);
            if (result == null) {
                result = caseAbstractToolDescription(menuItemDescription);
            }
            if (result == null) {
                result = caseMenuItemOrRef(menuItemDescription);
            }
            if (result == null) {
                result = caseToolEntry(menuItemDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(menuItemDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(menuItemDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.MENU_ITEM_DESCRIPTION_REFERENCE: {
            MenuItemDescriptionReference menuItemDescriptionReference = (MenuItemDescriptionReference) theEObject;
            T result = caseMenuItemDescriptionReference(menuItemDescriptionReference);
            if (result == null) {
                result = caseMenuItemOrRef(menuItemDescriptionReference);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.OPERATION_ACTION: {
            OperationAction operationAction = (OperationAction) theEObject;
            T result = caseOperationAction(operationAction);
            if (result == null) {
                result = caseMenuItemDescriptionWithIcon(operationAction);
            }
            if (result == null) {
                result = caseGroupMenuItem(operationAction);
            }
            if (result == null) {
                result = caseMenuItemDescription(operationAction);
            }
            if (result == null) {
                result = caseAbstractToolDescription(operationAction);
            }
            if (result == null) {
                result = caseMenuItemOrRef(operationAction);
            }
            if (result == null) {
                result = caseToolEntry(operationAction);
            }
            if (result == null) {
                result = caseDocumentedElement(operationAction);
            }
            if (result == null) {
                result = caseIdentifiedElement(operationAction);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.EXTERNAL_JAVA_ACTION: {
            ExternalJavaAction externalJavaAction = (ExternalJavaAction) theEObject;
            T result = caseExternalJavaAction(externalJavaAction);
            if (result == null) {
                result = caseMenuItemDescriptionWithIcon(externalJavaAction);
            }
            if (result == null) {
                result = caseContainerModelOperation(externalJavaAction);
            }
            if (result == null) {
                result = caseGroupMenuItem(externalJavaAction);
            }
            if (result == null) {
                result = caseMenuItemDescription(externalJavaAction);
            }
            if (result == null) {
                result = caseModelOperation(externalJavaAction);
            }
            if (result == null) {
                result = caseAbstractToolDescription(externalJavaAction);
            }
            if (result == null) {
                result = caseMenuItemOrRef(externalJavaAction);
            }
            if (result == null) {
                result = caseToolEntry(externalJavaAction);
            }
            if (result == null) {
                result = caseDocumentedElement(externalJavaAction);
            }
            if (result == null) {
                result = caseIdentifiedElement(externalJavaAction);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.EXTERNAL_JAVA_ACTION_CALL: {
            ExternalJavaActionCall externalJavaActionCall = (ExternalJavaActionCall) theEObject;
            T result = caseExternalJavaActionCall(externalJavaActionCall);
            if (result == null) {
                result = caseMenuItemDescriptionWithIcon(externalJavaActionCall);
            }
            if (result == null) {
                result = caseContainerModelOperation(externalJavaActionCall);
            }
            if (result == null) {
                result = caseGroupMenuItem(externalJavaActionCall);
            }
            if (result == null) {
                result = caseMenuItemDescription(externalJavaActionCall);
            }
            if (result == null) {
                result = caseModelOperation(externalJavaActionCall);
            }
            if (result == null) {
                result = caseAbstractToolDescription(externalJavaActionCall);
            }
            if (result == null) {
                result = caseMenuItemOrRef(externalJavaActionCall);
            }
            if (result == null) {
                result = caseToolEntry(externalJavaActionCall);
            }
            if (result == null) {
                result = caseDocumentedElement(externalJavaActionCall);
            }
            if (result == null) {
                result = caseIdentifiedElement(externalJavaActionCall);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.POPUP_MENU: {
            PopupMenu popupMenu = (PopupMenu) theEObject;
            T result = casePopupMenu(popupMenu);
            if (result == null) {
                result = caseGroupMenuItem(popupMenu);
            }
            if (result == null) {
                result = caseAbstractToolDescription(popupMenu);
            }
            if (result == null) {
                result = caseToolEntry(popupMenu);
            }
            if (result == null) {
                result = caseDocumentedElement(popupMenu);
            }
            if (result == null) {
                result = caseIdentifiedElement(popupMenu);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.VARIABLE_CONTAINER: {
            VariableContainer variableContainer = (VariableContainer) theEObject;
            T result = caseVariableContainer(variableContainer);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.ACCELEO_VARIABLE: {
            AcceleoVariable acceleoVariable = (AcceleoVariable) theEObject;
            T result = caseAcceleoVariable(acceleoVariable);
            if (result == null) {
                result = caseVariableContainer(acceleoVariable);
            }
            if (result == null) {
                result = caseSubVariable(acceleoVariable);
            }
            if (result == null) {
                result = caseAbstractVariable(acceleoVariable);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.DIALOG_VARIABLE: {
            DialogVariable dialogVariable = (DialogVariable) theEObject;
            T result = caseDialogVariable(dialogVariable);
            if (result == null) {
                result = caseAbstractVariable(dialogVariable);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.ELEMENT_DROP_VARIABLE: {
            ElementDropVariable elementDropVariable = (ElementDropVariable) theEObject;
            T result = caseElementDropVariable(elementDropVariable);
            if (result == null) {
                result = caseAbstractVariable(elementDropVariable);
            }
            if (result == null) {
                result = caseVariableContainer(elementDropVariable);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.ELEMENT_SELECT_VARIABLE: {
            ElementSelectVariable elementSelectVariable = (ElementSelectVariable) theEObject;
            T result = caseElementSelectVariable(elementSelectVariable);
            if (result == null) {
                result = caseAbstractVariable(elementSelectVariable);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.ELEMENT_VARIABLE: {
            ElementVariable elementVariable = (ElementVariable) theEObject;
            T result = caseElementVariable(elementVariable);
            if (result == null) {
                result = caseAbstractVariable(elementVariable);
            }
            if (result == null) {
                result = caseVariableContainer(elementVariable);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.ELEMENT_VIEW_VARIABLE: {
            ElementViewVariable elementViewVariable = (ElementViewVariable) theEObject;
            T result = caseElementViewVariable(elementViewVariable);
            if (result == null) {
                result = caseAbstractVariable(elementViewVariable);
            }
            if (result == null) {
                result = caseVariableContainer(elementViewVariable);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.ELEMENT_DELETE_VARIABLE: {
            ElementDeleteVariable elementDeleteVariable = (ElementDeleteVariable) theEObject;
            T result = caseElementDeleteVariable(elementDeleteVariable);
            if (result == null) {
                result = caseAbstractVariable(elementDeleteVariable);
            }
            if (result == null) {
                result = caseVariableContainer(elementDeleteVariable);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.DROP_CONTAINER_VARIABLE: {
            DropContainerVariable dropContainerVariable = (DropContainerVariable) theEObject;
            T result = caseDropContainerVariable(dropContainerVariable);
            if (result == null) {
                result = caseAbstractVariable(dropContainerVariable);
            }
            if (result == null) {
                result = caseVariableContainer(dropContainerVariable);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.SELECT_CONTAINER_VARIABLE: {
            SelectContainerVariable selectContainerVariable = (SelectContainerVariable) theEObject;
            T result = caseSelectContainerVariable(selectContainerVariable);
            if (result == null) {
                result = caseAbstractVariable(selectContainerVariable);
            }
            if (result == null) {
                result = caseVariableContainer(selectContainerVariable);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.CONTAINER_VIEW_VARIABLE: {
            ContainerViewVariable containerViewVariable = (ContainerViewVariable) theEObject;
            T result = caseContainerViewVariable(containerViewVariable);
            if (result == null) {
                result = caseAbstractVariable(containerViewVariable);
            }
            if (result == null) {
                result = caseVariableContainer(containerViewVariable);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE: {
            SelectModelElementVariable selectModelElementVariable = (SelectModelElementVariable) theEObject;
            T result = caseSelectModelElementVariable(selectModelElementVariable);
            if (result == null) {
                result = caseSubVariable(selectModelElementVariable);
            }
            if (result == null) {
                result = caseSelectionDescription(selectModelElementVariable);
            }
            if (result == null) {
                result = caseInteractiveVariableDescription(selectModelElementVariable);
            }
            if (result == null) {
                result = caseAbstractVariable(selectModelElementVariable);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.EDIT_MASK_VARIABLES: {
            EditMaskVariables editMaskVariables = (EditMaskVariables) theEObject;
            T result = caseEditMaskVariables(editMaskVariables);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.CONTAINER_MODEL_OPERATION: {
            ContainerModelOperation containerModelOperation = (ContainerModelOperation) theEObject;
            T result = caseContainerModelOperation(containerModelOperation);
            if (result == null) {
                result = caseModelOperation(containerModelOperation);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.MODEL_OPERATION: {
            ModelOperation modelOperation = (ModelOperation) theEObject;
            T result = caseModelOperation(modelOperation);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.INITIAL_NODE_CREATION_OPERATION: {
            InitialNodeCreationOperation initialNodeCreationOperation = (InitialNodeCreationOperation) theEObject;
            T result = caseInitialNodeCreationOperation(initialNodeCreationOperation);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.INITIAL_OPERATION: {
            InitialOperation initialOperation = (InitialOperation) theEObject;
            T result = caseInitialOperation(initialOperation);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.INIT_EDGE_CREATION_OPERATION: {
            InitEdgeCreationOperation initEdgeCreationOperation = (InitEdgeCreationOperation) theEObject;
            T result = caseInitEdgeCreationOperation(initEdgeCreationOperation);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.INITIAL_CONTAINER_DROP_OPERATION: {
            InitialContainerDropOperation initialContainerDropOperation = (InitialContainerDropOperation) theEObject;
            T result = caseInitialContainerDropOperation(initialContainerDropOperation);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.CREATE_INSTANCE: {
            CreateInstance createInstance = (CreateInstance) theEObject;
            T result = caseCreateInstance(createInstance);
            if (result == null) {
                result = caseContainerModelOperation(createInstance);
            }
            if (result == null) {
                result = caseModelOperation(createInstance);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.CHANGE_CONTEXT: {
            ChangeContext changeContext = (ChangeContext) theEObject;
            T result = caseChangeContext(changeContext);
            if (result == null) {
                result = caseContainerModelOperation(changeContext);
            }
            if (result == null) {
                result = caseModelOperation(changeContext);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.SET_VALUE: {
            SetValue setValue = (SetValue) theEObject;
            T result = caseSetValue(setValue);
            if (result == null) {
                result = caseContainerModelOperation(setValue);
            }
            if (result == null) {
                result = caseModelOperation(setValue);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.SET_OBJECT: {
            SetObject setObject = (SetObject) theEObject;
            T result = caseSetObject(setObject);
            if (result == null) {
                result = caseContainerModelOperation(setObject);
            }
            if (result == null) {
                result = caseModelOperation(setObject);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.UNSET: {
            Unset unset = (Unset) theEObject;
            T result = caseUnset(unset);
            if (result == null) {
                result = caseContainerModelOperation(unset);
            }
            if (result == null) {
                result = caseModelOperation(unset);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.MOVE_ELEMENT: {
            MoveElement moveElement = (MoveElement) theEObject;
            T result = caseMoveElement(moveElement);
            if (result == null) {
                result = caseContainerModelOperation(moveElement);
            }
            if (result == null) {
                result = caseModelOperation(moveElement);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.REMOVE_ELEMENT: {
            RemoveElement removeElement = (RemoveElement) theEObject;
            T result = caseRemoveElement(removeElement);
            if (result == null) {
                result = caseContainerModelOperation(removeElement);
            }
            if (result == null) {
                result = caseModelOperation(removeElement);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.FOR: {
            For for_ = (For) theEObject;
            T result = caseFor(for_);
            if (result == null) {
                result = caseContainerModelOperation(for_);
            }
            if (result == null) {
                result = caseModelOperation(for_);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.IF: {
            If if_ = (If) theEObject;
            T result = caseIf(if_);
            if (result == null) {
                result = caseContainerModelOperation(if_);
            }
            if (result == null) {
                result = caseModelOperation(if_);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.DELETE_VIEW: {
            DeleteView deleteView = (DeleteView) theEObject;
            T result = caseDeleteView(deleteView);
            if (result == null) {
                result = caseContainerModelOperation(deleteView);
            }
            if (result == null) {
                result = caseModelOperation(deleteView);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.NAME_VARIABLE: {
            NameVariable nameVariable = (NameVariable) theEObject;
            T result = caseNameVariable(nameVariable);
            if (result == null) {
                result = caseAbstractVariable(nameVariable);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.EXTERNAL_JAVA_ACTION_PARAMETER: {
            ExternalJavaActionParameter externalJavaActionParameter = (ExternalJavaActionParameter) theEObject;
            T result = caseExternalJavaActionParameter(externalJavaActionParameter);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.TOOL_FILTER_DESCRIPTION: {
            ToolFilterDescription toolFilterDescription = (ToolFilterDescription) theEObject;
            T result = caseToolFilterDescription(toolFilterDescription);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.FEATURE_CHANGE_LISTENER: {
            FeatureChangeListener featureChangeListener = (FeatureChangeListener) theEObject;
            T result = caseFeatureChangeListener(featureChangeListener);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.CASE: {
            Case case_ = (Case) theEObject;
            T result = caseCase(case_);
            if (result == null) {
                result = caseSwitchChild(case_);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.SWITCH_CHILD: {
            SwitchChild switchChild = (SwitchChild) theEObject;
            T result = caseSwitchChild(switchChild);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.DEFAULT: {
            Default default_ = (Default) theEObject;
            T result = caseDefault(default_);
            if (result == null) {
                result = caseSwitchChild(default_);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.SWITCH: {
            Switch switch_ = (Switch) theEObject;
            T result = caseSwitch(switch_);
            if (result == null) {
                result = caseModelOperation(switch_);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.LET: {
            Let let = (Let) theEObject;
            T result = caseLet(let);
            if (result == null) {
                result = caseContainerModelOperation(let);
            }
            if (result == null) {
                result = caseModelOperation(let);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.GROUP_MENU: {
            GroupMenu groupMenu = (GroupMenu) theEObject;
            T result = caseGroupMenu(groupMenu);
            if (result == null) {
                result = caseMenuItemDescription(groupMenu);
            }
            if (result == null) {
                result = caseAbstractToolDescription(groupMenu);
            }
            if (result == null) {
                result = caseMenuItemOrRef(groupMenu);
            }
            if (result == null) {
                result = caseToolEntry(groupMenu);
            }
            if (result == null) {
                result = caseDocumentedElement(groupMenu);
            }
            if (result == null) {
                result = caseIdentifiedElement(groupMenu);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.GROUP_MENU_ITEM: {
            GroupMenuItem groupMenuItem = (GroupMenuItem) theEObject;
            T result = caseGroupMenuItem(groupMenuItem);
            if (result == null) {
                result = caseAbstractToolDescription(groupMenuItem);
            }
            if (result == null) {
                result = caseToolEntry(groupMenuItem);
            }
            if (result == null) {
                result = caseDocumentedElement(groupMenuItem);
            }
            if (result == null) {
                result = caseIdentifiedElement(groupMenuItem);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.MENU_ITEM_DESCRIPTION_WITH_ICON: {
            MenuItemDescriptionWithIcon menuItemDescriptionWithIcon = (MenuItemDescriptionWithIcon) theEObject;
            T result = caseMenuItemDescriptionWithIcon(menuItemDescriptionWithIcon);
            if (result == null) {
                result = caseMenuItemDescription(menuItemDescriptionWithIcon);
            }
            if (result == null) {
                result = caseAbstractToolDescription(menuItemDescriptionWithIcon);
            }
            if (result == null) {
                result = caseMenuItemOrRef(menuItemDescriptionWithIcon);
            }
            if (result == null) {
                result = caseToolEntry(menuItemDescriptionWithIcon);
            }
            if (result == null) {
                result = caseDocumentedElement(menuItemDescriptionWithIcon);
            }
            if (result == null) {
                result = caseIdentifiedElement(menuItemDescriptionWithIcon);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        default:
            return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Entry</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Entry</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseToolEntry(ToolEntry object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Tool Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Tool Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractToolDescription(AbstractToolDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Mapping Based Tool Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Mapping Based Tool Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMappingBasedToolDescription(MappingBasedToolDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Description</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseToolDescription(ToolDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Paste Description</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Paste Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePasteDescription(PasteDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Selection Wizard Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Selection Wizard Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSelectionWizardDescription(SelectionWizardDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Pane Based Selection Wizard
     * Description</em>'. <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Pane Based Selection Wizard
     *         Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePaneBasedSelectionWizardDescription(PaneBasedSelectionWizardDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Representation Creation Description</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Representation Creation Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRepresentationCreationDescription(RepresentationCreationDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Representation Navigation Description</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Representation Navigation Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRepresentationNavigationDescription(RepresentationNavigationDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Menu Item Or Ref</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Menu Item Or Ref</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMenuItemOrRef(MenuItemOrRef object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Menu Item Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Menu Item Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMenuItemDescription(MenuItemDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Menu Item Description Reference</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Menu Item Description Reference</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMenuItemDescriptionReference(MenuItemDescriptionReference object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Operation Action</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Operation Action</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseOperationAction(OperationAction object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>External Java Action</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>External Java Action</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseExternalJavaAction(ExternalJavaAction object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>External Java Action Call</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>External Java Action Call</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseExternalJavaActionCall(ExternalJavaActionCall object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Popup Menu</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Popup Menu</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePopupMenu(PopupMenu object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Variable Container</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Variable Container</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseVariableContainer(VariableContainer object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Acceleo Variable</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Acceleo Variable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAcceleoVariable(AcceleoVariable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Dialog Variable</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Dialog Variable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDialogVariable(DialogVariable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Element Drop Variable</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Element Drop Variable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseElementDropVariable(ElementDropVariable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Element Select Variable</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Element Select Variable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseElementSelectVariable(ElementSelectVariable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Element Variable</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Element Variable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseElementVariable(ElementVariable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Element View Variable</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Element View Variable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseElementViewVariable(ElementViewVariable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Element Delete Variable</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Element Delete Variable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseElementDeleteVariable(ElementDeleteVariable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Drop Container Variable</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Drop Container Variable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDropContainerVariable(DropContainerVariable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Select Container Variable</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Select Container Variable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSelectContainerVariable(SelectContainerVariable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Container View Variable</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Container View Variable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseContainerViewVariable(ContainerViewVariable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Select Model Element Variable</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Select Model Element Variable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSelectModelElementVariable(SelectModelElementVariable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Edit Mask Variables</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Edit Mask Variables</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEditMaskVariables(EditMaskVariables object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Container Model Operation</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Container Model Operation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseContainerModelOperation(ContainerModelOperation object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Model Operation</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Model Operation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseModelOperation(ModelOperation object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Initial Node Creation Operation</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Initial Node Creation Operation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseInitialNodeCreationOperation(InitialNodeCreationOperation object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Initial Operation</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Initial Operation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseInitialOperation(InitialOperation object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Init Edge Creation Operation</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Init Edge Creation Operation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseInitEdgeCreationOperation(InitEdgeCreationOperation object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Initial Container Drop Operation</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Initial Container Drop Operation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseInitialContainerDropOperation(InitialContainerDropOperation object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Create Instance</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Create Instance</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCreateInstance(CreateInstance object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Change Context</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Change Context</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseChangeContext(ChangeContext object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Set Value</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Set Value</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSetValue(SetValue object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Set Object</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Set Object</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSetObject(SetObject object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Unset</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Unset</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseUnset(Unset object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Move Element</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Move Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMoveElement(MoveElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Remove Element</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Remove Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRemoveElement(RemoveElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>For</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>For</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFor(For object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>If</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>If</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIf(If object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Delete View</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Delete View</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDeleteView(DeleteView object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Name Variable</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Name Variable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNameVariable(NameVariable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>External Java Action Parameter</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>External Java Action Parameter</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseExternalJavaActionParameter(ExternalJavaActionParameter object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Filter Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Filter Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseToolFilterDescription(ToolFilterDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Feature Change Listener</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Feature Change Listener</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFeatureChangeListener(FeatureChangeListener object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Case</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Case</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCase(Case object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Switch Child</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Switch Child</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSwitchChild(SwitchChild object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Default</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Default</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDefault(Default object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Switch</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Switch</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSwitch(Switch object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Let</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Let</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLet(Let object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Group Menu</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Group Menu</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGroupMenu(GroupMenu object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Group Menu Item</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Group Menu Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGroupMenuItem(GroupMenuItem object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Menu Item Description With Icon</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Menu Item Description With Icon</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMenuItemDescriptionWithIcon(MenuItemDescriptionWithIcon object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Documented Element</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Documented Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDocumentedElement(DocumentedElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Identified Element</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Identified Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIdentifiedElement(IdentifiedElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Selection Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Selection Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSelectionDescription(SelectionDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Variable</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Variable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractVariable(AbstractVariable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sub Variable</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sub Variable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSubVariable(SubVariable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Interactive Variable Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Interactive Variable Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseInteractiveVariableDescription(InteractiveVariableDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch, but this is the last case
     * anyway. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    public T defaultCase(EObject object) {
        return null;
    }

} // ToolSwitch
