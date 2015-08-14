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
package org.eclipse.sirius.tree.description.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.sirius.tree.business.internal.color.DefaultColorStyleDescription;
import org.eclipse.sirius.tree.business.internal.metamodel.spec.TreeCreationDescriptionSpec;
import org.eclipse.sirius.tree.business.internal.metamodel.spec.TreeItemDeletionToolSpec;
import org.eclipse.sirius.tree.business.internal.metamodel.spec.TreeItemMappingSpec;
import org.eclipse.sirius.tree.business.internal.metamodel.spec.TreeNavigationDescriptionSpec;
import org.eclipse.sirius.tree.description.ConditionalTreeItemStyleDescription;
import org.eclipse.sirius.tree.description.DescriptionFactory;
import org.eclipse.sirius.tree.description.DescriptionPackage;
import org.eclipse.sirius.tree.description.PrecedingSiblingsVariables;
import org.eclipse.sirius.tree.description.StyleUpdater;
import org.eclipse.sirius.tree.description.TreeCreationDescription;
import org.eclipse.sirius.tree.description.TreeDescription;
import org.eclipse.sirius.tree.description.TreeDragSource;
import org.eclipse.sirius.tree.description.TreeItemContainerDropTool;
import org.eclipse.sirius.tree.description.TreeItemCreationTool;
import org.eclipse.sirius.tree.description.TreeItemDeletionTool;
import org.eclipse.sirius.tree.description.TreeItemDragTool;
import org.eclipse.sirius.tree.description.TreeItemEditionTool;
import org.eclipse.sirius.tree.description.TreeItemMapping;
import org.eclipse.sirius.tree.description.TreeItemStyleDescription;
import org.eclipse.sirius.tree.description.TreeItemUpdater;
import org.eclipse.sirius.tree.description.TreeMapping;
import org.eclipse.sirius.tree.description.TreeNavigationDescription;
import org.eclipse.sirius.tree.description.TreePopupMenu;
import org.eclipse.sirius.tree.description.TreeVariable;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class DescriptionFactoryImpl extends EFactoryImpl implements DescriptionFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public static DescriptionFactory init() {
        try {
            DescriptionFactory theDescriptionFactory = (DescriptionFactory) EPackage.Registry.INSTANCE.getEFactory(DescriptionPackage.eNS_URI);
            if (theDescriptionFactory != null) {
                return theDescriptionFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new DescriptionFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public DescriptionFactoryImpl() {
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
        case DescriptionPackage.TREE_DESCRIPTION:
            return createTreeDescription();
        case DescriptionPackage.TREE_ITEM_MAPPING:
            return createTreeItemMapping();
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION:
            return createTreeItemStyleDescription();
        case DescriptionPackage.CONDITIONAL_TREE_ITEM_STYLE_DESCRIPTION:
            return createConditionalTreeItemStyleDescription();
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL:
            return createTreeItemDragTool();
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL:
            return createTreeItemContainerDropTool();
        case DescriptionPackage.TREE_ITEM_CREATION_TOOL:
            return createTreeItemCreationTool();
        case DescriptionPackage.TREE_ITEM_EDITION_TOOL:
            return createTreeItemEditionTool();
        case DescriptionPackage.TREE_ITEM_DELETION_TOOL:
            return createTreeItemDeletionTool();
        case DescriptionPackage.TREE_CREATION_DESCRIPTION:
            return createTreeCreationDescription();
        case DescriptionPackage.TREE_NAVIGATION_DESCRIPTION:
            return createTreeNavigationDescription();
        case DescriptionPackage.TREE_MAPPING:
            return createTreeMapping();
        case DescriptionPackage.STYLE_UPDATER:
            return createStyleUpdater();
        case DescriptionPackage.TREE_VARIABLE:
            return createTreeVariable();
        case DescriptionPackage.TREE_ITEM_UPDATER:
            return createTreeItemUpdater();
        case DescriptionPackage.PRECEDING_SIBLINGS_VARIABLES:
            return createPrecedingSiblingsVariables();
        case DescriptionPackage.TREE_POPUP_MENU:
            return createTreePopupMenu();
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
        case DescriptionPackage.TREE_DRAG_SOURCE:
            return createTreeDragSourceFromString(eDataType, initialValue);
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
        case DescriptionPackage.TREE_DRAG_SOURCE:
            return convertTreeDragSourceToString(eDataType, instanceValue);
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
    public TreeDescription createTreeDescription() {
        TreeDescriptionImpl treeDescription = new TreeDescriptionImpl();
        return treeDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public TreeItemMapping createTreeItemMapping() {
        TreeItemMappingImpl treeItemMapping = new TreeItemMappingSpec();
        return treeItemMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public TreeItemStyleDescription createTreeItemStyleDescription() {
        TreeItemStyleDescriptionImpl treeItemStyleDescription = new TreeItemStyleDescriptionImpl();
        new DefaultColorStyleDescription().setDefaultColors(treeItemStyleDescription);
        return treeItemStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public ConditionalTreeItemStyleDescription createConditionalTreeItemStyleDescription() {
        ConditionalTreeItemStyleDescriptionImpl conditionalTreeItemStyleDescription = new ConditionalTreeItemStyleDescriptionImpl();
        new DefaultColorStyleDescription().setDefaultColors(conditionalTreeItemStyleDescription);
        return conditionalTreeItemStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TreeItemDragTool createTreeItemDragTool() {
        TreeItemDragToolImpl treeItemDragTool = new TreeItemDragToolImpl();
        return treeItemDragTool;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TreeItemContainerDropTool createTreeItemContainerDropTool() {
        TreeItemContainerDropToolImpl treeItemContainerDropTool = new TreeItemContainerDropToolImpl();
        return treeItemContainerDropTool;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TreeItemCreationTool createTreeItemCreationTool() {
        TreeItemCreationToolImpl treeItemCreationTool = new TreeItemCreationToolImpl();
        return treeItemCreationTool;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TreeItemEditionTool createTreeItemEditionTool() {
        TreeItemEditionToolImpl treeItemEditionTool = new TreeItemEditionToolImpl();
        return treeItemEditionTool;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public TreeItemDeletionTool createTreeItemDeletionTool() {
        TreeItemDeletionToolImpl treeItemDeletionTool = new TreeItemDeletionToolSpec();
        return treeItemDeletionTool;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public TreeCreationDescription createTreeCreationDescription() {
        TreeCreationDescriptionImpl treeCreationDescription = new TreeCreationDescriptionSpec();
        return treeCreationDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public TreeNavigationDescription createTreeNavigationDescription() {
        TreeNavigationDescriptionImpl treeNavigationDescription = new TreeNavigationDescriptionSpec();
        return treeNavigationDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TreeMapping createTreeMapping() {
        TreeMappingImpl treeMapping = new TreeMappingImpl();
        return treeMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public StyleUpdater createStyleUpdater() {
        StyleUpdaterImpl styleUpdater = new StyleUpdaterImpl();
        return styleUpdater;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TreeVariable createTreeVariable() {
        TreeVariableImpl treeVariable = new TreeVariableImpl();
        return treeVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TreeItemUpdater createTreeItemUpdater() {
        TreeItemUpdaterImpl treeItemUpdater = new TreeItemUpdaterImpl();
        return treeItemUpdater;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public PrecedingSiblingsVariables createPrecedingSiblingsVariables() {
        PrecedingSiblingsVariablesImpl precedingSiblingsVariables = new PrecedingSiblingsVariablesImpl();
        return precedingSiblingsVariables;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TreePopupMenu createTreePopupMenu() {
        TreePopupMenuImpl treePopupMenu = new TreePopupMenuImpl();
        return treePopupMenu;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TreeDragSource createTreeDragSourceFromString(EDataType eDataType, String initialValue) {
        TreeDragSource result = TreeDragSource.get(initialValue);
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
    public String convertTreeDragSourceToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DescriptionPackage getDescriptionPackage() {
        return (DescriptionPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @deprecated
     * @generated
     */
    @Deprecated
    public static DescriptionPackage getPackage() {
        return DescriptionPackage.eINSTANCE;
    }

} // DescriptionFactoryImpl
