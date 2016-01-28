/**
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Obeo - initial API and implementation
 * 
 */
package org.eclipse.sirius.properties.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.sirius.properties.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PropertiesFactoryImpl extends EFactoryImpl implements PropertiesFactory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static PropertiesFactory init() {
        try {
            PropertiesFactory thePropertiesFactory = (PropertiesFactory)EPackage.Registry.INSTANCE.getEFactory(PropertiesPackage.eNS_URI);
            if (thePropertiesFactory != null) {
                return thePropertiesFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new PropertiesFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PropertiesFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION: return createViewExtensionDescription();
            case PropertiesPackage.PAGE_DESCRIPTION: return createPageDescription();
            case PropertiesPackage.GROUP_DESCRIPTION: return createGroupDescription();
            case PropertiesPackage.CONTAINER_DESCRIPTION: return createContainerDescription();
            case PropertiesPackage.TEXT_DESCRIPTION: return createTextDescription();
            case PropertiesPackage.LABEL_DESCRIPTION: return createLabelDescription();
            case PropertiesPackage.DYNAMIC_MAPPING_FOR: return createDynamicMappingFor();
            case PropertiesPackage.DYNAMIC_MAPPING_SWITCH: return createDynamicMappingSwitch();
            case PropertiesPackage.DYNAMIC_MAPPING_CASE: return createDynamicMappingCase();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ViewExtensionDescription createViewExtensionDescription() {
        ViewExtensionDescriptionImpl viewExtensionDescription = new ViewExtensionDescriptionImpl();
        return viewExtensionDescription;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PageDescription createPageDescription() {
        PageDescriptionImpl pageDescription = new PageDescriptionImpl();
        return pageDescription;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GroupDescription createGroupDescription() {
        GroupDescriptionImpl groupDescription = new GroupDescriptionImpl();
        return groupDescription;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ContainerDescription createContainerDescription() {
        ContainerDescriptionImpl containerDescription = new ContainerDescriptionImpl();
        return containerDescription;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TextDescription createTextDescription() {
        TextDescriptionImpl textDescription = new TextDescriptionImpl();
        return textDescription;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LabelDescription createLabelDescription() {
        LabelDescriptionImpl labelDescription = new LabelDescriptionImpl();
        return labelDescription;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DynamicMappingFor createDynamicMappingFor() {
        DynamicMappingForImpl dynamicMappingFor = new DynamicMappingForImpl();
        return dynamicMappingFor;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DynamicMappingSwitch createDynamicMappingSwitch() {
        DynamicMappingSwitchImpl dynamicMappingSwitch = new DynamicMappingSwitchImpl();
        return dynamicMappingSwitch;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DynamicMappingCase createDynamicMappingCase() {
        DynamicMappingCaseImpl dynamicMappingCase = new DynamicMappingCaseImpl();
        return dynamicMappingCase;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PropertiesPackage getPropertiesPackage() {
        return (PropertiesPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static PropertiesPackage getPackage() {
        return PropertiesPackage.eINSTANCE;
    }

} //PropertiesFactoryImpl
