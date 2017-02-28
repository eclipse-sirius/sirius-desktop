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

import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.diagram.description.NodeMappingImport;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;

/**
 * This is the item provider adapter for a {@link org.eclipse.sirius.diagram.description.NodeMappingImport} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class NodeMappingImportItemProvider extends NodeMappingItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NodeMappingImportItemProvider(AdapterFactory adapterFactory) {
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

            addHideSubMappingsPropertyDescriptor(object);
            addInheritsAncestorFiltersPropertyDescriptor(object);
            addImportedMappingPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Hide Sub Mappings feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addHideSubMappingsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
                createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_AbstractMappingImport_hideSubMappings_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_AbstractMappingImport_hideSubMappings_feature", "_UI_AbstractMappingImport_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        DescriptionPackage.Literals.ABSTRACT_MAPPING_IMPORT__HIDE_SUB_MAPPINGS, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, getString("_UI_ImportPropertyCategory"), //$NON-NLS-1$
                        null));
    }

    /**
     * This adds a property descriptor for the Inherits Ancestor Filters feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    protected void addInheritsAncestorFiltersPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_AbstractMappingImport_inheritsAncestorFilters_feature"), //$NON-NLS-1$
                getString("_UI_AbstractMappingImport_inheritsAncestorFilters_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.ABSTRACT_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, getString("_UI_ImportPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Imported Mapping feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addImportedMappingPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
                createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_NodeMappingImport_importedMapping_feature"), //$NON-NLS-1$
                        getString("_UI_NodeMappingImport_importedMapping_description"), //$NON-NLS-1$
                        org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.NODE_MAPPING_IMPORT__IMPORTED_MAPPING, true, false, true, null, getString("_UI_ImportPropertyCategory"), //$NON-NLS-1$
                        null));
    }

    /**
     * This returns NodeMappingImport.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public Object getImage(Object object) {
        EStructuralFeature eContainingFeature = ((EObject) object).eContainingFeature();
        if (eContainingFeature != null && eContainingFeature.getFeatureID() == org.eclipse.sirius.diagram.description.DescriptionPackage.ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS) {
            return overlayImage(object, getResourceLocator().getImage("obj16/BorderedNodeMappingImport")); //$NON-NLS-1$
        }
        return overlayImage(object, getResourceLocator().getImage("full/obj16/NodeMappingImport")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public String getText(Object object) {
        String label = new IdentifiedElementQuery((IdentifiedElement) object).getLabel();
        EStructuralFeature eContainingFeature = ((EObject) object).eContainingFeature();
        if (eContainingFeature != null && eContainingFeature.getFeatureID() == org.eclipse.sirius.diagram.description.DescriptionPackage.ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS) {
            return MessageFormat.format(Messages.NodeMappingItemProvider_borderedLabel, label);
        }
        return label == null || label.length() == 0 ? getString("_UI_NodeMappingImport_type") : label; //$NON-NLS-1$

    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getTextGen(Object object) {
        String label = ((NodeMappingImport) object).getName();
        return label == null || label.length() == 0 ? getString("_UI_NodeMappingImport_type") : //$NON-NLS-1$
                getString("_UI_NodeMappingImport_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

        switch (notification.getFeatureID(NodeMappingImport.class)) {
        case org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING_IMPORT__HIDE_SUB_MAPPINGS:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING_IMPORT__IMPORTED_MAPPING:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
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
    }

}
