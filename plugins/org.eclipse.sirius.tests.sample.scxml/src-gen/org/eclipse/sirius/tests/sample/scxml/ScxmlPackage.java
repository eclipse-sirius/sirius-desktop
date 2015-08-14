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
package org.eclipse.sirius.tests.sample.scxml;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains
 * accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each operation of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc --> <!-- begin-model-doc -->
 *
 * This is the XML Schema data module for SCXML * datamodel * data * assign *
 * param * script * content The data module defines these elements and their
 * attributes.
 *
 *
 *
 * XML Schema datatypes for SCXML
 *
 * Defines containers for the SCXML datatypes, many of these imported from other
 * specifications and standards.
 *
 *
 *
 *
 * This is the XML Schema common attributes for SCXML
 *
 *
 *
 * XML Schema content models for SCXML * scxml.extra.content * content *
 * scxml.extra.attribs Defines SCXML shared content models.
 *
 *
 * <!-- end-model-doc -->
 *
 * @see org.eclipse.sirius.tests.sample.scxml.ScxmlFactory
 * @model kind="package"
 * @generated
 */
public interface ScxmlPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNAME = "scxml";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_URI = "http://www.w3.org/2005/07/scxml";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_PREFIX = "scxml";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    ScxmlPackage eINSTANCE = org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl.init();

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.scxml.impl.DocumentRootImpl
     * <em>Document Root</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.tests.sample.scxml.impl.DocumentRootImpl
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getDocumentRoot()
     * @generated
     */
    int DOCUMENT_ROOT = 0;

    /**
     * The feature id for the '<em><b>Mixed</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__MIXED = 0;

    /**
     * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

    /**
     * The feature id for the '<em><b>XSI Schema Location</b></em>' map. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

    /**
     * The feature id for the '<em><b>Assign</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__ASSIGN = 3;

    /**
     * The feature id for the '<em><b>Cancel</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__CANCEL = 4;

    /**
     * The feature id for the '<em><b>Content</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__CONTENT = 5;

    /**
     * The feature id for the '<em><b>Data</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__DATA = 6;

    /**
     * The feature id for the '<em><b>Datamodel</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__DATAMODEL = 7;

    /**
     * The feature id for the '<em><b>Donedata</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__DONEDATA = 8;

    /**
     * The feature id for the '<em><b>Else</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__ELSE = 9;

    /**
     * The feature id for the '<em><b>Elseif</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__ELSEIF = 10;

    /**
     * The feature id for the '<em><b>Final</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__FINAL = 11;

    /**
     * The feature id for the '<em><b>Finalize</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__FINALIZE = 12;

    /**
     * The feature id for the '<em><b>Foreach</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__FOREACH = 13;

    /**
     * The feature id for the '<em><b>History</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__HISTORY = 14;

    /**
     * The feature id for the '<em><b>If</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__IF = 15;

    /**
     * The feature id for the '<em><b>Initial</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__INITIAL = 16;

    /**
     * The feature id for the '<em><b>Invoke</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__INVOKE = 17;

    /**
     * The feature id for the '<em><b>Log</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__LOG = 18;

    /**
     * The feature id for the '<em><b>Onentry</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__ONENTRY = 19;

    /**
     * The feature id for the '<em><b>Onexit</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__ONEXIT = 20;

    /**
     * The feature id for the '<em><b>Parallel</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__PARALLEL = 21;

    /**
     * The feature id for the '<em><b>Param</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__PARAM = 22;

    /**
     * The feature id for the '<em><b>Raise</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__RAISE = 23;

    /**
     * The feature id for the '<em><b>Script</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__SCRIPT = 24;

    /**
     * The feature id for the '<em><b>Scxml</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__SCXML = 25;

    /**
     * The feature id for the '<em><b>Send</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__SEND = 26;

    /**
     * The feature id for the '<em><b>State</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__STATE = 27;

    /**
     * The feature id for the '<em><b>Transition</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__TRANSITION = 28;

    /**
     * The number of structural features of the '<em>Document Root</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT_FEATURE_COUNT = 29;

    /**
     * The number of operations of the '<em>Document Root</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlAssignTypeImpl
     * <em>Assign Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlAssignTypeImpl
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlAssignType()
     * @generated
     */
    int SCXML_ASSIGN_TYPE = 1;

    /**
     * The feature id for the '<em><b>Mixed</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ASSIGN_TYPE__MIXED = 0;

    /**
     * The feature id for the '<em><b>Any</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ASSIGN_TYPE__ANY = 1;

    /**
     * The feature id for the '<em><b>Attr</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ASSIGN_TYPE__ATTR = 2;

    /**
     * The feature id for the '<em><b>Expr</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ASSIGN_TYPE__EXPR = 3;

    /**
     * The feature id for the '<em><b>Location</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ASSIGN_TYPE__LOCATION = 4;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ASSIGN_TYPE__TYPE = 5;

    /**
     * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ASSIGN_TYPE__ANY_ATTRIBUTE = 6;

    /**
     * The number of structural features of the '<em>Assign Type</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ASSIGN_TYPE_FEATURE_COUNT = 7;

    /**
     * The number of operations of the '<em>Assign Type</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ASSIGN_TYPE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlCancelTypeImpl
     * <em>Cancel Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlCancelTypeImpl
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlCancelType()
     * @generated
     */
    int SCXML_CANCEL_TYPE = 2;

    /**
     * The feature id for the '<em><b>Scxml Extra Content</b></em>' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_CANCEL_TYPE__SCXML_EXTRA_CONTENT = 0;

    /**
     * The feature id for the '<em><b>Any</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_CANCEL_TYPE__ANY = 1;

    /**
     * The feature id for the '<em><b>Sendid</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_CANCEL_TYPE__SENDID = 2;

    /**
     * The feature id for the '<em><b>Sendidexpr</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_CANCEL_TYPE__SENDIDEXPR = 3;

    /**
     * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_CANCEL_TYPE__ANY_ATTRIBUTE = 4;

    /**
     * The number of structural features of the '<em>Cancel Type</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_CANCEL_TYPE_FEATURE_COUNT = 5;

    /**
     * The number of operations of the '<em>Cancel Type</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_CANCEL_TYPE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlContentTypeImpl
     * <em>Content Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlContentTypeImpl
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlContentType()
     * @generated
     */
    int SCXML_CONTENT_TYPE = 3;

    /**
     * The feature id for the '<em><b>Mixed</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_CONTENT_TYPE__MIXED = 0;

    /**
     * The feature id for the '<em><b>Any</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_CONTENT_TYPE__ANY = 1;

    /**
     * The feature id for the '<em><b>Expr</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_CONTENT_TYPE__EXPR = 2;

    /**
     * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_CONTENT_TYPE__ANY_ATTRIBUTE = 3;

    /**
     * The number of structural features of the '<em>Content Type</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_CONTENT_TYPE_FEATURE_COUNT = 4;

    /**
     * The number of operations of the '<em>Content Type</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_CONTENT_TYPE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlDatamodelTypeImpl
     * <em>Datamodel Type</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlDatamodelTypeImpl
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlDatamodelType()
     * @generated
     */
    int SCXML_DATAMODEL_TYPE = 4;

    /**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_DATAMODEL_TYPE__DATA = 0;

    /**
     * The feature id for the '<em><b>Scxml Extra Content</b></em>' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_DATAMODEL_TYPE__SCXML_EXTRA_CONTENT = 1;

    /**
     * The feature id for the '<em><b>Any</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_DATAMODEL_TYPE__ANY = 2;

    /**
     * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_DATAMODEL_TYPE__ANY_ATTRIBUTE = 3;

    /**
     * The number of structural features of the '<em>Datamodel Type</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_DATAMODEL_TYPE_FEATURE_COUNT = 4;

    /**
     * The number of operations of the '<em>Datamodel Type</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_DATAMODEL_TYPE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlDataTypeImpl
     * <em>Data Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlDataTypeImpl
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlDataType()
     * @generated
     */
    int SCXML_DATA_TYPE = 5;

    /**
     * The feature id for the '<em><b>Mixed</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_DATA_TYPE__MIXED = 0;

    /**
     * The feature id for the '<em><b>Any</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_DATA_TYPE__ANY = 1;

    /**
     * The feature id for the '<em><b>Expr</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_DATA_TYPE__EXPR = 2;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_DATA_TYPE__ID = 3;

    /**
     * The feature id for the '<em><b>Src</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_DATA_TYPE__SRC = 4;

    /**
     * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_DATA_TYPE__ANY_ATTRIBUTE = 5;

    /**
     * The number of structural features of the '<em>Data Type</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_DATA_TYPE_FEATURE_COUNT = 6;

    /**
     * The number of operations of the '<em>Data Type</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_DATA_TYPE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlDonedataTypeImpl
     * <em>Donedata Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlDonedataTypeImpl
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlDonedataType()
     * @generated
     */
    int SCXML_DONEDATA_TYPE = 6;

    /**
     * The feature id for the '<em><b>Content</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_DONEDATA_TYPE__CONTENT = 0;

    /**
     * The feature id for the '<em><b>Param</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_DONEDATA_TYPE__PARAM = 1;

    /**
     * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_DONEDATA_TYPE__ANY_ATTRIBUTE = 2;

    /**
     * The number of structural features of the '<em>Donedata Type</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_DONEDATA_TYPE_FEATURE_COUNT = 3;

    /**
     * The number of operations of the '<em>Donedata Type</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_DONEDATA_TYPE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlElseifTypeImpl
     * <em>Elseif Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlElseifTypeImpl
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlElseifType()
     * @generated
     */
    int SCXML_ELSEIF_TYPE = 7;

    /**
     * The feature id for the '<em><b>Cond</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ELSEIF_TYPE__COND = 0;

    /**
     * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ELSEIF_TYPE__ANY_ATTRIBUTE = 1;

    /**
     * The number of structural features of the '<em>Elseif Type</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ELSEIF_TYPE_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Elseif Type</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ELSEIF_TYPE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlElseTypeImpl
     * <em>Else Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlElseTypeImpl
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlElseType()
     * @generated
     */
    int SCXML_ELSE_TYPE = 8;

    /**
     * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ELSE_TYPE__ANY_ATTRIBUTE = 0;

    /**
     * The number of structural features of the '<em>Else Type</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ELSE_TYPE_FEATURE_COUNT = 1;

    /**
     * The number of operations of the '<em>Else Type</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ELSE_TYPE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlFinalizeTypeImpl
     * <em>Finalize Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlFinalizeTypeImpl
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlFinalizeType()
     * @generated
     */
    int SCXML_FINALIZE_TYPE = 9;

    /**
     * The feature id for the '<em><b>Scxml Core Executablecontent</b></em>'
     * attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_FINALIZE_TYPE__SCXML_CORE_EXECUTABLECONTENT = 0;

    /**
     * The feature id for the '<em><b>Any</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_FINALIZE_TYPE__ANY = 1;

    /**
     * The feature id for the '<em><b>Raise</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_FINALIZE_TYPE__RAISE = 2;

    /**
     * The feature id for the '<em><b>If</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_FINALIZE_TYPE__IF = 3;

    /**
     * The feature id for the '<em><b>Foreach</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_FINALIZE_TYPE__FOREACH = 4;

    /**
     * The feature id for the '<em><b>Send</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_FINALIZE_TYPE__SEND = 5;

    /**
     * The feature id for the '<em><b>Script</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_FINALIZE_TYPE__SCRIPT = 6;

    /**
     * The feature id for the '<em><b>Assign</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_FINALIZE_TYPE__ASSIGN = 7;

    /**
     * The feature id for the '<em><b>Log</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_FINALIZE_TYPE__LOG = 8;

    /**
     * The feature id for the '<em><b>Cancel</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_FINALIZE_TYPE__CANCEL = 9;

    /**
     * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_FINALIZE_TYPE__ANY_ATTRIBUTE = 10;

    /**
     * The number of structural features of the '<em>Finalize Type</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_FINALIZE_TYPE_FEATURE_COUNT = 11;

    /**
     * The number of operations of the '<em>Finalize Type</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_FINALIZE_TYPE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlFinalTypeImpl
     * <em>Final Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlFinalTypeImpl
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlFinalType()
     * @generated
     */
    int SCXML_FINAL_TYPE = 10;

    /**
     * The feature id for the '<em><b>Scxml Final Mix</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_FINAL_TYPE__SCXML_FINAL_MIX = 0;

    /**
     * The feature id for the '<em><b>Onentry</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_FINAL_TYPE__ONENTRY = 1;

    /**
     * The feature id for the '<em><b>Onexit</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_FINAL_TYPE__ONEXIT = 2;

    /**
     * The feature id for the '<em><b>Donedata</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_FINAL_TYPE__DONEDATA = 3;

    /**
     * The feature id for the '<em><b>Any</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_FINAL_TYPE__ANY = 4;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_FINAL_TYPE__ID = 5;

    /**
     * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_FINAL_TYPE__ANY_ATTRIBUTE = 6;

    /**
     * The number of structural features of the '<em>Final Type</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_FINAL_TYPE_FEATURE_COUNT = 7;

    /**
     * The number of operations of the '<em>Final Type</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_FINAL_TYPE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlForeachTypeImpl
     * <em>Foreach Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlForeachTypeImpl
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlForeachType()
     * @generated
     */
    int SCXML_FOREACH_TYPE = 11;

    /**
     * The feature id for the '<em><b>Scxml Core Executablecontent</b></em>'
     * attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_FOREACH_TYPE__SCXML_CORE_EXECUTABLECONTENT = 0;

    /**
     * The feature id for the '<em><b>Any</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_FOREACH_TYPE__ANY = 1;

    /**
     * The feature id for the '<em><b>Raise</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_FOREACH_TYPE__RAISE = 2;

    /**
     * The feature id for the '<em><b>If</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_FOREACH_TYPE__IF = 3;

    /**
     * The feature id for the '<em><b>Foreach</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_FOREACH_TYPE__FOREACH = 4;

    /**
     * The feature id for the '<em><b>Send</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_FOREACH_TYPE__SEND = 5;

    /**
     * The feature id for the '<em><b>Script</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_FOREACH_TYPE__SCRIPT = 6;

    /**
     * The feature id for the '<em><b>Assign</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_FOREACH_TYPE__ASSIGN = 7;

    /**
     * The feature id for the '<em><b>Log</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_FOREACH_TYPE__LOG = 8;

    /**
     * The feature id for the '<em><b>Cancel</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_FOREACH_TYPE__CANCEL = 9;

    /**
     * The feature id for the '<em><b>Array</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_FOREACH_TYPE__ARRAY = 10;

    /**
     * The feature id for the '<em><b>Index</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_FOREACH_TYPE__INDEX = 11;

    /**
     * The feature id for the '<em><b>Item</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_FOREACH_TYPE__ITEM = 12;

    /**
     * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_FOREACH_TYPE__ANY_ATTRIBUTE = 13;

    /**
     * The number of structural features of the '<em>Foreach Type</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_FOREACH_TYPE_FEATURE_COUNT = 14;

    /**
     * The number of operations of the '<em>Foreach Type</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_FOREACH_TYPE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlHistoryTypeImpl
     * <em>History Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlHistoryTypeImpl
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlHistoryType()
     * @generated
     */
    int SCXML_HISTORY_TYPE = 12;

    /**
     * The feature id for the '<em><b>Scxml Extra Content</b></em>' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_HISTORY_TYPE__SCXML_EXTRA_CONTENT = 0;

    /**
     * The feature id for the '<em><b>Any</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_HISTORY_TYPE__ANY = 1;

    /**
     * The feature id for the '<em><b>Transition</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_HISTORY_TYPE__TRANSITION = 2;

    /**
     * The feature id for the '<em><b>Scxml Extra Content1</b></em>' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_HISTORY_TYPE__SCXML_EXTRA_CONTENT1 = 3;

    /**
     * The feature id for the '<em><b>Any1</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_HISTORY_TYPE__ANY1 = 4;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_HISTORY_TYPE__ID = 5;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_HISTORY_TYPE__TYPE = 6;

    /**
     * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_HISTORY_TYPE__ANY_ATTRIBUTE = 7;

    /**
     * The number of structural features of the '<em>History Type</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_HISTORY_TYPE_FEATURE_COUNT = 8;

    /**
     * The number of operations of the '<em>History Type</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_HISTORY_TYPE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlIfTypeImpl
     * <em>If Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlIfTypeImpl
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlIfType()
     * @generated
     */
    int SCXML_IF_TYPE = 13;

    /**
     * The feature id for the '<em><b>Scxml Core Executablecontent</b></em>'
     * attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_IF_TYPE__SCXML_CORE_EXECUTABLECONTENT = 0;

    /**
     * The feature id for the '<em><b>Any</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_IF_TYPE__ANY = 1;

    /**
     * The feature id for the '<em><b>Raise</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_IF_TYPE__RAISE = 2;

    /**
     * The feature id for the '<em><b>If</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_IF_TYPE__IF = 3;

    /**
     * The feature id for the '<em><b>Foreach</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_IF_TYPE__FOREACH = 4;

    /**
     * The feature id for the '<em><b>Send</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_IF_TYPE__SEND = 5;

    /**
     * The feature id for the '<em><b>Script</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_IF_TYPE__SCRIPT = 6;

    /**
     * The feature id for the '<em><b>Assign</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_IF_TYPE__ASSIGN = 7;

    /**
     * The feature id for the '<em><b>Log</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_IF_TYPE__LOG = 8;

    /**
     * The feature id for the '<em><b>Cancel</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_IF_TYPE__CANCEL = 9;

    /**
     * The feature id for the '<em><b>Elseif</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_IF_TYPE__ELSEIF = 10;

    /**
     * The feature id for the '<em><b>Scxml Core Executablecontent1</b></em>'
     * attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_IF_TYPE__SCXML_CORE_EXECUTABLECONTENT1 = 11;

    /**
     * The feature id for the '<em><b>Any1</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_IF_TYPE__ANY1 = 12;

    /**
     * The feature id for the '<em><b>Raise1</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_IF_TYPE__RAISE1 = 13;

    /**
     * The feature id for the '<em><b>If1</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_IF_TYPE__IF1 = 14;

    /**
     * The feature id for the '<em><b>Foreach1</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_IF_TYPE__FOREACH1 = 15;

    /**
     * The feature id for the '<em><b>Send1</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_IF_TYPE__SEND1 = 16;

    /**
     * The feature id for the '<em><b>Script1</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_IF_TYPE__SCRIPT1 = 17;

    /**
     * The feature id for the '<em><b>Assign1</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_IF_TYPE__ASSIGN1 = 18;

    /**
     * The feature id for the '<em><b>Log1</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_IF_TYPE__LOG1 = 19;

    /**
     * The feature id for the '<em><b>Cancel1</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_IF_TYPE__CANCEL1 = 20;

    /**
     * The feature id for the '<em><b>Else</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_IF_TYPE__ELSE = 21;

    /**
     * The feature id for the '<em><b>Scxml Core Executablecontent2</b></em>'
     * attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_IF_TYPE__SCXML_CORE_EXECUTABLECONTENT2 = 22;

    /**
     * The feature id for the '<em><b>Any2</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_IF_TYPE__ANY2 = 23;

    /**
     * The feature id for the '<em><b>Raise2</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_IF_TYPE__RAISE2 = 24;

    /**
     * The feature id for the '<em><b>If2</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_IF_TYPE__IF2 = 25;

    /**
     * The feature id for the '<em><b>Foreach2</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_IF_TYPE__FOREACH2 = 26;

    /**
     * The feature id for the '<em><b>Send2</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_IF_TYPE__SEND2 = 27;

    /**
     * The feature id for the '<em><b>Script2</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_IF_TYPE__SCRIPT2 = 28;

    /**
     * The feature id for the '<em><b>Assign2</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_IF_TYPE__ASSIGN2 = 29;

    /**
     * The feature id for the '<em><b>Log2</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_IF_TYPE__LOG2 = 30;

    /**
     * The feature id for the '<em><b>Cancel2</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_IF_TYPE__CANCEL2 = 31;

    /**
     * The feature id for the '<em><b>Cond</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_IF_TYPE__COND = 32;

    /**
     * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_IF_TYPE__ANY_ATTRIBUTE = 33;

    /**
     * The number of structural features of the '<em>If Type</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_IF_TYPE_FEATURE_COUNT = 34;

    /**
     * The number of operations of the '<em>If Type</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_IF_TYPE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlInitialTypeImpl
     * <em>Initial Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlInitialTypeImpl
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlInitialType()
     * @generated
     */
    int SCXML_INITIAL_TYPE = 14;

    /**
     * The feature id for the '<em><b>Scxml Extra Content</b></em>' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_INITIAL_TYPE__SCXML_EXTRA_CONTENT = 0;

    /**
     * The feature id for the '<em><b>Any</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_INITIAL_TYPE__ANY = 1;

    /**
     * The feature id for the '<em><b>Transition</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_INITIAL_TYPE__TRANSITION = 2;

    /**
     * The feature id for the '<em><b>Scxml Extra Content1</b></em>' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_INITIAL_TYPE__SCXML_EXTRA_CONTENT1 = 3;

    /**
     * The feature id for the '<em><b>Any1</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_INITIAL_TYPE__ANY1 = 4;

    /**
     * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_INITIAL_TYPE__ANY_ATTRIBUTE = 5;

    /**
     * The number of structural features of the '<em>Initial Type</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_INITIAL_TYPE_FEATURE_COUNT = 6;

    /**
     * The number of operations of the '<em>Initial Type</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_INITIAL_TYPE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlInvokeTypeImpl
     * <em>Invoke Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlInvokeTypeImpl
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlInvokeType()
     * @generated
     */
    int SCXML_INVOKE_TYPE = 15;

    /**
     * The feature id for the '<em><b>Scxml Invoke Mix</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_INVOKE_TYPE__SCXML_INVOKE_MIX = 0;

    /**
     * The feature id for the '<em><b>Content</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_INVOKE_TYPE__CONTENT = 1;

    /**
     * The feature id for the '<em><b>Param</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_INVOKE_TYPE__PARAM = 2;

    /**
     * The feature id for the '<em><b>Finalize</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_INVOKE_TYPE__FINALIZE = 3;

    /**
     * The feature id for the '<em><b>Any</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_INVOKE_TYPE__ANY = 4;

    /**
     * The feature id for the '<em><b>Autoforward</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_INVOKE_TYPE__AUTOFORWARD = 5;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_INVOKE_TYPE__ID = 6;

    /**
     * The feature id for the '<em><b>Idlocation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_INVOKE_TYPE__IDLOCATION = 7;

    /**
     * The feature id for the '<em><b>Namelist</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_INVOKE_TYPE__NAMELIST = 8;

    /**
     * The feature id for the '<em><b>Src</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_INVOKE_TYPE__SRC = 9;

    /**
     * The feature id for the '<em><b>Srcexpr</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_INVOKE_TYPE__SRCEXPR = 10;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_INVOKE_TYPE__TYPE = 11;

    /**
     * The feature id for the '<em><b>Typeexpr</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_INVOKE_TYPE__TYPEEXPR = 12;

    /**
     * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_INVOKE_TYPE__ANY_ATTRIBUTE = 13;

    /**
     * The number of structural features of the '<em>Invoke Type</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_INVOKE_TYPE_FEATURE_COUNT = 14;

    /**
     * The number of operations of the '<em>Invoke Type</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_INVOKE_TYPE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlLogTypeImpl
     * <em>Log Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlLogTypeImpl
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlLogType()
     * @generated
     */
    int SCXML_LOG_TYPE = 16;

    /**
     * The feature id for the '<em><b>Scxml Extra Content</b></em>' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_LOG_TYPE__SCXML_EXTRA_CONTENT = 0;

    /**
     * The feature id for the '<em><b>Any</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_LOG_TYPE__ANY = 1;

    /**
     * The feature id for the '<em><b>Expr</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_LOG_TYPE__EXPR = 2;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_LOG_TYPE__LABEL = 3;

    /**
     * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_LOG_TYPE__ANY_ATTRIBUTE = 4;

    /**
     * The number of structural features of the '<em>Log Type</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_LOG_TYPE_FEATURE_COUNT = 5;

    /**
     * The number of operations of the '<em>Log Type</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_LOG_TYPE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlOnentryTypeImpl
     * <em>Onentry Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlOnentryTypeImpl
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlOnentryType()
     * @generated
     */
    int SCXML_ONENTRY_TYPE = 17;

    /**
     * The feature id for the '<em><b>Scxml Core Executablecontent</b></em>'
     * attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ONENTRY_TYPE__SCXML_CORE_EXECUTABLECONTENT = 0;

    /**
     * The feature id for the '<em><b>Any</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ONENTRY_TYPE__ANY = 1;

    /**
     * The feature id for the '<em><b>Raise</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ONENTRY_TYPE__RAISE = 2;

    /**
     * The feature id for the '<em><b>If</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ONENTRY_TYPE__IF = 3;

    /**
     * The feature id for the '<em><b>Foreach</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ONENTRY_TYPE__FOREACH = 4;

    /**
     * The feature id for the '<em><b>Send</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ONENTRY_TYPE__SEND = 5;

    /**
     * The feature id for the '<em><b>Script</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ONENTRY_TYPE__SCRIPT = 6;

    /**
     * The feature id for the '<em><b>Assign</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ONENTRY_TYPE__ASSIGN = 7;

    /**
     * The feature id for the '<em><b>Log</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ONENTRY_TYPE__LOG = 8;

    /**
     * The feature id for the '<em><b>Cancel</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ONENTRY_TYPE__CANCEL = 9;

    /**
     * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ONENTRY_TYPE__ANY_ATTRIBUTE = 10;

    /**
     * The number of structural features of the '<em>Onentry Type</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ONENTRY_TYPE_FEATURE_COUNT = 11;

    /**
     * The number of operations of the '<em>Onentry Type</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ONENTRY_TYPE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlOnexitTypeImpl
     * <em>Onexit Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlOnexitTypeImpl
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlOnexitType()
     * @generated
     */
    int SCXML_ONEXIT_TYPE = 18;

    /**
     * The feature id for the '<em><b>Scxml Core Executablecontent</b></em>'
     * attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ONEXIT_TYPE__SCXML_CORE_EXECUTABLECONTENT = 0;

    /**
     * The feature id for the '<em><b>Any</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ONEXIT_TYPE__ANY = 1;

    /**
     * The feature id for the '<em><b>Raise</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ONEXIT_TYPE__RAISE = 2;

    /**
     * The feature id for the '<em><b>If</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ONEXIT_TYPE__IF = 3;

    /**
     * The feature id for the '<em><b>Foreach</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ONEXIT_TYPE__FOREACH = 4;

    /**
     * The feature id for the '<em><b>Send</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ONEXIT_TYPE__SEND = 5;

    /**
     * The feature id for the '<em><b>Script</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ONEXIT_TYPE__SCRIPT = 6;

    /**
     * The feature id for the '<em><b>Assign</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ONEXIT_TYPE__ASSIGN = 7;

    /**
     * The feature id for the '<em><b>Log</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ONEXIT_TYPE__LOG = 8;

    /**
     * The feature id for the '<em><b>Cancel</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ONEXIT_TYPE__CANCEL = 9;

    /**
     * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ONEXIT_TYPE__ANY_ATTRIBUTE = 10;

    /**
     * The number of structural features of the '<em>Onexit Type</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ONEXIT_TYPE_FEATURE_COUNT = 11;

    /**
     * The number of operations of the '<em>Onexit Type</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_ONEXIT_TYPE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlParallelTypeImpl
     * <em>Parallel Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlParallelTypeImpl
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlParallelType()
     * @generated
     */
    int SCXML_PARALLEL_TYPE = 19;

    /**
     * The feature id for the '<em><b>Scxml Parallel Mix</b></em>' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_PARALLEL_TYPE__SCXML_PARALLEL_MIX = 0;

    /**
     * The feature id for the '<em><b>Onentry</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_PARALLEL_TYPE__ONENTRY = 1;

    /**
     * The feature id for the '<em><b>Onexit</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_PARALLEL_TYPE__ONEXIT = 2;

    /**
     * The feature id for the '<em><b>Transition</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_PARALLEL_TYPE__TRANSITION = 3;

    /**
     * The feature id for the '<em><b>State</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_PARALLEL_TYPE__STATE = 4;

    /**
     * The feature id for the '<em><b>Parallel</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_PARALLEL_TYPE__PARALLEL = 5;

    /**
     * The feature id for the '<em><b>History</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_PARALLEL_TYPE__HISTORY = 6;

    /**
     * The feature id for the '<em><b>Datamodel</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_PARALLEL_TYPE__DATAMODEL = 7;

    /**
     * The feature id for the '<em><b>Invoke</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_PARALLEL_TYPE__INVOKE = 8;

    /**
     * The feature id for the '<em><b>Any</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_PARALLEL_TYPE__ANY = 9;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_PARALLEL_TYPE__ID = 10;

    /**
     * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_PARALLEL_TYPE__ANY_ATTRIBUTE = 11;

    /**
     * The number of structural features of the '<em>Parallel Type</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_PARALLEL_TYPE_FEATURE_COUNT = 12;

    /**
     * The number of operations of the '<em>Parallel Type</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_PARALLEL_TYPE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlParamTypeImpl
     * <em>Param Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlParamTypeImpl
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlParamType()
     * @generated
     */
    int SCXML_PARAM_TYPE = 20;

    /**
     * The feature id for the '<em><b>Scxml Extra Content</b></em>' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_PARAM_TYPE__SCXML_EXTRA_CONTENT = 0;

    /**
     * The feature id for the '<em><b>Any</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_PARAM_TYPE__ANY = 1;

    /**
     * The feature id for the '<em><b>Expr</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_PARAM_TYPE__EXPR = 2;

    /**
     * The feature id for the '<em><b>Location</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_PARAM_TYPE__LOCATION = 3;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_PARAM_TYPE__NAME = 4;

    /**
     * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_PARAM_TYPE__ANY_ATTRIBUTE = 5;

    /**
     * The number of structural features of the '<em>Param Type</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_PARAM_TYPE_FEATURE_COUNT = 6;

    /**
     * The number of operations of the '<em>Param Type</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_PARAM_TYPE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlRaiseTypeImpl
     * <em>Raise Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlRaiseTypeImpl
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlRaiseType()
     * @generated
     */
    int SCXML_RAISE_TYPE = 21;

    /**
     * The feature id for the '<em><b>Event</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_RAISE_TYPE__EVENT = 0;

    /**
     * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_RAISE_TYPE__ANY_ATTRIBUTE = 1;

    /**
     * The number of structural features of the '<em>Raise Type</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_RAISE_TYPE_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Raise Type</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_RAISE_TYPE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlScriptTypeImpl
     * <em>Script Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlScriptTypeImpl
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlScriptType()
     * @generated
     */
    int SCXML_SCRIPT_TYPE = 22;

    /**
     * The feature id for the '<em><b>Mixed</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SCRIPT_TYPE__MIXED = 0;

    /**
     * The feature id for the '<em><b>Scxml Extra Content</b></em>' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SCRIPT_TYPE__SCXML_EXTRA_CONTENT = 1;

    /**
     * The feature id for the '<em><b>Any</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SCRIPT_TYPE__ANY = 2;

    /**
     * The feature id for the '<em><b>Src</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SCRIPT_TYPE__SRC = 3;

    /**
     * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SCRIPT_TYPE__ANY_ATTRIBUTE = 4;

    /**
     * The number of structural features of the '<em>Script Type</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SCRIPT_TYPE_FEATURE_COUNT = 5;

    /**
     * The number of operations of the '<em>Script Type</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SCRIPT_TYPE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlScxmlTypeImpl
     * <em>Scxml Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlScxmlTypeImpl
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlScxmlType()
     * @generated
     */
    int SCXML_SCXML_TYPE = 23;

    /**
     * The feature id for the '<em><b>Scxml Scxml Mix</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SCXML_TYPE__SCXML_SCXML_MIX = 0;

    /**
     * The feature id for the '<em><b>State</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SCXML_TYPE__STATE = 1;

    /**
     * The feature id for the '<em><b>Parallel</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SCXML_TYPE__PARALLEL = 2;

    /**
     * The feature id for the '<em><b>Final</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SCXML_TYPE__FINAL = 3;

    /**
     * The feature id for the '<em><b>Datamodel</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SCXML_TYPE__DATAMODEL = 4;

    /**
     * The feature id for the '<em><b>Script</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SCXML_TYPE__SCRIPT = 5;

    /**
     * The feature id for the '<em><b>Any</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SCXML_TYPE__ANY = 6;

    /**
     * The feature id for the '<em><b>Binding</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SCXML_TYPE__BINDING = 7;

    /**
     * The feature id for the '<em><b>Datamodel1</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SCXML_TYPE__DATAMODEL1 = 8;

    /**
     * The feature id for the '<em><b>Exmode</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SCXML_TYPE__EXMODE = 9;

    /**
     * The feature id for the '<em><b>Initial</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SCXML_TYPE__INITIAL = 10;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SCXML_TYPE__NAME = 11;

    /**
     * The feature id for the '<em><b>Version</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SCXML_TYPE__VERSION = 12;

    /**
     * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SCXML_TYPE__ANY_ATTRIBUTE = 13;

    /**
     * The number of structural features of the '<em>Scxml Type</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SCXML_TYPE_FEATURE_COUNT = 14;

    /**
     * The number of operations of the '<em>Scxml Type</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SCXML_TYPE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlSendTypeImpl
     * <em>Send Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlSendTypeImpl
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlSendType()
     * @generated
     */
    int SCXML_SEND_TYPE = 24;

    /**
     * The feature id for the '<em><b>Scxml Send Mix</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SEND_TYPE__SCXML_SEND_MIX = 0;

    /**
     * The feature id for the '<em><b>Content</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SEND_TYPE__CONTENT = 1;

    /**
     * The feature id for the '<em><b>Param</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SEND_TYPE__PARAM = 2;

    /**
     * The feature id for the '<em><b>Any</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SEND_TYPE__ANY = 3;

    /**
     * The feature id for the '<em><b>Delay</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SEND_TYPE__DELAY = 4;

    /**
     * The feature id for the '<em><b>Delayexpr</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SEND_TYPE__DELAYEXPR = 5;

    /**
     * The feature id for the '<em><b>Event</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SEND_TYPE__EVENT = 6;

    /**
     * The feature id for the '<em><b>Eventexpr</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SEND_TYPE__EVENTEXPR = 7;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SEND_TYPE__ID = 8;

    /**
     * The feature id for the '<em><b>Idlocation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SEND_TYPE__IDLOCATION = 9;

    /**
     * The feature id for the '<em><b>Namelist</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SEND_TYPE__NAMELIST = 10;

    /**
     * The feature id for the '<em><b>Target</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SEND_TYPE__TARGET = 11;

    /**
     * The feature id for the '<em><b>Targetexpr</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SEND_TYPE__TARGETEXPR = 12;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SEND_TYPE__TYPE = 13;

    /**
     * The feature id for the '<em><b>Typeexpr</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SEND_TYPE__TYPEEXPR = 14;

    /**
     * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SEND_TYPE__ANY_ATTRIBUTE = 15;

    /**
     * The number of structural features of the '<em>Send Type</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SEND_TYPE_FEATURE_COUNT = 16;

    /**
     * The number of operations of the '<em>Send Type</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_SEND_TYPE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlStateTypeImpl
     * <em>State Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlStateTypeImpl
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlStateType()
     * @generated
     */
    int SCXML_STATE_TYPE = 25;

    /**
     * The feature id for the '<em><b>Scxml State Mix</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_STATE_TYPE__SCXML_STATE_MIX = 0;

    /**
     * The feature id for the '<em><b>Onentry</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_STATE_TYPE__ONENTRY = 1;

    /**
     * The feature id for the '<em><b>Onexit</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_STATE_TYPE__ONEXIT = 2;

    /**
     * The feature id for the '<em><b>Transition</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_STATE_TYPE__TRANSITION = 3;

    /**
     * The feature id for the '<em><b>Initial</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_STATE_TYPE__INITIAL = 4;

    /**
     * The feature id for the '<em><b>State</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_STATE_TYPE__STATE = 5;

    /**
     * The feature id for the '<em><b>Parallel</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_STATE_TYPE__PARALLEL = 6;

    /**
     * The feature id for the '<em><b>Final</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_STATE_TYPE__FINAL = 7;

    /**
     * The feature id for the '<em><b>History</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_STATE_TYPE__HISTORY = 8;

    /**
     * The feature id for the '<em><b>Datamodel</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_STATE_TYPE__DATAMODEL = 9;

    /**
     * The feature id for the '<em><b>Invoke</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_STATE_TYPE__INVOKE = 10;

    /**
     * The feature id for the '<em><b>Any</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_STATE_TYPE__ANY = 11;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_STATE_TYPE__ID = 12;

    /**
     * The feature id for the '<em><b>Initial1</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_STATE_TYPE__INITIAL1 = 13;

    /**
     * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_STATE_TYPE__ANY_ATTRIBUTE = 14;

    /**
     * The number of structural features of the '<em>State Type</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_STATE_TYPE_FEATURE_COUNT = 15;

    /**
     * The number of operations of the '<em>State Type</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_STATE_TYPE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlTransitionTypeImpl
     * <em>Transition Type</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlTransitionTypeImpl
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlTransitionType()
     * @generated
     */
    int SCXML_TRANSITION_TYPE = 26;

    /**
     * The feature id for the '<em><b>Scxml Core Executablecontent</b></em>'
     * attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_TRANSITION_TYPE__SCXML_CORE_EXECUTABLECONTENT = 0;

    /**
     * The feature id for the '<em><b>Any</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_TRANSITION_TYPE__ANY = 1;

    /**
     * The feature id for the '<em><b>Raise</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_TRANSITION_TYPE__RAISE = 2;

    /**
     * The feature id for the '<em><b>If</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_TRANSITION_TYPE__IF = 3;

    /**
     * The feature id for the '<em><b>Foreach</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_TRANSITION_TYPE__FOREACH = 4;

    /**
     * The feature id for the '<em><b>Send</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_TRANSITION_TYPE__SEND = 5;

    /**
     * The feature id for the '<em><b>Script</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_TRANSITION_TYPE__SCRIPT = 6;

    /**
     * The feature id for the '<em><b>Assign</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_TRANSITION_TYPE__ASSIGN = 7;

    /**
     * The feature id for the '<em><b>Log</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_TRANSITION_TYPE__LOG = 8;

    /**
     * The feature id for the '<em><b>Cancel</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_TRANSITION_TYPE__CANCEL = 9;

    /**
     * The feature id for the '<em><b>Cond</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_TRANSITION_TYPE__COND = 10;

    /**
     * The feature id for the '<em><b>Event</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_TRANSITION_TYPE__EVENT = 11;

    /**
     * The feature id for the '<em><b>Target</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_TRANSITION_TYPE__TARGET = 12;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_TRANSITION_TYPE__TYPE = 13;

    /**
     * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_TRANSITION_TYPE__ANY_ATTRIBUTE = 14;

    /**
     * The number of structural features of the '<em>Transition Type</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_TRANSITION_TYPE_FEATURE_COUNT = 15;

    /**
     * The number of operations of the '<em>Transition Type</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SCXML_TRANSITION_TYPE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.scxml.AssignTypeDatatype
     * <em>Assign Type Datatype</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.scxml.AssignTypeDatatype
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getAssignTypeDatatype()
     * @generated
     */
    int ASSIGN_TYPE_DATATYPE = 27;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.scxml.BindingDatatype
     * <em>Binding Datatype</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.scxml.BindingDatatype
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getBindingDatatype()
     * @generated
     */
    int BINDING_DATATYPE = 28;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.scxml.BooleanDatatype
     * <em>Boolean Datatype</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.scxml.BooleanDatatype
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getBooleanDatatype()
     * @generated
     */
    int BOOLEAN_DATATYPE = 29;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ExmodeDatatype
     * <em>Exmode Datatype</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.scxml.ExmodeDatatype
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getExmodeDatatype()
     * @generated
     */
    int EXMODE_DATATYPE = 30;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.scxml.HistoryTypeDatatype
     * <em>History Type Datatype</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.scxml.HistoryTypeDatatype
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getHistoryTypeDatatype()
     * @generated
     */
    int HISTORY_TYPE_DATATYPE = 31;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.scxml.TransitionTypeDatatype
     * <em>Transition Type Datatype</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.scxml.TransitionTypeDatatype
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getTransitionTypeDatatype()
     * @generated
     */
    int TRANSITION_TYPE_DATATYPE = 32;

    /**
     * The meta object id for the '<em>Assign Type Datatype Object</em>' data
     * type. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.scxml.AssignTypeDatatype
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getAssignTypeDatatypeObject()
     * @generated
     */
    int ASSIGN_TYPE_DATATYPE_OBJECT = 33;

    /**
     * The meta object id for the '<em>Binding Datatype Object</em>' data type.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.scxml.BindingDatatype
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getBindingDatatypeObject()
     * @generated
     */
    int BINDING_DATATYPE_OBJECT = 34;

    /**
     * The meta object id for the '<em>Boolean Datatype Object</em>' data type.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.scxml.BooleanDatatype
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getBooleanDatatypeObject()
     * @generated
     */
    int BOOLEAN_DATATYPE_OBJECT = 35;

    /**
     * The meta object id for the '<em>Cond Lang Datatype</em>' data type. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see java.lang.String
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getCondLangDatatype()
     * @generated
     */
    int COND_LANG_DATATYPE = 36;

    /**
     * The meta object id for the '<em>Duration Datatype</em>' data type. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see java.lang.String
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getDurationDatatype()
     * @generated
     */
    int DURATION_DATATYPE = 37;

    /**
     * The meta object id for the '<em>Event Type Datatype</em>' data type. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see java.lang.String
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getEventTypeDatatype()
     * @generated
     */
    int EVENT_TYPE_DATATYPE = 38;

    /**
     * The meta object id for the '<em>Event Types Datatype</em>' data type.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see java.lang.String
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getEventTypesDatatype()
     * @generated
     */
    int EVENT_TYPES_DATATYPE = 39;

    /**
     * The meta object id for the '<em>Exmode Datatype Object</em>' data type.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.scxml.ExmodeDatatype
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getExmodeDatatypeObject()
     * @generated
     */
    int EXMODE_DATATYPE_OBJECT = 40;

    /**
     * The meta object id for the '<em>History Type Datatype Object</em>' data
     * type. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.scxml.HistoryTypeDatatype
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getHistoryTypeDatatypeObject()
     * @generated
     */
    int HISTORY_TYPE_DATATYPE_OBJECT = 41;

    /**
     * The meta object id for the '<em>Integer Datatype</em>' data type. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see java.math.BigInteger
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getIntegerDatatype()
     * @generated
     */
    int INTEGER_DATATYPE = 42;

    /**
     * The meta object id for the '<em>Loc Lang Datatype</em>' data type. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see java.lang.String
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getLocLangDatatype()
     * @generated
     */
    int LOC_LANG_DATATYPE = 43;

    /**
     * The meta object id for the '<em>Transition Type Datatype Object</em>'
     * data type. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.scxml.TransitionTypeDatatype
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getTransitionTypeDatatypeObject()
     * @generated
     */
    int TRANSITION_TYPE_DATATYPE_OBJECT = 44;

    /**
     * The meta object id for the '<em>URI Datatype</em>' data type. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see java.lang.String
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getURIDatatype()
     * @generated
     */
    int URI_DATATYPE = 45;

    /**
     * The meta object id for the '<em>Value Lang Datatype</em>' data type. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see java.lang.String
     * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getValueLangDatatype()
     * @generated
     */
    int VALUE_LANG_DATATYPE = 46;

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot
     * <em>Document Root</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Document Root</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.DocumentRoot
     * @generated
     */
    EClass getDocumentRoot();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getMixed
     * <em>Mixed</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Mixed</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getMixed()
     * @see #getDocumentRoot()
     * @generated
     */
    EAttribute getDocumentRoot_Mixed();

    /**
     * Returns the meta object for the map '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getXMLNSPrefixMap
     * <em>XMLNS Prefix Map</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getXMLNSPrefixMap()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_XMLNSPrefixMap();

    /**
     * Returns the meta object for the map '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getXSISchemaLocation
     * <em>XSI Schema Location</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the map '<em>XSI Schema Location</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getXSISchemaLocation()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_XSISchemaLocation();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getAssign
     * <em>Assign</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Assign</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getAssign()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Assign();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getCancel
     * <em>Cancel</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Cancel</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getCancel()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Cancel();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getContent
     * <em>Content</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Content</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getContent()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Content();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getData
     * <em>Data</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Data</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getData()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Data();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getDatamodel
     * <em>Datamodel</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Datamodel</em>
     *         '.
     * @see org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getDatamodel()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Datamodel();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getDonedata
     * <em>Donedata</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Donedata</em>
     *         '.
     * @see org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getDonedata()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Donedata();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getElse
     * <em>Else</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Else</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getElse()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Else();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getElseif
     * <em>Elseif</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Elseif</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getElseif()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Elseif();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getFinal
     * <em>Final</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Final</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getFinal()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Final();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getFinalize
     * <em>Finalize</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Finalize</em>
     *         '.
     * @see org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getFinalize()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Finalize();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getForeach
     * <em>Foreach</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Foreach</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getForeach()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Foreach();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getHistory
     * <em>History</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>History</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getHistory()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_History();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getIf
     * <em>If</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>If</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getIf()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_If();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getInitial
     * <em>Initial</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Initial</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getInitial()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Initial();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getInvoke
     * <em>Invoke</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Invoke</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getInvoke()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Invoke();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getLog
     * <em>Log</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Log</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getLog()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Log();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getOnentry
     * <em>Onentry</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Onentry</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getOnentry()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Onentry();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getOnexit
     * <em>Onexit</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Onexit</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getOnexit()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Onexit();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getParallel
     * <em>Parallel</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Parallel</em>
     *         '.
     * @see org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getParallel()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Parallel();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getParam
     * <em>Param</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Param</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getParam()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Param();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getRaise
     * <em>Raise</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Raise</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getRaise()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Raise();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getScript
     * <em>Script</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Script</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getScript()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Script();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getScxml
     * <em>Scxml</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Scxml</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getScxml()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Scxml();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getSend
     * <em>Send</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Send</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getSend()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Send();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getState
     * <em>State</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>State</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getState()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_State();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getTransition
     * <em>Transition</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '
     *         <em>Transition</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getTransition()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Transition();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlAssignType
     * <em>Assign Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Assign Type</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlAssignType
     * @generated
     */
    EClass getScxmlAssignType();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlAssignType#getMixed
     * <em>Mixed</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Mixed</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlAssignType#getMixed()
     * @see #getScxmlAssignType()
     * @generated
     */
    EAttribute getScxmlAssignType_Mixed();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlAssignType#getAny
     * <em>Any</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlAssignType#getAny()
     * @see #getScxmlAssignType()
     * @generated
     */
    EAttribute getScxmlAssignType_Any();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlAssignType#getAttr
     * <em>Attr</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Attr</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlAssignType#getAttr()
     * @see #getScxmlAssignType()
     * @generated
     */
    EAttribute getScxmlAssignType_Attr();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlAssignType#getExpr
     * <em>Expr</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Expr</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlAssignType#getExpr()
     * @see #getScxmlAssignType()
     * @generated
     */
    EAttribute getScxmlAssignType_Expr();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlAssignType#getLocation
     * <em>Location</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Location</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlAssignType#getLocation()
     * @see #getScxmlAssignType()
     * @generated
     */
    EAttribute getScxmlAssignType_Location();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlAssignType#getType
     * <em>Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlAssignType#getType()
     * @see #getScxmlAssignType()
     * @generated
     */
    EAttribute getScxmlAssignType_Type();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlAssignType#getAnyAttribute
     * <em>Any Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any Attribute</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlAssignType#getAnyAttribute()
     * @see #getScxmlAssignType()
     * @generated
     */
    EAttribute getScxmlAssignType_AnyAttribute();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlCancelType
     * <em>Cancel Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Cancel Type</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlCancelType
     * @generated
     */
    EClass getScxmlCancelType();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlCancelType#getScxmlExtraContent
     * <em>Scxml Extra Content</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute list '
     *         <em>Scxml Extra Content</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlCancelType#getScxmlExtraContent()
     * @see #getScxmlCancelType()
     * @generated
     */
    EAttribute getScxmlCancelType_ScxmlExtraContent();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlCancelType#getAny
     * <em>Any</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlCancelType#getAny()
     * @see #getScxmlCancelType()
     * @generated
     */
    EAttribute getScxmlCancelType_Any();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlCancelType#getSendid
     * <em>Sendid</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Sendid</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlCancelType#getSendid()
     * @see #getScxmlCancelType()
     * @generated
     */
    EAttribute getScxmlCancelType_Sendid();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlCancelType#getSendidexpr
     * <em>Sendidexpr</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Sendidexpr</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlCancelType#getSendidexpr()
     * @see #getScxmlCancelType()
     * @generated
     */
    EAttribute getScxmlCancelType_Sendidexpr();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlCancelType#getAnyAttribute
     * <em>Any Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any Attribute</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlCancelType#getAnyAttribute()
     * @see #getScxmlCancelType()
     * @generated
     */
    EAttribute getScxmlCancelType_AnyAttribute();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlContentType
     * <em>Content Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Content Type</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlContentType
     * @generated
     */
    EClass getScxmlContentType();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlContentType#getMixed
     * <em>Mixed</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Mixed</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlContentType#getMixed()
     * @see #getScxmlContentType()
     * @generated
     */
    EAttribute getScxmlContentType_Mixed();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlContentType#getAny
     * <em>Any</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlContentType#getAny()
     * @see #getScxmlContentType()
     * @generated
     */
    EAttribute getScxmlContentType_Any();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlContentType#getExpr
     * <em>Expr</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Expr</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlContentType#getExpr()
     * @see #getScxmlContentType()
     * @generated
     */
    EAttribute getScxmlContentType_Expr();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlContentType#getAnyAttribute
     * <em>Any Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any Attribute</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlContentType#getAnyAttribute()
     * @see #getScxmlContentType()
     * @generated
     */
    EAttribute getScxmlContentType_AnyAttribute();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlDatamodelType
     * <em>Datamodel Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Datamodel Type</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlDatamodelType
     * @generated
     */
    EClass getScxmlDatamodelType();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlDatamodelType#getData
     * <em>Data</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Data</em>
     *         '.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlDatamodelType#getData()
     * @see #getScxmlDatamodelType()
     * @generated
     */
    EReference getScxmlDatamodelType_Data();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlDatamodelType#getScxmlExtraContent
     * <em>Scxml Extra Content</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute list '
     *         <em>Scxml Extra Content</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlDatamodelType#getScxmlExtraContent()
     * @see #getScxmlDatamodelType()
     * @generated
     */
    EAttribute getScxmlDatamodelType_ScxmlExtraContent();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlDatamodelType#getAny
     * <em>Any</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlDatamodelType#getAny()
     * @see #getScxmlDatamodelType()
     * @generated
     */
    EAttribute getScxmlDatamodelType_Any();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlDatamodelType#getAnyAttribute
     * <em>Any Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any Attribute</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlDatamodelType#getAnyAttribute()
     * @see #getScxmlDatamodelType()
     * @generated
     */
    EAttribute getScxmlDatamodelType_AnyAttribute();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlDataType
     * <em>Data Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Data Type</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlDataType
     * @generated
     */
    EClass getScxmlDataType();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlDataType#getMixed
     * <em>Mixed</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Mixed</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlDataType#getMixed()
     * @see #getScxmlDataType()
     * @generated
     */
    EAttribute getScxmlDataType_Mixed();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlDataType#getAny
     * <em>Any</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlDataType#getAny()
     * @see #getScxmlDataType()
     * @generated
     */
    EAttribute getScxmlDataType_Any();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlDataType#getExpr
     * <em>Expr</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Expr</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlDataType#getExpr()
     * @see #getScxmlDataType()
     * @generated
     */
    EAttribute getScxmlDataType_Expr();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlDataType#getId
     * <em>Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlDataType#getId()
     * @see #getScxmlDataType()
     * @generated
     */
    EAttribute getScxmlDataType_Id();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlDataType#getSrc
     * <em>Src</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Src</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlDataType#getSrc()
     * @see #getScxmlDataType()
     * @generated
     */
    EAttribute getScxmlDataType_Src();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlDataType#getAnyAttribute
     * <em>Any Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any Attribute</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlDataType#getAnyAttribute()
     * @see #getScxmlDataType()
     * @generated
     */
    EAttribute getScxmlDataType_AnyAttribute();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlDonedataType
     * <em>Donedata Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Donedata Type</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlDonedataType
     * @generated
     */
    EClass getScxmlDonedataType();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlDonedataType#getContent
     * <em>Content</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Content</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlDonedataType#getContent()
     * @see #getScxmlDonedataType()
     * @generated
     */
    EReference getScxmlDonedataType_Content();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlDonedataType#getParam
     * <em>Param</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Param</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlDonedataType#getParam()
     * @see #getScxmlDonedataType()
     * @generated
     */
    EReference getScxmlDonedataType_Param();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlDonedataType#getAnyAttribute
     * <em>Any Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any Attribute</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlDonedataType#getAnyAttribute()
     * @see #getScxmlDonedataType()
     * @generated
     */
    EAttribute getScxmlDonedataType_AnyAttribute();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlElseifType
     * <em>Elseif Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Elseif Type</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlElseifType
     * @generated
     */
    EClass getScxmlElseifType();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlElseifType#getCond
     * <em>Cond</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Cond</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlElseifType#getCond()
     * @see #getScxmlElseifType()
     * @generated
     */
    EAttribute getScxmlElseifType_Cond();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlElseifType#getAnyAttribute
     * <em>Any Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any Attribute</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlElseifType#getAnyAttribute()
     * @see #getScxmlElseifType()
     * @generated
     */
    EAttribute getScxmlElseifType_AnyAttribute();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlElseType
     * <em>Else Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Else Type</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlElseType
     * @generated
     */
    EClass getScxmlElseType();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlElseType#getAnyAttribute
     * <em>Any Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any Attribute</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlElseType#getAnyAttribute()
     * @see #getScxmlElseType()
     * @generated
     */
    EAttribute getScxmlElseType_AnyAttribute();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlFinalizeType
     * <em>Finalize Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Finalize Type</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlFinalizeType
     * @generated
     */
    EClass getScxmlFinalizeType();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlFinalizeType#getScxmlCoreExecutablecontent
     * <em>Scxml Core Executablecontent</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the attribute list '
     *         <em>Scxml Core Executablecontent</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlFinalizeType#getScxmlCoreExecutablecontent()
     * @see #getScxmlFinalizeType()
     * @generated
     */
    EAttribute getScxmlFinalizeType_ScxmlCoreExecutablecontent();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlFinalizeType#getAny
     * <em>Any</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlFinalizeType#getAny()
     * @see #getScxmlFinalizeType()
     * @generated
     */
    EAttribute getScxmlFinalizeType_Any();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlFinalizeType#getRaise
     * <em>Raise</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Raise</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlFinalizeType#getRaise()
     * @see #getScxmlFinalizeType()
     * @generated
     */
    EReference getScxmlFinalizeType_Raise();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlFinalizeType#getIf
     * <em>If</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>If</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlFinalizeType#getIf()
     * @see #getScxmlFinalizeType()
     * @generated
     */
    EReference getScxmlFinalizeType_If();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlFinalizeType#getForeach
     * <em>Foreach</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Foreach</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlFinalizeType#getForeach()
     * @see #getScxmlFinalizeType()
     * @generated
     */
    EReference getScxmlFinalizeType_Foreach();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlFinalizeType#getSend
     * <em>Send</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Send</em>
     *         '.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlFinalizeType#getSend()
     * @see #getScxmlFinalizeType()
     * @generated
     */
    EReference getScxmlFinalizeType_Send();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlFinalizeType#getScript
     * <em>Script</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Script</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlFinalizeType#getScript()
     * @see #getScxmlFinalizeType()
     * @generated
     */
    EReference getScxmlFinalizeType_Script();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlFinalizeType#getAssign
     * <em>Assign</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Assign</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlFinalizeType#getAssign()
     * @see #getScxmlFinalizeType()
     * @generated
     */
    EReference getScxmlFinalizeType_Assign();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlFinalizeType#getLog
     * <em>Log</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Log</em>
     *         '.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlFinalizeType#getLog()
     * @see #getScxmlFinalizeType()
     * @generated
     */
    EReference getScxmlFinalizeType_Log();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlFinalizeType#getCancel
     * <em>Cancel</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Cancel</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlFinalizeType#getCancel()
     * @see #getScxmlFinalizeType()
     * @generated
     */
    EReference getScxmlFinalizeType_Cancel();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlFinalizeType#getAnyAttribute
     * <em>Any Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any Attribute</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlFinalizeType#getAnyAttribute()
     * @see #getScxmlFinalizeType()
     * @generated
     */
    EAttribute getScxmlFinalizeType_AnyAttribute();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlFinalType
     * <em>Final Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Final Type</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlFinalType
     * @generated
     */
    EClass getScxmlFinalType();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlFinalType#getScxmlFinalMix
     * <em>Scxml Final Mix</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Scxml Final Mix</em>
     *         '.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlFinalType#getScxmlFinalMix()
     * @see #getScxmlFinalType()
     * @generated
     */
    EAttribute getScxmlFinalType_ScxmlFinalMix();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlFinalType#getOnentry
     * <em>Onentry</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Onentry</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlFinalType#getOnentry()
     * @see #getScxmlFinalType()
     * @generated
     */
    EReference getScxmlFinalType_Onentry();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlFinalType#getOnexit
     * <em>Onexit</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Onexit</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlFinalType#getOnexit()
     * @see #getScxmlFinalType()
     * @generated
     */
    EReference getScxmlFinalType_Onexit();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlFinalType#getDonedata
     * <em>Donedata</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Donedata</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlFinalType#getDonedata()
     * @see #getScxmlFinalType()
     * @generated
     */
    EReference getScxmlFinalType_Donedata();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlFinalType#getAny
     * <em>Any</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlFinalType#getAny()
     * @see #getScxmlFinalType()
     * @generated
     */
    EAttribute getScxmlFinalType_Any();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlFinalType#getId
     * <em>Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlFinalType#getId()
     * @see #getScxmlFinalType()
     * @generated
     */
    EAttribute getScxmlFinalType_Id();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlFinalType#getAnyAttribute
     * <em>Any Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any Attribute</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlFinalType#getAnyAttribute()
     * @see #getScxmlFinalType()
     * @generated
     */
    EAttribute getScxmlFinalType_AnyAttribute();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType
     * <em>Foreach Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Foreach Type</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType
     * @generated
     */
    EClass getScxmlForeachType();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getScxmlCoreExecutablecontent
     * <em>Scxml Core Executablecontent</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the attribute list '
     *         <em>Scxml Core Executablecontent</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getScxmlCoreExecutablecontent()
     * @see #getScxmlForeachType()
     * @generated
     */
    EAttribute getScxmlForeachType_ScxmlCoreExecutablecontent();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getAny
     * <em>Any</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getAny()
     * @see #getScxmlForeachType()
     * @generated
     */
    EAttribute getScxmlForeachType_Any();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getRaise
     * <em>Raise</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Raise</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getRaise()
     * @see #getScxmlForeachType()
     * @generated
     */
    EReference getScxmlForeachType_Raise();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getIf
     * <em>If</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>If</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getIf()
     * @see #getScxmlForeachType()
     * @generated
     */
    EReference getScxmlForeachType_If();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getForeach
     * <em>Foreach</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Foreach</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getForeach()
     * @see #getScxmlForeachType()
     * @generated
     */
    EReference getScxmlForeachType_Foreach();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getSend
     * <em>Send</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Send</em>
     *         '.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getSend()
     * @see #getScxmlForeachType()
     * @generated
     */
    EReference getScxmlForeachType_Send();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getScript
     * <em>Script</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Script</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getScript()
     * @see #getScxmlForeachType()
     * @generated
     */
    EReference getScxmlForeachType_Script();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getAssign
     * <em>Assign</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Assign</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getAssign()
     * @see #getScxmlForeachType()
     * @generated
     */
    EReference getScxmlForeachType_Assign();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getLog
     * <em>Log</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Log</em>
     *         '.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getLog()
     * @see #getScxmlForeachType()
     * @generated
     */
    EReference getScxmlForeachType_Log();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getCancel
     * <em>Cancel</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Cancel</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getCancel()
     * @see #getScxmlForeachType()
     * @generated
     */
    EReference getScxmlForeachType_Cancel();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getArray
     * <em>Array</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Array</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getArray()
     * @see #getScxmlForeachType()
     * @generated
     */
    EAttribute getScxmlForeachType_Array();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getIndex
     * <em>Index</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Index</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getIndex()
     * @see #getScxmlForeachType()
     * @generated
     */
    EAttribute getScxmlForeachType_Index();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getItem
     * <em>Item</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Item</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getItem()
     * @see #getScxmlForeachType()
     * @generated
     */
    EAttribute getScxmlForeachType_Item();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getAnyAttribute
     * <em>Any Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any Attribute</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getAnyAttribute()
     * @see #getScxmlForeachType()
     * @generated
     */
    EAttribute getScxmlForeachType_AnyAttribute();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlHistoryType
     * <em>History Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>History Type</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlHistoryType
     * @generated
     */
    EClass getScxmlHistoryType();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlHistoryType#getScxmlExtraContent
     * <em>Scxml Extra Content</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute list '
     *         <em>Scxml Extra Content</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlHistoryType#getScxmlExtraContent()
     * @see #getScxmlHistoryType()
     * @generated
     */
    EAttribute getScxmlHistoryType_ScxmlExtraContent();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlHistoryType#getAny
     * <em>Any</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlHistoryType#getAny()
     * @see #getScxmlHistoryType()
     * @generated
     */
    EAttribute getScxmlHistoryType_Any();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlHistoryType#getTransition
     * <em>Transition</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '
     *         <em>Transition</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlHistoryType#getTransition()
     * @see #getScxmlHistoryType()
     * @generated
     */
    EReference getScxmlHistoryType_Transition();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlHistoryType#getScxmlExtraContent1
     * <em>Scxml Extra Content1</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the attribute list '
     *         <em>Scxml Extra Content1</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlHistoryType#getScxmlExtraContent1()
     * @see #getScxmlHistoryType()
     * @generated
     */
    EAttribute getScxmlHistoryType_ScxmlExtraContent1();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlHistoryType#getAny1
     * <em>Any1</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any1</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlHistoryType#getAny1()
     * @see #getScxmlHistoryType()
     * @generated
     */
    EAttribute getScxmlHistoryType_Any1();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlHistoryType#getId
     * <em>Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlHistoryType#getId()
     * @see #getScxmlHistoryType()
     * @generated
     */
    EAttribute getScxmlHistoryType_Id();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlHistoryType#getType
     * <em>Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlHistoryType#getType()
     * @see #getScxmlHistoryType()
     * @generated
     */
    EAttribute getScxmlHistoryType_Type();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlHistoryType#getAnyAttribute
     * <em>Any Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any Attribute</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlHistoryType#getAnyAttribute()
     * @see #getScxmlHistoryType()
     * @generated
     */
    EAttribute getScxmlHistoryType_AnyAttribute();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType
     * <em>If Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>If Type</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlIfType
     * @generated
     */
    EClass getScxmlIfType();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getScxmlCoreExecutablecontent
     * <em>Scxml Core Executablecontent</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the attribute list '
     *         <em>Scxml Core Executablecontent</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getScxmlCoreExecutablecontent()
     * @see #getScxmlIfType()
     * @generated
     */
    EAttribute getScxmlIfType_ScxmlCoreExecutablecontent();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getAny
     * <em>Any</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getAny()
     * @see #getScxmlIfType()
     * @generated
     */
    EAttribute getScxmlIfType_Any();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getRaise
     * <em>Raise</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Raise</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getRaise()
     * @see #getScxmlIfType()
     * @generated
     */
    EReference getScxmlIfType_Raise();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getIf
     * <em>If</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>If</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getIf()
     * @see #getScxmlIfType()
     * @generated
     */
    EReference getScxmlIfType_If();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getForeach
     * <em>Foreach</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Foreach</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getForeach()
     * @see #getScxmlIfType()
     * @generated
     */
    EReference getScxmlIfType_Foreach();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getSend
     * <em>Send</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Send</em>
     *         '.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getSend()
     * @see #getScxmlIfType()
     * @generated
     */
    EReference getScxmlIfType_Send();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getScript
     * <em>Script</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Script</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getScript()
     * @see #getScxmlIfType()
     * @generated
     */
    EReference getScxmlIfType_Script();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getAssign
     * <em>Assign</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Assign</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getAssign()
     * @see #getScxmlIfType()
     * @generated
     */
    EReference getScxmlIfType_Assign();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getLog
     * <em>Log</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Log</em>
     *         '.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getLog()
     * @see #getScxmlIfType()
     * @generated
     */
    EReference getScxmlIfType_Log();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getCancel
     * <em>Cancel</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Cancel</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getCancel()
     * @see #getScxmlIfType()
     * @generated
     */
    EReference getScxmlIfType_Cancel();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getElseif
     * <em>Elseif</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Elseif</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getElseif()
     * @see #getScxmlIfType()
     * @generated
     */
    EReference getScxmlIfType_Elseif();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getScxmlCoreExecutablecontent1
     * <em>Scxml Core Executablecontent1</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the attribute list '
     *         <em>Scxml Core Executablecontent1</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getScxmlCoreExecutablecontent1()
     * @see #getScxmlIfType()
     * @generated
     */
    EAttribute getScxmlIfType_ScxmlCoreExecutablecontent1();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getAny1
     * <em>Any1</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any1</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getAny1()
     * @see #getScxmlIfType()
     * @generated
     */
    EAttribute getScxmlIfType_Any1();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getRaise1
     * <em>Raise1</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Raise1</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getRaise1()
     * @see #getScxmlIfType()
     * @generated
     */
    EReference getScxmlIfType_Raise1();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getIf1
     * <em>If1</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>If1</em>
     *         '.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getIf1()
     * @see #getScxmlIfType()
     * @generated
     */
    EReference getScxmlIfType_If1();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getForeach1
     * <em>Foreach1</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Foreach1</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getForeach1()
     * @see #getScxmlIfType()
     * @generated
     */
    EReference getScxmlIfType_Foreach1();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getSend1
     * <em>Send1</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Send1</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getSend1()
     * @see #getScxmlIfType()
     * @generated
     */
    EReference getScxmlIfType_Send1();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getScript1
     * <em>Script1</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Script1</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getScript1()
     * @see #getScxmlIfType()
     * @generated
     */
    EReference getScxmlIfType_Script1();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getAssign1
     * <em>Assign1</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Assign1</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getAssign1()
     * @see #getScxmlIfType()
     * @generated
     */
    EReference getScxmlIfType_Assign1();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getLog1
     * <em>Log1</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Log1</em>
     *         '.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getLog1()
     * @see #getScxmlIfType()
     * @generated
     */
    EReference getScxmlIfType_Log1();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getCancel1
     * <em>Cancel1</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Cancel1</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getCancel1()
     * @see #getScxmlIfType()
     * @generated
     */
    EReference getScxmlIfType_Cancel1();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getElse
     * <em>Else</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Else</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getElse()
     * @see #getScxmlIfType()
     * @generated
     */
    EReference getScxmlIfType_Else();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getScxmlCoreExecutablecontent2
     * <em>Scxml Core Executablecontent2</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the attribute list '
     *         <em>Scxml Core Executablecontent2</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getScxmlCoreExecutablecontent2()
     * @see #getScxmlIfType()
     * @generated
     */
    EAttribute getScxmlIfType_ScxmlCoreExecutablecontent2();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getAny2
     * <em>Any2</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any2</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getAny2()
     * @see #getScxmlIfType()
     * @generated
     */
    EAttribute getScxmlIfType_Any2();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getRaise2
     * <em>Raise2</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Raise2</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getRaise2()
     * @see #getScxmlIfType()
     * @generated
     */
    EReference getScxmlIfType_Raise2();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getIf2
     * <em>If2</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>If2</em>
     *         '.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getIf2()
     * @see #getScxmlIfType()
     * @generated
     */
    EReference getScxmlIfType_If2();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getForeach2
     * <em>Foreach2</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Foreach2</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getForeach2()
     * @see #getScxmlIfType()
     * @generated
     */
    EReference getScxmlIfType_Foreach2();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getSend2
     * <em>Send2</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Send2</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getSend2()
     * @see #getScxmlIfType()
     * @generated
     */
    EReference getScxmlIfType_Send2();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getScript2
     * <em>Script2</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Script2</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getScript2()
     * @see #getScxmlIfType()
     * @generated
     */
    EReference getScxmlIfType_Script2();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getAssign2
     * <em>Assign2</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Assign2</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getAssign2()
     * @see #getScxmlIfType()
     * @generated
     */
    EReference getScxmlIfType_Assign2();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getLog2
     * <em>Log2</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Log2</em>
     *         '.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getLog2()
     * @see #getScxmlIfType()
     * @generated
     */
    EReference getScxmlIfType_Log2();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getCancel2
     * <em>Cancel2</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Cancel2</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getCancel2()
     * @see #getScxmlIfType()
     * @generated
     */
    EReference getScxmlIfType_Cancel2();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getCond
     * <em>Cond</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Cond</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getCond()
     * @see #getScxmlIfType()
     * @generated
     */
    EAttribute getScxmlIfType_Cond();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getAnyAttribute
     * <em>Any Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any Attribute</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getAnyAttribute()
     * @see #getScxmlIfType()
     * @generated
     */
    EAttribute getScxmlIfType_AnyAttribute();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInitialType
     * <em>Initial Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Initial Type</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlInitialType
     * @generated
     */
    EClass getScxmlInitialType();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInitialType#getScxmlExtraContent
     * <em>Scxml Extra Content</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute list '
     *         <em>Scxml Extra Content</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlInitialType#getScxmlExtraContent()
     * @see #getScxmlInitialType()
     * @generated
     */
    EAttribute getScxmlInitialType_ScxmlExtraContent();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInitialType#getAny
     * <em>Any</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlInitialType#getAny()
     * @see #getScxmlInitialType()
     * @generated
     */
    EAttribute getScxmlInitialType_Any();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInitialType#getTransition
     * <em>Transition</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '
     *         <em>Transition</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlInitialType#getTransition()
     * @see #getScxmlInitialType()
     * @generated
     */
    EReference getScxmlInitialType_Transition();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInitialType#getScxmlExtraContent1
     * <em>Scxml Extra Content1</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the attribute list '
     *         <em>Scxml Extra Content1</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlInitialType#getScxmlExtraContent1()
     * @see #getScxmlInitialType()
     * @generated
     */
    EAttribute getScxmlInitialType_ScxmlExtraContent1();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInitialType#getAny1
     * <em>Any1</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any1</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlInitialType#getAny1()
     * @see #getScxmlInitialType()
     * @generated
     */
    EAttribute getScxmlInitialType_Any1();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInitialType#getAnyAttribute
     * <em>Any Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any Attribute</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlInitialType#getAnyAttribute()
     * @see #getScxmlInitialType()
     * @generated
     */
    EAttribute getScxmlInitialType_AnyAttribute();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType
     * <em>Invoke Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Invoke Type</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType
     * @generated
     */
    EClass getScxmlInvokeType();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getScxmlInvokeMix
     * <em>Scxml Invoke Mix</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute list '<em>Scxml Invoke Mix</em>
     *         '.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getScxmlInvokeMix()
     * @see #getScxmlInvokeType()
     * @generated
     */
    EAttribute getScxmlInvokeType_ScxmlInvokeMix();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getContent
     * <em>Content</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Content</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getContent()
     * @see #getScxmlInvokeType()
     * @generated
     */
    EReference getScxmlInvokeType_Content();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getParam
     * <em>Param</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Param</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getParam()
     * @see #getScxmlInvokeType()
     * @generated
     */
    EReference getScxmlInvokeType_Param();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getFinalize
     * <em>Finalize</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Finalize</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getFinalize()
     * @see #getScxmlInvokeType()
     * @generated
     */
    EReference getScxmlInvokeType_Finalize();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getAny
     * <em>Any</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getAny()
     * @see #getScxmlInvokeType()
     * @generated
     */
    EAttribute getScxmlInvokeType_Any();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getAutoforward
     * <em>Autoforward</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Autoforward</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getAutoforward()
     * @see #getScxmlInvokeType()
     * @generated
     */
    EAttribute getScxmlInvokeType_Autoforward();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getId
     * <em>Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getId()
     * @see #getScxmlInvokeType()
     * @generated
     */
    EAttribute getScxmlInvokeType_Id();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getIdlocation
     * <em>Idlocation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Idlocation</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getIdlocation()
     * @see #getScxmlInvokeType()
     * @generated
     */
    EAttribute getScxmlInvokeType_Idlocation();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getNamelist
     * <em>Namelist</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Namelist</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getNamelist()
     * @see #getScxmlInvokeType()
     * @generated
     */
    EAttribute getScxmlInvokeType_Namelist();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getSrc
     * <em>Src</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Src</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getSrc()
     * @see #getScxmlInvokeType()
     * @generated
     */
    EAttribute getScxmlInvokeType_Src();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getSrcexpr
     * <em>Srcexpr</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Srcexpr</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getSrcexpr()
     * @see #getScxmlInvokeType()
     * @generated
     */
    EAttribute getScxmlInvokeType_Srcexpr();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getType
     * <em>Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getType()
     * @see #getScxmlInvokeType()
     * @generated
     */
    EAttribute getScxmlInvokeType_Type();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getTypeexpr
     * <em>Typeexpr</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Typeexpr</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getTypeexpr()
     * @see #getScxmlInvokeType()
     * @generated
     */
    EAttribute getScxmlInvokeType_Typeexpr();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getAnyAttribute
     * <em>Any Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any Attribute</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getAnyAttribute()
     * @see #getScxmlInvokeType()
     * @generated
     */
    EAttribute getScxmlInvokeType_AnyAttribute();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlLogType
     * <em>Log Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Log Type</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlLogType
     * @generated
     */
    EClass getScxmlLogType();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlLogType#getScxmlExtraContent
     * <em>Scxml Extra Content</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute list '
     *         <em>Scxml Extra Content</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlLogType#getScxmlExtraContent()
     * @see #getScxmlLogType()
     * @generated
     */
    EAttribute getScxmlLogType_ScxmlExtraContent();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlLogType#getAny
     * <em>Any</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlLogType#getAny()
     * @see #getScxmlLogType()
     * @generated
     */
    EAttribute getScxmlLogType_Any();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlLogType#getExpr
     * <em>Expr</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Expr</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlLogType#getExpr()
     * @see #getScxmlLogType()
     * @generated
     */
    EAttribute getScxmlLogType_Expr();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlLogType#getLabel
     * <em>Label</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Label</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlLogType#getLabel()
     * @see #getScxmlLogType()
     * @generated
     */
    EAttribute getScxmlLogType_Label();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlLogType#getAnyAttribute
     * <em>Any Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any Attribute</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlLogType#getAnyAttribute()
     * @see #getScxmlLogType()
     * @generated
     */
    EAttribute getScxmlLogType_AnyAttribute();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlOnentryType
     * <em>Onentry Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Onentry Type</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlOnentryType
     * @generated
     */
    EClass getScxmlOnentryType();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlOnentryType#getScxmlCoreExecutablecontent
     * <em>Scxml Core Executablecontent</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the attribute list '
     *         <em>Scxml Core Executablecontent</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlOnentryType#getScxmlCoreExecutablecontent()
     * @see #getScxmlOnentryType()
     * @generated
     */
    EAttribute getScxmlOnentryType_ScxmlCoreExecutablecontent();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlOnentryType#getAny
     * <em>Any</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlOnentryType#getAny()
     * @see #getScxmlOnentryType()
     * @generated
     */
    EAttribute getScxmlOnentryType_Any();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlOnentryType#getRaise
     * <em>Raise</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Raise</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlOnentryType#getRaise()
     * @see #getScxmlOnentryType()
     * @generated
     */
    EReference getScxmlOnentryType_Raise();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlOnentryType#getIf
     * <em>If</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>If</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlOnentryType#getIf()
     * @see #getScxmlOnentryType()
     * @generated
     */
    EReference getScxmlOnentryType_If();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlOnentryType#getForeach
     * <em>Foreach</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Foreach</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlOnentryType#getForeach()
     * @see #getScxmlOnentryType()
     * @generated
     */
    EReference getScxmlOnentryType_Foreach();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlOnentryType#getSend
     * <em>Send</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Send</em>
     *         '.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlOnentryType#getSend()
     * @see #getScxmlOnentryType()
     * @generated
     */
    EReference getScxmlOnentryType_Send();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlOnentryType#getScript
     * <em>Script</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Script</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlOnentryType#getScript()
     * @see #getScxmlOnentryType()
     * @generated
     */
    EReference getScxmlOnentryType_Script();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlOnentryType#getAssign
     * <em>Assign</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Assign</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlOnentryType#getAssign()
     * @see #getScxmlOnentryType()
     * @generated
     */
    EReference getScxmlOnentryType_Assign();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlOnentryType#getLog
     * <em>Log</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Log</em>
     *         '.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlOnentryType#getLog()
     * @see #getScxmlOnentryType()
     * @generated
     */
    EReference getScxmlOnentryType_Log();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlOnentryType#getCancel
     * <em>Cancel</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Cancel</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlOnentryType#getCancel()
     * @see #getScxmlOnentryType()
     * @generated
     */
    EReference getScxmlOnentryType_Cancel();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlOnentryType#getAnyAttribute
     * <em>Any Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any Attribute</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlOnentryType#getAnyAttribute()
     * @see #getScxmlOnentryType()
     * @generated
     */
    EAttribute getScxmlOnentryType_AnyAttribute();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlOnexitType
     * <em>Onexit Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Onexit Type</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlOnexitType
     * @generated
     */
    EClass getScxmlOnexitType();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlOnexitType#getScxmlCoreExecutablecontent
     * <em>Scxml Core Executablecontent</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the attribute list '
     *         <em>Scxml Core Executablecontent</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlOnexitType#getScxmlCoreExecutablecontent()
     * @see #getScxmlOnexitType()
     * @generated
     */
    EAttribute getScxmlOnexitType_ScxmlCoreExecutablecontent();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlOnexitType#getAny
     * <em>Any</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlOnexitType#getAny()
     * @see #getScxmlOnexitType()
     * @generated
     */
    EAttribute getScxmlOnexitType_Any();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlOnexitType#getRaise
     * <em>Raise</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Raise</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlOnexitType#getRaise()
     * @see #getScxmlOnexitType()
     * @generated
     */
    EReference getScxmlOnexitType_Raise();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlOnexitType#getIf
     * <em>If</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>If</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlOnexitType#getIf()
     * @see #getScxmlOnexitType()
     * @generated
     */
    EReference getScxmlOnexitType_If();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlOnexitType#getForeach
     * <em>Foreach</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Foreach</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlOnexitType#getForeach()
     * @see #getScxmlOnexitType()
     * @generated
     */
    EReference getScxmlOnexitType_Foreach();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlOnexitType#getSend
     * <em>Send</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Send</em>
     *         '.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlOnexitType#getSend()
     * @see #getScxmlOnexitType()
     * @generated
     */
    EReference getScxmlOnexitType_Send();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlOnexitType#getScript
     * <em>Script</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Script</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlOnexitType#getScript()
     * @see #getScxmlOnexitType()
     * @generated
     */
    EReference getScxmlOnexitType_Script();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlOnexitType#getAssign
     * <em>Assign</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Assign</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlOnexitType#getAssign()
     * @see #getScxmlOnexitType()
     * @generated
     */
    EReference getScxmlOnexitType_Assign();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlOnexitType#getLog
     * <em>Log</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Log</em>
     *         '.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlOnexitType#getLog()
     * @see #getScxmlOnexitType()
     * @generated
     */
    EReference getScxmlOnexitType_Log();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlOnexitType#getCancel
     * <em>Cancel</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Cancel</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlOnexitType#getCancel()
     * @see #getScxmlOnexitType()
     * @generated
     */
    EReference getScxmlOnexitType_Cancel();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlOnexitType#getAnyAttribute
     * <em>Any Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any Attribute</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlOnexitType#getAnyAttribute()
     * @see #getScxmlOnexitType()
     * @generated
     */
    EAttribute getScxmlOnexitType_AnyAttribute();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlParallelType
     * <em>Parallel Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Parallel Type</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlParallelType
     * @generated
     */
    EClass getScxmlParallelType();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlParallelType#getScxmlParallelMix
     * <em>Scxml Parallel Mix</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute list '
     *         <em>Scxml Parallel Mix</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlParallelType#getScxmlParallelMix()
     * @see #getScxmlParallelType()
     * @generated
     */
    EAttribute getScxmlParallelType_ScxmlParallelMix();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlParallelType#getOnentry
     * <em>Onentry</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Onentry</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlParallelType#getOnentry()
     * @see #getScxmlParallelType()
     * @generated
     */
    EReference getScxmlParallelType_Onentry();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlParallelType#getOnexit
     * <em>Onexit</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Onexit</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlParallelType#getOnexit()
     * @see #getScxmlParallelType()
     * @generated
     */
    EReference getScxmlParallelType_Onexit();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlParallelType#getTransition
     * <em>Transition</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Transition</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlParallelType#getTransition()
     * @see #getScxmlParallelType()
     * @generated
     */
    EReference getScxmlParallelType_Transition();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlParallelType#getState
     * <em>State</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>State</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlParallelType#getState()
     * @see #getScxmlParallelType()
     * @generated
     */
    EReference getScxmlParallelType_State();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlParallelType#getParallel
     * <em>Parallel</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Parallel</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlParallelType#getParallel()
     * @see #getScxmlParallelType()
     * @generated
     */
    EReference getScxmlParallelType_Parallel();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlParallelType#getHistory
     * <em>History</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>History</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlParallelType#getHistory()
     * @see #getScxmlParallelType()
     * @generated
     */
    EReference getScxmlParallelType_History();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlParallelType#getDatamodel
     * <em>Datamodel</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Datamodel</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlParallelType#getDatamodel()
     * @see #getScxmlParallelType()
     * @generated
     */
    EReference getScxmlParallelType_Datamodel();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlParallelType#getInvoke
     * <em>Invoke</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Invoke</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlParallelType#getInvoke()
     * @see #getScxmlParallelType()
     * @generated
     */
    EReference getScxmlParallelType_Invoke();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlParallelType#getAny
     * <em>Any</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlParallelType#getAny()
     * @see #getScxmlParallelType()
     * @generated
     */
    EAttribute getScxmlParallelType_Any();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlParallelType#getId
     * <em>Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlParallelType#getId()
     * @see #getScxmlParallelType()
     * @generated
     */
    EAttribute getScxmlParallelType_Id();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlParallelType#getAnyAttribute
     * <em>Any Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any Attribute</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlParallelType#getAnyAttribute()
     * @see #getScxmlParallelType()
     * @generated
     */
    EAttribute getScxmlParallelType_AnyAttribute();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlParamType
     * <em>Param Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Param Type</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlParamType
     * @generated
     */
    EClass getScxmlParamType();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlParamType#getScxmlExtraContent
     * <em>Scxml Extra Content</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute list '
     *         <em>Scxml Extra Content</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlParamType#getScxmlExtraContent()
     * @see #getScxmlParamType()
     * @generated
     */
    EAttribute getScxmlParamType_ScxmlExtraContent();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlParamType#getAny
     * <em>Any</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlParamType#getAny()
     * @see #getScxmlParamType()
     * @generated
     */
    EAttribute getScxmlParamType_Any();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlParamType#getExpr
     * <em>Expr</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Expr</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlParamType#getExpr()
     * @see #getScxmlParamType()
     * @generated
     */
    EAttribute getScxmlParamType_Expr();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlParamType#getLocation
     * <em>Location</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Location</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlParamType#getLocation()
     * @see #getScxmlParamType()
     * @generated
     */
    EAttribute getScxmlParamType_Location();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlParamType#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlParamType#getName()
     * @see #getScxmlParamType()
     * @generated
     */
    EAttribute getScxmlParamType_Name();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlParamType#getAnyAttribute
     * <em>Any Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any Attribute</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlParamType#getAnyAttribute()
     * @see #getScxmlParamType()
     * @generated
     */
    EAttribute getScxmlParamType_AnyAttribute();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlRaiseType
     * <em>Raise Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Raise Type</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlRaiseType
     * @generated
     */
    EClass getScxmlRaiseType();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlRaiseType#getEvent
     * <em>Event</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Event</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlRaiseType#getEvent()
     * @see #getScxmlRaiseType()
     * @generated
     */
    EAttribute getScxmlRaiseType_Event();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlRaiseType#getAnyAttribute
     * <em>Any Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any Attribute</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlRaiseType#getAnyAttribute()
     * @see #getScxmlRaiseType()
     * @generated
     */
    EAttribute getScxmlRaiseType_AnyAttribute();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScriptType
     * <em>Script Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Script Type</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlScriptType
     * @generated
     */
    EClass getScxmlScriptType();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScriptType#getMixed
     * <em>Mixed</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Mixed</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlScriptType#getMixed()
     * @see #getScxmlScriptType()
     * @generated
     */
    EAttribute getScxmlScriptType_Mixed();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScriptType#getScxmlExtraContent
     * <em>Scxml Extra Content</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute list '
     *         <em>Scxml Extra Content</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlScriptType#getScxmlExtraContent()
     * @see #getScxmlScriptType()
     * @generated
     */
    EAttribute getScxmlScriptType_ScxmlExtraContent();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScriptType#getAny
     * <em>Any</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlScriptType#getAny()
     * @see #getScxmlScriptType()
     * @generated
     */
    EAttribute getScxmlScriptType_Any();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScriptType#getSrc
     * <em>Src</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Src</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlScriptType#getSrc()
     * @see #getScxmlScriptType()
     * @generated
     */
    EAttribute getScxmlScriptType_Src();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScriptType#getAnyAttribute
     * <em>Any Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any Attribute</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlScriptType#getAnyAttribute()
     * @see #getScxmlScriptType()
     * @generated
     */
    EAttribute getScxmlScriptType_AnyAttribute();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType
     * <em>Scxml Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Scxml Type</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType
     * @generated
     */
    EClass getScxmlScxmlType();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getScxmlScxmlMix
     * <em>Scxml Scxml Mix</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Scxml Scxml Mix</em>
     *         '.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getScxmlScxmlMix()
     * @see #getScxmlScxmlType()
     * @generated
     */
    EAttribute getScxmlScxmlType_ScxmlScxmlMix();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getState
     * <em>State</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>State</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getState()
     * @see #getScxmlScxmlType()
     * @generated
     */
    EReference getScxmlScxmlType_State();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getParallel
     * <em>Parallel</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Parallel</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getParallel()
     * @see #getScxmlScxmlType()
     * @generated
     */
    EReference getScxmlScxmlType_Parallel();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getFinal
     * <em>Final</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Final</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getFinal()
     * @see #getScxmlScxmlType()
     * @generated
     */
    EReference getScxmlScxmlType_Final();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getDatamodel
     * <em>Datamodel</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Datamodel</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getDatamodel()
     * @see #getScxmlScxmlType()
     * @generated
     */
    EReference getScxmlScxmlType_Datamodel();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getScript
     * <em>Script</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Script</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getScript()
     * @see #getScxmlScxmlType()
     * @generated
     */
    EReference getScxmlScxmlType_Script();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getAny
     * <em>Any</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getAny()
     * @see #getScxmlScxmlType()
     * @generated
     */
    EAttribute getScxmlScxmlType_Any();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getBinding
     * <em>Binding</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Binding</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getBinding()
     * @see #getScxmlScxmlType()
     * @generated
     */
    EAttribute getScxmlScxmlType_Binding();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getDatamodel1
     * <em>Datamodel1</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Datamodel1</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getDatamodel1()
     * @see #getScxmlScxmlType()
     * @generated
     */
    EAttribute getScxmlScxmlType_Datamodel1();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getExmode
     * <em>Exmode</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Exmode</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getExmode()
     * @see #getScxmlScxmlType()
     * @generated
     */
    EAttribute getScxmlScxmlType_Exmode();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getInitial
     * <em>Initial</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Initial</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getInitial()
     * @see #getScxmlScxmlType()
     * @generated
     */
    EAttribute getScxmlScxmlType_Initial();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getName()
     * @see #getScxmlScxmlType()
     * @generated
     */
    EAttribute getScxmlScxmlType_Name();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getVersion
     * <em>Version</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Version</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getVersion()
     * @see #getScxmlScxmlType()
     * @generated
     */
    EAttribute getScxmlScxmlType_Version();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getAnyAttribute
     * <em>Any Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any Attribute</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getAnyAttribute()
     * @see #getScxmlScxmlType()
     * @generated
     */
    EAttribute getScxmlScxmlType_AnyAttribute();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType
     * <em>Send Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Send Type</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlSendType
     * @generated
     */
    EClass getScxmlSendType();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getScxmlSendMix
     * <em>Scxml Send Mix</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Scxml Send Mix</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getScxmlSendMix()
     * @see #getScxmlSendType()
     * @generated
     */
    EAttribute getScxmlSendType_ScxmlSendMix();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getContent
     * <em>Content</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Content</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getContent()
     * @see #getScxmlSendType()
     * @generated
     */
    EReference getScxmlSendType_Content();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getParam
     * <em>Param</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Param</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getParam()
     * @see #getScxmlSendType()
     * @generated
     */
    EReference getScxmlSendType_Param();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getAny
     * <em>Any</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getAny()
     * @see #getScxmlSendType()
     * @generated
     */
    EAttribute getScxmlSendType_Any();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getDelay
     * <em>Delay</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Delay</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getDelay()
     * @see #getScxmlSendType()
     * @generated
     */
    EAttribute getScxmlSendType_Delay();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getDelayexpr
     * <em>Delayexpr</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Delayexpr</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getDelayexpr()
     * @see #getScxmlSendType()
     * @generated
     */
    EAttribute getScxmlSendType_Delayexpr();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getEvent
     * <em>Event</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Event</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getEvent()
     * @see #getScxmlSendType()
     * @generated
     */
    EAttribute getScxmlSendType_Event();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getEventexpr
     * <em>Eventexpr</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Eventexpr</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getEventexpr()
     * @see #getScxmlSendType()
     * @generated
     */
    EAttribute getScxmlSendType_Eventexpr();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getId
     * <em>Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getId()
     * @see #getScxmlSendType()
     * @generated
     */
    EAttribute getScxmlSendType_Id();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getIdlocation
     * <em>Idlocation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Idlocation</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getIdlocation()
     * @see #getScxmlSendType()
     * @generated
     */
    EAttribute getScxmlSendType_Idlocation();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getNamelist
     * <em>Namelist</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Namelist</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getNamelist()
     * @see #getScxmlSendType()
     * @generated
     */
    EAttribute getScxmlSendType_Namelist();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getTarget
     * <em>Target</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Target</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getTarget()
     * @see #getScxmlSendType()
     * @generated
     */
    EAttribute getScxmlSendType_Target();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getTargetexpr
     * <em>Targetexpr</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Targetexpr</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getTargetexpr()
     * @see #getScxmlSendType()
     * @generated
     */
    EAttribute getScxmlSendType_Targetexpr();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getType
     * <em>Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getType()
     * @see #getScxmlSendType()
     * @generated
     */
    EAttribute getScxmlSendType_Type();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getTypeexpr
     * <em>Typeexpr</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Typeexpr</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getTypeexpr()
     * @see #getScxmlSendType()
     * @generated
     */
    EAttribute getScxmlSendType_Typeexpr();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getAnyAttribute
     * <em>Any Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any Attribute</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getAnyAttribute()
     * @see #getScxmlSendType()
     * @generated
     */
    EAttribute getScxmlSendType_AnyAttribute();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlStateType
     * <em>State Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>State Type</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlStateType
     * @generated
     */
    EClass getScxmlStateType();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlStateType#getScxmlStateMix
     * <em>Scxml State Mix</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Scxml State Mix</em>
     *         '.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlStateType#getScxmlStateMix()
     * @see #getScxmlStateType()
     * @generated
     */
    EAttribute getScxmlStateType_ScxmlStateMix();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlStateType#getOnentry
     * <em>Onentry</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Onentry</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlStateType#getOnentry()
     * @see #getScxmlStateType()
     * @generated
     */
    EReference getScxmlStateType_Onentry();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlStateType#getOnexit
     * <em>Onexit</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Onexit</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlStateType#getOnexit()
     * @see #getScxmlStateType()
     * @generated
     */
    EReference getScxmlStateType_Onexit();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlStateType#getTransition
     * <em>Transition</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Transition</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlStateType#getTransition()
     * @see #getScxmlStateType()
     * @generated
     */
    EReference getScxmlStateType_Transition();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlStateType#getInitial
     * <em>Initial</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Initial</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlStateType#getInitial()
     * @see #getScxmlStateType()
     * @generated
     */
    EReference getScxmlStateType_Initial();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlStateType#getState
     * <em>State</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>State</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlStateType#getState()
     * @see #getScxmlStateType()
     * @generated
     */
    EReference getScxmlStateType_State();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlStateType#getParallel
     * <em>Parallel</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Parallel</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlStateType#getParallel()
     * @see #getScxmlStateType()
     * @generated
     */
    EReference getScxmlStateType_Parallel();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlStateType#getFinal
     * <em>Final</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Final</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlStateType#getFinal()
     * @see #getScxmlStateType()
     * @generated
     */
    EReference getScxmlStateType_Final();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlStateType#getHistory
     * <em>History</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>History</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlStateType#getHistory()
     * @see #getScxmlStateType()
     * @generated
     */
    EReference getScxmlStateType_History();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlStateType#getDatamodel
     * <em>Datamodel</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Datamodel</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlStateType#getDatamodel()
     * @see #getScxmlStateType()
     * @generated
     */
    EReference getScxmlStateType_Datamodel();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlStateType#getInvoke
     * <em>Invoke</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Invoke</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlStateType#getInvoke()
     * @see #getScxmlStateType()
     * @generated
     */
    EReference getScxmlStateType_Invoke();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlStateType#getAny
     * <em>Any</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlStateType#getAny()
     * @see #getScxmlStateType()
     * @generated
     */
    EAttribute getScxmlStateType_Any();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlStateType#getId
     * <em>Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlStateType#getId()
     * @see #getScxmlStateType()
     * @generated
     */
    EAttribute getScxmlStateType_Id();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlStateType#getInitial1
     * <em>Initial1</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Initial1</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlStateType#getInitial1()
     * @see #getScxmlStateType()
     * @generated
     */
    EAttribute getScxmlStateType_Initial1();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlStateType#getAnyAttribute
     * <em>Any Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any Attribute</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlStateType#getAnyAttribute()
     * @see #getScxmlStateType()
     * @generated
     */
    EAttribute getScxmlStateType_AnyAttribute();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType
     * <em>Transition Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Transition Type</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType
     * @generated
     */
    EClass getScxmlTransitionType();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getScxmlCoreExecutablecontent
     * <em>Scxml Core Executablecontent</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the attribute list '
     *         <em>Scxml Core Executablecontent</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getScxmlCoreExecutablecontent()
     * @see #getScxmlTransitionType()
     * @generated
     */
    EAttribute getScxmlTransitionType_ScxmlCoreExecutablecontent();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getAny
     * <em>Any</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getAny()
     * @see #getScxmlTransitionType()
     * @generated
     */
    EAttribute getScxmlTransitionType_Any();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getRaise
     * <em>Raise</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Raise</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getRaise()
     * @see #getScxmlTransitionType()
     * @generated
     */
    EReference getScxmlTransitionType_Raise();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getIf
     * <em>If</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>If</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getIf()
     * @see #getScxmlTransitionType()
     * @generated
     */
    EReference getScxmlTransitionType_If();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getForeach
     * <em>Foreach</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Foreach</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getForeach()
     * @see #getScxmlTransitionType()
     * @generated
     */
    EReference getScxmlTransitionType_Foreach();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getSend
     * <em>Send</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Send</em>
     *         '.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getSend()
     * @see #getScxmlTransitionType()
     * @generated
     */
    EReference getScxmlTransitionType_Send();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getScript
     * <em>Script</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Script</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getScript()
     * @see #getScxmlTransitionType()
     * @generated
     */
    EReference getScxmlTransitionType_Script();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getAssign
     * <em>Assign</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Assign</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getAssign()
     * @see #getScxmlTransitionType()
     * @generated
     */
    EReference getScxmlTransitionType_Assign();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getLog
     * <em>Log</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Log</em>
     *         '.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getLog()
     * @see #getScxmlTransitionType()
     * @generated
     */
    EReference getScxmlTransitionType_Log();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getCancel
     * <em>Cancel</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Cancel</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getCancel()
     * @see #getScxmlTransitionType()
     * @generated
     */
    EReference getScxmlTransitionType_Cancel();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getCond
     * <em>Cond</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Cond</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getCond()
     * @see #getScxmlTransitionType()
     * @generated
     */
    EAttribute getScxmlTransitionType_Cond();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getEvent
     * <em>Event</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Event</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getEvent()
     * @see #getScxmlTransitionType()
     * @generated
     */
    EAttribute getScxmlTransitionType_Event();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getTarget
     * <em>Target</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Target</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getTarget()
     * @see #getScxmlTransitionType()
     * @generated
     */
    EAttribute getScxmlTransitionType_Target();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getType
     * <em>Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getType()
     * @see #getScxmlTransitionType()
     * @generated
     */
    EAttribute getScxmlTransitionType_Type();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getAnyAttribute
     * <em>Any Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Any Attribute</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getAnyAttribute()
     * @see #getScxmlTransitionType()
     * @generated
     */
    EAttribute getScxmlTransitionType_AnyAttribute();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.tests.sample.scxml.AssignTypeDatatype
     * <em>Assign Type Datatype</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for enum '<em>Assign Type Datatype</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.AssignTypeDatatype
     * @generated
     */
    EEnum getAssignTypeDatatype();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.tests.sample.scxml.BindingDatatype
     * <em>Binding Datatype</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for enum '<em>Binding Datatype</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.BindingDatatype
     * @generated
     */
    EEnum getBindingDatatype();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.tests.sample.scxml.BooleanDatatype
     * <em>Boolean Datatype</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for enum '<em>Boolean Datatype</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.BooleanDatatype
     * @generated
     */
    EEnum getBooleanDatatype();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.tests.sample.scxml.ExmodeDatatype
     * <em>Exmode Datatype</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for enum '<em>Exmode Datatype</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ExmodeDatatype
     * @generated
     */
    EEnum getExmodeDatatype();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.tests.sample.scxml.HistoryTypeDatatype
     * <em>History Type Datatype</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for enum '<em>History Type Datatype</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.HistoryTypeDatatype
     * @generated
     */
    EEnum getHistoryTypeDatatype();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.tests.sample.scxml.TransitionTypeDatatype
     * <em>Transition Type Datatype</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for enum '<em>Transition Type Datatype</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.TransitionTypeDatatype
     * @generated
     */
    EEnum getTransitionTypeDatatype();

    /**
     * Returns the meta object for data type '
     * {@link org.eclipse.sirius.tests.sample.scxml.AssignTypeDatatype
     * <em>Assign Type Datatype Object</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for data type '
     *         <em>Assign Type Datatype Object</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.AssignTypeDatatype
     * @model
     *        instanceClass="org.eclipse.sirius.tests.sample.scxml.AssignTypeDatatype"
     *        extendedMetaData=
     *        "name='AssignType.datatype:Object' baseType='AssignType.datatype'"
     * @generated
     */
    EDataType getAssignTypeDatatypeObject();

    /**
     * Returns the meta object for data type '
     * {@link org.eclipse.sirius.tests.sample.scxml.BindingDatatype
     * <em>Binding Datatype Object</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for data type '<em>Binding Datatype Object</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.BindingDatatype
     * @model
     *        instanceClass="org.eclipse.sirius.tests.sample.scxml.BindingDatatype"
     *        extendedMetaData
     *        ="name='Binding.datatype:Object' baseType='Binding.datatype'"
     * @generated
     */
    EDataType getBindingDatatypeObject();

    /**
     * Returns the meta object for data type '
     * {@link org.eclipse.sirius.tests.sample.scxml.BooleanDatatype
     * <em>Boolean Datatype Object</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for data type '<em>Boolean Datatype Object</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.BooleanDatatype
     * @model
     *        instanceClass="org.eclipse.sirius.tests.sample.scxml.BooleanDatatype"
     *        extendedMetaData
     *        ="name='Boolean.datatype:Object' baseType='Boolean.datatype'"
     * @generated
     */
    EDataType getBooleanDatatypeObject();

    /**
     * Returns the meta object for data type '{@link java.lang.String
     * <em>Cond Lang Datatype</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for data type '<em>Cond Lang Datatype</em>'.
     * @see java.lang.String
     * @model instanceClass="java.lang.String" extendedMetaData=
     *        "name='CondLang.datatype' baseType='http://www.eclipse.org/emf/2003/XMLType#string'"
     * @generated
     */
    EDataType getCondLangDatatype();

    /**
     * Returns the meta object for data type '{@link java.lang.String
     * <em>Duration Datatype</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for data type '<em>Duration Datatype</em>'.
     * @see java.lang.String
     * @model instanceClass="java.lang.String" extendedMetaData=
     *        "name='Duration.datatype' baseType='http://www.eclipse.org/emf/2003/XMLType#string' pattern='\\d*(\\.\\d+)?(ms|s|m|h|d)'"
     * @generated
     */
    EDataType getDurationDatatype();

    /**
     * Returns the meta object for data type '{@link java.lang.String
     * <em>Event Type Datatype</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for data type '<em>Event Type Datatype</em>'.
     * @see java.lang.String
     * @model instanceClass="java.lang.String" extendedMetaData=
     *        "name='EventType.datatype' baseType='http://www.eclipse.org/emf/2003/XMLType#token' pattern='(\\i|\\d|\\-)+(\\.(\\i|\\d|\\-)+)*'"
     * @generated
     */
    EDataType getEventTypeDatatype();

    /**
     * Returns the meta object for data type '{@link java.lang.String
     * <em>Event Types Datatype</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for data type '<em>Event Types Datatype</em>'.
     * @see java.lang.String
     * @model instanceClass="java.lang.String" extendedMetaData=
     *        "name='EventTypes.datatype' baseType='http://www.eclipse.org/emf/2003/XMLType#token' pattern='\\.?\\*|(\\i|\\d|\\-)+(\\.(\\i|\\d|\\-)+)*(\\.\\*)?(\\s(\\i|\\d|\\-)+(\\.(\\i|\\d|\\-)+)*(\\.\\*)?)*'"
     * @generated
     */
    EDataType getEventTypesDatatype();

    /**
     * Returns the meta object for data type '
     * {@link org.eclipse.sirius.tests.sample.scxml.ExmodeDatatype
     * <em>Exmode Datatype Object</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for data type '<em>Exmode Datatype Object</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.ExmodeDatatype
     * @model
     *        instanceClass="org.eclipse.sirius.tests.sample.scxml.ExmodeDatatype"
     *        extendedMetaData
     *        ="name='Exmode.datatype:Object' baseType='Exmode.datatype'"
     * @generated
     */
    EDataType getExmodeDatatypeObject();

    /**
     * Returns the meta object for data type '
     * {@link org.eclipse.sirius.tests.sample.scxml.HistoryTypeDatatype
     * <em>History Type Datatype Object</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for data type '
     *         <em>History Type Datatype Object</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.HistoryTypeDatatype
     * @model instanceClass=
     *        "org.eclipse.sirius.tests.sample.scxml.HistoryTypeDatatype"
     *        extendedMetaData=
     *        "name='HistoryType.datatype:Object' baseType='HistoryType.datatype'"
     * @generated
     */
    EDataType getHistoryTypeDatatypeObject();

    /**
     * Returns the meta object for data type '{@link java.math.BigInteger
     * <em>Integer Datatype</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for data type '<em>Integer Datatype</em>'.
     * @see java.math.BigInteger
     * @model instanceClass="java.math.BigInteger" extendedMetaData=
     *        "name='Integer.datatype' baseType='http://www.eclipse.org/emf/2003/XMLType#nonNegativeInteger'"
     * @generated
     */
    EDataType getIntegerDatatype();

    /**
     * Returns the meta object for data type '{@link java.lang.String
     * <em>Loc Lang Datatype</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for data type '<em>Loc Lang Datatype</em>'.
     * @see java.lang.String
     * @model instanceClass="java.lang.String" extendedMetaData=
     *        "name='LocLang.datatype' baseType='http://www.eclipse.org/emf/2003/XMLType#string'"
     * @generated
     */
    EDataType getLocLangDatatype();

    /**
     * Returns the meta object for data type '
     * {@link org.eclipse.sirius.tests.sample.scxml.TransitionTypeDatatype
     * <em>Transition Type Datatype Object</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for data type '
     *         <em>Transition Type Datatype Object</em>'.
     * @see org.eclipse.sirius.tests.sample.scxml.TransitionTypeDatatype
     * @model instanceClass=
     *        "org.eclipse.sirius.tests.sample.scxml.TransitionTypeDatatype"
     *        extendedMetaData=
     *        "name='TransitionType.datatype:Object' baseType='TransitionType.datatype'"
     * @generated
     */
    EDataType getTransitionTypeDatatypeObject();

    /**
     * Returns the meta object for data type '{@link java.lang.String
     * <em>URI Datatype</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for data type '<em>URI Datatype</em>'.
     * @see java.lang.String
     * @model instanceClass="java.lang.String" extendedMetaData=
     *        "name='URI.datatype' baseType='http://www.eclipse.org/emf/2003/XMLType#anyURI'"
     * @generated
     */
    EDataType getURIDatatype();

    /**
     * Returns the meta object for data type '{@link java.lang.String
     * <em>Value Lang Datatype</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for data type '<em>Value Lang Datatype</em>'.
     * @see java.lang.String
     * @model instanceClass="java.lang.String" extendedMetaData=
     *        "name='ValueLang.datatype' baseType='http://www.eclipse.org/emf/2003/XMLType#string'"
     * @generated
     */
    EDataType getValueLangDatatype();

    /**
     * Returns the factory that creates the instances of the model. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    ScxmlFactory getScxmlFactory();

    /**
     * <!-- begin-user-doc --> Defines literals for the meta objects that
     * represent
     * <ul>
     * <li>each class,</li>
     * <li>each feature of each class,</li>
     * <li>each operation of each class,</li>
     * <li>each enum,</li>
     * <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     *
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.scxml.impl.DocumentRootImpl
         * <em>Document Root</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.scxml.impl.DocumentRootImpl
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getDocumentRoot()
         * @generated
         */
        EClass DOCUMENT_ROOT = ScxmlPackage.eINSTANCE.getDocumentRoot();

        /**
         * The meta object literal for the '<em><b>Mixed</b></em>' attribute
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DOCUMENT_ROOT__MIXED = ScxmlPackage.eINSTANCE.getDocumentRoot_Mixed();

        /**
         * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>'
         * map feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DOCUMENT_ROOT__XMLNS_PREFIX_MAP = ScxmlPackage.eINSTANCE.getDocumentRoot_XMLNSPrefixMap();

        /**
         * The meta object literal for the '<em><b>XSI Schema Location</b></em>'
         * map feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = ScxmlPackage.eINSTANCE.getDocumentRoot_XSISchemaLocation();

        /**
         * The meta object literal for the '<em><b>Assign</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DOCUMENT_ROOT__ASSIGN = ScxmlPackage.eINSTANCE.getDocumentRoot_Assign();

        /**
         * The meta object literal for the '<em><b>Cancel</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DOCUMENT_ROOT__CANCEL = ScxmlPackage.eINSTANCE.getDocumentRoot_Cancel();

        /**
         * The meta object literal for the '<em><b>Content</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DOCUMENT_ROOT__CONTENT = ScxmlPackage.eINSTANCE.getDocumentRoot_Content();

        /**
         * The meta object literal for the '<em><b>Data</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DOCUMENT_ROOT__DATA = ScxmlPackage.eINSTANCE.getDocumentRoot_Data();

        /**
         * The meta object literal for the '<em><b>Datamodel</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference DOCUMENT_ROOT__DATAMODEL = ScxmlPackage.eINSTANCE.getDocumentRoot_Datamodel();

        /**
         * The meta object literal for the '<em><b>Donedata</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference DOCUMENT_ROOT__DONEDATA = ScxmlPackage.eINSTANCE.getDocumentRoot_Donedata();

        /**
         * The meta object literal for the '<em><b>Else</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DOCUMENT_ROOT__ELSE = ScxmlPackage.eINSTANCE.getDocumentRoot_Else();

        /**
         * The meta object literal for the '<em><b>Elseif</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DOCUMENT_ROOT__ELSEIF = ScxmlPackage.eINSTANCE.getDocumentRoot_Elseif();

        /**
         * The meta object literal for the '<em><b>Final</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DOCUMENT_ROOT__FINAL = ScxmlPackage.eINSTANCE.getDocumentRoot_Final();

        /**
         * The meta object literal for the '<em><b>Finalize</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference DOCUMENT_ROOT__FINALIZE = ScxmlPackage.eINSTANCE.getDocumentRoot_Finalize();

        /**
         * The meta object literal for the '<em><b>Foreach</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DOCUMENT_ROOT__FOREACH = ScxmlPackage.eINSTANCE.getDocumentRoot_Foreach();

        /**
         * The meta object literal for the '<em><b>History</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DOCUMENT_ROOT__HISTORY = ScxmlPackage.eINSTANCE.getDocumentRoot_History();

        /**
         * The meta object literal for the '<em><b>If</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DOCUMENT_ROOT__IF = ScxmlPackage.eINSTANCE.getDocumentRoot_If();

        /**
         * The meta object literal for the '<em><b>Initial</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DOCUMENT_ROOT__INITIAL = ScxmlPackage.eINSTANCE.getDocumentRoot_Initial();

        /**
         * The meta object literal for the '<em><b>Invoke</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DOCUMENT_ROOT__INVOKE = ScxmlPackage.eINSTANCE.getDocumentRoot_Invoke();

        /**
         * The meta object literal for the '<em><b>Log</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DOCUMENT_ROOT__LOG = ScxmlPackage.eINSTANCE.getDocumentRoot_Log();

        /**
         * The meta object literal for the '<em><b>Onentry</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DOCUMENT_ROOT__ONENTRY = ScxmlPackage.eINSTANCE.getDocumentRoot_Onentry();

        /**
         * The meta object literal for the '<em><b>Onexit</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DOCUMENT_ROOT__ONEXIT = ScxmlPackage.eINSTANCE.getDocumentRoot_Onexit();

        /**
         * The meta object literal for the '<em><b>Parallel</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference DOCUMENT_ROOT__PARALLEL = ScxmlPackage.eINSTANCE.getDocumentRoot_Parallel();

        /**
         * The meta object literal for the '<em><b>Param</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DOCUMENT_ROOT__PARAM = ScxmlPackage.eINSTANCE.getDocumentRoot_Param();

        /**
         * The meta object literal for the '<em><b>Raise</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DOCUMENT_ROOT__RAISE = ScxmlPackage.eINSTANCE.getDocumentRoot_Raise();

        /**
         * The meta object literal for the '<em><b>Script</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DOCUMENT_ROOT__SCRIPT = ScxmlPackage.eINSTANCE.getDocumentRoot_Script();

        /**
         * The meta object literal for the '<em><b>Scxml</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DOCUMENT_ROOT__SCXML = ScxmlPackage.eINSTANCE.getDocumentRoot_Scxml();

        /**
         * The meta object literal for the '<em><b>Send</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DOCUMENT_ROOT__SEND = ScxmlPackage.eINSTANCE.getDocumentRoot_Send();

        /**
         * The meta object literal for the '<em><b>State</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DOCUMENT_ROOT__STATE = ScxmlPackage.eINSTANCE.getDocumentRoot_State();

        /**
         * The meta object literal for the '<em><b>Transition</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference DOCUMENT_ROOT__TRANSITION = ScxmlPackage.eINSTANCE.getDocumentRoot_Transition();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlAssignTypeImpl
         * <em>Assign Type</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlAssignTypeImpl
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlAssignType()
         * @generated
         */
        EClass SCXML_ASSIGN_TYPE = ScxmlPackage.eINSTANCE.getScxmlAssignType();

        /**
         * The meta object literal for the '<em><b>Mixed</b></em>' attribute
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_ASSIGN_TYPE__MIXED = ScxmlPackage.eINSTANCE.getScxmlAssignType_Mixed();

        /**
         * The meta object literal for the '<em><b>Any</b></em>' attribute list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_ASSIGN_TYPE__ANY = ScxmlPackage.eINSTANCE.getScxmlAssignType_Any();

        /**
         * The meta object literal for the '<em><b>Attr</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_ASSIGN_TYPE__ATTR = ScxmlPackage.eINSTANCE.getScxmlAssignType_Attr();

        /**
         * The meta object literal for the '<em><b>Expr</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_ASSIGN_TYPE__EXPR = ScxmlPackage.eINSTANCE.getScxmlAssignType_Expr();

        /**
         * The meta object literal for the '<em><b>Location</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_ASSIGN_TYPE__LOCATION = ScxmlPackage.eINSTANCE.getScxmlAssignType_Location();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_ASSIGN_TYPE__TYPE = ScxmlPackage.eINSTANCE.getScxmlAssignType_Type();

        /**
         * The meta object literal for the '<em><b>Any Attribute</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_ASSIGN_TYPE__ANY_ATTRIBUTE = ScxmlPackage.eINSTANCE.getScxmlAssignType_AnyAttribute();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlCancelTypeImpl
         * <em>Cancel Type</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlCancelTypeImpl
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlCancelType()
         * @generated
         */
        EClass SCXML_CANCEL_TYPE = ScxmlPackage.eINSTANCE.getScxmlCancelType();

        /**
         * The meta object literal for the '<em><b>Scxml Extra Content</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_CANCEL_TYPE__SCXML_EXTRA_CONTENT = ScxmlPackage.eINSTANCE.getScxmlCancelType_ScxmlExtraContent();

        /**
         * The meta object literal for the '<em><b>Any</b></em>' attribute list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_CANCEL_TYPE__ANY = ScxmlPackage.eINSTANCE.getScxmlCancelType_Any();

        /**
         * The meta object literal for the '<em><b>Sendid</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_CANCEL_TYPE__SENDID = ScxmlPackage.eINSTANCE.getScxmlCancelType_Sendid();

        /**
         * The meta object literal for the '<em><b>Sendidexpr</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_CANCEL_TYPE__SENDIDEXPR = ScxmlPackage.eINSTANCE.getScxmlCancelType_Sendidexpr();

        /**
         * The meta object literal for the '<em><b>Any Attribute</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_CANCEL_TYPE__ANY_ATTRIBUTE = ScxmlPackage.eINSTANCE.getScxmlCancelType_AnyAttribute();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlContentTypeImpl
         * <em>Content Type</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlContentTypeImpl
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlContentType()
         * @generated
         */
        EClass SCXML_CONTENT_TYPE = ScxmlPackage.eINSTANCE.getScxmlContentType();

        /**
         * The meta object literal for the '<em><b>Mixed</b></em>' attribute
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_CONTENT_TYPE__MIXED = ScxmlPackage.eINSTANCE.getScxmlContentType_Mixed();

        /**
         * The meta object literal for the '<em><b>Any</b></em>' attribute list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_CONTENT_TYPE__ANY = ScxmlPackage.eINSTANCE.getScxmlContentType_Any();

        /**
         * The meta object literal for the '<em><b>Expr</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_CONTENT_TYPE__EXPR = ScxmlPackage.eINSTANCE.getScxmlContentType_Expr();

        /**
         * The meta object literal for the '<em><b>Any Attribute</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_CONTENT_TYPE__ANY_ATTRIBUTE = ScxmlPackage.eINSTANCE.getScxmlContentType_AnyAttribute();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlDatamodelTypeImpl
         * <em>Datamodel Type</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlDatamodelTypeImpl
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlDatamodelType()
         * @generated
         */
        EClass SCXML_DATAMODEL_TYPE = ScxmlPackage.eINSTANCE.getScxmlDatamodelType();

        /**
         * The meta object literal for the '<em><b>Data</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_DATAMODEL_TYPE__DATA = ScxmlPackage.eINSTANCE.getScxmlDatamodelType_Data();

        /**
         * The meta object literal for the '<em><b>Scxml Extra Content</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_DATAMODEL_TYPE__SCXML_EXTRA_CONTENT = ScxmlPackage.eINSTANCE.getScxmlDatamodelType_ScxmlExtraContent();

        /**
         * The meta object literal for the '<em><b>Any</b></em>' attribute list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_DATAMODEL_TYPE__ANY = ScxmlPackage.eINSTANCE.getScxmlDatamodelType_Any();

        /**
         * The meta object literal for the '<em><b>Any Attribute</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_DATAMODEL_TYPE__ANY_ATTRIBUTE = ScxmlPackage.eINSTANCE.getScxmlDatamodelType_AnyAttribute();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlDataTypeImpl
         * <em>Data Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlDataTypeImpl
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlDataType()
         * @generated
         */
        EClass SCXML_DATA_TYPE = ScxmlPackage.eINSTANCE.getScxmlDataType();

        /**
         * The meta object literal for the '<em><b>Mixed</b></em>' attribute
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_DATA_TYPE__MIXED = ScxmlPackage.eINSTANCE.getScxmlDataType_Mixed();

        /**
         * The meta object literal for the '<em><b>Any</b></em>' attribute list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_DATA_TYPE__ANY = ScxmlPackage.eINSTANCE.getScxmlDataType_Any();

        /**
         * The meta object literal for the '<em><b>Expr</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_DATA_TYPE__EXPR = ScxmlPackage.eINSTANCE.getScxmlDataType_Expr();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_DATA_TYPE__ID = ScxmlPackage.eINSTANCE.getScxmlDataType_Id();

        /**
         * The meta object literal for the '<em><b>Src</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_DATA_TYPE__SRC = ScxmlPackage.eINSTANCE.getScxmlDataType_Src();

        /**
         * The meta object literal for the '<em><b>Any Attribute</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_DATA_TYPE__ANY_ATTRIBUTE = ScxmlPackage.eINSTANCE.getScxmlDataType_AnyAttribute();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlDonedataTypeImpl
         * <em>Donedata Type</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlDonedataTypeImpl
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlDonedataType()
         * @generated
         */
        EClass SCXML_DONEDATA_TYPE = ScxmlPackage.eINSTANCE.getScxmlDonedataType();

        /**
         * The meta object literal for the '<em><b>Content</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_DONEDATA_TYPE__CONTENT = ScxmlPackage.eINSTANCE.getScxmlDonedataType_Content();

        /**
         * The meta object literal for the '<em><b>Param</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_DONEDATA_TYPE__PARAM = ScxmlPackage.eINSTANCE.getScxmlDonedataType_Param();

        /**
         * The meta object literal for the '<em><b>Any Attribute</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_DONEDATA_TYPE__ANY_ATTRIBUTE = ScxmlPackage.eINSTANCE.getScxmlDonedataType_AnyAttribute();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlElseifTypeImpl
         * <em>Elseif Type</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlElseifTypeImpl
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlElseifType()
         * @generated
         */
        EClass SCXML_ELSEIF_TYPE = ScxmlPackage.eINSTANCE.getScxmlElseifType();

        /**
         * The meta object literal for the '<em><b>Cond</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_ELSEIF_TYPE__COND = ScxmlPackage.eINSTANCE.getScxmlElseifType_Cond();

        /**
         * The meta object literal for the '<em><b>Any Attribute</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_ELSEIF_TYPE__ANY_ATTRIBUTE = ScxmlPackage.eINSTANCE.getScxmlElseifType_AnyAttribute();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlElseTypeImpl
         * <em>Else Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlElseTypeImpl
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlElseType()
         * @generated
         */
        EClass SCXML_ELSE_TYPE = ScxmlPackage.eINSTANCE.getScxmlElseType();

        /**
         * The meta object literal for the '<em><b>Any Attribute</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_ELSE_TYPE__ANY_ATTRIBUTE = ScxmlPackage.eINSTANCE.getScxmlElseType_AnyAttribute();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlFinalizeTypeImpl
         * <em>Finalize Type</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlFinalizeTypeImpl
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlFinalizeType()
         * @generated
         */
        EClass SCXML_FINALIZE_TYPE = ScxmlPackage.eINSTANCE.getScxmlFinalizeType();

        /**
         * The meta object literal for the '
         * <em><b>Scxml Core Executablecontent</b></em>' attribute list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_FINALIZE_TYPE__SCXML_CORE_EXECUTABLECONTENT = ScxmlPackage.eINSTANCE.getScxmlFinalizeType_ScxmlCoreExecutablecontent();

        /**
         * The meta object literal for the '<em><b>Any</b></em>' attribute list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_FINALIZE_TYPE__ANY = ScxmlPackage.eINSTANCE.getScxmlFinalizeType_Any();

        /**
         * The meta object literal for the '<em><b>Raise</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_FINALIZE_TYPE__RAISE = ScxmlPackage.eINSTANCE.getScxmlFinalizeType_Raise();

        /**
         * The meta object literal for the '<em><b>If</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_FINALIZE_TYPE__IF = ScxmlPackage.eINSTANCE.getScxmlFinalizeType_If();

        /**
         * The meta object literal for the '<em><b>Foreach</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_FINALIZE_TYPE__FOREACH = ScxmlPackage.eINSTANCE.getScxmlFinalizeType_Foreach();

        /**
         * The meta object literal for the '<em><b>Send</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_FINALIZE_TYPE__SEND = ScxmlPackage.eINSTANCE.getScxmlFinalizeType_Send();

        /**
         * The meta object literal for the '<em><b>Script</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_FINALIZE_TYPE__SCRIPT = ScxmlPackage.eINSTANCE.getScxmlFinalizeType_Script();

        /**
         * The meta object literal for the '<em><b>Assign</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_FINALIZE_TYPE__ASSIGN = ScxmlPackage.eINSTANCE.getScxmlFinalizeType_Assign();

        /**
         * The meta object literal for the '<em><b>Log</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_FINALIZE_TYPE__LOG = ScxmlPackage.eINSTANCE.getScxmlFinalizeType_Log();

        /**
         * The meta object literal for the '<em><b>Cancel</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_FINALIZE_TYPE__CANCEL = ScxmlPackage.eINSTANCE.getScxmlFinalizeType_Cancel();

        /**
         * The meta object literal for the '<em><b>Any Attribute</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_FINALIZE_TYPE__ANY_ATTRIBUTE = ScxmlPackage.eINSTANCE.getScxmlFinalizeType_AnyAttribute();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlFinalTypeImpl
         * <em>Final Type</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlFinalTypeImpl
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlFinalType()
         * @generated
         */
        EClass SCXML_FINAL_TYPE = ScxmlPackage.eINSTANCE.getScxmlFinalType();

        /**
         * The meta object literal for the '<em><b>Scxml Final Mix</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_FINAL_TYPE__SCXML_FINAL_MIX = ScxmlPackage.eINSTANCE.getScxmlFinalType_ScxmlFinalMix();

        /**
         * The meta object literal for the '<em><b>Onentry</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_FINAL_TYPE__ONENTRY = ScxmlPackage.eINSTANCE.getScxmlFinalType_Onentry();

        /**
         * The meta object literal for the '<em><b>Onexit</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_FINAL_TYPE__ONEXIT = ScxmlPackage.eINSTANCE.getScxmlFinalType_Onexit();

        /**
         * The meta object literal for the '<em><b>Donedata</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_FINAL_TYPE__DONEDATA = ScxmlPackage.eINSTANCE.getScxmlFinalType_Donedata();

        /**
         * The meta object literal for the '<em><b>Any</b></em>' attribute list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_FINAL_TYPE__ANY = ScxmlPackage.eINSTANCE.getScxmlFinalType_Any();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_FINAL_TYPE__ID = ScxmlPackage.eINSTANCE.getScxmlFinalType_Id();

        /**
         * The meta object literal for the '<em><b>Any Attribute</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_FINAL_TYPE__ANY_ATTRIBUTE = ScxmlPackage.eINSTANCE.getScxmlFinalType_AnyAttribute();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlForeachTypeImpl
         * <em>Foreach Type</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlForeachTypeImpl
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlForeachType()
         * @generated
         */
        EClass SCXML_FOREACH_TYPE = ScxmlPackage.eINSTANCE.getScxmlForeachType();

        /**
         * The meta object literal for the '
         * <em><b>Scxml Core Executablecontent</b></em>' attribute list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_FOREACH_TYPE__SCXML_CORE_EXECUTABLECONTENT = ScxmlPackage.eINSTANCE.getScxmlForeachType_ScxmlCoreExecutablecontent();

        /**
         * The meta object literal for the '<em><b>Any</b></em>' attribute list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_FOREACH_TYPE__ANY = ScxmlPackage.eINSTANCE.getScxmlForeachType_Any();

        /**
         * The meta object literal for the '<em><b>Raise</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_FOREACH_TYPE__RAISE = ScxmlPackage.eINSTANCE.getScxmlForeachType_Raise();

        /**
         * The meta object literal for the '<em><b>If</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_FOREACH_TYPE__IF = ScxmlPackage.eINSTANCE.getScxmlForeachType_If();

        /**
         * The meta object literal for the '<em><b>Foreach</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_FOREACH_TYPE__FOREACH = ScxmlPackage.eINSTANCE.getScxmlForeachType_Foreach();

        /**
         * The meta object literal for the '<em><b>Send</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_FOREACH_TYPE__SEND = ScxmlPackage.eINSTANCE.getScxmlForeachType_Send();

        /**
         * The meta object literal for the '<em><b>Script</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_FOREACH_TYPE__SCRIPT = ScxmlPackage.eINSTANCE.getScxmlForeachType_Script();

        /**
         * The meta object literal for the '<em><b>Assign</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_FOREACH_TYPE__ASSIGN = ScxmlPackage.eINSTANCE.getScxmlForeachType_Assign();

        /**
         * The meta object literal for the '<em><b>Log</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_FOREACH_TYPE__LOG = ScxmlPackage.eINSTANCE.getScxmlForeachType_Log();

        /**
         * The meta object literal for the '<em><b>Cancel</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_FOREACH_TYPE__CANCEL = ScxmlPackage.eINSTANCE.getScxmlForeachType_Cancel();

        /**
         * The meta object literal for the '<em><b>Array</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_FOREACH_TYPE__ARRAY = ScxmlPackage.eINSTANCE.getScxmlForeachType_Array();

        /**
         * The meta object literal for the '<em><b>Index</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_FOREACH_TYPE__INDEX = ScxmlPackage.eINSTANCE.getScxmlForeachType_Index();

        /**
         * The meta object literal for the '<em><b>Item</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_FOREACH_TYPE__ITEM = ScxmlPackage.eINSTANCE.getScxmlForeachType_Item();

        /**
         * The meta object literal for the '<em><b>Any Attribute</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_FOREACH_TYPE__ANY_ATTRIBUTE = ScxmlPackage.eINSTANCE.getScxmlForeachType_AnyAttribute();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlHistoryTypeImpl
         * <em>History Type</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlHistoryTypeImpl
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlHistoryType()
         * @generated
         */
        EClass SCXML_HISTORY_TYPE = ScxmlPackage.eINSTANCE.getScxmlHistoryType();

        /**
         * The meta object literal for the '<em><b>Scxml Extra Content</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_HISTORY_TYPE__SCXML_EXTRA_CONTENT = ScxmlPackage.eINSTANCE.getScxmlHistoryType_ScxmlExtraContent();

        /**
         * The meta object literal for the '<em><b>Any</b></em>' attribute list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_HISTORY_TYPE__ANY = ScxmlPackage.eINSTANCE.getScxmlHistoryType_Any();

        /**
         * The meta object literal for the '<em><b>Transition</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_HISTORY_TYPE__TRANSITION = ScxmlPackage.eINSTANCE.getScxmlHistoryType_Transition();

        /**
         * The meta object literal for the '<em><b>Scxml Extra Content1</b></em>
         * ' attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @generated
         */
        EAttribute SCXML_HISTORY_TYPE__SCXML_EXTRA_CONTENT1 = ScxmlPackage.eINSTANCE.getScxmlHistoryType_ScxmlExtraContent1();

        /**
         * The meta object literal for the '<em><b>Any1</b></em>' attribute list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_HISTORY_TYPE__ANY1 = ScxmlPackage.eINSTANCE.getScxmlHistoryType_Any1();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_HISTORY_TYPE__ID = ScxmlPackage.eINSTANCE.getScxmlHistoryType_Id();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_HISTORY_TYPE__TYPE = ScxmlPackage.eINSTANCE.getScxmlHistoryType_Type();

        /**
         * The meta object literal for the '<em><b>Any Attribute</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_HISTORY_TYPE__ANY_ATTRIBUTE = ScxmlPackage.eINSTANCE.getScxmlHistoryType_AnyAttribute();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlIfTypeImpl
         * <em>If Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlIfTypeImpl
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlIfType()
         * @generated
         */
        EClass SCXML_IF_TYPE = ScxmlPackage.eINSTANCE.getScxmlIfType();

        /**
         * The meta object literal for the '
         * <em><b>Scxml Core Executablecontent</b></em>' attribute list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_IF_TYPE__SCXML_CORE_EXECUTABLECONTENT = ScxmlPackage.eINSTANCE.getScxmlIfType_ScxmlCoreExecutablecontent();

        /**
         * The meta object literal for the '<em><b>Any</b></em>' attribute list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_IF_TYPE__ANY = ScxmlPackage.eINSTANCE.getScxmlIfType_Any();

        /**
         * The meta object literal for the '<em><b>Raise</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_IF_TYPE__RAISE = ScxmlPackage.eINSTANCE.getScxmlIfType_Raise();

        /**
         * The meta object literal for the '<em><b>If</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_IF_TYPE__IF = ScxmlPackage.eINSTANCE.getScxmlIfType_If();

        /**
         * The meta object literal for the '<em><b>Foreach</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_IF_TYPE__FOREACH = ScxmlPackage.eINSTANCE.getScxmlIfType_Foreach();

        /**
         * The meta object literal for the '<em><b>Send</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_IF_TYPE__SEND = ScxmlPackage.eINSTANCE.getScxmlIfType_Send();

        /**
         * The meta object literal for the '<em><b>Script</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_IF_TYPE__SCRIPT = ScxmlPackage.eINSTANCE.getScxmlIfType_Script();

        /**
         * The meta object literal for the '<em><b>Assign</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_IF_TYPE__ASSIGN = ScxmlPackage.eINSTANCE.getScxmlIfType_Assign();

        /**
         * The meta object literal for the '<em><b>Log</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_IF_TYPE__LOG = ScxmlPackage.eINSTANCE.getScxmlIfType_Log();

        /**
         * The meta object literal for the '<em><b>Cancel</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_IF_TYPE__CANCEL = ScxmlPackage.eINSTANCE.getScxmlIfType_Cancel();

        /**
         * The meta object literal for the '<em><b>Elseif</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_IF_TYPE__ELSEIF = ScxmlPackage.eINSTANCE.getScxmlIfType_Elseif();

        /**
         * The meta object literal for the '
         * <em><b>Scxml Core Executablecontent1</b></em>' attribute list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_IF_TYPE__SCXML_CORE_EXECUTABLECONTENT1 = ScxmlPackage.eINSTANCE.getScxmlIfType_ScxmlCoreExecutablecontent1();

        /**
         * The meta object literal for the '<em><b>Any1</b></em>' attribute list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_IF_TYPE__ANY1 = ScxmlPackage.eINSTANCE.getScxmlIfType_Any1();

        /**
         * The meta object literal for the '<em><b>Raise1</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_IF_TYPE__RAISE1 = ScxmlPackage.eINSTANCE.getScxmlIfType_Raise1();

        /**
         * The meta object literal for the '<em><b>If1</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_IF_TYPE__IF1 = ScxmlPackage.eINSTANCE.getScxmlIfType_If1();

        /**
         * The meta object literal for the '<em><b>Foreach1</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_IF_TYPE__FOREACH1 = ScxmlPackage.eINSTANCE.getScxmlIfType_Foreach1();

        /**
         * The meta object literal for the '<em><b>Send1</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_IF_TYPE__SEND1 = ScxmlPackage.eINSTANCE.getScxmlIfType_Send1();

        /**
         * The meta object literal for the '<em><b>Script1</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_IF_TYPE__SCRIPT1 = ScxmlPackage.eINSTANCE.getScxmlIfType_Script1();

        /**
         * The meta object literal for the '<em><b>Assign1</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_IF_TYPE__ASSIGN1 = ScxmlPackage.eINSTANCE.getScxmlIfType_Assign1();

        /**
         * The meta object literal for the '<em><b>Log1</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_IF_TYPE__LOG1 = ScxmlPackage.eINSTANCE.getScxmlIfType_Log1();

        /**
         * The meta object literal for the '<em><b>Cancel1</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_IF_TYPE__CANCEL1 = ScxmlPackage.eINSTANCE.getScxmlIfType_Cancel1();

        /**
         * The meta object literal for the '<em><b>Else</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_IF_TYPE__ELSE = ScxmlPackage.eINSTANCE.getScxmlIfType_Else();

        /**
         * The meta object literal for the '
         * <em><b>Scxml Core Executablecontent2</b></em>' attribute list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_IF_TYPE__SCXML_CORE_EXECUTABLECONTENT2 = ScxmlPackage.eINSTANCE.getScxmlIfType_ScxmlCoreExecutablecontent2();

        /**
         * The meta object literal for the '<em><b>Any2</b></em>' attribute list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_IF_TYPE__ANY2 = ScxmlPackage.eINSTANCE.getScxmlIfType_Any2();

        /**
         * The meta object literal for the '<em><b>Raise2</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_IF_TYPE__RAISE2 = ScxmlPackage.eINSTANCE.getScxmlIfType_Raise2();

        /**
         * The meta object literal for the '<em><b>If2</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_IF_TYPE__IF2 = ScxmlPackage.eINSTANCE.getScxmlIfType_If2();

        /**
         * The meta object literal for the '<em><b>Foreach2</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_IF_TYPE__FOREACH2 = ScxmlPackage.eINSTANCE.getScxmlIfType_Foreach2();

        /**
         * The meta object literal for the '<em><b>Send2</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_IF_TYPE__SEND2 = ScxmlPackage.eINSTANCE.getScxmlIfType_Send2();

        /**
         * The meta object literal for the '<em><b>Script2</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_IF_TYPE__SCRIPT2 = ScxmlPackage.eINSTANCE.getScxmlIfType_Script2();

        /**
         * The meta object literal for the '<em><b>Assign2</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_IF_TYPE__ASSIGN2 = ScxmlPackage.eINSTANCE.getScxmlIfType_Assign2();

        /**
         * The meta object literal for the '<em><b>Log2</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_IF_TYPE__LOG2 = ScxmlPackage.eINSTANCE.getScxmlIfType_Log2();

        /**
         * The meta object literal for the '<em><b>Cancel2</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_IF_TYPE__CANCEL2 = ScxmlPackage.eINSTANCE.getScxmlIfType_Cancel2();

        /**
         * The meta object literal for the '<em><b>Cond</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_IF_TYPE__COND = ScxmlPackage.eINSTANCE.getScxmlIfType_Cond();

        /**
         * The meta object literal for the '<em><b>Any Attribute</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_IF_TYPE__ANY_ATTRIBUTE = ScxmlPackage.eINSTANCE.getScxmlIfType_AnyAttribute();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlInitialTypeImpl
         * <em>Initial Type</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlInitialTypeImpl
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlInitialType()
         * @generated
         */
        EClass SCXML_INITIAL_TYPE = ScxmlPackage.eINSTANCE.getScxmlInitialType();

        /**
         * The meta object literal for the '<em><b>Scxml Extra Content</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_INITIAL_TYPE__SCXML_EXTRA_CONTENT = ScxmlPackage.eINSTANCE.getScxmlInitialType_ScxmlExtraContent();

        /**
         * The meta object literal for the '<em><b>Any</b></em>' attribute list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_INITIAL_TYPE__ANY = ScxmlPackage.eINSTANCE.getScxmlInitialType_Any();

        /**
         * The meta object literal for the '<em><b>Transition</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_INITIAL_TYPE__TRANSITION = ScxmlPackage.eINSTANCE.getScxmlInitialType_Transition();

        /**
         * The meta object literal for the '<em><b>Scxml Extra Content1</b></em>
         * ' attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @generated
         */
        EAttribute SCXML_INITIAL_TYPE__SCXML_EXTRA_CONTENT1 = ScxmlPackage.eINSTANCE.getScxmlInitialType_ScxmlExtraContent1();

        /**
         * The meta object literal for the '<em><b>Any1</b></em>' attribute list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_INITIAL_TYPE__ANY1 = ScxmlPackage.eINSTANCE.getScxmlInitialType_Any1();

        /**
         * The meta object literal for the '<em><b>Any Attribute</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_INITIAL_TYPE__ANY_ATTRIBUTE = ScxmlPackage.eINSTANCE.getScxmlInitialType_AnyAttribute();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlInvokeTypeImpl
         * <em>Invoke Type</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlInvokeTypeImpl
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlInvokeType()
         * @generated
         */
        EClass SCXML_INVOKE_TYPE = ScxmlPackage.eINSTANCE.getScxmlInvokeType();

        /**
         * The meta object literal for the '<em><b>Scxml Invoke Mix</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_INVOKE_TYPE__SCXML_INVOKE_MIX = ScxmlPackage.eINSTANCE.getScxmlInvokeType_ScxmlInvokeMix();

        /**
         * The meta object literal for the '<em><b>Content</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_INVOKE_TYPE__CONTENT = ScxmlPackage.eINSTANCE.getScxmlInvokeType_Content();

        /**
         * The meta object literal for the '<em><b>Param</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_INVOKE_TYPE__PARAM = ScxmlPackage.eINSTANCE.getScxmlInvokeType_Param();

        /**
         * The meta object literal for the '<em><b>Finalize</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_INVOKE_TYPE__FINALIZE = ScxmlPackage.eINSTANCE.getScxmlInvokeType_Finalize();

        /**
         * The meta object literal for the '<em><b>Any</b></em>' attribute list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_INVOKE_TYPE__ANY = ScxmlPackage.eINSTANCE.getScxmlInvokeType_Any();

        /**
         * The meta object literal for the '<em><b>Autoforward</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_INVOKE_TYPE__AUTOFORWARD = ScxmlPackage.eINSTANCE.getScxmlInvokeType_Autoforward();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_INVOKE_TYPE__ID = ScxmlPackage.eINSTANCE.getScxmlInvokeType_Id();

        /**
         * The meta object literal for the '<em><b>Idlocation</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_INVOKE_TYPE__IDLOCATION = ScxmlPackage.eINSTANCE.getScxmlInvokeType_Idlocation();

        /**
         * The meta object literal for the '<em><b>Namelist</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_INVOKE_TYPE__NAMELIST = ScxmlPackage.eINSTANCE.getScxmlInvokeType_Namelist();

        /**
         * The meta object literal for the '<em><b>Src</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_INVOKE_TYPE__SRC = ScxmlPackage.eINSTANCE.getScxmlInvokeType_Src();

        /**
         * The meta object literal for the '<em><b>Srcexpr</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_INVOKE_TYPE__SRCEXPR = ScxmlPackage.eINSTANCE.getScxmlInvokeType_Srcexpr();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_INVOKE_TYPE__TYPE = ScxmlPackage.eINSTANCE.getScxmlInvokeType_Type();

        /**
         * The meta object literal for the '<em><b>Typeexpr</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_INVOKE_TYPE__TYPEEXPR = ScxmlPackage.eINSTANCE.getScxmlInvokeType_Typeexpr();

        /**
         * The meta object literal for the '<em><b>Any Attribute</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_INVOKE_TYPE__ANY_ATTRIBUTE = ScxmlPackage.eINSTANCE.getScxmlInvokeType_AnyAttribute();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlLogTypeImpl
         * <em>Log Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlLogTypeImpl
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlLogType()
         * @generated
         */
        EClass SCXML_LOG_TYPE = ScxmlPackage.eINSTANCE.getScxmlLogType();

        /**
         * The meta object literal for the '<em><b>Scxml Extra Content</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_LOG_TYPE__SCXML_EXTRA_CONTENT = ScxmlPackage.eINSTANCE.getScxmlLogType_ScxmlExtraContent();

        /**
         * The meta object literal for the '<em><b>Any</b></em>' attribute list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_LOG_TYPE__ANY = ScxmlPackage.eINSTANCE.getScxmlLogType_Any();

        /**
         * The meta object literal for the '<em><b>Expr</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_LOG_TYPE__EXPR = ScxmlPackage.eINSTANCE.getScxmlLogType_Expr();

        /**
         * The meta object literal for the '<em><b>Label</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_LOG_TYPE__LABEL = ScxmlPackage.eINSTANCE.getScxmlLogType_Label();

        /**
         * The meta object literal for the '<em><b>Any Attribute</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_LOG_TYPE__ANY_ATTRIBUTE = ScxmlPackage.eINSTANCE.getScxmlLogType_AnyAttribute();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlOnentryTypeImpl
         * <em>Onentry Type</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlOnentryTypeImpl
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlOnentryType()
         * @generated
         */
        EClass SCXML_ONENTRY_TYPE = ScxmlPackage.eINSTANCE.getScxmlOnentryType();

        /**
         * The meta object literal for the '
         * <em><b>Scxml Core Executablecontent</b></em>' attribute list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_ONENTRY_TYPE__SCXML_CORE_EXECUTABLECONTENT = ScxmlPackage.eINSTANCE.getScxmlOnentryType_ScxmlCoreExecutablecontent();

        /**
         * The meta object literal for the '<em><b>Any</b></em>' attribute list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_ONENTRY_TYPE__ANY = ScxmlPackage.eINSTANCE.getScxmlOnentryType_Any();

        /**
         * The meta object literal for the '<em><b>Raise</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_ONENTRY_TYPE__RAISE = ScxmlPackage.eINSTANCE.getScxmlOnentryType_Raise();

        /**
         * The meta object literal for the '<em><b>If</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_ONENTRY_TYPE__IF = ScxmlPackage.eINSTANCE.getScxmlOnentryType_If();

        /**
         * The meta object literal for the '<em><b>Foreach</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_ONENTRY_TYPE__FOREACH = ScxmlPackage.eINSTANCE.getScxmlOnentryType_Foreach();

        /**
         * The meta object literal for the '<em><b>Send</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_ONENTRY_TYPE__SEND = ScxmlPackage.eINSTANCE.getScxmlOnentryType_Send();

        /**
         * The meta object literal for the '<em><b>Script</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_ONENTRY_TYPE__SCRIPT = ScxmlPackage.eINSTANCE.getScxmlOnentryType_Script();

        /**
         * The meta object literal for the '<em><b>Assign</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_ONENTRY_TYPE__ASSIGN = ScxmlPackage.eINSTANCE.getScxmlOnentryType_Assign();

        /**
         * The meta object literal for the '<em><b>Log</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_ONENTRY_TYPE__LOG = ScxmlPackage.eINSTANCE.getScxmlOnentryType_Log();

        /**
         * The meta object literal for the '<em><b>Cancel</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_ONENTRY_TYPE__CANCEL = ScxmlPackage.eINSTANCE.getScxmlOnentryType_Cancel();

        /**
         * The meta object literal for the '<em><b>Any Attribute</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_ONENTRY_TYPE__ANY_ATTRIBUTE = ScxmlPackage.eINSTANCE.getScxmlOnentryType_AnyAttribute();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlOnexitTypeImpl
         * <em>Onexit Type</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlOnexitTypeImpl
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlOnexitType()
         * @generated
         */
        EClass SCXML_ONEXIT_TYPE = ScxmlPackage.eINSTANCE.getScxmlOnexitType();

        /**
         * The meta object literal for the '
         * <em><b>Scxml Core Executablecontent</b></em>' attribute list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_ONEXIT_TYPE__SCXML_CORE_EXECUTABLECONTENT = ScxmlPackage.eINSTANCE.getScxmlOnexitType_ScxmlCoreExecutablecontent();

        /**
         * The meta object literal for the '<em><b>Any</b></em>' attribute list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_ONEXIT_TYPE__ANY = ScxmlPackage.eINSTANCE.getScxmlOnexitType_Any();

        /**
         * The meta object literal for the '<em><b>Raise</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_ONEXIT_TYPE__RAISE = ScxmlPackage.eINSTANCE.getScxmlOnexitType_Raise();

        /**
         * The meta object literal for the '<em><b>If</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_ONEXIT_TYPE__IF = ScxmlPackage.eINSTANCE.getScxmlOnexitType_If();

        /**
         * The meta object literal for the '<em><b>Foreach</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_ONEXIT_TYPE__FOREACH = ScxmlPackage.eINSTANCE.getScxmlOnexitType_Foreach();

        /**
         * The meta object literal for the '<em><b>Send</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_ONEXIT_TYPE__SEND = ScxmlPackage.eINSTANCE.getScxmlOnexitType_Send();

        /**
         * The meta object literal for the '<em><b>Script</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_ONEXIT_TYPE__SCRIPT = ScxmlPackage.eINSTANCE.getScxmlOnexitType_Script();

        /**
         * The meta object literal for the '<em><b>Assign</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_ONEXIT_TYPE__ASSIGN = ScxmlPackage.eINSTANCE.getScxmlOnexitType_Assign();

        /**
         * The meta object literal for the '<em><b>Log</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_ONEXIT_TYPE__LOG = ScxmlPackage.eINSTANCE.getScxmlOnexitType_Log();

        /**
         * The meta object literal for the '<em><b>Cancel</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_ONEXIT_TYPE__CANCEL = ScxmlPackage.eINSTANCE.getScxmlOnexitType_Cancel();

        /**
         * The meta object literal for the '<em><b>Any Attribute</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_ONEXIT_TYPE__ANY_ATTRIBUTE = ScxmlPackage.eINSTANCE.getScxmlOnexitType_AnyAttribute();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlParallelTypeImpl
         * <em>Parallel Type</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlParallelTypeImpl
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlParallelType()
         * @generated
         */
        EClass SCXML_PARALLEL_TYPE = ScxmlPackage.eINSTANCE.getScxmlParallelType();

        /**
         * The meta object literal for the '<em><b>Scxml Parallel Mix</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_PARALLEL_TYPE__SCXML_PARALLEL_MIX = ScxmlPackage.eINSTANCE.getScxmlParallelType_ScxmlParallelMix();

        /**
         * The meta object literal for the '<em><b>Onentry</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_PARALLEL_TYPE__ONENTRY = ScxmlPackage.eINSTANCE.getScxmlParallelType_Onentry();

        /**
         * The meta object literal for the '<em><b>Onexit</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_PARALLEL_TYPE__ONEXIT = ScxmlPackage.eINSTANCE.getScxmlParallelType_Onexit();

        /**
         * The meta object literal for the '<em><b>Transition</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_PARALLEL_TYPE__TRANSITION = ScxmlPackage.eINSTANCE.getScxmlParallelType_Transition();

        /**
         * The meta object literal for the '<em><b>State</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_PARALLEL_TYPE__STATE = ScxmlPackage.eINSTANCE.getScxmlParallelType_State();

        /**
         * The meta object literal for the '<em><b>Parallel</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_PARALLEL_TYPE__PARALLEL = ScxmlPackage.eINSTANCE.getScxmlParallelType_Parallel();

        /**
         * The meta object literal for the '<em><b>History</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_PARALLEL_TYPE__HISTORY = ScxmlPackage.eINSTANCE.getScxmlParallelType_History();

        /**
         * The meta object literal for the '<em><b>Datamodel</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_PARALLEL_TYPE__DATAMODEL = ScxmlPackage.eINSTANCE.getScxmlParallelType_Datamodel();

        /**
         * The meta object literal for the '<em><b>Invoke</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_PARALLEL_TYPE__INVOKE = ScxmlPackage.eINSTANCE.getScxmlParallelType_Invoke();

        /**
         * The meta object literal for the '<em><b>Any</b></em>' attribute list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_PARALLEL_TYPE__ANY = ScxmlPackage.eINSTANCE.getScxmlParallelType_Any();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_PARALLEL_TYPE__ID = ScxmlPackage.eINSTANCE.getScxmlParallelType_Id();

        /**
         * The meta object literal for the '<em><b>Any Attribute</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_PARALLEL_TYPE__ANY_ATTRIBUTE = ScxmlPackage.eINSTANCE.getScxmlParallelType_AnyAttribute();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlParamTypeImpl
         * <em>Param Type</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlParamTypeImpl
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlParamType()
         * @generated
         */
        EClass SCXML_PARAM_TYPE = ScxmlPackage.eINSTANCE.getScxmlParamType();

        /**
         * The meta object literal for the '<em><b>Scxml Extra Content</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_PARAM_TYPE__SCXML_EXTRA_CONTENT = ScxmlPackage.eINSTANCE.getScxmlParamType_ScxmlExtraContent();

        /**
         * The meta object literal for the '<em><b>Any</b></em>' attribute list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_PARAM_TYPE__ANY = ScxmlPackage.eINSTANCE.getScxmlParamType_Any();

        /**
         * The meta object literal for the '<em><b>Expr</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_PARAM_TYPE__EXPR = ScxmlPackage.eINSTANCE.getScxmlParamType_Expr();

        /**
         * The meta object literal for the '<em><b>Location</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_PARAM_TYPE__LOCATION = ScxmlPackage.eINSTANCE.getScxmlParamType_Location();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_PARAM_TYPE__NAME = ScxmlPackage.eINSTANCE.getScxmlParamType_Name();

        /**
         * The meta object literal for the '<em><b>Any Attribute</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_PARAM_TYPE__ANY_ATTRIBUTE = ScxmlPackage.eINSTANCE.getScxmlParamType_AnyAttribute();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlRaiseTypeImpl
         * <em>Raise Type</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlRaiseTypeImpl
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlRaiseType()
         * @generated
         */
        EClass SCXML_RAISE_TYPE = ScxmlPackage.eINSTANCE.getScxmlRaiseType();

        /**
         * The meta object literal for the '<em><b>Event</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_RAISE_TYPE__EVENT = ScxmlPackage.eINSTANCE.getScxmlRaiseType_Event();

        /**
         * The meta object literal for the '<em><b>Any Attribute</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_RAISE_TYPE__ANY_ATTRIBUTE = ScxmlPackage.eINSTANCE.getScxmlRaiseType_AnyAttribute();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlScriptTypeImpl
         * <em>Script Type</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlScriptTypeImpl
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlScriptType()
         * @generated
         */
        EClass SCXML_SCRIPT_TYPE = ScxmlPackage.eINSTANCE.getScxmlScriptType();

        /**
         * The meta object literal for the '<em><b>Mixed</b></em>' attribute
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_SCRIPT_TYPE__MIXED = ScxmlPackage.eINSTANCE.getScxmlScriptType_Mixed();

        /**
         * The meta object literal for the '<em><b>Scxml Extra Content</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_SCRIPT_TYPE__SCXML_EXTRA_CONTENT = ScxmlPackage.eINSTANCE.getScxmlScriptType_ScxmlExtraContent();

        /**
         * The meta object literal for the '<em><b>Any</b></em>' attribute list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_SCRIPT_TYPE__ANY = ScxmlPackage.eINSTANCE.getScxmlScriptType_Any();

        /**
         * The meta object literal for the '<em><b>Src</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_SCRIPT_TYPE__SRC = ScxmlPackage.eINSTANCE.getScxmlScriptType_Src();

        /**
         * The meta object literal for the '<em><b>Any Attribute</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_SCRIPT_TYPE__ANY_ATTRIBUTE = ScxmlPackage.eINSTANCE.getScxmlScriptType_AnyAttribute();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlScxmlTypeImpl
         * <em>Scxml Type</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlScxmlTypeImpl
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlScxmlType()
         * @generated
         */
        EClass SCXML_SCXML_TYPE = ScxmlPackage.eINSTANCE.getScxmlScxmlType();

        /**
         * The meta object literal for the '<em><b>Scxml Scxml Mix</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_SCXML_TYPE__SCXML_SCXML_MIX = ScxmlPackage.eINSTANCE.getScxmlScxmlType_ScxmlScxmlMix();

        /**
         * The meta object literal for the '<em><b>State</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_SCXML_TYPE__STATE = ScxmlPackage.eINSTANCE.getScxmlScxmlType_State();

        /**
         * The meta object literal for the '<em><b>Parallel</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_SCXML_TYPE__PARALLEL = ScxmlPackage.eINSTANCE.getScxmlScxmlType_Parallel();

        /**
         * The meta object literal for the '<em><b>Final</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_SCXML_TYPE__FINAL = ScxmlPackage.eINSTANCE.getScxmlScxmlType_Final();

        /**
         * The meta object literal for the '<em><b>Datamodel</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_SCXML_TYPE__DATAMODEL = ScxmlPackage.eINSTANCE.getScxmlScxmlType_Datamodel();

        /**
         * The meta object literal for the '<em><b>Script</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_SCXML_TYPE__SCRIPT = ScxmlPackage.eINSTANCE.getScxmlScxmlType_Script();

        /**
         * The meta object literal for the '<em><b>Any</b></em>' attribute list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_SCXML_TYPE__ANY = ScxmlPackage.eINSTANCE.getScxmlScxmlType_Any();

        /**
         * The meta object literal for the '<em><b>Binding</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_SCXML_TYPE__BINDING = ScxmlPackage.eINSTANCE.getScxmlScxmlType_Binding();

        /**
         * The meta object literal for the '<em><b>Datamodel1</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_SCXML_TYPE__DATAMODEL1 = ScxmlPackage.eINSTANCE.getScxmlScxmlType_Datamodel1();

        /**
         * The meta object literal for the '<em><b>Exmode</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_SCXML_TYPE__EXMODE = ScxmlPackage.eINSTANCE.getScxmlScxmlType_Exmode();

        /**
         * The meta object literal for the '<em><b>Initial</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_SCXML_TYPE__INITIAL = ScxmlPackage.eINSTANCE.getScxmlScxmlType_Initial();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_SCXML_TYPE__NAME = ScxmlPackage.eINSTANCE.getScxmlScxmlType_Name();

        /**
         * The meta object literal for the '<em><b>Version</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_SCXML_TYPE__VERSION = ScxmlPackage.eINSTANCE.getScxmlScxmlType_Version();

        /**
         * The meta object literal for the '<em><b>Any Attribute</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_SCXML_TYPE__ANY_ATTRIBUTE = ScxmlPackage.eINSTANCE.getScxmlScxmlType_AnyAttribute();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlSendTypeImpl
         * <em>Send Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlSendTypeImpl
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlSendType()
         * @generated
         */
        EClass SCXML_SEND_TYPE = ScxmlPackage.eINSTANCE.getScxmlSendType();

        /**
         * The meta object literal for the '<em><b>Scxml Send Mix</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_SEND_TYPE__SCXML_SEND_MIX = ScxmlPackage.eINSTANCE.getScxmlSendType_ScxmlSendMix();

        /**
         * The meta object literal for the '<em><b>Content</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_SEND_TYPE__CONTENT = ScxmlPackage.eINSTANCE.getScxmlSendType_Content();

        /**
         * The meta object literal for the '<em><b>Param</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_SEND_TYPE__PARAM = ScxmlPackage.eINSTANCE.getScxmlSendType_Param();

        /**
         * The meta object literal for the '<em><b>Any</b></em>' attribute list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_SEND_TYPE__ANY = ScxmlPackage.eINSTANCE.getScxmlSendType_Any();

        /**
         * The meta object literal for the '<em><b>Delay</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_SEND_TYPE__DELAY = ScxmlPackage.eINSTANCE.getScxmlSendType_Delay();

        /**
         * The meta object literal for the '<em><b>Delayexpr</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_SEND_TYPE__DELAYEXPR = ScxmlPackage.eINSTANCE.getScxmlSendType_Delayexpr();

        /**
         * The meta object literal for the '<em><b>Event</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_SEND_TYPE__EVENT = ScxmlPackage.eINSTANCE.getScxmlSendType_Event();

        /**
         * The meta object literal for the '<em><b>Eventexpr</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_SEND_TYPE__EVENTEXPR = ScxmlPackage.eINSTANCE.getScxmlSendType_Eventexpr();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_SEND_TYPE__ID = ScxmlPackage.eINSTANCE.getScxmlSendType_Id();

        /**
         * The meta object literal for the '<em><b>Idlocation</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_SEND_TYPE__IDLOCATION = ScxmlPackage.eINSTANCE.getScxmlSendType_Idlocation();

        /**
         * The meta object literal for the '<em><b>Namelist</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_SEND_TYPE__NAMELIST = ScxmlPackage.eINSTANCE.getScxmlSendType_Namelist();

        /**
         * The meta object literal for the '<em><b>Target</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_SEND_TYPE__TARGET = ScxmlPackage.eINSTANCE.getScxmlSendType_Target();

        /**
         * The meta object literal for the '<em><b>Targetexpr</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_SEND_TYPE__TARGETEXPR = ScxmlPackage.eINSTANCE.getScxmlSendType_Targetexpr();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_SEND_TYPE__TYPE = ScxmlPackage.eINSTANCE.getScxmlSendType_Type();

        /**
         * The meta object literal for the '<em><b>Typeexpr</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_SEND_TYPE__TYPEEXPR = ScxmlPackage.eINSTANCE.getScxmlSendType_Typeexpr();

        /**
         * The meta object literal for the '<em><b>Any Attribute</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_SEND_TYPE__ANY_ATTRIBUTE = ScxmlPackage.eINSTANCE.getScxmlSendType_AnyAttribute();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlStateTypeImpl
         * <em>State Type</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlStateTypeImpl
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlStateType()
         * @generated
         */
        EClass SCXML_STATE_TYPE = ScxmlPackage.eINSTANCE.getScxmlStateType();

        /**
         * The meta object literal for the '<em><b>Scxml State Mix</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_STATE_TYPE__SCXML_STATE_MIX = ScxmlPackage.eINSTANCE.getScxmlStateType_ScxmlStateMix();

        /**
         * The meta object literal for the '<em><b>Onentry</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_STATE_TYPE__ONENTRY = ScxmlPackage.eINSTANCE.getScxmlStateType_Onentry();

        /**
         * The meta object literal for the '<em><b>Onexit</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_STATE_TYPE__ONEXIT = ScxmlPackage.eINSTANCE.getScxmlStateType_Onexit();

        /**
         * The meta object literal for the '<em><b>Transition</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_STATE_TYPE__TRANSITION = ScxmlPackage.eINSTANCE.getScxmlStateType_Transition();

        /**
         * The meta object literal for the '<em><b>Initial</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_STATE_TYPE__INITIAL = ScxmlPackage.eINSTANCE.getScxmlStateType_Initial();

        /**
         * The meta object literal for the '<em><b>State</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_STATE_TYPE__STATE = ScxmlPackage.eINSTANCE.getScxmlStateType_State();

        /**
         * The meta object literal for the '<em><b>Parallel</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_STATE_TYPE__PARALLEL = ScxmlPackage.eINSTANCE.getScxmlStateType_Parallel();

        /**
         * The meta object literal for the '<em><b>Final</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_STATE_TYPE__FINAL = ScxmlPackage.eINSTANCE.getScxmlStateType_Final();

        /**
         * The meta object literal for the '<em><b>History</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_STATE_TYPE__HISTORY = ScxmlPackage.eINSTANCE.getScxmlStateType_History();

        /**
         * The meta object literal for the '<em><b>Datamodel</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_STATE_TYPE__DATAMODEL = ScxmlPackage.eINSTANCE.getScxmlStateType_Datamodel();

        /**
         * The meta object literal for the '<em><b>Invoke</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_STATE_TYPE__INVOKE = ScxmlPackage.eINSTANCE.getScxmlStateType_Invoke();

        /**
         * The meta object literal for the '<em><b>Any</b></em>' attribute list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_STATE_TYPE__ANY = ScxmlPackage.eINSTANCE.getScxmlStateType_Any();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_STATE_TYPE__ID = ScxmlPackage.eINSTANCE.getScxmlStateType_Id();

        /**
         * The meta object literal for the '<em><b>Initial1</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_STATE_TYPE__INITIAL1 = ScxmlPackage.eINSTANCE.getScxmlStateType_Initial1();

        /**
         * The meta object literal for the '<em><b>Any Attribute</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_STATE_TYPE__ANY_ATTRIBUTE = ScxmlPackage.eINSTANCE.getScxmlStateType_AnyAttribute();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlTransitionTypeImpl
         * <em>Transition Type</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlTransitionTypeImpl
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getScxmlTransitionType()
         * @generated
         */
        EClass SCXML_TRANSITION_TYPE = ScxmlPackage.eINSTANCE.getScxmlTransitionType();

        /**
         * The meta object literal for the '
         * <em><b>Scxml Core Executablecontent</b></em>' attribute list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_TRANSITION_TYPE__SCXML_CORE_EXECUTABLECONTENT = ScxmlPackage.eINSTANCE.getScxmlTransitionType_ScxmlCoreExecutablecontent();

        /**
         * The meta object literal for the '<em><b>Any</b></em>' attribute list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_TRANSITION_TYPE__ANY = ScxmlPackage.eINSTANCE.getScxmlTransitionType_Any();

        /**
         * The meta object literal for the '<em><b>Raise</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_TRANSITION_TYPE__RAISE = ScxmlPackage.eINSTANCE.getScxmlTransitionType_Raise();

        /**
         * The meta object literal for the '<em><b>If</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_TRANSITION_TYPE__IF = ScxmlPackage.eINSTANCE.getScxmlTransitionType_If();

        /**
         * The meta object literal for the '<em><b>Foreach</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_TRANSITION_TYPE__FOREACH = ScxmlPackage.eINSTANCE.getScxmlTransitionType_Foreach();

        /**
         * The meta object literal for the '<em><b>Send</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_TRANSITION_TYPE__SEND = ScxmlPackage.eINSTANCE.getScxmlTransitionType_Send();

        /**
         * The meta object literal for the '<em><b>Script</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_TRANSITION_TYPE__SCRIPT = ScxmlPackage.eINSTANCE.getScxmlTransitionType_Script();

        /**
         * The meta object literal for the '<em><b>Assign</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_TRANSITION_TYPE__ASSIGN = ScxmlPackage.eINSTANCE.getScxmlTransitionType_Assign();

        /**
         * The meta object literal for the '<em><b>Log</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_TRANSITION_TYPE__LOG = ScxmlPackage.eINSTANCE.getScxmlTransitionType_Log();

        /**
         * The meta object literal for the '<em><b>Cancel</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SCXML_TRANSITION_TYPE__CANCEL = ScxmlPackage.eINSTANCE.getScxmlTransitionType_Cancel();

        /**
         * The meta object literal for the '<em><b>Cond</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_TRANSITION_TYPE__COND = ScxmlPackage.eINSTANCE.getScxmlTransitionType_Cond();

        /**
         * The meta object literal for the '<em><b>Event</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_TRANSITION_TYPE__EVENT = ScxmlPackage.eINSTANCE.getScxmlTransitionType_Event();

        /**
         * The meta object literal for the '<em><b>Target</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_TRANSITION_TYPE__TARGET = ScxmlPackage.eINSTANCE.getScxmlTransitionType_Target();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_TRANSITION_TYPE__TYPE = ScxmlPackage.eINSTANCE.getScxmlTransitionType_Type();

        /**
         * The meta object literal for the '<em><b>Any Attribute</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SCXML_TRANSITION_TYPE__ANY_ATTRIBUTE = ScxmlPackage.eINSTANCE.getScxmlTransitionType_AnyAttribute();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.scxml.AssignTypeDatatype
         * <em>Assign Type Datatype</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.scxml.AssignTypeDatatype
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getAssignTypeDatatype()
         * @generated
         */
        EEnum ASSIGN_TYPE_DATATYPE = ScxmlPackage.eINSTANCE.getAssignTypeDatatype();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.scxml.BindingDatatype
         * <em>Binding Datatype</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.scxml.BindingDatatype
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getBindingDatatype()
         * @generated
         */
        EEnum BINDING_DATATYPE = ScxmlPackage.eINSTANCE.getBindingDatatype();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.scxml.BooleanDatatype
         * <em>Boolean Datatype</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.scxml.BooleanDatatype
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getBooleanDatatype()
         * @generated
         */
        EEnum BOOLEAN_DATATYPE = ScxmlPackage.eINSTANCE.getBooleanDatatype();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.scxml.ExmodeDatatype
         * <em>Exmode Datatype</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.scxml.ExmodeDatatype
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getExmodeDatatype()
         * @generated
         */
        EEnum EXMODE_DATATYPE = ScxmlPackage.eINSTANCE.getExmodeDatatype();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.scxml.HistoryTypeDatatype
         * <em>History Type Datatype</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.scxml.HistoryTypeDatatype
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getHistoryTypeDatatype()
         * @generated
         */
        EEnum HISTORY_TYPE_DATATYPE = ScxmlPackage.eINSTANCE.getHistoryTypeDatatype();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.scxml.TransitionTypeDatatype
         * <em>Transition Type Datatype</em>}' enum. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.scxml.TransitionTypeDatatype
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getTransitionTypeDatatype()
         * @generated
         */
        EEnum TRANSITION_TYPE_DATATYPE = ScxmlPackage.eINSTANCE.getTransitionTypeDatatype();

        /**
         * The meta object literal for the '<em>Assign Type Datatype Object</em>
         * ' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.scxml.AssignTypeDatatype
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getAssignTypeDatatypeObject()
         * @generated
         */
        EDataType ASSIGN_TYPE_DATATYPE_OBJECT = ScxmlPackage.eINSTANCE.getAssignTypeDatatypeObject();

        /**
         * The meta object literal for the '<em>Binding Datatype Object</em>'
         * data type. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.scxml.BindingDatatype
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getBindingDatatypeObject()
         * @generated
         */
        EDataType BINDING_DATATYPE_OBJECT = ScxmlPackage.eINSTANCE.getBindingDatatypeObject();

        /**
         * The meta object literal for the '<em>Boolean Datatype Object</em>'
         * data type. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.scxml.BooleanDatatype
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getBooleanDatatypeObject()
         * @generated
         */
        EDataType BOOLEAN_DATATYPE_OBJECT = ScxmlPackage.eINSTANCE.getBooleanDatatypeObject();

        /**
         * The meta object literal for the '<em>Cond Lang Datatype</em>' data
         * type. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see java.lang.String
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getCondLangDatatype()
         * @generated
         */
        EDataType COND_LANG_DATATYPE = ScxmlPackage.eINSTANCE.getCondLangDatatype();

        /**
         * The meta object literal for the '<em>Duration Datatype</em>' data
         * type. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see java.lang.String
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getDurationDatatype()
         * @generated
         */
        EDataType DURATION_DATATYPE = ScxmlPackage.eINSTANCE.getDurationDatatype();

        /**
         * The meta object literal for the '<em>Event Type Datatype</em>' data
         * type. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see java.lang.String
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getEventTypeDatatype()
         * @generated
         */
        EDataType EVENT_TYPE_DATATYPE = ScxmlPackage.eINSTANCE.getEventTypeDatatype();

        /**
         * The meta object literal for the '<em>Event Types Datatype</em>' data
         * type. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see java.lang.String
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getEventTypesDatatype()
         * @generated
         */
        EDataType EVENT_TYPES_DATATYPE = ScxmlPackage.eINSTANCE.getEventTypesDatatype();

        /**
         * The meta object literal for the '<em>Exmode Datatype Object</em>'
         * data type. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.scxml.ExmodeDatatype
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getExmodeDatatypeObject()
         * @generated
         */
        EDataType EXMODE_DATATYPE_OBJECT = ScxmlPackage.eINSTANCE.getExmodeDatatypeObject();

        /**
         * The meta object literal for the '
         * <em>History Type Datatype Object</em>' data type. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.scxml.HistoryTypeDatatype
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getHistoryTypeDatatypeObject()
         * @generated
         */
        EDataType HISTORY_TYPE_DATATYPE_OBJECT = ScxmlPackage.eINSTANCE.getHistoryTypeDatatypeObject();

        /**
         * The meta object literal for the '<em>Integer Datatype</em>' data
         * type. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see java.math.BigInteger
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getIntegerDatatype()
         * @generated
         */
        EDataType INTEGER_DATATYPE = ScxmlPackage.eINSTANCE.getIntegerDatatype();

        /**
         * The meta object literal for the '<em>Loc Lang Datatype</em>' data
         * type. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see java.lang.String
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getLocLangDatatype()
         * @generated
         */
        EDataType LOC_LANG_DATATYPE = ScxmlPackage.eINSTANCE.getLocLangDatatype();

        /**
         * The meta object literal for the '
         * <em>Transition Type Datatype Object</em>' data type. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.scxml.TransitionTypeDatatype
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getTransitionTypeDatatypeObject()
         * @generated
         */
        EDataType TRANSITION_TYPE_DATATYPE_OBJECT = ScxmlPackage.eINSTANCE.getTransitionTypeDatatypeObject();

        /**
         * The meta object literal for the '<em>URI Datatype</em>' data type.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see java.lang.String
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getURIDatatype()
         * @generated
         */
        EDataType URI_DATATYPE = ScxmlPackage.eINSTANCE.getURIDatatype();

        /**
         * The meta object literal for the '<em>Value Lang Datatype</em>' data
         * type. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see java.lang.String
         * @see org.eclipse.sirius.tests.sample.scxml.impl.ScxmlPackageImpl#getValueLangDatatype()
         * @generated
         */
        EDataType VALUE_LANG_DATATYPE = ScxmlPackage.eINSTANCE.getValueLangDatatype();

    }

} // ScxmlPackage
