/**
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.properties.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.properties.AbstractDynamicMappingForDescription;
import org.eclipse.sirius.properties.DynamicMappingForDescription;
import org.eclipse.sirius.properties.DynamicMappingIfDescription;
import org.eclipse.sirius.properties.PropertiesPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Abstract Dynamic Mapping For
 * Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractDynamicMappingForDescriptionImpl#getIterator
 * <em>Iterator</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractDynamicMappingForDescriptionImpl#getIterableExpression
 * <em>Iterable Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractDynamicMappingForDescriptionImpl#isForceRefresh <em>Force
 * Refresh</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractDynamicMappingForDescriptionImpl#getIfs <em>Ifs</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractDynamicMappingForDescriptionImpl#getExtends
 * <em>Extends</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractDynamicMappingForDescriptionImpl#getFilterIfsFromExtendedDynamicMappingForExpression
 * <em>Filter Ifs From Extended Dynamic Mapping For Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractDynamicMappingForDescriptionImpl extends AbstractControlDescriptionImpl implements AbstractDynamicMappingForDescription {
    /**
     * The default value of the '{@link #getIterator() <em>Iterator</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getIterator()
     * @generated
     * @ordered
     */
    protected static final String ITERATOR_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getIterator() <em>Iterator</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getIterator()
     * @generated
     * @ordered
     */
    protected String iterator = AbstractDynamicMappingForDescriptionImpl.ITERATOR_EDEFAULT;

    /**
     * The default value of the '{@link #getIterableExpression() <em>Iterable Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getIterableExpression()
     * @generated
     * @ordered
     */
    protected static final String ITERABLE_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getIterableExpression() <em>Iterable Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getIterableExpression()
     * @generated
     * @ordered
     */
    protected String iterableExpression = AbstractDynamicMappingForDescriptionImpl.ITERABLE_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #isForceRefresh() <em>Force Refresh</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #isForceRefresh()
     * @generated
     * @ordered
     */
    protected static final boolean FORCE_REFRESH_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isForceRefresh() <em>Force Refresh</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #isForceRefresh()
     * @generated
     * @ordered
     */
    protected boolean forceRefresh = AbstractDynamicMappingForDescriptionImpl.FORCE_REFRESH_EDEFAULT;

    /**
     * The cached value of the '{@link #getIfs() <em>Ifs</em>}' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getIfs()
     * @generated
     * @ordered
     */
    protected EList<DynamicMappingIfDescription> ifs;

    /**
     * The cached value of the '{@link #getExtends() <em>Extends</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getExtends()
     * @generated
     * @ordered
     */
    protected DynamicMappingForDescription extends_;

    /**
     * The default value of the '{@link #getFilterIfsFromExtendedDynamicMappingForExpression() <em>Filter Ifs From
     * Extended Dynamic Mapping For Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterIfsFromExtendedDynamicMappingForExpression()
     * @generated
     * @ordered
     */
    protected static final String FILTER_IFS_FROM_EXTENDED_DYNAMIC_MAPPING_FOR_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFilterIfsFromExtendedDynamicMappingForExpression() <em>Filter Ifs From
     * Extended Dynamic Mapping For Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterIfsFromExtendedDynamicMappingForExpression()
     * @generated
     * @ordered
     */
    protected String filterIfsFromExtendedDynamicMappingForExpression = AbstractDynamicMappingForDescriptionImpl.FILTER_IFS_FROM_EXTENDED_DYNAMIC_MAPPING_FOR_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected AbstractDynamicMappingForDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getIterator() {
        return iterator;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setIterator(String newIterator) {
        String oldIterator = iterator;
        iterator = newIterator;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__ITERATOR, oldIterator, iterator));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getIterableExpression() {
        return iterableExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setIterableExpression(String newIterableExpression) {
        String oldIterableExpression = iterableExpression;
        iterableExpression = newIterableExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__ITERABLE_EXPRESSION, oldIterableExpression, iterableExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isForceRefresh() {
        return forceRefresh;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setForceRefresh(boolean newForceRefresh) {
        boolean oldForceRefresh = forceRefresh;
        forceRefresh = newForceRefresh;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__FORCE_REFRESH, oldForceRefresh, forceRefresh));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DynamicMappingIfDescription> getIfs() {
        if (ifs == null) {
            ifs = new EObjectContainmentEList<DynamicMappingIfDescription>(DynamicMappingIfDescription.class, this, PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__IFS);
        }
        return ifs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DynamicMappingForDescription getExtends() {
        if (extends_ != null && extends_.eIsProxy()) {
            InternalEObject oldExtends = (InternalEObject) extends_;
            extends_ = (DynamicMappingForDescription) eResolveProxy(oldExtends);
            if (extends_ != oldExtends) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__EXTENDS, oldExtends, extends_));
                }
            }
        }
        return extends_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public DynamicMappingForDescription basicGetExtends() {
        return extends_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setExtends(DynamicMappingForDescription newExtends) {
        DynamicMappingForDescription oldExtends = extends_;
        extends_ = newExtends;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__EXTENDS, oldExtends, extends_));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getFilterIfsFromExtendedDynamicMappingForExpression() {
        return filterIfsFromExtendedDynamicMappingForExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFilterIfsFromExtendedDynamicMappingForExpression(String newFilterIfsFromExtendedDynamicMappingForExpression) {
        String oldFilterIfsFromExtendedDynamicMappingForExpression = filterIfsFromExtendedDynamicMappingForExpression;
        filterIfsFromExtendedDynamicMappingForExpression = newFilterIfsFromExtendedDynamicMappingForExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__FILTER_IFS_FROM_EXTENDED_DYNAMIC_MAPPING_FOR_EXPRESSION,
                    oldFilterIfsFromExtendedDynamicMappingForExpression, filterIfsFromExtendedDynamicMappingForExpression));
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
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__IFS:
            return ((InternalEList<?>) getIfs()).basicRemove(otherEnd, msgs);
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
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__ITERATOR:
            return getIterator();
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__ITERABLE_EXPRESSION:
            return getIterableExpression();
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__FORCE_REFRESH:
            return isForceRefresh();
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__IFS:
            return getIfs();
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__EXTENDS:
            if (resolve) {
                return getExtends();
            }
            return basicGetExtends();
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__FILTER_IFS_FROM_EXTENDED_DYNAMIC_MAPPING_FOR_EXPRESSION:
            return getFilterIfsFromExtendedDynamicMappingForExpression();
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
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__ITERATOR:
            setIterator((String) newValue);
            return;
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__ITERABLE_EXPRESSION:
            setIterableExpression((String) newValue);
            return;
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__FORCE_REFRESH:
            setForceRefresh((Boolean) newValue);
            return;
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__IFS:
            getIfs().clear();
            getIfs().addAll((Collection<? extends DynamicMappingIfDescription>) newValue);
            return;
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__EXTENDS:
            setExtends((DynamicMappingForDescription) newValue);
            return;
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__FILTER_IFS_FROM_EXTENDED_DYNAMIC_MAPPING_FOR_EXPRESSION:
            setFilterIfsFromExtendedDynamicMappingForExpression((String) newValue);
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
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__ITERATOR:
            setIterator(AbstractDynamicMappingForDescriptionImpl.ITERATOR_EDEFAULT);
            return;
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__ITERABLE_EXPRESSION:
            setIterableExpression(AbstractDynamicMappingForDescriptionImpl.ITERABLE_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__FORCE_REFRESH:
            setForceRefresh(AbstractDynamicMappingForDescriptionImpl.FORCE_REFRESH_EDEFAULT);
            return;
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__IFS:
            getIfs().clear();
            return;
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__EXTENDS:
            setExtends((DynamicMappingForDescription) null);
            return;
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__FILTER_IFS_FROM_EXTENDED_DYNAMIC_MAPPING_FOR_EXPRESSION:
            setFilterIfsFromExtendedDynamicMappingForExpression(AbstractDynamicMappingForDescriptionImpl.FILTER_IFS_FROM_EXTENDED_DYNAMIC_MAPPING_FOR_EXPRESSION_EDEFAULT);
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
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__ITERATOR:
            return AbstractDynamicMappingForDescriptionImpl.ITERATOR_EDEFAULT == null ? iterator != null : !AbstractDynamicMappingForDescriptionImpl.ITERATOR_EDEFAULT.equals(iterator);
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__ITERABLE_EXPRESSION:
            return AbstractDynamicMappingForDescriptionImpl.ITERABLE_EXPRESSION_EDEFAULT == null ? iterableExpression != null
                    : !AbstractDynamicMappingForDescriptionImpl.ITERABLE_EXPRESSION_EDEFAULT.equals(iterableExpression);
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__FORCE_REFRESH:
            return forceRefresh != AbstractDynamicMappingForDescriptionImpl.FORCE_REFRESH_EDEFAULT;
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__IFS:
            return ifs != null && !ifs.isEmpty();
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__EXTENDS:
            return extends_ != null;
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__FILTER_IFS_FROM_EXTENDED_DYNAMIC_MAPPING_FOR_EXPRESSION:
            return AbstractDynamicMappingForDescriptionImpl.FILTER_IFS_FROM_EXTENDED_DYNAMIC_MAPPING_FOR_EXPRESSION_EDEFAULT == null ? filterIfsFromExtendedDynamicMappingForExpression != null
                    : !AbstractDynamicMappingForDescriptionImpl.FILTER_IFS_FROM_EXTENDED_DYNAMIC_MAPPING_FOR_EXPRESSION_EDEFAULT.equals(filterIfsFromExtendedDynamicMappingForExpression);
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
        if (eIsProxy()) {
            return super.toString();
        }

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (iterator: "); //$NON-NLS-1$
        result.append(iterator);
        result.append(", iterableExpression: "); //$NON-NLS-1$
        result.append(iterableExpression);
        result.append(", forceRefresh: "); //$NON-NLS-1$
        result.append(forceRefresh);
        result.append(", filterIfsFromExtendedDynamicMappingForExpression: "); //$NON-NLS-1$
        result.append(filterIfsFromExtendedDynamicMappingForExpression);
        result.append(')');
        return result.toString();
    }

} // AbstractDynamicMappingForDescriptionImpl
