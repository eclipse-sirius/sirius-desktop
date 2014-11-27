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
import org.eclipse.emf.ecore.EStructuralFeature;
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
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

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
                getString("_UI_DTableElementStyle_labelSize_feature"), getString("_UI_PropertyDescriptor_description", "_UI_DTableElementStyle_labelSize_feature", "_UI_DTableElementStyle_type"),
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
                getString("_UI_DTableElementStyle_labelFormat_feature"), getString("_UI_PropertyDescriptor_description", "_UI_DTableElementStyle_labelFormat_feature", "_UI_DTableElementStyle_type"),
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
                getString("_UI_DTableElementStyle_defaultForegroundStyle_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_DTableElementStyle_defaultForegroundStyle_feature", "_UI_DTableElementStyle_type"),
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
                getString("_UI_DTableElementStyle_defaultBackgroundStyle_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_DTableElementStyle_defaultBackgroundStyle_feature", "_UI_DTableElementStyle_type"),
                TablePackage.Literals.DTABLE_ELEMENT_STYLE__DEFAULT_BACKGROUND_STYLE, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
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
            childrenFeatures.add(TablePackage.Literals.DTABLE_ELEMENT_STYLE__FOREGROUND_COLOR);
            childrenFeatures.add(TablePackage.Literals.DTABLE_ELEMENT_STYLE__BACKGROUND_COLOR);
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
     * This returns DTableElementStyle.gif. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/DTableElementStyle"));
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
        return getString("_UI_DTableElementStyle_type") + " " + dTableElementStyle.getLabelSize();
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
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case TablePackage.DTABLE_ELEMENT_STYLE__FOREGROUND_COLOR:
        case TablePackage.DTABLE_ELEMENT_STYLE__BACKGROUND_COLOR:
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

        newChildDescriptors.add(createChildParameter(TablePackage.Literals.DTABLE_ELEMENT_STYLE__FOREGROUND_COLOR,
                ViewpointFactory.eINSTANCE.createFromString(ViewpointPackage.Literals.RGB_VALUES, "0,0,0")));

        newChildDescriptors.add(createChildParameter(TablePackage.Literals.DTABLE_ELEMENT_STYLE__BACKGROUND_COLOR,
                ViewpointFactory.eINSTANCE.createFromString(ViewpointPackage.Literals.RGB_VALUES, "255,255,255")));
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
