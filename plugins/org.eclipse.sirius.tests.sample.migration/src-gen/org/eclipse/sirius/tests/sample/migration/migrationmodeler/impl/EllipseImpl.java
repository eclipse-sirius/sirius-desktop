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
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Color;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Ellipse;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Ellipse</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.EllipseImpl#getHorizontalDiameter
 * <em>Horizontal Diameter</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.EllipseImpl#getVerticalDiameter
 * <em>Vertical Diameter</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.EllipseImpl#getColor
 * <em>Color</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EllipseImpl extends NodeStyleImpl implements Ellipse {
    /**
     * The default value of the '{@link #getHorizontalDiameter()
     * <em>Horizontal Diameter</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getHorizontalDiameter()
     * @generated
     * @ordered
     */
    protected static final Integer HORIZONTAL_DIAMETER_EDEFAULT = new Integer(0);

    /**
     * The cached value of the '{@link #getHorizontalDiameter()
     * <em>Horizontal Diameter</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getHorizontalDiameter()
     * @generated
     * @ordered
     */
    protected Integer horizontalDiameter = EllipseImpl.HORIZONTAL_DIAMETER_EDEFAULT;

    /**
     * The default value of the '{@link #getVerticalDiameter()
     * <em>Vertical Diameter</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getVerticalDiameter()
     * @generated
     * @ordered
     */
    protected static final Integer VERTICAL_DIAMETER_EDEFAULT = new Integer(0);

    /**
     * The cached value of the '{@link #getVerticalDiameter()
     * <em>Vertical Diameter</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getVerticalDiameter()
     * @generated
     * @ordered
     */
    protected Integer verticalDiameter = EllipseImpl.VERTICAL_DIAMETER_EDEFAULT;

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
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected EllipseImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return MigrationmodelerPackage.Literals.ELLIPSE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Integer getHorizontalDiameter() {
        return horizontalDiameter;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setHorizontalDiameter(Integer newHorizontalDiameter) {
        Integer oldHorizontalDiameter = horizontalDiameter;
        horizontalDiameter = newHorizontalDiameter;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.ELLIPSE__HORIZONTAL_DIAMETER, oldHorizontalDiameter, horizontalDiameter));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Integer getVerticalDiameter() {
        return verticalDiameter;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setVerticalDiameter(Integer newVerticalDiameter) {
        Integer oldVerticalDiameter = verticalDiameter;
        verticalDiameter = newVerticalDiameter;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.ELLIPSE__VERTICAL_DIAMETER, oldVerticalDiameter, verticalDiameter));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.ELLIPSE__COLOR, oldColor, newColor);
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
                msgs = ((InternalEObject) color).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - MigrationmodelerPackage.ELLIPSE__COLOR, null, msgs);
            }
            if (newColor != null) {
                msgs = ((InternalEObject) newColor).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - MigrationmodelerPackage.ELLIPSE__COLOR, null, msgs);
            }
            msgs = basicSetColor(newColor, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.ELLIPSE__COLOR, newColor, newColor));
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
        case MigrationmodelerPackage.ELLIPSE__COLOR:
            return basicSetColor(null, msgs);
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
        case MigrationmodelerPackage.ELLIPSE__HORIZONTAL_DIAMETER:
            return getHorizontalDiameter();
        case MigrationmodelerPackage.ELLIPSE__VERTICAL_DIAMETER:
            return getVerticalDiameter();
        case MigrationmodelerPackage.ELLIPSE__COLOR:
            return getColor();
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
        case MigrationmodelerPackage.ELLIPSE__HORIZONTAL_DIAMETER:
            setHorizontalDiameter((Integer) newValue);
            return;
        case MigrationmodelerPackage.ELLIPSE__VERTICAL_DIAMETER:
            setVerticalDiameter((Integer) newValue);
            return;
        case MigrationmodelerPackage.ELLIPSE__COLOR:
            setColor((Color) newValue);
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
        case MigrationmodelerPackage.ELLIPSE__HORIZONTAL_DIAMETER:
            setHorizontalDiameter(EllipseImpl.HORIZONTAL_DIAMETER_EDEFAULT);
            return;
        case MigrationmodelerPackage.ELLIPSE__VERTICAL_DIAMETER:
            setVerticalDiameter(EllipseImpl.VERTICAL_DIAMETER_EDEFAULT);
            return;
        case MigrationmodelerPackage.ELLIPSE__COLOR:
            setColor((Color) null);
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
        case MigrationmodelerPackage.ELLIPSE__HORIZONTAL_DIAMETER:
            return EllipseImpl.HORIZONTAL_DIAMETER_EDEFAULT == null ? horizontalDiameter != null : !EllipseImpl.HORIZONTAL_DIAMETER_EDEFAULT.equals(horizontalDiameter);
        case MigrationmodelerPackage.ELLIPSE__VERTICAL_DIAMETER:
            return EllipseImpl.VERTICAL_DIAMETER_EDEFAULT == null ? verticalDiameter != null : !EllipseImpl.VERTICAL_DIAMETER_EDEFAULT.equals(verticalDiameter);
        case MigrationmodelerPackage.ELLIPSE__COLOR:
            return color != null;
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
        result.append(" (horizontalDiameter: "); //$NON-NLS-1$
        result.append(horizontalDiameter);
        result.append(", verticalDiameter: "); //$NON-NLS-1$
        result.append(verticalDiameter);
        result.append(')');
        return result.toString();
    }

} // EllipseImpl
