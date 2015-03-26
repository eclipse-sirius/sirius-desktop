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
package org.eclipse.sirius.diagram.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.DiagramPackage;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.diagram.DNodeListElement} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class DNodeListElementItemProvider extends DDiagramElementItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public DNodeListElementItemProvider(AdapterFactory adapterFactory) {
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

            addArrangeConstraintsPropertyDescriptor(object);
            addOriginalStylePropertyDescriptor(object);
            addActualMappingPropertyDescriptor(object);
            addCandidatesMappingPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Arrange Constraints feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addArrangeConstraintsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_AbstractDNode_arrangeConstraints_feature"), getString("_UI_PropertyDescriptor_description", "_UI_AbstractDNode_arrangeConstraints_feature", "_UI_AbstractDNode_type"),
                DiagramPackage.Literals.ABSTRACT_DNODE__ARRANGE_CONSTRAINTS, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Original Style feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addOriginalStylePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DNodeListElement_originalStyle_feature"), getString("_UI_PropertyDescriptor_description", "_UI_DNodeListElement_originalStyle_feature", "_UI_DNodeListElement_type"),
                DiagramPackage.Literals.DNODE_LIST_ELEMENT__ORIGINAL_STYLE, true, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Actual Mapping feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addActualMappingPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DNodeListElement_actualMapping_feature"), getString("_UI_PropertyDescriptor_description", "_UI_DNodeListElement_actualMapping_feature", "_UI_DNodeListElement_type"),
                DiagramPackage.Literals.DNODE_LIST_ELEMENT__ACTUAL_MAPPING, true, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Candidates Mapping feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addCandidatesMappingPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DNodeListElement_candidatesMapping_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_DNodeListElement_candidatesMapping_feature", "_UI_DNodeListElement_type"),
                DiagramPackage.Literals.DNODE_LIST_ELEMENT__CANDIDATES_MAPPING, true, false, true, null, null, null));
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
            childrenFeatures.add(DiagramPackage.Literals.ABSTRACT_DNODE__OWNED_BORDERED_NODES);
            childrenFeatures.add(DiagramPackage.Literals.DNODE_LIST_ELEMENT__OWNED_STYLE);
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
     * This returns DNodeListElement.gif. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/DNodeListElement"));
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public String getText(Object object) {
        String label = ((DNodeListElement) object).getName();
        return label == null || label.length() == 0 ? getString("_UI_DNodeListElement_type") : label;
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

        switch (notification.getFeatureID(DNodeListElement.class)) {
        case DiagramPackage.DNODE_LIST_ELEMENT__ARRANGE_CONSTRAINTS:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case DiagramPackage.DNODE_LIST_ELEMENT__OWNED_BORDERED_NODES:
        case DiagramPackage.DNODE_LIST_ELEMENT__OWNED_STYLE:
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

        newChildDescriptors.add(createChildParameter(DiagramPackage.Literals.ABSTRACT_DNODE__OWNED_BORDERED_NODES, DiagramFactory.eINSTANCE.createDNode()));

        newChildDescriptors.add(createChildParameter(DiagramPackage.Literals.DNODE_LIST_ELEMENT__OWNED_STYLE, DiagramFactory.eINSTANCE.createDot()));

        newChildDescriptors.add(createChildParameter(DiagramPackage.Literals.DNODE_LIST_ELEMENT__OWNED_STYLE, DiagramFactory.eINSTANCE.createSquare()));

        newChildDescriptors.add(createChildParameter(DiagramPackage.Literals.DNODE_LIST_ELEMENT__OWNED_STYLE, DiagramFactory.eINSTANCE.createEllipse()));

        newChildDescriptors.add(createChildParameter(DiagramPackage.Literals.DNODE_LIST_ELEMENT__OWNED_STYLE, DiagramFactory.eINSTANCE.createLozenge()));

        newChildDescriptors.add(createChildParameter(DiagramPackage.Literals.DNODE_LIST_ELEMENT__OWNED_STYLE, DiagramFactory.eINSTANCE.createBundledImage()));

        newChildDescriptors.add(createChildParameter(DiagramPackage.Literals.DNODE_LIST_ELEMENT__OWNED_STYLE, DiagramFactory.eINSTANCE.createWorkspaceImage()));

        newChildDescriptors.add(createChildParameter(DiagramPackage.Literals.DNODE_LIST_ELEMENT__OWNED_STYLE, DiagramFactory.eINSTANCE.createCustomStyle()));

        newChildDescriptors.add(createChildParameter(DiagramPackage.Literals.DNODE_LIST_ELEMENT__OWNED_STYLE, DiagramFactory.eINSTANCE.createGaugeCompositeStyle()));

        newChildDescriptors.add(createChildParameter(DiagramPackage.Literals.DNODE_LIST_ELEMENT__OWNED_STYLE, DiagramFactory.eINSTANCE.createNote()));
    }

}
