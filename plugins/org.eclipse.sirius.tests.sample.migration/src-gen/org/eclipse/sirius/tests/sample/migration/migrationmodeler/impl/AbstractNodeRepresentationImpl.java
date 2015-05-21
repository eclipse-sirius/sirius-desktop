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
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractNodeRepresentation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Bordered;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.NodeStyle;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Abstract Node Representation</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.AbstractNodeRepresentationImpl#getBordereds
 * <em>Bordereds</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.AbstractNodeRepresentationImpl#getOwnedStyle
 * <em>Owned Style</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractNodeRepresentationImpl extends AbstractRepresentationImpl implements AbstractNodeRepresentation {
    /**
     * The cached value of the '{@link #getBordereds() <em>Bordereds</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getBordereds()
     * @generated
     * @ordered
     */
    protected EList<Bordered> bordereds;

    /**
     * The cached value of the '{@link #getOwnedStyle() <em>Owned Style</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getOwnedStyle()
     * @generated
     * @ordered
     */
    protected NodeStyle ownedStyle;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected AbstractNodeRepresentationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return MigrationmodelerPackage.Literals.ABSTRACT_NODE_REPRESENTATION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Bordered> getBordereds() {
        if (bordereds == null) {
            bordereds = new EObjectContainmentEList<Bordered>(Bordered.class, this, MigrationmodelerPackage.ABSTRACT_NODE_REPRESENTATION__BORDEREDS);
        }
        return bordereds;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NodeStyle getOwnedStyle() {
        return ownedStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetOwnedStyle(NodeStyle newOwnedStyle, NotificationChain msgs) {
        NodeStyle oldOwnedStyle = ownedStyle;
        ownedStyle = newOwnedStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.ABSTRACT_NODE_REPRESENTATION__OWNED_STYLE, oldOwnedStyle, newOwnedStyle);
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
    public void setOwnedStyle(NodeStyle newOwnedStyle) {
        if (newOwnedStyle != ownedStyle) {
            NotificationChain msgs = null;
            if (ownedStyle != null) {
                msgs = ((InternalEObject) ownedStyle).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - MigrationmodelerPackage.ABSTRACT_NODE_REPRESENTATION__OWNED_STYLE, null, msgs);
            }
            if (newOwnedStyle != null) {
                msgs = ((InternalEObject) newOwnedStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - MigrationmodelerPackage.ABSTRACT_NODE_REPRESENTATION__OWNED_STYLE, null, msgs);
            }
            msgs = basicSetOwnedStyle(newOwnedStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.ABSTRACT_NODE_REPRESENTATION__OWNED_STYLE, newOwnedStyle, newOwnedStyle));
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
        case MigrationmodelerPackage.ABSTRACT_NODE_REPRESENTATION__BORDEREDS:
            return ((InternalEList<?>) getBordereds()).basicRemove(otherEnd, msgs);
        case MigrationmodelerPackage.ABSTRACT_NODE_REPRESENTATION__OWNED_STYLE:
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
        case MigrationmodelerPackage.ABSTRACT_NODE_REPRESENTATION__BORDEREDS:
            return getBordereds();
        case MigrationmodelerPackage.ABSTRACT_NODE_REPRESENTATION__OWNED_STYLE:
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
        case MigrationmodelerPackage.ABSTRACT_NODE_REPRESENTATION__BORDEREDS:
            getBordereds().clear();
            getBordereds().addAll((Collection<? extends Bordered>) newValue);
            return;
        case MigrationmodelerPackage.ABSTRACT_NODE_REPRESENTATION__OWNED_STYLE:
            setOwnedStyle((NodeStyle) newValue);
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
        case MigrationmodelerPackage.ABSTRACT_NODE_REPRESENTATION__BORDEREDS:
            getBordereds().clear();
            return;
        case MigrationmodelerPackage.ABSTRACT_NODE_REPRESENTATION__OWNED_STYLE:
            setOwnedStyle((NodeStyle) null);
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
        case MigrationmodelerPackage.ABSTRACT_NODE_REPRESENTATION__BORDEREDS:
            return bordereds != null && !bordereds.isEmpty();
        case MigrationmodelerPackage.ABSTRACT_NODE_REPRESENTATION__OWNED_STYLE:
            return ownedStyle != null;
        }
        return super.eIsSet(featureID);
    }

} // AbstractNodeRepresentationImpl
