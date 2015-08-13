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
package org.eclipse.sirius.table.metamodel.table.description.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.sirius.table.metamodel.table.description.ForegroundStyleDescription;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.description.ColorDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Foreground Style Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.ForegroundStyleDescriptionImpl#getLabelSize
 * <em>Label Size</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.ForegroundStyleDescriptionImpl#getLabelFormat
 * <em>Label Format</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.ForegroundStyleDescriptionImpl#getForeGroundColor
 * <em>Fore Ground Color</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ForegroundStyleDescriptionImpl extends MinimalEObjectImpl.Container implements ForegroundStyleDescription {

    /**
     * The default value of the '{@link #getLabelSize() <em>Label Size</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getLabelSize()
     * @generated
     * @ordered
     */
    protected static final int LABEL_SIZE_EDEFAULT = 12;

    /**
     * The cached value of the '{@link #getLabelSize() <em>Label Size</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getLabelSize()
     * @generated
     * @ordered
     */
    protected int labelSize = ForegroundStyleDescriptionImpl.LABEL_SIZE_EDEFAULT;

    /**
     * The cached value of the '{@link #getLabelFormat() <em>Label Format</em>}'
     * attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getLabelFormat()
     * @generated
     * @ordered
     */
    protected EList<FontFormat> labelFormat;

    /**
     * The cached value of the '{@link #getForeGroundColor()
     * <em>Fore Ground Color</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getForeGroundColor()
     * @generated
     * @ordered
     */
    protected ColorDescription foreGroundColor;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ForegroundStyleDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.FOREGROUND_STYLE_DESCRIPTION;
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.FOREGROUND_STYLE_DESCRIPTION__LABEL_SIZE, oldLabelSize, labelSize));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<FontFormat> getLabelFormat() {
        if (labelFormat == null) {
            labelFormat = new EDataTypeUniqueEList<FontFormat>(FontFormat.class, this, DescriptionPackage.FOREGROUND_STYLE_DESCRIPTION__LABEL_FORMAT);
        }
        return labelFormat;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ColorDescription getForeGroundColor() {
        if (foreGroundColor != null && foreGroundColor.eIsProxy()) {
            InternalEObject oldForeGroundColor = (InternalEObject) foreGroundColor;
            foreGroundColor = (ColorDescription) eResolveProxy(oldForeGroundColor);
            if (foreGroundColor != oldForeGroundColor) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DescriptionPackage.FOREGROUND_STYLE_DESCRIPTION__FORE_GROUND_COLOR, oldForeGroundColor, foreGroundColor));
                }
            }
        }
        return foreGroundColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ColorDescription basicGetForeGroundColor() {
        return foreGroundColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setForeGroundColor(ColorDescription newForeGroundColor) {
        ColorDescription oldForeGroundColor = foreGroundColor;
        foreGroundColor = newForeGroundColor;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.FOREGROUND_STYLE_DESCRIPTION__FORE_GROUND_COLOR, oldForeGroundColor, foreGroundColor));
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
        case DescriptionPackage.FOREGROUND_STYLE_DESCRIPTION__LABEL_SIZE:
            return getLabelSize();
        case DescriptionPackage.FOREGROUND_STYLE_DESCRIPTION__LABEL_FORMAT:
            return getLabelFormat();
        case DescriptionPackage.FOREGROUND_STYLE_DESCRIPTION__FORE_GROUND_COLOR:
            if (resolve) {
                return getForeGroundColor();
            }
            return basicGetForeGroundColor();
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
        case DescriptionPackage.FOREGROUND_STYLE_DESCRIPTION__LABEL_SIZE:
            setLabelSize((Integer) newValue);
            return;
        case DescriptionPackage.FOREGROUND_STYLE_DESCRIPTION__LABEL_FORMAT:
            getLabelFormat().clear();
            getLabelFormat().addAll((Collection<? extends FontFormat>) newValue);
            return;
        case DescriptionPackage.FOREGROUND_STYLE_DESCRIPTION__FORE_GROUND_COLOR:
            setForeGroundColor((ColorDescription) newValue);
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
        case DescriptionPackage.FOREGROUND_STYLE_DESCRIPTION__LABEL_SIZE:
            setLabelSize(ForegroundStyleDescriptionImpl.LABEL_SIZE_EDEFAULT);
            return;
        case DescriptionPackage.FOREGROUND_STYLE_DESCRIPTION__LABEL_FORMAT:
            getLabelFormat().clear();
            return;
        case DescriptionPackage.FOREGROUND_STYLE_DESCRIPTION__FORE_GROUND_COLOR:
            setForeGroundColor((ColorDescription) null);
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
        case DescriptionPackage.FOREGROUND_STYLE_DESCRIPTION__LABEL_SIZE:
            return labelSize != ForegroundStyleDescriptionImpl.LABEL_SIZE_EDEFAULT;
        case DescriptionPackage.FOREGROUND_STYLE_DESCRIPTION__LABEL_FORMAT:
            return labelFormat != null && !labelFormat.isEmpty();
        case DescriptionPackage.FOREGROUND_STYLE_DESCRIPTION__FORE_GROUND_COLOR:
            return foreGroundColor != null;
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
        result.append(')');
        return result.toString();
    }
} // ForegroundStyleDescriptionImpl
