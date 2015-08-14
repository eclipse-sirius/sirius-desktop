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
package org.eclipse.sirius.tree.description.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.tree.business.internal.metamodel.TreeToolVariables;
import org.eclipse.sirius.tree.description.ConditionalTreeItemStyleDescription;
import org.eclipse.sirius.tree.description.DescriptionFactory;
import org.eclipse.sirius.tree.description.DescriptionPackage;
import org.eclipse.sirius.tree.description.TreeItemContainerDropTool;
import org.eclipse.sirius.tree.description.TreeItemCreationTool;
import org.eclipse.sirius.tree.description.TreeItemDeletionTool;
import org.eclipse.sirius.tree.description.TreeItemEditionTool;
import org.eclipse.sirius.tree.description.TreeItemMapping;
import org.eclipse.sirius.tree.description.TreeMapping;
import org.eclipse.sirius.tree.description.TreePopupMenu;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.tree.description.TreeItemMapping} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class TreeItemMappingItemProvider extends TreeMappingItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public TreeItemMappingItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * This returns the property descriptors for the adapted class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
        if (itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);

            addDomainClassPropertyDescriptor(object);
            addPreconditionExpressionPropertyDescriptor(object);
            addSemanticCandidatesExpressionPropertyDescriptor(object);
            addReusedTreeItemMappingsPropertyDescriptor(object);
            addSpecializePropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Domain Class feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addDomainClassPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_TreeItemMapping_domainClass_feature"), //$NON-NLS-1$
                getString("_UI_TreeItemMapping_domainClass_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.TREE_ITEM_MAPPING__DOMAIN_CLASS, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Precondition Expression feature.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addPreconditionExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_TreeItemMapping_preconditionExpression_feature"), //$NON-NLS-1$
                getString("_UI_TreeItemMapping_preconditionExpression_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.TREE_ITEM_MAPPING__PRECONDITION_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_AdvancedPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Semantic Candidates Expression
     * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addSemanticCandidatesExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_TreeItemMapping_semanticCandidatesExpression_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_TreeItemMapping_semanticCandidatesExpression_feature", "_UI_TreeItemMapping_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DescriptionPackage.Literals.TREE_ITEM_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Reused Tree Item Mappings
     * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addReusedTreeItemMappingsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_TreeItemMapping_reusedTreeItemMappings_feature"), //$NON-NLS-1$
                getString("_UI_TreeItemMapping_reusedTreeItemMappings_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.TREE_ITEM_MAPPING__REUSED_TREE_ITEM_MAPPINGS, true, false, true, null, getString("_UI_ImportPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Specialize feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addSpecializePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_TreeItemMapping_specialize_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_TreeItemMapping_specialize_feature", "_UI_TreeItemMapping_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DescriptionPackage.Literals.TREE_ITEM_MAPPING__SPECIALIZE, true, false, true, null, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This specifies how to implement {@link #getChildren} and is used to
     * deduce an appropriate feature for an
     * {@link org.eclipse.emf.edit.command.AddCommand},
     * {@link org.eclipse.emf.edit.command.RemoveCommand} or
     * {@link org.eclipse.emf.edit.command.MoveCommand} in
     * {@link #createCommand}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
        if (childrenFeatures == null) {
            super.getChildrenFeatures(object);
            childrenFeatures.add(DescriptionPackage.Literals.STYLE_UPDATER__DEFAULT_STYLE);
            childrenFeatures.add(DescriptionPackage.Literals.STYLE_UPDATER__CONDITIONAL_STYLES);
            childrenFeatures.add(DescriptionPackage.Literals.TREE_ITEM_UPDATER__DIRECT_EDIT);
            childrenFeatures.add(DescriptionPackage.Literals.TREE_ITEM_MAPPING_CONTAINER__SUB_ITEM_MAPPINGS);
            childrenFeatures.add(DescriptionPackage.Literals.TREE_ITEM_MAPPING_CONTAINER__DROP_TOOLS);
            childrenFeatures.add(DescriptionPackage.Literals.TREE_ITEM_MAPPING__DELETE);
            childrenFeatures.add(DescriptionPackage.Literals.TREE_ITEM_MAPPING__CREATE);
            childrenFeatures.add(DescriptionPackage.Literals.TREE_ITEM_MAPPING__DND_TOOLS);
            childrenFeatures.add(DescriptionPackage.Literals.TREE_ITEM_MAPPING__POPUP_MENUS);
        }
        return childrenFeatures;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EStructuralFeature getChildFeature(Object object, Object child) {
        // Check the type of the specified child object and return the proper
        // feature to use for
        // adding (see {@link AddCommand}) it as a child.

        return super.getChildFeature(object, child);
    }

    /**
     * This returns TreeItemMapping.gif. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/TreeItemMapping")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public String getText(Object object) {
        String label = new IdentifiedElementQuery((TreeMapping) object).getLabel();
        return label == null || label.length() == 0 ? getString("_UI_TreeMapping_type") : label; //$NON-NLS-1$
    }

    /**
     * This handles model notifications by calling {@link #updateChildren} to
     * update any cached children and by creating a viewer notification, which
     * it passes to {@link #fireNotifyChanged}. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void notifyChanged(Notification notification) {
        updateChildren(notification);

        switch (notification.getFeatureID(TreeItemMapping.class)) {
        case DescriptionPackage.TREE_ITEM_MAPPING__DOMAIN_CLASS:
        case DescriptionPackage.TREE_ITEM_MAPPING__PRECONDITION_EXPRESSION:
        case DescriptionPackage.TREE_ITEM_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case DescriptionPackage.TREE_ITEM_MAPPING__DEFAULT_STYLE:
        case DescriptionPackage.TREE_ITEM_MAPPING__CONDITIONAL_STYLES:
        case DescriptionPackage.TREE_ITEM_MAPPING__DIRECT_EDIT:
        case DescriptionPackage.TREE_ITEM_MAPPING__SUB_ITEM_MAPPINGS:
        case DescriptionPackage.TREE_ITEM_MAPPING__DROP_TOOLS:
        case DescriptionPackage.TREE_ITEM_MAPPING__DELETE:
        case DescriptionPackage.TREE_ITEM_MAPPING__CREATE:
        case DescriptionPackage.TREE_ITEM_MAPPING__DND_TOOLS:
        case DescriptionPackage.TREE_ITEM_MAPPING__POPUP_MENUS:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
            return;
        }
        super.notifyChanged(notification);
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s
     * describing the children that can be created under this object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.STYLE_UPDATER__DEFAULT_STYLE, DescriptionFactory.eINSTANCE.createTreeItemStyleDescription()));

        ConditionalTreeItemStyleDescription conditionalTreeItemStyle = DescriptionFactory.eINSTANCE.createConditionalTreeItemStyleDescription();
        conditionalTreeItemStyle.setStyle(DescriptionFactory.eINSTANCE.createTreeItemStyleDescription());
        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.STYLE_UPDATER__CONDITIONAL_STYLES, conditionalTreeItemStyle));

        TreeItemMapping newItemMapping = DescriptionFactory.eINSTANCE.createTreeItemMapping();
        newItemMapping.setDefaultStyle(DescriptionFactory.eINSTANCE.createTreeItemStyleDescription());
        newItemMapping.setSemanticCandidatesExpression("feature:eAllContents"); //$NON-NLS-1$

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TREE_ITEM_MAPPING_CONTAINER__SUB_ITEM_MAPPINGS, newItemMapping));

        // VP-912 : Direct Edit Tool on Tree Items
        collectDirectEditToolChildDescriptors(newChildDescriptors, object);

        // VP-926 : Drag&Drop for trees
        collectDnDToolChildDescriptors(newChildDescriptors);

        // VP-915 : Popup Menus Tool
        collectPopupMenusChildDescriptors(newChildDescriptors);

        collectDeleteToolDescriptors(newChildDescriptors);
        collectTreeItemCreationTool(newChildDescriptors);

    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s
     * describing the children that can be created under this object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void collectNewChildDescriptorsGen(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.STYLE_UPDATER__DEFAULT_STYLE, DescriptionFactory.eINSTANCE.createTreeItemStyleDescription()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.STYLE_UPDATER__CONDITIONAL_STYLES, DescriptionFactory.eINSTANCE.createConditionalTreeItemStyleDescription()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TREE_ITEM_UPDATER__DIRECT_EDIT, DescriptionFactory.eINSTANCE.createTreeItemEditionTool()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TREE_ITEM_MAPPING_CONTAINER__SUB_ITEM_MAPPINGS, DescriptionFactory.eINSTANCE.createTreeItemMapping()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TREE_ITEM_MAPPING_CONTAINER__DROP_TOOLS, DescriptionFactory.eINSTANCE.createTreeItemContainerDropTool()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TREE_ITEM_MAPPING__DELETE, DescriptionFactory.eINSTANCE.createTreeItemDeletionTool()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TREE_ITEM_MAPPING__CREATE, DescriptionFactory.eINSTANCE.createTreeItemCreationTool()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TREE_ITEM_MAPPING__DND_TOOLS, DescriptionFactory.eINSTANCE.createTreeItemDragTool()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TREE_ITEM_MAPPING__POPUP_MENUS, DescriptionFactory.eINSTANCE.createTreePopupMenu()));
    }

    private void collectDeleteToolDescriptors(Collection<Object> newChildDescriptors) {
        TreeItemDeletionTool treeItemDeletionTool = DescriptionFactory.eINSTANCE.createTreeItemDeletionTool();
        new TreeToolVariables().doSwitch(treeItemDeletionTool);
        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TREE_ITEM_MAPPING__DELETE, treeItemDeletionTool));
    }

    private void collectTreeItemCreationTool(Collection<Object> newChildDescriptors) {
        TreeItemCreationTool treeItemCreationTool = DescriptionFactory.eINSTANCE.createTreeItemCreationTool();
        new TreeToolVariables().doSwitch(treeItemCreationTool);
        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TREE_ITEM_MAPPING__CREATE, treeItemCreationTool));
    }

    private void collectDnDToolChildDescriptors(Collection<Object> newChildDescriptors) {
        // building the drop tool.
        TreeItemContainerDropTool dropTool = DescriptionFactory.eINSTANCE.createTreeItemContainerDropTool();
        dropTool.setForceRefresh(true);
        new TreeToolVariables().doSwitch(dropTool);

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TREE_ITEM_MAPPING_CONTAINER__DROP_TOOLS, dropTool));
    }

    private void collectDirectEditToolChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        // build the direct edit Tool
        TreeItemEditionTool labelEditTool = DescriptionFactory.eINSTANCE.createTreeItemEditionTool();
        new TreeToolVariables().doSwitch(labelEditTool);
        if (object instanceof TreeItemMapping) {
            labelEditTool.getMapping().add((TreeItemMapping) object);
        }
        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TREE_ITEM_UPDATER__DIRECT_EDIT, labelEditTool));
    }

    private void collectPopupMenusChildDescriptors(Collection<Object> newChildDescriptors) {
        TreePopupMenu popupMenu = DescriptionFactory.eINSTANCE.createTreePopupMenu();
        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TREE_ITEM_MAPPING__POPUP_MENUS, popupMenu));
    }
}
