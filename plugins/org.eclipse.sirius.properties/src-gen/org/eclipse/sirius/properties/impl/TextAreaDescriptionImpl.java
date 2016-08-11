/**
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.properties.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.TextAreaDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Text Area Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.TextAreaDescriptionImpl#getLineCount
 * <em>Line Count</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TextAreaDescriptionImpl extends TextDescriptionImpl implements TextAreaDescription {
    /**
     * The default value of the '{@link #getLineCount() <em>Line Count</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getLineCount()
     * @generated
     * @ordered
     */
    protected static final int LINE_COUNT_EDEFAULT = 5;

    /**
     * The cached value of the '{@link #getLineCount() <em>Line Count</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getLineCount()
     * @generated
     * @ordered
     */
    protected int lineCount = TextAreaDescriptionImpl.LINE_COUNT_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected TextAreaDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.TEXT_AREA_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int getLineCount() {
        return lineCount;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setLineCount(int newLineCount) {
        int oldLineCount = lineCount;
        lineCount = newLineCount;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.TEXT_AREA_DESCRIPTION__LINE_COUNT, oldLineCount, lineCount));
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
        case PropertiesPackage.TEXT_AREA_DESCRIPTION__LINE_COUNT:
            return getLineCount();
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
        case PropertiesPackage.TEXT_AREA_DESCRIPTION__LINE_COUNT:
            setLineCount((Integer) newValue);
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
        case PropertiesPackage.TEXT_AREA_DESCRIPTION__LINE_COUNT:
            setLineCount(TextAreaDescriptionImpl.LINE_COUNT_EDEFAULT);
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
        case PropertiesPackage.TEXT_AREA_DESCRIPTION__LINE_COUNT:
            return lineCount != TextAreaDescriptionImpl.LINE_COUNT_EDEFAULT;
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
        result.append(" (lineCount: "); //$NON-NLS-1$
        result.append(lineCount);
        result.append(')');
        return result.toString();
    }

} // TextAreaDescriptionImpl
