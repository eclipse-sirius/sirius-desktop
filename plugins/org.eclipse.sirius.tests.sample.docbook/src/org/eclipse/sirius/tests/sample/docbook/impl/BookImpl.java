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
import org.eclipse.sirius.tests.sample.docbook.Book;
import org.eclipse.sirius.tests.sample.docbook.Chapter;
import org.eclipse.sirius.tests.sample.docbook.DocbookPackage;
import org.eclipse.sirius.tests.sample.docbook.Info;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Book</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.sirius.tests.sample.docbook.impl.BookImpl#getBookinfo
 * <em>Bookinfo</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.docbook.impl.BookImpl#getChapter
 * <em>Chapter</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.docbook.impl.BookImpl#getId <em>Id
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.docbook.impl.BookImpl#getLang <em>
 * Lang</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.docbook.impl.BookImpl#getVersion
 * <em>Version</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BookImpl extends EObjectImpl implements Book {
    /**
     * The cached value of the '{@link #getBookinfo() <em>Bookinfo</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getBookinfo()
     * @generated
     * @ordered
     */
    protected Info bookinfo;

    /**
     * The cached value of the '{@link #getChapter() <em>Chapter</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getChapter()
     * @generated
     * @ordered
     */
    protected EList<Chapter> chapter;

    /**
     * The default value of the '{@link #getId() <em>Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getId()
     * @generated
     * @ordered
     */
    protected static final String ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getId() <em>Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getId()
     * @generated
     * @ordered
     */
    protected String id = BookImpl.ID_EDEFAULT;

    /**
     * The default value of the '{@link #getLang() <em>Lang</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getLang()
     * @generated
     * @ordered
     */
    protected static final String LANG_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLang() <em>Lang</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getLang()
     * @generated
     * @ordered
     */
    protected String lang = BookImpl.LANG_EDEFAULT;

    /**
     * The default value of the '{@link #getVersion() <em>Version</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getVersion()
     * @generated
     * @ordered
     */
    protected static final String VERSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getVersion() <em>Version</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getVersion()
     * @generated
     * @ordered
     */
    protected String version = BookImpl.VERSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected BookImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DocbookPackage.Literals.BOOK;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Info getBookinfo() {
        return bookinfo;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetBookinfo(Info newBookinfo, NotificationChain msgs) {
        Info oldBookinfo = bookinfo;
        bookinfo = newBookinfo;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DocbookPackage.BOOK__BOOKINFO, oldBookinfo, newBookinfo);
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
    public void setBookinfo(Info newBookinfo) {
        if (newBookinfo != bookinfo) {
            NotificationChain msgs = null;
            if (bookinfo != null) {
                msgs = ((InternalEObject) bookinfo).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DocbookPackage.BOOK__BOOKINFO, null, msgs);
            }
            if (newBookinfo != null) {
                msgs = ((InternalEObject) newBookinfo).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DocbookPackage.BOOK__BOOKINFO, null, msgs);
            }
            msgs = basicSetBookinfo(newBookinfo, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DocbookPackage.BOOK__BOOKINFO, newBookinfo, newBookinfo));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Chapter> getChapter() {
        if (chapter == null) {
            chapter = new EObjectContainmentEList<Chapter>(Chapter.class, this, DocbookPackage.BOOK__CHAPTER);
        }
        return chapter;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setId(String newId) {
        String oldId = id;
        id = newId;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DocbookPackage.BOOK__ID, oldId, id));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getLang() {
        return lang;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setLang(String newLang) {
        String oldLang = lang;
        lang = newLang;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DocbookPackage.BOOK__LANG, oldLang, lang));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getVersion() {
        return version;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setVersion(String newVersion) {
        String oldVersion = version;
        version = newVersion;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DocbookPackage.BOOK__VERSION, oldVersion, version));
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
        case DocbookPackage.BOOK__BOOKINFO:
            return basicSetBookinfo(null, msgs);
        case DocbookPackage.BOOK__CHAPTER:
            return ((InternalEList<?>) getChapter()).basicRemove(otherEnd, msgs);
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
        case DocbookPackage.BOOK__BOOKINFO:
            return getBookinfo();
        case DocbookPackage.BOOK__CHAPTER:
            return getChapter();
        case DocbookPackage.BOOK__ID:
            return getId();
        case DocbookPackage.BOOK__LANG:
            return getLang();
        case DocbookPackage.BOOK__VERSION:
            return getVersion();
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
        case DocbookPackage.BOOK__BOOKINFO:
            setBookinfo((Info) newValue);
            return;
        case DocbookPackage.BOOK__CHAPTER:
            getChapter().clear();
            getChapter().addAll((Collection<? extends Chapter>) newValue);
            return;
        case DocbookPackage.BOOK__ID:
            setId((String) newValue);
            return;
        case DocbookPackage.BOOK__LANG:
            setLang((String) newValue);
            return;
        case DocbookPackage.BOOK__VERSION:
            setVersion((String) newValue);
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
        case DocbookPackage.BOOK__BOOKINFO:
            setBookinfo((Info) null);
            return;
        case DocbookPackage.BOOK__CHAPTER:
            getChapter().clear();
            return;
        case DocbookPackage.BOOK__ID:
            setId(BookImpl.ID_EDEFAULT);
            return;
        case DocbookPackage.BOOK__LANG:
            setLang(BookImpl.LANG_EDEFAULT);
            return;
        case DocbookPackage.BOOK__VERSION:
            setVersion(BookImpl.VERSION_EDEFAULT);
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
        case DocbookPackage.BOOK__BOOKINFO:
            return bookinfo != null;
        case DocbookPackage.BOOK__CHAPTER:
            return chapter != null && !chapter.isEmpty();
        case DocbookPackage.BOOK__ID:
            return BookImpl.ID_EDEFAULT == null ? id != null : !BookImpl.ID_EDEFAULT.equals(id);
        case DocbookPackage.BOOK__LANG:
            return BookImpl.LANG_EDEFAULT == null ? lang != null : !BookImpl.LANG_EDEFAULT.equals(lang);
        case DocbookPackage.BOOK__VERSION:
            return BookImpl.VERSION_EDEFAULT == null ? version != null : !BookImpl.VERSION_EDEFAULT.equals(version);
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
        result.append(" (id: "); //$NON-NLS-1$
        result.append(id);
        result.append(", lang: "); //$NON-NLS-1$
        result.append(lang);
        result.append(", version: "); //$NON-NLS-1$
        result.append(version);
        result.append(')');
        return result.toString();
    }

} // BookImpl
