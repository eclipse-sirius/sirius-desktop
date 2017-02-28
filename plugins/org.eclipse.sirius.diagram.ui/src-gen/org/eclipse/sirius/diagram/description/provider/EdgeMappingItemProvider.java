/**
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
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

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.diagram.description.ConditionalEdgeStyleDescription;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.style.BracketEdgeStyleDescription;
import org.eclipse.sirius.diagram.description.style.EdgeStyleDescription;
import org.eclipse.sirius.diagram.description.style.StyleFactory;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;

/**
 * This is the item provider adapter for a {@link org.eclipse.sirius.diagram.description.EdgeMapping} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class EdgeMappingItemProvider extends DiagramElementMappingItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EdgeMappingItemProvider(AdapterFactory adapterFactory) {
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
            addSourceMappingPropertyDescriptor(object);
            addTargetMappingPropertyDescriptor(object);
            addTargetFinderExpressionPropertyDescriptor(object);
            addSourceFinderExpressionPropertyDescriptor(object);
            addTargetExpressionPropertyDescriptor(object);
            addDomainClassPropertyDescriptor(object);
            addUseDomainElementPropertyDescriptor(object);
            addReconnectionsPropertyDescriptor(object);
            addPathExpressionPropertyDescriptor(object);
            addPathNodeMappingPropertyDescriptor(object);
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
     * This adds a property descriptor for the Source Mapping feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addSourceMappingPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_EdgeMapping_sourceMapping_feature"), //$NON-NLS-1$
                        getString("_UI_EdgeMapping_sourceMapping_description"), //$NON-NLS-1$
                        org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.EDGE_MAPPING__SOURCE_MAPPING, true, false, true, null, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                        null));
    }

    /**
     * This adds a property descriptor for the Target Mapping feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addTargetMappingPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_EdgeMapping_targetMapping_feature"), //$NON-NLS-1$
                        getString("_UI_EdgeMapping_targetMapping_description"), //$NON-NLS-1$
                        org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.EDGE_MAPPING__TARGET_MAPPING, true, false, true, null, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                        null));
    }

    /**
     * This adds a property descriptor for the Target Finder Expression feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    protected void addTargetFinderExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
                createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_EdgeMapping_targetFinderExpression_feature"), //$NON-NLS-1$
                        getString("_UI_EdgeMapping_targetFinderExpression_description"), //$NON-NLS-1$
                        org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.EDGE_MAPPING__TARGET_FINDER_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                        getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                        null));
    }

    /**
     * This adds a property descriptor for the Source Finder Expression feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    protected void addSourceFinderExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
                createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_EdgeMapping_sourceFinderExpression_feature"), //$NON-NLS-1$
                        getString("_UI_EdgeMapping_sourceFinderExpression_description"), //$NON-NLS-1$
                        org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.EDGE_MAPPING__SOURCE_FINDER_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                        getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                        null));
    }

    /**
     * This adds a property descriptor for the Target Expression feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addTargetExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_EdgeMapping_targetExpression_feature"), //$NON-NLS-1$
                        getString("_UI_EdgeMapping_targetExpression_description"), //$NON-NLS-1$
                        org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.EDGE_MAPPING__TARGET_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                        getString("_UI_AdvancedPropertyCategory"), //$NON-NLS-1$
                        null));
    }

    /**
     * This adds a property descriptor for the Domain Class feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addDomainClassPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_EdgeMapping_domainClass_feature"), //$NON-NLS-1$
                        getString("_UI_EdgeMapping_domainClass_description"), //$NON-NLS-1$
                        org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.EDGE_MAPPING__DOMAIN_CLASS, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                        getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                        null));
    }

    /**
     * This adds a property descriptor for the Use Domain Element feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addUseDomainElementPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_EdgeMapping_useDomainElement_feature"), //$NON-NLS-1$
                getString("_UI_EdgeMapping_useDomainElement_description"), //$NON-NLS-1$
                org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.EDGE_MAPPING__USE_DOMAIN_ELEMENT, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Reconnections feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addReconnectionsPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_EdgeMapping_reconnections_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_EdgeMapping_reconnections_feature", "_UI_EdgeMapping_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.EDGE_MAPPING__RECONNECTIONS, true, false, true, null, getString("_UI_BehaviorPropertyCategory"), //$NON-NLS-1$
                        null));
    }

    /**
     * This adds a property descriptor for the Path Expression feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addPathExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_EdgeMapping_pathExpression_feature"), //$NON-NLS-1$
                        getString("_UI_EdgeMapping_pathExpression_description"), //$NON-NLS-1$
                        org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.EDGE_MAPPING__PATH_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                        getString("_UI_PathPropertyCategory"), //$NON-NLS-1$
                        null));
    }

    /**
     * This adds a property descriptor for the Path Node Mapping feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addPathNodeMappingPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_EdgeMapping_pathNodeMapping_feature"), //$NON-NLS-1$
                        getString("_UI_EdgeMapping_pathNodeMapping_description"), //$NON-NLS-1$
                        org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.EDGE_MAPPING__PATH_NODE_MAPPING, true, false, true, null, getString("_UI_PathPropertyCategory"), //$NON-NLS-1$
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
            childrenFeatures.add(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.EDGE_MAPPING__STYLE);
            childrenFeatures.add(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.EDGE_MAPPING__CONDITIONNAL_STYLES);
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
     * This returns EdgeMapping.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/EdgeMapping")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public String getText(Object object) {
        String label = new IdentifiedElementQuery((IdentifiedElement) object).getLabel();
        if ((label == null || label.length() == 0)) {
            if (((EdgeMapping) object).isUseDomainElement()) {
                label = Messages.ItemProvider_elementBasedEdge;
            } else {
                label = Messages.ItemProvider_relationBasedEdge;
            }
        }
        return label == null || label.length() == 0 ? getString("_UI_EdgeMapping_type") : label; //$NON-NLS-1$
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

        switch (notification.getFeatureID(EdgeMapping.class)) {
        case org.eclipse.sirius.diagram.description.DescriptionPackage.EDGE_MAPPING__DOCUMENTATION:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.EDGE_MAPPING__TARGET_FINDER_EXPRESSION:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.EDGE_MAPPING__SOURCE_FINDER_EXPRESSION:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.EDGE_MAPPING__TARGET_EXPRESSION:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.EDGE_MAPPING__DOMAIN_CLASS:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.EDGE_MAPPING__USE_DOMAIN_ELEMENT:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.EDGE_MAPPING__PATH_EXPRESSION:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case org.eclipse.sirius.diagram.description.DescriptionPackage.EDGE_MAPPING__STYLE:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.EDGE_MAPPING__CONDITIONNAL_STYLES:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
            return;
        }
        super.notifyChanged(notification);
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children that can be created
     * under this object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated: directly create center label.
     */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);

        EdgeStyleDescription esd = StyleFactory.eINSTANCE.createEdgeStyleDescription();
        edgeStyleInit(esd);
        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.EDGE_MAPPING__STYLE, esd));

        BracketEdgeStyleDescription bsd = StyleFactory.eINSTANCE.createBracketEdgeStyleDescription();
        edgeStyleInit(bsd);
        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.EDGE_MAPPING__STYLE, bsd));

        ConditionalEdgeStyleDescription conditionalEdgeStyle = DescriptionFactory.eINSTANCE.createConditionalEdgeStyleDescription();
        EdgeStyleDescription edgeStyle = ((EdgeMapping) object).getStyle();
        EdgeStyleDescription newStyle;
        // if the style of the EdgeMapping is a BracketEdgeStyle, then the style
        // for the conditional style will be a BracketEdgeStyle, otherwise it
        // will be a EdgeStyle
        if (edgeStyle != null && edgeStyle.eClass() == StylePackage.eINSTANCE.getBracketEdgeStyleDescription()) {
            newStyle = StyleFactory.eINSTANCE.createBracketEdgeStyleDescription();
        } else {
            newStyle = StyleFactory.eINSTANCE.createEdgeStyleDescription();
        }
        edgeStyleInit(newStyle);
        conditionalEdgeStyle.setStyle(newStyle);
        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.EDGE_MAPPING__CONDITIONNAL_STYLES, conditionalEdgeStyle));
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children that can be created
     * under this object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void collectNewChildDescriptorsGen(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);

        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.EDGE_MAPPING__STYLE, StyleFactory.eINSTANCE.createEdgeStyleDescription()));

        newChildDescriptors
                .add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.EDGE_MAPPING__STYLE, StyleFactory.eINSTANCE.createBracketEdgeStyleDescription()));

        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.EDGE_MAPPING__CONDITIONNAL_STYLES,
                DescriptionFactory.eINSTANCE.createConditionalEdgeStyleDescription()));
    }

    /**
     * Add a center label style and set the size computation expression of the given edge style.
     *
     * @param edgeStyle
     *            the edge style.
     */
    private void edgeStyleInit(EdgeStyleDescription edgeStyle) {
        edgeStyle.setSizeComputationExpression("2"); //$NON-NLS-1$
        edgeStyle.setCenterLabelStyleDescription(StyleFactory.eINSTANCE.createCenterLabelStyleDescription());
    }

}
