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
package org.eclipse.sirius.diagram.description.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.diagram.description.ConditionalContainerStyleDescription;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.style.FlatContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.ShapeContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.StyleFactory;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.viewpoint.description.provider.ConditionalStyleDescriptionItemProvider;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.diagram.description.ConditionalContainerStyleDescription}
 * object. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class ConditionalContainerStyleDescriptionItemProvider extends ConditionalStyleDescriptionItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ConditionalContainerStyleDescriptionItemProvider(AdapterFactory adapterFactory) {
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

        }
        return itemPropertyDescriptors;
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
            childrenFeatures.add(DescriptionPackage.Literals.CONDITIONAL_CONTAINER_STYLE_DESCRIPTION__STYLE);
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
     * This returns ConditionalContainerStyleDescription.gif. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/ConditionalContainerStyleDescription")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getText(Object object) {
        String label = ((ConditionalContainerStyleDescription) object).getPredicateExpression();
        return label == null || label.length() == 0 ? getString("_UI_ConditionalContainerStyleDescription_type") : //$NON-NLS-1$
            getString("_UI_ConditionalContainerStyleDescription_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

        switch (notification.getFeatureID(ConditionalContainerStyleDescription.class)) {
        case DescriptionPackage.CONDITIONAL_CONTAINER_STYLE_DESCRIPTION__STYLE:
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
     * @not-generated: Set to 1 the default borderSizeComputationExpression
     *                 instead of 0 for flatContainerStyle and
     *                 shapeContainerStyle.
     */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);

        FlatContainerStyleDescription flatContainerStyleDescription = StyleFactory.eINSTANCE.createFlatContainerStyleDescription();
        flatContainerStyleDescription.setBorderSizeComputationExpression("1"); //$NON-NLS-1$
        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.CONDITIONAL_CONTAINER_STYLE_DESCRIPTION__STYLE, flatContainerStyleDescription));

        ShapeContainerStyleDescription shapeContainerStyleDescription = StyleFactory.eINSTANCE.createShapeContainerStyleDescription();
        shapeContainerStyleDescription.setBorderSizeComputationExpression("1"); //$NON-NLS-1$
        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.CONDITIONAL_CONTAINER_STYLE_DESCRIPTION__STYLE, shapeContainerStyleDescription));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.CONDITIONAL_CONTAINER_STYLE_DESCRIPTION__STYLE, StyleFactory.eINSTANCE.createWorkspaceImageDescription()));
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

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.CONDITIONAL_CONTAINER_STYLE_DESCRIPTION__STYLE, StyleFactory.eINSTANCE.createFlatContainerStyleDescription()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.CONDITIONAL_CONTAINER_STYLE_DESCRIPTION__STYLE, StyleFactory.eINSTANCE.createShapeContainerStyleDescription()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.CONDITIONAL_CONTAINER_STYLE_DESCRIPTION__STYLE, StyleFactory.eINSTANCE.createWorkspaceImageDescription()));
    }

    /**
     * Return the resource locator for this item provider's resources. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ResourceLocator getResourceLocator() {
        return DiagramUIPlugin.INSTANCE;
    }

}
