/**
 * Copyright (c) 2007, 2018 THALES GLOBAL SERVICES.
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

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.style.ContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.FlatContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.ShapeContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.StyleFactory;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;

/**
 * This is the item provider adapter for a {@link org.eclipse.sirius.diagram.description.ContainerMapping} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class ContainerMappingItemProvider extends AbstractNodeMappingItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ContainerMappingItemProvider(AdapterFactory adapterFactory) {
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

            addDropDescriptionsPropertyDescriptor(object);
            addReusedNodeMappingsPropertyDescriptor(object);
            addReusedContainerMappingsPropertyDescriptor(object);
            addChildrenPresentationPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Drop Descriptions feature. <!-- begin-user-doc --> <!-- end-user-doc -->
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
     * This adds a property descriptor for the Reused Node Mappings feature. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    protected void addReusedNodeMappingsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
                createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_ContainerMapping_reusedNodeMappings_feature"), //$NON-NLS-1$
                        getString("_UI_ContainerMapping_reusedNodeMappings_description"), //$NON-NLS-1$
                        DescriptionPackage.Literals.CONTAINER_MAPPING__REUSED_NODE_MAPPINGS, true, false, true, null, getString("_UI_ImportPropertyCategory"), //$NON-NLS-1$
                        null));
    }

    /**
     * This adds a property descriptor for the Reused Container Mappings feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    protected void addReusedContainerMappingsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_ContainerMapping_reusedContainerMappings_feature"), //$NON-NLS-1$
                getString("_UI_ContainerMapping_reusedContainerMappings_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.CONTAINER_MAPPING__REUSED_CONTAINER_MAPPINGS, true, false, true, null, getString("_UI_ImportPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Children Presentation feature. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    protected void addChildrenPresentationPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
                createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_ContainerMapping_childrenPresentation_feature"), //$NON-NLS-1$
                        getString("_UI_ContainerMapping_childrenPresentation_description"), //$NON-NLS-1$
                        DescriptionPackage.Literals.CONTAINER_MAPPING__CHILDREN_PRESENTATION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
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
            childrenFeatures.add(DescriptionPackage.Literals.CONTAINER_MAPPING__SUB_NODE_MAPPINGS);
            childrenFeatures.add(DescriptionPackage.Literals.CONTAINER_MAPPING__SUB_CONTAINER_MAPPINGS);
            childrenFeatures.add(DescriptionPackage.Literals.CONTAINER_MAPPING__STYLE);
            childrenFeatures.add(DescriptionPackage.Literals.CONTAINER_MAPPING__CONDITIONNAL_STYLES);
        }
        return childrenFeatures;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    protected EStructuralFeature getChildFeature(Object object, Object child) {
        // In the case for drag and drop the feature was choose by default was
        // BorderedNode. If the parent is a container, the feature must be a
        // node and not a borderedNode
        if (child instanceof NodeMapping) {
            return org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE.getContainerMapping_SubNodeMappings();
        }
        // Check the type of the specified child object and return the proper
        // feature to use for
        // adding (see {@link AddCommand}) it as a child.
        return super.getChildFeature(object, child);
    }

    /**
     * This returns ContainerMapping.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/ContainerMapping")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public String getText(Object object) {
        String label = new IdentifiedElementQuery((IdentifiedElement) object).getLabel();
        return label == null || label.length() == 0 ? getString("_UI_ContainerMapping_type") : label; //$NON-NLS-1$
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

        switch (notification.getFeatureID(ContainerMapping.class)) {
        case DescriptionPackage.CONTAINER_MAPPING__CHILDREN_PRESENTATION:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case DescriptionPackage.CONTAINER_MAPPING__SUB_NODE_MAPPINGS:
        case DescriptionPackage.CONTAINER_MAPPING__SUB_CONTAINER_MAPPINGS:
        case DescriptionPackage.CONTAINER_MAPPING__STYLE:
        case DescriptionPackage.CONTAINER_MAPPING__CONDITIONNAL_STYLES:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
            return;
        }
        super.notifyChanged(notification);
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children that can be created
     * under this object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated:
     *                 <UL>
     *                 <LI>Add a default semantic candidate expression at creation time.</LI>
     *                 <LI>Set to 1 the default borderSizeComputationExpression instead of 0 for flatContainerStyle and
     *                 shapeContainerStyle.</LI>
     *                 </UL>
     */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);

        NodeMapping createNodeMapping = DescriptionFactory.eINSTANCE.createNodeMapping();
        createNodeMapping.setSemanticCandidatesExpression("feature:eAllContents"); //$NON-NLS-1$
        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.CONTAINER_MAPPING__SUB_NODE_MAPPINGS, createNodeMapping));

        newChildDescriptors.add(
                createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.CONTAINER_MAPPING__SUB_NODE_MAPPINGS, DescriptionFactory.eINSTANCE.createNodeMappingImport()));

        ContainerMapping createContainerMapping = DescriptionFactory.eINSTANCE.createContainerMapping();
        createContainerMapping.setSemanticCandidatesExpression("feature:eAllContents"); //$NON-NLS-1$
        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.CONTAINER_MAPPING__SUB_CONTAINER_MAPPINGS, createContainerMapping));

        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.CONTAINER_MAPPING__SUB_CONTAINER_MAPPINGS,
                DescriptionFactory.eINSTANCE.createContainerMappingImport()));

        FlatContainerStyleDescription flatContainerStyleDescription = StyleFactory.eINSTANCE.createFlatContainerStyleDescription();
        flatContainerStyleDescription.setBorderSizeComputationExpression("1"); //$NON-NLS-1$
        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.CONTAINER_MAPPING__STYLE, flatContainerStyleDescription));

        ShapeContainerStyleDescription shapeContainerStyleDescription = StyleFactory.eINSTANCE.createShapeContainerStyleDescription();
        shapeContainerStyleDescription.setBorderSizeComputationExpression("1"); //$NON-NLS-1$
        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.CONTAINER_MAPPING__STYLE, shapeContainerStyleDescription));

        newChildDescriptors
                .add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.CONTAINER_MAPPING__STYLE, StyleFactory.eINSTANCE.createWorkspaceImageDescription()));

        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.CONTAINER_MAPPING__CONDITIONNAL_STYLES,
                DescriptionFactory.eINSTANCE.createConditionalContainerStyleDescription()));

        // Set by default the font size of all elements to 12
        for (Object obj : newChildDescriptors) {
            if (obj instanceof CommandParameter) {
                if (((CommandParameter) obj).getValue() instanceof ContainerStyleDescription) {
                    ContainerStyleDescription containerStyle = (ContainerStyleDescription) ((CommandParameter) obj).getValue();
                    containerStyle.setLabelSize(12);
                }
            }
        }
    }

    /**
     * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
        Object childFeature = feature;
        Object childObject = child;

        boolean qualify = childFeature == org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS
                || childFeature == org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.CONTAINER_MAPPING__SUB_NODE_MAPPINGS;

        if (qualify) {
            return getString("_UI_CreateChild_text2", new Object[] { getTypeText(childObject), getFeaturePrefixText(childFeature), getTypeText(owner) }); //$NON-NLS-1$
        }
        return super.getCreateChildText(owner, feature, child, selection);
    }

}
