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
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 *
 * @generated
 */
public class ScxmlPackageImpl extends EPackageImpl implements ScxmlPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass documentRootEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass scxmlAssignTypeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass scxmlCancelTypeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass scxmlContentTypeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass scxmlDatamodelTypeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass scxmlDataTypeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass scxmlDonedataTypeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass scxmlElseifTypeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass scxmlElseTypeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass scxmlFinalizeTypeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass scxmlFinalTypeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass scxmlForeachTypeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass scxmlHistoryTypeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass scxmlIfTypeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass scxmlInitialTypeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass scxmlInvokeTypeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass scxmlLogTypeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass scxmlOnentryTypeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass scxmlOnexitTypeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass scxmlParallelTypeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass scxmlParamTypeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass scxmlRaiseTypeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass scxmlScriptTypeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass scxmlScxmlTypeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass scxmlSendTypeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass scxmlStateTypeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass scxmlTransitionTypeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EEnum assignTypeDatatypeEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EEnum bindingDatatypeEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EEnum booleanDatatypeEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EEnum exmodeDatatypeEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EEnum historyTypeDatatypeEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EEnum transitionTypeDatatypeEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EDataType assignTypeDatatypeObjectEDataType = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EDataType bindingDatatypeObjectEDataType = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EDataType booleanDatatypeObjectEDataType = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EDataType condLangDatatypeEDataType = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EDataType durationDatatypeEDataType = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EDataType eventTypeDatatypeEDataType = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EDataType eventTypesDatatypeEDataType = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EDataType exmodeDatatypeObjectEDataType = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EDataType historyTypeDatatypeObjectEDataType = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EDataType integerDatatypeEDataType = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EDataType locLangDatatypeEDataType = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EDataType transitionTypeDatatypeObjectEDataType = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EDataType uriDatatypeEDataType = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EDataType valueLangDatatypeEDataType = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the
     * package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory
     * method {@link #init init()}, which also performs initialization of the
     * package, or returns the registered package, if one already exists. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private ScxmlPackageImpl() {
        super(ScxmlPackage.eNS_URI, ScxmlFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model,
     * and for any others upon which it depends.
     *
     * <p>
     * This method is used to initialize {@link ScxmlPackage#eINSTANCE} when
     * that field is accessed. Clients should not invoke it directly. Instead,
     * they should simply access that field to obtain the package. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static ScxmlPackage init() {
        if (ScxmlPackageImpl.isInited) {
            return (ScxmlPackage) EPackage.Registry.INSTANCE.getEPackage(ScxmlPackage.eNS_URI);
        }

        // Obtain or create and register package
        ScxmlPackageImpl theScxmlPackage = (ScxmlPackageImpl) (EPackage.Registry.INSTANCE.get(ScxmlPackage.eNS_URI) instanceof ScxmlPackageImpl ? EPackage.Registry.INSTANCE.get(ScxmlPackage.eNS_URI)
                : new ScxmlPackageImpl());

        ScxmlPackageImpl.isInited = true;

        // Initialize simple dependencies
        XMLNamespacePackage.eINSTANCE.eClass();
        XMLTypePackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theScxmlPackage.createPackageContents();

        // Initialize created meta-data
        theScxmlPackage.initializePackageContents();

        // Register package validator
        EValidator.Registry.INSTANCE.put(theScxmlPackage, new EValidator.Descriptor() {
            @Override
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
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDocumentRoot() {
        return documentRootEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDocumentRoot_Mixed() {
        return (EAttribute) documentRootEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDocumentRoot_XMLNSPrefixMap() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDocumentRoot_XSISchemaLocation() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDocumentRoot_Assign() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDocumentRoot_Cancel() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDocumentRoot_Content() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDocumentRoot_Data() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDocumentRoot_Datamodel() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDocumentRoot_Donedata() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDocumentRoot_Else() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDocumentRoot_Elseif() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDocumentRoot_Final() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDocumentRoot_Finalize() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDocumentRoot_Foreach() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDocumentRoot_History() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(14);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDocumentRoot_If() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(15);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDocumentRoot_Initial() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(16);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDocumentRoot_Invoke() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(17);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDocumentRoot_Log() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(18);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDocumentRoot_Onentry() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(19);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDocumentRoot_Onexit() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(20);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDocumentRoot_Parallel() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(21);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDocumentRoot_Param() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(22);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDocumentRoot_Raise() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(23);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDocumentRoot_Script() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(24);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDocumentRoot_Scxml() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(25);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDocumentRoot_Send() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(26);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDocumentRoot_State() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(27);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDocumentRoot_Transition() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(28);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getScxmlAssignType() {
        return scxmlAssignTypeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlAssignType_Mixed() {
        return (EAttribute) scxmlAssignTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlAssignType_Any() {
        return (EAttribute) scxmlAssignTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlAssignType_Attr() {
        return (EAttribute) scxmlAssignTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlAssignType_Expr() {
        return (EAttribute) scxmlAssignTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlAssignType_Location() {
        return (EAttribute) scxmlAssignTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlAssignType_Type() {
        return (EAttribute) scxmlAssignTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlAssignType_AnyAttribute() {
        return (EAttribute) scxmlAssignTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getScxmlCancelType() {
        return scxmlCancelTypeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlCancelType_ScxmlExtraContent() {
        return (EAttribute) scxmlCancelTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlCancelType_Any() {
        return (EAttribute) scxmlCancelTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlCancelType_Sendid() {
        return (EAttribute) scxmlCancelTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlCancelType_Sendidexpr() {
        return (EAttribute) scxmlCancelTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlCancelType_AnyAttribute() {
        return (EAttribute) scxmlCancelTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getScxmlContentType() {
        return scxmlContentTypeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlContentType_Mixed() {
        return (EAttribute) scxmlContentTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlContentType_Any() {
        return (EAttribute) scxmlContentTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlContentType_Expr() {
        return (EAttribute) scxmlContentTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlContentType_AnyAttribute() {
        return (EAttribute) scxmlContentTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getScxmlDatamodelType() {
        return scxmlDatamodelTypeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlDatamodelType_Data() {
        return (EReference) scxmlDatamodelTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlDatamodelType_ScxmlExtraContent() {
        return (EAttribute) scxmlDatamodelTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlDatamodelType_Any() {
        return (EAttribute) scxmlDatamodelTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlDatamodelType_AnyAttribute() {
        return (EAttribute) scxmlDatamodelTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getScxmlDataType() {
        return scxmlDataTypeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlDataType_Mixed() {
        return (EAttribute) scxmlDataTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlDataType_Any() {
        return (EAttribute) scxmlDataTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlDataType_Expr() {
        return (EAttribute) scxmlDataTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlDataType_Id() {
        return (EAttribute) scxmlDataTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlDataType_Src() {
        return (EAttribute) scxmlDataTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlDataType_AnyAttribute() {
        return (EAttribute) scxmlDataTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getScxmlDonedataType() {
        return scxmlDonedataTypeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlDonedataType_Content() {
        return (EReference) scxmlDonedataTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlDonedataType_Param() {
        return (EReference) scxmlDonedataTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlDonedataType_AnyAttribute() {
        return (EAttribute) scxmlDonedataTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getScxmlElseifType() {
        return scxmlElseifTypeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlElseifType_Cond() {
        return (EAttribute) scxmlElseifTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlElseifType_AnyAttribute() {
        return (EAttribute) scxmlElseifTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getScxmlElseType() {
        return scxmlElseTypeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlElseType_AnyAttribute() {
        return (EAttribute) scxmlElseTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getScxmlFinalizeType() {
        return scxmlFinalizeTypeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlFinalizeType_ScxmlCoreExecutablecontent() {
        return (EAttribute) scxmlFinalizeTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlFinalizeType_Any() {
        return (EAttribute) scxmlFinalizeTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlFinalizeType_Raise() {
        return (EReference) scxmlFinalizeTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlFinalizeType_If() {
        return (EReference) scxmlFinalizeTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlFinalizeType_Foreach() {
        return (EReference) scxmlFinalizeTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlFinalizeType_Send() {
        return (EReference) scxmlFinalizeTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlFinalizeType_Script() {
        return (EReference) scxmlFinalizeTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlFinalizeType_Assign() {
        return (EReference) scxmlFinalizeTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlFinalizeType_Log() {
        return (EReference) scxmlFinalizeTypeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlFinalizeType_Cancel() {
        return (EReference) scxmlFinalizeTypeEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlFinalizeType_AnyAttribute() {
        return (EAttribute) scxmlFinalizeTypeEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getScxmlFinalType() {
        return scxmlFinalTypeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlFinalType_ScxmlFinalMix() {
        return (EAttribute) scxmlFinalTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlFinalType_Onentry() {
        return (EReference) scxmlFinalTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlFinalType_Onexit() {
        return (EReference) scxmlFinalTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlFinalType_Donedata() {
        return (EReference) scxmlFinalTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlFinalType_Any() {
        return (EAttribute) scxmlFinalTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlFinalType_Id() {
        return (EAttribute) scxmlFinalTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlFinalType_AnyAttribute() {
        return (EAttribute) scxmlFinalTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getScxmlForeachType() {
        return scxmlForeachTypeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlForeachType_ScxmlCoreExecutablecontent() {
        return (EAttribute) scxmlForeachTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlForeachType_Any() {
        return (EAttribute) scxmlForeachTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlForeachType_Raise() {
        return (EReference) scxmlForeachTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlForeachType_If() {
        return (EReference) scxmlForeachTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlForeachType_Foreach() {
        return (EReference) scxmlForeachTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlForeachType_Send() {
        return (EReference) scxmlForeachTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlForeachType_Script() {
        return (EReference) scxmlForeachTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlForeachType_Assign() {
        return (EReference) scxmlForeachTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlForeachType_Log() {
        return (EReference) scxmlForeachTypeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlForeachType_Cancel() {
        return (EReference) scxmlForeachTypeEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlForeachType_Array() {
        return (EAttribute) scxmlForeachTypeEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlForeachType_Index() {
        return (EAttribute) scxmlForeachTypeEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlForeachType_Item() {
        return (EAttribute) scxmlForeachTypeEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlForeachType_AnyAttribute() {
        return (EAttribute) scxmlForeachTypeEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getScxmlHistoryType() {
        return scxmlHistoryTypeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlHistoryType_ScxmlExtraContent() {
        return (EAttribute) scxmlHistoryTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlHistoryType_Any() {
        return (EAttribute) scxmlHistoryTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlHistoryType_Transition() {
        return (EReference) scxmlHistoryTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlHistoryType_ScxmlExtraContent1() {
        return (EAttribute) scxmlHistoryTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlHistoryType_Any1() {
        return (EAttribute) scxmlHistoryTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlHistoryType_Id() {
        return (EAttribute) scxmlHistoryTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlHistoryType_Type() {
        return (EAttribute) scxmlHistoryTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlHistoryType_AnyAttribute() {
        return (EAttribute) scxmlHistoryTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getScxmlIfType() {
        return scxmlIfTypeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlIfType_ScxmlCoreExecutablecontent() {
        return (EAttribute) scxmlIfTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlIfType_Any() {
        return (EAttribute) scxmlIfTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlIfType_Raise() {
        return (EReference) scxmlIfTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlIfType_If() {
        return (EReference) scxmlIfTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlIfType_Foreach() {
        return (EReference) scxmlIfTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlIfType_Send() {
        return (EReference) scxmlIfTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlIfType_Script() {
        return (EReference) scxmlIfTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlIfType_Assign() {
        return (EReference) scxmlIfTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlIfType_Log() {
        return (EReference) scxmlIfTypeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlIfType_Cancel() {
        return (EReference) scxmlIfTypeEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlIfType_Elseif() {
        return (EReference) scxmlIfTypeEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlIfType_ScxmlCoreExecutablecontent1() {
        return (EAttribute) scxmlIfTypeEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlIfType_Any1() {
        return (EAttribute) scxmlIfTypeEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlIfType_Raise1() {
        return (EReference) scxmlIfTypeEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlIfType_If1() {
        return (EReference) scxmlIfTypeEClass.getEStructuralFeatures().get(14);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlIfType_Foreach1() {
        return (EReference) scxmlIfTypeEClass.getEStructuralFeatures().get(15);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlIfType_Send1() {
        return (EReference) scxmlIfTypeEClass.getEStructuralFeatures().get(16);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlIfType_Script1() {
        return (EReference) scxmlIfTypeEClass.getEStructuralFeatures().get(17);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlIfType_Assign1() {
        return (EReference) scxmlIfTypeEClass.getEStructuralFeatures().get(18);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlIfType_Log1() {
        return (EReference) scxmlIfTypeEClass.getEStructuralFeatures().get(19);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlIfType_Cancel1() {
        return (EReference) scxmlIfTypeEClass.getEStructuralFeatures().get(20);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlIfType_Else() {
        return (EReference) scxmlIfTypeEClass.getEStructuralFeatures().get(21);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlIfType_ScxmlCoreExecutablecontent2() {
        return (EAttribute) scxmlIfTypeEClass.getEStructuralFeatures().get(22);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlIfType_Any2() {
        return (EAttribute) scxmlIfTypeEClass.getEStructuralFeatures().get(23);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlIfType_Raise2() {
        return (EReference) scxmlIfTypeEClass.getEStructuralFeatures().get(24);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlIfType_If2() {
        return (EReference) scxmlIfTypeEClass.getEStructuralFeatures().get(25);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlIfType_Foreach2() {
        return (EReference) scxmlIfTypeEClass.getEStructuralFeatures().get(26);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlIfType_Send2() {
        return (EReference) scxmlIfTypeEClass.getEStructuralFeatures().get(27);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlIfType_Script2() {
        return (EReference) scxmlIfTypeEClass.getEStructuralFeatures().get(28);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlIfType_Assign2() {
        return (EReference) scxmlIfTypeEClass.getEStructuralFeatures().get(29);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlIfType_Log2() {
        return (EReference) scxmlIfTypeEClass.getEStructuralFeatures().get(30);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlIfType_Cancel2() {
        return (EReference) scxmlIfTypeEClass.getEStructuralFeatures().get(31);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlIfType_Cond() {
        return (EAttribute) scxmlIfTypeEClass.getEStructuralFeatures().get(32);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlIfType_AnyAttribute() {
        return (EAttribute) scxmlIfTypeEClass.getEStructuralFeatures().get(33);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getScxmlInitialType() {
        return scxmlInitialTypeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlInitialType_ScxmlExtraContent() {
        return (EAttribute) scxmlInitialTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlInitialType_Any() {
        return (EAttribute) scxmlInitialTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlInitialType_Transition() {
        return (EReference) scxmlInitialTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlInitialType_ScxmlExtraContent1() {
        return (EAttribute) scxmlInitialTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlInitialType_Any1() {
        return (EAttribute) scxmlInitialTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlInitialType_AnyAttribute() {
        return (EAttribute) scxmlInitialTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getScxmlInvokeType() {
        return scxmlInvokeTypeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlInvokeType_ScxmlInvokeMix() {
        return (EAttribute) scxmlInvokeTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlInvokeType_Content() {
        return (EReference) scxmlInvokeTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlInvokeType_Param() {
        return (EReference) scxmlInvokeTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlInvokeType_Finalize() {
        return (EReference) scxmlInvokeTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlInvokeType_Any() {
        return (EAttribute) scxmlInvokeTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlInvokeType_Autoforward() {
        return (EAttribute) scxmlInvokeTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlInvokeType_Id() {
        return (EAttribute) scxmlInvokeTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlInvokeType_Idlocation() {
        return (EAttribute) scxmlInvokeTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlInvokeType_Namelist() {
        return (EAttribute) scxmlInvokeTypeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlInvokeType_Src() {
        return (EAttribute) scxmlInvokeTypeEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlInvokeType_Srcexpr() {
        return (EAttribute) scxmlInvokeTypeEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlInvokeType_Type() {
        return (EAttribute) scxmlInvokeTypeEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlInvokeType_Typeexpr() {
        return (EAttribute) scxmlInvokeTypeEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlInvokeType_AnyAttribute() {
        return (EAttribute) scxmlInvokeTypeEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getScxmlLogType() {
        return scxmlLogTypeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlLogType_ScxmlExtraContent() {
        return (EAttribute) scxmlLogTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlLogType_Any() {
        return (EAttribute) scxmlLogTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlLogType_Expr() {
        return (EAttribute) scxmlLogTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlLogType_Label() {
        return (EAttribute) scxmlLogTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlLogType_AnyAttribute() {
        return (EAttribute) scxmlLogTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getScxmlOnentryType() {
        return scxmlOnentryTypeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlOnentryType_ScxmlCoreExecutablecontent() {
        return (EAttribute) scxmlOnentryTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlOnentryType_Any() {
        return (EAttribute) scxmlOnentryTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlOnentryType_Raise() {
        return (EReference) scxmlOnentryTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlOnentryType_If() {
        return (EReference) scxmlOnentryTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlOnentryType_Foreach() {
        return (EReference) scxmlOnentryTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlOnentryType_Send() {
        return (EReference) scxmlOnentryTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlOnentryType_Script() {
        return (EReference) scxmlOnentryTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlOnentryType_Assign() {
        return (EReference) scxmlOnentryTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlOnentryType_Log() {
        return (EReference) scxmlOnentryTypeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlOnentryType_Cancel() {
        return (EReference) scxmlOnentryTypeEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlOnentryType_AnyAttribute() {
        return (EAttribute) scxmlOnentryTypeEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getScxmlOnexitType() {
        return scxmlOnexitTypeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlOnexitType_ScxmlCoreExecutablecontent() {
        return (EAttribute) scxmlOnexitTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlOnexitType_Any() {
        return (EAttribute) scxmlOnexitTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlOnexitType_Raise() {
        return (EReference) scxmlOnexitTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlOnexitType_If() {
        return (EReference) scxmlOnexitTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlOnexitType_Foreach() {
        return (EReference) scxmlOnexitTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlOnexitType_Send() {
        return (EReference) scxmlOnexitTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlOnexitType_Script() {
        return (EReference) scxmlOnexitTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlOnexitType_Assign() {
        return (EReference) scxmlOnexitTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlOnexitType_Log() {
        return (EReference) scxmlOnexitTypeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlOnexitType_Cancel() {
        return (EReference) scxmlOnexitTypeEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlOnexitType_AnyAttribute() {
        return (EAttribute) scxmlOnexitTypeEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getScxmlParallelType() {
        return scxmlParallelTypeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlParallelType_ScxmlParallelMix() {
        return (EAttribute) scxmlParallelTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlParallelType_Onentry() {
        return (EReference) scxmlParallelTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlParallelType_Onexit() {
        return (EReference) scxmlParallelTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlParallelType_Transition() {
        return (EReference) scxmlParallelTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlParallelType_State() {
        return (EReference) scxmlParallelTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlParallelType_Parallel() {
        return (EReference) scxmlParallelTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlParallelType_History() {
        return (EReference) scxmlParallelTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlParallelType_Datamodel() {
        return (EReference) scxmlParallelTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlParallelType_Invoke() {
        return (EReference) scxmlParallelTypeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlParallelType_Any() {
        return (EAttribute) scxmlParallelTypeEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlParallelType_Id() {
        return (EAttribute) scxmlParallelTypeEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlParallelType_AnyAttribute() {
        return (EAttribute) scxmlParallelTypeEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getScxmlParamType() {
        return scxmlParamTypeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlParamType_ScxmlExtraContent() {
        return (EAttribute) scxmlParamTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlParamType_Any() {
        return (EAttribute) scxmlParamTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlParamType_Expr() {
        return (EAttribute) scxmlParamTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlParamType_Location() {
        return (EAttribute) scxmlParamTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlParamType_Name() {
        return (EAttribute) scxmlParamTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlParamType_AnyAttribute() {
        return (EAttribute) scxmlParamTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getScxmlRaiseType() {
        return scxmlRaiseTypeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlRaiseType_Event() {
        return (EAttribute) scxmlRaiseTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlRaiseType_AnyAttribute() {
        return (EAttribute) scxmlRaiseTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getScxmlScriptType() {
        return scxmlScriptTypeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlScriptType_Mixed() {
        return (EAttribute) scxmlScriptTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlScriptType_ScxmlExtraContent() {
        return (EAttribute) scxmlScriptTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlScriptType_Any() {
        return (EAttribute) scxmlScriptTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlScriptType_Src() {
        return (EAttribute) scxmlScriptTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlScriptType_AnyAttribute() {
        return (EAttribute) scxmlScriptTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getScxmlScxmlType() {
        return scxmlScxmlTypeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlScxmlType_ScxmlScxmlMix() {
        return (EAttribute) scxmlScxmlTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlScxmlType_State() {
        return (EReference) scxmlScxmlTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlScxmlType_Parallel() {
        return (EReference) scxmlScxmlTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlScxmlType_Final() {
        return (EReference) scxmlScxmlTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlScxmlType_Datamodel() {
        return (EReference) scxmlScxmlTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlScxmlType_Script() {
        return (EReference) scxmlScxmlTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlScxmlType_Any() {
        return (EAttribute) scxmlScxmlTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlScxmlType_Binding() {
        return (EAttribute) scxmlScxmlTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlScxmlType_Datamodel1() {
        return (EAttribute) scxmlScxmlTypeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlScxmlType_Exmode() {
        return (EAttribute) scxmlScxmlTypeEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlScxmlType_Initial() {
        return (EAttribute) scxmlScxmlTypeEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlScxmlType_Name() {
        return (EAttribute) scxmlScxmlTypeEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlScxmlType_Version() {
        return (EAttribute) scxmlScxmlTypeEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlScxmlType_AnyAttribute() {
        return (EAttribute) scxmlScxmlTypeEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getScxmlSendType() {
        return scxmlSendTypeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlSendType_ScxmlSendMix() {
        return (EAttribute) scxmlSendTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlSendType_Content() {
        return (EReference) scxmlSendTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlSendType_Param() {
        return (EReference) scxmlSendTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlSendType_Any() {
        return (EAttribute) scxmlSendTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlSendType_Delay() {
        return (EAttribute) scxmlSendTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlSendType_Delayexpr() {
        return (EAttribute) scxmlSendTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlSendType_Event() {
        return (EAttribute) scxmlSendTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlSendType_Eventexpr() {
        return (EAttribute) scxmlSendTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlSendType_Id() {
        return (EAttribute) scxmlSendTypeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlSendType_Idlocation() {
        return (EAttribute) scxmlSendTypeEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlSendType_Namelist() {
        return (EAttribute) scxmlSendTypeEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlSendType_Target() {
        return (EAttribute) scxmlSendTypeEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlSendType_Targetexpr() {
        return (EAttribute) scxmlSendTypeEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlSendType_Type() {
        return (EAttribute) scxmlSendTypeEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlSendType_Typeexpr() {
        return (EAttribute) scxmlSendTypeEClass.getEStructuralFeatures().get(14);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlSendType_AnyAttribute() {
        return (EAttribute) scxmlSendTypeEClass.getEStructuralFeatures().get(15);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getScxmlStateType() {
        return scxmlStateTypeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlStateType_ScxmlStateMix() {
        return (EAttribute) scxmlStateTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlStateType_Onentry() {
        return (EReference) scxmlStateTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlStateType_Onexit() {
        return (EReference) scxmlStateTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlStateType_Transition() {
        return (EReference) scxmlStateTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlStateType_Initial() {
        return (EReference) scxmlStateTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlStateType_State() {
        return (EReference) scxmlStateTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlStateType_Parallel() {
        return (EReference) scxmlStateTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlStateType_Final() {
        return (EReference) scxmlStateTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlStateType_History() {
        return (EReference) scxmlStateTypeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlStateType_Datamodel() {
        return (EReference) scxmlStateTypeEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlStateType_Invoke() {
        return (EReference) scxmlStateTypeEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlStateType_Any() {
        return (EAttribute) scxmlStateTypeEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlStateType_Id() {
        return (EAttribute) scxmlStateTypeEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlStateType_Initial1() {
        return (EAttribute) scxmlStateTypeEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlStateType_AnyAttribute() {
        return (EAttribute) scxmlStateTypeEClass.getEStructuralFeatures().get(14);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getScxmlTransitionType() {
        return scxmlTransitionTypeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlTransitionType_ScxmlCoreExecutablecontent() {
        return (EAttribute) scxmlTransitionTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlTransitionType_Any() {
        return (EAttribute) scxmlTransitionTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlTransitionType_Raise() {
        return (EReference) scxmlTransitionTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlTransitionType_If() {
        return (EReference) scxmlTransitionTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlTransitionType_Foreach() {
        return (EReference) scxmlTransitionTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlTransitionType_Send() {
        return (EReference) scxmlTransitionTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlTransitionType_Script() {
        return (EReference) scxmlTransitionTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlTransitionType_Assign() {
        return (EReference) scxmlTransitionTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlTransitionType_Log() {
        return (EReference) scxmlTransitionTypeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getScxmlTransitionType_Cancel() {
        return (EReference) scxmlTransitionTypeEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlTransitionType_Cond() {
        return (EAttribute) scxmlTransitionTypeEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlTransitionType_Event() {
        return (EAttribute) scxmlTransitionTypeEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlTransitionType_Target() {
        return (EAttribute) scxmlTransitionTypeEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlTransitionType_Type() {
        return (EAttribute) scxmlTransitionTypeEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getScxmlTransitionType_AnyAttribute() {
        return (EAttribute) scxmlTransitionTypeEClass.getEStructuralFeatures().get(14);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EEnum getAssignTypeDatatype() {
        return assignTypeDatatypeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EEnum getBindingDatatype() {
        return bindingDatatypeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EEnum getBooleanDatatype() {
        return booleanDatatypeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EEnum getExmodeDatatype() {
        return exmodeDatatypeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EEnum getHistoryTypeDatatype() {
        return historyTypeDatatypeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EEnum getTransitionTypeDatatype() {
        return transitionTypeDatatypeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EDataType getAssignTypeDatatypeObject() {
        return assignTypeDatatypeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EDataType getBindingDatatypeObject() {
        return bindingDatatypeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EDataType getBooleanDatatypeObject() {
        return booleanDatatypeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EDataType getCondLangDatatype() {
        return condLangDatatypeEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EDataType getDurationDatatype() {
        return durationDatatypeEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EDataType getEventTypeDatatype() {
        return eventTypeDatatypeEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EDataType getEventTypesDatatype() {
        return eventTypesDatatypeEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EDataType getExmodeDatatypeObject() {
        return exmodeDatatypeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EDataType getHistoryTypeDatatypeObject() {
        return historyTypeDatatypeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EDataType getIntegerDatatype() {
        return integerDatatypeEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EDataType getLocLangDatatype() {
        return locLangDatatypeEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EDataType getTransitionTypeDatatypeObject() {
        return transitionTypeDatatypeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EDataType getURIDatatype() {
        return uriDatatypeEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EDataType getValueLangDatatype() {
        return valueLangDatatypeEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlFactory getScxmlFactory() {
        return (ScxmlFactory) getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package. This method is guarded to
     * have no affect on any invocation but its first. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    public void createPackageContents() {
        if (isCreated) {
            return;
        }
        isCreated = true;

        // Create classes and their features
        documentRootEClass = createEClass(ScxmlPackage.DOCUMENT_ROOT);
        createEAttribute(documentRootEClass, ScxmlPackage.DOCUMENT_ROOT__MIXED);
        createEReference(documentRootEClass, ScxmlPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
        createEReference(documentRootEClass, ScxmlPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
        createEReference(documentRootEClass, ScxmlPackage.DOCUMENT_ROOT__ASSIGN);
        createEReference(documentRootEClass, ScxmlPackage.DOCUMENT_ROOT__CANCEL);
        createEReference(documentRootEClass, ScxmlPackage.DOCUMENT_ROOT__CONTENT);
        createEReference(documentRootEClass, ScxmlPackage.DOCUMENT_ROOT__DATA);
        createEReference(documentRootEClass, ScxmlPackage.DOCUMENT_ROOT__DATAMODEL);
        createEReference(documentRootEClass, ScxmlPackage.DOCUMENT_ROOT__DONEDATA);
        createEReference(documentRootEClass, ScxmlPackage.DOCUMENT_ROOT__ELSE);
        createEReference(documentRootEClass, ScxmlPackage.DOCUMENT_ROOT__ELSEIF);
        createEReference(documentRootEClass, ScxmlPackage.DOCUMENT_ROOT__FINAL);
        createEReference(documentRootEClass, ScxmlPackage.DOCUMENT_ROOT__FINALIZE);
        createEReference(documentRootEClass, ScxmlPackage.DOCUMENT_ROOT__FOREACH);
        createEReference(documentRootEClass, ScxmlPackage.DOCUMENT_ROOT__HISTORY);
        createEReference(documentRootEClass, ScxmlPackage.DOCUMENT_ROOT__IF);
        createEReference(documentRootEClass, ScxmlPackage.DOCUMENT_ROOT__INITIAL);
        createEReference(documentRootEClass, ScxmlPackage.DOCUMENT_ROOT__INVOKE);
        createEReference(documentRootEClass, ScxmlPackage.DOCUMENT_ROOT__LOG);
        createEReference(documentRootEClass, ScxmlPackage.DOCUMENT_ROOT__ONENTRY);
        createEReference(documentRootEClass, ScxmlPackage.DOCUMENT_ROOT__ONEXIT);
        createEReference(documentRootEClass, ScxmlPackage.DOCUMENT_ROOT__PARALLEL);
        createEReference(documentRootEClass, ScxmlPackage.DOCUMENT_ROOT__PARAM);
        createEReference(documentRootEClass, ScxmlPackage.DOCUMENT_ROOT__RAISE);
        createEReference(documentRootEClass, ScxmlPackage.DOCUMENT_ROOT__SCRIPT);
        createEReference(documentRootEClass, ScxmlPackage.DOCUMENT_ROOT__SCXML);
        createEReference(documentRootEClass, ScxmlPackage.DOCUMENT_ROOT__SEND);
        createEReference(documentRootEClass, ScxmlPackage.DOCUMENT_ROOT__STATE);
        createEReference(documentRootEClass, ScxmlPackage.DOCUMENT_ROOT__TRANSITION);

        scxmlAssignTypeEClass = createEClass(ScxmlPackage.SCXML_ASSIGN_TYPE);
        createEAttribute(scxmlAssignTypeEClass, ScxmlPackage.SCXML_ASSIGN_TYPE__MIXED);
        createEAttribute(scxmlAssignTypeEClass, ScxmlPackage.SCXML_ASSIGN_TYPE__ANY);
        createEAttribute(scxmlAssignTypeEClass, ScxmlPackage.SCXML_ASSIGN_TYPE__ATTR);
        createEAttribute(scxmlAssignTypeEClass, ScxmlPackage.SCXML_ASSIGN_TYPE__EXPR);
        createEAttribute(scxmlAssignTypeEClass, ScxmlPackage.SCXML_ASSIGN_TYPE__LOCATION);
        createEAttribute(scxmlAssignTypeEClass, ScxmlPackage.SCXML_ASSIGN_TYPE__TYPE);
        createEAttribute(scxmlAssignTypeEClass, ScxmlPackage.SCXML_ASSIGN_TYPE__ANY_ATTRIBUTE);

        scxmlCancelTypeEClass = createEClass(ScxmlPackage.SCXML_CANCEL_TYPE);
        createEAttribute(scxmlCancelTypeEClass, ScxmlPackage.SCXML_CANCEL_TYPE__SCXML_EXTRA_CONTENT);
        createEAttribute(scxmlCancelTypeEClass, ScxmlPackage.SCXML_CANCEL_TYPE__ANY);
        createEAttribute(scxmlCancelTypeEClass, ScxmlPackage.SCXML_CANCEL_TYPE__SENDID);
        createEAttribute(scxmlCancelTypeEClass, ScxmlPackage.SCXML_CANCEL_TYPE__SENDIDEXPR);
        createEAttribute(scxmlCancelTypeEClass, ScxmlPackage.SCXML_CANCEL_TYPE__ANY_ATTRIBUTE);

        scxmlContentTypeEClass = createEClass(ScxmlPackage.SCXML_CONTENT_TYPE);
        createEAttribute(scxmlContentTypeEClass, ScxmlPackage.SCXML_CONTENT_TYPE__MIXED);
        createEAttribute(scxmlContentTypeEClass, ScxmlPackage.SCXML_CONTENT_TYPE__ANY);
        createEAttribute(scxmlContentTypeEClass, ScxmlPackage.SCXML_CONTENT_TYPE__EXPR);
        createEAttribute(scxmlContentTypeEClass, ScxmlPackage.SCXML_CONTENT_TYPE__ANY_ATTRIBUTE);

        scxmlDatamodelTypeEClass = createEClass(ScxmlPackage.SCXML_DATAMODEL_TYPE);
        createEReference(scxmlDatamodelTypeEClass, ScxmlPackage.SCXML_DATAMODEL_TYPE__DATA);
        createEAttribute(scxmlDatamodelTypeEClass, ScxmlPackage.SCXML_DATAMODEL_TYPE__SCXML_EXTRA_CONTENT);
        createEAttribute(scxmlDatamodelTypeEClass, ScxmlPackage.SCXML_DATAMODEL_TYPE__ANY);
        createEAttribute(scxmlDatamodelTypeEClass, ScxmlPackage.SCXML_DATAMODEL_TYPE__ANY_ATTRIBUTE);

        scxmlDataTypeEClass = createEClass(ScxmlPackage.SCXML_DATA_TYPE);
        createEAttribute(scxmlDataTypeEClass, ScxmlPackage.SCXML_DATA_TYPE__MIXED);
        createEAttribute(scxmlDataTypeEClass, ScxmlPackage.SCXML_DATA_TYPE__ANY);
        createEAttribute(scxmlDataTypeEClass, ScxmlPackage.SCXML_DATA_TYPE__EXPR);
        createEAttribute(scxmlDataTypeEClass, ScxmlPackage.SCXML_DATA_TYPE__ID);
        createEAttribute(scxmlDataTypeEClass, ScxmlPackage.SCXML_DATA_TYPE__SRC);
        createEAttribute(scxmlDataTypeEClass, ScxmlPackage.SCXML_DATA_TYPE__ANY_ATTRIBUTE);

        scxmlDonedataTypeEClass = createEClass(ScxmlPackage.SCXML_DONEDATA_TYPE);
        createEReference(scxmlDonedataTypeEClass, ScxmlPackage.SCXML_DONEDATA_TYPE__CONTENT);
        createEReference(scxmlDonedataTypeEClass, ScxmlPackage.SCXML_DONEDATA_TYPE__PARAM);
        createEAttribute(scxmlDonedataTypeEClass, ScxmlPackage.SCXML_DONEDATA_TYPE__ANY_ATTRIBUTE);

        scxmlElseifTypeEClass = createEClass(ScxmlPackage.SCXML_ELSEIF_TYPE);
        createEAttribute(scxmlElseifTypeEClass, ScxmlPackage.SCXML_ELSEIF_TYPE__COND);
        createEAttribute(scxmlElseifTypeEClass, ScxmlPackage.SCXML_ELSEIF_TYPE__ANY_ATTRIBUTE);

        scxmlElseTypeEClass = createEClass(ScxmlPackage.SCXML_ELSE_TYPE);
        createEAttribute(scxmlElseTypeEClass, ScxmlPackage.SCXML_ELSE_TYPE__ANY_ATTRIBUTE);

        scxmlFinalizeTypeEClass = createEClass(ScxmlPackage.SCXML_FINALIZE_TYPE);
        createEAttribute(scxmlFinalizeTypeEClass, ScxmlPackage.SCXML_FINALIZE_TYPE__SCXML_CORE_EXECUTABLECONTENT);
        createEAttribute(scxmlFinalizeTypeEClass, ScxmlPackage.SCXML_FINALIZE_TYPE__ANY);
        createEReference(scxmlFinalizeTypeEClass, ScxmlPackage.SCXML_FINALIZE_TYPE__RAISE);
        createEReference(scxmlFinalizeTypeEClass, ScxmlPackage.SCXML_FINALIZE_TYPE__IF);
        createEReference(scxmlFinalizeTypeEClass, ScxmlPackage.SCXML_FINALIZE_TYPE__FOREACH);
        createEReference(scxmlFinalizeTypeEClass, ScxmlPackage.SCXML_FINALIZE_TYPE__SEND);
        createEReference(scxmlFinalizeTypeEClass, ScxmlPackage.SCXML_FINALIZE_TYPE__SCRIPT);
        createEReference(scxmlFinalizeTypeEClass, ScxmlPackage.SCXML_FINALIZE_TYPE__ASSIGN);
        createEReference(scxmlFinalizeTypeEClass, ScxmlPackage.SCXML_FINALIZE_TYPE__LOG);
        createEReference(scxmlFinalizeTypeEClass, ScxmlPackage.SCXML_FINALIZE_TYPE__CANCEL);
        createEAttribute(scxmlFinalizeTypeEClass, ScxmlPackage.SCXML_FINALIZE_TYPE__ANY_ATTRIBUTE);

        scxmlFinalTypeEClass = createEClass(ScxmlPackage.SCXML_FINAL_TYPE);
        createEAttribute(scxmlFinalTypeEClass, ScxmlPackage.SCXML_FINAL_TYPE__SCXML_FINAL_MIX);
        createEReference(scxmlFinalTypeEClass, ScxmlPackage.SCXML_FINAL_TYPE__ONENTRY);
        createEReference(scxmlFinalTypeEClass, ScxmlPackage.SCXML_FINAL_TYPE__ONEXIT);
        createEReference(scxmlFinalTypeEClass, ScxmlPackage.SCXML_FINAL_TYPE__DONEDATA);
        createEAttribute(scxmlFinalTypeEClass, ScxmlPackage.SCXML_FINAL_TYPE__ANY);
        createEAttribute(scxmlFinalTypeEClass, ScxmlPackage.SCXML_FINAL_TYPE__ID);
        createEAttribute(scxmlFinalTypeEClass, ScxmlPackage.SCXML_FINAL_TYPE__ANY_ATTRIBUTE);

        scxmlForeachTypeEClass = createEClass(ScxmlPackage.SCXML_FOREACH_TYPE);
        createEAttribute(scxmlForeachTypeEClass, ScxmlPackage.SCXML_FOREACH_TYPE__SCXML_CORE_EXECUTABLECONTENT);
        createEAttribute(scxmlForeachTypeEClass, ScxmlPackage.SCXML_FOREACH_TYPE__ANY);
        createEReference(scxmlForeachTypeEClass, ScxmlPackage.SCXML_FOREACH_TYPE__RAISE);
        createEReference(scxmlForeachTypeEClass, ScxmlPackage.SCXML_FOREACH_TYPE__IF);
        createEReference(scxmlForeachTypeEClass, ScxmlPackage.SCXML_FOREACH_TYPE__FOREACH);
        createEReference(scxmlForeachTypeEClass, ScxmlPackage.SCXML_FOREACH_TYPE__SEND);
        createEReference(scxmlForeachTypeEClass, ScxmlPackage.SCXML_FOREACH_TYPE__SCRIPT);
        createEReference(scxmlForeachTypeEClass, ScxmlPackage.SCXML_FOREACH_TYPE__ASSIGN);
        createEReference(scxmlForeachTypeEClass, ScxmlPackage.SCXML_FOREACH_TYPE__LOG);
        createEReference(scxmlForeachTypeEClass, ScxmlPackage.SCXML_FOREACH_TYPE__CANCEL);
        createEAttribute(scxmlForeachTypeEClass, ScxmlPackage.SCXML_FOREACH_TYPE__ARRAY);
        createEAttribute(scxmlForeachTypeEClass, ScxmlPackage.SCXML_FOREACH_TYPE__INDEX);
        createEAttribute(scxmlForeachTypeEClass, ScxmlPackage.SCXML_FOREACH_TYPE__ITEM);
        createEAttribute(scxmlForeachTypeEClass, ScxmlPackage.SCXML_FOREACH_TYPE__ANY_ATTRIBUTE);

        scxmlHistoryTypeEClass = createEClass(ScxmlPackage.SCXML_HISTORY_TYPE);
        createEAttribute(scxmlHistoryTypeEClass, ScxmlPackage.SCXML_HISTORY_TYPE__SCXML_EXTRA_CONTENT);
        createEAttribute(scxmlHistoryTypeEClass, ScxmlPackage.SCXML_HISTORY_TYPE__ANY);
        createEReference(scxmlHistoryTypeEClass, ScxmlPackage.SCXML_HISTORY_TYPE__TRANSITION);
        createEAttribute(scxmlHistoryTypeEClass, ScxmlPackage.SCXML_HISTORY_TYPE__SCXML_EXTRA_CONTENT1);
        createEAttribute(scxmlHistoryTypeEClass, ScxmlPackage.SCXML_HISTORY_TYPE__ANY1);
        createEAttribute(scxmlHistoryTypeEClass, ScxmlPackage.SCXML_HISTORY_TYPE__ID);
        createEAttribute(scxmlHistoryTypeEClass, ScxmlPackage.SCXML_HISTORY_TYPE__TYPE);
        createEAttribute(scxmlHistoryTypeEClass, ScxmlPackage.SCXML_HISTORY_TYPE__ANY_ATTRIBUTE);

        scxmlIfTypeEClass = createEClass(ScxmlPackage.SCXML_IF_TYPE);
        createEAttribute(scxmlIfTypeEClass, ScxmlPackage.SCXML_IF_TYPE__SCXML_CORE_EXECUTABLECONTENT);
        createEAttribute(scxmlIfTypeEClass, ScxmlPackage.SCXML_IF_TYPE__ANY);
        createEReference(scxmlIfTypeEClass, ScxmlPackage.SCXML_IF_TYPE__RAISE);
        createEReference(scxmlIfTypeEClass, ScxmlPackage.SCXML_IF_TYPE__IF);
        createEReference(scxmlIfTypeEClass, ScxmlPackage.SCXML_IF_TYPE__FOREACH);
        createEReference(scxmlIfTypeEClass, ScxmlPackage.SCXML_IF_TYPE__SEND);
        createEReference(scxmlIfTypeEClass, ScxmlPackage.SCXML_IF_TYPE__SCRIPT);
        createEReference(scxmlIfTypeEClass, ScxmlPackage.SCXML_IF_TYPE__ASSIGN);
        createEReference(scxmlIfTypeEClass, ScxmlPackage.SCXML_IF_TYPE__LOG);
        createEReference(scxmlIfTypeEClass, ScxmlPackage.SCXML_IF_TYPE__CANCEL);
        createEReference(scxmlIfTypeEClass, ScxmlPackage.SCXML_IF_TYPE__ELSEIF);
        createEAttribute(scxmlIfTypeEClass, ScxmlPackage.SCXML_IF_TYPE__SCXML_CORE_EXECUTABLECONTENT1);
        createEAttribute(scxmlIfTypeEClass, ScxmlPackage.SCXML_IF_TYPE__ANY1);
        createEReference(scxmlIfTypeEClass, ScxmlPackage.SCXML_IF_TYPE__RAISE1);
        createEReference(scxmlIfTypeEClass, ScxmlPackage.SCXML_IF_TYPE__IF1);
        createEReference(scxmlIfTypeEClass, ScxmlPackage.SCXML_IF_TYPE__FOREACH1);
        createEReference(scxmlIfTypeEClass, ScxmlPackage.SCXML_IF_TYPE__SEND1);
        createEReference(scxmlIfTypeEClass, ScxmlPackage.SCXML_IF_TYPE__SCRIPT1);
        createEReference(scxmlIfTypeEClass, ScxmlPackage.SCXML_IF_TYPE__ASSIGN1);
        createEReference(scxmlIfTypeEClass, ScxmlPackage.SCXML_IF_TYPE__LOG1);
        createEReference(scxmlIfTypeEClass, ScxmlPackage.SCXML_IF_TYPE__CANCEL1);
        createEReference(scxmlIfTypeEClass, ScxmlPackage.SCXML_IF_TYPE__ELSE);
        createEAttribute(scxmlIfTypeEClass, ScxmlPackage.SCXML_IF_TYPE__SCXML_CORE_EXECUTABLECONTENT2);
        createEAttribute(scxmlIfTypeEClass, ScxmlPackage.SCXML_IF_TYPE__ANY2);
        createEReference(scxmlIfTypeEClass, ScxmlPackage.SCXML_IF_TYPE__RAISE2);
        createEReference(scxmlIfTypeEClass, ScxmlPackage.SCXML_IF_TYPE__IF2);
        createEReference(scxmlIfTypeEClass, ScxmlPackage.SCXML_IF_TYPE__FOREACH2);
        createEReference(scxmlIfTypeEClass, ScxmlPackage.SCXML_IF_TYPE__SEND2);
        createEReference(scxmlIfTypeEClass, ScxmlPackage.SCXML_IF_TYPE__SCRIPT2);
        createEReference(scxmlIfTypeEClass, ScxmlPackage.SCXML_IF_TYPE__ASSIGN2);
        createEReference(scxmlIfTypeEClass, ScxmlPackage.SCXML_IF_TYPE__LOG2);
        createEReference(scxmlIfTypeEClass, ScxmlPackage.SCXML_IF_TYPE__CANCEL2);
        createEAttribute(scxmlIfTypeEClass, ScxmlPackage.SCXML_IF_TYPE__COND);
        createEAttribute(scxmlIfTypeEClass, ScxmlPackage.SCXML_IF_TYPE__ANY_ATTRIBUTE);

        scxmlInitialTypeEClass = createEClass(ScxmlPackage.SCXML_INITIAL_TYPE);
        createEAttribute(scxmlInitialTypeEClass, ScxmlPackage.SCXML_INITIAL_TYPE__SCXML_EXTRA_CONTENT);
        createEAttribute(scxmlInitialTypeEClass, ScxmlPackage.SCXML_INITIAL_TYPE__ANY);
        createEReference(scxmlInitialTypeEClass, ScxmlPackage.SCXML_INITIAL_TYPE__TRANSITION);
        createEAttribute(scxmlInitialTypeEClass, ScxmlPackage.SCXML_INITIAL_TYPE__SCXML_EXTRA_CONTENT1);
        createEAttribute(scxmlInitialTypeEClass, ScxmlPackage.SCXML_INITIAL_TYPE__ANY1);
        createEAttribute(scxmlInitialTypeEClass, ScxmlPackage.SCXML_INITIAL_TYPE__ANY_ATTRIBUTE);

        scxmlInvokeTypeEClass = createEClass(ScxmlPackage.SCXML_INVOKE_TYPE);
        createEAttribute(scxmlInvokeTypeEClass, ScxmlPackage.SCXML_INVOKE_TYPE__SCXML_INVOKE_MIX);
        createEReference(scxmlInvokeTypeEClass, ScxmlPackage.SCXML_INVOKE_TYPE__CONTENT);
        createEReference(scxmlInvokeTypeEClass, ScxmlPackage.SCXML_INVOKE_TYPE__PARAM);
        createEReference(scxmlInvokeTypeEClass, ScxmlPackage.SCXML_INVOKE_TYPE__FINALIZE);
        createEAttribute(scxmlInvokeTypeEClass, ScxmlPackage.SCXML_INVOKE_TYPE__ANY);
        createEAttribute(scxmlInvokeTypeEClass, ScxmlPackage.SCXML_INVOKE_TYPE__AUTOFORWARD);
        createEAttribute(scxmlInvokeTypeEClass, ScxmlPackage.SCXML_INVOKE_TYPE__ID);
        createEAttribute(scxmlInvokeTypeEClass, ScxmlPackage.SCXML_INVOKE_TYPE__IDLOCATION);
        createEAttribute(scxmlInvokeTypeEClass, ScxmlPackage.SCXML_INVOKE_TYPE__NAMELIST);
        createEAttribute(scxmlInvokeTypeEClass, ScxmlPackage.SCXML_INVOKE_TYPE__SRC);
        createEAttribute(scxmlInvokeTypeEClass, ScxmlPackage.SCXML_INVOKE_TYPE__SRCEXPR);
        createEAttribute(scxmlInvokeTypeEClass, ScxmlPackage.SCXML_INVOKE_TYPE__TYPE);
        createEAttribute(scxmlInvokeTypeEClass, ScxmlPackage.SCXML_INVOKE_TYPE__TYPEEXPR);
        createEAttribute(scxmlInvokeTypeEClass, ScxmlPackage.SCXML_INVOKE_TYPE__ANY_ATTRIBUTE);

        scxmlLogTypeEClass = createEClass(ScxmlPackage.SCXML_LOG_TYPE);
        createEAttribute(scxmlLogTypeEClass, ScxmlPackage.SCXML_LOG_TYPE__SCXML_EXTRA_CONTENT);
        createEAttribute(scxmlLogTypeEClass, ScxmlPackage.SCXML_LOG_TYPE__ANY);
        createEAttribute(scxmlLogTypeEClass, ScxmlPackage.SCXML_LOG_TYPE__EXPR);
        createEAttribute(scxmlLogTypeEClass, ScxmlPackage.SCXML_LOG_TYPE__LABEL);
        createEAttribute(scxmlLogTypeEClass, ScxmlPackage.SCXML_LOG_TYPE__ANY_ATTRIBUTE);

        scxmlOnentryTypeEClass = createEClass(ScxmlPackage.SCXML_ONENTRY_TYPE);
        createEAttribute(scxmlOnentryTypeEClass, ScxmlPackage.SCXML_ONENTRY_TYPE__SCXML_CORE_EXECUTABLECONTENT);
        createEAttribute(scxmlOnentryTypeEClass, ScxmlPackage.SCXML_ONENTRY_TYPE__ANY);
        createEReference(scxmlOnentryTypeEClass, ScxmlPackage.SCXML_ONENTRY_TYPE__RAISE);
        createEReference(scxmlOnentryTypeEClass, ScxmlPackage.SCXML_ONENTRY_TYPE__IF);
        createEReference(scxmlOnentryTypeEClass, ScxmlPackage.SCXML_ONENTRY_TYPE__FOREACH);
        createEReference(scxmlOnentryTypeEClass, ScxmlPackage.SCXML_ONENTRY_TYPE__SEND);
        createEReference(scxmlOnentryTypeEClass, ScxmlPackage.SCXML_ONENTRY_TYPE__SCRIPT);
        createEReference(scxmlOnentryTypeEClass, ScxmlPackage.SCXML_ONENTRY_TYPE__ASSIGN);
        createEReference(scxmlOnentryTypeEClass, ScxmlPackage.SCXML_ONENTRY_TYPE__LOG);
        createEReference(scxmlOnentryTypeEClass, ScxmlPackage.SCXML_ONENTRY_TYPE__CANCEL);
        createEAttribute(scxmlOnentryTypeEClass, ScxmlPackage.SCXML_ONENTRY_TYPE__ANY_ATTRIBUTE);

        scxmlOnexitTypeEClass = createEClass(ScxmlPackage.SCXML_ONEXIT_TYPE);
        createEAttribute(scxmlOnexitTypeEClass, ScxmlPackage.SCXML_ONEXIT_TYPE__SCXML_CORE_EXECUTABLECONTENT);
        createEAttribute(scxmlOnexitTypeEClass, ScxmlPackage.SCXML_ONEXIT_TYPE__ANY);
        createEReference(scxmlOnexitTypeEClass, ScxmlPackage.SCXML_ONEXIT_TYPE__RAISE);
        createEReference(scxmlOnexitTypeEClass, ScxmlPackage.SCXML_ONEXIT_TYPE__IF);
        createEReference(scxmlOnexitTypeEClass, ScxmlPackage.SCXML_ONEXIT_TYPE__FOREACH);
        createEReference(scxmlOnexitTypeEClass, ScxmlPackage.SCXML_ONEXIT_TYPE__SEND);
        createEReference(scxmlOnexitTypeEClass, ScxmlPackage.SCXML_ONEXIT_TYPE__SCRIPT);
        createEReference(scxmlOnexitTypeEClass, ScxmlPackage.SCXML_ONEXIT_TYPE__ASSIGN);
        createEReference(scxmlOnexitTypeEClass, ScxmlPackage.SCXML_ONEXIT_TYPE__LOG);
        createEReference(scxmlOnexitTypeEClass, ScxmlPackage.SCXML_ONEXIT_TYPE__CANCEL);
        createEAttribute(scxmlOnexitTypeEClass, ScxmlPackage.SCXML_ONEXIT_TYPE__ANY_ATTRIBUTE);

        scxmlParallelTypeEClass = createEClass(ScxmlPackage.SCXML_PARALLEL_TYPE);
        createEAttribute(scxmlParallelTypeEClass, ScxmlPackage.SCXML_PARALLEL_TYPE__SCXML_PARALLEL_MIX);
        createEReference(scxmlParallelTypeEClass, ScxmlPackage.SCXML_PARALLEL_TYPE__ONENTRY);
        createEReference(scxmlParallelTypeEClass, ScxmlPackage.SCXML_PARALLEL_TYPE__ONEXIT);
        createEReference(scxmlParallelTypeEClass, ScxmlPackage.SCXML_PARALLEL_TYPE__TRANSITION);
        createEReference(scxmlParallelTypeEClass, ScxmlPackage.SCXML_PARALLEL_TYPE__STATE);
        createEReference(scxmlParallelTypeEClass, ScxmlPackage.SCXML_PARALLEL_TYPE__PARALLEL);
        createEReference(scxmlParallelTypeEClass, ScxmlPackage.SCXML_PARALLEL_TYPE__HISTORY);
        createEReference(scxmlParallelTypeEClass, ScxmlPackage.SCXML_PARALLEL_TYPE__DATAMODEL);
        createEReference(scxmlParallelTypeEClass, ScxmlPackage.SCXML_PARALLEL_TYPE__INVOKE);
        createEAttribute(scxmlParallelTypeEClass, ScxmlPackage.SCXML_PARALLEL_TYPE__ANY);
        createEAttribute(scxmlParallelTypeEClass, ScxmlPackage.SCXML_PARALLEL_TYPE__ID);
        createEAttribute(scxmlParallelTypeEClass, ScxmlPackage.SCXML_PARALLEL_TYPE__ANY_ATTRIBUTE);

        scxmlParamTypeEClass = createEClass(ScxmlPackage.SCXML_PARAM_TYPE);
        createEAttribute(scxmlParamTypeEClass, ScxmlPackage.SCXML_PARAM_TYPE__SCXML_EXTRA_CONTENT);
        createEAttribute(scxmlParamTypeEClass, ScxmlPackage.SCXML_PARAM_TYPE__ANY);
        createEAttribute(scxmlParamTypeEClass, ScxmlPackage.SCXML_PARAM_TYPE__EXPR);
        createEAttribute(scxmlParamTypeEClass, ScxmlPackage.SCXML_PARAM_TYPE__LOCATION);
        createEAttribute(scxmlParamTypeEClass, ScxmlPackage.SCXML_PARAM_TYPE__NAME);
        createEAttribute(scxmlParamTypeEClass, ScxmlPackage.SCXML_PARAM_TYPE__ANY_ATTRIBUTE);

        scxmlRaiseTypeEClass = createEClass(ScxmlPackage.SCXML_RAISE_TYPE);
        createEAttribute(scxmlRaiseTypeEClass, ScxmlPackage.SCXML_RAISE_TYPE__EVENT);
        createEAttribute(scxmlRaiseTypeEClass, ScxmlPackage.SCXML_RAISE_TYPE__ANY_ATTRIBUTE);

        scxmlScriptTypeEClass = createEClass(ScxmlPackage.SCXML_SCRIPT_TYPE);
        createEAttribute(scxmlScriptTypeEClass, ScxmlPackage.SCXML_SCRIPT_TYPE__MIXED);
        createEAttribute(scxmlScriptTypeEClass, ScxmlPackage.SCXML_SCRIPT_TYPE__SCXML_EXTRA_CONTENT);
        createEAttribute(scxmlScriptTypeEClass, ScxmlPackage.SCXML_SCRIPT_TYPE__ANY);
        createEAttribute(scxmlScriptTypeEClass, ScxmlPackage.SCXML_SCRIPT_TYPE__SRC);
        createEAttribute(scxmlScriptTypeEClass, ScxmlPackage.SCXML_SCRIPT_TYPE__ANY_ATTRIBUTE);

        scxmlScxmlTypeEClass = createEClass(ScxmlPackage.SCXML_SCXML_TYPE);
        createEAttribute(scxmlScxmlTypeEClass, ScxmlPackage.SCXML_SCXML_TYPE__SCXML_SCXML_MIX);
        createEReference(scxmlScxmlTypeEClass, ScxmlPackage.SCXML_SCXML_TYPE__STATE);
        createEReference(scxmlScxmlTypeEClass, ScxmlPackage.SCXML_SCXML_TYPE__PARALLEL);
        createEReference(scxmlScxmlTypeEClass, ScxmlPackage.SCXML_SCXML_TYPE__FINAL);
        createEReference(scxmlScxmlTypeEClass, ScxmlPackage.SCXML_SCXML_TYPE__DATAMODEL);
        createEReference(scxmlScxmlTypeEClass, ScxmlPackage.SCXML_SCXML_TYPE__SCRIPT);
        createEAttribute(scxmlScxmlTypeEClass, ScxmlPackage.SCXML_SCXML_TYPE__ANY);
        createEAttribute(scxmlScxmlTypeEClass, ScxmlPackage.SCXML_SCXML_TYPE__BINDING);
        createEAttribute(scxmlScxmlTypeEClass, ScxmlPackage.SCXML_SCXML_TYPE__DATAMODEL1);
        createEAttribute(scxmlScxmlTypeEClass, ScxmlPackage.SCXML_SCXML_TYPE__EXMODE);
        createEAttribute(scxmlScxmlTypeEClass, ScxmlPackage.SCXML_SCXML_TYPE__INITIAL);
        createEAttribute(scxmlScxmlTypeEClass, ScxmlPackage.SCXML_SCXML_TYPE__NAME);
        createEAttribute(scxmlScxmlTypeEClass, ScxmlPackage.SCXML_SCXML_TYPE__VERSION);
        createEAttribute(scxmlScxmlTypeEClass, ScxmlPackage.SCXML_SCXML_TYPE__ANY_ATTRIBUTE);

        scxmlSendTypeEClass = createEClass(ScxmlPackage.SCXML_SEND_TYPE);
        createEAttribute(scxmlSendTypeEClass, ScxmlPackage.SCXML_SEND_TYPE__SCXML_SEND_MIX);
        createEReference(scxmlSendTypeEClass, ScxmlPackage.SCXML_SEND_TYPE__CONTENT);
        createEReference(scxmlSendTypeEClass, ScxmlPackage.SCXML_SEND_TYPE__PARAM);
        createEAttribute(scxmlSendTypeEClass, ScxmlPackage.SCXML_SEND_TYPE__ANY);
        createEAttribute(scxmlSendTypeEClass, ScxmlPackage.SCXML_SEND_TYPE__DELAY);
        createEAttribute(scxmlSendTypeEClass, ScxmlPackage.SCXML_SEND_TYPE__DELAYEXPR);
        createEAttribute(scxmlSendTypeEClass, ScxmlPackage.SCXML_SEND_TYPE__EVENT);
        createEAttribute(scxmlSendTypeEClass, ScxmlPackage.SCXML_SEND_TYPE__EVENTEXPR);
        createEAttribute(scxmlSendTypeEClass, ScxmlPackage.SCXML_SEND_TYPE__ID);
        createEAttribute(scxmlSendTypeEClass, ScxmlPackage.SCXML_SEND_TYPE__IDLOCATION);
        createEAttribute(scxmlSendTypeEClass, ScxmlPackage.SCXML_SEND_TYPE__NAMELIST);
        createEAttribute(scxmlSendTypeEClass, ScxmlPackage.SCXML_SEND_TYPE__TARGET);
        createEAttribute(scxmlSendTypeEClass, ScxmlPackage.SCXML_SEND_TYPE__TARGETEXPR);
        createEAttribute(scxmlSendTypeEClass, ScxmlPackage.SCXML_SEND_TYPE__TYPE);
        createEAttribute(scxmlSendTypeEClass, ScxmlPackage.SCXML_SEND_TYPE__TYPEEXPR);
        createEAttribute(scxmlSendTypeEClass, ScxmlPackage.SCXML_SEND_TYPE__ANY_ATTRIBUTE);

        scxmlStateTypeEClass = createEClass(ScxmlPackage.SCXML_STATE_TYPE);
        createEAttribute(scxmlStateTypeEClass, ScxmlPackage.SCXML_STATE_TYPE__SCXML_STATE_MIX);
        createEReference(scxmlStateTypeEClass, ScxmlPackage.SCXML_STATE_TYPE__ONENTRY);
        createEReference(scxmlStateTypeEClass, ScxmlPackage.SCXML_STATE_TYPE__ONEXIT);
        createEReference(scxmlStateTypeEClass, ScxmlPackage.SCXML_STATE_TYPE__TRANSITION);
        createEReference(scxmlStateTypeEClass, ScxmlPackage.SCXML_STATE_TYPE__INITIAL);
        createEReference(scxmlStateTypeEClass, ScxmlPackage.SCXML_STATE_TYPE__STATE);
        createEReference(scxmlStateTypeEClass, ScxmlPackage.SCXML_STATE_TYPE__PARALLEL);
        createEReference(scxmlStateTypeEClass, ScxmlPackage.SCXML_STATE_TYPE__FINAL);
        createEReference(scxmlStateTypeEClass, ScxmlPackage.SCXML_STATE_TYPE__HISTORY);
        createEReference(scxmlStateTypeEClass, ScxmlPackage.SCXML_STATE_TYPE__DATAMODEL);
        createEReference(scxmlStateTypeEClass, ScxmlPackage.SCXML_STATE_TYPE__INVOKE);
        createEAttribute(scxmlStateTypeEClass, ScxmlPackage.SCXML_STATE_TYPE__ANY);
        createEAttribute(scxmlStateTypeEClass, ScxmlPackage.SCXML_STATE_TYPE__ID);
        createEAttribute(scxmlStateTypeEClass, ScxmlPackage.SCXML_STATE_TYPE__INITIAL1);
        createEAttribute(scxmlStateTypeEClass, ScxmlPackage.SCXML_STATE_TYPE__ANY_ATTRIBUTE);

        scxmlTransitionTypeEClass = createEClass(ScxmlPackage.SCXML_TRANSITION_TYPE);
        createEAttribute(scxmlTransitionTypeEClass, ScxmlPackage.SCXML_TRANSITION_TYPE__SCXML_CORE_EXECUTABLECONTENT);
        createEAttribute(scxmlTransitionTypeEClass, ScxmlPackage.SCXML_TRANSITION_TYPE__ANY);
        createEReference(scxmlTransitionTypeEClass, ScxmlPackage.SCXML_TRANSITION_TYPE__RAISE);
        createEReference(scxmlTransitionTypeEClass, ScxmlPackage.SCXML_TRANSITION_TYPE__IF);
        createEReference(scxmlTransitionTypeEClass, ScxmlPackage.SCXML_TRANSITION_TYPE__FOREACH);
        createEReference(scxmlTransitionTypeEClass, ScxmlPackage.SCXML_TRANSITION_TYPE__SEND);
        createEReference(scxmlTransitionTypeEClass, ScxmlPackage.SCXML_TRANSITION_TYPE__SCRIPT);
        createEReference(scxmlTransitionTypeEClass, ScxmlPackage.SCXML_TRANSITION_TYPE__ASSIGN);
        createEReference(scxmlTransitionTypeEClass, ScxmlPackage.SCXML_TRANSITION_TYPE__LOG);
        createEReference(scxmlTransitionTypeEClass, ScxmlPackage.SCXML_TRANSITION_TYPE__CANCEL);
        createEAttribute(scxmlTransitionTypeEClass, ScxmlPackage.SCXML_TRANSITION_TYPE__COND);
        createEAttribute(scxmlTransitionTypeEClass, ScxmlPackage.SCXML_TRANSITION_TYPE__EVENT);
        createEAttribute(scxmlTransitionTypeEClass, ScxmlPackage.SCXML_TRANSITION_TYPE__TARGET);
        createEAttribute(scxmlTransitionTypeEClass, ScxmlPackage.SCXML_TRANSITION_TYPE__TYPE);
        createEAttribute(scxmlTransitionTypeEClass, ScxmlPackage.SCXML_TRANSITION_TYPE__ANY_ATTRIBUTE);

        // Create enums
        assignTypeDatatypeEEnum = createEEnum(ScxmlPackage.ASSIGN_TYPE_DATATYPE);
        bindingDatatypeEEnum = createEEnum(ScxmlPackage.BINDING_DATATYPE);
        booleanDatatypeEEnum = createEEnum(ScxmlPackage.BOOLEAN_DATATYPE);
        exmodeDatatypeEEnum = createEEnum(ScxmlPackage.EXMODE_DATATYPE);
        historyTypeDatatypeEEnum = createEEnum(ScxmlPackage.HISTORY_TYPE_DATATYPE);
        transitionTypeDatatypeEEnum = createEEnum(ScxmlPackage.TRANSITION_TYPE_DATATYPE);

        // Create data types
        assignTypeDatatypeObjectEDataType = createEDataType(ScxmlPackage.ASSIGN_TYPE_DATATYPE_OBJECT);
        bindingDatatypeObjectEDataType = createEDataType(ScxmlPackage.BINDING_DATATYPE_OBJECT);
        booleanDatatypeObjectEDataType = createEDataType(ScxmlPackage.BOOLEAN_DATATYPE_OBJECT);
        condLangDatatypeEDataType = createEDataType(ScxmlPackage.COND_LANG_DATATYPE);
        durationDatatypeEDataType = createEDataType(ScxmlPackage.DURATION_DATATYPE);
        eventTypeDatatypeEDataType = createEDataType(ScxmlPackage.EVENT_TYPE_DATATYPE);
        eventTypesDatatypeEDataType = createEDataType(ScxmlPackage.EVENT_TYPES_DATATYPE);
        exmodeDatatypeObjectEDataType = createEDataType(ScxmlPackage.EXMODE_DATATYPE_OBJECT);
        historyTypeDatatypeObjectEDataType = createEDataType(ScxmlPackage.HISTORY_TYPE_DATATYPE_OBJECT);
        integerDatatypeEDataType = createEDataType(ScxmlPackage.INTEGER_DATATYPE);
        locLangDatatypeEDataType = createEDataType(ScxmlPackage.LOC_LANG_DATATYPE);
        transitionTypeDatatypeObjectEDataType = createEDataType(ScxmlPackage.TRANSITION_TYPE_DATATYPE_OBJECT);
        uriDatatypeEDataType = createEDataType(ScxmlPackage.URI_DATATYPE);
        valueLangDatatypeEDataType = createEDataType(ScxmlPackage.VALUE_LANG_DATATYPE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model. This
     * method is guarded to have no affect on any invocation but its first. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized) {
            return;
        }
        isInitialized = true;

        // Initialize package
        setName(ScxmlPackage.eNAME);
        setNsPrefix(ScxmlPackage.eNS_PREFIX);
        setNsURI(ScxmlPackage.eNS_URI);

        // Obtain other dependent packages
        XMLTypePackage theXMLTypePackage = (XMLTypePackage) EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes

        // Initialize classes, features, and operations; add parameters
        initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getDocumentRoot_Mixed(),
                ecorePackage.getEFeatureMapEntry(),
                "mixed", null, 0, -1, null, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getDocumentRoot_XMLNSPrefixMap(),
                ecorePackage.getEStringToStringMapEntry(),
                null,
                "xMLNSPrefixMap", null, 0, -1, null, EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getDocumentRoot_XSISchemaLocation(),
                ecorePackage.getEStringToStringMapEntry(),
                null,
                "xSISchemaLocation", null, 0, -1, null, EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getDocumentRoot_Assign(),
                this.getScxmlAssignType(),
                null,
                "assign", null, 0, -2, null, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getDocumentRoot_Cancel(),
                this.getScxmlCancelType(),
                null,
                "cancel", null, 0, -2, null, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getDocumentRoot_Content(),
                this.getScxmlContentType(),
                null,
                "content", null, 0, -2, null, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getDocumentRoot_Data(),
                this.getScxmlDataType(),
                null,
                "data", null, 0, -2, null, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getDocumentRoot_Datamodel(),
                this.getScxmlDatamodelType(),
                null,
                "datamodel", null, 0, -2, null, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getDocumentRoot_Donedata(),
                this.getScxmlDonedataType(),
                null,
                "donedata", null, 0, -2, null, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getDocumentRoot_Else(),
                this.getScxmlElseType(),
                null,
                "else", null, 0, -2, null, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getDocumentRoot_Elseif(),
                this.getScxmlElseifType(),
                null,
                "elseif", null, 0, -2, null, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getDocumentRoot_Final(),
                this.getScxmlFinalType(),
                null,
                "final", null, 0, -2, null, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getDocumentRoot_Finalize(),
                this.getScxmlFinalizeType(),
                null,
                "finalize", null, 0, -2, null, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getDocumentRoot_Foreach(),
                this.getScxmlForeachType(),
                null,
                "foreach", null, 0, -2, null, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getDocumentRoot_History(),
                this.getScxmlHistoryType(),
                null,
                "history", null, 0, -2, null, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getDocumentRoot_If(),
                this.getScxmlIfType(),
                null,
                "if", null, 0, -2, null, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getDocumentRoot_Initial(),
                this.getScxmlInitialType(),
                null,
                "initial", null, 0, -2, null, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getDocumentRoot_Invoke(),
                this.getScxmlInvokeType(),
                null,
                "invoke", null, 0, -2, null, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getDocumentRoot_Log(),
                this.getScxmlLogType(),
                null,
                "log", null, 0, -2, null, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getDocumentRoot_Onentry(),
                this.getScxmlOnentryType(),
                null,
                "onentry", null, 0, -2, null, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getDocumentRoot_Onexit(),
                this.getScxmlOnexitType(),
                null,
                "onexit", null, 0, -2, null, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getDocumentRoot_Parallel(),
                this.getScxmlParallelType(),
                null,
                "parallel", null, 0, -2, null, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getDocumentRoot_Param(),
                this.getScxmlParamType(),
                null,
                "param", null, 0, -2, null, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getDocumentRoot_Raise(),
                this.getScxmlRaiseType(),
                null,
                "raise", null, 0, -2, null, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getDocumentRoot_Script(),
                this.getScxmlScriptType(),
                null,
                "script", null, 0, -2, null, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getDocumentRoot_Scxml(),
                this.getScxmlScxmlType(),
                null,
                "scxml", null, 0, -2, null, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getDocumentRoot_Send(),
                this.getScxmlSendType(),
                null,
                "send", null, 0, -2, null, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getDocumentRoot_State(),
                this.getScxmlStateType(),
                null,
                "state", null, 0, -2, null, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getDocumentRoot_Transition(),
                this.getScxmlTransitionType(),
                null,
                "transition", null, 0, -2, null, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(scxmlAssignTypeEClass, ScxmlAssignType.class, "ScxmlAssignType", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getScxmlAssignType_Mixed(),
                ecorePackage.getEFeatureMapEntry(),
                "mixed", null, 0, -1, ScxmlAssignType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlAssignType_Any(),
                ecorePackage.getEFeatureMapEntry(),
                "any", null, 0, -1, ScxmlAssignType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlAssignType_Attr(),
                theXMLTypePackage.getNMTOKEN(),
                "attr", null, 0, 1, ScxmlAssignType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlAssignType_Expr(),
                this.getValueLangDatatype(),
                "expr", null, 0, 1, ScxmlAssignType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlAssignType_Location(),
                this.getLocLangDatatype(),
                "location", null, 1, 1, ScxmlAssignType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlAssignType_Type(),
                this.getAssignTypeDatatype(),
                "type", "replacechildren", 0, 1, ScxmlAssignType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(
                getScxmlAssignType_AnyAttribute(),
                ecorePackage.getEFeatureMapEntry(),
                "anyAttribute", null, 0, -1, ScxmlAssignType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(scxmlCancelTypeEClass, ScxmlCancelType.class, "ScxmlCancelType", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getScxmlCancelType_ScxmlExtraContent(),
                ecorePackage.getEFeatureMapEntry(),
                "scxmlExtraContent", null, 0, -1, ScxmlCancelType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlCancelType_Any(),
                ecorePackage.getEFeatureMapEntry(),
                "any", null, 0, -1, ScxmlCancelType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlCancelType_Sendid(),
                theXMLTypePackage.getIDREF(),
                "sendid", null, 0, 1, ScxmlCancelType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlCancelType_Sendidexpr(),
                this.getValueLangDatatype(),
                "sendidexpr", null, 0, 1, ScxmlCancelType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlCancelType_AnyAttribute(),
                ecorePackage.getEFeatureMapEntry(),
                "anyAttribute", null, 0, -1, ScxmlCancelType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(scxmlContentTypeEClass, ScxmlContentType.class, "ScxmlContentType", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getScxmlContentType_Mixed(),
                ecorePackage.getEFeatureMapEntry(),
                "mixed", null, 0, -1, ScxmlContentType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlContentType_Any(),
                ecorePackage.getEFeatureMapEntry(),
                "any", null, 0, -1, ScxmlContentType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlContentType_Expr(),
                this.getValueLangDatatype(),
                "expr", null, 0, 1, ScxmlContentType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlContentType_AnyAttribute(),
                ecorePackage.getEFeatureMapEntry(),
                "anyAttribute", null, 0, -1, ScxmlContentType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(scxmlDatamodelTypeEClass, ScxmlDatamodelType.class, "ScxmlDatamodelType", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getScxmlDatamodelType_Data(),
                this.getScxmlDataType(),
                null,
                "data", null, 0, -1, ScxmlDatamodelType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlDatamodelType_ScxmlExtraContent(),
                ecorePackage.getEFeatureMapEntry(),
                "scxmlExtraContent", null, 0, -1, ScxmlDatamodelType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlDatamodelType_Any(),
                ecorePackage.getEFeatureMapEntry(),
                "any", null, 0, -1, ScxmlDatamodelType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlDatamodelType_AnyAttribute(),
                ecorePackage.getEFeatureMapEntry(),
                "anyAttribute", null, 0, -1, ScxmlDatamodelType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(scxmlDataTypeEClass, ScxmlDataType.class, "ScxmlDataType", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getScxmlDataType_Mixed(),
                ecorePackage.getEFeatureMapEntry(),
                "mixed", null, 0, -1, ScxmlDataType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlDataType_Any(),
                ecorePackage.getEFeatureMapEntry(),
                "any", null, 0, -1, ScxmlDataType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlDataType_Expr(),
                this.getValueLangDatatype(),
                "expr", null, 0, 1, ScxmlDataType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlDataType_Id(),
                theXMLTypePackage.getID(),
                "id", null, 1, 1, ScxmlDataType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlDataType_Src(),
                this.getURIDatatype(),
                "src", null, 0, 1, ScxmlDataType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlDataType_AnyAttribute(),
                ecorePackage.getEFeatureMapEntry(),
                "anyAttribute", null, 0, -1, ScxmlDataType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(scxmlDonedataTypeEClass, ScxmlDonedataType.class, "ScxmlDonedataType", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getScxmlDonedataType_Content(),
                this.getScxmlContentType(),
                null,
                "content", null, 0, 1, ScxmlDonedataType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlDonedataType_Param(),
                this.getScxmlParamType(),
                null,
                "param", null, 0, -1, ScxmlDonedataType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlDonedataType_AnyAttribute(),
                ecorePackage.getEFeatureMapEntry(),
                "anyAttribute", null, 0, -1, ScxmlDonedataType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(scxmlElseifTypeEClass, ScxmlElseifType.class, "ScxmlElseifType", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getScxmlElseifType_Cond(),
                this.getCondLangDatatype(),
                "cond", null, 1, 1, ScxmlElseifType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlElseifType_AnyAttribute(),
                ecorePackage.getEFeatureMapEntry(),
                "anyAttribute", null, 0, -1, ScxmlElseifType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(scxmlElseTypeEClass, ScxmlElseType.class, "ScxmlElseType", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getScxmlElseType_AnyAttribute(),
                ecorePackage.getEFeatureMapEntry(),
                "anyAttribute", null, 0, -1, ScxmlElseType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(scxmlFinalizeTypeEClass, ScxmlFinalizeType.class, "ScxmlFinalizeType", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getScxmlFinalizeType_ScxmlCoreExecutablecontent(),
                ecorePackage.getEFeatureMapEntry(),
                "scxmlCoreExecutablecontent", null, 0, -1, ScxmlFinalizeType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlFinalizeType_Any(),
                ecorePackage.getEFeatureMapEntry(),
                "any", null, 0, -1, ScxmlFinalizeType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlFinalizeType_Raise(),
                this.getScxmlRaiseType(),
                null,
                "raise", null, 0, -1, ScxmlFinalizeType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlFinalizeType_If(),
                this.getScxmlIfType(),
                null,
                "if", null, 0, -1, ScxmlFinalizeType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlFinalizeType_Foreach(),
                this.getScxmlForeachType(),
                null,
                "foreach", null, 0, -1, ScxmlFinalizeType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlFinalizeType_Send(),
                this.getScxmlSendType(),
                null,
                "send", null, 0, -1, ScxmlFinalizeType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlFinalizeType_Script(),
                this.getScxmlScriptType(),
                null,
                "script", null, 0, -1, ScxmlFinalizeType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlFinalizeType_Assign(),
                this.getScxmlAssignType(),
                null,
                "assign", null, 0, -1, ScxmlFinalizeType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlFinalizeType_Log(),
                this.getScxmlLogType(),
                null,
                "log", null, 0, -1, ScxmlFinalizeType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlFinalizeType_Cancel(),
                this.getScxmlCancelType(),
                null,
                "cancel", null, 0, -1, ScxmlFinalizeType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlFinalizeType_AnyAttribute(),
                ecorePackage.getEFeatureMapEntry(),
                "anyAttribute", null, 0, -1, ScxmlFinalizeType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(scxmlFinalTypeEClass, ScxmlFinalType.class, "ScxmlFinalType", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getScxmlFinalType_ScxmlFinalMix(),
                ecorePackage.getEFeatureMapEntry(),
                "scxmlFinalMix", null, 0, -1, ScxmlFinalType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlFinalType_Onentry(),
                this.getScxmlOnentryType(),
                null,
                "onentry", null, 0, -1, ScxmlFinalType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlFinalType_Onexit(),
                this.getScxmlOnexitType(),
                null,
                "onexit", null, 0, -1, ScxmlFinalType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlFinalType_Donedata(),
                this.getScxmlDonedataType(),
                null,
                "donedata", null, 0, -1, ScxmlFinalType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlFinalType_Any(),
                ecorePackage.getEFeatureMapEntry(),
                "any", null, 0, -1, ScxmlFinalType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlFinalType_Id(),
                theXMLTypePackage.getID(),
                "id", null, 0, 1, ScxmlFinalType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlFinalType_AnyAttribute(),
                ecorePackage.getEFeatureMapEntry(),
                "anyAttribute", null, 0, -1, ScxmlFinalType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(scxmlForeachTypeEClass, ScxmlForeachType.class, "ScxmlForeachType", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getScxmlForeachType_ScxmlCoreExecutablecontent(),
                ecorePackage.getEFeatureMapEntry(),
                "scxmlCoreExecutablecontent", null, 0, -1, ScxmlForeachType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlForeachType_Any(),
                ecorePackage.getEFeatureMapEntry(),
                "any", null, 0, -1, ScxmlForeachType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlForeachType_Raise(),
                this.getScxmlRaiseType(),
                null,
                "raise", null, 0, -1, ScxmlForeachType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlForeachType_If(),
                this.getScxmlIfType(),
                null,
                "if", null, 0, -1, ScxmlForeachType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlForeachType_Foreach(),
                this.getScxmlForeachType(),
                null,
                "foreach", null, 0, -1, ScxmlForeachType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlForeachType_Send(),
                this.getScxmlSendType(),
                null,
                "send", null, 0, -1, ScxmlForeachType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlForeachType_Script(),
                this.getScxmlScriptType(),
                null,
                "script", null, 0, -1, ScxmlForeachType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlForeachType_Assign(),
                this.getScxmlAssignType(),
                null,
                "assign", null, 0, -1, ScxmlForeachType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlForeachType_Log(),
                this.getScxmlLogType(),
                null,
                "log", null, 0, -1, ScxmlForeachType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlForeachType_Cancel(),
                this.getScxmlCancelType(),
                null,
                "cancel", null, 0, -1, ScxmlForeachType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlForeachType_Array(),
                this.getValueLangDatatype(),
                "array", null, 1, 1, ScxmlForeachType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlForeachType_Index(),
                theXMLTypePackage.getString(),
                "index", null, 0, 1, ScxmlForeachType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlForeachType_Item(),
                theXMLTypePackage.getString(),
                "item", null, 1, 1, ScxmlForeachType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlForeachType_AnyAttribute(),
                ecorePackage.getEFeatureMapEntry(),
                "anyAttribute", null, 0, -1, ScxmlForeachType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(scxmlHistoryTypeEClass, ScxmlHistoryType.class, "ScxmlHistoryType", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getScxmlHistoryType_ScxmlExtraContent(),
                ecorePackage.getEFeatureMapEntry(),
                "scxmlExtraContent", null, 0, -1, ScxmlHistoryType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlHistoryType_Any(),
                ecorePackage.getEFeatureMapEntry(),
                "any", null, 0, -1, ScxmlHistoryType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlHistoryType_Transition(),
                this.getScxmlTransitionType(),
                null,
                "transition", null, 1, 1, ScxmlHistoryType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlHistoryType_ScxmlExtraContent1(),
                ecorePackage.getEFeatureMapEntry(),
                "scxmlExtraContent1", null, 0, -1, ScxmlHistoryType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlHistoryType_Any1(),
                ecorePackage.getEFeatureMapEntry(),
                "any1", null, 0, -1, ScxmlHistoryType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlHistoryType_Id(),
                theXMLTypePackage.getID(),
                "id", null, 0, 1, ScxmlHistoryType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlHistoryType_Type(),
                this.getHistoryTypeDatatype(),
                "type", null, 0, 1, ScxmlHistoryType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlHistoryType_AnyAttribute(),
                ecorePackage.getEFeatureMapEntry(),
                "anyAttribute", null, 0, -1, ScxmlHistoryType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(scxmlIfTypeEClass, ScxmlIfType.class, "ScxmlIfType", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getScxmlIfType_ScxmlCoreExecutablecontent(),
                ecorePackage.getEFeatureMapEntry(),
                "scxmlCoreExecutablecontent", null, 0, -1, ScxmlIfType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlIfType_Any(),
                ecorePackage.getEFeatureMapEntry(),
                "any", null, 0, -1, ScxmlIfType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlIfType_Raise(),
                this.getScxmlRaiseType(),
                null,
                "raise", null, 0, -1, ScxmlIfType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlIfType_If(),
                this.getScxmlIfType(),
                null,
                "if", null, 0, -1, ScxmlIfType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlIfType_Foreach(),
                this.getScxmlForeachType(),
                null,
                "foreach", null, 0, -1, ScxmlIfType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlIfType_Send(),
                this.getScxmlSendType(),
                null,
                "send", null, 0, -1, ScxmlIfType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlIfType_Script(),
                this.getScxmlScriptType(),
                null,
                "script", null, 0, -1, ScxmlIfType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlIfType_Assign(),
                this.getScxmlAssignType(),
                null,
                "assign", null, 0, -1, ScxmlIfType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlIfType_Log(),
                this.getScxmlLogType(),
                null,
                "log", null, 0, -1, ScxmlIfType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlIfType_Cancel(),
                this.getScxmlCancelType(),
                null,
                "cancel", null, 0, -1, ScxmlIfType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlIfType_Elseif(),
                this.getScxmlElseifType(),
                null,
                "elseif", null, 0, 1, ScxmlIfType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlIfType_ScxmlCoreExecutablecontent1(),
                ecorePackage.getEFeatureMapEntry(),
                "scxmlCoreExecutablecontent1", null, 0, -1, ScxmlIfType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlIfType_Any1(),
                ecorePackage.getEFeatureMapEntry(),
                "any1", null, 0, -1, ScxmlIfType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlIfType_Raise1(),
                this.getScxmlRaiseType(),
                null,
                "raise1", null, 0, -1, ScxmlIfType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlIfType_If1(),
                this.getScxmlIfType(),
                null,
                "if1", null, 0, -1, ScxmlIfType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlIfType_Foreach1(),
                this.getScxmlForeachType(),
                null,
                "foreach1", null, 0, -1, ScxmlIfType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlIfType_Send1(),
                this.getScxmlSendType(),
                null,
                "send1", null, 0, -1, ScxmlIfType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlIfType_Script1(),
                this.getScxmlScriptType(),
                null,
                "script1", null, 0, -1, ScxmlIfType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlIfType_Assign1(),
                this.getScxmlAssignType(),
                null,
                "assign1", null, 0, -1, ScxmlIfType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlIfType_Log1(),
                this.getScxmlLogType(),
                null,
                "log1", null, 0, -1, ScxmlIfType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlIfType_Cancel1(),
                this.getScxmlCancelType(),
                null,
                "cancel1", null, 0, -1, ScxmlIfType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlIfType_Else(),
                this.getScxmlElseType(),
                null,
                "else", null, 0, 1, ScxmlIfType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlIfType_ScxmlCoreExecutablecontent2(),
                ecorePackage.getEFeatureMapEntry(),
                "scxmlCoreExecutablecontent2", null, 0, -1, ScxmlIfType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlIfType_Any2(),
                ecorePackage.getEFeatureMapEntry(),
                "any2", null, 0, -1, ScxmlIfType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlIfType_Raise2(),
                this.getScxmlRaiseType(),
                null,
                "raise2", null, 0, -1, ScxmlIfType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlIfType_If2(),
                this.getScxmlIfType(),
                null,
                "if2", null, 0, -1, ScxmlIfType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlIfType_Foreach2(),
                this.getScxmlForeachType(),
                null,
                "foreach2", null, 0, -1, ScxmlIfType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlIfType_Send2(),
                this.getScxmlSendType(),
                null,
                "send2", null, 0, -1, ScxmlIfType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlIfType_Script2(),
                this.getScxmlScriptType(),
                null,
                "script2", null, 0, -1, ScxmlIfType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlIfType_Assign2(),
                this.getScxmlAssignType(),
                null,
                "assign2", null, 0, -1, ScxmlIfType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlIfType_Log2(),
                this.getScxmlLogType(),
                null,
                "log2", null, 0, -1, ScxmlIfType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlIfType_Cancel2(),
                this.getScxmlCancelType(),
                null,
                "cancel2", null, 0, -1, ScxmlIfType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlIfType_Cond(),
                this.getCondLangDatatype(),
                "cond", null, 1, 1, ScxmlIfType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlIfType_AnyAttribute(),
                ecorePackage.getEFeatureMapEntry(),
                "anyAttribute", null, 0, -1, ScxmlIfType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(scxmlInitialTypeEClass, ScxmlInitialType.class, "ScxmlInitialType", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getScxmlInitialType_ScxmlExtraContent(),
                ecorePackage.getEFeatureMapEntry(),
                "scxmlExtraContent", null, 0, -1, ScxmlInitialType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlInitialType_Any(),
                ecorePackage.getEFeatureMapEntry(),
                "any", null, 0, -1, ScxmlInitialType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlInitialType_Transition(),
                this.getScxmlTransitionType(),
                null,
                "transition", null, 1, 1, ScxmlInitialType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlInitialType_ScxmlExtraContent1(),
                ecorePackage.getEFeatureMapEntry(),
                "scxmlExtraContent1", null, 0, -1, ScxmlInitialType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlInitialType_Any1(),
                ecorePackage.getEFeatureMapEntry(),
                "any1", null, 0, -1, ScxmlInitialType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlInitialType_AnyAttribute(),
                ecorePackage.getEFeatureMapEntry(),
                "anyAttribute", null, 0, -1, ScxmlInitialType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(scxmlInvokeTypeEClass, ScxmlInvokeType.class, "ScxmlInvokeType", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getScxmlInvokeType_ScxmlInvokeMix(),
                ecorePackage.getEFeatureMapEntry(),
                "scxmlInvokeMix", null, 0, -1, ScxmlInvokeType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlInvokeType_Content(),
                this.getScxmlContentType(),
                null,
                "content", null, 0, -1, ScxmlInvokeType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlInvokeType_Param(),
                this.getScxmlParamType(),
                null,
                "param", null, 0, -1, ScxmlInvokeType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlInvokeType_Finalize(),
                this.getScxmlFinalizeType(),
                null,
                "finalize", null, 0, -1, ScxmlInvokeType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlInvokeType_Any(),
                ecorePackage.getEFeatureMapEntry(),
                "any", null, 0, -1, ScxmlInvokeType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlInvokeType_Autoforward(),
                this.getBooleanDatatype(),
                "autoforward", "false", 0, 1, ScxmlInvokeType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(
                getScxmlInvokeType_Id(),
                theXMLTypePackage.getID(),
                "id", null, 0, 1, ScxmlInvokeType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlInvokeType_Idlocation(),
                this.getLocLangDatatype(),
                "idlocation", null, 0, 1, ScxmlInvokeType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlInvokeType_Namelist(),
                theXMLTypePackage.getString(),
                "namelist", null, 0, 1, ScxmlInvokeType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlInvokeType_Src(),
                this.getURIDatatype(),
                "src", null, 0, 1, ScxmlInvokeType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlInvokeType_Srcexpr(),
                this.getValueLangDatatype(),
                "srcexpr", null, 0, 1, ScxmlInvokeType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlInvokeType_Type(),
                theXMLTypePackage.getString(),
                "type", "scxml", 0, 1, ScxmlInvokeType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(
                getScxmlInvokeType_Typeexpr(),
                this.getValueLangDatatype(),
                "typeexpr", null, 0, 1, ScxmlInvokeType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlInvokeType_AnyAttribute(),
                ecorePackage.getEFeatureMapEntry(),
                "anyAttribute", null, 0, -1, ScxmlInvokeType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(scxmlLogTypeEClass, ScxmlLogType.class, "ScxmlLogType", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getScxmlLogType_ScxmlExtraContent(),
                ecorePackage.getEFeatureMapEntry(),
                "scxmlExtraContent", null, 0, -1, ScxmlLogType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlLogType_Any(),
                ecorePackage.getEFeatureMapEntry(),
                "any", null, 0, -1, ScxmlLogType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlLogType_Expr(),
                this.getValueLangDatatype(),
                "expr", null, 0, 1, ScxmlLogType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlLogType_Label(),
                theXMLTypePackage.getString(),
                "label", null, 0, 1, ScxmlLogType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlLogType_AnyAttribute(),
                ecorePackage.getEFeatureMapEntry(),
                "anyAttribute", null, 0, -1, ScxmlLogType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(scxmlOnentryTypeEClass, ScxmlOnentryType.class, "ScxmlOnentryType", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getScxmlOnentryType_ScxmlCoreExecutablecontent(),
                ecorePackage.getEFeatureMapEntry(),
                "scxmlCoreExecutablecontent", null, 0, -1, ScxmlOnentryType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlOnentryType_Any(),
                ecorePackage.getEFeatureMapEntry(),
                "any", null, 0, -1, ScxmlOnentryType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlOnentryType_Raise(),
                this.getScxmlRaiseType(),
                null,
                "raise", null, 0, -1, ScxmlOnentryType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlOnentryType_If(),
                this.getScxmlIfType(),
                null,
                "if", null, 0, -1, ScxmlOnentryType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlOnentryType_Foreach(),
                this.getScxmlForeachType(),
                null,
                "foreach", null, 0, -1, ScxmlOnentryType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlOnentryType_Send(),
                this.getScxmlSendType(),
                null,
                "send", null, 0, -1, ScxmlOnentryType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlOnentryType_Script(),
                this.getScxmlScriptType(),
                null,
                "script", null, 0, -1, ScxmlOnentryType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlOnentryType_Assign(),
                this.getScxmlAssignType(),
                null,
                "assign", null, 0, -1, ScxmlOnentryType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlOnentryType_Log(),
                this.getScxmlLogType(),
                null,
                "log", null, 0, -1, ScxmlOnentryType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlOnentryType_Cancel(),
                this.getScxmlCancelType(),
                null,
                "cancel", null, 0, -1, ScxmlOnentryType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlOnentryType_AnyAttribute(),
                ecorePackage.getEFeatureMapEntry(),
                "anyAttribute", null, 0, -1, ScxmlOnentryType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(scxmlOnexitTypeEClass, ScxmlOnexitType.class, "ScxmlOnexitType", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getScxmlOnexitType_ScxmlCoreExecutablecontent(),
                ecorePackage.getEFeatureMapEntry(),
                "scxmlCoreExecutablecontent", null, 0, -1, ScxmlOnexitType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlOnexitType_Any(),
                ecorePackage.getEFeatureMapEntry(),
                "any", null, 0, -1, ScxmlOnexitType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlOnexitType_Raise(),
                this.getScxmlRaiseType(),
                null,
                "raise", null, 0, -1, ScxmlOnexitType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlOnexitType_If(),
                this.getScxmlIfType(),
                null,
                "if", null, 0, -1, ScxmlOnexitType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlOnexitType_Foreach(),
                this.getScxmlForeachType(),
                null,
                "foreach", null, 0, -1, ScxmlOnexitType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlOnexitType_Send(),
                this.getScxmlSendType(),
                null,
                "send", null, 0, -1, ScxmlOnexitType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlOnexitType_Script(),
                this.getScxmlScriptType(),
                null,
                "script", null, 0, -1, ScxmlOnexitType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlOnexitType_Assign(),
                this.getScxmlAssignType(),
                null,
                "assign", null, 0, -1, ScxmlOnexitType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlOnexitType_Log(),
                this.getScxmlLogType(),
                null,
                "log", null, 0, -1, ScxmlOnexitType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlOnexitType_Cancel(),
                this.getScxmlCancelType(),
                null,
                "cancel", null, 0, -1, ScxmlOnexitType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlOnexitType_AnyAttribute(),
                ecorePackage.getEFeatureMapEntry(),
                "anyAttribute", null, 0, -1, ScxmlOnexitType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(scxmlParallelTypeEClass, ScxmlParallelType.class, "ScxmlParallelType", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getScxmlParallelType_ScxmlParallelMix(),
                ecorePackage.getEFeatureMapEntry(),
                "scxmlParallelMix", null, 0, -1, ScxmlParallelType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlParallelType_Onentry(),
                this.getScxmlOnentryType(),
                null,
                "onentry", null, 0, -1, ScxmlParallelType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlParallelType_Onexit(),
                this.getScxmlOnexitType(),
                null,
                "onexit", null, 0, -1, ScxmlParallelType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlParallelType_Transition(),
                this.getScxmlTransitionType(),
                null,
                "transition", null, 0, -1, ScxmlParallelType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlParallelType_State(),
                this.getScxmlStateType(),
                null,
                "state", null, 0, -1, ScxmlParallelType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlParallelType_Parallel(),
                this.getScxmlParallelType(),
                null,
                "parallel", null, 0, -1, ScxmlParallelType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlParallelType_History(),
                this.getScxmlHistoryType(),
                null,
                "history", null, 0, -1, ScxmlParallelType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlParallelType_Datamodel(),
                this.getScxmlDatamodelType(),
                null,
                "datamodel", null, 0, -1, ScxmlParallelType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlParallelType_Invoke(),
                this.getScxmlInvokeType(),
                null,
                "invoke", null, 0, -1, ScxmlParallelType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlParallelType_Any(),
                ecorePackage.getEFeatureMapEntry(),
                "any", null, 0, -1, ScxmlParallelType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlParallelType_Id(),
                theXMLTypePackage.getID(),
                "id", null, 0, 1, ScxmlParallelType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlParallelType_AnyAttribute(),
                ecorePackage.getEFeatureMapEntry(),
                "anyAttribute", null, 0, -1, ScxmlParallelType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(scxmlParamTypeEClass, ScxmlParamType.class, "ScxmlParamType", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getScxmlParamType_ScxmlExtraContent(),
                ecorePackage.getEFeatureMapEntry(),
                "scxmlExtraContent", null, 0, -1, ScxmlParamType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlParamType_Any(),
                ecorePackage.getEFeatureMapEntry(),
                "any", null, 0, -1, ScxmlParamType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlParamType_Expr(),
                this.getValueLangDatatype(),
                "expr", null, 0, 1, ScxmlParamType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlParamType_Location(),
                this.getLocLangDatatype(),
                "location", null, 0, 1, ScxmlParamType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlParamType_Name(),
                theXMLTypePackage.getNMTOKEN(),
                "name", null, 1, 1, ScxmlParamType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlParamType_AnyAttribute(),
                ecorePackage.getEFeatureMapEntry(),
                "anyAttribute", null, 0, -1, ScxmlParamType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(scxmlRaiseTypeEClass, ScxmlRaiseType.class, "ScxmlRaiseType", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getScxmlRaiseType_Event(),
                theXMLTypePackage.getNMTOKEN(),
                "event", null, 1, 1, ScxmlRaiseType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlRaiseType_AnyAttribute(),
                ecorePackage.getEFeatureMapEntry(),
                "anyAttribute", null, 0, -1, ScxmlRaiseType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(scxmlScriptTypeEClass, ScxmlScriptType.class, "ScxmlScriptType", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getScxmlScriptType_Mixed(),
                ecorePackage.getEFeatureMapEntry(),
                "mixed", null, 0, -1, ScxmlScriptType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlScriptType_ScxmlExtraContent(),
                ecorePackage.getEFeatureMapEntry(),
                "scxmlExtraContent", null, 0, -1, ScxmlScriptType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlScriptType_Any(),
                ecorePackage.getEFeatureMapEntry(),
                "any", null, 0, -1, ScxmlScriptType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlScriptType_Src(),
                this.getURIDatatype(),
                "src", null, 0, 1, ScxmlScriptType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlScriptType_AnyAttribute(),
                ecorePackage.getEFeatureMapEntry(),
                "anyAttribute", null, 0, -1, ScxmlScriptType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(scxmlScxmlTypeEClass, ScxmlScxmlType.class, "ScxmlScxmlType", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getScxmlScxmlType_ScxmlScxmlMix(),
                ecorePackage.getEFeatureMapEntry(),
                "scxmlScxmlMix", null, 0, -1, ScxmlScxmlType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlScxmlType_State(),
                this.getScxmlStateType(),
                null,
                "state", null, 0, -1, ScxmlScxmlType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlScxmlType_Parallel(),
                this.getScxmlParallelType(),
                null,
                "parallel", null, 0, -1, ScxmlScxmlType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlScxmlType_Final(),
                this.getScxmlFinalType(),
                null,
                "final", null, 0, -1, ScxmlScxmlType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlScxmlType_Datamodel(),
                this.getScxmlDatamodelType(),
                null,
                "datamodel", null, 0, -1, ScxmlScxmlType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlScxmlType_Script(),
                this.getScxmlScriptType(),
                null,
                "script", null, 0, -1, ScxmlScxmlType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlScxmlType_Any(),
                ecorePackage.getEFeatureMapEntry(),
                "any", null, 0, -1, ScxmlScxmlType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlScxmlType_Binding(),
                this.getBindingDatatype(),
                "binding", null, 0, 1, ScxmlScxmlType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlScxmlType_Datamodel1(),
                theXMLTypePackage.getNMTOKEN(),
                "datamodel1", "null", 0, 1, ScxmlScxmlType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(
                getScxmlScxmlType_Exmode(),
                this.getExmodeDatatype(),
                "exmode", null, 0, 1, ScxmlScxmlType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlScxmlType_Initial(),
                theXMLTypePackage.getIDREFS(),
                "initial", null, 0, 1, ScxmlScxmlType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlScxmlType_Name(),
                theXMLTypePackage.getNMTOKEN(),
                "name", null, 0, 1, ScxmlScxmlType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlScxmlType_Version(),
                theXMLTypePackage.getDecimal(),
                "version", "1.0", 1, 1, ScxmlScxmlType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(
                getScxmlScxmlType_AnyAttribute(),
                ecorePackage.getEFeatureMapEntry(),
                "anyAttribute", null, 0, -1, ScxmlScxmlType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(scxmlSendTypeEClass, ScxmlSendType.class, "ScxmlSendType", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getScxmlSendType_ScxmlSendMix(),
                ecorePackage.getEFeatureMapEntry(),
                "scxmlSendMix", null, 0, -1, ScxmlSendType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlSendType_Content(),
                this.getScxmlContentType(),
                null,
                "content", null, 0, -1, ScxmlSendType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlSendType_Param(),
                this.getScxmlParamType(),
                null,
                "param", null, 0, -1, ScxmlSendType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlSendType_Any(),
                ecorePackage.getEFeatureMapEntry(),
                "any", null, 0, -1, ScxmlSendType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlSendType_Delay(),
                this.getDurationDatatype(),
                "delay", "0s", 0, 1, ScxmlSendType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(
                getScxmlSendType_Delayexpr(),
                this.getValueLangDatatype(),
                "delayexpr", null, 0, 1, ScxmlSendType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlSendType_Event(),
                this.getEventTypeDatatype(),
                "event", null, 0, 1, ScxmlSendType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlSendType_Eventexpr(),
                this.getValueLangDatatype(),
                "eventexpr", null, 0, 1, ScxmlSendType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlSendType_Id(),
                theXMLTypePackage.getID(),
                "id", null, 0, 1, ScxmlSendType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlSendType_Idlocation(),
                this.getLocLangDatatype(),
                "idlocation", null, 0, 1, ScxmlSendType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlSendType_Namelist(),
                theXMLTypePackage.getString(),
                "namelist", null, 0, 1, ScxmlSendType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlSendType_Target(),
                this.getURIDatatype(),
                "target", null, 0, 1, ScxmlSendType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlSendType_Targetexpr(),
                this.getValueLangDatatype(),
                "targetexpr", null, 0, 1, ScxmlSendType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlSendType_Type(),
                theXMLTypePackage.getString(),
                "type", "scxml", 0, 1, ScxmlSendType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(
                getScxmlSendType_Typeexpr(),
                this.getValueLangDatatype(),
                "typeexpr", null, 0, 1, ScxmlSendType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlSendType_AnyAttribute(),
                ecorePackage.getEFeatureMapEntry(),
                "anyAttribute", null, 0, -1, ScxmlSendType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(scxmlStateTypeEClass, ScxmlStateType.class, "ScxmlStateType", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getScxmlStateType_ScxmlStateMix(),
                ecorePackage.getEFeatureMapEntry(),
                "scxmlStateMix", null, 0, -1, ScxmlStateType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlStateType_Onentry(),
                this.getScxmlOnentryType(),
                null,
                "onentry", null, 0, -1, ScxmlStateType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlStateType_Onexit(),
                this.getScxmlOnexitType(),
                null,
                "onexit", null, 0, -1, ScxmlStateType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlStateType_Transition(),
                this.getScxmlTransitionType(),
                null,
                "transition", null, 0, -1, ScxmlStateType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlStateType_Initial(),
                this.getScxmlInitialType(),
                null,
                "initial", null, 0, -1, ScxmlStateType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlStateType_State(),
                this.getScxmlStateType(),
                null,
                "state", null, 0, -1, ScxmlStateType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlStateType_Parallel(),
                this.getScxmlParallelType(),
                null,
                "parallel", null, 0, -1, ScxmlStateType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlStateType_Final(),
                this.getScxmlFinalType(),
                null,
                "final", null, 0, -1, ScxmlStateType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlStateType_History(),
                this.getScxmlHistoryType(),
                null,
                "history", null, 0, -1, ScxmlStateType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlStateType_Datamodel(),
                this.getScxmlDatamodelType(),
                null,
                "datamodel", null, 0, -1, ScxmlStateType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlStateType_Invoke(),
                this.getScxmlInvokeType(),
                null,
                "invoke", null, 0, -1, ScxmlStateType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlStateType_Any(),
                ecorePackage.getEFeatureMapEntry(),
                "any", null, 0, -1, ScxmlStateType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlStateType_Id(),
                theXMLTypePackage.getID(),
                "id", null, 0, 1, ScxmlStateType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlStateType_Initial1(),
                theXMLTypePackage.getIDREFS(),
                "initial1", null, 0, 1, ScxmlStateType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlStateType_AnyAttribute(),
                ecorePackage.getEFeatureMapEntry(),
                "anyAttribute", null, 0, -1, ScxmlStateType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(scxmlTransitionTypeEClass, ScxmlTransitionType.class, "ScxmlTransitionType", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getScxmlTransitionType_ScxmlCoreExecutablecontent(),
                ecorePackage.getEFeatureMapEntry(),
                "scxmlCoreExecutablecontent", null, 0, -1, ScxmlTransitionType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlTransitionType_Any(),
                ecorePackage.getEFeatureMapEntry(),
                "any", null, 0, -1, ScxmlTransitionType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlTransitionType_Raise(),
                this.getScxmlRaiseType(),
                null,
                "raise", null, 0, -1, ScxmlTransitionType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlTransitionType_If(),
                this.getScxmlIfType(),
                null,
                "if", null, 0, -1, ScxmlTransitionType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlTransitionType_Foreach(),
                this.getScxmlForeachType(),
                null,
                "foreach", null, 0, -1, ScxmlTransitionType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlTransitionType_Send(),
                this.getScxmlSendType(),
                null,
                "send", null, 0, -1, ScxmlTransitionType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlTransitionType_Script(),
                this.getScxmlScriptType(),
                null,
                "script", null, 0, -1, ScxmlTransitionType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlTransitionType_Assign(),
                this.getScxmlAssignType(),
                null,
                "assign", null, 0, -1, ScxmlTransitionType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlTransitionType_Log(),
                this.getScxmlLogType(),
                null,
                "log", null, 0, -1, ScxmlTransitionType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getScxmlTransitionType_Cancel(),
                this.getScxmlCancelType(),
                null,
                "cancel", null, 0, -1, ScxmlTransitionType.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlTransitionType_Cond(),
                this.getCondLangDatatype(),
                "cond", null, 0, 1, ScxmlTransitionType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlTransitionType_Event(),
                this.getEventTypesDatatype(),
                "event", null, 0, 1, ScxmlTransitionType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlTransitionType_Target(),
                theXMLTypePackage.getIDREFS(),
                "target", null, 0, 1, ScxmlTransitionType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlTransitionType_Type(),
                this.getTransitionTypeDatatype(),
                "type", null, 0, 1, ScxmlTransitionType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getScxmlTransitionType_AnyAttribute(),
                ecorePackage.getEFeatureMapEntry(),
                "anyAttribute", null, 0, -1, ScxmlTransitionType.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        // Initialize enums and add enum literals
        initEEnum(assignTypeDatatypeEEnum, AssignTypeDatatype.class, "AssignTypeDatatype"); //$NON-NLS-1$
        addEEnumLiteral(assignTypeDatatypeEEnum, AssignTypeDatatype.REPLACECHILDREN);
        addEEnumLiteral(assignTypeDatatypeEEnum, AssignTypeDatatype.FIRSTCHILD);
        addEEnumLiteral(assignTypeDatatypeEEnum, AssignTypeDatatype.LASTCHILD);
        addEEnumLiteral(assignTypeDatatypeEEnum, AssignTypeDatatype.PREVIOUSSIBLING);
        addEEnumLiteral(assignTypeDatatypeEEnum, AssignTypeDatatype.NEXTSIBLING);
        addEEnumLiteral(assignTypeDatatypeEEnum, AssignTypeDatatype.REPLACE);
        addEEnumLiteral(assignTypeDatatypeEEnum, AssignTypeDatatype.DELETE);
        addEEnumLiteral(assignTypeDatatypeEEnum, AssignTypeDatatype.ADDATTRIBUTE);

        initEEnum(bindingDatatypeEEnum, BindingDatatype.class, "BindingDatatype"); //$NON-NLS-1$
        addEEnumLiteral(bindingDatatypeEEnum, BindingDatatype.EARLY);
        addEEnumLiteral(bindingDatatypeEEnum, BindingDatatype.LATE);

        initEEnum(booleanDatatypeEEnum, BooleanDatatype.class, "BooleanDatatype"); //$NON-NLS-1$
        addEEnumLiteral(booleanDatatypeEEnum, BooleanDatatype.TRUE);
        addEEnumLiteral(booleanDatatypeEEnum, BooleanDatatype.FALSE);

        initEEnum(exmodeDatatypeEEnum, ExmodeDatatype.class, "ExmodeDatatype"); //$NON-NLS-1$
        addEEnumLiteral(exmodeDatatypeEEnum, ExmodeDatatype.LAX);
        addEEnumLiteral(exmodeDatatypeEEnum, ExmodeDatatype.STRICT);

        initEEnum(historyTypeDatatypeEEnum, HistoryTypeDatatype.class, "HistoryTypeDatatype"); //$NON-NLS-1$
        addEEnumLiteral(historyTypeDatatypeEEnum, HistoryTypeDatatype.SHALLOW);
        addEEnumLiteral(historyTypeDatatypeEEnum, HistoryTypeDatatype.DEEP);

        initEEnum(transitionTypeDatatypeEEnum, TransitionTypeDatatype.class, "TransitionTypeDatatype"); //$NON-NLS-1$
        addEEnumLiteral(transitionTypeDatatypeEEnum, TransitionTypeDatatype.INTERNAL);
        addEEnumLiteral(transitionTypeDatatypeEEnum, TransitionTypeDatatype.EXTERNAL);

        // Initialize data types
        initEDataType(assignTypeDatatypeObjectEDataType, AssignTypeDatatype.class, "AssignTypeDatatypeObject", EPackageImpl.IS_SERIALIZABLE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEDataType(bindingDatatypeObjectEDataType, BindingDatatype.class, "BindingDatatypeObject", EPackageImpl.IS_SERIALIZABLE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEDataType(booleanDatatypeObjectEDataType, BooleanDatatype.class, "BooleanDatatypeObject", EPackageImpl.IS_SERIALIZABLE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEDataType(condLangDatatypeEDataType, String.class, "CondLangDatatype", EPackageImpl.IS_SERIALIZABLE, !EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEDataType(durationDatatypeEDataType, String.class, "DurationDatatype", EPackageImpl.IS_SERIALIZABLE, !EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEDataType(eventTypeDatatypeEDataType, String.class, "EventTypeDatatype", EPackageImpl.IS_SERIALIZABLE, !EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEDataType(eventTypesDatatypeEDataType, String.class, "EventTypesDatatype", EPackageImpl.IS_SERIALIZABLE, !EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEDataType(exmodeDatatypeObjectEDataType, ExmodeDatatype.class, "ExmodeDatatypeObject", EPackageImpl.IS_SERIALIZABLE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEDataType(historyTypeDatatypeObjectEDataType, HistoryTypeDatatype.class, "HistoryTypeDatatypeObject", EPackageImpl.IS_SERIALIZABLE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEDataType(integerDatatypeEDataType, BigInteger.class, "IntegerDatatype", EPackageImpl.IS_SERIALIZABLE, !EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEDataType(locLangDatatypeEDataType, String.class, "LocLangDatatype", EPackageImpl.IS_SERIALIZABLE, !EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEDataType(transitionTypeDatatypeObjectEDataType, TransitionTypeDatatype.class, "TransitionTypeDatatypeObject", EPackageImpl.IS_SERIALIZABLE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEDataType(uriDatatypeEDataType, String.class, "URIDatatype", EPackageImpl.IS_SERIALIZABLE, !EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEDataType(valueLangDatatypeEDataType, String.class, "ValueLangDatatype", EPackageImpl.IS_SERIALIZABLE, !EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        // Create resource
        createResource(ScxmlPackage.eNS_URI);

        // Create annotations
        // http:///org/eclipse/emf/ecore/util/ExtendedMetaData
        createExtendedMetaDataAnnotations();
    }

    /**
     * Initializes the annotations for
     * <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void createExtendedMetaDataAnnotations() {
        String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData"; //$NON-NLS-1$
        addAnnotation(assignTypeDatatypeEEnum, source, new String[] { "name", "AssignType.datatype" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(assignTypeDatatypeObjectEDataType, source, new String[] { "name", "AssignType.datatype:Object", //$NON-NLS-1$ //$NON-NLS-2$
                "baseType", "AssignType.datatype" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(bindingDatatypeEEnum, source, new String[] { "name", "Binding.datatype" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(bindingDatatypeObjectEDataType, source, new String[] { "name", "Binding.datatype:Object", //$NON-NLS-1$ //$NON-NLS-2$
                "baseType", "Binding.datatype" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(booleanDatatypeEEnum, source, new String[] { "name", "Boolean.datatype" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(booleanDatatypeObjectEDataType, source, new String[] { "name", "Boolean.datatype:Object", //$NON-NLS-1$ //$NON-NLS-2$
                "baseType", "Boolean.datatype" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(condLangDatatypeEDataType, source, new String[] { "name", "CondLang.datatype", //$NON-NLS-1$ //$NON-NLS-2$
                "baseType", "http://www.eclipse.org/emf/2003/XMLType#string" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(documentRootEClass, source, new String[] { "name", "", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "mixed" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDocumentRoot_Mixed(), source, new String[] { "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":mixed" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDocumentRoot_XMLNSPrefixMap(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "xmlns:prefix" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDocumentRoot_XSISchemaLocation(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "xsi:schemaLocation" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDocumentRoot_Assign(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "assign", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDocumentRoot_Cancel(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "cancel", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDocumentRoot_Content(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "content", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDocumentRoot_Data(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "data", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDocumentRoot_Datamodel(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "datamodel", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDocumentRoot_Donedata(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "donedata", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDocumentRoot_Else(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "else", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDocumentRoot_Elseif(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "elseif", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDocumentRoot_Final(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "final", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDocumentRoot_Finalize(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "finalize", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDocumentRoot_Foreach(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "foreach", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDocumentRoot_History(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "history", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDocumentRoot_If(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "if", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDocumentRoot_Initial(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "initial", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDocumentRoot_Invoke(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "invoke", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDocumentRoot_Log(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "log", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDocumentRoot_Onentry(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "onentry", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDocumentRoot_Onexit(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "onexit", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDocumentRoot_Parallel(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "parallel", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDocumentRoot_Param(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "param", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDocumentRoot_Raise(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "raise", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDocumentRoot_Script(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "script", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDocumentRoot_Scxml(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "scxml", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDocumentRoot_Send(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "send", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDocumentRoot_State(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "state", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDocumentRoot_Transition(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "transition", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(durationDatatypeEDataType, source, new String[] { "name", "Duration.datatype", //$NON-NLS-1$ //$NON-NLS-2$
                "baseType", "http://www.eclipse.org/emf/2003/XMLType#string", //$NON-NLS-1$ //$NON-NLS-2$
                "pattern", "\\d*(\\.\\d+)?(ms|s|m|h|d)" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(eventTypeDatatypeEDataType, source, new String[] { "name", "EventType.datatype", //$NON-NLS-1$ //$NON-NLS-2$
                "baseType", "http://www.eclipse.org/emf/2003/XMLType#token", //$NON-NLS-1$ //$NON-NLS-2$
                "pattern", "(\\i|\\d|\\-)+(\\.(\\i|\\d|\\-)+)*" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(eventTypesDatatypeEDataType, source, new String[] { "name", "EventTypes.datatype", //$NON-NLS-1$ //$NON-NLS-2$
                "baseType", "http://www.eclipse.org/emf/2003/XMLType#token", //$NON-NLS-1$ //$NON-NLS-2$
                "pattern", "\\.?\\*|(\\i|\\d|\\-)+(\\.(\\i|\\d|\\-)+)*(\\.\\*)?(\\s(\\i|\\d|\\-)+(\\.(\\i|\\d|\\-)+)*(\\.\\*)?)*" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(exmodeDatatypeEEnum, source, new String[] { "name", "Exmode.datatype" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(exmodeDatatypeObjectEDataType, source, new String[] { "name", "Exmode.datatype:Object", //$NON-NLS-1$ //$NON-NLS-2$
                "baseType", "Exmode.datatype" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(historyTypeDatatypeEEnum, source, new String[] { "name", "HistoryType.datatype" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(historyTypeDatatypeObjectEDataType, source, new String[] { "name", "HistoryType.datatype:Object", //$NON-NLS-1$ //$NON-NLS-2$
                "baseType", "HistoryType.datatype" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(integerDatatypeEDataType, source, new String[] { "name", "Integer.datatype", //$NON-NLS-1$ //$NON-NLS-2$
                "baseType", "http://www.eclipse.org/emf/2003/XMLType#nonNegativeInteger" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(locLangDatatypeEDataType, source, new String[] { "name", "LocLang.datatype", //$NON-NLS-1$ //$NON-NLS-2$
                "baseType", "http://www.eclipse.org/emf/2003/XMLType#string" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(scxmlAssignTypeEClass, source, new String[] { "name", "scxml.assign.type", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "mixed" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlAssignType_Mixed(), source, new String[] { "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":mixed" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlAssignType_Any(), source, new String[] { "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##any", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":1", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlAssignType_Attr(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "attr" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlAssignType_Expr(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "expr" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlAssignType_Location(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "location" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlAssignType_Type(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "type" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlAssignType_AnyAttribute(), source, new String[] { "kind", "attributeWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":6", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(scxmlCancelTypeEClass, source, new String[] { "name", "scxml.cancel.type", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlCancelType_ScxmlExtraContent(), source, new String[] { "kind", "group", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "ScxmlExtraContent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlCancelType_Any(), source, new String[] { "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":1", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlExtraContent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlCancelType_Sendid(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "sendid" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlCancelType_Sendidexpr(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "sendidexpr" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlCancelType_AnyAttribute(), source, new String[] { "kind", "attributeWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":4", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(scxmlContentTypeEClass, source, new String[] { "name", "scxml.content.type", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "mixed" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlContentType_Mixed(), source, new String[] { "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":mixed" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlContentType_Any(), source, new String[] { "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##any", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":1", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlContentType_Expr(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "expr" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlContentType_AnyAttribute(), source, new String[] { "kind", "attributeWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":3", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(scxmlDatamodelTypeEClass, source, new String[] { "name", "scxml.datamodel.type", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlDatamodelType_Data(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "data", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlDatamodelType_ScxmlExtraContent(), source, new String[] { "kind", "group", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "ScxmlExtraContent:1" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlDatamodelType_Any(), source, new String[] { "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":2", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlExtraContent:1" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlDatamodelType_AnyAttribute(), source, new String[] { "kind", "attributeWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":3", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(scxmlDataTypeEClass, source, new String[] { "name", "scxml.data.type", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "mixed" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlDataType_Mixed(), source, new String[] { "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":mixed" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlDataType_Any(), source, new String[] { "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##any", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":1", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlDataType_Expr(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "expr" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlDataType_Id(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlDataType_Src(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "src" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlDataType_AnyAttribute(), source, new String[] { "kind", "attributeWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":5", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(scxmlDonedataTypeEClass, source, new String[] { "name", "scxml.donedata.type", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlDonedataType_Content(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "content", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlDonedataType_Param(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "param", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlDonedataType_AnyAttribute(), source, new String[] { "kind", "attributeWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":2", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(scxmlElseifTypeEClass, source, new String[] { "name", "scxml.elseif.type", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlElseifType_Cond(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "cond" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlElseifType_AnyAttribute(), source, new String[] { "kind", "attributeWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":1", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(scxmlElseTypeEClass, source, new String[] { "name", "scxml.else.type", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlElseType_AnyAttribute(), source, new String[] { "kind", "attributeWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(scxmlFinalizeTypeEClass, source, new String[] { "name", "scxml.finalize.type", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlFinalizeType_ScxmlCoreExecutablecontent(), source, new String[] { "kind", "group", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlFinalizeType_Any(), source, new String[] { "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":1", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlFinalizeType_Raise(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "raise", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlFinalizeType_If(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "if", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlFinalizeType_Foreach(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "foreach", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlFinalizeType_Send(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "send", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlFinalizeType_Script(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "script", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlFinalizeType_Assign(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "assign", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlFinalizeType_Log(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "log", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlFinalizeType_Cancel(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "cancel", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlFinalizeType_AnyAttribute(), source, new String[] { "kind", "attributeWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":10", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(scxmlFinalTypeEClass, source, new String[] { "name", "scxml.final.type", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlFinalType_ScxmlFinalMix(), source, new String[] { "kind", "group", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "ScxmlFinalMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlFinalType_Onentry(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "onentry", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlFinalMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlFinalType_Onexit(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "onexit", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlFinalMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlFinalType_Donedata(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "donedata", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlFinalMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlFinalType_Any(), source, new String[] { "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":4", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlFinalMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlFinalType_Id(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlFinalType_AnyAttribute(), source, new String[] { "kind", "attributeWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":6", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(scxmlForeachTypeEClass, source, new String[] { "name", "scxml.foreach.type", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlForeachType_ScxmlCoreExecutablecontent(), source, new String[] { "kind", "group", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlForeachType_Any(), source, new String[] { "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":1", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlForeachType_Raise(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "raise", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlForeachType_If(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "if", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlForeachType_Foreach(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "foreach", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlForeachType_Send(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "send", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlForeachType_Script(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "script", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlForeachType_Assign(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "assign", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlForeachType_Log(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "log", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlForeachType_Cancel(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "cancel", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlForeachType_Array(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "array" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlForeachType_Index(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "index" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlForeachType_Item(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "item" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlForeachType_AnyAttribute(), source, new String[] { "kind", "attributeWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":13", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(scxmlHistoryTypeEClass, source, new String[] { "name", "scxml.history.type", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlHistoryType_ScxmlExtraContent(), source, new String[] { "kind", "group", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "ScxmlExtraContent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlHistoryType_Any(), source, new String[] { "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":1", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlExtraContent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlHistoryType_Transition(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "transition", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlHistoryType_ScxmlExtraContent1(), source, new String[] { "kind", "group", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "ScxmlExtraContent:3" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlHistoryType_Any1(), source, new String[] { "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":4", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlExtraContent:3" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlHistoryType_Id(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlHistoryType_Type(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "type" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlHistoryType_AnyAttribute(), source, new String[] { "kind", "attributeWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":7", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(scxmlIfTypeEClass, source, new String[] { "name", "scxml.if.type", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlIfType_ScxmlCoreExecutablecontent(), source, new String[] { "kind", "group", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlIfType_Any(), source, new String[] { "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":1", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlIfType_Raise(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "raise", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlIfType_If(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "if", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlIfType_Foreach(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "foreach", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlIfType_Send(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "send", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlIfType_Script(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "script", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlIfType_Assign(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "assign", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlIfType_Log(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "log", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlIfType_Cancel(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "cancel", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlIfType_Elseif(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "elseif", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlIfType_ScxmlCoreExecutablecontent1(), source, new String[] { "kind", "group", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "ScxmlCoreExecutablecontent:11" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlIfType_Any1(), source, new String[] { "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":12", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:11" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlIfType_Raise1(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "raise", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:11" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlIfType_If1(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "if", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:11" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlIfType_Foreach1(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "foreach", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:11" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlIfType_Send1(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "send", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:11" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlIfType_Script1(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "script", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:11" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlIfType_Assign1(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "assign", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:11" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlIfType_Log1(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "log", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:11" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlIfType_Cancel1(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "cancel", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:11" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlIfType_Else(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "else", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlIfType_ScxmlCoreExecutablecontent2(), source, new String[] { "kind", "group", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "ScxmlCoreExecutablecontent:22" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlIfType_Any2(), source, new String[] { "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":23", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:22" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlIfType_Raise2(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "raise", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:22" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlIfType_If2(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "if", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:22" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlIfType_Foreach2(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "foreach", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:22" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlIfType_Send2(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "send", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:22" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlIfType_Script2(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "script", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:22" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlIfType_Assign2(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "assign", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:22" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlIfType_Log2(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "log", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:22" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlIfType_Cancel2(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "cancel", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:22" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlIfType_Cond(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "cond" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlIfType_AnyAttribute(), source, new String[] { "kind", "attributeWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":33", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(scxmlInitialTypeEClass, source, new String[] { "name", "scxml.initial.type", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlInitialType_ScxmlExtraContent(), source, new String[] { "kind", "group", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "ScxmlExtraContent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlInitialType_Any(), source, new String[] { "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":1", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlExtraContent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlInitialType_Transition(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "transition", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlInitialType_ScxmlExtraContent1(), source, new String[] { "kind", "group", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "ScxmlExtraContent:3" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlInitialType_Any1(), source, new String[] { "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":4", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlExtraContent:3" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlInitialType_AnyAttribute(), source, new String[] { "kind", "attributeWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":5", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(scxmlInvokeTypeEClass, source, new String[] { "name", "scxml.invoke.type", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlInvokeType_ScxmlInvokeMix(), source, new String[] { "kind", "group", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "ScxmlInvokeMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlInvokeType_Content(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "content", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlInvokeMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlInvokeType_Param(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "param", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlInvokeMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlInvokeType_Finalize(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "finalize", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlInvokeMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlInvokeType_Any(), source, new String[] { "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":4", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlInvokeMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlInvokeType_Autoforward(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "autoforward" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlInvokeType_Id(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlInvokeType_Idlocation(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "idlocation" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlInvokeType_Namelist(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "namelist" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlInvokeType_Src(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "src" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlInvokeType_Srcexpr(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "srcexpr" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlInvokeType_Type(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "type" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlInvokeType_Typeexpr(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "typeexpr" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlInvokeType_AnyAttribute(), source, new String[] { "kind", "attributeWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":13", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(scxmlLogTypeEClass, source, new String[] { "name", "scxml.log.type", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlLogType_ScxmlExtraContent(), source, new String[] { "kind", "group", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "ScxmlExtraContent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlLogType_Any(), source, new String[] { "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":1", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlExtraContent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlLogType_Expr(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "expr" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlLogType_Label(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "label" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlLogType_AnyAttribute(), source, new String[] { "kind", "attributeWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":4", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(scxmlOnentryTypeEClass, source, new String[] { "name", "scxml.onentry.type", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlOnentryType_ScxmlCoreExecutablecontent(), source, new String[] { "kind", "group", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlOnentryType_Any(), source, new String[] { "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":1", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlOnentryType_Raise(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "raise", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlOnentryType_If(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "if", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlOnentryType_Foreach(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "foreach", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlOnentryType_Send(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "send", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlOnentryType_Script(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "script", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlOnentryType_Assign(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "assign", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlOnentryType_Log(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "log", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlOnentryType_Cancel(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "cancel", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlOnentryType_AnyAttribute(), source, new String[] { "kind", "attributeWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":10", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(scxmlOnexitTypeEClass, source, new String[] { "name", "scxml.onexit.type", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlOnexitType_ScxmlCoreExecutablecontent(), source, new String[] { "kind", "group", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlOnexitType_Any(), source, new String[] { "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":1", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlOnexitType_Raise(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "raise", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlOnexitType_If(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "if", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlOnexitType_Foreach(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "foreach", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlOnexitType_Send(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "send", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlOnexitType_Script(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "script", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlOnexitType_Assign(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "assign", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlOnexitType_Log(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "log", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlOnexitType_Cancel(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "cancel", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlOnexitType_AnyAttribute(), source, new String[] { "kind", "attributeWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":10", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(scxmlParallelTypeEClass, source, new String[] { "name", "scxml.parallel.type", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlParallelType_ScxmlParallelMix(), source, new String[] { "kind", "group", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "ScxmlParallelMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlParallelType_Onentry(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "onentry", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlParallelMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlParallelType_Onexit(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "onexit", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlParallelMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlParallelType_Transition(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "transition", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlParallelMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlParallelType_State(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "state", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlParallelMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlParallelType_Parallel(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "parallel", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlParallelMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlParallelType_History(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "history", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlParallelMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlParallelType_Datamodel(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "datamodel", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlParallelMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlParallelType_Invoke(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "invoke", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlParallelMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlParallelType_Any(), source, new String[] { "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":9", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlParallelMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlParallelType_Id(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlParallelType_AnyAttribute(), source, new String[] { "kind", "attributeWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":11", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(scxmlParamTypeEClass, source, new String[] { "name", "scxml.param.type", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlParamType_ScxmlExtraContent(), source, new String[] { "kind", "group", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "ScxmlExtraContent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlParamType_Any(), source, new String[] { "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":1", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlExtraContent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlParamType_Expr(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "expr" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlParamType_Location(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "location" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlParamType_Name(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "name" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlParamType_AnyAttribute(), source, new String[] { "kind", "attributeWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":5", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(scxmlRaiseTypeEClass, source, new String[] { "name", "scxml.raise.type", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlRaiseType_Event(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "event" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlRaiseType_AnyAttribute(), source, new String[] { "kind", "attributeWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":1", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(scxmlScriptTypeEClass, source, new String[] { "name", "scxml.script.type", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "mixed" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlScriptType_Mixed(), source, new String[] { "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":mixed" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlScriptType_ScxmlExtraContent(), source, new String[] { "kind", "group", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "ScxmlExtraContent:1" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlScriptType_Any(), source, new String[] { "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":2", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlExtraContent:1" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlScriptType_Src(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "src" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlScriptType_AnyAttribute(), source, new String[] { "kind", "attributeWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":4", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(scxmlScxmlTypeEClass, source, new String[] { "name", "scxml.scxml.type", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlScxmlType_ScxmlScxmlMix(), source, new String[] { "kind", "group", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "ScxmlScxmlMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlScxmlType_State(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "state", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlScxmlMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlScxmlType_Parallel(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "parallel", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlScxmlMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlScxmlType_Final(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "final", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlScxmlMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlScxmlType_Datamodel(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "datamodel", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlScxmlMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlScxmlType_Script(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "script", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlScxmlMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlScxmlType_Any(), source, new String[] { "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":6", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlScxmlMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlScxmlType_Binding(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "binding" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlScxmlType_Datamodel1(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "datamodel" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlScxmlType_Exmode(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "exmode" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlScxmlType_Initial(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "initial" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlScxmlType_Name(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "name" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlScxmlType_Version(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "version" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlScxmlType_AnyAttribute(), source, new String[] { "kind", "attributeWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":13", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(scxmlSendTypeEClass, source, new String[] { "name", "scxml.send.type", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlSendType_ScxmlSendMix(), source, new String[] { "kind", "group", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "ScxmlSendMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlSendType_Content(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "content", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlSendMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlSendType_Param(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "param", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlSendMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlSendType_Any(), source, new String[] { "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":3", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlSendMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlSendType_Delay(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "delay" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlSendType_Delayexpr(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "delayexpr" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlSendType_Event(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "event" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlSendType_Eventexpr(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "eventexpr" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlSendType_Id(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlSendType_Idlocation(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "idlocation" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlSendType_Namelist(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "namelist" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlSendType_Target(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "target" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlSendType_Targetexpr(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "targetexpr" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlSendType_Type(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "type" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlSendType_Typeexpr(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "typeexpr" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlSendType_AnyAttribute(), source, new String[] { "kind", "attributeWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":15", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(scxmlStateTypeEClass, source, new String[] { "name", "scxml.state.type", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlStateType_ScxmlStateMix(), source, new String[] { "kind", "group", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "ScxmlStateMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlStateType_Onentry(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "onentry", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlStateMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlStateType_Onexit(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "onexit", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlStateMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlStateType_Transition(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "transition", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlStateMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlStateType_Initial(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "initial", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlStateMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlStateType_State(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "state", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlStateMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlStateType_Parallel(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "parallel", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlStateMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlStateType_Final(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "final", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlStateMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlStateType_History(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "history", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlStateMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlStateType_Datamodel(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "datamodel", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlStateMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlStateType_Invoke(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "invoke", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlStateMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlStateType_Any(), source, new String[] { "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":11", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlStateMix:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlStateType_Id(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlStateType_Initial1(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "initial" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlStateType_AnyAttribute(), source, new String[] { "kind", "attributeWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":14", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(scxmlTransitionTypeEClass, source, new String[] { "name", "scxml.transition.type", //$NON-NLS-1$ //$NON-NLS-2$
                "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlTransitionType_ScxmlCoreExecutablecontent(), source, new String[] { "kind", "group", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlTransitionType_Any(), source, new String[] { "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":1", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlTransitionType_Raise(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "raise", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlTransitionType_If(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "if", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlTransitionType_Foreach(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "foreach", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlTransitionType_Send(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "send", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlTransitionType_Script(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "script", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlTransitionType_Assign(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "assign", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlTransitionType_Log(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "log", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlTransitionType_Cancel(), source, new String[] { "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "cancel", //$NON-NLS-1$ //$NON-NLS-2$
            "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
            "group", "#ScxmlCoreExecutablecontent:0" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlTransitionType_Cond(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "cond" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlTransitionType_Event(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "event" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlTransitionType_Target(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "target" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlTransitionType_Type(), source, new String[] { "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
            "name", "type" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getScxmlTransitionType_AnyAttribute(), source, new String[] { "kind", "attributeWildcard", //$NON-NLS-1$ //$NON-NLS-2$
            "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
            "name", ":14", //$NON-NLS-1$ //$NON-NLS-2$
            "processing", "lax" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(transitionTypeDatatypeEEnum, source, new String[] { "name", "TransitionType.datatype" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(transitionTypeDatatypeObjectEDataType, source, new String[] { "name", "TransitionType.datatype:Object", //$NON-NLS-1$ //$NON-NLS-2$
                "baseType", "TransitionType.datatype" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(uriDatatypeEDataType, source, new String[] { "name", "URI.datatype", //$NON-NLS-1$ //$NON-NLS-2$
                "baseType", "http://www.eclipse.org/emf/2003/XMLType#anyURI" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(valueLangDatatypeEDataType, source, new String[] { "name", "ValueLang.datatype", //$NON-NLS-1$ //$NON-NLS-2$
                "baseType", "http://www.eclipse.org/emf/2003/XMLType#string" //$NON-NLS-1$ //$NON-NLS-2$
        });
    }

} // ScxmlPackageImpl
