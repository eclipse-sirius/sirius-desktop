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
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.diagram.description.ContainerMappingImport;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.diagram.description.ContainerMappingImport} object.
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class ContainerMappingImportItemProvider extends ContainerMappingItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ContainerMappingImportItemProvider(AdapterFactory adapterFactory) {
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

            addHideSubMappingsPropertyDescriptor(object);
            addInheritsAncestorFiltersPropertyDescriptor(object);
            addImportedMappingPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Hide Sub Mappings feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addHideSubMappingsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_AbstractMappingImport_hideSubMappings_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_AbstractMappingImport_hideSubMappings_feature", "_UI_AbstractMappingImport_type"),
                DescriptionPackage.Literals.ABSTRACT_MAPPING_IMPORT__HIDE_SUB_MAPPINGS, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, getString("_UI_ImportPropertyCategory"), null));
    }

    /**
     * This adds a property descriptor for the Inherits Ancestor Filters
     * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addInheritsAncestorFiltersPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_AbstractMappingImport_inheritsAncestorFilters_feature"), getString("_UI_AbstractMappingImport_inheritsAncestorFilters_description"),
                DescriptionPackage.Literals.ABSTRACT_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
                getString("_UI_ImportPropertyCategory"), null));
    }

    /**
     * This adds a property descriptor for the Imported Mapping feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addImportedMappingPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_ContainerMappingImport_importedMapping_feature"), getString("_UI_ContainerMappingImport_importedMapping_description"),
                org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.CONTAINER_MAPPING_IMPORT__IMPORTED_MAPPING, true, false, true, null, getString("_UI_ImportPropertyCategory"), null));
    }

    /**
     * This returns ContainerMappingImport.gif. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/ContainerMappingImport"));
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public String getText(Object object) {
        String label = new IdentifiedElementQuery((ContainerMappingImport) object).getLabel();
        return label == null || label.length() == 0 ? getString("_UI_ContainerMappingImport_type") : label;
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

        switch (notification.getFeatureID(ContainerMappingImport.class)) {
        case org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING_IMPORT__HIDE_SUB_MAPPINGS:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS:
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

        boolean qualify = childFeature == org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS
                || childFeature == org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.CONTAINER_MAPPING__SUB_NODE_MAPPINGS;

        if (qualify) {
            return getString("_UI_CreateChild_text2", new Object[] { getTypeText(childObject), getFeaturePrefixText(childFeature), getTypeText(owner) });
        }
        return super.getCreateChildText(owner, feature, child, selection);
    }

}
