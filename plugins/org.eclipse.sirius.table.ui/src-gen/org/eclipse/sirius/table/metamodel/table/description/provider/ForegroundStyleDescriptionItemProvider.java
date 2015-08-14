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
package org.eclipse.sirius.table.metamodel.table.description.provider;

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
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.sirius.table.metamodel.table.description.ForegroundStyleDescription;
import org.eclipse.sirius.table.metamodel.table.provider.TableUIPlugin;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.table.metamodel.table.description.ForegroundStyleDescription}
 * object. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class ForegroundStyleDescriptionItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider,
IItemPropertySource {
    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ForegroundStyleDescriptionItemProvider(AdapterFactory adapterFactory) {
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
            addForeGroundColorPropertyDescriptor(object);
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
                getString("_UI_ForegroundStyleDescription_labelSize_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_ForegroundStyleDescription_labelSize_feature", "_UI_ForegroundStyleDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DescriptionPackage.Literals.FOREGROUND_STYLE_DESCRIPTION__LABEL_SIZE, true, false, false, ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE, getString("_UI_LabelPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Label Format feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addLabelFormatPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_ForegroundStyleDescription_labelFormat_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_ForegroundStyleDescription_labelFormat_feature", "_UI_ForegroundStyleDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DescriptionPackage.Literals.FOREGROUND_STYLE_DESCRIPTION__LABEL_FORMAT, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_LabelPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Fore Ground Color feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addForeGroundColorPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_ForegroundStyleDescription_foreGroundColor_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_ForegroundStyleDescription_foreGroundColor_feature", "_UI_ForegroundStyleDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DescriptionPackage.Literals.FOREGROUND_STYLE_DESCRIPTION__FORE_GROUND_COLOR, true, false, false, null, getString("_UI_ColorPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This returns ForegroundStyleDescription.gif. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/ForegroundStyleDescription")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getText(Object object) {
        ForegroundStyleDescription foregroundStyleDescription = (ForegroundStyleDescription) object;
        return getString("_UI_ForegroundStyleDescription_type") + " " + foregroundStyleDescription.getLabelSize(); //$NON-NLS-1$ //$NON-NLS-2$
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

        switch (notification.getFeatureID(ForegroundStyleDescription.class)) {
        case DescriptionPackage.FOREGROUND_STYLE_DESCRIPTION__LABEL_SIZE:
        case DescriptionPackage.FOREGROUND_STYLE_DESCRIPTION__LABEL_FORMAT:
        case DescriptionPackage.FOREGROUND_STYLE_DESCRIPTION__FORE_GROUND_COLOR:
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
