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
package org.eclipse.sirius.diagram.sequence.template.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.diagram.sequence.template.TExecutionMapping;
import org.eclipse.sirius.diagram.sequence.template.TemplateFactory;
import org.eclipse.sirius.diagram.sequence.template.TemplatePackage;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.diagram.sequence.template.TExecutionMapping}
 * object. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class TExecutionMappingItemProvider extends TAbstractMappingItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public TExecutionMappingItemProvider(AdapterFactory adapterFactory) {
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
            addRecursivePropertyDescriptor(object);
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
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_TExecutionMapping_startingEndFinderExpression_feature"), //$NON-NLS-1$
                getString("_UI_TExecutionMapping_startingEndFinderExpression_description"), //$NON-NLS-1$
                TemplatePackage.Literals.TEXECUTION_MAPPING__STARTING_END_FINDER_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Finishing End Finder Expression
     * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addFinishingEndFinderExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_TExecutionMapping_finishingEndFinderExpression_feature"), //$NON-NLS-1$
                getString("_UI_TExecutionMapping_finishingEndFinderExpression_description"), //$NON-NLS-1$
                TemplatePackage.Literals.TEXECUTION_MAPPING__FINISHING_END_FINDER_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Recursive feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addRecursivePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_TExecutionMapping_recursive_feature"), //$NON-NLS-1$
                getString("_UI_TExecutionMapping_recursive_description"), //$NON-NLS-1$
                TemplatePackage.Literals.TEXECUTION_MAPPING__RECURSIVE, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
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
            childrenFeatures.add(TemplatePackage.Literals.TEXECUTION_MAPPING__EXECUTION_MAPPINGS);
            childrenFeatures.add(TemplatePackage.Literals.TEXECUTION_MAPPING__STYLE);
            childrenFeatures.add(TemplatePackage.Literals.TEXECUTION_MAPPING__CONDITIONAL_STYLES);
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
     * This returns TExecutionMapping.gif. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/TExecutionMapping")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public String getText(Object object) {
        String label = ((TExecutionMapping) object).getName();
        return label == null || label.length() == 0 ? getString("_UI_TExecutionMapping_type") : label; //$NON-NLS-1$
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

        switch (notification.getFeatureID(TExecutionMapping.class)) {
        case TemplatePackage.TEXECUTION_MAPPING__STARTING_END_FINDER_EXPRESSION:
        case TemplatePackage.TEXECUTION_MAPPING__FINISHING_END_FINDER_EXPRESSION:
        case TemplatePackage.TEXECUTION_MAPPING__RECURSIVE:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case TemplatePackage.TEXECUTION_MAPPING__EXECUTION_MAPPINGS:
        case TemplatePackage.TEXECUTION_MAPPING__STYLE:
        case TemplatePackage.TEXECUTION_MAPPING__CONDITIONAL_STYLES:
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

        newChildDescriptors.add(createChildParameter(TemplatePackage.Literals.TEXECUTION_MAPPING__EXECUTION_MAPPINGS, TemplateFactory.eINSTANCE.createTExecutionMapping()));

        newChildDescriptors.add(createChildParameter(TemplatePackage.Literals.TEXECUTION_MAPPING__STYLE, TemplateFactory.eINSTANCE.createTExecutionStyle()));

        newChildDescriptors.add(createChildParameter(TemplatePackage.Literals.TEXECUTION_MAPPING__CONDITIONAL_STYLES, TemplateFactory.eINSTANCE.createTConditionalExecutionStyle()));
    }

}
