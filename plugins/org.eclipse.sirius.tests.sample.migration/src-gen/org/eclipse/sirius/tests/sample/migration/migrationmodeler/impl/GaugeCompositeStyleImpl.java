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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.AlignmentKind;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeCompositeStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeSection;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Gauge Composite Style</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.GaugeCompositeStyleImpl#getAlignment
 * <em>Alignment</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.GaugeCompositeStyleImpl#getSections
 * <em>Sections</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GaugeCompositeStyleImpl extends NodeStyleImpl implements GaugeCompositeStyle {
    /**
     * The default value of the '{@link #getAlignment() <em>Alignment</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getAlignment()
     * @generated
     * @ordered
     */
    protected static final AlignmentKind ALIGNMENT_EDEFAULT = AlignmentKind.SQUARE;

    /**
     * The cached value of the '{@link #getAlignment() <em>Alignment</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getAlignment()
     * @generated
     * @ordered
     */
    protected AlignmentKind alignment = GaugeCompositeStyleImpl.ALIGNMENT_EDEFAULT;

    /**
     * The cached value of the '{@link #getSections() <em>Sections</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSections()
     * @generated
     * @ordered
     */
    protected EList<GaugeSection> sections;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected GaugeCompositeStyleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return MigrationmodelerPackage.Literals.GAUGE_COMPOSITE_STYLE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public AlignmentKind getAlignment() {
        return alignment;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setAlignment(AlignmentKind newAlignment) {
        AlignmentKind oldAlignment = alignment;
        alignment = newAlignment == null ? GaugeCompositeStyleImpl.ALIGNMENT_EDEFAULT : newAlignment;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.GAUGE_COMPOSITE_STYLE__ALIGNMENT, oldAlignment, alignment));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<GaugeSection> getSections() {
        if (sections == null) {
            sections = new EObjectContainmentEList<GaugeSection>(GaugeSection.class, this, MigrationmodelerPackage.GAUGE_COMPOSITE_STYLE__SECTIONS);
        }
        return sections;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case MigrationmodelerPackage.GAUGE_COMPOSITE_STYLE__SECTIONS:
            return ((InternalEList<?>) getSections()).basicRemove(otherEnd, msgs);
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
        case MigrationmodelerPackage.GAUGE_COMPOSITE_STYLE__ALIGNMENT:
            return getAlignment();
        case MigrationmodelerPackage.GAUGE_COMPOSITE_STYLE__SECTIONS:
            return getSections();
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
        case MigrationmodelerPackage.GAUGE_COMPOSITE_STYLE__ALIGNMENT:
            setAlignment((AlignmentKind) newValue);
            return;
        case MigrationmodelerPackage.GAUGE_COMPOSITE_STYLE__SECTIONS:
            getSections().clear();
            getSections().addAll((Collection<? extends GaugeSection>) newValue);
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
        case MigrationmodelerPackage.GAUGE_COMPOSITE_STYLE__ALIGNMENT:
            setAlignment(GaugeCompositeStyleImpl.ALIGNMENT_EDEFAULT);
            return;
        case MigrationmodelerPackage.GAUGE_COMPOSITE_STYLE__SECTIONS:
            getSections().clear();
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
        case MigrationmodelerPackage.GAUGE_COMPOSITE_STYLE__ALIGNMENT:
            return alignment != GaugeCompositeStyleImpl.ALIGNMENT_EDEFAULT;
        case MigrationmodelerPackage.GAUGE_COMPOSITE_STYLE__SECTIONS:
            return sections != null && !sections.isEmpty();
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
        result.append(" (alignment: "); //$NON-NLS-1$
        result.append(alignment);
        result.append(')');
        return result.toString();
    }

} // GaugeCompositeStyleImpl
