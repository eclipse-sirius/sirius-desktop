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
import org.eclipse.sirius.tests.sample.docbook.Author;
import org.eclipse.sirius.tests.sample.docbook.DocbookPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Author</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.sirius.tests.sample.docbook.impl.AuthorImpl#getEmail
 * <em>Email</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.docbook.impl.AuthorImpl#getPersonname
 * <em>Personname</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.docbook.impl.AuthorImpl#getAddress
 * <em>Address</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class AuthorImpl extends EObjectImpl implements Author {
    /**
     * The default value of the '{@link #getEmail() <em>Email</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getEmail()
     * @generated
     * @ordered
     */
    protected static final String EMAIL_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getEmail() <em>Email</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getEmail()
     * @generated
     * @ordered
     */
    protected String email = EMAIL_EDEFAULT;

    /**
     * The default value of the '{@link #getPersonname() <em>Personname</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getPersonname()
     * @generated
     * @ordered
     */
    protected static final String PERSONNAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPersonname() <em>Personname</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getPersonname()
     * @generated
     * @ordered
     */
    protected String personname = PERSONNAME_EDEFAULT;

    /**
     * The default value of the '{@link #getAddress() <em>Address</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getAddress()
     * @generated
     * @ordered
     */
    protected static final String ADDRESS_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getAddress() <em>Address</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getAddress()
     * @generated
     * @ordered
     */
    protected String address = ADDRESS_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected AuthorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DocbookPackage.Literals.AUTHOR;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getEmail() {
        return email;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setEmail(String newEmail) {
        String oldEmail = email;
        email = newEmail;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DocbookPackage.AUTHOR__EMAIL, oldEmail, email));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getPersonname() {
        return personname;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setPersonname(String newPersonname) {
        String oldPersonname = personname;
        personname = newPersonname;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DocbookPackage.AUTHOR__PERSONNAME, oldPersonname, personname));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getAddress() {
        return address;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setAddress(String newAddress) {
        String oldAddress = address;
        address = newAddress;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DocbookPackage.AUTHOR__ADDRESS, oldAddress, address));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case DocbookPackage.AUTHOR__EMAIL:
            return getEmail();
        case DocbookPackage.AUTHOR__PERSONNAME:
            return getPersonname();
        case DocbookPackage.AUTHOR__ADDRESS:
            return getAddress();
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
        case DocbookPackage.AUTHOR__EMAIL:
            setEmail((String) newValue);
            return;
        case DocbookPackage.AUTHOR__PERSONNAME:
            setPersonname((String) newValue);
            return;
        case DocbookPackage.AUTHOR__ADDRESS:
            setAddress((String) newValue);
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
        case DocbookPackage.AUTHOR__EMAIL:
            setEmail(EMAIL_EDEFAULT);
            return;
        case DocbookPackage.AUTHOR__PERSONNAME:
            setPersonname(PERSONNAME_EDEFAULT);
            return;
        case DocbookPackage.AUTHOR__ADDRESS:
            setAddress(ADDRESS_EDEFAULT);
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
        case DocbookPackage.AUTHOR__EMAIL:
            return EMAIL_EDEFAULT == null ? email != null : !EMAIL_EDEFAULT.equals(email);
        case DocbookPackage.AUTHOR__PERSONNAME:
            return PERSONNAME_EDEFAULT == null ? personname != null : !PERSONNAME_EDEFAULT.equals(personname);
        case DocbookPackage.AUTHOR__ADDRESS:
            return ADDRESS_EDEFAULT == null ? address != null : !ADDRESS_EDEFAULT.equals(address);
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
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (email: ");
        result.append(email);
        result.append(", personname: ");
        result.append(personname);
        result.append(", address: ");
        result.append(address);
        result.append(')');
        return result.toString();
    }

} // AuthorImpl
