/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.description.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tree.description.ConditionalTreeItemStyleDescription;
import org.eclipse.sirius.tree.description.DescriptionPackage;
import org.eclipse.sirius.tree.description.PrecedingSiblingsVariables;
import org.eclipse.sirius.tree.description.StyleUpdater;
import org.eclipse.sirius.tree.description.TreeCreationDescription;
import org.eclipse.sirius.tree.description.TreeDescription;
import org.eclipse.sirius.tree.description.TreeItemContainerDropTool;
import org.eclipse.sirius.tree.description.TreeItemCreationTool;
import org.eclipse.sirius.tree.description.TreeItemDeletionTool;
import org.eclipse.sirius.tree.description.TreeItemDragTool;
import org.eclipse.sirius.tree.description.TreeItemEditionTool;
import org.eclipse.sirius.tree.description.TreeItemMapping;
import org.eclipse.sirius.tree.description.TreeItemMappingContainer;
import org.eclipse.sirius.tree.description.TreeItemStyleDescription;
import org.eclipse.sirius.tree.description.TreeItemTool;
import org.eclipse.sirius.tree.description.TreeItemUpdater;
import org.eclipse.sirius.tree.description.TreeMapping;
import org.eclipse.sirius.tree.description.TreeNavigationDescription;
import org.eclipse.sirius.tree.description.TreePopupMenu;
import org.eclipse.sirius.tree.description.TreeVariable;
import org.eclipse.sirius.viewpoint.description.AbstractVariable;
import org.eclipse.sirius.viewpoint.description.ConditionalStyleDescription;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.EndUserDocumentedElement;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.LabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.MappingBasedToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolEntry;
import org.eclipse.sirius.viewpoint.description.tool.VariableContainer;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an adapter <code>createXXX</code>
 * method for each class of the model. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.tree.description.DescriptionPackage
 * @generated
 */
