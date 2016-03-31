/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.diagram.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.DiagramPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>DNode List</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.impl.DNodeListImpl#getOwnedElements
 * <em>Owned Elements</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DNodeListImpl extends DDiagramElementContainerImpl implements DNodeList {
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
        return DiagramPackage.Literals.DNODE_LIST;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DNodeListElement> getOwnedElements() {
        if (ownedElements == null) {
            ownedElements = new EObjectContainmentEList.Resolving<DNodeListElement>(DNodeListElement.class, this, DiagramPackage.DNODE_LIST__OWNED_ELEMENTS);
        }
        return ownedElements;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DiagramPackage.DNODE_LIST__OWNED_ELEMENTS:
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
        case DiagramPackage.DNODE_LIST__OWNED_ELEMENTS:
            return getOwnedElements();
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
        case DiagramPackage.DNODE_LIST__OWNED_ELEMENTS:
            getOwnedElements().clear();
            getOwnedElements().addAll((Collection<? extends DNodeListElement>) newValue);
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
        case DiagramPackage.DNODE_LIST__OWNED_ELEMENTS:
            getOwnedElements().clear();
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
        case DiagramPackage.DNODE_LIST__OWNED_ELEMENTS:
            return ownedElements != null && !ownedElements.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // DNodeListImpl
