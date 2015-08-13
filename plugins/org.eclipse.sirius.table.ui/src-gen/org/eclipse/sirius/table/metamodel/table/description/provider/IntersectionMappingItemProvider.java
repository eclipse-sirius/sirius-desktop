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
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.table.business.api.helper.TableHelper;
import org.eclipse.sirius.table.business.internal.metamodel.TableToolVariables;
import org.eclipse.sirius.table.metamodel.table.description.BackgroundConditionalStyle;
import org.eclipse.sirius.table.metamodel.table.description.CreateCellTool;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionFactory;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.sirius.table.metamodel.table.description.ForegroundConditionalStyle;
import org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping;
import org.eclipse.sirius.table.metamodel.table.description.LabelEditTool;
import org.eclipse.sirius.table.metamodel.table.description.TableTool;
import org.eclipse.sirius.table.metamodel.table.description.TableVariable;
import org.eclipse.sirius.table.tools.api.interpreter.IInterpreterSiriusTableVariables;
import org.eclipse.sirius.viewpoint.description.tool.ToolFactory;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping}
 * object. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class IntersectionMappingItemProvider extends TableMappingItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public IntersectionMappingItemProvider(AdapterFactory adapterFactory) {
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

            addCanEditPropertyDescriptor(object);
            addLineMappingPropertyDescriptor(object);
            addColumnMappingPropertyDescriptor(object);
            addLabelExpressionPropertyDescriptor(object);
            addUseDomainClassPropertyDescriptor(object);
            addColumnFinderExpressionPropertyDescriptor(object);
            addLineFinderExpressionPropertyDescriptor(object);
            addSemanticCandidatesExpressionPropertyDescriptor(object);
            addDomainClassPropertyDescriptor(object);
            addPreconditionExpressionPropertyDescriptor(object);
            addCreatePropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Can Edit feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addCanEditPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_CellUpdater_canEdit_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_CellUpdater_canEdit_feature", "_UI_CellUpdater_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DescriptionPackage.Literals.CELL_UPDATER__CAN_EDIT, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_BehaviorPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Line Mapping feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addLineMappingPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_IntersectionMapping_lineMapping_feature"), //$NON-NLS-1$
                getString("_UI_IntersectionMapping_lineMapping_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.INTERSECTION_MAPPING__LINE_MAPPING, true, false, true, null, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Column Mapping feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addColumnMappingPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_IntersectionMapping_columnMapping_feature"), //$NON-NLS-1$
                getString("_UI_IntersectionMapping_columnMapping_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.INTERSECTION_MAPPING__COLUMN_MAPPING, true, false, true, null, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Label Expression feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addLabelExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_IntersectionMapping_labelExpression_feature"), //$NON-NLS-1$
                getString("_UI_IntersectionMapping_labelExpression_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.INTERSECTION_MAPPING__LABEL_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_LabelPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Use Domain Class feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addUseDomainClassPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_IntersectionMapping_useDomainClass_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_IntersectionMapping_useDomainClass_feature", "_UI_IntersectionMapping_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DescriptionPackage.Literals.INTERSECTION_MAPPING__USE_DOMAIN_CLASS, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, getString("_UI_DomainBasedPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Column Finder Expression feature.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addColumnFinderExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_IntersectionMapping_columnFinderExpression_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_IntersectionMapping_columnFinderExpression_feature", "_UI_IntersectionMapping_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DescriptionPackage.Literals.INTERSECTION_MAPPING__COLUMN_FINDER_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Line Finder Expression feature.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addLineFinderExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_IntersectionMapping_lineFinderExpression_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_IntersectionMapping_lineFinderExpression_feature", "_UI_IntersectionMapping_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DescriptionPackage.Literals.INTERSECTION_MAPPING__LINE_FINDER_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_DomainBasedPropertyCategory"), //$NON-NLS-1$
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
                getString("_UI_IntersectionMapping_semanticCandidatesExpression_feature"), //$NON-NLS-1$
                getString("_UI_IntersectionMapping_semanticCandidatesExpression_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.INTERSECTION_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                getString("_UI_DomainBasedPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Domain Class feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addDomainClassPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_IntersectionMapping_domainClass_feature"), //$NON-NLS-1$
                getString("_UI_IntersectionMapping_domainClass_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.INTERSECTION_MAPPING__DOMAIN_CLASS, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_DomainBasedPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Precondition Expression feature.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addPreconditionExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_IntersectionMapping_preconditionExpression_feature"), //$NON-NLS-1$
                getString("_UI_IntersectionMapping_preconditionExpression_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.INTERSECTION_MAPPING__PRECONDITION_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_AdvancedPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Create feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addCreatePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_IntersectionMapping_create_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_IntersectionMapping_create_feature", "_UI_IntersectionMapping_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DescriptionPackage.Literals.INTERSECTION_MAPPING__CREATE, true, false, true, null, null, null));
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
            childrenFeatures.add(DescriptionPackage.Literals.CELL_UPDATER__DIRECT_EDIT);
            childrenFeatures.add(DescriptionPackage.Literals.STYLE_UPDATER__DEFAULT_FOREGROUND);
            childrenFeatures.add(DescriptionPackage.Literals.STYLE_UPDATER__FOREGROUND_CONDITIONAL_STYLE);
            childrenFeatures.add(DescriptionPackage.Literals.STYLE_UPDATER__DEFAULT_BACKGROUND);
            childrenFeatures.add(DescriptionPackage.Literals.STYLE_UPDATER__BACKGROUND_CONDITIONAL_STYLE);
            childrenFeatures.add(DescriptionPackage.Literals.INTERSECTION_MAPPING__CREATE);
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
     * This returns IntersectionMapping.gif. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/IntersectionMapping")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public String getText(Object object) {
        String label = new IdentifiedElementQuery((IntersectionMapping) object).getLabel();
        return label == null || label.length() == 0 ? getString("_UI_IntersectionMapping_type") : label; //$NON-NLS-1$
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

        switch (notification.getFeatureID(IntersectionMapping.class)) {
        case DescriptionPackage.INTERSECTION_MAPPING__CAN_EDIT:
        case DescriptionPackage.INTERSECTION_MAPPING__LABEL_EXPRESSION:
        case DescriptionPackage.INTERSECTION_MAPPING__USE_DOMAIN_CLASS:
        case DescriptionPackage.INTERSECTION_MAPPING__COLUMN_FINDER_EXPRESSION:
        case DescriptionPackage.INTERSECTION_MAPPING__LINE_FINDER_EXPRESSION:
        case DescriptionPackage.INTERSECTION_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION:
        case DescriptionPackage.INTERSECTION_MAPPING__DOMAIN_CLASS:
        case DescriptionPackage.INTERSECTION_MAPPING__PRECONDITION_EXPRESSION:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case DescriptionPackage.INTERSECTION_MAPPING__DIRECT_EDIT:
        case DescriptionPackage.INTERSECTION_MAPPING__DEFAULT_FOREGROUND:
        case DescriptionPackage.INTERSECTION_MAPPING__FOREGROUND_CONDITIONAL_STYLE:
        case DescriptionPackage.INTERSECTION_MAPPING__DEFAULT_BACKGROUND:
        case DescriptionPackage.INTERSECTION_MAPPING__BACKGROUND_CONDITIONAL_STYLE:
        case DescriptionPackage.INTERSECTION_MAPPING__CREATE:
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
    protected void collectNewChildDescriptorsGen(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.CELL_UPDATER__DIRECT_EDIT, DescriptionFactory.eINSTANCE.createLabelEditTool()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.STYLE_UPDATER__DEFAULT_FOREGROUND, DescriptionFactory.eINSTANCE.createForegroundStyleDescription()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.STYLE_UPDATER__FOREGROUND_CONDITIONAL_STYLE, DescriptionFactory.eINSTANCE.createForegroundConditionalStyle()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.STYLE_UPDATER__DEFAULT_BACKGROUND, DescriptionFactory.eINSTANCE.createBackgroundStyleDescription()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.STYLE_UPDATER__BACKGROUND_CONDITIONAL_STYLE, DescriptionFactory.eINSTANCE.createBackgroundConditionalStyle()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.INTERSECTION_MAPPING__CREATE, DescriptionFactory.eINSTANCE.createCreateCellTool()));
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s
     * describing the children that can be created under this object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * Add the variables to the labelEditTool
     *
     * @not-generated
     */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);

        LabelEditTool labelEditTool = DescriptionFactory.eINSTANCE.createLabelEditTool();
        labelEditTool.setMask(ToolFactory.eINSTANCE.createEditMaskVariables());
        addVariableDescriptor(labelEditTool, IInterpreterSiriusVariables.ELEMENT, "The currently edited element.");
        addVariableDescriptor(labelEditTool, IInterpreterSiriusTableVariables.LINE_SEMANTIC, "The semantic element corresponding to the line.");
        addVariableDescriptor(labelEditTool, IInterpreterSiriusTableVariables.COLUMN_SEMANTIC, "The semantic element corresponding to the column (only available for Intersection Mapping).");
        addVariableDescriptor(labelEditTool, IInterpreterSiriusVariables.ROOT, "The semantic element of the table.");

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.CELL_UPDATER__DIRECT_EDIT, labelEditTool));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.STYLE_UPDATER__DEFAULT_FOREGROUND, DescriptionFactory.eINSTANCE.createForegroundStyleDescription()));

        ForegroundConditionalStyle foregroundConditionalStyle = DescriptionFactory.eINSTANCE.createForegroundConditionalStyle();
        foregroundConditionalStyle.setStyle(DescriptionFactory.eINSTANCE.createForegroundStyleDescription());
        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.STYLE_UPDATER__FOREGROUND_CONDITIONAL_STYLE, foregroundConditionalStyle));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.STYLE_UPDATER__DEFAULT_BACKGROUND, DescriptionFactory.eINSTANCE.createBackgroundStyleDescription()));

        BackgroundConditionalStyle backgroundConditionalStyle = DescriptionFactory.eINSTANCE.createBackgroundConditionalStyle();
        backgroundConditionalStyle.setStyle(DescriptionFactory.eINSTANCE.createBackgroundStyleDescription());
        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.STYLE_UPDATER__BACKGROUND_CONDITIONAL_STYLE, backgroundConditionalStyle));

        collectCreateCellTool(newChildDescriptors);

    }

    private void collectCreateCellTool(Collection<Object> newChildDescriptors) {
        final CreateCellTool createCellTool = DescriptionFactory.eINSTANCE.createCreateCellTool();
        createCellTool.setMask(ToolFactory.eINSTANCE.createEditMaskVariables());
        new TableToolVariables().doSwitch(createCellTool);
        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.INTERSECTION_MAPPING__CREATE, createCellTool));
    }

    private void addVariableDescriptor(final TableTool tool, final String name, final String documentation) {
        if (TableHelper.getVariable(tool, name) == null) {
            final TableVariable newVar = DescriptionFactory.eINSTANCE.createTableVariable();
            newVar.setName(name);
            newVar.setDocumentation(documentation);
            tool.getVariables().add(newVar);
        }
    }

}
