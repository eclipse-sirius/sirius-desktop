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
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.tree.description.DescriptionPackage;
import org.eclipse.sirius.tree.description.TreeItemStyleDescription;
import org.eclipse.sirius.tree.ui.provider.TreeUIPlugin;
import org.eclipse.sirius.viewpoint.description.style.StylePackage;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.tree.description.TreeItemStyleDescription} object.
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class TreeItemStyleDescriptionItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider,
IItemPropertySource {
    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public TreeItemStyleDescriptionItemProvider(AdapterFactory adapterFactory) {
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
            addLabelExpressionPropertyDescriptor(object);
            addLabelColorPropertyDescriptor(object);
            addIconPathPropertyDescriptor(object);
            addLabelAlignmentPropertyDescriptor(object);
            addBackgroundColorPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Label Expression feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addLabelExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_BasicLabelStyleDescription_labelExpression_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_BasicLabelStyleDescription_labelExpression_feature", "_UI_BasicLabelStyleDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                StylePackage.Literals.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_LabelPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Label Alignment feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addLabelAlignmentPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_LabelStyleDescription_labelAlignment_feature"), //$NON-NLS-1$
                getString("_UI_LabelStyleDescription_labelAlignment_description"), //$NON-NLS-1$
                StylePackage.Literals.LABEL_STYLE_DESCRIPTION__LABEL_ALIGNMENT, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_LabelPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Label Color feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addLabelColorPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_BasicLabelStyleDescription_labelColor_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_BasicLabelStyleDescription_labelColor_feature", "_UI_BasicLabelStyleDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                StylePackage.Literals.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_COLOR, true, false, true, null, getString("_UI_ColorPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Icon Path feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addIconPathPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_BasicLabelStyleDescription_iconPath_feature"), //$NON-NLS-1$
                getString("_UI_BasicLabelStyleDescription_iconPath_description"), //$NON-NLS-1$
                StylePackage.Literals.BASIC_LABEL_STYLE_DESCRIPTION__ICON_PATH, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_AdvancedPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Background Color feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addBackgroundColorPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_TreeItemStyleDescription_backgroundColor_feature"), //$NON-NLS-1$
                getString("_UI_TreeItemStyleDescription_backgroundColor_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.TREE_ITEM_STYLE_DESCRIPTION__BACKGROUND_COLOR, true, false, true, null, getString("_UI_ColorPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Label Size feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addLabelSizePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_BasicLabelStyleDescription_labelSize_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_BasicLabelStyleDescription_labelSize_feature", "_UI_BasicLabelStyleDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                StylePackage.Literals.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_SIZE, true, false, false, ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE, getString("_UI_LabelPropertyCategory"), //$NON-NLS-1$
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
                getString("_UI_BasicLabelStyleDescription_labelFormat_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_BasicLabelStyleDescription_labelFormat_feature", "_UI_BasicLabelStyleDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                StylePackage.Literals.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_FORMAT, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_LabelPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Show Icon feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addShowIconPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_BasicLabelStyleDescription_showIcon_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_BasicLabelStyleDescription_showIcon_feature", "_UI_BasicLabelStyleDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                StylePackage.Literals.BASIC_LABEL_STYLE_DESCRIPTION__SHOW_ICON, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, getString("_UI_LabelPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This returns TreeItemStyleDescription.gif. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/TreeItemStyleDescription")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public String getText(Object object) {
        TreeItemStyleDescription style = (TreeItemStyleDescription) object;
        String txt = "no label";
        if (!StringUtil.isEmpty(style.getLabelExpression())) {
            txt = style.getLabelExpression();
        }
        return txt;
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

        switch (notification.getFeatureID(TreeItemStyleDescription.class)) {
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__LABEL_SIZE:
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__LABEL_FORMAT:
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__SHOW_ICON:
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__LABEL_EXPRESSION:
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__ICON_PATH:
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__LABEL_ALIGNMENT:
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__BACKGROUND_COLOR:
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
        return TreeUIPlugin.INSTANCE;
    }

}
