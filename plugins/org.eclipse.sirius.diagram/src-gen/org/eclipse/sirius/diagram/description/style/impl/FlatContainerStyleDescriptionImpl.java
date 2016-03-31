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
package org.eclipse.sirius.diagram.description.style.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.diagram.BackgroundStyle;
import org.eclipse.sirius.diagram.description.style.FlatContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.SizeComputationContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.viewpoint.description.ColorDescription;
import org.eclipse.sirius.viewpoint.description.style.LabelBorderStyleDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Flat Container Style Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.FlatContainerStyleDescriptionImpl#getWidthComputationExpression
 * <em>Width Computation Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.FlatContainerStyleDescriptionImpl#getHeightComputationExpression
 * <em>Height Computation Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.FlatContainerStyleDescriptionImpl#getBackgroundStyle
 * <em>Background Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.FlatContainerStyleDescriptionImpl#getBackgroundColor
 * <em>Background Color</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.FlatContainerStyleDescriptionImpl#getForegroundColor
 * <em>Foreground Color</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.FlatContainerStyleDescriptionImpl#getLabelBorderStyle
 * <em>Label Border Style</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FlatContainerStyleDescriptionImpl extends ContainerStyleDescriptionImpl implements FlatContainerStyleDescription {
    /**
     * The default value of the '{@link #getWidthComputationExpression()
     * <em>Width Computation Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getWidthComputationExpression()
     * @generated
     * @ordered
     */
    protected static final String WIDTH_COMPUTATION_EXPRESSION_EDEFAULT = "-1"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getWidthComputationExpression()
     * <em>Width Computation Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getWidthComputationExpression()
     * @generated
     * @ordered
     */
    protected String widthComputationExpression = FlatContainerStyleDescriptionImpl.WIDTH_COMPUTATION_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getHeightComputationExpression()
     * <em>Height Computation Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getHeightComputationExpression()
     * @generated
     * @ordered
     */
    protected static final String HEIGHT_COMPUTATION_EXPRESSION_EDEFAULT = "-1"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getHeightComputationExpression()
     * <em>Height Computation Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getHeightComputationExpression()
     * @generated
     * @ordered
     */
    protected String heightComputationExpression = FlatContainerStyleDescriptionImpl.HEIGHT_COMPUTATION_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getBackgroundStyle()
     * <em>Background Style</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getBackgroundStyle()
     * @generated
     * @ordered
     */
    protected static final BackgroundStyle BACKGROUND_STYLE_EDEFAULT = BackgroundStyle.GRADIENT_LEFT_TO_RIGHT_LITERAL;

    /**
     * The cached value of the '{@link #getBackgroundStyle()
     * <em>Background Style</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getBackgroundStyle()
     * @generated
     * @ordered
     */
    protected BackgroundStyle backgroundStyle = FlatContainerStyleDescriptionImpl.BACKGROUND_STYLE_EDEFAULT;

    /**
     * The cached value of the '{@link #getBackgroundColor()
     * <em>Background Color</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getBackgroundColor()
     * @generated
     * @ordered
     */
    protected ColorDescription backgroundColor;

    /**
     * The cached value of the '{@link #getForegroundColor()
     * <em>Foreground Color</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getForegroundColor()
     * @generated
     * @ordered
     */
    protected ColorDescription foregroundColor;

    /**
     * The cached value of the '{@link #getLabelBorderStyle()
     * <em>Label Border Style</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getLabelBorderStyle()
     * @generated
     * @ordered
     */
    protected LabelBorderStyleDescription labelBorderStyle;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected FlatContainerStyleDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return StylePackage.Literals.FLAT_CONTAINER_STYLE_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getWidthComputationExpression() {
        return widthComputationExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setWidthComputationExpression(String newWidthComputationExpression) {
        String oldWidthComputationExpression = widthComputationExpression;
        widthComputationExpression = newWidthComputationExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__WIDTH_COMPUTATION_EXPRESSION, oldWidthComputationExpression,
                    widthComputationExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getHeightComputationExpression() {
        return heightComputationExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setHeightComputationExpression(String newHeightComputationExpression) {
        String oldHeightComputationExpression = heightComputationExpression;
        heightComputationExpression = newHeightComputationExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__HEIGHT_COMPUTATION_EXPRESSION, oldHeightComputationExpression,
                    heightComputationExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public BackgroundStyle getBackgroundStyle() {
        return backgroundStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setBackgroundStyle(BackgroundStyle newBackgroundStyle) {
        BackgroundStyle oldBackgroundStyle = backgroundStyle;
        backgroundStyle = newBackgroundStyle == null ? FlatContainerStyleDescriptionImpl.BACKGROUND_STYLE_EDEFAULT : newBackgroundStyle;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__BACKGROUND_STYLE, oldBackgroundStyle, backgroundStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ColorDescription getBackgroundColor() {
        if (backgroundColor != null && backgroundColor.eIsProxy()) {
            InternalEObject oldBackgroundColor = (InternalEObject) backgroundColor;
            backgroundColor = (ColorDescription) eResolveProxy(oldBackgroundColor);
            if (backgroundColor != oldBackgroundColor) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__BACKGROUND_COLOR, oldBackgroundColor, backgroundColor));
                }
            }
        }
        return backgroundColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ColorDescription basicGetBackgroundColor() {
        return backgroundColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setBackgroundColor(ColorDescription newBackgroundColor) {
        ColorDescription oldBackgroundColor = backgroundColor;
        backgroundColor = newBackgroundColor;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__BACKGROUND_COLOR, oldBackgroundColor, backgroundColor));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ColorDescription getForegroundColor() {
        if (foregroundColor != null && foregroundColor.eIsProxy()) {
            InternalEObject oldForegroundColor = (InternalEObject) foregroundColor;
            foregroundColor = (ColorDescription) eResolveProxy(oldForegroundColor);
            if (foregroundColor != oldForegroundColor) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__FOREGROUND_COLOR, oldForegroundColor, foregroundColor));
                }
            }
        }
        return foregroundColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ColorDescription basicGetForegroundColor() {
        return foregroundColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setForegroundColor(ColorDescription newForegroundColor) {
        ColorDescription oldForegroundColor = foregroundColor;
        foregroundColor = newForegroundColor;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__FOREGROUND_COLOR, oldForegroundColor, foregroundColor));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public LabelBorderStyleDescription getLabelBorderStyle() {
        if (labelBorderStyle != null && labelBorderStyle.eIsProxy()) {
            InternalEObject oldLabelBorderStyle = (InternalEObject) labelBorderStyle;
            labelBorderStyle = (LabelBorderStyleDescription) eResolveProxy(oldLabelBorderStyle);
            if (labelBorderStyle != oldLabelBorderStyle) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__LABEL_BORDER_STYLE, oldLabelBorderStyle, labelBorderStyle));
                }
            }
        }
        return labelBorderStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public LabelBorderStyleDescription basicGetLabelBorderStyle() {
        return labelBorderStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setLabelBorderStyle(LabelBorderStyleDescription newLabelBorderStyle) {
        LabelBorderStyleDescription oldLabelBorderStyle = labelBorderStyle;
        labelBorderStyle = newLabelBorderStyle;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__LABEL_BORDER_STYLE, oldLabelBorderStyle, labelBorderStyle));
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
        case StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__WIDTH_COMPUTATION_EXPRESSION:
            return getWidthComputationExpression();
        case StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__HEIGHT_COMPUTATION_EXPRESSION:
            return getHeightComputationExpression();
        case StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__BACKGROUND_STYLE:
            return getBackgroundStyle();
        case StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__BACKGROUND_COLOR:
            if (resolve) {
                return getBackgroundColor();
            }
            return basicGetBackgroundColor();
        case StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__FOREGROUND_COLOR:
            if (resolve) {
                return getForegroundColor();
            }
            return basicGetForegroundColor();
        case StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__LABEL_BORDER_STYLE:
            if (resolve) {
                return getLabelBorderStyle();
            }
            return basicGetLabelBorderStyle();
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
        case StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__WIDTH_COMPUTATION_EXPRESSION:
            setWidthComputationExpression((String) newValue);
            return;
        case StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__HEIGHT_COMPUTATION_EXPRESSION:
            setHeightComputationExpression((String) newValue);
            return;
        case StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__BACKGROUND_STYLE:
            setBackgroundStyle((BackgroundStyle) newValue);
            return;
        case StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__BACKGROUND_COLOR:
            setBackgroundColor((ColorDescription) newValue);
            return;
        case StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__FOREGROUND_COLOR:
            setForegroundColor((ColorDescription) newValue);
            return;
        case StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__LABEL_BORDER_STYLE:
            setLabelBorderStyle((LabelBorderStyleDescription) newValue);
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
        case StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__WIDTH_COMPUTATION_EXPRESSION:
            setWidthComputationExpression(FlatContainerStyleDescriptionImpl.WIDTH_COMPUTATION_EXPRESSION_EDEFAULT);
            return;
        case StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__HEIGHT_COMPUTATION_EXPRESSION:
            setHeightComputationExpression(FlatContainerStyleDescriptionImpl.HEIGHT_COMPUTATION_EXPRESSION_EDEFAULT);
            return;
        case StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__BACKGROUND_STYLE:
            setBackgroundStyle(FlatContainerStyleDescriptionImpl.BACKGROUND_STYLE_EDEFAULT);
            return;
        case StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__BACKGROUND_COLOR:
            setBackgroundColor((ColorDescription) null);
            return;
        case StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__FOREGROUND_COLOR:
            setForegroundColor((ColorDescription) null);
            return;
        case StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__LABEL_BORDER_STYLE:
            setLabelBorderStyle((LabelBorderStyleDescription) null);
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
        case StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__WIDTH_COMPUTATION_EXPRESSION:
            return FlatContainerStyleDescriptionImpl.WIDTH_COMPUTATION_EXPRESSION_EDEFAULT == null ? widthComputationExpression != null
                    : !FlatContainerStyleDescriptionImpl.WIDTH_COMPUTATION_EXPRESSION_EDEFAULT.equals(widthComputationExpression);
        case StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__HEIGHT_COMPUTATION_EXPRESSION:
            return FlatContainerStyleDescriptionImpl.HEIGHT_COMPUTATION_EXPRESSION_EDEFAULT == null ? heightComputationExpression != null
                    : !FlatContainerStyleDescriptionImpl.HEIGHT_COMPUTATION_EXPRESSION_EDEFAULT.equals(heightComputationExpression);
        case StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__BACKGROUND_STYLE:
            return backgroundStyle != FlatContainerStyleDescriptionImpl.BACKGROUND_STYLE_EDEFAULT;
        case StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__BACKGROUND_COLOR:
            return backgroundColor != null;
        case StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__FOREGROUND_COLOR:
            return foregroundColor != null;
        case StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__LABEL_BORDER_STYLE:
            return labelBorderStyle != null;
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
        if (baseClass == SizeComputationContainerStyleDescription.class) {
            switch (derivedFeatureID) {
            case StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__WIDTH_COMPUTATION_EXPRESSION:
                return StylePackage.SIZE_COMPUTATION_CONTAINER_STYLE_DESCRIPTION__WIDTH_COMPUTATION_EXPRESSION;
            case StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__HEIGHT_COMPUTATION_EXPRESSION:
                return StylePackage.SIZE_COMPUTATION_CONTAINER_STYLE_DESCRIPTION__HEIGHT_COMPUTATION_EXPRESSION;
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
        if (baseClass == SizeComputationContainerStyleDescription.class) {
            switch (baseFeatureID) {
            case StylePackage.SIZE_COMPUTATION_CONTAINER_STYLE_DESCRIPTION__WIDTH_COMPUTATION_EXPRESSION:
                return StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__WIDTH_COMPUTATION_EXPRESSION;
            case StylePackage.SIZE_COMPUTATION_CONTAINER_STYLE_DESCRIPTION__HEIGHT_COMPUTATION_EXPRESSION:
                return StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__HEIGHT_COMPUTATION_EXPRESSION;
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
        result.append(" (widthComputationExpression: "); //$NON-NLS-1$
        result.append(widthComputationExpression);
        result.append(", heightComputationExpression: "); //$NON-NLS-1$
        result.append(heightComputationExpression);
        result.append(", backgroundStyle: "); //$NON-NLS-1$
        result.append(backgroundStyle);
        result.append(')');
        return result.toString();
    }

} // FlatContainerStyleDescriptionImpl
