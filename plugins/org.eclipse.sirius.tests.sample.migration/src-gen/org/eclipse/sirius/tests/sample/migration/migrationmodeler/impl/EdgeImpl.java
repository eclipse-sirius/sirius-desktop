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
package org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Edge;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeRepresentation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.GraphicalElement;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Edge</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.EdgeImpl#getEdgeRepresentations
 * <em>Edge Representations</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.EdgeImpl#getSource
 * <em>Source</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.EdgeImpl#getTarget
 * <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EdgeImpl extends GraphicalElementImpl implements Edge {
    /**
     * The cached value of the '{@link #getEdgeRepresentations()
     * <em>Edge Representations</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getEdgeRepresentations()
     * @generated
     * @ordered
     */
    protected EList<EdgeRepresentation> edgeRepresentations;

    /**
     * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSource()
     * @generated
     * @ordered
     */
    protected GraphicalElement source;

    /**
     * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getTarget()
     * @generated
     * @ordered
     */
    protected GraphicalElement target;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected EdgeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return MigrationmodelerPackage.Literals.EDGE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<EdgeRepresentation> getEdgeRepresentations() {
        if (edgeRepresentations == null) {
            edgeRepresentations = new EObjectContainmentEList<EdgeRepresentation>(EdgeRepresentation.class, this, MigrationmodelerPackage.EDGE__EDGE_REPRESENTATIONS);
        }
        return edgeRepresentations;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public GraphicalElement getSource() {
        if (source != null && source.eIsProxy()) {
            InternalEObject oldSource = (InternalEObject) source;
            source = (GraphicalElement) eResolveProxy(oldSource);
            if (source != oldSource) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, MigrationmodelerPackage.EDGE__SOURCE, oldSource, source));
                }
            }
        }
        return source;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public GraphicalElement basicGetSource() {
        return source;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setSource(GraphicalElement newSource) {
        GraphicalElement oldSource = source;
        source = newSource;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.EDGE__SOURCE, oldSource, source));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public GraphicalElement getTarget() {
        if (target != null && target.eIsProxy()) {
            InternalEObject oldTarget = (InternalEObject) target;
            target = (GraphicalElement) eResolveProxy(oldTarget);
            if (target != oldTarget) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, MigrationmodelerPackage.EDGE__TARGET, oldTarget, target));
                }
            }
        }
        return target;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public GraphicalElement basicGetTarget() {
        return target;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setTarget(GraphicalElement newTarget) {
        GraphicalElement oldTarget = target;
        target = newTarget;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.EDGE__TARGET, oldTarget, target));
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
        case MigrationmodelerPackage.EDGE__EDGE_REPRESENTATIONS:
            return ((InternalEList<?>) getEdgeRepresentations()).basicRemove(otherEnd, msgs);
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
        case MigrationmodelerPackage.EDGE__EDGE_REPRESENTATIONS:
            return getEdgeRepresentations();
        case MigrationmodelerPackage.EDGE__SOURCE:
            if (resolve) {
                return getSource();
            }
            return basicGetSource();
        case MigrationmodelerPackage.EDGE__TARGET:
            if (resolve) {
                return getTarget();
            }
            return basicGetTarget();
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
        case MigrationmodelerPackage.EDGE__EDGE_REPRESENTATIONS:
            getEdgeRepresentations().clear();
            getEdgeRepresentations().addAll((Collection<? extends EdgeRepresentation>) newValue);
            return;
        case MigrationmodelerPackage.EDGE__SOURCE:
            setSource((GraphicalElement) newValue);
            return;
        case MigrationmodelerPackage.EDGE__TARGET:
            setTarget((GraphicalElement) newValue);
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
        case MigrationmodelerPackage.EDGE__EDGE_REPRESENTATIONS:
            getEdgeRepresentations().clear();
            return;
        case MigrationmodelerPackage.EDGE__SOURCE:
            setSource((GraphicalElement) null);
            return;
        case MigrationmodelerPackage.EDGE__TARGET:
            setTarget((GraphicalElement) null);
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
        case MigrationmodelerPackage.EDGE__EDGE_REPRESENTATIONS:
            return edgeRepresentations != null && !edgeRepresentations.isEmpty();
        case MigrationmodelerPackage.EDGE__SOURCE:
            return source != null;
        case MigrationmodelerPackage.EDGE__TARGET:
            return target != null;
        }
        return super.eIsSet(featureID);
    }

} // EdgeImpl
