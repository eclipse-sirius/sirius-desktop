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
import org.eclipse.sirius.tests.sample.docbook.Chapter;
import org.eclipse.sirius.tests.sample.docbook.DocbookPackage;
import org.eclipse.sirius.tests.sample.docbook.Para;
import org.eclipse.sirius.tests.sample.docbook.Sect1;
import org.eclipse.sirius.tests.sample.docbook.Title;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Chapter</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.sirius.tests.sample.docbook.impl.ChapterImpl#getTitle
 * <em>Title</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.docbook.impl.ChapterImpl#getPara
 * <em>Para</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.docbook.impl.ChapterImpl#getSect1
 * <em>Sect1</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.docbook.impl.ChapterImpl#getId
 * <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ChapterImpl extends EObjectImpl implements Chapter {
    /**
     * The cached value of the '{@link #getTitle() <em>Title</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getTitle()
     * @generated
     * @ordered
     */
    protected Title title;

    /**
     * The cached value of the '{@link #getPara() <em>Para</em>}' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getPara()
     * @generated
     * @ordered
     */
    protected EList<Para> para;

    /**
     * The cached value of the '{@link #getSect1() <em>Sect1</em>}' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSect1()
     * @generated
     * @ordered
     */
    protected EList<Sect1> sect1;

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
    protected String id = ChapterImpl.ID_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ChapterImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DocbookPackage.Literals.CHAPTER;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Title getTitle() {
        return title;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetTitle(Title newTitle, NotificationChain msgs) {
        Title oldTitle = title;
        title = newTitle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DocbookPackage.CHAPTER__TITLE, oldTitle, newTitle);
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
    public void setTitle(Title newTitle) {
        if (newTitle != title) {
            NotificationChain msgs = null;
            if (title != null) {
                msgs = ((InternalEObject) title).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DocbookPackage.CHAPTER__TITLE, null, msgs);
            }
            if (newTitle != null) {
                msgs = ((InternalEObject) newTitle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DocbookPackage.CHAPTER__TITLE, null, msgs);
            }
            msgs = basicSetTitle(newTitle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DocbookPackage.CHAPTER__TITLE, newTitle, newTitle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Para> getPara() {
        if (para == null) {
            para = new EObjectContainmentEList<Para>(Para.class, this, DocbookPackage.CHAPTER__PARA);
        }
        return para;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Sect1> getSect1() {
        if (sect1 == null) {
            sect1 = new EObjectContainmentEList<Sect1>(Sect1.class, this, DocbookPackage.CHAPTER__SECT1);
        }
        return sect1;
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
            eNotify(new ENotificationImpl(this, Notification.SET, DocbookPackage.CHAPTER__ID, oldId, id));
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
        case DocbookPackage.CHAPTER__TITLE:
            return basicSetTitle(null, msgs);
        case DocbookPackage.CHAPTER__PARA:
            return ((InternalEList<?>) getPara()).basicRemove(otherEnd, msgs);
        case DocbookPackage.CHAPTER__SECT1:
            return ((InternalEList<?>) getSect1()).basicRemove(otherEnd, msgs);
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
        case DocbookPackage.CHAPTER__TITLE:
            return getTitle();
        case DocbookPackage.CHAPTER__PARA:
            return getPara();
        case DocbookPackage.CHAPTER__SECT1:
            return getSect1();
        case DocbookPackage.CHAPTER__ID:
            return getId();
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
        case DocbookPackage.CHAPTER__TITLE:
            setTitle((Title) newValue);
            return;
        case DocbookPackage.CHAPTER__PARA:
            getPara().clear();
            getPara().addAll((Collection<? extends Para>) newValue);
            return;
        case DocbookPackage.CHAPTER__SECT1:
            getSect1().clear();
            getSect1().addAll((Collection<? extends Sect1>) newValue);
            return;
        case DocbookPackage.CHAPTER__ID:
            setId((String) newValue);
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
        case DocbookPackage.CHAPTER__TITLE:
            setTitle((Title) null);
            return;
        case DocbookPackage.CHAPTER__PARA:
            getPara().clear();
            return;
        case DocbookPackage.CHAPTER__SECT1:
            getSect1().clear();
            return;
        case DocbookPackage.CHAPTER__ID:
            setId(ChapterImpl.ID_EDEFAULT);
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
        case DocbookPackage.CHAPTER__TITLE:
            return title != null;
        case DocbookPackage.CHAPTER__PARA:
            return para != null && !para.isEmpty();
        case DocbookPackage.CHAPTER__SECT1:
            return sect1 != null && !sect1.isEmpty();
        case DocbookPackage.CHAPTER__ID:
            return ChapterImpl.ID_EDEFAULT == null ? id != null : !ChapterImpl.ID_EDEFAULT.equals(id);
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
        result.append(')');
        return result.toString();
    }

} // ChapterImpl
