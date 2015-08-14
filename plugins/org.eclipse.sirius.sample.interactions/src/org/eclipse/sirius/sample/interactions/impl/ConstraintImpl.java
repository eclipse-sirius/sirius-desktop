/*******************************************************************************
 * Copyright (c) 2010, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.sample.interactions.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.sirius.sample.interactions.AbstractEnd;
import org.eclipse.sirius.sample.interactions.Constraint;
import org.eclipse.sirius.sample.interactions.InteractionsPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Constraint</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.sample.interactions.impl.ConstraintImpl#getExpression
 * <em>Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.sample.interactions.impl.ConstraintImpl#getConstrainedEnds
 * <em>Constrained Ends</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConstraintImpl extends EObjectImpl implements Constraint {
    /**
     * The default value of the '{@link #getExpression() <em>Expression</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getExpression()
     * @generated
     * @ordered
     */
    protected static final String EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getExpression() <em>Expression</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getExpression()
     * @generated
     * @ordered
     */
    protected String expression = ConstraintImpl.EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getConstrainedEnds()
     * <em>Constrained Ends</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getConstrainedEnds()
     * @generated
     * @ordered
     */
    protected EList<AbstractEnd> constrainedEnds;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ConstraintImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return InteractionsPackage.Literals.CONSTRAINT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getExpression() {
        return expression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setExpression(String newExpression) {
        String oldExpression = expression;
        expression = newExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, InteractionsPackage.CONSTRAINT__EXPRESSION, oldExpression, expression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<AbstractEnd> getConstrainedEnds() {
        if (constrainedEnds == null) {
            constrainedEnds = new EObjectResolvingEList<AbstractEnd>(AbstractEnd.class, this, InteractionsPackage.CONSTRAINT__CONSTRAINED_ENDS);
        }
        return constrainedEnds;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case InteractionsPackage.CONSTRAINT__EXPRESSION:
            return getExpression();
        case InteractionsPackage.CONSTRAINT__CONSTRAINED_ENDS:
            return getConstrainedEnds();
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
        case InteractionsPackage.CONSTRAINT__EXPRESSION:
            setExpression((String) newValue);
            return;
        case InteractionsPackage.CONSTRAINT__CONSTRAINED_ENDS:
            getConstrainedEnds().clear();
            getConstrainedEnds().addAll((Collection<? extends AbstractEnd>) newValue);
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
        case InteractionsPackage.CONSTRAINT__EXPRESSION:
            setExpression(ConstraintImpl.EXPRESSION_EDEFAULT);
            return;
        case InteractionsPackage.CONSTRAINT__CONSTRAINED_ENDS:
            getConstrainedEnds().clear();
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
        case InteractionsPackage.CONSTRAINT__EXPRESSION:
            return ConstraintImpl.EXPRESSION_EDEFAULT == null ? expression != null : !ConstraintImpl.EXPRESSION_EDEFAULT.equals(expression);
        case InteractionsPackage.CONSTRAINT__CONSTRAINED_ENDS:
            return constrainedEnds != null && !constrainedEnds.isEmpty();
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
        result.append(" (expression: "); //$NON-NLS-1$
        result.append(expression);
        result.append(')');
        return result.toString();
    }

} // ConstraintImpl
