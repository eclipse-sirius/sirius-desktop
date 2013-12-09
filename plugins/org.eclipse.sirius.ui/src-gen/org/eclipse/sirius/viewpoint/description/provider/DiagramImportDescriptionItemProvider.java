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
package org.eclipse.sirius.viewpoint.description.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.DiagramImportDescription;
import org.eclipse.sirius.viewpoint.description.EdgeMapping;
import org.eclipse.sirius.viewpoint.description.audit.AuditFactory;
import org.eclipse.sirius.viewpoint.description.concern.ConcernFactory;
import org.eclipse.sirius.viewpoint.description.filter.FilterFactory;
import org.eclipse.sirius.viewpoint.description.tool.ToolFactory;
import org.eclipse.sirius.viewpoint.description.validation.ValidationFactory;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.viewpoint.description.DiagramImportDescription}
 * object. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class DiagramImportDescriptionItemProvider extends DocumentedElementItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider,
        IItemLabelProvider, IItemPropertySource {
    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DiagramImportDescriptionItemProvider(AdapterFactory adapterFactory) {
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
            addDropDescriptionsPropertyDescriptor(object);
            addPasteDescriptionsPropertyDescriptor(object);
            addAllEdgeMappingsPropertyDescriptor(object);
            addAllNodeMappingsPropertyDescriptor(object);
            addAllContainerMappingsPropertyDescriptor(object);
            addAllToolsPropertyDescriptor(object);
            addDomainClassPropertyDescriptor(object);
            addPreconditionExpressionPropertyDescriptor(object);
            addDefaultConcernPropertyDescriptor(object);
            addRootExpressionPropertyDescriptor(object);
            addInitPropertyDescriptor(object);
            addAllLayersPropertyDescriptor(object);
            addAllActivatedToolsPropertyDescriptor(object);
            addReusedMappingsPropertyDescriptor(object);
            addReusedToolsPropertyDescriptor(object);
            addEnablePopupBarsPropertyDescriptor(object);
            addImportedDiagramPropertyDescriptor(object);
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
                getString("_UI_IdentifiedElement_name_feature"), getString("_UI_IdentifiedElement_name_description"), DescriptionPackage.Literals.IDENTIFIED_ELEMENT__NAME, true, false, false,
                ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), null));
    }

    /**
     * This adds a property descriptor for the Label feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addLabelPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_IdentifiedElement_label_feature"), getString("_UI_IdentifiedElement_label_description"), DescriptionPackage.Literals.IDENTIFIED_ELEMENT__LABEL, true, false, false,
                ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), null));
    }

    /**
     * This adds a property descriptor for the Title Expression feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addTitleExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_RepresentationDescription_titleExpression_feature"), getString("_UI_RepresentationDescription_titleExpression_description"),
                DescriptionPackage.Literals.REPRESENTATION_DESCRIPTION__TITLE_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_AdvancedPropertyCategory"),
                null));
    }

    /**
     * This adds a property descriptor for the Initialisation feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addInitialisationPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                        getString("_UI_RepresentationDescription_initialisation_feature"), getString("_UI_RepresentationDescription_initialisation_description"),
                        DescriptionPackage.Literals.REPRESENTATION_DESCRIPTION__INITIALISATION, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
                        getString("_UI_GeneralPropertyCategory"), null));
    }

    /**
     * This adds a property descriptor for the Drop Descriptions feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addDropDescriptionsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DragAndDropTargetDescription_dropDescriptions_feature"), getString("_UI_DragAndDropTargetDescription_dropDescriptions_description"),
                DescriptionPackage.Literals.DRAG_AND_DROP_TARGET_DESCRIPTION__DROP_DESCRIPTIONS, true, false, true, null, getString("_UI_BehaviorPropertyCategory"), null));
    }

    /**
     * This adds a property descriptor for the Paste Descriptions feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addPasteDescriptionsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_PasteTargetDescription_pasteDescriptions_feature"), getString("_UI_PasteTargetDescription_pasteDescriptions_description"),
                DescriptionPackage.Literals.PASTE_TARGET_DESCRIPTION__PASTE_DESCRIPTIONS, true, false, true, null, getString("_UI_BehaviorPropertyCategory"), null));
    }

    /**
     * This adds a property descriptor for the End User Documentation feature.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addEndUserDocumentationPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_EndUserDocumentedElement_endUserDocumentation_feature"), getString("_UI_EndUserDocumentedElement_endUserDocumentation_description"),
                DescriptionPackage.Literals.END_USER_DOCUMENTED_ELEMENT__END_USER_DOCUMENTATION, true, true, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                getString("_UI_DocumentationPropertyCategory"), null));
    }

    /**
     * This adds a property descriptor for the All Edge Mappings feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addAllEdgeMappingsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DiagramDescription_allEdgeMappings_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_DiagramDescription_allEdgeMappings_feature", "_UI_DiagramDescription_type"),
                DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__ALL_EDGE_MAPPINGS, false, false, false, null, null, null));
    }

    /**
     * This adds a property descriptor for the All Node Mappings feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addAllNodeMappingsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DiagramDescription_allNodeMappings_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_DiagramDescription_allNodeMappings_feature", "_UI_DiagramDescription_type"),
                DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__ALL_NODE_MAPPINGS, false, false, false, null, null, null));
    }

    /**
     * This adds a property descriptor for the All Container Mappings feature.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addAllContainerMappingsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DiagramDescription_allContainerMappings_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_DiagramDescription_allContainerMappings_feature", "_UI_DiagramDescription_type"),
                DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__ALL_CONTAINER_MAPPINGS, false, false, false, null, null, null));
    }

    /**
     * This adds a property descriptor for the All Tools feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addAllToolsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DiagramDescription_allTools_feature"), getString("_UI_PropertyDescriptor_description", "_UI_DiagramDescription_allTools_feature", "_UI_DiagramDescription_type"),
                DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__ALL_TOOLS, false, false, false, null, null, null));
    }

    /**
     * This adds a property descriptor for the Metamodel feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addMetamodelPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_RepresentationDescription_metamodel_feature"), getString("_UI_RepresentationDescription_metamodel_description"),
                DescriptionPackage.Literals.REPRESENTATION_DESCRIPTION__METAMODEL, true, false, true, null, getString("_UI_MetamodelsPropertyCategory"), null));
    }

    /**
     * This adds a property descriptor for the Show On Startup feature. <!--
     * begin-user-doc -->
     * 
     * @since 0.9.0
     * @generated
     */
    protected void addShowOnStartupPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                        getString("_UI_RepresentationDescription_showOnStartup_feature"), getString("_UI_RepresentationDescription_showOnStartup_description"),
                        DescriptionPackage.Literals.REPRESENTATION_DESCRIPTION__SHOW_ON_STARTUP, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
                        getString("_UI_GeneralPropertyCategory"), null));
    }

    /**
     * This adds a property descriptor for the Domain Class feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addDomainClassPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DiagramDescription_domainClass_feature"), getString("_UI_DiagramDescription_domainClass_description"), DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__DOMAIN_CLASS,
                true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), null));
    }

    /**
     * This adds a property descriptor for the Precondition Expression feature.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addPreconditionExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DiagramDescription_preconditionExpression_feature"), getString("_UI_DiagramDescription_preconditionExpression_description"),
                DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__PRECONDITION_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"),
                null));
    }

    /**
     * This adds a property descriptor for the Default Concern feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addDefaultConcernPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DiagramDescription_defaultConcern_feature"), getString("_UI_DiagramDescription_defaultConcern_description"),
                DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__DEFAULT_CONCERN, true, false, true, null, getString("_UI_GeneralPropertyCategory"), null));
    }

    /**
     * This adds a property descriptor for the Root Expression feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addRootExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DiagramDescription_rootExpression_feature"), getString("_UI_DiagramDescription_rootExpression_description"),
                DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__ROOT_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_AdvancedPropertyCategory"), null));
    }

    /**
     * This adds a property descriptor for the Init feature. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addInitPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DiagramDescription_init_feature"), getString("_UI_PropertyDescriptor_description", "_UI_DiagramDescription_init_feature", "_UI_DiagramDescription_type"),
                DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__INIT, true, false, true, null, getString("_UI_GeneralPropertyCategory"), null));
    }

    /**
     * This adds a property descriptor for the All Layers feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addAllLayersPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DiagramDescription_allLayers_feature"), getString("_UI_PropertyDescriptor_description", "_UI_DiagramDescription_allLayers_feature", "_UI_DiagramDescription_type"),
                DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__ALL_LAYERS, true, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the All Activated Tools feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addAllActivatedToolsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DiagramDescription_allActivatedTools_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_DiagramDescription_allActivatedTools_feature", "_UI_DiagramDescription_type"),
                DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__ALL_ACTIVATED_TOOLS, true, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Reused Mappings feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addReusedMappingsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DiagramDescription_reusedMappings_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_DiagramDescription_reusedMappings_feature", "_UI_DiagramDescription_type"),
                DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__REUSED_MAPPINGS, true, false, true, null, getString("_UI_ImportPropertyCategory"), null));
    }

    /**
     * This adds a property descriptor for the Reused Tools feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addReusedToolsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DiagramDescription_reusedTools_feature"), getString("_UI_PropertyDescriptor_description", "_UI_DiagramDescription_reusedTools_feature", "_UI_DiagramDescription_type"),
                DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__REUSED_TOOLS, true, false, true, null, getString("_UI_ImportPropertyCategory"), null));
    }

    /**
     * This adds a property descriptor for the Enable Popup Bars feature. <!--
     * begin-user-doc -->
     * 
     * @since 0.9.0
     * 
     * @generated
     */
    protected void addEnablePopupBarsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DiagramDescription_enablePopupBars_feature"), getString("_UI_DiagramDescription_enablePopupBars_description"),
                DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__ENABLE_POPUP_BARS, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), null));
    }

    /**
     * This adds a property descriptor for the Imported Diagram feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addImportedDiagramPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_DiagramImportDescription_importedDiagram_feature"), getString("_UI_DiagramImportDescription_importedDiagram_description"),
                DescriptionPackage.Literals.DIAGRAM_IMPORT_DESCRIPTION__IMPORTED_DIAGRAM, true, false, true, null, getString("_UI_GeneralPropertyCategory"), null));
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
            childrenFeatures.add(DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__FILTERS);
            childrenFeatures.add(DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__VALIDATION_SET);
            childrenFeatures.add(DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__CONCERNS);
            childrenFeatures.add(DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__INFORMATION_SECTIONS);
            childrenFeatures.add(DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__LAYOUT);
            childrenFeatures.add(DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__DIAGRAM_INITIALISATION);
            childrenFeatures.add(DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__DEFAULT_LAYER);
            childrenFeatures.add(DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__ADDITIONAL_LAYERS);
            childrenFeatures.add(DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__NODE_MAPPINGS);
            childrenFeatures.add(DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__EDGE_MAPPINGS);
            childrenFeatures.add(DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__EDGE_MAPPING_IMPORTS);
            childrenFeatures.add(DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__CONTAINER_MAPPINGS);
            childrenFeatures.add(DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__TOOL_SECTION);
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
     * This returns DiagramImportDescription.gif. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/DiagramImportDescription"));
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    @Override
    public String getText(Object object) {
        String label = new IdentifiedElementQuery((DiagramImportDescription) object).getLabel();
        return label == null || label.length() == 0 ? getString("_UI_DiagramImportDescription_type") : getString("_UI_DiagramImportDescription_type") + " " + label;
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

        switch (notification.getFeatureID(DiagramImportDescription.class)) {
        case DescriptionPackage.DIAGRAM_IMPORT_DESCRIPTION__END_USER_DOCUMENTATION:
        case DescriptionPackage.DIAGRAM_IMPORT_DESCRIPTION__NAME:
        case DescriptionPackage.DIAGRAM_IMPORT_DESCRIPTION__LABEL:
        case DescriptionPackage.DIAGRAM_IMPORT_DESCRIPTION__TITLE_EXPRESSION:
        case DescriptionPackage.DIAGRAM_IMPORT_DESCRIPTION__INITIALISATION:
        case DescriptionPackage.DIAGRAM_IMPORT_DESCRIPTION__SHOW_ON_STARTUP:
        case DescriptionPackage.DIAGRAM_IMPORT_DESCRIPTION__DOMAIN_CLASS:
        case DescriptionPackage.DIAGRAM_IMPORT_DESCRIPTION__PRECONDITION_EXPRESSION:
        case DescriptionPackage.DIAGRAM_IMPORT_DESCRIPTION__ROOT_EXPRESSION:
        case DescriptionPackage.DIAGRAM_IMPORT_DESCRIPTION__ENABLE_POPUP_BARS:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case DescriptionPackage.DIAGRAM_IMPORT_DESCRIPTION__FILTERS:
        case DescriptionPackage.DIAGRAM_IMPORT_DESCRIPTION__VALIDATION_SET:
        case DescriptionPackage.DIAGRAM_IMPORT_DESCRIPTION__CONCERNS:
        case DescriptionPackage.DIAGRAM_IMPORT_DESCRIPTION__INFORMATION_SECTIONS:
        case DescriptionPackage.DIAGRAM_IMPORT_DESCRIPTION__LAYOUT:
        case DescriptionPackage.DIAGRAM_IMPORT_DESCRIPTION__DIAGRAM_INITIALISATION:
        case DescriptionPackage.DIAGRAM_IMPORT_DESCRIPTION__DEFAULT_LAYER:
        case DescriptionPackage.DIAGRAM_IMPORT_DESCRIPTION__ADDITIONAL_LAYERS:
        case DescriptionPackage.DIAGRAM_IMPORT_DESCRIPTION__NODE_MAPPINGS:
        case DescriptionPackage.DIAGRAM_IMPORT_DESCRIPTION__EDGE_MAPPINGS:
        case DescriptionPackage.DIAGRAM_IMPORT_DESCRIPTION__EDGE_MAPPING_IMPORTS:
        case DescriptionPackage.DIAGRAM_IMPORT_DESCRIPTION__CONTAINER_MAPPINGS:
        case DescriptionPackage.DIAGRAM_IMPORT_DESCRIPTION__TOOL_SECTION:
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

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__FILTERS, FilterFactory.eINSTANCE.createCompositeFilterDescription()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__VALIDATION_SET, ValidationFactory.eINSTANCE.createValidationSet()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__CONCERNS, ConcernFactory.eINSTANCE.createConcernSet()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__INFORMATION_SECTIONS, AuditFactory.eINSTANCE.createTemplateInformationSection()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__LAYOUT, DescriptionFactory.eINSTANCE.createOrderedTreeLayout()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__LAYOUT, DescriptionFactory.eINSTANCE.createCompositeLayout()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__DIAGRAM_INITIALISATION, ToolFactory.eINSTANCE.createInitialOperation()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__DEFAULT_LAYER, DescriptionFactory.eINSTANCE.createLayer()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__DEFAULT_LAYER, DescriptionFactory.eINSTANCE.createAdditionalLayer()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__ADDITIONAL_LAYERS, DescriptionFactory.eINSTANCE.createAdditionalLayer()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__NODE_MAPPINGS, DescriptionFactory.eINSTANCE.createNodeMapping()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__NODE_MAPPINGS, DescriptionFactory.eINSTANCE.createNodeMappingImport()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__EDGE_MAPPINGS, DescriptionFactory.eINSTANCE.createEdgeMapping()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__EDGE_MAPPINGS, DescriptionFactory.eINSTANCE.createEdgeMappingUsingDomainElement()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__EDGE_MAPPING_IMPORTS, DescriptionFactory.eINSTANCE.createEdgeMappingImport()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__CONTAINER_MAPPINGS, DescriptionFactory.eINSTANCE.createContainerMapping()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__CONTAINER_MAPPINGS, DescriptionFactory.eINSTANCE.createContainerMappingImport()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__TOOL_SECTION, ToolFactory.eINSTANCE.createToolSection()));

    }

    /**
     * This returns the label text for
     * {@link org.eclipse.emf.edit.command.CreateChildCommand}. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    @Override
    public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
        Object childFeature = feature;
        Object childObject = child;

        boolean qualify = childFeature == DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__DEFAULT_LAYER || childFeature == DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__ADDITIONAL_LAYERS;

        if (qualify) {
            return getString("_UI_CreateChild_text2", new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
        }
        String createChildText = super.getCreateChildText(owner, feature, child, selection);
        if (child instanceof EdgeMapping) {
            if (((EdgeMapping) child).isUseDomainElement()) {
                createChildText = "Element Based Edge";
            } else {
                createChildText = "Relation Based Edge";
            }
        }
        return createChildText;
    }

}
