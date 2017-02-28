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
package org.eclipse.sirius.diagram.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.diagram.ContainerStyle;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.provider.LabelStyleItemProvider;

/**
 * This is the item provider adapter for a {@link org.eclipse.sirius.diagram.ContainerStyle} object. <!-- begin-user-doc
 * --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class ContainerStyleItemProvider extends LabelStyleItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ContainerStyleItemProvider(AdapterFactory adapterFactory) {
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

            addDescriptionPropertyDescriptor(object);
            addBorderSizePropertyDescriptor(object);
            addBorderColorPropertyDescriptor(object);
            addBorderLineStylePropertyDescriptor(object);
            addHideLabelByDefaultPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Description feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addDescriptionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_Style_description_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_Style_description_feature", "_UI_Style_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                ViewpointPackage.Literals.STYLE__DESCRIPTION, true, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Border Size feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addBorderSizePropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_BorderedStyle_borderSize_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_BorderedStyle_borderSize_feature", "_UI_BorderedStyle_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        DiagramPackage.Literals.BORDERED_STYLE__BORDER_SIZE, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_BorderPropertyCategory"), //$NON-NLS-1$
                        null));
    }

    /**
     * This adds a property descriptor for the Border Color feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addBorderColorPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_BorderedStyle_borderColor_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_BorderedStyle_borderColor_feature", "_UI_BorderedStyle_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        DiagramPackage.Literals.BORDERED_STYLE__BORDER_COLOR, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_BorderPropertyCategory"), //$NON-NLS-1$
                        null));
    }

    /**
     * This adds a property descriptor for the Border Line Style feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addBorderLineStylePropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_BorderedStyle_borderLineStyle_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_BorderedStyle_borderLineStyle_feature", "_UI_BorderedStyle_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        DiagramPackage.Literals.BORDERED_STYLE__BORDER_LINE_STYLE, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_BorderPropertyCategory"), //$NON-NLS-1$
                        null));
    }

    /**
     * This adds a property descriptor for the Hide Label By Default feature. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    protected void addHideLabelByDefaultPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_HideLabelCapabilityStyle_hideLabelByDefault_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_HideLabelCapabilityStyle_hideLabelByDefault_feature", "_UI_HideLabelCapabilityStyle_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DiagramPackage.Literals.HIDE_LABEL_CAPABILITY_STYLE__HIDE_LABEL_BY_DEFAULT, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getText(Object object) {
        ContainerStyle containerStyle = (ContainerStyle) object;
        return getString("_UI_ContainerStyle_type") + " " + containerStyle.getLabelSize(); //$NON-NLS-1$ //$NON-NLS-2$
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

        switch (notification.getFeatureID(ContainerStyle.class)) {
        case DiagramPackage.CONTAINER_STYLE__BORDER_SIZE:
        case DiagramPackage.CONTAINER_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION:
        case DiagramPackage.CONTAINER_STYLE__BORDER_COLOR:
        case DiagramPackage.CONTAINER_STYLE__BORDER_LINE_STYLE:
        case DiagramPackage.CONTAINER_STYLE__HIDE_LABEL_BY_DEFAULT:
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
