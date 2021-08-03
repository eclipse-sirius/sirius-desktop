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
package org.eclipse.sirius.diagram.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.ui.business.api.provider.DEdgeLabelItemProvider;

/**
 * This is the item provider adapter for a {@link org.eclipse.sirius.diagram.DEdge} object. <!-- begin-user-doc --> <!--
 * end-user-doc -->
 *
 * @generated
 */
public class DEdgeItemProvider extends DDiagramElementItemProvider {
    /**
     * The item provider used to simulate another child for Edge that has label on border.
     *
     * @not-generated
     */
    HashMap<Object, DEdgeLabelItemProvider> edgeLabelItemProviders = new HashMap<>();

    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public DEdgeItemProvider(AdapterFactory adapterFactory) {
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

            addOutgoingEdgesPropertyDescriptor(object);
            addIncomingEdgesPropertyDescriptor(object);
            addSizePropertyDescriptor(object);
            addSourceNodePropertyDescriptor(object);
            addTargetNodePropertyDescriptor(object);
            addActualMappingPropertyDescriptor(object);
            addRoutingStylePropertyDescriptor(object);
            addIsFoldPropertyDescriptor(object);
            addIsMockEdgePropertyDescriptor(object);
            addOriginalStylePropertyDescriptor(object);
            addPathPropertyDescriptor(object);
            addArrangeConstraintsPropertyDescriptor(object);
            addBeginLabelPropertyDescriptor(object);
            addEndLabelPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Outgoing Edges feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addOutgoingEdgesPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_EdgeTarget_outgoingEdges_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_EdgeTarget_outgoingEdges_feature", "_UI_EdgeTarget_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        DiagramPackage.Literals.EDGE_TARGET__OUTGOING_EDGES, false, false, false, null, null, null));
    }

    /**
     * This adds a property descriptor for the Incoming Edges feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addIncomingEdgesPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_EdgeTarget_incomingEdges_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_EdgeTarget_incomingEdges_feature", "_UI_EdgeTarget_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        DiagramPackage.Literals.EDGE_TARGET__INCOMING_EDGES, false, false, false, null, null, null));
    }

    /**
     * This adds a property descriptor for the Size feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addSizePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_DEdge_size_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DEdge_size_feature", "_UI_DEdge_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DiagramPackage.Literals.DEDGE__SIZE, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Source Node feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addSourceNodePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_DEdge_sourceNode_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DEdge_sourceNode_feature", "_UI_DEdge_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DiagramPackage.Literals.DEDGE__SOURCE_NODE, true, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Target Node feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addTargetNodePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_DEdge_targetNode_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DEdge_targetNode_feature", "_UI_DEdge_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DiagramPackage.Literals.DEDGE__TARGET_NODE, true, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Actual Mapping feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addActualMappingPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_DEdge_actualMapping_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_DEdge_actualMapping_feature", "_UI_DEdge_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        DiagramPackage.Literals.DEDGE__ACTUAL_MAPPING, true, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Routing Style feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addRoutingStylePropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_DEdge_routingStyle_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_DEdge_routingStyle_feature", "_UI_DEdge_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        DiagramPackage.Literals.DEDGE__ROUTING_STYLE, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Is Fold feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addIsFoldPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_DEdge_isFold_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DEdge_isFold_feature", "_UI_DEdge_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DiagramPackage.Literals.DEDGE__IS_FOLD, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Is Mock Edge feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addIsMockEdgePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_DEdge_isMockEdge_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DEdge_isMockEdge_feature", "_UI_DEdge_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DiagramPackage.Literals.DEDGE__IS_MOCK_EDGE, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Original Style feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addOriginalStylePropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_DEdge_originalStyle_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_DEdge_originalStyle_feature", "_UI_DEdge_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        DiagramPackage.Literals.DEDGE__ORIGINAL_STYLE, true, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Path feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addPathPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_DEdge_path_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DEdge_path_feature", "_UI_DEdge_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DiagramPackage.Literals.DEDGE__PATH, true, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Arrange Constraints feature. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    protected void addArrangeConstraintsPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_DEdge_arrangeConstraints_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_DEdge_arrangeConstraints_feature", "_UI_DEdge_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        DiagramPackage.Literals.DEDGE__ARRANGE_CONSTRAINTS, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Begin Label feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addBeginLabelPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_DEdge_beginLabel_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DEdge_beginLabel_feature", "_UI_DEdge_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DiagramPackage.Literals.DEDGE__BEGIN_LABEL, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the End Label feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addEndLabelPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_DEdge_endLabel_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DEdge_endLabel_feature", "_UI_DEdge_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DiagramPackage.Literals.DEDGE__END_LABEL, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
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
            childrenFeatures.add(DiagramPackage.Literals.DEDGE__OWNED_STYLE);
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
     * This returns DEdge.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/DEdge")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public String getText(Object object) {
        String label = ((DEdge) object).getName();
        return label == null || label.length() == 0 ? getString("_UI_DEdge_type") : label; //$NON-NLS-1$
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

        switch (notification.getFeatureID(DEdge.class)) {
        case DiagramPackage.DEDGE__SIZE:
        case DiagramPackage.DEDGE__ROUTING_STYLE:
        case DiagramPackage.DEDGE__IS_FOLD:
        case DiagramPackage.DEDGE__IS_MOCK_EDGE:
        case DiagramPackage.DEDGE__ARRANGE_CONSTRAINTS:
        case DiagramPackage.DEDGE__BEGIN_LABEL:
        case DiagramPackage.DEDGE__END_LABEL:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case DiagramPackage.DEDGE__OWNED_STYLE:
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

        newChildDescriptors.add(createChildParameter(DiagramPackage.Literals.DEDGE__OWNED_STYLE, DiagramFactory.eINSTANCE.createEdgeStyle()));

        newChildDescriptors.add(createChildParameter(DiagramPackage.Literals.DEDGE__OWNED_STYLE, DiagramFactory.eINSTANCE.createBracketEdgeStyle()));
    }

    /**
     * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
        Object childFeature = feature;
        Object childObject = child;

        boolean qualify = childFeature == DiagramPackage.Literals.DDIAGRAM_ELEMENT__DECORATIONS || childFeature == DiagramPackage.Literals.DDIAGRAM_ELEMENT__TRANSIENT_DECORATIONS;

        if (qualify) {
            return getString("_UI_CreateChild_text2", //$NON-NLS-1$
                    new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
        }
        return super.getCreateChildText(owner, feature, child, selection);
    }

    /**
     * @not-generated
     *
     *                {@inheritDoc}
     *
     * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildren(java.lang.Object)
     */
    @Override
    public Collection<?> getChildren(Object object) {
        Collection<Object> result = (Collection<Object>) super.getChildren(object);
        if (object instanceof DEdge && hasRelevantLabelItem(object)) {
            Collection<Object> resultTemp = new ArrayList<>();
            if (DEdgeLabelItemProvider.hasRelevantLabelItem((DEdge) object) && (edgeLabelItemProviders.get(object) == null)) {
                edgeLabelItemProviders.put(object, new DEdgeLabelItemProvider(adapterFactory, (DEdge) object));
            }
            // if (DEdgeBeginLabelItemProvider.hasRelevantLabelItem((DEdge)
            // object) && (edgeBeginLabelItemProviders.get(object) == null)) {
            // edgeBeginLabelItemProviders.put(object, new
            // DEdgeBeginLabelItemProvider(adapterFactory, (DEdge) object));
            // }
            // if (DEdgeEndLabelItemProvider.hasRelevantLabelItem((DEdge)
            // object) && (edgeEndLabelItemProviders.get(object) == null)) {
            // edgeEndLabelItemProviders.put(object, new
            // DEdgeEndLabelItemProvider(adapterFactory, (DEdge) object));
            // }
            if (DEdgeLabelItemProvider.hasRelevantLabelItem((DEdge) object)) {
                resultTemp.add(edgeLabelItemProviders.get(object));
            }
            // if (DEdgeBeginLabelItemProvider.hasRelevantLabelItem((DEdge)
            // object)) {
            // resultTemp.add(edgeBeginLabelItemProviders.get(object));
            // }
            // if (DEdgeEndLabelItemProvider.hasRelevantLabelItem((DEdge)
            // object)) {
            // resultTemp.add(edgeEndLabelItemProviders.get(object));
            // }
            resultTemp.addAll(result);
            result = resultTemp;
        } else {
            if (edgeLabelItemProviders.get(object) != null) {
                edgeLabelItemProviders.remove(object).dispose();
            }
            // if (edgeBeginLabelItemProviders.get(object) != null) {
            // edgeBeginLabelItemProviders.remove(object).dispose();
            // }
            // if (edgeEndLabelItemProviders.get(object) != null) {
            // edgeEndLabelItemProviders.remove(object).dispose();
            // }
        }
        return result;
    }

    private boolean hasRelevantLabelItem(Object object) {
        return DEdgeLabelItemProvider.hasRelevantLabelItem(
                (DEdge) object); /*
                                  * || DEdgeBeginLabelItemProvider . hasRelevantLabelItem ( ( DEdge ) object ) ||
                                  * DEdgeEndLabelItemProvider . hasRelevantLabelItem ( ( DEdge ) object ) ;
                                  */
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#dispose()
     * @not-generated
     */
    @Override
    public void dispose() {
        super.dispose();
        // Dispose all the DEdgeLabelItemProvider to avoid potential memory
        // leak.
        for (Object object : edgeLabelItemProviders.keySet()) {
            edgeLabelItemProviders.get(object).dispose();
        }
        edgeLabelItemProviders.clear();
    }
}
