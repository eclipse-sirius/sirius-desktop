/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
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
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.diagram.description.tool.ToolFactory;
import org.eclipse.sirius.diagram.description.tool.ToolGroupExtension;
import org.eclipse.sirius.diagram.description.tool.ToolPackage;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;

/**
 * This is the item provider adapter for a {@link org.eclipse.sirius.diagram.description.tool.ToolGroupExtension}
 * object. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class ToolGroupExtensionItemProvider extends ItemProviderAdapter
        implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ToolGroupExtensionItemProvider(AdapterFactory adapterFactory) {
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

            addGroupPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Group feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addGroupPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_ToolGroupExtension_group_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_ToolGroupExtension_group_feature", "_UI_ToolGroupExtension_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        ToolPackage.Literals.TOOL_GROUP_EXTENSION__GROUP, true, false, true, null, null, null));
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
            childrenFeatures.add(ToolPackage.Literals.TOOL_GROUP_EXTENSION__TOOLS);
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
     * This returns ToolGroupExtension.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/ToolGroupExtension")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getText(Object object) {
        return getString("_UI_ToolGroupExtension_type"); //$NON-NLS-1$
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

        switch (notification.getFeatureID(ToolGroupExtension.class)) {
        case ToolPackage.TOOL_GROUP_EXTENSION__TOOLS:
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

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.TOOL_GROUP_EXTENSION__TOOLS, ToolFactory.eINSTANCE.createNodeCreationDescription()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.TOOL_GROUP_EXTENSION__TOOLS, ToolFactory.eINSTANCE.createEdgeCreationDescription()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.TOOL_GROUP_EXTENSION__TOOLS, ToolFactory.eINSTANCE.createContainerCreationDescription()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.TOOL_GROUP_EXTENSION__TOOLS, ToolFactory.eINSTANCE.createDeleteElementDescription()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.TOOL_GROUP_EXTENSION__TOOLS, ToolFactory.eINSTANCE.createDoubleClickDescription()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.TOOL_GROUP_EXTENSION__TOOLS, ToolFactory.eINSTANCE.createReconnectEdgeDescription()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.TOOL_GROUP_EXTENSION__TOOLS, ToolFactory.eINSTANCE.createRequestDescription()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.TOOL_GROUP_EXTENSION__TOOLS, ToolFactory.eINSTANCE.createDirectEditLabel()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.TOOL_GROUP_EXTENSION__TOOLS, ToolFactory.eINSTANCE.createBehaviorTool()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.TOOL_GROUP_EXTENSION__TOOLS, ToolFactory.eINSTANCE.createDiagramCreationDescription()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.TOOL_GROUP_EXTENSION__TOOLS, ToolFactory.eINSTANCE.createDiagramNavigationDescription()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.TOOL_GROUP_EXTENSION__TOOLS, ToolFactory.eINSTANCE.createContainerDropDescription()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.TOOL_GROUP_EXTENSION__TOOLS, org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createToolDescription()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.TOOL_GROUP_EXTENSION__TOOLS, org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createPasteDescription()));

        newChildDescriptors
                .add(createChildParameter(ToolPackage.Literals.TOOL_GROUP_EXTENSION__TOOLS, org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createSelectionWizardDescription()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.TOOL_GROUP_EXTENSION__TOOLS,
                org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createPaneBasedSelectionWizardDescription()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.TOOL_GROUP_EXTENSION__TOOLS, org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createOperationAction()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.TOOL_GROUP_EXTENSION__TOOLS, org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createExternalJavaAction()));

        newChildDescriptors
                .add(createChildParameter(ToolPackage.Literals.TOOL_GROUP_EXTENSION__TOOLS, org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createExternalJavaActionCall()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.TOOL_GROUP_EXTENSION__TOOLS, org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createPopupMenu()));
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
