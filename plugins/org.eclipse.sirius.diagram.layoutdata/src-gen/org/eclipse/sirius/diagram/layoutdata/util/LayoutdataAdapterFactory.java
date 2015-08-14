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
package org.eclipse.sirius.diagram.layoutdata.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.layoutdata.AbstractLayoutData;
import org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData;
import org.eclipse.sirius.diagram.layoutdata.LayoutdataPackage;
import org.eclipse.sirius.diagram.layoutdata.NodeLayoutData;
import org.eclipse.sirius.diagram.layoutdata.Point;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides
 * an adapter <code>createXXX</code> method for each class of the model. <!--
 * end-user-doc -->
 * 
 * @see org.eclipse.sirius.diagram.layoutdata.LayoutdataPackage
 * @generated
 */
public class LayoutdataAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected static LayoutdataPackage modelPackage;

    /**
     * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public LayoutdataAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = LayoutdataPackage.eINSTANCE;
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
    protected LayoutdataSwitch<Adapter> modelSwitch = new LayoutdataSwitch<Adapter>() {
        @Override
        public Adapter caseAbstractLayoutData(AbstractLayoutData object) {
            return createAbstractLayoutDataAdapter();
        }

        @Override
        public Adapter caseNodeLayoutData(NodeLayoutData object) {
            return createNodeLayoutDataAdapter();
        }

        @Override
        public Adapter caseEdgeLayoutData(EdgeLayoutData object) {
            return createEdgeLayoutDataAdapter();
        }

        @Override
        public Adapter casePoint(Point object) {
            return createPointAdapter();
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
     * {@link org.eclipse.sirius.diagram.layoutdata.AbstractLayoutData
     * <em>Abstract Layout Data</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.layoutdata.AbstractLayoutData
     * @generated
     */
    public Adapter createAbstractLayoutDataAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.diagram.layoutdata.NodeLayoutData
     * <em>Node Layout Data</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.layoutdata.NodeLayoutData
     * @generated
     */
    public Adapter createNodeLayoutDataAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData
     * <em>Edge Layout Data</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData
     * @generated
     */
    public Adapter createEdgeLayoutDataAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.diagram.layoutdata.Point <em>Point</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.layoutdata.Point
     * @generated
     */
    public Adapter createPointAdapter() {
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

} // LayoutdataAdapterFactory
