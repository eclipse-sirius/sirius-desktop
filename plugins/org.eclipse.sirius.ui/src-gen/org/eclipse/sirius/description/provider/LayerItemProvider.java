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
package org.eclipse.sirius.description.provider;

import java.net.URL;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.description.DescriptionFactory;
import org.eclipse.sirius.description.DescriptionPackage;
import org.eclipse.sirius.description.EdgeMapping;
import org.eclipse.sirius.description.Layer;
import org.eclipse.sirius.description.style.EdgeStyleDescription;
import org.eclipse.sirius.description.style.StyleFactory;
import org.eclipse.sirius.description.tool.ToolFactory;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.tools.internal.util.EMFCoreUtil;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.description.Layer} object. <!-- begin-user-doc
 * --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class LayerItemProvider extends DocumentedElementItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider,
        IItemPropertySource {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public LayerItemProvider(AdapterFactory adapterFactory) {
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
            addReusedMappingsPropertyDescriptor(object);
            addAllToolsPropertyDescriptor(object);
            addReusedToolsPropertyDescriptor(object);
            addIconPropertyDescriptor(object);
            addAllEdgeMappingsPropertyDescriptor(object);
            addAllActivatedEdgeMappingsPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
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
     * This adds a property descriptor for the Reused Mappings feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addReusedMappingsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_Layer_reusedMappings_feature"), getString("_UI_PropertyDescriptor_description", "_UI_Layer_reusedMappings_feature", "_UI_Layer_type"),
                DescriptionPackage.Literals.LAYER__REUSED_MAPPINGS, true, false, true, null, getString("_UI_AdvancedPropertyCategory"), null));
    }

    /**
     * This adds a property descriptor for the All Tools feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addAllToolsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_Layer_allTools_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_Layer_allTools_feature", "_UI_Layer_type"), DescriptionPackage.Literals.LAYER__ALL_TOOLS, false, false, false, null, null, null));
    }

    /**
     * This adds a property descriptor for the Reused Tools feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addReusedToolsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_Layer_reusedTools_feature"), getString("_UI_PropertyDescriptor_description", "_UI_Layer_reusedTools_feature", "_UI_Layer_type"),
                DescriptionPackage.Literals.LAYER__REUSED_TOOLS, true, false, true, null, getString("_UI_AdvancedPropertyCategory"), null));
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
     * This adds a property descriptor for the Icon feature. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addIconPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_Layer_icon_feature"),
                getString("_UI_Layer_icon_description"), DescriptionPackage.Literals.LAYER__ICON, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                getString("_UI_AdvancedPropertyCategory"), null));
    }

    /**
     * This adds a property descriptor for the All Edge Mappings feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addAllEdgeMappingsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_Layer_allEdgeMappings_feature"), getString("_UI_PropertyDescriptor_description", "_UI_Layer_allEdgeMappings_feature", "_UI_Layer_type"),
                DescriptionPackage.Literals.LAYER__ALL_EDGE_MAPPINGS, false, false, false, null, null, null));
    }

    /**
     * This adds a property descriptor for the All Activated Edge Mappings
     * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addAllActivatedEdgeMappingsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_Layer_allActivatedEdgeMappings_feature"), getString("_UI_PropertyDescriptor_description", "_UI_Layer_allActivatedEdgeMappings_feature", "_UI_Layer_type"),
                DescriptionPackage.Literals.LAYER__ALL_ACTIVATED_EDGE_MAPPINGS, false, false, false, null, null, null));
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
            childrenFeatures.add(DescriptionPackage.Literals.LAYER__NODE_MAPPINGS);
            childrenFeatures.add(DescriptionPackage.Literals.LAYER__EDGE_MAPPINGS);
            childrenFeatures.add(DescriptionPackage.Literals.LAYER__EDGE_MAPPING_IMPORTS);
            childrenFeatures.add(DescriptionPackage.Literals.LAYER__CONTAINER_MAPPINGS);
            childrenFeatures.add(DescriptionPackage.Literals.LAYER__TOOL_SECTIONS);
            childrenFeatures.add(DescriptionPackage.Literals.LAYER__DECORATION_DESCRIPTIONS_SET);
            childrenFeatures.add(DescriptionPackage.Literals.LAYER__CUSTOMIZATION);
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
     * This returns Layer.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    @Override
    public Object getImage(Object object) {
        if (object instanceof EObject) {
            Option<URL> optionImageURL = EMFCoreUtil.getImage((EObject) object);
            if (optionImageURL.some()) {
                return optionImageURL.get();
            }
        }

        return overlayImage(object, getResourceLocator().getImage("full/obj16/Layer"));
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    @Override
    public String getText(Object object) {
        String label = new IdentifiedElementQuery((Layer) object).getLabel();
        return label == null || label.length() == 0 ? getString("_UI_Layer_type") : label;
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

        switch (notification.getFeatureID(Layer.class)) {
        case DescriptionPackage.LAYER__END_USER_DOCUMENTATION:
        case DescriptionPackage.LAYER__NAME:
        case DescriptionPackage.LAYER__LABEL:
        case DescriptionPackage.LAYER__ICON:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case DescriptionPackage.LAYER__NODE_MAPPINGS:
        case DescriptionPackage.LAYER__EDGE_MAPPINGS:
        case DescriptionPackage.LAYER__EDGE_MAPPING_IMPORTS:
        case DescriptionPackage.LAYER__CONTAINER_MAPPINGS:
        case DescriptionPackage.LAYER__TOOL_SECTIONS:
        case DescriptionPackage.LAYER__DECORATION_DESCRIPTIONS_SET:
        case DescriptionPackage.LAYER__CUSTOMIZATION:
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

        // Create automatically an edge style description on edge mapping and a
        // center label
        // style description on the edge style description
        EdgeMapping edge = DescriptionFactory.eINSTANCE.createEdgeMapping();
        EdgeMapping edgeDomain = DescriptionFactory.eINSTANCE.createEdgeMappingUsingDomainElement();

        // Create an edge style description for the edge mapping
        EdgeStyleDescription edgeStyle = StyleFactory.eINSTANCE.createEdgeStyleDescription();
        // Create an edge style description for the edge mapping with domain
        // element
        EdgeStyleDescription edgeDomainStyle = StyleFactory.eINSTANCE.createEdgeStyleDescription();

        // Add the center label style to edge style description
        edgeStyle.setSizeComputationExpression("2");
        edgeStyle.setCenterLabelStyleDescription(StyleFactory.eINSTANCE.createCenterLabelStyleDescription());
        // Add the center label style to edge style description for edge with
        // domain element
        edgeDomainStyle.setSizeComputationExpression("2");
        edgeDomainStyle.setCenterLabelStyleDescription(StyleFactory.eINSTANCE.createCenterLabelStyleDescription());

        // Add the edge style description to edge mapping
        edge.setStyle(edgeStyle);
        // Add the edge style description to edge mapping with domain element
        edgeDomain.setStyle(edgeDomainStyle);

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.LAYER__NODE_MAPPINGS, DescriptionFactory.eINSTANCE.createNodeMapping()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.LAYER__NODE_MAPPINGS, DescriptionFactory.eINSTANCE.createNodeMappingImport()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.LAYER__EDGE_MAPPINGS, edge));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.LAYER__EDGE_MAPPINGS, edgeDomain));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.LAYER__EDGE_MAPPING_IMPORTS, DescriptionFactory.eINSTANCE.createEdgeMappingImport()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.LAYER__CONTAINER_MAPPINGS, DescriptionFactory.eINSTANCE.createContainerMapping()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.LAYER__CONTAINER_MAPPINGS, DescriptionFactory.eINSTANCE.createContainerMappingImport()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.LAYER__TOOL_SECTIONS, ToolFactory.eINSTANCE.createToolSection()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.LAYER__DECORATION_DESCRIPTIONS_SET, DescriptionFactory.eINSTANCE.createDecorationDescriptionsSet()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.LAYER__CUSTOMIZATION, DescriptionFactory.eINSTANCE.createCustomization()));

        if (object instanceof EObject) {
            Collection<CommandParameter> extraMappings = DialectUIManager.INSTANCE.provideAdditionalMappings((EObject) object);
            newChildDescriptors.addAll(extraMappings);
        }
    }

    /**
     * This returns the label text for
     * {@link org.eclipse.emf.edit.command.CreateChildCommand}. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
        String createChildText = super.getCreateChildText(owner, feature, child, selection);
        if (child != null && isNormalEdgeMapping(child)) {
            if (((EdgeMapping) child).isUseDomainElement()) {
                createChildText = "Element Based Edge";
            } else {
                createChildText = "Relation Based Edge";
            }
        }
        return createChildText;
    }

    /**
     * @not-generated
     */
    private boolean isNormalEdgeMapping(Object obj) {
        return DescriptionPackage.eINSTANCE.getEdgeMapping().isInstance(obj) && ((EObject) obj).eClass().equals(DescriptionPackage.eINSTANCE.getEdgeMapping());
    }
}
