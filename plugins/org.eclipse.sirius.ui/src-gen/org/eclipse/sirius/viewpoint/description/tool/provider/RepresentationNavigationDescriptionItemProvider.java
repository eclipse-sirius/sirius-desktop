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
package org.eclipse.sirius.viewpoint.description.tool.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolFactory;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription}
 * object. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class RepresentationNavigationDescriptionItemProvider extends AbstractToolDescriptionItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public RepresentationNavigationDescriptionItemProvider(AdapterFactory adapterFactory) {
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

            addBrowseExpressionPropertyDescriptor(object);
            addNavigationNameExpressionPropertyDescriptor(object);
            addRepresentationDescriptionPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Browse Expression feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addBrowseExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_RepresentationNavigationDescription_browseExpression_feature"), //$NON-NLS-1$
                getString("_UI_RepresentationNavigationDescription_browseExpression_description"), //$NON-NLS-1$
                ToolPackage.Literals.REPRESENTATION_NAVIGATION_DESCRIPTION__BROWSE_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                getString("_UI_AdvancedPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Navigation Name Expression
     * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addNavigationNameExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_RepresentationNavigationDescription_navigationNameExpression_feature"), //$NON-NLS-1$
                getString("_UI_RepresentationNavigationDescription_navigationNameExpression_description"), //$NON-NLS-1$
                ToolPackage.Literals.REPRESENTATION_NAVIGATION_DESCRIPTION__NAVIGATION_NAME_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                getString("_UI_AdvancedPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Representation Description
     * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addRepresentationDescriptionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_RepresentationNavigationDescription_representationDescription_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_RepresentationNavigationDescription_representationDescription_feature", "_UI_RepresentationNavigationDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                ToolPackage.Literals.REPRESENTATION_NAVIGATION_DESCRIPTION__REPRESENTATION_DESCRIPTION, false, false, false, null, null, null));
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
            childrenFeatures.add(ToolPackage.Literals.REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE);
            childrenFeatures.add(ToolPackage.Literals.REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VARIABLE);
            childrenFeatures.add(ToolPackage.Literals.REPRESENTATION_NAVIGATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE);
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
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public String getText(Object object) {
        String label = new IdentifiedElementQuery((RepresentationNavigationDescription) object).getLabel();
        return StringUtil.isEmpty(label) ? getString("_UI_RepresentationNavigationDescription_type") : getString("_UI_RepresentationNavigationDescription_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

        switch (notification.getFeatureID(RepresentationNavigationDescription.class)) {
        case ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__BROWSE_EXPRESSION:
        case ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__NAVIGATION_NAME_EXPRESSION:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE:
        case ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VARIABLE:
        case ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE:
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

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE, ToolFactory.eINSTANCE.createContainerViewVariable()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VARIABLE, ToolFactory.eINSTANCE.createElementSelectVariable()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.REPRESENTATION_NAVIGATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE, ToolFactory.eINSTANCE.createNameVariable()));
    }

}
