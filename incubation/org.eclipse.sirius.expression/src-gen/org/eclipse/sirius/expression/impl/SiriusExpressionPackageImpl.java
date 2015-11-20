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
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.sirius.expression.ExpressionPackage;
import org.eclipse.sirius.expression.SiriusExpressionClass;
import org.eclipse.sirius.expression.SiriusExpressionPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sirius Expression Package</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.sirius.expression.impl.SiriusExpressionPackageImpl#getExpressionClasses <em>Expression Classes</em>}</li>
 *   <li>{@link org.eclipse.sirius.expression.impl.SiriusExpressionPackageImpl#getEPackage <em>EPackage</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SiriusExpressionPackageImpl extends MinimalEObjectImpl.Container implements SiriusExpressionPackage {
	/**
	 * The cached value of the '{@link #getExpressionClasses() <em>Expression Classes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpressionClasses()
	 * @generated
	 * @ordered
	 */
	protected EList<SiriusExpressionClass> expressionClasses;

	/**
	 * The cached value of the '{@link #getEPackage() <em>EPackage</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEPackage()
	 * @generated
	 * @ordered
	 */
	protected EPackage ePackage;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SiriusExpressionPackageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExpressionPackage.Literals.SIRIUS_EXPRESSION_PACKAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SiriusExpressionClass> getExpressionClasses() {
		if (expressionClasses == null) {
			expressionClasses = new EObjectContainmentEList.Resolving<SiriusExpressionClass>(SiriusExpressionClass.class, this, ExpressionPackage.SIRIUS_EXPRESSION_PACKAGE__EXPRESSION_CLASSES);
		}
		return expressionClasses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EPackage getEPackage() {
		if (ePackage != null && ePackage.eIsProxy()) {
			InternalEObject oldEPackage = (InternalEObject)ePackage;
			ePackage = (EPackage)eResolveProxy(oldEPackage);
			if (ePackage != oldEPackage) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ExpressionPackage.SIRIUS_EXPRESSION_PACKAGE__EPACKAGE, oldEPackage, ePackage));
			}
		}
		return ePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EPackage basicGetEPackage() {
		return ePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEPackage(EPackage newEPackage) {
		EPackage oldEPackage = ePackage;
		ePackage = newEPackage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExpressionPackage.SIRIUS_EXPRESSION_PACKAGE__EPACKAGE, oldEPackage, ePackage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ExpressionPackage.SIRIUS_EXPRESSION_PACKAGE__EXPRESSION_CLASSES:
				return ((InternalEList<?>)getExpressionClasses()).basicRemove(otherEnd, msgs);
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
			case ExpressionPackage.SIRIUS_EXPRESSION_PACKAGE__EXPRESSION_CLASSES:
				return getExpressionClasses();
			case ExpressionPackage.SIRIUS_EXPRESSION_PACKAGE__EPACKAGE:
				if (resolve) return getEPackage();
				return basicGetEPackage();
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
			case ExpressionPackage.SIRIUS_EXPRESSION_PACKAGE__EXPRESSION_CLASSES:
				getExpressionClasses().clear();
				getExpressionClasses().addAll((Collection<? extends SiriusExpressionClass>)newValue);
				return;
			case ExpressionPackage.SIRIUS_EXPRESSION_PACKAGE__EPACKAGE:
				setEPackage((EPackage)newValue);
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
			case ExpressionPackage.SIRIUS_EXPRESSION_PACKAGE__EXPRESSION_CLASSES:
				getExpressionClasses().clear();
				return;
			case ExpressionPackage.SIRIUS_EXPRESSION_PACKAGE__EPACKAGE:
				setEPackage((EPackage)null);
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
			case ExpressionPackage.SIRIUS_EXPRESSION_PACKAGE__EXPRESSION_CLASSES:
				return expressionClasses != null && !expressionClasses.isEmpty();
			case ExpressionPackage.SIRIUS_EXPRESSION_PACKAGE__EPACKAGE:
				return ePackage != null;
		}
		return super.eIsSet(featureID);
	}

} //SiriusExpressionPackageImpl
