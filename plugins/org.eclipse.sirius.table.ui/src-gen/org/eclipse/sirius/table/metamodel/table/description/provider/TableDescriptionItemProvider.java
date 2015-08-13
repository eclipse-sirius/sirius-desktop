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
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.table.business.internal.metamodel.TableToolVariables;
import org.eclipse.sirius.table.metamodel.table.description.CreateLineTool;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionFactory;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.table.metamodel.table.provider.TableUIPlugin;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.provider.DocumentedElementItemProvider;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.table.metamodel.table.description.TableDescription}
 * object. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class TableDescriptionItemProvider extends DocumentedElementItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public TableDescriptionItemProvider(AdapterFactory adapterFactory) {
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

            addEndUserDocumentationPropertyDescriptor(object);
            addNamePropertyDescriptor(object);
            addLabelPropertyDescriptor(object);
            addTitleExpressionPropertyDescriptor(object);
            addInitialisationPropertyDescriptor(object);
            addMetamodelPropertyDescriptor(object);
            addShowOnStartupPropertyDescriptor(object);
            addPreconditionExpressionPropertyDescriptor(object);
            addDomainClassPropertyDescriptor(object);
            addInitialHeaderColumnWidthPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Name feature. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addNamePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_IdentifiedElement_name_feature"), //$NON-NLS-1$
                getString("_UI_IdentifiedElement_name_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.IDENTIFIED_ELEMENT__NAME, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Label feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addLabelPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_IdentifiedElement_label_feature"), //$NON-NLS-1$
                getString("_UI_IdentifiedElement_label_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.IDENTIFIED_ELEMENT__LABEL, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Title Expression feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addTitleExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_RepresentationDescription_titleExpression_feature"), //$NON-NLS-1$
                getString("_UI_RepresentationDescription_titleExpression_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.REPRESENTATION_DESCRIPTION__TITLE_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_AdvancedPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Initialisation feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addInitialisationPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_RepresentationDescription_initialisation_feature"), //$NON-NLS-1$
                getString("_UI_RepresentationDescription_initialisation_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.REPRESENTATION_DESCRIPTION__INITIALISATION, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Metamodel feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addMetamodelPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_RepresentationDescription_metamodel_feature"), //$NON-NLS-1$
                getString("_UI_RepresentationDescription_metamodel_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.REPRESENTATION_DESCRIPTION__METAMODEL, true, false, true, null, getString("_UI_MetamodelsPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Show On Startup feature. <!--
     * begin-user-doc -->
     *
     * @since 0.9.0
     * @generated
     */
    protected void addShowOnStartupPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_RepresentationDescription_showOnStartup_feature"), //$NON-NLS-1$
                getString("_UI_RepresentationDescription_showOnStartup_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.REPRESENTATION_DESCRIPTION__SHOW_ON_STARTUP, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the End User Documentation feature.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addEndUserDocumentationPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_EndUserDocumentedElement_endUserDocumentation_feature"), //$NON-NLS-1$
                getString("_UI_EndUserDocumentedElement_endUserDocumentation_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.END_USER_DOCUMENTED_ELEMENT__END_USER_DOCUMENTATION, true, true, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                getString("_UI_DocumentationPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Precondition Expression feature.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addPreconditionExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_TableDescription_preconditionExpression_feature"), //$NON-NLS-1$
                getString("_UI_TableDescription_preconditionExpression_description"), //$NON-NLS-1$
                org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.Literals.TABLE_DESCRIPTION__PRECONDITION_EXPRESSION, true, false, false,
                ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_AdvancedPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Domain Class feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addDomainClassPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_TableDescription_domainClass_feature"), //$NON-NLS-1$
                getString("_UI_TableDescription_domainClass_description"), //$NON-NLS-1$
                org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.Literals.TABLE_DESCRIPTION__DOMAIN_CLASS, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Initial Header Column Width
     * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addInitialHeaderColumnWidthPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_TableDescription_initialHeaderColumnWidth_feature"), //$NON-NLS-1$
                getString("_UI_TableDescription_initialHeaderColumnWidth_description"), //$NON-NLS-1$
                org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.Literals.TABLE_DESCRIPTION__INITIAL_HEADER_COLUMN_WIDTH, true, false, false,
                ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE, getString("_UI_AdvancedPropertyCategory"), //$NON-NLS-1$
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
            childrenFeatures.add(org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.Literals.TABLE_DESCRIPTION__OWNED_REPRESENTATION_CREATION_DESCRIPTIONS);
            childrenFeatures.add(org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.Literals.TABLE_DESCRIPTION__OWNED_REPRESENTATION_NAVIGATION_DESCRIPTIONS);
            childrenFeatures.add(org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.Literals.TABLE_DESCRIPTION__OWNED_LINE_MAPPINGS);
            childrenFeatures.add(org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.Literals.TABLE_DESCRIPTION__OWNED_CREATE_LINE);
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
     * This returns TableDescription.gif. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/TableDescription")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public String getText(final Object object) {
        final String label = new IdentifiedElementQuery((TableDescription) object).getLabel();
        return label == null || label.length() == 0 ? getString("_UI_TableDescription_type") : label; //$NON-NLS-1$
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

        switch (notification.getFeatureID(TableDescription.class)) {
        case org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.TABLE_DESCRIPTION__END_USER_DOCUMENTATION:
        case org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.TABLE_DESCRIPTION__NAME:
        case org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.TABLE_DESCRIPTION__LABEL:
        case org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.TABLE_DESCRIPTION__TITLE_EXPRESSION:
        case org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.TABLE_DESCRIPTION__INITIALISATION:
        case org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.TABLE_DESCRIPTION__SHOW_ON_STARTUP:
        case org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.TABLE_DESCRIPTION__PRECONDITION_EXPRESSION:
        case org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.TABLE_DESCRIPTION__DOMAIN_CLASS:
        case org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.TABLE_DESCRIPTION__INITIAL_HEADER_COLUMN_WIDTH:
        case org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.TABLE_DESCRIPTION__IMPORTED_ELEMENTS:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.TABLE_DESCRIPTION__OWNED_REPRESENTATION_CREATION_DESCRIPTIONS:
        case org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.TABLE_DESCRIPTION__OWNED_REPRESENTATION_NAVIGATION_DESCRIPTIONS:
        case org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.TABLE_DESCRIPTION__OWNED_LINE_MAPPINGS:
        case org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.TABLE_DESCRIPTION__OWNED_CREATE_LINE:
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

        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.Literals.TABLE_DESCRIPTION__OWNED_LINE_MAPPINGS,
                DescriptionFactory.eINSTANCE.createLineMapping()));

        // add creation description tools for known representations
        newChildDescriptors
        .addAll(DialectUIManager.INSTANCE
                .provideRepresentationCreationToolDescriptors(org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.Literals.TABLE_DESCRIPTION__OWNED_REPRESENTATION_CREATION_DESCRIPTIONS));
        // add navigation description tools for known representations
        newChildDescriptors
        .addAll(DialectUIManager.INSTANCE
                .provideRepresentationNavigationToolDescriptors(org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.Literals.TABLE_DESCRIPTION__OWNED_REPRESENTATION_NAVIGATION_DESCRIPTIONS));

        collectCreateLineTool(newChildDescriptors);

    }

    /**
     *
     * @param newChildDescriptors
     * @param object
     * @generated
     */
    protected void collectNewChildDescriptorsGen(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);

        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.Literals.TABLE_DESCRIPTION__OWNED_REPRESENTATION_CREATION_DESCRIPTIONS,
                DescriptionFactory.eINSTANCE.createTableCreationDescription()));

        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.Literals.TABLE_DESCRIPTION__OWNED_REPRESENTATION_NAVIGATION_DESCRIPTIONS,
                DescriptionFactory.eINSTANCE.createTableNavigationDescription()));

        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.Literals.TABLE_DESCRIPTION__OWNED_LINE_MAPPINGS,
                DescriptionFactory.eINSTANCE.createLineMapping()));

        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.Literals.TABLE_DESCRIPTION__OWNED_CREATE_LINE,
                DescriptionFactory.eINSTANCE.createCreateLineTool()));
    }

    private void collectCreateLineTool(Collection<Object> newChildDescriptors) {
        CreateLineTool createLineTool = DescriptionFactory.eINSTANCE.createCreateLineTool();
        new TableToolVariables().doSwitch(createLineTool);
        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.Literals.TABLE_DESCRIPTION__OWNED_CREATE_LINE, createLineTool));
    }

    /**
     * Return the resource locator for this item provider's resources. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ResourceLocator getResourceLocator() {
        return TableUIPlugin.INSTANCE;
    }

}
