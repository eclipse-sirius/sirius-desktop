/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Obeo - initial API and implementation
 * 
 */
package org.eclipse.sirius.viewpoint.description.tool.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.viewpoint.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolFactory;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.viewpoint.description.tool.EdgeCreationDescription}
 * object. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class EdgeCreationDescriptionItemProvider extends MappingBasedToolDescriptionItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider,
        IItemLabelProvider, IItemPropertySource {
    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EdgeCreationDescriptionItemProvider(AdapterFactory adapterFactory) {
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

            addEdgeMappingsPropertyDescriptor(object);
            addIconPathPropertyDescriptor(object);
            addExtraSourceMappingsPropertyDescriptor(object);
            addExtraTargetMappingsPropertyDescriptor(object);
            addConnectionStartPreconditionPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Edge Mappings feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addEdgeMappingsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_EdgeCreationDescription_edgeMappings_feature"), getString("_UI_EdgeCreationDescription_edgeMappings_description"),
                ToolPackage.Literals.EDGE_CREATION_DESCRIPTION__EDGE_MAPPINGS, true, false, true, null, getString("_UI_GeneralPropertyCategory"), null));
    }

    /**
     * This adds a property descriptor for the Icon Path feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addIconPathPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_EdgeCreationDescription_iconPath_feature"), getString("_UI_EdgeCreationDescription_iconPath_description"), ToolPackage.Literals.EDGE_CREATION_DESCRIPTION__ICON_PATH,
                true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_AdvancedPropertyCategory"), null));
    }

    /**
     * This adds a property descriptor for the Extra Source Mappings feature.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addExtraSourceMappingsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_EdgeCreationDescription_extraSourceMappings_feature"), getString("_UI_EdgeCreationDescription_extraSourceMappings_description"),
                ToolPackage.Literals.EDGE_CREATION_DESCRIPTION__EXTRA_SOURCE_MAPPINGS, true, false, true, null, getString("_UI_AdvancedPropertyCategory"), null));
    }

    /**
     * This adds a property descriptor for the Extra Target Mappings feature.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addExtraTargetMappingsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_EdgeCreationDescription_extraTargetMappings_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_EdgeCreationDescription_extraTargetMappings_feature", "_UI_EdgeCreationDescription_type"),
                ToolPackage.Literals.EDGE_CREATION_DESCRIPTION__EXTRA_TARGET_MAPPINGS, true, false, true, null, getString("_UI_AdvancedPropertyCategory"), null));
    }

    /**
     * This adds a property descriptor for the Connection Start Precondition
     * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addConnectionStartPreconditionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_EdgeCreationDescription_connectionStartPrecondition_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_EdgeCreationDescription_connectionStartPrecondition_feature", "_UI_EdgeCreationDescription_type"),
                ToolPackage.Literals.EDGE_CREATION_DESCRIPTION__CONNECTION_START_PRECONDITION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                getString("_UI_GeneralPropertyCategory"), null));
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
            childrenFeatures.add(ToolPackage.Literals.EDGE_CREATION_DESCRIPTION__SOURCE_VARIABLE);
            childrenFeatures.add(ToolPackage.Literals.EDGE_CREATION_DESCRIPTION__TARGET_VARIABLE);
            childrenFeatures.add(ToolPackage.Literals.EDGE_CREATION_DESCRIPTION__SOURCE_VIEW_VARIABLE);
            childrenFeatures.add(ToolPackage.Literals.EDGE_CREATION_DESCRIPTION__TARGET_VIEW_VARIABLE);
            childrenFeatures.add(ToolPackage.Literals.EDGE_CREATION_DESCRIPTION__INITIAL_OPERATION);
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
     * This returns EdgeCreationDescription.gif. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/EdgeCreationDescription"));
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getText(Object object) {
        String label = ((EdgeCreationDescription) object).getName();
        return label == null || label.length() == 0 ? getString("_UI_EdgeCreationDescription_type") : getString("_UI_EdgeCreationDescription_type") + " " + label;
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

        switch (notification.getFeatureID(EdgeCreationDescription.class)) {
        case ToolPackage.EDGE_CREATION_DESCRIPTION__ICON_PATH:
        case ToolPackage.EDGE_CREATION_DESCRIPTION__CONNECTION_START_PRECONDITION:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case ToolPackage.EDGE_CREATION_DESCRIPTION__SOURCE_VARIABLE:
        case ToolPackage.EDGE_CREATION_DESCRIPTION__TARGET_VARIABLE:
        case ToolPackage.EDGE_CREATION_DESCRIPTION__SOURCE_VIEW_VARIABLE:
        case ToolPackage.EDGE_CREATION_DESCRIPTION__TARGET_VIEW_VARIABLE:
        case ToolPackage.EDGE_CREATION_DESCRIPTION__INITIAL_OPERATION:
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
     * @generated
     */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.EDGE_CREATION_DESCRIPTION__SOURCE_VARIABLE, ToolFactory.eINSTANCE.createSourceEdgeCreationVariable()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.EDGE_CREATION_DESCRIPTION__TARGET_VARIABLE, ToolFactory.eINSTANCE.createTargetEdgeCreationVariable()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.EDGE_CREATION_DESCRIPTION__SOURCE_VIEW_VARIABLE, ToolFactory.eINSTANCE.createSourceEdgeViewCreationVariable()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.EDGE_CREATION_DESCRIPTION__TARGET_VIEW_VARIABLE, ToolFactory.eINSTANCE.createTargetEdgeViewCreationVariable()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.EDGE_CREATION_DESCRIPTION__INITIAL_OPERATION, ToolFactory.eINSTANCE.createInitEdgeCreationOperation()));
    }

}
