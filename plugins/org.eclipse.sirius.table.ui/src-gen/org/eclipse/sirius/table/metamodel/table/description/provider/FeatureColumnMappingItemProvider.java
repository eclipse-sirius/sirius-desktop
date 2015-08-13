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
import org.eclipse.sirius.table.metamodel.table.description.BackgroundConditionalStyle;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionFactory;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.ForegroundConditionalStyle;
import org.eclipse.sirius.table.metamodel.table.description.LabelEditTool;
import org.eclipse.sirius.table.metamodel.table.description.TableTool;
import org.eclipse.sirius.table.metamodel.table.description.TableVariable;
import org.eclipse.sirius.table.tools.api.interpreter.IInterpreterSiriusTableVariables;
import org.eclipse.sirius.viewpoint.description.tool.ToolFactory;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping}
 * object. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class FeatureColumnMappingItemProvider extends ColumnMappingItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public FeatureColumnMappingItemProvider(AdapterFactory adapterFactory) {
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
            addFeatureNamePropertyDescriptor(object);
            addLabelExpressionPropertyDescriptor(object);
            addFeatureParentExpressionPropertyDescriptor(object);
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
     * This adds a property descriptor for the Feature Name feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addFeatureNamePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_FeatureColumnMapping_featureName_feature"), //$NON-NLS-1$
                getString("_UI_FeatureColumnMapping_featureName_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.FEATURE_COLUMN_MAPPING__FEATURE_NAME, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
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
                getString("_UI_FeatureColumnMapping_labelExpression_feature"), //$NON-NLS-1$
                getString("_UI_FeatureColumnMapping_labelExpression_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.FEATURE_COLUMN_MAPPING__LABEL_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_LabelPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Feature Parent Expression
     * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addFeatureParentExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_FeatureColumnMapping_featureParentExpression_feature"), //$NON-NLS-1$
                getString("_UI_FeatureColumnMapping_featureParentExpression_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.FEATURE_COLUMN_MAPPING__FEATURE_PARENT_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                getString("_UI_AdvancedPropertyCategory"), //$NON-NLS-1$
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
            childrenFeatures.add(DescriptionPackage.Literals.CELL_UPDATER__DIRECT_EDIT);
            childrenFeatures.add(DescriptionPackage.Literals.STYLE_UPDATER__DEFAULT_FOREGROUND);
            childrenFeatures.add(DescriptionPackage.Literals.STYLE_UPDATER__FOREGROUND_CONDITIONAL_STYLE);
            childrenFeatures.add(DescriptionPackage.Literals.STYLE_UPDATER__DEFAULT_BACKGROUND);
            childrenFeatures.add(DescriptionPackage.Literals.STYLE_UPDATER__BACKGROUND_CONDITIONAL_STYLE);
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
     * This returns FeatureColumnMapping.gif. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/FeatureColumnMapping")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public String getText(Object object) {
        String label = new IdentifiedElementQuery((FeatureColumnMapping) object).getLabel();
        return label == null || label.length() == 0 ? getString("_UI_FeatureColumnMapping_type") : label; //$NON-NLS-1$
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

        switch (notification.getFeatureID(FeatureColumnMapping.class)) {
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__CAN_EDIT:
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__FEATURE_NAME:
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__LABEL_EXPRESSION:
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__FEATURE_PARENT_EXPRESSION:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__DIRECT_EDIT:
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__DEFAULT_FOREGROUND:
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__FOREGROUND_CONDITIONAL_STYLE:
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__DEFAULT_BACKGROUND:
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__BACKGROUND_CONDITIONAL_STYLE:
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
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s
     * describing the children that can be created under this object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * Add the mask and the variables to the labelEditTool
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
