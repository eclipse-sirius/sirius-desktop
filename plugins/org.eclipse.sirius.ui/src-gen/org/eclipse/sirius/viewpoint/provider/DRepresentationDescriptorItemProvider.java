/**
 * Copyright (c) 2007, 2020 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.common.tools.api.query.IllegalStateExceptionQuery;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;

/**
 * This is the item provider adapter for a {@link org.eclipse.sirius.viewpoint.DRepresentationDescriptor} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class DRepresentationDescriptorItemProvider extends IdentifiedElementItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public DRepresentationDescriptorItemProvider(AdapterFactory adapterFactory) {
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

            addDocumentationPropertyDescriptor(object);
            addNamePropertyDescriptor(object);
            addDescriptionPropertyDescriptor(object);
            addTargetPropertyDescriptor(object);
            addRepresentationPropertyDescriptor(object);
            addRepPathPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Documentation feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addDocumentationPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_DocumentedElement_documentation_feature"), //$NON-NLS-1$
                        getString("_UI_DocumentedElement_documentation_description"), //$NON-NLS-1$
                        DescriptionPackage.Literals.DOCUMENTED_ELEMENT__DOCUMENTATION, true, true, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_DocumentationPropertyCategory"), //$NON-NLS-1$
                        null));
    }

    /**
     * This adds a property descriptor for the Name feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addNamePropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_DRepresentationDescriptor_name_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_DRepresentationDescriptor_name_feature", "_UI_DRepresentationDescriptor_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        ViewpointPackage.Literals.DREPRESENTATION_DESCRIPTOR__NAME, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Representation feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addRepresentationPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DRepresentationDescriptor_representation_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DRepresentationDescriptor_representation_feature", "_UI_DRepresentationDescriptor_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                ViewpointPackage.Literals.DREPRESENTATION_DESCRIPTOR__REPRESENTATION, false, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Rep Path feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addRepPathPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
                createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_DRepresentationDescriptor_repPath_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_DRepresentationDescriptor_repPath_feature", "_UI_DRepresentationDescriptor_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        ViewpointPackage.Literals.DREPRESENTATION_DESCRIPTOR__REP_PATH, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
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
            childrenFeatures.add(DescriptionPackage.Literals.DMODEL_ELEMENT__EANNOTATIONS);
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
     * This adds a property descriptor for the Description feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addDescriptionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
                createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_DRepresentationDescriptor_description_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_DRepresentationDescriptor_description_feature", "_UI_DRepresentationDescriptor_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        ViewpointPackage.Literals.DREPRESENTATION_DESCRIPTOR__DESCRIPTION, false, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Target feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addTargetPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_DRepresentationDescriptor_target_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_DRepresentationDescriptor_target_feature", "_UI_DRepresentationDescriptor_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        ViewpointPackage.Literals.DREPRESENTATION_DESCRIPTOR__TARGET, true, false, true, null, null, null));
    }

    /**
     * This returns DRepresentationDescriptor.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public Object getImage(Object object) {
        Object image = null;
        try {
            if (object instanceof DRepresentationDescriptor) {
                DRepresentationDescriptor descriptor = (DRepresentationDescriptor) object;
                RepresentationDescription description = descriptor.getDescription();
                if (description != null) {
                    // Retrieve the dialect image.
                    final IItemLabelProvider labelProvider = (IItemLabelProvider) getRootAdapterFactory().adapt(description, IItemLabelProvider.class);
                    if (labelProvider != null) {
                        image = labelProvider.getImage(description);
                    }
                }
            }
        } catch (IllegalStateException e) {
            if (!new IllegalStateExceptionQuery(e).isAConnectionLostException()) {
                throw e;
            }
        }
        if (image == null) {
            image = getResourceLocator().getImage("full/obj16/DRepresentationDescriptor"); //$NON-NLS-1$
        }
        return overlayImage(object, image);
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public String getText(Object object) {
        try {
            String label = ((DRepresentationDescriptor) object).getName();
            if (label == null || label.isEmpty()) {
                EObject objectToAdapt = ((DRepresentationDescriptor) object).getRepresentation();
                if (objectToAdapt == null) {
                    // Retrieve the description name.
                    objectToAdapt = ((DRepresentationDescriptor) object).getDescription();
                }
                if (objectToAdapt != null) {
                    final IItemLabelProvider labelProvider = (IItemLabelProvider) getRootAdapterFactory().adapt(objectToAdapt, IItemLabelProvider.class);
                    if (labelProvider != null) {
                        label = labelProvider.getText(objectToAdapt);
                    }
                }
            }
            return label;
        } catch (IllegalStateException e) {
            if (new IllegalStateExceptionQuery(e).isAConnectionLostException()) {
                return StringUtil.EMPTY_STRING;
            } else {
                throw e;
            }
        }
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

        switch (notification.getFeatureID(DRepresentationDescriptor.class)) {
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__DOCUMENTATION:
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__NAME:
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__REP_PATH:
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__CHANGE_ID:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__EANNOTATIONS:
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

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.DMODEL_ELEMENT__EANNOTATIONS, DescriptionFactory.eINSTANCE.createDAnnotation()));
    }

}
