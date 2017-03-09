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
import org.eclipse.sirius.properties.Category;
import org.eclipse.sirius.properties.PropertiesFactory;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.provider.IdentifiedElementItemProvider;

/**
 * This is the item provider adapter for a {@link org.eclipse.sirius.properties.Category} object. <!-- begin-user-doc
 * --> <!-- end-user-doc -->
 *
 * @generated
 */
public class CategoryItemProvider extends IdentifiedElementItemProvider implements IItemStyledLabelProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public CategoryItemProvider(AdapterFactory adapterFactory) {
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

            addDocumentationPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Documentation feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addDocumentationPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_DocumentedElement_documentation_feature"), //$NON-NLS-1$
                        getString("_UI_DocumentedElement_documentation_description"), //$NON-NLS-1$
                        DescriptionPackage.Literals.DOCUMENTED_ELEMENT__DOCUMENTATION, true, true, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_DocumentationPropertyCategory"), //$NON-NLS-1$
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
            childrenFeatures.add(PropertiesPackage.Literals.CATEGORY__PAGES);
            childrenFeatures.add(PropertiesPackage.Literals.CATEGORY__GROUPS);
            childrenFeatures.add(PropertiesPackage.Literals.CATEGORY__OVERRIDES);
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
     * This returns Category.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/Category")); //$NON-NLS-1$
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
        String label = ((Category) object).getName();
        StyledString styledLabel = new StyledString();
        if (label == null || label.length() == 0) {
            styledLabel.append(getString("_UI_Category_type"), StyledString.Style.QUALIFIER_STYLER); //$NON-NLS-1$
        } else {
            styledLabel.append(getString("_UI_Category_type"), StyledString.Style.QUALIFIER_STYLER).append(" " + label); //$NON-NLS-1$ //$NON-NLS-2$
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

        switch (notification.getFeatureID(Category.class)) {
        case PropertiesPackage.CATEGORY__DOCUMENTATION:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case PropertiesPackage.CATEGORY__PAGES:
        case PropertiesPackage.CATEGORY__GROUPS:
        case PropertiesPackage.CATEGORY__OVERRIDES:
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

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.CATEGORY__PAGES, PropertiesFactory.eINSTANCE.createPageDescription()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.CATEGORY__GROUPS, PropertiesFactory.eINSTANCE.createGroupDescription()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.CATEGORY__OVERRIDES, PropertiesFactory.eINSTANCE.createPageOverrideDescription()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.CATEGORY__OVERRIDES, PropertiesFactory.eINSTANCE.createGroupOverrideDescription()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.CATEGORY__OVERRIDES, PropertiesFactory.eINSTANCE.createContainerOverrideDescription()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.CATEGORY__OVERRIDES, PropertiesFactory.eINSTANCE.createTextOverrideDescription()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.CATEGORY__OVERRIDES, PropertiesFactory.eINSTANCE.createButtonOverrideDescription()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.CATEGORY__OVERRIDES, PropertiesFactory.eINSTANCE.createLabelOverrideDescription()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.CATEGORY__OVERRIDES, PropertiesFactory.eINSTANCE.createCheckboxOverrideDescription()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.CATEGORY__OVERRIDES, PropertiesFactory.eINSTANCE.createSelectOverrideDescription()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.CATEGORY__OVERRIDES, PropertiesFactory.eINSTANCE.createDynamicMappingForOverrideDescription()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.CATEGORY__OVERRIDES, PropertiesFactory.eINSTANCE.createDynamicMappingIfOverrideDescription()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.CATEGORY__OVERRIDES, PropertiesFactory.eINSTANCE.createTextAreaOverrideDescription()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.CATEGORY__OVERRIDES, PropertiesFactory.eINSTANCE.createRadioOverrideDescription()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.CATEGORY__OVERRIDES, PropertiesFactory.eINSTANCE.createListOverrideDescription()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.CATEGORY__OVERRIDES, PropertiesFactory.eINSTANCE.createCustomOverrideDescription()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.CATEGORY__OVERRIDES, PropertiesFactory.eINSTANCE.createHyperlinkOverrideDescription()));
    }

}
