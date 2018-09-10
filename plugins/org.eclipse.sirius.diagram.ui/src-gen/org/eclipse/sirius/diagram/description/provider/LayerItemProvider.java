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

import java.net.URL;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.style.CenterLabelStyleDescription;
import org.eclipse.sirius.diagram.description.style.EdgeStyleDescription;
import org.eclipse.sirius.diagram.description.style.StyleFactory;
import org.eclipse.sirius.diagram.description.tool.ToolFactory;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.tools.internal.util.EMFCoreUtil;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.provider.DocumentedElementItemProvider;

/**
 * This is the item provider adapter for a {@link org.eclipse.sirius.diagram.description.Layer} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class LayerItemProvider extends DocumentedElementItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public LayerItemProvider(AdapterFactory adapterFactory) {
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

            addEndUserDocumentationPropertyDescriptor(object);
            addNamePropertyDescriptor(object);
            addLabelPropertyDescriptor(object);
            addReusedMappingsPropertyDescriptor(object);
            addAllToolsPropertyDescriptor(object);
            addReusedToolsPropertyDescriptor(object);
            addIconPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
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
     * This adds a property descriptor for the Reused Mappings feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addReusedMappingsPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_Layer_reusedMappings_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_Layer_reusedMappings_feature", "_UI_Layer_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.LAYER__REUSED_MAPPINGS, true, false, true, null, getString("_UI_ImportPropertyCategory"), //$NON-NLS-1$
                        null));
    }

    /**
     * This adds a property descriptor for the All Tools feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addAllToolsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_Layer_allTools_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_Layer_allTools_feature", "_UI_Layer_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.LAYER__ALL_TOOLS, false, false, false, null, null, null));
    }

    /**
     * This adds a property descriptor for the Reused Tools feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addReusedToolsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_Layer_reusedTools_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_Layer_reusedTools_feature", "_UI_Layer_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.LAYER__REUSED_TOOLS, true, false, true, null, getString("_UI_ImportPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Icon feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addIconPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_Layer_icon_feature"), //$NON-NLS-1$
                getString("_UI_Layer_icon_description"), //$NON-NLS-1$
                org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.LAYER__ICON, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                getString("_UI_AdvancedPropertyCategory"), //$NON-NLS-1$
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
            childrenFeatures.add(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.LAYER__NODE_MAPPINGS);
            childrenFeatures.add(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.LAYER__EDGE_MAPPINGS);
            childrenFeatures.add(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.LAYER__EDGE_MAPPING_IMPORTS);
            childrenFeatures.add(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.LAYER__CONTAINER_MAPPINGS);
            childrenFeatures.add(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.LAYER__TOOL_SECTIONS);
            childrenFeatures.add(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.LAYER__DECORATION_DESCRIPTIONS_SET);
            childrenFeatures.add(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.LAYER__CUSTOMIZATION);
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

        return overlayImage(object, getResourceLocator().getImage("full/obj16/Layer")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public String getText(Object object) {
        String label = new IdentifiedElementQuery((IdentifiedElement) object).getLabel();
        return label == null || label.length() == 0 ? getString("_UI_Layer_type") : label; //$NON-NLS-1$
    }

    /**
     * This handles model notifications by calling {@link #updateChildren} to update any cached children and by creating
     * a viewer notification, which it passes to {@link #fireNotifyChanged}. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @not-generated
     */
    @Override
    public void notifyChanged(Notification notification) {
        updateChildren(notification);

        switch (notification.getFeatureID(Layer.class)) {
        case org.eclipse.sirius.diagram.description.DescriptionPackage.LAYER__END_USER_DOCUMENTATION:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.LAYER__NAME:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.LAYER__LABEL:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.LAYER__ICON:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case org.eclipse.sirius.diagram.description.DescriptionPackage.LAYER__NODE_MAPPINGS:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.LAYER__EDGE_MAPPINGS:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.LAYER__EDGE_MAPPING_IMPORTS:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.LAYER__CONTAINER_MAPPINGS:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.LAYER__TOOL_SECTIONS:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.LAYER__DECORATION_DESCRIPTIONS_SET:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.LAYER__CUSTOMIZATION:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, true));
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
        edgeStyle.setSizeComputationExpression("2"); //$NON-NLS-1$
        CenterLabelStyleDescription centerLabelEdgeStyle = StyleFactory.eINSTANCE.createCenterLabelStyleDescription();
        centerLabelEdgeStyle.setLabelSize(12);
        edgeStyle.setCenterLabelStyleDescription(centerLabelEdgeStyle);
        // Add the center label style to edge style description for edge with
        // domain element
        edgeDomainStyle.setSizeComputationExpression("2"); //$NON-NLS-1$
        CenterLabelStyleDescription centerLabelEdgeDomainStyle = StyleFactory.eINSTANCE.createCenterLabelStyleDescription();
        centerLabelEdgeDomainStyle.setLabelSize(12);
        edgeDomainStyle.setCenterLabelStyleDescription(centerLabelEdgeDomainStyle);

        // Add the edge style description to edge mapping
        edge.setStyle(edgeStyle);
        // Add the edge style description to edge mapping with domain element
        edgeDomain.setStyle(edgeDomainStyle);

        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.LAYER__NODE_MAPPINGS, DescriptionFactory.eINSTANCE.createNodeMapping()));

        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.LAYER__NODE_MAPPINGS, DescriptionFactory.eINSTANCE.createNodeMappingImport()));

        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.LAYER__EDGE_MAPPINGS, edge));

        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.LAYER__EDGE_MAPPINGS, edgeDomain));

        newChildDescriptors
                .add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.LAYER__EDGE_MAPPING_IMPORTS, DescriptionFactory.eINSTANCE.createEdgeMappingImport()));

        newChildDescriptors
                .add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.LAYER__CONTAINER_MAPPINGS, DescriptionFactory.eINSTANCE.createContainerMapping()));

        newChildDescriptors
                .add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.LAYER__CONTAINER_MAPPINGS, DescriptionFactory.eINSTANCE.createContainerMappingImport()));

        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.LAYER__TOOL_SECTIONS, ToolFactory.eINSTANCE.createToolSection()));

        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.LAYER__DECORATION_DESCRIPTIONS_SET,
                org.eclipse.sirius.viewpoint.description.DescriptionFactory.eINSTANCE.createDecorationDescriptionsSet()));

        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.LAYER__CUSTOMIZATION,
                org.eclipse.sirius.viewpoint.description.DescriptionFactory.eINSTANCE.createCustomization()));

        if (object instanceof EObject) {
            Collection<CommandParameter> extraMappings = DialectUIManager.INSTANCE.provideAdditionalMappings((EObject) object);
            newChildDescriptors.addAll(extraMappings);
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

    /**
     * Return the resource locator for this item provider's resources. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ResourceLocator getResourceLocator() {
        return DiagramUIPlugin.INSTANCE;
    }

}
