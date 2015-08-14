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
package org.eclipse.sirius.description.contribution.impl;

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
import org.eclipse.sirius.description.contribution.Contribution;
import org.eclipse.sirius.description.contribution.ContributionPackage;
import org.eclipse.sirius.description.contribution.EObjectReference;
import org.eclipse.sirius.description.contribution.FeatureContribution;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Contribution</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.description.contribution.impl.ContributionImpl#getSource
 * <em>Source</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.description.contribution.impl.ContributionImpl#getTarget
 * <em>Target</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.description.contribution.impl.ContributionImpl#getFeatureMask
 * <em>Feature Mask</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.description.contribution.impl.ContributionImpl#getSubContributions
 * <em>Sub Contributions</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.description.contribution.impl.ContributionImpl#getDescription
 * <em>Description</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ContributionImpl extends EObjectImpl implements Contribution {
    /**
     * The cached value of the '{@link #getSource() <em>Source</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSource()
     * @generated
     * @ordered
     */
    protected EObjectReference source;

    /**
     * The cached value of the '{@link #getTarget() <em>Target</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getTarget()
     * @generated
     * @ordered
     */
    protected EObjectReference target;

    /**
     * The cached value of the '{@link #getFeatureMask() <em>Feature Mask</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFeatureMask()
     * @generated
     * @ordered
     */
    protected EList<FeatureContribution> featureMask;

    /**
     * The cached value of the '{@link #getSubContributions()
     * <em>Sub Contributions</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSubContributions()
     * @generated
     * @ordered
     */
    protected EList<Contribution> subContributions;

    /**
     * The default value of the '{@link #getDescription() <em>Description</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected static final String DESCRIPTION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDescription() <em>Description</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected String description = ContributionImpl.DESCRIPTION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ContributionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContributionPackage.Literals.CONTRIBUTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EObjectReference getSource() {
        if (source != null && source.eIsProxy()) {
            InternalEObject oldSource = (InternalEObject) source;
            source = (EObjectReference) eResolveProxy(oldSource);
            if (source != oldSource) {
                InternalEObject newSource = (InternalEObject) source;
                NotificationChain msgs = oldSource.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ContributionPackage.CONTRIBUTION__SOURCE, null, null);
                if (newSource.eInternalContainer() == null) {
                    msgs = newSource.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ContributionPackage.CONTRIBUTION__SOURCE, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ContributionPackage.CONTRIBUTION__SOURCE, oldSource, source));
                }
            }
        }
        return source;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public EObjectReference basicGetSource() {
        return source;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetSource(EObjectReference newSource, NotificationChain msgs) {
        EObjectReference oldSource = source;
        source = newSource;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ContributionPackage.CONTRIBUTION__SOURCE, oldSource, newSource);
            if (msgs == null) {
                msgs = notification;
            } else {
                msgs.add(notification);
            }
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setSource(EObjectReference newSource) {
        if (newSource != source) {
            NotificationChain msgs = null;
            if (source != null) {
                msgs = ((InternalEObject) source).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ContributionPackage.CONTRIBUTION__SOURCE, null, msgs);
            }
            if (newSource != null) {
                msgs = ((InternalEObject) newSource).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ContributionPackage.CONTRIBUTION__SOURCE, null, msgs);
            }
            msgs = basicSetSource(newSource, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ContributionPackage.CONTRIBUTION__SOURCE, newSource, newSource));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EObjectReference getTarget() {
        if (target != null && target.eIsProxy()) {
            InternalEObject oldTarget = (InternalEObject) target;
            target = (EObjectReference) eResolveProxy(oldTarget);
            if (target != oldTarget) {
                InternalEObject newTarget = (InternalEObject) target;
                NotificationChain msgs = oldTarget.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ContributionPackage.CONTRIBUTION__TARGET, null, null);
                if (newTarget.eInternalContainer() == null) {
                    msgs = newTarget.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ContributionPackage.CONTRIBUTION__TARGET, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ContributionPackage.CONTRIBUTION__TARGET, oldTarget, target));
                }
            }
        }
        return target;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public EObjectReference basicGetTarget() {
        return target;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetTarget(EObjectReference newTarget, NotificationChain msgs) {
        EObjectReference oldTarget = target;
        target = newTarget;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ContributionPackage.CONTRIBUTION__TARGET, oldTarget, newTarget);
            if (msgs == null) {
                msgs = notification;
            } else {
                msgs.add(notification);
            }
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setTarget(EObjectReference newTarget) {
        if (newTarget != target) {
            NotificationChain msgs = null;
            if (target != null) {
                msgs = ((InternalEObject) target).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ContributionPackage.CONTRIBUTION__TARGET, null, msgs);
            }
            if (newTarget != null) {
                msgs = ((InternalEObject) newTarget).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ContributionPackage.CONTRIBUTION__TARGET, null, msgs);
            }
            msgs = basicSetTarget(newTarget, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ContributionPackage.CONTRIBUTION__TARGET, newTarget, newTarget));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<FeatureContribution> getFeatureMask() {
        if (featureMask == null) {
            featureMask = new EObjectContainmentEList.Resolving<FeatureContribution>(FeatureContribution.class, this, ContributionPackage.CONTRIBUTION__FEATURE_MASK);
        }
        return featureMask;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Contribution> getSubContributions() {
        if (subContributions == null) {
            subContributions = new EObjectContainmentEList.Resolving<Contribution>(Contribution.class, this, ContributionPackage.CONTRIBUTION__SUB_CONTRIBUTIONS);
        }
        return subContributions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setDescription(String newDescription) {
        String oldDescription = description;
        description = newDescription;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ContributionPackage.CONTRIBUTION__DESCRIPTION, oldDescription, description));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ContributionPackage.CONTRIBUTION__SOURCE:
            return basicSetSource(null, msgs);
        case ContributionPackage.CONTRIBUTION__TARGET:
            return basicSetTarget(null, msgs);
        case ContributionPackage.CONTRIBUTION__FEATURE_MASK:
            return ((InternalEList<?>) getFeatureMask()).basicRemove(otherEnd, msgs);
        case ContributionPackage.CONTRIBUTION__SUB_CONTRIBUTIONS:
            return ((InternalEList<?>) getSubContributions()).basicRemove(otherEnd, msgs);
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
        case ContributionPackage.CONTRIBUTION__SOURCE:
            if (resolve) {
                return getSource();
            }
            return basicGetSource();
        case ContributionPackage.CONTRIBUTION__TARGET:
            if (resolve) {
                return getTarget();
            }
            return basicGetTarget();
        case ContributionPackage.CONTRIBUTION__FEATURE_MASK:
            return getFeatureMask();
        case ContributionPackage.CONTRIBUTION__SUB_CONTRIBUTIONS:
            return getSubContributions();
        case ContributionPackage.CONTRIBUTION__DESCRIPTION:
            return getDescription();
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
        case ContributionPackage.CONTRIBUTION__SOURCE:
            setSource((EObjectReference) newValue);
            return;
        case ContributionPackage.CONTRIBUTION__TARGET:
            setTarget((EObjectReference) newValue);
            return;
        case ContributionPackage.CONTRIBUTION__FEATURE_MASK:
            getFeatureMask().clear();
            getFeatureMask().addAll((Collection<? extends FeatureContribution>) newValue);
            return;
        case ContributionPackage.CONTRIBUTION__SUB_CONTRIBUTIONS:
            getSubContributions().clear();
            getSubContributions().addAll((Collection<? extends Contribution>) newValue);
            return;
        case ContributionPackage.CONTRIBUTION__DESCRIPTION:
            setDescription((String) newValue);
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
        case ContributionPackage.CONTRIBUTION__SOURCE:
            setSource((EObjectReference) null);
            return;
        case ContributionPackage.CONTRIBUTION__TARGET:
            setTarget((EObjectReference) null);
            return;
        case ContributionPackage.CONTRIBUTION__FEATURE_MASK:
            getFeatureMask().clear();
            return;
        case ContributionPackage.CONTRIBUTION__SUB_CONTRIBUTIONS:
            getSubContributions().clear();
            return;
        case ContributionPackage.CONTRIBUTION__DESCRIPTION:
            setDescription(ContributionImpl.DESCRIPTION_EDEFAULT);
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
        case ContributionPackage.CONTRIBUTION__SOURCE:
            return source != null;
        case ContributionPackage.CONTRIBUTION__TARGET:
            return target != null;
        case ContributionPackage.CONTRIBUTION__FEATURE_MASK:
            return featureMask != null && !featureMask.isEmpty();
        case ContributionPackage.CONTRIBUTION__SUB_CONTRIBUTIONS:
            return subContributions != null && !subContributions.isEmpty();
        case ContributionPackage.CONTRIBUTION__DESCRIPTION:
            return ContributionImpl.DESCRIPTION_EDEFAULT == null ? description != null : !ContributionImpl.DESCRIPTION_EDEFAULT.equals(description);
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
        result.append(" (description: "); //$NON-NLS-1$
        result.append(description);
        result.append(')');
        return result.toString();
    }

} // ContributionImpl
