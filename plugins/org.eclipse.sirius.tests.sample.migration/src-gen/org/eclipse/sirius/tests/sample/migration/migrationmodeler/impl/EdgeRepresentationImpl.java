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
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeRepresentation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.GraphicalElement;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Point;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Edge Representation</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.EdgeRepresentationImpl#getSource
 * <em>Source</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.EdgeRepresentationImpl#getTarget
 * <em>Target</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.EdgeRepresentationImpl#getBendpoints
 * <em>Bendpoints</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.EdgeRepresentationImpl#getOwnedStyle
 * <em>Owned Style</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EdgeRepresentationImpl extends AbstractRepresentationImpl implements EdgeRepresentation {
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
     * The cached value of the '{@link #getBendpoints() <em>Bendpoints</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getBendpoints()
     * @generated
     * @ordered
     */
    protected EList<Point> bendpoints;

    /**
     * The cached value of the '{@link #getOwnedStyle() <em>Owned Style</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getOwnedStyle()
     * @generated
     * @ordered
     */
    protected EdgeStyle ownedStyle;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected EdgeRepresentationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return MigrationmodelerPackage.Literals.EDGE_REPRESENTATION;
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, MigrationmodelerPackage.EDGE_REPRESENTATION__SOURCE, oldSource, source));
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
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.EDGE_REPRESENTATION__SOURCE, oldSource, source));
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, MigrationmodelerPackage.EDGE_REPRESENTATION__TARGET, oldTarget, target));
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
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.EDGE_REPRESENTATION__TARGET, oldTarget, target));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Point> getBendpoints() {
        if (bendpoints == null) {
            bendpoints = new EObjectContainmentEList<Point>(Point.class, this, MigrationmodelerPackage.EDGE_REPRESENTATION__BENDPOINTS);
        }
        return bendpoints;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EdgeStyle getOwnedStyle() {
        return ownedStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetOwnedStyle(EdgeStyle newOwnedStyle, NotificationChain msgs) {
        EdgeStyle oldOwnedStyle = ownedStyle;
        ownedStyle = newOwnedStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.EDGE_REPRESENTATION__OWNED_STYLE, oldOwnedStyle, newOwnedStyle);
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
    public void setOwnedStyle(EdgeStyle newOwnedStyle) {
        if (newOwnedStyle != ownedStyle) {
            NotificationChain msgs = null;
            if (ownedStyle != null) {
                msgs = ((InternalEObject) ownedStyle).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - MigrationmodelerPackage.EDGE_REPRESENTATION__OWNED_STYLE, null, msgs);
            }
            if (newOwnedStyle != null) {
                msgs = ((InternalEObject) newOwnedStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - MigrationmodelerPackage.EDGE_REPRESENTATION__OWNED_STYLE, null, msgs);
            }
            msgs = basicSetOwnedStyle(newOwnedStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.EDGE_REPRESENTATION__OWNED_STYLE, newOwnedStyle, newOwnedStyle));
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
        case MigrationmodelerPackage.EDGE_REPRESENTATION__BENDPOINTS:
            return ((InternalEList<?>) getBendpoints()).basicRemove(otherEnd, msgs);
        case MigrationmodelerPackage.EDGE_REPRESENTATION__OWNED_STYLE:
            return basicSetOwnedStyle(null, msgs);
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
        case MigrationmodelerPackage.EDGE_REPRESENTATION__SOURCE:
            if (resolve) {
                return getSource();
            }
            return basicGetSource();
        case MigrationmodelerPackage.EDGE_REPRESENTATION__TARGET:
            if (resolve) {
                return getTarget();
            }
            return basicGetTarget();
        case MigrationmodelerPackage.EDGE_REPRESENTATION__BENDPOINTS:
            return getBendpoints();
        case MigrationmodelerPackage.EDGE_REPRESENTATION__OWNED_STYLE:
            return getOwnedStyle();
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
        case MigrationmodelerPackage.EDGE_REPRESENTATION__SOURCE:
            setSource((GraphicalElement) newValue);
            return;
        case MigrationmodelerPackage.EDGE_REPRESENTATION__TARGET:
            setTarget((GraphicalElement) newValue);
            return;
        case MigrationmodelerPackage.EDGE_REPRESENTATION__BENDPOINTS:
            getBendpoints().clear();
            getBendpoints().addAll((Collection<? extends Point>) newValue);
            return;
        case MigrationmodelerPackage.EDGE_REPRESENTATION__OWNED_STYLE:
            setOwnedStyle((EdgeStyle) newValue);
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
        case MigrationmodelerPackage.EDGE_REPRESENTATION__SOURCE:
            setSource((GraphicalElement) null);
            return;
        case MigrationmodelerPackage.EDGE_REPRESENTATION__TARGET:
            setTarget((GraphicalElement) null);
            return;
        case MigrationmodelerPackage.EDGE_REPRESENTATION__BENDPOINTS:
            getBendpoints().clear();
            return;
        case MigrationmodelerPackage.EDGE_REPRESENTATION__OWNED_STYLE:
            setOwnedStyle((EdgeStyle) null);
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
        case MigrationmodelerPackage.EDGE_REPRESENTATION__SOURCE:
            return source != null;
        case MigrationmodelerPackage.EDGE_REPRESENTATION__TARGET:
            return target != null;
        case MigrationmodelerPackage.EDGE_REPRESENTATION__BENDPOINTS:
            return bendpoints != null && !bendpoints.isEmpty();
        case MigrationmodelerPackage.EDGE_REPRESENTATION__OWNED_STYLE:
            return ownedStyle != null;
        }
        return super.eIsSet(featureID);
    }

} // EdgeRepresentationImpl
