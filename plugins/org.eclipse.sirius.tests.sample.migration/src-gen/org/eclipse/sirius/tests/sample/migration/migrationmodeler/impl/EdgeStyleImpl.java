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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.BasicLabelStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Color;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.RoutingStyle;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Edge Style</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.EdgeStyleImpl#getRoutingStyle
 * <em>Routing Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.EdgeStyleImpl#getColor
 * <em>Color</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.EdgeStyleImpl#getBeginLabelStyle
 * <em>Begin Label Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.EdgeStyleImpl#getCenterLabelStyle
 * <em>Center Label Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.EdgeStyleImpl#getEndLabelStyle
 * <em>End Label Style</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EdgeStyleImpl extends EObjectImpl implements EdgeStyle {
    /**
     * The default value of the '{@link #getRoutingStyle()
     * <em>Routing Style</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getRoutingStyle()
     * @generated
     * @ordered
     */
    protected static final RoutingStyle ROUTING_STYLE_EDEFAULT = RoutingStyle.STRAIGHT;

    /**
     * The cached value of the '{@link #getRoutingStyle()
     * <em>Routing Style</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getRoutingStyle()
     * @generated
     * @ordered
     */
    protected RoutingStyle routingStyle = EdgeStyleImpl.ROUTING_STYLE_EDEFAULT;

    /**
     * The cached value of the '{@link #getColor() <em>Color</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getColor()
     * @generated
     * @ordered
     */
    protected Color color;

    /**
     * The cached value of the '{@link #getBeginLabelStyle()
     * <em>Begin Label Style</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getBeginLabelStyle()
     * @generated
     * @ordered
     */
    protected BasicLabelStyle beginLabelStyle;

    /**
     * The cached value of the '{@link #getCenterLabelStyle()
     * <em>Center Label Style</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getCenterLabelStyle()
     * @generated
     * @ordered
     */
    protected BasicLabelStyle centerLabelStyle;

    /**
     * The cached value of the '{@link #getEndLabelStyle()
     * <em>End Label Style</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getEndLabelStyle()
     * @generated
     * @ordered
     */
    protected BasicLabelStyle endLabelStyle;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected EdgeStyleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return MigrationmodelerPackage.Literals.EDGE_STYLE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public RoutingStyle getRoutingStyle() {
        return routingStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setRoutingStyle(RoutingStyle newRoutingStyle) {
        RoutingStyle oldRoutingStyle = routingStyle;
        routingStyle = newRoutingStyle == null ? EdgeStyleImpl.ROUTING_STYLE_EDEFAULT : newRoutingStyle;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.EDGE_STYLE__ROUTING_STYLE, oldRoutingStyle, routingStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Color getColor() {
        return color;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetColor(Color newColor, NotificationChain msgs) {
        Color oldColor = color;
        color = newColor;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.EDGE_STYLE__COLOR, oldColor, newColor);
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
    public void setColor(Color newColor) {
        if (newColor != color) {
            NotificationChain msgs = null;
            if (color != null) {
                msgs = ((InternalEObject) color).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - MigrationmodelerPackage.EDGE_STYLE__COLOR, null, msgs);
            }
            if (newColor != null) {
                msgs = ((InternalEObject) newColor).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - MigrationmodelerPackage.EDGE_STYLE__COLOR, null, msgs);
            }
            msgs = basicSetColor(newColor, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.EDGE_STYLE__COLOR, newColor, newColor));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public BasicLabelStyle getBeginLabelStyle() {
        return beginLabelStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetBeginLabelStyle(BasicLabelStyle newBeginLabelStyle, NotificationChain msgs) {
        BasicLabelStyle oldBeginLabelStyle = beginLabelStyle;
        beginLabelStyle = newBeginLabelStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.EDGE_STYLE__BEGIN_LABEL_STYLE, oldBeginLabelStyle, newBeginLabelStyle);
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
    public void setBeginLabelStyle(BasicLabelStyle newBeginLabelStyle) {
        if (newBeginLabelStyle != beginLabelStyle) {
            NotificationChain msgs = null;
            if (beginLabelStyle != null) {
                msgs = ((InternalEObject) beginLabelStyle).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - MigrationmodelerPackage.EDGE_STYLE__BEGIN_LABEL_STYLE, null, msgs);
            }
            if (newBeginLabelStyle != null) {
                msgs = ((InternalEObject) newBeginLabelStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - MigrationmodelerPackage.EDGE_STYLE__BEGIN_LABEL_STYLE, null, msgs);
            }
            msgs = basicSetBeginLabelStyle(newBeginLabelStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.EDGE_STYLE__BEGIN_LABEL_STYLE, newBeginLabelStyle, newBeginLabelStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public BasicLabelStyle getCenterLabelStyle() {
        return centerLabelStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetCenterLabelStyle(BasicLabelStyle newCenterLabelStyle, NotificationChain msgs) {
        BasicLabelStyle oldCenterLabelStyle = centerLabelStyle;
        centerLabelStyle = newCenterLabelStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.EDGE_STYLE__CENTER_LABEL_STYLE, oldCenterLabelStyle, newCenterLabelStyle);
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
    public void setCenterLabelStyle(BasicLabelStyle newCenterLabelStyle) {
        if (newCenterLabelStyle != centerLabelStyle) {
            NotificationChain msgs = null;
            if (centerLabelStyle != null) {
                msgs = ((InternalEObject) centerLabelStyle).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - MigrationmodelerPackage.EDGE_STYLE__CENTER_LABEL_STYLE, null, msgs);
            }
            if (newCenterLabelStyle != null) {
                msgs = ((InternalEObject) newCenterLabelStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - MigrationmodelerPackage.EDGE_STYLE__CENTER_LABEL_STYLE, null, msgs);
            }
            msgs = basicSetCenterLabelStyle(newCenterLabelStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.EDGE_STYLE__CENTER_LABEL_STYLE, newCenterLabelStyle, newCenterLabelStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public BasicLabelStyle getEndLabelStyle() {
        return endLabelStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetEndLabelStyle(BasicLabelStyle newEndLabelStyle, NotificationChain msgs) {
        BasicLabelStyle oldEndLabelStyle = endLabelStyle;
        endLabelStyle = newEndLabelStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.EDGE_STYLE__END_LABEL_STYLE, oldEndLabelStyle, newEndLabelStyle);
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
    public void setEndLabelStyle(BasicLabelStyle newEndLabelStyle) {
        if (newEndLabelStyle != endLabelStyle) {
            NotificationChain msgs = null;
            if (endLabelStyle != null) {
                msgs = ((InternalEObject) endLabelStyle).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - MigrationmodelerPackage.EDGE_STYLE__END_LABEL_STYLE, null, msgs);
            }
            if (newEndLabelStyle != null) {
                msgs = ((InternalEObject) newEndLabelStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - MigrationmodelerPackage.EDGE_STYLE__END_LABEL_STYLE, null, msgs);
            }
            msgs = basicSetEndLabelStyle(newEndLabelStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.EDGE_STYLE__END_LABEL_STYLE, newEndLabelStyle, newEndLabelStyle));
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
        case MigrationmodelerPackage.EDGE_STYLE__COLOR:
            return basicSetColor(null, msgs);
        case MigrationmodelerPackage.EDGE_STYLE__BEGIN_LABEL_STYLE:
            return basicSetBeginLabelStyle(null, msgs);
        case MigrationmodelerPackage.EDGE_STYLE__CENTER_LABEL_STYLE:
            return basicSetCenterLabelStyle(null, msgs);
        case MigrationmodelerPackage.EDGE_STYLE__END_LABEL_STYLE:
            return basicSetEndLabelStyle(null, msgs);
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
        case MigrationmodelerPackage.EDGE_STYLE__ROUTING_STYLE:
            return getRoutingStyle();
        case MigrationmodelerPackage.EDGE_STYLE__COLOR:
            return getColor();
        case MigrationmodelerPackage.EDGE_STYLE__BEGIN_LABEL_STYLE:
            return getBeginLabelStyle();
        case MigrationmodelerPackage.EDGE_STYLE__CENTER_LABEL_STYLE:
            return getCenterLabelStyle();
        case MigrationmodelerPackage.EDGE_STYLE__END_LABEL_STYLE:
            return getEndLabelStyle();
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
        case MigrationmodelerPackage.EDGE_STYLE__ROUTING_STYLE:
            setRoutingStyle((RoutingStyle) newValue);
            return;
        case MigrationmodelerPackage.EDGE_STYLE__COLOR:
            setColor((Color) newValue);
            return;
        case MigrationmodelerPackage.EDGE_STYLE__BEGIN_LABEL_STYLE:
            setBeginLabelStyle((BasicLabelStyle) newValue);
            return;
        case MigrationmodelerPackage.EDGE_STYLE__CENTER_LABEL_STYLE:
            setCenterLabelStyle((BasicLabelStyle) newValue);
            return;
        case MigrationmodelerPackage.EDGE_STYLE__END_LABEL_STYLE:
            setEndLabelStyle((BasicLabelStyle) newValue);
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
        case MigrationmodelerPackage.EDGE_STYLE__ROUTING_STYLE:
            setRoutingStyle(EdgeStyleImpl.ROUTING_STYLE_EDEFAULT);
            return;
        case MigrationmodelerPackage.EDGE_STYLE__COLOR:
            setColor((Color) null);
            return;
        case MigrationmodelerPackage.EDGE_STYLE__BEGIN_LABEL_STYLE:
            setBeginLabelStyle((BasicLabelStyle) null);
            return;
        case MigrationmodelerPackage.EDGE_STYLE__CENTER_LABEL_STYLE:
            setCenterLabelStyle((BasicLabelStyle) null);
            return;
        case MigrationmodelerPackage.EDGE_STYLE__END_LABEL_STYLE:
            setEndLabelStyle((BasicLabelStyle) null);
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
        case MigrationmodelerPackage.EDGE_STYLE__ROUTING_STYLE:
            return routingStyle != EdgeStyleImpl.ROUTING_STYLE_EDEFAULT;
        case MigrationmodelerPackage.EDGE_STYLE__COLOR:
            return color != null;
        case MigrationmodelerPackage.EDGE_STYLE__BEGIN_LABEL_STYLE:
            return beginLabelStyle != null;
        case MigrationmodelerPackage.EDGE_STYLE__CENTER_LABEL_STYLE:
            return centerLabelStyle != null;
        case MigrationmodelerPackage.EDGE_STYLE__END_LABEL_STYLE:
            return endLabelStyle != null;
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
        result.append(" (routingStyle: "); //$NON-NLS-1$
        result.append(routingStyle);
        result.append(')');
        return result.toString();
    }

} // EdgeStyleImpl
