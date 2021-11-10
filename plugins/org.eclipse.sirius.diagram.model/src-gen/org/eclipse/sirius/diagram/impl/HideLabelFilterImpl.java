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
package org.eclipse.sirius.diagram.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.HideLabelFilter;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Hide Label Filter</b></em>'. <!-- end-user-doc
 * -->
 *
 * @generated
 */
public class HideLabelFilterImpl extends GraphicalFilterImpl implements HideLabelFilter {
    /**
     * The cached value of the '{@link #getHiddenLabels() <em>Hidden Labels</em>}' attribute list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getHiddenLabels()
     * @generated
     * @ordered
     */
    protected EList<Integer> hiddenLabels;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected HideLabelFilterImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DiagramPackage.Literals.HIDE_LABEL_FILTER;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Integer> getHiddenLabels() {
        if (hiddenLabels == null) {
            hiddenLabels = new EDataTypeUniqueEList<>(Integer.class, this, DiagramPackage.HIDE_LABEL_FILTER__HIDDEN_LABELS);
        }
        return hiddenLabels;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case DiagramPackage.HIDE_LABEL_FILTER__HIDDEN_LABELS:
            return getHiddenLabels();
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
        case DiagramPackage.HIDE_LABEL_FILTER__HIDDEN_LABELS:
            getHiddenLabels().clear();
            getHiddenLabels().addAll((Collection<? extends Integer>) newValue);
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
        case DiagramPackage.HIDE_LABEL_FILTER__HIDDEN_LABELS:
            getHiddenLabels().clear();
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
        case DiagramPackage.HIDE_LABEL_FILTER__HIDDEN_LABELS:
            return hiddenLabels != null && !hiddenLabels.isEmpty();
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

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (hiddenLabels: "); //$NON-NLS-1$
        result.append(hiddenLabels);
        result.append(')');
        return result.toString();
    }

} // HideLabelFilterImpl
