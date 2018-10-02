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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.sirius.diagram.sequence.description.InstanceRoleMapping;
import org.eclipse.sirius.diagram.sequence.description.tool.InstanceRoleReorderTool;
import org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.tool.ElementVariable;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;
import org.eclipse.sirius.viewpoint.description.tool.impl.AbstractToolDescriptionImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Instance Role Reorder Tool</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.sequence.description.tool.impl.InstanceRoleReorderToolImpl#getMappings
 * <em>Mappings</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.description.tool.impl.InstanceRoleReorderToolImpl#getPredecessorBefore
 * <em>Predecessor Before</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.description.tool.impl.InstanceRoleReorderToolImpl#getPredecessorAfter
 * <em>Predecessor After</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.description.tool.impl.InstanceRoleReorderToolImpl#getInstanceRoleMoved
 * <em>Instance Role Moved</em>}</li>
 * </ul>
 *
 * @generated
 */
public class InstanceRoleReorderToolImpl extends AbstractToolDescriptionImpl implements InstanceRoleReorderTool {
    /**
     * The cached value of the '{@link #getMappings() <em>Mappings</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getMappings()
     * @generated
     * @ordered
     */
    protected EList<InstanceRoleMapping> mappings;

    /**
     * The cached value of the '{@link #getPredecessorBefore() <em>Predecessor Before</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getPredecessorBefore()
     * @generated
     * @ordered
     */
    protected ElementVariable predecessorBefore;

    /**
     * The cached value of the '{@link #getPredecessorAfter() <em>Predecessor After</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getPredecessorAfter()
     * @generated
     * @ordered
     */
    protected ElementVariable predecessorAfter;

