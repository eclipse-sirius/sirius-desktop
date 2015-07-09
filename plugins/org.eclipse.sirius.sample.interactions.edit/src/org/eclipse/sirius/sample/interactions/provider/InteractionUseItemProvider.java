/*******************************************************************************
 * Copyright (c) 2010, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.sample.interactions.provider;

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
import org.eclipse.sirius.sample.interactions.InteractionUse;
import org.eclipse.sirius.sample.interactions.InteractionsPackage;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.sample.interactions.InteractionUse} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class InteractionUseItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider,
IItemPropertySource {
    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public InteractionUseItemProvider(AdapterFactory adapterFactory) {
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

            addTypePropertyDescriptor(object);
            addInteractionPropertyDescriptor(object);
            addCoveredParticipantsPropertyDescriptor(object);
            addStartPropertyDescriptor(object);
            addFinishPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Type feature. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addTypePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_InteractionUse_type_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_InteractionUse_type_feature", "_UI_InteractionUse_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                InteractionsPackage.Literals.INTERACTION_USE__TYPE, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Interaction feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addInteractionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_InteractionUse_interaction_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_InteractionUse_interaction_feature", "_UI_InteractionUse_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                InteractionsPackage.Literals.INTERACTION_USE__INTERACTION, true, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Covered Participants feature.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addCoveredParticipantsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_InteractionUse_coveredParticipants_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_InteractionUse_coveredParticipants_feature", "_UI_InteractionUse_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                InteractionsPackage.Literals.INTERACTION_USE__COVERED_PARTICIPANTS, true, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Start feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addStartPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_InteractionUse_start_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_InteractionUse_start_feature", "_UI_InteractionUse_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                InteractionsPackage.Literals.INTERACTION_USE__START, true, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Finish feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addFinishPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_InteractionUse_finish_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_InteractionUse_finish_feature", "_UI_InteractionUse_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                InteractionsPackage.Literals.INTERACTION_USE__FINISH, true, false, true, null, null, null));
    }

    /**
     * This returns InteractionUse.gif. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/InteractionUse")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getText(Object object) {
        String label = ((InteractionUse) object).getType();
        return label == null || label.length() == 0 ? getString("_UI_InteractionUse_type") : //$NON-NLS-1$
            getString("_UI_InteractionUse_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

        switch (notification.getFeatureID(InteractionUse.class)) {
        case InteractionsPackage.INTERACTION_USE__TYPE:
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
        return InteractionsEditPlugin.INSTANCE;
    }

}
