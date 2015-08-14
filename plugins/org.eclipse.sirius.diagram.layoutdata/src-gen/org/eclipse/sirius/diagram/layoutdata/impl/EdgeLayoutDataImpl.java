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
package org.eclipse.sirius.diagram.layoutdata.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData;
import org.eclipse.sirius.diagram.layoutdata.LayoutdataPackage;
import org.eclipse.sirius.diagram.layoutdata.Point;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Edge Layout Data</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.layoutdata.impl.EdgeLayoutDataImpl#getSourceTerminal
 * <em>Source Terminal</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.layoutdata.impl.EdgeLayoutDataImpl#getTargetTerminal
 * <em>Target Terminal</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.layoutdata.impl.EdgeLayoutDataImpl#getRouting
 * <em>Routing</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.layoutdata.impl.EdgeLayoutDataImpl#getPointList
 * <em>Point List</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.layoutdata.impl.EdgeLayoutDataImpl#getSourceRefPoint
 * <em>Source Ref Point</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.layoutdata.impl.EdgeLayoutDataImpl#getTargetRefPoint
 * <em>Target Ref Point</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.layoutdata.impl.EdgeLayoutDataImpl#getJumpLinkStatus
 * <em>Jump Link Status</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.layoutdata.impl.EdgeLayoutDataImpl#getJumpLinkType
 * <em>Jump Link Type</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.layoutdata.impl.EdgeLayoutDataImpl#isReverseJumpLink
 * <em>Reverse Jump Link</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.layoutdata.impl.EdgeLayoutDataImpl#getSmoothness
 * <em>Smoothness</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EdgeLayoutDataImpl extends AbstractLayoutDataImpl implements EdgeLayoutData {
    /**
     * The default value of the '{@link #getSourceTerminal()
     * <em>Source Terminal</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getSourceTerminal()
     * @generated
     * @ordered
     */
    protected static final String SOURCE_TERMINAL_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSourceTerminal()
     * <em>Source Terminal</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getSourceTerminal()
     * @generated
     * @ordered
     */
    protected String sourceTerminal = SOURCE_TERMINAL_EDEFAULT;

    /**
     * The default value of the '{@link #getTargetTerminal()
     * <em>Target Terminal</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getTargetTerminal()
     * @generated
     * @ordered
     */
    protected static final String TARGET_TERMINAL_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTargetTerminal()
     * <em>Target Terminal</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getTargetTerminal()
     * @generated
     * @ordered
     */
    protected String targetTerminal = TARGET_TERMINAL_EDEFAULT;

    /**
     * The default value of the '{@link #getRouting() <em>Routing</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getRouting()
     * @generated
     * @ordered
     */
    protected static final int ROUTING_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getRouting() <em>Routing</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getRouting()
     * @generated
     * @ordered
     */
    protected int routing = ROUTING_EDEFAULT;

    /**
     * The cached value of the '{@link #getPointList() <em>Point List</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getPointList()
     * @generated
     * @ordered
     */
    protected EList<Point> pointList;

    /**
     * The cached value of the '{@link #getSourceRefPoint()
     * <em>Source Ref Point</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getSourceRefPoint()
     * @generated
     * @ordered
     */
    protected Point sourceRefPoint;

    /**
     * The cached value of the '{@link #getTargetRefPoint()
     * <em>Target Ref Point</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getTargetRefPoint()
     * @generated
     * @ordered
     */
    protected Point targetRefPoint;

    /**
     * The default value of the '{@link #getJumpLinkStatus()
     * <em>Jump Link Status</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getJumpLinkStatus()
     * @generated
     * @ordered
     */
    protected static final int JUMP_LINK_STATUS_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getJumpLinkStatus()
     * <em>Jump Link Status</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getJumpLinkStatus()
     * @generated
     * @ordered
     */
    protected int jumpLinkStatus = JUMP_LINK_STATUS_EDEFAULT;

    /**
     * The default value of the '{@link #getJumpLinkType()
     * <em>Jump Link Type</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getJumpLinkType()
     * @generated
     * @ordered
     */
    protected static final int JUMP_LINK_TYPE_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getJumpLinkType()
     * <em>Jump Link Type</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getJumpLinkType()
     * @generated
     * @ordered
     */
    protected int jumpLinkType = JUMP_LINK_TYPE_EDEFAULT;

    /**
     * The default value of the '{@link #isReverseJumpLink()
     * <em>Reverse Jump Link</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #isReverseJumpLink()
     * @generated
     * @ordered
     */
    protected static final boolean REVERSE_JUMP_LINK_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isReverseJumpLink()
     * <em>Reverse Jump Link</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #isReverseJumpLink()
     * @generated
     * @ordered
     */
    protected boolean reverseJumpLink = REVERSE_JUMP_LINK_EDEFAULT;

    /**
     * The default value of the '{@link #getSmoothness() <em>Smoothness</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getSmoothness()
     * @generated
     * @ordered
     */
    protected static final int SMOOTHNESS_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getSmoothness() <em>Smoothness</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getSmoothness()
     * @generated
     * @ordered
     */
    protected int smoothness = SMOOTHNESS_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected EdgeLayoutDataImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return LayoutdataPackage.Literals.EDGE_LAYOUT_DATA;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getSourceTerminal() {
        return sourceTerminal;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setSourceTerminal(String newSourceTerminal) {
        String oldSourceTerminal = sourceTerminal;
        sourceTerminal = newSourceTerminal;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LayoutdataPackage.EDGE_LAYOUT_DATA__SOURCE_TERMINAL, oldSourceTerminal, sourceTerminal));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getTargetTerminal() {
        return targetTerminal;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setTargetTerminal(String newTargetTerminal) {
        String oldTargetTerminal = targetTerminal;
        targetTerminal = newTargetTerminal;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LayoutdataPackage.EDGE_LAYOUT_DATA__TARGET_TERMINAL, oldTargetTerminal, targetTerminal));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public int getRouting() {
        return routing;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setRouting(int newRouting) {
        int oldRouting = routing;
        routing = newRouting;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LayoutdataPackage.EDGE_LAYOUT_DATA__ROUTING, oldRouting, routing));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<Point> getPointList() {
        if (pointList == null) {
            pointList = new EObjectContainmentEList.Resolving<Point>(Point.class, this, LayoutdataPackage.EDGE_LAYOUT_DATA__POINT_LIST);
        }
        return pointList;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Point getSourceRefPoint() {
        if (sourceRefPoint != null && sourceRefPoint.eIsProxy()) {
            InternalEObject oldSourceRefPoint = (InternalEObject) sourceRefPoint;
            sourceRefPoint = (Point) eResolveProxy(oldSourceRefPoint);
            if (sourceRefPoint != oldSourceRefPoint) {
                InternalEObject newSourceRefPoint = (InternalEObject) sourceRefPoint;
                NotificationChain msgs = oldSourceRefPoint.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - LayoutdataPackage.EDGE_LAYOUT_DATA__SOURCE_REF_POINT, null, null);
                if (newSourceRefPoint.eInternalContainer() == null) {
                    msgs = newSourceRefPoint.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - LayoutdataPackage.EDGE_LAYOUT_DATA__SOURCE_REF_POINT, null, msgs);
                }
                if (msgs != null)
                    msgs.dispatch();
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, LayoutdataPackage.EDGE_LAYOUT_DATA__SOURCE_REF_POINT, oldSourceRefPoint, sourceRefPoint));
            }
        }
        return sourceRefPoint;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Point basicGetSourceRefPoint() {
        return sourceRefPoint;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetSourceRefPoint(Point newSourceRefPoint, NotificationChain msgs) {
        Point oldSourceRefPoint = sourceRefPoint;
        sourceRefPoint = newSourceRefPoint;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LayoutdataPackage.EDGE_LAYOUT_DATA__SOURCE_REF_POINT, oldSourceRefPoint, newSourceRefPoint);
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
    @Override
    public void setSourceRefPoint(Point newSourceRefPoint) {
        if (newSourceRefPoint != sourceRefPoint) {
            NotificationChain msgs = null;
            if (sourceRefPoint != null)
                msgs = ((InternalEObject) sourceRefPoint).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - LayoutdataPackage.EDGE_LAYOUT_DATA__SOURCE_REF_POINT, null, msgs);
            if (newSourceRefPoint != null)
                msgs = ((InternalEObject) newSourceRefPoint).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - LayoutdataPackage.EDGE_LAYOUT_DATA__SOURCE_REF_POINT, null, msgs);
            msgs = basicSetSourceRefPoint(newSourceRefPoint, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LayoutdataPackage.EDGE_LAYOUT_DATA__SOURCE_REF_POINT, newSourceRefPoint, newSourceRefPoint));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Point getTargetRefPoint() {
        if (targetRefPoint != null && targetRefPoint.eIsProxy()) {
            InternalEObject oldTargetRefPoint = (InternalEObject) targetRefPoint;
            targetRefPoint = (Point) eResolveProxy(oldTargetRefPoint);
            if (targetRefPoint != oldTargetRefPoint) {
                InternalEObject newTargetRefPoint = (InternalEObject) targetRefPoint;
                NotificationChain msgs = oldTargetRefPoint.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - LayoutdataPackage.EDGE_LAYOUT_DATA__TARGET_REF_POINT, null, null);
                if (newTargetRefPoint.eInternalContainer() == null) {
                    msgs = newTargetRefPoint.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - LayoutdataPackage.EDGE_LAYOUT_DATA__TARGET_REF_POINT, null, msgs);
                }
                if (msgs != null)
                    msgs.dispatch();
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, LayoutdataPackage.EDGE_LAYOUT_DATA__TARGET_REF_POINT, oldTargetRefPoint, targetRefPoint));
            }
        }
        return targetRefPoint;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Point basicGetTargetRefPoint() {
        return targetRefPoint;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetTargetRefPoint(Point newTargetRefPoint, NotificationChain msgs) {
        Point oldTargetRefPoint = targetRefPoint;
        targetRefPoint = newTargetRefPoint;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LayoutdataPackage.EDGE_LAYOUT_DATA__TARGET_REF_POINT, oldTargetRefPoint, newTargetRefPoint);
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
    @Override
    public void setTargetRefPoint(Point newTargetRefPoint) {
        if (newTargetRefPoint != targetRefPoint) {
            NotificationChain msgs = null;
            if (targetRefPoint != null)
                msgs = ((InternalEObject) targetRefPoint).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - LayoutdataPackage.EDGE_LAYOUT_DATA__TARGET_REF_POINT, null, msgs);
            if (newTargetRefPoint != null)
                msgs = ((InternalEObject) newTargetRefPoint).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - LayoutdataPackage.EDGE_LAYOUT_DATA__TARGET_REF_POINT, null, msgs);
            msgs = basicSetTargetRefPoint(newTargetRefPoint, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LayoutdataPackage.EDGE_LAYOUT_DATA__TARGET_REF_POINT, newTargetRefPoint, newTargetRefPoint));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public int getJumpLinkStatus() {
        return jumpLinkStatus;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setJumpLinkStatus(int newJumpLinkStatus) {
        int oldJumpLinkStatus = jumpLinkStatus;
        jumpLinkStatus = newJumpLinkStatus;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LayoutdataPackage.EDGE_LAYOUT_DATA__JUMP_LINK_STATUS, oldJumpLinkStatus, jumpLinkStatus));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public int getJumpLinkType() {
        return jumpLinkType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setJumpLinkType(int newJumpLinkType) {
        int oldJumpLinkType = jumpLinkType;
        jumpLinkType = newJumpLinkType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LayoutdataPackage.EDGE_LAYOUT_DATA__JUMP_LINK_TYPE, oldJumpLinkType, jumpLinkType));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean isReverseJumpLink() {
        return reverseJumpLink;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setReverseJumpLink(boolean newReverseJumpLink) {
        boolean oldReverseJumpLink = reverseJumpLink;
        reverseJumpLink = newReverseJumpLink;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LayoutdataPackage.EDGE_LAYOUT_DATA__REVERSE_JUMP_LINK, oldReverseJumpLink, reverseJumpLink));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public int getSmoothness() {
        return smoothness;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setSmoothness(int newSmoothness) {
        int oldSmoothness = smoothness;
        smoothness = newSmoothness;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LayoutdataPackage.EDGE_LAYOUT_DATA__SMOOTHNESS, oldSmoothness, smoothness));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case LayoutdataPackage.EDGE_LAYOUT_DATA__POINT_LIST:
            return ((InternalEList<?>) getPointList()).basicRemove(otherEnd, msgs);
        case LayoutdataPackage.EDGE_LAYOUT_DATA__SOURCE_REF_POINT:
            return basicSetSourceRefPoint(null, msgs);
        case LayoutdataPackage.EDGE_LAYOUT_DATA__TARGET_REF_POINT:
            return basicSetTargetRefPoint(null, msgs);
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
        case LayoutdataPackage.EDGE_LAYOUT_DATA__SOURCE_TERMINAL:
            return getSourceTerminal();
        case LayoutdataPackage.EDGE_LAYOUT_DATA__TARGET_TERMINAL:
            return getTargetTerminal();
        case LayoutdataPackage.EDGE_LAYOUT_DATA__ROUTING:
            return getRouting();
        case LayoutdataPackage.EDGE_LAYOUT_DATA__POINT_LIST:
            return getPointList();
        case LayoutdataPackage.EDGE_LAYOUT_DATA__SOURCE_REF_POINT:
            if (resolve)
                return getSourceRefPoint();
            return basicGetSourceRefPoint();
        case LayoutdataPackage.EDGE_LAYOUT_DATA__TARGET_REF_POINT:
            if (resolve)
                return getTargetRefPoint();
            return basicGetTargetRefPoint();
        case LayoutdataPackage.EDGE_LAYOUT_DATA__JUMP_LINK_STATUS:
            return getJumpLinkStatus();
        case LayoutdataPackage.EDGE_LAYOUT_DATA__JUMP_LINK_TYPE:
            return getJumpLinkType();
        case LayoutdataPackage.EDGE_LAYOUT_DATA__REVERSE_JUMP_LINK:
            return isReverseJumpLink();
        case LayoutdataPackage.EDGE_LAYOUT_DATA__SMOOTHNESS:
            return getSmoothness();
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
        case LayoutdataPackage.EDGE_LAYOUT_DATA__SOURCE_TERMINAL:
            setSourceTerminal((String) newValue);
            return;
        case LayoutdataPackage.EDGE_LAYOUT_DATA__TARGET_TERMINAL:
            setTargetTerminal((String) newValue);
            return;
        case LayoutdataPackage.EDGE_LAYOUT_DATA__ROUTING:
            setRouting((Integer) newValue);
            return;
        case LayoutdataPackage.EDGE_LAYOUT_DATA__POINT_LIST:
            getPointList().clear();
            getPointList().addAll((Collection<? extends Point>) newValue);
            return;
        case LayoutdataPackage.EDGE_LAYOUT_DATA__SOURCE_REF_POINT:
            setSourceRefPoint((Point) newValue);
            return;
        case LayoutdataPackage.EDGE_LAYOUT_DATA__TARGET_REF_POINT:
            setTargetRefPoint((Point) newValue);
            return;
        case LayoutdataPackage.EDGE_LAYOUT_DATA__JUMP_LINK_STATUS:
            setJumpLinkStatus((Integer) newValue);
            return;
        case LayoutdataPackage.EDGE_LAYOUT_DATA__JUMP_LINK_TYPE:
            setJumpLinkType((Integer) newValue);
            return;
        case LayoutdataPackage.EDGE_LAYOUT_DATA__REVERSE_JUMP_LINK:
            setReverseJumpLink((Boolean) newValue);
            return;
        case LayoutdataPackage.EDGE_LAYOUT_DATA__SMOOTHNESS:
            setSmoothness((Integer) newValue);
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
        case LayoutdataPackage.EDGE_LAYOUT_DATA__SOURCE_TERMINAL:
            setSourceTerminal(SOURCE_TERMINAL_EDEFAULT);
            return;
        case LayoutdataPackage.EDGE_LAYOUT_DATA__TARGET_TERMINAL:
            setTargetTerminal(TARGET_TERMINAL_EDEFAULT);
            return;
        case LayoutdataPackage.EDGE_LAYOUT_DATA__ROUTING:
            setRouting(ROUTING_EDEFAULT);
            return;
        case LayoutdataPackage.EDGE_LAYOUT_DATA__POINT_LIST:
            getPointList().clear();
            return;
        case LayoutdataPackage.EDGE_LAYOUT_DATA__SOURCE_REF_POINT:
            setSourceRefPoint((Point) null);
            return;
        case LayoutdataPackage.EDGE_LAYOUT_DATA__TARGET_REF_POINT:
            setTargetRefPoint((Point) null);
            return;
        case LayoutdataPackage.EDGE_LAYOUT_DATA__JUMP_LINK_STATUS:
            setJumpLinkStatus(JUMP_LINK_STATUS_EDEFAULT);
            return;
        case LayoutdataPackage.EDGE_LAYOUT_DATA__JUMP_LINK_TYPE:
            setJumpLinkType(JUMP_LINK_TYPE_EDEFAULT);
            return;
        case LayoutdataPackage.EDGE_LAYOUT_DATA__REVERSE_JUMP_LINK:
            setReverseJumpLink(REVERSE_JUMP_LINK_EDEFAULT);
            return;
        case LayoutdataPackage.EDGE_LAYOUT_DATA__SMOOTHNESS:
            setSmoothness(SMOOTHNESS_EDEFAULT);
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
        case LayoutdataPackage.EDGE_LAYOUT_DATA__SOURCE_TERMINAL:
            return SOURCE_TERMINAL_EDEFAULT == null ? sourceTerminal != null : !SOURCE_TERMINAL_EDEFAULT.equals(sourceTerminal);
        case LayoutdataPackage.EDGE_LAYOUT_DATA__TARGET_TERMINAL:
            return TARGET_TERMINAL_EDEFAULT == null ? targetTerminal != null : !TARGET_TERMINAL_EDEFAULT.equals(targetTerminal);
        case LayoutdataPackage.EDGE_LAYOUT_DATA__ROUTING:
            return routing != ROUTING_EDEFAULT;
        case LayoutdataPackage.EDGE_LAYOUT_DATA__POINT_LIST:
            return pointList != null && !pointList.isEmpty();
        case LayoutdataPackage.EDGE_LAYOUT_DATA__SOURCE_REF_POINT:
            return sourceRefPoint != null;
        case LayoutdataPackage.EDGE_LAYOUT_DATA__TARGET_REF_POINT:
            return targetRefPoint != null;
        case LayoutdataPackage.EDGE_LAYOUT_DATA__JUMP_LINK_STATUS:
            return jumpLinkStatus != JUMP_LINK_STATUS_EDEFAULT;
        case LayoutdataPackage.EDGE_LAYOUT_DATA__JUMP_LINK_TYPE:
            return jumpLinkType != JUMP_LINK_TYPE_EDEFAULT;
        case LayoutdataPackage.EDGE_LAYOUT_DATA__REVERSE_JUMP_LINK:
            return reverseJumpLink != REVERSE_JUMP_LINK_EDEFAULT;
        case LayoutdataPackage.EDGE_LAYOUT_DATA__SMOOTHNESS:
            return smoothness != SMOOTHNESS_EDEFAULT;
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
        result.append(" (sourceTerminal: "); //$NON-NLS-1$
        result.append(sourceTerminal);
        result.append(", targetTerminal: "); //$NON-NLS-1$
        result.append(targetTerminal);
        result.append(", routing: "); //$NON-NLS-1$
        result.append(routing);
        result.append(", jumpLinkStatus: "); //$NON-NLS-1$
        result.append(jumpLinkStatus);
        result.append(", jumpLinkType: "); //$NON-NLS-1$
        result.append(jumpLinkType);
        result.append(", reverseJumpLink: "); //$NON-NLS-1$
        result.append(reverseJumpLink);
        result.append(", smoothness: "); //$NON-NLS-1$
        result.append(smoothness);
        result.append(')');
        return result.toString();
    }

} // EdgeLayoutDataImpl
