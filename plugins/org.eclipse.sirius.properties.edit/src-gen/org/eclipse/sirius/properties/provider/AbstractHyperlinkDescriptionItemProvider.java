/**
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.properties.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.StyledString;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.properties.AbstractHyperlinkDescription;
import org.eclipse.sirius.properties.PropertiesFactory;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.viewpoint.description.tool.ToolFactory;

/**
 * This is the item provider adapter for a {@link org.eclipse.sirius.properties.AbstractHyperlinkDescription} object.
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class AbstractHyperlinkDescriptionItemProvider extends AbstractWidgetDescriptionItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public AbstractHyperlinkDescriptionItemProvider(AdapterFactory adapterFactory) {
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

            addValueExpressionPropertyDescriptor(object);
            addDisplayExpressionPropertyDescriptor(object);
            addExtendsPropertyDescriptor(object);
            addFilterConditionalStylesFromExtendedHyperlinkExpressionPropertyDescriptor(object);
            addFilterActionsFromExtendedHyperlinkExpressionPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Value Expression feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addValueExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_AbstractHyperlinkDescription_valueExpression_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_AbstractHyperlinkDescription_valueExpression_feature", "_UI_AbstractHyperlinkDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                PropertiesPackage.Literals.ABSTRACT_HYPERLINK_DESCRIPTION__VALUE_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Display Expression feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addDisplayExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_AbstractHyperlinkDescription_displayExpression_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_AbstractHyperlinkDescription_displayExpression_feature", "_UI_AbstractHyperlinkDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                PropertiesPackage.Literals.ABSTRACT_HYPERLINK_DESCRIPTION__DISPLAY_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Extends feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addExtendsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
                createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_AbstractHyperlinkDescription_extends_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_AbstractHyperlinkDescription_extends_feature", "_UI_AbstractHyperlinkDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        PropertiesPackage.Literals.ABSTRACT_HYPERLINK_DESCRIPTION__EXTENDS, true, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Filter Conditional Styles From Extended Hyperlink Expression feature.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addFilterConditionalStylesFromExtendedHyperlinkExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_AbstractHyperlinkDescription_filterConditionalStylesFromExtendedHyperlinkExpression_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_AbstractHyperlinkDescription_filterConditionalStylesFromExtendedHyperlinkExpression_feature", //$NON-NLS-1$//$NON-NLS-2$
                        "_UI_AbstractHyperlinkDescription_type"), //$NON-NLS-1$
                PropertiesPackage.Literals.ABSTRACT_HYPERLINK_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_HYPERLINK_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                null, null));
    }

    /**
     * This adds a property descriptor for the Filter Actions From Extended Hyperlink Expression feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addFilterActionsFromExtendedHyperlinkExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_AbstractHyperlinkDescription_filterActionsFromExtendedHyperlinkExpression_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_AbstractHyperlinkDescription_filterActionsFromExtendedHyperlinkExpression_feature", "_UI_AbstractHyperlinkDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                PropertiesPackage.Literals.ABSTRACT_HYPERLINK_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_HYPERLINK_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null,
                null));
    }

    /**
     * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
     * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
     * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    @Override
    public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
        if (childrenFeatures == null) {
            super.getChildrenFeatures(object);
            childrenFeatures.add(PropertiesPackage.Literals.ABSTRACT_HYPERLINK_DESCRIPTION__INITIAL_OPERATION);
            childrenFeatures.add(PropertiesPackage.Literals.ABSTRACT_HYPERLINK_DESCRIPTION__STYLE);
            childrenFeatures.add(PropertiesPackage.Literals.ABSTRACT_HYPERLINK_DESCRIPTION__CONDITIONAL_STYLES);
            childrenFeatures.add(PropertiesPackage.Literals.ABSTRACT_HYPERLINK_DESCRIPTION__ACTIONS);
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
        // Check the type of the specified child object and return the proper feature to use for
        // adding (see {@link AddCommand}) it as a child.

        return super.getChildFeature(object, child);
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getText(Object object) {
        return ((StyledString) getStyledText(object)).getString();
    }

    /**
     * This returns the label styled text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object getStyledText(Object object) {
        String label = ((AbstractHyperlinkDescription) object).getName();
        StyledString styledLabel = new StyledString();
        if (label == null || label.length() == 0) {
            styledLabel.append(getString("_UI_AbstractHyperlinkDescription_type"), StyledString.Style.QUALIFIER_STYLER); //$NON-NLS-1$
        } else {
            styledLabel.append(getString("_UI_AbstractHyperlinkDescription_type"), StyledString.Style.QUALIFIER_STYLER).append(" " + label); //$NON-NLS-1$ //$NON-NLS-2$
        }
        return styledLabel;
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

        switch (notification.getFeatureID(AbstractHyperlinkDescription.class)) {
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__VALUE_EXPRESSION:
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__DISPLAY_EXPRESSION:
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__EXTENDS:
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_HYPERLINK_EXPRESSION:
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_HYPERLINK_EXPRESSION:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__INITIAL_OPERATION:
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__STYLE:
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__CONDITIONAL_STYLES:
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__ACTIONS:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
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

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.ABSTRACT_HYPERLINK_DESCRIPTION__INITIAL_OPERATION, ToolFactory.eINSTANCE.createInitialOperation()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.ABSTRACT_HYPERLINK_DESCRIPTION__STYLE, PropertiesFactory.eINSTANCE.createHyperlinkWidgetStyle()));

        newChildDescriptors
                .add(createChildParameter(PropertiesPackage.Literals.ABSTRACT_HYPERLINK_DESCRIPTION__CONDITIONAL_STYLES, PropertiesFactory.eINSTANCE.createHyperlinkWidgetConditionalStyle()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.ABSTRACT_HYPERLINK_DESCRIPTION__ACTIONS, PropertiesFactory.eINSTANCE.createWidgetAction()));
    }

}
