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
package org.eclipse.sirius.viewpoint.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.viewpoint.BorderedStyle;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Bordered Style</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.BorderedStyleImpl#getBorderSize
 * <em>Border Size</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.impl.BorderedStyleImpl#getBorderSizeComputationExpression
 * <em>Border Size Computation Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.impl.BorderedStyleImpl#getBorderColor
 * <em>Border Color</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class BorderedStyleImpl extends StyleImpl implements BorderedStyle {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

    /**
     * The default value of the '{@link #getBorderSize() <em>Border Size</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getBorderSize()
     * @generated
     * @ordered
     */
    protected static final Integer BORDER_SIZE_EDEFAULT = new Integer(0);

    /**
     * The cached value of the '{@link #getBorderSize() <em>Border Size</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getBorderSize()
     * @generated
     * @ordered
     */
    protected Integer borderSize = BORDER_SIZE_EDEFAULT;

    /**
     * The default value of the '{@link #getBorderSizeComputationExpression()
     * <em>Border Size Computation Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getBorderSizeComputationExpression()
     * @generated
     * @ordered
     */
    protected static final String BORDER_SIZE_COMPUTATION_EXPRESSION_EDEFAULT = "0";

    /**
     * The cached value of the '{@link #getBorderSizeComputationExpression()
     * <em>Border Size Computation Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getBorderSizeComputationExpression()
     * @generated
     * @ordered
     */
    protected String borderSizeComputationExpression = BORDER_SIZE_COMPUTATION_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getBorderColor() <em>Border Color</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getBorderColor()
     * @generated
     * @ordered
     */
    protected RGBValues borderColor;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected BorderedStyleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ViewpointPackage.Literals.BORDERED_STYLE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Integer getBorderSize() {
        return borderSize;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setBorderSize(Integer newBorderSize) {
        Integer oldBorderSize = borderSize;
        borderSize = newBorderSize;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.BORDERED_STYLE__BORDER_SIZE, oldBorderSize, borderSize));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getBorderSizeComputationExpression() {
        return borderSizeComputationExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setBorderSizeComputationExpression(String newBorderSizeComputationExpression) {
        String oldBorderSizeComputationExpression = borderSizeComputationExpression;
        borderSizeComputationExpression = newBorderSizeComputationExpression;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.BORDERED_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION, oldBorderSizeComputationExpression,
                    borderSizeComputationExpression));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public RGBValues getBorderColor() {
        if (borderColor != null && borderColor.eIsProxy()) {
            InternalEObject oldBorderColor = (InternalEObject) borderColor;
            borderColor = (RGBValues) eResolveProxy(oldBorderColor);
            if (borderColor != oldBorderColor) {
                InternalEObject newBorderColor = (InternalEObject) borderColor;
                NotificationChain msgs = oldBorderColor.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewpointPackage.BORDERED_STYLE__BORDER_COLOR, null, null);
                if (newBorderColor.eInternalContainer() == null) {
                    msgs = newBorderColor.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewpointPackage.BORDERED_STYLE__BORDER_COLOR, null, msgs);
                }
                if (msgs != null)
                    msgs.dispatch();
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViewpointPackage.BORDERED_STYLE__BORDER_COLOR, oldBorderColor, borderColor));
            }
        }
        return borderColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public RGBValues basicGetBorderColor() {
        return borderColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetBorderColor(RGBValues newBorderColor, NotificationChain msgs) {
        RGBValues oldBorderColor = borderColor;
        borderColor = newBorderColor;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewpointPackage.BORDERED_STYLE__BORDER_COLOR, oldBorderColor, newBorderColor);
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
    public void setBorderColor(RGBValues newBorderColor) {
        if (newBorderColor != borderColor) {
            NotificationChain msgs = null;
            if (borderColor != null)
                msgs = ((InternalEObject) borderColor).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewpointPackage.BORDERED_STYLE__BORDER_COLOR, null, msgs);
            if (newBorderColor != null)
                msgs = ((InternalEObject) newBorderColor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewpointPackage.BORDERED_STYLE__BORDER_COLOR, null, msgs);
            msgs = basicSetBorderColor(newBorderColor, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.BORDERED_STYLE__BORDER_COLOR, newBorderColor, newBorderColor));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ViewpointPackage.BORDERED_STYLE__BORDER_COLOR:
            return basicSetBorderColor(null, msgs);
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
        case ViewpointPackage.BORDERED_STYLE__BORDER_SIZE:
            return getBorderSize();
        case ViewpointPackage.BORDERED_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION:
            return getBorderSizeComputationExpression();
        case ViewpointPackage.BORDERED_STYLE__BORDER_COLOR:
            if (resolve)
                return getBorderColor();
            return basicGetBorderColor();
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
        case ViewpointPackage.BORDERED_STYLE__BORDER_SIZE:
            setBorderSize((Integer) newValue);
            return;
        case ViewpointPackage.BORDERED_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION:
            setBorderSizeComputationExpression((String) newValue);
            return;
        case ViewpointPackage.BORDERED_STYLE__BORDER_COLOR:
            setBorderColor((RGBValues) newValue);
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
        case ViewpointPackage.BORDERED_STYLE__BORDER_SIZE:
            setBorderSize(BORDER_SIZE_EDEFAULT);
            return;
        case ViewpointPackage.BORDERED_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION:
            setBorderSizeComputationExpression(BORDER_SIZE_COMPUTATION_EXPRESSION_EDEFAULT);
            return;
        case ViewpointPackage.BORDERED_STYLE__BORDER_COLOR:
            setBorderColor((RGBValues) null);
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
        case ViewpointPackage.BORDERED_STYLE__BORDER_SIZE:
            return BORDER_SIZE_EDEFAULT == null ? borderSize != null : !BORDER_SIZE_EDEFAULT.equals(borderSize);
        case ViewpointPackage.BORDERED_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION:
            return BORDER_SIZE_COMPUTATION_EXPRESSION_EDEFAULT == null ? borderSizeComputationExpression != null : !BORDER_SIZE_COMPUTATION_EXPRESSION_EDEFAULT.equals(borderSizeComputationExpression);
        case ViewpointPackage.BORDERED_STYLE__BORDER_COLOR:
            return borderColor != null;
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
        result.append(" (borderSize: ");
        result.append(borderSize);
        result.append(", borderSizeComputationExpression: ");
        result.append(borderSizeComputationExpression);
        result.append(')');
        return result.toString();
    }

} // BorderedStyleImpl
