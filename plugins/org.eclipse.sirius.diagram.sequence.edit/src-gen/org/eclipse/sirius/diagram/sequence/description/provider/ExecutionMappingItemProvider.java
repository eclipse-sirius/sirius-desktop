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
package org.eclipse.sirius.diagram.sequence.description.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.diagram.description.provider.NodeMappingItemProvider;
import org.eclipse.sirius.diagram.sequence.description.DescriptionFactory;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;
import org.eclipse.sirius.diagram.sequence.description.ExecutionMapping;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.diagram.sequence.description.ExecutionMapping}
 * object. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class ExecutionMappingItemProvider extends NodeMappingItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ExecutionMappingItemProvider(AdapterFactory adapterFactory) {
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

            addStartingEndFinderExpressionPropertyDescriptor(object);
            addFinishingEndFinderExpressionPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Starting End Finder Expression
     * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addStartingEndFinderExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_DelimitedEventMapping_startingEndFinderExpression_feature"), //$NON-NLS-1$
                getString("_UI_DelimitedEventMapping_startingEndFinderExpression_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.DELIMITED_EVENT_MAPPING__STARTING_END_FINDER_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Finishing End Finder Expression
     * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addFinishingEndFinderExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_DelimitedEventMapping_finishingEndFinderExpression_feature"), //$NON-NLS-1$
                getString("_UI_DelimitedEventMapping_finishingEndFinderExpression_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.DELIMITED_EVENT_MAPPING__FINISHING_END_FINDER_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This returns ExecutionMapping.gif. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/ExecutionMapping")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public String getText(Object object) {
        String label = new IdentifiedElementQuery((ExecutionMapping) object).getLabel();
        return label == null || label.length() == 0 ? getString("_UI_ExecutionMapping_type") : getString("_UI_ExecutionMapping_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

        switch (notification.getFeatureID(ExecutionMapping.class)) {
        case DescriptionPackage.EXECUTION_MAPPING__STARTING_END_FINDER_EXPRESSION:
        case DescriptionPackage.EXECUTION_MAPPING__FINISHING_END_FINDER_EXPRESSION:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
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

        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS,
                DescriptionFactory.eINSTANCE.createInstanceRoleMapping()));

        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS,
                DescriptionFactory.eINSTANCE.createExecutionMapping()));

        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS,
                DescriptionFactory.eINSTANCE.createStateMapping()));

        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS,
                DescriptionFactory.eINSTANCE.createEndOfLifeMapping()));

        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS,
                DescriptionFactory.eINSTANCE.createObservationPointMapping()));
    }

    /**
     * Return the resource locator for this item provider's resources. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ResourceLocator getResourceLocator() {
        return SequenceEditPlugin.INSTANCE;
    }

    /**
     * This returns the label text for
     * {@link org.eclipse.emf.edit.command.CreateChildCommand}. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
        Object childFeature = feature;
        Object childObject = child;

        boolean qualify = childFeature == org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS;
        qualify = qualify && childObject instanceof EObject && DescriptionPackage.eINSTANCE.equals(((EObject) childObject).eClass().getEPackage());

        if (qualify) {
            return getString("_UI_CreateChild_text", new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) }); //$NON-NLS-1$
        }
        return super.getCreateChildText(owner, feature, child, selection);
    }

}
