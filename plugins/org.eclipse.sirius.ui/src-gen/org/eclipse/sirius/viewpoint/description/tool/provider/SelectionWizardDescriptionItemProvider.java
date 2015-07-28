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
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolFactory;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription}
 * object. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class SelectionWizardDescriptionItemProvider extends AbstractToolDescriptionItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public SelectionWizardDescriptionItemProvider(AdapterFactory adapterFactory) {
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

            addCandidatesExpressionPropertyDescriptor(object);
            addMultiplePropertyDescriptor(object);
            addTreePropertyDescriptor(object);
            addRootExpressionPropertyDescriptor(object);
            addChildrenExpressionPropertyDescriptor(object);
            addMessagePropertyDescriptor(object);
            addIconPathPropertyDescriptor(object);
            addWindowTitlePropertyDescriptor(object);
            addWindowImagePathPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Candidates Expression feature.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addCandidatesExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_SelectionDescription_candidatesExpression_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_SelectionDescription_candidatesExpression_feature", "_UI_SelectionDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DescriptionPackage.Literals.SELECTION_DESCRIPTION__CANDIDATES_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Multiple feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addMultiplePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_SelectionDescription_multiple_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_SelectionDescription_multiple_feature", "_UI_SelectionDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DescriptionPackage.Literals.SELECTION_DESCRIPTION__MULTIPLE, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Tree feature. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addTreePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_SelectionDescription_tree_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_SelectionDescription_tree_feature", "_UI_SelectionDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DescriptionPackage.Literals.SELECTION_DESCRIPTION__TREE, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Root Expression feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addRootExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_SelectionDescription_rootExpression_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_SelectionDescription_rootExpression_feature", "_UI_SelectionDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DescriptionPackage.Literals.SELECTION_DESCRIPTION__ROOT_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Children Expression feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addChildrenExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_SelectionDescription_childrenExpression_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_SelectionDescription_childrenExpression_feature", "_UI_SelectionDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DescriptionPackage.Literals.SELECTION_DESCRIPTION__CHILDREN_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Message feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addMessagePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_SelectionDescription_message_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_SelectionDescription_message_feature", "_UI_SelectionDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DescriptionPackage.Literals.SELECTION_DESCRIPTION__MESSAGE, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
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
                getString("_UI_SelectionWizardDescription_iconPath_feature"), //$NON-NLS-1$
                getString("_UI_SelectionWizardDescription_iconPath_description"), //$NON-NLS-1$
                ToolPackage.Literals.SELECTION_WIZARD_DESCRIPTION__ICON_PATH, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_AdvancedPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Window Title feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addWindowTitlePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_SelectionWizardDescription_windowTitle_feature"), //$NON-NLS-1$
                getString("_UI_SelectionWizardDescription_windowTitle_description"), //$NON-NLS-1$
                ToolPackage.Literals.SELECTION_WIZARD_DESCRIPTION__WINDOW_TITLE, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_AdvancedPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Window Image Path feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addWindowImagePathPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_SelectionWizardDescription_windowImagePath_feature"), //$NON-NLS-1$
                getString("_UI_SelectionWizardDescription_windowImagePath_description"), //$NON-NLS-1$
                ToolPackage.Literals.SELECTION_WIZARD_DESCRIPTION__WINDOW_IMAGE_PATH, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_AdvancedPropertyCategory"), //$NON-NLS-1$
                null));
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
            childrenFeatures.add(ToolPackage.Literals.SELECTION_WIZARD_DESCRIPTION__ELEMENT);
            childrenFeatures.add(ToolPackage.Literals.SELECTION_WIZARD_DESCRIPTION__CONTAINER_VIEW);
            childrenFeatures.add(ToolPackage.Literals.SELECTION_WIZARD_DESCRIPTION__CONTAINER);
            childrenFeatures.add(ToolPackage.Literals.SELECTION_WIZARD_DESCRIPTION__INITIAL_OPERATION);
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
     * This returns SelectionWizardDescription.gif. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/SelectionWizardDescription")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public String getText(Object object) {
        String label = new IdentifiedElementQuery((SelectionWizardDescription) object).getLabel();
        return StringUtil.isEmpty(label) ? getString("_UI_SelectionWizardDescription_type") : getString("_UI_SelectionWizardDescription_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

        switch (notification.getFeatureID(SelectionWizardDescription.class)) {
        case ToolPackage.SELECTION_WIZARD_DESCRIPTION__CANDIDATES_EXPRESSION:
        case ToolPackage.SELECTION_WIZARD_DESCRIPTION__MULTIPLE:
        case ToolPackage.SELECTION_WIZARD_DESCRIPTION__TREE:
        case ToolPackage.SELECTION_WIZARD_DESCRIPTION__ROOT_EXPRESSION:
        case ToolPackage.SELECTION_WIZARD_DESCRIPTION__CHILDREN_EXPRESSION:
        case ToolPackage.SELECTION_WIZARD_DESCRIPTION__MESSAGE:
        case ToolPackage.SELECTION_WIZARD_DESCRIPTION__ICON_PATH:
        case ToolPackage.SELECTION_WIZARD_DESCRIPTION__WINDOW_TITLE:
        case ToolPackage.SELECTION_WIZARD_DESCRIPTION__WINDOW_IMAGE_PATH:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case ToolPackage.SELECTION_WIZARD_DESCRIPTION__ELEMENT:
        case ToolPackage.SELECTION_WIZARD_DESCRIPTION__CONTAINER_VIEW:
        case ToolPackage.SELECTION_WIZARD_DESCRIPTION__CONTAINER:
        case ToolPackage.SELECTION_WIZARD_DESCRIPTION__INITIAL_OPERATION:
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

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.SELECTION_WIZARD_DESCRIPTION__ELEMENT, ToolFactory.eINSTANCE.createElementSelectVariable()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.SELECTION_WIZARD_DESCRIPTION__CONTAINER_VIEW, ToolFactory.eINSTANCE.createContainerViewVariable()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.SELECTION_WIZARD_DESCRIPTION__CONTAINER, ToolFactory.eINSTANCE.createSelectContainerVariable()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.SELECTION_WIZARD_DESCRIPTION__INITIAL_OPERATION, ToolFactory.eINSTANCE.createInitialOperation()));
    }

}
