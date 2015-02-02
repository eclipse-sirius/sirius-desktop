/**
 * Copyright (c) 2015 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Obeo - initial API and implementation
 */
package org.eclipse.sirius.tests.sample.component.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.sirius.tests.sample.component.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ComponentFactoryImpl extends EFactoryImpl implements ComponentFactory {
    /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
   * @generated
   */
    public static ComponentFactory init() {
    try {
      ComponentFactory theComponentFactory = (ComponentFactory)EPackage.Registry.INSTANCE.getEFactory(ComponentPackage.eNS_URI);
      if (theComponentFactory != null) {
        return theComponentFactory;
      }
    }
    catch (Exception exception) {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new ComponentFactoryImpl();
  }

    /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
   * @generated
   */
    public ComponentFactoryImpl() {
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
      case ComponentPackage.COMPONENT: return createComponent();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

    /**
   * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
   * @generated
   */
    public Component createComponent() {
    ComponentImpl component = new ComponentImpl();
    return component;
  }

    /**
   * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
   * @generated
   */
    public ComponentPackage getComponentPackage() {
    return (ComponentPackage)getEPackage();
  }

    /**
   * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
    @Deprecated
    public static ComponentPackage getPackage() {
    return ComponentPackage.eINSTANCE;
  }

} //ComponentFactoryImpl
