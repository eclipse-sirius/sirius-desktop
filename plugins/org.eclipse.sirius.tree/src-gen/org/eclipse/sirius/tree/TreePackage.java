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
package org.eclipse.sirius.tree;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains
 * accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * 
 * @see org.eclipse.sirius.tree.TreeFactory
 * @model kind="package"
 * @generated
 */
public interface TreePackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNAME = "tree"; //$NON-NLS-1$

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius/tree/1.0.0"; //$NON-NLS-1$

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_PREFIX = "tree"; //$NON-NLS-1$

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    TreePackage eINSTANCE = org.eclipse.sirius.tree.impl.TreePackageImpl.init();

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tree.impl.DTreeItemContainerImpl
     * <em>DTree Item Container</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.tree.impl.DTreeItemContainerImpl
     * @see org.eclipse.sirius.tree.impl.TreePackageImpl#getDTreeItemContainer()
     * @generated
     */
    int DTREE_ITEM_CONTAINER = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tree.impl.DTreeImpl <em>DTree</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.tree.impl.DTreeImpl
     * @see org.eclipse.sirius.tree.impl.TreePackageImpl#getDTree()
     * @generated
     */
    int DTREE = 0;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DTREE__DOCUMENTATION = ViewpointPackage.DREPRESENTATION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DTREE__EANNOTATIONS = ViewpointPackage.DREPRESENTATION__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Owned Representation Elements</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DTREE__OWNED_REPRESENTATION_ELEMENTS = ViewpointPackage.DREPRESENTATION__OWNED_REPRESENTATION_ELEMENTS;

    /**
     * The feature id for the '<em><b>Representation Elements</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DTREE__REPRESENTATION_ELEMENTS = ViewpointPackage.DREPRESENTATION__REPRESENTATION_ELEMENTS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DTREE__NAME = ViewpointPackage.DREPRESENTATION__NAME;

    /**
     * The feature id for the '<em><b>Owned Annotation Entries</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DTREE__OWNED_ANNOTATION_ENTRIES = ViewpointPackage.DREPRESENTATION__OWNED_ANNOTATION_ENTRIES;

    /**
     * The feature id for the '<em><b>Ui State</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DTREE__UI_STATE = ViewpointPackage.DREPRESENTATION__UI_STATE;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DTREE__TARGET = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Owned Tree Items</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DTREE__OWNED_TREE_ITEMS = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DTREE__SEMANTIC_ELEMENTS = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DTREE__DESCRIPTION = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>DTree</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DTREE_FEATURE_COUNT = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tree.impl.DTreeElementImpl
     * <em>DTree Element</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.tree.impl.DTreeElementImpl
     * @see org.eclipse.sirius.tree.impl.TreePackageImpl#getDTreeElement()
     * @generated
     */
    int DTREE_ELEMENT = 1;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DTREE_ELEMENT__TARGET = ViewpointPackage.DREPRESENTATION_ELEMENT__TARGET;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DTREE_ELEMENT__NAME = ViewpointPackage.DREPRESENTATION_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DTREE_ELEMENT__SEMANTIC_ELEMENTS = ViewpointPackage.DREPRESENTATION_ELEMENT__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Tree Element Mapping</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DTREE_ELEMENT__TREE_ELEMENT_MAPPING = ViewpointPackage.DREPRESENTATION_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>DTree Element</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DTREE_ELEMENT_FEATURE_COUNT = ViewpointPackage.DREPRESENTATION_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DTREE_ITEM_CONTAINER__TARGET = ViewpointPackage.DSEMANTIC_DECORATOR__TARGET;

    /**
     * The feature id for the '<em><b>Owned Tree Items</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DTREE_ITEM_CONTAINER__OWNED_TREE_ITEMS = ViewpointPackage.DSEMANTIC_DECORATOR_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>DTree Item Container</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DTREE_ITEM_CONTAINER_FEATURE_COUNT = ViewpointPackage.DSEMANTIC_DECORATOR_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tree.impl.DTreeItemImpl <em>DTree Item</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.tree.impl.DTreeItemImpl
     * @see org.eclipse.sirius.tree.impl.TreePackageImpl#getDTreeItem()
     * @generated
     */
    int DTREE_ITEM = 3;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DTREE_ITEM__TARGET = TreePackage.DTREE_ITEM_CONTAINER__TARGET;

    /**
     * The feature id for the '<em><b>Owned Tree Items</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DTREE_ITEM__OWNED_TREE_ITEMS = TreePackage.DTREE_ITEM_CONTAINER__OWNED_TREE_ITEMS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DTREE_ITEM__NAME = TreePackage.DTREE_ITEM_CONTAINER_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DTREE_ITEM__SEMANTIC_ELEMENTS = TreePackage.DTREE_ITEM_CONTAINER_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Tree Element Mapping</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DTREE_ITEM__TREE_ELEMENT_MAPPING = TreePackage.DTREE_ITEM_CONTAINER_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Expanded</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DTREE_ITEM__EXPANDED = TreePackage.DTREE_ITEM_CONTAINER_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Owned Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DTREE_ITEM__OWNED_STYLE = TreePackage.DTREE_ITEM_CONTAINER_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Actual Mapping</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DTREE_ITEM__ACTUAL_MAPPING = TreePackage.DTREE_ITEM_CONTAINER_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Container</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DTREE_ITEM__CONTAINER = TreePackage.DTREE_ITEM_CONTAINER_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Style Updater</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DTREE_ITEM__STYLE_UPDATER = TreePackage.DTREE_ITEM_CONTAINER_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Updater</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DTREE_ITEM__UPDATER = TreePackage.DTREE_ITEM_CONTAINER_FEATURE_COUNT + 8;

    /**
     * The number of structural features of the '<em>DTree Item</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DTREE_ITEM_FEATURE_COUNT = TreePackage.DTREE_ITEM_CONTAINER_FEATURE_COUNT + 9;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tree.impl.TreeItemStyleImpl
     * <em>Item Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.tree.impl.TreeItemStyleImpl
     * @see org.eclipse.sirius.tree.impl.TreePackageImpl#getTreeItemStyle()
     * @generated
     */
    int TREE_ITEM_STYLE = 4;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TREE_ITEM_STYLE__CUSTOM_FEATURES = ViewpointPackage.STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TREE_ITEM_STYLE__DESCRIPTION = ViewpointPackage.STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TREE_ITEM_STYLE__LABEL_SIZE = ViewpointPackage.STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TREE_ITEM_STYLE__LABEL_FORMAT = ViewpointPackage.STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TREE_ITEM_STYLE__SHOW_ICON = ViewpointPackage.STYLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TREE_ITEM_STYLE__ICON_PATH = ViewpointPackage.STYLE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TREE_ITEM_STYLE__LABEL_COLOR = ViewpointPackage.STYLE_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TREE_ITEM_STYLE__LABEL_ALIGNMENT = ViewpointPackage.STYLE_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Background Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TREE_ITEM_STYLE__BACKGROUND_COLOR = ViewpointPackage.STYLE_FEATURE_COUNT + 6;

    /**
     * The number of structural features of the '<em>Item Style</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TREE_ITEM_STYLE_FEATURE_COUNT = ViewpointPackage.STYLE_FEATURE_COUNT + 7;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tree.impl.DTreeElementSynchronizerImpl
     * <em>DTree Element Synchronizer</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.tree.impl.DTreeElementSynchronizerImpl
     * @see org.eclipse.sirius.tree.impl.TreePackageImpl#getDTreeElementSynchronizer()
     * @generated
     */
    int DTREE_ELEMENT_SYNCHRONIZER = 5;

    /**
     * The number of structural features of the '
     * <em>DTree Element Synchronizer</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DTREE_ELEMENT_SYNCHRONIZER_FEATURE_COUNT = 0;

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tree.DTreeItemContainer
     * <em>DTree Item Container</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>DTree Item Container</em>'.
     * @see org.eclipse.sirius.tree.DTreeItemContainer
     * @generated
     */
    EClass getDTreeItemContainer();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tree.DTreeItemContainer#getOwnedTreeItems
     * <em>Owned Tree Items</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Owned Tree Items</em>'.
     * @see org.eclipse.sirius.tree.DTreeItemContainer#getOwnedTreeItems()
     * @see #getDTreeItemContainer()
     * @generated
     */
    EReference getDTreeItemContainer_OwnedTreeItems();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.tree.DTree
     * <em>DTree</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DTree</em>'.
     * @see org.eclipse.sirius.tree.DTree
     * @generated
     */
    EClass getDTree();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.tree.DTree#getSemanticElements
     * <em>Semantic Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the reference list '
     *         <em>Semantic Elements</em>'.
     * @see org.eclipse.sirius.tree.DTree#getSemanticElements()
     * @see #getDTree()
     * @generated
     */
    EReference getDTree_SemanticElements();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.tree.DTree#getDescription <em>Description</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Description</em>'.
     * @see org.eclipse.sirius.tree.DTree#getDescription()
     * @see #getDTree()
     * @generated
     */
    EReference getDTree_Description();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tree.DTreeElement <em>DTree Element</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DTree Element</em>'.
     * @see org.eclipse.sirius.tree.DTreeElement
     * @generated
     */
    EClass getDTreeElement();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.tree.DTreeElement#getTreeElementMapping
     * <em>Tree Element Mapping</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Tree Element Mapping</em>
     *         '.
     * @see org.eclipse.sirius.tree.DTreeElement#getTreeElementMapping()
     * @see #getDTreeElement()
     * @generated
     */
    EReference getDTreeElement_TreeElementMapping();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tree.DTreeItem <em>DTree Item</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>DTree Item</em>'.
     * @see org.eclipse.sirius.tree.DTreeItem
     * @generated
     */
    EClass getDTreeItem();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tree.DTreeItem#isExpanded <em>Expanded</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Expanded</em>'.
     * @see org.eclipse.sirius.tree.DTreeItem#isExpanded()
     * @see #getDTreeItem()
     * @generated
     */
    EAttribute getDTreeItem_Expanded();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tree.DTreeItem#getOwnedStyle
     * <em>Owned Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Owned Style</em>'.
     * @see org.eclipse.sirius.tree.DTreeItem#getOwnedStyle()
     * @see #getDTreeItem()
     * @generated
     */
    EReference getDTreeItem_OwnedStyle();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.tree.DTreeItem#getActualMapping
     * <em>Actual Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Actual Mapping</em>'.
     * @see org.eclipse.sirius.tree.DTreeItem#getActualMapping()
     * @see #getDTreeItem()
     * @generated
     */
    EReference getDTreeItem_ActualMapping();

    /**
     * Returns the meta object for the container reference '
     * {@link org.eclipse.sirius.tree.DTreeItem#getContainer <em>Container</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the container reference '<em>Container</em>'.
     * @see org.eclipse.sirius.tree.DTreeItem#getContainer()
     * @see #getDTreeItem()
     * @generated
     */
    EReference getDTreeItem_Container();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.tree.DTreeItem#getStyleUpdater
     * <em>Style Updater</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Style Updater</em>'.
     * @see org.eclipse.sirius.tree.DTreeItem#getStyleUpdater()
     * @see #getDTreeItem()
     * @generated
     */
    EReference getDTreeItem_StyleUpdater();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.tree.DTreeItem#getUpdater <em>Updater</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Updater</em>'.
     * @see org.eclipse.sirius.tree.DTreeItem#getUpdater()
     * @see #getDTreeItem()
     * @generated
     */
    EReference getDTreeItem_Updater();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tree.TreeItemStyle <em>Item Style</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Item Style</em>'.
     * @see org.eclipse.sirius.tree.TreeItemStyle
     * @generated
     */
    EClass getTreeItemStyle();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tree.TreeItemStyle#getBackgroundColor
     * <em>Background Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Background Color</em>'.
     * @see org.eclipse.sirius.tree.TreeItemStyle#getBackgroundColor()
     * @see #getTreeItemStyle()
     * @generated
     */
    EAttribute getTreeItemStyle_BackgroundColor();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tree.DTreeElementSynchronizer
     * <em>DTree Element Synchronizer</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>DTree Element Synchronizer</em>'.
     * @see org.eclipse.sirius.tree.DTreeElementSynchronizer
     * @generated
     */
    EClass getDTreeElementSynchronizer();

    /**
     * Returns the factory that creates the instances of the model. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    TreeFactory getTreeFactory();

    /**
     * <!-- begin-user-doc --> Defines literals for the meta objects that
     * represent
     * <ul>
     * <li>each class,</li>
     * <li>each feature of each class,</li>
     * <li>each enum,</li>
     * <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tree.impl.DTreeItemContainerImpl
         * <em>DTree Item Container</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.tree.impl.DTreeItemContainerImpl
         * @see org.eclipse.sirius.tree.impl.TreePackageImpl#getDTreeItemContainer()
         * @generated
         */
        EClass DTREE_ITEM_CONTAINER = TreePackage.eINSTANCE.getDTreeItemContainer();

        /**
         * The meta object literal for the '<em><b>Owned Tree Items</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DTREE_ITEM_CONTAINER__OWNED_TREE_ITEMS = TreePackage.eINSTANCE.getDTreeItemContainer_OwnedTreeItems();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tree.impl.DTreeImpl <em>DTree</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.tree.impl.DTreeImpl
         * @see org.eclipse.sirius.tree.impl.TreePackageImpl#getDTree()
         * @generated
         */
        EClass DTREE = TreePackage.eINSTANCE.getDTree();

        /**
         * The meta object literal for the '<em><b>Semantic Elements</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DTREE__SEMANTIC_ELEMENTS = TreePackage.eINSTANCE.getDTree_SemanticElements();

        /**
         * The meta object literal for the '<em><b>Description</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DTREE__DESCRIPTION = TreePackage.eINSTANCE.getDTree_Description();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tree.impl.DTreeElementImpl
         * <em>DTree Element</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.tree.impl.DTreeElementImpl
         * @see org.eclipse.sirius.tree.impl.TreePackageImpl#getDTreeElement()
         * @generated
         */
        EClass DTREE_ELEMENT = TreePackage.eINSTANCE.getDTreeElement();

        /**
         * The meta object literal for the '<em><b>Tree Element Mapping</b></em>
         * ' reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DTREE_ELEMENT__TREE_ELEMENT_MAPPING = TreePackage.eINSTANCE.getDTreeElement_TreeElementMapping();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tree.impl.DTreeItemImpl
         * <em>DTree Item</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.tree.impl.DTreeItemImpl
         * @see org.eclipse.sirius.tree.impl.TreePackageImpl#getDTreeItem()
         * @generated
         */
        EClass DTREE_ITEM = TreePackage.eINSTANCE.getDTreeItem();

        /**
         * The meta object literal for the '<em><b>Expanded</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DTREE_ITEM__EXPANDED = TreePackage.eINSTANCE.getDTreeItem_Expanded();

        /**
         * The meta object literal for the '<em><b>Owned Style</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DTREE_ITEM__OWNED_STYLE = TreePackage.eINSTANCE.getDTreeItem_OwnedStyle();

        /**
         * The meta object literal for the '<em><b>Actual Mapping</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DTREE_ITEM__ACTUAL_MAPPING = TreePackage.eINSTANCE.getDTreeItem_ActualMapping();

        /**
         * The meta object literal for the '<em><b>Container</b></em>' container
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DTREE_ITEM__CONTAINER = TreePackage.eINSTANCE.getDTreeItem_Container();

        /**
         * The meta object literal for the '<em><b>Style Updater</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DTREE_ITEM__STYLE_UPDATER = TreePackage.eINSTANCE.getDTreeItem_StyleUpdater();

        /**
         * The meta object literal for the '<em><b>Updater</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DTREE_ITEM__UPDATER = TreePackage.eINSTANCE.getDTreeItem_Updater();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tree.impl.TreeItemStyleImpl
         * <em>Item Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.tree.impl.TreeItemStyleImpl
         * @see org.eclipse.sirius.tree.impl.TreePackageImpl#getTreeItemStyle()
         * @generated
         */
        EClass TREE_ITEM_STYLE = TreePackage.eINSTANCE.getTreeItemStyle();

        /**
         * The meta object literal for the '<em><b>Background Color</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TREE_ITEM_STYLE__BACKGROUND_COLOR = TreePackage.eINSTANCE.getTreeItemStyle_BackgroundColor();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tree.impl.DTreeElementSynchronizerImpl
         * <em>DTree Element Synchronizer</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.tree.impl.DTreeElementSynchronizerImpl
         * @see org.eclipse.sirius.tree.impl.TreePackageImpl#getDTreeElementSynchronizer()
         * @generated
         */
        EClass DTREE_ELEMENT_SYNCHRONIZER = TreePackage.eINSTANCE.getDTreeElementSynchronizer();

    }

} // TreePackage
