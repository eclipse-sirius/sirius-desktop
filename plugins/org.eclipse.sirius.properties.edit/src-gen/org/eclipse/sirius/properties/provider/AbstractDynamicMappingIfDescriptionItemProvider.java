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
import org.eclipse.emf.edit.provider.IItemStyledLabelProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.StyledString;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.properties.AbstractDynamicMappingIfDescription;
import org.eclipse.sirius.properties.PropertiesFactory;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.viewpoint.description.provider.IdentifiedElementItemProvider;

/**
 * This is the item provider adapter for a {@link org.eclipse.sirius.properties.AbstractDynamicMappingIfDescription}
 * object. <!-- begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class AbstractDynamicMappingIfDescriptionItemProvider extends IdentifiedElementItemProvider implements IItemStyledLabelProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public AbstractDynamicMappingIfDescriptionItemProvider(AdapterFactory adapterFactory) {
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

            addPredicateExpressionPropertyDescriptor(object);
            addExtendsPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Predicate Expression feature. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    protected void addPredicateExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_AbstractDynamicMappingIfDescription_predicateExpression_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_AbstractDynamicMappingIfDescription_predicateExpression_feature", "_UI_AbstractDynamicMappingIfDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                PropertiesPackage.Literals.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__PREDICATE_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Extends feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addExtendsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_AbstractDynamicMappingIfDescription_extends_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_AbstractDynamicMappingIfDescription_extends_feature", "_UI_AbstractDynamicMappingIfDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                PropertiesPackage.Literals.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__EXTENDS, true, false, true, null, null, null));
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
            childrenFeatures.add(PropertiesPackage.Literals.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__WIDGET);
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
        String label = ((AbstractDynamicMappingIfDescription) object).getName();
        StyledString styledLabel = new StyledString();
        if (label == null || label.length() == 0) {
            styledLabel.append(getString("_UI_AbstractDynamicMappingIfDescription_type"), StyledString.Style.QUALIFIER_STYLER); //$NON-NLS-1$
        } else {
            styledLabel.append(getString("_UI_AbstractDynamicMappingIfDescription_type"), StyledString.Style.QUALIFIER_STYLER).append(" " + label); //$NON-NLS-1$ //$NON-NLS-2$
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

        switch (notification.getFeatureID(AbstractDynamicMappingIfDescription.class)) {
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__PREDICATE_EXPRESSION:
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__EXTENDS:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__WIDGET:
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

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__WIDGET, PropertiesFactory.eINSTANCE.createTextDescription()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__WIDGET, PropertiesFactory.eINSTANCE.createButtonDescription()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__WIDGET, PropertiesFactory.eINSTANCE.createLabelDescription()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__WIDGET, PropertiesFactory.eINSTANCE.createCheckboxDescription()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__WIDGET, PropertiesFactory.eINSTANCE.createSelectDescription()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__WIDGET, PropertiesFactory.eINSTANCE.createTextAreaDescription()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__WIDGET, PropertiesFactory.eINSTANCE.createRadioDescription()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__WIDGET, PropertiesFactory.eINSTANCE.createListDescription()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__WIDGET, PropertiesFactory.eINSTANCE.createCustomDescription()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__WIDGET, PropertiesFactory.eINSTANCE.createHyperlinkDescription()));
    }

}
