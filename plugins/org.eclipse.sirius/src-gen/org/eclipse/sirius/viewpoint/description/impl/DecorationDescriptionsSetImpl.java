/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.viewpoint.description.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.viewpoint.description.DecorationDescription;
import org.eclipse.sirius.viewpoint.description.DecorationDescriptionsSet;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Decoration Descriptions Set</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.impl.DecorationDescriptionsSetImpl#getDecorationDescriptions
 * <em>Decoration Descriptions</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DecorationDescriptionsSetImpl extends MinimalEObjectImpl.Container implements DecorationDescriptionsSet {
    /**
     * The cached value of the '{@link #getDecorationDescriptions() <em>Decoration Descriptions</em>}' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDecorationDescriptions()
     * @generated
     * @ordered
     */
    protected EList<DecorationDescription> decorationDescriptions;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected DecorationDescriptionsSetImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.DECORATION_DESCRIPTIONS_SET;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DecorationDescription> getDecorationDescriptions() {
        if (decorationDescriptions == null) {
            decorationDescriptions = new EObjectContainmentEList.Resolving<DecorationDescription>(DecorationDescription.class, this,
                    DescriptionPackage.DECORATION_DESCRIPTIONS_SET__DECORATION_DESCRIPTIONS);
        }
        return decorationDescriptions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DescriptionPackage.DECORATION_DESCRIPTIONS_SET__DECORATION_DESCRIPTIONS:
            return ((InternalEList<?>) getDecorationDescriptions()).basicRemove(otherEnd, msgs);
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
        case DescriptionPackage.DECORATION_DESCRIPTIONS_SET__DECORATION_DESCRIPTIONS:
            return getDecorationDescriptions();
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
        case DescriptionPackage.DECORATION_DESCRIPTIONS_SET__DECORATION_DESCRIPTIONS:
            getDecorationDescriptions().clear();
            getDecorationDescriptions().addAll((Collection<? extends DecorationDescription>) newValue);
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
        case DescriptionPackage.DECORATION_DESCRIPTIONS_SET__DECORATION_DESCRIPTIONS:
            getDecorationDescriptions().clear();
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
        case DescriptionPackage.DECORATION_DESCRIPTIONS_SET__DECORATION_DESCRIPTIONS:
            return decorationDescriptions != null && !decorationDescriptions.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // DecorationDescriptionsSetImpl
