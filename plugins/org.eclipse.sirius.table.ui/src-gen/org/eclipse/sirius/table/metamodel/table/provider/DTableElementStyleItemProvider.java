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
package org.eclipse.sirius.table.metamodel.table.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.table.metamodel.table.DTableElementStyle;
import org.eclipse.sirius.table.metamodel.table.TablePackage;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.table.metamodel.table.DTableElementStyle} object.
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class DTableElementStyleItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider,
IItemPropertySource {
    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public DTableElementStyleItemProvider(AdapterFactory adapterFactory) {
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
            addDefaultForegroundStylePropertyDescriptor(object);
            addDefaultBackgroundStylePropertyDescriptor(object);
            addForegroundColorPropertyDescriptor(object);
            addBackgroundColorPropertyDescriptor(object);
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
                getString("_UI_DTableElementStyle_labelSize_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DTableElementStyle_labelSize_feature", "_UI_DTableElementStyle_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                TablePackage.Literals.DTABLE_ELEMENT_STYLE__LABEL_SIZE, true, false, false, ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Label Format feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addLabelFormatPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DTableElementStyle_labelFormat_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DTableElementStyle_labelFormat_feature", "_UI_DTableElementStyle_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                TablePackage.Literals.DTABLE_ELEMENT_STYLE__LABEL_FORMAT, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Default Foreground Style feature.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addDefaultForegroundStylePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DTableElementStyle_defaultForegroundStyle_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DTableElementStyle_defaultForegroundStyle_feature", "_UI_DTableElementStyle_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                TablePackage.Literals.DTABLE_ELEMENT_STYLE__DEFAULT_FOREGROUND_STYLE, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Default Background Style feature.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addDefaultBackgroundStylePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DTableElementStyle_defaultBackgroundStyle_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DTableElementStyle_defaultBackgroundStyle_feature", "_UI_DTableElementStyle_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                TablePackage.Literals.DTABLE_ELEMENT_STYLE__DEFAULT_BACKGROUND_STYLE, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Foreground Color feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addForegroundColorPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DTableElementStyle_foregroundColor_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DTableElementStyle_foregroundColor_feature", "_UI_DTableElementStyle_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                TablePackage.Literals.DTABLE_ELEMENT_STYLE__FOREGROUND_COLOR, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Background Color feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addBackgroundColorPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DTableElementStyle_backgroundColor_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DTableElementStyle_backgroundColor_feature", "_UI_DTableElementStyle_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                TablePackage.Literals.DTABLE_ELEMENT_STYLE__BACKGROUND_COLOR, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This returns DTableElementStyle.gif. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/DTableElementStyle")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getText(Object object) {
        DTableElementStyle dTableElementStyle = (DTableElementStyle) object;
        return getString("_UI_DTableElementStyle_type") + " " + dTableElementStyle.getLabelSize(); //$NON-NLS-1$ //$NON-NLS-2$
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

        switch (notification.getFeatureID(DTableElementStyle.class)) {
        case TablePackage.DTABLE_ELEMENT_STYLE__LABEL_SIZE:
        case TablePackage.DTABLE_ELEMENT_STYLE__LABEL_FORMAT:
        case TablePackage.DTABLE_ELEMENT_STYLE__DEFAULT_FOREGROUND_STYLE:
        case TablePackage.DTABLE_ELEMENT_STYLE__DEFAULT_BACKGROUND_STYLE:
        case TablePackage.DTABLE_ELEMENT_STYLE__FOREGROUND_COLOR:
        case TablePackage.DTABLE_ELEMENT_STYLE__BACKGROUND_COLOR:
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

    /**
     * Return the resource locator for this item provider's resources. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ResourceLocator getResourceLocator() {
        return TableUIPlugin.INSTANCE;
    }

}
