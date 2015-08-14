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
package org.eclipse.sirius.viewpoint.description.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.EStructuralFeatureCustomization;
import org.eclipse.sirius.viewpoint.description.VSMElementCustomization;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>VSM Element Customization</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.VSMElementCustomizationImpl#getPredicateExpression
 * <em>Predicate Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.VSMElementCustomizationImpl#getFeatureCustomizations
 * <em>Feature Customizations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VSMElementCustomizationImpl extends MinimalEObjectImpl.Container implements VSMElementCustomization {
    /**
     * The default value of the '{@link #getPredicateExpression()
     * <em>Predicate Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getPredicateExpression()
     * @generated
     * @ordered
     */
    protected static final String PREDICATE_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPredicateExpression()
     * <em>Predicate Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getPredicateExpression()
     * @generated
     * @ordered
     */
    protected String predicateExpression = VSMElementCustomizationImpl.PREDICATE_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getFeatureCustomizations()
     * <em>Feature Customizations</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFeatureCustomizations()
     * @generated
     * @ordered
     */
    protected EList<EStructuralFeatureCustomization> featureCustomizations;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected VSMElementCustomizationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.VSM_ELEMENT_CUSTOMIZATION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getPredicateExpression() {
        return predicateExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setPredicateExpression(String newPredicateExpression) {
        String oldPredicateExpression = predicateExpression;
        predicateExpression = newPredicateExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.VSM_ELEMENT_CUSTOMIZATION__PREDICATE_EXPRESSION, oldPredicateExpression, predicateExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<EStructuralFeatureCustomization> getFeatureCustomizations() {
        if (featureCustomizations == null) {
            featureCustomizations = new EObjectContainmentEList.Resolving<EStructuralFeatureCustomization>(EStructuralFeatureCustomization.class, this,
                    DescriptionPackage.VSM_ELEMENT_CUSTOMIZATION__FEATURE_CUSTOMIZATIONS);
        }
        return featureCustomizations;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DescriptionPackage.VSM_ELEMENT_CUSTOMIZATION__FEATURE_CUSTOMIZATIONS:
            return ((InternalEList<?>) getFeatureCustomizations()).basicRemove(otherEnd, msgs);
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
        case DescriptionPackage.VSM_ELEMENT_CUSTOMIZATION__PREDICATE_EXPRESSION:
            return getPredicateExpression();
        case DescriptionPackage.VSM_ELEMENT_CUSTOMIZATION__FEATURE_CUSTOMIZATIONS:
            return getFeatureCustomizations();
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
        case DescriptionPackage.VSM_ELEMENT_CUSTOMIZATION__PREDICATE_EXPRESSION:
            setPredicateExpression((String) newValue);
            return;
        case DescriptionPackage.VSM_ELEMENT_CUSTOMIZATION__FEATURE_CUSTOMIZATIONS:
            getFeatureCustomizations().clear();
            getFeatureCustomizations().addAll((Collection<? extends EStructuralFeatureCustomization>) newValue);
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
        case DescriptionPackage.VSM_ELEMENT_CUSTOMIZATION__PREDICATE_EXPRESSION:
            setPredicateExpression(VSMElementCustomizationImpl.PREDICATE_EXPRESSION_EDEFAULT);
            return;
        case DescriptionPackage.VSM_ELEMENT_CUSTOMIZATION__FEATURE_CUSTOMIZATIONS:
            getFeatureCustomizations().clear();
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
        case DescriptionPackage.VSM_ELEMENT_CUSTOMIZATION__PREDICATE_EXPRESSION:
            return VSMElementCustomizationImpl.PREDICATE_EXPRESSION_EDEFAULT == null ? predicateExpression != null : !VSMElementCustomizationImpl.PREDICATE_EXPRESSION_EDEFAULT
                    .equals(predicateExpression);
        case DescriptionPackage.VSM_ELEMENT_CUSTOMIZATION__FEATURE_CUSTOMIZATIONS:
            return featureCustomizations != null && !featureCustomizations.isEmpty();
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
        result.append(" (predicateExpression: "); //$NON-NLS-1$
        result.append(predicateExpression);
        result.append(')');
        return result.toString();
    }

} // VSMElementCustomizationImpl
