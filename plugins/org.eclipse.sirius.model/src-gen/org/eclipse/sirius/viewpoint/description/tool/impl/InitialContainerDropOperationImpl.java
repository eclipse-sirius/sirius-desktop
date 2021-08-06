/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.viewpoint.description.tool.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.sirius.viewpoint.description.tool.InitialContainerDropOperation;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Initial Container Drop Operation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.impl.InitialContainerDropOperationImpl#getFirstModelOperations
 * <em>First Model Operations</em>}</li>
 * </ul>
 *
 * @generated
 */
public class InitialContainerDropOperationImpl extends MinimalEObjectImpl.Container implements InitialContainerDropOperation {
    /**
     * The cached value of the '{@link #getFirstModelOperations() <em>First Model Operations</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFirstModelOperations()
     * @generated
     * @ordered
     */
    protected ModelOperation firstModelOperations;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected InitialContainerDropOperationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.INITIAL_CONTAINER_DROP_OPERATION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ModelOperation getFirstModelOperations() {
        if (firstModelOperations != null && firstModelOperations.eIsProxy()) {
            InternalEObject oldFirstModelOperations = (InternalEObject) firstModelOperations;
            firstModelOperations = (ModelOperation) eResolveProxy(oldFirstModelOperations);
            if (firstModelOperations != oldFirstModelOperations) {
                InternalEObject newFirstModelOperations = (InternalEObject) firstModelOperations;
                NotificationChain msgs = oldFirstModelOperations.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.INITIAL_CONTAINER_DROP_OPERATION__FIRST_MODEL_OPERATIONS,
                        null, null);
                if (newFirstModelOperations.eInternalContainer() == null) {
                    msgs = newFirstModelOperations.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.INITIAL_CONTAINER_DROP_OPERATION__FIRST_MODEL_OPERATIONS, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.INITIAL_CONTAINER_DROP_OPERATION__FIRST_MODEL_OPERATIONS, oldFirstModelOperations, firstModelOperations));
                }
            }
        }
        return firstModelOperations;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ModelOperation basicGetFirstModelOperations() {
        return firstModelOperations;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetFirstModelOperations(ModelOperation newFirstModelOperations, NotificationChain msgs) {
        ModelOperation oldFirstModelOperations = firstModelOperations;
        firstModelOperations = newFirstModelOperations;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.INITIAL_CONTAINER_DROP_OPERATION__FIRST_MODEL_OPERATIONS, oldFirstModelOperations,
                    newFirstModelOperations);
            if (msgs == null) {
                msgs = notification;
            } else {
                msgs.add(notification);
            }
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFirstModelOperations(ModelOperation newFirstModelOperations) {
        if (newFirstModelOperations != firstModelOperations) {
            NotificationChain msgs = null;
            if (firstModelOperations != null) {
                msgs = ((InternalEObject) firstModelOperations).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.INITIAL_CONTAINER_DROP_OPERATION__FIRST_MODEL_OPERATIONS,
                        null, msgs);
            }
            if (newFirstModelOperations != null) {
                msgs = ((InternalEObject) newFirstModelOperations).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.INITIAL_CONTAINER_DROP_OPERATION__FIRST_MODEL_OPERATIONS,
                        null, msgs);
            }
            msgs = basicSetFirstModelOperations(newFirstModelOperations, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.INITIAL_CONTAINER_DROP_OPERATION__FIRST_MODEL_OPERATIONS, newFirstModelOperations, newFirstModelOperations));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ToolPackage.INITIAL_CONTAINER_DROP_OPERATION__FIRST_MODEL_OPERATIONS:
            return basicSetFirstModelOperations(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case ToolPackage.INITIAL_CONTAINER_DROP_OPERATION__FIRST_MODEL_OPERATIONS:
            if (resolve) {
                return getFirstModelOperations();
            }
            return basicGetFirstModelOperations();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case ToolPackage.INITIAL_CONTAINER_DROP_OPERATION__FIRST_MODEL_OPERATIONS:
            setFirstModelOperations((ModelOperation) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
        case ToolPackage.INITIAL_CONTAINER_DROP_OPERATION__FIRST_MODEL_OPERATIONS:
            setFirstModelOperations((ModelOperation) null);
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
        case ToolPackage.INITIAL_CONTAINER_DROP_OPERATION__FIRST_MODEL_OPERATIONS:
            return firstModelOperations != null;
        }
        return super.eIsSet(featureID);
    }

} // InitialContainerDropOperationImpl
