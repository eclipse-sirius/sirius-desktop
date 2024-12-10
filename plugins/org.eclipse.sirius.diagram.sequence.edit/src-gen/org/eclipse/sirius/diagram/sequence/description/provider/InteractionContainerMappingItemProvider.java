/**
 * Copyright (c) 2024 CEA.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 */
package org.eclipse.sirius.diagram.sequence.description.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.provider.ContainerMappingItemProvider;
import org.eclipse.sirius.diagram.sequence.description.DescriptionFactory;
import org.eclipse.sirius.diagram.sequence.description.InteractionContainerMapping;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.diagram.sequence.description.InteractionContainerMapping} object. <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class InteractionContainerMappingItemProvider extends ContainerMappingItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public InteractionContainerMappingItemProvider(AdapterFactory adapterFactory) {
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

        }
        return itemPropertyDescriptors;
    }

    /**
     * This returns InteractionContainerMapping.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/InteractionContainerMapping")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getText(Object object) {
        String label = ((InteractionContainerMapping) object).getName();
        return label == null || label.length() == 0 ? getString("_UI_InteractionContainerMapping_type") : //$NON-NLS-1$
                getString("_UI_InteractionContainerMapping_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS, DescriptionFactory.eINSTANCE.createInstanceRoleMapping()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS, DescriptionFactory.eINSTANCE.createExecutionMapping()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS, DescriptionFactory.eINSTANCE.createStateMapping()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS, DescriptionFactory.eINSTANCE.createEndOfLifeMapping()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS, DescriptionFactory.eINSTANCE.createObservationPointMapping()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.CONTAINER_MAPPING__SUB_NODE_MAPPINGS, DescriptionFactory.eINSTANCE.createInstanceRoleMapping()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.CONTAINER_MAPPING__SUB_NODE_MAPPINGS, DescriptionFactory.eINSTANCE.createExecutionMapping()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.CONTAINER_MAPPING__SUB_NODE_MAPPINGS, DescriptionFactory.eINSTANCE.createStateMapping()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.CONTAINER_MAPPING__SUB_NODE_MAPPINGS, DescriptionFactory.eINSTANCE.createEndOfLifeMapping()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.CONTAINER_MAPPING__SUB_NODE_MAPPINGS, DescriptionFactory.eINSTANCE.createObservationPointMapping()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.CONTAINER_MAPPING__SUB_CONTAINER_MAPPINGS, DescriptionFactory.eINSTANCE.createInteractionUseMapping()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.CONTAINER_MAPPING__SUB_CONTAINER_MAPPINGS, DescriptionFactory.eINSTANCE.createCombinedFragmentMapping()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.CONTAINER_MAPPING__SUB_CONTAINER_MAPPINGS, DescriptionFactory.eINSTANCE.createOperandMapping()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.CONTAINER_MAPPING__SUB_CONTAINER_MAPPINGS, DescriptionFactory.eINSTANCE.createInteractionContainerMapping()));
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

        boolean qualify = childFeature == DescriptionPackage.Literals.ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS || childFeature == DescriptionPackage.Literals.CONTAINER_MAPPING__SUB_NODE_MAPPINGS;

        if (qualify) {
            return getString("_UI_CreateChild_text2", //$NON-NLS-1$
                    new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
        }
        return super.getCreateChildText(owner, feature, child, selection);
    }

    /**
     * Return the resource locator for this item provider's resources. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ResourceLocator getResourceLocator() {
        return SequenceEditPlugin.INSTANCE;
    }

}
