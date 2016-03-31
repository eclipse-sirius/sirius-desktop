/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.diagram.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.EdgeTarget;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Edge Target</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.impl.EdgeTargetImpl#getOutgoingEdges
 * <em>Outgoing Edges</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.EdgeTargetImpl#getIncomingEdges
 * <em>Incoming Edges</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class EdgeTargetImpl extends MinimalEObjectImpl.Container implements EdgeTarget {
    /**
     * The cached value of the '{@link #getOutgoingEdges()
     * <em>Outgoing Edges</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getOutgoingEdges()
     * @generated
     * @ordered
     */
    protected EList<DEdge> outgoingEdges;

    /**
     * The cached value of the '{@link #getIncomingEdges()
     * <em>Incoming Edges</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getIncomingEdges()
     * @generated
     * @ordered
     */
    protected EList<DEdge> incomingEdges;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected EdgeTargetImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DiagramPackage.Literals.EDGE_TARGET;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DEdge> getOutgoingEdges() {
        if (outgoingEdges == null) {
            outgoingEdges = new EObjectWithInverseResolvingEList<DEdge>(DEdge.class, this, DiagramPackage.EDGE_TARGET__OUTGOING_EDGES, DiagramPackage.DEDGE__SOURCE_NODE);
        }
        return outgoingEdges;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DEdge> getIncomingEdges() {
        if (incomingEdges == null) {
            incomingEdges = new EObjectWithInverseResolvingEList<DEdge>(DEdge.class, this, DiagramPackage.EDGE_TARGET__INCOMING_EDGES, DiagramPackage.DEDGE__TARGET_NODE);
        }
        return incomingEdges;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DiagramPackage.EDGE_TARGET__OUTGOING_EDGES:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getOutgoingEdges()).basicAdd(otherEnd, msgs);
        case DiagramPackage.EDGE_TARGET__INCOMING_EDGES:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getIncomingEdges()).basicAdd(otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DiagramPackage.EDGE_TARGET__OUTGOING_EDGES:
            return ((InternalEList<?>) getOutgoingEdges()).basicRemove(otherEnd, msgs);
        case DiagramPackage.EDGE_TARGET__INCOMING_EDGES:
            return ((InternalEList<?>) getIncomingEdges()).basicRemove(otherEnd, msgs);
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
        case DiagramPackage.EDGE_TARGET__OUTGOING_EDGES:
            return getOutgoingEdges();
        case DiagramPackage.EDGE_TARGET__INCOMING_EDGES:
            return getIncomingEdges();
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
        case DiagramPackage.EDGE_TARGET__OUTGOING_EDGES:
            getOutgoingEdges().clear();
            getOutgoingEdges().addAll((Collection<? extends DEdge>) newValue);
            return;
        case DiagramPackage.EDGE_TARGET__INCOMING_EDGES:
            getIncomingEdges().clear();
            getIncomingEdges().addAll((Collection<? extends DEdge>) newValue);
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
        case DiagramPackage.EDGE_TARGET__OUTGOING_EDGES:
            getOutgoingEdges().clear();
            return;
        case DiagramPackage.EDGE_TARGET__INCOMING_EDGES:
            getIncomingEdges().clear();
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
        case DiagramPackage.EDGE_TARGET__OUTGOING_EDGES:
            return outgoingEdges != null && !outgoingEdges.isEmpty();
        case DiagramPackage.EDGE_TARGET__INCOMING_EDGES:
            return incomingEdges != null && !incomingEdges.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // EdgeTargetImpl
