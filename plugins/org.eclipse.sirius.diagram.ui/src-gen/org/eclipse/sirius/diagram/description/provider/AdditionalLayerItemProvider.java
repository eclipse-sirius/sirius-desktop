/**
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.description.provider;

import java.net.URL;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemFontProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemStyledLabelProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.StyledString;
import org.eclipse.emf.edit.provider.StyledString.Style;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.diagram.description.AdditionalLayer;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.model.business.internal.helper.LayerModelHelper;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ui.tools.internal.util.EMFCoreUtil;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;

/**
 * This is the item provider adapter for a {@link org.eclipse.sirius.diagram.description.AdditionalLayer} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated NOT
 */
public class AdditionalLayerItemProvider extends LayerItemProvider implements IItemStyledLabelProvider {

    /**
     * The style used for transient layer.
     */
    public static final Style TRANSIENT_LAYER_STYLE = Style.newBuilder().setFont(IItemFontProvider.ITALIC_FONT).toStyle(); // $NON-NLS-1$

    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public AdditionalLayerItemProvider(AdapterFactory adapterFactory) {
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

            addActiveByDefaultPropertyDescriptor(object);
            addOptionalPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Active By Default feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addActiveByDefaultPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_AdditionalLayer_activeByDefault_feature"), //$NON-NLS-1$
                        getString("_UI_AdditionalLayer_activeByDefault_description"), //$NON-NLS-1$
                        DescriptionPackage.Literals.ADDITIONAL_LAYER__ACTIVE_BY_DEFAULT, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                        null));
    }

    /**
     * This adds a property descriptor for the Optional feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addOptionalPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_AdditionalLayer_optional_feature"), //$NON-NLS-1$
                        getString("_UI_AdditionalLayer_optional_description"), //$NON-NLS-1$
                        DescriptionPackage.Literals.ADDITIONAL_LAYER__OPTIONAL, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                        null));
    }

    /**
     * This returns AdditionalLayer.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
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
        return overlayImage(object, getResourceLocator().getImage("full/obj16/AdditionalLayer")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public String getText(Object object) {
        String label = new IdentifiedElementQuery((IdentifiedElement) object).getLabel();
        return label == null || label.length() == 0 ? getString("_UI_AdditionalLayer_type") : label; //$NON-NLS-1$
    }

    /**
     * @generated NOT
     */
    @Override
    public Object getStyledText(Object object) {
        StyledString styledString = new StyledString(getText(object));
        if (LayerModelHelper.isTransientLayer((Layer) object)) {
            styledString.append(MessageFormat.format(" ({0})", getString("AdditionalLayerItemProvider_transientLayer")), AdditionalLayerItemProvider.TRANSIENT_LAYER_STYLE); //$NON-NLS-1$ //$NON-NLS-2$
        }
        return styledString;

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

        switch (notification.getFeatureID(AdditionalLayer.class)) {
        case DescriptionPackage.ADDITIONAL_LAYER__ACTIVE_BY_DEFAULT:
        case DescriptionPackage.ADDITIONAL_LAYER__OPTIONAL:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
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
    }

}
