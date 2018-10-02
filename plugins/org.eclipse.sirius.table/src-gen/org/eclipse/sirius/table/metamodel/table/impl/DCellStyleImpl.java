/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.metamodel.table.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.table.metamodel.table.DCellStyle;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.metamodel.table.description.TableMapping;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>DCell Style</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.impl.DCellStyleImpl#getForegroundStyleOrigin <em>Foreground Style
 * Origin</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.impl.DCellStyleImpl#getBackgroundStyleOrigin <em>Background Style
 * Origin</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DCellStyleImpl extends DTableElementStyleImpl implements DCellStyle {
    /**
     * The cached value of the '{@link #getForegroundStyleOrigin() <em>Foreground Style Origin</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getForegroundStyleOrigin()
     * @generated
     * @ordered
     */
    protected TableMapping foregroundStyleOrigin;

    /**
     * The cached value of the '{@link #getBackgroundStyleOrigin() <em>Background Style Origin</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getBackgroundStyleOrigin()
     * @generated
     * @ordered
     */
    protected TableMapping backgroundStyleOrigin;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected DCellStyleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TablePackage.Literals.DCELL_STYLE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public TableMapping getForegroundStyleOrigin() {
        if (foregroundStyleOrigin != null && foregroundStyleOrigin.eIsProxy()) {
            InternalEObject oldForegroundStyleOrigin = (InternalEObject) foregroundStyleOrigin;
            foregroundStyleOrigin = (TableMapping) eResolveProxy(oldForegroundStyleOrigin);
            if (foregroundStyleOrigin != oldForegroundStyleOrigin) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, TablePackage.DCELL_STYLE__FOREGROUND_STYLE_ORIGIN, oldForegroundStyleOrigin, foregroundStyleOrigin));
                }
            }
        }
        return foregroundStyleOrigin;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public TableMapping basicGetForegroundStyleOrigin() {
        return foregroundStyleOrigin;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setForegroundStyleOrigin(TableMapping newForegroundStyleOrigin) {
        TableMapping oldForegroundStyleOrigin = foregroundStyleOrigin;
        foregroundStyleOrigin = newForegroundStyleOrigin;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TablePackage.DCELL_STYLE__FOREGROUND_STYLE_ORIGIN, oldForegroundStyleOrigin, foregroundStyleOrigin));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public TableMapping getBackgroundStyleOrigin() {
        if (backgroundStyleOrigin != null && backgroundStyleOrigin.eIsProxy()) {
            InternalEObject oldBackgroundStyleOrigin = (InternalEObject) backgroundStyleOrigin;
            backgroundStyleOrigin = (TableMapping) eResolveProxy(oldBackgroundStyleOrigin);
            if (backgroundStyleOrigin != oldBackgroundStyleOrigin) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, TablePackage.DCELL_STYLE__BACKGROUND_STYLE_ORIGIN, oldBackgroundStyleOrigin, backgroundStyleOrigin));
                }
            }
        }
        return backgroundStyleOrigin;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public TableMapping basicGetBackgroundStyleOrigin() {
        return backgroundStyleOrigin;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setBackgroundStyleOrigin(TableMapping newBackgroundStyleOrigin) {
        TableMapping oldBackgroundStyleOrigin = backgroundStyleOrigin;
        backgroundStyleOrigin = newBackgroundStyleOrigin;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TablePackage.DCELL_STYLE__BACKGROUND_STYLE_ORIGIN, oldBackgroundStyleOrigin, backgroundStyleOrigin));
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
        case TablePackage.DCELL_STYLE__FOREGROUND_STYLE_ORIGIN:
            if (resolve) {
                return getForegroundStyleOrigin();
            }
            return basicGetForegroundStyleOrigin();
        case TablePackage.DCELL_STYLE__BACKGROUND_STYLE_ORIGIN:
            if (resolve) {
                return getBackgroundStyleOrigin();
            }
            return basicGetBackgroundStyleOrigin();
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
        case TablePackage.DCELL_STYLE__FOREGROUND_STYLE_ORIGIN:
            setForegroundStyleOrigin((TableMapping) newValue);
            return;
        case TablePackage.DCELL_STYLE__BACKGROUND_STYLE_ORIGIN:
            setBackgroundStyleOrigin((TableMapping) newValue);
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
        case TablePackage.DCELL_STYLE__FOREGROUND_STYLE_ORIGIN:
            setForegroundStyleOrigin((TableMapping) null);
            return;
        case TablePackage.DCELL_STYLE__BACKGROUND_STYLE_ORIGIN:
            setBackgroundStyleOrigin((TableMapping) null);
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
        case TablePackage.DCELL_STYLE__FOREGROUND_STYLE_ORIGIN:
            return foregroundStyleOrigin != null;
        case TablePackage.DCELL_STYLE__BACKGROUND_STYLE_ORIGIN:
            return backgroundStyleOrigin != null;
        }
        return super.eIsSet(featureID);
    }

} // DCellStyleImpl
