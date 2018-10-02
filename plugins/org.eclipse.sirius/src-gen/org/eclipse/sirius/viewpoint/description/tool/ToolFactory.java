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
package org.eclipse.sirius.viewpoint.description.tool;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of
 * the model. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage
 * @generated
 */
public interface ToolFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    ToolFactory eINSTANCE = org.eclipse.sirius.viewpoint.description.tool.impl.ToolFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Description</em>'.
     * @generated
     */
    ToolDescription createToolDescription();

    /**
     * Returns a new object of class '<em>Paste Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Paste Description</em>'.
     * @generated
     */
    PasteDescription createPasteDescription();

    /**
     * Returns a new object of class '<em>Selection Wizard Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>Selection Wizard Description</em>'.
     * @generated
     */
    SelectionWizardDescription createSelectionWizardDescription();

    /**
     * Returns a new object of class '<em>Pane Based Selection Wizard Description</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Pane Based Selection Wizard Description</em>'.
     * @generated
     */
    PaneBasedSelectionWizardDescription createPaneBasedSelectionWizardDescription();

    /**
     * Returns a new object of class '<em>Menu Item Description Reference</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Menu Item Description Reference</em>'.
     * @generated
     */
    MenuItemDescriptionReference createMenuItemDescriptionReference();

    /**
     * Returns a new object of class '<em>Operation Action</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Operation Action</em>'.
     * @generated
     */
    OperationAction createOperationAction();

    /**
     * Returns a new object of class '<em>External Java Action</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>External Java Action</em>'.
     * @generated
     */
    ExternalJavaAction createExternalJavaAction();

    /**
     * Returns a new object of class '<em>External Java Action Call</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>External Java Action Call</em>'.
     * @generated
     */
    ExternalJavaActionCall createExternalJavaActionCall();

    /**
     * Returns a new object of class '<em>Popup Menu</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Popup Menu</em>'.
     * @generated
     */
    PopupMenu createPopupMenu();

    /**
     * Returns a new object of class '<em>Acceleo Variable</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Acceleo Variable</em>'.
     * @generated
     */
    AcceleoVariable createAcceleoVariable();

    /**
     * Returns a new object of class '<em>Element Drop Variable</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Element Drop Variable</em>'.
     * @generated
     */
    ElementDropVariable createElementDropVariable();

    /**
     * Returns a new object of class '<em>Element Select Variable</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Element Select Variable</em>'.
     * @generated
     */
    ElementSelectVariable createElementSelectVariable();

    /**
     * Returns a new object of class '<em>Element Variable</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Element Variable</em>'.
     * @generated
     */
    ElementVariable createElementVariable();

    /**
     * Returns a new object of class '<em>Element View Variable</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Element View Variable</em>'.
     * @generated
     */
    ElementViewVariable createElementViewVariable();

    /**
     * Returns a new object of class '<em>Element Delete Variable</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Element Delete Variable</em>'.
     * @generated
     */
    ElementDeleteVariable createElementDeleteVariable();

    /**
     * Returns a new object of class '<em>Drop Container Variable</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Drop Container Variable</em>'.
     * @generated
     */
    DropContainerVariable createDropContainerVariable();

    /**
     * Returns a new object of class '<em>Select Container Variable</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Select Container Variable</em>'.
     * @generated
     */
    SelectContainerVariable createSelectContainerVariable();

    /**
     * Returns a new object of class '<em>Container View Variable</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Container View Variable</em>'.
     * @generated
     */
    ContainerViewVariable createContainerViewVariable();

    /**
     * Returns a new object of class '<em>Select Model Element Variable</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>Select Model Element Variable</em>'.
     * @generated
     */
    SelectModelElementVariable createSelectModelElementVariable();

    /**
     * Returns a new object of class '<em>Edit Mask Variables</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Edit Mask Variables</em>'.
     * @generated
     */
    EditMaskVariables createEditMaskVariables();

    /**
     * Returns a new object of class '<em>Initial Node Creation Operation</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Initial Node Creation Operation</em>'.
     * @generated
     */
    InitialNodeCreationOperation createInitialNodeCreationOperation();

    /**
     * Returns a new object of class '<em>Initial Operation</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Initial Operation</em>'.
     * @generated
     */
    InitialOperation createInitialOperation();

    /**
     * Returns a new object of class '<em>Init Edge Creation Operation</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>Init Edge Creation Operation</em>'.
     * @generated
     */
    InitEdgeCreationOperation createInitEdgeCreationOperation();

    /**
     * Returns a new object of class '<em>Initial Container Drop Operation</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Initial Container Drop Operation</em>'.
     * @generated
     */
    InitialContainerDropOperation createInitialContainerDropOperation();

    /**
     * Returns a new object of class '<em>Create Instance</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Create Instance</em>'.
     * @generated
     */
    CreateInstance createCreateInstance();

    /**
     * Returns a new object of class '<em>Change Context</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Change Context</em>'.
     * @generated
     */
    ChangeContext createChangeContext();

    /**
     * Returns a new object of class '<em>Set Value</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Set Value</em>'.
     * @generated
     */
    SetValue createSetValue();

    /**
     * Returns a new object of class '<em>Set Object</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Set Object</em>'.
     * @generated
     */
    SetObject createSetObject();

    /**
     * Returns a new object of class '<em>Unset</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Unset</em>'.
     * @generated
     */
    Unset createUnset();

    /**
     * Returns a new object of class '<em>Move Element</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Move Element</em>'.
     * @generated
     */
    MoveElement createMoveElement();

    /**
     * Returns a new object of class '<em>Remove Element</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Remove Element</em>'.
     * @generated
     */
    RemoveElement createRemoveElement();

    /**
     * Returns a new object of class '<em>For</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>For</em>'.
     * @generated
     */
    For createFor();

    /**
     * Returns a new object of class '<em>If</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>If</em>'.
     * @generated
     */
    If createIf();

    /**
     * Returns a new object of class '<em>Delete View</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Delete View</em>'.
     * @generated
     */
    DeleteView createDeleteView();

    /**
     * Returns a new object of class '<em>Name Variable</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Name Variable</em>'.
     * @generated
     */
    NameVariable createNameVariable();

    /**
     * Returns a new object of class '<em>External Java Action Parameter</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>External Java Action Parameter</em>'.
     * @generated
     */
    ExternalJavaActionParameter createExternalJavaActionParameter();

    /**
     * Returns a new object of class '<em>Filter Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Filter Description</em>'.
     * @generated
     */
    ToolFilterDescription createToolFilterDescription();

    /**
     * Returns a new object of class '<em>Feature Change Listener</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Feature Change Listener</em>'.
     * @generated
     */
    FeatureChangeListener createFeatureChangeListener();

    /**
     * Returns a new object of class '<em>Case</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Case</em>'.
     * @generated
     */
    Case createCase();

    /**
     * Returns a new object of class '<em>Default</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Default</em>'.
     * @generated
     */
    Default createDefault();

    /**
     * Returns a new object of class '<em>Switch</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Switch</em>'.
     * @generated
     */
    Switch createSwitch();

    /**
     * Returns a new object of class '<em>Let</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Let</em>'.
     * @generated
     */
    Let createLet();

    /**
     * Returns a new object of class '<em>Group Menu</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Group Menu</em>'.
     * @generated
     */
    GroupMenu createGroupMenu();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the package supported by this factory.
     * @generated
     */
    ToolPackage getToolPackage();

} // ToolFactory
