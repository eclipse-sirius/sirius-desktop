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
package org.eclipse.sirius.diagram.description.style.provider;

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
import org.eclipse.sirius.diagram.LineStyle;
import org.eclipse.sirius.diagram.description.style.EdgeStyleDescription;
import org.eclipse.sirius.diagram.description.style.StyleFactory;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;

/**
 * This is the item provider adapter for a {@link org.eclipse.sirius.diagram.description.style.EdgeStyleDescription}
 * object. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class EdgeStyleDescriptionItemProvider extends ItemProviderAdapter
        implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EdgeStyleDescriptionItemProvider(AdapterFactory adapterFactory) {
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

            addStrokeColorPropertyDescriptor(object);
            addLineStylePropertyDescriptor(object);
            addSourceArrowPropertyDescriptor(object);
            addTargetArrowPropertyDescriptor(object);
            addSizeComputationExpressionPropertyDescriptor(object);
            addRoutingStylePropertyDescriptor(object);
            addFoldingStylePropertyDescriptor(object);
            addEndsCenteringPropertyDescriptor(object);
            addCenteredSourceMappingsPropertyDescriptor(object);
            addCenteredTargetMappingsPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Stroke Color feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addStrokeColorPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_EdgeStyleDescription_strokeColor_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_EdgeStyleDescription_strokeColor_feature", "_UI_EdgeStyleDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        StylePackage.Literals.EDGE_STYLE_DESCRIPTION__STROKE_COLOR, true, false, false, null, getString("_UI_ColorPropertyCategory"), //$NON-NLS-1$
                        null));
    }

    /**
     * This adds a property descriptor for the Line Style feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addLineStylePropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_EdgeStyleDescription_lineStyle_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_EdgeStyleDescription_lineStyle_feature", "_UI_EdgeStyleDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        StylePackage.Literals.EDGE_STYLE_DESCRIPTION__LINE_STYLE, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                        null));
    }

    /**
     * This adds a property descriptor for the Source Arrow feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addSourceArrowPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_EdgeStyleDescription_sourceArrow_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_EdgeStyleDescription_sourceArrow_feature", "_UI_EdgeStyleDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        StylePackage.Literals.EDGE_STYLE_DESCRIPTION__SOURCE_ARROW, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_DecoratorsPropertyCategory"), //$NON-NLS-1$
                        null));
    }

    /**
     * This adds a property descriptor for the Target Arrow feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addTargetArrowPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_EdgeStyleDescription_targetArrow_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_EdgeStyleDescription_targetArrow_feature", "_UI_EdgeStyleDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        StylePackage.Literals.EDGE_STYLE_DESCRIPTION__TARGET_ARROW, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_DecoratorsPropertyCategory"), //$NON-NLS-1$
                        null));
    }

    /**
     * This adds a property descriptor for the Size Computation Expression feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    protected void addSizeComputationExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_EdgeStyleDescription_sizeComputationExpression_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_EdgeStyleDescription_sizeComputationExpression_feature", "_UI_EdgeStyleDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                StylePackage.Literals.EDGE_STYLE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_AdvancedPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Routing Style feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addRoutingStylePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
                createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_EdgeStyleDescription_routingStyle_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_EdgeStyleDescription_routingStyle_feature", "_UI_EdgeStyleDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        StylePackage.Literals.EDGE_STYLE_DESCRIPTION__ROUTING_STYLE, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                        null));
    }

    /**
     * This adds a property descriptor for the Folding Style feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addFoldingStylePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
                createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_EdgeStyleDescription_foldingStyle_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_EdgeStyleDescription_foldingStyle_feature", "_UI_EdgeStyleDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        StylePackage.Literals.EDGE_STYLE_DESCRIPTION__FOLDING_STYLE, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_AdvancedPropertyCategory"), //$NON-NLS-1$
                        null));
    }

    /**
     * This adds a property descriptor for the Ends Centering feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addEndsCenteringPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
                createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_EdgeStyleDescription_endsCentering_feature"), //$NON-NLS-1$
                        getString("_UI_EdgeStyleDescription_endsCentering_description"), //$NON-NLS-1$
                        StylePackage.Literals.EDGE_STYLE_DESCRIPTION__ENDS_CENTERING, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Centered Source Mappings feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    protected void addCenteredSourceMappingsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_EdgeStyleDescription_centeredSourceMappings_feature"), //$NON-NLS-1$
                getString("_UI_EdgeStyleDescription_centeredSourceMappings_description"), //$NON-NLS-1$
                StylePackage.Literals.EDGE_STYLE_DESCRIPTION__CENTERED_SOURCE_MAPPINGS, true, false, true, null, getString("_UI_AdvancedPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Centered Target Mappings feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    protected void addCenteredTargetMappingsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_EdgeStyleDescription_centeredTargetMappings_feature"), //$NON-NLS-1$
                getString("_UI_EdgeStyleDescription_centeredTargetMappings_description"), //$NON-NLS-1$
                StylePackage.Literals.EDGE_STYLE_DESCRIPTION__CENTERED_TARGET_MAPPINGS, true, false, true, null, getString("_UI_AdvancedPropertyCategory"), //$NON-NLS-1$
                null));
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
            childrenFeatures.add(StylePackage.Literals.EDGE_STYLE_DESCRIPTION__BEGIN_LABEL_STYLE_DESCRIPTION);
            childrenFeatures.add(StylePackage.Literals.EDGE_STYLE_DESCRIPTION__CENTER_LABEL_STYLE_DESCRIPTION);
            childrenFeatures.add(StylePackage.Literals.EDGE_STYLE_DESCRIPTION__END_LABEL_STYLE_DESCRIPTION);
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
     * This returns EdgeStyleDescription.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/EdgeStyleDescription")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getText(Object object) {
        LineStyle labelValue = ((EdgeStyleDescription) object).getLineStyle();
        String label = labelValue == null ? null : labelValue.toString();
        return label == null || label.length() == 0 ? getString("_UI_EdgeStyleDescription_type") : //$NON-NLS-1$
                getString("_UI_EdgeStyleDescription_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

        switch (notification.getFeatureID(EdgeStyleDescription.class)) {
        case StylePackage.EDGE_STYLE_DESCRIPTION__STROKE_COLOR:
        case StylePackage.EDGE_STYLE_DESCRIPTION__LINE_STYLE:
        case StylePackage.EDGE_STYLE_DESCRIPTION__SOURCE_ARROW:
        case StylePackage.EDGE_STYLE_DESCRIPTION__TARGET_ARROW:
        case StylePackage.EDGE_STYLE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION:
        case StylePackage.EDGE_STYLE_DESCRIPTION__ROUTING_STYLE:
        case StylePackage.EDGE_STYLE_DESCRIPTION__FOLDING_STYLE:
        case StylePackage.EDGE_STYLE_DESCRIPTION__ENDS_CENTERING:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case StylePackage.EDGE_STYLE_DESCRIPTION__BEGIN_LABEL_STYLE_DESCRIPTION:
        case StylePackage.EDGE_STYLE_DESCRIPTION__CENTER_LABEL_STYLE_DESCRIPTION:
        case StylePackage.EDGE_STYLE_DESCRIPTION__END_LABEL_STYLE_DESCRIPTION:
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

        newChildDescriptors.add(createChildParameter(StylePackage.Literals.EDGE_STYLE_DESCRIPTION__BEGIN_LABEL_STYLE_DESCRIPTION, StyleFactory.eINSTANCE.createBeginLabelStyleDescription()));

        newChildDescriptors.add(createChildParameter(StylePackage.Literals.EDGE_STYLE_DESCRIPTION__CENTER_LABEL_STYLE_DESCRIPTION, StyleFactory.eINSTANCE.createCenterLabelStyleDescription()));

        newChildDescriptors.add(createChildParameter(StylePackage.Literals.EDGE_STYLE_DESCRIPTION__END_LABEL_STYLE_DESCRIPTION, StyleFactory.eINSTANCE.createEndLabelStyleDescription()));
    }

    /**
     * Return the resource locator for this item provider's resources. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ResourceLocator getResourceLocator() {
        return DiagramUIPlugin.INSTANCE;
    }

}
