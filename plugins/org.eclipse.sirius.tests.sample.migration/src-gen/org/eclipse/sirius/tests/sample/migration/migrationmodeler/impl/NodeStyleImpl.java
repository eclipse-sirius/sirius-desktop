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
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.BorderedStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Color;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.LabelPosition;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.NodeStyle;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Node Style</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.NodeStyleImpl#getBorderSize
 * <em>Border Size</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.NodeStyleImpl#getBorderColor
 * <em>Border Color</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.NodeStyleImpl#getLabelPosition
 * <em>Label Position</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.NodeStyleImpl#isHideLabelByDefault
 * <em>Hide Label By Default</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NodeStyleImpl extends LabelStyleImpl implements NodeStyle {
    /**
     * The default value of the '{@link #getBorderSize() <em>Border Size</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getBorderSize()
     * @generated
     * @ordered
     */
    protected static final int BORDER_SIZE_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getBorderSize() <em>Border Size</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getBorderSize()
     * @generated
     * @ordered
     */
    protected int borderSize = NodeStyleImpl.BORDER_SIZE_EDEFAULT;

    /**
     * The cached value of the '{@link #getBorderColor() <em>Border Color</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getBorderColor()
     * @generated
     * @ordered
     */
    protected Color borderColor;

    /**
     * The default value of the '{@link #getLabelPosition()
     * <em>Label Position</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getLabelPosition()
     * @generated
     * @ordered
     */
    protected static final LabelPosition LABEL_POSITION_EDEFAULT = LabelPosition.BORDER;

    /**
     * The cached value of the '{@link #getLabelPosition()
     * <em>Label Position</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getLabelPosition()
     * @generated
     * @ordered
     */
    protected LabelPosition labelPosition = NodeStyleImpl.LABEL_POSITION_EDEFAULT;

    /**
     * The default value of the '{@link #isHideLabelByDefault()
     * <em>Hide Label By Default</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isHideLabelByDefault()
     * @generated
     * @ordered
     */
    protected static final boolean HIDE_LABEL_BY_DEFAULT_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isHideLabelByDefault()
     * <em>Hide Label By Default</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isHideLabelByDefault()
     * @generated
     * @ordered
     */
    protected boolean hideLabelByDefault = NodeStyleImpl.HIDE_LABEL_BY_DEFAULT_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected NodeStyleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return MigrationmodelerPackage.Literals.NODE_STYLE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int getBorderSize() {
        return borderSize;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setBorderSize(int newBorderSize) {
        int oldBorderSize = borderSize;
        borderSize = newBorderSize;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.NODE_STYLE__BORDER_SIZE, oldBorderSize, borderSize));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Color getBorderColor() {
        return borderColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetBorderColor(Color newBorderColor, NotificationChain msgs) {
        Color oldBorderColor = borderColor;
        borderColor = newBorderColor;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.NODE_STYLE__BORDER_COLOR, oldBorderColor, newBorderColor);
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
    public void setBorderColor(Color newBorderColor) {
        if (newBorderColor != borderColor) {
            NotificationChain msgs = null;
            if (borderColor != null) {
                msgs = ((InternalEObject) borderColor).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - MigrationmodelerPackage.NODE_STYLE__BORDER_COLOR, null, msgs);
            }
            if (newBorderColor != null) {
                msgs = ((InternalEObject) newBorderColor).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - MigrationmodelerPackage.NODE_STYLE__BORDER_COLOR, null, msgs);
            }
            msgs = basicSetBorderColor(newBorderColor, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.NODE_STYLE__BORDER_COLOR, newBorderColor, newBorderColor));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public LabelPosition getLabelPosition() {
        return labelPosition;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setLabelPosition(LabelPosition newLabelPosition) {
        LabelPosition oldLabelPosition = labelPosition;
        labelPosition = newLabelPosition == null ? NodeStyleImpl.LABEL_POSITION_EDEFAULT : newLabelPosition;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.NODE_STYLE__LABEL_POSITION, oldLabelPosition, labelPosition));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isHideLabelByDefault() {
        return hideLabelByDefault;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setHideLabelByDefault(boolean newHideLabelByDefault) {
        boolean oldHideLabelByDefault = hideLabelByDefault;
        hideLabelByDefault = newHideLabelByDefault;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.NODE_STYLE__HIDE_LABEL_BY_DEFAULT, oldHideLabelByDefault, hideLabelByDefault));
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
        case MigrationmodelerPackage.NODE_STYLE__BORDER_COLOR:
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
        case MigrationmodelerPackage.NODE_STYLE__BORDER_SIZE:
            return getBorderSize();
        case MigrationmodelerPackage.NODE_STYLE__BORDER_COLOR:
            return getBorderColor();
        case MigrationmodelerPackage.NODE_STYLE__LABEL_POSITION:
            return getLabelPosition();
        case MigrationmodelerPackage.NODE_STYLE__HIDE_LABEL_BY_DEFAULT:
            return isHideLabelByDefault();
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
        case MigrationmodelerPackage.NODE_STYLE__BORDER_SIZE:
            setBorderSize((Integer) newValue);
            return;
        case MigrationmodelerPackage.NODE_STYLE__BORDER_COLOR:
            setBorderColor((Color) newValue);
            return;
        case MigrationmodelerPackage.NODE_STYLE__LABEL_POSITION:
            setLabelPosition((LabelPosition) newValue);
            return;
        case MigrationmodelerPackage.NODE_STYLE__HIDE_LABEL_BY_DEFAULT:
            setHideLabelByDefault((Boolean) newValue);
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
        case MigrationmodelerPackage.NODE_STYLE__BORDER_SIZE:
            setBorderSize(NodeStyleImpl.BORDER_SIZE_EDEFAULT);
            return;
        case MigrationmodelerPackage.NODE_STYLE__BORDER_COLOR:
            setBorderColor((Color) null);
            return;
        case MigrationmodelerPackage.NODE_STYLE__LABEL_POSITION:
            setLabelPosition(NodeStyleImpl.LABEL_POSITION_EDEFAULT);
            return;
        case MigrationmodelerPackage.NODE_STYLE__HIDE_LABEL_BY_DEFAULT:
            setHideLabelByDefault(NodeStyleImpl.HIDE_LABEL_BY_DEFAULT_EDEFAULT);
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
        case MigrationmodelerPackage.NODE_STYLE__BORDER_SIZE:
            return borderSize != NodeStyleImpl.BORDER_SIZE_EDEFAULT;
        case MigrationmodelerPackage.NODE_STYLE__BORDER_COLOR:
            return borderColor != null;
        case MigrationmodelerPackage.NODE_STYLE__LABEL_POSITION:
            return labelPosition != NodeStyleImpl.LABEL_POSITION_EDEFAULT;
        case MigrationmodelerPackage.NODE_STYLE__HIDE_LABEL_BY_DEFAULT:
            return hideLabelByDefault != NodeStyleImpl.HIDE_LABEL_BY_DEFAULT_EDEFAULT;
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
        if (baseClass == BorderedStyle.class) {
            switch (derivedFeatureID) {
            case MigrationmodelerPackage.NODE_STYLE__BORDER_SIZE:
                return MigrationmodelerPackage.BORDERED_STYLE__BORDER_SIZE;
            case MigrationmodelerPackage.NODE_STYLE__BORDER_COLOR:
                return MigrationmodelerPackage.BORDERED_STYLE__BORDER_COLOR;
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
        if (baseClass == BorderedStyle.class) {
            switch (baseFeatureID) {
            case MigrationmodelerPackage.BORDERED_STYLE__BORDER_SIZE:
                return MigrationmodelerPackage.NODE_STYLE__BORDER_SIZE;
            case MigrationmodelerPackage.BORDERED_STYLE__BORDER_COLOR:
                return MigrationmodelerPackage.NODE_STYLE__BORDER_COLOR;
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
        result.append(" (borderSize: "); //$NON-NLS-1$
        result.append(borderSize);
        result.append(", labelPosition: "); //$NON-NLS-1$
        result.append(labelPosition);
        result.append(", hideLabelByDefault: "); //$NON-NLS-1$
        result.append(hideLabelByDefault);
        result.append(')');
        return result.toString();
    }

} // NodeStyleImpl
