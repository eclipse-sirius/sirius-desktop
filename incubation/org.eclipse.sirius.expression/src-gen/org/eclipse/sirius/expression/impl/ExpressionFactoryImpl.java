/**
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.htm
 */
package org.eclipse.sirius.expression.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.sirius.expression.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ExpressionFactoryImpl extends EFactoryImpl implements ExpressionFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ExpressionFactory init() {
		try {
			ExpressionFactory theExpressionFactory = (ExpressionFactory)EPackage.Registry.INSTANCE.getEFactory(ExpressionPackage.eNS_URI);
			if (theExpressionFactory != null) {
				return theExpressionFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ExpressionFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpressionFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ExpressionPackage.SIRIUS_EXPRESSION_PACKAGE: return createSiriusExpressionPackage();
			case ExpressionPackage.SIRIUS_EXPRESSION_DESCRIPTION: return createSiriusExpressionDescription();
			case ExpressionPackage.SIRIUS_VARIABLE: return createSiriusVariable();
			case ExpressionPackage.SIRIUS_EXPRESSION_CLASS: return createSiriusExpressionClass();
			case ExpressionPackage.SIRIUS_PARAMETER: return createSiriusParameter();
			case ExpressionPackage.USER_DEFINED_VARIABLE: return createUserDefinedVariable();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case ExpressionPackage.VOID:
				return createVoidFromString(eDataType, initialValue);
			case ExpressionPackage.OBJECT:
				return createObjectFromString(eDataType, initialValue);
			case ExpressionPackage.PREDICATE:
				return createPredicateFromString(eDataType, initialValue);
			case ExpressionPackage.EXPRESSION:
				return createExpressionFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case ExpressionPackage.VOID:
				return convertVoidToString(eDataType, instanceValue);
			case ExpressionPackage.OBJECT:
				return convertObjectToString(eDataType, instanceValue);
			case ExpressionPackage.PREDICATE:
				return convertPredicateToString(eDataType, instanceValue);
			case ExpressionPackage.EXPRESSION:
				return convertExpressionToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SiriusExpressionPackage createSiriusExpressionPackage() {
		SiriusExpressionPackageImpl siriusExpressionPackage = new SiriusExpressionPackageImpl();
		return siriusExpressionPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SiriusExpressionDescription createSiriusExpressionDescription() {
		SiriusExpressionDescriptionImpl siriusExpressionDescription = new SiriusExpressionDescriptionImpl();
		return siriusExpressionDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SiriusVariable createSiriusVariable() {
		SiriusVariableImpl siriusVariable = new SiriusVariableImpl();
		return siriusVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SiriusExpressionClass createSiriusExpressionClass() {
		SiriusExpressionClassImpl siriusExpressionClass = new SiriusExpressionClassImpl();
		return siriusExpressionClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SiriusParameter createSiriusParameter() {
		SiriusParameterImpl siriusParameter = new SiriusParameterImpl();
		return siriusParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UserDefinedVariable createUserDefinedVariable() {
		UserDefinedVariableImpl userDefinedVariable = new UserDefinedVariableImpl();
		return userDefinedVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Void createVoidFromString(EDataType eDataType, String initialValue) {
		return (Void)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertVoidToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object createObjectFromString(EDataType eDataType, String initialValue) {
		return super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertObjectToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object createPredicateFromString(EDataType eDataType, String initialValue) {
		return super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPredicateToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createExpressionFromString(EDataType eDataType, String initialValue) {
		return (String)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertExpressionToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpressionPackage getExpressionPackage() {
		return (ExpressionPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ExpressionPackage getPackage() {
		return ExpressionPackage.eINSTANCE;
	}

} //ExpressionFactoryImpl
