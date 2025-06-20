/*******************************************************************************
 * Copyright (c) 2007-2015 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.description.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.provider.NodeMappingItemProvider;
import org.eclipse.sirius.diagram.sequence.description.DescriptionFactory;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;

/**
 * This is the item provider adapter for a {@link org.eclipse.sirius.diagram.sequence.description.EndOfLifeMapping}
 * object. <!-- begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class EndOfLifeMappingItemProvider extends NodeMappingItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public EndOfLifeMappingItemProvider(AdapterFactory adapterFactory) {
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
     * This returns EndOfLifeMapping.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/EndOfLifeMapping")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public String getText(Object object) {
        String label = new IdentifiedElementQuery((IdentifiedElement) object).getLabel();
        return label == null || label.length() == 0 ? getString("_UI_EndOfLifeMapping_type") : getString("_UI_EndOfLifeMapping_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS, DescriptionFactory.eINSTANCE.createGateMapping()));
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

        boolean qualify = childFeature == DescriptionPackage.Literals.ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS;
        qualify = qualify && childObject instanceof EObject && org.eclipse.sirius.diagram.sequence.description.DescriptionPackage.eINSTANCE.equals(((EObject) childObject).eClass().getEPackage());

        if (qualify) {
            return getString("_UI_CreateChild_text", new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) }); //$NON-NLS-1$
        }
        return super.getCreateChildText(owner, feature, child, selection);
    }

}
