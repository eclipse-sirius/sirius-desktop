/**
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Obeo - initial API and implementation
 * 
 */
package org.eclipse.sirius.properties.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.SelectDescription;

import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Select Description</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.sirius.properties.impl.SelectDescriptionImpl#getValueExpression <em>Value Expression</em>}</li>
 *   <li>{@link org.eclipse.sirius.properties.impl.SelectDescriptionImpl#getInitialOperation <em>Initial Operation</em>}</li>
 *   <li>{@link org.eclipse.sirius.properties.impl.SelectDescriptionImpl#getCandidatesExpression <em>Candidates Expression</em>}</li>
 *   <li>{@link org.eclipse.sirius.properties.impl.SelectDescriptionImpl#getCandidateDisplayExpression <em>Candidate Display Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SelectDescriptionImpl extends WidgetDescriptionImpl implements SelectDescription {
    /**
     * The default value of the '{@link #getValueExpression() <em>Value Expression</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getValueExpression()
     * @generated
     * @ordered
     */
    protected static final String VALUE_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getValueExpression() <em>Value Expression</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getValueExpression()
     * @generated
     * @ordered
     */
    protected String valueExpression = VALUE_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getInitialOperation() <em>Initial Operation</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInitialOperation()
     * @generated
     * @ordered
     */
    protected InitialOperation initialOperation;

    /**
     * The default value of the '{@link #getCandidatesExpression() <em>Candidates Expression</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCandidatesExpression()
     * @generated
     * @ordered
     */
    protected static final String CANDIDATES_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getCandidatesExpression() <em>Candidates Expression</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCandidatesExpression()
     * @generated
     * @ordered
     */
    protected String candidatesExpression = CANDIDATES_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getCandidateDisplayExpression() <em>Candidate Display Expression</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCandidateDisplayExpression()
     * @generated
     * @ordered
     */
    protected static final String CANDIDATE_DISPLAY_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getCandidateDisplayExpression() <em>Candidate Display Expression</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCandidateDisplayExpression()
     * @generated
     * @ordered
     */
    protected String candidateDisplayExpression = CANDIDATE_DISPLAY_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SelectDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.SELECT_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getValueExpression() {
        return valueExpression;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValueExpression(String newValueExpression) {
        String oldValueExpression = valueExpression;
        valueExpression = newValueExpression;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.SELECT_DESCRIPTION__VALUE_EXPRESSION, oldValueExpression, valueExpression));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InitialOperation getInitialOperation() {
        return initialOperation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetInitialOperation(InitialOperation newInitialOperation, NotificationChain msgs) {
        InitialOperation oldInitialOperation = initialOperation;
        initialOperation = newInitialOperation;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.SELECT_DESCRIPTION__INITIAL_OPERATION, oldInitialOperation, newInitialOperation);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setInitialOperation(InitialOperation newInitialOperation) {
        if (newInitialOperation != initialOperation) {
            NotificationChain msgs = null;
            if (initialOperation != null)
                msgs = ((InternalEObject)initialOperation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PropertiesPackage.SELECT_DESCRIPTION__INITIAL_OPERATION, null, msgs);
            if (newInitialOperation != null)
                msgs = ((InternalEObject)newInitialOperation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PropertiesPackage.SELECT_DESCRIPTION__INITIAL_OPERATION, null, msgs);
            msgs = basicSetInitialOperation(newInitialOperation, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.SELECT_DESCRIPTION__INITIAL_OPERATION, newInitialOperation, newInitialOperation));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getCandidatesExpression() {
        return candidatesExpression;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCandidatesExpression(String newCandidatesExpression) {
        String oldCandidatesExpression = candidatesExpression;
        candidatesExpression = newCandidatesExpression;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.SELECT_DESCRIPTION__CANDIDATES_EXPRESSION, oldCandidatesExpression, candidatesExpression));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getCandidateDisplayExpression() {
        return candidateDisplayExpression;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCandidateDisplayExpression(String newCandidateDisplayExpression) {
        String oldCandidateDisplayExpression = candidateDisplayExpression;
        candidateDisplayExpression = newCandidateDisplayExpression;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.SELECT_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION, oldCandidateDisplayExpression, candidateDisplayExpression));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case PropertiesPackage.SELECT_DESCRIPTION__INITIAL_OPERATION:
                return basicSetInitialOperation(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case PropertiesPackage.SELECT_DESCRIPTION__VALUE_EXPRESSION:
                return getValueExpression();
            case PropertiesPackage.SELECT_DESCRIPTION__INITIAL_OPERATION:
                return getInitialOperation();
            case PropertiesPackage.SELECT_DESCRIPTION__CANDIDATES_EXPRESSION:
                return getCandidatesExpression();
            case PropertiesPackage.SELECT_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION:
                return getCandidateDisplayExpression();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case PropertiesPackage.SELECT_DESCRIPTION__VALUE_EXPRESSION:
                setValueExpression((String)newValue);
                return;
            case PropertiesPackage.SELECT_DESCRIPTION__INITIAL_OPERATION:
                setInitialOperation((InitialOperation)newValue);
                return;
            case PropertiesPackage.SELECT_DESCRIPTION__CANDIDATES_EXPRESSION:
                setCandidatesExpression((String)newValue);
                return;
            case PropertiesPackage.SELECT_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION:
                setCandidateDisplayExpression((String)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case PropertiesPackage.SELECT_DESCRIPTION__VALUE_EXPRESSION:
                setValueExpression(VALUE_EXPRESSION_EDEFAULT);
                return;
            case PropertiesPackage.SELECT_DESCRIPTION__INITIAL_OPERATION:
                setInitialOperation((InitialOperation)null);
                return;
            case PropertiesPackage.SELECT_DESCRIPTION__CANDIDATES_EXPRESSION:
                setCandidatesExpression(CANDIDATES_EXPRESSION_EDEFAULT);
                return;
            case PropertiesPackage.SELECT_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION:
                setCandidateDisplayExpression(CANDIDATE_DISPLAY_EXPRESSION_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case PropertiesPackage.SELECT_DESCRIPTION__VALUE_EXPRESSION:
                return VALUE_EXPRESSION_EDEFAULT == null ? valueExpression != null : !VALUE_EXPRESSION_EDEFAULT.equals(valueExpression);
            case PropertiesPackage.SELECT_DESCRIPTION__INITIAL_OPERATION:
                return initialOperation != null;
            case PropertiesPackage.SELECT_DESCRIPTION__CANDIDATES_EXPRESSION:
                return CANDIDATES_EXPRESSION_EDEFAULT == null ? candidatesExpression != null : !CANDIDATES_EXPRESSION_EDEFAULT.equals(candidatesExpression);
            case PropertiesPackage.SELECT_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION:
                return CANDIDATE_DISPLAY_EXPRESSION_EDEFAULT == null ? candidateDisplayExpression != null : !CANDIDATE_DISPLAY_EXPRESSION_EDEFAULT.equals(candidateDisplayExpression);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (valueExpression: ");
        result.append(valueExpression);
        result.append(", candidatesExpression: ");
        result.append(candidatesExpression);
        result.append(", candidateDisplayExpression: ");
        result.append(candidateDisplayExpression);
        result.append(')');
        return result.toString();
    }

} //SelectDescriptionImpl
