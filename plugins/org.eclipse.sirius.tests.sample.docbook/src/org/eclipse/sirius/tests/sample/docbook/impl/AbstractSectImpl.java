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
import org.eclipse.sirius.tests.sample.docbook.AbstractSect;
import org.eclipse.sirius.tests.sample.docbook.DocbookPackage;
import org.eclipse.sirius.tests.sample.docbook.Para;
import org.eclipse.sirius.tests.sample.docbook.Title;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Abstract Sect</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.docbook.impl.AbstractSectImpl#getTitle
 * <em>Title</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.docbook.impl.AbstractSectImpl#getPara
 * <em>Para</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractSectImpl extends EObjectImpl implements AbstractSect {
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
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected AbstractSectImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DocbookPackage.Literals.ABSTRACT_SECT;
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DocbookPackage.ABSTRACT_SECT__TITLE, oldTitle, newTitle);
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
                msgs = ((InternalEObject) title).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DocbookPackage.ABSTRACT_SECT__TITLE, null, msgs);
            }
            if (newTitle != null) {
                msgs = ((InternalEObject) newTitle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DocbookPackage.ABSTRACT_SECT__TITLE, null, msgs);
            }
            msgs = basicSetTitle(newTitle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DocbookPackage.ABSTRACT_SECT__TITLE, newTitle, newTitle));
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
            para = new EObjectContainmentEList<Para>(Para.class, this, DocbookPackage.ABSTRACT_SECT__PARA);
        }
        return para;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DocbookPackage.ABSTRACT_SECT__TITLE:
            return basicSetTitle(null, msgs);
        case DocbookPackage.ABSTRACT_SECT__PARA:
            return ((InternalEList<?>) getPara()).basicRemove(otherEnd, msgs);
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
        case DocbookPackage.ABSTRACT_SECT__TITLE:
            return getTitle();
        case DocbookPackage.ABSTRACT_SECT__PARA:
            return getPara();
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
        case DocbookPackage.ABSTRACT_SECT__TITLE:
            setTitle((Title) newValue);
            return;
        case DocbookPackage.ABSTRACT_SECT__PARA:
            getPara().clear();
            getPara().addAll((Collection<? extends Para>) newValue);
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
        case DocbookPackage.ABSTRACT_SECT__TITLE:
            setTitle((Title) null);
            return;
        case DocbookPackage.ABSTRACT_SECT__PARA:
            getPara().clear();
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
        case DocbookPackage.ABSTRACT_SECT__TITLE:
            return title != null;
        case DocbookPackage.ABSTRACT_SECT__PARA:
            return para != null && !para.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // AbstractSectImpl
