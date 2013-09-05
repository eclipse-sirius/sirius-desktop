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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
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

import org.eclipse.sirius.DNode;
import org.eclipse.sirius.SiriusFactory;
import org.eclipse.sirius.SiriusPackage;
import org.eclipse.sirius.ui.business.api.provider.DNodeLabelItemProvider;

/**
 * This is the item provider adapter for a {@link org.eclipse.sirius.DNode}
 * object. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class DNodeItemProvider extends DDiagramElementItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider,
        IItemPropertySource {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

    /**
     * The item provider used to simulate another child for Node that has label
     * on border.
     * 
     * @not-generated
     */
    HashMap<Object, DNodeLabelItemProvider> nodeLabelItemProviders = new HashMap<Object, DNodeLabelItemProvider>();

    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DNodeItemProvider(AdapterFactory adapterFactory) {
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
            addWidthPropertyDescriptor(object);
            addHeightPropertyDescriptor(object);
            addLabelPositionPropertyDescriptor(object);
            addResizeKindPropertyDescriptor(object);
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
     * This adds a property descriptor for the Width feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addWidthPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_DNode_width_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_DNode_width_feature", "_UI_DNode_type"), SiriusPackage.Literals.DNODE__WIDTH, true, false, false,
                ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Height feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addHeightPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_DNode_height_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_DNode_height_feature", "_UI_DNode_type"), SiriusPackage.Literals.DNODE__HEIGHT, true, false, false,
                ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Label Position feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addLabelPositionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DNode_labelPosition_feature"), getString("_UI_PropertyDescriptor_description", "_UI_DNode_labelPosition_feature", "_UI_DNode_type"),
                SiriusPackage.Literals.DNODE__LABEL_POSITION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Resize Kind feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addResizeKindPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_DNode_resizeKind_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_DNode_resizeKind_feature", "_UI_DNode_type"), SiriusPackage.Literals.DNODE__RESIZE_KIND, true, false, false,
                ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Original Style feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addOriginalStylePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DNode_originalStyle_feature"), getString("_UI_PropertyDescriptor_description", "_UI_DNode_originalStyle_feature", "_UI_DNode_type"),
                SiriusPackage.Literals.DNODE__ORIGINAL_STYLE, true, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Actual Mapping feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addActualMappingPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DNode_actualMapping_feature"), getString("_UI_PropertyDescriptor_description", "_UI_DNode_actualMapping_feature", "_UI_DNode_type"),
                SiriusPackage.Literals.DNODE__ACTUAL_MAPPING, true, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Candidates Mapping feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addCandidatesMappingPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DNode_candidatesMapping_feature"), getString("_UI_PropertyDescriptor_description", "_UI_DNode_candidatesMapping_feature", "_UI_DNode_type"),
                SiriusPackage.Literals.DNODE__CANDIDATES_MAPPING, true, false, true, null, null, null));
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
            childrenFeatures.add(SiriusPackage.Literals.DNODE__OWNED_STYLE);
            childrenFeatures.add(SiriusPackage.Literals.DNODE__OWNED_DETAILS);
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
     * This returns DNode.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/DNode"));
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    @Override
    public String getText(Object object) {
        String label = ((DNode) object).getName();
        return label == null || label.length() == 0 ? getString("_UI_DNode_type") : label;
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

        switch (notification.getFeatureID(DNode.class)) {
        case SiriusPackage.DNODE__ARRANGE_CONSTRAINTS:
        case SiriusPackage.DNODE__WIDTH:
        case SiriusPackage.DNODE__HEIGHT:
        case SiriusPackage.DNODE__LABEL_POSITION:
        case SiriusPackage.DNODE__RESIZE_KIND:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case SiriusPackage.DNODE__OWNED_BORDERED_NODES:
        case SiriusPackage.DNODE__OWNED_STYLE:
        case SiriusPackage.DNODE__OWNED_DETAILS:
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

        newChildDescriptors.add(createChildParameter(SiriusPackage.Literals.DNODE__OWNED_STYLE, SiriusFactory.eINSTANCE.createDot()));

        newChildDescriptors.add(createChildParameter(SiriusPackage.Literals.DNODE__OWNED_STYLE, SiriusFactory.eINSTANCE.createSquare()));

        newChildDescriptors.add(createChildParameter(SiriusPackage.Literals.DNODE__OWNED_STYLE, SiriusFactory.eINSTANCE.createEllipse()));

        newChildDescriptors.add(createChildParameter(SiriusPackage.Literals.DNODE__OWNED_STYLE, SiriusFactory.eINSTANCE.createLozenge()));

        newChildDescriptors.add(createChildParameter(SiriusPackage.Literals.DNODE__OWNED_STYLE, SiriusFactory.eINSTANCE.createBundledImage()));

        newChildDescriptors.add(createChildParameter(SiriusPackage.Literals.DNODE__OWNED_STYLE, SiriusFactory.eINSTANCE.createWorkspaceImage()));

        newChildDescriptors.add(createChildParameter(SiriusPackage.Literals.DNODE__OWNED_STYLE, SiriusFactory.eINSTANCE.createCustomStyle()));

        newChildDescriptors.add(createChildParameter(SiriusPackage.Literals.DNODE__OWNED_STYLE, SiriusFactory.eINSTANCE.createGaugeCompositeStyle()));

        newChildDescriptors.add(createChildParameter(SiriusPackage.Literals.DNODE__OWNED_STYLE, SiriusFactory.eINSTANCE.createNote()));

        newChildDescriptors.add(createChildParameter(SiriusPackage.Literals.DNODE__OWNED_DETAILS, SiriusFactory.eINSTANCE.createDDiagram()));

        newChildDescriptors.add(createChildParameter(SiriusPackage.Literals.DNODE__OWNED_DETAILS, SiriusFactory.eINSTANCE.createDSemanticDiagram()));
    }

    /**
     * @not-generated
     * 
     *                {@inheritDoc}
     * 
     * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildren(java.lang.Object)
     */
    public Collection<?> getChildren(Object object) {
        Collection<Object> result = (Collection<Object>) super.getChildren(object);
        if (object instanceof DNode && DNodeLabelItemProvider.hasRelevantLabelItem((DNode) object)) {
            Collection<Object> resultTemp = new ArrayList<Object>();
            if (nodeLabelItemProviders.get(object) == null) {
                nodeLabelItemProviders.put(object, new DNodeLabelItemProvider(adapterFactory, (DNode) object));
            }
            resultTemp.add(nodeLabelItemProviders.get(object));
            resultTemp.addAll(result);
            result = resultTemp;
        } else if (nodeLabelItemProviders.get(object) != null) {
            nodeLabelItemProviders.remove(object).dispose();
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#dispose()
     * @not-generated
     */
    public void dispose() {
        super.dispose();
        // Dispose all the DNodeLabelItemProvider to avoid potential memory
        // leak.
        for (Iterator<Object> iterator = nodeLabelItemProviders.keySet().iterator(); iterator.hasNext();) {
            nodeLabelItemProviders.get(iterator.next()).dispose();
        }
        nodeLabelItemProviders.clear();
    }
}
