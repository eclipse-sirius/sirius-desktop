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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
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
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an adapter <code>createXXX</code>
 * method for each class of the model. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage
 * @generated
 */
public class ToolAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static ToolPackage modelPackage;

    /**
     * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ToolAdapterFactory() {
        if (ToolAdapterFactory.modelPackage == null) {
            ToolAdapterFactory.modelPackage = ToolPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object. <!-- begin-user-doc --> This
     * implementation returns <code>true</code> if the object is either the model's package or is an instance object of
     * the model. <!-- end-user-doc -->
     *
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == ToolAdapterFactory.modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject) object).eClass().getEPackage() == ToolAdapterFactory.modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ToolSwitch<Adapter> modelSwitch = new ToolSwitch<Adapter>() {
        @Override
        public Adapter caseToolEntry(ToolEntry object) {
            return createToolEntryAdapter();
        }

        @Override
        public Adapter caseAbstractToolDescription(AbstractToolDescription object) {
            return createAbstractToolDescriptionAdapter();
        }

        @Override
        public Adapter caseMappingBasedToolDescription(MappingBasedToolDescription object) {
            return createMappingBasedToolDescriptionAdapter();
        }

        @Override
        public Adapter caseToolDescription(ToolDescription object) {
            return createToolDescriptionAdapter();
        }

        @Override
        public Adapter casePasteDescription(PasteDescription object) {
            return createPasteDescriptionAdapter();
        }

        @Override
        public Adapter caseSelectionWizardDescription(SelectionWizardDescription object) {
            return createSelectionWizardDescriptionAdapter();
        }

        @Override
        public Adapter casePaneBasedSelectionWizardDescription(PaneBasedSelectionWizardDescription object) {
            return createPaneBasedSelectionWizardDescriptionAdapter();
        }

        @Override
        public Adapter caseRepresentationCreationDescription(RepresentationCreationDescription object) {
            return createRepresentationCreationDescriptionAdapter();
        }

        @Override
        public Adapter caseRepresentationNavigationDescription(RepresentationNavigationDescription object) {
            return createRepresentationNavigationDescriptionAdapter();
        }

        @Override
        public Adapter caseMenuItemOrRef(MenuItemOrRef object) {
            return createMenuItemOrRefAdapter();
        }

        @Override
        public Adapter caseMenuItemDescription(MenuItemDescription object) {
            return createMenuItemDescriptionAdapter();
        }

        @Override
        public Adapter caseMenuItemDescriptionReference(MenuItemDescriptionReference object) {
            return createMenuItemDescriptionReferenceAdapter();
        }

        @Override
        public Adapter caseOperationAction(OperationAction object) {
            return createOperationActionAdapter();
        }

        @Override
        public Adapter caseExternalJavaAction(ExternalJavaAction object) {
            return createExternalJavaActionAdapter();
        }

        @Override
        public Adapter caseExternalJavaActionCall(ExternalJavaActionCall object) {
            return createExternalJavaActionCallAdapter();
        }

        @Override
        public Adapter casePopupMenu(PopupMenu object) {
            return createPopupMenuAdapter();
        }

        @Override
        public Adapter caseVariableContainer(VariableContainer object) {
            return createVariableContainerAdapter();
        }

        @Override
        public Adapter caseAcceleoVariable(AcceleoVariable object) {
            return createAcceleoVariableAdapter();
        }

        @Override
        public Adapter caseDialogVariable(DialogVariable object) {
            return createDialogVariableAdapter();
        }

        @Override
        public Adapter caseElementDropVariable(ElementDropVariable object) {
            return createElementDropVariableAdapter();
        }

        @Override
        public Adapter caseElementSelectVariable(ElementSelectVariable object) {
            return createElementSelectVariableAdapter();
        }

        @Override
        public Adapter caseElementVariable(ElementVariable object) {
            return createElementVariableAdapter();
        }

        @Override
        public Adapter caseElementViewVariable(ElementViewVariable object) {
            return createElementViewVariableAdapter();
        }

        @Override
        public Adapter caseElementDeleteVariable(ElementDeleteVariable object) {
            return createElementDeleteVariableAdapter();
        }

        @Override
        public Adapter caseDropContainerVariable(DropContainerVariable object) {
            return createDropContainerVariableAdapter();
        }

        @Override
        public Adapter caseSelectContainerVariable(SelectContainerVariable object) {
            return createSelectContainerVariableAdapter();
        }

        @Override
        public Adapter caseContainerViewVariable(ContainerViewVariable object) {
            return createContainerViewVariableAdapter();
        }

        @Override
        public Adapter caseSelectModelElementVariable(SelectModelElementVariable object) {
            return createSelectModelElementVariableAdapter();
        }

        @Override
        public Adapter caseEditMaskVariables(EditMaskVariables object) {
            return createEditMaskVariablesAdapter();
        }

        @Override
        public Adapter caseContainerModelOperation(ContainerModelOperation object) {
            return createContainerModelOperationAdapter();
        }

        @Override
        public Adapter caseModelOperation(ModelOperation object) {
            return createModelOperationAdapter();
        }

        @Override
        public Adapter caseInitialNodeCreationOperation(InitialNodeCreationOperation object) {
            return createInitialNodeCreationOperationAdapter();
        }

        @Override
        public Adapter caseInitialOperation(InitialOperation object) {
            return createInitialOperationAdapter();
        }

        @Override
        public Adapter caseInitEdgeCreationOperation(InitEdgeCreationOperation object) {
            return createInitEdgeCreationOperationAdapter();
        }

        @Override
        public Adapter caseInitialContainerDropOperation(InitialContainerDropOperation object) {
            return createInitialContainerDropOperationAdapter();
        }

        @Override
        public Adapter caseCreateInstance(CreateInstance object) {
            return createCreateInstanceAdapter();
        }

        @Override
        public Adapter caseChangeContext(ChangeContext object) {
            return createChangeContextAdapter();
        }

        @Override
        public Adapter caseSetValue(SetValue object) {
            return createSetValueAdapter();
        }

        @Override
        public Adapter caseSetObject(SetObject object) {
            return createSetObjectAdapter();
        }

        @Override
        public Adapter caseUnset(Unset object) {
            return createUnsetAdapter();
        }

        @Override
        public Adapter caseMoveElement(MoveElement object) {
            return createMoveElementAdapter();
        }

        @Override
        public Adapter caseRemoveElement(RemoveElement object) {
            return createRemoveElementAdapter();
        }

        @Override
        public Adapter caseFor(For object) {
            return createForAdapter();
        }

        @Override
        public Adapter caseIf(If object) {
            return createIfAdapter();
        }

        @Override
        public Adapter caseDeleteView(DeleteView object) {
            return createDeleteViewAdapter();
        }

        @Override
        public Adapter caseNameVariable(NameVariable object) {
            return createNameVariableAdapter();
        }

        @Override
        public Adapter caseExternalJavaActionParameter(ExternalJavaActionParameter object) {
            return createExternalJavaActionParameterAdapter();
        }

        @Override
        public Adapter caseToolFilterDescription(ToolFilterDescription object) {
            return createToolFilterDescriptionAdapter();
        }

        @Override
        public Adapter caseFeatureChangeListener(FeatureChangeListener object) {
            return createFeatureChangeListenerAdapter();
        }

        @Override
        public Adapter caseCase(Case object) {
            return createCaseAdapter();
        }

        @Override
        public Adapter caseSwitchChild(SwitchChild object) {
            return createSwitchChildAdapter();
        }

        @Override
        public Adapter caseDefault(Default object) {
            return createDefaultAdapter();
        }

        @Override
        public Adapter caseSwitch(Switch object) {
            return createSwitchAdapter();
        }

        @Override
        public Adapter caseLet(Let object) {
            return createLetAdapter();
        }

        @Override
        public Adapter caseGroupMenu(GroupMenu object) {
            return createGroupMenuAdapter();
        }

        @Override
        public Adapter caseGroupMenuItem(GroupMenuItem object) {
            return createGroupMenuItemAdapter();
        }

        @Override
        public Adapter caseMenuItemDescriptionWithIcon(MenuItemDescriptionWithIcon object) {
            return createMenuItemDescriptionWithIconAdapter();
        }

        @Override
        public Adapter caseDocumentedElement(DocumentedElement object) {
            return createDocumentedElementAdapter();
        }

        @Override
        public Adapter caseIdentifiedElement(IdentifiedElement object) {
            return createIdentifiedElementAdapter();
        }

        @Override
        public Adapter caseSelectionDescription(SelectionDescription object) {
            return createSelectionDescriptionAdapter();
        }

        @Override
        public Adapter caseAbstractVariable(AbstractVariable object) {
            return createAbstractVariableAdapter();
        }

        @Override
        public Adapter caseSubVariable(SubVariable object) {
            return createSubVariableAdapter();
        }

        @Override
        public Adapter caseInteractiveVariableDescription(InteractiveVariableDescription object) {
            return createInteractiveVariableDescriptionAdapter();
        }

        @Override
        public Adapter defaultCase(EObject object) {
            return createEObjectAdapter();
        }
    };

    /**
     * Creates an adapter for the <code>target</code>. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param target
     *            the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject) target);
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.tool.ToolEntry
     * <em>Entry</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolEntry
     * @generated
     */
    public Adapter createToolEntryAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription <em>Abstract Tool
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription
     * @generated
     */
    public Adapter createAbstractToolDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.tool.MappingBasedToolDescription <em>Mapping Based Tool
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.MappingBasedToolDescription
     * @generated
     */
    public Adapter createMappingBasedToolDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.tool.ToolDescription <em>Description</em>}'. <!-- begin-user-doc
     * --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case
     * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolDescription
     * @generated
     */
    public Adapter createToolDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.PasteDescription <em>Paste Description</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.PasteDescription
     * @generated
     */
    public Adapter createPasteDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription <em>Selection Wizard
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription
     * @generated
     */
    public Adapter createSelectionWizardDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription <em>Pane Based
     * Selection Wizard Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription
     * @generated
     */
    public Adapter createPaneBasedSelectionWizardDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription <em>Representation
     * Creation Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription
     * @generated
     */
    public Adapter createRepresentationCreationDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription <em>Representation
     * Navigation Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription
     * @generated
     */
    public Adapter createRepresentationNavigationDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.tool.MenuItemOrRef
     * <em>Menu Item Or Ref</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.MenuItemOrRef
     * @generated
     */
    public Adapter createMenuItemOrRefAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.MenuItemDescription <em>Menu Item Description</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.MenuItemDescription
     * @generated
     */
    public Adapter createMenuItemDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.tool.MenuItemDescriptionReference <em>Menu Item Description
     * Reference</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.MenuItemDescriptionReference
     * @generated
     */
    public Adapter createMenuItemDescriptionReferenceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.OperationAction <em>Operation Action</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.OperationAction
     * @generated
     */
    public Adapter createOperationActionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.ExternalJavaAction <em>External Java Action</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.ExternalJavaAction
     * @generated
     */
    public Adapter createExternalJavaActionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.tool.ExternalJavaActionCall <em>External Java Action
     * Call</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.ExternalJavaActionCall
     * @generated
     */
    public Adapter createExternalJavaActionCallAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.tool.PopupMenu
     * <em>Popup Menu</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.PopupMenu
     * @generated
     */
    public Adapter createPopupMenuAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.AbstractVariable
     * <em>Abstract Variable</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.AbstractVariable
     * @generated
     */
    public Adapter createAbstractVariableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.VariableContainer <em>Variable Container</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.VariableContainer
     * @generated
     */
    public Adapter createVariableContainerAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.AcceleoVariable <em>Acceleo Variable</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.AcceleoVariable
     * @generated
     */
    public Adapter createAcceleoVariableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.SubVariable <em>Sub
     * Variable</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.SubVariable
     * @generated
     */
    public Adapter createSubVariableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.InteractiveVariableDescription <em>Interactive Variable
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.InteractiveVariableDescription
     * @generated
     */
    public Adapter createInteractiveVariableDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.tool.DialogVariable
     * <em>Dialog Variable</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.DialogVariable
     * @generated
     */
    public Adapter createDialogVariableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.ElementDropVariable <em>Element Drop Variable</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.ElementDropVariable
     * @generated
     */
    public Adapter createElementDropVariableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.tool.ElementSelectVariable <em>Element Select Variable</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.ElementSelectVariable
     * @generated
     */
    public Adapter createElementSelectVariableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.ElementVariable <em>Element Variable</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.ElementVariable
     * @generated
     */
    public Adapter createElementVariableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.ElementViewVariable <em>Element View Variable</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.ElementViewVariable
     * @generated
     */
    public Adapter createElementViewVariableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.tool.ElementDeleteVariable <em>Element Delete Variable</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.ElementDeleteVariable
     * @generated
     */
    public Adapter createElementDeleteVariableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.tool.DropContainerVariable <em>Drop Container Variable</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.DropContainerVariable
     * @generated
     */
    public Adapter createDropContainerVariableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.tool.SelectContainerVariable <em>Select Container
     * Variable</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.SelectContainerVariable
     * @generated
     */
    public Adapter createSelectContainerVariableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.tool.ContainerViewVariable <em>Container View Variable</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.ContainerViewVariable
     * @generated
     */
    public Adapter createContainerViewVariableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.tool.SelectModelElementVariable <em>Select Model Element
     * Variable</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.SelectModelElementVariable
     * @generated
     */
    public Adapter createSelectModelElementVariableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.EditMaskVariables <em>Edit Mask Variables</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.EditMaskVariables
     * @generated
     */
    public Adapter createEditMaskVariablesAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.tool.ContainerModelOperation <em>Container Model
     * Operation</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.ContainerModelOperation
     * @generated
     */
    public Adapter createContainerModelOperationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.tool.ModelOperation
     * <em>Model Operation</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.ModelOperation
     * @generated
     */
    public Adapter createModelOperationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.tool.InitialNodeCreationOperation <em>Initial Node Creation
     * Operation</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.InitialNodeCreationOperation
     * @generated
     */
    public Adapter createInitialNodeCreationOperationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.InitialOperation <em>Initial Operation</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.InitialOperation
     * @generated
     */
    public Adapter createInitialOperationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.tool.InitEdgeCreationOperation <em>Init Edge Creation
     * Operation</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.InitEdgeCreationOperation
     * @generated
     */
    public Adapter createInitEdgeCreationOperationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.tool.InitialContainerDropOperation <em>Initial Container Drop
     * Operation</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.InitialContainerDropOperation
     * @generated
     */
    public Adapter createInitialContainerDropOperationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.tool.CreateInstance
     * <em>Create Instance</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.CreateInstance
     * @generated
     */
    public Adapter createCreateInstanceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.tool.ChangeContext
     * <em>Change Context</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.ChangeContext
     * @generated
     */
    public Adapter createChangeContextAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class ' {@link org.eclipse.sirius.viewpoint.description.tool.SetValue
     * <em>Set Value</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.SetValue
     * @generated
     */
    public Adapter createSetValueAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.tool.SetObject
     * <em>Set Object</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.SetObject
     * @generated
     */
    public Adapter createSetObjectAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.tool.Unset
     * <em>Unset</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.Unset
     * @generated
     */
    public Adapter createUnsetAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.tool.MoveElement
     * <em>Move Element</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.MoveElement
     * @generated
     */
    public Adapter createMoveElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.tool.RemoveElement
     * <em>Remove Element</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.RemoveElement
     * @generated
     */
    public Adapter createRemoveElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.tool.For
     * <em>For</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.For
     * @generated
     */
    public Adapter createForAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.tool.If
     * <em>If</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.If
     * @generated
     */
    public Adapter createIfAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.tool.DeleteView
     * <em>Delete View</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.DeleteView
     * @generated
     */
    public Adapter createDeleteViewAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.tool.NameVariable
     * <em>Name Variable</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.NameVariable
     * @generated
     */
    public Adapter createNameVariableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.tool.ExternalJavaActionParameter <em>External Java Action
     * Parameter</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.ExternalJavaActionParameter
     * @generated
     */
    public Adapter createExternalJavaActionParameterAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.ToolFilterDescription <em>Filter Description</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolFilterDescription
     * @generated
     */
    public Adapter createToolFilterDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.tool.FeatureChangeListener <em>Feature Change Listener</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.FeatureChangeListener
     * @generated
     */
    public Adapter createFeatureChangeListenerAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.tool.Case
     * <em>Case</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.Case
     * @generated
     */
    public Adapter createCaseAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.tool.SwitchChild
     * <em>Switch Child</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.SwitchChild
     * @generated
     */
    public Adapter createSwitchChildAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.tool.Default
     * <em>Default</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.Default
     * @generated
     */
    public Adapter createDefaultAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.tool.Switch
     * <em>Switch</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.Switch
     * @generated
     */
    public Adapter createSwitchAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.tool.Let
     * <em>Let</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.Let
     * @generated
     */
    public Adapter createLetAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.tool.GroupMenu
     * <em>Group Menu</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.GroupMenu
     * @generated
     */
    public Adapter createGroupMenuAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.tool.GroupMenuItem
     * <em>Group Menu Item</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.GroupMenuItem
     * @generated
     */
    public Adapter createGroupMenuItemAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.tool.MenuItemDescriptionWithIcon <em>Menu Item Description With
     * Icon</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.MenuItemDescriptionWithIcon
     * @generated
     */
    public Adapter createMenuItemDescriptionWithIconAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.DocumentedElement
     * <em>Documented Element</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.DocumentedElement
     * @generated
     */
    public Adapter createDocumentedElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.IdentifiedElement
     * <em>Identified Element</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.IdentifiedElement
     * @generated
     */
    public Adapter createIdentifiedElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.description.SelectionDescription <em>Selection Description</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.SelectionDescription
     * @generated
     */
    public Adapter createSelectionDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case. <!-- begin-user-doc --> This default implementation returns null.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} // ToolAdapterFactory
