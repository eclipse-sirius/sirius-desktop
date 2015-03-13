/**
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Obeo - initial API and implementation
 */
package org.eclipse.sirius.tests.sample.scxml.impl;

import java.math.BigInteger;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.namespace.XMLNamespacePackage;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.eclipse.sirius.tests.sample.scxml.AssignTypeDatatype;
import org.eclipse.sirius.tests.sample.scxml.BindingDatatype;
import org.eclipse.sirius.tests.sample.scxml.BooleanDatatype;
import org.eclipse.sirius.tests.sample.scxml.DocumentRoot;
import org.eclipse.sirius.tests.sample.scxml.ExmodeDatatype;
import org.eclipse.sirius.tests.sample.scxml.HistoryTypeDatatype;
import org.eclipse.sirius.tests.sample.scxml.ScxmlAssignType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlCancelType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlContentType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlDataType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlDatamodelType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlDonedataType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlElseType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlElseifType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlFactory;
import org.eclipse.sirius.tests.sample.scxml.ScxmlFinalType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlFinalizeType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlHistoryType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlIfType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlInitialType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlLogType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlOnentryType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlOnexitType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlPackage;
import org.eclipse.sirius.tests.sample.scxml.ScxmlParallelType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlParamType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlRaiseType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlScriptType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlSendType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlStateType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType;
import org.eclipse.sirius.tests.sample.scxml.TransitionTypeDatatype;

