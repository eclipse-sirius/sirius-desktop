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
package org.eclipse.sirius.tree.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeElement;
import org.eclipse.sirius.tree.DTreeElementSynchronizer;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.TreeFactory;
import org.eclipse.sirius.tree.TreeItemStyle;
import org.eclipse.sirius.tree.TreePackage;
import org.eclipse.sirius.tree.business.internal.metamodel.spec.DTreeItemSpec;
import org.eclipse.sirius.tree.business.internal.metamodel.spec.DTreeSpec;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class TreeFactoryImpl extends EFactoryImpl implements TreeFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public static TreeFactory init() {
        try {
            TreeFactory theTreeFactory = (TreeFactory) EPackage.Registry.INSTANCE.getEFactory(TreePackage.eNS_URI);
            if (theTreeFactory != null) {
                return theTreeFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new TreeFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public TreeFactoryImpl() {
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
        case TreePackage.DTREE:
            return createDTree();
        case TreePackage.DTREE_ELEMENT:
            return createDTreeElement();
        case TreePackage.DTREE_ITEM:
            return createDTreeItem();
        case TreePackage.TREE_ITEM_STYLE:
            return createTreeItemStyle();
        case TreePackage.DTREE_ELEMENT_SYNCHRONIZER:
            return createDTreeElementSynchronizer();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public DTree createDTree() {
        DTreeImpl dTree = new DTreeSpec();
        return dTree;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DTreeElement createDTreeElement() {
        DTreeElementImpl dTreeElement = new DTreeElementImpl();
        return dTreeElement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public DTreeItem createDTreeItem() {
        DTreeItemImpl dTreeItem = new DTreeItemSpec();
        return dTreeItem;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TreeItemStyle createTreeItemStyle() {
        TreeItemStyleImpl treeItemStyle = new TreeItemStyleImpl();
        return treeItemStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DTreeElementSynchronizer createDTreeElementSynchronizer() {
        DTreeElementSynchronizerImpl dTreeElementSynchronizer = new DTreeElementSynchronizerImpl();
        return dTreeElementSynchronizer;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TreePackage getTreePackage() {
        return (TreePackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @deprecated
     * @generated
     */
    @Deprecated
    public static TreePackage getPackage() {
        return TreePackage.eINSTANCE;
    }

} // TreeFactoryImpl
