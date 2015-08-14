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
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.BasicLabelStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Color;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.FontFormat;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Basic Label Style</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.BasicLabelStyleImpl#getLabelSize
 * <em>Label Size</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.BasicLabelStyleImpl#getLabelFormat
 * <em>Label Format</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.BasicLabelStyleImpl#isShowIcon
 * <em>Show Icon</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.BasicLabelStyleImpl#getLabelColor
 * <em>Label Color</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.BasicLabelStyleImpl#getIconPath
 * <em>Icon Path</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BasicLabelStyleImpl extends EObjectImpl implements BasicLabelStyle {
    /**
     * The default value of the '{@link #getLabelSize() <em>Label Size</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getLabelSize()
     * @generated
     * @ordered
     */
    protected static final int LABEL_SIZE_EDEFAULT = 8;

    /**
     * The cached value of the '{@link #getLabelSize() <em>Label Size</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getLabelSize()
     * @generated
     * @ordered
     */
    protected int labelSize = BasicLabelStyleImpl.LABEL_SIZE_EDEFAULT;

    /**
     * The default value of the '{@link #getLabelFormat() <em>Label Format</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getLabelFormat()
     * @generated
     * @ordered
     */
    protected static final FontFormat LABEL_FORMAT_EDEFAULT = FontFormat.NORMAL;

    /**
     * The cached value of the '{@link #getLabelFormat() <em>Label Format</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getLabelFormat()
     * @generated
     * @ordered
     */
    protected FontFormat labelFormat = BasicLabelStyleImpl.LABEL_FORMAT_EDEFAULT;

    /**
     * The default value of the '{@link #isShowIcon() <em>Show Icon</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isShowIcon()
     * @generated
     * @ordered
     */
    protected static final boolean SHOW_ICON_EDEFAULT = true;

    /**
     * The cached value of the '{@link #isShowIcon() <em>Show Icon</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isShowIcon()
     * @generated
     * @ordered
     */
    protected boolean showIcon = BasicLabelStyleImpl.SHOW_ICON_EDEFAULT;

    /**
     * The cached value of the '{@link #getLabelColor() <em>Label Color</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getLabelColor()
     * @generated
     * @ordered
     */
    protected Color labelColor;

    /**
     * The default value of the '{@link #getIconPath() <em>Icon Path</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getIconPath()
     * @generated
     * @ordered
     */
    protected static final String ICON_PATH_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getIconPath() <em>Icon Path</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getIconPath()
     * @generated
     * @ordered
     */
    protected String iconPath = BasicLabelStyleImpl.ICON_PATH_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected BasicLabelStyleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return MigrationmodelerPackage.Literals.BASIC_LABEL_STYLE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int getLabelSize() {
        return labelSize;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setLabelSize(int newLabelSize) {
        int oldLabelSize = labelSize;
        labelSize = newLabelSize;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.BASIC_LABEL_STYLE__LABEL_SIZE, oldLabelSize, labelSize));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FontFormat getLabelFormat() {
        return labelFormat;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setLabelFormat(FontFormat newLabelFormat) {
        FontFormat oldLabelFormat = labelFormat;
        labelFormat = newLabelFormat == null ? BasicLabelStyleImpl.LABEL_FORMAT_EDEFAULT : newLabelFormat;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.BASIC_LABEL_STYLE__LABEL_FORMAT, oldLabelFormat, labelFormat));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isShowIcon() {
        return showIcon;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setShowIcon(boolean newShowIcon) {
        boolean oldShowIcon = showIcon;
        showIcon = newShowIcon;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.BASIC_LABEL_STYLE__SHOW_ICON, oldShowIcon, showIcon));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Color getLabelColor() {
        return labelColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetLabelColor(Color newLabelColor, NotificationChain msgs) {
        Color oldLabelColor = labelColor;
        labelColor = newLabelColor;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.BASIC_LABEL_STYLE__LABEL_COLOR, oldLabelColor, newLabelColor);
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
    public void setLabelColor(Color newLabelColor) {
        if (newLabelColor != labelColor) {
            NotificationChain msgs = null;
            if (labelColor != null) {
                msgs = ((InternalEObject) labelColor).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - MigrationmodelerPackage.BASIC_LABEL_STYLE__LABEL_COLOR, null, msgs);
            }
            if (newLabelColor != null) {
                msgs = ((InternalEObject) newLabelColor).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - MigrationmodelerPackage.BASIC_LABEL_STYLE__LABEL_COLOR, null, msgs);
            }
            msgs = basicSetLabelColor(newLabelColor, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.BASIC_LABEL_STYLE__LABEL_COLOR, newLabelColor, newLabelColor));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getIconPath() {
        return iconPath;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setIconPath(String newIconPath) {
        String oldIconPath = iconPath;
        iconPath = newIconPath;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.BASIC_LABEL_STYLE__ICON_PATH, oldIconPath, iconPath));
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
        case MigrationmodelerPackage.BASIC_LABEL_STYLE__LABEL_COLOR:
            return basicSetLabelColor(null, msgs);
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
        case MigrationmodelerPackage.BASIC_LABEL_STYLE__LABEL_SIZE:
            return getLabelSize();
        case MigrationmodelerPackage.BASIC_LABEL_STYLE__LABEL_FORMAT:
            return getLabelFormat();
        case MigrationmodelerPackage.BASIC_LABEL_STYLE__SHOW_ICON:
            return isShowIcon();
        case MigrationmodelerPackage.BASIC_LABEL_STYLE__LABEL_COLOR:
            return getLabelColor();
        case MigrationmodelerPackage.BASIC_LABEL_STYLE__ICON_PATH:
            return getIconPath();
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
        case MigrationmodelerPackage.BASIC_LABEL_STYLE__LABEL_SIZE:
            setLabelSize((Integer) newValue);
            return;
        case MigrationmodelerPackage.BASIC_LABEL_STYLE__LABEL_FORMAT:
            setLabelFormat((FontFormat) newValue);
            return;
        case MigrationmodelerPackage.BASIC_LABEL_STYLE__SHOW_ICON:
            setShowIcon((Boolean) newValue);
            return;
        case MigrationmodelerPackage.BASIC_LABEL_STYLE__LABEL_COLOR:
            setLabelColor((Color) newValue);
            return;
        case MigrationmodelerPackage.BASIC_LABEL_STYLE__ICON_PATH:
            setIconPath((String) newValue);
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
        case MigrationmodelerPackage.BASIC_LABEL_STYLE__LABEL_SIZE:
            setLabelSize(BasicLabelStyleImpl.LABEL_SIZE_EDEFAULT);
            return;
        case MigrationmodelerPackage.BASIC_LABEL_STYLE__LABEL_FORMAT:
            setLabelFormat(BasicLabelStyleImpl.LABEL_FORMAT_EDEFAULT);
            return;
        case MigrationmodelerPackage.BASIC_LABEL_STYLE__SHOW_ICON:
            setShowIcon(BasicLabelStyleImpl.SHOW_ICON_EDEFAULT);
            return;
        case MigrationmodelerPackage.BASIC_LABEL_STYLE__LABEL_COLOR:
            setLabelColor((Color) null);
            return;
        case MigrationmodelerPackage.BASIC_LABEL_STYLE__ICON_PATH:
            setIconPath(BasicLabelStyleImpl.ICON_PATH_EDEFAULT);
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
        case MigrationmodelerPackage.BASIC_LABEL_STYLE__LABEL_SIZE:
            return labelSize != BasicLabelStyleImpl.LABEL_SIZE_EDEFAULT;
        case MigrationmodelerPackage.BASIC_LABEL_STYLE__LABEL_FORMAT:
            return labelFormat != BasicLabelStyleImpl.LABEL_FORMAT_EDEFAULT;
        case MigrationmodelerPackage.BASIC_LABEL_STYLE__SHOW_ICON:
            return showIcon != BasicLabelStyleImpl.SHOW_ICON_EDEFAULT;
        case MigrationmodelerPackage.BASIC_LABEL_STYLE__LABEL_COLOR:
            return labelColor != null;
        case MigrationmodelerPackage.BASIC_LABEL_STYLE__ICON_PATH:
            return BasicLabelStyleImpl.ICON_PATH_EDEFAULT == null ? iconPath != null : !BasicLabelStyleImpl.ICON_PATH_EDEFAULT.equals(iconPath);
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
        result.append(" (labelSize: "); //$NON-NLS-1$
        result.append(labelSize);
        result.append(", labelFormat: "); //$NON-NLS-1$
        result.append(labelFormat);
        result.append(", showIcon: "); //$NON-NLS-1$
        result.append(showIcon);
        result.append(", iconPath: "); //$NON-NLS-1$
        result.append(iconPath);
        result.append(')');
        return result.toString();
    }

} // BasicLabelStyleImpl