    /**
     * The cached value of the '{@link #getInstanceRoleMoved() <em>Instance Role Moved</em>}' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getInstanceRoleMoved()
     * @generated
     * @ordered
     */
    protected InitialOperation instanceRoleMoved;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected InstanceRoleReorderToolImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.INSTANCE_ROLE_REORDER_TOOL;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<InstanceRoleMapping> getMappings() {
        if (mappings == null) {
            mappings = new EObjectResolvingEList<InstanceRoleMapping>(InstanceRoleMapping.class, this, ToolPackage.INSTANCE_ROLE_REORDER_TOOL__MAPPINGS);
        }
        return mappings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ElementVariable getPredecessorBefore() {
        return predecessorBefore;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetPredecessorBefore(ElementVariable newPredecessorBefore, NotificationChain msgs) {
        ElementVariable oldPredecessorBefore = predecessorBefore;
        predecessorBefore = newPredecessorBefore;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.INSTANCE_ROLE_REORDER_TOOL__PREDECESSOR_BEFORE, oldPredecessorBefore, newPredecessorBefore);
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
    public void setPredecessorBefore(ElementVariable newPredecessorBefore) {
        if (newPredecessorBefore != predecessorBefore) {
            NotificationChain msgs = null;
            if (predecessorBefore != null) {
                msgs = ((InternalEObject) predecessorBefore).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.INSTANCE_ROLE_REORDER_TOOL__PREDECESSOR_BEFORE, null, msgs);
            }
            if (newPredecessorBefore != null) {
                msgs = ((InternalEObject) newPredecessorBefore).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.INSTANCE_ROLE_REORDER_TOOL__PREDECESSOR_BEFORE, null, msgs);
            }
            msgs = basicSetPredecessorBefore(newPredecessorBefore, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.INSTANCE_ROLE_REORDER_TOOL__PREDECESSOR_BEFORE, newPredecessorBefore, newPredecessorBefore));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ElementVariable getPredecessorAfter() {
        return predecessorAfter;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetPredecessorAfter(ElementVariable newPredecessorAfter, NotificationChain msgs) {
        ElementVariable oldPredecessorAfter = predecessorAfter;
        predecessorAfter = newPredecessorAfter;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.INSTANCE_ROLE_REORDER_TOOL__PREDECESSOR_AFTER, oldPredecessorAfter, newPredecessorAfter);
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
    public void setPredecessorAfter(ElementVariable newPredecessorAfter) {
        if (newPredecessorAfter != predecessorAfter) {
            NotificationChain msgs = null;
            if (predecessorAfter != null) {
                msgs = ((InternalEObject) predecessorAfter).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.INSTANCE_ROLE_REORDER_TOOL__PREDECESSOR_AFTER, null, msgs);
            }
            if (newPredecessorAfter != null) {
                msgs = ((InternalEObject) newPredecessorAfter).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.INSTANCE_ROLE_REORDER_TOOL__PREDECESSOR_AFTER, null, msgs);
            }
            msgs = basicSetPredecessorAfter(newPredecessorAfter, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.INSTANCE_ROLE_REORDER_TOOL__PREDECESSOR_AFTER, newPredecessorAfter, newPredecessorAfter));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public InitialOperation getInstanceRoleMoved() {
        return instanceRoleMoved;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetInstanceRoleMoved(InitialOperation newInstanceRoleMoved, NotificationChain msgs) {
        InitialOperation oldInstanceRoleMoved = instanceRoleMoved;
        instanceRoleMoved = newInstanceRoleMoved;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.INSTANCE_ROLE_REORDER_TOOL__INSTANCE_ROLE_MOVED, oldInstanceRoleMoved, newInstanceRoleMoved);
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
    public void setInstanceRoleMoved(InitialOperation newInstanceRoleMoved) {
        if (newInstanceRoleMoved != instanceRoleMoved) {
            NotificationChain msgs = null;
            if (instanceRoleMoved != null) {
                msgs = ((InternalEObject) instanceRoleMoved).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.INSTANCE_ROLE_REORDER_TOOL__INSTANCE_ROLE_MOVED, null, msgs);
            }
            if (newInstanceRoleMoved != null) {
                msgs = ((InternalEObject) newInstanceRoleMoved).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.INSTANCE_ROLE_REORDER_TOOL__INSTANCE_ROLE_MOVED, null, msgs);
            }
            msgs = basicSetInstanceRoleMoved(newInstanceRoleMoved, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.INSTANCE_ROLE_REORDER_TOOL__INSTANCE_ROLE_MOVED, newInstanceRoleMoved, newInstanceRoleMoved));
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
        case ToolPackage.INSTANCE_ROLE_REORDER_TOOL__PREDECESSOR_BEFORE:
            return basicSetPredecessorBefore(null, msgs);
        case ToolPackage.INSTANCE_ROLE_REORDER_TOOL__PREDECESSOR_AFTER:
            return basicSetPredecessorAfter(null, msgs);
        case ToolPackage.INSTANCE_ROLE_REORDER_TOOL__INSTANCE_ROLE_MOVED:
            return basicSetInstanceRoleMoved(null, msgs);
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
        case ToolPackage.INSTANCE_ROLE_REORDER_TOOL__MAPPINGS:
            return getMappings();
        case ToolPackage.INSTANCE_ROLE_REORDER_TOOL__PREDECESSOR_BEFORE:
            return getPredecessorBefore();
        case ToolPackage.INSTANCE_ROLE_REORDER_TOOL__PREDECESSOR_AFTER:
            return getPredecessorAfter();
        case ToolPackage.INSTANCE_ROLE_REORDER_TOOL__INSTANCE_ROLE_MOVED:
            return getInstanceRoleMoved();
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
        case ToolPackage.INSTANCE_ROLE_REORDER_TOOL__MAPPINGS:
            getMappings().clear();
            getMappings().addAll((Collection<? extends InstanceRoleMapping>) newValue);
            return;
        case ToolPackage.INSTANCE_ROLE_REORDER_TOOL__PREDECESSOR_BEFORE:
            setPredecessorBefore((ElementVariable) newValue);
            return;
        case ToolPackage.INSTANCE_ROLE_REORDER_TOOL__PREDECESSOR_AFTER:
            setPredecessorAfter((ElementVariable) newValue);
            return;
        case ToolPackage.INSTANCE_ROLE_REORDER_TOOL__INSTANCE_ROLE_MOVED:
            setInstanceRoleMoved((InitialOperation) newValue);
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
        case ToolPackage.INSTANCE_ROLE_REORDER_TOOL__MAPPINGS:
            getMappings().clear();
            return;
        case ToolPackage.INSTANCE_ROLE_REORDER_TOOL__PREDECESSOR_BEFORE:
            setPredecessorBefore((ElementVariable) null);
            return;
        case ToolPackage.INSTANCE_ROLE_REORDER_TOOL__PREDECESSOR_AFTER:
            setPredecessorAfter((ElementVariable) null);
            return;
        case ToolPackage.INSTANCE_ROLE_REORDER_TOOL__INSTANCE_ROLE_MOVED:
            setInstanceRoleMoved((InitialOperation) null);
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
        case ToolPackage.INSTANCE_ROLE_REORDER_TOOL__MAPPINGS:
            return mappings != null && !mappings.isEmpty();
        case ToolPackage.INSTANCE_ROLE_REORDER_TOOL__PREDECESSOR_BEFORE:
            return predecessorBefore != null;
        case ToolPackage.INSTANCE_ROLE_REORDER_TOOL__PREDECESSOR_AFTER:
            return predecessorAfter != null;
        case ToolPackage.INSTANCE_ROLE_REORDER_TOOL__INSTANCE_ROLE_MOVED:
            return instanceRoleMoved != null;
        }
        return super.eIsSet(featureID);
    }

} // InstanceRoleReorderToolImpl
