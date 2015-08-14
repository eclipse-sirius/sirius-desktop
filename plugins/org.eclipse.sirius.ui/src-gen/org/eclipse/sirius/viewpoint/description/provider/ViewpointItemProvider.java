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
package org.eclipse.sirius.viewpoint.description.provider;

import java.net.URL;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.featureExtensions.FeatureExtensionsUIManager;
import org.eclipse.sirius.ui.business.api.template.RepresentationTemplateEditManager;
import org.eclipse.sirius.ui.tools.internal.util.EMFCoreUtil;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.validation.ValidationFactory;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.viewpoint.description.Viewpoint} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class ViewpointItemProvider extends DocumentedElementItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ViewpointItemProvider(AdapterFactory adapterFactory) {
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
            addModelFileExtensionPropertyDescriptor(object);
            addIconPropertyDescriptor(object);
            addConflictsPropertyDescriptor(object);
            addReusesPropertyDescriptor(object);
            addCustomizesPropertyDescriptor(object);
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
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_EndUserDocumentedElement_endUserDocumentation_feature"), //$NON-NLS-1$
                getString("_UI_EndUserDocumentedElement_endUserDocumentation_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.END_USER_DOCUMENTED_ELEMENT__END_USER_DOCUMENTATION, true, true, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                getString("_UI_DocumentationPropertyCategory"), //$NON-NLS-1$
                null));
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
     * This adds a property descriptor for the Model File Extension feature.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addModelFileExtensionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_Viewpoint_modelFileExtension_feature"), //$NON-NLS-1$
                getString("_UI_Viewpoint_modelFileExtension_description"), //$NON-NLS-1$
                DescriptionPackage.Literals.VIEWPOINT__MODEL_FILE_EXTENSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Icon feature. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addIconPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_Viewpoint_icon_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_Viewpoint_icon_feature", "_UI_Viewpoint_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DescriptionPackage.Literals.VIEWPOINT__ICON, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
    }

    /**
     * This adds a property descriptor for the Conflicts feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addConflictsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_Viewpoint_conflicts_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_Viewpoint_conflicts_feature", "_UI_Viewpoint_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DescriptionPackage.Literals.VIEWPOINT__CONFLICTS, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Reuses feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addReusesPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_Viewpoint_reuses_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_Viewpoint_reuses_feature", "_UI_Viewpoint_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DescriptionPackage.Literals.VIEWPOINT__REUSES, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Customizes feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addCustomizesPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_Viewpoint_customizes_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_Viewpoint_customizes_feature", "_UI_Viewpoint_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                DescriptionPackage.Literals.VIEWPOINT__CUSTOMIZES, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
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
            childrenFeatures.add(DescriptionPackage.Literals.VIEWPOINT__VALIDATION_SET);
            childrenFeatures.add(DescriptionPackage.Literals.VIEWPOINT__OWNED_REPRESENTATIONS);
            childrenFeatures.add(DescriptionPackage.Literals.VIEWPOINT__OWNED_REPRESENTATION_EXTENSIONS);
            childrenFeatures.add(DescriptionPackage.Literals.VIEWPOINT__OWNED_JAVA_EXTENSIONS);
            childrenFeatures.add(DescriptionPackage.Literals.VIEWPOINT__OWNED_MM_EXTENSIONS);
            childrenFeatures.add(DescriptionPackage.Literals.VIEWPOINT__OWNED_FEATURE_EXTENSIONS);
            childrenFeatures.add(DescriptionPackage.Literals.VIEWPOINT__OWNED_TEMPLATES);
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
     * This returns Sirius.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
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
        return overlayImage(object, getResourceLocator().getImage("full/obj16/Sirius")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public String getText(Object object) {
        String label = new IdentifiedElementQuery((Viewpoint) object).getLabel();
        return label == null || label.length() == 0 ? getString("_UI_Viewpoint_type") : label; //$NON-NLS-1$
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

        switch (notification.getFeatureID(Viewpoint.class)) {
        case DescriptionPackage.VIEWPOINT__END_USER_DOCUMENTATION:
        case DescriptionPackage.VIEWPOINT__NAME:
        case DescriptionPackage.VIEWPOINT__LABEL:
        case DescriptionPackage.VIEWPOINT__MODEL_FILE_EXTENSION:
        case DescriptionPackage.VIEWPOINT__ICON:
        case DescriptionPackage.VIEWPOINT__CONFLICTS:
        case DescriptionPackage.VIEWPOINT__REUSES:
        case DescriptionPackage.VIEWPOINT__CUSTOMIZES:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case DescriptionPackage.VIEWPOINT__VALIDATION_SET:
        case DescriptionPackage.VIEWPOINT__OWNED_REPRESENTATIONS:
        case DescriptionPackage.VIEWPOINT__OWNED_REPRESENTATION_EXTENSIONS:
        case DescriptionPackage.VIEWPOINT__OWNED_JAVA_EXTENSIONS:
        case DescriptionPackage.VIEWPOINT__OWNED_MM_EXTENSIONS:
        case DescriptionPackage.VIEWPOINT__OWNED_FEATURE_EXTENSIONS:
        case DescriptionPackage.VIEWPOINT__OWNED_TEMPLATES:
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
     * @not-generated call the dialectUImanager to retrieve the contributed new
     *                childs.
     */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.VIEWPOINT__VALIDATION_SET, ValidationFactory.eINSTANCE.createValidationSet()));

        // newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.VIEWPOINT__OWNED_REPRESENTATIONS,
        // DescriptionFactory.eINSTANCE.createDiagramDescription()));

        // newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.VIEWPOINT__OWNED_REPRESENTATIONS,
        // DescriptionFactory.eINSTANCE.createDiagramImportDescription()));

        // newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.VIEWPOINT__OWNED_REPRESENTATION_EXTENSIONS,
        // DescriptionFactory.eINSTANCE.createDiagramExtensionDescription()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.VIEWPOINT__OWNED_JAVA_EXTENSIONS,
                org.eclipse.sirius.viewpoint.description.DescriptionFactory.eINSTANCE.createJavaExtension()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.VIEWPOINT__OWNED_MM_EXTENSIONS,
                org.eclipse.sirius.viewpoint.description.DescriptionFactory.eINSTANCE.createMetamodelExtensionSetting()));

        // add known representation descriptions
        newChildDescriptors.addAll(DialectUIManager.INSTANCE.provideNewChildDescriptors());

        // add known representation templates
        newChildDescriptors.addAll(RepresentationTemplateEditManager.INSTANCE.provideNewChildDescriptors());

        // add known feature extensions
        newChildDescriptors.addAll(FeatureExtensionsUIManager.INSTANCE.provideNewChildDescriptors());
    }

    /**
     *
     * @param newChildDescriptors
     * @param object
     * @generated
     */
    protected void collectNewChildDescriptorsGen(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.VIEWPOINT__VALIDATION_SET, ValidationFactory.eINSTANCE.createValidationSet()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.VIEWPOINT__OWNED_JAVA_EXTENSIONS, DescriptionFactory.eINSTANCE.createJavaExtension()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.VIEWPOINT__OWNED_MM_EXTENSIONS, DescriptionFactory.eINSTANCE.createMetamodelExtensionSetting()));
    }

}
