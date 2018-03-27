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
import org.eclipse.emf.edit.provider.IItemStyledLabelProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.StyledString;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.properties.AbstractGroupDescription;
import org.eclipse.sirius.properties.PropertiesFactory;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.provider.IdentifiedElementItemProvider;

/**
 * This is the item provider adapter for a {@link org.eclipse.sirius.properties.AbstractGroupDescription} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class AbstractGroupDescriptionItemProvider extends IdentifiedElementItemProvider implements IItemStyledLabelProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public AbstractGroupDescriptionItemProvider(AdapterFactory adapterFactory) {
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

            addDocumentationPropertyDescriptor(object);
            addLabelExpressionPropertyDescriptor(object);
            addDomainClassPropertyDescriptor(object);
            addSemanticCandidateExpressionPropertyDescriptor(object);
            addPreconditionExpressionPropertyDescriptor(object);
            addExtendsPropertyDescriptor(object);
            addFilterControlsFromExtendedGroupExpressionPropertyDescriptor(object);
            addFilterValidationRulesFromExtendedGroupExpressionPropertyDescriptor(object);
            addFilterConditionalStylesFromExtendedGroupExpressionPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Documentation feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addDocumentationPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_DocumentedElement_documentation_feature"), //$NON-NLS-1$
                        getString("_UI_DocumentedElement_documentation_description"), //$NON-NLS-1$
                        DescriptionPackage.Literals.DOCUMENTED_ELEMENT__DOCUMENTATION, true, true, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_DocumentationPropertyCategory"), //$NON-NLS-1$
                        null));
    }

    /**
     * This adds a property descriptor for the Label Expression feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addLabelExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_AbstractGroupDescription_labelExpression_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_AbstractGroupDescription_labelExpression_feature", "_UI_AbstractGroupDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                PropertiesPackage.Literals.ABSTRACT_GROUP_DESCRIPTION__LABEL_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Domain Class feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addDomainClassPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
                createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_AbstractGroupDescription_domainClass_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_AbstractGroupDescription_domainClass_feature", "_UI_AbstractGroupDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        PropertiesPackage.Literals.ABSTRACT_GROUP_DESCRIPTION__DOMAIN_CLASS, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Semantic Candidate Expression feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    protected void addSemanticCandidateExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_AbstractGroupDescription_semanticCandidateExpression_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_AbstractGroupDescription_semanticCandidateExpression_feature", "_UI_AbstractGroupDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                PropertiesPackage.Literals.ABSTRACT_GROUP_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Precondition Expression feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    protected void addPreconditionExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_AbstractGroupDescription_preconditionExpression_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_AbstractGroupDescription_preconditionExpression_feature", "_UI_AbstractGroupDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                PropertiesPackage.Literals.ABSTRACT_GROUP_DESCRIPTION__PRECONDITION_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Extends feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addExtendsPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_AbstractGroupDescription_extends_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_AbstractGroupDescription_extends_feature", "_UI_AbstractGroupDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        PropertiesPackage.Literals.ABSTRACT_GROUP_DESCRIPTION__EXTENDS, true, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Filter Controls From Extended Group Expression feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addFilterControlsFromExtendedGroupExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_AbstractGroupDescription_filterControlsFromExtendedGroupExpression_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_AbstractGroupDescription_filterControlsFromExtendedGroupExpression_feature", "_UI_AbstractGroupDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                PropertiesPackage.Literals.ABSTRACT_GROUP_DESCRIPTION__FILTER_CONTROLS_FROM_EXTENDED_GROUP_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Filter Validation Rules From Extended Group Expression feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addFilterValidationRulesFromExtendedGroupExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_AbstractGroupDescription_filterValidationRulesFromExtendedGroupExpression_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_AbstractGroupDescription_filterValidationRulesFromExtendedGroupExpression_feature", "_UI_AbstractGroupDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                PropertiesPackage.Literals.ABSTRACT_GROUP_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_EXTENDED_GROUP_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null,
                null));
    }

    /**
     * This adds a property descriptor for the Filter Conditional Styles From Extended Group Expression feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addFilterConditionalStylesFromExtendedGroupExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_AbstractGroupDescription_filterConditionalStylesFromExtendedGroupExpression_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_AbstractGroupDescription_filterConditionalStylesFromExtendedGroupExpression_feature", "_UI_AbstractGroupDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                PropertiesPackage.Literals.ABSTRACT_GROUP_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_GROUP_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null,
                null));
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
            childrenFeatures.add(PropertiesPackage.Literals.ABSTRACT_GROUP_DESCRIPTION__CONTROLS);
            childrenFeatures.add(PropertiesPackage.Literals.ABSTRACT_GROUP_DESCRIPTION__VALIDATION_SET);
            childrenFeatures.add(PropertiesPackage.Literals.ABSTRACT_GROUP_DESCRIPTION__STYLE);
            childrenFeatures.add(PropertiesPackage.Literals.ABSTRACT_GROUP_DESCRIPTION__CONDITIONAL_STYLES);
            childrenFeatures.add(PropertiesPackage.Literals.ABSTRACT_GROUP_DESCRIPTION__ACTIONS);
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
        return ((StyledString) getStyledText(object)).getString();
    }

    /**
     * This returns the label styled text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object getStyledText(Object object) {
        String label = ((AbstractGroupDescription) object).getName();
        StyledString styledLabel = new StyledString();
        if (label == null || label.length() == 0) {
            styledLabel.append(getString("_UI_AbstractGroupDescription_type"), StyledString.Style.QUALIFIER_STYLER); //$NON-NLS-1$
        } else {
            styledLabel.append(getString("_UI_AbstractGroupDescription_type"), StyledString.Style.QUALIFIER_STYLER).append(" " + label); //$NON-NLS-1$ //$NON-NLS-2$
        }
        return styledLabel;
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

        switch (notification.getFeatureID(AbstractGroupDescription.class)) {
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__DOCUMENTATION:
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__LABEL_EXPRESSION:
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__DOMAIN_CLASS:
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION:
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__PRECONDITION_EXPRESSION:
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__EXTENDS:
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__FILTER_CONTROLS_FROM_EXTENDED_GROUP_EXPRESSION:
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_EXTENDED_GROUP_EXPRESSION:
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_GROUP_EXPRESSION:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__CONTROLS:
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__VALIDATION_SET:
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__STYLE:
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__CONDITIONAL_STYLES:
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__ACTIONS:
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

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.ABSTRACT_GROUP_DESCRIPTION__CONTROLS, PropertiesFactory.eINSTANCE.createContainerDescription()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.ABSTRACT_GROUP_DESCRIPTION__CONTROLS, PropertiesFactory.eINSTANCE.createTextDescription()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.ABSTRACT_GROUP_DESCRIPTION__CONTROLS, PropertiesFactory.eINSTANCE.createButtonDescription()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.ABSTRACT_GROUP_DESCRIPTION__CONTROLS, PropertiesFactory.eINSTANCE.createLabelDescription()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.ABSTRACT_GROUP_DESCRIPTION__CONTROLS, PropertiesFactory.eINSTANCE.createCheckboxDescription()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.ABSTRACT_GROUP_DESCRIPTION__CONTROLS, PropertiesFactory.eINSTANCE.createSelectDescription()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.ABSTRACT_GROUP_DESCRIPTION__CONTROLS, PropertiesFactory.eINSTANCE.createDynamicMappingForDescription()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.ABSTRACT_GROUP_DESCRIPTION__CONTROLS, PropertiesFactory.eINSTANCE.createTextAreaDescription()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.ABSTRACT_GROUP_DESCRIPTION__CONTROLS, PropertiesFactory.eINSTANCE.createRadioDescription()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.ABSTRACT_GROUP_DESCRIPTION__CONTROLS, PropertiesFactory.eINSTANCE.createListDescription()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.ABSTRACT_GROUP_DESCRIPTION__CONTROLS, PropertiesFactory.eINSTANCE.createCustomDescription()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.ABSTRACT_GROUP_DESCRIPTION__CONTROLS, PropertiesFactory.eINSTANCE.createHyperlinkDescription()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.ABSTRACT_GROUP_DESCRIPTION__VALIDATION_SET, PropertiesFactory.eINSTANCE.createGroupValidationSetDescription()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.ABSTRACT_GROUP_DESCRIPTION__STYLE, PropertiesFactory.eINSTANCE.createGroupStyle()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.ABSTRACT_GROUP_DESCRIPTION__CONDITIONAL_STYLES, PropertiesFactory.eINSTANCE.createGroupConditionalStyle()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.ABSTRACT_GROUP_DESCRIPTION__ACTIONS, PropertiesFactory.eINSTANCE.createToolbarAction()));
    }

}
