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
package org.eclipse.sirius.viewpoint.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.viewpoint.BasicLabelStyle} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class BasicLabelStyleItemProvider extends CustomizableItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public BasicLabelStyleItemProvider(AdapterFactory adapterFactory) {
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

            addLabelSizePropertyDescriptor(object);
            addLabelFormatPropertyDescriptor(object);
            addShowIconPropertyDescriptor(object);
            addIconPathPropertyDescriptor(object);
            addLabelColorPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Label Size feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addLabelSizePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_BasicLabelStyle_labelSize_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_BasicLabelStyle_labelSize_feature", "_UI_BasicLabelStyle_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE, true, false, false, ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Label Format feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addLabelFormatPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_BasicLabelStyle_labelFormat_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_BasicLabelStyle_labelFormat_feature", "_UI_BasicLabelStyle_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_FORMAT, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Show Icon feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addShowIconPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_BasicLabelStyle_showIcon_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_BasicLabelStyle_showIcon_feature", "_UI_BasicLabelStyle_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                ViewpointPackage.Literals.BASIC_LABEL_STYLE__SHOW_ICON, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Icon Path feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addIconPathPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_BasicLabelStyle_iconPath_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_BasicLabelStyle_iconPath_feature", "_UI_BasicLabelStyle_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                ViewpointPackage.Literals.BASIC_LABEL_STYLE__ICON_PATH, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Label Color feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addLabelColorPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_BasicLabelStyle_labelColor_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_BasicLabelStyle_labelColor_feature", "_UI_BasicLabelStyle_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_COLOR, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This returns BasicLabelStyle.gif. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/BasicLabelStyle")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getText(Object object) {
        BasicLabelStyle basicLabelStyle = (BasicLabelStyle) object;
        return getString("_UI_BasicLabelStyle_type") + " " + basicLabelStyle.getLabelSize(); //$NON-NLS-1$ //$NON-NLS-2$
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

        switch (notification.getFeatureID(BasicLabelStyle.class)) {
        case ViewpointPackage.BASIC_LABEL_STYLE__LABEL_SIZE:
        case ViewpointPackage.BASIC_LABEL_STYLE__LABEL_FORMAT:
        case ViewpointPackage.BASIC_LABEL_STYLE__SHOW_ICON:
        case ViewpointPackage.BASIC_LABEL_STYLE__ICON_PATH:
        case ViewpointPackage.BASIC_LABEL_STYLE__LABEL_COLOR:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
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
    }

}
