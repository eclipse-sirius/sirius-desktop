/**
 * Copyright (c) 2007, 2018 THALES GLOBAL SERVICES and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.diagram.description.provider;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.diagram.description.AdditionalLayer;
import org.eclipse.sirius.diagram.description.CustomLayoutConfiguration;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.concern.ConcernFactory;
import org.eclipse.sirius.diagram.description.concern.ConcernSet;
import org.eclipse.sirius.diagram.description.filter.FilterFactory;
import org.eclipse.sirius.diagram.ui.api.layout.CustomLayoutAlgorithm;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.tool.ToolFactory;
import org.eclipse.sirius.viewpoint.description.validation.ValidationFactory;

/**
 * This is the item provider adapter for a {@link org.eclipse.sirius.diagram.description.DiagramDescription} object.
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class DiagramDescriptionItemProvider extends DragAndDropTargetDescriptionItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public DiagramDescriptionItemProvider(AdapterFactory adapterFactory) {
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
            addEndUserDocumentationPropertyDescriptor(object);
            addNamePropertyDescriptor(object);
            addLabelPropertyDescriptor(object);
            addTitleExpressionPropertyDescriptor(object);
            addInitialisationPropertyDescriptor(object);
            addMetamodelPropertyDescriptor(object);
            addShowOnStartupPropertyDescriptor(object);
            addPasteDescriptionsPropertyDescriptor(object);
            addDomainClassPropertyDescriptor(object);
            addPreconditionExpressionPropertyDescriptor(object);
            addDefaultConcernPropertyDescriptor(object);
            addRootExpressionPropertyDescriptor(object);
            addInitPropertyDescriptor(object);
            addReusedMappingsPropertyDescriptor(object);
            addReusedToolsPropertyDescriptor(object);
            addEnablePopupBarsPropertyDescriptor(object);
            addBackgroundColorPropertyDescriptor(object);
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
     * This adds a property descriptor for the End User Documentation feature. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    protected void addEndUserDocumentationPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_EndUserDocumentedElement_endUserDocumentation_feature"), //$NON-NLS-1$
                getString("_UI_EndUserDocumentedElement_endUserDocumentation_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.END_USER_DOCUMENTED_ELEMENT__END_USER_DOCUMENTATION, true, true, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                getString("_UI_DocumentationPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Name feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addNamePropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_IdentifiedElement_name_feature"), //$NON-NLS-1$
                        getString("_UI_IdentifiedElement_name_description"), //$NON-NLS-1$
                        DescriptionPackage.Literals.IDENTIFIED_ELEMENT__NAME, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                        null));
    }

    /**
     * This adds a property descriptor for the Label feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addLabelPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_IdentifiedElement_label_feature"), //$NON-NLS-1$
                        getString("_UI_IdentifiedElement_label_description"), //$NON-NLS-1$
                        DescriptionPackage.Literals.IDENTIFIED_ELEMENT__LABEL, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                        null));
    }

    /**
     * This adds a property descriptor for the Title Expression feature. <!-- begin-user-doc --> <!-- end-user-doc -->
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
     * This adds a property descriptor for the Initialisation feature. <!-- begin-user-doc --> <!-- end-user-doc -->
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
     * This adds a property descriptor for the Metamodel feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addMetamodelPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
                createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_RepresentationDescription_metamodel_feature"), //$NON-NLS-1$
                        getString("_UI_RepresentationDescription_metamodel_description"), //$NON-NLS-1$
                        DescriptionPackage.Literals.REPRESENTATION_DESCRIPTION__METAMODEL, true, false, true, null, getString("_UI_MetamodelsPropertyCategory"), //$NON-NLS-1$
                        null));
    }

    /**
     * This adds a property descriptor for the Show On Startup feature. <!-- begin-user-doc -->
     *
     * @since 0.9.0 <!-- end-user-doc -->
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
     * This adds a property descriptor for the Domain Class feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addDomainClassPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_DiagramDescription_domainClass_feature"), //$NON-NLS-1$
                        getString("_UI_DiagramDescription_domainClass_description"), //$NON-NLS-1$
                        org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__DOMAIN_CLASS, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                        getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
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
                getString("_UI_DiagramDescription_preconditionExpression_feature"), //$NON-NLS-1$
                getString("_UI_DiagramDescription_preconditionExpression_description"), //$NON-NLS-1$
                org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__PRECONDITION_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Default Concern feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addDefaultConcernPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
                createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_DiagramDescription_defaultConcern_feature"), //$NON-NLS-1$
                        getString("_UI_DiagramDescription_defaultConcern_description"), //$NON-NLS-1$
                        org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__DEFAULT_CONCERN, true, false, true, null, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                        null));
    }

    /**
     * This adds a property descriptor for the Root Expression feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addRootExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
                createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_DiagramDescription_rootExpression_feature"), //$NON-NLS-1$
                        getString("_UI_DiagramDescription_rootExpression_description"), //$NON-NLS-1$
                        org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__ROOT_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                        getString("_UI_AdvancedPropertyCategory"), //$NON-NLS-1$
                        null));
    }

    /**
     * This adds a property descriptor for the Init feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addInitPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_DiagramDescription_init_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_DiagramDescription_init_feature", "_UI_DiagramDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__INIT, true, false, true, null, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                        null));
    }

    /**
     * This adds a property descriptor for the Reused Mappings feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addReusedMappingsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
                createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_DiagramDescription_reusedMappings_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_DiagramDescription_reusedMappings_feature", "_UI_DiagramDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__REUSED_MAPPINGS, true, false, true, null, getString("_UI_ImportPropertyCategory"), //$NON-NLS-1$
                        null));
    }

    /**
     * This adds a property descriptor for the Reused Tools feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addReusedToolsPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_DiagramDescription_reusedTools_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_DiagramDescription_reusedTools_feature", "_UI_DiagramDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__REUSED_TOOLS, true, false, true, null, getString("_UI_ImportPropertyCategory"), //$NON-NLS-1$
                        null));
    }

    /**
     * This adds a property descriptor for the Enable Popup Bars feature. <!-- begin-user-doc -->
     *
     * @since 0.9.0 <!-- end-user-doc -->
     * @generated
     */
    protected void addEnablePopupBarsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
                createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_DiagramDescription_enablePopupBars_feature"), //$NON-NLS-1$
                        getString("_UI_DiagramDescription_enablePopupBars_description"), //$NON-NLS-1$
                        org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__ENABLE_POPUP_BARS, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
                        getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                        null));
    }

    /**
     * This adds a property descriptor for the Background Color feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addBackgroundColorPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
                createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_DiagramDescription_backgroundColor_feature"), //$NON-NLS-1$
                        getString("_UI_DiagramDescription_backgroundColor_description"), //$NON-NLS-1$
                        org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__BACKGROUND_COLOR, true, false, true, null, getString("_UI_BackgroundPropertyCategory"), //$NON-NLS-1$
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
            childrenFeatures.add(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__FILTERS);
            childrenFeatures.add(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__VALIDATION_SET);
            childrenFeatures.add(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__CONCERNS);
            childrenFeatures.add(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__LAYOUT);
            childrenFeatures.add(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__DIAGRAM_INITIALISATION);
            childrenFeatures.add(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__DEFAULT_LAYER);
            childrenFeatures.add(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__ADDITIONAL_LAYERS);
            childrenFeatures.add(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__NODE_MAPPINGS);
            childrenFeatures.add(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__EDGE_MAPPINGS);
            childrenFeatures.add(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__EDGE_MAPPING_IMPORTS);
            childrenFeatures.add(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__CONTAINER_MAPPINGS);
            childrenFeatures.add(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__TOOL_SECTION);
        }
        return childrenFeatures;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    protected EStructuralFeature getChildFeature(Object object, Object child) {
        // Check the type of the specified child object and return the proper
        // feature to use for
        // adding (see {@link AddCommand}) it as a child.
        if (object instanceof DiagramDescription && child instanceof AdditionalLayer) {
            return org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__ADDITIONAL_LAYERS;
        }

        return super.getChildFeature(object, child);
    }

    /**
     * This returns DiagramDescription.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/DiagramDescription")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public String getText(Object object) {
        String label = new IdentifiedElementQuery((IdentifiedElement) object).getLabel();
        return label == null || label.length() == 0 ? getString("_UI_DiagramDescription_type") : label; //$NON-NLS-1$
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

        switch (notification.getFeatureID(DiagramDescription.class)) {
        case org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__DOCUMENTATION:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__END_USER_DOCUMENTATION:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__NAME:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__LABEL:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__TITLE_EXPRESSION:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__INITIALISATION:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__SHOW_ON_STARTUP:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__DOMAIN_CLASS:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__PRECONDITION_EXPRESSION:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__ROOT_EXPRESSION:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__ENABLE_POPUP_BARS:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__FILTERS:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__VALIDATION_SET:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__CONCERNS:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__LAYOUT:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__DIAGRAM_INITIALISATION:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__DEFAULT_LAYER:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__ADDITIONAL_LAYERS:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__NODE_MAPPINGS:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__EDGE_MAPPINGS:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__EDGE_MAPPING_IMPORTS:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__CONTAINER_MAPPINGS:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__TOOL_SECTION:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
            return;
        }
        super.notifyChanged(notification);
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children that can be created
     * under this object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);

        newChildDescriptors
                .add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__FILTERS, FilterFactory.eINSTANCE.createCompositeFilterDescription()));

        newChildDescriptors
                .add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__VALIDATION_SET, ValidationFactory.eINSTANCE.createValidationSet()));

        ConcernSet concernSet = ConcernFactory.eINSTANCE.createConcernSet();
        concernSet.getOwnedConcernDescriptions().add(ConcernFactory.eINSTANCE.createConcernDescription());
        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__CONCERNS, concernSet));

        newChildDescriptors
                .add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__LAYOUT, DescriptionFactory.eINSTANCE.createOrderedTreeLayout()));

        newChildDescriptors
                .add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__LAYOUT, DescriptionFactory.eINSTANCE.createCompositeLayout()));
        addAllCustomLayoutChildDescriptors(newChildDescriptors);
        newChildDescriptors.add(
                createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__DIAGRAM_INITIALISATION, ToolFactory.eINSTANCE.createInitialOperation()));

        Layer defaultLayer = DescriptionFactory.eINSTANCE.createLayer();
        defaultLayer.setName(Messages.DefaultLayerName);
        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__DEFAULT_LAYER, defaultLayer));

        // Do not add additional layer as default layer.
        // newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__DEFAULT_LAYER,
        // DescriptionFactory.eINSTANCE.createAdditionalLayer()));

        newChildDescriptors.add(
                createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__ADDITIONAL_LAYERS, DescriptionFactory.eINSTANCE.createAdditionalLayer()));

        newChildDescriptors
                .add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__NODE_MAPPINGS, DescriptionFactory.eINSTANCE.createNodeMapping()));

        newChildDescriptors.add(
                createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__NODE_MAPPINGS, DescriptionFactory.eINSTANCE.createNodeMappingImport()));

        newChildDescriptors
                .add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__EDGE_MAPPINGS, DescriptionFactory.eINSTANCE.createEdgeMapping()));

        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__EDGE_MAPPINGS,
                DescriptionFactory.eINSTANCE.createEdgeMappingUsingDomainElement()));

        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__EDGE_MAPPING_IMPORTS,
                DescriptionFactory.eINSTANCE.createEdgeMappingImport()));

        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__CONTAINER_MAPPINGS,
                DescriptionFactory.eINSTANCE.createContainerMapping()));

        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__CONTAINER_MAPPINGS,
                DescriptionFactory.eINSTANCE.createContainerMappingImport()));

        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__TOOL_SECTION,
                org.eclipse.sirius.diagram.description.tool.ToolFactory.eINSTANCE.createToolSection()));
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children that can be created
     * under this object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    protected void collectNewChildDescriptorsGen(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);

        newChildDescriptors
                .add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__FILTERS, FilterFactory.eINSTANCE.createCompositeFilterDescription()));

        newChildDescriptors
                .add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__VALIDATION_SET, ValidationFactory.eINSTANCE.createValidationSet()));

        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__CONCERNS, ConcernFactory.eINSTANCE.createConcernSet()));

        newChildDescriptors
                .add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__LAYOUT, DescriptionFactory.eINSTANCE.createOrderedTreeLayout()));

        newChildDescriptors
                .add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__LAYOUT, DescriptionFactory.eINSTANCE.createCompositeLayout()));

        newChildDescriptors.add(
                createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__LAYOUT, DescriptionFactory.eINSTANCE.createCustomLayoutConfiguration()));
        addAllCustomLayoutChildDescriptors(newChildDescriptors);
        newChildDescriptors.add(
                createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__DIAGRAM_INITIALISATION, ToolFactory.eINSTANCE.createInitialOperation()));

        newChildDescriptors
                .add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__DEFAULT_LAYER, DescriptionFactory.eINSTANCE.createLayer()));

        newChildDescriptors
                .add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__DEFAULT_LAYER, DescriptionFactory.eINSTANCE.createAdditionalLayer()));

        newChildDescriptors.add(
                createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__ADDITIONAL_LAYERS, DescriptionFactory.eINSTANCE.createAdditionalLayer()));

        newChildDescriptors
                .add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__NODE_MAPPINGS, DescriptionFactory.eINSTANCE.createNodeMapping()));

        newChildDescriptors.add(
                createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__NODE_MAPPINGS, DescriptionFactory.eINSTANCE.createNodeMappingImport()));

        newChildDescriptors
                .add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__EDGE_MAPPINGS, DescriptionFactory.eINSTANCE.createEdgeMapping()));

        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__EDGE_MAPPING_IMPORTS,
                DescriptionFactory.eINSTANCE.createEdgeMappingImport()));

        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__CONTAINER_MAPPINGS,
                DescriptionFactory.eINSTANCE.createContainerMapping()));

        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__CONTAINER_MAPPINGS,
                DescriptionFactory.eINSTANCE.createContainerMappingImport()));

        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__TOOL_SECTION,
                org.eclipse.sirius.diagram.description.tool.ToolFactory.eINSTANCE.createToolSection()));
    }

    /**
     * Add all registered generic layout providers as child descriptor.
     *
     * @param newChildDescriptors
     *            the child descriptor collection where to add new child descriptors.
     */
    private void addAllCustomLayoutChildDescriptors(Collection<Object> newChildDescriptors) {
        Map<String, CustomLayoutAlgorithm> layoutProviderRegistry = DiagramUIPlugin.getPlugin().getLayoutAlgorithms();
        Set<Entry<String, CustomLayoutAlgorithm>> layoutProviders = layoutProviderRegistry.entrySet();
        for (Entry<String, CustomLayoutAlgorithm> layoutProviderEntry : layoutProviders) {
            CustomLayoutConfiguration customConfiguration = DescriptionFactory.eINSTANCE.createCustomLayoutConfiguration();
            customConfiguration.setId(layoutProviderEntry.getKey());
            customConfiguration.setLabel(layoutProviderEntry.getValue().getLabel());
            customConfiguration.setDescription(layoutProviderEntry.getValue().getDescription());
            newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__LAYOUT, customConfiguration));
        }
    }

    /**
     * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
        if (feature == org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__DEFAULT_LAYER) {
            // We do not use the getFeatureText method here, as we want only the
            // first term in "Default Layer" and put the Type text after (in
            // this case we could also put only the Feature text as the only
            // child type currently available for is Layer (see
            // collectNewChildDescriptors).
            return getString("_UI_CreateChild_text2", new Object[] { getTypeText(child), "Default", getTypeText(owner) }); //$NON-NLS-1$ //$NON-NLS-2$
        } else if (child instanceof CustomLayoutConfiguration) {
            return ((CustomLayoutConfiguration) child).getLabel();
        }
        String createChildText = super.getCreateChildText(owner, feature, child, selection);
        if (child != null && isNormalEdgeMapping(child)) {
            if (((EdgeMapping) child).isUseDomainElement()) {
                createChildText = Messages.ItemProvider_elementBasedEdge;
            } else {
                createChildText = Messages.ItemProvider_relationBasedEdge;
            }
        }
        return createChildText;
    }

    /**
     * @not-generated
     */
    private boolean isNormalEdgeMapping(Object obj) {
        return org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE.getEdgeMapping().isInstance(obj)
                && ((EObject) obj).eClass().equals(org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE.getEdgeMapping());
    }

}
