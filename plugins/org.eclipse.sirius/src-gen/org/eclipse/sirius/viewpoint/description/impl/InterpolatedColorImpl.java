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
package org.eclipse.sirius.viewpoint.description.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.viewpoint.description.ColorStep;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.InterpolatedColor;
import org.eclipse.sirius.viewpoint.description.UserColor;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Interpolated Color</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.InterpolatedColorImpl#getName
 * <em>Name</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.InterpolatedColorImpl#getColorValueComputationExpression
 * <em>Color Value Computation Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.InterpolatedColorImpl#getMinValueComputationExpression
 * <em>Min Value Computation Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.InterpolatedColorImpl#getMaxValueComputationExpression
 * <em>Max Value Computation Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.InterpolatedColorImpl#getColorSteps
 * <em>Color Steps</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InterpolatedColorImpl extends ColorDescriptionImpl implements InterpolatedColor {
    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = InterpolatedColorImpl.NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getColorValueComputationExpression()
     * <em>Color Value Computation Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getColorValueComputationExpression()
     * @generated
     * @ordered
     */
    protected static final String COLOR_VALUE_COMPUTATION_EXPRESSION_EDEFAULT = "1"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getColorValueComputationExpression()
     * <em>Color Value Computation Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getColorValueComputationExpression()
     * @generated
     * @ordered
     */
    protected String colorValueComputationExpression = InterpolatedColorImpl.COLOR_VALUE_COMPUTATION_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getMinValueComputationExpression()
     * <em>Min Value Computation Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMinValueComputationExpression()
     * @generated
     * @ordered
     */
    protected static final String MIN_VALUE_COMPUTATION_EXPRESSION_EDEFAULT = "0"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getMinValueComputationExpression()
     * <em>Min Value Computation Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMinValueComputationExpression()
     * @generated
     * @ordered
     */
    protected String minValueComputationExpression = InterpolatedColorImpl.MIN_VALUE_COMPUTATION_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getMaxValueComputationExpression()
     * <em>Max Value Computation Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMaxValueComputationExpression()
     * @generated
     * @ordered
     */
    protected static final String MAX_VALUE_COMPUTATION_EXPRESSION_EDEFAULT = "10"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getMaxValueComputationExpression()
     * <em>Max Value Computation Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMaxValueComputationExpression()
     * @generated
     * @ordered
     */
    protected String maxValueComputationExpression = InterpolatedColorImpl.MAX_VALUE_COMPUTATION_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getColorSteps() <em>Color Steps</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getColorSteps()
     * @generated
     * @ordered
     */
    protected EList<ColorStep> colorSteps;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected InterpolatedColorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.INTERPOLATED_COLOR;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.INTERPOLATED_COLOR__NAME, oldName, name));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getColorValueComputationExpression() {
        return colorValueComputationExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setColorValueComputationExpression(String newColorValueComputationExpression) {
        String oldColorValueComputationExpression = colorValueComputationExpression;
        colorValueComputationExpression = newColorValueComputationExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.INTERPOLATED_COLOR__COLOR_VALUE_COMPUTATION_EXPRESSION, oldColorValueComputationExpression,
                    colorValueComputationExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getMinValueComputationExpression() {
        return minValueComputationExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setMinValueComputationExpression(String newMinValueComputationExpression) {
        String oldMinValueComputationExpression = minValueComputationExpression;
        minValueComputationExpression = newMinValueComputationExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.INTERPOLATED_COLOR__MIN_VALUE_COMPUTATION_EXPRESSION, oldMinValueComputationExpression,
                    minValueComputationExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getMaxValueComputationExpression() {
        return maxValueComputationExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setMaxValueComputationExpression(String newMaxValueComputationExpression) {
        String oldMaxValueComputationExpression = maxValueComputationExpression;
        maxValueComputationExpression = newMaxValueComputationExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.INTERPOLATED_COLOR__MAX_VALUE_COMPUTATION_EXPRESSION, oldMaxValueComputationExpression,
                    maxValueComputationExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<ColorStep> getColorSteps() {
        if (colorSteps == null) {
            colorSteps = new EObjectContainmentEList.Resolving<ColorStep>(ColorStep.class, this, DescriptionPackage.INTERPOLATED_COLOR__COLOR_STEPS);
        }
        return colorSteps;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DescriptionPackage.INTERPOLATED_COLOR__COLOR_STEPS:
            return ((InternalEList<?>) getColorSteps()).basicRemove(otherEnd, msgs);
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
        case DescriptionPackage.INTERPOLATED_COLOR__NAME:
            return getName();
        case DescriptionPackage.INTERPOLATED_COLOR__COLOR_VALUE_COMPUTATION_EXPRESSION:
            return getColorValueComputationExpression();
        case DescriptionPackage.INTERPOLATED_COLOR__MIN_VALUE_COMPUTATION_EXPRESSION:
            return getMinValueComputationExpression();
        case DescriptionPackage.INTERPOLATED_COLOR__MAX_VALUE_COMPUTATION_EXPRESSION:
            return getMaxValueComputationExpression();
        case DescriptionPackage.INTERPOLATED_COLOR__COLOR_STEPS:
            return getColorSteps();
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
        case DescriptionPackage.INTERPOLATED_COLOR__NAME:
            setName((String) newValue);
            return;
        case DescriptionPackage.INTERPOLATED_COLOR__COLOR_VALUE_COMPUTATION_EXPRESSION:
            setColorValueComputationExpression((String) newValue);
            return;
        case DescriptionPackage.INTERPOLATED_COLOR__MIN_VALUE_COMPUTATION_EXPRESSION:
            setMinValueComputationExpression((String) newValue);
            return;
        case DescriptionPackage.INTERPOLATED_COLOR__MAX_VALUE_COMPUTATION_EXPRESSION:
            setMaxValueComputationExpression((String) newValue);
            return;
        case DescriptionPackage.INTERPOLATED_COLOR__COLOR_STEPS:
            getColorSteps().clear();
            getColorSteps().addAll((Collection<? extends ColorStep>) newValue);
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
        case DescriptionPackage.INTERPOLATED_COLOR__NAME:
            setName(InterpolatedColorImpl.NAME_EDEFAULT);
            return;
        case DescriptionPackage.INTERPOLATED_COLOR__COLOR_VALUE_COMPUTATION_EXPRESSION:
            setColorValueComputationExpression(InterpolatedColorImpl.COLOR_VALUE_COMPUTATION_EXPRESSION_EDEFAULT);
            return;
        case DescriptionPackage.INTERPOLATED_COLOR__MIN_VALUE_COMPUTATION_EXPRESSION:
            setMinValueComputationExpression(InterpolatedColorImpl.MIN_VALUE_COMPUTATION_EXPRESSION_EDEFAULT);
            return;
        case DescriptionPackage.INTERPOLATED_COLOR__MAX_VALUE_COMPUTATION_EXPRESSION:
            setMaxValueComputationExpression(InterpolatedColorImpl.MAX_VALUE_COMPUTATION_EXPRESSION_EDEFAULT);
            return;
        case DescriptionPackage.INTERPOLATED_COLOR__COLOR_STEPS:
            getColorSteps().clear();
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
        case DescriptionPackage.INTERPOLATED_COLOR__NAME:
            return InterpolatedColorImpl.NAME_EDEFAULT == null ? name != null : !InterpolatedColorImpl.NAME_EDEFAULT.equals(name);
        case DescriptionPackage.INTERPOLATED_COLOR__COLOR_VALUE_COMPUTATION_EXPRESSION:
            return InterpolatedColorImpl.COLOR_VALUE_COMPUTATION_EXPRESSION_EDEFAULT == null ? colorValueComputationExpression != null
                    : !InterpolatedColorImpl.COLOR_VALUE_COMPUTATION_EXPRESSION_EDEFAULT.equals(colorValueComputationExpression);
        case DescriptionPackage.INTERPOLATED_COLOR__MIN_VALUE_COMPUTATION_EXPRESSION:
            return InterpolatedColorImpl.MIN_VALUE_COMPUTATION_EXPRESSION_EDEFAULT == null ? minValueComputationExpression != null : !InterpolatedColorImpl.MIN_VALUE_COMPUTATION_EXPRESSION_EDEFAULT
                    .equals(minValueComputationExpression);
        case DescriptionPackage.INTERPOLATED_COLOR__MAX_VALUE_COMPUTATION_EXPRESSION:
            return InterpolatedColorImpl.MAX_VALUE_COMPUTATION_EXPRESSION_EDEFAULT == null ? maxValueComputationExpression != null : !InterpolatedColorImpl.MAX_VALUE_COMPUTATION_EXPRESSION_EDEFAULT
                    .equals(maxValueComputationExpression);
        case DescriptionPackage.INTERPOLATED_COLOR__COLOR_STEPS:
            return colorSteps != null && !colorSteps.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == UserColor.class) {
            switch (derivedFeatureID) {
            case DescriptionPackage.INTERPOLATED_COLOR__NAME:
                return DescriptionPackage.USER_COLOR__NAME;
            default:
                return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if (baseClass == UserColor.class) {
            switch (baseFeatureID) {
            case DescriptionPackage.USER_COLOR__NAME:
                return DescriptionPackage.INTERPOLATED_COLOR__NAME;
            default:
                return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
        result.append(" (name: "); //$NON-NLS-1$
        result.append(name);
        result.append(", colorValueComputationExpression: "); //$NON-NLS-1$
        result.append(colorValueComputationExpression);
        result.append(", minValueComputationExpression: "); //$NON-NLS-1$
        result.append(minValueComputationExpression);
        result.append(", maxValueComputationExpression: "); //$NON-NLS-1$
        result.append(maxValueComputationExpression);
        result.append(')');
        return result.toString();
    }

} // InterpolatedColorImpl
