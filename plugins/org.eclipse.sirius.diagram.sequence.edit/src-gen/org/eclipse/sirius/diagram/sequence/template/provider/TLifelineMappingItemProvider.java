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
package org.eclipse.sirius.diagram.sequence.template.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.diagram.description.style.StyleFactory;
import org.eclipse.sirius.diagram.sequence.template.TLifelineMapping;
import org.eclipse.sirius.diagram.sequence.template.TemplateFactory;
import org.eclipse.sirius.diagram.sequence.template.TemplatePackage;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.sirius.diagram.sequence.template.TLifelineMapping} object.
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class TLifelineMappingItemProvider extends TAbstractMappingItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public TLifelineMappingItemProvider(AdapterFactory adapterFactory) {
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

            addEolVisibleExpressionPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Eol Visible Expression feature.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addEolVisibleExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_TLifelineMapping_eolVisibleExpression_feature"), //$NON-NLS-1$
                getString("_UI_TLifelineMapping_eolVisibleExpression_description"), //$NON-NLS-1$
                TemplatePackage.Literals.TLIFELINE_MAPPING__EOL_VISIBLE_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), //$NON-NLS-1$
                null));
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
            childrenFeatures.add(TemplatePackage.Literals.TLIFELINE_MAPPING__EXECUTION_MAPPINGS);
            childrenFeatures.add(TemplatePackage.Literals.TLIFELINE_MAPPING__INSTANCE_ROLE_STYLE);
            childrenFeatures.add(TemplatePackage.Literals.TLIFELINE_MAPPING__LIFELINE_STYLE);
            childrenFeatures.add(TemplatePackage.Literals.TLIFELINE_MAPPING__END_OF_LIFE_STYLE);
            childrenFeatures.add(TemplatePackage.Literals.TLIFELINE_MAPPING__CONDITIONAL_LIFE_LINE_STYLES);
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
     * This returns TLifelineMapping.gif. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/TLifelineMapping")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public String getText(Object object) {
        String label = ((TLifelineMapping) object).getName();
        return label == null || label.length() == 0 ? getString("_UI_TLifelineMapping_type") : label; //$NON-NLS-1$
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

        switch (notification.getFeatureID(TLifelineMapping.class)) {
        case TemplatePackage.TLIFELINE_MAPPING__EOL_VISIBLE_EXPRESSION:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case TemplatePackage.TLIFELINE_MAPPING__EXECUTION_MAPPINGS:
        case TemplatePackage.TLIFELINE_MAPPING__INSTANCE_ROLE_STYLE:
        case TemplatePackage.TLIFELINE_MAPPING__LIFELINE_STYLE:
        case TemplatePackage.TLIFELINE_MAPPING__END_OF_LIFE_STYLE:
        case TemplatePackage.TLIFELINE_MAPPING__CONDITIONAL_LIFE_LINE_STYLES:
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

        newChildDescriptors.add(createChildParameter(TemplatePackage.Literals.TLIFELINE_MAPPING__EXECUTION_MAPPINGS, TemplateFactory.eINSTANCE.createTExecutionMapping()));

        newChildDescriptors.add(createChildParameter(TemplatePackage.Literals.TLIFELINE_MAPPING__INSTANCE_ROLE_STYLE, StyleFactory.eINSTANCE.createCustomStyleDescription()));

        newChildDescriptors.add(createChildParameter(TemplatePackage.Literals.TLIFELINE_MAPPING__INSTANCE_ROLE_STYLE, StyleFactory.eINSTANCE.createSquareDescription()));

        newChildDescriptors.add(createChildParameter(TemplatePackage.Literals.TLIFELINE_MAPPING__INSTANCE_ROLE_STYLE, StyleFactory.eINSTANCE.createLozengeNodeDescription()));

        newChildDescriptors.add(createChildParameter(TemplatePackage.Literals.TLIFELINE_MAPPING__INSTANCE_ROLE_STYLE, StyleFactory.eINSTANCE.createEllipseNodeDescription()));

        newChildDescriptors.add(createChildParameter(TemplatePackage.Literals.TLIFELINE_MAPPING__INSTANCE_ROLE_STYLE, StyleFactory.eINSTANCE.createBundledImageDescription()));

        newChildDescriptors.add(createChildParameter(TemplatePackage.Literals.TLIFELINE_MAPPING__INSTANCE_ROLE_STYLE, StyleFactory.eINSTANCE.createNoteDescription()));

        newChildDescriptors.add(createChildParameter(TemplatePackage.Literals.TLIFELINE_MAPPING__INSTANCE_ROLE_STYLE, StyleFactory.eINSTANCE.createDotDescription()));

        newChildDescriptors.add(createChildParameter(TemplatePackage.Literals.TLIFELINE_MAPPING__INSTANCE_ROLE_STYLE, StyleFactory.eINSTANCE.createGaugeCompositeStyleDescription()));

        newChildDescriptors.add(createChildParameter(TemplatePackage.Literals.TLIFELINE_MAPPING__INSTANCE_ROLE_STYLE, StyleFactory.eINSTANCE.createWorkspaceImageDescription()));

        newChildDescriptors.add(createChildParameter(TemplatePackage.Literals.TLIFELINE_MAPPING__LIFELINE_STYLE, TemplateFactory.eINSTANCE.createTLifelineStyle()));

        newChildDescriptors.add(createChildParameter(TemplatePackage.Literals.TLIFELINE_MAPPING__END_OF_LIFE_STYLE, StyleFactory.eINSTANCE.createCustomStyleDescription()));

        newChildDescriptors.add(createChildParameter(TemplatePackage.Literals.TLIFELINE_MAPPING__END_OF_LIFE_STYLE, StyleFactory.eINSTANCE.createSquareDescription()));

        newChildDescriptors.add(createChildParameter(TemplatePackage.Literals.TLIFELINE_MAPPING__END_OF_LIFE_STYLE, StyleFactory.eINSTANCE.createLozengeNodeDescription()));

        newChildDescriptors.add(createChildParameter(TemplatePackage.Literals.TLIFELINE_MAPPING__END_OF_LIFE_STYLE, StyleFactory.eINSTANCE.createEllipseNodeDescription()));

        newChildDescriptors.add(createChildParameter(TemplatePackage.Literals.TLIFELINE_MAPPING__END_OF_LIFE_STYLE, StyleFactory.eINSTANCE.createBundledImageDescription()));

        newChildDescriptors.add(createChildParameter(TemplatePackage.Literals.TLIFELINE_MAPPING__END_OF_LIFE_STYLE, StyleFactory.eINSTANCE.createNoteDescription()));

        newChildDescriptors.add(createChildParameter(TemplatePackage.Literals.TLIFELINE_MAPPING__END_OF_LIFE_STYLE, StyleFactory.eINSTANCE.createDotDescription()));

        newChildDescriptors.add(createChildParameter(TemplatePackage.Literals.TLIFELINE_MAPPING__END_OF_LIFE_STYLE, StyleFactory.eINSTANCE.createGaugeCompositeStyleDescription()));

        newChildDescriptors.add(createChildParameter(TemplatePackage.Literals.TLIFELINE_MAPPING__END_OF_LIFE_STYLE, StyleFactory.eINSTANCE.createWorkspaceImageDescription()));

        newChildDescriptors.add(createChildParameter(TemplatePackage.Literals.TLIFELINE_MAPPING__CONDITIONAL_LIFE_LINE_STYLES, TemplateFactory.eINSTANCE.createTConditionalLifelineStyle()));
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

        boolean qualify = childFeature == TemplatePackage.Literals.TLIFELINE_MAPPING__INSTANCE_ROLE_STYLE || childFeature == TemplatePackage.Literals.TLIFELINE_MAPPING__END_OF_LIFE_STYLE;

        if (qualify) {
            return getString("_UI_CreateChild_text2", //$NON-NLS-1$
                    new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
        }
        return super.getCreateChildText(owner, feature, child, selection);
    }

}
