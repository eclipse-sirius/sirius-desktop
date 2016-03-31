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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.Ellipse;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Ellipse</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.impl.EllipseImpl#getHorizontalDiameter
 * <em>Horizontal Diameter</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.EllipseImpl#getVerticalDiameter
 * <em>Vertical Diameter</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.EllipseImpl#getColor
 * <em>Color</em>}</li>
 * </ul>
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
     * The default value of the '{@link #getColor() <em>Color</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getColor()
     * @generated
     * @ordered
     */
    protected static final RGBValues COLOR_EDEFAULT = (RGBValues) ViewpointFactory.eINSTANCE.createFromString(ViewpointPackage.eINSTANCE.getRGBValues(), "136,136,136"); //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getColor() <em>Color</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getColor()
     * @generated
     * @ordered
     */
    protected RGBValues color = EllipseImpl.COLOR_EDEFAULT;

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
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.ELLIPSE__HORIZONTAL_DIAMETER, oldHorizontalDiameter, horizontalDiameter));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.ELLIPSE__VERTICAL_DIAMETER, oldVerticalDiameter, verticalDiameter));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public RGBValues getColor() {
        return color;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setColor(RGBValues newColor) {
        RGBValues oldColor = color;
        color = newColor;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.ELLIPSE__COLOR, oldColor, color));
        }
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
            setHorizontalDiameter(EllipseImpl.HORIZONTAL_DIAMETER_EDEFAULT);
            return;
        case DiagramPackage.ELLIPSE__VERTICAL_DIAMETER:
            setVerticalDiameter(EllipseImpl.VERTICAL_DIAMETER_EDEFAULT);
            return;
        case DiagramPackage.ELLIPSE__COLOR:
            setColor(EllipseImpl.COLOR_EDEFAULT);
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
            return EllipseImpl.HORIZONTAL_DIAMETER_EDEFAULT == null ? horizontalDiameter != null : !EllipseImpl.HORIZONTAL_DIAMETER_EDEFAULT.equals(horizontalDiameter);
        case DiagramPackage.ELLIPSE__VERTICAL_DIAMETER:
            return EllipseImpl.VERTICAL_DIAMETER_EDEFAULT == null ? verticalDiameter != null : !EllipseImpl.VERTICAL_DIAMETER_EDEFAULT.equals(verticalDiameter);
        case DiagramPackage.ELLIPSE__COLOR:
            return EllipseImpl.COLOR_EDEFAULT == null ? color != null : !EllipseImpl.COLOR_EDEFAULT.equals(color);
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
        result.append(", color: "); //$NON-NLS-1$
        result.append(color);
        result.append(')');
        return result.toString();
    }

} // EllipseImpl
