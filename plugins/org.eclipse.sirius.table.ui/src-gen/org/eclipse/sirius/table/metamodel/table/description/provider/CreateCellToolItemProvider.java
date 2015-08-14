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
package org.eclipse.sirius.table.metamodel.table.description.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.table.metamodel.table.description.CreateCellTool;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.tool.ToolFactory;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.table.metamodel.table.description.CreateCellTool}
 * object. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class CreateCellToolItemProvider extends TableToolItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public CreateCellToolItemProvider(AdapterFactory adapterFactory) {
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

            addDocumentationPropertyDescriptor(object);
            addNamePropertyDescriptor(object);
            addLabelPropertyDescriptor(object);
            addPreconditionPropertyDescriptor(object);
            addForceRefreshPropertyDescriptor(object);
            addElementsToSelectPropertyDescriptor(object);
            addInverseSelectionOrderPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Documentation feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addDocumentationPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DocumentedElement_documentation_feature"), //$NON-NLS-1$
                getString("_UI_DocumentedElement_documentation_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.DOCUMENTED_ELEMENT__DOCUMENTATION, true, true, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_DocumentationPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Name feature. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addNamePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_IdentifiedElement_name_feature"), //$NON-NLS-1$
                getString("_UI_IdentifiedElement_name_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.IDENTIFIED_ELEMENT__NAME, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Label feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addLabelPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_IdentifiedElement_label_feature"), //$NON-NLS-1$
                getString("_UI_IdentifiedElement_label_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.IDENTIFIED_ELEMENT__LABEL, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Precondition feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addPreconditionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_AbstractToolDescription_precondition_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_AbstractToolDescription_precondition_feature", "_UI_AbstractToolDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                ToolPackage.Literals.ABSTRACT_TOOL_DESCRIPTION__PRECONDITION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Force Refresh feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addForceRefreshPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_AbstractToolDescription_forceRefresh_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_AbstractToolDescription_forceRefresh_feature", "_UI_AbstractToolDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                ToolPackage.Literals.ABSTRACT_TOOL_DESCRIPTION__FORCE_REFRESH, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Elements To Select feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addElementsToSelectPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_AbstractToolDescription_elementsToSelect_feature"), //$NON-NLS-1$
                getString("_UI_AbstractToolDescription_elementsToSelect_description"), //$NON-NLS-1$
                ToolPackage.Literals.ABSTRACT_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Inverse Selection Order feature.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addInverseSelectionOrderPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_AbstractToolDescription_inverseSelectionOrder_feature"), //$NON-NLS-1$
                getString("_UI_AbstractToolDescription_inverseSelectionOrder_description"), //$NON-NLS-1$
                ToolPackage.Literals.ABSTRACT_TOOL_DESCRIPTION__INVERSE_SELECTION_ORDER, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
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
            childrenFeatures.add(ToolPackage.Literals.ABSTRACT_TOOL_DESCRIPTION__FILTERS);
            childrenFeatures.add(org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.Literals.CREATE_CELL_TOOL__MASK);
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
     * This returns CreateCellTool.gif. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/CreateCellTool")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public String getText(Object object) {
        String label = new IdentifiedElementQuery((CreateCellTool) object).getLabel();
        return StringUtil.isEmpty(label) ? getString("_UI_CreateCellTool_type") : getString("_UI_CreateCellTool_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

        switch (notification.getFeatureID(CreateCellTool.class)) {
        case org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.CREATE_CELL_TOOL__DOCUMENTATION:
        case org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.CREATE_CELL_TOOL__NAME:
        case org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.CREATE_CELL_TOOL__LABEL:
        case org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.CREATE_CELL_TOOL__PRECONDITION:
        case org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.CREATE_CELL_TOOL__FORCE_REFRESH:
        case org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.CREATE_CELL_TOOL__ELEMENTS_TO_SELECT:
        case org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.CREATE_CELL_TOOL__INVERSE_SELECTION_ORDER:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.CREATE_CELL_TOOL__FILTERS:
        case org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.CREATE_CELL_TOOL__MASK:
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

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.ABSTRACT_TOOL_DESCRIPTION__FILTERS, ToolFactory.eINSTANCE.createToolFilterDescription()));

        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.Literals.CREATE_CELL_TOOL__MASK,
                ToolFactory.eINSTANCE.createEditMaskVariables()));
    }

}
