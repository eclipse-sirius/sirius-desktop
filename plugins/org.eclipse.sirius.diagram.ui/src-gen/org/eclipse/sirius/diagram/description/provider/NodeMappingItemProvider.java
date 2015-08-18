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

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.diagram.LabelPosition;
import org.eclipse.sirius.diagram.ResizeKind;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.style.NodeStyleDescription;
import org.eclipse.sirius.diagram.description.style.StyleFactory;
import org.eclipse.sirius.diagram.description.style.WorkspaceImageDescription;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.diagram.description.NodeMapping} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class NodeMappingItemProvider extends AbstractNodeMappingItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NodeMappingItemProvider(AdapterFactory adapterFactory) {
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

            addDropDescriptionsPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Drop Descriptions feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addDropDescriptionsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DragAndDropTargetDescription_dropDescriptions_feature"), //$NON-NLS-1$
                getString("_UI_DragAndDropTargetDescription_dropDescriptions_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.DRAG_AND_DROP_TARGET_DESCRIPTION__DROP_DESCRIPTIONS, true, false, true, null, getString("_UI_BehaviorPropertyCategory"), //$NON-NLS-1$
                null));
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
            childrenFeatures.add(DescriptionPackage.Literals.NODE_MAPPING__STYLE);
            childrenFeatures.add(DescriptionPackage.Literals.NODE_MAPPING__CONDITIONNAL_STYLES);
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
     * This returns NodeMapping.gif. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @not-generated
     */
    @Override
    public Object getImage(Object object) {
        EStructuralFeature eContainingFeature = ((EObject) object).eContainingFeature();
        if (eContainingFeature != null && eContainingFeature.getFeatureID() == org.eclipse.sirius.diagram.description.DescriptionPackage.ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS) {
            return overlayImage(object, getResourceLocator().getImage("obj16/BorderedNodeMapping")); //$NON-NLS-1$
        }
        return overlayImage(object, getResourceLocator().getImage("full/obj16/NodeMapping")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public String getText(Object object) {
        String label = new IdentifiedElementQuery((NodeMapping) object).getLabel();
        EStructuralFeature eContainingFeature = ((EObject) object).eContainingFeature();
        if (eContainingFeature != null && eContainingFeature.getFeatureID() == org.eclipse.sirius.diagram.description.DescriptionPackage.ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS) {
            return "Bordered " + label;
        }
        return label == null || label.length() == 0 ? getString("_UI_NodeMapping_type") : label; //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getTextGen(Object object) {
        String label = ((NodeMapping) object).getName();
        return label == null || label.length() == 0 ? getString("_UI_NodeMapping_type") : //$NON-NLS-1$
            getString("_UI_NodeMapping_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

        switch (notification.getFeatureID(NodeMapping.class)) {
        case DescriptionPackage.NODE_MAPPING__STYLE:
        case DescriptionPackage.NODE_MAPPING__CONDITIONNAL_STYLES:
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
     * @not-generated
     */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.NODE_MAPPING__STYLE, StyleFactory.eINSTANCE.createCustomStyleDescription()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.NODE_MAPPING__STYLE, StyleFactory.eINSTANCE.createSquareDescription()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.NODE_MAPPING__STYLE, StyleFactory.eINSTANCE.createLozengeNodeDescription()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.NODE_MAPPING__STYLE, StyleFactory.eINSTANCE.createEllipseNodeDescription()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.NODE_MAPPING__STYLE, StyleFactory.eINSTANCE.createBundledImageDescription()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.NODE_MAPPING__STYLE, StyleFactory.eINSTANCE.createNoteDescription()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.NODE_MAPPING__STYLE, StyleFactory.eINSTANCE.createDotDescription()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.NODE_MAPPING__STYLE, StyleFactory.eINSTANCE.createGaugeCompositeStyleDescription()));

        WorkspaceImageDescription wkpImageDescription = StyleFactory.eINSTANCE.createWorkspaceImageDescription();
        wkpImageDescription.setSizeComputationExpression("-1"); //$NON-NLS-1$
        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.NODE_MAPPING__STYLE, wkpImageDescription));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.NODE_MAPPING__CONDITIONNAL_STYLES, DescriptionFactory.eINSTANCE.createConditionalNodeStyleDescription()));

        // Set by default all elements resizable and their label position on
        // "node".
        for (Object obj : newChildDescriptors) {
            if (obj instanceof CommandParameter) {
                if (((CommandParameter) obj).getValue() instanceof NodeStyleDescription) {
                    NodeStyleDescription nodeStyle = (NodeStyleDescription) ((CommandParameter) obj).getValue();
                    nodeStyle.setResizeKind(ResizeKind.NSEW_LITERAL);
                    nodeStyle.setLabelPosition(LabelPosition.NODE_LITERAL);
                }
            }
        }
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s
     * describing the children that can be created under this object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void collectNewChildDescriptorsGen(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.NODE_MAPPING__STYLE, StyleFactory.eINSTANCE.createCustomStyleDescription()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.NODE_MAPPING__STYLE, StyleFactory.eINSTANCE.createSquareDescription()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.NODE_MAPPING__STYLE, StyleFactory.eINSTANCE.createLozengeNodeDescription()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.NODE_MAPPING__STYLE, StyleFactory.eINSTANCE.createEllipseNodeDescription()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.NODE_MAPPING__STYLE, StyleFactory.eINSTANCE.createBundledImageDescription()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.NODE_MAPPING__STYLE, StyleFactory.eINSTANCE.createNoteDescription()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.NODE_MAPPING__STYLE, StyleFactory.eINSTANCE.createDotDescription()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.NODE_MAPPING__STYLE, StyleFactory.eINSTANCE.createGaugeCompositeStyleDescription()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.NODE_MAPPING__STYLE, StyleFactory.eINSTANCE.createWorkspaceImageDescription()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.NODE_MAPPING__CONDITIONNAL_STYLES, DescriptionFactory.eINSTANCE.createConditionalNodeStyleDescription()));
    }

}
