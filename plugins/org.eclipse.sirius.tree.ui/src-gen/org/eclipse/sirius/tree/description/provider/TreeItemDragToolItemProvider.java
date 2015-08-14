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
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.tree.description.DescriptionFactory;
import org.eclipse.sirius.tree.description.DescriptionPackage;
import org.eclipse.sirius.tree.description.TreeItemDragTool;
import org.eclipse.sirius.tree.ui.provider.TreeUIPlugin;
import org.eclipse.sirius.ui.tools.api.provider.ItemProviderHelper;
import org.eclipse.sirius.viewpoint.description.tool.ToolFactory;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.tool.provider.MappingBasedToolDescriptionItemProvider;

import com.google.common.collect.Lists;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.tree.description.TreeItemDragTool} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class TreeItemDragToolItemProvider extends MappingBasedToolDescriptionItemProvider {
    private static final Collection<EClass> TYPES_TO_HIDE = Lists.newArrayList(ToolPackage.Literals.DELETE_VIEW);

    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public TreeItemDragToolItemProvider(AdapterFactory adapterFactory) {
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

            addContainersPropertyDescriptor(object);
            addDragSourceTypePropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Containers feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addContainersPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_TreeItemDragTool_containers_feature"), //$NON-NLS-1$
                getString("_UI_TreeItemDragTool_containers_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.TREE_ITEM_DRAG_TOOL__CONTAINERS, true, false, true, null, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Drag Source Type feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addDragSourceTypePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_TreeItemDragTool_dragSourceType_feature"), //$NON-NLS-1$
                getString("_UI_TreeItemDragTool_dragSourceType_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.TREE_ITEM_DRAG_TOOL__DRAG_SOURCE_TYPE, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
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
     * @not-generated
     */
    @Override
    public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
        if (childrenFeatures == null) {
            super.getChildrenFeatures(object);
            childrenFeatures.add(DescriptionPackage.Literals.TREE_ITEM_TOOL__VARIABLES);
            childrenFeatures.add(DescriptionPackage.Literals.TREE_ITEM_DRAG_TOOL__OLD_CONTAINER);
            childrenFeatures.add(DescriptionPackage.Literals.TREE_ITEM_DRAG_TOOL__NEW_CONTAINER);
            childrenFeatures.add(DescriptionPackage.Literals.TREE_ITEM_DRAG_TOOL__ELEMENT);
            childrenFeatures.add(DescriptionPackage.Literals.TREE_ITEM_DRAG_TOOL__NEW_VIEW_CONTAINER);
            childrenFeatures.add(DescriptionPackage.Literals.TREE_ITEM_DRAG_TOOL__PRECEDING_SIBLINGS);
            childrenFeatures.add(DescriptionPackage.Literals.TREE_ITEM_TOOL__FIRST_MODEL_OPERATION);
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
     * This returns TreeItemDragTool.gif. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/TreeItemDragTool")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public String getText(Object object) {
        String label = new IdentifiedElementQuery((TreeItemDragTool) object).getLabel();
        return StringUtil.isEmpty(label) ? getString("_UI_TreeItemDragTool_type") : getString("_UI_TreeItemDragTool_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

        switch (notification.getFeatureID(TreeItemDragTool.class)) {
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__DRAG_SOURCE_TYPE:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__FIRST_MODEL_OPERATION:
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__VARIABLES:
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__OLD_CONTAINER:
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__NEW_CONTAINER:
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__ELEMENT:
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__NEW_VIEW_CONTAINER:
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__PRECEDING_SIBLINGS:
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
        collectNewChildDescriptorsGen(newChildDescriptors, object);
        ItemProviderHelper.filterChildDescriptorsByType(newChildDescriptors, TreeItemDragToolItemProvider.TYPES_TO_HIDE);
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

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TREE_ITEM_TOOL__FIRST_MODEL_OPERATION, ToolFactory.eINSTANCE.createExternalJavaAction()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TREE_ITEM_TOOL__FIRST_MODEL_OPERATION, ToolFactory.eINSTANCE.createExternalJavaActionCall()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TREE_ITEM_TOOL__FIRST_MODEL_OPERATION, ToolFactory.eINSTANCE.createCreateInstance()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TREE_ITEM_TOOL__FIRST_MODEL_OPERATION, ToolFactory.eINSTANCE.createChangeContext()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TREE_ITEM_TOOL__FIRST_MODEL_OPERATION, ToolFactory.eINSTANCE.createSetValue()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TREE_ITEM_TOOL__FIRST_MODEL_OPERATION, ToolFactory.eINSTANCE.createSetObject()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TREE_ITEM_TOOL__FIRST_MODEL_OPERATION, ToolFactory.eINSTANCE.createUnset()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TREE_ITEM_TOOL__FIRST_MODEL_OPERATION, ToolFactory.eINSTANCE.createMoveElement()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TREE_ITEM_TOOL__FIRST_MODEL_OPERATION, ToolFactory.eINSTANCE.createRemoveElement()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TREE_ITEM_TOOL__FIRST_MODEL_OPERATION, ToolFactory.eINSTANCE.createFor()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TREE_ITEM_TOOL__FIRST_MODEL_OPERATION, ToolFactory.eINSTANCE.createIf()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TREE_ITEM_TOOL__FIRST_MODEL_OPERATION, ToolFactory.eINSTANCE.createDeleteView()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TREE_ITEM_TOOL__FIRST_MODEL_OPERATION, ToolFactory.eINSTANCE.createSwitch()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TREE_ITEM_DRAG_TOOL__OLD_CONTAINER, ToolFactory.eINSTANCE.createDropContainerVariable()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TREE_ITEM_DRAG_TOOL__NEW_CONTAINER, ToolFactory.eINSTANCE.createDropContainerVariable()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TREE_ITEM_DRAG_TOOL__ELEMENT, ToolFactory.eINSTANCE.createElementDropVariable()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TREE_ITEM_DRAG_TOOL__NEW_VIEW_CONTAINER, ToolFactory.eINSTANCE.createContainerViewVariable()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TREE_ITEM_DRAG_TOOL__PRECEDING_SIBLINGS, DescriptionFactory.eINSTANCE.createPrecedingSiblingsVariables()));
    }

    /**
     * This returns the label text for
     * {@link org.eclipse.emf.edit.command.CreateChildCommand}. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
        Object childFeature = feature;
        Object childObject = child;

        boolean qualify = childFeature == DescriptionPackage.Literals.TREE_ITEM_DRAG_TOOL__OLD_CONTAINER || childFeature == DescriptionPackage.Literals.TREE_ITEM_DRAG_TOOL__NEW_CONTAINER;

        if (qualify) {
            return getString("_UI_CreateChild_text2", //$NON-NLS-1$
                    new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
        }
        return super.getCreateChildText(owner, feature, child, selection);
    }

    /**
     * Return the resource locator for this item provider's resources. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ResourceLocator getResourceLocator() {
        return TreeUIPlugin.INSTANCE;
    }

}
