/**
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.diagram.description.tool.provider;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.diagram.description.tool.Navigation;
import org.eclipse.sirius.diagram.description.tool.ToolFactory;
import org.eclipse.sirius.diagram.description.tool.ToolPackage;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.viewpoint.description.tool.provider.ContainerModelOperationItemProvider;

/**
 * This is the item provider adapter for a {@link org.eclipse.sirius.diagram.description.tool.Navigation} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class NavigationItemProvider extends ContainerModelOperationItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NavigationItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * This returns the property descriptors for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
        if (itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);

            addCreateIfNotExistentPropertyDescriptor(object);
            addDiagramDescriptionPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Create If Not Existent feature. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    protected void addCreateIfNotExistentPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_Navigation_createIfNotExistent_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_Navigation_createIfNotExistent_feature", "_UI_Navigation_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        ToolPackage.Literals.NAVIGATION__CREATE_IF_NOT_EXISTENT, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                        null));
    }

    /**
     * This adds a property descriptor for the Diagram Description feature. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    protected void addDiagramDescriptionPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_Navigation_diagramDescription_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_Navigation_diagramDescription_feature", "_UI_Navigation_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        ToolPackage.Literals.NAVIGATION__DIAGRAM_DESCRIPTION, true, false, true, null, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                        null));
    }

    /**
     * This returns Navigation.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/Navigation")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public String getText(Object object) {
        Navigation navigation = (Navigation) object;
        if (navigation.getDiagramDescription() != null && navigation.getDiagramDescription().getName() != null) {
            return MessageFormat.format(Messages.NavigationItemProvider_labelWithDescriptionName, getString("_UI_Navigation_type"), navigation.getDiagramDescription().getName()); //$NON-NLS-1$
        } else {
            return MessageFormat.format("{0} {1}", getString("_UI_Navigation_type"), navigation.isCreateIfNotExistent()); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * This handles model notifications by calling {@link #updateChildren} to update any cached children and by creating
     * a viewer notification, which it passes to {@link #fireNotifyChanged}. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    @Override
    public void notifyChanged(Notification notification) {
        updateChildren(notification);

        switch (notification.getFeatureID(Navigation.class)) {
        case ToolPackage.NAVIGATION__CREATE_IF_NOT_EXISTENT:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        }
        super.notifyChanged(notification);
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children that can be created
     * under this object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);

        newChildDescriptors.add(
                createChildParameter(org.eclipse.sirius.viewpoint.description.tool.ToolPackage.Literals.CONTAINER_MODEL_OPERATION__SUB_MODEL_OPERATIONS, ToolFactory.eINSTANCE.createCreateView()));

        newChildDescriptors.add(
                createChildParameter(org.eclipse.sirius.viewpoint.description.tool.ToolPackage.Literals.CONTAINER_MODEL_OPERATION__SUB_MODEL_OPERATIONS, ToolFactory.eINSTANCE.createCreateEdgeView()));

        newChildDescriptors.add(
                createChildParameter(org.eclipse.sirius.viewpoint.description.tool.ToolPackage.Literals.CONTAINER_MODEL_OPERATION__SUB_MODEL_OPERATIONS, ToolFactory.eINSTANCE.createNavigation()));
    }

    /**
     * Return the resource locator for this item provider's resources. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ResourceLocator getResourceLocator() {
        return DiagramUIPlugin.INSTANCE;
    }

}
