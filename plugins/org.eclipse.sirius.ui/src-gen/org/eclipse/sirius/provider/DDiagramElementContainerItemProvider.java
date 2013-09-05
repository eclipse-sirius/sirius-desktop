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
package org.eclipse.sirius.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.sirius.DDiagramElementContainer;
import org.eclipse.sirius.SiriusFactory;
import org.eclipse.sirius.SiriusPackage;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.DDiagramElementContainer} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class DDiagramElementContainerItemProvider extends DDiagramElementItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider,
        IItemLabelProvider, IItemPropertySource {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DDiagramElementContainerItemProvider(AdapterFactory adapterFactory) {
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
            addOutgoingEdgesPropertyDescriptor(object);
            addIncomingEdgesPropertyDescriptor(object);
            addNodesPropertyDescriptor(object);
            addContainersPropertyDescriptor(object);
            addElementsPropertyDescriptor(object);
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
                SiriusPackage.Literals.ABSTRACT_DNODE__ARRANGE_CONSTRAINTS, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Outgoing Edges feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addOutgoingEdgesPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_EdgeTarget_outgoingEdges_feature"), getString("_UI_PropertyDescriptor_description", "_UI_EdgeTarget_outgoingEdges_feature", "_UI_EdgeTarget_type"),
                SiriusPackage.Literals.EDGE_TARGET__OUTGOING_EDGES, false, false, false, null, null, null));
    }

    /**
     * This adds a property descriptor for the Incoming Edges feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addIncomingEdgesPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_EdgeTarget_incomingEdges_feature"), getString("_UI_PropertyDescriptor_description", "_UI_EdgeTarget_incomingEdges_feature", "_UI_EdgeTarget_type"),
                SiriusPackage.Literals.EDGE_TARGET__INCOMING_EDGES, false, false, false, null, null, null));
    }

    /**
     * This adds a property descriptor for the Nodes feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addNodesPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DDiagramElementContainer_nodes_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_DDiagramElementContainer_nodes_feature", "_UI_DDiagramElementContainer_type"),
                SiriusPackage.Literals.DDIAGRAM_ELEMENT_CONTAINER__NODES, false, false, false, null, null, null));
    }

    /**
     * This adds a property descriptor for the Containers feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addContainersPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DDiagramElementContainer_containers_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_DDiagramElementContainer_containers_feature", "_UI_DDiagramElementContainer_type"),
                SiriusPackage.Literals.DDIAGRAM_ELEMENT_CONTAINER__CONTAINERS, false, false, false, null, null, null));
    }

    /**
     * This adds a property descriptor for the Elements feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addElementsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DDiagramElementContainer_elements_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_DDiagramElementContainer_elements_feature", "_UI_DDiagramElementContainer_type"),
                SiriusPackage.Literals.DDIAGRAM_ELEMENT_CONTAINER__ELEMENTS, false, false, false, null, null, null));
    }

    /**
     * This adds a property descriptor for the Original Style feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addOriginalStylePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DDiagramElementContainer_originalStyle_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_DDiagramElementContainer_originalStyle_feature", "_UI_DDiagramElementContainer_type"),
                SiriusPackage.Literals.DDIAGRAM_ELEMENT_CONTAINER__ORIGINAL_STYLE, true, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Actual Mapping feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addActualMappingPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DDiagramElementContainer_actualMapping_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_DDiagramElementContainer_actualMapping_feature", "_UI_DDiagramElementContainer_type"),
                SiriusPackage.Literals.DDIAGRAM_ELEMENT_CONTAINER__ACTUAL_MAPPING, true, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Candidates Mapping feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addCandidatesMappingPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DDiagramElementContainer_candidatesMapping_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_DDiagramElementContainer_candidatesMapping_feature", "_UI_DDiagramElementContainer_type"),
                SiriusPackage.Literals.DDIAGRAM_ELEMENT_CONTAINER__CANDIDATES_MAPPING, true, false, true, null, null, null));
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
            childrenFeatures.add(SiriusPackage.Literals.ABSTRACT_DNODE__OWNED_BORDERED_NODES);
            childrenFeatures.add(SiriusPackage.Literals.DDIAGRAM_ELEMENT_CONTAINER__OWNED_STYLE);
            childrenFeatures.add(SiriusPackage.Literals.DDIAGRAM_ELEMENT_CONTAINER__OWNED_DETAILS);
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
        String label = ((DDiagramElementContainer) object).getName();
        return label == null || label.length() == 0 ? getString("_UI_DDiagramElementContainer_type") : getString("_UI_DDiagramElementContainer_type") + " " + label;
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

        switch (notification.getFeatureID(DDiagramElementContainer.class)) {
        case SiriusPackage.DDIAGRAM_ELEMENT_CONTAINER__ARRANGE_CONSTRAINTS:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case SiriusPackage.DDIAGRAM_ELEMENT_CONTAINER__OWNED_BORDERED_NODES:
        case SiriusPackage.DDIAGRAM_ELEMENT_CONTAINER__OWNED_STYLE:
        case SiriusPackage.DDIAGRAM_ELEMENT_CONTAINER__OWNED_DETAILS:
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

        newChildDescriptors.add(createChildParameter(SiriusPackage.Literals.ABSTRACT_DNODE__OWNED_BORDERED_NODES, SiriusFactory.eINSTANCE.createDNode()));

        newChildDescriptors.add(createChildParameter(SiriusPackage.Literals.DDIAGRAM_ELEMENT_CONTAINER__OWNED_STYLE, SiriusFactory.eINSTANCE.createFlatContainerStyle()));

        newChildDescriptors.add(createChildParameter(SiriusPackage.Literals.DDIAGRAM_ELEMENT_CONTAINER__OWNED_STYLE, SiriusFactory.eINSTANCE.createShapeContainerStyle()));

        newChildDescriptors.add(createChildParameter(SiriusPackage.Literals.DDIAGRAM_ELEMENT_CONTAINER__OWNED_STYLE, SiriusFactory.eINSTANCE.createWorkspaceImage()));

        newChildDescriptors.add(createChildParameter(SiriusPackage.Literals.DDIAGRAM_ELEMENT_CONTAINER__OWNED_DETAILS, SiriusFactory.eINSTANCE.createDDiagram()));

        newChildDescriptors.add(createChildParameter(SiriusPackage.Literals.DDIAGRAM_ELEMENT_CONTAINER__OWNED_DETAILS, SiriusFactory.eINSTANCE.createDSemanticDiagram()));
    }

}
