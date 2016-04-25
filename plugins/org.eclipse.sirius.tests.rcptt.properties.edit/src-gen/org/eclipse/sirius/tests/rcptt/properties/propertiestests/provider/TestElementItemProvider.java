/**
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.htm
 */
package org.eclipse.sirius.tests.rcptt.properties.propertiestests.provider;

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
import org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsFactory;
import org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage;
import org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement}
 * object. <!-- begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class TestElementItemProvider extends ItemProviderAdapter
        implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public TestElementItemProvider(AdapterFactory adapterFactory) {
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

            addStringAttributePropertyDescriptor(object);
            addStringAttributesPropertyDescriptor(object);
            addIntAttributePropertyDescriptor(object);
            addIntAttributesPropertyDescriptor(object);
            addBooleanAttributePropertyDescriptor(object);
            addBooleanAttributesPropertyDescriptor(object);
            addEnumAttributePropertyDescriptor(object);
            addEnumAttributesPropertyDescriptor(object);
            addReferencePropertyDescriptor(object);
            addReferencesPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the String Attribute feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addStringAttributePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_TestElement_stringAttribute_feature"), getString("_UI_PropertyDescriptor_description", "_UI_TestElement_stringAttribute_feature", "_UI_TestElement_type"),
                PropertiestestsPackage.Literals.TEST_ELEMENT__STRING_ATTRIBUTE, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the String Attributes feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addStringAttributesPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_TestElement_stringAttributes_feature"), getString("_UI_PropertyDescriptor_description", "_UI_TestElement_stringAttributes_feature", "_UI_TestElement_type"),
                PropertiestestsPackage.Literals.TEST_ELEMENT__STRING_ATTRIBUTES, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Int Attribute feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addIntAttributePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_TestElement_intAttribute_feature"), getString("_UI_PropertyDescriptor_description", "_UI_TestElement_intAttribute_feature", "_UI_TestElement_type"),
                PropertiestestsPackage.Literals.TEST_ELEMENT__INT_ATTRIBUTE, true, false, false, ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Int Attributes feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addIntAttributesPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_TestElement_intAttributes_feature"), getString("_UI_PropertyDescriptor_description", "_UI_TestElement_intAttributes_feature", "_UI_TestElement_type"),
                PropertiestestsPackage.Literals.TEST_ELEMENT__INT_ATTRIBUTES, true, false, false, ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Boolean Attribute feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addBooleanAttributePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_TestElement_booleanAttribute_feature"), getString("_UI_PropertyDescriptor_description", "_UI_TestElement_booleanAttribute_feature", "_UI_TestElement_type"),
                PropertiestestsPackage.Literals.TEST_ELEMENT__BOOLEAN_ATTRIBUTE, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Boolean Attributes feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addBooleanAttributesPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_TestElement_booleanAttributes_feature"), getString("_UI_PropertyDescriptor_description", "_UI_TestElement_booleanAttributes_feature", "_UI_TestElement_type"),
                PropertiestestsPackage.Literals.TEST_ELEMENT__BOOLEAN_ATTRIBUTES, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Enum Attribute feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addEnumAttributePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_TestElement_enumAttribute_feature"), getString("_UI_PropertyDescriptor_description", "_UI_TestElement_enumAttribute_feature", "_UI_TestElement_type"),
                PropertiestestsPackage.Literals.TEST_ELEMENT__ENUM_ATTRIBUTE, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Enum Attributes feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addEnumAttributesPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_TestElement_enumAttributes_feature"), getString("_UI_PropertyDescriptor_description", "_UI_TestElement_enumAttributes_feature", "_UI_TestElement_type"),
                PropertiestestsPackage.Literals.TEST_ELEMENT__ENUM_ATTRIBUTES, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Reference feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addReferencePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_TestElement_reference_feature"), getString("_UI_PropertyDescriptor_description", "_UI_TestElement_reference_feature", "_UI_TestElement_type"),
                PropertiestestsPackage.Literals.TEST_ELEMENT__REFERENCE, true, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the References feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addReferencesPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_TestElement_references_feature"), getString("_UI_PropertyDescriptor_description", "_UI_TestElement_references_feature", "_UI_TestElement_type"),
                PropertiestestsPackage.Literals.TEST_ELEMENT__REFERENCES, true, false, true, null, null, null));
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
            childrenFeatures.add(PropertiestestsPackage.Literals.TEST_ELEMENT__CONTAINMENT_REFERENCE);
            childrenFeatures.add(PropertiestestsPackage.Literals.TEST_ELEMENT__CONTAINMENT_REFERENCES);
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
     * This returns TestElement.gif. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/TestElement"));
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getText(Object object) {
        String label = ((TestElement) object).getStringAttribute();
        return label == null || label.length() == 0 ? getString("_UI_TestElement_type") : getString("_UI_TestElement_type") + " " + label;
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

        switch (notification.getFeatureID(TestElement.class)) {
        case PropertiestestsPackage.TEST_ELEMENT__STRING_ATTRIBUTE:
        case PropertiestestsPackage.TEST_ELEMENT__STRING_ATTRIBUTES:
        case PropertiestestsPackage.TEST_ELEMENT__INT_ATTRIBUTE:
        case PropertiestestsPackage.TEST_ELEMENT__INT_ATTRIBUTES:
        case PropertiestestsPackage.TEST_ELEMENT__BOOLEAN_ATTRIBUTE:
        case PropertiestestsPackage.TEST_ELEMENT__BOOLEAN_ATTRIBUTES:
        case PropertiestestsPackage.TEST_ELEMENT__ENUM_ATTRIBUTE:
        case PropertiestestsPackage.TEST_ELEMENT__ENUM_ATTRIBUTES:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCE:
        case PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCES:
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
     * @generated
     */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);

        newChildDescriptors.add(createChildParameter(PropertiestestsPackage.Literals.TEST_ELEMENT__CONTAINMENT_REFERENCE, PropertiestestsFactory.eINSTANCE.createTestElement()));

        newChildDescriptors.add(createChildParameter(PropertiestestsPackage.Literals.TEST_ELEMENT__CONTAINMENT_REFERENCES, PropertiestestsFactory.eINSTANCE.createTestElement()));
    }

    /**
     * This returns the label text for
     * {@link org.eclipse.emf.edit.command.CreateChildCommand}. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
        Object childFeature = feature;
        Object childObject = child;

        boolean qualify = childFeature == PropertiestestsPackage.Literals.TEST_ELEMENT__CONTAINMENT_REFERENCE || childFeature == PropertiestestsPackage.Literals.TEST_ELEMENT__CONTAINMENT_REFERENCES;

        if (qualify) {
            return getString("_UI_CreateChild_text2", new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
        }
        return super.getCreateChildText(owner, feature, child, selection);
    }

    /**
     * Return the resource locator for this item provider's resources. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ResourceLocator getResourceLocator() {
        return PropertiestestsEditPlugin.INSTANCE;
    }

}
