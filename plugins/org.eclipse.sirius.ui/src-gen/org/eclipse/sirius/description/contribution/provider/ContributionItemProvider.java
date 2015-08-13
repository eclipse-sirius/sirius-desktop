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
package org.eclipse.sirius.description.contribution.provider;

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
import org.eclipse.sirius.description.contribution.Contribution;
import org.eclipse.sirius.description.contribution.ContributionFactory;
import org.eclipse.sirius.description.contribution.ContributionPackage;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.description.contribution.Contribution} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class ContributionItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider,
        IItemPropertySource {
    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ContributionItemProvider(AdapterFactory adapterFactory) {
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

            addDescriptionPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Description feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addDescriptionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Contribution_description_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_Contribution_description_feature", "_UI_Contribution_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 ContributionPackage.Literals.CONTRIBUTION__DESCRIPTION,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/Contribution")); //$NON-NLS-1$
    }

    /**
     * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
     * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
     * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
        if (childrenFeatures == null) {
            super.getChildrenFeatures(object);
            childrenFeatures.add(ContributionPackage.Literals.CONTRIBUTION__SOURCE);
            childrenFeatures.add(ContributionPackage.Literals.CONTRIBUTION__TARGET);
            childrenFeatures.add(ContributionPackage.Literals.CONTRIBUTION__FEATURE_MASK);
            childrenFeatures.add(ContributionPackage.Literals.CONTRIBUTION__SUB_CONTRIBUTIONS);
        }
        return childrenFeatures;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EStructuralFeature getChildFeature(Object object, Object child) {
        // Check the type of the specified child object and return the proper feature to use for
        // adding (see {@link AddCommand}) it as a child.

        return super.getChildFeature(object, child);
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getText(Object object) {
        String label = ((Contribution)object).getDescription();
        return label == null || label.length() == 0 ?
            getString("_UI_Contribution_type") : //$NON-NLS-1$
            getString("_UI_Contribution_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * This handles model notifications by calling {@link #updateChildren} to update any cached
     * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     */
    @Override
    public void notifyChanged(Notification notification) {
        updateChildren(notification);

        switch (notification.getFeatureID(Contribution.class)) {
            case ContributionPackage.CONTRIBUTION__DESCRIPTION:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
            case ContributionPackage.CONTRIBUTION__SOURCE:
            case ContributionPackage.CONTRIBUTION__TARGET:
            case ContributionPackage.CONTRIBUTION__FEATURE_MASK:
            case ContributionPackage.CONTRIBUTION__SUB_CONTRIBUTIONS:
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

        newChildDescriptors.add
            (createChildParameter
                (ContributionPackage.Literals.CONTRIBUTION__SOURCE,
                 ContributionFactory.eINSTANCE.createDirectEObjectReference()));

        newChildDescriptors.add
            (createChildParameter
                (ContributionPackage.Literals.CONTRIBUTION__SOURCE,
                 ContributionFactory.eINSTANCE.createComputedEObjectReference()));

        newChildDescriptors.add
            (createChildParameter
                (ContributionPackage.Literals.CONTRIBUTION__TARGET,
                 ContributionFactory.eINSTANCE.createDirectEObjectReference()));

        newChildDescriptors.add
            (createChildParameter
                (ContributionPackage.Literals.CONTRIBUTION__TARGET,
                 ContributionFactory.eINSTANCE.createComputedEObjectReference()));

        newChildDescriptors.add
            (createChildParameter
                (ContributionPackage.Literals.CONTRIBUTION__FEATURE_MASK,
                 ContributionFactory.eINSTANCE.createIgnoreFeatureContribution()));

        newChildDescriptors.add
            (createChildParameter
                (ContributionPackage.Literals.CONTRIBUTION__FEATURE_MASK,
                 ContributionFactory.eINSTANCE.createSetFeatureContribution()));

        newChildDescriptors.add
            (createChildParameter
                (ContributionPackage.Literals.CONTRIBUTION__FEATURE_MASK,
                 ContributionFactory.eINSTANCE.createAddFeatureContribution()));

        newChildDescriptors.add
            (createChildParameter
                (ContributionPackage.Literals.CONTRIBUTION__FEATURE_MASK,
                 ContributionFactory.eINSTANCE.createRemoveFeatureContribution()));

        newChildDescriptors.add
            (createChildParameter
                (ContributionPackage.Literals.CONTRIBUTION__FEATURE_MASK,
                 ContributionFactory.eINSTANCE.createClearFeatureContribution()));

        newChildDescriptors.add
            (createChildParameter
                (ContributionPackage.Literals.CONTRIBUTION__FEATURE_MASK,
                 ContributionFactory.eINSTANCE.createResetFeatureContribution()));

        newChildDescriptors.add
            (createChildParameter
                (ContributionPackage.Literals.CONTRIBUTION__SUB_CONTRIBUTIONS,
                 ContributionFactory.eINSTANCE.createContribution()));
    }

    /**
     * This returns the label text for
     * {@link org.eclipse.emf.edit.command.CreateChildCommand}. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
        Object childFeature = feature;
        Object childObject = child;

        boolean qualify =
            childFeature == ContributionPackage.Literals.CONTRIBUTION__SOURCE ||
            childFeature == ContributionPackage.Literals.CONTRIBUTION__TARGET;

        if (qualify) {
            return getString
                ("_UI_CreateChild_text2", //$NON-NLS-1$
                 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
        }
        return super.getCreateChildText(owner, feature, child, selection);
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

}
