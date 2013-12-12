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
import org.eclipse.sirius.diagram.Dot;
import org.eclipse.sirius.viewpoint.RGBValues;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Dot</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.impl.DotImpl#getBackgroundColor <em>
 * Background Color</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.impl.DotImpl#getStrokeSizeComputationExpression
 * <em>Stroke Size Computation Expression</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class DotImpl extends NodeStyleImpl implements Dot {
    /**
     * The cached value of the '{@link #getBackgroundColor()
     * <em>Background Color</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getBackgroundColor()
     * @generated
     * @ordered
     */
    protected RGBValues backgroundColor;

    /**
     * The default value of the '{@link #getStrokeSizeComputationExpression()
     * <em>Stroke Size Computation Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getStrokeSizeComputationExpression()
     * @generated
     * @ordered
     */
    protected static final String STROKE_SIZE_COMPUTATION_EXPRESSION_EDEFAULT = "2";

    /**
     * The cached value of the '{@link #getStrokeSizeComputationExpression()
     * <em>Stroke Size Computation Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getStrokeSizeComputationExpression()
     * @generated
     * @ordered
     */
    protected String strokeSizeComputationExpression = STROKE_SIZE_COMPUTATION_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected DotImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DiagramPackage.Literals.DOT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public RGBValues getBackgroundColor() {
        if (backgroundColor != null && backgroundColor.eIsProxy()) {
            InternalEObject oldBackgroundColor = (InternalEObject) backgroundColor;
            backgroundColor = (RGBValues) eResolveProxy(oldBackgroundColor);
            if (backgroundColor != oldBackgroundColor) {
                InternalEObject newBackgroundColor = (InternalEObject) backgroundColor;
                NotificationChain msgs = oldBackgroundColor.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.DOT__BACKGROUND_COLOR, null, null);
                if (newBackgroundColor.eInternalContainer() == null) {
                    msgs = newBackgroundColor.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.DOT__BACKGROUND_COLOR, null, msgs);
                }
                if (msgs != null)
                    msgs.dispatch();
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DiagramPackage.DOT__BACKGROUND_COLOR, oldBackgroundColor, backgroundColor));
            }
        }
        return backgroundColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public RGBValues basicGetBackgroundColor() {
        return backgroundColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetBackgroundColor(RGBValues newBackgroundColor, NotificationChain msgs) {
        RGBValues oldBackgroundColor = backgroundColor;
        backgroundColor = newBackgroundColor;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DiagramPackage.DOT__BACKGROUND_COLOR, oldBackgroundColor, newBackgroundColor);
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
    public void setBackgroundColor(RGBValues newBackgroundColor) {
        if (newBackgroundColor != backgroundColor) {
            NotificationChain msgs = null;
            if (backgroundColor != null)
                msgs = ((InternalEObject) backgroundColor).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.DOT__BACKGROUND_COLOR, null, msgs);
            if (newBackgroundColor != null)
                msgs = ((InternalEObject) newBackgroundColor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.DOT__BACKGROUND_COLOR, null, msgs);
            msgs = basicSetBackgroundColor(newBackgroundColor, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DOT__BACKGROUND_COLOR, newBackgroundColor, newBackgroundColor));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getStrokeSizeComputationExpression() {
        return strokeSizeComputationExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setStrokeSizeComputationExpression(String newStrokeSizeComputationExpression) {
        String oldStrokeSizeComputationExpression = strokeSizeComputationExpression;
        strokeSizeComputationExpression = newStrokeSizeComputationExpression;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DOT__STROKE_SIZE_COMPUTATION_EXPRESSION, oldStrokeSizeComputationExpression, strokeSizeComputationExpression));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DiagramPackage.DOT__BACKGROUND_COLOR:
            return basicSetBackgroundColor(null, msgs);
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
        case DiagramPackage.DOT__BACKGROUND_COLOR:
            if (resolve)
                return getBackgroundColor();
            return basicGetBackgroundColor();
        case DiagramPackage.DOT__STROKE_SIZE_COMPUTATION_EXPRESSION:
            return getStrokeSizeComputationExpression();
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
        case DiagramPackage.DOT__BACKGROUND_COLOR:
            setBackgroundColor((RGBValues) newValue);
            return;
        case DiagramPackage.DOT__STROKE_SIZE_COMPUTATION_EXPRESSION:
            setStrokeSizeComputationExpression((String) newValue);
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
        case DiagramPackage.DOT__BACKGROUND_COLOR:
            setBackgroundColor((RGBValues) null);
            return;
        case DiagramPackage.DOT__STROKE_SIZE_COMPUTATION_EXPRESSION:
            setStrokeSizeComputationExpression(STROKE_SIZE_COMPUTATION_EXPRESSION_EDEFAULT);
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
        case DiagramPackage.DOT__BACKGROUND_COLOR:
            return backgroundColor != null;
        case DiagramPackage.DOT__STROKE_SIZE_COMPUTATION_EXPRESSION:
            return STROKE_SIZE_COMPUTATION_EXPRESSION_EDEFAULT == null ? strokeSizeComputationExpression != null : !STROKE_SIZE_COMPUTATION_EXPRESSION_EDEFAULT.equals(strokeSizeComputationExpression);
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
        result.append(" (strokeSizeComputationExpression: ");
        result.append(strokeSizeComputationExpression);
        result.append(')');
        return result.toString();
    }

} // DotImpl
