/**
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.htm
 */
package org.eclipse.sirius.expression.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.sirius.expression.ExpressionFactory;
import org.eclipse.sirius.expression.ExpressionPackage;
import org.eclipse.sirius.expression.SiriusExpressionClass;
import org.eclipse.sirius.expression.SiriusExpressionDescription;
import org.eclipse.sirius.expression.SiriusExpressionPackage;
import org.eclipse.sirius.expression.SiriusParameter;
import org.eclipse.sirius.expression.SiriusVariable;
import org.eclipse.sirius.expression.UserDefinedVariable;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ExpressionPackageImpl extends EPackageImpl implements ExpressionPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass siriusExpressionPackageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass siriusExpressionDescriptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass siriusVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass siriusExpressionClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass siriusParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass userDefinedVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType voidEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType objectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType predicateEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType expressionEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.sirius.expression.ExpressionPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ExpressionPackageImpl() {
		super(eNS_URI, ExpressionFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link ExpressionPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ExpressionPackage init() {
		if (isInited) return (ExpressionPackage)EPackage.Registry.INSTANCE.getEPackage(ExpressionPackage.eNS_URI);

		// Obtain or create and register package
		ExpressionPackageImpl theExpressionPackage = (ExpressionPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ExpressionPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ExpressionPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theExpressionPackage.createPackageContents();

		// Initialize created meta-data
		theExpressionPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theExpressionPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ExpressionPackage.eNS_URI, theExpressionPackage);
		return theExpressionPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSiriusExpressionPackage() {
		return siriusExpressionPackageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSiriusExpressionPackage_ExpressionClasses() {
		return (EReference)siriusExpressionPackageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSiriusExpressionPackage_EPackage() {
		return (EReference)siriusExpressionPackageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSiriusExpressionDescription() {
		return siriusExpressionDescriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSiriusExpressionDescription_LowerBound() {
		return (EAttribute)siriusExpressionDescriptionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSiriusExpressionDescription_UpperBound() {
		return (EAttribute)siriusExpressionDescriptionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSiriusExpressionDescription_Expression() {
		return (EReference)siriusExpressionDescriptionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSiriusExpressionDescription_Parameters() {
		return (EReference)siriusExpressionDescriptionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSiriusExpressionDescription_ReturnType() {
		return (EReference)siriusExpressionDescriptionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSiriusExpressionDescription_VariableContainers() {
		return (EReference)siriusExpressionDescriptionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSiriusVariable() {
		return siriusVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSiriusVariable_Name() {
		return (EAttribute)siriusVariableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSiriusVariable_Documentation() {
		return (EAttribute)siriusVariableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSiriusVariable_EType() {
		return (EReference)siriusVariableEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSiriusExpressionClass() {
		return siriusExpressionClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSiriusExpressionClass_Variables() {
		return (EReference)siriusExpressionClassEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSiriusExpressionClass_ExpressionDescriptions() {
		return (EReference)siriusExpressionClassEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSiriusExpressionClass_EClass() {
		return (EReference)siriusExpressionClassEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSiriusParameter() {
		return siriusParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSiriusParameter_Optional() {
		return (EAttribute)siriusParameterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSiriusParameter_Variable() {
		return (EReference)siriusParameterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUserDefinedVariable() {
		return userDefinedVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUserDefinedVariable_ValueExpression() {
		return (EAttribute)userDefinedVariableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getVoid() {
		return voidEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getObject() {
		return objectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getPredicate() {
		return predicateEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getExpression() {
		return expressionEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpressionFactory getExpressionFactory() {
		return (ExpressionFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		siriusExpressionPackageEClass = createEClass(SIRIUS_EXPRESSION_PACKAGE);
		createEReference(siriusExpressionPackageEClass, SIRIUS_EXPRESSION_PACKAGE__EXPRESSION_CLASSES);
		createEReference(siriusExpressionPackageEClass, SIRIUS_EXPRESSION_PACKAGE__EPACKAGE);

		siriusExpressionDescriptionEClass = createEClass(SIRIUS_EXPRESSION_DESCRIPTION);
		createEAttribute(siriusExpressionDescriptionEClass, SIRIUS_EXPRESSION_DESCRIPTION__LOWER_BOUND);
		createEAttribute(siriusExpressionDescriptionEClass, SIRIUS_EXPRESSION_DESCRIPTION__UPPER_BOUND);
		createEReference(siriusExpressionDescriptionEClass, SIRIUS_EXPRESSION_DESCRIPTION__EXPRESSION);
		createEReference(siriusExpressionDescriptionEClass, SIRIUS_EXPRESSION_DESCRIPTION__PARAMETERS);
		createEReference(siriusExpressionDescriptionEClass, SIRIUS_EXPRESSION_DESCRIPTION__RETURN_TYPE);
		createEReference(siriusExpressionDescriptionEClass, SIRIUS_EXPRESSION_DESCRIPTION__VARIABLE_CONTAINERS);

		siriusVariableEClass = createEClass(SIRIUS_VARIABLE);
		createEAttribute(siriusVariableEClass, SIRIUS_VARIABLE__NAME);
		createEAttribute(siriusVariableEClass, SIRIUS_VARIABLE__DOCUMENTATION);
		createEReference(siriusVariableEClass, SIRIUS_VARIABLE__ETYPE);

		siriusExpressionClassEClass = createEClass(SIRIUS_EXPRESSION_CLASS);
		createEReference(siriusExpressionClassEClass, SIRIUS_EXPRESSION_CLASS__VARIABLES);
		createEReference(siriusExpressionClassEClass, SIRIUS_EXPRESSION_CLASS__EXPRESSION_DESCRIPTIONS);
		createEReference(siriusExpressionClassEClass, SIRIUS_EXPRESSION_CLASS__ECLASS);

		siriusParameterEClass = createEClass(SIRIUS_PARAMETER);
		createEAttribute(siriusParameterEClass, SIRIUS_PARAMETER__OPTIONAL);
		createEReference(siriusParameterEClass, SIRIUS_PARAMETER__VARIABLE);

		userDefinedVariableEClass = createEClass(USER_DEFINED_VARIABLE);
		createEAttribute(userDefinedVariableEClass, USER_DEFINED_VARIABLE__VALUE_EXPRESSION);

		// Create data types
		voidEDataType = createEDataType(VOID);
		objectEDataType = createEDataType(OBJECT);
		predicateEDataType = createEDataType(PREDICATE);
		expressionEDataType = createEDataType(EXPRESSION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		userDefinedVariableEClass.getESuperTypes().add(this.getSiriusVariable());

		// Initialize classes, features, and operations; add parameters
		initEClass(siriusExpressionPackageEClass, SiriusExpressionPackage.class, "SiriusExpressionPackage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getSiriusExpressionPackage_ExpressionClasses(), this.getSiriusExpressionClass(), null, "expressionClasses", null, 0, -1, SiriusExpressionPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSiriusExpressionPackage_EPackage(), theEcorePackage.getEPackage(), null, "ePackage", null, 0, 1, SiriusExpressionPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(siriusExpressionDescriptionEClass, SiriusExpressionDescription.class, "SiriusExpressionDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getSiriusExpressionDescription_LowerBound(), ecorePackage.getEInt(), "lowerBound", null, 0, 1, SiriusExpressionDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSiriusExpressionDescription_UpperBound(), ecorePackage.getEInt(), "upperBound", "1", 0, 1, SiriusExpressionDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getSiriusExpressionDescription_Expression(), theEcorePackage.getEStructuralFeature(), null, "expression", null, 0, 1, SiriusExpressionDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSiriusExpressionDescription_Parameters(), this.getSiriusParameter(), null, "parameters", null, 0, -1, SiriusExpressionDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSiriusExpressionDescription_ReturnType(), theEcorePackage.getEClassifier(), null, "returnType", null, 0, 1, SiriusExpressionDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSiriusExpressionDescription_VariableContainers(), theEcorePackage.getEClass(), null, "variableContainers", null, 0, -1, SiriusExpressionDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(siriusVariableEClass, SiriusVariable.class, "SiriusVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getSiriusVariable_Name(), ecorePackage.getEString(), "name", null, 0, 1, SiriusVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSiriusVariable_Documentation(), ecorePackage.getEString(), "documentation", null, 0, 1, SiriusVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSiriusVariable_EType(), theEcorePackage.getEClassifier(), null, "eType", null, 0, 1, SiriusVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(siriusExpressionClassEClass, SiriusExpressionClass.class, "SiriusExpressionClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getSiriusExpressionClass_Variables(), this.getSiriusVariable(), null, "variables", null, 0, -1, SiriusExpressionClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSiriusExpressionClass_ExpressionDescriptions(), this.getSiriusExpressionDescription(), null, "expressionDescriptions", null, 0, -1, SiriusExpressionClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSiriusExpressionClass_EClass(), theEcorePackage.getEClass(), null, "eClass", null, 0, 1, SiriusExpressionClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(siriusParameterEClass, SiriusParameter.class, "SiriusParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getSiriusParameter_Optional(), ecorePackage.getEBoolean(), "optional", null, 0, 1, SiriusParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSiriusParameter_Variable(), this.getSiriusVariable(), null, "variable", null, 0, 1, SiriusParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(userDefinedVariableEClass, UserDefinedVariable.class, "UserDefinedVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getUserDefinedVariable_ValueExpression(), this.getExpression(), "valueExpression", null, 0, 1, UserDefinedVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		// Initialize data types
		initEDataType(voidEDataType, Void.class, "Void", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(objectEDataType, Object.class, "Object", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(predicateEDataType, Object.class, "Predicate", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(expressionEDataType, String.class, "Expression", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		// Create resource
		createResource(eNS_URI);
	}

} //ExpressionPackageImpl
