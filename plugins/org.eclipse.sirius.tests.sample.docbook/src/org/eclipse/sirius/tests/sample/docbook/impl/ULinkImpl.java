/*******************************************************************************
 * Copyright (c) 2013, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.sample.docbook.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.sirius.tests.sample.docbook.DocbookPackage;
import org.eclipse.sirius.tests.sample.docbook.ULink;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>ULink</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.sirius.tests.sample.docbook.impl.ULinkImpl#getUrl <em>
 * Url</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.docbook.impl.ULinkImpl#getData
 * <em>Data</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ULinkImpl extends EObjectImpl implements ULink {
    /**
     * The default value of the '{@link #getUrl() <em>Url</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getUrl()
     * @generated
     * @ordered
     */
    protected static final String URL_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getUrl() <em>Url</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getUrl()
     * @generated
     * @ordered
     */
    protected String url = ULinkImpl.URL_EDEFAULT;

    /**
     * The default value of the '{@link #getData() <em>Data</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getData()
     * @generated
     * @ordered
     */
    protected static final String DATA_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getData() <em>Data</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getData()
     * @generated
     * @ordered
     */
    protected String data = ULinkImpl.DATA_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ULinkImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DocbookPackage.Literals.ULINK;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getUrl() {
        return url;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setUrl(String newUrl) {
        String oldUrl = url;
        url = newUrl;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DocbookPackage.ULINK__URL, oldUrl, url));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getData() {
        return data;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setData(String newData) {
        String oldData = data;
        data = newData;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DocbookPackage.ULINK__DATA, oldData, data));
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
        case DocbookPackage.ULINK__URL:
            return getUrl();
        case DocbookPackage.ULINK__DATA:
            return getData();
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
        case DocbookPackage.ULINK__URL:
            setUrl((String) newValue);
            return;
        case DocbookPackage.ULINK__DATA:
            setData((String) newValue);
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
        case DocbookPackage.ULINK__URL:
            setUrl(ULinkImpl.URL_EDEFAULT);
            return;
        case DocbookPackage.ULINK__DATA:
            setData(ULinkImpl.DATA_EDEFAULT);
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
        case DocbookPackage.ULINK__URL:
            return ULinkImpl.URL_EDEFAULT == null ? url != null : !ULinkImpl.URL_EDEFAULT.equals(url);
        case DocbookPackage.ULINK__DATA:
            return ULinkImpl.DATA_EDEFAULT == null ? data != null : !ULinkImpl.DATA_EDEFAULT.equals(data);
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
        result.append(" (url: "); //$NON-NLS-1$
        result.append(url);
        result.append(", data: "); //$NON-NLS-1$
        result.append(data);
        result.append(')');
        return result.toString();
    }

} // ULinkImpl
