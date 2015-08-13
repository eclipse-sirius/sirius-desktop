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
package org.eclipse.sirius.diagram.sequence.template.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.diagram.sequence.template.TLifelineStyle;
import org.eclipse.sirius.diagram.sequence.template.TemplatePackage;
import org.eclipse.sirius.viewpoint.description.ColorDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>TLifeline Style</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.impl.TLifelineStyleImpl#getLifelineWidthComputationExpression
 * <em>Lifeline Width Computation Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.impl.TLifelineStyleImpl#getLifelineColor
 * <em>Lifeline Color</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TLifelineStyleImpl extends TTransformerImpl implements TLifelineStyle {
    /**
     * The default value of the '
     * {@link #getLifelineWidthComputationExpression()
     * <em>Lifeline Width Computation Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getLifelineWidthComputationExpression()
     * @generated
     * @ordered
     */
    protected static final String LIFELINE_WIDTH_COMPUTATION_EXPRESSION_EDEFAULT = "0"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getLifelineWidthComputationExpression()
     * <em>Lifeline Width Computation Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getLifelineWidthComputationExpression()
     * @generated
     * @ordered
     */
    protected String lifelineWidthComputationExpression = TLifelineStyleImpl.LIFELINE_WIDTH_COMPUTATION_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getLifelineColor()
     * <em>Lifeline Color</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getLifelineColor()
     * @generated
     * @ordered
     */
    protected ColorDescription lifelineColor;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected TLifelineStyleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TemplatePackage.Literals.TLIFELINE_STYLE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getLifelineWidthComputationExpression() {
        return lifelineWidthComputationExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setLifelineWidthComputationExpression(String newLifelineWidthComputationExpression) {
        String oldLifelineWidthComputationExpression = lifelineWidthComputationExpression;
        lifelineWidthComputationExpression = newLifelineWidthComputationExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TLIFELINE_STYLE__LIFELINE_WIDTH_COMPUTATION_EXPRESSION, oldLifelineWidthComputationExpression,
                    lifelineWidthComputationExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ColorDescription getLifelineColor() {
        if (lifelineColor != null && lifelineColor.eIsProxy()) {
            InternalEObject oldLifelineColor = (InternalEObject) lifelineColor;
            lifelineColor = (ColorDescription) eResolveProxy(oldLifelineColor);
            if (lifelineColor != oldLifelineColor) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, TemplatePackage.TLIFELINE_STYLE__LIFELINE_COLOR, oldLifelineColor, lifelineColor));
                }
            }
        }
        return lifelineColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ColorDescription basicGetLifelineColor() {
        return lifelineColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setLifelineColor(ColorDescription newLifelineColor) {
        ColorDescription oldLifelineColor = lifelineColor;
        lifelineColor = newLifelineColor;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TLIFELINE_STYLE__LIFELINE_COLOR, oldLifelineColor, lifelineColor));
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
        case TemplatePackage.TLIFELINE_STYLE__LIFELINE_WIDTH_COMPUTATION_EXPRESSION:
            return getLifelineWidthComputationExpression();
        case TemplatePackage.TLIFELINE_STYLE__LIFELINE_COLOR:
            if (resolve) {
                return getLifelineColor();
            }
            return basicGetLifelineColor();
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
        case TemplatePackage.TLIFELINE_STYLE__LIFELINE_WIDTH_COMPUTATION_EXPRESSION:
            setLifelineWidthComputationExpression((String) newValue);
            return;
        case TemplatePackage.TLIFELINE_STYLE__LIFELINE_COLOR:
            setLifelineColor((ColorDescription) newValue);
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
        case TemplatePackage.TLIFELINE_STYLE__LIFELINE_WIDTH_COMPUTATION_EXPRESSION:
            setLifelineWidthComputationExpression(TLifelineStyleImpl.LIFELINE_WIDTH_COMPUTATION_EXPRESSION_EDEFAULT);
            return;
        case TemplatePackage.TLIFELINE_STYLE__LIFELINE_COLOR:
            setLifelineColor((ColorDescription) null);
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
        case TemplatePackage.TLIFELINE_STYLE__LIFELINE_WIDTH_COMPUTATION_EXPRESSION:
            return TLifelineStyleImpl.LIFELINE_WIDTH_COMPUTATION_EXPRESSION_EDEFAULT == null ? lifelineWidthComputationExpression != null
                    : !TLifelineStyleImpl.LIFELINE_WIDTH_COMPUTATION_EXPRESSION_EDEFAULT.equals(lifelineWidthComputationExpression);
        case TemplatePackage.TLIFELINE_STYLE__LIFELINE_COLOR:
            return lifelineColor != null;
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
        result.append(" (lifelineWidthComputationExpression: "); //$NON-NLS-1$
        result.append(lifelineWidthComputationExpression);
        result.append(')');
        return result.toString();
    }

} // TLifelineStyleImpl
