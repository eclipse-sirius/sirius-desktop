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
        super(DescriptionPackage.eNS_URI, DescriptionFactory.eINSTANCE);
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
        if (DescriptionPackageImpl.isInited) {
            return (DescriptionPackage) EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI);
        }

        // Obtain or create and register package
        DescriptionPackageImpl theDescriptionPackage = (DescriptionPackageImpl) (EPackage.Registry.INSTANCE.get(DescriptionPackage.eNS_URI) instanceof DescriptionPackageImpl ? EPackage.Registry.INSTANCE
                .get(DescriptionPackage.eNS_URI) : new DescriptionPackageImpl());

        DescriptionPackageImpl.isInited = true;

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
    @Override
    public EClass getTreeDescription() {
        return treeDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTreeDescription_DomainClass() {
        return (EAttribute) treeDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTreeDescription_PreconditionExpression() {
        return (EAttribute) treeDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTreeDescription_CreateTreeItem() {
        return (EReference) treeDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTreeDescription_OwnedRepresentationCreationDescriptions() {
        return (EReference) treeDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTreeDescription_OwnedRepresentationNavigationDescriptions() {
        return (EReference) treeDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTreeItemMapping() {
        return treeItemMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTreeItemMapping_DomainClass() {
        return (EAttribute) treeItemMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTreeItemMapping_PreconditionExpression() {
        return (EAttribute) treeItemMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTreeItemMapping_SemanticCandidatesExpression() {
        return (EAttribute) treeItemMappingEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTreeItemMapping_ReusedTreeItemMappings() {
        return (EReference) treeItemMappingEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTreeItemMapping_AllSubMappings() {
        return (EReference) treeItemMappingEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTreeItemMapping_Specialize() {
        return (EReference) treeItemMappingEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTreeItemMapping_Delete() {
        return (EReference) treeItemMappingEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTreeItemMapping_Create() {
        return (EReference) treeItemMappingEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTreeItemMapping_DndTools() {
        return (EReference) treeItemMappingEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTreeItemMapping_PopupMenus() {
        return (EReference) treeItemMappingEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTreeItemStyleDescription() {
        return treeItemStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTreeItemStyleDescription_BackgroundColor() {
        return (EReference) treeItemStyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getConditionalTreeItemStyleDescription() {
        return conditionalTreeItemStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getConditionalTreeItemStyleDescription_Style() {
        return (EReference) conditionalTreeItemStyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTreeItemTool() {
        return treeItemToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTreeItemTool_FirstModelOperation() {
        return (EReference) treeItemToolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTreeItemTool_Variables() {
        return (EReference) treeItemToolEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTreeItemDragTool() {
        return treeItemDragToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTreeItemDragTool_OldContainer() {
        return (EReference) treeItemDragToolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTreeItemDragTool_NewContainer() {
        return (EReference) treeItemDragToolEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTreeItemDragTool_Element() {
        return (EReference) treeItemDragToolEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTreeItemDragTool_NewViewContainer() {
        return (EReference) treeItemDragToolEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTreeItemDragTool_Containers() {
        return (EReference) treeItemDragToolEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTreeItemDragTool_DragSourceType() {
        return (EAttribute) treeItemDragToolEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTreeItemDragTool_PrecedingSiblings() {
        return (EReference) treeItemDragToolEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTreeItemContainerDropTool() {
        return treeItemContainerDropToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTreeItemContainerDropTool_OldContainer() {
        return (EReference) treeItemContainerDropToolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTreeItemContainerDropTool_NewContainer() {
        return (EReference) treeItemContainerDropToolEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTreeItemContainerDropTool_Element() {
        return (EReference) treeItemContainerDropToolEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTreeItemContainerDropTool_NewViewContainer() {
        return (EReference) treeItemContainerDropToolEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTreeItemContainerDropTool_PrecedingSiblings() {
        return (EReference) treeItemContainerDropToolEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTreeItemContainerDropTool_DragSource() {
        return (EAttribute) treeItemContainerDropToolEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTreeItemCreationTool() {
        return treeItemCreationToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTreeItemCreationTool_Mapping() {
        return (EReference) treeItemCreationToolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTreeItemEditionTool() {
        return treeItemEditionToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTreeItemEditionTool_Mask() {
        return (EReference) treeItemEditionToolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTreeItemEditionTool_Mapping() {
        return (EReference) treeItemEditionToolEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTreeItemEditionTool_Element() {
        return (EReference) treeItemEditionToolEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTreeItemEditionTool_Root() {
        return (EReference) treeItemEditionToolEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTreeItemDeletionTool() {
        return treeItemDeletionToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTreeItemDeletionTool_Mapping() {
        return (EReference) treeItemDeletionToolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTreeCreationDescription() {
        return treeCreationDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTreeCreationDescription_TreeDescription() {
        return (EReference) treeCreationDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTreeNavigationDescription() {
        return treeNavigationDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTreeNavigationDescription_TreeDescription() {
        return (EReference) treeNavigationDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTreeMapping() {
        return treeMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTreeMapping_SemanticElements() {
        return (EAttribute) treeMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getStyleUpdater() {
        return styleUpdaterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getStyleUpdater_DefaultStyle() {
        return (EReference) styleUpdaterEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getStyleUpdater_ConditionalStyles() {
        return (EReference) styleUpdaterEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTreeVariable() {
        return treeVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTreeVariable_Documentation() {
        return (EAttribute) treeVariableEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTreeItemUpdater() {
        return treeItemUpdaterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTreeItemUpdater_DirectEdit() {
        return (EReference) treeItemUpdaterEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getPrecedingSiblingsVariables() {
        return precedingSiblingsVariablesEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTreeItemMappingContainer() {
        return treeItemMappingContainerEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTreeItemMappingContainer_SubItemMappings() {
        return (EReference) treeItemMappingContainerEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTreeItemMappingContainer_DropTools() {
        return (EReference) treeItemMappingContainerEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTreePopupMenu() {
        return treePopupMenuEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTreePopupMenu_MenuItemDescriptions() {
        return (EReference) treePopupMenuEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EEnum getTreeDragSource() {
        return treeDragSourceEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
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
        if (isCreated) {
            return;
        }
        isCreated = true;

        // Create classes and their features
        treeDescriptionEClass = createEClass(DescriptionPackage.TREE_DESCRIPTION);
        createEAttribute(treeDescriptionEClass, DescriptionPackage.TREE_DESCRIPTION__DOMAIN_CLASS);
        createEAttribute(treeDescriptionEClass, DescriptionPackage.TREE_DESCRIPTION__PRECONDITION_EXPRESSION);
        createEReference(treeDescriptionEClass, DescriptionPackage.TREE_DESCRIPTION__CREATE_TREE_ITEM);
        createEReference(treeDescriptionEClass, DescriptionPackage.TREE_DESCRIPTION__OWNED_REPRESENTATION_CREATION_DESCRIPTIONS);
        createEReference(treeDescriptionEClass, DescriptionPackage.TREE_DESCRIPTION__OWNED_REPRESENTATION_NAVIGATION_DESCRIPTIONS);

        treeItemMappingEClass = createEClass(DescriptionPackage.TREE_ITEM_MAPPING);
        createEAttribute(treeItemMappingEClass, DescriptionPackage.TREE_ITEM_MAPPING__DOMAIN_CLASS);
        createEAttribute(treeItemMappingEClass, DescriptionPackage.TREE_ITEM_MAPPING__PRECONDITION_EXPRESSION);
        createEAttribute(treeItemMappingEClass, DescriptionPackage.TREE_ITEM_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION);
        createEReference(treeItemMappingEClass, DescriptionPackage.TREE_ITEM_MAPPING__REUSED_TREE_ITEM_MAPPINGS);
        createEReference(treeItemMappingEClass, DescriptionPackage.TREE_ITEM_MAPPING__ALL_SUB_MAPPINGS);
        createEReference(treeItemMappingEClass, DescriptionPackage.TREE_ITEM_MAPPING__SPECIALIZE);
        createEReference(treeItemMappingEClass, DescriptionPackage.TREE_ITEM_MAPPING__DELETE);
        createEReference(treeItemMappingEClass, DescriptionPackage.TREE_ITEM_MAPPING__CREATE);
        createEReference(treeItemMappingEClass, DescriptionPackage.TREE_ITEM_MAPPING__DND_TOOLS);
        createEReference(treeItemMappingEClass, DescriptionPackage.TREE_ITEM_MAPPING__POPUP_MENUS);

        treeItemStyleDescriptionEClass = createEClass(DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION);
        createEReference(treeItemStyleDescriptionEClass, DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__BACKGROUND_COLOR);

        conditionalTreeItemStyleDescriptionEClass = createEClass(DescriptionPackage.CONDITIONAL_TREE_ITEM_STYLE_DESCRIPTION);
        createEReference(conditionalTreeItemStyleDescriptionEClass, DescriptionPackage.CONDITIONAL_TREE_ITEM_STYLE_DESCRIPTION__STYLE);

        treeItemToolEClass = createEClass(DescriptionPackage.TREE_ITEM_TOOL);
        createEReference(treeItemToolEClass, DescriptionPackage.TREE_ITEM_TOOL__FIRST_MODEL_OPERATION);
        createEReference(treeItemToolEClass, DescriptionPackage.TREE_ITEM_TOOL__VARIABLES);

        treeItemDragToolEClass = createEClass(DescriptionPackage.TREE_ITEM_DRAG_TOOL);
        createEReference(treeItemDragToolEClass, DescriptionPackage.TREE_ITEM_DRAG_TOOL__OLD_CONTAINER);
        createEReference(treeItemDragToolEClass, DescriptionPackage.TREE_ITEM_DRAG_TOOL__NEW_CONTAINER);
        createEReference(treeItemDragToolEClass, DescriptionPackage.TREE_ITEM_DRAG_TOOL__ELEMENT);
        createEReference(treeItemDragToolEClass, DescriptionPackage.TREE_ITEM_DRAG_TOOL__NEW_VIEW_CONTAINER);
        createEReference(treeItemDragToolEClass, DescriptionPackage.TREE_ITEM_DRAG_TOOL__CONTAINERS);
        createEAttribute(treeItemDragToolEClass, DescriptionPackage.TREE_ITEM_DRAG_TOOL__DRAG_SOURCE_TYPE);
        createEReference(treeItemDragToolEClass, DescriptionPackage.TREE_ITEM_DRAG_TOOL__PRECEDING_SIBLINGS);

        treeItemContainerDropToolEClass = createEClass(DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL);
        createEReference(treeItemContainerDropToolEClass, DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__OLD_CONTAINER);
        createEReference(treeItemContainerDropToolEClass, DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__NEW_CONTAINER);
        createEReference(treeItemContainerDropToolEClass, DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__ELEMENT);
        createEReference(treeItemContainerDropToolEClass, DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__NEW_VIEW_CONTAINER);
        createEReference(treeItemContainerDropToolEClass, DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__PRECEDING_SIBLINGS);
        createEAttribute(treeItemContainerDropToolEClass, DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__DRAG_SOURCE);

        treeItemCreationToolEClass = createEClass(DescriptionPackage.TREE_ITEM_CREATION_TOOL);
        createEReference(treeItemCreationToolEClass, DescriptionPackage.TREE_ITEM_CREATION_TOOL__MAPPING);

        treeItemEditionToolEClass = createEClass(DescriptionPackage.TREE_ITEM_EDITION_TOOL);
        createEReference(treeItemEditionToolEClass, DescriptionPackage.TREE_ITEM_EDITION_TOOL__MASK);
        createEReference(treeItemEditionToolEClass, DescriptionPackage.TREE_ITEM_EDITION_TOOL__MAPPING);
        createEReference(treeItemEditionToolEClass, DescriptionPackage.TREE_ITEM_EDITION_TOOL__ELEMENT);
        createEReference(treeItemEditionToolEClass, DescriptionPackage.TREE_ITEM_EDITION_TOOL__ROOT);

        treeItemDeletionToolEClass = createEClass(DescriptionPackage.TREE_ITEM_DELETION_TOOL);
        createEReference(treeItemDeletionToolEClass, DescriptionPackage.TREE_ITEM_DELETION_TOOL__MAPPING);

        treeCreationDescriptionEClass = createEClass(DescriptionPackage.TREE_CREATION_DESCRIPTION);
        createEReference(treeCreationDescriptionEClass, DescriptionPackage.TREE_CREATION_DESCRIPTION__TREE_DESCRIPTION);

        treeNavigationDescriptionEClass = createEClass(DescriptionPackage.TREE_NAVIGATION_DESCRIPTION);
        createEReference(treeNavigationDescriptionEClass, DescriptionPackage.TREE_NAVIGATION_DESCRIPTION__TREE_DESCRIPTION);

        treeMappingEClass = createEClass(DescriptionPackage.TREE_MAPPING);
        createEAttribute(treeMappingEClass, DescriptionPackage.TREE_MAPPING__SEMANTIC_ELEMENTS);

        styleUpdaterEClass = createEClass(DescriptionPackage.STYLE_UPDATER);
        createEReference(styleUpdaterEClass, DescriptionPackage.STYLE_UPDATER__DEFAULT_STYLE);
        createEReference(styleUpdaterEClass, DescriptionPackage.STYLE_UPDATER__CONDITIONAL_STYLES);

        treeVariableEClass = createEClass(DescriptionPackage.TREE_VARIABLE);
        createEAttribute(treeVariableEClass, DescriptionPackage.TREE_VARIABLE__DOCUMENTATION);

        treeItemUpdaterEClass = createEClass(DescriptionPackage.TREE_ITEM_UPDATER);
        createEReference(treeItemUpdaterEClass, DescriptionPackage.TREE_ITEM_UPDATER__DIRECT_EDIT);

        precedingSiblingsVariablesEClass = createEClass(DescriptionPackage.PRECEDING_SIBLINGS_VARIABLES);

        treeItemMappingContainerEClass = createEClass(DescriptionPackage.TREE_ITEM_MAPPING_CONTAINER);
        createEReference(treeItemMappingContainerEClass, DescriptionPackage.TREE_ITEM_MAPPING_CONTAINER__SUB_ITEM_MAPPINGS);
        createEReference(treeItemMappingContainerEClass, DescriptionPackage.TREE_ITEM_MAPPING_CONTAINER__DROP_TOOLS);

        treePopupMenuEClass = createEClass(DescriptionPackage.TREE_POPUP_MENU);
        createEReference(treePopupMenuEClass, DescriptionPackage.TREE_POPUP_MENU__MENU_ITEM_DESCRIPTIONS);

        // Create enums
        treeDragSourceEEnum = createEEnum(DescriptionPackage.TREE_DRAG_SOURCE);
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
        if (isInitialized) {
            return;
        }
        isInitialized = true;

        // Initialize package
        setName(DescriptionPackage.eNAME);
        setNsPrefix(DescriptionPackage.eNS_PREFIX);
        setNsURI(DescriptionPackage.eNS_URI);

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
        initEClass(treeDescriptionEClass, TreeDescription.class, "TreeDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getTreeDescription_DomainClass(),
                theDescriptionPackage_1.getTypeName(),
                "domainClass", null, 1, 1, TreeDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getTreeDescription_PreconditionExpression(),
                theDescriptionPackage_1.getInterpretedExpression(),
                "preconditionExpression", null, 0, 1, TreeDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTreeDescription_CreateTreeItem(),
                this.getTreeItemCreationTool(),
                null,
                "createTreeItem", null, 0, -1, TreeDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTreeDescription_OwnedRepresentationCreationDescriptions(),
                theToolPackage.getRepresentationCreationDescription(),
                null,
                "ownedRepresentationCreationDescriptions", null, 0, -1, TreeDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        getTreeDescription_OwnedRepresentationCreationDescriptions().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(
                getTreeDescription_OwnedRepresentationNavigationDescriptions(),
                theToolPackage.getRepresentationNavigationDescription(),
                null,
                "ownedRepresentationNavigationDescriptions", null, 0, -1, TreeDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        getTreeDescription_OwnedRepresentationNavigationDescriptions().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());

        initEClass(treeItemMappingEClass, TreeItemMapping.class, "TreeItemMapping", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getTreeItemMapping_DomainClass(),
                theDescriptionPackage_1.getTypeName(),
                "domainClass", null, 1, 1, TreeItemMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getTreeItemMapping_PreconditionExpression(),
                theDescriptionPackage_1.getInterpretedExpression(),
                "preconditionExpression", null, 0, 1, TreeItemMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getTreeItemMapping_SemanticCandidatesExpression(),
                theDescriptionPackage_1.getInterpretedExpression(),
                "semanticCandidatesExpression", null, 0, 1, TreeItemMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTreeItemMapping_ReusedTreeItemMappings(),
                this.getTreeItemMapping(),
                null,
                "reusedTreeItemMappings", null, 0, -1, TreeItemMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTreeItemMapping_AllSubMappings(),
                this.getTreeItemMapping(),
                null,
                "allSubMappings", null, 0, -1, TreeItemMapping.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, !EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTreeItemMapping_Specialize(),
                this.getTreeItemMapping(),
                null,
                "specialize", null, 0, 1, TreeItemMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTreeItemMapping_Delete(),
                this.getTreeItemDeletionTool(),
                this.getTreeItemDeletionTool_Mapping(),
                "delete", null, 0, 1, TreeItemMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTreeItemMapping_Create(),
                this.getTreeItemCreationTool(),
                null,
                "create", null, 0, -1, TreeItemMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTreeItemMapping_DndTools(),
                this.getTreeItemDragTool(),
                null,
                "dndTools", null, 0, -1, TreeItemMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTreeItemMapping_PopupMenus(),
                this.getTreePopupMenu(),
                null,
                "popupMenus", null, 0, -1, TreeItemMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(treeItemStyleDescriptionEClass, TreeItemStyleDescription.class,
                "TreeItemStyleDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getTreeItemStyleDescription_BackgroundColor(),
                theDescriptionPackage_1.getColorDescription(),
                null,
                "backgroundColor", null, 1, 1, TreeItemStyleDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(conditionalTreeItemStyleDescriptionEClass, ConditionalTreeItemStyleDescription.class,
                "ConditionalTreeItemStyleDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getConditionalTreeItemStyleDescription_Style(),
                this.getTreeItemStyleDescription(),
                null,
                "style", null, 0, 1, ConditionalTreeItemStyleDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(treeItemToolEClass, TreeItemTool.class, "TreeItemTool", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getTreeItemTool_FirstModelOperation(),
                theToolPackage.getModelOperation(),
                null,
                "firstModelOperation", null, 0, 1, TreeItemTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTreeItemTool_Variables(),
                this.getTreeVariable(),
                null,
                "variables", null, 0, -1, TreeItemTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(treeItemDragToolEClass, TreeItemDragTool.class, "TreeItemDragTool", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getTreeItemDragTool_OldContainer(),
                theToolPackage.getDropContainerVariable(),
                null,
                "oldContainer", null, 1, 1, TreeItemDragTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTreeItemDragTool_NewContainer(),
                theToolPackage.getDropContainerVariable(),
                null,
                "newContainer", null, 1, 1, TreeItemDragTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTreeItemDragTool_Element(),
                theToolPackage.getElementDropVariable(),
                null,
                "element", null, 1, 1, TreeItemDragTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTreeItemDragTool_NewViewContainer(),
                theToolPackage.getContainerViewVariable(),
                null,
                "newViewContainer", null, 1, 1, TreeItemDragTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTreeItemDragTool_Containers(),
                this.getTreeItemMappingContainer(),
                null,
                "containers", null, 1, -1, TreeItemDragTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getTreeItemDragTool_DragSourceType(),
                this.getTreeDragSource(),
                "dragSourceType", null, 1, 1, TreeItemDragTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTreeItemDragTool_PrecedingSiblings(),
                this.getPrecedingSiblingsVariables(),
                null,
                "precedingSiblings", null, 1, 1, TreeItemDragTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        addEOperation(treeItemDragToolEClass, this.getTreeItemMapping(), "getBestTreeItemMapping", 0, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(treeItemContainerDropToolEClass, TreeItemContainerDropTool.class,
                "TreeItemContainerDropTool", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getTreeItemContainerDropTool_OldContainer(),
                theToolPackage.getDropContainerVariable(),
                null,
                "oldContainer", null, 1, 1, TreeItemContainerDropTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTreeItemContainerDropTool_NewContainer(),
                theToolPackage.getDropContainerVariable(),
                null,
                "newContainer", null, 1, 1, TreeItemContainerDropTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTreeItemContainerDropTool_Element(),
                theToolPackage.getElementDropVariable(),
                null,
                "element", null, 1, 1, TreeItemContainerDropTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTreeItemContainerDropTool_NewViewContainer(),
                theToolPackage.getContainerViewVariable(),
                null,
                "newViewContainer", null, 1, 1, TreeItemContainerDropTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTreeItemContainerDropTool_PrecedingSiblings(),
                this.getPrecedingSiblingsVariables(),
                null,
                "precedingSiblings", null, 1, 1, TreeItemContainerDropTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getTreeItemContainerDropTool_DragSource(),
                this.getTreeDragSource(),
                "dragSource", null, 1, 1, TreeItemContainerDropTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(treeItemCreationToolEClass, TreeItemCreationTool.class, "TreeItemCreationTool", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getTreeItemCreationTool_Mapping(),
                this.getTreeItemMapping(),
                null,
                "mapping", null, 0, -1, TreeItemCreationTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(treeItemEditionToolEClass, TreeItemEditionTool.class, "TreeItemEditionTool", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getTreeItemEditionTool_Mask(),
                theToolPackage.getEditMaskVariables(),
                null,
                "mask", null, 1, 1, TreeItemEditionTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTreeItemEditionTool_Mapping(),
                this.getTreeItemMapping(),
                null,
                "mapping", null, 0, -1, TreeItemEditionTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTreeItemEditionTool_Element(),
                theToolPackage.getElementDropVariable(),
                null,
                "element", null, 1, 1, TreeItemEditionTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getTreeItemEditionTool_Root(),
                theToolPackage.getElementDropVariable(),
                null,
                "root", null, 1, 1, TreeItemEditionTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(treeItemDeletionToolEClass, TreeItemDeletionTool.class, "TreeItemDeletionTool", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getTreeItemDeletionTool_Mapping(),
                this.getTreeItemMapping(),
                this.getTreeItemMapping_Delete(),
                "mapping", null, 1, 1, TreeItemDeletionTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(treeCreationDescriptionEClass, TreeCreationDescription.class,
                "TreeCreationDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getTreeCreationDescription_TreeDescription(),
                this.getTreeDescription(),
                null,
                "treeDescription", null, 1, 1, TreeCreationDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(treeNavigationDescriptionEClass, TreeNavigationDescription.class,
                "TreeNavigationDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getTreeNavigationDescription_TreeDescription(),
                this.getTreeDescription(),
                null,
                "treeDescription", null, 1, 1, TreeNavigationDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(treeMappingEClass, TreeMapping.class, "TreeMapping", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getTreeMapping_SemanticElements(),
                theDescriptionPackage_1.getInterpretedExpression(),
                "semanticElements", null, 0, 1, TreeMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(styleUpdaterEClass, StyleUpdater.class, "StyleUpdater", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getStyleUpdater_DefaultStyle(),
                this.getTreeItemStyleDescription(),
                null,
                "defaultStyle", null, 1, 1, StyleUpdater.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getStyleUpdater_ConditionalStyles(),
                this.getConditionalTreeItemStyleDescription(),
                null,
                "conditionalStyles", null, 0, -1, StyleUpdater.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(treeVariableEClass, TreeVariable.class, "TreeVariable", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getTreeVariable_Documentation(),
                theEcorePackage.getEString(),
                "documentation", null, 0, 1, TreeVariable.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(treeItemUpdaterEClass, TreeItemUpdater.class, "TreeItemUpdater", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getTreeItemUpdater_DirectEdit(),
                this.getTreeItemEditionTool(),
                null,
                "directEdit", null, 0, 1, TreeItemUpdater.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        addEOperation(treeItemUpdaterEClass, theDescriptionPackage_1.getInterpretedExpression(), "getLabelComputationExpression", 1, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        addEOperation(treeItemUpdaterEClass, this.getTreeItemCreationTool(), "getCreateTreeItem", 0, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(precedingSiblingsVariablesEClass, PrecedingSiblingsVariables.class,
                "PrecedingSiblingsVariables", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(treeItemMappingContainerEClass, TreeItemMappingContainer.class,
                "TreeItemMappingContainer", EPackageImpl.IS_ABSTRACT, EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getTreeItemMappingContainer_SubItemMappings(),
                this.getTreeItemMapping(),
                null,
                "subItemMappings", null, 0, -1, TreeItemMappingContainer.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        getTreeItemMappingContainer_SubItemMappings().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(
                getTreeItemMappingContainer_DropTools(),
                this.getTreeItemContainerDropTool(),
                null,
                "dropTools", null, 0, -1, TreeItemMappingContainer.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(treePopupMenuEClass, TreePopupMenu.class, "TreePopupMenu", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getTreePopupMenu_MenuItemDescriptions(),
                theToolPackage.getMenuItemOrRef(),
                null,
                "menuItemDescriptions", null, 0, -1, TreePopupMenu.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        // Initialize enums and add enum literals
        initEEnum(treeDragSourceEEnum, TreeDragSource.class, "TreeDragSource"); //$NON-NLS-1$
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
        String source = "http://www.eclipse.org/sirius/interpreted/expression/returnType"; //$NON-NLS-1$
        addAnnotation(getTreeDescription_PreconditionExpression(), source, new String[] { "returnType", "boolean" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getTreeItemMapping_PreconditionExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getTreeItemMapping_SemanticCandidatesExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getTreeMapping_SemanticElements(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
    }

    /**
     * Initializes the annotations for
     * <b>http://www.eclipse.org/sirius/interpreted/expression/variables</b>.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void createVariablesAnnotations() {
        String source = "http://www.eclipse.org/sirius/interpreted/expression/variables"; //$NON-NLS-1$
        addAnnotation(getTreeDescription_PreconditionExpression(), source, new String[] {});
        addAnnotation(getTreeItemMapping_PreconditionExpression(), source, new String[] { "tree", "tree.DTree | current DTree.", //$NON-NLS-1$ //$NON-NLS-2$
            "containerView", "ecore.EObject | container of the current DTreeElement (variable is available if container is not null).", //$NON-NLS-1$ //$NON-NLS-2$
            "container", "ecore.EObject | semantic target of $containerView (if it is a DSemanticDecorator)." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getTreeItemMapping_SemanticCandidatesExpression(), source, new String[] { "tree", "tree.DTree | current DTree.", //$NON-NLS-1$ //$NON-NLS-2$
            "containerView", "ecore.EObject | container of the current DTreeElement (variable is available if container is not null).", //$NON-NLS-1$ //$NON-NLS-2$
            "container", "ecore.EObject | semantic target of $containerView (if it is a DSemanticDecorator)." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getTreeMapping_SemanticElements(), source, new String[] { "view", "tree.DTreeElement | current DTreeElement.", //$NON-NLS-1$ //$NON-NLS-2$
            "containerView", "ecore.EObject | container of the current DTreeElement (variable is available if container is not null).", //$NON-NLS-1$ //$NON-NLS-2$
            "container", "ecore.EObject | semantic target of $containerView (if it is a DSemanticDecorator)." //$NON-NLS-1$ //$NON-NLS-2$
        });
    }

} // DescriptionPackageImpl
