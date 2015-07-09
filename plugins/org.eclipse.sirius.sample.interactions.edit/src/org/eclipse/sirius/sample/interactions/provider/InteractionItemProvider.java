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
import org.eclipse.sirius.sample.interactions.Interaction;
import org.eclipse.sirius.sample.interactions.InteractionsFactory;
import org.eclipse.sirius.sample.interactions.InteractionsPackage;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.sample.interactions.Interaction} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class InteractionItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider,
IItemPropertySource {
    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public InteractionItemProvider(AdapterFactory adapterFactory) {
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

            addNamePropertyDescriptor(object);
            addMessagesPropertyDescriptor(object);
            addEndsPropertyDescriptor(object);
            addConstraintsPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Name feature. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addNamePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_Interaction_name_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_Interaction_name_feature", "_UI_Interaction_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                InteractionsPackage.Literals.INTERACTION__NAME, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Messages feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addMessagesPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_Interaction_messages_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_Interaction_messages_feature", "_UI_Interaction_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                InteractionsPackage.Literals.INTERACTION__MESSAGES, true, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Ends feature. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addEndsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_Interaction_ends_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_Interaction_ends_feature", "_UI_Interaction_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                InteractionsPackage.Literals.INTERACTION__ENDS, true, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Constraints feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addConstraintsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_Interaction_constraints_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_Interaction_constraints_feature", "_UI_Interaction_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                InteractionsPackage.Literals.INTERACTION__CONSTRAINTS, true, false, true, null, null, null));
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
            childrenFeatures.add(InteractionsPackage.Literals.INTERACTION__PARTICIPANTS);
            childrenFeatures.add(InteractionsPackage.Literals.INTERACTION__MESSAGES);
            childrenFeatures.add(InteractionsPackage.Literals.INTERACTION__EXECUTIONS);
            childrenFeatures.add(InteractionsPackage.Literals.INTERACTION__STATES);
            childrenFeatures.add(InteractionsPackage.Literals.INTERACTION__INTERACTION_USES);
            childrenFeatures.add(InteractionsPackage.Literals.INTERACTION__COMBINED_FRAGMENTS);
            childrenFeatures.add(InteractionsPackage.Literals.INTERACTION__ENDS);
            childrenFeatures.add(InteractionsPackage.Literals.INTERACTION__CONSTRAINTS);
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
     * This returns Interaction.gif. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/Interaction")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getText(Object object) {
        String label = ((Interaction) object).getName();
        return label == null || label.length() == 0 ? getString("_UI_Interaction_type") : //$NON-NLS-1$
            getString("_UI_Interaction_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

        switch (notification.getFeatureID(Interaction.class)) {
        case InteractionsPackage.INTERACTION__NAME:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case InteractionsPackage.INTERACTION__PARTICIPANTS:
        case InteractionsPackage.INTERACTION__MESSAGES:
        case InteractionsPackage.INTERACTION__EXECUTIONS:
        case InteractionsPackage.INTERACTION__STATES:
        case InteractionsPackage.INTERACTION__INTERACTION_USES:
        case InteractionsPackage.INTERACTION__COMBINED_FRAGMENTS:
        case InteractionsPackage.INTERACTION__ENDS:
        case InteractionsPackage.INTERACTION__CONSTRAINTS:
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

        newChildDescriptors.add(createChildParameter(InteractionsPackage.Literals.INTERACTION__PARTICIPANTS, InteractionsFactory.eINSTANCE.createParticipant()));

        newChildDescriptors.add(createChildParameter(InteractionsPackage.Literals.INTERACTION__MESSAGES, InteractionsFactory.eINSTANCE.createCallMessage()));

        newChildDescriptors.add(createChildParameter(InteractionsPackage.Literals.INTERACTION__MESSAGES, InteractionsFactory.eINSTANCE.createFeatureAccessMessage()));

        newChildDescriptors.add(createChildParameter(InteractionsPackage.Literals.INTERACTION__MESSAGES, InteractionsFactory.eINSTANCE.createCreateParticipantMessage()));

        newChildDescriptors.add(createChildParameter(InteractionsPackage.Literals.INTERACTION__MESSAGES, InteractionsFactory.eINSTANCE.createDestroyParticipantMessage()));

        newChildDescriptors.add(createChildParameter(InteractionsPackage.Literals.INTERACTION__MESSAGES, InteractionsFactory.eINSTANCE.createReturnMessage()));

        newChildDescriptors.add(createChildParameter(InteractionsPackage.Literals.INTERACTION__EXECUTIONS, InteractionsFactory.eINSTANCE.createExecution()));

        newChildDescriptors.add(createChildParameter(InteractionsPackage.Literals.INTERACTION__STATES, InteractionsFactory.eINSTANCE.createState()));

        newChildDescriptors.add(createChildParameter(InteractionsPackage.Literals.INTERACTION__INTERACTION_USES, InteractionsFactory.eINSTANCE.createInteractionUse()));

        newChildDescriptors.add(createChildParameter(InteractionsPackage.Literals.INTERACTION__COMBINED_FRAGMENTS, InteractionsFactory.eINSTANCE.createCombinedFragment()));

        newChildDescriptors.add(createChildParameter(InteractionsPackage.Literals.INTERACTION__ENDS, InteractionsFactory.eINSTANCE.createMessageEnd()));

        newChildDescriptors.add(createChildParameter(InteractionsPackage.Literals.INTERACTION__ENDS, InteractionsFactory.eINSTANCE.createExecutionEnd()));

        newChildDescriptors.add(createChildParameter(InteractionsPackage.Literals.INTERACTION__ENDS, InteractionsFactory.eINSTANCE.createStateEnd()));

        newChildDescriptors.add(createChildParameter(InteractionsPackage.Literals.INTERACTION__ENDS, InteractionsFactory.eINSTANCE.createInteractionUseEnd()));

        newChildDescriptors.add(createChildParameter(InteractionsPackage.Literals.INTERACTION__ENDS, InteractionsFactory.eINSTANCE.createCombinedFragmentEnd()));

        newChildDescriptors.add(createChildParameter(InteractionsPackage.Literals.INTERACTION__ENDS, InteractionsFactory.eINSTANCE.createOperandEnd()));

        newChildDescriptors.add(createChildParameter(InteractionsPackage.Literals.INTERACTION__ENDS, InteractionsFactory.eINSTANCE.createMixEnd()));

        newChildDescriptors.add(createChildParameter(InteractionsPackage.Literals.INTERACTION__CONSTRAINTS, InteractionsFactory.eINSTANCE.createConstraint()));
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
