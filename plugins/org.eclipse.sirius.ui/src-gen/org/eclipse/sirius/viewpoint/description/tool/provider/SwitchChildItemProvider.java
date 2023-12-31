/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.viewpoint.description.tool.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IChildCreationExtender;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.viewpoint.description.tool.Case;
import org.eclipse.sirius.viewpoint.description.tool.Default;
import org.eclipse.sirius.viewpoint.description.tool.Switch;
import org.eclipse.sirius.viewpoint.description.tool.SwitchChild;
import org.eclipse.sirius.viewpoint.description.tool.ToolFactory;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.sirius.viewpoint.description.tool.SwitchChild} object.
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class SwitchChildItemProvider extends ItemProviderAdapter
        implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public SwitchChildItemProvider(AdapterFactory adapterFactory) {
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

        }
        return itemPropertyDescriptors;
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
            childrenFeatures.add(ToolPackage.Literals.SWITCH_CHILD__SUB_MODEL_OPERATIONS);
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
        return getString("_UI_SwitchChild_type"); //$NON-NLS-1$
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

        switch (notification.getFeatureID(SwitchChild.class)) {
        case ToolPackage.SWITCH_CHILD__SUB_MODEL_OPERATIONS:
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

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.SWITCH_CHILD__SUB_MODEL_OPERATIONS, ToolFactory.eINSTANCE.createExternalJavaAction()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.SWITCH_CHILD__SUB_MODEL_OPERATIONS, ToolFactory.eINSTANCE.createExternalJavaActionCall()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.SWITCH_CHILD__SUB_MODEL_OPERATIONS, ToolFactory.eINSTANCE.createCreateInstance()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.SWITCH_CHILD__SUB_MODEL_OPERATIONS, ToolFactory.eINSTANCE.createChangeContext()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.SWITCH_CHILD__SUB_MODEL_OPERATIONS, ToolFactory.eINSTANCE.createSetValue()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.SWITCH_CHILD__SUB_MODEL_OPERATIONS, ToolFactory.eINSTANCE.createSetObject()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.SWITCH_CHILD__SUB_MODEL_OPERATIONS, ToolFactory.eINSTANCE.createUnset()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.SWITCH_CHILD__SUB_MODEL_OPERATIONS, ToolFactory.eINSTANCE.createMoveElement()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.SWITCH_CHILD__SUB_MODEL_OPERATIONS, ToolFactory.eINSTANCE.createRemoveElement()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.SWITCH_CHILD__SUB_MODEL_OPERATIONS, ToolFactory.eINSTANCE.createFor()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.SWITCH_CHILD__SUB_MODEL_OPERATIONS, ToolFactory.eINSTANCE.createIf()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.SWITCH_CHILD__SUB_MODEL_OPERATIONS, ToolFactory.eINSTANCE.createLet()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.SWITCH_CHILD__SUB_MODEL_OPERATIONS, ToolFactory.eINSTANCE.createDeleteView()));

        Switch switchCase = ToolFactory.eINSTANCE.createSwitch();
        Case caseCase = ToolFactory.eINSTANCE.createCase();
        switchCase.getCases().add(caseCase);
        Default defaultCase = ToolFactory.eINSTANCE.createDefault();
        switchCase.setDefault(defaultCase);

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.SWITCH_CHILD__SUB_MODEL_OPERATIONS, switchCase));
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children that can be created
     * under this object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void collectNewChildDescriptorsGen(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.SWITCH_CHILD__SUB_MODEL_OPERATIONS, ToolFactory.eINSTANCE.createExternalJavaAction()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.SWITCH_CHILD__SUB_MODEL_OPERATIONS, ToolFactory.eINSTANCE.createExternalJavaActionCall()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.SWITCH_CHILD__SUB_MODEL_OPERATIONS, ToolFactory.eINSTANCE.createCreateInstance()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.SWITCH_CHILD__SUB_MODEL_OPERATIONS, ToolFactory.eINSTANCE.createChangeContext()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.SWITCH_CHILD__SUB_MODEL_OPERATIONS, ToolFactory.eINSTANCE.createSetValue()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.SWITCH_CHILD__SUB_MODEL_OPERATIONS, ToolFactory.eINSTANCE.createSetObject()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.SWITCH_CHILD__SUB_MODEL_OPERATIONS, ToolFactory.eINSTANCE.createUnset()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.SWITCH_CHILD__SUB_MODEL_OPERATIONS, ToolFactory.eINSTANCE.createMoveElement()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.SWITCH_CHILD__SUB_MODEL_OPERATIONS, ToolFactory.eINSTANCE.createRemoveElement()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.SWITCH_CHILD__SUB_MODEL_OPERATIONS, ToolFactory.eINSTANCE.createFor()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.SWITCH_CHILD__SUB_MODEL_OPERATIONS, ToolFactory.eINSTANCE.createIf()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.SWITCH_CHILD__SUB_MODEL_OPERATIONS, ToolFactory.eINSTANCE.createDeleteView()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.SWITCH_CHILD__SUB_MODEL_OPERATIONS, ToolFactory.eINSTANCE.createSwitch()));

        newChildDescriptors.add(createChildParameter(ToolPackage.Literals.SWITCH_CHILD__SUB_MODEL_OPERATIONS, ToolFactory.eINSTANCE.createLet()));
    }

    /**
     * Return the resource locator for this item provider's resources. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ResourceLocator getResourceLocator() {
        return ((IChildCreationExtender) adapterFactory).getResourceLocator();
    }

}
