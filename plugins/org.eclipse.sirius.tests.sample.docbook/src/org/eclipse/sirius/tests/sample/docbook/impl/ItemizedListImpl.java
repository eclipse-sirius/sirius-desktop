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
import org.eclipse.sirius.tests.sample.docbook.DocbookPackage;
import org.eclipse.sirius.tests.sample.docbook.ItemizedList;
import org.eclipse.sirius.tests.sample.docbook.ListItem;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Itemized List</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.docbook.impl.ItemizedListImpl#getMark
 * <em>Mark</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.docbook.impl.ItemizedListImpl#getListitem
 * <em>Listitem</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ItemizedListImpl extends EObjectImpl implements ItemizedList {
    /**
     * The default value of the '{@link #getMark() <em>Mark</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMark()
     * @generated
     * @ordered
     */
    protected static final String MARK_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getMark() <em>Mark</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMark()
     * @generated
     * @ordered
     */
    protected String mark = ItemizedListImpl.MARK_EDEFAULT;

    /**
     * The cached value of the '{@link #getListitem() <em>Listitem</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getListitem()
     * @generated
     * @ordered
     */
    protected EList<ListItem> listitem;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ItemizedListImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DocbookPackage.Literals.ITEMIZED_LIST;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getMark() {
        return mark;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setMark(String newMark) {
        String oldMark = mark;
        mark = newMark;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DocbookPackage.ITEMIZED_LIST__MARK, oldMark, mark));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ListItem> getListitem() {
        if (listitem == null) {
            listitem = new EObjectContainmentEList<ListItem>(ListItem.class, this, DocbookPackage.ITEMIZED_LIST__LISTITEM);
        }
        return listitem;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DocbookPackage.ITEMIZED_LIST__LISTITEM:
            return ((InternalEList<?>) getListitem()).basicRemove(otherEnd, msgs);
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
        case DocbookPackage.ITEMIZED_LIST__MARK:
            return getMark();
        case DocbookPackage.ITEMIZED_LIST__LISTITEM:
            return getListitem();
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
        case DocbookPackage.ITEMIZED_LIST__MARK:
            setMark((String) newValue);
            return;
        case DocbookPackage.ITEMIZED_LIST__LISTITEM:
            getListitem().clear();
            getListitem().addAll((Collection<? extends ListItem>) newValue);
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
        case DocbookPackage.ITEMIZED_LIST__MARK:
            setMark(ItemizedListImpl.MARK_EDEFAULT);
            return;
        case DocbookPackage.ITEMIZED_LIST__LISTITEM:
            getListitem().clear();
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
        case DocbookPackage.ITEMIZED_LIST__MARK:
            return ItemizedListImpl.MARK_EDEFAULT == null ? mark != null : !ItemizedListImpl.MARK_EDEFAULT.equals(mark);
        case DocbookPackage.ITEMIZED_LIST__LISTITEM:
            return listitem != null && !listitem.isEmpty();
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
        result.append(" (mark: "); //$NON-NLS-1$
        result.append(mark);
        result.append(')');
        return result.toString();
    }

} // ItemizedListImpl
