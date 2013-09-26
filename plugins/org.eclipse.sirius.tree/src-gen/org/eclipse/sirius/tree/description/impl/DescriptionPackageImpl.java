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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.sirius.tree.TreePackage;
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
import org.eclipse.sirius.tree.description.TreeItemMappingContainer;
import org.eclipse.sirius.tree.description.TreeItemStyleDescription;
import org.eclipse.sirius.tree.description.TreeItemTool;
import org.eclipse.sirius.tree.description.TreeItemUpdater;
import org.eclipse.sirius.tree.description.TreeMapping;
import org.eclipse.sirius.tree.description.TreeNavigationDescription;
import org.eclipse.sirius.tree.description.TreePopupMenu;
import org.eclipse.sirius.tree.description.TreeVariable;
import org.eclipse.sirius.tree.impl.TreePackageImpl;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.style.StylePackage;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class DescriptionPackageImpl extends EPackageImpl implements DescriptionPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass treeDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass treeItemMappingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass treeItemStyleDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass conditionalTreeItemStyleDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass treeItemToolEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass treeItemDragToolEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass treeItemContainerDropToolEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass treeItemCreationToolEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass treeItemEditionToolEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass treeItemDeletionToolEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass treeCreationDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass treeNavigationDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass treeMappingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass styleUpdaterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass treeVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass treeItemUpdaterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass precedingSiblingsVariablesEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass treeItemMappingContainerEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass treePopupMenuEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum treeDragSourceEEnum = null;

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
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private DescriptionPackageImpl() {
        super(eNS_URI, DescriptionFactory.eINSTANCE);
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
     * This method is used to initialize {@link DescriptionPackage#eINSTANCE}
     * when that field is accessed. Clients should not invoke it directly.
     * Instead, they should simply access that field to obtain the package. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static DescriptionPackage init() {
        if (isInited)
            return (DescriptionPackage) EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI);

        // Obtain or create and register package
        DescriptionPackageImpl theDescriptionPackage = (DescriptionPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof DescriptionPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
                : new DescriptionPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        ViewpointPackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        TreePackageImpl theTreePackage = (TreePackageImpl) (EPackage.Registry.INSTANCE.getEPackage(TreePackage.eNS_URI) instanceof TreePackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(TreePackage.eNS_URI) : TreePackage.eINSTANCE);

        // Create package meta-data objects
        theDescriptionPackage.createPackageContents();
        theTreePackage.createPackageContents();

        // Initialize created meta-data
        theDescriptionPackage.initializePackageContents();
        theTreePackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theDescriptionPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(DescriptionPackage.eNS_URI, theDescriptionPackage);
        return theDescriptionPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTreeDescription() {
        return treeDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTreeDescription_DomainClass() {
        return (EAttribute) treeDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTreeDescription_PreconditionExpression() {
        return (EAttribute) treeDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTreeDescription_CreateTreeItem() {
        return (EReference) treeDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTreeDescription_OwnedRepresentationCreationDescriptions() {
        return (EReference) treeDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTreeDescription_OwnedRepresentationNavigationDescriptions() {
        return (EReference) treeDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTreeItemMapping() {
        return treeItemMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTreeItemMapping_DomainClass() {
        return (EAttribute) treeItemMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTreeItemMapping_PreconditionExpression() {
        return (EAttribute) treeItemMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTreeItemMapping_SemanticCandidatesExpression() {
        return (EAttribute) treeItemMappingEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTreeItemMapping_ReusedTreeItemMappings() {
        return (EReference) treeItemMappingEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTreeItemMapping_AllSubMappings() {
        return (EReference) treeItemMappingEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTreeItemMapping_Specialize() {
        return (EReference) treeItemMappingEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTreeItemMapping_Delete() {
        return (EReference) treeItemMappingEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTreeItemMapping_Create() {
        return (EReference) treeItemMappingEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTreeItemMapping_DndTools() {
        return (EReference) treeItemMappingEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTreeItemMapping_PopupMenus() {
        return (EReference) treeItemMappingEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTreeItemStyleDescription() {
        return treeItemStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTreeItemStyleDescription_BackgroundColor() {
        return (EReference) treeItemStyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getConditionalTreeItemStyleDescription() {
        return conditionalTreeItemStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getConditionalTreeItemStyleDescription_Style() {
        return (EReference) conditionalTreeItemStyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTreeItemTool() {
        return treeItemToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTreeItemTool_FirstModelOperation() {
        return (EReference) treeItemToolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTreeItemTool_Variables() {
        return (EReference) treeItemToolEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTreeItemDragTool() {
        return treeItemDragToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTreeItemDragTool_OldContainer() {
        return (EReference) treeItemDragToolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTreeItemDragTool_NewContainer() {
        return (EReference) treeItemDragToolEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTreeItemDragTool_Element() {
        return (EReference) treeItemDragToolEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTreeItemDragTool_NewViewContainer() {
        return (EReference) treeItemDragToolEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTreeItemDragTool_Containers() {
        return (EReference) treeItemDragToolEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTreeItemDragTool_DragSourceType() {
        return (EAttribute) treeItemDragToolEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTreeItemDragTool_PrecedingSiblings() {
        return (EReference) treeItemDragToolEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTreeItemContainerDropTool() {
        return treeItemContainerDropToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTreeItemContainerDropTool_OldContainer() {
        return (EReference) treeItemContainerDropToolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTreeItemContainerDropTool_NewContainer() {
        return (EReference) treeItemContainerDropToolEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTreeItemContainerDropTool_Element() {
        return (EReference) treeItemContainerDropToolEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTreeItemContainerDropTool_NewViewContainer() {
        return (EReference) treeItemContainerDropToolEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTreeItemContainerDropTool_PrecedingSiblings() {
        return (EReference) treeItemContainerDropToolEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTreeItemContainerDropTool_DragSource() {
        return (EAttribute) treeItemContainerDropToolEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTreeItemCreationTool() {
        return treeItemCreationToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTreeItemCreationTool_Mapping() {
        return (EReference) treeItemCreationToolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTreeItemEditionTool() {
        return treeItemEditionToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTreeItemEditionTool_Mask() {
        return (EReference) treeItemEditionToolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTreeItemEditionTool_Mapping() {
        return (EReference) treeItemEditionToolEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTreeItemEditionTool_Element() {
        return (EReference) treeItemEditionToolEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTreeItemEditionTool_Root() {
        return (EReference) treeItemEditionToolEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTreeItemDeletionTool() {
        return treeItemDeletionToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTreeItemDeletionTool_Mapping() {
        return (EReference) treeItemDeletionToolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTreeCreationDescription() {
        return treeCreationDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTreeCreationDescription_TreeDescription() {
        return (EReference) treeCreationDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTreeNavigationDescription() {
        return treeNavigationDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTreeNavigationDescription_TreeDescription() {
        return (EReference) treeNavigationDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTreeMapping() {
        return treeMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTreeMapping_SemanticElements() {
        return (EAttribute) treeMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getStyleUpdater() {
        return styleUpdaterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getStyleUpdater_DefaultStyle() {
        return (EReference) styleUpdaterEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getStyleUpdater_ConditionalStyles() {
        return (EReference) styleUpdaterEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTreeVariable() {
        return treeVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTreeVariable_Documentation() {
        return (EAttribute) treeVariableEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTreeItemUpdater() {
        return treeItemUpdaterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTreeItemUpdater_DirectEdit() {
        return (EReference) treeItemUpdaterEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getPrecedingSiblingsVariables() {
        return precedingSiblingsVariablesEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTreeItemMappingContainer() {
        return treeItemMappingContainerEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTreeItemMappingContainer_SubItemMappings() {
        return (EReference) treeItemMappingContainerEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTreeItemMappingContainer_DropTools() {
        return (EReference) treeItemMappingContainerEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTreePopupMenu() {
        return treePopupMenuEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTreePopupMenu_MenuItemDescriptions() {
        return (EReference) treePopupMenuEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getTreeDragSource() {
        return treeDragSourceEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DescriptionFactory getDescriptionFactory() {
        return (DescriptionFactory) getEFactoryInstance();
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
        treeDescriptionEClass = createEClass(TREE_DESCRIPTION);
        createEAttribute(treeDescriptionEClass, TREE_DESCRIPTION__DOMAIN_CLASS);
        createEAttribute(treeDescriptionEClass, TREE_DESCRIPTION__PRECONDITION_EXPRESSION);
        createEReference(treeDescriptionEClass, TREE_DESCRIPTION__CREATE_TREE_ITEM);
        createEReference(treeDescriptionEClass, TREE_DESCRIPTION__OWNED_REPRESENTATION_CREATION_DESCRIPTIONS);
        createEReference(treeDescriptionEClass, TREE_DESCRIPTION__OWNED_REPRESENTATION_NAVIGATION_DESCRIPTIONS);

        treeItemMappingEClass = createEClass(TREE_ITEM_MAPPING);
        createEAttribute(treeItemMappingEClass, TREE_ITEM_MAPPING__DOMAIN_CLASS);
        createEAttribute(treeItemMappingEClass, TREE_ITEM_MAPPING__PRECONDITION_EXPRESSION);
        createEAttribute(treeItemMappingEClass, TREE_ITEM_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION);
        createEReference(treeItemMappingEClass, TREE_ITEM_MAPPING__REUSED_TREE_ITEM_MAPPINGS);
        createEReference(treeItemMappingEClass, TREE_ITEM_MAPPING__ALL_SUB_MAPPINGS);
        createEReference(treeItemMappingEClass, TREE_ITEM_MAPPING__SPECIALIZE);
        createEReference(treeItemMappingEClass, TREE_ITEM_MAPPING__DELETE);
        createEReference(treeItemMappingEClass, TREE_ITEM_MAPPING__CREATE);
        createEReference(treeItemMappingEClass, TREE_ITEM_MAPPING__DND_TOOLS);
        createEReference(treeItemMappingEClass, TREE_ITEM_MAPPING__POPUP_MENUS);

        treeItemStyleDescriptionEClass = createEClass(TREE_ITEM_STYLE_DESCRIPTION);
        createEReference(treeItemStyleDescriptionEClass, TREE_ITEM_STYLE_DESCRIPTION__BACKGROUND_COLOR);

        conditionalTreeItemStyleDescriptionEClass = createEClass(CONDITIONAL_TREE_ITEM_STYLE_DESCRIPTION);
        createEReference(conditionalTreeItemStyleDescriptionEClass, CONDITIONAL_TREE_ITEM_STYLE_DESCRIPTION__STYLE);

        treeItemToolEClass = createEClass(TREE_ITEM_TOOL);
        createEReference(treeItemToolEClass, TREE_ITEM_TOOL__FIRST_MODEL_OPERATION);
        createEReference(treeItemToolEClass, TREE_ITEM_TOOL__VARIABLES);

        treeItemDragToolEClass = createEClass(TREE_ITEM_DRAG_TOOL);
        createEReference(treeItemDragToolEClass, TREE_ITEM_DRAG_TOOL__OLD_CONTAINER);
        createEReference(treeItemDragToolEClass, TREE_ITEM_DRAG_TOOL__NEW_CONTAINER);
        createEReference(treeItemDragToolEClass, TREE_ITEM_DRAG_TOOL__ELEMENT);
        createEReference(treeItemDragToolEClass, TREE_ITEM_DRAG_TOOL__NEW_VIEW_CONTAINER);
        createEReference(treeItemDragToolEClass, TREE_ITEM_DRAG_TOOL__CONTAINERS);
        createEAttribute(treeItemDragToolEClass, TREE_ITEM_DRAG_TOOL__DRAG_SOURCE_TYPE);
        createEReference(treeItemDragToolEClass, TREE_ITEM_DRAG_TOOL__PRECEDING_SIBLINGS);

        treeItemContainerDropToolEClass = createEClass(TREE_ITEM_CONTAINER_DROP_TOOL);
        createEReference(treeItemContainerDropToolEClass, TREE_ITEM_CONTAINER_DROP_TOOL__OLD_CONTAINER);
        createEReference(treeItemContainerDropToolEClass, TREE_ITEM_CONTAINER_DROP_TOOL__NEW_CONTAINER);
        createEReference(treeItemContainerDropToolEClass, TREE_ITEM_CONTAINER_DROP_TOOL__ELEMENT);
        createEReference(treeItemContainerDropToolEClass, TREE_ITEM_CONTAINER_DROP_TOOL__NEW_VIEW_CONTAINER);
        createEReference(treeItemContainerDropToolEClass, TREE_ITEM_CONTAINER_DROP_TOOL__PRECEDING_SIBLINGS);
        createEAttribute(treeItemContainerDropToolEClass, TREE_ITEM_CONTAINER_DROP_TOOL__DRAG_SOURCE);

        treeItemCreationToolEClass = createEClass(TREE_ITEM_CREATION_TOOL);
        createEReference(treeItemCreationToolEClass, TREE_ITEM_CREATION_TOOL__MAPPING);

        treeItemEditionToolEClass = createEClass(TREE_ITEM_EDITION_TOOL);
        createEReference(treeItemEditionToolEClass, TREE_ITEM_EDITION_TOOL__MASK);
        createEReference(treeItemEditionToolEClass, TREE_ITEM_EDITION_TOOL__MAPPING);
        createEReference(treeItemEditionToolEClass, TREE_ITEM_EDITION_TOOL__ELEMENT);
        createEReference(treeItemEditionToolEClass, TREE_ITEM_EDITION_TOOL__ROOT);

        treeItemDeletionToolEClass = createEClass(TREE_ITEM_DELETION_TOOL);
        createEReference(treeItemDeletionToolEClass, TREE_ITEM_DELETION_TOOL__MAPPING);

        treeCreationDescriptionEClass = createEClass(TREE_CREATION_DESCRIPTION);
        createEReference(treeCreationDescriptionEClass, TREE_CREATION_DESCRIPTION__TREE_DESCRIPTION);

        treeNavigationDescriptionEClass = createEClass(TREE_NAVIGATION_DESCRIPTION);
        createEReference(treeNavigationDescriptionEClass, TREE_NAVIGATION_DESCRIPTION__TREE_DESCRIPTION);

        treeMappingEClass = createEClass(TREE_MAPPING);
        createEAttribute(treeMappingEClass, TREE_MAPPING__SEMANTIC_ELEMENTS);

        styleUpdaterEClass = createEClass(STYLE_UPDATER);
        createEReference(styleUpdaterEClass, STYLE_UPDATER__DEFAULT_STYLE);
        createEReference(styleUpdaterEClass, STYLE_UPDATER__CONDITIONAL_STYLES);

        treeVariableEClass = createEClass(TREE_VARIABLE);
        createEAttribute(treeVariableEClass, TREE_VARIABLE__DOCUMENTATION);

        treeItemUpdaterEClass = createEClass(TREE_ITEM_UPDATER);
        createEReference(treeItemUpdaterEClass, TREE_ITEM_UPDATER__DIRECT_EDIT);

        precedingSiblingsVariablesEClass = createEClass(PRECEDING_SIBLINGS_VARIABLES);

        treeItemMappingContainerEClass = createEClass(TREE_ITEM_MAPPING_CONTAINER);
        createEReference(treeItemMappingContainerEClass, TREE_ITEM_MAPPING_CONTAINER__SUB_ITEM_MAPPINGS);
        createEReference(treeItemMappingContainerEClass, TREE_ITEM_MAPPING_CONTAINER__DROP_TOOLS);

        treePopupMenuEClass = createEClass(TREE_POPUP_MENU);
        createEReference(treePopupMenuEClass, TREE_POPUP_MENU__MENU_ITEM_DESCRIPTIONS);

        // Create enums
        treeDragSourceEEnum = createEEnum(TREE_DRAG_SOURCE);
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
        org.eclipse.sirius.viewpoint.description.DescriptionPackage theDescriptionPackage_1 = (org.eclipse.sirius.viewpoint.description.DescriptionPackage) EPackage.Registry.INSTANCE
                .getEPackage(org.eclipse.sirius.viewpoint.description.DescriptionPackage.eNS_URI);
        ToolPackage theToolPackage = (ToolPackage) EPackage.Registry.INSTANCE.getEPackage(ToolPackage.eNS_URI);
        StylePackage theStylePackage = (StylePackage) EPackage.Registry.INSTANCE.getEPackage(StylePackage.eNS_URI);
        EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        treeDescriptionEClass.getESuperTypes().add(theDescriptionPackage_1.getRepresentationDescription());
        treeDescriptionEClass.getESuperTypes().add(this.getTreeItemMappingContainer());
        treeItemMappingEClass.getESuperTypes().add(this.getTreeMapping());
        treeItemMappingEClass.getESuperTypes().add(this.getStyleUpdater());
        treeItemMappingEClass.getESuperTypes().add(this.getTreeItemUpdater());
        treeItemMappingEClass.getESuperTypes().add(this.getTreeItemMappingContainer());
        treeItemStyleDescriptionEClass.getESuperTypes().add(theStylePackage.getStyleDescription());
        treeItemStyleDescriptionEClass.getESuperTypes().add(theStylePackage.getLabelStyleDescription());
        conditionalTreeItemStyleDescriptionEClass.getESuperTypes().add(theDescriptionPackage_1.getConditionalStyleDescription());
        treeItemToolEClass.getESuperTypes().add(theToolPackage.getAbstractToolDescription());
        treeItemDragToolEClass.getESuperTypes().add(theToolPackage.getMappingBasedToolDescription());
        treeItemDragToolEClass.getESuperTypes().add(this.getTreeItemTool());
        treeItemContainerDropToolEClass.getESuperTypes().add(theToolPackage.getMappingBasedToolDescription());
        treeItemContainerDropToolEClass.getESuperTypes().add(this.getTreeItemTool());
        treeItemCreationToolEClass.getESuperTypes().add(this.getTreeItemTool());
        treeItemCreationToolEClass.getESuperTypes().add(theToolPackage.getMappingBasedToolDescription());
        treeItemEditionToolEClass.getESuperTypes().add(this.getTreeItemTool());
        treeItemDeletionToolEClass.getESuperTypes().add(this.getTreeItemTool());
        treeCreationDescriptionEClass.getESuperTypes().add(theToolPackage.getRepresentationCreationDescription());
        treeNavigationDescriptionEClass.getESuperTypes().add(theToolPackage.getRepresentationNavigationDescription());
        treeMappingEClass.getESuperTypes().add(theDescriptionPackage_1.getRepresentationElementMapping());
        treeVariableEClass.getESuperTypes().add(theToolPackage.getAbstractVariable());
        treeVariableEClass.getESuperTypes().add(theToolPackage.getVariableContainer());
        precedingSiblingsVariablesEClass.getESuperTypes().add(this.getTreeVariable());
        treePopupMenuEClass.getESuperTypes().add(theToolPackage.getAbstractToolDescription());

        // Initialize classes and features; add operations and parameters
        initEClass(treeDescriptionEClass, TreeDescription.class, "TreeDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTreeDescription_DomainClass(), theDescriptionPackage_1.getTypeName(), "domainClass", null, 1, 1, TreeDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTreeDescription_PreconditionExpression(), theDescriptionPackage_1.getInterpretedExpression(), "preconditionExpression", null, 0, 1, TreeDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTreeDescription_CreateTreeItem(), this.getTreeItemCreationTool(), null, "createTreeItem", null, 0, -1, TreeDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTreeDescription_OwnedRepresentationCreationDescriptions(), theToolPackage.getRepresentationCreationDescription(), null, "ownedRepresentationCreationDescriptions", null, 0,
                -1, TreeDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getTreeDescription_OwnedRepresentationCreationDescriptions().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(getTreeDescription_OwnedRepresentationNavigationDescriptions(), theToolPackage.getRepresentationNavigationDescription(), null, "ownedRepresentationNavigationDescriptions",
                null, 0, -1, TreeDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getTreeDescription_OwnedRepresentationNavigationDescriptions().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());

        initEClass(treeItemMappingEClass, TreeItemMapping.class, "TreeItemMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTreeItemMapping_DomainClass(), theDescriptionPackage_1.getTypeName(), "domainClass", null, 1, 1, TreeItemMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTreeItemMapping_PreconditionExpression(), theDescriptionPackage_1.getInterpretedExpression(), "preconditionExpression", null, 0, 1, TreeItemMapping.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTreeItemMapping_SemanticCandidatesExpression(), theDescriptionPackage_1.getInterpretedExpression(), "semanticCandidatesExpression", null, 0, 1, TreeItemMapping.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTreeItemMapping_ReusedTreeItemMappings(), this.getTreeItemMapping(), null, "reusedTreeItemMappings", null, 0, -1, TreeItemMapping.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTreeItemMapping_AllSubMappings(), this.getTreeItemMapping(), null, "allSubMappings", null, 0, -1, TreeItemMapping.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getTreeItemMapping_Specialize(), this.getTreeItemMapping(), null, "specialize", null, 0, 1, TreeItemMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTreeItemMapping_Delete(), this.getTreeItemDeletionTool(), this.getTreeItemDeletionTool_Mapping(), "delete", null, 0, 1, TreeItemMapping.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTreeItemMapping_Create(), this.getTreeItemCreationTool(), null, "create", null, 0, -1, TreeItemMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTreeItemMapping_DndTools(), this.getTreeItemDragTool(), null, "dndTools", null, 0, -1, TreeItemMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTreeItemMapping_PopupMenus(), this.getTreePopupMenu(), null, "popupMenus", null, 0, -1, TreeItemMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(treeItemStyleDescriptionEClass, TreeItemStyleDescription.class, "TreeItemStyleDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTreeItemStyleDescription_BackgroundColor(), theDescriptionPackage_1.getColorDescription(), null, "backgroundColor", null, 1, 1, TreeItemStyleDescription.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(conditionalTreeItemStyleDescriptionEClass, ConditionalTreeItemStyleDescription.class, "ConditionalTreeItemStyleDescription", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getConditionalTreeItemStyleDescription_Style(), this.getTreeItemStyleDescription(), null, "style", null, 0, 1, ConditionalTreeItemStyleDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(treeItemToolEClass, TreeItemTool.class, "TreeItemTool", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTreeItemTool_FirstModelOperation(), theToolPackage.getModelOperation(), null, "firstModelOperation", null, 0, 1, TreeItemTool.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTreeItemTool_Variables(), this.getTreeVariable(), null, "variables", null, 0, -1, TreeItemTool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(treeItemDragToolEClass, TreeItemDragTool.class, "TreeItemDragTool", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTreeItemDragTool_OldContainer(), theToolPackage.getDropContainerVariable(), null, "oldContainer", null, 1, 1, TreeItemDragTool.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTreeItemDragTool_NewContainer(), theToolPackage.getDropContainerVariable(), null, "newContainer", null, 1, 1, TreeItemDragTool.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTreeItemDragTool_Element(), theToolPackage.getElementDropVariable(), null, "element", null, 1, 1, TreeItemDragTool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTreeItemDragTool_NewViewContainer(), theToolPackage.getContainerViewVariable(), null, "newViewContainer", null, 1, 1, TreeItemDragTool.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTreeItemDragTool_Containers(), this.getTreeItemMappingContainer(), null, "containers", null, 1, -1, TreeItemDragTool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTreeItemDragTool_DragSourceType(), this.getTreeDragSource(), "dragSourceType", null, 1, 1, TreeItemDragTool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTreeItemDragTool_PrecedingSiblings(), this.getPrecedingSiblingsVariables(), null, "precedingSiblings", null, 1, 1, TreeItemDragTool.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        addEOperation(treeItemDragToolEClass, this.getTreeItemMapping(), "getBestTreeItemMapping", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(treeItemContainerDropToolEClass, TreeItemContainerDropTool.class, "TreeItemContainerDropTool", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTreeItemContainerDropTool_OldContainer(), theToolPackage.getDropContainerVariable(), null, "oldContainer", null, 1, 1, TreeItemContainerDropTool.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTreeItemContainerDropTool_NewContainer(), theToolPackage.getDropContainerVariable(), null, "newContainer", null, 1, 1, TreeItemContainerDropTool.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTreeItemContainerDropTool_Element(), theToolPackage.getElementDropVariable(), null, "element", null, 1, 1, TreeItemContainerDropTool.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTreeItemContainerDropTool_NewViewContainer(), theToolPackage.getContainerViewVariable(), null, "newViewContainer", null, 1, 1, TreeItemContainerDropTool.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTreeItemContainerDropTool_PrecedingSiblings(), this.getPrecedingSiblingsVariables(), null, "precedingSiblings", null, 1, 1, TreeItemContainerDropTool.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTreeItemContainerDropTool_DragSource(), this.getTreeDragSource(), "dragSource", null, 1, 1, TreeItemContainerDropTool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(treeItemCreationToolEClass, TreeItemCreationTool.class, "TreeItemCreationTool", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTreeItemCreationTool_Mapping(), this.getTreeItemMapping(), null, "mapping", null, 0, -1, TreeItemCreationTool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(treeItemEditionToolEClass, TreeItemEditionTool.class, "TreeItemEditionTool", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTreeItemEditionTool_Mask(), theToolPackage.getEditMaskVariables(), null, "mask", null, 1, 1, TreeItemEditionTool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTreeItemEditionTool_Mapping(), this.getTreeItemMapping(), null, "mapping", null, 0, -1, TreeItemEditionTool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTreeItemEditionTool_Element(), theToolPackage.getElementDropVariable(), null, "element", null, 1, 1, TreeItemEditionTool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTreeItemEditionTool_Root(), theToolPackage.getElementDropVariable(), null, "root", null, 1, 1, TreeItemEditionTool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(treeItemDeletionToolEClass, TreeItemDeletionTool.class, "TreeItemDeletionTool", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTreeItemDeletionTool_Mapping(), this.getTreeItemMapping(), this.getTreeItemMapping_Delete(), "mapping", null, 1, 1, TreeItemDeletionTool.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(treeCreationDescriptionEClass, TreeCreationDescription.class, "TreeCreationDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTreeCreationDescription_TreeDescription(), this.getTreeDescription(), null, "treeDescription", null, 1, 1, TreeCreationDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(treeNavigationDescriptionEClass, TreeNavigationDescription.class, "TreeNavigationDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTreeNavigationDescription_TreeDescription(), this.getTreeDescription(), null, "treeDescription", null, 1, 1, TreeNavigationDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(treeMappingEClass, TreeMapping.class, "TreeMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTreeMapping_SemanticElements(), theDescriptionPackage_1.getInterpretedExpression(), "semanticElements", null, 0, 1, TreeMapping.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(styleUpdaterEClass, StyleUpdater.class, "StyleUpdater", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getStyleUpdater_DefaultStyle(), this.getTreeItemStyleDescription(), null, "defaultStyle", null, 1, 1, StyleUpdater.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getStyleUpdater_ConditionalStyles(), this.getConditionalTreeItemStyleDescription(), null, "conditionalStyles", null, 0, -1, StyleUpdater.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(treeVariableEClass, TreeVariable.class, "TreeVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTreeVariable_Documentation(), theEcorePackage.getEString(), "documentation", null, 0, 1, TreeVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(treeItemUpdaterEClass, TreeItemUpdater.class, "TreeItemUpdater", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTreeItemUpdater_DirectEdit(), this.getTreeItemEditionTool(), null, "directEdit", null, 0, 1, TreeItemUpdater.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        addEOperation(treeItemUpdaterEClass, theDescriptionPackage_1.getInterpretedExpression(), "getLabelComputationExpression", 1, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(treeItemUpdaterEClass, this.getTreeItemCreationTool(), "getCreateTreeItem", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(precedingSiblingsVariablesEClass, PrecedingSiblingsVariables.class, "PrecedingSiblingsVariables", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(treeItemMappingContainerEClass, TreeItemMappingContainer.class, "TreeItemMappingContainer", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTreeItemMappingContainer_SubItemMappings(), this.getTreeItemMapping(), null, "subItemMappings", null, 0, -1, TreeItemMappingContainer.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        getTreeItemMappingContainer_SubItemMappings().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(getTreeItemMappingContainer_DropTools(), this.getTreeItemContainerDropTool(), null, "dropTools", null, 0, -1, TreeItemMappingContainer.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(treePopupMenuEClass, TreePopupMenu.class, "TreePopupMenu", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTreePopupMenu_MenuItemDescriptions(), theToolPackage.getMenuItemOrRef(), null, "menuItemDescriptions", null, 0, -1, TreePopupMenu.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(treeDragSourceEEnum, TreeDragSource.class, "TreeDragSource");
        addEEnumLiteral(treeDragSourceEEnum, TreeDragSource.TREE);
        addEEnumLiteral(treeDragSourceEEnum, TreeDragSource.PROJECT_EXPLORER);
        addEEnumLiteral(treeDragSourceEEnum, TreeDragSource.BOTH);

        // Create annotations
        // http://www.eclipse.org/sirius/interpreted/expression/returnType
        createReturnTypeAnnotations();
        // http://www.eclipse.org/sirius/interpreted/expression/variables
        createVariablesAnnotations();
    }

    /**
     * Initializes the annotations for
     * <b>http://www.eclipse.org/sirius/interpreted/expression/returnType</b>.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void createReturnTypeAnnotations() {
        String source = "http://www.eclipse.org/sirius/interpreted/expression/returnType";
        addAnnotation(getTreeDescription_PreconditionExpression(), source, new String[] { "returnType", "boolean" });
        addAnnotation(getTreeItemMapping_PreconditionExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." });
        addAnnotation(getTreeItemMapping_SemanticCandidatesExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." });
        addAnnotation(getTreeMapping_SemanticElements(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." });
    }

    /**
     * Initializes the annotations for
     * <b>http://www.eclipse.org/sirius/interpreted/expression/variables</b>.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void createVariablesAnnotations() {
        String source = "http://www.eclipse.org/sirius/interpreted/expression/variables";
        addAnnotation(getTreeDescription_PreconditionExpression(), source, new String[] {});
        addAnnotation(getTreeItemMapping_PreconditionExpression(), source, new String[] { "view", "tree.DTreeElement | current DTreeElement.", "containerView",
                "ecore.EObject | container of the current DTreeElement (variable is available if container is not null).", "container",
                "ecore.EObject | semantic target of $containerView (if it is a DSemanticDecorator)." });
        addAnnotation(getTreeItemMapping_SemanticCandidatesExpression(), source, new String[] { "view", "tree.DTreeElement | current DTreeElement.", "containerView",
                "ecore.EObject | container of the current DTreeElement (variable is available if container is not null).", "container",
                "ecore.EObject | semantic target of $containerView (if it is a DSemanticDecorator)." });
        addAnnotation(getTreeMapping_SemanticElements(), source, new String[] { "view", "tree.DTreeElement | current DTreeElement.", "containerView",
                "ecore.EObject | container of the current DTreeElement (variable is available if container is not null).", "container",
                "ecore.EObject | semantic target of $containerView (if it is a DSemanticDecorator)." });
    }

} // DescriptionPackageImpl
