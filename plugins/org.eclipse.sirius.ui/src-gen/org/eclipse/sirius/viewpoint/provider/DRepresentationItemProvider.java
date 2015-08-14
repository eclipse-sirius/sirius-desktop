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
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.provider.DocumentedElementItemProvider;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.viewpoint.DRepresentation} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class DRepresentationItemProvider extends DocumentedElementItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public DRepresentationItemProvider(AdapterFactory adapterFactory) {
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

            addOwnedRepresentationElementsPropertyDescriptor(object);
            addRepresentationElementsPropertyDescriptor(object);
            addNamePropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Owned Representation Elements
     * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addOwnedRepresentationElementsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DRepresentation_ownedRepresentationElements_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DRepresentation_ownedRepresentationElements_feature", "_UI_DRepresentation_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                ViewpointPackage.Literals.DREPRESENTATION__OWNED_REPRESENTATION_ELEMENTS, false, false, false, null, null, null));
    }

    /**
     * This adds a property descriptor for the Representation Elements feature.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addRepresentationElementsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DRepresentation_representationElements_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DRepresentation_representationElements_feature", "_UI_DRepresentation_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                ViewpointPackage.Literals.DREPRESENTATION__REPRESENTATION_ELEMENTS, false, false, false, null, null, null));
    }

    /**
     * This adds a property descriptor for the Name feature. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addNamePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DRepresentation_name_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DRepresentation_name_feature", "_UI_DRepresentation_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                ViewpointPackage.Literals.DREPRESENTATION__NAME, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
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
            childrenFeatures.add(DescriptionPackage.Literals.DMODEL_ELEMENT__EANNOTATIONS);
            childrenFeatures.add(ViewpointPackage.Literals.DREPRESENTATION__OWNED_ANNOTATION_ENTRIES);
            childrenFeatures.add(ViewpointPackage.Literals.DREPRESENTATION__UI_STATE);
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
     * @generated
     */
    @Override
    public String getText(Object object) {
        String label = ((DRepresentation) object).getName();
        return label == null || label.length() == 0 ? getString("_UI_DRepresentation_type") : //$NON-NLS-1$
            getString("_UI_DRepresentation_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

        switch (notification.getFeatureID(DRepresentation.class)) {
        case ViewpointPackage.DREPRESENTATION__NAME:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case ViewpointPackage.DREPRESENTATION__EANNOTATIONS:
        case ViewpointPackage.DREPRESENTATION__OWNED_ANNOTATION_ENTRIES:
        case ViewpointPackage.DREPRESENTATION__UI_STATE:
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

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.DMODEL_ELEMENT__EANNOTATIONS, DescriptionFactory.eINSTANCE.createDAnnotation()));

        newChildDescriptors.add(createChildParameter(ViewpointPackage.Literals.DREPRESENTATION__OWNED_ANNOTATION_ENTRIES, DescriptionFactory.eINSTANCE.createAnnotationEntry()));

        newChildDescriptors.add(createChildParameter(ViewpointPackage.Literals.DREPRESENTATION__UI_STATE, ViewpointFactory.eINSTANCE.createUIState()));
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
