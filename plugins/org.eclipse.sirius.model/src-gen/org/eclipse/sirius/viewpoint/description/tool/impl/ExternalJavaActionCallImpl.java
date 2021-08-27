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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.viewpoint.description.tool.ContainerModelOperation;
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaAction;
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaActionCall;
import org.eclipse.sirius.viewpoint.description.tool.GroupMenuItem;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>External Java Action Call</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.impl.ExternalJavaActionCallImpl#getSubModelOperations
 * <em>Sub Model Operations</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.impl.ExternalJavaActionCallImpl#getAction
 * <em>Action</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ExternalJavaActionCallImpl extends MenuItemDescriptionWithIconImpl implements ExternalJavaActionCall {
    /**
     * The cached value of the '{@link #getSubModelOperations() <em>Sub Model Operations</em>}' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSubModelOperations()
     * @generated
     * @ordered
     */
    protected EList<ModelOperation> subModelOperations;

    /**
     * The cached value of the '{@link #getAction() <em>Action</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getAction()
     * @generated
     * @ordered
     */
    protected ExternalJavaAction action;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ExternalJavaActionCallImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.EXTERNAL_JAVA_ACTION_CALL;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ModelOperation> getSubModelOperations() {
        if (subModelOperations == null) {
            subModelOperations = new EObjectContainmentEList.Resolving<>(ModelOperation.class, this, ToolPackage.EXTERNAL_JAVA_ACTION_CALL__SUB_MODEL_OPERATIONS);
        }
        return subModelOperations;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ExternalJavaAction getAction() {
        if (action != null && action.eIsProxy()) {
            InternalEObject oldAction = (InternalEObject) action;
            action = (ExternalJavaAction) eResolveProxy(oldAction);
            if (action != oldAction) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.EXTERNAL_JAVA_ACTION_CALL__ACTION, oldAction, action));
                }
            }
        }
        return action;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ExternalJavaAction basicGetAction() {
        return action;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setAction(ExternalJavaAction newAction) {
        ExternalJavaAction oldAction = action;
        action = newAction;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.EXTERNAL_JAVA_ACTION_CALL__ACTION, oldAction, action));
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
        case ToolPackage.EXTERNAL_JAVA_ACTION_CALL__SUB_MODEL_OPERATIONS:
            return ((InternalEList<?>) getSubModelOperations()).basicRemove(otherEnd, msgs);
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
        case ToolPackage.EXTERNAL_JAVA_ACTION_CALL__SUB_MODEL_OPERATIONS:
            return getSubModelOperations();
        case ToolPackage.EXTERNAL_JAVA_ACTION_CALL__ACTION:
            if (resolve) {
                return getAction();
            }
            return basicGetAction();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case ToolPackage.EXTERNAL_JAVA_ACTION_CALL__SUB_MODEL_OPERATIONS:
            getSubModelOperations().clear();
            getSubModelOperations().addAll((Collection<? extends ModelOperation>) newValue);
            return;
        case ToolPackage.EXTERNAL_JAVA_ACTION_CALL__ACTION:
            setAction((ExternalJavaAction) newValue);
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
        case ToolPackage.EXTERNAL_JAVA_ACTION_CALL__SUB_MODEL_OPERATIONS:
            getSubModelOperations().clear();
            return;
        case ToolPackage.EXTERNAL_JAVA_ACTION_CALL__ACTION:
            setAction((ExternalJavaAction) null);
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
        case ToolPackage.EXTERNAL_JAVA_ACTION_CALL__SUB_MODEL_OPERATIONS:
            return subModelOperations != null && !subModelOperations.isEmpty();
        case ToolPackage.EXTERNAL_JAVA_ACTION_CALL__ACTION:
            return action != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == ModelOperation.class) {
            switch (derivedFeatureID) {
            default:
                return -1;
            }
        }
        if (baseClass == ContainerModelOperation.class) {
            switch (derivedFeatureID) {
            case ToolPackage.EXTERNAL_JAVA_ACTION_CALL__SUB_MODEL_OPERATIONS:
                return ToolPackage.CONTAINER_MODEL_OPERATION__SUB_MODEL_OPERATIONS;
            default:
                return -1;
            }
        }
        if (baseClass == GroupMenuItem.class) {
            switch (derivedFeatureID) {
            default:
                return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if (baseClass == ModelOperation.class) {
            switch (baseFeatureID) {
            default:
                return -1;
            }
        }
        if (baseClass == ContainerModelOperation.class) {
            switch (baseFeatureID) {
            case ToolPackage.CONTAINER_MODEL_OPERATION__SUB_MODEL_OPERATIONS:
                return ToolPackage.EXTERNAL_JAVA_ACTION_CALL__SUB_MODEL_OPERATIONS;
            default:
                return -1;
            }
        }
        if (baseClass == GroupMenuItem.class) {
            switch (baseFeatureID) {
            default:
                return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
    }

} // ExternalJavaActionCallImpl
