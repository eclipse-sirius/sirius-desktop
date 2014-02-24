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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.Ellipse;
import org.eclipse.sirius.viewpoint.RGBValues;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Ellipse</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.impl.EllipseImpl#getHorizontalDiameter
 * <em>Horizontal Diameter</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.EllipseImpl#getVerticalDiameter
 * <em>Vertical Diameter</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.EllipseImpl#getColor <em>Color
 * </em>}</li>
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
    protected Integer horizontalDiameter = HORIZONTAL_DIAMETER_EDEFAULT;

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
    protected Integer verticalDiameter = VERTICAL_DIAMETER_EDEFAULT;

    /**
     * The cached value of the '{@link #getColor() <em>Color</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getColor()
     * @generated
     * @ordered
     */
    protected RGBValues color;

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
        return DiagramPackage.Literals.ELLIPSE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Integer getHorizontalDiameter() {
        return horizontalDiameter;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setHorizontalDiameter(Integer newHorizontalDiameter) {
        Integer oldHorizontalDiameter = horizontalDiameter;
        horizontalDiameter = newHorizontalDiameter;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.ELLIPSE__HORIZONTAL_DIAMETER, oldHorizontalDiameter, horizontalDiameter));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Integer getVerticalDiameter() {
        return verticalDiameter;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setVerticalDiameter(Integer newVerticalDiameter) {
        Integer oldVerticalDiameter = verticalDiameter;
        verticalDiameter = newVerticalDiameter;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.ELLIPSE__VERTICAL_DIAMETER, oldVerticalDiameter, verticalDiameter));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public RGBValues getColor() {
        if (color != null && color.eIsProxy()) {
            InternalEObject oldColor = (InternalEObject) color;
            color = (RGBValues) eResolveProxy(oldColor);
            if (color != oldColor) {
                InternalEObject newColor = (InternalEObject) color;
                NotificationChain msgs = oldColor.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.ELLIPSE__COLOR, null, null);
                if (newColor.eInternalContainer() == null) {
                    msgs = newColor.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.ELLIPSE__COLOR, null, msgs);
                }
                if (msgs != null)
                    msgs.dispatch();
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DiagramPackage.ELLIPSE__COLOR, oldColor, color));
            }
        }
        return color;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public RGBValues basicGetColor() {
        return color;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetColor(RGBValues newColor, NotificationChain msgs) {
        RGBValues oldColor = color;
        color = newColor;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DiagramPackage.ELLIPSE__COLOR, oldColor, newColor);
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
    public void setColor(RGBValues newColor) {
        if (newColor != color) {
            NotificationChain msgs = null;
            if (color != null)
                msgs = ((InternalEObject) color).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.ELLIPSE__COLOR, null, msgs);
            if (newColor != null)
                msgs = ((InternalEObject) newColor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.ELLIPSE__COLOR, null, msgs);
            msgs = basicSetColor(newColor, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.ELLIPSE__COLOR, newColor, newColor));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DiagramPackage.ELLIPSE__COLOR:
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
        case DiagramPackage.ELLIPSE__HORIZONTAL_DIAMETER:
            return getHorizontalDiameter();
        case DiagramPackage.ELLIPSE__VERTICAL_DIAMETER:
            return getVerticalDiameter();
        case DiagramPackage.ELLIPSE__COLOR:
            if (resolve)
                return getColor();
            return basicGetColor();
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
        case DiagramPackage.ELLIPSE__HORIZONTAL_DIAMETER:
            setHorizontalDiameter((Integer) newValue);
            return;
        case DiagramPackage.ELLIPSE__VERTICAL_DIAMETER:
            setVerticalDiameter((Integer) newValue);
            return;
        case DiagramPackage.ELLIPSE__COLOR:
            setColor((RGBValues) newValue);
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
        case DiagramPackage.ELLIPSE__HORIZONTAL_DIAMETER:
            setHorizontalDiameter(HORIZONTAL_DIAMETER_EDEFAULT);
            return;
        case DiagramPackage.ELLIPSE__VERTICAL_DIAMETER:
            setVerticalDiameter(VERTICAL_DIAMETER_EDEFAULT);
            return;
        case DiagramPackage.ELLIPSE__COLOR:
            setColor((RGBValues) null);
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
        case DiagramPackage.ELLIPSE__HORIZONTAL_DIAMETER:
            return HORIZONTAL_DIAMETER_EDEFAULT == null ? horizontalDiameter != null : !HORIZONTAL_DIAMETER_EDEFAULT.equals(horizontalDiameter);
        case DiagramPackage.ELLIPSE__VERTICAL_DIAMETER:
            return VERTICAL_DIAMETER_EDEFAULT == null ? verticalDiameter != null : !VERTICAL_DIAMETER_EDEFAULT.equals(verticalDiameter);
        case DiagramPackage.ELLIPSE__COLOR:
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
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (horizontalDiameter: ");
        result.append(horizontalDiameter);
        result.append(", verticalDiameter: ");
        result.append(verticalDiameter);
        result.append(')');
        return result.toString();
    }

} // EllipseImpl