import org.eclipse.sirius.tests.sample.scxml.util.ScxmlValidator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ScxmlPackageImpl extends EPackageImpl implements ScxmlPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass documentRootEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass scxmlAssignTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass scxmlCancelTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass scxmlContentTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass scxmlDatamodelTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass scxmlDataTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass scxmlDonedataTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass scxmlElseifTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass scxmlElseTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass scxmlFinalizeTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass scxmlFinalTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass scxmlForeachTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass scxmlHistoryTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass scxmlIfTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass scxmlInitialTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass scxmlInvokeTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass scxmlLogTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass scxmlOnentryTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass scxmlOnexitTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass scxmlParallelTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass scxmlParamTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass scxmlRaiseTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass scxmlScriptTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass scxmlScxmlTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass scxmlSendTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass scxmlStateTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass scxmlTransitionTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum assignTypeDatatypeEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum bindingDatatypeEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum booleanDatatypeEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum exmodeDatatypeEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum historyTypeDatatypeEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum transitionTypeDatatypeEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType assignTypeDatatypeObjectEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType bindingDatatypeObjectEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType booleanDatatypeObjectEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType condLangDatatypeEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType durationDatatypeEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType eventTypeDatatypeEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType eventTypesDatatypeEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType exmodeDatatypeObjectEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType historyTypeDatatypeObjectEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType integerDatatypeEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType locLangDatatypeEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType transitionTypeDatatypeObjectEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType uriDatatypeEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType valueLangDatatypeEDataType = null;

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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private ScxmlPackageImpl() {
        super(eNS_URI, ScxmlFactory.eINSTANCE);
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
     * <p>This method is used to initialize {@link ScxmlPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static ScxmlPackage init() {
        if (isInited) return (ScxmlPackage)EPackage.Registry.INSTANCE.getEPackage(ScxmlPackage.eNS_URI);

        // Obtain or create and register package
        ScxmlPackageImpl theScxmlPackage = (ScxmlPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ScxmlPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ScxmlPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        XMLNamespacePackage.eINSTANCE.eClass();
        XMLTypePackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theScxmlPackage.createPackageContents();

        // Initialize created meta-data
        theScxmlPackage.initializePackageContents();

        // Register package validator
        EValidator.Registry.INSTANCE.put
            (theScxmlPackage, 
             new EValidator.Descriptor() {
                 public EValidator getEValidator() {
                     return ScxmlValidator.INSTANCE;
                 }
             });

        // Mark meta-data to indicate it can't be changed
        theScxmlPackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(ScxmlPackage.eNS_URI, theScxmlPackage);
        return theScxmlPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDocumentRoot() {
        return documentRootEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentRoot_Mixed() {
        return (EAttribute)documentRootEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_XMLNSPrefixMap() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_XSISchemaLocation() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Assign() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Cancel() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Content() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Data() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Datamodel() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Donedata() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Else() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Elseif() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Final() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Finalize() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Foreach() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_History() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(14);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_If() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(15);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Initial() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(16);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Invoke() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(17);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Log() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(18);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Onentry() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(19);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Onexit() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(20);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Parallel() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(21);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Param() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(22);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Raise() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(23);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Script() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(24);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Scxml() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(25);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Send() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(26);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_State() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(27);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Transition() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(28);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getScxmlAssignType() {
        return scxmlAssignTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlAssignType_Mixed() {
        return (EAttribute)scxmlAssignTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlAssignType_Any() {
        return (EAttribute)scxmlAssignTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlAssignType_Attr() {
        return (EAttribute)scxmlAssignTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlAssignType_Expr() {
        return (EAttribute)scxmlAssignTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlAssignType_Location() {
        return (EAttribute)scxmlAssignTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlAssignType_Type() {
        return (EAttribute)scxmlAssignTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlAssignType_AnyAttribute() {
        return (EAttribute)scxmlAssignTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getScxmlCancelType() {
        return scxmlCancelTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlCancelType_ScxmlExtraContent() {
        return (EAttribute)scxmlCancelTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlCancelType_Any() {
        return (EAttribute)scxmlCancelTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlCancelType_Sendid() {
        return (EAttribute)scxmlCancelTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlCancelType_Sendidexpr() {
        return (EAttribute)scxmlCancelTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlCancelType_AnyAttribute() {
        return (EAttribute)scxmlCancelTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getScxmlContentType() {
        return scxmlContentTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlContentType_Mixed() {
        return (EAttribute)scxmlContentTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlContentType_Any() {
        return (EAttribute)scxmlContentTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlContentType_Expr() {
        return (EAttribute)scxmlContentTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlContentType_AnyAttribute() {
        return (EAttribute)scxmlContentTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getScxmlDatamodelType() {
        return scxmlDatamodelTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlDatamodelType_Data() {
        return (EReference)scxmlDatamodelTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlDatamodelType_ScxmlExtraContent() {
        return (EAttribute)scxmlDatamodelTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlDatamodelType_Any() {
        return (EAttribute)scxmlDatamodelTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlDatamodelType_AnyAttribute() {
        return (EAttribute)scxmlDatamodelTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getScxmlDataType() {
        return scxmlDataTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlDataType_Mixed() {
        return (EAttribute)scxmlDataTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlDataType_Any() {
        return (EAttribute)scxmlDataTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlDataType_Expr() {
        return (EAttribute)scxmlDataTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlDataType_Id() {
        return (EAttribute)scxmlDataTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlDataType_Src() {
        return (EAttribute)scxmlDataTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlDataType_AnyAttribute() {
        return (EAttribute)scxmlDataTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getScxmlDonedataType() {
        return scxmlDonedataTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlDonedataType_Content() {
        return (EReference)scxmlDonedataTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlDonedataType_Param() {
        return (EReference)scxmlDonedataTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlDonedataType_AnyAttribute() {
        return (EAttribute)scxmlDonedataTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getScxmlElseifType() {
        return scxmlElseifTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlElseifType_Cond() {
        return (EAttribute)scxmlElseifTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlElseifType_AnyAttribute() {
        return (EAttribute)scxmlElseifTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getScxmlElseType() {
        return scxmlElseTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlElseType_AnyAttribute() {
        return (EAttribute)scxmlElseTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getScxmlFinalizeType() {
        return scxmlFinalizeTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlFinalizeType_ScxmlCoreExecutablecontent() {
        return (EAttribute)scxmlFinalizeTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlFinalizeType_Any() {
        return (EAttribute)scxmlFinalizeTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlFinalizeType_Raise() {
        return (EReference)scxmlFinalizeTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlFinalizeType_If() {
        return (EReference)scxmlFinalizeTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlFinalizeType_Foreach() {
        return (EReference)scxmlFinalizeTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlFinalizeType_Send() {
        return (EReference)scxmlFinalizeTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlFinalizeType_Script() {
        return (EReference)scxmlFinalizeTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlFinalizeType_Assign() {
        return (EReference)scxmlFinalizeTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlFinalizeType_Log() {
        return (EReference)scxmlFinalizeTypeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlFinalizeType_Cancel() {
        return (EReference)scxmlFinalizeTypeEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlFinalizeType_AnyAttribute() {
        return (EAttribute)scxmlFinalizeTypeEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getScxmlFinalType() {
        return scxmlFinalTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlFinalType_ScxmlFinalMix() {
        return (EAttribute)scxmlFinalTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlFinalType_Onentry() {
        return (EReference)scxmlFinalTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlFinalType_Onexit() {
        return (EReference)scxmlFinalTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlFinalType_Donedata() {
        return (EReference)scxmlFinalTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlFinalType_Any() {
        return (EAttribute)scxmlFinalTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlFinalType_Id() {
        return (EAttribute)scxmlFinalTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlFinalType_AnyAttribute() {
        return (EAttribute)scxmlFinalTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getScxmlForeachType() {
        return scxmlForeachTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlForeachType_ScxmlCoreExecutablecontent() {
        return (EAttribute)scxmlForeachTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlForeachType_Any() {
        return (EAttribute)scxmlForeachTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlForeachType_Raise() {
        return (EReference)scxmlForeachTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlForeachType_If() {
        return (EReference)scxmlForeachTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlForeachType_Foreach() {
        return (EReference)scxmlForeachTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlForeachType_Send() {
        return (EReference)scxmlForeachTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlForeachType_Script() {
        return (EReference)scxmlForeachTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlForeachType_Assign() {
        return (EReference)scxmlForeachTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlForeachType_Log() {
        return (EReference)scxmlForeachTypeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlForeachType_Cancel() {
        return (EReference)scxmlForeachTypeEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlForeachType_Array() {
        return (EAttribute)scxmlForeachTypeEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlForeachType_Index() {
        return (EAttribute)scxmlForeachTypeEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlForeachType_Item() {
        return (EAttribute)scxmlForeachTypeEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlForeachType_AnyAttribute() {
        return (EAttribute)scxmlForeachTypeEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getScxmlHistoryType() {
        return scxmlHistoryTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlHistoryType_ScxmlExtraContent() {
        return (EAttribute)scxmlHistoryTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlHistoryType_Any() {
        return (EAttribute)scxmlHistoryTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlHistoryType_Transition() {
        return (EReference)scxmlHistoryTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlHistoryType_ScxmlExtraContent1() {
        return (EAttribute)scxmlHistoryTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlHistoryType_Any1() {
        return (EAttribute)scxmlHistoryTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlHistoryType_Id() {
        return (EAttribute)scxmlHistoryTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlHistoryType_Type() {
        return (EAttribute)scxmlHistoryTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlHistoryType_AnyAttribute() {
        return (EAttribute)scxmlHistoryTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getScxmlIfType() {
        return scxmlIfTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlIfType_ScxmlCoreExecutablecontent() {
        return (EAttribute)scxmlIfTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlIfType_Any() {
        return (EAttribute)scxmlIfTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlIfType_Raise() {
        return (EReference)scxmlIfTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlIfType_If() {
        return (EReference)scxmlIfTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlIfType_Foreach() {
        return (EReference)scxmlIfTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlIfType_Send() {
        return (EReference)scxmlIfTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlIfType_Script() {
        return (EReference)scxmlIfTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlIfType_Assign() {
        return (EReference)scxmlIfTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlIfType_Log() {
        return (EReference)scxmlIfTypeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlIfType_Cancel() {
        return (EReference)scxmlIfTypeEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlIfType_Elseif() {
        return (EReference)scxmlIfTypeEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlIfType_ScxmlCoreExecutablecontent1() {
        return (EAttribute)scxmlIfTypeEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlIfType_Any1() {
        return (EAttribute)scxmlIfTypeEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlIfType_Raise1() {
        return (EReference)scxmlIfTypeEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlIfType_If1() {
        return (EReference)scxmlIfTypeEClass.getEStructuralFeatures().get(14);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlIfType_Foreach1() {
        return (EReference)scxmlIfTypeEClass.getEStructuralFeatures().get(15);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlIfType_Send1() {
        return (EReference)scxmlIfTypeEClass.getEStructuralFeatures().get(16);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlIfType_Script1() {
        return (EReference)scxmlIfTypeEClass.getEStructuralFeatures().get(17);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlIfType_Assign1() {
        return (EReference)scxmlIfTypeEClass.getEStructuralFeatures().get(18);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlIfType_Log1() {
        return (EReference)scxmlIfTypeEClass.getEStructuralFeatures().get(19);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlIfType_Cancel1() {
        return (EReference)scxmlIfTypeEClass.getEStructuralFeatures().get(20);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlIfType_Else() {
        return (EReference)scxmlIfTypeEClass.getEStructuralFeatures().get(21);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlIfType_ScxmlCoreExecutablecontent2() {
        return (EAttribute)scxmlIfTypeEClass.getEStructuralFeatures().get(22);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlIfType_Any2() {
        return (EAttribute)scxmlIfTypeEClass.getEStructuralFeatures().get(23);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlIfType_Raise2() {
        return (EReference)scxmlIfTypeEClass.getEStructuralFeatures().get(24);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlIfType_If2() {
        return (EReference)scxmlIfTypeEClass.getEStructuralFeatures().get(25);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlIfType_Foreach2() {
        return (EReference)scxmlIfTypeEClass.getEStructuralFeatures().get(26);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlIfType_Send2() {
        return (EReference)scxmlIfTypeEClass.getEStructuralFeatures().get(27);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlIfType_Script2() {
        return (EReference)scxmlIfTypeEClass.getEStructuralFeatures().get(28);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlIfType_Assign2() {
        return (EReference)scxmlIfTypeEClass.getEStructuralFeatures().get(29);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlIfType_Log2() {
        return (EReference)scxmlIfTypeEClass.getEStructuralFeatures().get(30);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlIfType_Cancel2() {
        return (EReference)scxmlIfTypeEClass.getEStructuralFeatures().get(31);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlIfType_Cond() {
        return (EAttribute)scxmlIfTypeEClass.getEStructuralFeatures().get(32);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlIfType_AnyAttribute() {
        return (EAttribute)scxmlIfTypeEClass.getEStructuralFeatures().get(33);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getScxmlInitialType() {
        return scxmlInitialTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlInitialType_ScxmlExtraContent() {
        return (EAttribute)scxmlInitialTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlInitialType_Any() {
        return (EAttribute)scxmlInitialTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlInitialType_Transition() {
        return (EReference)scxmlInitialTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlInitialType_ScxmlExtraContent1() {
        return (EAttribute)scxmlInitialTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlInitialType_Any1() {
        return (EAttribute)scxmlInitialTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlInitialType_AnyAttribute() {
        return (EAttribute)scxmlInitialTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getScxmlInvokeType() {
        return scxmlInvokeTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlInvokeType_ScxmlInvokeMix() {
        return (EAttribute)scxmlInvokeTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlInvokeType_Content() {
        return (EReference)scxmlInvokeTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlInvokeType_Param() {
        return (EReference)scxmlInvokeTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlInvokeType_Finalize() {
        return (EReference)scxmlInvokeTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlInvokeType_Any() {
        return (EAttribute)scxmlInvokeTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlInvokeType_Autoforward() {
        return (EAttribute)scxmlInvokeTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlInvokeType_Id() {
        return (EAttribute)scxmlInvokeTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlInvokeType_Idlocation() {
        return (EAttribute)scxmlInvokeTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlInvokeType_Namelist() {
        return (EAttribute)scxmlInvokeTypeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlInvokeType_Src() {
        return (EAttribute)scxmlInvokeTypeEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlInvokeType_Srcexpr() {
        return (EAttribute)scxmlInvokeTypeEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlInvokeType_Type() {
        return (EAttribute)scxmlInvokeTypeEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlInvokeType_Typeexpr() {
        return (EAttribute)scxmlInvokeTypeEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlInvokeType_AnyAttribute() {
        return (EAttribute)scxmlInvokeTypeEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getScxmlLogType() {
        return scxmlLogTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlLogType_ScxmlExtraContent() {
        return (EAttribute)scxmlLogTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlLogType_Any() {
        return (EAttribute)scxmlLogTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlLogType_Expr() {
        return (EAttribute)scxmlLogTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlLogType_Label() {
        return (EAttribute)scxmlLogTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlLogType_AnyAttribute() {
        return (EAttribute)scxmlLogTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getScxmlOnentryType() {
        return scxmlOnentryTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlOnentryType_ScxmlCoreExecutablecontent() {
        return (EAttribute)scxmlOnentryTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlOnentryType_Any() {
        return (EAttribute)scxmlOnentryTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlOnentryType_Raise() {
        return (EReference)scxmlOnentryTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlOnentryType_If() {
        return (EReference)scxmlOnentryTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlOnentryType_Foreach() {
        return (EReference)scxmlOnentryTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlOnentryType_Send() {
        return (EReference)scxmlOnentryTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlOnentryType_Script() {
        return (EReference)scxmlOnentryTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlOnentryType_Assign() {
        return (EReference)scxmlOnentryTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlOnentryType_Log() {
        return (EReference)scxmlOnentryTypeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlOnentryType_Cancel() {
        return (EReference)scxmlOnentryTypeEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlOnentryType_AnyAttribute() {
        return (EAttribute)scxmlOnentryTypeEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getScxmlOnexitType() {
        return scxmlOnexitTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlOnexitType_ScxmlCoreExecutablecontent() {
        return (EAttribute)scxmlOnexitTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlOnexitType_Any() {
        return (EAttribute)scxmlOnexitTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlOnexitType_Raise() {
        return (EReference)scxmlOnexitTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlOnexitType_If() {
        return (EReference)scxmlOnexitTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlOnexitType_Foreach() {
        return (EReference)scxmlOnexitTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlOnexitType_Send() {
        return (EReference)scxmlOnexitTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlOnexitType_Script() {
        return (EReference)scxmlOnexitTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlOnexitType_Assign() {
        return (EReference)scxmlOnexitTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlOnexitType_Log() {
        return (EReference)scxmlOnexitTypeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlOnexitType_Cancel() {
        return (EReference)scxmlOnexitTypeEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlOnexitType_AnyAttribute() {
        return (EAttribute)scxmlOnexitTypeEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getScxmlParallelType() {
        return scxmlParallelTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlParallelType_ScxmlParallelMix() {
        return (EAttribute)scxmlParallelTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlParallelType_Onentry() {
        return (EReference)scxmlParallelTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlParallelType_Onexit() {
        return (EReference)scxmlParallelTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlParallelType_Transition() {
        return (EReference)scxmlParallelTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlParallelType_State() {
        return (EReference)scxmlParallelTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlParallelType_Parallel() {
        return (EReference)scxmlParallelTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlParallelType_History() {
        return (EReference)scxmlParallelTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlParallelType_Datamodel() {
        return (EReference)scxmlParallelTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlParallelType_Invoke() {
        return (EReference)scxmlParallelTypeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlParallelType_Any() {
        return (EAttribute)scxmlParallelTypeEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlParallelType_Id() {
        return (EAttribute)scxmlParallelTypeEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlParallelType_AnyAttribute() {
        return (EAttribute)scxmlParallelTypeEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getScxmlParamType() {
        return scxmlParamTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlParamType_ScxmlExtraContent() {
        return (EAttribute)scxmlParamTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlParamType_Any() {
        return (EAttribute)scxmlParamTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlParamType_Expr() {
        return (EAttribute)scxmlParamTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlParamType_Location() {
        return (EAttribute)scxmlParamTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlParamType_Name() {
        return (EAttribute)scxmlParamTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlParamType_AnyAttribute() {
        return (EAttribute)scxmlParamTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getScxmlRaiseType() {
        return scxmlRaiseTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlRaiseType_Event() {
        return (EAttribute)scxmlRaiseTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlRaiseType_AnyAttribute() {
        return (EAttribute)scxmlRaiseTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getScxmlScriptType() {
        return scxmlScriptTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlScriptType_Mixed() {
        return (EAttribute)scxmlScriptTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlScriptType_ScxmlExtraContent() {
        return (EAttribute)scxmlScriptTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlScriptType_Any() {
        return (EAttribute)scxmlScriptTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlScriptType_Src() {
        return (EAttribute)scxmlScriptTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlScriptType_AnyAttribute() {
        return (EAttribute)scxmlScriptTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getScxmlScxmlType() {
        return scxmlScxmlTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlScxmlType_ScxmlScxmlMix() {
        return (EAttribute)scxmlScxmlTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlScxmlType_State() {
        return (EReference)scxmlScxmlTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlScxmlType_Parallel() {
        return (EReference)scxmlScxmlTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlScxmlType_Final() {
        return (EReference)scxmlScxmlTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlScxmlType_Datamodel() {
        return (EReference)scxmlScxmlTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlScxmlType_Script() {
        return (EReference)scxmlScxmlTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlScxmlType_Any() {
        return (EAttribute)scxmlScxmlTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlScxmlType_Binding() {
        return (EAttribute)scxmlScxmlTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlScxmlType_Datamodel1() {
        return (EAttribute)scxmlScxmlTypeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlScxmlType_Exmode() {
        return (EAttribute)scxmlScxmlTypeEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlScxmlType_Initial() {
        return (EAttribute)scxmlScxmlTypeEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlScxmlType_Name() {
        return (EAttribute)scxmlScxmlTypeEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlScxmlType_Version() {
        return (EAttribute)scxmlScxmlTypeEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlScxmlType_AnyAttribute() {
        return (EAttribute)scxmlScxmlTypeEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getScxmlSendType() {
        return scxmlSendTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlSendType_ScxmlSendMix() {
        return (EAttribute)scxmlSendTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlSendType_Content() {
        return (EReference)scxmlSendTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlSendType_Param() {
        return (EReference)scxmlSendTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlSendType_Any() {
        return (EAttribute)scxmlSendTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlSendType_Delay() {
        return (EAttribute)scxmlSendTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlSendType_Delayexpr() {
        return (EAttribute)scxmlSendTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlSendType_Event() {
        return (EAttribute)scxmlSendTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlSendType_Eventexpr() {
        return (EAttribute)scxmlSendTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlSendType_Id() {
        return (EAttribute)scxmlSendTypeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlSendType_Idlocation() {
        return (EAttribute)scxmlSendTypeEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlSendType_Namelist() {
        return (EAttribute)scxmlSendTypeEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlSendType_Target() {
        return (EAttribute)scxmlSendTypeEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlSendType_Targetexpr() {
        return (EAttribute)scxmlSendTypeEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlSendType_Type() {
        return (EAttribute)scxmlSendTypeEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlSendType_Typeexpr() {
        return (EAttribute)scxmlSendTypeEClass.getEStructuralFeatures().get(14);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlSendType_AnyAttribute() {
        return (EAttribute)scxmlSendTypeEClass.getEStructuralFeatures().get(15);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getScxmlStateType() {
        return scxmlStateTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlStateType_ScxmlStateMix() {
        return (EAttribute)scxmlStateTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlStateType_Onentry() {
        return (EReference)scxmlStateTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlStateType_Onexit() {
        return (EReference)scxmlStateTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlStateType_Transition() {
        return (EReference)scxmlStateTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlStateType_Initial() {
        return (EReference)scxmlStateTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlStateType_State() {
        return (EReference)scxmlStateTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlStateType_Parallel() {
        return (EReference)scxmlStateTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlStateType_Final() {
        return (EReference)scxmlStateTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlStateType_History() {
        return (EReference)scxmlStateTypeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlStateType_Datamodel() {
        return (EReference)scxmlStateTypeEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlStateType_Invoke() {
        return (EReference)scxmlStateTypeEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlStateType_Any() {
        return (EAttribute)scxmlStateTypeEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlStateType_Id() {
        return (EAttribute)scxmlStateTypeEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlStateType_Initial1() {
        return (EAttribute)scxmlStateTypeEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlStateType_AnyAttribute() {
        return (EAttribute)scxmlStateTypeEClass.getEStructuralFeatures().get(14);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getScxmlTransitionType() {
        return scxmlTransitionTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlTransitionType_ScxmlCoreExecutablecontent() {
        return (EAttribute)scxmlTransitionTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlTransitionType_Any() {
        return (EAttribute)scxmlTransitionTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlTransitionType_Raise() {
        return (EReference)scxmlTransitionTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlTransitionType_If() {
        return (EReference)scxmlTransitionTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlTransitionType_Foreach() {
        return (EReference)scxmlTransitionTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlTransitionType_Send() {
        return (EReference)scxmlTransitionTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlTransitionType_Script() {
        return (EReference)scxmlTransitionTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlTransitionType_Assign() {
        return (EReference)scxmlTransitionTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlTransitionType_Log() {
        return (EReference)scxmlTransitionTypeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScxmlTransitionType_Cancel() {
        return (EReference)scxmlTransitionTypeEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlTransitionType_Cond() {
        return (EAttribute)scxmlTransitionTypeEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlTransitionType_Event() {
        return (EAttribute)scxmlTransitionTypeEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlTransitionType_Target() {
        return (EAttribute)scxmlTransitionTypeEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlTransitionType_Type() {
        return (EAttribute)scxmlTransitionTypeEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScxmlTransitionType_AnyAttribute() {
        return (EAttribute)scxmlTransitionTypeEClass.getEStructuralFeatures().get(14);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getAssignTypeDatatype() {
        return assignTypeDatatypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getBindingDatatype() {
        return bindingDatatypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getBooleanDatatype() {
        return booleanDatatypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getExmodeDatatype() {
        return exmodeDatatypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getHistoryTypeDatatype() {
        return historyTypeDatatypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getTransitionTypeDatatype() {
        return transitionTypeDatatypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getAssignTypeDatatypeObject() {
        return assignTypeDatatypeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getBindingDatatypeObject() {
        return bindingDatatypeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getBooleanDatatypeObject() {
        return booleanDatatypeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getCondLangDatatype() {
        return condLangDatatypeEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getDurationDatatype() {
        return durationDatatypeEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getEventTypeDatatype() {
        return eventTypeDatatypeEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getEventTypesDatatype() {
        return eventTypesDatatypeEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getExmodeDatatypeObject() {
        return exmodeDatatypeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getHistoryTypeDatatypeObject() {
        return historyTypeDatatypeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getIntegerDatatype() {
        return integerDatatypeEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getLocLangDatatype() {
        return locLangDatatypeEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getTransitionTypeDatatypeObject() {
        return transitionTypeDatatypeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getURIDatatype() {
        return uriDatatypeEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getValueLangDatatype() {
        return valueLangDatatypeEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ScxmlFactory getScxmlFactory() {
        return (ScxmlFactory)getEFactoryInstance();
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
        documentRootEClass = createEClass(DOCUMENT_ROOT);
        createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
        createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
        createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
        createEReference(documentRootEClass, DOCUMENT_ROOT__ASSIGN);
        createEReference(documentRootEClass, DOCUMENT_ROOT__CANCEL);
        createEReference(documentRootEClass, DOCUMENT_ROOT__CONTENT);
        createEReference(documentRootEClass, DOCUMENT_ROOT__DATA);
        createEReference(documentRootEClass, DOCUMENT_ROOT__DATAMODEL);
        createEReference(documentRootEClass, DOCUMENT_ROOT__DONEDATA);
        createEReference(documentRootEClass, DOCUMENT_ROOT__ELSE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__ELSEIF);
        createEReference(documentRootEClass, DOCUMENT_ROOT__FINAL);
        createEReference(documentRootEClass, DOCUMENT_ROOT__FINALIZE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__FOREACH);
        createEReference(documentRootEClass, DOCUMENT_ROOT__HISTORY);
        createEReference(documentRootEClass, DOCUMENT_ROOT__IF);
        createEReference(documentRootEClass, DOCUMENT_ROOT__INITIAL);
        createEReference(documentRootEClass, DOCUMENT_ROOT__INVOKE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__LOG);
        createEReference(documentRootEClass, DOCUMENT_ROOT__ONENTRY);
        createEReference(documentRootEClass, DOCUMENT_ROOT__ONEXIT);
        createEReference(documentRootEClass, DOCUMENT_ROOT__PARALLEL);
        createEReference(documentRootEClass, DOCUMENT_ROOT__PARAM);
        createEReference(documentRootEClass, DOCUMENT_ROOT__RAISE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__SCRIPT);
        createEReference(documentRootEClass, DOCUMENT_ROOT__SCXML);
        createEReference(documentRootEClass, DOCUMENT_ROOT__SEND);
        createEReference(documentRootEClass, DOCUMENT_ROOT__STATE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__TRANSITION);

        scxmlAssignTypeEClass = createEClass(SCXML_ASSIGN_TYPE);
        createEAttribute(scxmlAssignTypeEClass, SCXML_ASSIGN_TYPE__MIXED);
        createEAttribute(scxmlAssignTypeEClass, SCXML_ASSIGN_TYPE__ANY);
        createEAttribute(scxmlAssignTypeEClass, SCXML_ASSIGN_TYPE__ATTR);
        createEAttribute(scxmlAssignTypeEClass, SCXML_ASSIGN_TYPE__EXPR);
        createEAttribute(scxmlAssignTypeEClass, SCXML_ASSIGN_TYPE__LOCATION);
        createEAttribute(scxmlAssignTypeEClass, SCXML_ASSIGN_TYPE__TYPE);
        createEAttribute(scxmlAssignTypeEClass, SCXML_ASSIGN_TYPE__ANY_ATTRIBUTE);

        scxmlCancelTypeEClass = createEClass(SCXML_CANCEL_TYPE);
        createEAttribute(scxmlCancelTypeEClass, SCXML_CANCEL_TYPE__SCXML_EXTRA_CONTENT);
        createEAttribute(scxmlCancelTypeEClass, SCXML_CANCEL_TYPE__ANY);
        createEAttribute(scxmlCancelTypeEClass, SCXML_CANCEL_TYPE__SENDID);
        createEAttribute(scxmlCancelTypeEClass, SCXML_CANCEL_TYPE__SENDIDEXPR);
        createEAttribute(scxmlCancelTypeEClass, SCXML_CANCEL_TYPE__ANY_ATTRIBUTE);

        scxmlContentTypeEClass = createEClass(SCXML_CONTENT_TYPE);
        createEAttribute(scxmlContentTypeEClass, SCXML_CONTENT_TYPE__MIXED);
        createEAttribute(scxmlContentTypeEClass, SCXML_CONTENT_TYPE__ANY);
        createEAttribute(scxmlContentTypeEClass, SCXML_CONTENT_TYPE__EXPR);
        createEAttribute(scxmlContentTypeEClass, SCXML_CONTENT_TYPE__ANY_ATTRIBUTE);

        scxmlDatamodelTypeEClass = createEClass(SCXML_DATAMODEL_TYPE);
        createEReference(scxmlDatamodelTypeEClass, SCXML_DATAMODEL_TYPE__DATA);
        createEAttribute(scxmlDatamodelTypeEClass, SCXML_DATAMODEL_TYPE__SCXML_EXTRA_CONTENT);
        createEAttribute(scxmlDatamodelTypeEClass, SCXML_DATAMODEL_TYPE__ANY);
        createEAttribute(scxmlDatamodelTypeEClass, SCXML_DATAMODEL_TYPE__ANY_ATTRIBUTE);

        scxmlDataTypeEClass = createEClass(SCXML_DATA_TYPE);
        createEAttribute(scxmlDataTypeEClass, SCXML_DATA_TYPE__MIXED);
        createEAttribute(scxmlDataTypeEClass, SCXML_DATA_TYPE__ANY);
        createEAttribute(scxmlDataTypeEClass, SCXML_DATA_TYPE__EXPR);
        createEAttribute(scxmlDataTypeEClass, SCXML_DATA_TYPE__ID);
        createEAttribute(scxmlDataTypeEClass, SCXML_DATA_TYPE__SRC);
        createEAttribute(scxmlDataTypeEClass, SCXML_DATA_TYPE__ANY_ATTRIBUTE);

        scxmlDonedataTypeEClass = createEClass(SCXML_DONEDATA_TYPE);
        createEReference(scxmlDonedataTypeEClass, SCXML_DONEDATA_TYPE__CONTENT);
        createEReference(scxmlDonedataTypeEClass, SCXML_DONEDATA_TYPE__PARAM);
        createEAttribute(scxmlDonedataTypeEClass, SCXML_DONEDATA_TYPE__ANY_ATTRIBUTE);

        scxmlElseifTypeEClass = createEClass(SCXML_ELSEIF_TYPE);
        createEAttribute(scxmlElseifTypeEClass, SCXML_ELSEIF_TYPE__COND);
        createEAttribute(scxmlElseifTypeEClass, SCXML_ELSEIF_TYPE__ANY_ATTRIBUTE);

        scxmlElseTypeEClass = createEClass(SCXML_ELSE_TYPE);
        createEAttribute(scxmlElseTypeEClass, SCXML_ELSE_TYPE__ANY_ATTRIBUTE);

        scxmlFinalizeTypeEClass = createEClass(SCXML_FINALIZE_TYPE);
        createEAttribute(scxmlFinalizeTypeEClass, SCXML_FINALIZE_TYPE__SCXML_CORE_EXECUTABLECONTENT);
        createEAttribute(scxmlFinalizeTypeEClass, SCXML_FINALIZE_TYPE__ANY);
        createEReference(scxmlFinalizeTypeEClass, SCXML_FINALIZE_TYPE__RAISE);
        createEReference(scxmlFinalizeTypeEClass, SCXML_FINALIZE_TYPE__IF);
        createEReference(scxmlFinalizeTypeEClass, SCXML_FINALIZE_TYPE__FOREACH);
        createEReference(scxmlFinalizeTypeEClass, SCXML_FINALIZE_TYPE__SEND);
        createEReference(scxmlFinalizeTypeEClass, SCXML_FINALIZE_TYPE__SCRIPT);
        createEReference(scxmlFinalizeTypeEClass, SCXML_FINALIZE_TYPE__ASSIGN);
        createEReference(scxmlFinalizeTypeEClass, SCXML_FINALIZE_TYPE__LOG);
        createEReference(scxmlFinalizeTypeEClass, SCXML_FINALIZE_TYPE__CANCEL);
        createEAttribute(scxmlFinalizeTypeEClass, SCXML_FINALIZE_TYPE__ANY_ATTRIBUTE);

        scxmlFinalTypeEClass = createEClass(SCXML_FINAL_TYPE);
        createEAttribute(scxmlFinalTypeEClass, SCXML_FINAL_TYPE__SCXML_FINAL_MIX);
        createEReference(scxmlFinalTypeEClass, SCXML_FINAL_TYPE__ONENTRY);
        createEReference(scxmlFinalTypeEClass, SCXML_FINAL_TYPE__ONEXIT);
        createEReference(scxmlFinalTypeEClass, SCXML_FINAL_TYPE__DONEDATA);
        createEAttribute(scxmlFinalTypeEClass, SCXML_FINAL_TYPE__ANY);
        createEAttribute(scxmlFinalTypeEClass, SCXML_FINAL_TYPE__ID);
        createEAttribute(scxmlFinalTypeEClass, SCXML_FINAL_TYPE__ANY_ATTRIBUTE);

        scxmlForeachTypeEClass = createEClass(SCXML_FOREACH_TYPE);
        createEAttribute(scxmlForeachTypeEClass, SCXML_FOREACH_TYPE__SCXML_CORE_EXECUTABLECONTENT);
        createEAttribute(scxmlForeachTypeEClass, SCXML_FOREACH_TYPE__ANY);
        createEReference(scxmlForeachTypeEClass, SCXML_FOREACH_TYPE__RAISE);
        createEReference(scxmlForeachTypeEClass, SCXML_FOREACH_TYPE__IF);
        createEReference(scxmlForeachTypeEClass, SCXML_FOREACH_TYPE__FOREACH);
        createEReference(scxmlForeachTypeEClass, SCXML_FOREACH_TYPE__SEND);
        createEReference(scxmlForeachTypeEClass, SCXML_FOREACH_TYPE__SCRIPT);
        createEReference(scxmlForeachTypeEClass, SCXML_FOREACH_TYPE__ASSIGN);
        createEReference(scxmlForeachTypeEClass, SCXML_FOREACH_TYPE__LOG);
        createEReference(scxmlForeachTypeEClass, SCXML_FOREACH_TYPE__CANCEL);
        createEAttribute(scxmlForeachTypeEClass, SCXML_FOREACH_TYPE__ARRAY);
        createEAttribute(scxmlForeachTypeEClass, SCXML_FOREACH_TYPE__INDEX);
        createEAttribute(scxmlForeachTypeEClass, SCXML_FOREACH_TYPE__ITEM);
        createEAttribute(scxmlForeachTypeEClass, SCXML_FOREACH_TYPE__ANY_ATTRIBUTE);

        scxmlHistoryTypeEClass = createEClass(SCXML_HISTORY_TYPE);
        createEAttribute(scxmlHistoryTypeEClass, SCXML_HISTORY_TYPE__SCXML_EXTRA_CONTENT);
        createEAttribute(scxmlHistoryTypeEClass, SCXML_HISTORY_TYPE__ANY);
        createEReference(scxmlHistoryTypeEClass, SCXML_HISTORY_TYPE__TRANSITION);
        createEAttribute(scxmlHistoryTypeEClass, SCXML_HISTORY_TYPE__SCXML_EXTRA_CONTENT1);
        createEAttribute(scxmlHistoryTypeEClass, SCXML_HISTORY_TYPE__ANY1);
        createEAttribute(scxmlHistoryTypeEClass, SCXML_HISTORY_TYPE__ID);
        createEAttribute(scxmlHistoryTypeEClass, SCXML_HISTORY_TYPE__TYPE);
        createEAttribute(scxmlHistoryTypeEClass, SCXML_HISTORY_TYPE__ANY_ATTRIBUTE);

        scxmlIfTypeEClass = createEClass(SCXML_IF_TYPE);
        createEAttribute(scxmlIfTypeEClass, SCXML_IF_TYPE__SCXML_CORE_EXECUTABLECONTENT);
        createEAttribute(scxmlIfTypeEClass, SCXML_IF_TYPE__ANY);
        createEReference(scxmlIfTypeEClass, SCXML_IF_TYPE__RAISE);
        createEReference(scxmlIfTypeEClass, SCXML_IF_TYPE__IF);
        createEReference(scxmlIfTypeEClass, SCXML_IF_TYPE__FOREACH);
        createEReference(scxmlIfTypeEClass, SCXML_IF_TYPE__SEND);
        createEReference(scxmlIfTypeEClass, SCXML_IF_TYPE__SCRIPT);
        createEReference(scxmlIfTypeEClass, SCXML_IF_TYPE__ASSIGN);
        createEReference(scxmlIfTypeEClass, SCXML_IF_TYPE__LOG);
        createEReference(scxmlIfTypeEClass, SCXML_IF_TYPE__CANCEL);
        createEReference(scxmlIfTypeEClass, SCXML_IF_TYPE__ELSEIF);
        createEAttribute(scxmlIfTypeEClass, SCXML_IF_TYPE__SCXML_CORE_EXECUTABLECONTENT1);
        createEAttribute(scxmlIfTypeEClass, SCXML_IF_TYPE__ANY1);
        createEReference(scxmlIfTypeEClass, SCXML_IF_TYPE__RAISE1);
        createEReference(scxmlIfTypeEClass, SCXML_IF_TYPE__IF1);
        createEReference(scxmlIfTypeEClass, SCXML_IF_TYPE__FOREACH1);
        createEReference(scxmlIfTypeEClass, SCXML_IF_TYPE__SEND1);
        createEReference(scxmlIfTypeEClass, SCXML_IF_TYPE__SCRIPT1);
        createEReference(scxmlIfTypeEClass, SCXML_IF_TYPE__ASSIGN1);
        createEReference(scxmlIfTypeEClass, SCXML_IF_TYPE__LOG1);
        createEReference(scxmlIfTypeEClass, SCXML_IF_TYPE__CANCEL1);
        createEReference(scxmlIfTypeEClass, SCXML_IF_TYPE__ELSE);
        createEAttribute(scxmlIfTypeEClass, SCXML_IF_TYPE__SCXML_CORE_EXECUTABLECONTENT2);
        createEAttribute(scxmlIfTypeEClass, SCXML_IF_TYPE__ANY2);
        createEReference(scxmlIfTypeEClass, SCXML_IF_TYPE__RAISE2);
        createEReference(scxmlIfTypeEClass, SCXML_IF_TYPE__IF2);
        createEReference(scxmlIfTypeEClass, SCXML_IF_TYPE__FOREACH2);
        createEReference(scxmlIfTypeEClass, SCXML_IF_TYPE__SEND2);
        createEReference(scxmlIfTypeEClass, SCXML_IF_TYPE__SCRIPT2);
        createEReference(scxmlIfTypeEClass, SCXML_IF_TYPE__ASSIGN2);
        createEReference(scxmlIfTypeEClass, SCXML_IF_TYPE__LOG2);
        createEReference(scxmlIfTypeEClass, SCXML_IF_TYPE__CANCEL2);
        createEAttribute(scxmlIfTypeEClass, SCXML_IF_TYPE__COND);
        createEAttribute(scxmlIfTypeEClass, SCXML_IF_TYPE__ANY_ATTRIBUTE);

        scxmlInitialTypeEClass = createEClass(SCXML_INITIAL_TYPE);
        createEAttribute(scxmlInitialTypeEClass, SCXML_INITIAL_TYPE__SCXML_EXTRA_CONTENT);
        createEAttribute(scxmlInitialTypeEClass, SCXML_INITIAL_TYPE__ANY);
        createEReference(scxmlInitialTypeEClass, SCXML_INITIAL_TYPE__TRANSITION);
        createEAttribute(scxmlInitialTypeEClass, SCXML_INITIAL_TYPE__SCXML_EXTRA_CONTENT1);
        createEAttribute(scxmlInitialTypeEClass, SCXML_INITIAL_TYPE__ANY1);
        createEAttribute(scxmlInitialTypeEClass, SCXML_INITIAL_TYPE__ANY_ATTRIBUTE);

        scxmlInvokeTypeEClass = createEClass(SCXML_INVOKE_TYPE);
        createEAttribute(scxmlInvokeTypeEClass, SCXML_INVOKE_TYPE__SCXML_INVOKE_MIX);
        createEReference(scxmlInvokeTypeEClass, SCXML_INVOKE_TYPE__CONTENT);
        createEReference(scxmlInvokeTypeEClass, SCXML_INVOKE_TYPE__PARAM);
        createEReference(scxmlInvokeTypeEClass, SCXML_INVOKE_TYPE__FINALIZE);
        createEAttribute(scxmlInvokeTypeEClass, SCXML_INVOKE_TYPE__ANY);
        createEAttribute(scxmlInvokeTypeEClass, SCXML_INVOKE_TYPE__AUTOFORWARD);
        createEAttribute(scxmlInvokeTypeEClass, SCXML_INVOKE_TYPE__ID);
        createEAttribute(scxmlInvokeTypeEClass, SCXML_INVOKE_TYPE__IDLOCATION);
        createEAttribute(scxmlInvokeTypeEClass, SCXML_INVOKE_TYPE__NAMELIST);
        createEAttribute(scxmlInvokeTypeEClass, SCXML_INVOKE_TYPE__SRC);
        createEAttribute(scxmlInvokeTypeEClass, SCXML_INVOKE_TYPE__SRCEXPR);
        createEAttribute(scxmlInvokeTypeEClass, SCXML_INVOKE_TYPE__TYPE);
        createEAttribute(scxmlInvokeTypeEClass, SCXML_INVOKE_TYPE__TYPEEXPR);
        createEAttribute(scxmlInvokeTypeEClass, SCXML_INVOKE_TYPE__ANY_ATTRIBUTE);

        scxmlLogTypeEClass = createEClass(SCXML_LOG_TYPE);
        createEAttribute(scxmlLogTypeEClass, SCXML_LOG_TYPE__SCXML_EXTRA_CONTENT);
        createEAttribute(scxmlLogTypeEClass, SCXML_LOG_TYPE__ANY);
        createEAttribute(scxmlLogTypeEClass, SCXML_LOG_TYPE__EXPR);
        createEAttribute(scxmlLogTypeEClass, SCXML_LOG_TYPE__LABEL);
        createEAttribute(scxmlLogTypeEClass, SCXML_LOG_TYPE__ANY_ATTRIBUTE);

        scxmlOnentryTypeEClass = createEClass(SCXML_ONENTRY_TYPE);
        createEAttribute(scxmlOnentryTypeEClass, SCXML_ONENTRY_TYPE__SCXML_CORE_EXECUTABLECONTENT);
        createEAttribute(scxmlOnentryTypeEClass, SCXML_ONENTRY_TYPE__ANY);
        createEReference(scxmlOnentryTypeEClass, SCXML_ONENTRY_TYPE__RAISE);
        createEReference(scxmlOnentryTypeEClass, SCXML_ONENTRY_TYPE__IF);
        createEReference(scxmlOnentryTypeEClass, SCXML_ONENTRY_TYPE__FOREACH);
        createEReference(scxmlOnentryTypeEClass, SCXML_ONENTRY_TYPE__SEND);
        createEReference(scxmlOnentryTypeEClass, SCXML_ONENTRY_TYPE__SCRIPT);
        createEReference(scxmlOnentryTypeEClass, SCXML_ONENTRY_TYPE__ASSIGN);
        createEReference(scxmlOnentryTypeEClass, SCXML_ONENTRY_TYPE__LOG);
        createEReference(scxmlOnentryTypeEClass, SCXML_ONENTRY_TYPE__CANCEL);
        createEAttribute(scxmlOnentryTypeEClass, SCXML_ONENTRY_TYPE__ANY_ATTRIBUTE);

        scxmlOnexitTypeEClass = createEClass(SCXML_ONEXIT_TYPE);
        createEAttribute(scxmlOnexitTypeEClass, SCXML_ONEXIT_TYPE__SCXML_CORE_EXECUTABLECONTENT);
        createEAttribute(scxmlOnexitTypeEClass, SCXML_ONEXIT_TYPE__ANY);
        createEReference(scxmlOnexitTypeEClass, SCXML_ONEXIT_TYPE__RAISE);
        createEReference(scxmlOnexitTypeEClass, SCXML_ONEXIT_TYPE__IF);
        createEReference(scxmlOnexitTypeEClass, SCXML_ONEXIT_TYPE__FOREACH);
        createEReference(scxmlOnexitTypeEClass, SCXML_ONEXIT_TYPE__SEND);
        createEReference(scxmlOnexitTypeEClass, SCXML_ONEXIT_TYPE__SCRIPT);
        createEReference(scxmlOnexitTypeEClass, SCXML_ONEXIT_TYPE__ASSIGN);
        createEReference(scxmlOnexitTypeEClass, SCXML_ONEXIT_TYPE__LOG);
        createEReference(scxmlOnexitTypeEClass, SCXML_ONEXIT_TYPE__CANCEL);
        createEAttribute(scxmlOnexitTypeEClass, SCXML_ONEXIT_TYPE__ANY_ATTRIBUTE);

        scxmlParallelTypeEClass = createEClass(SCXML_PARALLEL_TYPE);
        createEAttribute(scxmlParallelTypeEClass, SCXML_PARALLEL_TYPE__SCXML_PARALLEL_MIX);
        createEReference(scxmlParallelTypeEClass, SCXML_PARALLEL_TYPE__ONENTRY);
        createEReference(scxmlParallelTypeEClass, SCXML_PARALLEL_TYPE__ONEXIT);
        createEReference(scxmlParallelTypeEClass, SCXML_PARALLEL_TYPE__TRANSITION);
        createEReference(scxmlParallelTypeEClass, SCXML_PARALLEL_TYPE__STATE);
        createEReference(scxmlParallelTypeEClass, SCXML_PARALLEL_TYPE__PARALLEL);
        createEReference(scxmlParallelTypeEClass, SCXML_PARALLEL_TYPE__HISTORY);
        createEReference(scxmlParallelTypeEClass, SCXML_PARALLEL_TYPE__DATAMODEL);
        createEReference(scxmlParallelTypeEClass, SCXML_PARALLEL_TYPE__INVOKE);
        createEAttribute(scxmlParallelTypeEClass, SCXML_PARALLEL_TYPE__ANY);
        createEAttribute(scxmlParallelTypeEClass, SCXML_PARALLEL_TYPE__ID);
        createEAttribute(scxmlParallelTypeEClass, SCXML_PARALLEL_TYPE__ANY_ATTRIBUTE);

        scxmlParamTypeEClass = createEClass(SCXML_PARAM_TYPE);
        createEAttribute(scxmlParamTypeEClass, SCXML_PARAM_TYPE__SCXML_EXTRA_CONTENT);
        createEAttribute(scxmlParamTypeEClass, SCXML_PARAM_TYPE__ANY);
        createEAttribute(scxmlParamTypeEClass, SCXML_PARAM_TYPE__EXPR);
        createEAttribute(scxmlParamTypeEClass, SCXML_PARAM_TYPE__LOCATION);
        createEAttribute(scxmlParamTypeEClass, SCXML_PARAM_TYPE__NAME);
        createEAttribute(scxmlParamTypeEClass, SCXML_PARAM_TYPE__ANY_ATTRIBUTE);

        scxmlRaiseTypeEClass = createEClass(SCXML_RAISE_TYPE);
        createEAttribute(scxmlRaiseTypeEClass, SCXML_RAISE_TYPE__EVENT);
        createEAttribute(scxmlRaiseTypeEClass, SCXML_RAISE_TYPE__ANY_ATTRIBUTE);

        scxmlScriptTypeEClass = createEClass(SCXML_SCRIPT_TYPE);
        createEAttribute(scxmlScriptTypeEClass, SCXML_SCRIPT_TYPE__MIXED);
        createEAttribute(scxmlScriptTypeEClass, SCXML_SCRIPT_TYPE__SCXML_EXTRA_CONTENT);
        createEAttribute(scxmlScriptTypeEClass, SCXML_SCRIPT_TYPE__ANY);
        createEAttribute(scxmlScriptTypeEClass, SCXML_SCRIPT_TYPE__SRC);
        createEAttribute(scxmlScriptTypeEClass, SCXML_SCRIPT_TYPE__ANY_ATTRIBUTE);

        scxmlScxmlTypeEClass = createEClass(SCXML_SCXML_TYPE);
        createEAttribute(scxmlScxmlTypeEClass, SCXML_SCXML_TYPE__SCXML_SCXML_MIX);
        createEReference(scxmlScxmlTypeEClass, SCXML_SCXML_TYPE__STATE);
        createEReference(scxmlScxmlTypeEClass, SCXML_SCXML_TYPE__PARALLEL);
        createEReference(scxmlScxmlTypeEClass, SCXML_SCXML_TYPE__FINAL);
        createEReference(scxmlScxmlTypeEClass, SCXML_SCXML_TYPE__DATAMODEL);
        createEReference(scxmlScxmlTypeEClass, SCXML_SCXML_TYPE__SCRIPT);
        createEAttribute(scxmlScxmlTypeEClass, SCXML_SCXML_TYPE__ANY);
        createEAttribute(scxmlScxmlTypeEClass, SCXML_SCXML_TYPE__BINDING);
        createEAttribute(scxmlScxmlTypeEClass, SCXML_SCXML_TYPE__DATAMODEL1);
        createEAttribute(scxmlScxmlTypeEClass, SCXML_SCXML_TYPE__EXMODE);
        createEAttribute(scxmlScxmlTypeEClass, SCXML_SCXML_TYPE__INITIAL);
        createEAttribute(scxmlScxmlTypeEClass, SCXML_SCXML_TYPE__NAME);
        createEAttribute(scxmlScxmlTypeEClass, SCXML_SCXML_TYPE__VERSION);
        createEAttribute(scxmlScxmlTypeEClass, SCXML_SCXML_TYPE__ANY_ATTRIBUTE);

        scxmlSendTypeEClass = createEClass(SCXML_SEND_TYPE);
        createEAttribute(scxmlSendTypeEClass, SCXML_SEND_TYPE__SCXML_SEND_MIX);
        createEReference(scxmlSendTypeEClass, SCXML_SEND_TYPE__CONTENT);
        createEReference(scxmlSendTypeEClass, SCXML_SEND_TYPE__PARAM);
        createEAttribute(scxmlSendTypeEClass, SCXML_SEND_TYPE__ANY);
        createEAttribute(scxmlSendTypeEClass, SCXML_SEND_TYPE__DELAY);
        createEAttribute(scxmlSendTypeEClass, SCXML_SEND_TYPE__DELAYEXPR);
        createEAttribute(scxmlSendTypeEClass, SCXML_SEND_TYPE__EVENT);
        createEAttribute(scxmlSendTypeEClass, SCXML_SEND_TYPE__EVENTEXPR);
        createEAttribute(scxmlSendTypeEClass, SCXML_SEND_TYPE__ID);
        createEAttribute(scxmlSendTypeEClass, SCXML_SEND_TYPE__IDLOCATION);
        createEAttribute(scxmlSendTypeEClass, SCXML_SEND_TYPE__NAMELIST);
        createEAttribute(scxmlSendTypeEClass, SCXML_SEND_TYPE__TARGET);
        createEAttribute(scxmlSendTypeEClass, SCXML_SEND_TYPE__TARGETEXPR);
        createEAttribute(scxmlSendTypeEClass, SCXML_SEND_TYPE__TYPE);
        createEAttribute(scxmlSendTypeEClass, SCXML_SEND_TYPE__TYPEEXPR);
        createEAttribute(scxmlSendTypeEClass, SCXML_SEND_TYPE__ANY_ATTRIBUTE);

        scxmlStateTypeEClass = createEClass(SCXML_STATE_TYPE);
        createEAttribute(scxmlStateTypeEClass, SCXML_STATE_TYPE__SCXML_STATE_MIX);
        createEReference(scxmlStateTypeEClass, SCXML_STATE_TYPE__ONENTRY);
        createEReference(scxmlStateTypeEClass, SCXML_STATE_TYPE__ONEXIT);
        createEReference(scxmlStateTypeEClass, SCXML_STATE_TYPE__TRANSITION);
        createEReference(scxmlStateTypeEClass, SCXML_STATE_TYPE__INITIAL);
        createEReference(scxmlStateTypeEClass, SCXML_STATE_TYPE__STATE);
        createEReference(scxmlStateTypeEClass, SCXML_STATE_TYPE__PARALLEL);
        createEReference(scxmlStateTypeEClass, SCXML_STATE_TYPE__FINAL);
        createEReference(scxmlStateTypeEClass, SCXML_STATE_TYPE__HISTORY);
        createEReference(scxmlStateTypeEClass, SCXML_STATE_TYPE__DATAMODEL);
        createEReference(scxmlStateTypeEClass, SCXML_STATE_TYPE__INVOKE);
        createEAttribute(scxmlStateTypeEClass, SCXML_STATE_TYPE__ANY);
        createEAttribute(scxmlStateTypeEClass, SCXML_STATE_TYPE__ID);
        createEAttribute(scxmlStateTypeEClass, SCXML_STATE_TYPE__INITIAL1);
        createEAttribute(scxmlStateTypeEClass, SCXML_STATE_TYPE__ANY_ATTRIBUTE);

        scxmlTransitionTypeEClass = createEClass(SCXML_TRANSITION_TYPE);
        createEAttribute(scxmlTransitionTypeEClass, SCXML_TRANSITION_TYPE__SCXML_CORE_EXECUTABLECONTENT);
        createEAttribute(scxmlTransitionTypeEClass, SCXML_TRANSITION_TYPE__ANY);
        createEReference(scxmlTransitionTypeEClass, SCXML_TRANSITION_TYPE__RAISE);
        createEReference(scxmlTransitionTypeEClass, SCXML_TRANSITION_TYPE__IF);
        createEReference(scxmlTransitionTypeEClass, SCXML_TRANSITION_TYPE__FOREACH);
        createEReference(scxmlTransitionTypeEClass, SCXML_TRANSITION_TYPE__SEND);
        createEReference(scxmlTransitionTypeEClass, SCXML_TRANSITION_TYPE__SCRIPT);
        createEReference(scxmlTransitionTypeEClass, SCXML_TRANSITION_TYPE__ASSIGN);
        createEReference(scxmlTransitionTypeEClass, SCXML_TRANSITION_TYPE__LOG);
        createEReference(scxmlTransitionTypeEClass, SCXML_TRANSITION_TYPE__CANCEL);
        createEAttribute(scxmlTransitionTypeEClass, SCXML_TRANSITION_TYPE__COND);
        createEAttribute(scxmlTransitionTypeEClass, SCXML_TRANSITION_TYPE__EVENT);
        createEAttribute(scxmlTransitionTypeEClass, SCXML_TRANSITION_TYPE__TARGET);
        createEAttribute(scxmlTransitionTypeEClass, SCXML_TRANSITION_TYPE__TYPE);
        createEAttribute(scxmlTransitionTypeEClass, SCXML_TRANSITION_TYPE__ANY_ATTRIBUTE);

        // Create enums
        assignTypeDatatypeEEnum = createEEnum(ASSIGN_TYPE_DATATYPE);
        bindingDatatypeEEnum = createEEnum(BINDING_DATATYPE);
        booleanDatatypeEEnum = createEEnum(BOOLEAN_DATATYPE);
        exmodeDatatypeEEnum = createEEnum(EXMODE_DATATYPE);
        historyTypeDatatypeEEnum = createEEnum(HISTORY_TYPE_DATATYPE);
        transitionTypeDatatypeEEnum = createEEnum(TRANSITION_TYPE_DATATYPE);

        // Create data types
        assignTypeDatatypeObjectEDataType = createEDataType(ASSIGN_TYPE_DATATYPE_OBJECT);
        bindingDatatypeObjectEDataType = createEDataType(BINDING_DATATYPE_OBJECT);
        booleanDatatypeObjectEDataType = createEDataType(BOOLEAN_DATATYPE_OBJECT);
        condLangDatatypeEDataType = createEDataType(COND_LANG_DATATYPE);
        durationDatatypeEDataType = createEDataType(DURATION_DATATYPE);
        eventTypeDatatypeEDataType = createEDataType(EVENT_TYPE_DATATYPE);
        eventTypesDatatypeEDataType = createEDataType(EVENT_TYPES_DATATYPE);
        exmodeDatatypeObjectEDataType = createEDataType(EXMODE_DATATYPE_OBJECT);
        historyTypeDatatypeObjectEDataType = createEDataType(HISTORY_TYPE_DATATYPE_OBJECT);
        integerDatatypeEDataType = createEDataType(INTEGER_DATATYPE);
        locLangDatatypeEDataType = createEDataType(LOC_LANG_DATATYPE);
        transitionTypeDatatypeObjectEDataType = createEDataType(TRANSITION_TYPE_DATATYPE_OBJECT);
        uriDatatypeEDataType = createEDataType(URI_DATATYPE);
        valueLangDatatypeEDataType = createEDataType(VALUE_LANG_DATATYPE);
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
        XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes

        // Initialize classes, features, and operations; add parameters
        initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Assign(), this.getScxmlAssignType(), null, "assign", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Cancel(), this.getScxmlCancelType(), null, "cancel", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Content(), this.getScxmlContentType(), null, "content", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Data(), this.getScxmlDataType(), null, "data", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Datamodel(), this.getScxmlDatamodelType(), null, "datamodel", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Donedata(), this.getScxmlDonedataType(), null, "donedata", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Else(), this.getScxmlElseType(), null, "else", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Elseif(), this.getScxmlElseifType(), null, "elseif", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Final(), this.getScxmlFinalType(), null, "final", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Finalize(), this.getScxmlFinalizeType(), null, "finalize", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Foreach(), this.getScxmlForeachType(), null, "foreach", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_History(), this.getScxmlHistoryType(), null, "history", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_If(), this.getScxmlIfType(), null, "if", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Initial(), this.getScxmlInitialType(), null, "initial", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Invoke(), this.getScxmlInvokeType(), null, "invoke", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Log(), this.getScxmlLogType(), null, "log", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Onentry(), this.getScxmlOnentryType(), null, "onentry", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Onexit(), this.getScxmlOnexitType(), null, "onexit", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Parallel(), this.getScxmlParallelType(), null, "parallel", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Param(), this.getScxmlParamType(), null, "param", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Raise(), this.getScxmlRaiseType(), null, "raise", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Script(), this.getScxmlScriptType(), null, "script", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Scxml(), this.getScxmlScxmlType(), null, "scxml", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Send(), this.getScxmlSendType(), null, "send", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_State(), this.getScxmlStateType(), null, "state", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Transition(), this.getScxmlTransitionType(), null, "transition", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

        initEClass(scxmlAssignTypeEClass, ScxmlAssignType.class, "ScxmlAssignType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getScxmlAssignType_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, ScxmlAssignType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlAssignType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, ScxmlAssignType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlAssignType_Attr(), theXMLTypePackage.getNMTOKEN(), "attr", null, 0, 1, ScxmlAssignType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlAssignType_Expr(), this.getValueLangDatatype(), "expr", null, 0, 1, ScxmlAssignType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlAssignType_Location(), this.getLocLangDatatype(), "location", null, 1, 1, ScxmlAssignType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlAssignType_Type(), this.getAssignTypeDatatype(), "type", "replacechildren", 0, 1, ScxmlAssignType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlAssignType_AnyAttribute(), ecorePackage.getEFeatureMapEntry(), "anyAttribute", null, 0, -1, ScxmlAssignType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(scxmlCancelTypeEClass, ScxmlCancelType.class, "ScxmlCancelType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getScxmlCancelType_ScxmlExtraContent(), ecorePackage.getEFeatureMapEntry(), "scxmlExtraContent", null, 0, -1, ScxmlCancelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlCancelType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, ScxmlCancelType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlCancelType_Sendid(), theXMLTypePackage.getIDREF(), "sendid", null, 0, 1, ScxmlCancelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlCancelType_Sendidexpr(), this.getValueLangDatatype(), "sendidexpr", null, 0, 1, ScxmlCancelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlCancelType_AnyAttribute(), ecorePackage.getEFeatureMapEntry(), "anyAttribute", null, 0, -1, ScxmlCancelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(scxmlContentTypeEClass, ScxmlContentType.class, "ScxmlContentType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getScxmlContentType_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, ScxmlContentType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlContentType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, ScxmlContentType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlContentType_Expr(), this.getValueLangDatatype(), "expr", null, 0, 1, ScxmlContentType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlContentType_AnyAttribute(), ecorePackage.getEFeatureMapEntry(), "anyAttribute", null, 0, -1, ScxmlContentType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(scxmlDatamodelTypeEClass, ScxmlDatamodelType.class, "ScxmlDatamodelType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getScxmlDatamodelType_Data(), this.getScxmlDataType(), null, "data", null, 0, -1, ScxmlDatamodelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlDatamodelType_ScxmlExtraContent(), ecorePackage.getEFeatureMapEntry(), "scxmlExtraContent", null, 0, -1, ScxmlDatamodelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlDatamodelType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, ScxmlDatamodelType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlDatamodelType_AnyAttribute(), ecorePackage.getEFeatureMapEntry(), "anyAttribute", null, 0, -1, ScxmlDatamodelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(scxmlDataTypeEClass, ScxmlDataType.class, "ScxmlDataType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getScxmlDataType_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, ScxmlDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlDataType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, ScxmlDataType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlDataType_Expr(), this.getValueLangDatatype(), "expr", null, 0, 1, ScxmlDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlDataType_Id(), theXMLTypePackage.getID(), "id", null, 1, 1, ScxmlDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlDataType_Src(), this.getURIDatatype(), "src", null, 0, 1, ScxmlDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlDataType_AnyAttribute(), ecorePackage.getEFeatureMapEntry(), "anyAttribute", null, 0, -1, ScxmlDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(scxmlDonedataTypeEClass, ScxmlDonedataType.class, "ScxmlDonedataType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getScxmlDonedataType_Content(), this.getScxmlContentType(), null, "content", null, 0, 1, ScxmlDonedataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlDonedataType_Param(), this.getScxmlParamType(), null, "param", null, 0, -1, ScxmlDonedataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlDonedataType_AnyAttribute(), ecorePackage.getEFeatureMapEntry(), "anyAttribute", null, 0, -1, ScxmlDonedataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(scxmlElseifTypeEClass, ScxmlElseifType.class, "ScxmlElseifType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getScxmlElseifType_Cond(), this.getCondLangDatatype(), "cond", null, 1, 1, ScxmlElseifType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlElseifType_AnyAttribute(), ecorePackage.getEFeatureMapEntry(), "anyAttribute", null, 0, -1, ScxmlElseifType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(scxmlElseTypeEClass, ScxmlElseType.class, "ScxmlElseType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getScxmlElseType_AnyAttribute(), ecorePackage.getEFeatureMapEntry(), "anyAttribute", null, 0, -1, ScxmlElseType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(scxmlFinalizeTypeEClass, ScxmlFinalizeType.class, "ScxmlFinalizeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getScxmlFinalizeType_ScxmlCoreExecutablecontent(), ecorePackage.getEFeatureMapEntry(), "scxmlCoreExecutablecontent", null, 0, -1, ScxmlFinalizeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlFinalizeType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, ScxmlFinalizeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlFinalizeType_Raise(), this.getScxmlRaiseType(), null, "raise", null, 0, -1, ScxmlFinalizeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlFinalizeType_If(), this.getScxmlIfType(), null, "if", null, 0, -1, ScxmlFinalizeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlFinalizeType_Foreach(), this.getScxmlForeachType(), null, "foreach", null, 0, -1, ScxmlFinalizeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlFinalizeType_Send(), this.getScxmlSendType(), null, "send", null, 0, -1, ScxmlFinalizeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlFinalizeType_Script(), this.getScxmlScriptType(), null, "script", null, 0, -1, ScxmlFinalizeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlFinalizeType_Assign(), this.getScxmlAssignType(), null, "assign", null, 0, -1, ScxmlFinalizeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlFinalizeType_Log(), this.getScxmlLogType(), null, "log", null, 0, -1, ScxmlFinalizeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlFinalizeType_Cancel(), this.getScxmlCancelType(), null, "cancel", null, 0, -1, ScxmlFinalizeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlFinalizeType_AnyAttribute(), ecorePackage.getEFeatureMapEntry(), "anyAttribute", null, 0, -1, ScxmlFinalizeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(scxmlFinalTypeEClass, ScxmlFinalType.class, "ScxmlFinalType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getScxmlFinalType_ScxmlFinalMix(), ecorePackage.getEFeatureMapEntry(), "scxmlFinalMix", null, 0, -1, ScxmlFinalType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlFinalType_Onentry(), this.getScxmlOnentryType(), null, "onentry", null, 0, -1, ScxmlFinalType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlFinalType_Onexit(), this.getScxmlOnexitType(), null, "onexit", null, 0, -1, ScxmlFinalType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlFinalType_Donedata(), this.getScxmlDonedataType(), null, "donedata", null, 0, -1, ScxmlFinalType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlFinalType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, ScxmlFinalType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlFinalType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ScxmlFinalType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlFinalType_AnyAttribute(), ecorePackage.getEFeatureMapEntry(), "anyAttribute", null, 0, -1, ScxmlFinalType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(scxmlForeachTypeEClass, ScxmlForeachType.class, "ScxmlForeachType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getScxmlForeachType_ScxmlCoreExecutablecontent(), ecorePackage.getEFeatureMapEntry(), "scxmlCoreExecutablecontent", null, 0, -1, ScxmlForeachType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlForeachType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, ScxmlForeachType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlForeachType_Raise(), this.getScxmlRaiseType(), null, "raise", null, 0, -1, ScxmlForeachType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlForeachType_If(), this.getScxmlIfType(), null, "if", null, 0, -1, ScxmlForeachType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlForeachType_Foreach(), this.getScxmlForeachType(), null, "foreach", null, 0, -1, ScxmlForeachType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlForeachType_Send(), this.getScxmlSendType(), null, "send", null, 0, -1, ScxmlForeachType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlForeachType_Script(), this.getScxmlScriptType(), null, "script", null, 0, -1, ScxmlForeachType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlForeachType_Assign(), this.getScxmlAssignType(), null, "assign", null, 0, -1, ScxmlForeachType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlForeachType_Log(), this.getScxmlLogType(), null, "log", null, 0, -1, ScxmlForeachType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlForeachType_Cancel(), this.getScxmlCancelType(), null, "cancel", null, 0, -1, ScxmlForeachType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlForeachType_Array(), this.getValueLangDatatype(), "array", null, 1, 1, ScxmlForeachType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlForeachType_Index(), theXMLTypePackage.getString(), "index", null, 0, 1, ScxmlForeachType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlForeachType_Item(), theXMLTypePackage.getString(), "item", null, 1, 1, ScxmlForeachType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlForeachType_AnyAttribute(), ecorePackage.getEFeatureMapEntry(), "anyAttribute", null, 0, -1, ScxmlForeachType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(scxmlHistoryTypeEClass, ScxmlHistoryType.class, "ScxmlHistoryType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getScxmlHistoryType_ScxmlExtraContent(), ecorePackage.getEFeatureMapEntry(), "scxmlExtraContent", null, 0, -1, ScxmlHistoryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlHistoryType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, ScxmlHistoryType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlHistoryType_Transition(), this.getScxmlTransitionType(), null, "transition", null, 1, 1, ScxmlHistoryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlHistoryType_ScxmlExtraContent1(), ecorePackage.getEFeatureMapEntry(), "scxmlExtraContent1", null, 0, -1, ScxmlHistoryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlHistoryType_Any1(), ecorePackage.getEFeatureMapEntry(), "any1", null, 0, -1, ScxmlHistoryType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlHistoryType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ScxmlHistoryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlHistoryType_Type(), this.getHistoryTypeDatatype(), "type", null, 0, 1, ScxmlHistoryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlHistoryType_AnyAttribute(), ecorePackage.getEFeatureMapEntry(), "anyAttribute", null, 0, -1, ScxmlHistoryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(scxmlIfTypeEClass, ScxmlIfType.class, "ScxmlIfType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getScxmlIfType_ScxmlCoreExecutablecontent(), ecorePackage.getEFeatureMapEntry(), "scxmlCoreExecutablecontent", null, 0, -1, ScxmlIfType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlIfType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, ScxmlIfType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlIfType_Raise(), this.getScxmlRaiseType(), null, "raise", null, 0, -1, ScxmlIfType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlIfType_If(), this.getScxmlIfType(), null, "if", null, 0, -1, ScxmlIfType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlIfType_Foreach(), this.getScxmlForeachType(), null, "foreach", null, 0, -1, ScxmlIfType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlIfType_Send(), this.getScxmlSendType(), null, "send", null, 0, -1, ScxmlIfType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlIfType_Script(), this.getScxmlScriptType(), null, "script", null, 0, -1, ScxmlIfType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlIfType_Assign(), this.getScxmlAssignType(), null, "assign", null, 0, -1, ScxmlIfType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlIfType_Log(), this.getScxmlLogType(), null, "log", null, 0, -1, ScxmlIfType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlIfType_Cancel(), this.getScxmlCancelType(), null, "cancel", null, 0, -1, ScxmlIfType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlIfType_Elseif(), this.getScxmlElseifType(), null, "elseif", null, 0, 1, ScxmlIfType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlIfType_ScxmlCoreExecutablecontent1(), ecorePackage.getEFeatureMapEntry(), "scxmlCoreExecutablecontent1", null, 0, -1, ScxmlIfType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlIfType_Any1(), ecorePackage.getEFeatureMapEntry(), "any1", null, 0, -1, ScxmlIfType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlIfType_Raise1(), this.getScxmlRaiseType(), null, "raise1", null, 0, -1, ScxmlIfType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlIfType_If1(), this.getScxmlIfType(), null, "if1", null, 0, -1, ScxmlIfType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlIfType_Foreach1(), this.getScxmlForeachType(), null, "foreach1", null, 0, -1, ScxmlIfType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlIfType_Send1(), this.getScxmlSendType(), null, "send1", null, 0, -1, ScxmlIfType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlIfType_Script1(), this.getScxmlScriptType(), null, "script1", null, 0, -1, ScxmlIfType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlIfType_Assign1(), this.getScxmlAssignType(), null, "assign1", null, 0, -1, ScxmlIfType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlIfType_Log1(), this.getScxmlLogType(), null, "log1", null, 0, -1, ScxmlIfType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlIfType_Cancel1(), this.getScxmlCancelType(), null, "cancel1", null, 0, -1, ScxmlIfType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlIfType_Else(), this.getScxmlElseType(), null, "else", null, 0, 1, ScxmlIfType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlIfType_ScxmlCoreExecutablecontent2(), ecorePackage.getEFeatureMapEntry(), "scxmlCoreExecutablecontent2", null, 0, -1, ScxmlIfType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlIfType_Any2(), ecorePackage.getEFeatureMapEntry(), "any2", null, 0, -1, ScxmlIfType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlIfType_Raise2(), this.getScxmlRaiseType(), null, "raise2", null, 0, -1, ScxmlIfType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlIfType_If2(), this.getScxmlIfType(), null, "if2", null, 0, -1, ScxmlIfType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlIfType_Foreach2(), this.getScxmlForeachType(), null, "foreach2", null, 0, -1, ScxmlIfType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlIfType_Send2(), this.getScxmlSendType(), null, "send2", null, 0, -1, ScxmlIfType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlIfType_Script2(), this.getScxmlScriptType(), null, "script2", null, 0, -1, ScxmlIfType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlIfType_Assign2(), this.getScxmlAssignType(), null, "assign2", null, 0, -1, ScxmlIfType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlIfType_Log2(), this.getScxmlLogType(), null, "log2", null, 0, -1, ScxmlIfType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlIfType_Cancel2(), this.getScxmlCancelType(), null, "cancel2", null, 0, -1, ScxmlIfType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlIfType_Cond(), this.getCondLangDatatype(), "cond", null, 1, 1, ScxmlIfType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlIfType_AnyAttribute(), ecorePackage.getEFeatureMapEntry(), "anyAttribute", null, 0, -1, ScxmlIfType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(scxmlInitialTypeEClass, ScxmlInitialType.class, "ScxmlInitialType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getScxmlInitialType_ScxmlExtraContent(), ecorePackage.getEFeatureMapEntry(), "scxmlExtraContent", null, 0, -1, ScxmlInitialType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlInitialType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, ScxmlInitialType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlInitialType_Transition(), this.getScxmlTransitionType(), null, "transition", null, 1, 1, ScxmlInitialType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlInitialType_ScxmlExtraContent1(), ecorePackage.getEFeatureMapEntry(), "scxmlExtraContent1", null, 0, -1, ScxmlInitialType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlInitialType_Any1(), ecorePackage.getEFeatureMapEntry(), "any1", null, 0, -1, ScxmlInitialType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlInitialType_AnyAttribute(), ecorePackage.getEFeatureMapEntry(), "anyAttribute", null, 0, -1, ScxmlInitialType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(scxmlInvokeTypeEClass, ScxmlInvokeType.class, "ScxmlInvokeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getScxmlInvokeType_ScxmlInvokeMix(), ecorePackage.getEFeatureMapEntry(), "scxmlInvokeMix", null, 0, -1, ScxmlInvokeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlInvokeType_Content(), this.getScxmlContentType(), null, "content", null, 0, -1, ScxmlInvokeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlInvokeType_Param(), this.getScxmlParamType(), null, "param", null, 0, -1, ScxmlInvokeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlInvokeType_Finalize(), this.getScxmlFinalizeType(), null, "finalize", null, 0, -1, ScxmlInvokeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlInvokeType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, ScxmlInvokeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlInvokeType_Autoforward(), this.getBooleanDatatype(), "autoforward", "false", 0, 1, ScxmlInvokeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlInvokeType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ScxmlInvokeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlInvokeType_Idlocation(), this.getLocLangDatatype(), "idlocation", null, 0, 1, ScxmlInvokeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlInvokeType_Namelist(), theXMLTypePackage.getString(), "namelist", null, 0, 1, ScxmlInvokeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlInvokeType_Src(), this.getURIDatatype(), "src", null, 0, 1, ScxmlInvokeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlInvokeType_Srcexpr(), this.getValueLangDatatype(), "srcexpr", null, 0, 1, ScxmlInvokeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlInvokeType_Type(), theXMLTypePackage.getString(), "type", "scxml", 0, 1, ScxmlInvokeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlInvokeType_Typeexpr(), this.getValueLangDatatype(), "typeexpr", null, 0, 1, ScxmlInvokeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlInvokeType_AnyAttribute(), ecorePackage.getEFeatureMapEntry(), "anyAttribute", null, 0, -1, ScxmlInvokeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(scxmlLogTypeEClass, ScxmlLogType.class, "ScxmlLogType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getScxmlLogType_ScxmlExtraContent(), ecorePackage.getEFeatureMapEntry(), "scxmlExtraContent", null, 0, -1, ScxmlLogType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlLogType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, ScxmlLogType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlLogType_Expr(), this.getValueLangDatatype(), "expr", null, 0, 1, ScxmlLogType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlLogType_Label(), theXMLTypePackage.getString(), "label", null, 0, 1, ScxmlLogType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlLogType_AnyAttribute(), ecorePackage.getEFeatureMapEntry(), "anyAttribute", null, 0, -1, ScxmlLogType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(scxmlOnentryTypeEClass, ScxmlOnentryType.class, "ScxmlOnentryType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getScxmlOnentryType_ScxmlCoreExecutablecontent(), ecorePackage.getEFeatureMapEntry(), "scxmlCoreExecutablecontent", null, 0, -1, ScxmlOnentryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlOnentryType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, ScxmlOnentryType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlOnentryType_Raise(), this.getScxmlRaiseType(), null, "raise", null, 0, -1, ScxmlOnentryType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlOnentryType_If(), this.getScxmlIfType(), null, "if", null, 0, -1, ScxmlOnentryType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlOnentryType_Foreach(), this.getScxmlForeachType(), null, "foreach", null, 0, -1, ScxmlOnentryType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlOnentryType_Send(), this.getScxmlSendType(), null, "send", null, 0, -1, ScxmlOnentryType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlOnentryType_Script(), this.getScxmlScriptType(), null, "script", null, 0, -1, ScxmlOnentryType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlOnentryType_Assign(), this.getScxmlAssignType(), null, "assign", null, 0, -1, ScxmlOnentryType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlOnentryType_Log(), this.getScxmlLogType(), null, "log", null, 0, -1, ScxmlOnentryType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlOnentryType_Cancel(), this.getScxmlCancelType(), null, "cancel", null, 0, -1, ScxmlOnentryType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlOnentryType_AnyAttribute(), ecorePackage.getEFeatureMapEntry(), "anyAttribute", null, 0, -1, ScxmlOnentryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(scxmlOnexitTypeEClass, ScxmlOnexitType.class, "ScxmlOnexitType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getScxmlOnexitType_ScxmlCoreExecutablecontent(), ecorePackage.getEFeatureMapEntry(), "scxmlCoreExecutablecontent", null, 0, -1, ScxmlOnexitType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlOnexitType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, ScxmlOnexitType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlOnexitType_Raise(), this.getScxmlRaiseType(), null, "raise", null, 0, -1, ScxmlOnexitType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlOnexitType_If(), this.getScxmlIfType(), null, "if", null, 0, -1, ScxmlOnexitType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlOnexitType_Foreach(), this.getScxmlForeachType(), null, "foreach", null, 0, -1, ScxmlOnexitType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlOnexitType_Send(), this.getScxmlSendType(), null, "send", null, 0, -1, ScxmlOnexitType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlOnexitType_Script(), this.getScxmlScriptType(), null, "script", null, 0, -1, ScxmlOnexitType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlOnexitType_Assign(), this.getScxmlAssignType(), null, "assign", null, 0, -1, ScxmlOnexitType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlOnexitType_Log(), this.getScxmlLogType(), null, "log", null, 0, -1, ScxmlOnexitType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlOnexitType_Cancel(), this.getScxmlCancelType(), null, "cancel", null, 0, -1, ScxmlOnexitType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlOnexitType_AnyAttribute(), ecorePackage.getEFeatureMapEntry(), "anyAttribute", null, 0, -1, ScxmlOnexitType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(scxmlParallelTypeEClass, ScxmlParallelType.class, "ScxmlParallelType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getScxmlParallelType_ScxmlParallelMix(), ecorePackage.getEFeatureMapEntry(), "scxmlParallelMix", null, 0, -1, ScxmlParallelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlParallelType_Onentry(), this.getScxmlOnentryType(), null, "onentry", null, 0, -1, ScxmlParallelType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlParallelType_Onexit(), this.getScxmlOnexitType(), null, "onexit", null, 0, -1, ScxmlParallelType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlParallelType_Transition(), this.getScxmlTransitionType(), null, "transition", null, 0, -1, ScxmlParallelType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlParallelType_State(), this.getScxmlStateType(), null, "state", null, 0, -1, ScxmlParallelType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlParallelType_Parallel(), this.getScxmlParallelType(), null, "parallel", null, 0, -1, ScxmlParallelType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlParallelType_History(), this.getScxmlHistoryType(), null, "history", null, 0, -1, ScxmlParallelType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlParallelType_Datamodel(), this.getScxmlDatamodelType(), null, "datamodel", null, 0, -1, ScxmlParallelType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlParallelType_Invoke(), this.getScxmlInvokeType(), null, "invoke", null, 0, -1, ScxmlParallelType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlParallelType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, ScxmlParallelType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlParallelType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ScxmlParallelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlParallelType_AnyAttribute(), ecorePackage.getEFeatureMapEntry(), "anyAttribute", null, 0, -1, ScxmlParallelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(scxmlParamTypeEClass, ScxmlParamType.class, "ScxmlParamType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getScxmlParamType_ScxmlExtraContent(), ecorePackage.getEFeatureMapEntry(), "scxmlExtraContent", null, 0, -1, ScxmlParamType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlParamType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, ScxmlParamType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlParamType_Expr(), this.getValueLangDatatype(), "expr", null, 0, 1, ScxmlParamType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlParamType_Location(), this.getLocLangDatatype(), "location", null, 0, 1, ScxmlParamType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlParamType_Name(), theXMLTypePackage.getNMTOKEN(), "name", null, 1, 1, ScxmlParamType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlParamType_AnyAttribute(), ecorePackage.getEFeatureMapEntry(), "anyAttribute", null, 0, -1, ScxmlParamType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(scxmlRaiseTypeEClass, ScxmlRaiseType.class, "ScxmlRaiseType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getScxmlRaiseType_Event(), theXMLTypePackage.getNMTOKEN(), "event", null, 1, 1, ScxmlRaiseType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlRaiseType_AnyAttribute(), ecorePackage.getEFeatureMapEntry(), "anyAttribute", null, 0, -1, ScxmlRaiseType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(scxmlScriptTypeEClass, ScxmlScriptType.class, "ScxmlScriptType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getScxmlScriptType_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, ScxmlScriptType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlScriptType_ScxmlExtraContent(), ecorePackage.getEFeatureMapEntry(), "scxmlExtraContent", null, 0, -1, ScxmlScriptType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlScriptType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, ScxmlScriptType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlScriptType_Src(), this.getURIDatatype(), "src", null, 0, 1, ScxmlScriptType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlScriptType_AnyAttribute(), ecorePackage.getEFeatureMapEntry(), "anyAttribute", null, 0, -1, ScxmlScriptType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(scxmlScxmlTypeEClass, ScxmlScxmlType.class, "ScxmlScxmlType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getScxmlScxmlType_ScxmlScxmlMix(), ecorePackage.getEFeatureMapEntry(), "scxmlScxmlMix", null, 0, -1, ScxmlScxmlType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlScxmlType_State(), this.getScxmlStateType(), null, "state", null, 0, -1, ScxmlScxmlType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlScxmlType_Parallel(), this.getScxmlParallelType(), null, "parallel", null, 0, -1, ScxmlScxmlType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlScxmlType_Final(), this.getScxmlFinalType(), null, "final", null, 0, -1, ScxmlScxmlType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlScxmlType_Datamodel(), this.getScxmlDatamodelType(), null, "datamodel", null, 0, -1, ScxmlScxmlType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlScxmlType_Script(), this.getScxmlScriptType(), null, "script", null, 0, -1, ScxmlScxmlType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlScxmlType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, ScxmlScxmlType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlScxmlType_Binding(), this.getBindingDatatype(), "binding", null, 0, 1, ScxmlScxmlType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlScxmlType_Datamodel1(), theXMLTypePackage.getNMTOKEN(), "datamodel1", "null", 0, 1, ScxmlScxmlType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlScxmlType_Exmode(), this.getExmodeDatatype(), "exmode", null, 0, 1, ScxmlScxmlType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlScxmlType_Initial(), theXMLTypePackage.getIDREFS(), "initial", null, 0, 1, ScxmlScxmlType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlScxmlType_Name(), theXMLTypePackage.getNMTOKEN(), "name", null, 0, 1, ScxmlScxmlType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlScxmlType_Version(), theXMLTypePackage.getDecimal(), "version", "1.0", 1, 1, ScxmlScxmlType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlScxmlType_AnyAttribute(), ecorePackage.getEFeatureMapEntry(), "anyAttribute", null, 0, -1, ScxmlScxmlType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(scxmlSendTypeEClass, ScxmlSendType.class, "ScxmlSendType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getScxmlSendType_ScxmlSendMix(), ecorePackage.getEFeatureMapEntry(), "scxmlSendMix", null, 0, -1, ScxmlSendType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlSendType_Content(), this.getScxmlContentType(), null, "content", null, 0, -1, ScxmlSendType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlSendType_Param(), this.getScxmlParamType(), null, "param", null, 0, -1, ScxmlSendType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlSendType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, ScxmlSendType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlSendType_Delay(), this.getDurationDatatype(), "delay", "0s", 0, 1, ScxmlSendType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlSendType_Delayexpr(), this.getValueLangDatatype(), "delayexpr", null, 0, 1, ScxmlSendType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlSendType_Event(), this.getEventTypeDatatype(), "event", null, 0, 1, ScxmlSendType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlSendType_Eventexpr(), this.getValueLangDatatype(), "eventexpr", null, 0, 1, ScxmlSendType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlSendType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ScxmlSendType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlSendType_Idlocation(), this.getLocLangDatatype(), "idlocation", null, 0, 1, ScxmlSendType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlSendType_Namelist(), theXMLTypePackage.getString(), "namelist", null, 0, 1, ScxmlSendType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlSendType_Target(), this.getURIDatatype(), "target", null, 0, 1, ScxmlSendType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlSendType_Targetexpr(), this.getValueLangDatatype(), "targetexpr", null, 0, 1, ScxmlSendType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlSendType_Type(), theXMLTypePackage.getString(), "type", "scxml", 0, 1, ScxmlSendType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlSendType_Typeexpr(), this.getValueLangDatatype(), "typeexpr", null, 0, 1, ScxmlSendType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlSendType_AnyAttribute(), ecorePackage.getEFeatureMapEntry(), "anyAttribute", null, 0, -1, ScxmlSendType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(scxmlStateTypeEClass, ScxmlStateType.class, "ScxmlStateType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getScxmlStateType_ScxmlStateMix(), ecorePackage.getEFeatureMapEntry(), "scxmlStateMix", null, 0, -1, ScxmlStateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlStateType_Onentry(), this.getScxmlOnentryType(), null, "onentry", null, 0, -1, ScxmlStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlStateType_Onexit(), this.getScxmlOnexitType(), null, "onexit", null, 0, -1, ScxmlStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlStateType_Transition(), this.getScxmlTransitionType(), null, "transition", null, 0, -1, ScxmlStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlStateType_Initial(), this.getScxmlInitialType(), null, "initial", null, 0, -1, ScxmlStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlStateType_State(), this.getScxmlStateType(), null, "state", null, 0, -1, ScxmlStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlStateType_Parallel(), this.getScxmlParallelType(), null, "parallel", null, 0, -1, ScxmlStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlStateType_Final(), this.getScxmlFinalType(), null, "final", null, 0, -1, ScxmlStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlStateType_History(), this.getScxmlHistoryType(), null, "history", null, 0, -1, ScxmlStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlStateType_Datamodel(), this.getScxmlDatamodelType(), null, "datamodel", null, 0, -1, ScxmlStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlStateType_Invoke(), this.getScxmlInvokeType(), null, "invoke", null, 0, -1, ScxmlStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlStateType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, ScxmlStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlStateType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ScxmlStateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlStateType_Initial1(), theXMLTypePackage.getIDREFS(), "initial1", null, 0, 1, ScxmlStateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlStateType_AnyAttribute(), ecorePackage.getEFeatureMapEntry(), "anyAttribute", null, 0, -1, ScxmlStateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(scxmlTransitionTypeEClass, ScxmlTransitionType.class, "ScxmlTransitionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getScxmlTransitionType_ScxmlCoreExecutablecontent(), ecorePackage.getEFeatureMapEntry(), "scxmlCoreExecutablecontent", null, 0, -1, ScxmlTransitionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlTransitionType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, ScxmlTransitionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlTransitionType_Raise(), this.getScxmlRaiseType(), null, "raise", null, 0, -1, ScxmlTransitionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlTransitionType_If(), this.getScxmlIfType(), null, "if", null, 0, -1, ScxmlTransitionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlTransitionType_Foreach(), this.getScxmlForeachType(), null, "foreach", null, 0, -1, ScxmlTransitionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlTransitionType_Send(), this.getScxmlSendType(), null, "send", null, 0, -1, ScxmlTransitionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlTransitionType_Script(), this.getScxmlScriptType(), null, "script", null, 0, -1, ScxmlTransitionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlTransitionType_Assign(), this.getScxmlAssignType(), null, "assign", null, 0, -1, ScxmlTransitionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlTransitionType_Log(), this.getScxmlLogType(), null, "log", null, 0, -1, ScxmlTransitionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getScxmlTransitionType_Cancel(), this.getScxmlCancelType(), null, "cancel", null, 0, -1, ScxmlTransitionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlTransitionType_Cond(), this.getCondLangDatatype(), "cond", null, 0, 1, ScxmlTransitionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlTransitionType_Event(), this.getEventTypesDatatype(), "event", null, 0, 1, ScxmlTransitionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlTransitionType_Target(), theXMLTypePackage.getIDREFS(), "target", null, 0, 1, ScxmlTransitionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlTransitionType_Type(), this.getTransitionTypeDatatype(), "type", null, 0, 1, ScxmlTransitionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getScxmlTransitionType_AnyAttribute(), ecorePackage.getEFeatureMapEntry(), "anyAttribute", null, 0, -1, ScxmlTransitionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(assignTypeDatatypeEEnum, AssignTypeDatatype.class, "AssignTypeDatatype");
        addEEnumLiteral(assignTypeDatatypeEEnum, AssignTypeDatatype.REPLACECHILDREN);
        addEEnumLiteral(assignTypeDatatypeEEnum, AssignTypeDatatype.FIRSTCHILD);
        addEEnumLiteral(assignTypeDatatypeEEnum, AssignTypeDatatype.LASTCHILD);
        addEEnumLiteral(assignTypeDatatypeEEnum, AssignTypeDatatype.PREVIOUSSIBLING);
        addEEnumLiteral(assignTypeDatatypeEEnum, AssignTypeDatatype.NEXTSIBLING);
        addEEnumLiteral(assignTypeDatatypeEEnum, AssignTypeDatatype.REPLACE);
        addEEnumLiteral(assignTypeDatatypeEEnum, AssignTypeDatatype.DELETE);
        addEEnumLiteral(assignTypeDatatypeEEnum, AssignTypeDatatype.ADDATTRIBUTE);

        initEEnum(bindingDatatypeEEnum, BindingDatatype.class, "BindingDatatype");
        addEEnumLiteral(bindingDatatypeEEnum, BindingDatatype.EARLY);
        addEEnumLiteral(bindingDatatypeEEnum, BindingDatatype.LATE);

        initEEnum(booleanDatatypeEEnum, BooleanDatatype.class, "BooleanDatatype");
        addEEnumLiteral(booleanDatatypeEEnum, BooleanDatatype.TRUE);
        addEEnumLiteral(booleanDatatypeEEnum, BooleanDatatype.FALSE);

        initEEnum(exmodeDatatypeEEnum, ExmodeDatatype.class, "ExmodeDatatype");
        addEEnumLiteral(exmodeDatatypeEEnum, ExmodeDatatype.LAX);
        addEEnumLiteral(exmodeDatatypeEEnum, ExmodeDatatype.STRICT);

        initEEnum(historyTypeDatatypeEEnum, HistoryTypeDatatype.class, "HistoryTypeDatatype");
        addEEnumLiteral(historyTypeDatatypeEEnum, HistoryTypeDatatype.SHALLOW);
        addEEnumLiteral(historyTypeDatatypeEEnum, HistoryTypeDatatype.DEEP);

        initEEnum(transitionTypeDatatypeEEnum, TransitionTypeDatatype.class, "TransitionTypeDatatype");
        addEEnumLiteral(transitionTypeDatatypeEEnum, TransitionTypeDatatype.INTERNAL);
        addEEnumLiteral(transitionTypeDatatypeEEnum, TransitionTypeDatatype.EXTERNAL);

        // Initialize data types
        initEDataType(assignTypeDatatypeObjectEDataType, AssignTypeDatatype.class, "AssignTypeDatatypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
        initEDataType(bindingDatatypeObjectEDataType, BindingDatatype.class, "BindingDatatypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
        initEDataType(booleanDatatypeObjectEDataType, BooleanDatatype.class, "BooleanDatatypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
        initEDataType(condLangDatatypeEDataType, String.class, "CondLangDatatype", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
        initEDataType(durationDatatypeEDataType, String.class, "DurationDatatype", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
        initEDataType(eventTypeDatatypeEDataType, String.class, "EventTypeDatatype", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
        initEDataType(eventTypesDatatypeEDataType, String.class, "EventTypesDatatype", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
        initEDataType(exmodeDatatypeObjectEDataType, ExmodeDatatype.class, "ExmodeDatatypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
        initEDataType(historyTypeDatatypeObjectEDataType, HistoryTypeDatatype.class, "HistoryTypeDatatypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
        initEDataType(integerDatatypeEDataType, BigInteger.class, "IntegerDatatype", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
        initEDataType(locLangDatatypeEDataType, String.class, "LocLangDatatype", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
        initEDataType(transitionTypeDatatypeObjectEDataType, TransitionTypeDatatype.class, "TransitionTypeDatatypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
        initEDataType(uriDatatypeEDataType, String.class, "URIDatatype", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
        initEDataType(valueLangDatatypeEDataType, String.class, "ValueLangDatatype", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

        // Create resource
        createResource(eNS_URI);

        // Create annotations
        // http:///org/eclipse/emf/ecore/util/ExtendedMetaData
        createExtendedMetaDataAnnotations();
    }

    /**
     * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void createExtendedMetaDataAnnotations() {
        String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";	
        addAnnotation
          (assignTypeDatatypeEEnum, 
           source, 
           new String[] {
             "name", "AssignType.datatype"
           });	
        addAnnotation
          (assignTypeDatatypeObjectEDataType, 
           source, 
           new String[] {
             "name", "AssignType.datatype:Object",
             "baseType", "AssignType.datatype"
           });	
        addAnnotation
          (bindingDatatypeEEnum, 
           source, 
           new String[] {
             "name", "Binding.datatype"
           });	
        addAnnotation
          (bindingDatatypeObjectEDataType, 
           source, 
           new String[] {
             "name", "Binding.datatype:Object",
             "baseType", "Binding.datatype"
           });	
        addAnnotation
          (booleanDatatypeEEnum, 
           source, 
           new String[] {
             "name", "Boolean.datatype"
           });	
        addAnnotation
          (booleanDatatypeObjectEDataType, 
           source, 
           new String[] {
             "name", "Boolean.datatype:Object",
             "baseType", "Boolean.datatype"
           });	
        addAnnotation
          (condLangDatatypeEDataType, 
           source, 
           new String[] {
             "name", "CondLang.datatype",
             "baseType", "http://www.eclipse.org/emf/2003/XMLType#string"
           });	
        addAnnotation
          (documentRootEClass, 
           source, 
           new String[] {
             "name", "",
             "kind", "mixed"
           });	
        addAnnotation
          (getDocumentRoot_Mixed(), 
           source, 
           new String[] {
             "kind", "elementWildcard",
             "name", ":mixed"
           });	
        addAnnotation
          (getDocumentRoot_XMLNSPrefixMap(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "xmlns:prefix"
           });	
        addAnnotation
          (getDocumentRoot_XSISchemaLocation(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "xsi:schemaLocation"
           });	
        addAnnotation
          (getDocumentRoot_Assign(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "assign",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getDocumentRoot_Cancel(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "cancel",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getDocumentRoot_Content(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "content",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getDocumentRoot_Data(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "data",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getDocumentRoot_Datamodel(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "datamodel",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getDocumentRoot_Donedata(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "donedata",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getDocumentRoot_Else(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "else",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getDocumentRoot_Elseif(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "elseif",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getDocumentRoot_Final(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "final",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getDocumentRoot_Finalize(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "finalize",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getDocumentRoot_Foreach(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "foreach",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getDocumentRoot_History(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "history",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getDocumentRoot_If(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "if",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getDocumentRoot_Initial(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "initial",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getDocumentRoot_Invoke(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "invoke",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getDocumentRoot_Log(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "log",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getDocumentRoot_Onentry(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "onentry",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getDocumentRoot_Onexit(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "onexit",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getDocumentRoot_Parallel(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "parallel",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getDocumentRoot_Param(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "param",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getDocumentRoot_Raise(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "raise",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getDocumentRoot_Script(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "script",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getDocumentRoot_Scxml(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "scxml",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getDocumentRoot_Send(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "send",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getDocumentRoot_State(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "state",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getDocumentRoot_Transition(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "transition",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (durationDatatypeEDataType, 
           source, 
           new String[] {
             "name", "Duration.datatype",
             "baseType", "http://www.eclipse.org/emf/2003/XMLType#string",
             "pattern", "\\d*(\\.\\d+)?(ms|s|m|h|d)"
           });	
        addAnnotation
          (eventTypeDatatypeEDataType, 
           source, 
           new String[] {
             "name", "EventType.datatype",
             "baseType", "http://www.eclipse.org/emf/2003/XMLType#token",
             "pattern", "(\\i|\\d|\\-)+(\\.(\\i|\\d|\\-)+)*"
           });	
        addAnnotation
          (eventTypesDatatypeEDataType, 
           source, 
           new String[] {
             "name", "EventTypes.datatype",
             "baseType", "http://www.eclipse.org/emf/2003/XMLType#token",
             "pattern", "\\.?\\*|(\\i|\\d|\\-)+(\\.(\\i|\\d|\\-)+)*(\\.\\*)?(\\s(\\i|\\d|\\-)+(\\.(\\i|\\d|\\-)+)*(\\.\\*)?)*"
           });	
        addAnnotation
          (exmodeDatatypeEEnum, 
           source, 
           new String[] {
             "name", "Exmode.datatype"
           });	
        addAnnotation
          (exmodeDatatypeObjectEDataType, 
           source, 
           new String[] {
             "name", "Exmode.datatype:Object",
             "baseType", "Exmode.datatype"
           });	
        addAnnotation
          (historyTypeDatatypeEEnum, 
           source, 
           new String[] {
             "name", "HistoryType.datatype"
           });	
        addAnnotation
          (historyTypeDatatypeObjectEDataType, 
           source, 
           new String[] {
             "name", "HistoryType.datatype:Object",
             "baseType", "HistoryType.datatype"
           });	
        addAnnotation
          (integerDatatypeEDataType, 
           source, 
           new String[] {
             "name", "Integer.datatype",
             "baseType", "http://www.eclipse.org/emf/2003/XMLType#nonNegativeInteger"
           });	
        addAnnotation
          (locLangDatatypeEDataType, 
           source, 
           new String[] {
             "name", "LocLang.datatype",
             "baseType", "http://www.eclipse.org/emf/2003/XMLType#string"
           });	
        addAnnotation
          (scxmlAssignTypeEClass, 
           source, 
           new String[] {
             "name", "scxml.assign.type",
             "kind", "mixed"
           });	
        addAnnotation
          (getScxmlAssignType_Mixed(), 
           source, 
           new String[] {
             "kind", "elementWildcard",
             "name", ":mixed"
           });	
        addAnnotation
          (getScxmlAssignType_Any(), 
           source, 
           new String[] {
             "kind", "elementWildcard",
             "wildcards", "##any",
             "name", ":1",
             "processing", "lax"
           });	
        addAnnotation
          (getScxmlAssignType_Attr(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "attr"
           });	
        addAnnotation
          (getScxmlAssignType_Expr(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "expr"
           });	
        addAnnotation
          (getScxmlAssignType_Location(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "location"
           });	
        addAnnotation
          (getScxmlAssignType_Type(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "type"
           });	
        addAnnotation
          (getScxmlAssignType_AnyAttribute(), 
           source, 
           new String[] {
             "kind", "attributeWildcard",
             "wildcards", "##other",
             "name", ":6",
             "processing", "lax"
           });	
        addAnnotation
          (scxmlCancelTypeEClass, 
           source, 
           new String[] {
             "name", "scxml.cancel.type",
             "kind", "elementOnly"
           });	
        addAnnotation
          (getScxmlCancelType_ScxmlExtraContent(), 
           source, 
           new String[] {
             "kind", "group",
             "name", "ScxmlExtraContent:0"
           });	
        addAnnotation
          (getScxmlCancelType_Any(), 
           source, 
           new String[] {
             "kind", "elementWildcard",
             "wildcards", "##other",
             "name", ":1",
             "processing", "lax",
             "group", "#ScxmlExtraContent:0"
           });	
        addAnnotation
          (getScxmlCancelType_Sendid(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "sendid"
           });	
        addAnnotation
          (getScxmlCancelType_Sendidexpr(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "sendidexpr"
           });	
        addAnnotation
          (getScxmlCancelType_AnyAttribute(), 
           source, 
           new String[] {
             "kind", "attributeWildcard",
             "wildcards", "##other",
             "name", ":4",
             "processing", "lax"
           });	
        addAnnotation
          (scxmlContentTypeEClass, 
           source, 
           new String[] {
             "name", "scxml.content.type",
             "kind", "mixed"
           });	
        addAnnotation
          (getScxmlContentType_Mixed(), 
           source, 
           new String[] {
             "kind", "elementWildcard",
             "name", ":mixed"
           });	
        addAnnotation
          (getScxmlContentType_Any(), 
           source, 
           new String[] {
             "kind", "elementWildcard",
             "wildcards", "##any",
             "name", ":1",
             "processing", "lax"
           });	
        addAnnotation
          (getScxmlContentType_Expr(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "expr"
           });	
        addAnnotation
          (getScxmlContentType_AnyAttribute(), 
           source, 
           new String[] {
             "kind", "attributeWildcard",
             "wildcards", "##other",
             "name", ":3",
             "processing", "lax"
           });	
        addAnnotation
          (scxmlDatamodelTypeEClass, 
           source, 
           new String[] {
             "name", "scxml.datamodel.type",
             "kind", "elementOnly"
           });	
        addAnnotation
          (getScxmlDatamodelType_Data(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "data",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getScxmlDatamodelType_ScxmlExtraContent(), 
           source, 
           new String[] {
             "kind", "group",
             "name", "ScxmlExtraContent:1"
           });	
        addAnnotation
          (getScxmlDatamodelType_Any(), 
           source, 
           new String[] {
             "kind", "elementWildcard",
             "wildcards", "##other",
             "name", ":2",
             "processing", "lax",
             "group", "#ScxmlExtraContent:1"
           });	
        addAnnotation
          (getScxmlDatamodelType_AnyAttribute(), 
           source, 
           new String[] {
             "kind", "attributeWildcard",
             "wildcards", "##other",
             "name", ":3",
             "processing", "lax"
           });	
        addAnnotation
          (scxmlDataTypeEClass, 
           source, 
           new String[] {
             "name", "scxml.data.type",
             "kind", "mixed"
           });	
        addAnnotation
          (getScxmlDataType_Mixed(), 
           source, 
           new String[] {
             "kind", "elementWildcard",
             "name", ":mixed"
           });	
        addAnnotation
          (getScxmlDataType_Any(), 
           source, 
           new String[] {
             "kind", "elementWildcard",
             "wildcards", "##any",
             "name", ":1",
             "processing", "lax"
           });	
        addAnnotation
          (getScxmlDataType_Expr(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "expr"
           });	
        addAnnotation
          (getScxmlDataType_Id(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "id"
           });	
        addAnnotation
          (getScxmlDataType_Src(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "src"
           });	
        addAnnotation
          (getScxmlDataType_AnyAttribute(), 
           source, 
           new String[] {
             "kind", "attributeWildcard",
             "wildcards", "##other",
             "name", ":5",
             "processing", "lax"
           });	
        addAnnotation
          (scxmlDonedataTypeEClass, 
           source, 
           new String[] {
             "name", "scxml.donedata.type",
             "kind", "elementOnly"
           });	
        addAnnotation
          (getScxmlDonedataType_Content(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "content",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getScxmlDonedataType_Param(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "param",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getScxmlDonedataType_AnyAttribute(), 
           source, 
           new String[] {
             "kind", "attributeWildcard",
             "wildcards", "##other",
             "name", ":2",
             "processing", "lax"
           });	
        addAnnotation
          (scxmlElseifTypeEClass, 
           source, 
           new String[] {
             "name", "scxml.elseif.type",
             "kind", "elementOnly"
           });	
        addAnnotation
          (getScxmlElseifType_Cond(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "cond"
           });	
        addAnnotation
          (getScxmlElseifType_AnyAttribute(), 
           source, 
           new String[] {
             "kind", "attributeWildcard",
             "wildcards", "##other",
             "name", ":1",
             "processing", "lax"
           });	
        addAnnotation
          (scxmlElseTypeEClass, 
           source, 
           new String[] {
             "name", "scxml.else.type",
             "kind", "elementOnly"
           });	
        addAnnotation
          (getScxmlElseType_AnyAttribute(), 
           source, 
           new String[] {
             "kind", "attributeWildcard",
             "wildcards", "##other",
             "name", ":0",
             "processing", "lax"
           });	
        addAnnotation
          (scxmlFinalizeTypeEClass, 
           source, 
           new String[] {
             "name", "scxml.finalize.type",
             "kind", "elementOnly"
           });	
        addAnnotation
          (getScxmlFinalizeType_ScxmlCoreExecutablecontent(), 
           source, 
           new String[] {
             "kind", "group",
             "name", "ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlFinalizeType_Any(), 
           source, 
           new String[] {
             "kind", "elementWildcard",
             "wildcards", "##other",
             "name", ":1",
             "processing", "lax",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlFinalizeType_Raise(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "raise",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlFinalizeType_If(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "if",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlFinalizeType_Foreach(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "foreach",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlFinalizeType_Send(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "send",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlFinalizeType_Script(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "script",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlFinalizeType_Assign(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "assign",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlFinalizeType_Log(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "log",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlFinalizeType_Cancel(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "cancel",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlFinalizeType_AnyAttribute(), 
           source, 
           new String[] {
             "kind", "attributeWildcard",
             "wildcards", "##other",
             "name", ":10",
             "processing", "lax"
           });	
        addAnnotation
          (scxmlFinalTypeEClass, 
           source, 
           new String[] {
             "name", "scxml.final.type",
             "kind", "elementOnly"
           });	
        addAnnotation
          (getScxmlFinalType_ScxmlFinalMix(), 
           source, 
           new String[] {
             "kind", "group",
             "name", "ScxmlFinalMix:0"
           });	
        addAnnotation
          (getScxmlFinalType_Onentry(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "onentry",
             "namespace", "##targetNamespace",
             "group", "#ScxmlFinalMix:0"
           });	
        addAnnotation
          (getScxmlFinalType_Onexit(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "onexit",
             "namespace", "##targetNamespace",
             "group", "#ScxmlFinalMix:0"
           });	
        addAnnotation
          (getScxmlFinalType_Donedata(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "donedata",
             "namespace", "##targetNamespace",
             "group", "#ScxmlFinalMix:0"
           });	
        addAnnotation
          (getScxmlFinalType_Any(), 
           source, 
           new String[] {
             "kind", "elementWildcard",
             "wildcards", "##other",
             "name", ":4",
             "processing", "lax",
             "group", "#ScxmlFinalMix:0"
           });	
        addAnnotation
          (getScxmlFinalType_Id(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "id"
           });	
        addAnnotation
          (getScxmlFinalType_AnyAttribute(), 
           source, 
           new String[] {
             "kind", "attributeWildcard",
             "wildcards", "##other",
             "name", ":6",
             "processing", "lax"
           });	
        addAnnotation
          (scxmlForeachTypeEClass, 
           source, 
           new String[] {
             "name", "scxml.foreach.type",
             "kind", "elementOnly"
           });	
        addAnnotation
          (getScxmlForeachType_ScxmlCoreExecutablecontent(), 
           source, 
           new String[] {
             "kind", "group",
             "name", "ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlForeachType_Any(), 
           source, 
           new String[] {
             "kind", "elementWildcard",
             "wildcards", "##other",
             "name", ":1",
             "processing", "lax",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlForeachType_Raise(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "raise",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlForeachType_If(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "if",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlForeachType_Foreach(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "foreach",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlForeachType_Send(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "send",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlForeachType_Script(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "script",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlForeachType_Assign(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "assign",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlForeachType_Log(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "log",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlForeachType_Cancel(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "cancel",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlForeachType_Array(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "array"
           });	
        addAnnotation
          (getScxmlForeachType_Index(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "index"
           });	
        addAnnotation
          (getScxmlForeachType_Item(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "item"
           });	
        addAnnotation
          (getScxmlForeachType_AnyAttribute(), 
           source, 
           new String[] {
             "kind", "attributeWildcard",
             "wildcards", "##other",
             "name", ":13",
             "processing", "lax"
           });	
        addAnnotation
          (scxmlHistoryTypeEClass, 
           source, 
           new String[] {
             "name", "scxml.history.type",
             "kind", "elementOnly"
           });	
        addAnnotation
          (getScxmlHistoryType_ScxmlExtraContent(), 
           source, 
           new String[] {
             "kind", "group",
             "name", "ScxmlExtraContent:0"
           });	
        addAnnotation
          (getScxmlHistoryType_Any(), 
           source, 
           new String[] {
             "kind", "elementWildcard",
             "wildcards", "##other",
             "name", ":1",
             "processing", "lax",
             "group", "#ScxmlExtraContent:0"
           });	
        addAnnotation
          (getScxmlHistoryType_Transition(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "transition",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getScxmlHistoryType_ScxmlExtraContent1(), 
           source, 
           new String[] {
             "kind", "group",
             "name", "ScxmlExtraContent:3"
           });	
        addAnnotation
          (getScxmlHistoryType_Any1(), 
           source, 
           new String[] {
             "kind", "elementWildcard",
             "wildcards", "##other",
             "name", ":4",
             "processing", "lax",
             "group", "#ScxmlExtraContent:3"
           });	
        addAnnotation
          (getScxmlHistoryType_Id(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "id"
           });	
        addAnnotation
          (getScxmlHistoryType_Type(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "type"
           });	
        addAnnotation
          (getScxmlHistoryType_AnyAttribute(), 
           source, 
           new String[] {
             "kind", "attributeWildcard",
             "wildcards", "##other",
             "name", ":7",
             "processing", "lax"
           });	
        addAnnotation
          (scxmlIfTypeEClass, 
           source, 
           new String[] {
             "name", "scxml.if.type",
             "kind", "elementOnly"
           });	
        addAnnotation
          (getScxmlIfType_ScxmlCoreExecutablecontent(), 
           source, 
           new String[] {
             "kind", "group",
             "name", "ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlIfType_Any(), 
           source, 
           new String[] {
             "kind", "elementWildcard",
             "wildcards", "##other",
             "name", ":1",
             "processing", "lax",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlIfType_Raise(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "raise",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlIfType_If(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "if",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlIfType_Foreach(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "foreach",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlIfType_Send(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "send",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlIfType_Script(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "script",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlIfType_Assign(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "assign",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlIfType_Log(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "log",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlIfType_Cancel(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "cancel",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlIfType_Elseif(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "elseif",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getScxmlIfType_ScxmlCoreExecutablecontent1(), 
           source, 
           new String[] {
             "kind", "group",
             "name", "ScxmlCoreExecutablecontent:11"
           });	
        addAnnotation
          (getScxmlIfType_Any1(), 
           source, 
           new String[] {
             "kind", "elementWildcard",
             "wildcards", "##other",
             "name", ":12",
             "processing", "lax",
             "group", "#ScxmlCoreExecutablecontent:11"
           });	
        addAnnotation
          (getScxmlIfType_Raise1(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "raise",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:11"
           });	
        addAnnotation
          (getScxmlIfType_If1(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "if",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:11"
           });	
        addAnnotation
          (getScxmlIfType_Foreach1(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "foreach",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:11"
           });	
        addAnnotation
          (getScxmlIfType_Send1(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "send",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:11"
           });	
        addAnnotation
          (getScxmlIfType_Script1(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "script",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:11"
           });	
        addAnnotation
          (getScxmlIfType_Assign1(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "assign",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:11"
           });	
        addAnnotation
          (getScxmlIfType_Log1(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "log",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:11"
           });	
        addAnnotation
          (getScxmlIfType_Cancel1(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "cancel",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:11"
           });	
        addAnnotation
          (getScxmlIfType_Else(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "else",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getScxmlIfType_ScxmlCoreExecutablecontent2(), 
           source, 
           new String[] {
             "kind", "group",
             "name", "ScxmlCoreExecutablecontent:22"
           });	
        addAnnotation
          (getScxmlIfType_Any2(), 
           source, 
           new String[] {
             "kind", "elementWildcard",
             "wildcards", "##other",
             "name", ":23",
             "processing", "lax",
             "group", "#ScxmlCoreExecutablecontent:22"
           });	
        addAnnotation
          (getScxmlIfType_Raise2(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "raise",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:22"
           });	
        addAnnotation
          (getScxmlIfType_If2(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "if",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:22"
           });	
        addAnnotation
          (getScxmlIfType_Foreach2(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "foreach",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:22"
           });	
        addAnnotation
          (getScxmlIfType_Send2(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "send",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:22"
           });	
        addAnnotation
          (getScxmlIfType_Script2(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "script",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:22"
           });	
        addAnnotation
          (getScxmlIfType_Assign2(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "assign",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:22"
           });	
        addAnnotation
          (getScxmlIfType_Log2(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "log",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:22"
           });	
        addAnnotation
          (getScxmlIfType_Cancel2(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "cancel",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:22"
           });	
        addAnnotation
          (getScxmlIfType_Cond(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "cond"
           });	
        addAnnotation
          (getScxmlIfType_AnyAttribute(), 
           source, 
           new String[] {
             "kind", "attributeWildcard",
             "wildcards", "##other",
             "name", ":33",
             "processing", "lax"
           });	
        addAnnotation
          (scxmlInitialTypeEClass, 
           source, 
           new String[] {
             "name", "scxml.initial.type",
             "kind", "elementOnly"
           });	
        addAnnotation
          (getScxmlInitialType_ScxmlExtraContent(), 
           source, 
           new String[] {
             "kind", "group",
             "name", "ScxmlExtraContent:0"
           });	
        addAnnotation
          (getScxmlInitialType_Any(), 
           source, 
           new String[] {
             "kind", "elementWildcard",
             "wildcards", "##other",
             "name", ":1",
             "processing", "lax",
             "group", "#ScxmlExtraContent:0"
           });	
        addAnnotation
          (getScxmlInitialType_Transition(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "transition",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getScxmlInitialType_ScxmlExtraContent1(), 
           source, 
           new String[] {
             "kind", "group",
             "name", "ScxmlExtraContent:3"
           });	
        addAnnotation
          (getScxmlInitialType_Any1(), 
           source, 
           new String[] {
             "kind", "elementWildcard",
             "wildcards", "##other",
             "name", ":4",
             "processing", "lax",
             "group", "#ScxmlExtraContent:3"
           });	
        addAnnotation
          (getScxmlInitialType_AnyAttribute(), 
           source, 
           new String[] {
             "kind", "attributeWildcard",
             "wildcards", "##other",
             "name", ":5",
             "processing", "lax"
           });	
        addAnnotation
          (scxmlInvokeTypeEClass, 
           source, 
           new String[] {
             "name", "scxml.invoke.type",
             "kind", "elementOnly"
           });	
        addAnnotation
          (getScxmlInvokeType_ScxmlInvokeMix(), 
           source, 
           new String[] {
             "kind", "group",
             "name", "ScxmlInvokeMix:0"
           });	
        addAnnotation
          (getScxmlInvokeType_Content(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "content",
             "namespace", "##targetNamespace",
             "group", "#ScxmlInvokeMix:0"
           });	
        addAnnotation
          (getScxmlInvokeType_Param(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "param",
             "namespace", "##targetNamespace",
             "group", "#ScxmlInvokeMix:0"
           });	
        addAnnotation
          (getScxmlInvokeType_Finalize(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "finalize",
             "namespace", "##targetNamespace",
             "group", "#ScxmlInvokeMix:0"
           });	
        addAnnotation
          (getScxmlInvokeType_Any(), 
           source, 
           new String[] {
             "kind", "elementWildcard",
             "wildcards", "##other",
             "name", ":4",
             "processing", "lax",
             "group", "#ScxmlInvokeMix:0"
           });	
        addAnnotation
          (getScxmlInvokeType_Autoforward(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "autoforward"
           });	
        addAnnotation
          (getScxmlInvokeType_Id(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "id"
           });	
        addAnnotation
          (getScxmlInvokeType_Idlocation(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "idlocation"
           });	
        addAnnotation
          (getScxmlInvokeType_Namelist(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "namelist"
           });	
        addAnnotation
          (getScxmlInvokeType_Src(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "src"
           });	
        addAnnotation
          (getScxmlInvokeType_Srcexpr(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "srcexpr"
           });	
        addAnnotation
          (getScxmlInvokeType_Type(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "type"
           });	
        addAnnotation
          (getScxmlInvokeType_Typeexpr(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "typeexpr"
           });	
        addAnnotation
          (getScxmlInvokeType_AnyAttribute(), 
           source, 
           new String[] {
             "kind", "attributeWildcard",
             "wildcards", "##other",
             "name", ":13",
             "processing", "lax"
           });	
        addAnnotation
          (scxmlLogTypeEClass, 
           source, 
           new String[] {
             "name", "scxml.log.type",
             "kind", "elementOnly"
           });	
        addAnnotation
          (getScxmlLogType_ScxmlExtraContent(), 
           source, 
           new String[] {
             "kind", "group",
             "name", "ScxmlExtraContent:0"
           });	
        addAnnotation
          (getScxmlLogType_Any(), 
           source, 
           new String[] {
             "kind", "elementWildcard",
             "wildcards", "##other",
             "name", ":1",
             "processing", "lax",
             "group", "#ScxmlExtraContent:0"
           });	
        addAnnotation
          (getScxmlLogType_Expr(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "expr"
           });	
        addAnnotation
          (getScxmlLogType_Label(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "label"
           });	
        addAnnotation
          (getScxmlLogType_AnyAttribute(), 
           source, 
           new String[] {
             "kind", "attributeWildcard",
             "wildcards", "##other",
             "name", ":4",
             "processing", "lax"
           });	
        addAnnotation
          (scxmlOnentryTypeEClass, 
           source, 
           new String[] {
             "name", "scxml.onentry.type",
             "kind", "elementOnly"
           });	
        addAnnotation
          (getScxmlOnentryType_ScxmlCoreExecutablecontent(), 
           source, 
           new String[] {
             "kind", "group",
             "name", "ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlOnentryType_Any(), 
           source, 
           new String[] {
             "kind", "elementWildcard",
             "wildcards", "##other",
             "name", ":1",
             "processing", "lax",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlOnentryType_Raise(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "raise",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlOnentryType_If(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "if",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlOnentryType_Foreach(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "foreach",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlOnentryType_Send(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "send",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlOnentryType_Script(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "script",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlOnentryType_Assign(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "assign",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlOnentryType_Log(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "log",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlOnentryType_Cancel(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "cancel",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlOnentryType_AnyAttribute(), 
           source, 
           new String[] {
             "kind", "attributeWildcard",
             "wildcards", "##other",
             "name", ":10",
             "processing", "lax"
           });	
        addAnnotation
          (scxmlOnexitTypeEClass, 
           source, 
           new String[] {
             "name", "scxml.onexit.type",
             "kind", "elementOnly"
           });	
        addAnnotation
          (getScxmlOnexitType_ScxmlCoreExecutablecontent(), 
           source, 
           new String[] {
             "kind", "group",
             "name", "ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlOnexitType_Any(), 
           source, 
           new String[] {
             "kind", "elementWildcard",
             "wildcards", "##other",
             "name", ":1",
             "processing", "lax",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlOnexitType_Raise(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "raise",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlOnexitType_If(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "if",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlOnexitType_Foreach(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "foreach",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlOnexitType_Send(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "send",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlOnexitType_Script(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "script",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlOnexitType_Assign(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "assign",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlOnexitType_Log(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "log",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlOnexitType_Cancel(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "cancel",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlOnexitType_AnyAttribute(), 
           source, 
           new String[] {
             "kind", "attributeWildcard",
             "wildcards", "##other",
             "name", ":10",
             "processing", "lax"
           });	
        addAnnotation
          (scxmlParallelTypeEClass, 
           source, 
           new String[] {
             "name", "scxml.parallel.type",
             "kind", "elementOnly"
           });	
        addAnnotation
          (getScxmlParallelType_ScxmlParallelMix(), 
           source, 
           new String[] {
             "kind", "group",
             "name", "ScxmlParallelMix:0"
           });	
        addAnnotation
          (getScxmlParallelType_Onentry(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "onentry",
             "namespace", "##targetNamespace",
             "group", "#ScxmlParallelMix:0"
           });	
        addAnnotation
          (getScxmlParallelType_Onexit(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "onexit",
             "namespace", "##targetNamespace",
             "group", "#ScxmlParallelMix:0"
           });	
        addAnnotation
          (getScxmlParallelType_Transition(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "transition",
             "namespace", "##targetNamespace",
             "group", "#ScxmlParallelMix:0"
           });	
        addAnnotation
          (getScxmlParallelType_State(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "state",
             "namespace", "##targetNamespace",
             "group", "#ScxmlParallelMix:0"
           });	
        addAnnotation
          (getScxmlParallelType_Parallel(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "parallel",
             "namespace", "##targetNamespace",
             "group", "#ScxmlParallelMix:0"
           });	
        addAnnotation
          (getScxmlParallelType_History(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "history",
             "namespace", "##targetNamespace",
             "group", "#ScxmlParallelMix:0"
           });	
        addAnnotation
          (getScxmlParallelType_Datamodel(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "datamodel",
             "namespace", "##targetNamespace",
             "group", "#ScxmlParallelMix:0"
           });	
        addAnnotation
          (getScxmlParallelType_Invoke(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "invoke",
             "namespace", "##targetNamespace",
             "group", "#ScxmlParallelMix:0"
           });	
        addAnnotation
          (getScxmlParallelType_Any(), 
           source, 
           new String[] {
             "kind", "elementWildcard",
             "wildcards", "##other",
             "name", ":9",
             "processing", "lax",
             "group", "#ScxmlParallelMix:0"
           });	
        addAnnotation
          (getScxmlParallelType_Id(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "id"
           });	
        addAnnotation
          (getScxmlParallelType_AnyAttribute(), 
           source, 
           new String[] {
             "kind", "attributeWildcard",
             "wildcards", "##other",
             "name", ":11",
             "processing", "lax"
           });	
        addAnnotation
          (scxmlParamTypeEClass, 
           source, 
           new String[] {
             "name", "scxml.param.type",
             "kind", "elementOnly"
           });	
        addAnnotation
          (getScxmlParamType_ScxmlExtraContent(), 
           source, 
           new String[] {
             "kind", "group",
             "name", "ScxmlExtraContent:0"
           });	
        addAnnotation
          (getScxmlParamType_Any(), 
           source, 
           new String[] {
             "kind", "elementWildcard",
             "wildcards", "##other",
             "name", ":1",
             "processing", "lax",
             "group", "#ScxmlExtraContent:0"
           });	
        addAnnotation
          (getScxmlParamType_Expr(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "expr"
           });	
        addAnnotation
          (getScxmlParamType_Location(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "location"
           });	
        addAnnotation
          (getScxmlParamType_Name(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "name"
           });	
        addAnnotation
          (getScxmlParamType_AnyAttribute(), 
           source, 
           new String[] {
             "kind", "attributeWildcard",
             "wildcards", "##other",
             "name", ":5",
             "processing", "lax"
           });	
        addAnnotation
          (scxmlRaiseTypeEClass, 
           source, 
           new String[] {
             "name", "scxml.raise.type",
             "kind", "elementOnly"
           });	
        addAnnotation
          (getScxmlRaiseType_Event(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "event"
           });	
        addAnnotation
          (getScxmlRaiseType_AnyAttribute(), 
           source, 
           new String[] {
             "kind", "attributeWildcard",
             "wildcards", "##other",
             "name", ":1",
             "processing", "lax"
           });	
        addAnnotation
          (scxmlScriptTypeEClass, 
           source, 
           new String[] {
             "name", "scxml.script.type",
             "kind", "mixed"
           });	
        addAnnotation
          (getScxmlScriptType_Mixed(), 
           source, 
           new String[] {
             "kind", "elementWildcard",
             "name", ":mixed"
           });	
        addAnnotation
          (getScxmlScriptType_ScxmlExtraContent(), 
           source, 
           new String[] {
             "kind", "group",
             "name", "ScxmlExtraContent:1"
           });	
        addAnnotation
          (getScxmlScriptType_Any(), 
           source, 
           new String[] {
             "kind", "elementWildcard",
             "wildcards", "##other",
             "name", ":2",
             "processing", "lax",
             "group", "#ScxmlExtraContent:1"
           });	
        addAnnotation
          (getScxmlScriptType_Src(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "src"
           });	
        addAnnotation
          (getScxmlScriptType_AnyAttribute(), 
           source, 
           new String[] {
             "kind", "attributeWildcard",
             "wildcards", "##other",
             "name", ":4",
             "processing", "lax"
           });	
        addAnnotation
          (scxmlScxmlTypeEClass, 
           source, 
           new String[] {
             "name", "scxml.scxml.type",
             "kind", "elementOnly"
           });	
        addAnnotation
          (getScxmlScxmlType_ScxmlScxmlMix(), 
           source, 
           new String[] {
             "kind", "group",
             "name", "ScxmlScxmlMix:0"
           });	
        addAnnotation
          (getScxmlScxmlType_State(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "state",
             "namespace", "##targetNamespace",
             "group", "#ScxmlScxmlMix:0"
           });	
        addAnnotation
          (getScxmlScxmlType_Parallel(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "parallel",
             "namespace", "##targetNamespace",
             "group", "#ScxmlScxmlMix:0"
           });	
        addAnnotation
          (getScxmlScxmlType_Final(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "final",
             "namespace", "##targetNamespace",
             "group", "#ScxmlScxmlMix:0"
           });	
        addAnnotation
          (getScxmlScxmlType_Datamodel(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "datamodel",
             "namespace", "##targetNamespace",
             "group", "#ScxmlScxmlMix:0"
           });	
        addAnnotation
          (getScxmlScxmlType_Script(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "script",
             "namespace", "##targetNamespace",
             "group", "#ScxmlScxmlMix:0"
           });	
        addAnnotation
          (getScxmlScxmlType_Any(), 
           source, 
           new String[] {
             "kind", "elementWildcard",
             "wildcards", "##other",
             "name", ":6",
             "processing", "lax",
             "group", "#ScxmlScxmlMix:0"
           });	
        addAnnotation
          (getScxmlScxmlType_Binding(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "binding"
           });	
        addAnnotation
          (getScxmlScxmlType_Datamodel1(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "datamodel"
           });	
        addAnnotation
          (getScxmlScxmlType_Exmode(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "exmode"
           });	
        addAnnotation
          (getScxmlScxmlType_Initial(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "initial"
           });	
        addAnnotation
          (getScxmlScxmlType_Name(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "name"
           });	
        addAnnotation
          (getScxmlScxmlType_Version(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "version"
           });	
        addAnnotation
          (getScxmlScxmlType_AnyAttribute(), 
           source, 
           new String[] {
             "kind", "attributeWildcard",
             "wildcards", "##other",
             "name", ":13",
             "processing", "lax"
           });	
        addAnnotation
          (scxmlSendTypeEClass, 
           source, 
           new String[] {
             "name", "scxml.send.type",
             "kind", "elementOnly"
           });	
        addAnnotation
          (getScxmlSendType_ScxmlSendMix(), 
           source, 
           new String[] {
             "kind", "group",
             "name", "ScxmlSendMix:0"
           });	
        addAnnotation
          (getScxmlSendType_Content(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "content",
             "namespace", "##targetNamespace",
             "group", "#ScxmlSendMix:0"
           });	
        addAnnotation
          (getScxmlSendType_Param(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "param",
             "namespace", "##targetNamespace",
             "group", "#ScxmlSendMix:0"
           });	
        addAnnotation
          (getScxmlSendType_Any(), 
           source, 
           new String[] {
             "kind", "elementWildcard",
             "wildcards", "##other",
             "name", ":3",
             "processing", "lax",
             "group", "#ScxmlSendMix:0"
           });	
        addAnnotation
          (getScxmlSendType_Delay(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "delay"
           });	
        addAnnotation
          (getScxmlSendType_Delayexpr(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "delayexpr"
           });	
        addAnnotation
          (getScxmlSendType_Event(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "event"
           });	
        addAnnotation
          (getScxmlSendType_Eventexpr(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "eventexpr"
           });	
        addAnnotation
          (getScxmlSendType_Id(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "id"
           });	
        addAnnotation
          (getScxmlSendType_Idlocation(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "idlocation"
           });	
        addAnnotation
          (getScxmlSendType_Namelist(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "namelist"
           });	
        addAnnotation
          (getScxmlSendType_Target(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "target"
           });	
        addAnnotation
          (getScxmlSendType_Targetexpr(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "targetexpr"
           });	
        addAnnotation
          (getScxmlSendType_Type(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "type"
           });	
        addAnnotation
          (getScxmlSendType_Typeexpr(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "typeexpr"
           });	
        addAnnotation
          (getScxmlSendType_AnyAttribute(), 
           source, 
           new String[] {
             "kind", "attributeWildcard",
             "wildcards", "##other",
             "name", ":15",
             "processing", "lax"
           });	
        addAnnotation
          (scxmlStateTypeEClass, 
           source, 
           new String[] {
             "name", "scxml.state.type",
             "kind", "elementOnly"
           });	
        addAnnotation
          (getScxmlStateType_ScxmlStateMix(), 
           source, 
           new String[] {
             "kind", "group",
             "name", "ScxmlStateMix:0"
           });	
        addAnnotation
          (getScxmlStateType_Onentry(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "onentry",
             "namespace", "##targetNamespace",
             "group", "#ScxmlStateMix:0"
           });	
        addAnnotation
          (getScxmlStateType_Onexit(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "onexit",
             "namespace", "##targetNamespace",
             "group", "#ScxmlStateMix:0"
           });	
        addAnnotation
          (getScxmlStateType_Transition(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "transition",
             "namespace", "##targetNamespace",
             "group", "#ScxmlStateMix:0"
           });	
        addAnnotation
          (getScxmlStateType_Initial(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "initial",
             "namespace", "##targetNamespace",
             "group", "#ScxmlStateMix:0"
           });	
        addAnnotation
          (getScxmlStateType_State(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "state",
             "namespace", "##targetNamespace",
             "group", "#ScxmlStateMix:0"
           });	
        addAnnotation
          (getScxmlStateType_Parallel(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "parallel",
             "namespace", "##targetNamespace",
             "group", "#ScxmlStateMix:0"
           });	
        addAnnotation
          (getScxmlStateType_Final(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "final",
             "namespace", "##targetNamespace",
             "group", "#ScxmlStateMix:0"
           });	
        addAnnotation
          (getScxmlStateType_History(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "history",
             "namespace", "##targetNamespace",
             "group", "#ScxmlStateMix:0"
           });	
        addAnnotation
          (getScxmlStateType_Datamodel(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "datamodel",
             "namespace", "##targetNamespace",
             "group", "#ScxmlStateMix:0"
           });	
        addAnnotation
          (getScxmlStateType_Invoke(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "invoke",
             "namespace", "##targetNamespace",
             "group", "#ScxmlStateMix:0"
           });	
        addAnnotation
          (getScxmlStateType_Any(), 
           source, 
           new String[] {
             "kind", "elementWildcard",
             "wildcards", "##other",
             "name", ":11",
             "processing", "lax",
             "group", "#ScxmlStateMix:0"
           });	
        addAnnotation
          (getScxmlStateType_Id(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "id"
           });	
        addAnnotation
          (getScxmlStateType_Initial1(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "initial"
           });	
        addAnnotation
          (getScxmlStateType_AnyAttribute(), 
           source, 
           new String[] {
             "kind", "attributeWildcard",
             "wildcards", "##other",
             "name", ":14",
             "processing", "lax"
           });	
        addAnnotation
          (scxmlTransitionTypeEClass, 
           source, 
           new String[] {
             "name", "scxml.transition.type",
             "kind", "elementOnly"
           });	
        addAnnotation
          (getScxmlTransitionType_ScxmlCoreExecutablecontent(), 
           source, 
           new String[] {
             "kind", "group",
             "name", "ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlTransitionType_Any(), 
           source, 
           new String[] {
             "kind", "elementWildcard",
             "wildcards", "##other",
             "name", ":1",
             "processing", "lax",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlTransitionType_Raise(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "raise",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlTransitionType_If(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "if",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlTransitionType_Foreach(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "foreach",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlTransitionType_Send(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "send",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlTransitionType_Script(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "script",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlTransitionType_Assign(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "assign",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlTransitionType_Log(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "log",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlTransitionType_Cancel(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "cancel",
             "namespace", "##targetNamespace",
             "group", "#ScxmlCoreExecutablecontent:0"
           });	
        addAnnotation
          (getScxmlTransitionType_Cond(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "cond"
           });	
        addAnnotation
          (getScxmlTransitionType_Event(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "event"
           });	
        addAnnotation
          (getScxmlTransitionType_Target(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "target"
           });	
        addAnnotation
          (getScxmlTransitionType_Type(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "type"
           });	
        addAnnotation
          (getScxmlTransitionType_AnyAttribute(), 
           source, 
           new String[] {
             "kind", "attributeWildcard",
             "wildcards", "##other",
             "name", ":14",
             "processing", "lax"
           });	
        addAnnotation
          (transitionTypeDatatypeEEnum, 
           source, 
           new String[] {
             "name", "TransitionType.datatype"
           });	
        addAnnotation
          (transitionTypeDatatypeObjectEDataType, 
           source, 
           new String[] {
             "name", "TransitionType.datatype:Object",
             "baseType", "TransitionType.datatype"
           });	
        addAnnotation
          (uriDatatypeEDataType, 
           source, 
           new String[] {
             "name", "URI.datatype",
             "baseType", "http://www.eclipse.org/emf/2003/XMLType#anyURI"
           });	
        addAnnotation
          (valueLangDatatypeEDataType, 
           source, 
           new String[] {
             "name", "ValueLang.datatype",
             "baseType", "http://www.eclipse.org/emf/2003/XMLType#string"
           });
    }

} //ScxmlPackageImpl
