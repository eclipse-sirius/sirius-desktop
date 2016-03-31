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
package org.eclipse.sirius.diagram.description.style.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.diagram.AlignmentKind;
import org.eclipse.sirius.diagram.description.style.GaugeCompositeStyleDescription;
import org.eclipse.sirius.diagram.description.style.GaugeSectionDescription;
import org.eclipse.sirius.diagram.description.style.StylePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Gauge Composite Style Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.GaugeCompositeStyleDescriptionImpl#getAlignment
 * <em>Alignment</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.GaugeCompositeStyleDescriptionImpl#getSections
 * <em>Sections</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GaugeCompositeStyleDescriptionImpl extends NodeStyleDescriptionImpl implements GaugeCompositeStyleDescription {
    /**
     * The default value of the '{@link #getAlignment() <em>Alignment</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getAlignment()
     * @generated
     * @ordered
     */
    protected static final AlignmentKind ALIGNMENT_EDEFAULT = AlignmentKind.SQUARE_LITERAL;

    /**
     * The cached value of the '{@link #getAlignment() <em>Alignment</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getAlignment()
     * @generated
     * @ordered
     */
    protected AlignmentKind alignment = GaugeCompositeStyleDescriptionImpl.ALIGNMENT_EDEFAULT;

    /**
     * The cached value of the '{@link #getSections() <em>Sections</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSections()
     * @generated
     * @ordered
     */
    protected EList<GaugeSectionDescription> sections;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected GaugeCompositeStyleDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return StylePackage.Literals.GAUGE_COMPOSITE_STYLE_DESCRIPTION;
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
        alignment = newAlignment == null ? GaugeCompositeStyleDescriptionImpl.ALIGNMENT_EDEFAULT : newAlignment;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.GAUGE_COMPOSITE_STYLE_DESCRIPTION__ALIGNMENT, oldAlignment, alignment));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<GaugeSectionDescription> getSections() {
        if (sections == null) {
            sections = new EObjectContainmentEList.Resolving<GaugeSectionDescription>(GaugeSectionDescription.class, this, StylePackage.GAUGE_COMPOSITE_STYLE_DESCRIPTION__SECTIONS);
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
        case StylePackage.GAUGE_COMPOSITE_STYLE_DESCRIPTION__SECTIONS:
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
        case StylePackage.GAUGE_COMPOSITE_STYLE_DESCRIPTION__ALIGNMENT:
            return getAlignment();
        case StylePackage.GAUGE_COMPOSITE_STYLE_DESCRIPTION__SECTIONS:
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
        case StylePackage.GAUGE_COMPOSITE_STYLE_DESCRIPTION__ALIGNMENT:
            setAlignment((AlignmentKind) newValue);
            return;
        case StylePackage.GAUGE_COMPOSITE_STYLE_DESCRIPTION__SECTIONS:
            getSections().clear();
            getSections().addAll((Collection<? extends GaugeSectionDescription>) newValue);
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
        case StylePackage.GAUGE_COMPOSITE_STYLE_DESCRIPTION__ALIGNMENT:
            setAlignment(GaugeCompositeStyleDescriptionImpl.ALIGNMENT_EDEFAULT);
            return;
        case StylePackage.GAUGE_COMPOSITE_STYLE_DESCRIPTION__SECTIONS:
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
        case StylePackage.GAUGE_COMPOSITE_STYLE_DESCRIPTION__ALIGNMENT:
            return alignment != GaugeCompositeStyleDescriptionImpl.ALIGNMENT_EDEFAULT;
        case StylePackage.GAUGE_COMPOSITE_STYLE_DESCRIPTION__SECTIONS:
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

} // GaugeCompositeStyleDescriptionImpl
