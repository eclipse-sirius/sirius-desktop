/**
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.properties.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.properties.PropertiesFactory;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.RadioDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolFactory;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.properties.RadioDescription} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class RadioDescriptionItemProvider extends WidgetDescriptionItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public RadioDescriptionItemProvider(AdapterFactory adapterFactory) {
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

            addValueExpressionPropertyDescriptor(object);
            addCandidatesExpressionPropertyDescriptor(object);
            addCandidateDisplayExpressionPropertyDescriptor(object);
            addNumberOfColumnsPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Value Expression feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addValueExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_RadioDescription_valueExpression_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_RadioDescription_valueExpression_feature", "_UI_RadioDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        PropertiesPackage.Literals.RADIO_DESCRIPTION__VALUE_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Candidates Expression feature.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addCandidatesExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
                createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_RadioDescription_candidatesExpression_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_RadioDescription_candidatesExpression_feature", "_UI_RadioDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        PropertiesPackage.Literals.RADIO_DESCRIPTION__CANDIDATES_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Candidate Display Expression
     * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addCandidateDisplayExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_RadioDescription_candidateDisplayExpression_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_RadioDescription_candidateDisplayExpression_feature", "_UI_RadioDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                PropertiesPackage.Literals.RADIO_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Number Of Columns feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addNumberOfColumnsPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_RadioDescription_numberOfColumns_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_RadioDescription_numberOfColumns_feature", "_UI_RadioDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        PropertiesPackage.Literals.RADIO_DESCRIPTION__NUMBER_OF_COLUMNS, true, false, false, ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE, null, null));
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
            childrenFeatures.add(PropertiesPackage.Literals.RADIO_DESCRIPTION__INITIAL_OPERATION);
            childrenFeatures.add(PropertiesPackage.Literals.RADIO_DESCRIPTION__STYLE);
            childrenFeatures.add(PropertiesPackage.Literals.RADIO_DESCRIPTION__CONDITIONAL_STYLES);
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
     * This returns RadioDescription.gif. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/RadioDescription")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getText(Object object) {
        String label = ((RadioDescription) object).getIdentifier();
        return label == null || label.length() == 0 ? getString("_UI_RadioDescription_type") : //$NON-NLS-1$
                getString("_UI_RadioDescription_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

        switch (notification.getFeatureID(RadioDescription.class)) {
        case PropertiesPackage.RADIO_DESCRIPTION__VALUE_EXPRESSION:
        case PropertiesPackage.RADIO_DESCRIPTION__CANDIDATES_EXPRESSION:
        case PropertiesPackage.RADIO_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION:
        case PropertiesPackage.RADIO_DESCRIPTION__NUMBER_OF_COLUMNS:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case PropertiesPackage.RADIO_DESCRIPTION__INITIAL_OPERATION:
        case PropertiesPackage.RADIO_DESCRIPTION__STYLE:
        case PropertiesPackage.RADIO_DESCRIPTION__CONDITIONAL_STYLES:
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

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.RADIO_DESCRIPTION__INITIAL_OPERATION, ToolFactory.eINSTANCE.createInitialOperation()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.RADIO_DESCRIPTION__STYLE, PropertiesFactory.eINSTANCE.createRadioWidgetStyle()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.RADIO_DESCRIPTION__CONDITIONAL_STYLES, PropertiesFactory.eINSTANCE.createRadioWidgetConditionalStyle()));
    }

}
