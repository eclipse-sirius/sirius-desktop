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
package org.eclipse.sirius.viewpoint.description.style.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.sirius.viewpoint.description.style.StylePackage;
import org.eclipse.sirius.viewpoint.description.style.TooltipStyleDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Tooltip Style Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.style.impl.TooltipStyleDescriptionImpl#getTooltipExpression
 * <em>Tooltip Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TooltipStyleDescriptionImpl extends MinimalEObjectImpl.Container implements TooltipStyleDescription {
    /**
     * The default value of the '{@link #getTooltipExpression()
     * <em>Tooltip Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getTooltipExpression()
     * @generated
     * @ordered
     */
    protected static final String TOOLTIP_EXPRESSION_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getTooltipExpression()
     * <em>Tooltip Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getTooltipExpression()
     * @generated
     * @ordered
     */
    protected String tooltipExpression = TooltipStyleDescriptionImpl.TOOLTIP_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected TooltipStyleDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return StylePackage.Literals.TOOLTIP_STYLE_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getTooltipExpression() {
        return tooltipExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setTooltipExpression(String newTooltipExpression) {
        String oldTooltipExpression = tooltipExpression;
        tooltipExpression = newTooltipExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.TOOLTIP_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION, oldTooltipExpression, tooltipExpression));
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
        case StylePackage.TOOLTIP_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION:
            return getTooltipExpression();
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
        case StylePackage.TOOLTIP_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION:
            setTooltipExpression((String) newValue);
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
        case StylePackage.TOOLTIP_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION:
            setTooltipExpression(TooltipStyleDescriptionImpl.TOOLTIP_EXPRESSION_EDEFAULT);
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
        case StylePackage.TOOLTIP_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION:
            return TooltipStyleDescriptionImpl.TOOLTIP_EXPRESSION_EDEFAULT == null ? tooltipExpression != null : !TooltipStyleDescriptionImpl.TOOLTIP_EXPRESSION_EDEFAULT.equals(tooltipExpression);
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
        result.append(" (tooltipExpression: "); //$NON-NLS-1$
        result.append(tooltipExpression);
        result.append(')');
        return result.toString();
    }

} // TooltipStyleDescriptionImpl
