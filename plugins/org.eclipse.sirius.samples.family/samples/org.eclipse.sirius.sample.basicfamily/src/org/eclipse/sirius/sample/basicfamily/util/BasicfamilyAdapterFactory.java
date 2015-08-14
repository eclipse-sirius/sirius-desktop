/**
 * Copyright (c) 2014 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Obeo - initial API and implementation
 */
package org.eclipse.sirius.sample.basicfamily.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.sample.basicfamily.BasicfamilyPackage;
import org.eclipse.sirius.sample.basicfamily.Family;
import org.eclipse.sirius.sample.basicfamily.Man;
import org.eclipse.sirius.sample.basicfamily.Person;
import org.eclipse.sirius.sample.basicfamily.Woman;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides
 * an adapter <code>createXXX</code> method for each class of the model. <!--
 * end-user-doc -->
 * 
 * @see org.eclipse.sirius.sample.basicfamily.BasicfamilyPackage
 * @generated
 */
public class BasicfamilyAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected static BasicfamilyPackage modelPackage;

    /**
     * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public BasicfamilyAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = BasicfamilyPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc --> This implementation returns <code>true</code> if
     * the object is either the model's package or is an instance object of the
     * model. <!-- end-user-doc -->
     * 
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject) object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected BasicfamilySwitch<Adapter> modelSwitch = new BasicfamilySwitch<Adapter>() {
        @Override
        public Adapter casePerson(Person object) {
            return createPersonAdapter();
        }

        @Override
        public Adapter caseFamily(Family object) {
            return createFamilyAdapter();
        }

        @Override
        public Adapter caseMan(Man object) {
            return createManAdapter();
        }

        @Override
        public Adapter caseWoman(Woman object) {
            return createWomanAdapter();
        }

        @Override
        public Adapter defaultCase(EObject object) {
            return createEObjectAdapter();
        }
    };

    /**
     * Creates an adapter for the <code>target</code>. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param target
     *            the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject) target);
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.sample.basicfamily.Person <em>Person</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.sample.basicfamily.Person
     * @generated
     */
    public Adapter createPersonAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.sample.basicfamily.Family <em>Family</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.sample.basicfamily.Family
     * @generated
     */
    public Adapter createFamilyAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.sample.basicfamily.Man <em>Man</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.sample.basicfamily.Man
     * @generated
     */
    public Adapter createManAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.sample.basicfamily.Woman <em>Woman</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.sample.basicfamily.Woman
     * @generated
     */
    public Adapter createWomanAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case. <!-- begin-user-doc --> This
     * default implementation returns null. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} // BasicfamilyAdapterFactory
