/**
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.htm
 */
package org.eclipse.sirius.expression;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.sirius.expression.ExpressionFactory
 * @model kind="package"
 * @generated
 */
public interface ExpressionPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "expression"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/sirius/expression"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "expression"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ExpressionPackage eINSTANCE = org.eclipse.sirius.expression.impl.ExpressionPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.sirius.expression.impl.SiriusExpressionPackageImpl <em>Sirius Expression Package</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.sirius.expression.impl.SiriusExpressionPackageImpl
	 * @see org.eclipse.sirius.expression.impl.ExpressionPackageImpl#getSiriusExpressionPackage()
	 * @generated
	 */
	int SIRIUS_EXPRESSION_PACKAGE = 0;

	/**
	 * The feature id for the '<em><b>Expression Classes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIRIUS_EXPRESSION_PACKAGE__EXPRESSION_CLASSES = 0;

	/**
	 * The feature id for the '<em><b>EPackage</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIRIUS_EXPRESSION_PACKAGE__EPACKAGE = 1;

	/**
	 * The number of structural features of the '<em>Sirius Expression Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIRIUS_EXPRESSION_PACKAGE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Sirius Expression Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIRIUS_EXPRESSION_PACKAGE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.sirius.expression.impl.SiriusExpressionDescriptionImpl <em>Sirius Expression Description</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.sirius.expression.impl.SiriusExpressionDescriptionImpl
	 * @see org.eclipse.sirius.expression.impl.ExpressionPackageImpl#getSiriusExpressionDescription()
	 * @generated
	 */
	int SIRIUS_EXPRESSION_DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIRIUS_EXPRESSION_DESCRIPTION__LOWER_BOUND = 0;

	/**
	 * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIRIUS_EXPRESSION_DESCRIPTION__UPPER_BOUND = 1;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIRIUS_EXPRESSION_DESCRIPTION__EXPRESSION = 2;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIRIUS_EXPRESSION_DESCRIPTION__PARAMETERS = 3;

	/**
	 * The feature id for the '<em><b>Return Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIRIUS_EXPRESSION_DESCRIPTION__RETURN_TYPE = 4;

	/**
	 * The feature id for the '<em><b>Variable Containers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIRIUS_EXPRESSION_DESCRIPTION__VARIABLE_CONTAINERS = 5;

	/**
	 * The number of structural features of the '<em>Sirius Expression Description</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIRIUS_EXPRESSION_DESCRIPTION_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Sirius Expression Description</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIRIUS_EXPRESSION_DESCRIPTION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.sirius.expression.impl.SiriusVariableImpl <em>Sirius Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.sirius.expression.impl.SiriusVariableImpl
	 * @see org.eclipse.sirius.expression.impl.ExpressionPackageImpl#getSiriusVariable()
	 * @generated
	 */
	int SIRIUS_VARIABLE = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIRIUS_VARIABLE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIRIUS_VARIABLE__DOCUMENTATION = 1;

	/**
	 * The feature id for the '<em><b>EType</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIRIUS_VARIABLE__ETYPE = 2;

	/**
	 * The number of structural features of the '<em>Sirius Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIRIUS_VARIABLE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Sirius Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIRIUS_VARIABLE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.sirius.expression.impl.SiriusExpressionClassImpl <em>Sirius Expression Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.sirius.expression.impl.SiriusExpressionClassImpl
	 * @see org.eclipse.sirius.expression.impl.ExpressionPackageImpl#getSiriusExpressionClass()
	 * @generated
	 */
	int SIRIUS_EXPRESSION_CLASS = 3;

	/**
	 * The feature id for the '<em><b>Variables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIRIUS_EXPRESSION_CLASS__VARIABLES = 0;

	/**
	 * The feature id for the '<em><b>Expression Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIRIUS_EXPRESSION_CLASS__EXPRESSION_DESCRIPTIONS = 1;

	/**
	 * The feature id for the '<em><b>EClass</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIRIUS_EXPRESSION_CLASS__ECLASS = 2;

	/**
	 * The number of structural features of the '<em>Sirius Expression Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIRIUS_EXPRESSION_CLASS_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Sirius Expression Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIRIUS_EXPRESSION_CLASS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.sirius.expression.impl.SiriusParameterImpl <em>Sirius Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.sirius.expression.impl.SiriusParameterImpl
	 * @see org.eclipse.sirius.expression.impl.ExpressionPackageImpl#getSiriusParameter()
	 * @generated
	 */
	int SIRIUS_PARAMETER = 4;

	/**
	 * The feature id for the '<em><b>Optional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIRIUS_PARAMETER__OPTIONAL = 0;

	/**
	 * The feature id for the '<em><b>Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIRIUS_PARAMETER__VARIABLE = 1;

	/**
	 * The number of structural features of the '<em>Sirius Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIRIUS_PARAMETER_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Sirius Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIRIUS_PARAMETER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.sirius.expression.impl.UserDefinedVariableImpl <em>User Defined Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.sirius.expression.impl.UserDefinedVariableImpl
	 * @see org.eclipse.sirius.expression.impl.ExpressionPackageImpl#getUserDefinedVariable()
	 * @generated
	 */
	int USER_DEFINED_VARIABLE = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_VARIABLE__NAME = SIRIUS_VARIABLE__NAME;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_VARIABLE__DOCUMENTATION = SIRIUS_VARIABLE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>EType</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_VARIABLE__ETYPE = SIRIUS_VARIABLE__ETYPE;

	/**
	 * The feature id for the '<em><b>Value Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_VARIABLE__VALUE_EXPRESSION = SIRIUS_VARIABLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>User Defined Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_VARIABLE_FEATURE_COUNT = SIRIUS_VARIABLE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>User Defined Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_VARIABLE_OPERATION_COUNT = SIRIUS_VARIABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '<em>Void</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.Void
	 * @see org.eclipse.sirius.expression.impl.ExpressionPackageImpl#getVoid()
	 * @generated
	 */
	int VOID = 6;

	/**
	 * The meta object id for the '<em>Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.Object
	 * @see org.eclipse.sirius.expression.impl.ExpressionPackageImpl#getObject()
	 * @generated
	 */
	int OBJECT = 7;

	/**
	 * The meta object id for the '<em>Predicate</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.Object
	 * @see org.eclipse.sirius.expression.impl.ExpressionPackageImpl#getPredicate()
	 * @generated
	 */
	int PREDICATE = 8;


	/**
	 * The meta object id for the '<em>Expression</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.sirius.expression.impl.ExpressionPackageImpl#getExpression()
	 * @generated
	 */
	int EXPRESSION = 9;


	/**
	 * Returns the meta object for class '{@link org.eclipse.sirius.expression.SiriusExpressionPackage <em>Sirius Expression Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sirius Expression Package</em>'.
	 * @see org.eclipse.sirius.expression.SiriusExpressionPackage
	 * @generated
	 */
	EClass getSiriusExpressionPackage();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.sirius.expression.SiriusExpressionPackage#getExpressionClasses <em>Expression Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Expression Classes</em>'.
	 * @see org.eclipse.sirius.expression.SiriusExpressionPackage#getExpressionClasses()
	 * @see #getSiriusExpressionPackage()
	 * @generated
	 */
	EReference getSiriusExpressionPackage_ExpressionClasses();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.sirius.expression.SiriusExpressionPackage#getEPackage <em>EPackage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>EPackage</em>'.
	 * @see org.eclipse.sirius.expression.SiriusExpressionPackage#getEPackage()
	 * @see #getSiriusExpressionPackage()
	 * @generated
	 */
	EReference getSiriusExpressionPackage_EPackage();

	/**
	 * Returns the meta object for class '{@link org.eclipse.sirius.expression.SiriusExpressionDescription <em>Sirius Expression Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sirius Expression Description</em>'.
	 * @see org.eclipse.sirius.expression.SiriusExpressionDescription
	 * @generated
	 */
	EClass getSiriusExpressionDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.sirius.expression.SiriusExpressionDescription#getLowerBound <em>Lower Bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lower Bound</em>'.
	 * @see org.eclipse.sirius.expression.SiriusExpressionDescription#getLowerBound()
	 * @see #getSiriusExpressionDescription()
	 * @generated
	 */
	EAttribute getSiriusExpressionDescription_LowerBound();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.sirius.expression.SiriusExpressionDescription#getUpperBound <em>Upper Bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Upper Bound</em>'.
	 * @see org.eclipse.sirius.expression.SiriusExpressionDescription#getUpperBound()
	 * @see #getSiriusExpressionDescription()
	 * @generated
	 */
	EAttribute getSiriusExpressionDescription_UpperBound();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.sirius.expression.SiriusExpressionDescription#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Expression</em>'.
	 * @see org.eclipse.sirius.expression.SiriusExpressionDescription#getExpression()
	 * @see #getSiriusExpressionDescription()
	 * @generated
	 */
	EReference getSiriusExpressionDescription_Expression();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.sirius.expression.SiriusExpressionDescription#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameters</em>'.
	 * @see org.eclipse.sirius.expression.SiriusExpressionDescription#getParameters()
	 * @see #getSiriusExpressionDescription()
	 * @generated
	 */
	EReference getSiriusExpressionDescription_Parameters();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.sirius.expression.SiriusExpressionDescription#getReturnType <em>Return Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Return Type</em>'.
	 * @see org.eclipse.sirius.expression.SiriusExpressionDescription#getReturnType()
	 * @see #getSiriusExpressionDescription()
	 * @generated
	 */
	EReference getSiriusExpressionDescription_ReturnType();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.sirius.expression.SiriusExpressionDescription#getVariableContainers <em>Variable Containers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Variable Containers</em>'.
	 * @see org.eclipse.sirius.expression.SiriusExpressionDescription#getVariableContainers()
	 * @see #getSiriusExpressionDescription()
	 * @generated
	 */
	EReference getSiriusExpressionDescription_VariableContainers();

	/**
	 * Returns the meta object for class '{@link org.eclipse.sirius.expression.SiriusVariable <em>Sirius Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sirius Variable</em>'.
	 * @see org.eclipse.sirius.expression.SiriusVariable
	 * @generated
	 */
	EClass getSiriusVariable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.sirius.expression.SiriusVariable#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.sirius.expression.SiriusVariable#getName()
	 * @see #getSiriusVariable()
	 * @generated
	 */
	EAttribute getSiriusVariable_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.sirius.expression.SiriusVariable#getDocumentation <em>Documentation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Documentation</em>'.
	 * @see org.eclipse.sirius.expression.SiriusVariable#getDocumentation()
	 * @see #getSiriusVariable()
	 * @generated
	 */
	EAttribute getSiriusVariable_Documentation();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.sirius.expression.SiriusVariable#getEType <em>EType</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>EType</em>'.
	 * @see org.eclipse.sirius.expression.SiriusVariable#getEType()
	 * @see #getSiriusVariable()
	 * @generated
	 */
	EReference getSiriusVariable_EType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.sirius.expression.SiriusExpressionClass <em>Sirius Expression Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sirius Expression Class</em>'.
	 * @see org.eclipse.sirius.expression.SiriusExpressionClass
	 * @generated
	 */
	EClass getSiriusExpressionClass();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.sirius.expression.SiriusExpressionClass#getVariables <em>Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Variables</em>'.
	 * @see org.eclipse.sirius.expression.SiriusExpressionClass#getVariables()
	 * @see #getSiriusExpressionClass()
	 * @generated
	 */
	EReference getSiriusExpressionClass_Variables();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.sirius.expression.SiriusExpressionClass#getExpressionDescriptions <em>Expression Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Expression Descriptions</em>'.
	 * @see org.eclipse.sirius.expression.SiriusExpressionClass#getExpressionDescriptions()
	 * @see #getSiriusExpressionClass()
	 * @generated
	 */
	EReference getSiriusExpressionClass_ExpressionDescriptions();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.sirius.expression.SiriusExpressionClass#getEClass <em>EClass</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>EClass</em>'.
	 * @see org.eclipse.sirius.expression.SiriusExpressionClass#getEClass()
	 * @see #getSiriusExpressionClass()
	 * @generated
	 */
	EReference getSiriusExpressionClass_EClass();

	/**
	 * Returns the meta object for class '{@link org.eclipse.sirius.expression.SiriusParameter <em>Sirius Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sirius Parameter</em>'.
	 * @see org.eclipse.sirius.expression.SiriusParameter
	 * @generated
	 */
	EClass getSiriusParameter();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.sirius.expression.SiriusParameter#isOptional <em>Optional</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Optional</em>'.
	 * @see org.eclipse.sirius.expression.SiriusParameter#isOptional()
	 * @see #getSiriusParameter()
	 * @generated
	 */
	EAttribute getSiriusParameter_Optional();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.sirius.expression.SiriusParameter#getVariable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Variable</em>'.
	 * @see org.eclipse.sirius.expression.SiriusParameter#getVariable()
	 * @see #getSiriusParameter()
	 * @generated
	 */
	EReference getSiriusParameter_Variable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.sirius.expression.UserDefinedVariable <em>User Defined Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Defined Variable</em>'.
	 * @see org.eclipse.sirius.expression.UserDefinedVariable
	 * @generated
	 */
	EClass getUserDefinedVariable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.sirius.expression.UserDefinedVariable#getValueExpression <em>Value Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value Expression</em>'.
	 * @see org.eclipse.sirius.expression.UserDefinedVariable#getValueExpression()
	 * @see #getUserDefinedVariable()
	 * @generated
	 */
	EAttribute getUserDefinedVariable_ValueExpression();

	/**
	 * Returns the meta object for data type '{@link java.lang.Void <em>Void</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Void</em>'.
	 * @see java.lang.Void
	 * @model instanceClass="java.lang.Void"
	 * @generated
	 */
	EDataType getVoid();

	/**
	 * Returns the meta object for data type '{@link java.lang.Object <em>Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Object</em>'.
	 * @see java.lang.Object
	 * @model instanceClass="java.lang.Object"
	 * @generated
	 */
	EDataType getObject();

	/**
	 * Returns the meta object for data type '{@link java.lang.Object <em>Predicate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Predicate</em>'.
	 * @see java.lang.Object
	 * @model instanceClass="java.lang.Object"
	 * @generated
	 */
	EDataType getPredicate();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Expression</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 * @generated
	 */
	EDataType getExpression();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ExpressionFactory getExpressionFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.sirius.expression.impl.SiriusExpressionPackageImpl <em>Sirius Expression Package</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.sirius.expression.impl.SiriusExpressionPackageImpl
		 * @see org.eclipse.sirius.expression.impl.ExpressionPackageImpl#getSiriusExpressionPackage()
		 * @generated
		 */
		EClass SIRIUS_EXPRESSION_PACKAGE = eINSTANCE.getSiriusExpressionPackage();

		/**
		 * The meta object literal for the '<em><b>Expression Classes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SIRIUS_EXPRESSION_PACKAGE__EXPRESSION_CLASSES = eINSTANCE.getSiriusExpressionPackage_ExpressionClasses();

		/**
		 * The meta object literal for the '<em><b>EPackage</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SIRIUS_EXPRESSION_PACKAGE__EPACKAGE = eINSTANCE.getSiriusExpressionPackage_EPackage();

		/**
		 * The meta object literal for the '{@link org.eclipse.sirius.expression.impl.SiriusExpressionDescriptionImpl <em>Sirius Expression Description</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.sirius.expression.impl.SiriusExpressionDescriptionImpl
		 * @see org.eclipse.sirius.expression.impl.ExpressionPackageImpl#getSiriusExpressionDescription()
		 * @generated
		 */
		EClass SIRIUS_EXPRESSION_DESCRIPTION = eINSTANCE.getSiriusExpressionDescription();

		/**
		 * The meta object literal for the '<em><b>Lower Bound</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIRIUS_EXPRESSION_DESCRIPTION__LOWER_BOUND = eINSTANCE.getSiriusExpressionDescription_LowerBound();

		/**
		 * The meta object literal for the '<em><b>Upper Bound</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIRIUS_EXPRESSION_DESCRIPTION__UPPER_BOUND = eINSTANCE.getSiriusExpressionDescription_UpperBound();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SIRIUS_EXPRESSION_DESCRIPTION__EXPRESSION = eINSTANCE.getSiriusExpressionDescription_Expression();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SIRIUS_EXPRESSION_DESCRIPTION__PARAMETERS = eINSTANCE.getSiriusExpressionDescription_Parameters();

		/**
		 * The meta object literal for the '<em><b>Return Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SIRIUS_EXPRESSION_DESCRIPTION__RETURN_TYPE = eINSTANCE.getSiriusExpressionDescription_ReturnType();

		/**
		 * The meta object literal for the '<em><b>Variable Containers</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SIRIUS_EXPRESSION_DESCRIPTION__VARIABLE_CONTAINERS = eINSTANCE.getSiriusExpressionDescription_VariableContainers();

		/**
		 * The meta object literal for the '{@link org.eclipse.sirius.expression.impl.SiriusVariableImpl <em>Sirius Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.sirius.expression.impl.SiriusVariableImpl
		 * @see org.eclipse.sirius.expression.impl.ExpressionPackageImpl#getSiriusVariable()
		 * @generated
		 */
		EClass SIRIUS_VARIABLE = eINSTANCE.getSiriusVariable();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIRIUS_VARIABLE__NAME = eINSTANCE.getSiriusVariable_Name();

		/**
		 * The meta object literal for the '<em><b>Documentation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIRIUS_VARIABLE__DOCUMENTATION = eINSTANCE.getSiriusVariable_Documentation();

		/**
		 * The meta object literal for the '<em><b>EType</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SIRIUS_VARIABLE__ETYPE = eINSTANCE.getSiriusVariable_EType();

		/**
		 * The meta object literal for the '{@link org.eclipse.sirius.expression.impl.SiriusExpressionClassImpl <em>Sirius Expression Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.sirius.expression.impl.SiriusExpressionClassImpl
		 * @see org.eclipse.sirius.expression.impl.ExpressionPackageImpl#getSiriusExpressionClass()
		 * @generated
		 */
		EClass SIRIUS_EXPRESSION_CLASS = eINSTANCE.getSiriusExpressionClass();

		/**
		 * The meta object literal for the '<em><b>Variables</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SIRIUS_EXPRESSION_CLASS__VARIABLES = eINSTANCE.getSiriusExpressionClass_Variables();

		/**
		 * The meta object literal for the '<em><b>Expression Descriptions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SIRIUS_EXPRESSION_CLASS__EXPRESSION_DESCRIPTIONS = eINSTANCE.getSiriusExpressionClass_ExpressionDescriptions();

		/**
		 * The meta object literal for the '<em><b>EClass</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SIRIUS_EXPRESSION_CLASS__ECLASS = eINSTANCE.getSiriusExpressionClass_EClass();

		/**
		 * The meta object literal for the '{@link org.eclipse.sirius.expression.impl.SiriusParameterImpl <em>Sirius Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.sirius.expression.impl.SiriusParameterImpl
		 * @see org.eclipse.sirius.expression.impl.ExpressionPackageImpl#getSiriusParameter()
		 * @generated
		 */
		EClass SIRIUS_PARAMETER = eINSTANCE.getSiriusParameter();

		/**
		 * The meta object literal for the '<em><b>Optional</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIRIUS_PARAMETER__OPTIONAL = eINSTANCE.getSiriusParameter_Optional();

		/**
		 * The meta object literal for the '<em><b>Variable</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SIRIUS_PARAMETER__VARIABLE = eINSTANCE.getSiriusParameter_Variable();

		/**
		 * The meta object literal for the '{@link org.eclipse.sirius.expression.impl.UserDefinedVariableImpl <em>User Defined Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.sirius.expression.impl.UserDefinedVariableImpl
		 * @see org.eclipse.sirius.expression.impl.ExpressionPackageImpl#getUserDefinedVariable()
		 * @generated
		 */
		EClass USER_DEFINED_VARIABLE = eINSTANCE.getUserDefinedVariable();

		/**
		 * The meta object literal for the '<em><b>Value Expression</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER_DEFINED_VARIABLE__VALUE_EXPRESSION = eINSTANCE.getUserDefinedVariable_ValueExpression();

		/**
		 * The meta object literal for the '<em>Void</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Void
		 * @see org.eclipse.sirius.expression.impl.ExpressionPackageImpl#getVoid()
		 * @generated
		 */
		EDataType VOID = eINSTANCE.getVoid();

		/**
		 * The meta object literal for the '<em>Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Object
		 * @see org.eclipse.sirius.expression.impl.ExpressionPackageImpl#getObject()
		 * @generated
		 */
		EDataType OBJECT = eINSTANCE.getObject();

		/**
		 * The meta object literal for the '<em>Predicate</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Object
		 * @see org.eclipse.sirius.expression.impl.ExpressionPackageImpl#getPredicate()
		 * @generated
		 */
		EDataType PREDICATE = eINSTANCE.getPredicate();

		/**
		 * The meta object literal for the '<em>Expression</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.String
		 * @see org.eclipse.sirius.expression.impl.ExpressionPackageImpl#getExpression()
		 * @generated
		 */
		EDataType EXPRESSION = eINSTANCE.getExpression();

	}

} //ExpressionPackage
