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
package org.eclipse.sirius.viewpoint.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.viewpoint.DNodeList;
import org.eclipse.sirius.viewpoint.DNodeListElement;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>View Node List</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DNodeListImpl#getOwnedElements
 * <em>Owned Elements</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DNodeListImpl#getLineWidth <em>
 * Line Width</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class DNodeListImpl extends DDiagramElementContainerImpl implements DNodeList {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.\nAll rights reserved. This program and the accompanying materials\nare made available under the terms of the Eclipse Public License v1.0\nwhich accompanies this distribution, and is available at\nhttp://www.eclipse.org/legal/epl-v10.html\n\nContributors:\n   Obeo - initial API and implementation\n";

    /**
     * The cached value of the '{@link #getOwnedElements()
     * <em>Owned Elements</em>}' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getOwnedElements()
     * @generated
     * @ordered
     */
    protected EList<DNodeListElement> ownedElements;

    /**
     * The default value of the '{@link #getLineWidth() <em>Line Width</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getLineWidth()
     * @generated
     * @ordered
     */
    protected static final int LINE_WIDTH_EDEFAULT = 1;

    /**
     * The cached value of the '{@link #getLineWidth() <em>Line Width</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getLineWidth()
     * @generated
     * @ordered
     */
    protected int lineWidth = LINE_WIDTH_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected DNodeListImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */

    @Override
    protected EClass eStaticClass() {
        return ViewpointPackage.Literals.DNODE_LIST;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<DNodeListElement> getOwnedElements() {
        if (ownedElements == null) {
            ownedElements = new EObjectContainmentEList.Resolving<DNodeListElement>(DNodeListElement.class, this, ViewpointPackage.DNODE_LIST__OWNED_ELEMENTS);
        }
        return ownedElements;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public int getLineWidth() {
        return lineWidth;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setLineWidth(int newLineWidth) {
        int oldLineWidth = lineWidth;
        lineWidth = newLineWidth;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DNODE_LIST__LINE_WIDTH, oldLineWidth, lineWidth));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */

    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ViewpointPackage.DNODE_LIST__OWNED_ELEMENTS:
            return ((InternalEList<?>) getOwnedElements()).basicRemove(otherEnd, msgs);
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
        case ViewpointPackage.DNODE_LIST__OWNED_ELEMENTS:
            return getOwnedElements();
        case ViewpointPackage.DNODE_LIST__LINE_WIDTH:
            return getLineWidth();
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
        case ViewpointPackage.DNODE_LIST__OWNED_ELEMENTS:
            getOwnedElements().clear();
            getOwnedElements().addAll((Collection<? extends DNodeListElement>) newValue);
            return;
        case ViewpointPackage.DNODE_LIST__LINE_WIDTH:
            setLineWidth((Integer) newValue);
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
        case ViewpointPackage.DNODE_LIST__OWNED_ELEMENTS:
            getOwnedElements().clear();
            return;
        case ViewpointPackage.DNODE_LIST__LINE_WIDTH:
            setLineWidth(LINE_WIDTH_EDEFAULT);
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
        case ViewpointPackage.DNODE_LIST__OWNED_ELEMENTS:
            return ownedElements != null && !ownedElements.isEmpty();
        case ViewpointPackage.DNODE_LIST__LINE_WIDTH:
            return lineWidth != LINE_WIDTH_EDEFAULT;
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
        result.append(" (lineWidth: ");
        result.append(lineWidth);
        result.append(')');
        return result.toString();
    }

} // DNodeListImpl
