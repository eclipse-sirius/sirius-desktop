/**
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.diagram.description.tool.provider;

import java.util.Collection;
import java.util.Iterator;
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
import org.eclipse.sirius.diagram.description.tool.DiagramCreationDescription;
import org.eclipse.sirius.diagram.description.tool.DiagramNavigationDescription;
import org.eclipse.sirius.diagram.description.tool.ToolFactory;
import org.eclipse.sirius.diagram.description.tool.ToolPackage;
import org.eclipse.sirius.diagram.description.tool.ToolSection;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.provider.DocumentedElementItemProvider;

/**
 * This is the item provider adapter for a {@link org.eclipse.sirius.diagram.description.tool.ToolSection} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class ToolSectionItemProvider extends DocumentedElementItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ToolSectionItemProvider(AdapterFactory adapterFactory) {
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

            addNamePropertyDescriptor(object);
            addLabelPropertyDescriptor(object);
            addIconPropertyDescriptor(object);
            addPopupMenusPropertyDescriptor(object);
            addReusedToolsPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
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
     * This adds a property descriptor for the Icon feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addIconPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_ToolSection_icon_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_ToolSection_icon_feature", "_UI_ToolSection_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                ToolPackage.Literals.TOOL_SECTION__ICON, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Popup Menus feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addPopupMenusPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_ToolSection_popupMenus_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_ToolSection_popupMenus_feature", "_UI_ToolSection_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        ToolPackage.Literals.TOOL_SECTION__POPUP_MENUS, false, false, false, null, null, null));
    }

    /**
     * This adds a property descriptor for the Reused Tools feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addReusedToolsPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_ToolSection_reusedTools_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_ToolSection_reusedTools_feature", "_UI_ToolSection_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        ToolPackage.Literals.TOOL_SECTION__REUSED_TOOLS, true, false, true, null, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
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
            childrenFeatures.add(ToolPackage.Literals.TOOL_SECTION__OWNED_TOOLS);
            childrenFeatures.add(ToolPackage.Literals.TOOL_SECTION__SUB_SECTIONS);
            childrenFeatures.add(ToolPackage.Literals.TOOL_SECTION__GROUP_EXTENSIONS);
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
     * This returns ToolSection.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/ToolSection")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public String getText(Object object) {
        String label = new IdentifiedElementQuery((IdentifiedElement) object).getLabel();
        return label == null || label.length() == 0 ? getString("_UI_ToolSection_type") : getString("_UI_ToolSection_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

        switch (notification.getFeatureID(ToolSection.class)) {
        case ToolPackage.TOOL_SECTION__NAME:
        case ToolPackage.TOOL_SECTION__LABEL:
        case ToolPackage.TOOL_SECTION__ICON:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case ToolPackage.TOOL_SECTION__OWNED_TOOLS:
        case ToolPackage.TOOL_SECTION__SUB_SECTIONS:
        case ToolPackage.TOOL_SECTION__GROUP_EXTENSIONS:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
            return;
        }
        super.notifyChanged(notification);
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children that can be created
     * under this object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated : hooking call to add specific tools from the diagram types.
     */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        collectNewChildDescriptorsGen(newChildDescriptors, object);
        if (object instanceof EObject) {
            newChildDescriptors.addAll(DialectUIManager.INSTANCE.provideTools((EObject) object));
        }

        removeChildDescriptors(newChildDescriptors, DiagramCreationDescription.class);
        removeChildDescriptors(newChildDescriptors, DiagramNavigationDescription.class);

        // add creation description tools for known representations
        newChildDescriptors.addAll(DialectUIManager.INSTANCE.provideRepresentationCreationToolDescriptors(ToolPackage.Literals.TOOL_SECTION__OWNED_TOOLS));

        // add navigation description tools for known representations
        newChildDescriptors.addAll(DialectUIManager.INSTANCE.provideRepresentationNavigationToolDescriptors(ToolPackage.Literals.TOOL_SECTION__OWNED_TOOLS));

    }

    /**
     * Remove the descriptor which have a value instance of <code>classOfValue</code>.
     *
     * @param newChildDescriptors
     *            List of child descriptors
     * @param classOfValue
     *            The searched type
     */
    protected void removeChildDescriptors(Collection<Object> newChildDescriptors, Class<? extends Object> classOfValue) {
        for (Iterator<Object> iterator = newChildDescriptors.iterator(); iterator.hasNext();) {
            Object object = iterator.next();
            if (object instanceof CommandParameter && classOfValue.isInstance(((CommandParameter) object).getValue())) {
                iterator.remove();
            }
        }
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children that can be created
     * under this object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void collectNewChildDescriptorsGen(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.TOOL_SECTION__OWNED_TOOLS, ToolFactory.eINSTANCE.createToolGroup()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.TOOL_SECTION__OWNED_TOOLS, ToolFactory.eINSTANCE.createNodeCreationDescription()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.TOOL_SECTION__OWNED_TOOLS, ToolFactory.eINSTANCE.createEdgeCreationDescription()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.TOOL_SECTION__OWNED_TOOLS, ToolFactory.eINSTANCE.createContainerCreationDescription()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.TOOL_SECTION__OWNED_TOOLS, ToolFactory.eINSTANCE.createDeleteElementDescription()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.TOOL_SECTION__OWNED_TOOLS, ToolFactory.eINSTANCE.createDoubleClickDescription()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.TOOL_SECTION__OWNED_TOOLS, ToolFactory.eINSTANCE.createReconnectEdgeDescription()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.TOOL_SECTION__OWNED_TOOLS, ToolFactory.eINSTANCE.createRequestDescription()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.TOOL_SECTION__OWNED_TOOLS, ToolFactory.eINSTANCE.createDirectEditLabel()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.TOOL_SECTION__OWNED_TOOLS, ToolFactory.eINSTANCE.createBehaviorTool()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.TOOL_SECTION__OWNED_TOOLS, ToolFactory.eINSTANCE.createDiagramCreationDescription()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.TOOL_SECTION__OWNED_TOOLS, ToolFactory.eINSTANCE.createDiagramNavigationDescription()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.TOOL_SECTION__OWNED_TOOLS, ToolFactory.eINSTANCE.createContainerDropDescription()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.TOOL_SECTION__OWNED_TOOLS, org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createToolDescription()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.TOOL_SECTION__OWNED_TOOLS, org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createPasteDescription()));

        newChildDescriptors
                .add(createChildParameter(ToolPackage.Literals.TOOL_SECTION__OWNED_TOOLS, org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createSelectionWizardDescription()));

        newChildDescriptors.add(
                createChildParameter(ToolPackage.Literals.TOOL_SECTION__OWNED_TOOLS, org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createPaneBasedSelectionWizardDescription()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.TOOL_SECTION__OWNED_TOOLS, org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createOperationAction()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.TOOL_SECTION__OWNED_TOOLS, org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createExternalJavaAction()));

        newChildDescriptors
                .add(createChildParameter(ToolPackage.Literals.TOOL_SECTION__OWNED_TOOLS, org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createExternalJavaActionCall()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.TOOL_SECTION__OWNED_TOOLS, org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createPopupMenu()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.TOOL_SECTION__SUB_SECTIONS, ToolFactory.eINSTANCE.createToolSection()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.TOOL_SECTION__GROUP_EXTENSIONS, ToolFactory.eINSTANCE.createToolGroupExtension()));
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
