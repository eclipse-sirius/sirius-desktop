/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.description.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.diagram.sequence.description.BasicMessageMapping;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Basic Message Mapping</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.sequence.description.impl.BasicMessageMappingImpl#isOblique
 * <em>Oblique</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BasicMessageMappingImpl extends MessageMappingImpl implements BasicMessageMapping {
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
    protected boolean oblique = BasicMessageMappingImpl.OBLIQUE_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected BasicMessageMappingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.BASIC_MESSAGE_MAPPING;
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.BASIC_MESSAGE_MAPPING__OBLIQUE, oldOblique, oblique));
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
        case DescriptionPackage.BASIC_MESSAGE_MAPPING__OBLIQUE:
            return isOblique();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case DescriptionPackage.BASIC_MESSAGE_MAPPING__OBLIQUE:
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
        case DescriptionPackage.BASIC_MESSAGE_MAPPING__OBLIQUE:
            setOblique(BasicMessageMappingImpl.OBLIQUE_EDEFAULT);
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
        case DescriptionPackage.BASIC_MESSAGE_MAPPING__OBLIQUE:
            return oblique != BasicMessageMappingImpl.OBLIQUE_EDEFAULT;
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

} // BasicMessageMappingImpl
