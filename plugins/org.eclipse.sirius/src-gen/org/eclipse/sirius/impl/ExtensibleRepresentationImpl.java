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
package org.eclipse.sirius.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.sirius.ExtensibleRepresentation;
import org.eclipse.sirius.SiriusPackage;
import org.eclipse.sirius.description.RepresentationDescription;
import org.eclipse.sirius.description.contribution.ContributionPoint;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Extensible Representation</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.impl.ExtensibleRepresentationImpl#getEffectiveDescription
 * <em>Effective Description</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.impl.ExtensibleRepresentationImpl#getContributionPoints
 * <em>Contribution Points</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class ExtensibleRepresentationImpl extends EObjectImpl implements ExtensibleRepresentation {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

    /**
     * The cached value of the '{@link #getEffectiveDescription()
     * <em>Effective Description</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getEffectiveDescription()
     * @generated
     * @ordered
     */
    protected RepresentationDescription effectiveDescription;

    /**
     * The cached value of the '{@link #getContributionPoints()
     * <em>Contribution Points</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getContributionPoints()
     * @generated
     * @ordered
     */
    protected EList<ContributionPoint> contributionPoints;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ExtensibleRepresentationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return SiriusPackage.Literals.EXTENSIBLE_REPRESENTATION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public RepresentationDescription getEffectiveDescription() {
        if (effectiveDescription != null && effectiveDescription.eIsProxy()) {
            InternalEObject oldEffectiveDescription = (InternalEObject) effectiveDescription;
            effectiveDescription = (RepresentationDescription) eResolveProxy(oldEffectiveDescription);
            if (effectiveDescription != oldEffectiveDescription) {
                InternalEObject newEffectiveDescription = (InternalEObject) effectiveDescription;
                NotificationChain msgs = oldEffectiveDescription.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SiriusPackage.EXTENSIBLE_REPRESENTATION__EFFECTIVE_DESCRIPTION, null, null);
                if (newEffectiveDescription.eInternalContainer() == null) {
                    msgs = newEffectiveDescription.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SiriusPackage.EXTENSIBLE_REPRESENTATION__EFFECTIVE_DESCRIPTION, null, msgs);
                }
                if (msgs != null)
                    msgs.dispatch();
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SiriusPackage.EXTENSIBLE_REPRESENTATION__EFFECTIVE_DESCRIPTION, oldEffectiveDescription, effectiveDescription));
            }
        }
        return effectiveDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public RepresentationDescription basicGetEffectiveDescription() {
        return effectiveDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetEffectiveDescription(RepresentationDescription newEffectiveDescription, NotificationChain msgs) {
        RepresentationDescription oldEffectiveDescription = effectiveDescription;
        effectiveDescription = newEffectiveDescription;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SiriusPackage.EXTENSIBLE_REPRESENTATION__EFFECTIVE_DESCRIPTION, oldEffectiveDescription,
                    newEffectiveDescription);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setEffectiveDescription(RepresentationDescription newEffectiveDescription) {
        if (newEffectiveDescription != effectiveDescription) {
            NotificationChain msgs = null;
            if (effectiveDescription != null)
                msgs = ((InternalEObject) effectiveDescription).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SiriusPackage.EXTENSIBLE_REPRESENTATION__EFFECTIVE_DESCRIPTION, null, msgs);
            if (newEffectiveDescription != null)
                msgs = ((InternalEObject) newEffectiveDescription).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SiriusPackage.EXTENSIBLE_REPRESENTATION__EFFECTIVE_DESCRIPTION, null, msgs);
            msgs = basicSetEffectiveDescription(newEffectiveDescription, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SiriusPackage.EXTENSIBLE_REPRESENTATION__EFFECTIVE_DESCRIPTION, newEffectiveDescription, newEffectiveDescription));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<ContributionPoint> getContributionPoints() {
        if (contributionPoints == null) {
            contributionPoints = new EObjectContainmentEList.Resolving<ContributionPoint>(ContributionPoint.class, this, SiriusPackage.EXTENSIBLE_REPRESENTATION__CONTRIBUTION_POINTS);
        }
        return contributionPoints;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case SiriusPackage.EXTENSIBLE_REPRESENTATION__EFFECTIVE_DESCRIPTION:
            return basicSetEffectiveDescription(null, msgs);
        case SiriusPackage.EXTENSIBLE_REPRESENTATION__CONTRIBUTION_POINTS:
            return ((InternalEList<?>) getContributionPoints()).basicRemove(otherEnd, msgs);
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
        case SiriusPackage.EXTENSIBLE_REPRESENTATION__EFFECTIVE_DESCRIPTION:
            if (resolve)
                return getEffectiveDescription();
            return basicGetEffectiveDescription();
        case SiriusPackage.EXTENSIBLE_REPRESENTATION__CONTRIBUTION_POINTS:
            return getContributionPoints();
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
        case SiriusPackage.EXTENSIBLE_REPRESENTATION__EFFECTIVE_DESCRIPTION:
            setEffectiveDescription((RepresentationDescription) newValue);
            return;
        case SiriusPackage.EXTENSIBLE_REPRESENTATION__CONTRIBUTION_POINTS:
            getContributionPoints().clear();
            getContributionPoints().addAll((Collection<? extends ContributionPoint>) newValue);
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
        case SiriusPackage.EXTENSIBLE_REPRESENTATION__EFFECTIVE_DESCRIPTION:
            setEffectiveDescription((RepresentationDescription) null);
            return;
        case SiriusPackage.EXTENSIBLE_REPRESENTATION__CONTRIBUTION_POINTS:
            getContributionPoints().clear();
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
        case SiriusPackage.EXTENSIBLE_REPRESENTATION__EFFECTIVE_DESCRIPTION:
            return effectiveDescription != null;
        case SiriusPackage.EXTENSIBLE_REPRESENTATION__CONTRIBUTION_POINTS:
            return contributionPoints != null && !contributionPoints.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // ExtensibleRepresentationImpl
