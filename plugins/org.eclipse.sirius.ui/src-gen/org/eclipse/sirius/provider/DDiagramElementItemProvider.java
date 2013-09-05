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

import java.util.Collection;
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

import org.eclipse.sirius.DDiagramElement;
import org.eclipse.sirius.SiriusFactory;
import org.eclipse.sirius.SiriusPackage;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.DDiagramElement} object. <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class DDiagramElementItemProvider extends DRepresentationElementItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider,
        IItemLabelProvider, IItemPropertySource {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DDiagramElementItemProvider(AdapterFactory adapterFactory) {
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

            addVisiblePropertyDescriptor(object);
            addTooltipTextPropertyDescriptor(object);
            addParentLayersPropertyDescriptor(object);
            addDiagramElementMappingPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Visible feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addVisiblePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DDiagramElement_visible_feature"), getString("_UI_PropertyDescriptor_description", "_UI_DDiagramElement_visible_feature", "_UI_DDiagramElement_type"),
                SiriusPackage.Literals.DDIAGRAM_ELEMENT__VISIBLE, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Tooltip Text feature. <!--
     * begin-user-doc -->
     * 
     * @since 2.0 <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addTooltipTextPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DDiagramElement_tooltipText_feature"), getString("_UI_PropertyDescriptor_description", "_UI_DDiagramElement_tooltipText_feature", "_UI_DDiagramElement_type"),
                SiriusPackage.Literals.DDIAGRAM_ELEMENT__TOOLTIP_TEXT, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Parent Layers feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addParentLayersPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DDiagramElement_parentLayers_feature"), getString("_UI_PropertyDescriptor_description", "_UI_DDiagramElement_parentLayers_feature", "_UI_DDiagramElement_type"),
                SiriusPackage.Literals.DDIAGRAM_ELEMENT__PARENT_LAYERS, true, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Diagram Element Mapping feature.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addDiagramElementMappingPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DDiagramElement_diagramElementMapping_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_DDiagramElement_diagramElementMapping_feature", "_UI_DDiagramElement_type"),
                SiriusPackage.Literals.DDIAGRAM_ELEMENT__DIAGRAM_ELEMENT_MAPPING, false, false, false, null, null, null));
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
            childrenFeatures.add(SiriusPackage.Literals.DNAVIGABLE__OWNED_NAVIGATION_LINKS);
            childrenFeatures.add(SiriusPackage.Literals.DDIAGRAM_ELEMENT__DECORATIONS);
            childrenFeatures.add(SiriusPackage.Literals.DDIAGRAM_ELEMENT__GRAPHICAL_FILTERS);
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
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getText(Object object) {
        String label = ((DDiagramElement) object).getName();
        return label == null || label.length() == 0 ? getString("_UI_DDiagramElement_type") : getString("_UI_DDiagramElement_type") + " " + label;
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

        switch (notification.getFeatureID(DDiagramElement.class)) {
        case SiriusPackage.DDIAGRAM_ELEMENT__VISIBLE:
        case SiriusPackage.DDIAGRAM_ELEMENT__TOOLTIP_TEXT:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case SiriusPackage.DDIAGRAM_ELEMENT__OWNED_NAVIGATION_LINKS:
        case SiriusPackage.DDIAGRAM_ELEMENT__DECORATIONS:
        case SiriusPackage.DDIAGRAM_ELEMENT__GRAPHICAL_FILTERS:
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

        newChildDescriptors.add(createChildParameter(SiriusPackage.Literals.DNAVIGABLE__OWNED_NAVIGATION_LINKS, SiriusFactory.eINSTANCE.createDEObjectLink()));

        newChildDescriptors.add(createChildParameter(SiriusPackage.Literals.DNAVIGABLE__OWNED_NAVIGATION_LINKS, SiriusFactory.eINSTANCE.createDDiagramLink()));

        newChildDescriptors.add(createChildParameter(SiriusPackage.Literals.DNAVIGABLE__OWNED_NAVIGATION_LINKS, SiriusFactory.eINSTANCE.createDSourceFileLink()));

        newChildDescriptors.add(createChildParameter(SiriusPackage.Literals.DDIAGRAM_ELEMENT__DECORATIONS, SiriusFactory.eINSTANCE.createDecoration()));

        newChildDescriptors.add(createChildParameter(SiriusPackage.Literals.DDIAGRAM_ELEMENT__GRAPHICAL_FILTERS, SiriusFactory.eINSTANCE.createHideFilter()));

        newChildDescriptors.add(createChildParameter(SiriusPackage.Literals.DDIAGRAM_ELEMENT__GRAPHICAL_FILTERS, SiriusFactory.eINSTANCE.createHideLabelFilter()));

        newChildDescriptors.add(createChildParameter(SiriusPackage.Literals.DDIAGRAM_ELEMENT__GRAPHICAL_FILTERS, SiriusFactory.eINSTANCE.createFoldingPointFilter()));

        newChildDescriptors.add(createChildParameter(SiriusPackage.Literals.DDIAGRAM_ELEMENT__GRAPHICAL_FILTERS, SiriusFactory.eINSTANCE.createFoldingFilter()));

        newChildDescriptors.add(createChildParameter(SiriusPackage.Literals.DDIAGRAM_ELEMENT__GRAPHICAL_FILTERS, SiriusFactory.eINSTANCE.createAppliedCompositeFilters()));

        newChildDescriptors.add(createChildParameter(SiriusPackage.Literals.DDIAGRAM_ELEMENT__GRAPHICAL_FILTERS, SiriusFactory.eINSTANCE.createAbsoluteBoundsFilter()));

        newChildDescriptors.add(createChildParameter(SiriusPackage.Literals.DDIAGRAM_ELEMENT__GRAPHICAL_FILTERS, SiriusFactory.eINSTANCE.createCollapseFilter()));

        newChildDescriptors.add(createChildParameter(SiriusPackage.Literals.DDIAGRAM_ELEMENT__GRAPHICAL_FILTERS, SiriusFactory.eINSTANCE.createIndirectlyCollapseFilter()));
    }

}
