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
package org.eclipse.sirius.table.metamodel.table.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.TableFactory;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.table.metamodel.table.DLine} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class DLineItemProvider extends LineContainerItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public DLineItemProvider(AdapterFactory adapterFactory) {
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

            addNamePropertyDescriptor(object);
            addSemanticElementsPropertyDescriptor(object);
            addTableElementMappingPropertyDescriptor(object);
            addLabelPropertyDescriptor(object);
            addOriginMappingPropertyDescriptor(object);
            addVisiblePropertyDescriptor(object);
            addCollapsedPropertyDescriptor(object);
            addOrderedCellsPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Name feature. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addNamePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DRepresentationElement_name_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DRepresentationElement_name_feature", "_UI_DRepresentationElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                ViewpointPackage.Literals.DREPRESENTATION_ELEMENT__NAME, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Semantic Elements feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addSemanticElementsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DRepresentationElement_semanticElements_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DRepresentationElement_semanticElements_feature", "_UI_DRepresentationElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                ViewpointPackage.Literals.DREPRESENTATION_ELEMENT__SEMANTIC_ELEMENTS, true, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Table Element Mapping feature.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addTableElementMappingPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DTableElement_tableElementMapping_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DTableElement_tableElementMapping_feature", "_UI_DTableElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                TablePackage.Literals.DTABLE_ELEMENT__TABLE_ELEMENT_MAPPING, false, false, false, null, null, null));
    }

    /**
     * This adds a property descriptor for the Label feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addLabelPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_DLine_label_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DLine_label_feature", "_UI_DLine_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                TablePackage.Literals.DLINE__LABEL, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Origin Mapping feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addOriginMappingPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DLine_originMapping_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DLine_originMapping_feature", "_UI_DLine_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                TablePackage.Literals.DLINE__ORIGIN_MAPPING, true, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Visible feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addVisiblePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_DLine_visible_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DLine_visible_feature", "_UI_DLine_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                TablePackage.Literals.DLINE__VISIBLE, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Collapsed feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addCollapsedPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_DLine_collapsed_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DLine_collapsed_feature", "_UI_DLine_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                TablePackage.Literals.DLINE__COLLAPSED, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Ordered Cells feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addOrderedCellsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DLine_orderedCells_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DLine_orderedCells_feature", "_UI_DLine_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                TablePackage.Literals.DLINE__ORDERED_CELLS, false, false, false, null, null, null));
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
            childrenFeatures.add(TablePackage.Literals.DLINE__CELLS);
            childrenFeatures.add(TablePackage.Literals.DLINE__CURRENT_STYLE);
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
     * This returns DLine.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/DLine")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getText(Object object) {
        String label = ((DLine) object).getName();
        return label == null || label.length() == 0 ? getString("_UI_DLine_type") : //$NON-NLS-1$
            getString("_UI_DLine_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

        switch (notification.getFeatureID(DLine.class)) {
        case TablePackage.DLINE__NAME:
        case TablePackage.DLINE__LABEL:
        case TablePackage.DLINE__VISIBLE:
        case TablePackage.DLINE__COLLAPSED:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case TablePackage.DLINE__CELLS:
        case TablePackage.DLINE__CURRENT_STYLE:
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

        newChildDescriptors.add(createChildParameter(TablePackage.Literals.DLINE__CELLS, TableFactory.eINSTANCE.createDCell()));

        newChildDescriptors.add(createChildParameter(TablePackage.Literals.DLINE__CURRENT_STYLE, TableFactory.eINSTANCE.createDTableElementStyle()));

        newChildDescriptors.add(createChildParameter(TablePackage.Literals.DLINE__CURRENT_STYLE, TableFactory.eINSTANCE.createDCellStyle()));
    }

}
