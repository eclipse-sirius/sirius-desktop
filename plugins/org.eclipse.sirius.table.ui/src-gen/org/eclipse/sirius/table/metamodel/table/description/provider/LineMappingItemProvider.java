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
import org.eclipse.sirius.table.business.internal.metamodel.TableToolVariables;
import org.eclipse.sirius.table.metamodel.table.description.BackgroundConditionalStyle;
import org.eclipse.sirius.table.metamodel.table.description.CreateLineTool;
import org.eclipse.sirius.table.metamodel.table.description.DeleteLineTool;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionFactory;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.sirius.table.metamodel.table.description.ForegroundConditionalStyle;
import org.eclipse.sirius.table.metamodel.table.description.LineMapping;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.table.metamodel.table.description.LineMapping}
 * object. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class LineMappingItemProvider extends TableMappingItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public LineMappingItemProvider(AdapterFactory adapterFactory) {
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

            addReusedSubLinesPropertyDescriptor(object);
            addReusedInMappingsPropertyDescriptor(object);
            addDomainClassPropertyDescriptor(object);
            addSemanticCandidatesExpressionPropertyDescriptor(object);
            addHeaderLabelExpressionPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Reused Sub Lines feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addReusedSubLinesPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_LineMapping_reusedSubLines_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_LineMapping_reusedSubLines_feature", "_UI_LineMapping_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DescriptionPackage.Literals.LINE_MAPPING__REUSED_SUB_LINES, true, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Domain Class feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addDomainClassPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_LineMapping_domainClass_feature"), //$NON-NLS-1$
                getString("_UI_LineMapping_domainClass_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.LINE_MAPPING__DOMAIN_CLASS, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Semantic Candidates Expression
     * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addSemanticCandidatesExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_LineMapping_semanticCandidatesExpression_feature"), //$NON-NLS-1$
                getString("_UI_LineMapping_semanticCandidatesExpression_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.LINE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Header Label Expression feature.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addHeaderLabelExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_LineMapping_headerLabelExpression_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_LineMapping_headerLabelExpression_feature", "_UI_LineMapping_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DescriptionPackage.Literals.LINE_MAPPING__HEADER_LABEL_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_LabelPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Reused In Mappings feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addReusedInMappingsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_LineMapping_reusedInMappings_feature"), //$NON-NLS-1$
                getString("_UI_LineMapping_reusedInMappings_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.LINE_MAPPING__REUSED_IN_MAPPINGS, true, false, true, null, getString("_UI_ImportPropertyCategory"), //$NON-NLS-1$
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
            childrenFeatures.add(DescriptionPackage.Literals.STYLE_UPDATER__DEFAULT_FOREGROUND);
            childrenFeatures.add(DescriptionPackage.Literals.STYLE_UPDATER__FOREGROUND_CONDITIONAL_STYLE);
            childrenFeatures.add(DescriptionPackage.Literals.STYLE_UPDATER__DEFAULT_BACKGROUND);
            childrenFeatures.add(DescriptionPackage.Literals.STYLE_UPDATER__BACKGROUND_CONDITIONAL_STYLE);
            childrenFeatures.add(DescriptionPackage.Literals.LINE_MAPPING__OWNED_SUB_LINES);
            childrenFeatures.add(DescriptionPackage.Literals.LINE_MAPPING__CREATE);
            childrenFeatures.add(DescriptionPackage.Literals.LINE_MAPPING__DELETE);
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
     * This returns LineMapping.gif. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/LineMapping")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public String getText(Object object) {
        String label = new IdentifiedElementQuery((LineMapping) object).getLabel();
        return label == null || label.length() == 0 ? getString("_UI_LineMapping_type") : label; //$NON-NLS-1$
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

        switch (notification.getFeatureID(LineMapping.class)) {
        case DescriptionPackage.LINE_MAPPING__DOMAIN_CLASS:
        case DescriptionPackage.LINE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION:
        case DescriptionPackage.LINE_MAPPING__HEADER_LABEL_EXPRESSION:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case DescriptionPackage.LINE_MAPPING__DEFAULT_FOREGROUND:
        case DescriptionPackage.LINE_MAPPING__FOREGROUND_CONDITIONAL_STYLE:
        case DescriptionPackage.LINE_MAPPING__DEFAULT_BACKGROUND:
        case DescriptionPackage.LINE_MAPPING__BACKGROUND_CONDITIONAL_STYLE:
        case DescriptionPackage.LINE_MAPPING__OWNED_SUB_LINES:
        case DescriptionPackage.LINE_MAPPING__CREATE:
        case DescriptionPackage.LINE_MAPPING__DELETE:
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
     * @not-generated
     */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.STYLE_UPDATER__DEFAULT_FOREGROUND, DescriptionFactory.eINSTANCE.createForegroundStyleDescription()));

        ForegroundConditionalStyle foregroundConditionalStyle = DescriptionFactory.eINSTANCE.createForegroundConditionalStyle();
        foregroundConditionalStyle.setStyle(DescriptionFactory.eINSTANCE.createForegroundStyleDescription());
        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.STYLE_UPDATER__FOREGROUND_CONDITIONAL_STYLE, foregroundConditionalStyle));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.STYLE_UPDATER__DEFAULT_BACKGROUND, DescriptionFactory.eINSTANCE.createBackgroundStyleDescription()));

        BackgroundConditionalStyle backgroundConditionalStyle = DescriptionFactory.eINSTANCE.createBackgroundConditionalStyle();
        backgroundConditionalStyle.setStyle(DescriptionFactory.eINSTANCE.createBackgroundStyleDescription());
        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.STYLE_UPDATER__BACKGROUND_CONDITIONAL_STYLE, backgroundConditionalStyle));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.LINE_MAPPING__OWNED_SUB_LINES, DescriptionFactory.eINSTANCE.createLineMapping()));

        collectCreateLineTool(newChildDescriptors);

        collectCreateDeleteLineTool(newChildDescriptors);
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s
     * describing the children that can be created under this object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void collectNewChildDescriptorsGen(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.STYLE_UPDATER__DEFAULT_FOREGROUND, DescriptionFactory.eINSTANCE.createForegroundStyleDescription()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.STYLE_UPDATER__FOREGROUND_CONDITIONAL_STYLE, DescriptionFactory.eINSTANCE.createForegroundConditionalStyle()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.STYLE_UPDATER__DEFAULT_BACKGROUND, DescriptionFactory.eINSTANCE.createBackgroundStyleDescription()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.STYLE_UPDATER__BACKGROUND_CONDITIONAL_STYLE, DescriptionFactory.eINSTANCE.createBackgroundConditionalStyle()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.LINE_MAPPING__OWNED_SUB_LINES, DescriptionFactory.eINSTANCE.createLineMapping()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.LINE_MAPPING__CREATE, DescriptionFactory.eINSTANCE.createCreateLineTool()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.LINE_MAPPING__DELETE, DescriptionFactory.eINSTANCE.createDeleteLineTool()));
    }

    private void collectCreateLineTool(Collection<Object> newChildDescriptors) {
        CreateLineTool createLineTool = DescriptionFactory.eINSTANCE.createCreateLineTool();
        new TableToolVariables().doSwitch(createLineTool);
        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.LINE_MAPPING__CREATE, createLineTool));
    }

    private void collectCreateDeleteLineTool(Collection<Object> newChildDescriptors) {
        DeleteLineTool deleteLineTool = DescriptionFactory.eINSTANCE.createDeleteLineTool();
        new TableToolVariables().doSwitch(deleteLineTool);
        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.LINE_MAPPING__DELETE, deleteLineTool));

    }

}
