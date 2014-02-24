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
package org.eclipse.sirius.diagram.description.style.provider;

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
import org.eclipse.sirius.diagram.description.style.NodeStyleDescription;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.diagram.description.style.NodeStyleDescription}
 * object. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class NodeStyleDescriptionItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider,
        IItemPropertySource {
    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NodeStyleDescriptionItemProvider(AdapterFactory adapterFactory) {
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

            addBorderSizeComputationExpressionPropertyDescriptor(object);
            addBorderColorPropertyDescriptor(object);
            addLabelSizePropertyDescriptor(object);
            addLabelFormatPropertyDescriptor(object);
            addShowIconPropertyDescriptor(object);
            addLabelExpressionPropertyDescriptor(object);
            addLabelColorPropertyDescriptor(object);
            addIconPathPropertyDescriptor(object);
            addLabelAlignmentPropertyDescriptor(object);
            addTooltipExpressionPropertyDescriptor(object);
            addSizeComputationExpressionPropertyDescriptor(object);
            addLabelPositionPropertyDescriptor(object);
            addHideLabelByDefaultPropertyDescriptor(object);
            addResizeKindPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Border Size Computation
     * Expression feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addBorderSizeComputationExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_BorderedStyleDescription_borderSizeComputationExpression_feature"), getString("_UI_BorderedStyleDescription_borderSizeComputationExpression_description"),
                StylePackage.Literals.BORDERED_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                getString("_UI_BorderPropertyCategory"), null));
    }

    /**
     * This adds a property descriptor for the Border Color feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addBorderColorPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_BorderedStyleDescription_borderColor_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_BorderedStyleDescription_borderColor_feature", "_UI_BorderedStyleDescription_type"),
                StylePackage.Literals.BORDERED_STYLE_DESCRIPTION__BORDER_COLOR, true, false, false, null, getString("_UI_ColorPropertyCategory"), null));
    }

    /**
     * This adds a property descriptor for the Label Size feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addLabelSizePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_BasicLabelStyleDescription_labelSize_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_BasicLabelStyleDescription_labelSize_feature", "_UI_BasicLabelStyleDescription_type"),
                org.eclipse.sirius.viewpoint.description.style.StylePackage.Literals.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_SIZE, true, false, false, ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
                getString("_UI_LabelPropertyCategory"), null));
    }

    /**
     * This adds a property descriptor for the Label Format feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addLabelFormatPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_BasicLabelStyleDescription_labelFormat_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_BasicLabelStyleDescription_labelFormat_feature", "_UI_BasicLabelStyleDescription_type"),
                org.eclipse.sirius.viewpoint.description.style.StylePackage.Literals.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_FORMAT, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                getString("_UI_LabelPropertyCategory"), null));
    }

    /**
     * This adds a property descriptor for the Show Icon feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addShowIconPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_BasicLabelStyleDescription_showIcon_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_BasicLabelStyleDescription_showIcon_feature", "_UI_BasicLabelStyleDescription_type"),
                org.eclipse.sirius.viewpoint.description.style.StylePackage.Literals.BASIC_LABEL_STYLE_DESCRIPTION__SHOW_ICON, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
                getString("_UI_LabelPropertyCategory"), null));
    }

    /**
     * This adds a property descriptor for the Label Expression feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addLabelExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_BasicLabelStyleDescription_labelExpression_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_BasicLabelStyleDescription_labelExpression_feature", "_UI_BasicLabelStyleDescription_type"),
                org.eclipse.sirius.viewpoint.description.style.StylePackage.Literals.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                getString("_UI_LabelPropertyCategory"), null));
    }

    /**
     * This adds a property descriptor for the Label Color feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addLabelColorPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                        getString("_UI_BasicLabelStyleDescription_labelColor_feature"),
                        getString("_UI_PropertyDescriptor_description", "_UI_BasicLabelStyleDescription_labelColor_feature", "_UI_BasicLabelStyleDescription_type"),
                        org.eclipse.sirius.viewpoint.description.style.StylePackage.Literals.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_COLOR, true, false, true, null,
                        getString("_UI_ColorPropertyCategory"), null));
    }

    /**
     * This adds a property descriptor for the Icon Path feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addIconPathPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_BasicLabelStyleDescription_iconPath_feature"), getString("_UI_BasicLabelStyleDescription_iconPath_description"),
                org.eclipse.sirius.viewpoint.description.style.StylePackage.Literals.BASIC_LABEL_STYLE_DESCRIPTION__ICON_PATH, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                getString("_UI_AdvancedPropertyCategory"), null));
    }

    /**
     * This adds a property descriptor for the Label Alignment feature. <!--
     * begin-user-doc -->
     * 
     * @since 0.9.0 <!-- end-user-doc -->
     * @generated
     */
    protected void addLabelAlignmentPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_LabelStyleDescription_labelAlignment_feature"), getString("_UI_LabelStyleDescription_labelAlignment_description"),
                org.eclipse.sirius.viewpoint.description.style.StylePackage.Literals.LABEL_STYLE_DESCRIPTION__LABEL_ALIGNMENT, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                getString("_UI_LabelPropertyCategory"), null));
    }

    /**
     * This adds a property descriptor for the Tooltip Expression feature. <!--
     * begin-user-doc -->
     * 
     * @since 0.9.0 <!-- end-user-doc -->
     * @generated
     */
    protected void addTooltipExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_TooltipStyleDescription_tooltipExpression_feature"), getString("_UI_TooltipStyleDescription_tooltipExpression_description"),
                org.eclipse.sirius.viewpoint.description.style.StylePackage.Literals.TOOLTIP_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                getString("_UI_GeneralPropertyCategory"), null));
    }

    /**
     * This adds a property descriptor for the Size Computation Expression
     * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addSizeComputationExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_NodeStyleDescription_sizeComputationExpression_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_NodeStyleDescription_sizeComputationExpression_feature", "_UI_NodeStyleDescription_type"),
                StylePackage.Literals.NODE_STYLE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_AdvancedPropertyCategory"),
                null));
    }

    /**
     * This adds a property descriptor for the Label Position feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addLabelPositionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_NodeStyleDescription_labelPosition_feature"), getString("_UI_NodeStyleDescription_labelPosition_description"),
                StylePackage.Literals.NODE_STYLE_DESCRIPTION__LABEL_POSITION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_LabelPropertyCategory"), null));
    }

    /**
     * This adds a property descriptor for the Hide Label By Default feature.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addHideLabelByDefaultPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_NodeStyleDescription_hideLabelByDefault_feature"), getString("_UI_NodeStyleDescription_hideLabelByDefault_description"),
                StylePackage.Literals.NODE_STYLE_DESCRIPTION__HIDE_LABEL_BY_DEFAULT, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, getString("_UI_LabelPropertyCategory"), null));
    }

    /**
     * This adds a property descriptor for the Resize Kind feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addResizeKindPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_NodeStyleDescription_resizeKind_feature"), getString("_UI_NodeStyleDescription_resizeKind_description"), StylePackage.Literals.NODE_STYLE_DESCRIPTION__RESIZE_KIND,
                true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_AdvancedPropertyCategory"), null));
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    @Override
    public String getText(Object object) {
        String label = getString("_UI_NodeStyleDescription_type");
        return label;
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

        switch (notification.getFeatureID(NodeStyleDescription.class)) {
        case StylePackage.NODE_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION:
        case StylePackage.NODE_STYLE_DESCRIPTION__BORDER_COLOR:
        case StylePackage.NODE_STYLE_DESCRIPTION__LABEL_SIZE:
        case StylePackage.NODE_STYLE_DESCRIPTION__LABEL_FORMAT:
        case StylePackage.NODE_STYLE_DESCRIPTION__SHOW_ICON:
        case StylePackage.NODE_STYLE_DESCRIPTION__LABEL_EXPRESSION:
        case StylePackage.NODE_STYLE_DESCRIPTION__ICON_PATH:
        case StylePackage.NODE_STYLE_DESCRIPTION__LABEL_ALIGNMENT:
        case StylePackage.NODE_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION:
        case StylePackage.NODE_STYLE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION:
        case StylePackage.NODE_STYLE_DESCRIPTION__LABEL_POSITION:
        case StylePackage.NODE_STYLE_DESCRIPTION__HIDE_LABEL_BY_DEFAULT:
        case StylePackage.NODE_STYLE_DESCRIPTION__RESIZE_KIND:
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
        return SiriusEditPlugin.INSTANCE;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getCreateChildText(java.lang.Object,
     *      java.lang.Object, java.lang.Object, java.util.Collection)
     * @not-generated
     */
    @Override
    public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
        boolean qualify = feature == org.eclipse.sirius.diagram.description.style.StylePackage.Literals.BORDERED_STYLE_DESCRIPTION__BORDER_COLOR;

        if (qualify) {
            return getString("_UI_CreateChild_text2", new Object[] { getTypeText(child), getFeatureText(feature), getTypeText(owner) });
        }

        return super.getCreateChildText(owner, feature, child, selection);
    }
}
