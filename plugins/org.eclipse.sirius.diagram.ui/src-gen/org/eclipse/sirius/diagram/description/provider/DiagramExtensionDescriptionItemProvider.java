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
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.DiagramExtensionDescription;
import org.eclipse.sirius.diagram.description.concern.ConcernFactory;
import org.eclipse.sirius.diagram.description.concern.ConcernSet;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.validation.ValidationFactory;

/**
 * This is the item provider adapter for a {@link org.eclipse.sirius.diagram.description.DiagramExtensionDescription}
 * object. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class DiagramExtensionDescriptionItemProvider extends ItemProviderAdapter
        implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DiagramExtensionDescriptionItemProvider(AdapterFactory adapterFactory) {
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
            addViewpointURIPropertyDescriptor(object);
            addRepresentationNamePropertyDescriptor(object);
            addMetamodelPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Name feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addNamePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_RepresentationExtensionDescription_name_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_RepresentationExtensionDescription_name_feature", "_UI_RepresentationExtensionDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DescriptionPackage.Literals.REPRESENTATION_EXTENSION_DESCRIPTION__NAME, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Viewpoint URI feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addViewpointURIPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_RepresentationExtensionDescription_viewpointURI_feature"), //$NON-NLS-1$
                getString("_UI_RepresentationExtensionDescription_viewpointURI_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.REPRESENTATION_EXTENSION_DESCRIPTION__VIEWPOINT_URI, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Representation Name feature. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    protected void addRepresentationNamePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_RepresentationExtensionDescription_representationName_feature"), //$NON-NLS-1$
                getString("_UI_RepresentationExtensionDescription_representationName_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.REPRESENTATION_EXTENSION_DESCRIPTION__REPRESENTATION_NAME, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Metamodel feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addMetamodelPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_RepresentationExtensionDescription_metamodel_feature"), //$NON-NLS-1$
                getString("_UI_RepresentationExtensionDescription_metamodel_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.REPRESENTATION_EXTENSION_DESCRIPTION__METAMODEL, true, false, true, null, getString("_UI_AdvancedPropertyCategory"), //$NON-NLS-1$
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
            childrenFeatures.add(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_EXTENSION_DESCRIPTION__LAYERS);
            childrenFeatures.add(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_EXTENSION_DESCRIPTION__VALIDATION_SET);
            childrenFeatures.add(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_EXTENSION_DESCRIPTION__CONCERNS);
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
     * This returns DiagramExtensionDescription.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/DiagramExtensionDescription")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getText(Object object) {
        String label = ((DiagramExtensionDescription) object).getName();
        return label == null || label.length() == 0 ? getString("_UI_DiagramExtensionDescription_type") : //$NON-NLS-1$
                getString("_UI_DiagramExtensionDescription_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

        switch (notification.getFeatureID(DiagramExtensionDescription.class)) {
        case org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__NAME:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__VIEWPOINT_URI:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__REPRESENTATION_NAME:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__LAYERS:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__VALIDATION_SET:
        case org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__CONCERNS:
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

        newChildDescriptors.add(
                createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_EXTENSION_DESCRIPTION__LAYERS, DescriptionFactory.eINSTANCE.createAdditionalLayer()));

        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_EXTENSION_DESCRIPTION__VALIDATION_SET,
                ValidationFactory.eINSTANCE.createValidationSet()));

        ConcernSet concernSet = ConcernFactory.eINSTANCE.createConcernSet();
        concernSet.getOwnedConcernDescriptions().add(ConcernFactory.eINSTANCE.createConcernDescription());
        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_EXTENSION_DESCRIPTION__CONCERNS, concernSet));
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children that can be created
     * under this object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void collectNewChildDescriptorsGen(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);

        newChildDescriptors.add(
                createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_EXTENSION_DESCRIPTION__LAYERS, DescriptionFactory.eINSTANCE.createAdditionalLayer()));

        newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_EXTENSION_DESCRIPTION__VALIDATION_SET,
                ValidationFactory.eINSTANCE.createValidationSet()));

        newChildDescriptors
                .add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.DIAGRAM_EXTENSION_DESCRIPTION__CONCERNS, ConcernFactory.eINSTANCE.createConcernSet()));
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
