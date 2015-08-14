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
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.viewpoint.provider.DRepresentationItemProvider;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.diagram.DDiagram} object. <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class DDiagramItemProvider extends DRepresentationItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public DDiagramItemProvider(AdapterFactory adapterFactory) {
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

            addDiagramElementsPropertyDescriptor(object);
            addDescriptionPropertyDescriptor(object);
            addEdgesPropertyDescriptor(object);
            addNodesPropertyDescriptor(object);
            addNodeListElementsPropertyDescriptor(object);
            addContainersPropertyDescriptor(object);
            addCurrentConcernPropertyDescriptor(object);
            addActivatedFiltersPropertyDescriptor(object);
            addAllFiltersPropertyDescriptor(object);
            addActivatedRulesPropertyDescriptor(object);
            addActivateBehaviorsPropertyDescriptor(object);
            addActivatedLayersPropertyDescriptor(object);
            addSynchronizedPropertyDescriptor(object);
            addHiddenElementsPropertyDescriptor(object);
            addIsInLayoutingModePropertyDescriptor(object);
            addHeaderHeightPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Diagram Elements feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addDiagramElementsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DDiagram_diagramElements_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DDiagram_diagramElements_feature", "_UI_DDiagram_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DiagramPackage.Literals.DDIAGRAM__DIAGRAM_ELEMENTS, false, false, false, null, null, null));
    }

    /**
     * This adds a property descriptor for the Description feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addDescriptionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DDiagram_description_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DDiagram_description_feature", "_UI_DDiagram_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DiagramPackage.Literals.DDIAGRAM__DESCRIPTION, true, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Edges feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addEdgesPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_DDiagram_edges_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DDiagram_edges_feature", "_UI_DDiagram_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DiagramPackage.Literals.DDIAGRAM__EDGES, false, false, false, null, null, null));
    }

    /**
     * This adds a property descriptor for the Nodes feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addNodesPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_DDiagram_nodes_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DDiagram_nodes_feature", "_UI_DDiagram_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DiagramPackage.Literals.DDIAGRAM__NODES, false, false, false, null, null, null));
    }

    /**
     * This adds a property descriptor for the Node List Elements feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addNodeListElementsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DDiagram_nodeListElements_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DDiagram_nodeListElements_feature", "_UI_DDiagram_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DiagramPackage.Literals.DDIAGRAM__NODE_LIST_ELEMENTS, false, false, false, null, null, null));
    }

    /**
     * This adds a property descriptor for the Containers feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addContainersPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DDiagram_containers_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DDiagram_containers_feature", "_UI_DDiagram_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DiagramPackage.Literals.DDIAGRAM__CONTAINERS, false, false, false, null, null, null));
    }

    /**
     * This adds a property descriptor for the Current Concern feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addCurrentConcernPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DDiagram_currentConcern_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DDiagram_currentConcern_feature", "_UI_DDiagram_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DiagramPackage.Literals.DDIAGRAM__CURRENT_CONCERN, true, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Activated Filters feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addActivatedFiltersPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DDiagram_activatedFilters_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DDiagram_activatedFilters_feature", "_UI_DDiagram_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DiagramPackage.Literals.DDIAGRAM__ACTIVATED_FILTERS, true, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the All Filters feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addAllFiltersPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DDiagram_allFilters_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DDiagram_allFilters_feature", "_UI_DDiagram_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DiagramPackage.Literals.DDIAGRAM__ALL_FILTERS, false, false, false, null, null, null));
    }

    /**
     * This adds a property descriptor for the Activated Rules feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addActivatedRulesPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DDiagram_activatedRules_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DDiagram_activatedRules_feature", "_UI_DDiagram_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DiagramPackage.Literals.DDIAGRAM__ACTIVATED_RULES, true, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Activate Behaviors feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addActivateBehaviorsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DDiagram_activateBehaviors_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DDiagram_activateBehaviors_feature", "_UI_DDiagram_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DiagramPackage.Literals.DDIAGRAM__ACTIVATE_BEHAVIORS, true, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Activated Layers feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addActivatedLayersPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DDiagram_activatedLayers_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DDiagram_activatedLayers_feature", "_UI_DDiagram_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DiagramPackage.Literals.DDIAGRAM__ACTIVATED_LAYERS, true, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Synchronized feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addSynchronizedPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DDiagram_synchronized_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DDiagram_synchronized_feature", "_UI_DDiagram_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DiagramPackage.Literals.DDIAGRAM__SYNCHRONIZED, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Hidden Elements feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addHiddenElementsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DDiagram_hiddenElements_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DDiagram_hiddenElements_feature", "_UI_DDiagram_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DiagramPackage.Literals.DDIAGRAM__HIDDEN_ELEMENTS, true, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Is In Layouting Mode feature.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addIsInLayoutingModePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DDiagram_isInLayoutingMode_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DDiagram_isInLayoutingMode_feature", "_UI_DDiagram_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DiagramPackage.Literals.DDIAGRAM__IS_IN_LAYOUTING_MODE, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Header Height feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addHeaderHeightPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DDiagram_headerHeight_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DDiagram_headerHeight_feature", "_UI_DDiagram_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DiagramPackage.Literals.DDIAGRAM__HEADER_HEIGHT, true, false, false, ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE, null, null));
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
            childrenFeatures.add(DiagramPackage.Literals.DDIAGRAM__OWNED_DIAGRAM_ELEMENTS);
            childrenFeatures.add(DiagramPackage.Literals.DDIAGRAM__FILTER_VARIABLE_HISTORY);
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
     * This returns DDiagram.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/DDiagram")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getText(Object object) {
        String label = ((DDiagram) object).getName();
        return label == null || label.length() == 0 ? getString("_UI_DDiagram_type") : //$NON-NLS-1$
            getString("_UI_DDiagram_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

        switch (notification.getFeatureID(DDiagram.class)) {
        case DiagramPackage.DDIAGRAM__SYNCHRONIZED:
        case DiagramPackage.DDIAGRAM__IS_IN_LAYOUTING_MODE:
        case DiagramPackage.DDIAGRAM__HEADER_HEIGHT:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case DiagramPackage.DDIAGRAM__OWNED_DIAGRAM_ELEMENTS:
        case DiagramPackage.DDIAGRAM__FILTER_VARIABLE_HISTORY:
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

        newChildDescriptors.add(createChildParameter(DiagramPackage.Literals.DDIAGRAM__OWNED_DIAGRAM_ELEMENTS, DiagramFactory.eINSTANCE.createDNode()));

        newChildDescriptors.add(createChildParameter(DiagramPackage.Literals.DDIAGRAM__OWNED_DIAGRAM_ELEMENTS, DiagramFactory.eINSTANCE.createDNodeContainer()));

        newChildDescriptors.add(createChildParameter(DiagramPackage.Literals.DDIAGRAM__OWNED_DIAGRAM_ELEMENTS, DiagramFactory.eINSTANCE.createDNodeList()));

        newChildDescriptors.add(createChildParameter(DiagramPackage.Literals.DDIAGRAM__OWNED_DIAGRAM_ELEMENTS, DiagramFactory.eINSTANCE.createDNodeListElement()));

        newChildDescriptors.add(createChildParameter(DiagramPackage.Literals.DDIAGRAM__OWNED_DIAGRAM_ELEMENTS, DiagramFactory.eINSTANCE.createDEdge()));

        newChildDescriptors.add(createChildParameter(DiagramPackage.Literals.DDIAGRAM__FILTER_VARIABLE_HISTORY, DiagramFactory.eINSTANCE.createFilterVariableHistory()));
    }

    /**
     * Return the resource locator for this item provider's resources. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ResourceLocator getResourceLocator() {
        return DiagramUIPlugin.INSTANCE;
    }

}
