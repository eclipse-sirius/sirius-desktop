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
package org.eclipse.sirius.diagram.sequence.ordering.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.sirius.diagram.sequence.business.internal.metamodel.ordering.CompoundEventEndSpec;
import org.eclipse.sirius.diagram.sequence.business.internal.metamodel.ordering.SingleEventEndSpec;
import org.eclipse.sirius.diagram.sequence.ordering.CompoundEventEnd;
import org.eclipse.sirius.diagram.sequence.ordering.EventEndsOrdering;
import org.eclipse.sirius.diagram.sequence.ordering.InstanceRolesOrdering;
import org.eclipse.sirius.diagram.sequence.ordering.OrderingFactory;
import org.eclipse.sirius.diagram.sequence.ordering.OrderingPackage;
import org.eclipse.sirius.diagram.sequence.ordering.SingleEventEnd;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class OrderingFactoryImpl extends EFactoryImpl implements OrderingFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public static OrderingFactory init() {
        try {
            OrderingFactory theOrderingFactory = (OrderingFactory) EPackage.Registry.INSTANCE.getEFactory(OrderingPackage.eNS_URI);
            if (theOrderingFactory != null) {
                return theOrderingFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new OrderingFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public OrderingFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
        case OrderingPackage.EVENT_ENDS_ORDERING:
            return createEventEndsOrdering();
        case OrderingPackage.SINGLE_EVENT_END:
            return createSingleEventEnd();
        case OrderingPackage.COMPOUND_EVENT_END:
            return createCompoundEventEnd();
        case OrderingPackage.INSTANCE_ROLES_ORDERING:
            return createInstanceRolesOrdering();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EventEndsOrdering createEventEndsOrdering() {
        EventEndsOrderingImpl eventEndsOrdering = new EventEndsOrderingImpl();
        return eventEndsOrdering;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public SingleEventEnd createSingleEventEnd() {
        SingleEventEndImpl singleEventEnd = new SingleEventEndSpec();
        return singleEventEnd;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public CompoundEventEnd createCompoundEventEnd() {
        CompoundEventEndImpl compoundEventEnd = new CompoundEventEndSpec();
        return compoundEventEnd;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public InstanceRolesOrdering createInstanceRolesOrdering() {
        InstanceRolesOrderingImpl instanceRolesOrdering = new InstanceRolesOrderingImpl();
        return instanceRolesOrdering;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public OrderingPackage getOrderingPackage() {
        return (OrderingPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @deprecated
     * @generated
     */
    @Deprecated
    public static OrderingPackage getPackage() {
        return OrderingPackage.eINSTANCE;
    }

} // OrderingFactoryImpl
