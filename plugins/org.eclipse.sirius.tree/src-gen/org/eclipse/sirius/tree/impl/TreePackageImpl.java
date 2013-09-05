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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.sirius.SiriusPackage;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeElement;
import org.eclipse.sirius.tree.DTreeElementSynchronizer;
import org.eclipse.sirius.tree.DTreeElementUpdater;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.DTreeItemContainer;
import org.eclipse.sirius.tree.TreeFactory;
import org.eclipse.sirius.tree.TreeItemStyle;
import org.eclipse.sirius.tree.TreePackage;
import org.eclipse.sirius.tree.description.DescriptionPackage;
import org.eclipse.sirius.tree.description.impl.DescriptionPackageImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class TreePackageImpl extends EPackageImpl implements TreePackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass dTreeItemContainerEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass dTreeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass dTreeElementUpdaterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass dTreeElementEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass dTreeItemEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass treeItemStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass dTreeElementSynchronizerEClass = null;

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
     * @see org.eclipse.sirius.tree.TreePackage#eNS_URI
     * @see #init()
     * @generated
     */
    private TreePackageImpl() {
        super(eNS_URI, TreeFactory.eINSTANCE);
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
     * This method is used to initialize {@link TreePackage#eINSTANCE} when that
     * field is accessed. Clients should not invoke it directly. Instead, they
     * should simply access that field to obtain the package. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static TreePackage init() {
        if (isInited)
            return (TreePackage) EPackage.Registry.INSTANCE.getEPackage(TreePackage.eNS_URI);

        // Obtain or create and register package
        TreePackageImpl theTreePackage = (TreePackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof TreePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new TreePackageImpl());

        isInited = true;

        // Initialize simple dependencies
        SiriusPackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        DescriptionPackageImpl theDescriptionPackage = (DescriptionPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI) instanceof DescriptionPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(DescriptionPackage.eNS_URI) : DescriptionPackage.eINSTANCE);

        // Create package meta-data objects
        theTreePackage.createPackageContents();
        theDescriptionPackage.createPackageContents();

        // Initialize created meta-data
        theTreePackage.initializePackageContents();
        theDescriptionPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theTreePackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(TreePackage.eNS_URI, theTreePackage);
        return theTreePackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDTreeItemContainer() {
        return dTreeItemContainerEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDTreeItemContainer_OwnedTreeItems() {
        return (EReference) dTreeItemContainerEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDTree() {
        return dTreeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDTree_SemanticElements() {
        return (EReference) dTreeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDTree_Description() {
        return (EReference) dTreeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDTreeElementUpdater() {
        return dTreeElementUpdaterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDTreeElement() {
        return dTreeElementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDTreeElement_TreeElementMapping() {
        return (EReference) dTreeElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDTreeItem() {
        return dTreeItemEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDTreeItem_Expanded() {
        return (EAttribute) dTreeItemEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDTreeItem_OwnedStyle() {
        return (EReference) dTreeItemEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDTreeItem_ActualMapping() {
        return (EReference) dTreeItemEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDTreeItem_Container() {
        return (EReference) dTreeItemEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDTreeItem_StyleUpdater() {
        return (EReference) dTreeItemEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDTreeItem_Updater() {
        return (EReference) dTreeItemEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTreeItemStyle() {
        return treeItemStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTreeItemStyle_BackgroundColor() {
        return (EReference) treeItemStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDTreeElementSynchronizer() {
        return dTreeElementSynchronizerEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TreeFactory getTreeFactory() {
        return (TreeFactory) getEFactoryInstance();
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
        dTreeEClass = createEClass(DTREE);
        createEReference(dTreeEClass, DTREE__SEMANTIC_ELEMENTS);
        createEReference(dTreeEClass, DTREE__DESCRIPTION);

        dTreeElementUpdaterEClass = createEClass(DTREE_ELEMENT_UPDATER);

        dTreeElementEClass = createEClass(DTREE_ELEMENT);
        createEReference(dTreeElementEClass, DTREE_ELEMENT__TREE_ELEMENT_MAPPING);

        dTreeItemContainerEClass = createEClass(DTREE_ITEM_CONTAINER);
        createEReference(dTreeItemContainerEClass, DTREE_ITEM_CONTAINER__OWNED_TREE_ITEMS);

        dTreeItemEClass = createEClass(DTREE_ITEM);
        createEAttribute(dTreeItemEClass, DTREE_ITEM__EXPANDED);
        createEReference(dTreeItemEClass, DTREE_ITEM__OWNED_STYLE);
        createEReference(dTreeItemEClass, DTREE_ITEM__ACTUAL_MAPPING);
        createEReference(dTreeItemEClass, DTREE_ITEM__CONTAINER);
        createEReference(dTreeItemEClass, DTREE_ITEM__STYLE_UPDATER);
        createEReference(dTreeItemEClass, DTREE_ITEM__UPDATER);

        treeItemStyleEClass = createEClass(TREE_ITEM_STYLE);
        createEReference(treeItemStyleEClass, TREE_ITEM_STYLE__BACKGROUND_COLOR);

        dTreeElementSynchronizerEClass = createEClass(DTREE_ELEMENT_SYNCHRONIZER);
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
        SiriusPackage theSiriusPackage = (SiriusPackage) EPackage.Registry.INSTANCE.getEPackage(SiriusPackage.eNS_URI);
        EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

        // Add subpackages
        getESubpackages().add(theDescriptionPackage);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        dTreeEClass.getESuperTypes().add(theSiriusPackage.getDRepresentation());
        dTreeEClass.getESuperTypes().add(this.getDTreeItemContainer());
        dTreeEClass.getESuperTypes().add(this.getDTreeElementUpdater());
        dTreeElementEClass.getESuperTypes().add(theSiriusPackage.getDRepresentationElement());
        dTreeItemContainerEClass.getESuperTypes().add(theSiriusPackage.getDSemanticDecorator());
        dTreeItemEClass.getESuperTypes().add(this.getDTreeItemContainer());
        dTreeItemEClass.getESuperTypes().add(this.getDTreeElement());
        dTreeItemEClass.getESuperTypes().add(this.getDTreeElementUpdater());
        treeItemStyleEClass.getESuperTypes().add(theSiriusPackage.getStyle());
        treeItemStyleEClass.getESuperTypes().add(theSiriusPackage.getLabelStyle());

        // Initialize classes and features; add operations and parameters
        initEClass(dTreeEClass, DTree.class, "DTree", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDTree_SemanticElements(), theEcorePackage.getEObject(), null, "semanticElements", null, 0, -1, DTree.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDTree_Description(), theDescriptionPackage.getTreeDescription(), null, "description", null, 1, 1, DTree.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(dTreeElementUpdaterEClass, DTreeElementUpdater.class, "DTreeElementUpdater", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        EOperation op = addEOperation(dTreeElementUpdaterEClass, null, "activate", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getDTreeElementSynchronizer(), "sync", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(dTreeElementUpdaterEClass, null, "deactivate", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(dTreeElementEClass, DTreeElement.class, "DTreeElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDTreeElement_TreeElementMapping(), theDescriptionPackage.getTreeMapping(), null, "treeElementMapping", null, 0, 1, DTreeElement.class, IS_TRANSIENT, IS_VOLATILE,
                !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

        initEClass(dTreeItemContainerEClass, DTreeItemContainer.class, "DTreeItemContainer", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDTreeItemContainer_OwnedTreeItems(), this.getDTreeItem(), this.getDTreeItem_Container(), "ownedTreeItems", null, 0, -1, DTreeItemContainer.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(dTreeItemEClass, DTreeItem.class, "DTreeItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDTreeItem_Expanded(), theEcorePackage.getEBoolean(), "expanded", null, 1, 1, DTreeItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEReference(getDTreeItem_OwnedStyle(), this.getTreeItemStyle(), null, "ownedStyle", null, 1, 1, DTreeItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDTreeItem_ActualMapping(), theDescriptionPackage.getTreeItemMapping(), null, "actualMapping", null, 1, 1, DTreeItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDTreeItem_Container(), this.getDTreeItemContainer(), this.getDTreeItemContainer_OwnedTreeItems(), "container", null, 0, 1, DTreeItem.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDTreeItem_StyleUpdater(), theDescriptionPackage.getStyleUpdater(), null, "styleUpdater", null, 0, 1, DTreeItem.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDTreeItem_Updater(), theDescriptionPackage.getTreeItemUpdater(), null, "updater", null, 0, 1, DTreeItem.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

        initEClass(treeItemStyleEClass, TreeItemStyle.class, "TreeItemStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTreeItemStyle_BackgroundColor(), theSiriusPackage.getRGBValues(), null, "backgroundColor", null, 0, 1, TreeItemStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(dTreeElementSynchronizerEClass, DTreeElementSynchronizer.class, "DTreeElementSynchronizer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        op = addEOperation(dTreeElementSynchronizerEClass, null, "refresh", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getDTreeItem(), "DTreeItem", 1, 1, IS_UNIQUE, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} // TreePackageImpl
