/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.template.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.diagram.LineStyle;
import org.eclipse.sirius.diagram.sequence.template.TMessageStyle;
import org.eclipse.sirius.diagram.sequence.template.TemplatePackage;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.diagram.sequence.template.TMessageStyle} object.
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class TMessageStyleItemProvider extends TTransformerItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public TMessageStyleItemProvider(AdapterFactory adapterFactory) {
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

            addStrokeColorPropertyDescriptor(object);
            addLineStylePropertyDescriptor(object);
            addSourceArrowPropertyDescriptor(object);
            addTargetArrowPropertyDescriptor(object);
            addLabelExpressionPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Stroke Color feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addStrokeColorPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_TMessageStyle_strokeColor_feature"), //$NON-NLS-1$
                getString("_UI_TMessageStyle_strokeColor_description"), //$NON-NLS-1$
                TemplatePackage.Literals.TMESSAGE_STYLE__STROKE_COLOR, true, false, true, null, getString("_UI_ColorPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Line Style feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addLineStylePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_TMessageStyle_lineStyle_feature"), //$NON-NLS-1$
                getString("_UI_TMessageStyle_lineStyle_description"), //$NON-NLS-1$
                TemplatePackage.Literals.TMESSAGE_STYLE__LINE_STYLE, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Source Arrow feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addSourceArrowPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_TMessageStyle_sourceArrow_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_TMessageStyle_sourceArrow_feature", "_UI_TMessageStyle_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                TemplatePackage.Literals.TMESSAGE_STYLE__SOURCE_ARROW, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Target Arrow feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addTargetArrowPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_TMessageStyle_targetArrow_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_TMessageStyle_targetArrow_feature", "_UI_TMessageStyle_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                TemplatePackage.Literals.TMESSAGE_STYLE__TARGET_ARROW, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Label Expression feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addLabelExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_TMessageStyle_labelExpression_feature"), //$NON-NLS-1$
                getString("_UI_TMessageStyle_labelExpression_description"), //$NON-NLS-1$
                TemplatePackage.Literals.TMESSAGE_STYLE__LABEL_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_LabelPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This returns TMessageStyle.gif. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/TMessageStyle")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public String getText(Object object) {
        LineStyle labelValue = ((TMessageStyle) object).getLineStyle();
        String label = labelValue == null ? null : labelValue.toString();
        return label == null || label.length() == 0 ? getString("_UI_TMessageStyle_type") : label; //$NON-NLS-1$
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

        switch (notification.getFeatureID(TMessageStyle.class)) {
        case TemplatePackage.TMESSAGE_STYLE__LINE_STYLE:
        case TemplatePackage.TMESSAGE_STYLE__SOURCE_ARROW:
        case TemplatePackage.TMESSAGE_STYLE__TARGET_ARROW:
        case TemplatePackage.TMESSAGE_STYLE__LABEL_EXPRESSION:
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
