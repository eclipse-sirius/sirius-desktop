/**
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.diagram.description.style.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.business.api.query.ColorQuery;
import org.eclipse.sirius.diagram.BundledImageShape;
import org.eclipse.sirius.diagram.description.style.BundledImageDescription;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.diagram.internal.queries.BundledImageExtensionQuery;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.diagram.description.style.BundledImageDescription}
 * object. <!-- begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class BundledImageDescriptionItemProvider extends NodeStyleDescriptionItemProvider {

    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public BundledImageDescriptionItemProvider(AdapterFactory adapterFactory) {
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

            addShapePropertyDescriptor(object);
            addColorPropertyDescriptor(object);
            addProvidedShapeIDPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Shape feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addShapePropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_BundledImageDescription_shape_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_BundledImageDescription_shape_feature", "_UI_BundledImageDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        StylePackage.Literals.BUNDLED_IMAGE_DESCRIPTION__SHAPE, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                        null));
    }

    /**
     * This adds a property descriptor for the Color feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addColorPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_BundledImageDescription_color_feature"), //$NON-NLS-1$
                        getString("_UI_BundledImageDescription_color_description"), //$NON-NLS-1$
                        StylePackage.Literals.BUNDLED_IMAGE_DESCRIPTION__COLOR, true, false, false, null, getString("_UI_ColorPropertyCategory"), //$NON-NLS-1$
                        null));
    }

    /**
     * This adds a property descriptor for the Provided Shape ID feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addProvidedShapeIDPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_BundledImageDescription_providedShapeID_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_BundledImageDescription_providedShapeID_feature", "_UI_BundledImageDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                StylePackage.Literals.BUNDLED_IMAGE_DESCRIPTION__PROVIDED_SHAPE_ID, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This returns BundledImageDescription.gif. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/BundledImageDescription")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public String getText(Object object) {
        String color = new ColorQuery(((BundledImageDescription) object).getColor()).getLabel();
        String label = getString("_UI_BundledImageDescription_type"); //$NON-NLS-1$
        String shape = ((BundledImageDescription) object).getShape().getName();

        if (shape.equals(BundledImageShape.PROVIDED_SHAPE_LITERAL.getName()) && object instanceof BundledImageDescription) {
            BundledImageDescription bundledImageDescription = (BundledImageDescription) object;
            String providedShapeID = bundledImageDescription.getProvidedShapeID();
            BundledImageExtensionQuery bundledImageExtensionQuery = new BundledImageExtensionQuery();
            shape = bundledImageExtensionQuery.getExtendedLabelForVSM(providedShapeID);
        }

        if (shape != null && color != null) {
            return label + " " + color + " " + shape; //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            return label;
        }
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

        switch (notification.getFeatureID(BundledImageDescription.class)) {
        case StylePackage.BUNDLED_IMAGE_DESCRIPTION__SHAPE:
        case StylePackage.BUNDLED_IMAGE_DESCRIPTION__COLOR:
        case StylePackage.BUNDLED_IMAGE_DESCRIPTION__PROVIDED_SHAPE_ID:
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
