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
package org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.LabelAlignment;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.LabelStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Label Style</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.LabelStyleImpl#getLabelAlignment
 * <em>Label Alignment</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LabelStyleImpl extends BasicLabelStyleImpl implements LabelStyle {
    /**
     * The default value of the '{@link #getLabelAlignment()
     * <em>Label Alignment</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getLabelAlignment()
     * @generated
     * @ordered
     */
    protected static final LabelAlignment LABEL_ALIGNMENT_EDEFAULT = LabelAlignment.CENTER;

    /**
     * The cached value of the '{@link #getLabelAlignment()
     * <em>Label Alignment</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getLabelAlignment()
     * @generated
     * @ordered
     */
    protected LabelAlignment labelAlignment = LabelStyleImpl.LABEL_ALIGNMENT_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected LabelStyleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return MigrationmodelerPackage.Literals.LABEL_STYLE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public LabelAlignment getLabelAlignment() {
        return labelAlignment;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setLabelAlignment(LabelAlignment newLabelAlignment) {
        LabelAlignment oldLabelAlignment = labelAlignment;
        labelAlignment = newLabelAlignment == null ? LabelStyleImpl.LABEL_ALIGNMENT_EDEFAULT : newLabelAlignment;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.LABEL_STYLE__LABEL_ALIGNMENT, oldLabelAlignment, labelAlignment));
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
        case MigrationmodelerPackage.LABEL_STYLE__LABEL_ALIGNMENT:
            return getLabelAlignment();
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
        case MigrationmodelerPackage.LABEL_STYLE__LABEL_ALIGNMENT:
            setLabelAlignment((LabelAlignment) newValue);
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
        case MigrationmodelerPackage.LABEL_STYLE__LABEL_ALIGNMENT:
            setLabelAlignment(LabelStyleImpl.LABEL_ALIGNMENT_EDEFAULT);
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
        case MigrationmodelerPackage.LABEL_STYLE__LABEL_ALIGNMENT:
            return labelAlignment != LabelStyleImpl.LABEL_ALIGNMENT_EDEFAULT;
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
        result.append(" (labelAlignment: "); //$NON-NLS-1$
        result.append(labelAlignment);
        result.append(')');
        return result.toString();
    }

} // LabelStyleImpl
