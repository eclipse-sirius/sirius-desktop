/*******************************************************************************
 * Copyright (c) 2013, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.sample.benchmark.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.tests.sample.benchmark.BenchmarkPackage;
import org.eclipse.sirius.tests.sample.benchmark.Property;
import org.eclipse.sirius.tests.sample.benchmark.TimeResult;
import org.eclipse.sirius.tests.sample.benchmark.Variant;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Time Result</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.benchmark.impl.TimeResultImpl#getElapsedTime
 * <em>Elapsed Time</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.benchmark.impl.TimeResultImpl#getElapsedMaxTime
 * <em>Elapsed Max Time</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.benchmark.impl.TimeResultImpl#getVariant
 * <em>Variant</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.benchmark.impl.TimeResultImpl#getProperties
 * <em>Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TimeResultImpl extends EObjectImpl implements TimeResult {
    /**
     * The default value of the '{@link #getElapsedTime() <em>Elapsed Time</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getElapsedTime()
     * @generated
     * @ordered
     */
    protected static final long ELAPSED_TIME_EDEFAULT = 0L;

    /**
     * The cached value of the '{@link #getElapsedTime() <em>Elapsed Time</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getElapsedTime()
     * @generated
     * @ordered
     */
    protected long elapsedTime = ELAPSED_TIME_EDEFAULT;

    /**
     * The default value of the '{@link #getElapsedMaxTime()
     * <em>Elapsed Max Time</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getElapsedMaxTime()
     * @generated
     * @ordered
     */
    protected static final long ELAPSED_MAX_TIME_EDEFAULT = 0L;

    /**
     * The cached value of the '{@link #getElapsedMaxTime()
     * <em>Elapsed Max Time</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getElapsedMaxTime()
     * @generated
     * @ordered
     */
    protected long elapsedMaxTime = ELAPSED_MAX_TIME_EDEFAULT;

    /**
     * The cached value of the '{@link #getVariant() <em>Variant</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getVariant()
     * @generated
     * @ordered
     */
    protected Variant variant;

    /**
     * The cached value of the '{@link #getProperties() <em>Properties</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getProperties()
     * @generated
     * @ordered
     */
    protected EList<Property> properties;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected TimeResultImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BenchmarkPackage.Literals.TIME_RESULT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public long getElapsedTime() {
        return elapsedTime;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setElapsedTime(long newElapsedTime) {
        long oldElapsedTime = elapsedTime;
        elapsedTime = newElapsedTime;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BenchmarkPackage.TIME_RESULT__ELAPSED_TIME, oldElapsedTime, elapsedTime));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public long getElapsedMaxTime() {
        return elapsedMaxTime;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setElapsedMaxTime(long newElapsedMaxTime) {
        long oldElapsedMaxTime = elapsedMaxTime;
        elapsedMaxTime = newElapsedMaxTime;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BenchmarkPackage.TIME_RESULT__ELAPSED_MAX_TIME, oldElapsedMaxTime, elapsedMaxTime));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Variant getVariant() {
        if (variant != null && variant.eIsProxy()) {
            InternalEObject oldVariant = (InternalEObject) variant;
            variant = (Variant) eResolveProxy(oldVariant);
            if (variant != oldVariant) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, BenchmarkPackage.TIME_RESULT__VARIANT, oldVariant, variant));
            }
        }
        return variant;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Variant basicGetVariant() {
        return variant;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetVariant(Variant newVariant, NotificationChain msgs) {
        Variant oldVariant = variant;
        variant = newVariant;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BenchmarkPackage.TIME_RESULT__VARIANT, oldVariant, newVariant);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setVariant(Variant newVariant) {
        if (newVariant != variant) {
            NotificationChain msgs = null;
            if (variant != null)
                msgs = ((InternalEObject) variant).eInverseRemove(this, BenchmarkPackage.VARIANT__RESULTS, Variant.class, msgs);
            if (newVariant != null)
                msgs = ((InternalEObject) newVariant).eInverseAdd(this, BenchmarkPackage.VARIANT__RESULTS, Variant.class, msgs);
            msgs = basicSetVariant(newVariant, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BenchmarkPackage.TIME_RESULT__VARIANT, newVariant, newVariant));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Property> getProperties() {
        if (properties == null) {
            properties = new EObjectContainmentEList<Property>(Property.class, this, BenchmarkPackage.TIME_RESULT__PROPERTIES);
        }
        return properties;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case BenchmarkPackage.TIME_RESULT__VARIANT:
            if (variant != null)
                msgs = ((InternalEObject) variant).eInverseRemove(this, BenchmarkPackage.VARIANT__RESULTS, Variant.class, msgs);
            return basicSetVariant((Variant) otherEnd, msgs);
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
        case BenchmarkPackage.TIME_RESULT__VARIANT:
            return basicSetVariant(null, msgs);
        case BenchmarkPackage.TIME_RESULT__PROPERTIES:
            return ((InternalEList<?>) getProperties()).basicRemove(otherEnd, msgs);
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
        case BenchmarkPackage.TIME_RESULT__ELAPSED_TIME:
            return getElapsedTime();
        case BenchmarkPackage.TIME_RESULT__ELAPSED_MAX_TIME:
            return getElapsedMaxTime();
        case BenchmarkPackage.TIME_RESULT__VARIANT:
            if (resolve)
                return getVariant();
            return basicGetVariant();
        case BenchmarkPackage.TIME_RESULT__PROPERTIES:
            return getProperties();
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
        case BenchmarkPackage.TIME_RESULT__ELAPSED_TIME:
            setElapsedTime((Long) newValue);
            return;
        case BenchmarkPackage.TIME_RESULT__ELAPSED_MAX_TIME:
            setElapsedMaxTime((Long) newValue);
            return;
        case BenchmarkPackage.TIME_RESULT__VARIANT:
            setVariant((Variant) newValue);
            return;
        case BenchmarkPackage.TIME_RESULT__PROPERTIES:
            getProperties().clear();
            getProperties().addAll((Collection<? extends Property>) newValue);
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
        case BenchmarkPackage.TIME_RESULT__ELAPSED_TIME:
            setElapsedTime(ELAPSED_TIME_EDEFAULT);
            return;
        case BenchmarkPackage.TIME_RESULT__ELAPSED_MAX_TIME:
            setElapsedMaxTime(ELAPSED_MAX_TIME_EDEFAULT);
            return;
        case BenchmarkPackage.TIME_RESULT__VARIANT:
            setVariant((Variant) null);
            return;
        case BenchmarkPackage.TIME_RESULT__PROPERTIES:
            getProperties().clear();
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
        case BenchmarkPackage.TIME_RESULT__ELAPSED_TIME:
            return elapsedTime != ELAPSED_TIME_EDEFAULT;
        case BenchmarkPackage.TIME_RESULT__ELAPSED_MAX_TIME:
            return elapsedMaxTime != ELAPSED_MAX_TIME_EDEFAULT;
        case BenchmarkPackage.TIME_RESULT__VARIANT:
            return variant != null;
        case BenchmarkPackage.TIME_RESULT__PROPERTIES:
            return properties != null && !properties.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (elapsedTime: "); //$NON-NLS-1$
        result.append(elapsedTime);
        result.append(", elapsedMaxTime: "); //$NON-NLS-1$
        result.append(elapsedMaxTime);
        result.append(')');
        return result.toString();
    }

} // TimeResultImpl
