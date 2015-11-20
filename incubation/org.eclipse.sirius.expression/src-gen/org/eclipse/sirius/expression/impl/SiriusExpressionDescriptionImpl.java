/**
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.htm
 */
package org.eclipse.sirius.expression.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.sirius.expression.ExpressionPackage;
import org.eclipse.sirius.expression.SiriusExpressionDescription;
import org.eclipse.sirius.expression.SiriusParameter;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sirius Expression Description</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.sirius.expression.impl.SiriusExpressionDescriptionImpl#getLowerBound <em>Lower Bound</em>}</li>
 *   <li>{@link org.eclipse.sirius.expression.impl.SiriusExpressionDescriptionImpl#getUpperBound <em>Upper Bound</em>}</li>
 *   <li>{@link org.eclipse.sirius.expression.impl.SiriusExpressionDescriptionImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.eclipse.sirius.expression.impl.SiriusExpressionDescriptionImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.eclipse.sirius.expression.impl.SiriusExpressionDescriptionImpl#getReturnType <em>Return Type</em>}</li>
 *   <li>{@link org.eclipse.sirius.expression.impl.SiriusExpressionDescriptionImpl#getVariableContainers <em>Variable Containers</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SiriusExpressionDescriptionImpl extends MinimalEObjectImpl.Container implements SiriusExpressionDescription {
	/**
	 * The default value of the '{@link #getLowerBound() <em>Lower Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLowerBound()
	 * @generated
	 * @ordered
	 */
	protected static final int LOWER_BOUND_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getLowerBound() <em>Lower Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLowerBound()
	 * @generated
	 * @ordered
	 */
	protected int lowerBound = LOWER_BOUND_EDEFAULT;

	/**
	 * The default value of the '{@link #getUpperBound() <em>Upper Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpperBound()
	 * @generated
	 * @ordered
	 */
	protected static final int UPPER_BOUND_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getUpperBound() <em>Upper Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpperBound()
	 * @generated
	 * @ordered
	 */
	protected int upperBound = UPPER_BOUND_EDEFAULT;

	/**
	 * The cached value of the '{@link #getExpression() <em>Expression</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpression()
	 * @generated
	 * @ordered
	 */
	protected EStructuralFeature expression;

	/**
	 * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<SiriusParameter> parameters;

	/**
	 * The cached value of the '{@link #getReturnType() <em>Return Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReturnType()
	 * @generated
	 * @ordered
	 */
	protected EClassifier returnType;

	/**
	 * The cached value of the '{@link #getVariableContainers() <em>Variable Containers</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariableContainers()
	 * @generated
	 * @ordered
	 */
	protected EList<EClass> variableContainers;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SiriusExpressionDescriptionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExpressionPackage.Literals.SIRIUS_EXPRESSION_DESCRIPTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getLowerBound() {
		return lowerBound;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLowerBound(int newLowerBound) {
		int oldLowerBound = lowerBound;
		lowerBound = newLowerBound;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExpressionPackage.SIRIUS_EXPRESSION_DESCRIPTION__LOWER_BOUND, oldLowerBound, lowerBound));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getUpperBound() {
		return upperBound;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUpperBound(int newUpperBound) {
		int oldUpperBound = upperBound;
		upperBound = newUpperBound;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExpressionPackage.SIRIUS_EXPRESSION_DESCRIPTION__UPPER_BOUND, oldUpperBound, upperBound));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EStructuralFeature getExpression() {
		if (expression != null && expression.eIsProxy()) {
			InternalEObject oldExpression = (InternalEObject)expression;
			expression = (EStructuralFeature)eResolveProxy(oldExpression);
			if (expression != oldExpression) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ExpressionPackage.SIRIUS_EXPRESSION_DESCRIPTION__EXPRESSION, oldExpression, expression));
			}
		}
		return expression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EStructuralFeature basicGetExpression() {
		return expression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExpression(EStructuralFeature newExpression) {
		EStructuralFeature oldExpression = expression;
		expression = newExpression;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExpressionPackage.SIRIUS_EXPRESSION_DESCRIPTION__EXPRESSION, oldExpression, expression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SiriusParameter> getParameters() {
		if (parameters == null) {
			parameters = new EObjectContainmentEList.Resolving<SiriusParameter>(SiriusParameter.class, this, ExpressionPackage.SIRIUS_EXPRESSION_DESCRIPTION__PARAMETERS);
		}
		return parameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClassifier getReturnType() {
		if (returnType != null && returnType.eIsProxy()) {
			InternalEObject oldReturnType = (InternalEObject)returnType;
			returnType = (EClassifier)eResolveProxy(oldReturnType);
			if (returnType != oldReturnType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ExpressionPackage.SIRIUS_EXPRESSION_DESCRIPTION__RETURN_TYPE, oldReturnType, returnType));
			}
		}
		return returnType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClassifier basicGetReturnType() {
		return returnType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReturnType(EClassifier newReturnType) {
		EClassifier oldReturnType = returnType;
		returnType = newReturnType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExpressionPackage.SIRIUS_EXPRESSION_DESCRIPTION__RETURN_TYPE, oldReturnType, returnType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EClass> getVariableContainers() {
		if (variableContainers == null) {
			variableContainers = new EObjectResolvingEList<EClass>(EClass.class, this, ExpressionPackage.SIRIUS_EXPRESSION_DESCRIPTION__VARIABLE_CONTAINERS);
		}
		return variableContainers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ExpressionPackage.SIRIUS_EXPRESSION_DESCRIPTION__PARAMETERS:
				return ((InternalEList<?>)getParameters()).basicRemove(otherEnd, msgs);
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
			case ExpressionPackage.SIRIUS_EXPRESSION_DESCRIPTION__LOWER_BOUND:
				return getLowerBound();
			case ExpressionPackage.SIRIUS_EXPRESSION_DESCRIPTION__UPPER_BOUND:
				return getUpperBound();
			case ExpressionPackage.SIRIUS_EXPRESSION_DESCRIPTION__EXPRESSION:
				if (resolve) return getExpression();
				return basicGetExpression();
			case ExpressionPackage.SIRIUS_EXPRESSION_DESCRIPTION__PARAMETERS:
				return getParameters();
			case ExpressionPackage.SIRIUS_EXPRESSION_DESCRIPTION__RETURN_TYPE:
				if (resolve) return getReturnType();
				return basicGetReturnType();
			case ExpressionPackage.SIRIUS_EXPRESSION_DESCRIPTION__VARIABLE_CONTAINERS:
				return getVariableContainers();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ExpressionPackage.SIRIUS_EXPRESSION_DESCRIPTION__LOWER_BOUND:
				setLowerBound((Integer)newValue);
				return;
			case ExpressionPackage.SIRIUS_EXPRESSION_DESCRIPTION__UPPER_BOUND:
				setUpperBound((Integer)newValue);
				return;
			case ExpressionPackage.SIRIUS_EXPRESSION_DESCRIPTION__EXPRESSION:
				setExpression((EStructuralFeature)newValue);
				return;
			case ExpressionPackage.SIRIUS_EXPRESSION_DESCRIPTION__PARAMETERS:
				getParameters().clear();
				getParameters().addAll((Collection<? extends SiriusParameter>)newValue);
				return;
			case ExpressionPackage.SIRIUS_EXPRESSION_DESCRIPTION__RETURN_TYPE:
				setReturnType((EClassifier)newValue);
				return;
			case ExpressionPackage.SIRIUS_EXPRESSION_DESCRIPTION__VARIABLE_CONTAINERS:
				getVariableContainers().clear();
				getVariableContainers().addAll((Collection<? extends EClass>)newValue);
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
			case ExpressionPackage.SIRIUS_EXPRESSION_DESCRIPTION__LOWER_BOUND:
				setLowerBound(LOWER_BOUND_EDEFAULT);
				return;
			case ExpressionPackage.SIRIUS_EXPRESSION_DESCRIPTION__UPPER_BOUND:
				setUpperBound(UPPER_BOUND_EDEFAULT);
				return;
			case ExpressionPackage.SIRIUS_EXPRESSION_DESCRIPTION__EXPRESSION:
				setExpression((EStructuralFeature)null);
				return;
			case ExpressionPackage.SIRIUS_EXPRESSION_DESCRIPTION__PARAMETERS:
				getParameters().clear();
				return;
			case ExpressionPackage.SIRIUS_EXPRESSION_DESCRIPTION__RETURN_TYPE:
				setReturnType((EClassifier)null);
				return;
			case ExpressionPackage.SIRIUS_EXPRESSION_DESCRIPTION__VARIABLE_CONTAINERS:
				getVariableContainers().clear();
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
			case ExpressionPackage.SIRIUS_EXPRESSION_DESCRIPTION__LOWER_BOUND:
				return lowerBound != LOWER_BOUND_EDEFAULT;
			case ExpressionPackage.SIRIUS_EXPRESSION_DESCRIPTION__UPPER_BOUND:
				return upperBound != UPPER_BOUND_EDEFAULT;
			case ExpressionPackage.SIRIUS_EXPRESSION_DESCRIPTION__EXPRESSION:
				return expression != null;
			case ExpressionPackage.SIRIUS_EXPRESSION_DESCRIPTION__PARAMETERS:
				return parameters != null && !parameters.isEmpty();
			case ExpressionPackage.SIRIUS_EXPRESSION_DESCRIPTION__RETURN_TYPE:
				return returnType != null;
			case ExpressionPackage.SIRIUS_EXPRESSION_DESCRIPTION__VARIABLE_CONTAINERS:
				return variableContainers != null && !variableContainers.isEmpty();
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
		result.append(" (lowerBound: "); //$NON-NLS-1$
		result.append(lowerBound);
		result.append(", upperBound: "); //$NON-NLS-1$
		result.append(upperBound);
		result.append(')');
		return result.toString();
	}

} //SiriusExpressionDescriptionImpl
