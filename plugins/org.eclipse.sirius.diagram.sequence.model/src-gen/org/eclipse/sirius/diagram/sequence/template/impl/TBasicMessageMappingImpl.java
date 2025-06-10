/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.template.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.sirius.diagram.sequence.template.TBasicMessageMapping;
import org.eclipse.sirius.diagram.sequence.template.TMessageExtremity;
import org.eclipse.sirius.diagram.sequence.template.TemplatePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>TBasic Message Mapping</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.sequence.template.impl.TBasicMessageMappingImpl#getTarget <em>Target</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.template.impl.TBasicMessageMappingImpl#isOblique
 * <em>Oblique</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TBasicMessageMappingImpl extends TSourceTargetMessageMappingImpl implements TBasicMessageMapping {
    /**
     * The cached value of the '{@link #getTarget() <em>Target</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getTarget()
     * @generated
     * @ordered
     */
    protected EList<TMessageExtremity> target;

    /**
     * The default value of the '{@link #isOblique() <em>Oblique</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isOblique()
     * @generated
     * @ordered
     */
    protected static final boolean OBLIQUE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isOblique() <em>Oblique</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isOblique()
     * @generated
     * @ordered
     */
    protected boolean oblique = TBasicMessageMappingImpl.OBLIQUE_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected TBasicMessageMappingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TemplatePackage.Literals.TBASIC_MESSAGE_MAPPING;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<TMessageExtremity> getTarget() {
        if (target == null) {
            target = new EObjectResolvingEList<>(TMessageExtremity.class, this, TemplatePackage.TBASIC_MESSAGE_MAPPING__TARGET);
        }
        return target;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isOblique() {
        return oblique;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setOblique(boolean newOblique) {
        boolean oldOblique = oblique;
        oblique = newOblique;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TBASIC_MESSAGE_MAPPING__OBLIQUE, oldOblique, oblique));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case TemplatePackage.TBASIC_MESSAGE_MAPPING__TARGET:
            return getTarget();
        case TemplatePackage.TBASIC_MESSAGE_MAPPING__OBLIQUE:
            return isOblique();
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
        case TemplatePackage.TBASIC_MESSAGE_MAPPING__TARGET:
            getTarget().clear();
            getTarget().addAll((Collection<? extends TMessageExtremity>) newValue);
            return;
        case TemplatePackage.TBASIC_MESSAGE_MAPPING__OBLIQUE:
            setOblique((Boolean) newValue);
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
        case TemplatePackage.TBASIC_MESSAGE_MAPPING__TARGET:
            getTarget().clear();
            return;
        case TemplatePackage.TBASIC_MESSAGE_MAPPING__OBLIQUE:
            setOblique(TBasicMessageMappingImpl.OBLIQUE_EDEFAULT);
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
        case TemplatePackage.TBASIC_MESSAGE_MAPPING__TARGET:
            return target != null && !target.isEmpty();
        case TemplatePackage.TBASIC_MESSAGE_MAPPING__OBLIQUE:
            return oblique != TBasicMessageMappingImpl.OBLIQUE_EDEFAULT;
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
        result.append(" (oblique: "); //$NON-NLS-1$
        result.append(oblique);
        result.append(')');
        return result.toString();
    }

} // TBasicMessageMappingImpl
