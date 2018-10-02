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
package org.eclipse.sirius.diagram.sequence.description.tool.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.diagram.description.tool.impl.NodeCreationDescriptionImpl;
import org.eclipse.sirius.diagram.sequence.description.tool.InstanceRoleCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.tool.ElementVariable;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Instance Role Creation Tool</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.sequence.description.tool.impl.InstanceRoleCreationToolImpl#getPredecessor
 * <em>Predecessor</em>}</li>
 * </ul>
 *
 * @generated
 */
public class InstanceRoleCreationToolImpl extends NodeCreationDescriptionImpl implements InstanceRoleCreationTool {
    /**
     * The cached value of the '{@link #getPredecessor() <em>Predecessor</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getPredecessor()
     * @generated
     * @ordered
     */
    protected ElementVariable predecessor;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected InstanceRoleCreationToolImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.INSTANCE_ROLE_CREATION_TOOL;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ElementVariable getPredecessor() {
        return predecessor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetPredecessor(ElementVariable newPredecessor, NotificationChain msgs) {
        ElementVariable oldPredecessor = predecessor;
        predecessor = newPredecessor;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.INSTANCE_ROLE_CREATION_TOOL__PREDECESSOR, oldPredecessor, newPredecessor);
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
    public void setPredecessor(ElementVariable newPredecessor) {
        if (newPredecessor != predecessor) {
            NotificationChain msgs = null;
            if (predecessor != null) {
                msgs = ((InternalEObject) predecessor).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.INSTANCE_ROLE_CREATION_TOOL__PREDECESSOR, null, msgs);
            }
            if (newPredecessor != null) {
                msgs = ((InternalEObject) newPredecessor).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.INSTANCE_ROLE_CREATION_TOOL__PREDECESSOR, null, msgs);
            }
            msgs = basicSetPredecessor(newPredecessor, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.INSTANCE_ROLE_CREATION_TOOL__PREDECESSOR, newPredecessor, newPredecessor));
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
        case ToolPackage.INSTANCE_ROLE_CREATION_TOOL__PREDECESSOR:
            return basicSetPredecessor(null, msgs);
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
        case ToolPackage.INSTANCE_ROLE_CREATION_TOOL__PREDECESSOR:
            return getPredecessor();
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
        case ToolPackage.INSTANCE_ROLE_CREATION_TOOL__PREDECESSOR:
            setPredecessor((ElementVariable) newValue);
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
        case ToolPackage.INSTANCE_ROLE_CREATION_TOOL__PREDECESSOR:
            setPredecessor((ElementVariable) null);
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
        case ToolPackage.INSTANCE_ROLE_CREATION_TOOL__PREDECESSOR:
            return predecessor != null;
        }
        return super.eIsSet(featureID);
    }

} // InstanceRoleCreationToolImpl
