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
package org.eclipse.sirius.viewpoint.provider;

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
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DAnalysisSessionEObject;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.viewpoint.DAnalysisSessionEObject} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class DAnalysisSessionEObjectItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider,
IItemPropertySource {
    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public DAnalysisSessionEObjectItemProvider(AdapterFactory adapterFactory) {
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

            addOpenPropertyDescriptor(object);
            addResourcesPropertyDescriptor(object);
            addControlledResourcesPropertyDescriptor(object);
            addActivatedViewpointsPropertyDescriptor(object);
            addAnalysesPropertyDescriptor(object);
            addSynchronizationStatusPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Open feature. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addOpenPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DAnalysisSessionEObject_open_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DAnalysisSessionEObject_open_feature", "_UI_DAnalysisSessionEObject_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                ViewpointPackage.Literals.DANALYSIS_SESSION_EOBJECT__OPEN, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Resources feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addResourcesPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DAnalysisSessionEObject_resources_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DAnalysisSessionEObject_resources_feature", "_UI_DAnalysisSessionEObject_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                ViewpointPackage.Literals.DANALYSIS_SESSION_EOBJECT__RESOURCES, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Controlled Resources feature.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addControlledResourcesPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DAnalysisSessionEObject_controlledResources_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DAnalysisSessionEObject_controlledResources_feature", "_UI_DAnalysisSessionEObject_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                ViewpointPackage.Literals.DANALYSIS_SESSION_EOBJECT__CONTROLLED_RESOURCES, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Activated Viewpoints feature.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addActivatedViewpointsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DAnalysisSessionEObject_activatedViewpoints_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DAnalysisSessionEObject_activatedViewpoints_feature", "_UI_DAnalysisSessionEObject_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                ViewpointPackage.Literals.DANALYSIS_SESSION_EOBJECT__ACTIVATED_VIEWPOINTS, true, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Analyses feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addAnalysesPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DAnalysisSessionEObject_analyses_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DAnalysisSessionEObject_analyses_feature", "_UI_DAnalysisSessionEObject_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                ViewpointPackage.Literals.DANALYSIS_SESSION_EOBJECT__ANALYSES, true, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Synchronization Status feature.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addSynchronizationStatusPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DAnalysisSessionEObject_synchronizationStatus_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DAnalysisSessionEObject_synchronizationStatus_feature", "_UI_DAnalysisSessionEObject_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                ViewpointPackage.Literals.DANALYSIS_SESSION_EOBJECT__SYNCHRONIZATION_STATUS, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This returns DAnalysisSessionEObject.gif. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/DAnalysisSessionEObject")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public String getText(Object object) {
        if (object instanceof Session) {
            return object.toString();
        }

        DAnalysisSessionEObject dAnalysisSessionEObject = (DAnalysisSessionEObject) object;
        return getString("_UI_DAnalysisSessionEObject_type") + " " + dAnalysisSessionEObject.isOpen(); //$NON-NLS-1$ //$NON-NLS-2$
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

        switch (notification.getFeatureID(DAnalysisSessionEObject.class)) {
        case ViewpointPackage.DANALYSIS_SESSION_EOBJECT__OPEN:
        case ViewpointPackage.DANALYSIS_SESSION_EOBJECT__RESOURCES:
        case ViewpointPackage.DANALYSIS_SESSION_EOBJECT__CONTROLLED_RESOURCES:
        case ViewpointPackage.DANALYSIS_SESSION_EOBJECT__SYNCHRONIZATION_STATUS:
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
