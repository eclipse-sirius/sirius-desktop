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
package org.eclipse.sirius.diagram.sequence.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.diagram.impl.DSemanticDiagramImpl;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.SequencePackage;
import org.eclipse.sirius.diagram.sequence.ordering.EventEndsOrdering;
import org.eclipse.sirius.diagram.sequence.ordering.InstanceRolesOrdering;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>DDiagram</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.sequence.impl.SequenceDDiagramImpl#getSemanticOrdering <em>Semantic
 * Ordering</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.impl.SequenceDDiagramImpl#getGraphicalOrdering <em>Graphical
 * Ordering</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.impl.SequenceDDiagramImpl#getInstanceRoleSemanticOrdering <em>Instance
 * Role Semantic Ordering</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SequenceDDiagramImpl extends DSemanticDiagramImpl implements SequenceDDiagram {
    /**
     * The cached value of the '{@link #getSemanticOrdering() <em>Semantic Ordering</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSemanticOrdering()
     * @generated
     * @ordered
     */
    protected EventEndsOrdering semanticOrdering;

    /**
     * The cached value of the '{@link #getGraphicalOrdering() <em>Graphical Ordering</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getGraphicalOrdering()
     * @generated
     * @ordered
     */
    protected EventEndsOrdering graphicalOrdering;

    /**
     * The cached value of the '{@link #getInstanceRoleSemanticOrdering() <em>Instance Role Semantic Ordering</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getInstanceRoleSemanticOrdering()
     * @generated
     * @ordered
     */
    protected InstanceRolesOrdering instanceRoleSemanticOrdering;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected SequenceDDiagramImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return SequencePackage.Literals.SEQUENCE_DDIAGRAM;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EventEndsOrdering getSemanticOrdering() {
        return semanticOrdering;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetSemanticOrdering(EventEndsOrdering newSemanticOrdering, NotificationChain msgs) {
        EventEndsOrdering oldSemanticOrdering = semanticOrdering;
        semanticOrdering = newSemanticOrdering;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SequencePackage.SEQUENCE_DDIAGRAM__SEMANTIC_ORDERING, oldSemanticOrdering, newSemanticOrdering);
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
    public void setSemanticOrdering(EventEndsOrdering newSemanticOrdering) {
        if (newSemanticOrdering != semanticOrdering) {
            NotificationChain msgs = null;
            if (semanticOrdering != null) {
                msgs = ((InternalEObject) semanticOrdering).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - SequencePackage.SEQUENCE_DDIAGRAM__SEMANTIC_ORDERING, null, msgs);
            }
            if (newSemanticOrdering != null) {
                msgs = ((InternalEObject) newSemanticOrdering).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - SequencePackage.SEQUENCE_DDIAGRAM__SEMANTIC_ORDERING, null, msgs);
            }
            msgs = basicSetSemanticOrdering(newSemanticOrdering, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.SEQUENCE_DDIAGRAM__SEMANTIC_ORDERING, newSemanticOrdering, newSemanticOrdering));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EventEndsOrdering getGraphicalOrdering() {
        return graphicalOrdering;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetGraphicalOrdering(EventEndsOrdering newGraphicalOrdering, NotificationChain msgs) {
        EventEndsOrdering oldGraphicalOrdering = graphicalOrdering;
        graphicalOrdering = newGraphicalOrdering;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SequencePackage.SEQUENCE_DDIAGRAM__GRAPHICAL_ORDERING, oldGraphicalOrdering, newGraphicalOrdering);
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
    public void setGraphicalOrdering(EventEndsOrdering newGraphicalOrdering) {
        if (newGraphicalOrdering != graphicalOrdering) {
            NotificationChain msgs = null;
            if (graphicalOrdering != null) {
                msgs = ((InternalEObject) graphicalOrdering).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - SequencePackage.SEQUENCE_DDIAGRAM__GRAPHICAL_ORDERING, null, msgs);
            }
            if (newGraphicalOrdering != null) {
                msgs = ((InternalEObject) newGraphicalOrdering).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - SequencePackage.SEQUENCE_DDIAGRAM__GRAPHICAL_ORDERING, null, msgs);
            }
            msgs = basicSetGraphicalOrdering(newGraphicalOrdering, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.SEQUENCE_DDIAGRAM__GRAPHICAL_ORDERING, newGraphicalOrdering, newGraphicalOrdering));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public InstanceRolesOrdering getInstanceRoleSemanticOrdering() {
        return instanceRoleSemanticOrdering;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetInstanceRoleSemanticOrdering(InstanceRolesOrdering newInstanceRoleSemanticOrdering, NotificationChain msgs) {
        InstanceRolesOrdering oldInstanceRoleSemanticOrdering = instanceRoleSemanticOrdering;
        instanceRoleSemanticOrdering = newInstanceRoleSemanticOrdering;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SequencePackage.SEQUENCE_DDIAGRAM__INSTANCE_ROLE_SEMANTIC_ORDERING, oldInstanceRoleSemanticOrdering,
                    newInstanceRoleSemanticOrdering);
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
    public void setInstanceRoleSemanticOrdering(InstanceRolesOrdering newInstanceRoleSemanticOrdering) {
        if (newInstanceRoleSemanticOrdering != instanceRoleSemanticOrdering) {
            NotificationChain msgs = null;
            if (instanceRoleSemanticOrdering != null) {
                msgs = ((InternalEObject) instanceRoleSemanticOrdering).eInverseRemove(this,
                        InternalEObject.EOPPOSITE_FEATURE_BASE - SequencePackage.SEQUENCE_DDIAGRAM__INSTANCE_ROLE_SEMANTIC_ORDERING, null, msgs);
            }
            if (newInstanceRoleSemanticOrdering != null) {
                msgs = ((InternalEObject) newInstanceRoleSemanticOrdering).eInverseAdd(this,
                        InternalEObject.EOPPOSITE_FEATURE_BASE - SequencePackage.SEQUENCE_DDIAGRAM__INSTANCE_ROLE_SEMANTIC_ORDERING, null, msgs);
            }
            msgs = basicSetInstanceRoleSemanticOrdering(newInstanceRoleSemanticOrdering, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.SEQUENCE_DDIAGRAM__INSTANCE_ROLE_SEMANTIC_ORDERING, newInstanceRoleSemanticOrdering,
                    newInstanceRoleSemanticOrdering));
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
        case SequencePackage.SEQUENCE_DDIAGRAM__SEMANTIC_ORDERING:
            return basicSetSemanticOrdering(null, msgs);
        case SequencePackage.SEQUENCE_DDIAGRAM__GRAPHICAL_ORDERING:
            return basicSetGraphicalOrdering(null, msgs);
        case SequencePackage.SEQUENCE_DDIAGRAM__INSTANCE_ROLE_SEMANTIC_ORDERING:
            return basicSetInstanceRoleSemanticOrdering(null, msgs);
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
        case SequencePackage.SEQUENCE_DDIAGRAM__SEMANTIC_ORDERING:
            return getSemanticOrdering();
        case SequencePackage.SEQUENCE_DDIAGRAM__GRAPHICAL_ORDERING:
            return getGraphicalOrdering();
        case SequencePackage.SEQUENCE_DDIAGRAM__INSTANCE_ROLE_SEMANTIC_ORDERING:
            return getInstanceRoleSemanticOrdering();
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
        case SequencePackage.SEQUENCE_DDIAGRAM__SEMANTIC_ORDERING:
            setSemanticOrdering((EventEndsOrdering) newValue);
            return;
        case SequencePackage.SEQUENCE_DDIAGRAM__GRAPHICAL_ORDERING:
            setGraphicalOrdering((EventEndsOrdering) newValue);
            return;
        case SequencePackage.SEQUENCE_DDIAGRAM__INSTANCE_ROLE_SEMANTIC_ORDERING:
            setInstanceRoleSemanticOrdering((InstanceRolesOrdering) newValue);
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
        case SequencePackage.SEQUENCE_DDIAGRAM__SEMANTIC_ORDERING:
            setSemanticOrdering((EventEndsOrdering) null);
            return;
        case SequencePackage.SEQUENCE_DDIAGRAM__GRAPHICAL_ORDERING:
            setGraphicalOrdering((EventEndsOrdering) null);
            return;
        case SequencePackage.SEQUENCE_DDIAGRAM__INSTANCE_ROLE_SEMANTIC_ORDERING:
            setInstanceRoleSemanticOrdering((InstanceRolesOrdering) null);
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
        case SequencePackage.SEQUENCE_DDIAGRAM__SEMANTIC_ORDERING:
            return semanticOrdering != null;
        case SequencePackage.SEQUENCE_DDIAGRAM__GRAPHICAL_ORDERING:
            return graphicalOrdering != null;
        case SequencePackage.SEQUENCE_DDIAGRAM__INSTANCE_ROLE_SEMANTIC_ORDERING:
            return instanceRoleSemanticOrdering != null;
        }
        return super.eIsSet(featureID);
    }

} // SequenceDDiagramImpl
