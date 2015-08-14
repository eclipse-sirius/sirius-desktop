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
import org.eclipse.sirius.table.metamodel.table.description.CreateColumnTool;
import org.eclipse.sirius.table.metamodel.table.description.DeleteColumnTool;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionFactory;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.sirius.table.metamodel.table.description.ElementColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.ForegroundConditionalStyle;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.table.metamodel.table.description.ElementColumnMapping}
 * object. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class ElementColumnMappingItemProvider extends ColumnMappingItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ElementColumnMappingItemProvider(AdapterFactory adapterFactory) {
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

            addDomainClassPropertyDescriptor(object);
            addSemanticCandidatesExpressionPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Domain Class feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addDomainClassPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_ElementColumnMapping_domainClass_feature"), //$NON-NLS-1$
                getString("_UI_ElementColumnMapping_domainClass_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.ELEMENT_COLUMN_MAPPING__DOMAIN_CLASS, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Semantic Candidates Expression
     * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addSemanticCandidatesExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_ElementColumnMapping_semanticCandidatesExpression_feature"), //$NON-NLS-1$
                getString("_UI_ElementColumnMapping_semanticCandidatesExpression_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.ELEMENT_COLUMN_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
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
            childrenFeatures.add(DescriptionPackage.Literals.ELEMENT_COLUMN_MAPPING__CREATE);
            childrenFeatures.add(DescriptionPackage.Literals.ELEMENT_COLUMN_MAPPING__DELETE);
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
     * This returns ElementColumnMapping.gif. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/ElementColumnMapping")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public String getText(Object object) {
        String label = new IdentifiedElementQuery((ElementColumnMapping) object).getLabel();
        return label == null || label.length() == 0 ? getString("_UI_ElementColumnMapping_type") : label; //$NON-NLS-1$
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

        switch (notification.getFeatureID(ElementColumnMapping.class)) {
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__DOMAIN_CLASS:
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__DEFAULT_FOREGROUND:
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__FOREGROUND_CONDITIONAL_STYLE:
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__DEFAULT_BACKGROUND:
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__BACKGROUND_CONDITIONAL_STYLE:
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__CREATE:
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__DELETE:
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

        collectCreateColumnTool(newChildDescriptors);

        collectCreateDeleteColumnTool(newChildDescriptors);
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

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.ELEMENT_COLUMN_MAPPING__CREATE, DescriptionFactory.eINSTANCE.createCreateColumnTool()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.ELEMENT_COLUMN_MAPPING__DELETE, DescriptionFactory.eINSTANCE.createDeleteColumnTool()));
    }

    private void collectCreateColumnTool(Collection<Object> newChildDescriptors) {
        CreateColumnTool createColumnTool = DescriptionFactory.eINSTANCE.createCreateColumnTool();
        new TableToolVariables().doSwitch(createColumnTool);
        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.ELEMENT_COLUMN_MAPPING__CREATE, createColumnTool));
    }

    private void collectCreateDeleteColumnTool(Collection<Object> newChildDescriptors) {
        DeleteColumnTool deleteColumnTool = DescriptionFactory.eINSTANCE.createDeleteColumnTool();
        new TableToolVariables().doSwitch(deleteColumnTool);
        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.ELEMENT_COLUMN_MAPPING__DELETE, deleteColumnTool));
    }

}