public class DescriptionAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static DescriptionPackage modelPackage;

    /**
     * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public DescriptionAdapterFactory() {
        if (DescriptionAdapterFactory.modelPackage == null) {
            DescriptionAdapterFactory.modelPackage = DescriptionPackage.eINSTANCE;
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
        if (object == DescriptionAdapterFactory.modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject) object).eClass().getEPackage() == DescriptionAdapterFactory.modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected DescriptionSwitch<Adapter> modelSwitch = new DescriptionSwitch<Adapter>() {
        @Override
        public Adapter caseTreeDescription(TreeDescription object) {
            return createTreeDescriptionAdapter();
        }

        @Override
        public Adapter caseTreeItemMapping(TreeItemMapping object) {
            return createTreeItemMappingAdapter();
        }

        @Override
        public Adapter caseTreeItemStyleDescription(TreeItemStyleDescription object) {
            return createTreeItemStyleDescriptionAdapter();
        }

        @Override
        public Adapter caseConditionalTreeItemStyleDescription(ConditionalTreeItemStyleDescription object) {
            return createConditionalTreeItemStyleDescriptionAdapter();
        }

        @Override
        public Adapter caseTreeItemTool(TreeItemTool object) {
            return createTreeItemToolAdapter();
        }

        @Override
        public Adapter caseTreeItemDragTool(TreeItemDragTool object) {
            return createTreeItemDragToolAdapter();
        }

        @Override
        public Adapter caseTreeItemContainerDropTool(TreeItemContainerDropTool object) {
            return createTreeItemContainerDropToolAdapter();
        }

        @Override
        public Adapter caseTreeItemCreationTool(TreeItemCreationTool object) {
            return createTreeItemCreationToolAdapter();
        }

        @Override
        public Adapter caseTreeItemEditionTool(TreeItemEditionTool object) {
            return createTreeItemEditionToolAdapter();
        }

        @Override
        public Adapter caseTreeItemDeletionTool(TreeItemDeletionTool object) {
            return createTreeItemDeletionToolAdapter();
        }

        @Override
        public Adapter caseTreeCreationDescription(TreeCreationDescription object) {
            return createTreeCreationDescriptionAdapter();
        }

        @Override
        public Adapter caseTreeNavigationDescription(TreeNavigationDescription object) {
            return createTreeNavigationDescriptionAdapter();
        }

        @Override
        public Adapter caseTreeMapping(TreeMapping object) {
            return createTreeMappingAdapter();
        }

        @Override
        public Adapter caseStyleUpdater(StyleUpdater object) {
            return createStyleUpdaterAdapter();
        }

        @Override
        public Adapter caseTreeVariable(TreeVariable object) {
            return createTreeVariableAdapter();
        }

        @Override
        public Adapter caseTreeItemUpdater(TreeItemUpdater object) {
            return createTreeItemUpdaterAdapter();
        }

        @Override
        public Adapter casePrecedingSiblingsVariables(PrecedingSiblingsVariables object) {
            return createPrecedingSiblingsVariablesAdapter();
        }

        @Override
        public Adapter caseTreeItemMappingContainer(TreeItemMappingContainer object) {
            return createTreeItemMappingContainerAdapter();
        }

        @Override
        public Adapter caseTreePopupMenu(TreePopupMenu object) {
            return createTreePopupMenuAdapter();
        }

        @Override
        public Adapter caseDocumentedElement(DocumentedElement object) {
            return createDocumentedElementAdapter();
        }

        @Override
        public Adapter caseEndUserDocumentedElement(EndUserDocumentedElement object) {
            return createEndUserDocumentedElementAdapter();
        }

        @Override
        public Adapter caseIdentifiedElement(IdentifiedElement object) {
            return createIdentifiedElementAdapter();
        }

        @Override
        public Adapter caseRepresentationDescription(RepresentationDescription object) {
            return createRepresentationDescriptionAdapter();
        }

        @Override
        public Adapter caseRepresentationElementMapping(RepresentationElementMapping object) {
            return createRepresentationElementMappingAdapter();
        }

        @Override
        public Adapter caseStyleDescription(StyleDescription object) {
            return createStyleDescriptionAdapter();
        }

        @Override
        public Adapter caseBasicLabelStyleDescription(BasicLabelStyleDescription object) {
            return createBasicLabelStyleDescriptionAdapter();
        }

        @Override
        public Adapter caseLabelStyleDescription(LabelStyleDescription object) {
            return createLabelStyleDescriptionAdapter();
        }

        @Override
        public Adapter caseConditionalStyleDescription(ConditionalStyleDescription object) {
            return createConditionalStyleDescriptionAdapter();
        }

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
        public Adapter caseRepresentationCreationDescription(RepresentationCreationDescription object) {
            return createRepresentationCreationDescriptionAdapter();
        }

        @Override
        public Adapter caseRepresentationNavigationDescription(RepresentationNavigationDescription object) {
            return createRepresentationNavigationDescriptionAdapter();
        }

        @Override
        public Adapter caseAbstractVariable(AbstractVariable object) {
            return createAbstractVariableAdapter();
        }

        @Override
        public Adapter caseVariableContainer(VariableContainer object) {
            return createVariableContainerAdapter();
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
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.tree.description.TreeDescription <em>Tree
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tree.description.TreeDescription
     * @generated
     */
    public Adapter createTreeDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.tree.description.TreeItemMapping <em>Tree
     * Item Mapping</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tree.description.TreeItemMapping
     * @generated
     */
    public Adapter createTreeItemMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.tree.description.TreeItemStyleDescription
     * <em>Tree Item Style Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tree.description.TreeItemStyleDescription
     * @generated
     */
    public Adapter createTreeItemStyleDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.tree.description.ConditionalTreeItemStyleDescription <em>Conditional Tree Item Style
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tree.description.ConditionalTreeItemStyleDescription
     * @generated
     */
    public Adapter createConditionalTreeItemStyleDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.tree.description.TreeItemTool <em>Tree
     * Item Tool</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tree.description.TreeItemTool
     * @generated
     */
    public Adapter createTreeItemToolAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.tree.description.TreeItemDragTool
     * <em>Tree Item Drag Tool</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tree.description.TreeItemDragTool
     * @generated
     */
    public Adapter createTreeItemDragToolAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.tree.description.TreeItemContainerDropTool <em>Tree Item Container Drop Tool</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tree.description.TreeItemContainerDropTool
     * @generated
     */
    public Adapter createTreeItemContainerDropToolAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.tree.description.TreeItemCreationTool
     * <em>Tree Item Creation Tool</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tree.description.TreeItemCreationTool
     * @generated
     */
    public Adapter createTreeItemCreationToolAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.tree.description.TreeItemEditionTool
     * <em>Tree Item Edition Tool</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tree.description.TreeItemEditionTool
     * @generated
     */
    public Adapter createTreeItemEditionToolAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.tree.description.TreeItemDeletionTool
     * <em>Tree Item Deletion Tool</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tree.description.TreeItemDeletionTool
     * @generated
     */
    public Adapter createTreeItemDeletionToolAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.tree.description.TreeCreationDescription
     * <em>Tree Creation Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tree.description.TreeCreationDescription
     * @generated
     */
    public Adapter createTreeCreationDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tree.description.TreeNavigationDescription <em>Tree Navigation Description</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tree.description.TreeNavigationDescription
     * @generated
     */
    public Adapter createTreeNavigationDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.tree.description.TreeMapping <em>Tree
     * Mapping</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tree.description.TreeMapping
     * @generated
     */
    public Adapter createTreeMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.tree.description.StyleUpdater <em>Style
     * Updater</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tree.description.StyleUpdater
     * @generated
     */
    public Adapter createStyleUpdaterAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.tree.description.TreeVariable <em>Tree
     * Variable</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tree.description.TreeVariable
     * @generated
     */
    public Adapter createTreeVariableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.tree.description.TreeItemUpdater <em>Tree
     * Item Updater</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tree.description.TreeItemUpdater
     * @generated
     */
    public Adapter createTreeItemUpdaterAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.tree.description.PrecedingSiblingsVariables <em>Preceding Siblings Variables</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tree.description.PrecedingSiblingsVariables
     * @generated
     */
    public Adapter createPrecedingSiblingsVariablesAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.tree.description.TreeItemMappingContainer
     * <em>Tree Item Mapping Container</em>}'. <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tree.description.TreeItemMappingContainer
     * @generated
     */
    public Adapter createTreeItemMappingContainerAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.tree.description.TreePopupMenu <em>Tree
     * Popup Menu</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tree.description.TreePopupMenu
     * @generated
     */
    public Adapter createTreePopupMenuAdapter() {
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
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.EndUserDocumentedElement <em>End User Documented Element</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.EndUserDocumentedElement
     * @generated
     */
    public Adapter createEndUserDocumentedElementAdapter() {
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
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.RepresentationDescription <em>Representation Description</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.RepresentationDescription
     * @generated
     */
    public Adapter createRepresentationDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.RepresentationElementMapping <em>Representation Element
     * Mapping</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.RepresentationElementMapping
     * @generated
     */
    public Adapter createRepresentationElementMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.description.style.StyleDescription <em>Description</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.style.StyleDescription
     * @generated
     */
    public Adapter createStyleDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription <em>Basic Label Style
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription
     * @generated
     */
    public Adapter createBasicLabelStyleDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.style.LabelStyleDescription <em>Label Style Description</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.style.LabelStyleDescription
     * @generated
     */
    public Adapter createLabelStyleDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.ConditionalStyleDescription <em>Conditional Style
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.ConditionalStyleDescription
     * @generated
     */
    public Adapter createConditionalStyleDescriptionAdapter() {
        return null;
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
     * Creates a new adapter for the default case. <!-- begin-user-doc --> This default implementation returns null.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} // DescriptionAdapterFactory
