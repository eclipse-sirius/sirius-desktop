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
package org.eclipse.sirius.properties.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.sirius.properties.*;

import org.eclipse.sirius.viewpoint.description.Extension;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.sirius.properties.PropertiesPackage
 * @generated
 */
public class PropertiesAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static PropertiesPackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PropertiesAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = PropertiesPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
     * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
     * <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected PropertiesSwitch<Adapter> modelSwitch =
        new PropertiesSwitch<Adapter>() {
            @Override
            public Adapter caseViewExtensionDescription(ViewExtensionDescription object) {
                return createViewExtensionDescriptionAdapter();
            }
            @Override
            public Adapter casePageDescription(PageDescription object) {
                return createPageDescriptionAdapter();
            }
            @Override
            public Adapter caseGroupDescription(GroupDescription object) {
                return createGroupDescriptionAdapter();
            }
            @Override
            public Adapter caseContainerDescription(ContainerDescription object) {
                return createContainerDescriptionAdapter();
            }
            @Override
            public Adapter caseWidgetDescription(WidgetDescription object) {
                return createWidgetDescriptionAdapter();
            }
            @Override
            public Adapter caseTextDescription(TextDescription object) {
                return createTextDescriptionAdapter();
            }
            @Override
            public Adapter caseLabelDescription(LabelDescription object) {
                return createLabelDescriptionAdapter();
            }
            @Override
            public Adapter caseExtension(Extension object) {
                return createExtensionAdapter();
            }
            @Override
            public Adapter defaultCase(EObject object) {
                return createEObjectAdapter();
            }
        };

    /**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject)target);
    }


    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.ViewExtensionDescription <em>View Extension Description</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.ViewExtensionDescription
     * @generated
     */
    public Adapter createViewExtensionDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.PageDescription <em>Page Description</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.PageDescription
     * @generated
     */
    public Adapter createPageDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.GroupDescription <em>Group Description</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.GroupDescription
     * @generated
     */
    public Adapter createGroupDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.ContainerDescription <em>Container Description</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.ContainerDescription
     * @generated
     */
    public Adapter createContainerDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.WidgetDescription <em>Widget Description</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.WidgetDescription
     * @generated
     */
    public Adapter createWidgetDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.TextDescription <em>Text Description</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.TextDescription
     * @generated
     */
    public Adapter createTextDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.LabelDescription <em>Label Description</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.LabelDescription
     * @generated
     */
    public Adapter createLabelDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.Extension <em>Extension</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.Extension
     * @generated
     */
    public Adapter createExtensionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
     * This default implementation returns null.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} //PropertiesAdapterFactory
