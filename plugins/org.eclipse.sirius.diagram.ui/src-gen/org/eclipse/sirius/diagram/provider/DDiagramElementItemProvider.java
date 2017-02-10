/**
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.diagram.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.provider.DRepresentationElementItemProvider;

/**
 * This is the item provider adapter for a {@link org.eclipse.sirius.diagram.DDiagramElement} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class DDiagramElementItemProvider extends DRepresentationElementItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DDiagramElementItemProvider(AdapterFactory adapterFactory) {
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

            addVisiblePropertyDescriptor(object);
            addTooltipTextPropertyDescriptor(object);
            addParentLayersPropertyDescriptor(object);
            addDiagramElementMappingPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Visible feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addVisiblePropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_DDiagramElement_visible_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_DDiagramElement_visible_feature", "_UI_DDiagramElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        DiagramPackage.Literals.DDIAGRAM_ELEMENT__VISIBLE, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Tooltip Text feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addTooltipTextPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_DDiagramElement_tooltipText_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_DDiagramElement_tooltipText_feature", "_UI_DDiagramElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        DiagramPackage.Literals.DDIAGRAM_ELEMENT__TOOLTIP_TEXT, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Parent Layers feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addParentLayersPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_DDiagramElement_parentLayers_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_DDiagramElement_parentLayers_feature", "_UI_DDiagramElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        DiagramPackage.Literals.DDIAGRAM_ELEMENT__PARENT_LAYERS, true, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Diagram Element Mapping feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    protected void addDiagramElementMappingPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
                createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_DDiagramElement_diagramElementMapping_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_DDiagramElement_diagramElementMapping_feature", "_UI_DDiagramElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        DiagramPackage.Literals.DDIAGRAM_ELEMENT__DIAGRAM_ELEMENT_MAPPING, false, false, false, null, null, null));
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
            childrenFeatures.add(DiagramPackage.Literals.DDIAGRAM_ELEMENT__DECORATIONS);
            childrenFeatures.add(DiagramPackage.Literals.DDIAGRAM_ELEMENT__TRANSIENT_DECORATIONS);
            childrenFeatures.add(DiagramPackage.Literals.DDIAGRAM_ELEMENT__GRAPHICAL_FILTERS);
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
        // Check the type of the specified child object and return the proper feature to use for
        // adding (see {@link AddCommand}) it as a child.

        return super.getChildFeature(object, child);
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getText(Object object) {
        String label = ((DDiagramElement) object).getName();
        return label == null || label.length() == 0 ? getString("_UI_DDiagramElement_type") : //$NON-NLS-1$
                getString("_UI_DDiagramElement_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

        switch (notification.getFeatureID(DDiagramElement.class)) {
        case DiagramPackage.DDIAGRAM_ELEMENT__VISIBLE:
        case DiagramPackage.DDIAGRAM_ELEMENT__TOOLTIP_TEXT:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case DiagramPackage.DDIAGRAM_ELEMENT__DECORATIONS:
        case DiagramPackage.DDIAGRAM_ELEMENT__TRANSIENT_DECORATIONS:
        case DiagramPackage.DDIAGRAM_ELEMENT__GRAPHICAL_FILTERS:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
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

        newChildDescriptors.add(createChildParameter(DiagramPackage.Literals.DDIAGRAM_ELEMENT__DECORATIONS, ViewpointFactory.eINSTANCE.createDecoration()));

        newChildDescriptors.add(createChildParameter(DiagramPackage.Literals.DDIAGRAM_ELEMENT__TRANSIENT_DECORATIONS, ViewpointFactory.eINSTANCE.createDecoration()));

        newChildDescriptors.add(createChildParameter(DiagramPackage.Literals.DDIAGRAM_ELEMENT__GRAPHICAL_FILTERS, DiagramFactory.eINSTANCE.createHideFilter()));

        newChildDescriptors.add(createChildParameter(DiagramPackage.Literals.DDIAGRAM_ELEMENT__GRAPHICAL_FILTERS, DiagramFactory.eINSTANCE.createHideLabelFilter()));

        newChildDescriptors.add(createChildParameter(DiagramPackage.Literals.DDIAGRAM_ELEMENT__GRAPHICAL_FILTERS, DiagramFactory.eINSTANCE.createFoldingPointFilter()));

        newChildDescriptors.add(createChildParameter(DiagramPackage.Literals.DDIAGRAM_ELEMENT__GRAPHICAL_FILTERS, DiagramFactory.eINSTANCE.createFoldingFilter()));

        newChildDescriptors.add(createChildParameter(DiagramPackage.Literals.DDIAGRAM_ELEMENT__GRAPHICAL_FILTERS, DiagramFactory.eINSTANCE.createAppliedCompositeFilters()));

        newChildDescriptors.add(createChildParameter(DiagramPackage.Literals.DDIAGRAM_ELEMENT__GRAPHICAL_FILTERS, DiagramFactory.eINSTANCE.createAbsoluteBoundsFilter()));

        newChildDescriptors.add(createChildParameter(DiagramPackage.Literals.DDIAGRAM_ELEMENT__GRAPHICAL_FILTERS, DiagramFactory.eINSTANCE.createCollapseFilter()));

        newChildDescriptors.add(createChildParameter(DiagramPackage.Literals.DDIAGRAM_ELEMENT__GRAPHICAL_FILTERS, DiagramFactory.eINSTANCE.createIndirectlyCollapseFilter()));
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

        boolean qualify = childFeature == DiagramPackage.Literals.DDIAGRAM_ELEMENT__DECORATIONS || childFeature == DiagramPackage.Literals.DDIAGRAM_ELEMENT__TRANSIENT_DECORATIONS;

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
        return DiagramUIPlugin.INSTANCE;
    }

}
