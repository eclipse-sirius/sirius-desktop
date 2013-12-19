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
package org.eclipse.sirius.diagram.description.provider;

import java.net.URL;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.diagram.description.AdditionalLayer;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.ui.tools.internal.util.EMFCoreUtil;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.diagram.description.AdditionalLayer} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class AdditionalLayerItemProvider extends LayerItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider,
        IItemPropertySource {
    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public AdditionalLayerItemProvider(AdapterFactory adapterFactory) {
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

            addActiveByDefaultPropertyDescriptor(object);
            addOptionalPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Active By Default feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addActiveByDefaultPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_AdditionalLayer_activeByDefault_feature"), getString("_UI_AdditionalLayer_activeByDefault_description"),
                DescriptionPackage.Literals.ADDITIONAL_LAYER__ACTIVE_BY_DEFAULT, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), null));
    }

    /**
     * This adds a property descriptor for the Optional feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addOptionalPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_AdditionalLayer_optional_feature"), getString("_UI_AdditionalLayer_optional_description"), DescriptionPackage.Literals.ADDITIONAL_LAYER__OPTIONAL, true, false, false,
                ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), null));
    }

    /**
     * This returns AdditionalLayer.gif. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @not-generated
     */
    @Override
    public Object getImage(Object object) {
        if (object instanceof EObject) {
            Option<URL> optionImageURL = EMFCoreUtil.getImage((EObject) object);
            if (optionImageURL.some()) {
                return optionImageURL.get();
            }
        }
        return overlayImage(object, getResourceLocator().getImage("full/obj16/AdditionalLayer"));
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    @Override
    public String getText(Object object) {
        String label = new IdentifiedElementQuery((AdditionalLayer) object).getLabel();
        return label == null || label.length() == 0 ? getString("_UI_AdditionalLayer_type") : label;
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

        switch (notification.getFeatureID(AdditionalLayer.class)) {
        case DescriptionPackage.ADDITIONAL_LAYER__ACTIVE_BY_DEFAULT:
        case DescriptionPackage.ADDITIONAL_LAYER__OPTIONAL:
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

}
