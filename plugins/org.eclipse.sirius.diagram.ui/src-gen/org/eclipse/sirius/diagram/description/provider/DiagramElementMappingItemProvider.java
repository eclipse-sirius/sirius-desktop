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
package org.eclipse.sirius.diagram.description.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.provider.RepresentationElementMappingItemProvider;

/**
 * This is the item provider adapter for a {@link org.eclipse.sirius.diagram.description.DiagramElementMapping} object.
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class DiagramElementMappingItemProvider extends RepresentationElementMappingItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DiagramElementMappingItemProvider(AdapterFactory adapterFactory) {
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

            addPasteDescriptionsPropertyDescriptor(object);
            addPreconditionExpressionPropertyDescriptor(object);
            addDeletionDescriptionPropertyDescriptor(object);
            addLabelDirectEditPropertyDescriptor(object);
            addSemanticCandidatesExpressionPropertyDescriptor(object);
            addSemanticElementsPropertyDescriptor(object);
            addDoubleClickDescriptionPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Paste Descriptions feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addPasteDescriptionsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_PasteTargetDescription_pasteDescriptions_feature"), //$NON-NLS-1$
                getString("_UI_PasteTargetDescription_pasteDescriptions_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.PASTE_TARGET_DESCRIPTION__PASTE_DESCRIPTIONS, true, false, true, null, getString("_UI_BehaviorPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Precondition Expression feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    protected void addPreconditionExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DiagramElementMapping_preconditionExpression_feature"), //$NON-NLS-1$
                getString("_UI_DiagramElementMapping_preconditionExpression_description"), //$NON-NLS-1$
                org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_ELEMENT_MAPPING__PRECONDITION_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                getString("_UI_AdvancedPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Deletion Description feature. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    protected void addDeletionDescriptionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DiagramElementMapping_deletionDescription_feature"), //$NON-NLS-1$
                getString("_UI_DiagramElementMapping_deletionDescription_description"), //$NON-NLS-1$
                org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_ELEMENT_MAPPING__DELETION_DESCRIPTION, true, false, true, null, getString("_UI_BehaviorPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Label Direct Edit feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addLabelDirectEditPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DiagramElementMapping_labelDirectEdit_feature"), //$NON-NLS-1$
                getString("_UI_DiagramElementMapping_labelDirectEdit_description"), //$NON-NLS-1$
                org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_ELEMENT_MAPPING__LABEL_DIRECT_EDIT, true, false, true, null, getString("_UI_BehaviorPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Semantic Candidates Expression feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    protected void addSemanticCandidatesExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DiagramElementMapping_semanticCandidatesExpression_feature"), //$NON-NLS-1$
                getString("_UI_DiagramElementMapping_semanticCandidatesExpression_description"), //$NON-NLS-1$
                org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_ELEMENT_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION, true, false, false,
                ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Semantic Elements feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addSemanticElementsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DiagramElementMapping_semanticElements_feature"), //$NON-NLS-1$
                getString("_UI_DiagramElementMapping_semanticElements_description"), //$NON-NLS-1$
                org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_ELEMENT_MAPPING__SEMANTIC_ELEMENTS, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Double Click Description feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    protected void addDoubleClickDescriptionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DiagramElementMapping_doubleClickDescription_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_DiagramElementMapping_doubleClickDescription_feature", "_UI_DiagramElementMapping_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_ELEMENT_MAPPING__DOUBLE_CLICK_DESCRIPTION, true, false, true, null,
                getString("_UI_BehaviorPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public String getText(Object object) {
        String label = new IdentifiedElementQuery((IdentifiedElement) object).getLabel();
        return StringUtil.isEmpty(label) ? getString("_UI_DiagramElementMapping_type") : getString("_UI_DiagramElementMapping_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

        switch (notification.getFeatureID(DiagramElementMapping.class)) {
        case org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__PRECONDITION_EXPRESSION:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__CREATE_ELEMENTS:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__SEMANTIC_ELEMENTS:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__SYNCHRONIZATION_LOCK:
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
