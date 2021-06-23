/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.ordering.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.sequence.ordering.CompoundEventEnd;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.ordering.EventEndsOrdering;
import org.eclipse.sirius.diagram.sequence.ordering.InstanceRolesOrdering;
import org.eclipse.sirius.diagram.sequence.ordering.OrderingPackage;
import org.eclipse.sirius.diagram.sequence.ordering.SingleEventEnd;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the call
 * {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the inheritance hierarchy until a non-null result is
 * returned, which is the result of the switch. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.diagram.sequence.ordering.OrderingPackage
 * @generated
 */
public class OrderingSwitch<T> {
    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static OrderingPackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public OrderingSwitch() {
        if (OrderingSwitch.modelPackage == null) {
            OrderingSwitch.modelPackage = OrderingPackage.eINSTANCE;
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that
     * result. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    public T doSwitch(EObject theEObject) {
        return doSwitch(theEObject.eClass(), theEObject);
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that
     * result. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected T doSwitch(EClass theEClass, EObject theEObject) {
        if (theEClass.eContainer() == OrderingSwitch.modelPackage) {
            return doSwitch(theEClass.getClassifierID(), theEObject);
        } else {
            List<EClass> eSuperTypes = theEClass.getESuperTypes();
            return eSuperTypes.isEmpty() ? defaultCase(theEObject) : doSwitch(eSuperTypes.get(0), theEObject);
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that
     * result. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
        case OrderingPackage.EVENT_ENDS_ORDERING: {
            EventEndsOrdering eventEndsOrdering = (EventEndsOrdering) theEObject;
            T result = caseEventEndsOrdering(eventEndsOrdering);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case OrderingPackage.EVENT_END: {
            EventEnd eventEnd = (EventEnd) theEObject;
            T result = caseEventEnd(eventEnd);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case OrderingPackage.SINGLE_EVENT_END: {
            SingleEventEnd singleEventEnd = (SingleEventEnd) theEObject;
            T result = caseSingleEventEnd(singleEventEnd);
            if (result == null) {
                result = caseEventEnd(singleEventEnd);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case OrderingPackage.COMPOUND_EVENT_END: {
            CompoundEventEnd compoundEventEnd = (CompoundEventEnd) theEObject;
            T result = caseCompoundEventEnd(compoundEventEnd);
            if (result == null) {
                result = caseEventEnd(compoundEventEnd);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case OrderingPackage.INSTANCE_ROLES_ORDERING: {
            InstanceRolesOrdering instanceRolesOrdering = (InstanceRolesOrdering) theEObject;
            T result = caseInstanceRolesOrdering(instanceRolesOrdering);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        default:
            return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Event Ends Ordering</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Event Ends Ordering</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEventEndsOrdering(EventEndsOrdering object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Event End</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Event End</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEventEnd(EventEnd object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Single Event End</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Single Event End</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSingleEventEnd(SingleEventEnd object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Compound Event End</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Compound Event End</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCompoundEventEnd(CompoundEventEnd object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Instance Roles Ordering</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Instance Roles Ordering</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseInstanceRolesOrdering(InstanceRolesOrdering object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch, but this is the last case
     * anyway. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    public T defaultCase(EObject object) {
        return null;
    }

} // OrderingSwitch
