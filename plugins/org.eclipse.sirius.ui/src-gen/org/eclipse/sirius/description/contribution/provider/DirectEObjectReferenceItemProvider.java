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
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.description.contribution.ContributionPackage;
import org.eclipse.sirius.description.contribution.DirectEObjectReference;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;

/**
 * This is the item provider adapter for a {@link org.eclipse.sirius.description.contribution.DirectEObjectReference} object.
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * @generated
 */
public class DirectEObjectReferenceItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider,
        IItemPropertySource {
    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DirectEObjectReferenceItemProvider(AdapterFactory adapterFactory) {
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

            addValuePropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Value feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addValuePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_DirectEObjectReference_value_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_DirectEObjectReference_value_feature", "_UI_DirectEObjectReference_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 ContributionPackage.Literals.DIRECT_EOBJECT_REFERENCE__VALUE,
                 true,
                 false,
                 true,
                 null,
                 getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                 null));
    }

    /**
     * This returns DirectEObjectReference.gif.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/DirectEObjectReference")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    @Override
    public String getText(Object object) {
        String result = getString("_UI_DirectEObjectReference_type"); //$NON-NLS-1$
        if (object instanceof DirectEObjectReference) {
            DirectEObjectReference ref = (DirectEObjectReference) object;
            if (ref.eContainer() != null && ref.eContainingFeature() == ContributionPackage.eINSTANCE.getContribution_Source()) {
                result = "Source " + result; //$NON-NLS-1$
            } else if (ref.eContainer() != null && ref.eContainingFeature() == ContributionPackage.eINSTANCE.getContribution_Target()) {
                result = "Target " + result; //$NON-NLS-1$
            }
            if (ref.getValue() == null) {
                result += " [unset]"; //$NON-NLS-1$
            } else {
                AdapterFactory ipaf = SiriusEditPlugin.getPlugin().getItemProvidersAdapterFactory();
                result += " to: " + ((IItemLabelProvider) ipaf.adapt(ref.getValue(), IItemLabelProvider.class)).getText(ref.getValue()); //$NON-NLS-1$
                result += " (" + ref.getValue().eClass().getName() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
            }
        }
        return result;
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

        switch (notification.getFeatureID(DirectEObjectReference.class)) {
            case ContributionPackage.DIRECT_EOBJECT_REFERENCE__VALUE:
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

}
