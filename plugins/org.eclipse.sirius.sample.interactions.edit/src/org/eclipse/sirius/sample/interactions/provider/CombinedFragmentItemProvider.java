/**
 * Copyright (c) 2007, 2025 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 */
package org.eclipse.sirius.sample.interactions.provider;

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
import org.eclipse.sirius.sample.interactions.CombinedFragment;
import org.eclipse.sirius.sample.interactions.InteractionsFactory;
import org.eclipse.sirius.sample.interactions.InteractionsPackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.sirius.sample.interactions.CombinedFragment} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class CombinedFragmentItemProvider extends ItemProviderAdapter
        implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public CombinedFragmentItemProvider(AdapterFactory adapterFactory) {
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

            addOperatorPropertyDescriptor(object);
            addCoveredParticipantsPropertyDescriptor(object);
            addStartPropertyDescriptor(object);
            addFinishPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Operator feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addOperatorPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_CombinedFragment_operator_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_CombinedFragment_operator_feature", "_UI_CombinedFragment_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        InteractionsPackage.Literals.COMBINED_FRAGMENT__OPERATOR, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Covered Participants feature. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    protected void addCoveredParticipantsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
                createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_CombinedFragment_coveredParticipants_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_CombinedFragment_coveredParticipants_feature", "_UI_CombinedFragment_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        InteractionsPackage.Literals.COMBINED_FRAGMENT__COVERED_PARTICIPANTS, true, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Start feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addStartPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_CombinedFragment_start_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_CombinedFragment_start_feature", "_UI_CombinedFragment_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        InteractionsPackage.Literals.COMBINED_FRAGMENT__START, true, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Finish feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addFinishPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_CombinedFragment_finish_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_CombinedFragment_finish_feature", "_UI_CombinedFragment_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        InteractionsPackage.Literals.COMBINED_FRAGMENT__FINISH, true, false, true, null, null, null));
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
            childrenFeatures.add(InteractionsPackage.Literals.COMBINED_FRAGMENT__OWNED_OPERANDS);
            childrenFeatures.add(InteractionsPackage.Literals.COMBINED_FRAGMENT__OWNED_GATES);
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
     * This returns CombinedFragment.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/CombinedFragment")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getText(Object object) {
        String label = ((CombinedFragment) object).getOperator();
        return label == null || label.length() == 0 ? getString("_UI_CombinedFragment_type") : //$NON-NLS-1$
                getString("_UI_CombinedFragment_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

        switch (notification.getFeatureID(CombinedFragment.class)) {
        case InteractionsPackage.COMBINED_FRAGMENT__OPERATOR:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case InteractionsPackage.COMBINED_FRAGMENT__OWNED_OPERANDS:
        case InteractionsPackage.COMBINED_FRAGMENT__OWNED_GATES:
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

        newChildDescriptors.add(createChildParameter(InteractionsPackage.Literals.COMBINED_FRAGMENT__OWNED_OPERANDS, InteractionsFactory.eINSTANCE.createOperand()));

        newChildDescriptors.add(createChildParameter(InteractionsPackage.Literals.COMBINED_FRAGMENT__OWNED_GATES, InteractionsFactory.eINSTANCE.createGate()));
    }

    /**
     * Return the resource locator for this item provider's resources. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ResourceLocator getResourceLocator() {
        return InteractionsEditPlugin.INSTANCE;
    }

}
