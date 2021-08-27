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
package org.eclipse.sirius.viewpoint.description.style.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.viewpoint.description.style.LabelBorderStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.LabelBorderStyles;
import org.eclipse.sirius.viewpoint.description.style.StylePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Label Border Styles</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.style.impl.LabelBorderStylesImpl#getLabelBorderStyleDescriptions
 * <em>Label Border Style Descriptions</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LabelBorderStylesImpl extends MinimalEObjectImpl.Container implements LabelBorderStyles {
    /**
     * The cached value of the '{@link #getLabelBorderStyleDescriptions() <em>Label Border Style Descriptions</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getLabelBorderStyleDescriptions()
     * @generated
     * @ordered
     */
    protected EList<LabelBorderStyleDescription> labelBorderStyleDescriptions;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected LabelBorderStylesImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return StylePackage.Literals.LABEL_BORDER_STYLES;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<LabelBorderStyleDescription> getLabelBorderStyleDescriptions() {
        if (labelBorderStyleDescriptions == null) {
            labelBorderStyleDescriptions = new EObjectContainmentEList.Resolving<>(LabelBorderStyleDescription.class, this, StylePackage.LABEL_BORDER_STYLES__LABEL_BORDER_STYLE_DESCRIPTIONS);
        }
        return labelBorderStyleDescriptions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case StylePackage.LABEL_BORDER_STYLES__LABEL_BORDER_STYLE_DESCRIPTIONS:
            return ((InternalEList<?>) getLabelBorderStyleDescriptions()).basicRemove(otherEnd, msgs);
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
        case StylePackage.LABEL_BORDER_STYLES__LABEL_BORDER_STYLE_DESCRIPTIONS:
            return getLabelBorderStyleDescriptions();
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
        case StylePackage.LABEL_BORDER_STYLES__LABEL_BORDER_STYLE_DESCRIPTIONS:
            getLabelBorderStyleDescriptions().clear();
            getLabelBorderStyleDescriptions().addAll((Collection<? extends LabelBorderStyleDescription>) newValue);
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
        case StylePackage.LABEL_BORDER_STYLES__LABEL_BORDER_STYLE_DESCRIPTIONS:
            getLabelBorderStyleDescriptions().clear();
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
        case StylePackage.LABEL_BORDER_STYLES__LABEL_BORDER_STYLE_DESCRIPTIONS:
            return labelBorderStyleDescriptions != null && !labelBorderStyleDescriptions.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // LabelBorderStylesImpl
