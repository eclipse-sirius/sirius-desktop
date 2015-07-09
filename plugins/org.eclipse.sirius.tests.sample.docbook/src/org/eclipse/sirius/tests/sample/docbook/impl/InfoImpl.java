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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.tests.sample.docbook.Author;
import org.eclipse.sirius.tests.sample.docbook.DocbookPackage;
import org.eclipse.sirius.tests.sample.docbook.Info;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Info</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.sirius.tests.sample.docbook.impl.InfoImpl#getAuthor
 * <em>Author</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.docbook.impl.InfoImpl#getDate <em>
 * Date</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.docbook.impl.InfoImpl#getPubdate
 * <em>Pubdate</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InfoImpl extends EObjectImpl implements Info {
    /**
     * The cached value of the '{@link #getAuthor() <em>Author</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getAuthor()
     * @generated
     * @ordered
     */
    protected EList<Author> author;

    /**
     * The default value of the '{@link #getDate() <em>Date</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDate()
     * @generated
     * @ordered
     */
    protected static final String DATE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDate() <em>Date</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDate()
     * @generated
     * @ordered
     */
    protected String date = InfoImpl.DATE_EDEFAULT;

    /**
     * The default value of the '{@link #getPubdate() <em>Pubdate</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getPubdate()
     * @generated
     * @ordered
     */
    protected static final String PUBDATE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPubdate() <em>Pubdate</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getPubdate()
     * @generated
     * @ordered
     */
    protected String pubdate = InfoImpl.PUBDATE_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected InfoImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DocbookPackage.Literals.INFO;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Author> getAuthor() {
        if (author == null) {
            author = new EObjectContainmentEList<Author>(Author.class, this, DocbookPackage.INFO__AUTHOR);
        }
        return author;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getDate() {
        return date;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setDate(String newDate) {
        String oldDate = date;
        date = newDate;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DocbookPackage.INFO__DATE, oldDate, date));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getPubdate() {
        return pubdate;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setPubdate(String newPubdate) {
        String oldPubdate = pubdate;
        pubdate = newPubdate;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DocbookPackage.INFO__PUBDATE, oldPubdate, pubdate));
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
        case DocbookPackage.INFO__AUTHOR:
            return ((InternalEList<?>) getAuthor()).basicRemove(otherEnd, msgs);
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
        case DocbookPackage.INFO__AUTHOR:
            return getAuthor();
        case DocbookPackage.INFO__DATE:
            return getDate();
        case DocbookPackage.INFO__PUBDATE:
            return getPubdate();
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
        case DocbookPackage.INFO__AUTHOR:
            getAuthor().clear();
            getAuthor().addAll((Collection<? extends Author>) newValue);
            return;
        case DocbookPackage.INFO__DATE:
            setDate((String) newValue);
            return;
        case DocbookPackage.INFO__PUBDATE:
            setPubdate((String) newValue);
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
        case DocbookPackage.INFO__AUTHOR:
            getAuthor().clear();
            return;
        case DocbookPackage.INFO__DATE:
            setDate(InfoImpl.DATE_EDEFAULT);
            return;
        case DocbookPackage.INFO__PUBDATE:
            setPubdate(InfoImpl.PUBDATE_EDEFAULT);
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
        case DocbookPackage.INFO__AUTHOR:
            return author != null && !author.isEmpty();
        case DocbookPackage.INFO__DATE:
            return InfoImpl.DATE_EDEFAULT == null ? date != null : !InfoImpl.DATE_EDEFAULT.equals(date);
        case DocbookPackage.INFO__PUBDATE:
            return InfoImpl.PUBDATE_EDEFAULT == null ? pubdate != null : !InfoImpl.PUBDATE_EDEFAULT.equals(pubdate);
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
        result.append(" (date: "); //$NON-NLS-1$
        result.append(date);
        result.append(", pubdate: "); //$NON-NLS-1$
        result.append(pubdate);
        result.append(')');
        return result.toString();
    }

} // InfoImpl
